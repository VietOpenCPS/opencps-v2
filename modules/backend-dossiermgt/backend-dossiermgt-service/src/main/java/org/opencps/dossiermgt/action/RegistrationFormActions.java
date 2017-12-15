package org.opencps.dossiermgt.action;

import org.opencps.dossiermgt.model.RegistrationForm;

import com.liferay.portal.kernel.exception.PortalException;

public interface RegistrationFormActions {
	public void insert(RegistrationForm model);

	public void update(RegistrationForm model);

	public void delete(long registrationFormId) throws PortalException;

	public RegistrationForm getDetail(long registrationFormId) throws PortalException;
}
