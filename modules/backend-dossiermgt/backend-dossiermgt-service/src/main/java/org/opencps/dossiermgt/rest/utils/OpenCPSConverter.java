package org.opencps.dossiermgt.rest.utils;

import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opencps.dossiermgt.constants.DossierActionTerm;
import org.opencps.dossiermgt.constants.DossierDocumentTerm;
import org.opencps.dossiermgt.constants.DossierFileTerm;
import org.opencps.dossiermgt.constants.DossierMarkTerm;
import org.opencps.dossiermgt.constants.DossierPartTerm;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.PaymentFileTerm;
import org.opencps.dossiermgt.lgsp.model.MSyncDocument;
import org.opencps.dossiermgt.lgsp.model.Mtoken;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.DossierDocument;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.OpencpsDossierStatistic;
import org.opencps.dossiermgt.model.OpencpsVotingStatistic;
import org.opencps.dossiermgt.model.PaymentFile;
import org.opencps.dossiermgt.rest.model.DossierDetailModel;
import org.opencps.dossiermgt.rest.model.DossierDocumentModel;
import org.opencps.dossiermgt.rest.model.DossierFileModel;
import org.opencps.dossiermgt.rest.model.DossierInputModel;
import org.opencps.dossiermgt.rest.model.DossierMarkInputModel;
import org.opencps.dossiermgt.rest.model.DossierMarkResultModel;
import org.opencps.dossiermgt.rest.model.DossierPublishModel;
import org.opencps.dossiermgt.rest.model.ExecuteOneAction;
import org.opencps.dossiermgt.rest.model.PaymentFileInputModel;
import org.opencps.dossiermgt.scheduler.RESTFulConfiguration;
import org.opencps.dossiermgt.service.DossierActionLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.OpencpsVotingStatisticLocalServiceUtil;
import org.opencps.dossiermgt.service.PaymentFileLocalServiceUtil;

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
	    params.put(DossierTerm.DELEGATE_NAME, model.getDelegateName());
	    params.put(DossierTerm.DELEGATE_EMAIL, model.getDelegateEmail());
	    params.put(DossierTerm.DELEGATE_ADDRESS, model.getDelegateAddress());
	    params.put(DossierTerm.DOSSIER_NAME, model.getDossierName());
	    params.put(DossierTerm.ORIGIN_DOSSIER_NO, model.getOriginDossierNo());
	    params.put(DossierTerm.DELEGATE_TELNO, model.getDelegateTelNo());
	    params.put(DossierTerm.DELEGATE_CITYCODE, model.getCityCode());
	    params.put(DossierTerm.DELEGATE_CITYNAME, model.getCityName());
	    params.put(DossierTerm.DELEGATE_DISTRICTCODE, model.getDistrictCode());
	    params.put(DossierTerm.DELEGATE_DISTRICTNAME, model.getDistrictName());
	    params.put(DossierTerm.DELEGATE_WARDCODE, model.getWardCode());
	    params.put(DossierTerm.DELEGATE_WARDNAME, model.getWardName());

	    if (Validator.isNotNull(model.getPassword())) {
		    params.put(DossierTerm.SECRET, model.getPassword());	    	
	    }
	    if (Validator.isNotNull(model.getOnline())) {
		    params.put(DossierTerm.ONLINE, model.getOnline());	    	
	    }
	    if (Validator.isNotNull(model.getViaPostal())) {
	    	params.put(DossierTerm.VIA_POSTAL, model.getViaPostal());
	    }
	    if (Validator.isNotNull(model.getPostalAddress())) {
	    	params.put(DossierTerm.POSTAL_ADDRESS, model.getPostalAddress());	
	    }
	    if (Validator.isNotNull(model.getPostalServiceCode())) {
	    	params.put(DossierTerm.POSTAL_SERVICE_CODE, model.getPostalServiceCode());	    	
	    }
	    if (Validator.isNotNull(model.getPostalServiceName())) {
	    	params.put(DossierTerm.POSTAL_SERVICE_NAME, model.getPostalServiceName());	    	
	    }
	    if (Validator.isNotNull(model.getPostalCityCode())) {
	    	params.put(DossierTerm.POSTAL_CITY_CODE, model.getPostalCityCode());	    		    	
	    }
	    if (Validator.isNotNull(model.getPostalCityName())) {
	    	params.put(DossierTerm.POSTAL_CITY_NAME, model.getPostalCityName());	    		    	
	    }
	    if (Validator.isNotNull(model.getPostalDistrictCode())) {
	    	params.put(DossierTerm.POSTAL_DISTRICT_CODE, model.getPostalDistrictCode());	    		    	
	    }
	    if (Validator.isNotNull(model.getPostalDistrictName())) {
	    	params.put(DossierTerm.POSTAL_DISTRICT_NAME, model.getPostalDistrictName());	    		    	
	    }
	    if (Validator.isNotNull(model.getPostalWardCode())) {
	    	params.put(DossierTerm.POSTAL_WARD_CODE, model.getPostalWardCode());	    		    	
	    }
	    if (Validator.isNotNull(model.getPostalWardName())) {
	    	params.put(DossierTerm.POSTAL_WARD_NAME, model.getPostalWardName());	    		    	
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
	    if (Validator.isNotNull(model.getOnline())) {
	    	params.put(DossierTerm.ONLINE, model.getOnline());	    	
	    }
	    if (Validator.isNotNull(model.getServerNo())) {
	    	params.put(DossierTerm.SERVER_NO, model.getServerNo());
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
	    params.put(DossierTerm.DOSSIER_NAME, model.getDossierName());
	    params.put(DossierTerm.DOSSIER_ACTION_ID, model.getDossierActionId() != null ? model.getDossierActionId(): 0);

	    if (Validator.isNotNull(model.getPassword())) {
		    params.put(DossierTerm.SECRET, model.getPassword());	    	
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
	    if (Validator.isNotNull(model.getSubmissionNote())) {
	    	params.put(DossierTerm.SUBMISSION_NOTE, model.getSubmissionNote());
	    }
	    if (Validator.isNotNull(model.getLockState())) {
	    	params.put(DossierTerm.LOCK_STATE, model.getLockState());
	    }
	    if (Validator.isNotNull(model.getCounter())) {
	    	params.put(DossierTerm.COUNTER, model.getCounter());
	    }
	    if (Validator.isNotNull(model.getPostalAddress())) {
	    	params.put(DossierTerm.POSTAL_ADDRESS, model.getPostalAddress());
	    }
	    if (Validator.isNotNull(model.getPostalCityCode())) {
	    	params.put(DossierTerm.POSTAL_CITY_CODE, model.getPostalCityCode());
	    }
	    if (Validator.isNotNull(model.getPostalCityName())) {
	    	params.put(DossierTerm.POSTAL_CITY_NAME, model.getPostalCityName());
	    }
	    if (Validator.isNotNull(model.getDelegateName())) {
	    	params.put(DossierTerm.DELEGATE_NAME, model.getDelegateName());
	    }
	    if (Validator.isNotNull(model.getDelegateIdNo())) {
	    	params.put(DossierTerm.DELEGATE_ID_NO, model.getDelegateIdNo());
	    }
	    if (Validator.isNotNull(model.getDelegateTelNo())) {
	    	params.put(DossierTerm.DELEGATE_TELNO, model.getDelegateTelNo());
	    }
	    if (Validator.isNotNull(model.getDelegateEmail())) {
	    	params.put(DossierTerm.DELEGATE_EMAIL, model.getDelegateEmail());
	    }
	    if (Validator.isNotNull(model.getDelegateAddress())) {
	    	params.put(DossierTerm.DELEGATE_ADDRESS, model.getDelegateAddress());
	    }
	    if (Validator.isNotNull(model.getDelegateCityCode())) {
	    	params.put(DossierTerm.DELEGATE_CITYCODE, model.getDelegateCityCode());
	    }
	    if (Validator.isNotNull(model.getDelegateCityName())) {
	    	params.put(DossierTerm.DELEGATE_CITYNAME, model.getDelegateCityName());
	    }
	    if (Validator.isNotNull(model.getDelegateDistrictCode())) {
	    	params.put(DossierTerm.DELEGATE_DISTRICTCODE, model.getDelegateDistrictCode());
	    }
	    if (Validator.isNotNull(model.getDelegateDistrictName())) {
	    	params.put(DossierTerm.DELEGATE_DISTRICTNAME, model.getDelegateDistrictName());
	    }
	    if (Validator.isNotNull(model.getDelegateWardCode())) {
	    	params.put(DossierTerm.DELEGATE_WARDCODE, model.getDelegateWardCode());
	    }
	    if (Validator.isNotNull(model.getDelegateWardName())) {
	    	params.put(DossierTerm.DELEGATE_WARDNAME, model.getDelegateWardName());
	    }
	    if (Validator.isNotNull(model.getProcessNo())) {
	    	params.put(DossierTerm.PROCESS_NO, model.getProcessNo());
	    }
	    if (Validator.isNotNull(model.getDurationCount())) {
	    	params.put(DossierTerm.DURATION_COUNT, model.getDurationCount());
	    }
	    if (Validator.isNotNull(model.getDurationUnit())) {
	    	params.put(DossierTerm.DURATION_UNIT, model.getDurationUnit());
	    }
	    if (Validator.isNotNull(model.getSampleCount())) {
	    	params.put(DossierTerm.SAMPLE_COUNT, model.getSampleCount());
	    }
	    if (Validator.isNotNull(model.getDossierName())) {
	    	params.put(DossierTerm.DOSSIER_NAME, model.getDossierName());
	    }
	    
	    return params;
	}	
	
	public static Map<String, Object> convertPublishHttpParams(Dossier model) {
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
	    params.put(DossierTerm.DOSSIER_NAME, model.getDossierName());
	    params.put(DossierTerm.DOSSIER_ACTION_ID, model.getDossierActionId());

	    if (Validator.isNotNull(model.getPassword())) {
		    params.put(DossierTerm.SECRET, model.getPassword());	    	
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
	    	params.put(DossierTerm.ORIGINALLITY, String.valueOf(model.getOriginality()));
	    }
	    if (Validator.isNotNull(model.getCreateDate())) {
	    	params.put(DossierTerm.CREATE_DATE, (model.getCreateDate() != null ? model.getCreateDate().getTime() : 0l));
	    }
	    if (Validator.isNotNull(model.getModifiedDate())) {
	    	params.put(DossierTerm.MODIFIED_DATE, model.getModifiedDate() != null ? model.getModifiedDate().getTime() : 0l);
	    }
	    if (Validator.isNotNull(model.getSubmitDate())) {
	    	params.put(DossierTerm.SUBMIT_DATE, model.getSubmitDate() != null ? model.getSubmitDate().getTime() : 0l);
	    }
	    if (Validator.isNotNull(model.getReceiveDate())) {
	    	params.put(DossierTerm.RECEIVE_DATE, model.getReceiveDate() != null ? model.getReceiveDate().getTime() : 0l);
	    }
	    if (Validator.isNotNull(model.getDueDate())) {
	    	params.put(DossierTerm.DUE_DATE, model.getDueDate() != null ? model.getDueDate().getTime() : 0l);
	    }
	    if (Validator.isNotNull(model.getReleaseDate())) {
	    	params.put(DossierTerm.RELEASE_DATE, model.getReleaseDate() != null ? model.getReleaseDate().getTime() : 0l);
	    }
	    if (Validator.isNotNull(model.getFinishDate())) {
	    	params.put(DossierTerm.FINISH_DATE, model.getFinishDate() != null ?  model.getFinishDate().getTime() : 0l);
	    }
	    if (Validator.isNotNull(model.getCancellingDate())) {
	    	params.put(DossierTerm.CANCELLING_DATE, model.getCancellingDate() != null ? model.getCancellingDate().getTime() : 0l);
	    }
	    if (Validator.isNotNull(model.getCorrecttingDate())) {
	    	params.put(DossierTerm.CORRECTING_DATE, model.getCorrecttingDate() != null ? model.getCorrecttingDate().getTime() : 0l);
	    }
	    if (Validator.isNotNull(model.getEndorsementDate())) {
	    	params.put(DossierTerm.ENDORSEMENT_DATE, model.getEndorsementDate() != null ? model.getEndorsementDate().getTime() : 0l);
	    }
	    if (Validator.isNotNull(model.getExtendDate())) {
	    	params.put(DossierTerm.EXTEND_DATE, model.getExtendDate() != null ? model.getExtendDate().getTime() : 0l);
	    }
	    if (Validator.isNotNull(model.getProcessDate())) {
	    	params.put(DossierTerm.PROCESS_DATE, model.getProcessDate() != null ? model.getProcessDate().getTime() : 0l);
	    }
	    if (Validator.isNotNull(model.getSubmissionNote())) {
	    	params.put(DossierTerm.SUBMISSION_NOTE, model.getSubmissionNote());
	    }
	    if (Validator.isNotNull(model.getLockState())) {
	    	params.put(DossierTerm.LOCK_STATE, model.getLockState());
	    }
	    
	    return params;
	}	
	
	public static Map<String, Object> convertDossierMarkInputHttpParams(DossierMarkInputModel model) {
	    Map<String, Object> params = new HashMap<>();
	    
	    if (Validator.isNotNull(model.getFileMark())) {
		    params.put(DossierPartTerm.FILE_MARK, model.getFileMark());	    	
	    }
	    if (Validator.isNotNull(model.getFileCheck())) {
		    params.put(DossierPartTerm.FILE_CHECK, model.getFileCheck());	    	
	    }
	    if (Validator.isNotNull(model.getFileComment())) {
		    params.put(DossierPartTerm.FILE_COMMENT, model.getFileComment());	    	
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
		if (jsonObj.has(DossierTerm.SECRET)) {
			model.setPassword(jsonObj.getString(DossierTerm.SECRET));
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
	
		if (jsonObj.has(DossierTerm.DOSSIER_ID)) {
			model.setDossierId(jsonObj.getLong(DossierTerm.DOSSIER_ID));
		}
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
		if (jsonObj.has(DossierTerm.SECRET)) {
			model.setPassword(jsonObj.getString(DossierTerm.SECRET));
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
		if (jsonObj.has(DossierTerm.DOSSIER_NAME)) {
			model.setDossierName(jsonObj.getString(DossierTerm.DOSSIER_NAME));
		}
		if (jsonObj.has(DossierTerm.DOSSIER_ACTION_ID)) {
			model.setDossierActionId(jsonObj.getLong(DossierTerm.DOSSIER_ACTION_ID));
		}
		if (jsonObj.has(DossierTerm.SUBMISSION_NOTE)) {
			model.setSubmissionNote(jsonObj.getString(DossierTerm.SUBMISSION_NOTE));
		}
		if (jsonObj.has(DossierTerm.BRIEF_NOTE)) {
			model.setBriefNote(jsonObj.getString(DossierTerm.BRIEF_NOTE));
		}
		if (jsonObj.has(DossierTerm.LOCK_STATE)) {
			model.setLockState(jsonObj.getString(DossierTerm.LOCK_STATE));
		}
		if (jsonObj.has(DossierTerm.COUNTER)) {
			model.setCounter(jsonObj.getInt(DossierTerm.COUNTER));
		}
		if (jsonObj.has(DossierTerm.POSTAL_ADDRESS)) {
			model.setPostalAddress(jsonObj.getString(DossierTerm.POSTAL_ADDRESS));
		}
		if (jsonObj.has(DossierTerm.POSTAL_CITY_CODE)) {
			model.setPostalCityCode(jsonObj.getString(DossierTerm.POSTAL_CITY_CODE));
		}
		if (jsonObj.has(DossierTerm.POSTAL_CITY_NAME)) {
			model.setPostalCityName(jsonObj.getString(DossierTerm.POSTAL_CITY_NAME));
		}
		if (jsonObj.has(DossierTerm.POSTAL_DISTRICT_CODE)) {
			model.setPostalDistrictCode(jsonObj.getString(DossierTerm.POSTAL_DISTRICT_CODE));
		}
		if (jsonObj.has(DossierTerm.POSTAL_DISTRICT_NAME)) {
			model.setPostalDistrictName(jsonObj.getString(DossierTerm.POSTAL_DISTRICT_NAME));
		}
		if (jsonObj.has(DossierTerm.POSTAL_WARD_CODE)) {
			model.setPostalWardCode(jsonObj.getString(DossierTerm.POSTAL_WARD_CODE));
		}
		if (jsonObj.has(DossierTerm.POSTAL_WARD_NAME)) {
			model.setPostalWardName(jsonObj.getString(DossierTerm.POSTAL_WARD_NAME));
		}
		if (jsonObj.has(DossierTerm.POSTAL_TEL_NO)) {
			model.setPostalTelNo(jsonObj.getString(DossierTerm.POSTAL_TEL_NO));
		}
		if (jsonObj.has(DossierTerm.POSTAL_SERVICE_CODE)) {
			model.setPostalServiceCode(jsonObj.getString(DossierTerm.POSTAL_SERVICE_CODE));
		}
		if (jsonObj.has(DossierTerm.POSTAL_SERVICE_NAME)) {
			model.setPostalServiceName(jsonObj.getString(DossierTerm.POSTAL_SERVICE_NAME));
		}
		if (jsonObj.has(DossierTerm.DELEGATE_NAME)) {
			model.setDelegateName(jsonObj.getString(DossierTerm.DELEGATE_NAME));
		}
		if (jsonObj.has(DossierTerm.DELEGATE_ADDRESS)) {
			model.setDelegateAddress(jsonObj.getString(DossierTerm.DELEGATE_ADDRESS));
		}
		if (jsonObj.has(DossierTerm.DELEGATE_ID_NO)) {
			model.setDelegateIdNo(jsonObj.getString(DossierTerm.DELEGATE_ID_NO));
		}
		if (jsonObj.has(DossierTerm.DELEGATE_TELNO)) {
			model.setDelegateTelNo(jsonObj.getString(DossierTerm.DELEGATE_TELNO));
		}
		if (jsonObj.has(DossierTerm.DELEGATE_EMAIL)) {
			model.setDelegateEmail(jsonObj.getString(DossierTerm.DELEGATE_EMAIL));
		}
		if (jsonObj.has(DossierTerm.DELEGATE_CITYCODE)) {
			model.setDelegateCityCode(jsonObj.getString(DossierTerm.DELEGATE_CITYCODE));
		}
		if (jsonObj.has(DossierTerm.DELEGATE_CITYNAME)) {
			model.setDelegateCityName(jsonObj.getString(DossierTerm.DELEGATE_CITYNAME));
		}
		if (jsonObj.has(DossierTerm.DELEGATE_DISTRICTCODE)) {
			model.setDelegateDistrictCode(jsonObj.getString(DossierTerm.DELEGATE_DISTRICTCODE));
		}
		if (jsonObj.has(DossierTerm.DELEGATE_DISTRICTNAME)) {
			model.setDelegateDistrictName(jsonObj.getString(DossierTerm.DELEGATE_DISTRICTNAME));
		}
		if (jsonObj.has(DossierTerm.DELEGATE_WARDCODE)) {
			model.setDelegateWardCode(jsonObj.getString(DossierTerm.DELEGATE_WARDCODE));
		}
		if (jsonObj.has(DossierTerm.DELEGATE_WARDNAME)) {
			model.setDelegateWardName(jsonObj.getString(DossierTerm.DELEGATE_WARDNAME));
		}
		if (jsonObj.has(DossierTerm.PROCESS_NO)) {
			model.setProcessNo(jsonObj.getString(DossierTerm.PROCESS_NO));
		}
		if (jsonObj.has(DossierTerm.DURATION_COUNT)) {
			model.setDurationCount(jsonObj.getDouble(DossierTerm.DURATION_COUNT));
		}
		if (jsonObj.has(DossierTerm.DURATION_UNIT)) {
			model.setDurationUnit(jsonObj.getInt(DossierTerm.DURATION_UNIT));
		}
		if (jsonObj.has(DossierTerm.SAMPLE_COUNT)) {
			model.setSampleCount(jsonObj.getLong(DossierTerm.SAMPLE_COUNT));
		}
		if (jsonObj.has(DossierTerm.DOSSIER_NAME)) {
			model.setDossierName(jsonObj.getString(DossierTerm.DOSSIER_NAME));
		}
		if (jsonObj.has(DossierTerm.META_DATA)) {
			model.setMetaData(jsonObj.getString(DossierTerm.META_DATA));
		}
		
		return model;
	}	
	
	public static MSyncDocument convertLGSPSyncDocument(JSONObject jsonObj) {
		MSyncDocument model = new MSyncDocument();
		
		return model;
	}
	
	public static Mtoken convertMtoken(JSONObject jsonObj) {
		Mtoken model = new Mtoken();
		if (jsonObj.has("access_token")) {
			model.setAccessToken(jsonObj.getString("access_token"));
		}
		if (jsonObj.has("scope")) {
			model.setScope(jsonObj.getString("scope"));
		}
		if (jsonObj.has("token_type")) {
			model.setTokenType(jsonObj.getString("token_type"));
		}
		if (jsonObj.has("expires_in")) {
			model.setExpiresIn(jsonObj.getInt("expires_in"));
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
//		_log.info("strMessage: "+strMessage);
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
		if (jsonObj.has(DossierTerm.DOSSIER_NAME)) {
			model.setDossierName(jsonObj.getString(DossierTerm.DOSSIER_NAME));
		}
		if (jsonObj.has(DossierTerm.DOSSIER_ACTION_ID)) {
			model.setDossierActionId(jsonObj.getInt(DossierTerm.DOSSIER_ACTION_ID));
		}
		/*
		if (jsonObj.has(DossierTerm.SERVER_NO)) {
			model.setServerNo(jsonObj.getString(DossierTerm.SERVER_NO));
		}
		*/
		if (jsonObj.has(DossierTerm.META_DATA)) {
			model.setMetaData(jsonObj.getString(DossierTerm.META_DATA));
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
		model.setServiceName(dossier.getServiceName());
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
		model.setDelegateEmail(dossier.getDelegateEmail());
		model.setDelegateCityCode(dossier.getDelegateCityCode());
		model.setDelegateDistrictCode(dossier.getDelegateDistrictCode());
		model.setDelegateWardCode(dossier.getDelegateWardCode());
		model.setViaPostal(String.valueOf(dossier.getViaPostal()));
		model.setPostalAddress(dossier.getPostalAddress());
		model.setPostalCityCode(dossier.getPostalCityCode());
		model.setPostalDistrictCode(dossier.getPostalDistrictCode());
		model.setPostalServiceCode(dossier.getPostalServiceCode());
		model.setPostalTelNo(dossier.getPostalTelNo());
		model.setPostalWardCode(dossier.getPostalWardCode());
		model.setOriginDossierNo(dossier.getOriginDossierNo());
		model.setDossierName(dossier.getDossierName());
		if (Validator.isNotNull(dossier.getPassword())) {
			model.setPassword(dossier.getPassword());
		}
		model.setOnline(String.valueOf(dossier.getOnline()));
		if (Validator.isNotNull(dossier.getServerNo())
				&& dossier.getServerNo().contains(StringPool.COMMA)) {
			if (dossier.getServerNo().contains(StringPool.AT)) {
				String serverNoProcess = dossier.getServerNo().split(StringPool.COMMA)[0];
				String serviceCode = serverNoProcess.split(StringPool.AT)[1];
				model.setServiceCode(serviceCode);
				model.setServerNo(dossier.getServerNo().split(StringPool.COMMA)[1]);
			}
			else {
				model.setServerNo(dossier.getServerNo());				
			}
		}
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
		int status = Integer.parseInt(Validator.isNotNull(jsonObj.getString(RESTFulConfiguration.STATUS)) ? jsonObj.getString(RESTFulConfiguration.STATUS) : HttpURLConnection.HTTP_OK + ""
				);
		if (status == HttpURLConnection.HTTP_OK) {
			ExecuteOneAction result = new ExecuteOneAction();
			
			return result;
		}
		else {
			return null;
		}
	}
	
	public static HashMap<String, String> convertDossierFileHttpParams(DossierFileModel model) {
		HashMap<String, String> params = new HashMap<>();
		params.put(DossierFileTerm.REFERENCE_UID, model.getReferenceUid());
		params.put(DossierFileTerm.MODIFIED_DATE, model.getModifiedDate());
		params.put(DossierFileTerm.DOSSIER_PART_NO, model.getDossierPartNo());
		params.put(DossierFileTerm.DISPLAY_NAME, model.getDisplayName());
		params.put(DossierFileTerm.DOSSIER_TEMPLATE_NO, model.getDossierTemplateNo());
		params.put(DossierFileTerm.FILE_TEMPLATE_NO, model.getFileTemplateNo());
		params.put(DossierFileTerm.FORM_DATA, model.getFormData());
		params.put(DossierFileTerm.FILE_TYPE, model.getFileType());
		params.put(DossierFileTerm.IS_SYNC, "true");
		if(Validator.isNotNull(model.isRemoved())) {
			params.put(DossierFileTerm.REMOVED, Boolean.toString(model.isRemoved()));	
		}
		if(Validator.isNotNull(model.isEForm())) {
			params.put(DossierFileTerm.E_FORM, Boolean.toString(model.isEForm()));	
		}
		
		return params;
	}
	
	public static HashMap<String, String> convertDossierFileEFormHttpParams(DossierFileModel model) {
		HashMap<String, String> params = new HashMap<>();
		params.put(DossierFileTerm.FORM_DATA, model.getFormData());
		if(Validator.isNotNull(model.isRemoved())) {
			params.put(DossierFileTerm.REMOVED, Boolean.toString(model.isRemoved()));	
		}
		if(Validator.isNotNull(model.isEForm())) {
			params.put(DossierFileTerm.E_FORM, Boolean.toString(model.isEForm()));	
		}
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
	    if (Validator.isNotNull(model.getFeeAmount())) {
	    	params.put(PaymentFileTerm.FEE_AMOUNT, GetterUtil.getLong(model.getFeeAmount()));
	    }
	    if (Validator.isNotNull(model.getPaymentStatus())) {
	    	params.put(PaymentFileTerm.PAYMENT_STATUS, GetterUtil.getInteger(model.getPaymentStatus()));
	    }
	    if (Validator.isNotNull(model.getInvoiceTemplateNo())) {
	    	params.put(PaymentFileTerm.INVOICE_TEMPLATE_NO, model.getInvoiceTemplateNo());
	    }
	    if (Validator.isNotNull(model.getConfirmFileEntryId())) {
	    	params.put(PaymentFileTerm.CONFIRM_FILE_ENTRY_ID, GetterUtil.getLong(model.getConfirmFileEntryId()));
	    }
	    if (Validator.isNotNull(model.getEinvoice())) {
	    	params.put(PaymentFileTerm.EINVOICE, model.getEinvoice());
	    }
	    if (Validator.isNotNull(model.getAdvanceAmount())) {
	    	params.put(PaymentFileTerm.ADVANCE_AMOUNT, GetterUtil.getLong(model.getAdvanceAmount()));
	    }
	    if (Validator.isNotNull(model.getServiceAmount())) {
	    	params.put(PaymentFileTerm.SERVICE_AMOUNT, model.getServiceAmount());
	    }
	    if (Validator.isNotNull(model.getShipAmount())) {
	    	params.put(PaymentFileTerm.SHIP_AMOUNT, GetterUtil.getLong(model.getShipAmount()));
	    }
	    if (Validator.isNotNull(model.getPaymentMethod())) {
	    	params.put(PaymentFileTerm.PAYMENT_METHOD, model.getPaymentMethod());
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
			result.setPaymentAmount(jsonObj.getLong(PaymentFileTerm.PAYMENT_AMOUNT));
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
		obj.put(DossierFileTerm.REMOVED, model.isRemoved());
		obj.put(DossierFileTerm.E_FORM, model.isEForm());
		
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
	
	public static JSONObject convertDossierToLGSPJSON(DossierPublishModel model) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		result.put("DocTypeCode", model.getServiceCode());
		result.put("DocTypeName", model.getServiceName());
		result.put("DocCode", model.getDossierNo());
		result.put("CitizenName", model.getApplicantName());
		result.put("CitizenInfo", model.getApplicantNote());
		result.put("ApplicantsId", model.getApplicantIdNo());
		if ("citizen".equals(model.getApplicantIdType())) {
			result.put("ApplicantsType", 1);			
		}
		else if ("business".equals(model.getApplicantIdType())) {
			result.put("ApplicantsType", 2);
		}
		else if ("organization".equals(model.getApplicantIdType())) {
			result.put("ApplicantsType", 3);
		}
		else {
			result.put("ApplicantsType", 4);
		}
		result.put("Address", model.getAddress());
		result.put("Email", model.getContactEmail());
		result.put("Phone", model.getContactTelNo());
		result.put("Compendium", model.getBriefNote());
		if (model.getReceiveDate() != 0) {
			result.put("DateReceived", convertToUTCDate(new Date(model.getReceiveDate())));
		}
		if (model.getDueDate() != 0) {
			result.put("DateAppointed", convertToUTCDate(new Date(model.getDueDate())));
		}		
		result.put("IsSuccess", isSuccess(model));
		if (Validator.isNotNull(model.getReleaseDate())) {
			result.put("SuccessDate", convertToUTCDate(new Date(model.getReleaseDate())));
		}
		else {
			result.put("SuccessDate", "0000-00-00T00:00:00.000+0700");
		}
		result.put("ApproverName", StringPool.BLANK);
		result.put("ApproverPosition", StringPool.BLANK);
		result.put("SuccessNote", StringPool.BLANK);
		if (DossierTerm.DOSSIER_STATUS_DONE.equals(model.getDossierStatus())) {
			result.put("IsReturned", true);
			result.put("ReturnedDate", convertToUTCDate(new Date(model.getFinishDate())));
		}
		else {
			result.put("IsReturned", false);
			result.put("ReturnedDate", convertToUTCDate(new Date(model.getDueDate())));
		}
		result.put("ReturnNote", StringPool.BLANK);
		if ("0".equalsIgnoreCase(model.getViaPostal())) {
			result.put("ReturnedType", 0);
		}
		else {
			result.put("ReturnedType", 1);
		}
		if (DossierTerm.DOSSIER_STATUS_DONE.equals(model.getDossierStatus())
				|| DossierTerm.DOSSIER_STATUS_CANCELLED.equals(model.getDossierStatus())
				|| DossierTerm.DOSSIER_STATUS_DENIED.equals(model.getDossierStatus())) {
			result.put("FinishedDate", convertToUTCDate(new Date(model.getFinishDate())));
		}
		if (DossierTerm.DOSSIER_STATUS_RELEASING.equals(model.getDossierStatus())) {
			result.put("Status", 2);
		}
		else {
			result.put("Status", 1);			
		}
		result.put("ProcessingOrganName", model.getGovAgencyName());
		result.put("HasSupplementary", false);
		result.put("Note", StringPool.BLANK);
		List<DossierFile> lstFiles = DossierFileLocalServiceUtil.findByDID(model.getDossierId());
		JSONArray attachmentsArr = JSONFactoryUtil.createJSONArray();
		
		for (DossierFile df : lstFiles) {
			JSONObject attachmentObj = JSONFactoryUtil.createJSONObject();
			attachmentObj.put("AttachmentId", df.getDossierFileId());
			attachmentObj.put("AttachmentName", df.getDisplayName());
			attachmentObj.put("IsDeleted", df.getRemoved());
			attachmentObj.put("IsVerified", true);
			if (df.getFileEntryId() > 0) {
				FileEntry fileEntry;
				try {
					fileEntry = DLAppLocalServiceUtil.getFileEntry(df.getFileEntryId());
					File file = DLFileEntryLocalServiceUtil.getFile(fileEntry.getFileEntryId(), fileEntry.getVersion(),
							true);
					byte[] bytes = Base64.getEncoder().encode(Files.readAllBytes(file.toPath()));
					attachmentObj.put("Base64", new String(bytes));
				} catch (PortalException e) {
					_log.error(e);
				} catch (IOException e) {
					_log.error(e);
				}

			}
			attachmentsArr.put(attachmentObj);
		}
		
		result.put("Attachments", attachmentsArr);
		
		JSONArray docFeesArr = JSONFactoryUtil.createJSONArray();
		try {
			PaymentFile paymentFile = PaymentFileLocalServiceUtil.getByDossierId(model.getGroupId(), model.getDossierId());
			
			if (paymentFile != null) {
				JSONObject docFeeObj = JSONFactoryUtil.createJSONObject();
				if (Validator.isNotNull(paymentFile.getPaymentFee())) {
					docFeeObj.put("FeeName", paymentFile.getPaymentFee());				
				}
				docFeeObj.put("FeeType", 4);
				if (Validator.isNotNull(paymentFile.getPaymentAmount())) {
					docFeeObj.put("Price", paymentFile.getPaymentAmount());				
				}
				
				docFeesArr.put(docFeeObj);
			}
		}
		catch (Exception e) {
			_log.debug(e);
		}
		result.put("DocFees", docFeesArr);
		result.put("OrganInchargeIdLevel1", model.getGovAgencyCode());
		result.put("OrganInchargeName", model.getGovAgencyName());
		System.out.println("LGSP SYNC DOCUMENT");
		return result;
	}
	
	public static Boolean isSuccess(DossierPublishModel model) {
		if (DossierTerm.DOSSIER_STATUS_RELEASING.equals(model.getDossierStatus())) {
			return Boolean.TRUE;
		}
		else if (DossierTerm.DOSSIER_STATUS_DENIED.equals(model.getDossierStatus())) {
			return Boolean.FALSE;
		}
		else {
			return Boolean.FALSE;
		}
	}
	
	public static String convertToUTCDate(Date d) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		String s = df.format(d);
		return s;
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
	
	public static DossierMarkResultModel convertDossierMark(JSONObject jsonObj) {
		DossierMarkResultModel result = new DossierMarkResultModel();
		if (jsonObj.has(DossierMarkTerm.DOSSIER_MARK_ID)) {
			result.setDossierMarkId(jsonObj.getLong(DossierMarkTerm.DOSSIER_MARK_ID));
		}
		if (jsonObj.has(DossierPartTerm.FILE_MARK)) {
			result.setFileMark(jsonObj.getInt(DossierPartTerm.FILE_MARK));
		}
		if (jsonObj.has(DossierPartTerm.FILE_CHECK)) {
			result.setFileCheck(jsonObj.getInt(DossierPartTerm.FILE_CHECK));
		}
		if (jsonObj.has(DossierPartTerm.FILE_COMMENT)) {
			result.setFileComment(DossierPartTerm.FILE_COMMENT);
		}
		return result;
	}
	
	public static MSyncDocument convertDossierToLGSPSyncDocument(DossierPublishModel model) {
		MSyncDocument result = new MSyncDocument();
		result.setDocTypeCode(model.getServiceCode());
		result.setDocTypeName(model.getServiceName());
		result.setDocCode(model.getDossierNo());
		return result;
	}
	
	public static JSONObject convertToDocumentTraces(long dossierId) {
		JSONObject obj = JSONFactoryUtil.createJSONObject();
		
		Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
		if (dossier != null) {
			obj.put("DocumentId", dossier.getDossierId());
			obj.put("DocCode", dossier.getDossierNo());
			DossierAction da = DossierActionLocalServiceUtil.fetchDossierAction(dossier.getDossierActionId());
			if (da != null) {
				obj.put("UserName", da.getUserName());
				obj.put("UserPosition", StringPool.BLANK);
				obj.put("DateCreated", convertToUTCDate(da.getCreateDate()));
				obj.put("Comment", da.getActionNote());
				obj.put("Status", 0);
				obj.put("OrganizationInchargeIdLevel1", dossier.getGovAgencyCode());
				obj.put("OrganizationInchargeName", dossier.getGovAgencyName());
			}
		}
		
		return obj;
	}
	
	public static JSONObject convertStatisticsToLGSPJSON(OpencpsDossierStatistic statistic) {
		JSONObject obj = JSONFactoryUtil.createJSONObject();
		obj.put("Month", statistic.getMonth());
		obj.put("Year", statistic.getYear());
		if (statistic.getMonth() == 0) {
			obj.put("IsMonthStatistic", false);
		}
		else {
			obj.put("IsMonthStatistic", true);
		}
		obj.put("NewReception", statistic.getReceivedCount());
		obj.put("PreExtisting", statistic.getRemainingCount());
		obj.put("Total", statistic.getTotalCount());
		obj.put("TotalSolved", statistic.getDoneCount());
		obj.put("SolvedInTime", statistic.getOntimeCount());
		obj.put("SolvedInTimePercent", statistic.getOntimePercentage());
		obj.put("SolvedLatePercent", 1.0 * statistic.getOvertimeCount() / statistic.getReleaseCount());
		obj.put("SolvedLate", statistic.getOverdueCount());
		obj.put("TotalPending", statistic.getWaitingCount());
		obj.put("Pending", statistic.getUndueCount());
		obj.put("PendingLate", statistic.getOverdueCount());
		obj.put("PendingLatePercent", 1.0 * statistic.getOverdueCount() / statistic.getProcessingCount());
		obj.put("PendingPercent", 1.0 * statistic.getUndueCount() / statistic.getProcessingCount());
		obj.put("Note", StringPool.BLANK);
		obj.put("OrganizationInchargeIdlevel1", StringPool.BLANK);
		obj.put("OrganizationInchargeName", StringPool.BLANK);
		
		return obj;
	}

	public static JSONObject convertVotingStatisticsToLGSPJSON(OpencpsVotingStatistic statistic) {
		JSONObject obj = JSONFactoryUtil.createJSONObject();
		obj.put("DateCreated", convertToUTCDate(new Date()));
		obj.put("TotalVoted", statistic.getTotalVoted());
		obj.put("PercentVeryGood", Double.valueOf(statistic.getPercentVeryGood()));
		obj.put("PercentGood", Double.valueOf(statistic.getPercentGood()));
		obj.put("PercentBad", Double.valueOf(statistic.getPercentBad()));
		List<OpencpsVotingStatistic> lstVotings = OpencpsVotingStatisticLocalServiceUtil.fetchByG_M_Y_G_D(statistic.getGroupId(), statistic.getMonth(), statistic.getYear(), StringPool.BLANK, StringPool.BLANK);
		JSONArray questions = JSONFactoryUtil.createJSONArray();
		for (OpencpsVotingStatistic vt : lstVotings) {
			if (Validator.isNotNull(vt.getVotingCode())) {
				JSONObject question = JSONFactoryUtil.createJSONObject();
				question.put("DocTypeCode", StringPool.BLANK);
				question.put("Content", vt.getVotingSubject());
				question.put("PercentVeryGood", Double.valueOf(vt.getPercentVeryGood()));
				question.put("PercentGood", Double.valueOf(vt.getPercentGood()));
				question.put("PercentBad", Double.valueOf(vt.getPercentBad()));
				
				questions.put(question);				
			}
		}
		obj.put("Questions", questions);
		obj.put("OrganizationInchargeIdlevel1", StringPool.BLANK);
		obj.put("OrganizationInchargeName", StringPool.BLANK);
		
		return obj;
	}	
}
