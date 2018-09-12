package org.opencps.dossiermgt.listenner;

import org.opencps.auth.api.keys.NotificationType;
import org.opencps.dossiermgt.action.util.DossierLogUtils;
import org.opencps.dossiermgt.model.PaymentFile;
import org.opencps.dossiermgt.service.DossierLogLocalServiceUtil;
import org.opencps.dossiermgt.service.PaymentFileLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.service.ServiceContext;

@Component(immediate = true, service = ModelListener.class)
public class PaymentFileListenner extends BaseModelListener<PaymentFile> {

	@Override
	public void onBeforeCreate(PaymentFile model) throws ModelListenerException {
		_log.info("Before Created........... ==> " + model.getDossierId());
	}
	
	@Override
	public void onAfterCreate(PaymentFile model) throws ModelListenerException {
		String content = "On PaymentFile Created";
		String notificationType = "";
		String payload = DossierLogUtils.createPayload(null, model, null);
		
		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setCompanyId(model.getCompanyId());
		serviceContext.setUserId(model.getUserId());
		try {
			DossierLogLocalServiceUtil.addDossierLog(model.getGroupId(), model.getDossierId(), model.getUserName(), content,
					notificationType, payload, serviceContext);
		} catch (SystemException | PortalException e) {
//			e.printStackTrace();
			_log.error(e);
		}
		super.onAfterCreate(model);
	}

	@Override
	public void onAfterRemove(PaymentFile model) throws ModelListenerException {
		String content = "On PaymentFile Delete";
		String notificationType = "";
		String payload = DossierLogUtils.createPayload(null, model, null);
		
		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setCompanyId(model.getCompanyId());
		serviceContext.setUserId(model.getUserId());
		
		try {
			DossierLogLocalServiceUtil.addDossierLog(model.getGroupId(), model.getDossierId(), model.getUserName(), content,
					notificationType, payload, serviceContext);
		} catch (SystemException | PortalException e) {
//			e.printStackTrace();
			_log.error(e);
		}
	}
	
	@Override
	public void onBeforeUpdate(PaymentFile model) throws ModelListenerException {
		try {
			modelBeforeUpdate = PaymentFileLocalServiceUtil.getPaymentFile(model.getPrimaryKey());
		} catch (Exception e) {
			_log.error(e);
		}
	}
	
	@Override
	public void onAfterUpdate(PaymentFile model) throws ModelListenerException {
		String content = "On PaymentFile Update";
		String notificationType = "";
		String payload = DossierLogUtils.createPayload(null, model, null);
		
		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setCompanyId(model.getCompanyId());
		serviceContext.setUserId(model.getUserId());
		
		try {
			boolean hasChangedStatus = modelBeforeUpdate.getPaymentStatus() == model.getPaymentStatus();
			if (hasChangedStatus && model.getPaymentStatus() ==1 ) {
				notificationType = NotificationType.DOSSIER_07;
			} else if (hasChangedStatus && model.getPaymentStatus() ==2 ) {
				notificationType = NotificationType.DOSSIER_08;
			}
			
			DossierLogLocalServiceUtil.addDossierLog(model.getGroupId(), model.getDossierId(), model.getUserName(), content,
					notificationType, payload, serviceContext);
		} catch (SystemException | PortalException e) {
//			e.printStackTrace();
			_log.error(e);
		}
	}



	public PaymentFile modelBeforeUpdate;

	private Log _log = LogFactoryUtil.getLog(PaymentFileListenner.class.getName());
}
