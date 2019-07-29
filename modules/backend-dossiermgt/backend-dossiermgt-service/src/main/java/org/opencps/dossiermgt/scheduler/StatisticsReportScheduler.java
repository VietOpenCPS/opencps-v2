package org.opencps.dossiermgt.scheduler;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.SchedulerEntryImpl;
import com.liferay.portal.kernel.scheduler.SchedulerException;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.scheduler.StorageTypeAware;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.constants.PublishQueueTerm;
import org.opencps.dossiermgt.constants.ServerConfigTerm;
import org.opencps.dossiermgt.lgsp.model.MResult;
import org.opencps.dossiermgt.lgsp.model.Mtoken;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.OpencpsDossierStatistic;
import org.opencps.dossiermgt.model.OpencpsVotingStatistic;
import org.opencps.dossiermgt.rest.utils.LGSPRestClient;
import org.opencps.dossiermgt.rest.utils.OpenCPSConverter;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.OpencpsDossierStatisticLocalServiceUtil;
import org.opencps.dossiermgt.service.OpencpsVotingStatisticLocalServiceUtil;
import org.opencps.dossiermgt.service.PublishQueueLocalServiceUtil;
import org.opencps.kernel.scheduler.StorageTypeAwareSchedulerEntryImpl;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

//@Component(immediate = true, service = StatisticsReportScheduler.class)
public class StatisticsReportScheduler extends BaseMessageListener {
	private volatile boolean isRunning = false;
	@Override
	protected void doReceive(Message message) throws Exception {
		if (!isRunning) {
			isRunning = true;
		}
		else {
			return;
		}
		try {
			_log.info("OpenCPS PUBLISH STATISTICS IS  : " + APIDateTimeUtils.convertDateToString(new Date()));
			
			Date now = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(now);
	
			int month = c.get(Calendar.MONTH) + 1;
			int year = c.get(Calendar.YEAR);
			ServiceContext context = new ServiceContext();
			Dossier dossier = DossierLocalServiceUtil.fetchOnePublicService();
			List<ServerConfig> lstScs = ServerConfigLocalServiceUtil.getByProtocol(dossier.getGroupId(), ServerConfigTerm.LGSP_PROTOCOL);
			for (ServerConfig sc : lstScs) {
				try {
					LGSPRestClient client = LGSPRestClient.fromJSONObject(JSONFactoryUtil.createJSONObject(sc.getConfigs()));
					Mtoken token = client.getToken();
					if (Validator.isNotNull(token.getAccessToken())) {
						MResult result = client.updateStatisticsMonth(token.getAccessToken(), dossier.getGroupId(), month, year);
						OpencpsDossierStatistic statistic = OpencpsDossierStatisticLocalServiceUtil.fetchByG_M_Y_G_D(dossier.getGroupId(), month, year, null, null);
						if (statistic != null) {
							JSONObject lgspObj = OpenCPSConverter.convertStatisticsToLGSPJSON(statistic);
							if (client.isWriteLog()) {
								JSONObject messageObj = JSONFactoryUtil.createJSONObject();
								messageObj.put("token", token.getAccessToken());
								messageObj.put("MStatistic", lgspObj.toJSONString());
								String messageText = messageObj.toJSONString();
								String acknowlegement = JSONFactoryUtil.looseSerialize(result);
								PublishQueueLocalServiceUtil.updatePublishQueue(
										sc.getGroupId(), 0l, 3, 0l, 
										sc.getServerNo(), StringPool.BLANK, PublishQueueTerm.STATE_RECEIVED_ACK, 0, 
										messageText, acknowlegement,
										context);	
							}
						}
						
						MResult resultVoting = client.updateVotingStatisticsMonth(token.getAccessToken(), dossier.getGroupId(), month, year);
						OpencpsVotingStatistic vtstatistic = OpencpsVotingStatisticLocalServiceUtil.fetchByG_M_Y_G_D_VC(sc.getGroupId(), month, year, StringPool.BLANK, StringPool.BLANK, StringPool.BLANK);
						if (vtstatistic != null) {
							JSONObject lgspVotingObj = OpenCPSConverter.convertVotingStatisticsToLGSPJSON(vtstatistic);
							if (client.isWriteLog()) {
								JSONObject messageObj = JSONFactoryUtil.createJSONObject();
								messageObj.put("token", token.getAccessToken());
								messageObj.put("MVotes", lgspVotingObj.toJSONString());
								String messageText = messageObj.toJSONString();
								String acknowlegement = JSONFactoryUtil.looseSerialize(resultVoting);
								
								PublishQueueLocalServiceUtil.updatePublishQueue(
										sc.getGroupId(), 0l, 4, 0l, 
										sc.getServerNo(), StringPool.BLANK, PublishQueueTerm.STATE_RECEIVED_ACK, 0, 
										messageText, acknowlegement,
										context);	
							}
						}
					}
				} catch (PortalException e) {
					_log.debug(e);
				}
			}
			_log.info("OpenCPS PUBlISH STATISTICS HAS BEEN DONE : " + APIDateTimeUtils.convertDateToString(new Date()));
		}
		catch (Exception e) {
			_log.debug(e);
		}
		isRunning = false;
	}
	
