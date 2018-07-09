package org.opencps.dossiermgt.rest.utils;

import java.util.HashMap;
import java.util.Map;

import org.opencps.dossiermgt.constants.DossierFileTerm;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.rest.model.DossierDetailModel;
import org.opencps.dossiermgt.rest.model.DossierFileModel;
import org.opencps.dossiermgt.rest.model.DossierInputModel;
import org.opencps.dossiermgt.rest.model.ExecuteOneAction;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import backend.utils.APIDateTimeUtils;

public class OpenCPSConverter {
	public static Map<String, Object> convertHttpParams(DossierInputModel model) {
	    Map<String, Object> params = new HashMap<>();
	    
	    if (Validator.isNotNull(model.getReferenceUid())) {
		    params.put(DossierTerm.REFERENCE_UID, model.getReferenceUid());	    	
	    }
	    if (Validator.isNotNull(model.getServiceCode())) {
		    params.put(DossierTerm.SERVICE_CODE, model.getServiceCode());	    	
	    }
	    if (Validator.isNotNull(model.getGovAgencyCode())) {
		    params.put(DossierTerm.GOV_AGENCY_CODE, model.getGovAgencyCode());	    	
	    }
	    if (Validator.isNotNull(model.getDossierTemplateNo())) {
		    params.put(DossierTerm.DOSSIER_TEMPLATE_NO, model.getDossierTemplateNo());	    	
	    }
	    params.put(DossierTerm.APPLICANT_NAME, model.getApplicantName());
	    params.put(DossierTerm.APPLICANT_ID_TYPE, model.getApplicantIdType());
	    params.put(DossierTerm.APPLICANT_ID_NO, model.getApplicantIdNo());
	    params.put(DossierTerm.APPLICANT_ID_DATE, model.getApplicantIdDate());
	    params.put(DossierTerm.ADDRESS, model.getAddress());
	    params.put(DossierTerm.CITY_CODE, model.getCityCode());
	    params.put(DossierTerm.DISTRICT_CODE, model.getDistrictCode());
	    params.put(DossierTerm.WARD_CODE, model.getWardCode());
	    params.put(DossierTerm.CONTACT_NAME, model.getContactName());
	    params.put(DossierTerm.CONTACT_TEL_NO, model.getContactTelNo());
	    params.put(DossierTerm.CONTACT_EMAIL, model.getContactEmail());
	    if (Validator.isNotNull(model.getPassword())) {
		    params.put(DossierTerm.PASSWORD, model.getPassword());	    	
	    }
	    if (Validator.isNotNull(model.getOnline())) {
		    params.put(DossierTerm.ONLINE, model.getOnline());	    	
	    }
	    if (Validator.isNotNull(model.getViaPostal())) {
	    	params.put(DossierTerm.VIA_POSTAL, model.getViaPostal());
	    }
	    if (Validator.isNotNull(model.getPostalServiceCode())) {
	    	params.put(DossierTerm.POSTAL_SERVICE_CODE, model.getPostalServiceCode());	    	
	    }
	    if (Validator.isNotNull(model.getPostalCityCode())) {
	    	params.put(DossierTerm.POSTAL_CITY_CODE, model.getPostalCityCode());	    		    	
	    }
	    if (Validator.isNotNull(model.getPostalDistrictCode())) {
	    	params.put(DossierTerm.POSTAL_DISTRICT_CODE, model.getPostalDistrictCode());	    		    	
	    }
	    if (Validator.isNotNull(model.getPostalWardCode())) {
	    	params.put(DossierTerm.POSTAL_WARD_CODE, model.getPostalWardCode());	    		    	
	    }
	    if (Validator.isNotNull(model.getPostalTelNo())) {
	    	params.put(DossierTerm.POSTAL_TEL_NO, model.getPostalTelNo());	    		    		    	
	    }
	    if (Validator.isNotNull(model.getApplicantNote())) {
	    	params.put(DossierTerm.APPLICANT_NOTE, model.getApplicantNote());	    		    		    		    	
	    }
	    if (Validator.isNotNull(model.getOriginality())) {
	    	params.put(DossierTerm.ORIGINALLITY, model.getOriginality().toString());
	    }
	    
	    return params;
	}
	
