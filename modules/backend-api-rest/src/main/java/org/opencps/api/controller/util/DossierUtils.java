package org.opencps.api.controller.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.opencps.api.dossier.model.CertNumberModel;
import org.opencps.api.dossier.model.DossierDataModel;
import org.opencps.api.dossier.model.DossierDetailModel;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.dossiermgt.action.DeliverableActions;
import org.opencps.dossiermgt.action.impl.DeliverableActionsImpl;
import org.opencps.dossiermgt.action.util.DossierOverDueUtils;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.model.Deliverable;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.DossierActionUser;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.ProcessStep;
import org.opencps.dossiermgt.service.DossierActionLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierActionUserLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessStepLocalServiceUtil;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

public class DossierUtils {

	public static List<DossierDataModel> mappingForGetList(List<Document> docs) {
		List<DossierDataModel> ouputs = new ArrayList<DossierDataModel>();

		for (Document doc : docs) {
			DossierDataModel model = new DossierDataModel();
			model.setDossierIdCTN(doc.get(DossierTerm.DOSSIER_ID+"CTN"));
			model.setDossierId(GetterUtil.getInteger(doc.get(Field.ENTRY_CLASS_PK)));
			model.setGroupId(GetterUtil.getInteger(doc.get(Field.GROUP_ID)));
			model.setCreateDate(doc.get(Field.CREATE_DATE));
			model.setModifiedDate(doc.get(Field.MODIFIED_DATE));
			model.setReferenceUid(doc.get(DossierTerm.REFERENCE_UID));
			model.setCounter(GetterUtil.getInteger(doc.get(DossierTerm.COUNTER)));
			model.setServiceCode(doc.get(DossierTerm.SERVICE_CODE));
			model.setServiceName(doc.get(DossierTerm.SERVICE_NAME));
			model.setGovAgencyCode(doc.get(DossierTerm.GOV_AGENCY_CODE));
			model.setGovAgencyName(doc.get(DossierTerm.GOV_AGENCY_NAME));
			model.setApplicantName(doc.get(DossierTerm.APPLICANT_NAME));
			model.setApplicantNote(doc.get(DossierTerm.APPLICANT_NOTE));
			model.setApplicantIdType(doc.get(DossierTerm.APPLICANT_ID_TYPE));
			model.setApplicantIdNo(doc.get(DossierTerm.APPLICANT_ID_NO));
			model.setApplicantIdDate(doc.get(DossierTerm.APPLICANT_ID_DATE));
			model.setAddress(doc.get(DossierTerm.ADDRESS));
			model.setCityCode(doc.get(DossierTerm.CITY_CODE));
			model.setCityName(doc.get(DossierTerm.CITY_NAME));
			model.setDistrictCode(doc.get(DossierTerm.DISTRICT_CODE));
			model.setDistrictName(doc.get(DossierTerm.DISTRICT_NAME));
			model.setWardCode(doc.get(DossierTerm.WARD_CODE));
			model.setWardName(doc.get(DossierTerm.WARD_NAME));
			model.setContactName(doc.get(DossierTerm.CONTACT_NAME));
			model.setContactTelNo(doc.get(DossierTerm.CONTACT_TEL_NO));
			model.setContactEmail(doc.get(DossierTerm.CONTACT_EMAIL));
			model.setDossierNote(doc.get(DossierTerm.DOSSIER_NOTE));
			model.setSubmissionNote(doc.get(DossierTerm.SUBMISSION_NOTE));
			model.setBriefNote(doc.get(DossierTerm.BRIEF_NOTE));
			model.setDossierNo(doc.get(DossierTerm.DOSSIER_NO));
			model.setBriefNote(doc.get(DossierTerm.BRIEF_NOTE));
			model.setSubmitDate(doc.get(DossierTerm.SUBMIT_DATE));
			model.setReceiveDate(doc.get(DossierTerm.RECEIVE_DATE));
			model.setDueDate(doc.get(DossierTerm.DUE_DATE));
			model.setFinishDate(doc.get(DossierTerm.FINISH_DATE));
			model.setCancellingDate(doc.get(DossierTerm.CANCELLING_DATE));
			model.setCorrectingDate(doc.get(DossierTerm.CORRECTING_DATE));
			model.setDossierStatus(doc.get(DossierTerm.DOSSIER_STATUS));
			model.setDossierStatusText(doc.get(DossierTerm.DOSSIER_STATUS_TEXT));
			model.setDossierSubStatus(doc.get(DossierTerm.DOSSIER_SUB_STATUS));
			model.setDossierSubStatusText(doc.get(DossierTerm.DOSSIER_SUB_STATUS_TEXT));
			model.setDossierOverdue(doc.get(DossierTerm.DOSSIER_OVER_DUE));
			model.setSubmitting(doc.get(DossierTerm.SUBMITTING));
			model.setPermission(getPermission(GetterUtil.getLong(doc.get(Field.ENTRY_CLASS_PK))));
			model.setLastActionDate(doc.get(DossierTerm.LAST_ACTION_DATE));
			model.setLastActionCode(doc.get(DossierTerm.LAST_ACTION_CODE));
			model.setLastActionName(doc.get(DossierTerm.LAST_ACTION_NAME));
			model.setLastActionUser(doc.get(DossierTerm.LAST_ACTION_USER));
			model.setLastActionNote(doc.get(DossierTerm.LAST_ACTION_NOTE));
			model.setStepCode(doc.get(DossierTerm.STEP_CODE));
			model.setStepName(doc.get(DossierTerm.STEP_NAME));
			model.setStepDuedate(doc.get(DossierTerm.STEP_DUE_DATE));
			model.setStepOverdue(doc.get(DossierTerm.STEP_OVER_DUE));
			model.setVisited(getVisisted(GetterUtil.getLong(doc.get(Field.ENTRY_CLASS_PK))));
			model.setPending(getPendding(GetterUtil.getLong(doc.get(Field.ENTRY_CLASS_PK))));
			model.setOnline(doc.get(DossierTerm.ONLINE));
			model.setHasPassword(doc.get(DossierTerm.PASSWORD));
			model.setDossierTemplateNo(doc.get(DossierTerm.DOSSIER_TEMPLATE_NO));
			model.setServerNo(doc.get(DossierTerm.SERVER_NO));
			
			model.setViaPostal(doc.get(DossierTerm.VIA_POSTAL));
			model.setPostalAddress(doc.get(DossierTerm.POSTAL_ADDRESS));
			model.setPostalCityCode(doc.get(DossierTerm.POSTAL_CITY_CODE));
			model.setPostalCityName(doc.get(DossierTerm.POSTAL_CITY_NAME));
			model.setPostalTelNo(doc.get(DossierTerm.POSTAL_TEL_NO));

			//TODO: Get info cert Number
			List<DossierFile> dossierFileList = DossierFileLocalServiceUtil
					.getDossierFilesByDossierId(GetterUtil.getInteger(doc.get(Field.ENTRY_CLASS_PK)));
			
			StringBuilder sb = new StringBuilder();
			String deliverableCode = StringPool.BLANK;
			if (dossierFileList != null && dossierFileList.size() > 0) {
				int length = dossierFileList.size();
//				_log.info("Size dossier File: "+ length);
				int ii = 0;
				for (int i = 0; i < length; i++) {
					DossierFile dossierFile = dossierFileList.get(i);
					deliverableCode = dossierFile.getDeliverableCode();
//					_log.info("deliverableCode: "+ deliverableCode);
					if (Validator.isNotNull(deliverableCode)) {
//						_log.info("deliverableCode Check: "+ deliverableCode);
						ii += 1;
						if (ii == 1) {
							sb.append(StringPool.APOSTROPHE);
							sb.append(deliverableCode);
							sb.append(StringPool.APOSTROPHE);
						} else {
							sb.append(StringPool.COMMA);
							sb.append(StringPool.APOSTROPHE);
							sb.append(deliverableCode);
							sb.append(StringPool.APOSTROPHE);
						}
					}
				}
//				_log.info("Str Dossier Id: "+ sb.toString());
			}

			DeliverableActions action = new DeliverableActionsImpl();
			if (Validator.isNotNull(sb.toString())) {
				List<Deliverable> deliverableList = action.getDeliverableByState(sb.toString(), "2");
				
				if (deliverableList != null && deliverableList.size() > 0) {
	//				int lengthDeliver = deliverableList.size();
	//				_log.info("Size list deliverable: "+ lengthDeliver);
					String formData = StringPool.BLANK;
					List<CertNumberModel> certNumberList = new ArrayList<CertNumberModel>();
					for (Deliverable deliverable : deliverableList) {
						CertNumberModel certNumberDetail = new CertNumberModel();
						formData = deliverable.getFormData();
	//					_log.info("formData: "+ formData);
						try {
							JSONObject jsonData = JSONFactoryUtil.createJSONObject(formData);
							String certNo = String.valueOf(jsonData.get("so_chung_chi"));
							String certDate = String.valueOf(jsonData.get("ngay_ky_cc"));
							certNumberDetail.setCertNo(certNo);
							certNumberDetail.setCertDate(certDate);
							certNumberList.add(certNumberDetail);
						} catch (Exception e) {
							// TODO:
						}
					}
					model.getCertNumber().addAll(certNumberList);
				}
			}

			ouputs.add(model);
		}

		return ouputs;
	}

