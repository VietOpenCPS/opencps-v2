package org.opencps.jasper.message;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.opencps.dossiermgt.action.FileUploadUtils;
import org.opencps.dossiermgt.action.util.DeliverableNumberGenerator;
import org.opencps.dossiermgt.constants.DeliverableTerm;
import org.opencps.dossiermgt.model.*;
import org.opencps.dossiermgt.service.*;

public class Engine implements MessageListener {

	@Override
	public void receive(Message message) throws MessageListenerException {
		// TODO Auto-generated method stub
		try {
			JSONObject msgData = (JSONObject) message.get("msgToEngine");
			
			boolean dossierFileSync = msgData.getBoolean("dossierFileSync");
			
			if (dossierFileSync) {
				_doReceiveKySoRequest(message);
			} else {
				_doReceiveJasperRequest(message);
			}
			
		} catch (Exception e) {
			_log.error("Unable to process message " + message, e);
		}
	}
	
	private void _doReceiveKySoRequest(Message message) {
		
		try {
			
			JSONObject msgData = (JSONObject) message.get("msgToEngine");
			
			long dossierFileId = msgData.getLong("dossierFileId");
			
			DossierFile dossierFile = DossierFileLocalServiceUtil.fetchDossierFile(dossierFileId);
			
			dossierFile.setIsNew(true);
			
			DossierFileLocalServiceUtil.updateDossierFile(dossierFile);
			
			Indexer<DossierFile> indexer = IndexerRegistryUtil.nullSafeGetIndexer(DossierFile.class);
			
			indexer.reindex(dossierFile);
			
		} catch (SearchException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			_log.error(e);
		}
	}
	
	private static final int MAX_TRY_COUNT = 5;
	