	public static DossierInputModel convertDossierSummary(JSONObject jsonObj) {
		DossierInputModel model = new DossierInputModel();
	
		if (jsonObj.has(DossierTerm.REFERENCE_UID)) {
			model.setReferenceUid(jsonObj.getString(DossierTerm.REFERENCE_UID));
		}
		if (jsonObj.has(DossierTerm.SERVICE_CODE)) {
			model.setServiceCode(jsonObj.getString(DossierTerm.SERVICE_CODE));
		}
		if (jsonObj.has(DossierTerm.GOV_AGENCY_CODE)) {
			model.setGovAgencyCode(jsonObj.getString(DossierTerm.GOV_AGENCY_CODE));
		}
		if (jsonObj.has(DossierTerm.DOSSIER_TEMPLATE_NO)) {
			model.setDossierTemplateNo(jsonObj.getString(DossierTerm.DOSSIER_TEMPLATE_NO));
		}
		if (jsonObj.has(DossierTerm.APPLICANT_NAME)) {
			model.setApplicantName(jsonObj.getString(DossierTerm.APPLICANT_NAME));
		}
		if (jsonObj.has(DossierTerm.APPLICANT_ID_TYPE)) {
			model.setApplicantIdType(jsonObj.getString(DossierTerm.APPLICANT_ID_TYPE));
		}
		if (jsonObj.has(DossierTerm.APPLICANT_ID_NO)) {
			model.setApplicantIdNo(jsonObj.getString(DossierTerm.APPLICANT_ID_NO));
		}
		if (jsonObj.has(DossierTerm.APPLICANT_ID_DATE)) {
			model.setApplicantIdDate(jsonObj.getString(DossierTerm.APPLICANT_ID_DATE));
		}
		if (jsonObj.has(DossierTerm.ADDRESS)) {
			model.setAddress(jsonObj.getString(DossierTerm.ADDRESS));
		}
		if (jsonObj.has(DossierTerm.CITY_CODE)) {
			model.setCityCode(jsonObj.getString(DossierTerm.CITY_CODE));
		}
		if (jsonObj.has(DossierTerm.DISTRICT_CODE)) {
			model.setDistrictCode(jsonObj.getString(DossierTerm.DISTRICT_CODE));
		}
		if (jsonObj.has(DossierTerm.WARD_CODE)) {
			model.setWardCode(jsonObj.getString(DossierTerm.WARD_CODE));
		}
		if (jsonObj.has(DossierTerm.CONTACT_NAME)) {
			model.setContactName(jsonObj.getString(DossierTerm.CONTACT_NAME));
		}
		if (jsonObj.has(DossierTerm.CONTACT_TEL_NO)) {
			model.setContactTelNo(jsonObj.getString(DossierTerm.CONTACT_TEL_NO));
		}
		if (jsonObj.has(DossierTerm.CONTACT_EMAIL)) {
			model.setContactEmail(jsonObj.getString(DossierTerm.CONTACT_EMAIL));
		}
		if (jsonObj.has(DossierTerm.PASSWORD)) {
			model.setPassword(jsonObj.getString(DossierTerm.PASSWORD));
		}
		if (jsonObj.has(DossierTerm.ONLINE)) {
			model.setOnline(jsonObj.getString(DossierTerm.ONLINE));
		}
		if (jsonObj.has(DossierTerm.VIA_POSTAL)) {
			model.setViaPostal(jsonObj.getString(DossierTerm.VIA_POSTAL));
		}
		if (jsonObj.has(DossierTerm.POSTAL_SERVICE_CODE)) {
			model.setPostalServiceCode(jsonObj.getString(DossierTerm.POSTAL_SERVICE_CODE));
		}
		if (jsonObj.has(DossierTerm.POSTAL_ADDRESS)) {
			model.setPostalAddress(jsonObj.getString(DossierTerm.POSTAL_ADDRESS));
		}
		if (jsonObj.has(DossierTerm.POSTAL_CITY_CODE)) {
			model.setPostalCityCode(jsonObj.getString(DossierTerm.POSTAL_CITY_CODE));
		}
		if (jsonObj.has(DossierTerm.POSTAL_DISTRICT_CODE)) {
			model.setPostalDistrictCode(jsonObj.getString(DossierTerm.POSTAL_DISTRICT_CODE));
		}
		if (jsonObj.has(DossierTerm.POSTAL_WARD_CODE)) {
			model.setPostalWardCode(jsonObj.getString(DossierTerm.POSTAL_WARD_CODE));
		}
		if (jsonObj.has(DossierTerm.POSTAL_TEL_NO)) {
			model.setPostalTelNo(jsonObj.getString(DossierTerm.POSTAL_TEL_NO));
		}
		if (jsonObj.has(DossierTerm.APPLICANT_NOTE)) {
			model.setApplicantNote(jsonObj.getString(DossierTerm.APPLICANT_NOTE));
		}
		if (jsonObj.has(DossierTerm.NOTIFICATION)) {
			model.setNotification(jsonObj.getString(DossierTerm.NOTIFICATION));
		}
		
		return model;
	}
		
