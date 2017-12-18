package org.opencps.dossiermgt.action.impl;

import java.util.List;

import org.opencps.dossiermgt.action.RegistrationFormActions;
import org.opencps.dossiermgt.model.RegistrationForm;
import org.opencps.dossiermgt.service.RegistrationFormLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;

public class RegistrationFormActionsImpl implements RegistrationFormActions {

	@Override
	public RegistrationForm insert(long groupId, long registrationId, String referenceUid, String formNo,
			String formName, String formData, String formScript, String formReport, long fileEntryId, boolean isNew,
			boolean removed, ServiceContext serviceContext) throws PortalException {

		return RegistrationFormLocalServiceUtil.addRegistrationForm(groupId, registrationId, referenceUid, formNo,
				formName, formData, formScript, formReport, fileEntryId, isNew, removed, serviceContext);
	}

	@Override
	public RegistrationForm update(RegistrationForm model) {
		return RegistrationFormLocalServiceUtil.updateRegistrationForm(model);

	}

	@Override
	public RegistrationForm deleteRegistrationForm(long registrationId, String referenceUid) throws PortalException {
		
		return RegistrationFormLocalServiceUtil.deleteRegistrationForm(registrationId, referenceUid);

	}

	@Override
	public RegistrationForm getDetail(long registrationFormId) throws PortalException {
		return RegistrationFormLocalServiceUtil.getRegistrationForm(registrationFormId);

	}

	@Override
	public List<RegistrationForm> getFormbyRegId(long registrationId) throws PortalException {

		List<RegistrationForm> lstRegistrationForm = RegistrationFormLocalServiceUtil.getFormsbyRegId(registrationId);

		return lstRegistrationForm;
	}

}
