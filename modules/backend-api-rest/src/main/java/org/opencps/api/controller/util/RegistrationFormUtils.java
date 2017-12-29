
package org.opencps.api.controller.util;

import java.util.ArrayList;
import java.util.List;

import org.opencps.api.registrationform.model.RegistrationFormDetailModel;
import org.opencps.api.registrationform.model.RegistrationFormModel;
import org.opencps.dossiermgt.model.Registration;
import org.opencps.dossiermgt.model.RegistrationForm;
import org.opencps.dossiermgt.model.RegistrationTemplates;
import org.opencps.dossiermgt.service.RegistrationLocalServiceUtil;
import org.opencps.dossiermgt.service.RegistrationTemplatesLocalServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

public class RegistrationFormUtils {

	Log _log = LogFactoryUtil.getLog(RegistrationFormUtils.class);

	public static RegistrationFormDetailModel mappingToRegistrationFormDetailModel(RegistrationForm registrationForm) {

		if (registrationForm == null) {
			return null;
		}
		long version = 1;
		RegistrationFormDetailModel model = new RegistrationFormDetailModel();

		try {
			Registration registration = RegistrationLocalServiceUtil
					.getRegistrationByG_REGID(registrationForm.getGroupId(), registrationForm.getRegistrationId());

			RegistrationTemplates registrationTemplates = RegistrationTemplatesLocalServiceUtil
					.getRegTempbyFormNoGovCode(registrationForm.getGroupId(), registrationForm.getFormNo(),
							registration.getGovAgencyCode());

			model.setReferenceUid(registrationForm.getReferenceUid());
			model.setFormNo(registrationForm.getFormNo());
			model.setFormName(registrationForm.getFormName());
			model.setIsNew(registrationForm.isIsNew());
			model.setRemoved(registrationForm.isRemoved());
			model.setVersion(version);
			model.setMultiple(registrationTemplates.getMultiple());

		} catch (Exception e) {

		}
		return model;
	}

	public static List<RegistrationFormModel> mappingToRegistrationFormResultsModel(
			List<RegistrationForm> lstRegistrationForm) {
		List<RegistrationFormModel> outputs = new ArrayList<RegistrationFormModel>();
		long version = 1;
		if (Validator.isNotNull(lstRegistrationForm)) {
			for (RegistrationForm registrationForm : lstRegistrationForm) {
				Registration registration = RegistrationLocalServiceUtil
						.getRegistrationByG_REGID(registrationForm.getGroupId(), registrationForm.getRegistrationId());

				RegistrationTemplates registrationTemplates = RegistrationTemplatesLocalServiceUtil
						.getRegTempbyFormNoGovCode(registrationForm.getGroupId(), registrationForm.getFormNo(),
								registration.getGovAgencyCode());

				RegistrationFormModel model = new RegistrationFormModel();

				model.setReferenceUid(registrationForm.getReferenceUid());
				model.setFormNo(registrationForm.getFormNo());
				model.setFormName(registrationForm.getFormName());
				model.setIsNew(Boolean.valueOf(registrationForm.isIsNew()));
				model.setRemoved(Boolean.valueOf(registrationForm.isRemoved()));
				model.setVersion(version);
				model.setMultiple(Boolean.valueOf(registrationTemplates.getMultiple()));
				outputs.add(model);
			}
		}
		return outputs;
	}

}