	private void _doReceiveJasperRequest(Message message) {
		// TODO Auto-generated method stub
		_log.info("Dossier listener receive Jasper .............................");
		JSONObject msgData = (JSONObject) message.get("msgToEngine");

		try {

			long userId = msgData.getLong("userId");

			long classPK = msgData.getLong("classPK");

			String className = msgData.getString("className");
			
			String filePath = msgData.getString("filePath");

			File file = new File(filePath);

			_log.info("Engine._doReceiveJasperRequest()" + filePath);
			Class<?> engineClass = Class.forName(className);
			
			if(engineClass.isAssignableFrom(DossierFile.class)) {

    			DossierFile dossierFile = DossierFileLocalServiceUtil.fetchDossierFile(classPK);
//    			if(Validator.isNotNull(dossierFile)){
//    				_log.info("LOG_DOSSIER_FILE : " + JSONFactoryUtil.looseSerialize(dossierFile));
//				}
    			ServiceContext serviceContext = new ServiceContext();
    			serviceContext.setUserId(dossierFile.getUserId());
    			serviceContext.setCompanyId(dossierFile.getCompanyId());
    
    			long fileEntryId = 0;
    
    			FileEntry fileEntry = FileUploadUtils.uploadDossierFile(userId, dossierFile.getGroupId(), file, filePath,
    					serviceContext);
    
    			fileEntryId = fileEntry.getFileEntryId();
    
    			dossierFile.setFileEntryId(fileEntryId);
//    			if(Validator.isNotNull(fileEntryId)){
//    				_log.info("File entry ID" + fileEntryId);
//				}
    			dossierFile.setIsNew(false);
    			DossierFileLocalServiceUtil.updateDossierFile(dossierFile);
//    			_log.info("LOG DOSSIER_FILE UPDATE : " + JSONFactoryUtil.looseSerialize(dossierFile));
				if (dossierFile.getDossierPartType() == 7) {
					List<Dossier> lstDossiers = DossierLocalServiceUtil.findByG_GDID(dossierFile.getGroupId(), String.valueOf(dossierFile.getDossierId()));
					if (lstDossiers != null && lstDossiers.size() > 0)
						for (Dossier dossier: lstDossiers) {
							DossierFile fileJasper = DossierFileLocalServiceUtil.getByGID_DID_PART_EFORM(dossier.getGroupId(), dossier.getDossierId(),
									dossierFile.getDossierPartNo(), true, false);
							if (fileJasper != null && fileEntryId > 0) {
								fileJasper.setFileEntryId(fileEntryId);
								fileJasper.setIsNew(false);
								DossierFileLocalServiceUtil.updateDossierFile(fileJasper);
//								_log.info("LOG FILE_JASPER UPDATE : " + JSONFactoryUtil.looseSerialize(fileJasper));
							}
						}
				}
    
    			//Indexer<DossierFile> indexer = IndexerRegistryUtil.nullSafeGetIndexer(DossierFile.class);
    			//indexer.reindex(dossierFile);
    
			} else if(engineClass.isAssignableFrom(RegistrationForm.class)) {
			    RegistrationForm registrationForm = RegistrationFormLocalServiceUtil.fetchRegistrationForm(classPK);
			    
			    if(registrationForm != null) {
			        ServiceContext serviceContext = new ServiceContext();
	                serviceContext.setUserId(registrationForm.getUserId());
	    
	                long fileEntryId = 0;
	    
	                FileEntry fileEntry = FileUploadUtils.uploadFile(
	                    userId, registrationForm.getGroupId(), 0, file, filePath, null, "REGISTRATION_FORM",
	                        serviceContext);
	    
	                fileEntryId = fileEntry.getFileEntryId();
	                
			        registrationForm.setIsNew(true);
			        registrationForm.setModifiedDate(new Date());
			        registrationForm.setFileEntryId(fileEntryId);
			        
			        RegistrationFormLocalServiceUtil.updateRegistrationForm(registrationForm);
			    }
			}
			else if (engineClass.isAssignableFrom(Deliverable.class)) {
				Deliverable deliverable = DeliverableLocalServiceUtil.fetchDeliverable(classPK);
				//
				boolean flagAttach = false;
				String formData = deliverable.getFormData();
				if (Validator.isNotNull(formData)) {
					try {
						JSONObject jsonData = JSONFactoryUtil.createJSONObject(formData);
						if (jsonData.has("fileAttach")) {
							flagAttach = jsonData.getBoolean("fileAttach");
						}
					} catch (JSONException e) {
						_log.debug(e);
					}
				}
				//
				ServiceContext serviceContext = new ServiceContext();
				serviceContext.setUserId(deliverable.getUserId());

				long fileEntryId = 0;

				FileEntry fileEntry = FileUploadUtils.uploadDossierFile(userId, deliverable.getGroupId(), file,
						filePath, serviceContext);

				fileEntryId = fileEntry.getFileEntryId();

				_log.info("flagAttach: "+flagAttach);
				if (!flagAttach) {
					deliverable.setFileEntryId(fileEntryId);
//					_log.info("fileEntryId Deliverable: "+fileEntryId);
					DeliverableLocalServiceUtil.updateDeliverable(deliverable);
				}

				// Process dossierFile
				DossierFile dossierFile = DossierFileLocalServiceUtil
						.getByDeliverableCode(deliverable.getDeliverableCode());
				if (dossierFile != null) {
//					_log.info("DossierFile deliverable : " + dossierFile);
					DossierFile dossierFileAttach = DossierFileLocalServiceUtil.getByGID_DID_TEMP_PART_EFORM(
							dossierFile.getGroupId(), dossierFile.getDossierId(), dossierFile.getDossierTemplateNo(),
							dossierFile.getDossierPartNo(), false, false);
					if (dossierFileAttach != null) {
//						_log.info("DossierFileAttach :" + JSONFactoryUtil.looseSerialize(dossierFileAttach));
						String formDataAttach = dossierFileAttach.getFormData();
						if (Validator.isNotNull(formDataAttach)) {
							JSONObject jsonData = JSONFactoryUtil.createJSONObject(formDataAttach);
							String deliverableCode = jsonData.getString(DeliverableTerm.DELIVERABLE_CODE);
							if (Validator.isNotNull(deliverableCode)) {
								dossierFileAttach.setFileEntryId(fileEntryId);
								dossierFileAttach.setDisplayName(fileEntry.getFileName());
//								_log.info("DossierFileAttach :" + dossierFileAttach.getDisplayName() + "FILE ENtryId :" + dossierFileAttach.getFileEntryId());
								//
								DossierFileLocalServiceUtil.updateDossierFile(dossierFileAttach);
							}
						}
					} else {
//						String deliverableCode = StringPool.BLANK;
//						DossierPart dossierPart = DossierPartLocalServiceUtil.fetchByTemplatePartNo(
//								dossierFile.getGroupId(), dossierFile.getDossierTemplateNo(),
//								dossierFile.getDossierPartNo());
//						if (dossierPart != null && Validator.isNotNull(dossierPart.getDeliverableType())) {
//							DeliverableType deliverableType = DeliverableTypeLocalServiceUtil
//									.getByCode(dossierFile.getGroupId(), dossierPart.getDeliverableType());
//							if (Validator.isNotNull(deliverableType)) {
//								deliverableCode = DeliverableNumberGenerator.generateDeliverableNumber(
//										dossierFile.getGroupId(), serviceContext.getCompanyId(),
//										deliverableType.getDeliverableTypeId());
//							}
//						}

//						System.out.println("==========addDossierByDeliverable=========" + deliverable.getDeliverableCode());
//						DossierFileLocalServiceUtil.addDossierByDeliverable(dossierFile.getGroupId(),
//								dossierFile.getCompanyId(), dossierFile.getUserId(), dossierFile.getUserName(),
//								dossierFile.getDossierId(), StringPool.BLANK, dossierFile.getDossierTemplateNo(),
//								dossierFile.getDossierPartNo(), dossierFile.getDossierPartType(),
//								dossierFile.getFileTemplateNo(), fileEntry.getFileName(), dossierFile.getFormData(),
//								fileEntryId, dossierFile.getOriginal(), false, false, false, deliverable.getDeliverableCode());
					}
				}
			}
			else if (engineClass.isAssignableFrom(DossierDocument.class)) {
				DossierDocument dossierDocument = DossierDocumentLocalServiceUtil.fetchDossierDocument(classPK);
				int tryCount = 0;
				while (dossierDocument == null) {
					try {
						Thread.sleep(5000);
						dossierDocument = DossierDocumentLocalServiceUtil.fetchDossierDocument(classPK);
						tryCount++;
						if (tryCount == MAX_TRY_COUNT) break;
					}
					catch (InterruptedException e) {
						break;
					}
				}
				
				if (dossierDocument != null) {
				ServiceContext serviceContext = new ServiceContext();
    			_log.info("jasper export dossier document: " + classPK + ", " + dossierDocument + ", service context: " + serviceContext );
    			_log.info("jasper export dossier document: " + dossierDocument );
    			
    			serviceContext.setUserId(dossierDocument.getUserId());
    
    			long fileEntryId = 0;
    
    			FileEntry fileEntry = FileUploadUtils.uploadDossierFile(userId, dossierDocument.getGroupId(), file, filePath,
    					serviceContext);
    
    			fileEntryId = fileEntry.getFileEntryId();
    
    			dossierDocument.setDocumentFileId(fileEntryId);
    
    			DossierDocumentLocalServiceUtil.updateDossierDocument(dossierDocument);
    
    			Indexer<DossierDocument> indexer = IndexerRegistryUtil.nullSafeGetIndexer(DossierDocument.class);
    
    			indexer.reindex(dossierDocument);
				}
    			
			}
			else if (engineClass.isAssignableFrom(PaymentFile.class)) {
				PaymentFile paymentFile = PaymentFileLocalServiceUtil.fetchPaymentFile(classPK);
				int tryCount = 0;
				while (paymentFile == null) {
					try {
						Thread.sleep(5000);
						paymentFile = PaymentFileLocalServiceUtil.fetchPaymentFile(classPK);
						tryCount++;
						if (paymentFile != null || tryCount == MAX_TRY_COUNT) break;
					}
					catch (InterruptedException e) {
						break;
					}
				}
				
    			ServiceContext serviceContext = new ServiceContext();
    			_log.info("jasper export paymentFile: " + classPK + ", " + paymentFile );
    			if (paymentFile != null) {
    				serviceContext.setUserId(paymentFile.getUserId());
        			long fileEntryId = 0;
        		    
        			FileEntry fileEntry = FileUploadUtils.uploadDossierFile(userId, paymentFile.getGroupId(), file, filePath,
        					serviceContext);
        
        			fileEntryId = fileEntry.getFileEntryId();
        
        			paymentFile.setInvoiceFileEntryId(fileEntryId);
        
        			PaymentFileLocalServiceUtil.updatePaymentFile(paymentFile);
        
        			Indexer<PaymentFile> indexer = IndexerRegistryUtil.nullSafeGetIndexer(PaymentFile.class);
        
        			indexer.reindex(paymentFile);
    			}
    							
			}
//			else if (engineClass.isAssignableFrom(Deliverable.class)) {
//				Deliverable openCPSDeliverable = DeliverableLocalServiceUtil.fetchDeliverable(classPK);
//				
//    			ServiceContext serviceContext = new ServiceContext();
//    			serviceContext.setUserId(openCPSDeliverable.getUserId());
//    
//    			long fileEntryId = 0;
//    
//    			FileEntry fileEntry = FileUploadUtils.uploadDossierFile(userId, openCPSDeliverable.getGroupId(), file, filePath,
//    					serviceContext);
//    
//    			fileEntryId = fileEntry.getFileEntryId();
//    
//    			openCPSDeliverable.setFileEntryId(fileEntryId);
//    
//    			DeliverableLocalServiceUtil.updateDeliverable(openCPSDeliverable);
//    
//			}

		} catch (Exception e) {
		    _log.error(e);
		}

	}

	private Log _log = LogFactoryUtil.getLog(Engine.class);
}
