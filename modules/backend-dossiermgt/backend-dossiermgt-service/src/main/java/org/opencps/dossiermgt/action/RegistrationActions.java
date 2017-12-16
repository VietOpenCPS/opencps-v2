package org.opencps.dossiermgt.action;

import org.opencps.dossiermgt.model.Registration;

import com.liferay.portal.kernel.exception.PortalException;

public interface RegistrationActions {
	public void insert(Registration model);

	public void update(Registration model);

	public void delete(long registrationId) throws PortalException;

	public Registration getDetail(long registrationId) throws PortalException;
}
