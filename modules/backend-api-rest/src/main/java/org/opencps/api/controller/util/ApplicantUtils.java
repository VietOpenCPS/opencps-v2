package org.opencps.api.controller.util;

import java.util.ArrayList;
import java.util.List;

import org.opencps.api.usermgt.model.ApplicantModel;
import org.opencps.api.usermgt.model.MappingUser;
import org.opencps.usermgt.model.Applicant;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

public class ApplicantUtils {

	/**
	 * @author khoavu
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
		model.setCityCode(applicant.getCityCode());
		model.setCityName(applicant.getCityName());
		model.setDistrictCode(applicant.getDistrictCode());
		model.setDistrictName(applicant.getDistrictName());
		model.setWardCode(applicant.getWardCode());
		model.setWardName(applicant.getWardName());
		model.setContactName(applicant.getContactName());
		model.setContactTelNo(applicant.getContactTelNo());
		

		return model;
	}

	/**
	 * @author khoavu
	 * @param documents
	 * @return
	 */
	public static List<ApplicantModel> mappingToApplicantResults(List<Document> documents) {

		List<ApplicantModel> data = new ArrayList<ApplicantModel>();


		for (Document doc : documents) {
			ApplicantModel model = new ApplicantModel();

			model.setApplicantId(GetterUtil.getLong(doc.get("applicantId")));
			model.setCreateDate(GetterUtil.getString(doc.get(Field.CREATE_DATE)));
			model.setModifiedDate(GetterUtil.getString(doc.get(Field.MODIFIED_DATE)));
			model.setApplicantName(GetterUtil.getString(doc.get("applicantName")));
			model.setApplicantIdType(GetterUtil.getString(doc.get("applicantIdType")));
			model.setApplicantIdNo(GetterUtil.getString(doc.get("applicantIdNo")));
			model.setApplicantIdDate(GetterUtil.getString(doc.get("applicantIdDate")));
			model.setAddress(GetterUtil.getString(doc.get("address")));
			model.setCityCode(GetterUtil.getString(doc.get("cityCode")));
			model.setCityName(GetterUtil.getString(doc.get("cityName")));
			model.setDistrictCode(GetterUtil.getString(doc.get("districtCode")));
			model.setDistrictName(GetterUtil.getString(doc.get("districtName")));
			model.setWardCode(GetterUtil.getString(doc.get("wardCode")));
			model.setWardName(GetterUtil.getString(doc.get("wardName")));
			model.setContactName(GetterUtil.getString(doc.get("contactName")));
			model.setContactTelNo(GetterUtil.getString(doc.get("contactTelNo")));
			model.setContactEmail(GetterUtil.getString(doc.get("contactEmail")));

			MappingUser mappingUser = new MappingUser();

			long mappingUserId = GetterUtil.getLong(doc.get("mappingUserId"));

			User user = UserUtils.getUser(mappingUserId);

			if (Validator.isNotNull(user)) {
				mappingUser.setUserId(GetterUtil.getString(mappingUserId));
				mappingUser.setScreenName(user.getScreenName());
				mappingUser.setLocking(user.getLockout());
			}

			model.setMappingUser(mappingUser);

			data.add(model);
		}

		return data;
	}
}
