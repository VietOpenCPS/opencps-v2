package org.opencps.api.controller.util;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.opencps.api.applicantdata.model.ApplicantDataDetailModel;
import org.opencps.api.applicantdata.model.ApplicantDataModel;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.kernel.util.FileUploadUtil;
import org.opencps.usermgt.constants.ApplicantDataTerm;
import org.opencps.usermgt.constants.ApplicantTerm;
import org.opencps.usermgt.model.ApplicantData;

public class ApplicantDataUtils {
    public static ApplicantDataDetailModel mappingToApplicantDataModel(
            ApplicantData applicantData) {
    	ApplicantDataDetailModel model = new ApplicantDataDetailModel();
    	model.setApplicantDataId(applicantData.getApplicantDataId());
    	model.setUserId(applicantData.getUserId());
    	model.setUserName(applicantData.getUserName());
    	model.setFileTemplateNo(applicantData.getFileTemplateNo());
    	model.setFileNo(applicantData.getFileNo());
    	model.setFileName(applicantData.getFileName());
    	model.setFileEntryId(applicantData.getFileEntryId());
    	model.setMetadata(applicantData.getMetadata());
    	model.setStatus(applicantData.getStatus());
    	model.setApplicantIdNo(applicantData.getApplicantIdNo());
    	model.setApplicantDataType(applicantData.getApplicantDataType());
    	
    	return model;
    }
    
	public static List<ApplicantDataModel> mappingToApplicantDataResults(List<Document> documents, HttpServletRequest request) {

		List<ApplicantDataModel> data = new ArrayList<ApplicantDataModel>();

		for (Document doc : documents) {
			ApplicantDataModel model = new ApplicantDataModel();

			model.setGroupId(GetterUtil.getLong(doc.get(ApplicantDataTerm.GROUP_ID)));
			model.setUserId(GetterUtil.getLong(doc.get(ApplicantDataTerm.USER_ID)));
			model.setApplicantDataId(GetterUtil.getLong(doc.get(Field.ENTRY_CLASS_PK)));
			model.setUserName(GetterUtil.getString(doc.get(ApplicantDataTerm.USERNAME)));
			model.setApplicantIdNo(GetterUtil.getString(doc.get(ApplicantTerm.APPLICANTIDNO)));
			model.setFileTemplateNo(GetterUtil.getString(doc.get(ApplicantDataTerm.FILE_TEMPLATE_NO)));
			model.setFileNo(GetterUtil.getString(doc.get(ApplicantDataTerm.FILE_NO)));
			model.setFileName(GetterUtil.getString(doc.get(ApplicantDataTerm.FILE_NAME)));
			model.setApplicantIdNo(GetterUtil.getString(doc.get(ApplicantDataTerm.APPLICANT_ID_NO)));
			model.setApplicantDataType(GetterUtil.getInteger(doc.get(ApplicantDataTerm.APPLICANT_DATA_TYPE)));
			model.setFileEntryId(GetterUtil.getLong(doc.get(ApplicantDataTerm.FILE_ENTRY_ID)));
			model.setStatus(GetterUtil.getInteger(doc.get(ApplicantDataTerm.STATUS)));
			model.setApplicantDataType(GetterUtil.getInteger(doc.get(ApplicantDataTerm.APPLICANT_DATA_TYPE)));
			if (Validator.isNotNull(doc.get(ApplicantDataTerm.CREATE_DATE))) {
				Date createDate = APIDateTimeUtils.convertStringToDate(doc.get(ApplicantDataTerm.CREATE_DATE), APIDateTimeUtils._LUCENE_PATTERN);
				model.setCreateDate(APIDateTimeUtils.convertDateToString(createDate, APIDateTimeUtils._NORMAL_PARTTERN));
			} else {
				model.setCreateDate(doc.get(ApplicantDataTerm.CREATE_DATE));
			}
			if (Validator.isNotNull(doc.get(ApplicantDataTerm.MODIFIED_DATE))) {
				Date modifiedDate = APIDateTimeUtils.convertStringToDate(doc.get(ApplicantDataTerm.MODIFIED_DATE), APIDateTimeUtils._LUCENE_PATTERN);
				model.setModifiedDate(APIDateTimeUtils.convertDateToString(modifiedDate, APIDateTimeUtils._NORMAL_PARTTERN));
			} else {
				model.setModifiedDate(doc.get(ApplicantDataTerm.MODIFIED_DATE));
			}
			if (Validator.isNotNull(doc.get(ApplicantDataTerm.FILE_ENTRY_ID))) {
				String filePath = FileUploadUtil.getFileEntryPreviewPath(GetterUtil.getLong(doc.get(ApplicantDataTerm.FILE_ENTRY_ID)), request);
				model.setFilePath(filePath);	
				DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.fetchDLFileEntry(GetterUtil.getLong(doc.get(ApplicantDataTerm.FILE_ENTRY_ID)));
				if (fileEntry != null) {
					model.setFileExtension(fileEntry.getExtension());
				}
			}
			model.setDossierNo(GetterUtil.getString(doc.get(ApplicantDataTerm.DOSSIER_NO)));
			data.add(model);
		}

		return data;
	}
}
