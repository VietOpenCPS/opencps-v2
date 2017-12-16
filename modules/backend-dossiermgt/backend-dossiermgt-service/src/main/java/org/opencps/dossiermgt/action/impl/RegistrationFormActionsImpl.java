package org.opencps.dossiermgt.action.impl;

import org.opencps.dossiermgt.action.RegistrationFormActions;
import org.opencps.dossiermgt.model.RegistrationForm;
import org.opencps.dossiermgt.service.RegistrationFormLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;

public class RegistrationFormActionsImpl implements RegistrationFormActions {

	@Override
	public void insert(RegistrationForm model) {
		RegistrationFormLocalServiceUtil.addRegistrationForm(model);
	}

	@Override
	public void update(RegistrationForm model) {
		RegistrationFormLocalServiceUtil.updateRegistrationForm(model);

	}

	@Override
	public void delete(long registrationFormId) throws PortalException {
		RegistrationFormLocalServiceUtil.deleteRegistrationForm(registrationFormId);

	}

	@Override
	public RegistrationForm getDetail(long registrationFormId) throws PortalException {
		return RegistrationFormLocalServiceUtil.getRegistrationForm(registrationFormId);

	}

}
