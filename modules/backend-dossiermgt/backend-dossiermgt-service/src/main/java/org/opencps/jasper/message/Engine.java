package org.opencps.jasper.message;

import java.io.File;
import java.util.Date;

import org.opencps.dossiermgt.action.FileUploadUtils;
import org.opencps.dossiermgt.model.Deliverable;
import org.opencps.dossiermgt.model.DossierDocument;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.RegistrationForm;
import org.opencps.dossiermgt.service.DeliverableLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierDocumentLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.RegistrationFormLocalServiceUtil;

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
    
    			ServiceContext serviceContext = new ServiceContext();
    			serviceContext.setUserId(dossierFile.getUserId());
    			serviceContext.setCompanyId(dossierFile.getCompanyId());
    
    			long fileEntryId = 0;
    
    			FileEntry fileEntry = FileUploadUtils.uploadDossierFile(userId, dossierFile.getGroupId(), file, filePath,
    					serviceContext);
    
    			fileEntryId = fileEntry.getFileEntryId();
    
    			dossierFile.setFileEntryId(fileEntryId);
    
    			DossierFileLocalServiceUtil.updateDossierFile(dossierFile);
    
    			Indexer<DossierFile> indexer = IndexerRegistryUtil.nullSafeGetIndexer(DossierFile.class);
    
    			indexer.reindex(dossierFile);
    
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
				
    			ServiceContext serviceContext = new ServiceContext();
    			serviceContext.setUserId(deliverable.getUserId());
    
    			long fileEntryId = 0;
    
    			FileEntry fileEntry = FileUploadUtils.uploadDossierFile(userId, deliverable.getGroupId(), file, filePath,
    					serviceContext);
    
    			fileEntryId = fileEntry.getFileEntryId();
    
    			deliverable.setFileEntryId(fileEntryId);
    
    			DeliverableLocalServiceUtil.updateDeliverable(deliverable);
    
			}
			else if (engineClass.isAssignableFrom(DossierDocument.class)) {
				DossierDocument dossierDocument = DossierDocumentLocalServiceUtil.fetchDossierDocument(classPK);
				
    			ServiceContext serviceContext = new ServiceContext();
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
