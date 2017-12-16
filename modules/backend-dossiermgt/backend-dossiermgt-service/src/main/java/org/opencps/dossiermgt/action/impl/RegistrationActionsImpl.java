package org.opencps.dossiermgt.action.impl;

import org.opencps.dossiermgt.action.RegistrationActions;
import org.opencps.dossiermgt.model.Registration;
import org.opencps.dossiermgt.service.RegistrationLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;

public class RegistrationActionsImpl implements RegistrationActions {

	@Override
	public void insert(Registration model) {
		RegistrationLocalServiceUtil.addRegistration(model);
	}

	@Override
	public void update(Registration model) {
		RegistrationLocalServiceUtil.updateRegistration(model);

	}

	@Override
	public void delete(long registrationId) throws PortalException {
		RegistrationLocalServiceUtil.deleteRegistration(registrationId);

	}

	@Override
	public Registration getDetail(long registrationId) throws PortalException {
		return RegistrationLocalServiceUtil.getRegistration(registrationId);

	}

}
