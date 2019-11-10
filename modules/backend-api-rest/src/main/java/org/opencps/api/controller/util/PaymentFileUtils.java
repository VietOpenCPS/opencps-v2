package org.opencps.api.controller.util;

import com.liferay.document.library.kernel.model.DLFileVersion;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileVersionLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.opencps.api.paymentfile.model.PaymentFileInputModel;
import org.opencps.api.paymentfile.model.PaymentFileModel;
import org.opencps.api.paymentfile.model.PaymentFileSearchTemplateModel;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.dossiermgt.action.util.ConstantUtils;
import org.opencps.dossiermgt.action.util.ReadFilePropertiesUtils;
import org.opencps.dossiermgt.constants.PaymentFileTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.PaymentFile;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;

public class PaymentFileUtils {

	/**
	 * Mapping documents with Object PaymentFileModel
	 * 
	 * @param documents
	 * @return List<PaymentFileModel>
	 */
	public static List<PaymentFileModel> mappingToPaymentFileModel(List<Document> documents) throws ParseException {

		if (documents == null) {
			return null;
		}

		List<PaymentFileModel> results = new ArrayList<PaymentFileModel>();

		for (Document doc : documents) {
			PaymentFileModel model = new PaymentFileModel();

			String strCreateDate = doc.get(Field.CREATE_DATE);

			Date createDate = null;

			if (Validator.isNotNull(strCreateDate)) {
				createDate = APIDateTimeUtils.convertStringToDate(strCreateDate, APIDateTimeUtils._LUCENE_PATTERN);
			}

			model.setCreateDate(createDate != null
					? APIDateTimeUtils.convertDateToString(createDate, APIDateTimeUtils._TIMESTAMP) : strCreateDate);

			String strModifiedDate = doc.get(Field.MODIFIED_DATE);

			Date modifiedDate = null;

			if (Validator.isNotNull(strModifiedDate)) {
				modifiedDate = APIDateTimeUtils.convertStringToDate(strModifiedDate, APIDateTimeUtils._LUCENE_PATTERN);
			}

			model.setModifiedDate(modifiedDate != null
					? APIDateTimeUtils.convertDateToString(modifiedDate, APIDateTimeUtils._TIMESTAMP)
					: strModifiedDate);
			model.setReferenceUid(doc.get(PaymentFileTerm.REFERENCE_UID));
			model.setApplicantName(doc.get(PaymentFileTerm.APPLICANT_NAME));
			model.setApplicantIdNo(doc.get(PaymentFileTerm.APPLICANT_ID_NO));
			model.setPaymentFee(doc.get(PaymentFileTerm.PAYMENT_FEE));
			model.setPaymentAmount(GetterUtil.getLong(doc.get(PaymentFileTerm.PAYMENT_AMOUNT)));
			model.setPaymentNote(doc.get(PaymentFileTerm.PAYMENT_NOTE));
			model.setBankInfo(doc.get(PaymentFileTerm.BANK_INFO));
			model.setEpaymentProfile(doc.get(PaymentFileTerm.EPAYMENT_PROFILE));
			model.setPaymentStatus(Integer.parseInt(doc.get(PaymentFileTerm.PAYMENT_STATUS)));
			model.setPaymentMethod(doc.get(PaymentFileTerm.PAYMENT_METHOD));
			model.setConfirmPayload(doc.get(PaymentFileTerm.CONFIRM_PAYLOAD));
			DLFileVersion dlFilePayLoad = getFileInfo(
					GetterUtil.getLong(doc.get(PaymentFileTerm.CONFIRM_FILE_ENTRY_ID)));
			if (dlFilePayLoad != null) {
				model.setConfirmFileType(dlFilePayLoad.getExtension());
				model.setConfirmFileSize(dlFilePayLoad.getSize());
			} else {
				model.setConfirmFileType(StringPool.BLANK);
				model.setConfirmFileSize(0L);
			}
			model.setConfirmNote(doc.get(PaymentFileTerm.CONFIRM_NOTE));
			model.setAccountUserName(doc.get(PaymentFileTerm.ACCOUNT_USER_NAME));
			model.setGovAgencyTaxNo(doc.get(PaymentFileTerm.GOV_AGENCY_TAX_NO));
			model.setInvoiceTemplateNo(doc.get(PaymentFileTerm.INVOICE_TEMPLATE_NO));
			model.setInvoiceIssueNo(doc.get(PaymentFileTerm.INVOICE_ISSUE_NO));
			model.setInvoiceNo(doc.get(PaymentFileTerm.INVOICE_NO));
			
			results.add(model);
		}

		return results;
	}

