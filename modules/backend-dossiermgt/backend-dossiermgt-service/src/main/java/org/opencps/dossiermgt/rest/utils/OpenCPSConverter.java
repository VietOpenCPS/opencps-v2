package org.opencps.dossiermgt.rest.utils;

import java.util.HashMap;
import java.util.Map;

import org.opencps.dossiermgt.constants.DossierActionTerm;
import org.opencps.dossiermgt.constants.DossierDocumentTerm;
import org.opencps.dossiermgt.constants.DossierFileTerm;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.PaymentFileTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierDocument;
import org.opencps.dossiermgt.rest.model.DossierDetailModel;
import org.opencps.dossiermgt.rest.model.DossierDocumentModel;
import org.opencps.dossiermgt.rest.model.DossierFileModel;
import org.opencps.dossiermgt.rest.model.DossierInputModel;
import org.opencps.dossiermgt.rest.model.DossierPublishModel;
import org.opencps.dossiermgt.rest.model.ExecuteOneAction;
import org.opencps.dossiermgt.rest.model.PaymentFileInputModel;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
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
	    params.put(DossierTerm.SERVICE_NAME, model.getServiceName());
	    params.put(DossierTerm.GOV_AGENCY_NAME, model.getGovAgencyName());
	    params.put(DossierTerm.APPLICANT_NAME, model.getApplicantName());
	    params.put(DossierTerm.APPLICANT_ID_TYPE, model.getApplicantIdType());
	    params.put(DossierTerm.APPLICANT_ID_NO, model.getApplicantIdNo());
	    params.put(DossierTerm.APPLICANT_ID_DATE, model.getApplicantIdDate());
	    params.put(DossierTerm.ADDRESS, model.getAddress());
	    params.put(DossierTerm.CITY_CODE, model.getCityCode());
	    params.put(DossierTerm.CITY_NAME, model.getCityName());
	    params.put(DossierTerm.DISTRICT_CODE, model.getDistrictCode());
	    params.put(DossierTerm.DISTRICT_NAME, model.getDistrictName());
	    params.put(DossierTerm.WARD_CODE, model.getWardCode());
	    params.put(DossierTerm.WARD_NAME, model.getWardName());
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
	
	public static Map<String, Object> convertPublishHttpParams(DossierPublishModel model) {
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
	    if (Validator.isNotNull(model.getGovAgencyName())) {
		    params.put(DossierTerm.GOV_AGENCY_NAME, model.getGovAgencyName());	    	
	    }
	    if (Validator.isNotNull(model.getDossierTemplateNo())) {
		    params.put(DossierTerm.DOSSIER_TEMPLATE_NO, model.getDossierTemplateNo());	    	
	    }
	    if (Validator.isNotNull(model.getDossierNo())) {
	    	params.put(DossierTerm.DOSSIER_NO, model.getDossierNo());
	    }
	    params.put(DossierTerm.SERVICE_NAME, model.getServiceName());
	    params.put(DossierTerm.GOV_AGENCY_NAME, model.getGovAgencyName());
	    params.put(DossierTerm.APPLICANT_NAME, model.getApplicantName());
	    params.put(DossierTerm.APPLICANT_ID_TYPE, model.getApplicantIdType());
	    params.put(DossierTerm.APPLICANT_ID_NO, model.getApplicantIdNo());
	    params.put(DossierTerm.APPLICANT_ID_DATE, model.getApplicantIdDate());
	    params.put(DossierTerm.ADDRESS, model.getAddress());
	    params.put(DossierTerm.CITY_CODE, model.getCityCode());
	    params.put(DossierTerm.CITY_NAME, model.getCityName());
	    params.put(DossierTerm.DISTRICT_CODE, model.getDistrictCode());
	    params.put(DossierTerm.DISTRICT_NAME, model.getDistrictName());
	    params.put(DossierTerm.WARD_CODE, model.getWardCode());
	    params.put(DossierTerm.WARD_NAME, model.getWardName());
	    params.put(DossierTerm.CONTACT_NAME, model.getContactName());
	    params.put(DossierTerm.CONTACT_TEL_NO, model.getContactTelNo());
	    params.put(DossierTerm.CONTACT_EMAIL, model.getContactEmail());
	    params.put(DossierTerm.DOSSIER_STATUS, model.getDossierStatus());
	    params.put(DossierTerm.DOSSIER_STATUS_TEXT, model.getDossierStatusText());
	    params.put(DossierTerm.DOSSIER_SUB_STATUS, model.getDossierSubStatus());
	    params.put(DossierTerm.DOSSIER_SUB_STATUS_TEXT, model.getDossierSubStatusText());

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
	    if (Validator.isNotNull(model.getCreateDate())) {
	    	params.put(DossierTerm.CREATE_DATE, model.getCreateDate());
	    }
	    if (Validator.isNotNull(model.getModifiedDate())) {
	    	params.put(DossierTerm.MODIFIED_DATE, model.getModifiedDate());
	    }
	    if (Validator.isNotNull(model.getSubmitDate())) {
	    	params.put(DossierTerm.SUBMIT_DATE, model.getSubmitDate());
	    }
	    if (Validator.isNotNull(model.getReceiveDate())) {
	    	params.put(DossierTerm.RECEIVE_DATE, model.getReceiveDate());
	    }
	    if (Validator.isNotNull(model.getDueDate())) {
	    	params.put(DossierTerm.DUE_DATE, model.getDueDate());
	    }
	    if (Validator.isNotNull(model.getReleaseDate())) {
	    	params.put(DossierTerm.RELEASE_DATE, model.getReleaseDate());
	    }
	    if (Validator.isNotNull(model.getFinishDate())) {
	    	params.put(DossierTerm.FINISH_DATE, model.getFinishDate());
	    }
	    if (Validator.isNotNull(model.getCancellingDate())) {
	    	params.put(DossierTerm.CANCELLING_DATE, model.getCancellingDate());
	    }
	    if (Validator.isNotNull(model.getCorrecttingDate())) {
	    	params.put(DossierTerm.CORRECTING_DATE, model.getCorrecttingDate());
	    }
	    if (Validator.isNotNull(model.getEndorsementDate())) {
	    	params.put(DossierTerm.ENDORSEMENT_DATE, model.getEndorsementDate());
	    }
	    if (Validator.isNotNull(model.getExtendDate())) {
	    	params.put(DossierTerm.EXTEND_DATE, model.getExtendDate());
	    }
	    if (Validator.isNotNull(model.getProcessDate())) {
	    	params.put(DossierTerm.PROCESS_DATE, model.getProcessDate());
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
		if (jsonObj.has(DossierTerm.SERVICE_NAME)) {
			model.setServiceName(jsonObj.getString(DossierTerm.SERVICE_NAME));
		}
		if (jsonObj.has(DossierTerm.GOV_AGENCY_CODE)) {
			model.setGovAgencyCode(jsonObj.getString(DossierTerm.GOV_AGENCY_CODE));
		}
		if (jsonObj.has(DossierTerm.GOV_AGENCY_NAME)) {
			model.setGovAgencyName(jsonObj.getString(DossierTerm.GOV_AGENCY_NAME));
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
		if (jsonObj.has(DossierTerm.CITY_NAME)) {
			model.setCityName(jsonObj.getString(DossierTerm.CITY_NAME));
		}
		if (jsonObj.has(DossierTerm.DISTRICT_CODE)) {
			model.setDistrictCode(jsonObj.getString(DossierTerm.DISTRICT_CODE));
		}
		if (jsonObj.has(DossierTerm.DISTRICT_NAME)) {
			model.setDistrictName(jsonObj.getString(DossierTerm.DISTRICT_NAME));
		}
		if (jsonObj.has(DossierTerm.WARD_CODE)) {
			model.setWardCode(jsonObj.getString(DossierTerm.WARD_CODE));
		}
		if (jsonObj.has(DossierTerm.WARD_NAME)) {
			model.setWardName(jsonObj.getString(DossierTerm.WARD_NAME));
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
	
	public static DossierPublishModel convertDossierPublish(JSONObject jsonObj) {
		DossierPublishModel model = new DossierPublishModel();
	
		if (jsonObj.has(DossierTerm.REFERENCE_UID)) {
			model.setReferenceUid(jsonObj.getString(DossierTerm.REFERENCE_UID));
		}
		if (jsonObj.has(DossierTerm.SERVICE_CODE)) {
			model.setServiceCode(jsonObj.getString(DossierTerm.SERVICE_CODE));
		}
		if (jsonObj.has(DossierTerm.SERVICE_NAME)) {
			model.setServiceName(jsonObj.getString(DossierTerm.SERVICE_NAME));
		}
		if (jsonObj.has(DossierTerm.GOV_AGENCY_CODE)) {
			model.setGovAgencyCode(jsonObj.getString(DossierTerm.GOV_AGENCY_CODE));
		}
		if (jsonObj.has(DossierTerm.GOV_AGENCY_NAME)) {
			model.setGovAgencyName(jsonObj.getString(DossierTerm.GOV_AGENCY_NAME));
		}
		if (jsonObj.has(DossierTerm.DOSSIER_TEMPLATE_NO)) {
			model.setDossierTemplateNo(jsonObj.getString(DossierTerm.DOSSIER_TEMPLATE_NO));
		}
		if (jsonObj.has(DossierTerm.DOSSIER_NO)) {
			model.setDossierNo(jsonObj.getString(DossierTerm.DOSSIER_NO));
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
		if (jsonObj.has(DossierTerm.CITY_NAME)) {
			model.setCityName(jsonObj.getString(DossierTerm.CITY_NAME));
		}
		if (jsonObj.has(DossierTerm.DISTRICT_CODE)) {
			model.setDistrictCode(jsonObj.getString(DossierTerm.DISTRICT_CODE));
		}
		if (jsonObj.has(DossierTerm.DISTRICT_NAME)) {
			model.setDistrictName(jsonObj.getString(DossierTerm.DISTRICT_NAME));
		}
		if (jsonObj.has(DossierTerm.WARD_CODE)) {
			model.setWardCode(jsonObj.getString(DossierTerm.WARD_CODE));
		}
		if (jsonObj.has(DossierTerm.WARD_NAME)) {
			model.setWardName(jsonObj.getString(DossierTerm.WARD_NAME));
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
		if (jsonObj.has(DossierTerm.CREATE_DATE)) {
			model.setCreateDate(jsonObj.getLong(DossierTerm.CREATE_DATE));
		}
		if (jsonObj.has(DossierTerm.MODIFIED_DATE)) {
			model.setModifiedDate(jsonObj.getLong(DossierTerm.MODIFIED_DATE));
		}
		if (jsonObj.has(DossierTerm.SUBMIT_DATE)) {
			model.setSubmitDate(jsonObj.getLong(DossierTerm.SUBMIT_DATE));
		}
		if (jsonObj.has(DossierTerm.RECEIVE_DATE)) {
			model.setReceiveDate(jsonObj.getLong(DossierTerm.RECEIVE_DATE));
		}
		if (jsonObj.has(DossierTerm.DUE_DATE)) {
			model.setDueDate(jsonObj.getLong(DossierTerm.DUE_DATE));
		}
		if (jsonObj.has(DossierTerm.RELEASE_DATE)) {
			model.setReleaseDate(jsonObj.getLong(DossierTerm.RELEASE_DATE));
		}
		if (jsonObj.has(DossierTerm.FINISH_DATE)) {
			model.setFinishDate(jsonObj.getLong(DossierTerm.FINISH_DATE));
		}
		if (jsonObj.has(DossierTerm.CANCELLING_DATE)) {
			model.setCancellingDate(jsonObj.getLong(DossierTerm.CANCELLING_DATE));
		}
		if (jsonObj.has(DossierTerm.CORRECTING_DATE)) {
			model.setCorrecttingDate(jsonObj.getLong(DossierTerm.CORRECTING_DATE));
		}
		if (jsonObj.has(DossierTerm.ENDORSEMENT_DATE)) {
			model.setEndorsementDate(jsonObj.getLong(DossierTerm.ENDORSEMENT_DATE));
		}
		if (jsonObj.has(DossierTerm.EXTEND_DATE)) {
			model.setExtendDate(jsonObj.getLong(DossierTerm.EXTEND_DATE));
		}
		if (jsonObj.has(DossierTerm.PROCESS_DATE)) {
			model.setProcessDate(jsonObj.getLong(DossierTerm.PROCESS_DATE));
		}
		if (jsonObj.has(DossierTerm.DOSSIER_STATUS)) {
			model.setDossierStatus(jsonObj.getString(DossierTerm.DOSSIER_STATUS));
		}
		if (jsonObj.has(DossierTerm.DOSSIER_STATUS_TEXT)) {
			model.setDossierStatusText(jsonObj.getString(DossierTerm.DOSSIER_STATUS_TEXT));
		}
		if (jsonObj.has(DossierTerm.DOSSIER_SUB_STATUS)) {
			model.setDossierSubStatus(jsonObj.getString(DossierTerm.DOSSIER_SUB_STATUS));
		}
		if (jsonObj.has(DossierTerm.DOSSIER_SUB_STATUS_TEXT)) {
			model.setDossierSubStatusText(jsonObj.getString(DossierTerm.DOSSIER_SUB_STATUS_TEXT));
		}

		return model;
	}	
	private static Log _log = LogFactoryUtil.getLog(OpenCPSConverter.class);
	public static DossierDetailModel convertDossierDetail(JSONObject jsonObj) {
		DossierDetailModel model = new DossierDetailModel();
	
		if (jsonObj.has("status") && jsonObj.getInt("status") != 200) {
			return model;
		}
		
		if (!jsonObj.has("message")) {
			return model;
		}

		String strMessage = jsonObj.getString("message");
		_log.info("strMessage: "+strMessage);
		if (Validator.isNotNull(strMessage)) {
			try {
				jsonObj = JSONFactoryUtil.createJSONObject(strMessage);
//				_log.info("jsonObj2: "+jsonObj);
			} catch (JSONException e) {
//				e.printStackTrace();
				_log.error(e);
			}
		}

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
		if (jsonObj.has(DossierTerm.DOSSIER_STATUS)) {
			model.setDossierStatus(jsonObj.getString(DossierTerm.DOSSIER_STATUS));
		}
		if (jsonObj.has(DossierTerm.DOSSIER_STATUS_TEXT)) {
			model.setDossierStatusText(jsonObj.getString(DossierTerm.DOSSIER_STATUS_TEXT));
		}
		if (jsonObj.has(DossierTerm.DOSSIER_SUB_STATUS)) {
			model.setDossierSubStatus(jsonObj.getString(DossierTerm.DOSSIER_SUB_STATUS));
		}
		if (jsonObj.has(DossierTerm.DOSSIER_SUB_STATUS_TEXT)) {
			model.setDossierSubStatusText(jsonObj.getString(DossierTerm.DOSSIER_SUB_STATUS_TEXT));
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
//			e.printStackTrace();
			_log.error(e);
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
	
	public static HashMap<String, String> convertDossierFileEFormHttpParams(DossierFileModel model) {
		HashMap<String, String> params = new HashMap<>();
		params.put(DossierFileTerm.FORM_DATA, model.getFormData());
		return params;
	}

	public static Map<String, Object> convertPaymentFileInputHttpParams(PaymentFileInputModel model) {
	    Map<String, Object> params = new HashMap<>();
	    
	    if (Validator.isNotNull(model.getReferenceUid())) {
		    params.put(PaymentFileTerm.REFERENCE_UID, model.getReferenceUid());	    	
	    }
	    if (Validator.isNotNull(model.getGovAgencyCode())) {
		    params.put(PaymentFileTerm.GOV_AGENCY_CODE, model.getGovAgencyCode());	    	
	    }
	    if (Validator.isNotNull(model.getGovAgencyName())) {
		    params.put(PaymentFileTerm.GOV_AGENCY_NAME, model.getGovAgencyName());	    	
	    }
	    if (Validator.isNotNull(model.getApplicantName())) {
		    params.put(PaymentFileTerm.APPLICANT_NAME, model.getApplicantName());	    	
	    }
	    if (Validator.isNotNull(model.getApplicantIdNo())) {
	    	params.put(PaymentFileTerm.APPLICANT_ID_NO, model.getApplicantIdNo());
	    }
	    if (Validator.isNotNull(model.getPaymentFee())) {
	    	params.put(PaymentFileTerm.PAYMENT_FEE, model.getPaymentFee());
	    }
	    if (Validator.isNotNull(model.getPaymentAmount())) {
	    	params.put(PaymentFileTerm.PAYMENT_AMOUNT, model.getPaymentAmount());
	    }
	    if (Validator.isNotNull(model.getPaymentNote())) {
	    	params.put(PaymentFileTerm.PAYMENT_NOTE, model.getPaymentNote());
	    }
	    if (Validator.isNotNull(model.getEpaymentProfile())) {
	    	params.put(PaymentFileTerm.EPAYMENT_PROFILE, model.getEpaymentProfile());
	    }
	    if (Validator.isNotNull(model.getBankInfo())) {
	    	params.put(PaymentFileTerm.BANK_INFO, model.getBankInfo());
	    }
	    
	    return params;
	}	
	
	public static PaymentFileInputModel convertPaymentFile(JSONObject jsonObj) {
		PaymentFileInputModel result = new PaymentFileInputModel();
		if (jsonObj.has(PaymentFileTerm.REFERENCE_UID)) {
			result.setReferenceUid(jsonObj.getString(PaymentFileTerm.REFERENCE_UID));
		}
		if (jsonObj.has(PaymentFileTerm.GOV_AGENCY_CODE)) {
			result.setGovAgencyCode(jsonObj.getString(PaymentFileTerm.GOV_AGENCY_CODE));
		}
		if (jsonObj.has(PaymentFileTerm.GOV_AGENCY_NAME)) {
			result.setGovAgencyName(jsonObj.getString(PaymentFileTerm.GOV_AGENCY_NAME));
		}
		if (jsonObj.has(PaymentFileTerm.GOV_AGENCY_NAME)) {
			result.setGovAgencyName(jsonObj.getString(PaymentFileTerm.GOV_AGENCY_NAME));
		}
		if (jsonObj.has(PaymentFileTerm.APPLICANT_NAME)) {
			result.setApplicantName(jsonObj.getString(PaymentFileTerm.GOV_AGENCY_NAME));
		}
		if (jsonObj.has(PaymentFileTerm.APPLICANT_ID_NO)) {
			result.setApplicantIdNo(jsonObj.getString(PaymentFileTerm.APPLICANT_ID_NO));
		}
		if (jsonObj.has(PaymentFileTerm.PAYMENT_FEE)) {
			result.setPaymentFee(jsonObj.getString(PaymentFileTerm.PAYMENT_FEE));
		}
		if (jsonObj.has(PaymentFileTerm.PAYMENT_AMOUNT)) {
			result.setPaymentAmount(jsonObj.getString(PaymentFileTerm.PAYMENT_AMOUNT));
		}
		if (jsonObj.has(PaymentFileTerm.PAYMENT_NOTE)) {
			result.setPaymentNote(jsonObj.getString(PaymentFileTerm.PAYMENT_NOTE));
		}
		if (jsonObj.has(PaymentFileTerm.EPAYMENT_PROFILE)) {
			result.setEpaymentProfile(jsonObj.getString(PaymentFileTerm.EPAYMENT_PROFILE));
		}
		if (jsonObj.has(PaymentFileTerm.BANK_INFO)) {
			result.setBankInfo(jsonObj.getString(PaymentFileTerm.BANK_INFO));
		}
		
		return result;
	}
	
	public static JSONObject convertFileInputModelToJSON(DossierFileModel model) {
		JSONObject obj = JSONFactoryUtil.createJSONObject();
		obj.put(DossierFileTerm.SIGN_INFO, model.getSignInfo());
		obj.put(DossierFileTerm.SIGN_CHECK, model.getSignCheck());
		obj.put(DossierFileTerm.CREATE_DATE, model.getCreateDate());
		obj.put(DossierFileTerm.DISPLAY_NAME, model.getDisplayName());
		obj.put(DossierFileTerm.DOSSIER_FILE_ID, model.getDossierFileId());
		obj.put(DossierFileTerm.DOSSIER_PART_NO, model.getDossierPartNo());
		obj.put(DossierFileTerm.DOSSIER_PART_TYPE, model.getDossierPartType());
		obj.put(DossierFileTerm.DOSSIER_TEMPLATE_NO, model.getDossierTemplateNo());
		obj.put(DossierFileTerm.FILE_TEMPLATE_NO, model.getFileTemplateNo());
		return obj;
	}
	
	public static JSONObject convertExecuteOneActionToJSON(ExecuteOneAction model) {
		JSONObject obj = JSONFactoryUtil.createJSONObject();
		obj.put(DossierActionTerm.ACTION_CODE, model.getActionCode());
		obj.put(DossierActionTerm.ACTION_NOTE, model.getActionNote());
		obj.put(DossierActionTerm.ACTION_USER, model.getActionUser());
		obj.put(DossierActionTerm.PAYLOAD, model.getPayload());
		return obj;
	}
	
	public static JSONObject convertDossierToJSON(DossierDetailModel model) {
		JSONObject obj = JSONFactoryUtil.createJSONObject();
		obj.put(DossierTerm.ADDRESS, model.getAddress());
		obj.put(DossierTerm.DOSSIER_NO, model.getDossierNo());
		obj.put(DossierTerm.DOSSIER_ID, model.getDossierId());
		return obj;
	}
	
	public static HashMap<String, String> convertDossierDocumentHttpParams(DossierDocumentModel model) {
		HashMap<String, String> params = new HashMap<>();
	    
	    if (Validator.isNotNull(model.getReferenceUid())) {
		    params.put(DossierDocumentTerm.REFERENCE_UID, model.getReferenceUid());	    	
	    }
	    if (Validator.isNotNull(model.getDocumentType())) {
		    params.put(DossierDocumentTerm.DOCUMENT_TYPE, model.getDocumentType());	    	
	    }
	    if (Validator.isNotNull(model.getDocumentName())) {
		    params.put(DossierDocumentTerm.DOCUMENT_NAME, model.getDocumentName());	    	
	    }
	    if (Validator.isNotNull(model.getDocumentCode())) {
		    params.put(DossierDocumentTerm.DOCUMENT_CODE, model.getDocumentCode());	    	
	    }
	    
	    return params;
	}		
	
	public static DossierDocumentModel convertDossierDocument(JSONObject jsonObj) {
		DossierDocumentModel result = new DossierDocumentModel();
		
		if (jsonObj.has(DossierDocumentTerm.REFERENCE_UID)) {
			result.setReferenceUid(jsonObj.getString(DossierDocumentTerm.REFERENCE_UID));
		}
		if (jsonObj.has(DossierDocumentTerm.DOCUMENT_NAME)) {
			result.setDocumentName(jsonObj.getString(DossierDocumentTerm.DOCUMENT_NAME));
		}
		if (jsonObj.has(DossierDocumentTerm.DOCUMENT_TYPE)) {
			result.setDocumentType(jsonObj.getString(DossierDocumentTerm.DOCUMENT_TYPE));
		}
		if (jsonObj.has(DossierDocumentTerm.DOCUMENT_CODE)) {
			result.setDocumentCode(jsonObj.getString(DossierDocumentTerm.DOCUMENT_CODE));
		}
		return result;
	}
	
	public static DossierDocumentModel convertDossierDocument(DossierDocument dossierDocument) {
		DossierDocumentModel result = new DossierDocumentModel();
		result.setDocumentCode(dossierDocument.getDocumentCode());
		result.setDocumentName(dossierDocument.getDocumentName());
		result.setDocumentType(dossierDocument.getDocumentType());
		result.setReferenceUid(dossierDocument.getReferenceUid());
		return result;
	}
}
