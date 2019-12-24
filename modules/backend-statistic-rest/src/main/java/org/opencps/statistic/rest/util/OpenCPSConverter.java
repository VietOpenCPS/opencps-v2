package org.opencps.statistic.rest.util;

import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.lgsp.model.Mtoken;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.PaymentFile;
import org.opencps.dossiermgt.rest.model.DossierPublishModel;
import org.opencps.dossiermgt.service.DossierActionLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.PaymentFileLocalServiceUtil;
import org.opencps.statistic.model.OpencpsDossierStatistic;
import org.opencps.statistic.model.OpencpsVotingStatistic;
import org.opencps.statistic.service.OpencpsVotingStatisticLocalServiceUtil;

public class OpenCPSConverter {
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

	public static String convertToUTCDate(Date d) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		String s = df.format(d);
		return s;
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
	
	private static Log _log = LogFactoryUtil.getLog(OpenCPSConverter.class);
}
