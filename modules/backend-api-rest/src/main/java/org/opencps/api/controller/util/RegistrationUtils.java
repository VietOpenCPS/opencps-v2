
package org.opencps.api.controller.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.opencps.api.registration.model.RegistrationDetailModel;
import org.opencps.api.registration.model.RegistrationDetailResultModel;
import org.opencps.api.registration.model.RegistrationModel;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.dossiermgt.action.RegistrationFormActions;
import org.opencps.dossiermgt.action.impl.RegistrationFormActionsImpl;
import org.opencps.dossiermgt.constants.RegistrationTerm;
import org.opencps.dossiermgt.model.Registration;
import org.opencps.dossiermgt.model.RegistrationForm;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

public class RegistrationUtils {
	public static List<RegistrationModel> mappingToRegistrationResultModel(List<Document> documents) {
		List<RegistrationModel> data = new ArrayList<RegistrationModel>();

		for (Document doc : documents) {
			RegistrationModel model = new RegistrationModel();

			model.setRegistrationId(GetterUtil.getLong(doc.get(RegistrationTerm.REGISTRATION_ID)));
			model.setUserId(GetterUtil.getLong(doc.get(Field.USER_ID)));
            model.setApplicantName(doc.get(RegistrationTerm.APPLICATION_NAME));
			
			String createDate = doc.get(Field.CREATE_DATE);
            if (Validator.isNotNull(createDate)) {
                Date date = APIDateTimeUtils.convertStringToDate(createDate, APIDateTimeUtils._LUCENE_PATTERN);
                
                createDate = APIDateTimeUtils.convertDateToString(date, APIDateTimeUtils._TIMESTAMP);
            }
            model.setCreateDate(createDate);
			
            String modifiedDate = doc.get(Field.MODIFIED_DATE);
            if (Validator.isNotNull(modifiedDate)) {
                Date date = APIDateTimeUtils.convertStringToDate(modifiedDate, APIDateTimeUtils._LUCENE_PATTERN);
                
                modifiedDate = APIDateTimeUtils.convertDateToString(date, APIDateTimeUtils._TIMESTAMP);
            }
            model.setModifiedDate(modifiedDate);
            
			String applicantIdDate = doc.get(RegistrationTerm.APPLICATION_ID_DATE);
			if (Validator.isNotNull(applicantIdDate)) {
			    Date date = APIDateTimeUtils.convertStringToDate(applicantIdDate, APIDateTimeUtils._LUCENE_PATTERN);
			    
			    applicantIdDate = APIDateTimeUtils.convertDateToString(date, APIDateTimeUtils._TIMESTAMP);
			}
			model.setApplicantIdDate(applicantIdDate);
			
			model.setApplicantIdType(doc.get(RegistrationTerm.APPLICATION_ID_TYPE));
			model.setApplicantIdNo(doc.get(RegistrationTerm.APPLICATION_ID_NO));

			model.setAddress(doc.get(RegistrationTerm.ADDRESS));
			model.setCityCode(doc.get(RegistrationTerm.CITY_CODE));
			model.setCityName(doc.get(RegistrationTerm.CITY_NAME));
			model.setDistrictCode(doc.get(RegistrationTerm.DISTRICT_CODE));
			model.setDistrictName(doc.get(RegistrationTerm.DISTRICT_NAME));
			model.setWardCode(doc.get(RegistrationTerm.WARD_CODE));
			model.setWardName(doc.get(RegistrationTerm.WARD_NAME));
			model.setContactName(doc.get(RegistrationTerm.CONTACT_NAME));
			model.setContactTelNo(doc.get(RegistrationTerm.CONTACT_TEL_NO));
			model.setContactEmail(doc.get(RegistrationTerm.CONTACT_EMAIL));
			model.setGovAgencyCode(doc.get(RegistrationTerm.GOV_AGENCY_CODE));
			model.setGovAgencyName(doc.get(RegistrationTerm.GOV_AGENCY_NAME));
			model.setRegistrationState(GetterUtil.getInteger(doc.get(RegistrationTerm.REGISTRATIONSTATE)));
			model.setRegistrationClass(doc.get(RegistrationTerm.REGISTRATION_CLASS));
			model.setSubmitting(GetterUtil.getBoolean(doc.get(RegistrationTerm.SUBMITTING)));
			data.add(model);
		}

		return data;
	}

	public static List<RegistrationModel> mappingRegistrationToRegistrationResultModel(List<Registration> registrations,
			ServiceContext serviceContext) {
		List<RegistrationModel> data = new ArrayList<RegistrationModel>();

		for (Registration registration : registrations) {
			RegistrationModel model = new RegistrationModel();

			model.setRegistrationId(registration.getRegistrationId());
			// model.setServiceInfoId(GetterUtil.getLong(doc.get(Field.ENTRY_CLASS_PK)));
			model.setCreateDate(
					APIDateTimeUtils.convertDateToString(registration.getCreateDate(), APIDateTimeUtils._TIMESTAMP));
			model.setModifiedDate(
					APIDateTimeUtils.convertDateToString(registration.getModifiedDate(), APIDateTimeUtils._TIMESTAMP));
			model.setUserId(registration.getUserId());
			model.setApplicantName(registration.getApplicantName());
			model.setApplicantIdType(registration.getApplicantIdType());
			model.setApplicantIdNo(registration.getApplicantIdNo());
			model.setApplicantIdDate(APIDateTimeUtils.convertDateToString(registration.getApplicantIdDate(),
					APIDateTimeUtils._TIMESTAMP));
			model.setAddress(registration.getAddress());
			model.setCityCode(registration.getCityCode());

			model.setCityName(registration.getCityName());
			model.setDistrictCode(registration.getDistrictCode());
			model.setDistrictName(registration.getDistrictName());
			model.setWardCode(registration.getWardCode());
			model.setWardName(registration.getWardName());
			model.setContactName(registration.getContactName());
			model.setContactTelNo(registration.getContactTelNo());
			model.setContactEmail(registration.getContactEmail());
			model.setGovAgencyCode(registration.getGovAgencyCode());
			model.setGovAgencyName(registration.getGovAgencyName());
			model.setRegistrationState(registration.getRegistrationState());
			model.setRegistrationClass(registration.getRegistrationClass());
			model.setSubmitting(registration.getSubmitting());
			data.add(model);
		}

		return data;
	}

