package org.opencps.jasper.message;

import java.io.File;
import java.util.Date;

import org.opencps.dossiermgt.action.FileUploadUtils;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.model.RegistrationForm;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierPartLocalServiceUtil;
import org.opencps.dossiermgt.service.RegistrationFormLocalServiceUtil;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.service.ServiceContext;

public class Engine implements MessageListener {

	@Override
	public void receive(Message message) throws MessageListenerException {
		// TODO Auto-generated method stub
		try {
			_doReceiveJasperRequest(message);
		} catch (Exception e) {
			_log.error("Unable to process message " + message, e);
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
			
			if(className.equals(DossierFile.class.getName())) {

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
    
    			// Binhth add message bus to processing KySO file
    			DossierPart dossierPart = DossierPartLocalServiceUtil.fetchByTemplatePartNo(dossierFile.getGroupId(),
    					dossierFile.getDossierTemplateNo(), dossierFile.getDossierPartNo());
    			
    			JSONObject msgDataESign = JSONFactoryUtil.createJSONObject();
    			msgDataESign.put("userId", dossierFile.getUserId());
    			msgDataESign.put("eSign", dossierPart.getESign());
    			msgDataESign.put("fileEntryId", fileEntryId);
    
    			message.put("msgToEngine", msgDataESign);
    			MessageBusUtil.sendMessage("kyso/engine/out/destination", message);
			} else if(className.equals(RegistrationForm.class.getName())) {
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

		} catch (Exception e) {
		    _log.error(e);
		}

	}

	private Log _log = LogFactoryUtil.getLog(Engine.class);
}
