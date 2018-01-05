package org.opencps.dossiermgt.listenner;

import org.opencps.dossiermgt.action.util.DossierLogUtils;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLogLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierPartLocalServiceUtil;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.service.ServiceContext;

//@Component(immediate = true, service = ModelListener.class)
public class DossierFileListenner extends BaseModelListener<DossierFile> {

	@Override
	public void onBeforeCreate(DossierFile model) throws ModelListenerException {
		_log.info("Before Created........... ==> " + model.getDossierId());
	}
	
	@Override
	public void onAfterCreate(DossierFile model) throws ModelListenerException {
		_log.info("After Created........... ");
		String content = "On DossiserFile Created";
		String notificationType = "File-01";
		String payload = DossierLogUtils.createPayload(model, null, null);
		
		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setCompanyId(model.getCompanyId());
		serviceContext.setUserId(model.getUserId());
		try {
			DossierLogLocalServiceUtil.addDossierLog(model.getGroupId(), model.getDossierId(), model.getUserName(), content,
					notificationType, payload, serviceContext);
			
			// Binhth add message bus to processing KySO file
			Message message = new Message();
			DossierPart dossierPart = DossierPartLocalServiceUtil.fetchByTemplatePartNo(model.getGroupId(),
					model.getDossierTemplateNo(), model.getDossierPartNo());
			
			JSONObject msgDataESign = JSONFactoryUtil.createJSONObject();
			msgDataESign.put("userId", model.getUserId());
			msgDataESign.put("eSign", dossierPart.getESign());
			msgDataESign.put("fileEntryId", model.getFileEntryId());

			message.put("msgToEngine", msgDataESign);
			MessageBusUtil.sendMessage("kyso/engine/out/destination", message);
		} catch (SystemException | PortalException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onAfterRemove(DossierFile model) throws ModelListenerException {
		String content = "On DossiserFile Delete";
		String notificationType = "";
		String payload = DossierLogUtils.createPayload(model, null, null);
		
		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setCompanyId(model.getCompanyId());
		serviceContext.setUserId(model.getUserId());
		
		try {
			DossierLogLocalServiceUtil.addDossierLog(model.getGroupId(), model.getDossierId(), model.getUserName(), content,
					notificationType, payload, serviceContext);
		} catch (SystemException | PortalException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onBeforeUpdate(DossierFile model) throws ModelListenerException {
		try {
			modelBeforeUpdate = DossierFileLocalServiceUtil.getDossierFile(model.getPrimaryKey());
		} catch (Exception e) {
			_log.error(e);
		}
	}
	
	@Override
	public void onAfterUpdate(DossierFile model) throws ModelListenerException {
		String content = "On DossiserFile Update";
		String notificationType = "";
		String payload = DossierLogUtils.createPayload(model, null, null);
		
		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setCompanyId(model.getCompanyId());
		serviceContext.setUserId(model.getUserId());
		
	}



	public static DossierFile modelBeforeUpdate;

	private Log _log = LogFactoryUtil.getLog(DossierFileListenner.class.getName());
}