	public static DossierDetailModel convertDossierDetail(JSONObject jsonObj) {
		DossierDetailModel model = new DossierDetailModel();
	
		if (jsonObj.has(DossierTerm.DOSSIER_ID)) {
			model.setDossierId(jsonObj.getInt(DossierTerm.DOSSIER_ID));
		}
		if (jsonObj.has(DossierTerm.REFERENCE_UID)) {
			model.setReferenceUid(jsonObj.getString(DossierTerm.REFERENCE_UID));
		}
		if (jsonObj.has(DossierTerm.SERVICE_CODE)) {
			model.setServiceCode(jsonObj.getString(DossierTerm.SERVICE_CODE));
		}
		if (jsonObj.has(DossierTerm.GOV_AGENCY_CODE)) {
			model.setGovAgencyCode(jsonObj.getString(DossierTerm.GOV_AGENCY_CODE));
		}
		if (jsonObj.has(DossierTerm.DOSSIER_TEMPLATE_NO)) {
			model.setDossierTemplateNo(jsonObj.getString(DossierTerm.DOSSIER_TEMPLATE_NO));
		}
		if (jsonObj.has(DossierTerm.APPLICANT_NAME)) {
			model.setApplicantName(jsonObj.getString(DossierTerm.APPLICANT_NAME));
		}
		if (jsonObj.has(DossierTerm.APPLICANT_ID_TYPE)) {
			model.setApplicantIdType(jsonObj.getString(DossierTerm.APPLICANT_ID_TYPE));
		}
		if (jsonObj.has(DossierTerm.APPLICANT_ID_NO)) {
			model.setApplicantIdNo(jsonObj.getString(DossierTerm.APPLICANT_ID_NO));
		}
		if (jsonObj.has(DossierTerm.APPLICANT_ID_DATE)) {
			model.setApplicantIdDate(jsonObj.getString(DossierTerm.APPLICANT_ID_DATE));
		}
		if (jsonObj.has(DossierTerm.ADDRESS)) {
			model.setAddress(jsonObj.getString(DossierTerm.ADDRESS));
		}
		if (jsonObj.has(DossierTerm.CITY_CODE)) {
			model.setCityCode(jsonObj.getString(DossierTerm.CITY_CODE));
		}
		if (jsonObj.has(DossierTerm.DISTRICT_CODE)) {
			model.setDistrictCode(jsonObj.getString(DossierTerm.DISTRICT_CODE));
		}
		if (jsonObj.has(DossierTerm.WARD_CODE)) {
			model.setWardCode(jsonObj.getString(DossierTerm.WARD_CODE));
		}
		if (jsonObj.has(DossierTerm.CONTACT_NAME)) {
			model.setContactName(jsonObj.getString(DossierTerm.CONTACT_NAME));
		}
		if (jsonObj.has(DossierTerm.CONTACT_TEL_NO)) {
			model.setContactTelNo(jsonObj.getString(DossierTerm.CONTACT_TEL_NO));
		}
		if (jsonObj.has(DossierTerm.CONTACT_EMAIL)) {
			model.setContactEmail(jsonObj.getString(DossierTerm.CONTACT_EMAIL));
		}
		if (jsonObj.has(DossierTerm.ONLINE)) {
			model.setOnline(jsonObj.getString(DossierTerm.ONLINE));
		}
		if (jsonObj.has(DossierTerm.VIA_POSTAL)) {
			model.setViaPostal(jsonObj.getString(DossierTerm.VIA_POSTAL));
		}
		if (jsonObj.has(DossierTerm.POSTAL_ADDRESS)) {
			model.setPostalAddress(jsonObj.getString(DossierTerm.POSTAL_ADDRESS));
		}
		if (jsonObj.has(DossierTerm.POSTAL_CITY_CODE)) {
			model.setPostalCityCode(jsonObj.getString(DossierTerm.POSTAL_CITY_CODE));
		}
		if (jsonObj.has(DossierTerm.POSTAL_TEL_NO)) {
			model.setPostalTelNo(jsonObj.getString(DossierTerm.POSTAL_TEL_NO));
		}
		if (jsonObj.has(DossierTerm.APPLICANT_NOTE)) {
			model.setApplicantNote(jsonObj.getString(DossierTerm.APPLICANT_NOTE));
		}
		if (jsonObj.has(DossierTerm.NOTIFICATION)) {
			model.setNotification(jsonObj.getString(DossierTerm.NOTIFICATION));
		}
		
		return model;
	}	
	
