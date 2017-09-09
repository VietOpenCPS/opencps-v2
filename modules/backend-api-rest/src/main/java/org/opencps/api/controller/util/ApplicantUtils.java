package org.opencps.api.controller.util;

import org.opencps.api.usermgt.model.ApplicantModel;
import org.opencps.usermgt.model.Applicant;

import com.liferay.portal.kernel.util.StringUtil;

public class ApplicantUtils {
	
	/**
	 * @param applicant
	 * @return
	 */
	public static ApplicantModel mappingToApplicantModel(Applicant applicant) {
		
		ApplicantModel model = new ApplicantModel();
		
		model.setApplicantName(applicant.getApplicantName());
		model.setApplicantIdType(applicant.getApplicantIdType());
		model.setApplicantIdNo(applicant.getApplicantIdNo());
		model.setApplicantIdDate(StringUtil.valueOf(applicant.getApplicantIdDate()));
		model.setContactEmail(applicant.getContactEmail());
		
		return model;
	} 
}
