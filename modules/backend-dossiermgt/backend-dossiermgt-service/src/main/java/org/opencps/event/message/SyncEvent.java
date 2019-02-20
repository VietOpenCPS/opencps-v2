package org.opencps.event.message;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.SubscriptionLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.opencps.communication.model.Notificationtemplate;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.NotificationtemplateLocalServiceUtil;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.action.util.DossierActionUtils;
import org.opencps.dossiermgt.action.util.DossierMgtUtils;
import org.opencps.dossiermgt.constants.ActionConfigTerm;
import org.opencps.dossiermgt.constants.DossierDocumentTerm;
import org.opencps.dossiermgt.constants.DossierFileTerm;
import org.opencps.dossiermgt.constants.DossierSyncTerm;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.PublishQueueTerm;
import org.opencps.dossiermgt.constants.ServerConfigTerm;
import org.opencps.dossiermgt.model.ActionConfig;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.DossierActionUser;
import org.opencps.dossiermgt.model.DossierDocument;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.model.DossierTemplate;
import org.opencps.dossiermgt.model.ProcessAction;
import org.opencps.dossiermgt.model.ProcessOption;
import org.opencps.dossiermgt.model.PublishQueue;
import org.opencps.dossiermgt.model.ServiceProcess;
import org.opencps.dossiermgt.model.StepConfig;
import org.opencps.dossiermgt.service.DossierActionLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierActionUserLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierDocumentLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierPartLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierSyncLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierTemplateLocalServiceUtil;
import org.opencps.dossiermgt.service.PublishQueueLocalServiceUtil;
import org.opencps.dossiermgt.service.StepConfigLocalServiceUtil;

public class SyncEvent implements MessageListener {
	@Override
	public void receive(Message message) throws MessageListenerException {
		try {
			_doReceiveRequest(message);
		} catch (Exception e) {
			_log.error("Unable to process message " + message, e);
		}
	}
	
	private void createSubcription(long userId, long groupId, Dossier dossier, ActionConfig actionConfig, DossierAction dossierAction, ServiceContext context) {
		if (actionConfig != null && Validator.isNotNull(actionConfig.getNotificationType())) {
//			DossierAction dossierAction = DossierActionLocalServiceUtil.fetchDossierAction(dossier.getDossierActionId());
			User u = UserLocalServiceUtil.fetchUser(userId);
			
			Notificationtemplate notiTemplate = NotificationtemplateLocalServiceUtil.fetchByF_NotificationtemplateByType(groupId, actionConfig.getNotificationType());
			if (notiTemplate != null) {
				if (actionConfig.getDocumentType().startsWith("APLC")) {
					if (dossier.getOriginality() == DossierTerm.ORIGINALITY_MOTCUA
							|| dossier.getOriginality() == DossierTerm.ORIGINALITY_LIENTHONG) {
					}
				}
				else if (actionConfig.getDocumentType().startsWith("EMPL")) {
					if ((dossier.getOriginality() == DossierTerm.ORIGINALITY_MOTCUA
							|| dossier.getOriginality() == DossierTerm.ORIGINALITY_LIENTHONG)
							&& dossierAction != null) {
						StepConfig stepConfig = StepConfigLocalServiceUtil.getByCode(groupId, dossierAction.getStepCode());
						List<DossierActionUser> lstDaus = DossierActionUserLocalServiceUtil.getByDID_DAI_SC_AS(dossier.getDossierId(), dossierAction.getDossierActionId(), dossierAction.getStepCode(), new int[] { 1, 2 });
						if ("EMPL-01".equals(actionConfig.getDocumentType())
								&& stepConfig != null && "1".equals(stepConfig.getStepType())) {
							for (DossierActionUser dau : lstDaus) {
								try {
									SubscriptionLocalServiceUtil.addSubscription(dau.getUserId(), groupId, "EMPL-01", 0);
								} catch (PortalException e) {
									_log.debug(e);
								}
							}
						}
					}					
				}
				else if (actionConfig.getDocumentType().startsWith("USER")) {
					
				}
			}
		}		
	}

