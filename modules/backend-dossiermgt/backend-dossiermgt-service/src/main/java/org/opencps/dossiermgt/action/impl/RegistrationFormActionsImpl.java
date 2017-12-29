package org.opencps.dossiermgt.action.impl;

import java.util.List;
import java.util.UUID;

import org.opencps.dossiermgt.action.RegistrationFormActions;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.RegistrationForm;
import org.opencps.dossiermgt.model.RegistrationTemplates;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.RegistrationFormLocalServiceUtil;
import org.opencps.dossiermgt.service.RegistrationTemplatesLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;

public class RegistrationFormActionsImpl implements RegistrationFormActions {

	private static final Log _log = LogFactoryUtil.getLog(RegistrationFormActionsImpl.class);

	@Override
	public RegistrationForm insert(long groupId, long companyId, long registrationId, String referenceUid, String formNo,
			String formName, String formData, String formScript, String formReport, long fileEntryId, boolean isNew,
			boolean removed, ServiceContext serviceContext) throws PortalException {

		return RegistrationFormLocalServiceUtil.addRegistrationForm(groupId, companyId, registrationId, referenceUid, formNo,
				formName, formData, formScript, formReport, fileEntryId, isNew, removed, serviceContext);
	}

	@Override
	public RegistrationForm update(RegistrationForm model) {
		return RegistrationFormLocalServiceUtil.updateRegistrationForm(model);

	}

	@Override
	public RegistrationForm deleteRegistrationForm(long groupId, long registrationId, String referenceUid) throws PortalException {

		return RegistrationFormLocalServiceUtil.deleteRegistrationForm(groupId, registrationId, referenceUid);

	}

	@Override
	public RegistrationForm getDetail(long registrationFormId) throws PortalException {
		return RegistrationFormLocalServiceUtil.getRegistrationForm(registrationFormId);

	}

	@Override
	public List<RegistrationForm> getFormbyRegId(long groupId, long registrationId) throws PortalException {

		List<RegistrationForm> lstRegistrationForm = RegistrationFormLocalServiceUtil.getFormsbyRegId(groupId, registrationId);

		return lstRegistrationForm;
	}

	@Override
	public void addRegistrationFormbaseonRegTemplate(long groupId, long companyId, long registrationId, String govAgencyCode,
			ServiceContext serviceContext) throws PortalException, SystemException {
		// get lstRegistrationTemplate
		List<RegistrationTemplates> lstRegistrationTemplate = RegistrationTemplatesLocalServiceUtil
				.getRegistrationTemplatesbyGOVCODE(groupId, govAgencyCode);

		// add registrationForm
		for (RegistrationTemplates registrationTemplates : lstRegistrationTemplate) {
			// create referenceUid
			String referenceUid = UUID.randomUUID().toString();

			RegistrationFormLocalServiceUtil.addRegistrationForm(groupId, companyId, registrationId, referenceUid,
					registrationTemplates.getFormNo(), registrationTemplates.getFormName(),
					registrationTemplates.getSampleData(), registrationTemplates.getFormScript(),
					registrationTemplates.getFormReport(), 0, false, false, serviceContext);
		}
	}
	
	@Override
	public List<RegistrationForm> deleteRegistrationForms(long groupId, long registrationId) throws PortalException {

		return RegistrationFormLocalServiceUtil.deleteRegistrationForms(groupId, registrationId);

	}
	
	@Override
	public RegistrationForm updateRegFormFormData(long groupId, long registrationId, String referenceUid, String formData,
			ServiceContext serviceContext) throws SystemException, PortalException {

		return RegistrationFormLocalServiceUtil.updateFormData(groupId, registrationId, referenceUid, formData, serviceContext);
	}
}