	public static RegistrationDetailModel mappingToRegistrationDetailModel(Registration registration) {

		if (registration == null) {
			return new RegistrationDetailModel();
		}
		RegistrationDetailModel model = new RegistrationDetailModel();

		model.setRegistrationId(registration.getRegistrationId());
		model.setApplicantName(registration.getApplicantName());
		model.setApplicantIdType(registration.getApplicantIdType());
		model.setApplicantIdNo(registration.getApplicantIdNo());
        model.setApplicantIdDate(APIDateTimeUtils.convertDateToString(registration.getApplicantIdDate(), APIDateTimeUtils._TIMESTAMP));
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

	public static List<RegistrationModel> mappingToRegistrationResultsModel(List<Registration> lstRegistration) {
		List<RegistrationModel> outputs = new ArrayList<RegistrationModel>();
		if (Validator.isNotNull(lstRegistration)) {
			for (Registration registration : lstRegistration) {

				RegistrationModel model = mappingToRegistrationModel(registration);

				outputs.add(model);
			}
		}
		return outputs;
	}

	public static RegistrationModel mappingToRegistrationModel(Registration registration) {

		try {
			if (registration == null) {
				return null;
			}

			RegistrationModel model = new RegistrationModel();

			long registrationId = GetterUtil.getLong(registration.getRegistrationId());
			long userId = GetterUtil.getLong(registration.getUserId());

			model.setRegistrationId(registrationId);
			model.setUserId(userId);
			model.setCreateDate(APIDateTimeUtils.convertDateToString(registration.getCreateDate(), APIDateTimeUtils._TIMESTAMP));
			model.setModifiedDate(APIDateTimeUtils.convertDateToString(registration.getModifiedDate(), APIDateTimeUtils._TIMESTAMP));
			model.setApplicantIdDate(APIDateTimeUtils.convertDateToString(registration.getApplicantIdDate(), APIDateTimeUtils._TIMESTAMP));
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

			return model;

		} catch (Exception e) {
//			e.printStackTrace();
			_log.error(e);
			return null;
		}

	}

	public static RegistrationDetailResultModel mappingToRegistrationDetailResultModel(Registration registration) {

		try {
			if (registration == null) {
				return null;
			}

			RegistrationDetailResultModel model = new RegistrationDetailResultModel();

			long registrationId = GetterUtil.getLong(registration.getRegistrationId());
			long userId = GetterUtil.getLong(registration.getUserId());

			model.setRegistrationId(registrationId);
			model.setUserId(userId);
			model.setCreateDate(APIDateTimeUtils.convertDateToString(registration.getCreateDate(), APIDateTimeUtils._TIMESTAMP));
			model.setModifiedDate(APIDateTimeUtils.convertDateToString(registration.getModifiedDate(), APIDateTimeUtils._TIMESTAMP));
			model.setApplicantIdDate(APIDateTimeUtils.convertDateToString(registration.getApplicantIdDate(), APIDateTimeUtils._TIMESTAMP));
			model.setApplicantName(registration.getApplicantName());
			model.setApplicantIdType(registration.getApplicantIdType());
			model.setApplicantIdNo(registration.getApplicantIdNo());
			model.setAddress(registration.getAddress());
			model.setCityCode(registration.getCityCode());
			model.setCityName(registration.getCityName());
			model.setDistrictCode(registration.getDistrictCode());
			model.setDistrictName(registration.getDistrictName());
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

			return model;

		} catch (Exception e) {
//			e.printStackTrace();
			_log.error(e);
			return null;
		}

	}

	private static Log _log = LogFactoryUtil.getLog(RegistrationUtils.class);
	public static JSONArray mappingFormData(Registration reg) throws PortalException {
		JSONArray data = JSONFactoryUtil.createJSONArray();

//		for (Document doc : documents) {
		long registrationId = reg.getRegistrationId();
		_log.info("registrationId: "+registrationId);
//			long groupId = GetterUtil.getLong(doc.get(Field.GROUP_ID));
			if (Validator.isNotNull(registrationId) && registrationId > 0) {
				RegistrationFormActions action = new RegistrationFormActionsImpl();
			List<RegistrationForm> registrationFormList = action.getFormbyRegId(reg.getGroupId(), registrationId);

				if (registrationFormList != null && registrationFormList.size() > 0) {
					for (RegistrationForm regForm : registrationFormList) {
					JSONObject xuongSXJson = JSONFactoryUtil.createJSONObject();
					String formData = regForm.getFormData();
					long registrationFormId = regForm.getRegistrationFormId();
					_log.info("formData: "+formData);
					try {
						JSONObject formJson = JSONFactoryUtil.createJSONObject(formData);
						String xuongSX = formJson.getString("ten_xuong_san_xuat");
						if (Validator.isNotNull(xuongSX)) {
							xuongSXJson.put("ten_xuong_san_xuat", xuongSX);
							xuongSXJson.put("registrationFormId",registrationFormId);
							data.put(xuongSXJson);
							}
						} catch (Exception e) {
							// TODO: handle exception
							_log.error(e);
						}
					}
				}
			}
//		}
		return data;
	}
}