	/**
	 * Mapping Object PaymentFile with Object PaymentFileInputModel
	 * 
	 * @param paymentFile
	 * @return PaymentFileInputModel
	 */
	public static PaymentFileInputModel mappingToPaymentFileInputModel(PaymentFile paymentFile) {

		if (paymentFile == null) {
			return null;
		}

		PaymentFileInputModel model = new PaymentFileInputModel();

		model.setReferenceUid(paymentFile.getReferenceUid());

		try {
			DossierLocalServiceUtil.getDossier(paymentFile.getDossierId());
		} catch (Exception e) {
			_log.debug(e);
		}
		model.setPaymentFee(paymentFile.getPaymentFee());
		model.setPaymentAmount(paymentFile.getPaymentAmount());
		model.setPaymentNote(paymentFile.getPaymentNote());
		model.setEpaymentProfile(paymentFile.getEpaymentProfile());

		return model;
	}

	/**
	 * Mapping Object PaymentFile with Object PaymentFileModel
	 * 
	 * @param paymentFile
	 * @return PaymentFileModel
	 */
	public static PaymentFileModel mappingToPaymentFileModel(PaymentFile paymentFile) {

		if (paymentFile == null) {
			return null;
		}

		PaymentFileModel model = new PaymentFileModel();

		model.setCreateDate(APIDateTimeUtils.convertDateToString(paymentFile.getCreateDate()));
		model.setModifiedDate(APIDateTimeUtils.convertDateToString(paymentFile.getModifiedDate()));
		model.setReferenceUid(paymentFile.getReferenceUid());
		Dossier dossier = null;
		try {
			dossier = DossierLocalServiceUtil.getDossier(paymentFile.getDossierId());

		} catch (Exception e) {
			_log.debug(e);
		}

		model.setApplicantName((dossier != null && Validator.isNotNull(dossier.getApplicantName()))
				? dossier.getApplicantName() : StringPool.BLANK);
		model.setApplicantIdNo((dossier != null && Validator.isNotNull(dossier.getApplicantIdNo()))
				? dossier.getApplicantIdNo() : StringPool.BLANK);

		model.setPaymentFee(paymentFile.getPaymentFee());
		model.setAdvanceAmount(paymentFile.getAdvanceAmount());
		model.setFeeAmount(paymentFile.getFeeAmount());
		model.setServiceAmount(paymentFile.getServiceAmount());
		model.setShipAmount(paymentFile.getShipAmount());
		model.setPaymentAmount(paymentFile.getPaymentAmount());
		model.setPaymentNote(paymentFile.getPaymentNote());
		model.setBankInfo(paymentFile.getBankInfo());
		model.setEpaymentProfile(paymentFile.getEpaymentProfile());
		model.setPaymentStatus(paymentFile.getPaymentStatus());
		model.setPaymentMethod(paymentFile.getPaymentMethod());
		model.setConfirmDatetime(APIDateTimeUtils.convertDateToString(paymentFile.getConfirmDatetime()));
		model.setConfirmPayload(paymentFile.getConfirmPayload());

		DLFileVersion dlFilePayLoad = getFileInfo(GetterUtil.getLong(paymentFile.getConfirmFileEntryId()));

		if (dlFilePayLoad != null) {
			model.setConfirmFileType(dlFilePayLoad.getExtension());
			model.setConfirmFileSize(dlFilePayLoad.getSize());
		} else {
			model.setConfirmFileType(StringPool.BLANK);
			model.setConfirmFileSize(0L);
		}
		model.setConfirmNote(paymentFile.getConfirmNote());
		model.setApproveDatetime(APIDateTimeUtils.convertDateToString(paymentFile.getApproveDatetime()));
		model.setAccountUserName(paymentFile.getAccountUserName());
		model.setGovAgencyTaxNo(paymentFile.getGovAgencyTaxNo());
		model.setInvoiceTemplateNo(paymentFile.getInvoiceTemplateNo());
		model.setInvoiceIssueNo(paymentFile.getInvoiceIssueNo());
		model.setInvoiceNo(paymentFile.getInvoiceNo());
		model.setConfirmPayload(paymentFile.getConfirmPayload());
		model.setEinvoice(paymentFile.getEinvoice());

		return model;
	}

