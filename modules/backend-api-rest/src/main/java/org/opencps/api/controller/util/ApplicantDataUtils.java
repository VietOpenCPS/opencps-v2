package org.opencps.api.controller.util;

import org.opencps.api.applicantdata.model.ApplicantDataDetailModel;
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
}
