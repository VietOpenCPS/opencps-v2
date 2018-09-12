
package org.opencps.api.controller.util;

import java.util.ArrayList;
import java.util.List;

import org.opencps.api.registrationform.model.RegistrationFormDetailModel;
import org.opencps.api.registrationform.model.RegistrationFormModel;
import org.opencps.dossiermgt.model.Registration;
import org.opencps.dossiermgt.model.RegistrationForm;
import org.opencps.dossiermgt.model.RegistrationTemplates;
import org.opencps.dossiermgt.service.RegistrationLocalServiceUtil;
import org.opencps.dossiermgt.service.RegistrationTemplatesLocalServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

public class RegistrationFormUtils {

	private static Log _log = LogFactoryUtil.getLog(RegistrationFormUtils.class);

	public static RegistrationFormDetailModel mappingToRegistrationFormDetailModel(RegistrationForm registrationForm) {

		if (registrationForm == null) {
			return null;
		}
		long version = 1;
		RegistrationFormDetailModel model = new RegistrationFormDetailModel();

		try {
			Registration registration = RegistrationLocalServiceUtil
					.getRegistrationByG_REGID(registrationForm.getGroupId(), registrationForm.getRegistrationId());

			RegistrationTemplates registrationTemplates = RegistrationTemplatesLocalServiceUtil
					.getRegTempbyFormNoGovCode(registrationForm.getGroupId(), registrationForm.getFormNo(),
							registration.getGovAgencyCode());

			model.setReferenceUid(registrationForm.getReferenceUid());
			model.setFormNo(registrationForm.getFormNo());
			model.setFormName(registrationForm.getFormName());
			model.setIsNew(registrationForm.isIsNew());
			model.setRemoved(registrationForm.isRemoved());
			model.setVersion(version);
			model.setMultiple(registrationTemplates.getMultiple());

		} catch (Exception e) {
			_log.error(e);
		}
		return model;
	}

	public static List<RegistrationFormModel> mappingToRegistrationFormResultsModel(
			List<RegistrationForm> lstRegistrationForm) {
		List<RegistrationFormModel> outputs = new ArrayList<RegistrationFormModel>();
		long version = 1;
		if (Validator.isNotNull(lstRegistrationForm)) {
			for (RegistrationForm registrationForm : lstRegistrationForm) {
				Registration registration = RegistrationLocalServiceUtil
						.getRegistrationByG_REGID(registrationForm.getGroupId(), registrationForm.getRegistrationId());

				RegistrationTemplates registrationTemplates = RegistrationTemplatesLocalServiceUtil
						.getRegTempbyFormNoGovCode(registrationForm.getGroupId(), registrationForm.getFormNo(),
								registration.getGovAgencyCode());

				boolean multiple = registrationTemplates != null ? registrationTemplates.getMultiple() : false;
				
				RegistrationFormModel model = new RegistrationFormModel();
				
				model.setReferenceUid(registrationForm.getReferenceUid());
				model.setFormNo(registrationForm.getFormNo());
				model.setFormName(registrationForm.getFormName());
				model.setIsNew(Boolean.valueOf(registrationForm.isIsNew()));
				model.setRemoved(Boolean.valueOf(registrationForm.isRemoved()));
				model.setVersion(version);
				model.setMultiple(Boolean.valueOf(multiple));
				
				outputs.add(model);
			}
		}
		return outputs;
	}

