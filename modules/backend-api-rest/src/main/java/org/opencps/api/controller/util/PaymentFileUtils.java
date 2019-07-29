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

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.opencps.api.paymentfile.model.PaymentFileInputModel;
import org.opencps.api.paymentfile.model.PaymentFileModel;
import org.opencps.api.paymentfile.model.PaymentFileSearchTemplateModel;
import org.opencps.auth.utils.APIDateTimeUtils;
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
			// model.setCreateDate(doc.get(PaymentFileTerm.CREATE_DATE));
			// model.setModifiedDate(doc.get(PaymentFileTerm.MODIFIED_DATE));
			model.setReferenceUid(doc.get(PaymentFileTerm.REFERENCE_UID));
//			model.setGovAgencyCode(doc.get(PaymentFileTerm.GOV_AGENCY_CODE));
//			model.setGovAgencyName(doc.get(PaymentFileTerm.GOV_AGENCY_NAME));
			model.setApplicantName(doc.get(PaymentFileTerm.APPLICANT_NAME));
			model.setApplicantIdNo(doc.get(PaymentFileTerm.APPLICANT_ID_NO));
			// model.setIsNew(paymentFile.getIsNew());
			model.setPaymentFee(doc.get(PaymentFileTerm.PAYMENT_FEE));
			model.setPaymentAmount(GetterUtil.getLong(doc.get(PaymentFileTerm.PAYMENT_AMOUNT)));
			model.setPaymentNote(doc.get(PaymentFileTerm.PAYMENT_NOTE));
			model.setBankInfo(doc.get(PaymentFileTerm.BANK_INFO));
			model.setEpaymentProfile(doc.get(PaymentFileTerm.EPAYMENT_PROFILE));
			model.setPaymentStatus(Integer.parseInt(doc.get(PaymentFileTerm.PAYMENT_STATUS)));
			model.setPaymentMethod(doc.get(PaymentFileTerm.PAYMENT_METHOD));
			// model.setConfirmDatetime(APIDateTimeUtils.convertDateToString(doc.getDate(PaymentFileTerm.CONFIRM_DATETIME)));
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
			
//			DLFileVersion dlFileInvoice = getFileInfo(
//					GetterUtil.getLong(doc.get(PaymentFileTerm.INVOICE_FILE_ENTRY_ID)));
//			if (dlFileInvoice != null) {
//				model.setInvoiceFileType(dlFileInvoice.getExtension());
//				model.setInvoiceFileSize(dlFileInvoice.getSize());
//			} else {
//				model.setInvoiceFileType(StringPool.BLANK);
//				model.setInvoiceFileSize(0L);
//			}
//			model.setConfirmFileEntryId(GetterUtil.getLong(doc.get(PaymentFileTerm.CONFIRM_FILE_ENTRY_ID)));
			