	private void publishEvent(Dossier dossier, ServiceContext context) {
		if (dossier.getOriginDossierId() != 0 || Validator.isNotNull(dossier.getOriginDossierNo())) {
			return;
		}
		Message message = new Message();
		JSONObject msgData = JSONFactoryUtil.createJSONObject();

		message.put("msgToEngine", msgData);
		message.put("dossier", DossierMgtUtils.convertDossierToJSON(dossier));
		
//		MessageBusUtil.sendMessage(DossierTerm.PUBLISH_DOSSIER_DESTINATION, message);	
		
		Message lgspMessage = new Message();
		JSONObject lgspMsgData = msgData;

		lgspMessage.put("msgToEngine", lgspMsgData);
		lgspMessage.put("dossier", DossierMgtUtils.convertDossierToJSON(dossier));
		
//		MessageBusUtil.sendMessage(DossierTerm.LGSP_DOSSIER_DESTINATION, lgspMessage);	
		
		//Add publish queue
		List<ServerConfig> lstScs = ServerConfigLocalServiceUtil.getByProtocol(dossier.getGroupId(), ServerConfigTerm.PUBLISH_PROTOCOL);
		for (ServerConfig sc : lstScs) {
			try {
				List<PublishQueue> lstQueues = PublishQueueLocalServiceUtil.getByG_DID_SN_ST(dossier.getGroupId(), dossier.getDossierId(), sc.getServerNo(), new int[] { PublishQueueTerm.STATE_WAITING_SYNC, PublishQueueTerm.STATE_ALREADY_SENT });
				if (lstQueues == null || lstQueues.isEmpty()) {
					PublishQueueLocalServiceUtil.updatePublishQueue(dossier.getGroupId(), 0, dossier.getDossierId(), sc.getServerNo(), PublishQueueTerm.STATE_WAITING_SYNC, 0, context);										
				}
//				PublishQueue pq = PublishQueueLocalServiceUtil.getByG_DID_SN(dossier.getGroupId(), dossier.getDossierId(), sc.getServerNo());
//				if (pq == null) {
//					PublishQueueLocalServiceUtil.updatePublishQueue(dossier.getGroupId(), 0, dossier.getDossierId(), sc.getServerNo(), PublishQueueTerm.STATE_WAITING_SYNC, 0, context);					
//				}
//				else {
//					if (pq.getStatus() == PublishQueueTerm.STATE_ACK_ERROR) {
//						PublishQueueLocalServiceUtil.updatePublishQueue(dossier.getGroupId(), pq.getPublishQueueId(), dossier.getDossierId(), sc.getServerNo(), PublishQueueTerm.STATE_WAITING_SYNC, 0, context);																
//					}
//				}
			} catch (PortalException e) {
				_log.debug(e);
			}
		}

		lstScs = ServerConfigLocalServiceUtil.getByProtocol(dossier.getGroupId(), ServerConfigTerm.LGSP_PROTOCOL);
		for (ServerConfig sc : lstScs) {
			try {
				List<PublishQueue> lstQueues = PublishQueueLocalServiceUtil.getByG_DID_SN_ST(dossier.getGroupId(), dossier.getDossierId(), sc.getServerNo(), new int[] { PublishQueueTerm.STATE_WAITING_SYNC, PublishQueueTerm.STATE_ALREADY_SENT });
				if (lstQueues == null || lstQueues.isEmpty()) {
					PublishQueueLocalServiceUtil.updatePublishQueue(dossier.getGroupId(), 0, dossier.getDossierId(), sc.getServerNo(), PublishQueueTerm.STATE_WAITING_SYNC, 0, context);					
				}
//				PublishQueue pq = PublishQueueLocalServiceUtil.getByG_DID_SN(dossier.getGroupId(), dossier.getDossierId(), sc.getServerNo());
//				if (pq == null) {
//					PublishQueueLocalServiceUtil.updatePublishQueue(dossier.getGroupId(), 0, dossier.getDossierId(), sc.getServerNo(), PublishQueueTerm.STATE_WAITING_SYNC, 0, context);					
//				}
//				else {
//					if (pq.getStatus() == PublishQueueTerm.STATE_ACK_ERROR) {
//						PublishQueueLocalServiceUtil.updatePublishQueue(dossier.getGroupId(), pq.getPublishQueueId(), dossier.getDossierId(), sc.getServerNo(), PublishQueueTerm.STATE_WAITING_SYNC, 0, context);																
//					}
//				}
			} catch (PortalException e) {
				_log.debug(e);
			}
		}	
	}
	
