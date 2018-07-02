package org.opencps.dossiermgt.rest.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.rest.model.DossierDetailModel;
import org.opencps.dossiermgt.rest.model.DossierInputModel;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;

public class OpenCPSConverter {
	public static List<NameValuePair> convertHttpParams(DossierInputModel model) {
	    List<NameValuePair> params = new ArrayList<NameValuePair>();
	    if (Validator.isNotNull(model.getReferenceUid())) {
		    params.add(new BasicNameValuePair(DossierTerm.REFERENCE_UID, model.getReferenceUid()));	    	
	    }
	    if (Validator.isNotNull(model.getServiceCode())) {
		    params.add(new BasicNameValuePair(DossierTerm.SERVICE_CODE, model.getServiceCode()));	    	
	    }
	    if (Validator.isNotNull(model.getGovAgencyCode())) {
		    params.add(new BasicNameValuePair(DossierTerm.GOV_AGENCY_CODE, model.getGovAgencyCode()));	    	
	    }
	    if (Validator.isNotNull(model.getDossierTemplateNo())) {
		    params.add(new BasicNameValuePair(DossierTerm.DOSSIER_TEMPLATE_NO, model.getDossierTemplateNo()));	    	
	    }
	    params.add(new BasicNameValuePair(DossierTerm.APPLICANT_NAME, model.getApplicantName()));
	    params.add(new BasicNameValuePair(DossierTerm.APPLICANT_ID_TYPE, model.getApplicantIdType()));
	    params.add(new BasicNameValuePair(DossierTerm.APPLICANT_ID_NO, model.getApplicantIdNo()));
	    params.add(new BasicNameValuePair(DossierTerm.APPLICANT_ID_DATE, model.getApplicantIdDate()));
	    params.add(new BasicNameValuePair(DossierTerm.ADDRESS, model.getAddress()));
	    params.add(new BasicNameValuePair(DossierTerm.CITY_CODE, model.getCityCode()));
	    params.add(new BasicNameValuePair(DossierTerm.DISTRICT_CODE, model.getDistrictCode()));
	    params.add(new BasicNameValuePair(DossierTerm.WARD_CODE, model.getWardCode()));
	    params.add(new BasicNameValuePair(DossierTerm.CONTACT_NAME, model.getContactName()));
	    params.add(new BasicNameValuePair(DossierTerm.CONTACT_TEL_NO, model.getContactTelNo()));
	    params.add(new BasicNameValuePair(DossierTerm.CONTACT_EMAIL, model.getContactEmail()));
	    if (Validator.isNotNull(model.getPassword())) {
		    params.add(new BasicNameValuePair(DossierTerm.PASSWORD, model.getPassword()));	    	
	    }
	    if (Validator.isNotNull(model.getOnline())) {
		    params.add(new BasicNameValuePair(DossierTerm.ONLINE, model.getOnline()));	    	
	    }
	    if (Validator.isNotNull(model.getViaPostal())) {
	    	params.add(new BasicNameValuePair(DossierTerm.VIA_POSTAL, model.getViaPostal()));
	    }
	    if (Validator.isNotNull(model.getPostalServiceCode())) {
	    	params.add(new BasicNameValuePair(DossierTerm.POSTAL_SERVICE_CODE, model.getPostalServiceCode()));	    	
	    }
	    if (Validator.isNotNull(model.getPostalCityCode())) {
	    	params.add(new BasicNameValuePair(DossierTerm.POSTAL_CITY_CODE, model.getPostalCityCode()));	    		    	
	    }
	    if (Validator.isNotNull(model.getPostalDistrictCode())) {
	    	params.add(new BasicNameValuePair(DossierTerm.POSTAL_DISTRICT_CODE, model.getPostalDistrictCode()));	    		    	
	    }
	    if (Validator.isNotNull(model.getPostalWardCode())) {
	    	params.add(new BasicNameValuePair(DossierTerm.POSTAL_WARD_CODE, model.getPostalWardCode()));	    		    	
	    }
	    if (Validator.isNotNull(model.getPostalTelNo())) {
	    	params.add(new BasicNameValuePair(DossierTerm.POSTAL_TEL_NO, model.getPostalTelNo()));	    		    		    	
	    }
	    if (Validator.isNotNull(model.getApplicantNote())) {
	    	params.add(new BasicNameValuePair(DossierTerm.APPLICANT_NOTE, model.getApplicantNote()));	    		    		    		    	
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
	
	public static DossierDetailModel convertDossier(JSONObject jsonObj) {
		try {
			Gson gson = new Gson();
			
			return gson.fromJson(jsonObj.toJSONString(), DossierDetailModel.class);
		}
		catch (JsonSyntaxException e) {
		}
			
		return null;
	}
}
