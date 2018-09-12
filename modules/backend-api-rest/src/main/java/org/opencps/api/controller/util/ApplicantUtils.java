package org.opencps.api.controller.util;

import java.util.ArrayList;
import java.util.List;

import org.opencps.api.usermgt.model.ApplicantModel;
import org.opencps.api.usermgt.model.MappingUser;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
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
		model.setApplicantId(GetterUtil.getLong(applicant.getPrimaryKey()));
		model.setCreateDate(String.valueOf(applicant.getCreateDate()));
		model.setModifiedDate(String.valueOf(applicant.getModifiedDate()));
		model.setApplicantName(applicant.getApplicantName());
		model.setApplicantIdType(applicant.getApplicantIdType());
		model.setApplicantIdNo(applicant.getApplicantIdNo());
		model.setApplicantIdDate(StringUtil.valueOf(applicant.getApplicantIdDate()));
		model.setContactEmail(applicant.getContactEmail());
		model.setAddress(applicant.getAddress());
		model.setCityCode(applicant.getCityCode());
		model.setCityName(applicant.getCityName());
		model.setDistrictCode(applicant.getDistrictCode());
		model.setDistrictName(applicant.getDistrictName());
		model.setWardCode(applicant.getWardCode());
		model.setWardName(applicant.getWardName());
		model.setContactName(applicant.getContactName());
		model.setContactTelNo(applicant.getContactTelNo());
		
		long mappingUserId = applicant.getMappingUserId();

		MappingUser mappingUser = new MappingUser();

		User user = null;
		
		try {
			user = UserLocalServiceUtil.getUser(mappingUserId);
		} catch (Exception e) {
			// TODO: handle exception
			_log.error(e);
		}

		if (user != null) {
			mappingUser.setUserId(Long.toString(mappingUserId));
			mappingUser.setScreenName(user.getScreenName());
			mappingUser.setLocking(user.getLockout());
		}
		
		 model.setMappingUser(mappingUser);


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

			model.setApplicantId(GetterUtil.getLong(doc.get(Field.ENTRY_CLASS_PK)));
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
			
			User user = null;
			
			try {
				user = UserLocalServiceUtil.getUser(mappingUserId);
			} catch (Exception e) {
				// TODO: handle exception
				_log.error(e);
			}

			if (user != null) {
				mappingUser.setUserId(Long.toString(mappingUserId));
				mappingUser.setScreenName(user.getScreenName());
				mappingUser.setLocking(user.getLockout());
			}

			model.setMappingUser(mappingUser);

			data.add(model);
		}

		return data;
	}
	
	public static User getUser(long applicantId) {
		User user = null;
		
		try {
			Applicant applicant = ApplicantLocalServiceUtil.getApplicant(applicantId);
			
			user = UserLocalServiceUtil.fetchUser(applicant.getMappingUserId());
		} catch (Exception e) {
			_log.error(e);
		}
		
		return user;
	}
	
	static Log _log = LogFactoryUtil.getLog(ApplicantUtils.class);
}
