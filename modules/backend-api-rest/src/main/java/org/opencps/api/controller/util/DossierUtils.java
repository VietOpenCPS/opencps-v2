package org.opencps.api.controller.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.opencps.api.dossier.model.DossierDataModel;
import org.opencps.api.dossier.model.DossierDetailModel;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.dossiermgt.action.util.DossierOverDueUtils;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.ProcessStep;
import org.opencps.dossiermgt.service.DossierActionLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessStepLocalServiceUtil;

import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;

public class DossierUtils {

	public static List<DossierDataModel> mappingForGetList(List<Document> docs) {
		List<DossierDataModel> ouputs = new ArrayList<>();

		for (Document doc : docs) {
			DossierDataModel model = new DossierDataModel();

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

			ouputs.add(model);
		}

		return ouputs;
	}

	public static DossierDetailModel mappingForGetDetail(Dossier input) {

		DossierDetailModel model = new DossierDetailModel();

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
