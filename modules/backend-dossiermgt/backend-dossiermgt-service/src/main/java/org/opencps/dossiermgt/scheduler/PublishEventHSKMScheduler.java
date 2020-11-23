package org.opencps.dossiermgt.scheduler;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.*;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.action.TTTTIntegrationAction;
import org.opencps.dossiermgt.action.impl.DVCQGIntegrationActionImpl;
import org.opencps.dossiermgt.action.impl.TTTTIntegrationImpl;
import org.opencps.dossiermgt.action.util.DossierMgtUtils;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.PublishQueueTerm;
import org.opencps.dossiermgt.constants.ServerConfigTerm;
import org.opencps.dossiermgt.lgsp.model.MResult;
import org.opencps.dossiermgt.lgsp.model.Mtoken;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.PaymentFile;
import org.opencps.dossiermgt.model.PublishQueue;
import org.opencps.dossiermgt.rest.model.DossierDetailModel;
import org.opencps.dossiermgt.rest.model.PaymentFileInputModel;
import org.opencps.dossiermgt.rest.utils.LGSPRestClient;
import org.opencps.dossiermgt.rest.utils.OpenCPSConverter;
import org.opencps.dossiermgt.rest.utils.OpenCPSRestClient;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.PaymentFileLocalServiceUtil;
import org.opencps.dossiermgt.service.PublishQueueLocalServiceUtil;
import org.opencps.kernel.scheduler.StorageTypeAwareSchedulerEntryImpl;
import org.osgi.service.component.annotations.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Component(immediate = true, service = PublishEventHSKMScheduler.class)
public class PublishEventHSKMScheduler extends BaseMessageListener {
	private volatile boolean isRunning = false;
	private static final Boolean ENABLE_JOB = Validator.isNotNull(PropsUtil.get("org.opencps.synchskm.enable"))
			? Boolean.valueOf(PropsUtil.get("org.opencps.synchskm.enable")) : true;
	@Override
	protected void doReceive(Message message) throws Exception {
		
		_log.debug("===PublishEventHSKMScheduler doReceive===");
		
		_log.debug("=== isRunning==="+isRunning);

		if (isRunning) {
			
			_log.debug("===Return===");
			return;
		}
		else {
			
			isRunning = true;
		}
		try {
			_log.debug("TASK SEND HSKM to DVCQG: " + APIDateTimeUtils.convertDateToString(new Date()));
			
			List<PublishQueue> lstPqs = PublishQueueLocalServiceUtil.getByStatusesAndServerNo(new int[] {
					PublishQueueTerm.STATE_WAITING_SYNC,
					PublishQueueTerm.STATE_ALREADY_SENT},
					ServerConfigTerm.DVCQG_INTEGRATION, 0, 10);

			_log.debug("lstPqs  : " + lstPqs.size());

			for (PublishQueue pq : lstPqs) {
				try {
					pq.setStatus(PublishQueueTerm.STATE_ALREADY_SENT);
					pq = PublishQueueLocalServiceUtil.updatePublishQueue(pq);
					boolean result = processPublish(pq);
					if (!result) {
						int retry = pq.getRetry();
						if (retry < PublishQueueTerm.MAX_RETRY) {
							pq.setRetry(pq.getRetry() + 1);
							pq.setStatus(PublishQueueTerm.STATE_WAITING_SYNC);
							PublishQueueLocalServiceUtil.updatePublishQueue(pq);					
						}
						else {
							pq.setRetry(0);
							pq.setStatus(PublishQueueTerm.STATE_ACK_ERROR);
							PublishQueueLocalServiceUtil.updatePublishQueue(pq);
						}				
					}
					else {
						pq.setStatus(PublishQueueTerm.STATE_RECEIVED_ACK);
						PublishQueueLocalServiceUtil.updatePublishQueue(pq);				
		//				PublishQueueLocalServiceUtil.removePublishQueue(pq.getPublishQueueId());
					}
				}
				catch (Exception e) {
					_log.error(e);
				}
			}
			_log.debug("TASK SEND HSKM to DVCQG HAS BEEN DONE : " + APIDateTimeUtils.convertDateToString(new Date()));
		}
		catch (Exception e) {
			_log.error(e);
		}
		isRunning = false;
	}
	
	private boolean processPublish(PublishQueue pq) {
		long dossierId = pq.getDossierId();
		Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
		if (Validator.isNotNull(dossier) && Validator.isNotNull(dossier.getOriginDossierId()) && (dossier.getOriginDossierId() != 0 || Validator.isNotNull(dossier.getOriginDossierNo()))) {
			return true;
		}
		_log.info("pq: "+JSONFactoryUtil.looseSerialize(pq));
		long groupId = pq.getGroupId();
		ServerConfig sc = ServerConfigLocalServiceUtil.getByCode(groupId, pq.getServerNo());

		//add by TrungNt
		if (ServerConfigTerm.DVCQG_INTEGRATION.equals(sc.getProtocol())) {
			
			try {
				DVCQGIntegrationActionImpl actionImpl = new DVCQGIntegrationActionImpl();
				
				JSONObject result = actionImpl.syncDossierAndDossierStatus(groupId, dossier, null);

				if(Validator.isNull(result)) {
					return false;
				}

				_log.debug("result DVCQG: "+result);
				if(result.has("error_code") && "0".equals(result.getString("error_code"))) {
					PublishQueueLocalServiceUtil.updatePublishQueue(
							sc.getGroupId(), pq.getPublishQueueId(), 2, dossier.getDossierId(), 
							sc.getServerNo(), StringPool.BLANK, PublishQueueTerm.STATE_RECEIVED_ACK, 0, 
							String.valueOf(dossier.getDossierNo()), result.toJSONString(),
							new ServiceContext());	
					return true;
				}
			} catch (Exception e) {
				_log.error(e);
			}
			return false;
		}

		return true;
	}
	
	  @Activate
	  @Modified
	  protected void activate(Map<String,Object> properties) throws SchedulerException {
		  String listenerClass = getClass().getName();
		  Trigger jobTrigger = _triggerFactory.createTrigger(listenerClass, listenerClass, new Date(), null, 60, TimeUnit.SECOND);

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
		isRunning = false;
	}

	/**
	 * getStorageType: Utility method to get the storage type from the scheduler entry wrapper.
	 * @return StorageType The storage type to use.
	*/
	protected StorageType getStorageType() {
	    if (_schedulerEntryImpl instanceof StorageTypeAware) {
	    	return ((StorageTypeAware) _schedulerEntryImpl).getStorageType();
	    }
	    
	    return StorageType.PERSISTED;
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

	private Log _log = LogFactoryUtil.getLog(PublishEventHSKMScheduler.class);
	
}
