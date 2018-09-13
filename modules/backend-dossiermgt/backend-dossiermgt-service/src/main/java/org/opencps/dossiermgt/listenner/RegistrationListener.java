package org.opencps.dossiermgt.listenner;

import org.opencps.dossiermgt.model.Registration;
import org.opencps.dossiermgt.service.RegistrationLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;

@Component(immediate = true, service = ModelListener.class)
public class RegistrationListener extends BaseModelListener<Registration> {

	@Override
	public void onAfterCreate(Registration model) throws ModelListenerException {
		
		DossierListennerUltils.createNotificationQueue(model, false);
	
	}

	@Override
	public void onAfterUpdate(Registration model) throws ModelListenerException {
		
		//check to change state
		if (modelBeforeUpdate.getRegistrationState() != model.getRegistrationState()) {
			
			DossierListennerUltils.createNotificationQueue(model, true);
		}
	
	}

	@Override
	public void onBeforeUpdate(Registration model) throws ModelListenerException {
		try {
			modelBeforeUpdate = RegistrationLocalServiceUtil.getRegistration(model.getPrimaryKey());
		} catch (Exception e) {
			_log.error(e);
		}
	}

	public Registration modelBeforeUpdate;

	private Log _log = LogFactoryUtil.getLog(RegistrationListener.class.getName());
	
}
