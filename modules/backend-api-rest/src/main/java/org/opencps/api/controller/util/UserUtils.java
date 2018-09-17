package org.opencps.api.controller.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.opencps.api.jobpos.model.JobposPermissionModel;
import org.opencps.api.user.model.UserAccountModel;
import org.opencps.api.user.model.UserModel;
import org.opencps.api.user.model.UserProfileModel;
import org.opencps.api.user.model.UserRolesModel;
import org.opencps.api.user.model.UserSitesModel;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;

import backend.auth.api.BackendAuthImpl;
import backend.auth.api.keys.ModelNameKeys;
import backend.utils.APIDateTimeUtils;

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

	public static UserAccountModel mapperUserAccountModel(Document document) {

		UserAccountModel ett = new UserAccountModel();

		try {

			ett.setUserId(document.get("userId"));
			ett.setUserName(document.get("userName"));
			ett.setContactEmail(document.get("contactEmail"));
			ett.setContactTelNo(document.get("contactTelNo"));

		} catch (Exception e) {
			_log.error(e);
		}

		return ett;
	}
	
	public static List<UserModel> mapperUserList(List<User> listDocument, long groupId) {

		List<UserModel> results = new ArrayList<>();

		try {

			UserModel ett = null;

			for (User document : listDocument) {
				ett = new UserModel();

				Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, document.getUserId());
				
				ett.setUserId(document.getUserId());
				ett.setScreenName(document.getScreenName());
				ett.setEmail(document.getEmailAddress());
				
				String fullName = StringPool.BLANK;
				String contactEmail = StringPool.BLANK;
				String contactTelNo = StringPool.BLANK;
				String className = StringPool.BLANK;
				String classPK = StringPool.BLANK;
				
				if(Validator.isNotNull(employee)){
					className = Employee.class.getName();
					classPK = String.valueOf(employee.getEmployeeId());
					fullName = employee.getFullName();
					contactEmail = employee.getEmail();
					contactTelNo = employee.getTelNo();
				}
				
				ett.setClassName(className);
				ett.setClassPK(classPK);
				ett.setFullName(fullName);
				ett.setContactEmail(contactEmail);
				ett.setContactTelNo(contactTelNo);
				
				results.add(ett);
			}

		} catch (Exception e) {
			_log.error(e);
		}

		return results;
	}

	public static UserModel mapperUserModel(User user, long groupId) {

		UserModel ett = new UserModel();

		try {

			Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, user.getUserId());
			
			ett.setUserId(user.getUserId());
			ett.setScreenName(user.getScreenName());
			ett.setEmail(user.getEmailAddress());
			
			String fullName = StringPool.BLANK;
			String contactEmail = StringPool.BLANK;
			String contactTelNo = StringPool.BLANK;
			String className = StringPool.BLANK;
			String classPK = StringPool.BLANK;
			
			if(Validator.isNotNull(employee)){
				className = Employee.class.getName();
				classPK = String.valueOf(employee.getEmployeeId());
				fullName = employee.getFullName();
				contactEmail = employee.getEmail();
				contactTelNo = employee.getTelNo();
			}
			
			ett.setClassName(className);
			ett.setClassPK(classPK);
			ett.setFullName(fullName);
			ett.setContactEmail(contactEmail);
			ett.setContactTelNo(contactTelNo);
			
		} catch (Exception e) {
			_log.error(e);
		}

		return ett;
	}

	public static List<JobposPermissionModel> mapperUsersPermissionsList(String[] listPermissions, long userId,
			ServiceContext serviceContext) {
		
		serviceContext.setUserId(userId);
		
		List<JobposPermissionModel> results = new ArrayList<>();
		
		BackendAuthImpl authImpl = new BackendAuthImpl();
		
		try {
			
			JobposPermissionModel ett = null;

			for (String actionKey : listPermissions) {
				ett = new JobposPermissionModel();

				ett.setActionId(actionKey);
				ett.setActionName(LanguageUtil.get(locale, actionKey));

				boolean selected = authImpl.userHasResource(serviceContext, ModelNameKeys.WORKINGUNIT_MGT_CENTER, actionKey);
						
				
				ett.setSelected(selected);

				results.add(ett);
			}

		} catch (Exception e) {
			_log.error(e);
		}

		return results;
	}
	
	public static final Locale locale = new Locale("vi", "VN");
	static Log _log = LogFactoryUtil.getLog(UserUtils.class);
}
