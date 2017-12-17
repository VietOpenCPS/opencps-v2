package org.opencps.dossiermgt.action;

import java.util.List;

import org.opencps.dossiermgt.model.RegistrationForm;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;

public interface RegistrationFormActions {

	public void update(RegistrationForm model);

	public void delete(long registrationFormId) throws PortalException;

	public RegistrationForm getDetail(long registrationFormId) throws PortalException;

	public List<RegistrationForm> getFormbyRegId(long registrationId) throws PortalException;

	public RegistrationForm insert(long groupId, long registrationId, String referenceUid, String formNo, String formName,
			String formData, String formScript, String formReport, long fileEntryId, boolean isNew, boolean removed,
			ServiceContext serviceContext) throws PortalException;
}
