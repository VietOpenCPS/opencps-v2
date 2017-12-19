
package org.opencps.api.controller.util;


import java.util.ArrayList;
import java.util.List;

import org.opencps.api.registrationform.model.RegistrationFormDetailModel;
import org.opencps.api.registrationform.model.RegistrationFormModel;
import org.opencps.dossiermgt.model.RegistrationForm;

import com.liferay.portal.kernel.util.Validator;

public class RegistrationFormUtils {


	public static RegistrationFormDetailModel mappingToRegistrationFormDetailModel(
			RegistrationForm registrationForm) {

		if (registrationForm == null) {
			return null;
		}
		long version = 1;
		RegistrationFormDetailModel model = new RegistrationFormDetailModel();

		model.setReferenceUid(registrationForm.getReferenceUid());
		model.setFormNo(registrationForm.getFormNo());
		model.setFormName(registrationForm.getFormName());
		model.setIsNew(registrationForm.isIsNew());
		model.setRemoved(registrationForm.isRemoved());
		model.setVersion(version);	
		return model;
	}
	
	public static List<RegistrationFormModel> mappingToRegistrationFormResultsModel(
			List<RegistrationForm> lstRegistrationForm) {
		List<RegistrationFormModel> outputs = new ArrayList<RegistrationFormModel>();
		long version = 1;
		if (Validator.isNotNull(lstRegistrationForm)) {
			for (RegistrationForm registrationForm : lstRegistrationForm) {

				RegistrationFormModel model = new RegistrationFormModel();

				model.setReferenceUid(registrationForm.getReferenceUid());
				model.setFormNo(registrationForm.getFormNo());
				model.setFormName(registrationForm.getFormName());
				model.setIsNew(registrationForm.isIsNew());
				model.setRemoved(registrationForm.isRemoved());
				model.setVersion(version);

				outputs.add(model);
			}
		}
		return outputs;
	}

}
