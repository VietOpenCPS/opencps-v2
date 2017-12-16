
package org.opencps.api.controller.util;


import org.opencps.api.registration.model.RegistrationDetailModel;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.dossiermgt.model.Registration;

public class RegistrationUtils {


	public static RegistrationDetailModel mappingToRegistrationDetailModel(
			Registration registration) {

		if (registration == null) {
			return null;
		}
		RegistrationDetailModel model = new RegistrationDetailModel();

		model.setApplicantName(registration.getApplicantName());
		model.setApplicantIdType(registration.getApplicantIdType());
		model.setApplicantIdNo(registration.getApplicantIdNo());
		model.setApplicantIdDate(APIDateTimeUtils.convertDateToString(registration.getApplicantIdDate()));
		model.setAddress(registration.getAddress());
		model.setCityCode(registration.getCityCode());
		model.setDistrictCode(registration.getDistrictCode());
		model.setWardCode(registration.getWardCode());
		model.setContactName(registration.getContactName());
		model.setContactTelNo(registration.getContactTelNo());
		model.setContactEmail(registration.getContactEmail());
		model.setGovAgencyCode(registration.getGovAgencyCode());
		model.setRegistrationState(registration.getRegistrationState());
		model.setRegistrationClass(registration.getRegistrationClass());
		
		return model;
	}

}