	//TODO: Process get list Paging
	public static List<DossierDataModel> mappingForGetListPaging(List<Document> docs, int start, int end) {
		List<DossierDataModel> ouputs = new ArrayList<DossierDataModel>();
		int lengthDossier = docs.size();
		int endPage = 0;
		if (lengthDossier < end) {
			endPage = lengthDossier;
		} else {
			endPage = end;
		}
		for (int i = start; i < endPage; i++) {
			Document doc = docs.get(i);
			_log.info("i: "+i);
			DossierDataModel model = new DossierDataModel();
			
			model.setDossierIdCTN(doc.get(DossierTerm.DOSSIER_ID+"CTN"));
			model.setDossierId(GetterUtil.getInteger(doc.get(Field.ENTRY_CLASS_PK)));
			model.setGroupId(GetterUtil.getInteger(doc.get(Field.GROUP_ID)));
			model.setCreateDate(doc.get(Field.CREATE_DATE));
			model.setModifiedDate(doc.get(Field.MODIFIED_DATE));
			model.setReferenceUid(doc.get(DossierTerm.REFERENCE_UID));
			model.setCounter(GetterUtil.getInteger(doc.get(DossierTerm.COUNTER)));
			model.setServiceCode(doc.get(DossierTerm.SERVICE_CODE));
			model.setServiceName(doc.get(DossierTerm.SERVICE_NAME));
			model.setGovAgencyCode(doc.get(DossierTerm.GOV_AGENCY_CODE));
			model.setGovAgencyName(doc.get(DossierTerm.GOV_AGENCY_NAME));
			model.setApplicantName(doc.get(DossierTerm.APPLICANT_NAME));
			model.setApplicantNote(doc.get(DossierTerm.APPLICANT_NOTE));
			model.setApplicantIdType(doc.get(DossierTerm.APPLICANT_ID_TYPE));
			model.setApplicantIdNo(doc.get(DossierTerm.APPLICANT_ID_NO));
			model.setApplicantIdDate(doc.get(DossierTerm.APPLICANT_ID_DATE));
			model.setAddress(doc.get(DossierTerm.ADDRESS));
			model.setCityCode(doc.get(DossierTerm.CITY_CODE));
			model.setCityName(doc.get(DossierTerm.CITY_NAME));
			model.setDistrictCode(doc.get(DossierTerm.DISTRICT_CODE));
			model.setDistrictName(doc.get(DossierTerm.DISTRICT_NAME));
			model.setWardCode(doc.get(DossierTerm.WARD_CODE));
			model.setWardName(doc.get(DossierTerm.WARD_NAME));
			model.setContactName(doc.get(DossierTerm.CONTACT_NAME));
			model.setContactTelNo(doc.get(DossierTerm.CONTACT_TEL_NO));
			model.setContactEmail(doc.get(DossierTerm.CONTACT_EMAIL));
			model.setDossierNote(doc.get(DossierTerm.DOSSIER_NOTE));
			model.setSubmissionNote(doc.get(DossierTerm.SUBMISSION_NOTE));
			model.setBriefNote(doc.get(DossierTerm.BRIEF_NOTE));
			model.setDossierNo(doc.get(DossierTerm.DOSSIER_NO));
			model.setBriefNote(doc.get(DossierTerm.BRIEF_NOTE));
			model.setSubmitDate(doc.get(DossierTerm.SUBMIT_DATE));
			model.setReceiveDate(doc.get(DossierTerm.RECEIVE_DATE));
			model.setDueDate(doc.get(DossierTerm.DUE_DATE));
			model.setFinishDate(doc.get(DossierTerm.FINISH_DATE));
			model.setCancellingDate(doc.get(DossierTerm.CANCELLING_DATE));
			model.setCorrectingDate(doc.get(DossierTerm.CORRECTING_DATE));
			model.setDossierStatus(doc.get(DossierTerm.DOSSIER_STATUS));
			model.setDossierStatusText(doc.get(DossierTerm.DOSSIER_STATUS_TEXT));
			model.setDossierSubStatus(doc.get(DossierTerm.DOSSIER_SUB_STATUS));
			model.setDossierSubStatusText(doc.get(DossierTerm.DOSSIER_SUB_STATUS_TEXT));
			model.setDossierOverdue(doc.get(DossierTerm.DOSSIER_OVER_DUE));
			model.setSubmitting(doc.get(DossierTerm.SUBMITTING));
			model.setPermission(getPermission(GetterUtil.getLong(doc.get(Field.ENTRY_CLASS_PK))));
			model.setLastActionDate(doc.get(DossierTerm.LAST_ACTION_DATE));
			model.setLastActionCode(doc.get(DossierTerm.LAST_ACTION_CODE));
			model.setLastActionName(doc.get(DossierTerm.LAST_ACTION_NAME));
			model.setLastActionUser(doc.get(DossierTerm.LAST_ACTION_USER));
			model.setLastActionNote(doc.get(DossierTerm.LAST_ACTION_NOTE));
			model.setStepCode(doc.get(DossierTerm.STEP_CODE));
			model.setStepName(doc.get(DossierTerm.STEP_NAME));
			model.setStepDuedate(doc.get(DossierTerm.STEP_DUE_DATE));
			model.setStepOverdue(doc.get(DossierTerm.STEP_OVER_DUE));
			model.setVisited(getVisisted(GetterUtil.getLong(doc.get(Field.ENTRY_CLASS_PK))));
			model.setPending(getPendding(GetterUtil.getLong(doc.get(Field.ENTRY_CLASS_PK))));
			model.setOnline(doc.get(DossierTerm.ONLINE));
			model.setHasPassword(doc.get(DossierTerm.PASSWORD));
			model.setDossierTemplateNo(doc.get(DossierTerm.DOSSIER_TEMPLATE_NO));
			model.setServerNo(doc.get(DossierTerm.SERVER_NO));
			
			model.setViaPostal(doc.get(DossierTerm.VIA_POSTAL));
			model.setPostalAddress(doc.get(DossierTerm.POSTAL_ADDRESS));
			model.setPostalCityCode(doc.get(DossierTerm.POSTAL_CITY_CODE));
			model.setPostalCityName(doc.get(DossierTerm.POSTAL_CITY_NAME));
			model.setPostalTelNo(doc.get(DossierTerm.POSTAL_TEL_NO));

			//TODO: Get info cert Number
			List<DossierFile> dossierFileList = DossierFileLocalServiceUtil
					.getDossierFilesByDossierId(GetterUtil.getInteger(doc.get(Field.ENTRY_CLASS_PK)));
			
			StringBuilder sb = new StringBuilder();
			String deliverableCode = StringPool.BLANK;
			if (dossierFileList != null && dossierFileList.size() > 0) {
				int length = dossierFileList.size();
//				_log.info("Size dossier File: "+ length);
				int jj = 0;
				for (int j = 0; j < length; j++) {
					DossierFile dossierFile = dossierFileList.get(j);
					deliverableCode = dossierFile.getDeliverableCode();
//					_log.info("deliverableCode: "+ deliverableCode);
					if (Validator.isNotNull(deliverableCode)) {
//						_log.info("deliverableCode Check: "+ deliverableCode);
						jj += 1;
						if (jj == 1) {
							sb.append(StringPool.APOSTROPHE);
							sb.append(deliverableCode);
							sb.append(StringPool.APOSTROPHE);
						} else {
							sb.append(StringPool.COMMA);
							sb.append(StringPool.APOSTROPHE);
							sb.append(deliverableCode);
							sb.append(StringPool.APOSTROPHE);
						}
					}
				}
//				_log.info("Str Dossier Id: "+ sb.toString());
			}

			DeliverableActions action = new DeliverableActionsImpl();
			if (Validator.isNotNull(sb.toString())) {
				List<Deliverable> deliverableList = action.getDeliverableByState(sb.toString(), "2");
				
				if (deliverableList != null && deliverableList.size() > 0) {
	//				int lengthDeliver = deliverableList.size();
	//				_log.info("Size list deliverable: "+ lengthDeliver);
					String formData = StringPool.BLANK;
					List<CertNumberModel> certNumberList = new ArrayList<CertNumberModel>();
					for (Deliverable deliverable : deliverableList) {
						CertNumberModel certNumberDetail = new CertNumberModel();
						formData = deliverable.getFormData();
	//					_log.info("formData: "+ formData);
						try {
							JSONObject jsonData = JSONFactoryUtil.createJSONObject(formData);
							String certNo = String.valueOf(jsonData.get("so_chung_chi"));
							String certDate = String.valueOf(jsonData.get("ngay_ky_cc"));
							certNumberDetail.setCertNo(certNo);
							certNumberDetail.setCertDate(certDate);
							certNumberList.add(certNumberDetail);
						} catch (Exception e) {
							// TODO:
						}
					}
					model.getCertNumber().addAll(certNumberList);
				}
			}
			ouputs.add(model);
		}
		_log.info("ouputs: "+ouputs.size());
		return ouputs;
	}

