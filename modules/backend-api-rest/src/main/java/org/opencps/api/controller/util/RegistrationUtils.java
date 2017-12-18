
package org.opencps.api.controller.util;


import java.util.ArrayList;
import java.util.List;

import org.opencps.api.deliverabletype.model.DeliverableTypesModel;
import org.opencps.api.registration.model.RegistrationDetailModel;
import org.opencps.api.registration.model.RegistrationModel;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.dossiermgt.model.DeliverableType;
import org.opencps.dossiermgt.model.Registration;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

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
	
	public static List<RegistrationModel> mappingToRegistrationResultsModel(
			List<Registration> lstRegistration) {
		List<RegistrationModel> outputs = new ArrayList<RegistrationModel>();
		if (Validator.isNotNull(lstRegistration)) {
			for (Registration registration : lstRegistration) {

				RegistrationModel model = new RegistrationModel();

				long registrationId = GetterUtil.getLong(registration.getRegistrationId());
				long userId  = GetterUtil.getLong(registration.getUserId());

				model.setRegistrationId(registrationId);
				model.setUserId(userId);
				model.setCreateDate(APIDateTimeUtils.convertDateToString(registration.getCreateDate()));
				model.setModifiedDate(APIDateTimeUtils.convertDateToString(registration.getModifiedDate()));
				model.setApplicantIdDate(APIDateTimeUtils.convertDateToString(registration.getApplicantIdDate()));
				model.setApplicantName(registration.getApplicantName());
				model.setApplicantIdType(registration.getApplicantIdType());
				model.setApplicantIdNo(registration.getApplicantIdNo());
				model.setAddress(registration.getAddress());
				model.setCityCode(registration.getCityCode());
				model.setCityName(registration.getCityName());
				model.setDistrictCode(registration.getDistrictName());
				model.setWardCode(registration.getWardCode());
				model.setWardName(registration.getWardName());
				model.setContactName(registration.getContactName());
				model.setContactTelNo(registration.getContactTelNo());
				model.setContactEmail(registration.getContactEmail());
				model.setGovAgencyCode(registration.getGovAgencyCode());
				model.setGovAgencyName(registration.getGovAgencyName());
				model.setRegistrationState(registration.getRegistrationState());
				model.setRegistrationClass(registration.getRegistrationClass());
				model.setSubmitting(registration.isSubmitting());
				outputs.add(model);
			}
		}
		return outputs;
	}

}
