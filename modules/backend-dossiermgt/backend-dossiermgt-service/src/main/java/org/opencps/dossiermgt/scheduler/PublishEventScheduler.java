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

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.action.util.DossierMgtUtils;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.PublishQueueTerm;
import org.opencps.dossiermgt.constants.ServerConfigTerm;
import org.opencps.dossiermgt.lgsp.model.MResult;
import org.opencps.dossiermgt.lgsp.model.Mtoken;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.PublishQueue;
import org.opencps.dossiermgt.rest.model.DossierDetailModel;
import org.opencps.dossiermgt.rest.utils.LGSPRestClient;
import org.opencps.dossiermgt.rest.utils.OpenCPSConverter;
import org.opencps.dossiermgt.rest.utils.OpenCPSRestClient;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.PublishQueueLocalServiceUtil;
import org.opencps.kernel.scheduler.StorageTypeAwareSchedulerEntryImpl;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, service = PublishEventScheduler.class)
public class PublishEventScheduler extends BaseMessageListener {
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
			_log.info("OpenCPS PUBLISH DOSSIERS IS  : " + APIDateTimeUtils.convertDateToString(new Date()));
			
			List<PublishQueue> lstPqs = PublishQueueLocalServiceUtil.getByStatus(PublishQueueTerm.STATE_WAITING_SYNC, 0, 10);
			_log.info("lstPqs  : " + lstPqs.size());
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
					_log.debug(e);
				}
			}
			_log.info("OpenCPS PUBlISH DOSSIERS HAS BEEN DONE : " + APIDateTimeUtils.convertDateToString(new Date()));
		}
		catch (Exception e) {
			_log.debug(e);
		}
		isRunning = false;
	}
	
	private boolean processPublish(PublishQueue pq) {
		long dossierId = pq.getDossierId();
		Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
		if (dossier.getOriginDossierId() != 0 || Validator.isNotNull(dossier.getOriginDossierNo())) {
			return true;
		}
		long groupId = pq.getGroupId();
		ServerConfig sc = ServerConfigLocalServiceUtil.getByCode(groupId, pq.getServerNo());
		
		if (ServerConfigTerm.PUBLISH_PROTOCOL.equals(sc.getProtocol())) {
			try {
				if (dossier != null && dossier.getOriginality() > 0) {
					OpenCPSRestClient client = OpenCPSRestClient.fromJSONObject(JSONFactoryUtil.createJSONObject(sc.getConfigs()));
					DossierDetailModel result = client.publishDossier(OpenCPSConverter.convertDossierPublish(DossierMgtUtils.convertDossierToJSON(dossier)));
					if (client.isWriteLog()) {
						String messageText = DossierMgtUtils.convertDossierToJSON(dossier).toJSONString();
						String acknowlegement = JSONFactoryUtil.looseSerialize(result);
						pq.setMessageText(messageText);
						pq.setAcknowlegement(acknowlegement);
						PublishQueueLocalServiceUtil.updatePublishQueue(pq);
					}
					if (result.getDossierId() != null) {
						return true;
					}
					else {
						return false;
					}
				}
				else {
					return true;
				}
			} catch (JSONException e) {
				_log.error(e);
			}			
		}
		else if (ServerConfigTerm.LGSP_PROTOCOL.equals(sc.getProtocol())) {
			try {
				if (dossier != null && dossier.getOriginality() > 0) {
					LGSPRestClient client = LGSPRestClient.fromJSONObject(JSONFactoryUtil.createJSONObject(sc.getConfigs()));
					Mtoken token = client.getToken();
					if (Validator.isNotNull(token.getAccessToken())) {
						JSONObject dossierObj = DossierMgtUtils.convertDossierToJSON(dossier);
						MResult result = client.publishDossier(token.getAccessToken(), OpenCPSConverter.convertDossierPublish(DossierMgtUtils.convertDossierToJSON(dossier)));
						if (client.isWriteLog()) {
							JSONObject messageObj = JSONFactoryUtil.createJSONObject();
							messageObj.put("token", token.getAccessToken());
							messageObj.put("MSyncDocument", JSONFactoryUtil.looseSerialize(OpenCPSConverter.convertDossierToLGSPJSON(OpenCPSConverter.convertDossierPublish(dossierObj))));
							String messageText = messageObj.toJSONString();
							String acknowlegement = JSONFactoryUtil.looseSerialize(result);
							pq.setMessageText(messageText);
							pq.setAcknowlegement(acknowlegement);
							pq.setPublishType(1);
							PublishQueueLocalServiceUtil.updatePublishQueue(pq);							
						}
						if (result.getStatus() != 200) {
							return false;
						}
						else {
							ServiceContext context = new ServiceContext();
							MResult result2 = client.postDocumentTrace(token.getAccessToken(), dossierObj.getLong(DossierTerm.DOSSIER_ID));	
							JSONObject messageObj = JSONFactoryUtil.createJSONObject();
							messageObj.put("token", token.getAccessToken());
							JSONObject lgspObj = OpenCPSConverter.convertToDocumentTraces(dossierId);
							messageObj.put("MDocumentTraces", lgspObj.toJSONString());
							String messageText = messageObj.toJSONString();
							String acknowlegement = JSONFactoryUtil.looseSerialize(result2);
							PublishQueueLocalServiceUtil.updatePublishQueue(
									sc.getGroupId(), 0l, 2, 0l, 
									sc.getServerNo(), StringPool.BLANK, PublishQueueTerm.STATE_RECEIVED_ACK, 0, 
									messageText, acknowlegement,
									context);	
							
							if (result2.getStatus() != 200) {
								return false;
							}
							else {
								return true;
							}
						}
					}
					else {
						return false;
					}
				}
				else {
					return true;
				}
			} catch (JSONException e) {
				_log.error(e);
			} catch (PortalException e) {
				_log.error(e);
			}					
		}
		return true;
	}
	
	  @Activate
	  @Modified
	  protected void activate(Map<String,Object> properties) throws SchedulerException {
		  String listenerClass = getClass().getName();
		  Trigger jobTrigger = _triggerFactory.createTrigger(listenerClass, listenerClass, new Date(), null, 5, TimeUnit.SECOND);

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

	private Log _log = LogFactoryUtil.getLog(PublishEventScheduler.class);
	
}
