package org.opencps.dossiermgt.listenner;

import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.service.DossierLogLocalServiceUtil;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

public class DossierActionListenner extends BaseModelListener<DossierAction> {
	@Override
	public void onAfterCreate(DossierAction model) throws ModelListenerException {
		if (Validator.isNotNull(model.getSyncActionCode()) && model.getSyncActionCode().length() != 0) {
			String content = "On DossiserAction Created";
			String notificationType = "";
			String payload = "";
			
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
	}
}