//			Dossier dossier = DossierLocalServiceUtil.fetchDossier(GetterUtil.getLong(doc.get(PaymentFileTerm.DOSSIER_ID)));
//			model.setBriefNote(dossier.getBriefNote());
			
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
//		model.setGovAgencyCode(paymentFile.getGovAgencyCode());
//		model.setGovAgencyName(paymentFile.getGovAgencyName());

		try {
			DossierLocalServiceUtil.getDossier(paymentFile.getDossierId());
//			model.setApplicantName(dossier.getApplicantName());
//			model.setApplicantIdNo(dossier.getApplicantIdNo());
		} catch (Exception e) {
			_log.debug(e);
			//_log.error(e);
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
			//_log.error(e);
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

			// model.setCreateDate(APIDateTimeUtils.convertDateToString(doc.getDate(PaymentFileTerm.CREATE_DATE)));
			// model.setModifiedDate(APIDateTimeUtils.convertDateToString(doc.getDate(PaymentFileTerm.MODIFIED_DATE)));
			model.setDossierId(GetterUtil.getLong(doc.get(PaymentFileTerm.DOSSIER_ID)));
			model.setDossierNo(doc.get(PaymentFileTerm.DOSSIER_NO));
			model.setCounter(GetterUtil.getInteger(PaymentFileTerm.COUNTER));
			model.setServiceCode(doc.get(PaymentFileTerm.SERVICE_CODE));
			model.setServiceName(doc.get(PaymentFileTerm.SERVICE_NAME));
			model.setReferenceUid(doc.get(PaymentFileTerm.REFERENCE_UID));
			// model.setCreateDate(doc.get(PaymentFileTerm.CREATE_DATE));
			// model.setModifiedDate(doc.get(PaymentFileTerm.MODIFIED_DATE));

			String strCreateDate = doc.get(Field.CREATE_DATE);

			Date createDate = null;

			if (Validator.isNotNull(strCreateDate)) {
				createDate = APIDateTimeUtils.convertStringToDate(strCreateDate, "yyyyMMddHHmmss");
			}

			model.setCreateDate(createDate != null
					? APIDateTimeUtils.convertDateToString(createDate, APIDateTimeUtils._TIMESTAMP) : strCreateDate);

			String strModifiedDate = doc.get(Field.MODIFIED_DATE);

			Date modifiedDate = null;

			if (Validator.isNotNull(strModifiedDate)) {
				modifiedDate = APIDateTimeUtils.convertStringToDate(strModifiedDate, "yyyyMMddHHmmss");
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
//			model.setPaymentAmount(GetterUtil.getLong(doc.get(PaymentFileTerm.PAYMENT_AMOUNT)));
			model.setPaymentNote(doc.get(PaymentFileTerm.PAYMENT_NOTE));
			model.setBankInfo(doc.get(PaymentFileTerm.BANK_INFO));
			model.setEpaymentProfile(doc.get(PaymentFileTerm.EPAYMENT_PROFILE));
			model.setPaymentStatus(Integer.parseInt(doc.get(PaymentFileTerm.PAYMENT_STATUS)));
			model.setPaymentMethod(doc.get(PaymentFileTerm.PAYMENT_METHOD));
			// model.setConfirmDatetime(doc.get(PaymentFileTerm.CONFIRM_DATETIME));

			String strConfirmDatetime = doc.get(PaymentFileTerm.CONFIRM_DATETIME);

			Date confirmDatetime = null;

			if (Validator.isNotNull(strConfirmDatetime)) {
				confirmDatetime = APIDateTimeUtils.convertStringToDate(strConfirmDatetime, "yyyyMMddHHmmss");
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
			// model.setApproveDatetime(doc.get(PaymentFileTerm.APPROVE_DATETIME));

			String strApproveDatetime = doc.get(PaymentFileTerm.CONFIRM_DATETIME);

			Date approveDatetime = null;

			if (Validator.isNotNull(strApproveDatetime)) {
				approveDatetime = APIDateTimeUtils.convertStringToDate(strApproveDatetime, "yyyyMMddHHmmss");
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
			
//			model.setBriefNote(dossier.getBriefNote());

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
				//_log.error(e);
			}
		}
		return dlFileVersion;
	}
	

	private static final String KHONG = "không";    
	private static final String MOT = "một";
	private static final String HAI = "hai";
	private static final String BA = "ba";
	private static final String BON = "bốn";
	private static final String NAM = "năm";
	private static final String SAU = "sáu";
	private static final String BAY = "bảy";
	private static final String TAM = "tám";
	private static final String CHIN = "chín";
	private static final String LAM = "lăm";
	private static final String LE = "lẻ";
	private static final String MUOI = "mươi";
	private static final String MUOIF = "mười";
	private static final String MOTS = "mốt";
	private static final String TRAM = "trăm";
	private static final String NGHIN = "nghìn";
	private static final String TRIEU = "triệu";
	private static final String TY = "tỷ";
 
 
	private static final String [] number = {KHONG, MOT, HAI, BA,
        BON, NAM, SAU, BAY, TAM, CHIN};
    
	// Hàm đọc số
	public static String readNum(String a) {
		ArrayList<String> kq = new ArrayList<String>();

		// Cắt chuổi string chử số ra thành các chuổi nhỏ 3 chử số
		ArrayList<String> List_Num = Split(a, 3);

		while (List_Num.size() != 0) {
			// Xét 3 số đầu tiên của chuổi (số đầu tiên của List_Num)
			switch (List_Num.size() % 3) {
			// 3 số đó thuộc hàng trăm
			case 1:
				kq.addAll(read_3num(List_Num.get(0)));
				break;
			// 3 số đó thuộc hàng nghìn
			case 2:
				ArrayList<String> nghin = read_3num(List_Num.get(0));
				if (!nghin.isEmpty()) {
					kq.addAll(nghin);
					kq.add(NGHIN);
				}
				break;
			// 3 số đó thuộc hàng triệu
			case 0:
				ArrayList<String> trieu = read_3num(List_Num.get(0));
				if (!trieu.isEmpty()) {
					kq.addAll(trieu);
					kq.add(TRIEU);
				}
				break;
			}

			// Xét nếu 3 số đó thuộc hàng tỷ
			if (List_Num.size() == (List_Num.size() / 3) * 3 + 1 && List_Num.size() != 1)
				kq.add(TY);

			// Xóa 3 số đầu tiên để tiếp tục 3 số kế
			List_Num.remove(0);
		}
		
		StringBuilder sb = new StringBuilder();
		for (String s : kq) {
            sb.append(s);
            sb.append(" ");
        }
		sb.append("đồng ");
		
		return sb.toString();
	}

	// Đọc 3 số
	private static ArrayList<String> read_3num(String a) {
		ArrayList<String> kq = new ArrayList<String>();
		int num = -1;
		try {
			num = Integer.parseInt(a);
		} catch (Exception ex) {
			_log.debug(ex);
		}
		if (num == 0)
			return kq;

		int hang_tram = -1;
		try {
			hang_tram = Integer.parseInt(a.substring(0, 1));
		} catch (Exception ex) {
			_log.debug(ex);
		}
		int hang_chuc = -1;
		try {
			hang_chuc = Integer.parseInt(a.substring(1, 2));
		} catch (Exception ex) {
			_log.debug(ex);
		}
		int hang_dv = -1;
		try {
			hang_dv = Integer.parseInt(a.substring(2, 3));
		} catch (Exception ex) {
			_log.debug(ex);
		}

		// xét hàng trăm
		if (hang_tram != -1) {
			kq.add(number[hang_tram]);
			kq.add(TRAM);
		}

		// xét hàng chục
		switch (hang_chuc) {
		case -1:
			break;
		case 1:
			kq.add(MUOIF);
			break;
		case 0:
			if (hang_dv != 0)
				kq.add(LE);
			break;
		default:
			kq.add(number[hang_chuc]);
			kq.add(MUOI);
			break;
		}

		// xét hàng đơn vị
		switch (hang_dv) {
		case -1:
			break;
		case 1:
			if ((hang_chuc != 0) && (hang_chuc != 1) && (hang_chuc != -1))
				kq.add(MOTS);
			else
				kq.add(number[hang_dv]);
			break;
		case 5:
			if ((hang_chuc != 0) && (hang_chuc != -1))
				kq.add(LAM);
			else
				kq.add(number[hang_dv]);
			break;
		case 0:
			if (kq.isEmpty())
				kq.add(number[hang_dv]);
			break;
		default:
			kq.add(number[hang_dv]);
			break;
		}
		return kq;
	}

	private static ArrayList<String> Split(String str, int chunkSize) {
		int du = str.length() % chunkSize;
		// Nếu độ dài chuổi không phải bội số của chunkSize thì thêm # vào trước cho đủ.
		if (du != 0)
			for (int i = 0; i < (chunkSize - du); i++)
				str = "#" + str;
		return splitStringEvery(str, chunkSize);
	}

	// Hàm cắt chuổi ra thành chuổi nhỏ
	private static ArrayList<String> splitStringEvery(String s, int interval) {
		ArrayList<String> arrList = new ArrayList<String>();
		int arrayLength = (int) Math.ceil(((s.length() / (double) interval)));
		String[] result = new String[arrayLength];
		int j = 0;
		int lastIndex = result.length - 1;
		for (int i = 0; i < lastIndex; i++) {
			result[i] = s.substring(j, j + interval);
			j += interval;
		}
		result[lastIndex] = s.substring(j);

		/*
		 * Có thể dùng hàm sau để cắt nhưng hiệu suất sẽ thấp hơn cách trên result =
		 * s.split("(?<=\\G.{" + interval + "})");
		 */

		arrList.addAll(Arrays.asList(result));
		return arrList;
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
