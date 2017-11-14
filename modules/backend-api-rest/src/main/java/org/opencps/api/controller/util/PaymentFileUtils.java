package org.opencps.api.controller.util;

import java.util.ArrayList;
import java.util.List;

import org.opencps.api.paymentFile.model.PaymentFileModel;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.dossiermgt.model.PaymentFile;
import com.liferay.document.library.kernel.model.DLFileVersion;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileVersionLocalServiceUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;

public class PaymentFileUtils {

    public static List<PaymentFileModel> mappingToPaymentFileData(
            List<PaymentFile> paymentFiles) {

            List<PaymentFileModel> outputs = new ArrayList<PaymentFileModel>();

            for (PaymentFile paymentFile : paymentFiles) {

            	PaymentFileModel model = mappingToPaymentFileModel(paymentFile);

                outputs.add(model);
            }

            return outputs;
        }

    public static PaymentFileModel mappingToPaymentFileModel(
    		PaymentFile paymentFile) {

            if (paymentFile == null) {
                return null;
            }

            PaymentFileModel model = new PaymentFileModel();

            //
            model.setCreateDate(
                APIDateTimeUtils.convertDateToString(paymentFile.getCreateDate()));
            model.setModifiedDate(
                APIDateTimeUtils.convertDateToString(
                		paymentFile.getModifiedDate()));
            //
            model.setReferenceUid(paymentFile.getReferenceUid());
            model.setGovAgencyCode(paymentFile.getGovAgencyCode());
            model.setGovAgencyName(paymentFile.getGovAgencyName());
            model.setApplicantName(paymentFile.getApplicantName());
            model.setApplicantIdNo(paymentFile.getApplicantIdNo());
//            model.setIsNew(paymentFile.getIsNew());
            model.setPaymentFee(paymentFile.getPaymentFee());
            model.setPaymentAmount(paymentFile.getPaymentAmount());
            model.setPaymentNote(paymentFile.getPaymentNote());
            model.setBankInfo(paymentFile.getBankInfo());
            model.setEpaymentProfile(paymentFile.getEpaymentProfile());
            model.setPaymentStatus(paymentFile.getPaymentStatus());
            model.setPaymentMethod(paymentFile.getPaymentMethod());
            model.setConfirmDatetime(
            		APIDateTimeUtils.convertDateToString(paymentFile.getConfirmDatetime()));
            model.setConfirmPayload(paymentFile.getConfirmPayload());
            DLFileVersion dlFilePayLoad = getFileInfo(paymentFile.getConfirmFileEntryId());
            if (dlFilePayLoad != null) {
            	model.setConfirmFileType(dlFilePayLoad.getExtension());
                model.setConfirmFileSize(dlFilePayLoad.getSize());
			} else {
	            model.setConfirmFileType(StringPool.BLANK);
	            model.setConfirmFileSize(0L);
			}
//            model.setConfirmFileType(paymentFile.getConfirmFileType());
//            model.setConfirmFileSize(paymentFile.getConfirmFileSize());
            model.setConfirmNote(paymentFile.getConfirmNote());
            model.setApproveDatetime(
            		APIDateTimeUtils.convertDateToString(paymentFile.getApproveDatetime()));
            model.setAccountUserName(paymentFile.getAccountUserName());
            model.setGovAgencyTaxNo(paymentFile.getGovAgencyTaxNo());
            model.setInvoiceTemplateNo(paymentFile.getInvoiceTemplateNo());
            model.setInvoiceIssueNo(paymentFile.getInvoiceIssueNo());
            model.setInvoiceNo(paymentFile.getInvoiceNo());
            DLFileVersion dlFileInvoice = getFileInfo(paymentFile.getInvoiceFileEntryId());
            if (dlFileInvoice != null) {
            	model.setInvoiceFileType(dlFileInvoice.getExtension());
                model.setInvoiceFileSize(dlFileInvoice.getSize());
			} else {
				model.setInvoiceFileType(StringPool.BLANK);
	            model.setInvoiceFileSize(0L);
			}
//            model.setInvoiceFileType(paymentFile.getInvoiceFileType());
//            model.setInvoiceFileSize(paymentFile.getInvoiceFileSize());
            

//            String fileType = StringPool.BLANK;
//            long fileSize = 0;

//            if (paymentFile.getPaymentFileId() > 0) {
//                try {
//                    FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(
//                    		paymentFile.getPaymentFileId());
//                    DLFileVersion dlFileVersion =
//                        DLFileVersionLocalServiceUtil.getLatestFileVersion(
//                            fileEntry.getFileEntryId(), true);
//
//                    fileType = dlFileVersion.getExtension();
//                    fileSize = dlFileVersion.getSize();
//                }
//                catch (Exception e) {
//
//                }
//            }

            return model;
        }
	
	public static DLFileVersion getFileInfo(Long fileId){
//		String fileType = StringPool.BLANK;
//        long fileSize = 0;
        DLFileVersion dlFileVersion = null;

        if (fileId > 0) {
            try {
                FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileId);
                dlFileVersion = DLFileVersionLocalServiceUtil.getLatestFileVersion(
                        fileEntry.getFileEntryId(), true);
            } catch (Exception e) {
            	//TODO:
            }
        }
        return dlFileVersion;
	}
	
	
	
	
	
	
}