	  @Activate
	  @Modified
	  protected void activate(Map<String,Object> properties) throws SchedulerException {
		  String listenerClass = getClass().getName();
		  Trigger jobTrigger = _triggerFactory.createTrigger(listenerClass, listenerClass, new Date(), null, 10, TimeUnit.MINUTE);

		  _schedulerEntryImpl = new SchedulerEntryImpl(getClass().getName(), jobTrigger);
		  _schedulerEntryImpl = new StorageTypeAwareSchedulerEntryImpl(_schedulerEntryImpl, StorageType.MEMORY_CLUSTERED);
		  
//		  _schedulerEntryImpl.setTrigger(jobTrigger);

		  if (_initialized) {
			  deactivate();
		  }

	    _schedulerEngineHelper.register(this, _schedulerEntryImpl, DestinationNames.SCHEDULER_DISPATCH);
	    _initialized = true;
	  }
	  
	@Deactivate
	protected void deactivate() {
		if (_initialized) {
			try {
				_schedulerEngineHelper.unschedule(_schedulerEntryImpl, getStorageType());
		    } catch (SchedulerException se) {
		        if (_log.isWarnEnabled()) {
		        	_log.warn("Unable to unschedule trigger", se);
		        }
		    }

		      _schedulerEngineHelper.unregister(this);
		}
		_initialized = false;
	}

	/**
	 * getStorageType: Utility method to get the storage type from the scheduler entry wrapper.
	 * @return StorageType The storage type to use.
	*/
	protected StorageType getStorageType() {
	    if (_schedulerEntryImpl instanceof StorageTypeAware) {
	    	return ((StorageTypeAware) _schedulerEntryImpl).getStorageType();
	    }
	    
	    return StorageType.MEMORY_CLUSTERED;
	}
	  
	/**
	   * setModuleServiceLifecycle: So this requires some explanation...
	   * 
	   * OSGi will start a component once all of it's dependencies are satisfied.  However, there
	   * are times where you want to hold off until the portal is completely ready to go.
	   * 
	   * This reference declaration is waiting for the ModuleServiceLifecycle's PORTAL_INITIALIZED
	   * component which will not be available until, surprise surprise, the portal has finished
	   * initializing.
	   * 
	   * With this reference, this component activation waits until portal initialization has completed.
	   * @param moduleServiceLifecycle
	   */
	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	@Reference(unbind = "-")
	protected void setTriggerFactory(TriggerFactory triggerFactory) {
		_triggerFactory = triggerFactory;
	}

	@Reference(unbind = "-")
	protected void setSchedulerEngineHelper(SchedulerEngineHelper schedulerEngineHelper) {
		_schedulerEngineHelper = schedulerEngineHelper;
	}
	
	private SchedulerEngineHelper _schedulerEngineHelper;
	private TriggerFactory _triggerFactory;
	private volatile boolean _initialized;
	private SchedulerEntryImpl _schedulerEntryImpl = null;

	private Log _log = LogFactoryUtil.getLog(StatisticsReportScheduler.class);
	
}