	/**
	 * Mapping document with Object PaymentFileSearchTemplateModel
	 * 
	 * @param documents
	 * @return List<PaymentFileSearchTemplateModel>
	 */
	public static List<PaymentFileSearchTemplateModel> mappingToPaymentFileSearchResultModel(List<Document> documents) {

		if (documents == null) {
			return null;
		}

		List<PaymentFileSearchTemplateModel> results = new ArrayList<PaymentFileSearchTemplateModel>();
		//
		for (Document doc : documents) {
			PaymentFileSearchTemplateModel model = new PaymentFileSearchTemplateModel();

			model.setDossierId(GetterUtil.getLong(doc.get(PaymentFileTerm.DOSSIER_ID)));
			model.setDossierNo(doc.get(PaymentFileTerm.DOSSIER_NO));
			model.setCounter(GetterUtil.getInteger(PaymentFileTerm.COUNTER));
			model.setServiceCode(doc.get(PaymentFileTerm.SERVICE_CODE));
			model.setServiceName(doc.get(PaymentFileTerm.SERVICE_NAME));
			model.setReferenceUid(doc.get(PaymentFileTerm.REFERENCE_UID));

			String strCreateDate = doc.get(Field.CREATE_DATE);

			Date createDate = null;

			if (Validator.isNotNull(strCreateDate)) {
				createDate = APIDateTimeUtils.convertStringToDate(strCreateDate, ReadFilePropertiesUtils.get(ConstantUtils.PATTERN_LUCENE));
			}

			model.setCreateDate(createDate != null
					? APIDateTimeUtils.convertDateToString(createDate, APIDateTimeUtils._TIMESTAMP) : strCreateDate);

			String strModifiedDate = doc.get(Field.MODIFIED_DATE);

			Date modifiedDate = null;

			if (Validator.isNotNull(strModifiedDate)) {
				modifiedDate = APIDateTimeUtils.convertStringToDate(strModifiedDate, ReadFilePropertiesUtils.get(ConstantUtils.PATTERN_LUCENE));
			}

			model.setModifiedDate(modifiedDate != null
					? APIDateTimeUtils.convertDateToString(modifiedDate, APIDateTimeUtils._TIMESTAMP)
					: strModifiedDate);

			model.setGovAgencyCode(doc.get(PaymentFileTerm.GOV_AGENCY_CODE));
			model.setGovAgencyName(doc.get(PaymentFileTerm.GOV_AGENCY_NAME));
			model.setApplicantName(doc.get(PaymentFileTerm.APPLICANT_NAME));
			model.setApplicantIdNo(doc.get(PaymentFileTerm.APPLICANT_ID_NO));
			model.setIsNew(GetterUtil.getBoolean(doc.get(PaymentFileTerm.IS_NEW)));
			model.setPaymentFee(doc.get(PaymentFileTerm.PAYMENT_FEE));
			model.setPaymentNote(doc.get(PaymentFileTerm.PAYMENT_NOTE));
			model.setBankInfo(doc.get(PaymentFileTerm.BANK_INFO));
			model.setEpaymentProfile(doc.get(PaymentFileTerm.EPAYMENT_PROFILE));
			model.setPaymentStatus(Integer.parseInt(doc.get(PaymentFileTerm.PAYMENT_STATUS)));
			model.setPaymentMethod(doc.get(PaymentFileTerm.PAYMENT_METHOD));

			String strConfirmDatetime = doc.get(PaymentFileTerm.CONFIRM_DATETIME);

			Date confirmDatetime = null;

			if (Validator.isNotNull(strConfirmDatetime)) {
				confirmDatetime = APIDateTimeUtils.convertStringToDate(strConfirmDatetime, ReadFilePropertiesUtils.get(ConstantUtils.PATTERN_LUCENE));
			}

			model.setConfirmDatetime(confirmDatetime != null
					? APIDateTimeUtils.convertDateToString(confirmDatetime, APIDateTimeUtils._TIMESTAMP)
					: strConfirmDatetime);

			model.setConfirmPayload(doc.get(PaymentFileTerm.CONFIRM_PAYLOAD));
			DLFileVersion dlFilePayLoad = getFileInfo(
					GetterUtil.getLong(doc.get(PaymentFileTerm.CONFIRM_FILE_ENTRY_ID)));
			if (dlFilePayLoad != null) {
				model.setConfirmFileType(dlFilePayLoad.getExtension());
				model.setConfirmFileSize(dlFilePayLoad.getSize());
			} else {
				model.setConfirmFileType(StringPool.BLANK);
				model.setConfirmFileSize(0L);
			}
			model.setConfirmNote(doc.get(PaymentFileTerm.CONFIRM_NOTE));

			String strApproveDatetime = doc.get(PaymentFileTerm.CONFIRM_DATETIME);

			Date approveDatetime = null;

			if (Validator.isNotNull(strApproveDatetime)) {
				approveDatetime = APIDateTimeUtils.convertStringToDate(strApproveDatetime, ReadFilePropertiesUtils.get(ConstantUtils.PATTERN_LUCENE));
			}

			model.setApproveDatetime(approveDatetime != null
					? APIDateTimeUtils.convertDateToString(approveDatetime, APIDateTimeUtils._TIMESTAMP)
					: strApproveDatetime);

			model.setAccountUserName(doc.get(PaymentFileTerm.ACCOUNT_USER_NAME));
			model.setGovAgencyTaxNo(doc.get(PaymentFileTerm.GOV_AGENCY_TAX_NO));
			model.setInvoiceTemplateNo(doc.get(PaymentFileTerm.INVOICE_TEMPLATE_NO));
			model.setInvoiceIssueNo(doc.get(PaymentFileTerm.INVOICE_ISSUE_NO));
			model.setInvoiceNo(doc.get(PaymentFileTerm.INVOICE_NO));
			DLFileVersion dlFileInvoice = getFileInfo(
					GetterUtil.getLong(doc.get(PaymentFileTerm.INVOICE_FILE_ENTRY_ID)));
			if (dlFileInvoice != null) {
				model.setInvoiceFileType(dlFileInvoice.getExtension());
				model.setInvoiceFileSize(dlFileInvoice.getSize());
			} else {
				model.setInvoiceFileType(StringPool.BLANK);
				model.setInvoiceFileSize(0L);
			}

			DossierLocalServiceUtil.fetchDossier(GetterUtil.getLong(doc.get(PaymentFileTerm.DOSSIER_ID)));

			results.add(model);

		}

		return results;
	}

