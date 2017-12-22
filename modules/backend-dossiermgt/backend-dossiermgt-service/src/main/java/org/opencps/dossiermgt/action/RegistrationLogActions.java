package org.opencps.dossiermgt.action;

import java.util.List;

import org.opencps.dossiermgt.model.RegistrationLog;

import com.liferay.portal.kernel.exception.PortalException;

public interface RegistrationLogActions {

	public List<RegistrationLog> getRegistrationLogbyId(long groupId, long registrationId) throws PortalException;

}
