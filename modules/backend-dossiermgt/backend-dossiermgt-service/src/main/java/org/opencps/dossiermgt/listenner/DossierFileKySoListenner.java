package org.opencps.dossiermgt.listenner;

import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
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
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;

import org.opencps.dossiermgt.action.util.ConstantUtils;
import org.opencps.dossiermgt.constants.DossierFileTerm;
import org.opencps.dossiermgt.constants.DossierPartTerm;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierPartLocalServiceUtil;

//@Component(immediate = true, service = ModelListener.class)
public class DossierFileKySoListenner extends BaseModelListener<DossierFile> {

	@Override
	public void onBeforeCreate(DossierFile model) throws ModelListenerException {
		_log.info("Before Created........... ==> " + model.getDossierId());
	}
	
	@Override
	public void onAfterCreate(DossierFile model) throws ModelListenerException {
		_log.info("After Created........... ");
		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setCompanyId(model.getCompanyId());
		serviceContext.setUserId(model.getUserId());
		try {
			// Binhth add message bus to processing KySO file
			Message message = new Message();
			DossierPart dossierPart = DossierPartLocalServiceUtil.fetchByTemplatePartNo(model.getGroupId(),
					model.getDossierTemplateNo(), model.getDossierPartNo());
			
			JSONObject msgDataESign = JSONFactoryUtil.createJSONObject();
			msgDataESign.put(Field.USER_ID, model.getUserId());
			msgDataESign.put(DossierPartTerm.ESIGN, dossierPart.getESign());
			msgDataESign.put(DossierFileTerm.FILE_ENTRY_ID, model.getFileEntryId());

			message.put(ConstantUtils.MSG_ENG, msgDataESign);
			MessageBusUtil.sendMessage(DossierTerm.KYSO_ENGINE_OUT_DESTINATION, message);
		} catch (SystemException | PortalException e) {
//			e.printStackTrace();
			_log.error(e);
		}
	}

	@Override
	public void onAfterRemove(DossierFile model) throws ModelListenerException {
	}
	
	@Override
	public void onBeforeUpdate(DossierFile model) throws ModelListenerException {
		try {
			modelBeforeUpdate = DossierFileLocalServiceUtil.getDossierFile(model.getPrimaryKey());
		} catch (Exception e) {
			_log.debug(e);
			//_log.error(e);
		}
	}
	
	@Override
	public void onAfterUpdate(DossierFile model) throws ModelListenerException {
		
		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setCompanyId(model.getCompanyId());
		serviceContext.setUserId(model.getUserId());
		
	}



	public DossierFile modelBeforeUpdate;

	private Log _log = LogFactoryUtil.getLog(DossierFileKySoListenner.class.getName());
}