	//TODO:
	public static JSONArray mappingForGetJSON(List<Document> docs) {
		JSONArray ouputs = JSONFactoryUtil.createJSONArray();

		for (Document doc : docs) {
			JSONObject models = JSONFactoryUtil.createJSONObject();
			models.put(DossierTerm.DOSSIER_ID+"CTN", doc.get(DossierTerm.DOSSIER_ID+"CTN"));
			models.put(DossierTerm.DOSSIER_ID, GetterUtil.getInteger(doc.get(Field.ENTRY_CLASS_PK)));
			models.put(Field.GROUP_ID, GetterUtil.getInteger(doc.get(Field.GROUP_ID)));
			models.put(Field.CREATE_DATE, doc.get(Field.CREATE_DATE));
			models.put(Field.MODIFIED_DATE, doc.get(Field.MODIFIED_DATE));
			models.put(DossierTerm.REFERENCE_UID, doc.get(DossierTerm.REFERENCE_UID));
			models.put(DossierTerm.COUNTER, doc.get(DossierTerm.COUNTER));
			models.put(DossierTerm.SERVICE_CODE, doc.get(DossierTerm.SERVICE_CODE));
			models.put(DossierTerm.SERVICE_NAME, doc.get(DossierTerm.SERVICE_NAME));
			models.put(DossierTerm.GOV_AGENCY_CODE, doc.get(DossierTerm.GOV_AGENCY_CODE));
			models.put(DossierTerm.GOV_AGENCY_NAME, doc.get(DossierTerm.GOV_AGENCY_NAME));
			models.put(DossierTerm.APPLICANT_NAME, doc.get(DossierTerm.APPLICANT_NAME));
			models.put(DossierTerm.APPLICANT_NOTE, doc.get(DossierTerm.APPLICANT_NOTE));
			models.put(DossierTerm.APPLICANT_ID_TYPE, doc.get(DossierTerm.APPLICANT_ID_TYPE));
			models.put(DossierTerm.APPLICANT_ID_NO, doc.get(DossierTerm.APPLICANT_ID_NO));
			models.put(DossierTerm.APPLICANT_ID_DATE, doc.get(DossierTerm.APPLICANT_ID_DATE));
			models.put(DossierTerm.ADDRESS, doc.get(DossierTerm.ADDRESS));
			models.put(DossierTerm.CITY_CODE, doc.get(DossierTerm.CITY_CODE));
			models.put(DossierTerm.CITY_NAME, doc.get(DossierTerm.CITY_NAME));
			models.put(DossierTerm.DISTRICT_CODE, doc.get(DossierTerm.DISTRICT_CODE));
			models.put(DossierTerm.DISTRICT_NAME, doc.get(DossierTerm.DISTRICT_NAME));
			models.put(DossierTerm.WARD_CODE, doc.get(DossierTerm.WARD_CODE));
			models.put(DossierTerm.WARD_NAME, doc.get(DossierTerm.WARD_NAME));
			models.put(DossierTerm.CONTACT_NAME, doc.get(DossierTerm.CONTACT_NAME));
			models.put(DossierTerm.CONTACT_TEL_NO, doc.get(DossierTerm.CONTACT_TEL_NO));
			models.put(DossierTerm.CONTACT_EMAIL, doc.get(DossierTerm.CONTACT_EMAIL));
			models.put(DossierTerm.DOSSIER_NOTE, doc.get(DossierTerm.DOSSIER_NOTE));
			models.put(DossierTerm.SUBMISSION_NOTE, doc.get(DossierTerm.SUBMISSION_NOTE));
			models.put(DossierTerm.BRIEF_NOTE, doc.get(DossierTerm.BRIEF_NOTE));
			models.put(DossierTerm.DOSSIER_NO, doc.get(DossierTerm.DOSSIER_NO));
			models.put(DossierTerm.SUBMIT_DATE, doc.get(DossierTerm.SUBMIT_DATE));
			models.put(DossierTerm.RECEIVE_DATE, doc.get(DossierTerm.RECEIVE_DATE));
			models.put(DossierTerm.DUE_DATE, doc.get(DossierTerm.DUE_DATE));
			models.put(DossierTerm.FINISH_DATE, doc.get(DossierTerm.FINISH_DATE));
			models.put(DossierTerm.DOSSIER_STATUS, doc.get(DossierTerm.DOSSIER_STATUS));
			models.put(DossierTerm.DOSSIER_STATUS_TEXT, doc.get(DossierTerm.DOSSIER_STATUS_TEXT));
			models.put(DossierTerm.DOSSIER_SUB_STATUS, doc.get(DossierTerm.DOSSIER_SUB_STATUS));
			models.put(DossierTerm.DOSSIER_SUB_STATUS_TEXT, doc.get(DossierTerm.DOSSIER_SUB_STATUS_TEXT));
			models.put(DossierTerm.DOSSIER_OVER_DUE, doc.get(DossierTerm.DOSSIER_OVER_DUE));
			models.put(DossierTerm.SUBMITTING, doc.get(DossierTerm.SUBMITTING));
			models.put("permission", getPermission(GetterUtil.getLong(doc.get(Field.ENTRY_CLASS_PK))));
			models.put(DossierTerm.LAST_ACTION_DATE, doc.get(DossierTerm.LAST_ACTION_DATE));
			models.put(DossierTerm.LAST_ACTION_CODE, doc.get(DossierTerm.LAST_ACTION_CODE));
			models.put(DossierTerm.LAST_ACTION_NAME, doc.get(DossierTerm.LAST_ACTION_NAME));
			models.put(DossierTerm.LAST_ACTION_USER, doc.get(DossierTerm.LAST_ACTION_USER));
			models.put(DossierTerm.LAST_ACTION_NOTE, doc.get(DossierTerm.LAST_ACTION_NOTE));
			models.put(DossierTerm.STEP_CODE, doc.get(DossierTerm.STEP_CODE));
			models.put(DossierTerm.STEP_NAME, doc.get(DossierTerm.STEP_NAME));
			models.put(DossierTerm.STEP_DUE_DATE, doc.get(DossierTerm.STEP_DUE_DATE));
			models.put(DossierTerm.STEP_OVER_DUE, doc.get(DossierTerm.STEP_OVER_DUE));
			models.put("visited", getVisisted(GetterUtil.getLong(doc.get(Field.ENTRY_CLASS_PK))));
			models.put("pending", getPendding(GetterUtil.getLong(doc.get(Field.ENTRY_CLASS_PK))));
			models.put(DossierTerm.ONLINE, doc.get(DossierTerm.ONLINE));
			models.put(DossierTerm.PASSWORD, doc.get(DossierTerm.PASSWORD));
			models.put(DossierTerm.DOSSIER_TEMPLATE_NO, doc.get(DossierTerm.DOSSIER_TEMPLATE_NO));
			models.put(DossierTerm.SERVER_NO, doc.get(DossierTerm.SERVER_NO));
			models.put(DossierTerm.VIA_POSTAL, doc.get(DossierTerm.VIA_POSTAL));
			models.put(DossierTerm.POSTAL_ADDRESS, doc.get(DossierTerm.POSTAL_ADDRESS));
			models.put(DossierTerm.POSTAL_CITY_CODE, doc.get(DossierTerm.POSTAL_CITY_CODE));
			models.put(DossierTerm.POSTAL_CITY_NAME, doc.get(DossierTerm.POSTAL_CITY_NAME));
			models.put(DossierTerm.POSTAL_TEL_NO, doc.get(DossierTerm.POSTAL_TEL_NO));

			//TODO: Get info cert Number
			List<DossierFile> dossierFileList = DossierFileLocalServiceUtil
					.getDossierFilesByDossierId(GetterUtil.getInteger(doc.get(Field.ENTRY_CLASS_PK)));
			
			StringBuilder sb = new StringBuilder();
			String deliverableCode = StringPool.BLANK;
			if (dossierFileList != null && dossierFileList.size() > 0) {
				int length = dossierFileList.size();
//				_log.info("Size dossier File: "+ length);
				int ii = 0;
				for (int i = 0; i < length; i++) {
					DossierFile dossierFile = dossierFileList.get(i);
					deliverableCode = dossierFile.getDeliverableCode();
//					_log.info("deliverableCode: "+ deliverableCode);
					if (Validator.isNotNull(deliverableCode)) {
//						_log.info("deliverableCode Check: "+ deliverableCode);
						ii += 1;
						if (ii == 1) {
							sb.append(StringPool.APOSTROPHE);
							sb.append(deliverableCode);
							sb.append(StringPool.APOSTROPHE);
						} else {
							sb.append(StringPool.COMMA);
							sb.append(StringPool.APOSTROPHE);
							sb.append(deliverableCode);
							sb.append(StringPool.APOSTROPHE);
						}
					}
				}
//				_log.info("Str Dossier Id: "+ sb.toString());
			}

			DeliverableActions action = new DeliverableActionsImpl();

			List<Deliverable> deliverableList = action.getDeliverableByState(sb.toString(), "2");
			
			if (deliverableList != null && deliverableList.size() > 0) {
//				int lengthDeliver = deliverableList.size();
//				_log.info("Size list deliverable: "+ lengthDeliver);
				String formData = StringPool.BLANK;
				JSONArray certNumberArr = JSONFactoryUtil.createJSONArray();
				for (Deliverable deliverable : deliverableList) {
					JSONObject certNumberDetail = JSONFactoryUtil.createJSONObject();
					formData = deliverable.getFormData();
//					_log.info("formData: "+ formData);
					try {
						JSONObject jsonData = JSONFactoryUtil.createJSONObject(formData);
						certNumberDetail.put("so_chung_chi", jsonData.get("so_chung_chi"));
						certNumberDetail.put("ngay_ky_cc", jsonData.get("ngay_ky_cc"));
						certNumberArr.put(certNumberDetail);
					} catch (Exception e) {
						//-log.error(e);
						// TODO:
					}
//					
				}
				models.put("certNumber", certNumberArr);
			}
			ouputs.put(models);
		}

		return ouputs;
	}
	