	/**
	 * Get info file upload
	 * 
	 * @param fileId
	 * @return DLFileVersion
	 */
	public static DLFileVersion getFileInfo(Long fileId) {
		DLFileVersion dlFileVersion = null;

		if (fileId > 0) {
			try {
				FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileId);
				dlFileVersion = DLFileVersionLocalServiceUtil.getLatestFileVersion(fileEntry.getFileEntryId(), true);
			} catch (Exception e) {
				_log.debug(e);
			}
		}
		return dlFileVersion;
	}

	public static org.opencps.dossiermgt.input.model.PaymentFileInputModel convertFormModelToInputModel(PaymentFileInputModel input) {
		org.opencps.dossiermgt.input.model.PaymentFileInputModel model = new org.opencps.dossiermgt.input.model.PaymentFileInputModel();
		model.setAccountUserName(input.getAccountUserName());
		model.setAdvanceAmount(input.getAdvanceAmount());
		model.setApproveDatetime(input.getApproveDatetime());
		model.setBankInfo(input.getBankInfo());
		model.setConfirmFileEntryId(input.getConfirmFileEntryId());
		model.setConfirmNote(input.getConfirmNote());
		model.setConfirmPayload(input.getConfirmPayload());
		model.setEinvoice(input.getEinvoice());
		model.setEpaymentProfile(input.getEpaymentProfile());
		model.setFeeAmount(input.getFeeAmount());
		model.setGovAgencyTaxNo(input.getGovAgencyTaxNo());
		model.setInvoiceIssueNo(input.getInvoiceIssueNo());
		model.setInvoiceNo(input.getInvoiceNo());;
		model.setInvoicePayload(input.getInvoicePayload());
		model.setInvoiceTemplateNo(input.getInvoiceTemplateNo());
		model.setPaymentAmount(input.getPaymentAmount());
		model.setPaymentFee(input.getPaymentFee());
		model.setPaymentMethod(input.getPaymentMethod());
		model.setPaymentNote(input.getPaymentNote());
		model.setPaymentStatus(input.getPaymentStatus());
		model.setReferenceUid(input.getReferenceUid());
		model.setServiceAmount(input.getServiceAmount());
		model.setShipAmount(input.getShipAmount());
		
		return model;
	}
	private static Log _log = LogFactoryUtil.getLog(PaymentFileUtils.class.getName());

}