	private void _doReceiveRequest(Message message) {	
		User user =  (User)message.get("user");
		long groupId = (Long) message.get("groupId");
		Dossier dossier = (Dossier) message.get("dossier");
		ActionConfig actionConfig = (ActionConfig) message.get("actionConfig");
		DossierAction dossierAction = (DossierAction) message.get("dossierAction");
		JSONObject payloadObject = (JSONObject) message.get("payloadObject");
		int syncType = (Integer) message.get("syncType");
		long userId = (Long) message.get("userId");
		long dossierId = (Long) message.get("dossierId");
		ProcessOption option = (ProcessOption) message.get("option");
		ProcessAction proAction = (ProcessAction) message.get("proAction");
		String actionCode = (String) message.get("actionCode");
		String actionUser = (String) message.get("actionUser");
		String actionNote = (String) message.get("actionNote");
		ServiceProcess serviceProcess = (ServiceProcess) message.get("serviceProcess");
		Map<String, Boolean> flagChanged = (Map<String, Boolean>) message.get("flagChanged");
		ServiceContext context = (ServiceContext) message.get("context");
		
		String dossierRefUid = dossier.getReferenceUid();
		String syncRefUid = UUID.randomUUID().toString();
		
		createSubcription(userId, groupId, dossier, actionConfig, dossierAction, context);
		
		try {
			if (syncType > 0) {
				int state = DossierActionUtils.getSyncState(syncType, dossier);
				
				if (state == DossierSyncTerm.STATE_WAITING_SYNC) {
					if (dossierAction != null) {
						dossierAction.setPending(true);
						DossierActionLocalServiceUtil.updateDossierAction(dossierAction);
					}
				}
				else {
					if (dossierAction != null) {
						dossierAction.setPending(false);
						DossierActionLocalServiceUtil.updateDossierAction(dossierAction);				
					}
				}
				
				JSONArray dossierFilesArr = JSONFactoryUtil.createJSONArray();
				List<DossierFile> lstFiles = DossierFileLocalServiceUtil.findByDID(dossierId);
				if (actionConfig.getSyncType() == DossierSyncTerm.SYNCTYPE_REQUEST) {
					if (dossier.getOriginDossierId() == 0) {
						if (lstFiles.size() > 0) {
							for (DossierFile df : lstFiles) {
								JSONObject dossierFileObj = JSONFactoryUtil.createJSONObject();
								dossierFileObj.put(DossierFileTerm.REFERENCE_UID, df.getReferenceUid());
								dossierFilesArr.put(dossierFileObj);
							}
						}					
					}
					else {
								ProcessOption processOption = option;
								
								DossierTemplate dossierTemplate = DossierTemplateLocalServiceUtil.fetchDossierTemplate(processOption.getDossierTemplateId());
								List<DossierPart> lstParts = DossierPartLocalServiceUtil.getByTemplateNo(groupId, dossierTemplate.getTemplateNo());
								
								List<DossierFile> lstOriginFiles = DossierFileLocalServiceUtil.findByDID(dossier.getOriginDossierId());
								if (lstOriginFiles.size() > 0) {
									for (DossierFile df : lstOriginFiles) {
										boolean flagHslt = false;
										for (DossierPart dp : lstParts) {
											if (dp.getPartNo().equals(df.getDossierPartNo())) {
												flagHslt = true;
												break;
											}
										}
										if (flagHslt) {
											JSONObject dossierFileObj = JSONFactoryUtil.createJSONObject();
											dossierFileObj.put(DossierFileTerm.REFERENCE_UID, df.getReferenceUid());
											dossierFilesArr.put(dossierFileObj);										
										}
									}
								}										
					}
				}
				else {
				}
				
				payloadObject.put("dossierFiles", dossierFilesArr);
				
				if (Validator.isNotNull(proAction.getReturnDossierFiles())) {
					List<DossierFile> lsDossierFile = lstFiles;
					dossierFilesArr = JSONFactoryUtil.createJSONArray();
	
					List<String> returnDossierFileTemplateNos = ListUtil
							.toList(StringUtil.split(proAction.getReturnDossierFiles()));
	
					for (DossierFile dossierFile : lsDossierFile) {
						if (returnDossierFileTemplateNos.contains(dossierFile.getFileTemplateNo())) {
							JSONObject dossierFileObj = JSONFactoryUtil.createJSONObject();
							dossierFileObj.put(DossierFileTerm.REFERENCE_UID, dossierFile.getReferenceUid());
							dossierFilesArr.put(dossierFileObj);
	
						}
	
					}
					payloadObject.put("dossierFiles", dossierFilesArr);				
				}
				
				List<DossierDocument> lstDossierDocuments = DossierDocumentLocalServiceUtil.getDossierDocumentList(dossierId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
				JSONArray dossierDocumentArr = JSONFactoryUtil.createJSONArray();
	
				for (DossierDocument dossierDocument : lstDossierDocuments) {
					JSONObject dossierDocumentObj = JSONFactoryUtil.createJSONObject();
					dossierDocumentObj.put(DossierDocumentTerm.REFERENCE_UID, dossierDocument.getReferenceUid());
					dossierDocumentArr.put(dossierDocumentObj);
				}
				payloadObject.put("dossierFiles", dossierFilesArr);				
				payloadObject.put("dossierDocuments", dossierDocumentArr);
				
				payloadObject.put(DossierTerm.DOSSIER_NOTE, dossier.getDossierNote());
				payloadObject.put(DossierTerm.SUBMIT_DATE, dossier.getSubmitDate() != null ? dossier.getSubmitDate().getTime() : 0);
				
				payloadObject = DossierActionUtils.buildChangedPayload(payloadObject, flagChanged, dossier);
				
				DossierSyncLocalServiceUtil.updateDossierSync(groupId, userId, dossierId, dossierRefUid, syncRefUid,
						dossierAction.getPrimaryKey(), actionCode, proAction.getActionName(), actionUser, actionNote,
						syncType, actionConfig.getInfoType(), payloadObject.toJSONString(), serviceProcess.getServerNo(), state);
				
				if (state == DossierSyncTerm.STATE_NOT_SYNC
						&& actionConfig != null && actionConfig.getEventType() == ActionConfigTerm.EVENT_TYPE_SENT) {
					publishEvent(dossier, context);
				}
			}
			else if (actionConfig != null && actionConfig.getEventType() == ActionConfigTerm.EVENT_TYPE_SENT) {
				publishEvent(dossier, context);			
			}
		}
		catch (Exception e) {
			
		}
	}

	private Log _log = LogFactoryUtil.getLog(SyncEvent.class);
}