	static Log _log = LogFactoryUtil.getLog(DossierUtils.class);

	public static DossierDetailModel mappingForGetDetail(Dossier input, long userId) {

		DossierDetailModel model = new DossierDetailModel();
		
		try {
			Document dossierDoc = DossierLocalServiceUtil.getDossierById(input.getDossierId(), input.getCompanyId());
			model.setDossierIdCTN(dossierDoc.get(DossierTerm.DOSSIER_ID+"CTN"));
		} catch (Exception e) {
			model.setDossierIdCTN("");
		}
		

		model.setDossierId(GetterUtil.getInteger(input.getDossierId()));
		model.setUserId(GetterUtil.getInteger(input.getUserId()));
		model.setCreateDate(
				APIDateTimeUtils.convertDateToString(input.getCreateDate(), APIDateTimeUtils._NORMAL_PARTTERN));
		model.setModifiedDate(
				APIDateTimeUtils.convertDateToString(input.getModifiedDate(), APIDateTimeUtils._NORMAL_PARTTERN));
		model.setReferenceUid(input.getReferenceUid());
		model.setCounter(input.getCounter());
		model.setServiceCode(input.getServiceCode());
		model.setServiceName(input.getServiceName());
		model.setGovAgencyCode(input.getGovAgencyCode());
		model.setGovAgencyName(input.getGovAgencyName());
		model.setDossierTemplateNo(input.getDossierTemplateNo());
		model.setApplicantName(input.getApplicantName());
		model.setApplicantIdType(input.getApplicantIdType());
		model.setApplicantIdNo(input.getApplicantIdNo());
		model.setApplicantIdDate(
				APIDateTimeUtils.convertDateToString(input.getApplicantIdDate(), APIDateTimeUtils._NORMAL_PARTTERN));
		model.setAddress(input.getAddress());
		model.setCityCode(input.getCityCode());
		model.setCityName(input.getCityName());
		model.setDistrictCode(input.getDistrictCode());
		model.setDistrictName(input.getDistrictName());
		model.setWardCode(input.getWardCode());
		model.setWardName(input.getWardName());
		model.setContactName(input.getContactName());
		model.setContactTelNo(input.getContactTelNo());
		model.setContactEmail(input.getContactEmail());
		model.setDossierNote(input.getDossierNote());
		model.setSubmissionNote(input.getSubmissionNote());
		model.setBriefNote(input.getBriefNote());
		model.setDossierNo(input.getDossierNo());
		model.setSubmitting(Boolean.toString(input.getSubmitting()));
		model.setSubmitDate(
				APIDateTimeUtils.convertDateToString(input.getSubmitDate(), APIDateTimeUtils._NORMAL_PARTTERN));
		model.setReceiveDate(
				APIDateTimeUtils.convertDateToString(input.getReceiveDate(), APIDateTimeUtils._NORMAL_PARTTERN));
		model.setDueDate(APIDateTimeUtils.convertDateToString(input.getDueDate(), APIDateTimeUtils._NORMAL_PARTTERN));
		model.setReleaseDate(
				APIDateTimeUtils.convertDateToString(input.getReleaseDate(), APIDateTimeUtils._NORMAL_PARTTERN));
		model.setFinishDate(
				APIDateTimeUtils.convertDateToString(input.getFinishDate(), APIDateTimeUtils._NORMAL_PARTTERN));
		model.setCancellingDate(
				APIDateTimeUtils.convertDateToString(input.getCancellingDate(), APIDateTimeUtils._NORMAL_PARTTERN));
		model.setCorrectingDate(
				APIDateTimeUtils.convertDateToString(input.getCorrecttingDate(), APIDateTimeUtils._NORMAL_PARTTERN));
		model.setDossierStatus(input.getDossierStatus());
		model.setDossierStatusText(input.getDossierStatusText());
		model.setDossierSubStatus(input.getDossierSubStatus());
		model.setDossierSubStatusText(input.getDossierSubStatusText());
		model.setViaPostal(Integer.toString(input.getViaPostal()));
		model.setPostalAddress(input.getPostalAddress());
		model.setPostalCityCode(input.getPostalCityCode());
		model.setPostalCityName(input.getPostalCityName());
		model.setPostalTelNo(input.getPostalTelNo());
		model.setPermission(getPermission(input.getPrimaryKey()));

		if (input.getDossierActionId() != 0) {
			DossierAction dossierAction = DossierActionLocalServiceUtil.fetchDossierAction(input.getDossierActionId());

			model.setLastActionDate(APIDateTimeUtils.convertDateToString(dossierAction.getCreateDate(),
					APIDateTimeUtils._NORMAL_PARTTERN));
			model.setLastActionName(dossierAction.getActionName());
			model.setLastActionUser(dossierAction.getActionUser());
			model.setLastActionNote(dossierAction.getActionNote());
			model.setLastActionCode(dossierAction.getActionCode());

			model.setStepCode(dossierAction.getStepCode());
			model.setStepName(dossierAction.getStepName());

			Date stepDuedate = DossierOverDueUtils.getStepOverDue(dossierAction.getActionOverdue(), new Date());

			if (dossierAction.getActionOverdue() != 0) {
				model.setStepOverdue(StringPool.TRUE);
			} else {
				model.setStepOverdue(StringPool.TRUE);
			}

			model.setStepDuedate(APIDateTimeUtils.convertDateToString(stepDuedate, APIDateTimeUtils._NORMAL_PARTTERN));

			ProcessStep step = ProcessStepLocalServiceUtil.fetchBySC_GID(dossierAction.getStepCode(),
					dossierAction.getGroupId(), dossierAction.getServiceProcessId());

			model.setStepInstruction(step.getStepInstruction());

			// Check permission process dossier
			DossierActionUser dau = DossierActionUserLocalServiceUtil.getByDossierAndUser(input.getDossierActionId(), userId);
			if (dau != null) {
				model.setSpecialNo(dau.getModerator());
			} else {
				model.setSpecialNo(0);
			}
		}

		model.setVisited(getVisisted(input.getPrimaryKey()));
		model.setPending(getPendding(input.getPrimaryKey()));
		model.setApplicantNote(input.getApplicantNote());
		model.setNotification(Boolean.toString(input.getNotification()));
		model.setOnline(Boolean.toString(input.getOnline()));

		return model;
	}

	private static String getPermission(long dossierId) {
		// TODO add logic here
		// return list of permission, separate by the comma
		return StringPool.BLANK;
	}

	private static String getVisisted(long dossierId) {
		// TODO add logic here
		// return true or false in String type
		return StringPool.FALSE;
	}

	private static String getPendding(long dossierId) {
		// TODO add logic here
		// return true or false in String type
		return StringPool.FALSE;
	}

	private static String getApplicationNote(long dossierId) {
		// TODO add logic here
		// return true or false in String type
		return StringPool.BLANK;
	}
}
