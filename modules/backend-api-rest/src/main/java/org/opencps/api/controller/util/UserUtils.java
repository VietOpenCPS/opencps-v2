package org.opencps.api.controller.util;

import java.util.ArrayList;
import java.util.List;

import org.opencps.api.user.model.UserProfileModel;
import org.opencps.api.user.model.UserRolesModel;
import org.opencps.api.user.model.UserSitesModel;
import org.opencps.datamgt.constants.LabelTerm;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import org.opencps.auth.utils.APIDateTimeUtils;

public class UserUtils {

	public static List<UserSitesModel> mapperUserSitesList(List<Document> listDocument) {

		List<UserSitesModel> results = new ArrayList<>();

		try {

			UserSitesModel ett = null;

			for (Document document : listDocument) {
				ett = new UserSitesModel();

				ett.setOfficeSiteId(Long.valueOf(document.get("entryClassPK")));
				ett.setSiteName(document.get("siteName"));
				ett.setSiteGroupId(Long.valueOf(document.get("siteGroupId")));
				ett.setCurrentSite(Boolean.valueOf(document.get("currentSite")));

				results.add(ett);
			}

		} catch (Exception e) {
			_log.error(e);
		}

		return results;
	}

	public static List<UserRolesModel> mapperUserRolesList(List<Document> listDocument) {

		List<UserRolesModel> results = new ArrayList<>();

		try {

			UserRolesModel ett = null;

			for (Document document : listDocument) {
				ett = new UserRolesModel();

				ett.setRoleId(Long.valueOf(document.get("entryClassPK")));
				ett.setRoleName(document.get("roleName"));

				results.add(ett);
			}

		} catch (Exception e) {
			_log.error(e);
		}

		return results;
	}

	public static UserProfileModel mapperUserProfileModel(Document document) {

		UserProfileModel ett = new UserProfileModel();

		try {

			ett.setClassName(document.get("className"));
			ett.setClassPK(document.get("classPK"));
			ett.setScreenName(document.get("screenName"));
			ett.setEmail(document.get("email"));
			ett.setFullName(document.get("fullName"));
			ett.setContactEmail(document.get("contactEmail"));
			ett.setContactTelNo(document.get("contactTelNo"));
			ett.setGender(document.get("gender"));
			ett.setBirthdate(Validator.isNotNull(document.getDate("birthdate")) ? APIDateTimeUtils
					.convertDateToString(document.getDate("birthdate"), APIDateTimeUtils._TIMESTAMP)
					: StringPool.BLANK);

		} catch (Exception e) {
			_log.error(e);
		}

		return ett;
	}
	
	public static User getUser(long applicantId) {
		User user = null;
		
		try {
			Applicant applicant = ApplicantLocalServiceUtil.getApplicant(applicantId);
			
			user = UserUtils.getUser(applicant.getMappingUserId());
		} catch (Exception e) {
			
		}
		
		return user;
	}


	static Log _log = LogFactoryUtil.getLog(UserUtils.class);
}