	public static DossierFileModel convertDossierFile(JSONObject jsonObj) {
		DossierFileModel model = new DossierFileModel();
	
		if (jsonObj.has(DossierFileTerm.DISPLAY_NAME)) {
			model.setDisplayName(jsonObj.getString(DossierFileTerm.DISPLAY_NAME));
		}
		
		return model;
	}		
	
	public static DossierInputModel convertDossierToInputModel(Dossier dossier) {
		DossierInputModel model = new DossierInputModel();
		model.setReferenceUid(dossier.getReferenceUid());
		model.setServiceCode(dossier.getServiceCode());
		model.setGovAgencyCode(dossier.getGovAgencyCode());
		model.setDossierTemplateNo(dossier.getDossierTemplateNo());
		model.setApplicantName(dossier.getApplicantName());
		model.setApplicantIdType(dossier.getApplicantIdType());
		model.setApplicantIdNo(dossier.getApplicantIdNo());
		model.setApplicantIdDate(APIDateTimeUtils.convertDateToString(dossier.getApplicantIdDate(), APIDateTimeUtils._TIMESTAMP));
		model.setAddress(dossier.getAddress());
		model.setCityCode(dossier.getCityCode());
		model.setDistrictCode(dossier.getDistrictCode());
		model.setWardCode(dossier.getWardCode());
		model.setContactName(dossier.getContactName());
		model.setContactTelNo(dossier.getContactTelNo());
		model.setContactEmail(dossier.getContactEmail());
		model.setDelegateName(dossier.getDelegateName());
		model.setDelegateAddress(dossier.getDelegateAddress());
		model.setDelegateCityCode(dossier.getDelegateCityCode());
		model.setDelegateDistrictCode(dossier.getDelegateDistrictCode());
		model.setDelegateWardCode(dossier.getDelegateWardCode());

		return model;
	}
	
	public static Map<String, Object> convertExecuteActionHttpParams(ExecuteOneAction model) {
	    Map<String, Object> params = new HashMap<>();
	    params.put(ExecuteOneActionTerm.ACTION_CODE, model.getActionCode());
	    params.put(ExecuteOneActionTerm.ACTION_USER, model.getActionUser());
	    params.put(ExecuteOneActionTerm.ACTION_NOTE, model.getActionNote());
	    JSONArray assignUserArrs = JSONFactoryUtil.createJSONArray();
		try {
			assignUserArrs = JSONFactoryUtil.createJSONArray(model.getAssignUsers());
		} catch (JSONException e) {
			e.printStackTrace();
		}
	    params.put(ExecuteOneActionTerm.ASSIGN_USERS, assignUserArrs.toJSONString());
	    params.put(ExecuteOneActionTerm.PAYLOAD, model.getPayload());
	    
	    return params;
	}

	public static ExecuteOneAction convertProcessAction(JSONObject jsonObj) {
		ExecuteOneAction result = new ExecuteOneAction();
		
		return result;
	}
	
	public static HashMap<String, String> convertDossierFileHttpParams(DossierFileModel model) {
		HashMap<String, String> params = new HashMap<>();
		params.put(DossierFileTerm.REFERENCE_UID, StringPool.BLANK);
		params.put(DossierFileTerm.DOSSIER_PART_NO, model.getDossierPartNo());
		params.put(DossierFileTerm.DISPLAY_NAME, model.getDisplayName());
		params.put(DossierFileTerm.DOSSIER_TEMPLATE_NO, model.getDossierTemplateNo());
		params.put(DossierFileTerm.FILE_TEMPLATE_NO, model.getFileTemplateNo());
		params.put(DossierFileTerm.FORM_DATA, model.getFormData());
		params.put(DossierFileTerm.FILE_TYPE, model.getFileType());
		params.put(DossierFileTerm.IS_SYNC, "true");
		return params;
	}
}