	//TODO:
//	public static List<RegistrationFormModel> mappingToRegistrationFormResultModel(List<Document> documents) {
//		List<RegistrationFormModel> data = new ArrayList<RegistrationFormModel>();
//
//		for (Document doc : documents) {
//			RegistrationFormModel model = mappingToRegistrationForm(doc);
//			data.add(model);
//		}
//
//		return data;
//	}
//
//	public static RegistrationFormModel mappingToRegistrationForm (Document doc) {
//		RegistrationFormModel model = new RegistrationFormModel();
//
//		model.set
//	    model.setDeliverableId(Long.valueOf(doc.get(DeliverableTerm.DELIVERABLE_ID)));
//	    model.setUserId(Long.valueOf(doc.get(Field.USER_ID)));
//	    model.setUserName(doc.get(Field.USER_NAME));
//	    //Convert Date to String
//		String strCreateDate = doc.get(Field.CREATE_DATE);
//		Date createDate = null;
//		if (Validator.isNotNull(strCreateDate)) {
//			createDate = APIDateTimeUtils.convertStringToDate(strCreateDate, "yyyyMMddHHmmss");
//		}
//		model.setCreateDate(createDate != null
//				? APIDateTimeUtils.convertDateToString(createDate, APIDateTimeUtils._TIMESTAMP) : strCreateDate);
//
//		String strModifiedDate = doc.get(Field.MODIFIED_DATE);
//		Date modifiedDate = null;
//		if (Validator.isNotNull(strModifiedDate)) {
//			modifiedDate = APIDateTimeUtils.convertStringToDate(strModifiedDate, "yyyyMMddHHmmss");
//		}
//		model.setModifiedDate(modifiedDate != null
//				? APIDateTimeUtils.convertDateToString(modifiedDate, APIDateTimeUtils._TIMESTAMP)
//				: strModifiedDate);
//
//	    model.setDeliverableCode(doc.get(DeliverableTerm.DELIVERABLE_CODE));
//	    model.setDeliverableName(doc.get(DeliverableTerm.DELIVERABLE_NAME));
//	    model.setDeliverableType(doc.get(DeliverableTerm.DELIVERABLE_TYPE));
//	    model.setGovAgencyCode(doc.get(DeliverableTerm.GOV_AGENCY_CODE));
//	    model.setGovAgencyName(doc.get(DeliverableTerm.GOV_AGENCY_NAME));
//	    model.setApplicantIdNo(doc.get(DeliverableTerm.APPLICANT_ID_NO));
//	    model.setApplicantName(doc.get(DeliverableTerm.APPLICANT_NAME));
//	    model.setSubject(doc.get(DeliverableTerm.SUBJECT));
//	  //Convert Date to String
//		String strIssueDate = doc.get(DeliverableTerm.ISSUE_DATE);
//		Date issueDate = null;
//		if (Validator.isNotNull(strIssueDate)) {
//			issueDate = APIDateTimeUtils.convertStringToDate(strIssueDate, "yyyyMMddHHmmss");
//		}
//		model.setModifiedDate(issueDate != null
//				? APIDateTimeUtils.convertDateToString(issueDate, APIDateTimeUtils._TIMESTAMP)
//				: strIssueDate);
//
//		String strExpireDate = doc.get(DeliverableTerm.EXPIRE_DATE);
//		Date expireDate = null;
//		if (Validator.isNotNull(strExpireDate)) {
//			expireDate = APIDateTimeUtils.convertStringToDate(strExpireDate, "yyyyMMddHHmmss");
//		}
//		model.setModifiedDate(expireDate != null
//				? APIDateTimeUtils.convertDateToString(expireDate, APIDateTimeUtils._TIMESTAMP)
//				: strExpireDate);
//
//		String strRevalidate = doc.get(DeliverableTerm.REVALIDATE);
//		Date revalidate = null;
//		if (Validator.isNotNull(strRevalidate)) {
//			revalidate = APIDateTimeUtils.convertStringToDate(strRevalidate, "yyyyMMddHHmmss");
//		}
//		model.setModifiedDate(revalidate != null
//				? APIDateTimeUtils.convertDateToString(revalidate, APIDateTimeUtils._TIMESTAMP)
//				: strRevalidate);
//
//	    model.setDeliverableState(doc.get(DeliverableTerm.DELIVERABLE_STATE));
//
//	    return model;
//	}

}
