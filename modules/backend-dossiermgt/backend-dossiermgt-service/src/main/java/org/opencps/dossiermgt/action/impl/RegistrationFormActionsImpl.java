package org.opencps.dossiermgt.action.impl;

import java.util.List;
import java.util.UUID;

import org.opencps.dossiermgt.action.RegistrationFormActions;
import org.opencps.dossiermgt.model.RegistrationForm;
import org.opencps.dossiermgt.model.RegistrationTemplates;
import org.opencps.dossiermgt.service.RegistrationFormLocalServiceUtil;
import org.opencps.dossiermgt.service.RegistrationTemplatesLocalServiceUtil;

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

	@Override
	public void addRegistrationFormbaseonRegTemplate(long groupId, long registrationId,
			ServiceContext serviceContext) throws PortalException {

		int start = -1, end = -1;

		// create referenceUid
		String referenceUid = UUID.randomUUID().toString();

		// get lstRegistrationTemplate
		List<RegistrationTemplates> lstRegistrationTemplate = RegistrationTemplatesLocalServiceUtil
				.getRegistrationTemplateses(start, end);

		// add registrationForm
		for (RegistrationTemplates registrationTemplates : lstRegistrationTemplate) {
			int fileEntryId = getfileEntryId(registrationTemplates.getSampleData(),
					registrationTemplates.getFormScript(), registrationTemplates.getFormReport());

			RegistrationFormLocalServiceUtil.addRegistrationForm(groupId, registrationId, referenceUid,
					registrationTemplates.getFormNo(), registrationTemplates.getFormName(),
					registrationTemplates.getSampleData(), registrationTemplates.getFormScript(),
					registrationTemplates.getFormReport(), fileEntryId, false, false, serviceContext);
		}
	}

	public int getfileEntryId(String formdata, String formScript, String formReport) {

		int fileEntryId = 0;

		return fileEntryId;
	}

}
