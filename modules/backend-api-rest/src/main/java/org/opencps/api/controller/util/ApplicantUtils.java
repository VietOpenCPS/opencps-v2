package org.opencps.api.controller.util;

import java.util.ArrayList;
import java.util.List;

import org.opencps.api.usermgt.model.ApplicantModel;
import org.opencps.api.usermgt.model.MappingUser;
import org.opencps.usermgt.constants.ApplicantTerm;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;

public class ApplicantUtils {

	private static Log _log = LogFactoryUtil.getLog(ApplicantUtils.class);

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
		model.setApplicantIdDate(String.valueOf(applicant.getApplicantIdDate()));
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
		MappingUser mappingUser = processMappingUser(mappingUserId);
		if (mappingUser != null) {
			model.setMappingUser(mappingUser);
		}

//		MappingUser mappingUser = new MappingUser();
//		User user = null;
//		try {
//			user = UserLocalServiceUtil.getUser(mappingUserId);
//		} catch (Exception e) {
//			//_log.error(e);
//			_log.debug(e);
//		}
//		if (user != null) {
//			mappingUser.setUserId(Long.toString(mappingUserId));
//			mappingUser.setScreenName(user.getScreenName());
//			mappingUser.setLocking(user.getLockout());
//		}
//		model.setMappingUser(mappingUser);

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
			model.setApplicantName(GetterUtil.getString(doc.get(ApplicantTerm.APPLICANTNAME)));
			model.setApplicantIdType(GetterUtil.getString(doc.get(ApplicantTerm.APPLICANTIDTYPE)));
			model.setApplicantIdNo(GetterUtil.getString(doc.get(ApplicantTerm.APPLICANTIDNO)));
			model.setApplicantIdDate(GetterUtil.getString(doc.get(ApplicantTerm.APPLICANTIDDATE)));
			model.setAddress(GetterUtil.getString(doc.get(ApplicantTerm.ADDRESS)));
			model.setCityCode(GetterUtil.getString(doc.get(ApplicantTerm.CITYCODE)));
			model.setCityName(GetterUtil.getString(doc.get(ApplicantTerm.CITYNAME)));
			model.setDistrictCode(GetterUtil.getString(doc.get(ApplicantTerm.DISTRICTCODE)));
			model.setDistrictName(GetterUtil.getString(doc.get(ApplicantTerm.DISTRICTNAME)));
			model.setWardCode(GetterUtil.getString(doc.get(ApplicantTerm.WARDCODE)));
			model.setWardName(GetterUtil.getString(doc.get(ApplicantTerm.WARDNAME)));
			model.setContactName(GetterUtil.getString(doc.get(ApplicantTerm.CONTACTNAME)));
			model.setContactTelNo(GetterUtil.getString(doc.get(ApplicantTerm.CONTACTTELNO)));
			model.setContactEmail(GetterUtil.getString(doc.get(ApplicantTerm.CONTACTEMAIL)));

			long mappingUserId = GetterUtil.getLong(doc.get(ApplicantTerm.MAPPINGUSERID));
			MappingUser mappingUser = processMappingUser(mappingUserId);
			if (mappingUser != null) {
				model.setMappingUser(mappingUser);
			}

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
			//_log.error(e);
			_log.debug(e);
		}
		
		return user;
	}

	private static MappingUser processMappingUser(long mappingUserId) {

		try {
			User user = UserLocalServiceUtil.getUser(mappingUserId);
			if (user != null) {
				MappingUser mappingUser = new MappingUser();

				mappingUser.setUserId(Long.toString(mappingUserId));
				mappingUser.setScreenName(user.getScreenName());
				mappingUser.setLocking(user.getLockout());
				//
				return mappingUser;
			}
		} catch (Exception e) {
			//_log.error(e);
			_log.debug(e);
		}

		return null;
	}

}
