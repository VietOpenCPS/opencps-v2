package org.opencps.usermgt.action.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.opencps.communication.service.NotificationQueueLocalServiceUtil;
import org.opencps.usermgt.action.UserInterface;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.model.OfficeSite;
import org.opencps.usermgt.model.Preferences;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;
import org.opencps.usermgt.service.OfficeSiteLocalServiceUtil;
import org.opencps.usermgt.service.PreferencesLocalServiceUtil;

import com.liferay.asset.kernel.exception.DuplicateCategoryException;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Image;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ImageLocalServiceUtil;
import com.liferay.portal.kernel.service.PasswordTrackerLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PwdGenerator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import backend.auth.api.exception.NotFoundException;
import backend.auth.api.exception.UnauthenticationException;
import backend.auth.api.exception.UnauthorizationException;
import backend.auth.api.keys.Constants;
import backend.utils.FileUploadUtils;

public class UserActions implements UserInterface {

	public static Locale locale = new Locale("vi", "VN");
	private static final Log _log = LogFactoryUtil.getLog(UserActions.class);

	@Override
	public File getPhoto(long id, ServiceContext serviceContext) {
		User user = UserLocalServiceUtil.fetchUser(id);

		File file = null;
		Image image = ImageLocalServiceUtil.fetchImage(user.getPortraitId());

		try {
			file = FileUtil.createTempFile(image.getTextObj());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return file;
	}

	@Override
	public File uploadPhoto(long userId, long companyId, long groupId, long id, InputStream inputStream,
			String fileName, String fileType, long fileSize, String destination, String desc,
			ServiceContext serviceContext) throws Exception {
		File file = null;

		FileEntry fileEntry = FileUploadUtils.uploadFile(userId, companyId, groupId, inputStream, fileName, fileType,
				fileSize, destination, desc, serviceContext);

		User user = UserLocalServiceUtil.fetchUser(id);

		file = DLFileEntryLocalServiceUtil.getFile(fileEntry.getFileEntryId(), fileEntry.getVersion(), false);

		UserLocalServiceUtil.updatePortrait(user.getUserId(), FileUtil.getBytes(file));

		return file;
	}

	@Override
	public String getType(long id, ServiceContext serviceContext) {
		User user = UserLocalServiceUtil.fetchUser(id);

		Image image = ImageLocalServiceUtil.fetchImage(user.getPortraitId());

		String type = Validator.isNotNull(image) ? image.getType() : StringPool.BLANK;

		return type;
	}

	@Override
	public JSONObject getSites(long id, long groupId, ServiceContext serviceContext) {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		try {
			List<Group> listGroup = GroupLocalServiceUtil.getUserSitesGroups(id);

			List<Document> list = new ArrayList<>();

			for (Group group : listGroup) {

				Document document = new DocumentImpl();

				OfficeSite officeSite = OfficeSiteLocalServiceUtil.fetchF_groupId_siteGroupId(groupId,
						group.getGroupId());

				if (Validator.isNotNull(officeSite)) {

					document.addNumberSortable("entryClassPK", officeSite.getOfficeSiteId());
					document.addTextSortable("siteName", officeSite.getName());

				} else {

					document.addNumberSortable("entryClassPK", 0);
					document.addTextSortable("siteName", group.getName(locale));

				}

				document.addNumberSortable("siteGroupId", group.getGroupId());

				boolean isCurrent = false;

				if (group.getGroupId() == groupId) {

					isCurrent = true;

				}
				document.addTextSortable("currentSite", String.valueOf(isCurrent));

				list.add(document);

			}

			result.put("data", list);

			long total = listGroup.size();

			result.put("total", total);

		} catch (PortalException e) {
			e.printStackTrace();
		}

		return result;

	}

	@Override
	public JSONObject getRoles(long id, long groupId, ServiceContext serviceContext) {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		List<Role> listRole = RoleLocalServiceUtil.getUserRoles(id);

		List<Document> list = new ArrayList<>();

		for (Role role : listRole) {

			Document document = new DocumentImpl();

			document.addNumberSortable("entryClassPK", role.getRoleId());
			document.addTextSortable("roleName", role.getName());

			list.add(document);
		}

		result.put("data", list);

		long total = listRole.size();

		result.put("total", total);

		return result;
	}

	@Override
	public Document getUserProfile(long id, long groupId, ServiceContext serviceContext) {
		Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, id);
		// TODO
		// PARTER INFO
		Document document = new DocumentImpl();

		User user = UserLocalServiceUtil.fetchUser(employee.getMappingUserId());

		String screenName = StringPool.BLANK;
		String email = StringPool.BLANK;

		if (Validator.isNotNull(user)) {

			screenName = user.getScreenName();
			email = user.getEmailAddress();

		}

		document.addTextSortable("className", Employee.class.getName());
		document.addTextSortable("classPK", String.valueOf(employee.getEmployeeId()));

		document.addTextSortable("screenName", screenName);
		document.addTextSortable("email", email);
		document.addTextSortable("fullName", employee.getFullName());

		document.addTextSortable("contactEmail", employee.getEmail());
		document.addTextSortable("contactTelNo", employee.getTelNo());
		document.addNumberSortable("gender", employee.getGender());
		document.addDateSortable("birthdate", employee.getBirthdate());

		return document;

	}

	@Override
	public String getPreferenceByKey(long id, long groupId, String key, ServiceContext serviceContext) {
		Preferences preferences = PreferencesLocalServiceUtil.fetchByF_userId(groupId, id);
		String result = StringPool.BLANK;
		try {

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject(preferences.getPreferences());

			result = jsonObject.getString(key);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public String getPreference(long id, long groupId, ServiceContext serviceContext) {
		Preferences preferences = PreferencesLocalServiceUtil.fetchByF_userId(groupId, id);

		String result = Validator.isNotNull(preferences) ? preferences.getPreferences() : StringPool.BLANK;

		return result;
	}

	@Override
	public String addPreferences(long id, long groupId, String preferencesData, ServiceContext serviceContext)
			throws NoSuchUserException, UnauthenticationException, UnauthorizationException, DuplicateCategoryException,
			NotFoundException {

		Preferences preferences = PreferencesLocalServiceUtil.fetchByF_userId(groupId, id);

		if (Validator.isNull(preferences)) {
			preferences = PreferencesLocalServiceUtil.addPreferences(id, groupId, preferencesData, serviceContext);
		} else {
			preferences = PreferencesLocalServiceUtil.updatePreferences(id, preferences.getPreferencesId(),
					preferencesData, serviceContext);
		}

		return preferences.getPreferences();
	}

	@Override
	public String updatePreferences(long id, long groupId, String key, String value, ServiceContext serviceContext)
			throws NoSuchUserException, NotFoundException, UnauthenticationException, UnauthorizationException,
			DuplicateCategoryException {
		Preferences preferences = PreferencesLocalServiceUtil.fetchByF_userId(groupId, id);

		JSONObject jsonObject;
		try {
			jsonObject = JSONFactoryUtil.createJSONObject(preferences.getPreferences());

			jsonObject.put(key, value);

			preferences = PreferencesLocalServiceUtil.updatePreferences(id, preferences.getPreferencesId(),
					jsonObject.toJSONString(), serviceContext);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return preferences.getPreferences();
	}

	@Override
	public JSONObject getUsers(long groupId, ServiceContext serviceContext) {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		List<User> users = UserLocalServiceUtil.getGroupUsers(groupId);

		result.put("data", users);

		long total = users.size();

		result.put("total", total);

		return result;
	}

	@Override
	public User getUserById(long groupId, long companyId, long id, ServiceContext serviceContext) {
		return UserLocalServiceUtil.fetchUser(id);
	}

	@Override
	public Document getForgot(long groupId, long companyId, String screenname_email, ServiceContext serviceContext) {
		Document document = new DocumentImpl();

		User user = UserLocalServiceUtil.fetchUserByEmailAddress(companyId, screenname_email);

		if (Validator.isNull(user)) {
			user = UserLocalServiceUtil.fetchUserByScreenName(companyId, screenname_email);
		}

		long mappingUserId = Validator.isNotNull(user) ? user.getUserId() : 0;
		String userName = Validator.isNotNull(user) ? user.getFullName() : StringPool.BLANK;

		Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, mappingUserId);

		document.addTextSortable("userId", String.valueOf(mappingUserId));
		document.addTextSortable("userName", Validator.isNotNull(employee) ? employee.getFullName() : userName);
		document.addTextSortable("contactEmail",
				Validator.isNotNull(employee) ? employee.getEmail() : StringPool.BLANK);
		document.addTextSortable("contactTelNo",
				Validator.isNotNull(employee) ? employee.getTelNo() : StringPool.BLANK);

		// changePassWord
		String passWord = PwdGenerator.getPassword();

		try {

			user.setDigest(passWord);

			user = UserLocalServiceUtil.updateUser(user);

			JSONObject payLoad = JSONFactoryUtil.createJSONObject();

			payLoad.put("USERNAME", user.getScreenName());
			payLoad.put("USEREMAIL", user.getEmailAddress());
			payLoad.put("PASSWORD_CODE", passWord);

			NotificationQueueLocalServiceUtil.addNotificationQueue(user.getUserId(), groupId, Constants.USER_03,
					User.class.getName(), String.valueOf(user.getUserId()), payLoad.toJSONString(), "SYSTEM",
					employee.getFullName(), employee.getMappingUserId(), employee.getEmail(), employee.getTelNo(),
					new Date(), null, serviceContext);

		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return document;
	}

	@Override
	public Document getForgotConfirm(long groupId, long companyId, String screenname_email, String code,
			ServiceContext serviceContext) throws DigestException {
		Document document = new DocumentImpl();

		User user = UserLocalServiceUtil.fetchUserByEmailAddress(companyId, screenname_email);

		if (Validator.isNull(user)) {
			user = UserLocalServiceUtil.fetchUserByScreenName(companyId, screenname_email);
		}

		long mappingUserId = Validator.isNotNull(user) ? user.getUserId() : 0;
		String userName = Validator.isNotNull(user) ? user.getFullName() : StringPool.BLANK;

		// changePassWord
		String passWord = PwdGenerator.getPassword();

		try {

			if (user.getDigest().equals(code)) {
				user = UserLocalServiceUtil.updatePassword(user.getUserId(), passWord, passWord, Boolean.TRUE);

				user.setDigest(passWord + System.currentTimeMillis());

				UserLocalServiceUtil.updateUser(user);

			} else {

				throw new DigestException();

			}

			Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, mappingUserId);

			document.addTextSortable("userId", String.valueOf(mappingUserId));
			document.addTextSortable("userName", Validator.isNotNull(employee) ? employee.getFullName() : userName);
			document.addTextSortable("contactEmail",
					Validator.isNotNull(employee) ? employee.getEmail() : StringPool.BLANK);
			document.addTextSortable("contactTelNo",
					Validator.isNotNull(employee) ? employee.getTelNo() : StringPool.BLANK);

			JSONObject payLoad = JSONFactoryUtil.createJSONObject();

			payLoad.put("USERNAME", user.getScreenName());
			payLoad.put("USEREMAIL", user.getEmailAddress());
			payLoad.put("PASSWORD", passWord);

			NotificationQueueLocalServiceUtil.addNotificationQueue(user.getUserId(), groupId, Constants.USER_04,
					User.class.getName(), String.valueOf(user.getUserId()), payLoad.toJSONString(), "SYSTEM",
					employee.getFullName(), employee.getMappingUserId(), employee.getEmail(), employee.getTelNo(),
					new Date(), null, serviceContext);

		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return document;
	}

	@Override
	public boolean getCheckpass(long groupId, long companyId, long id, String password, ServiceContext serviceContext) {
		boolean flag = false;

		try {
			flag = PasswordTrackerLocalServiceUtil.isSameAsCurrentPassword(id, password);
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flag;
	}

	@Override
	public boolean addChangepass(long groupId, long companyId, long id, String oldPassword, String newPassword,
			ServiceContext serviceContext) {
		
		boolean flag = getCheckpass(groupId, companyId, id, oldPassword, serviceContext);

		if (flag) {
			try {

				User user = UserLocalServiceUtil.updatePassword(id, newPassword, newPassword, Boolean.FALSE);

				Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, id);

				JSONObject payLoad = JSONFactoryUtil.createJSONObject();

				payLoad.put("USERNAME", user.getScreenName());
				payLoad.put("USEREMAIL", user.getEmailAddress());
				payLoad.put("PASSWORD", newPassword);

				NotificationQueueLocalServiceUtil.addNotificationQueue(user.getUserId(), groupId, Constants.USER_04,
						User.class.getName(), String.valueOf(user.getUserId()), payLoad.toJSONString(), "SYSTEM",
						user.getFullName(), user.getUserId(), employee.getEmail(), StringPool.BLANK, new Date(), null,
						serviceContext);

			} catch (PortalException e) {
				flag = false;
			}
		}

		return flag;
	}
	
	@Override
	public boolean addChangepass(long groupId, long companyId, long id, String oldPassword, String newPassword, int type,
			ServiceContext serviceContext) {
			
		boolean flag = getCheckpass(groupId, companyId, id, oldPassword, serviceContext);

		if (flag) {
			try {

				User user = UserLocalServiceUtil.updatePassword(id, newPassword, newPassword, Boolean.FALSE);
				
				String email = StringPool.BLANK;
				
				if (type == 1) {
					//update application
					Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, id);
				
					email = employee.getEmail();
				} else {
					//update employee
					Applicant applicant = ApplicantLocalServiceUtil.fetchByMappingID(id);
				
					email = applicant.getContactEmail();
				}


				JSONObject payLoad = JSONFactoryUtil.createJSONObject();

				payLoad.put("USERNAME", user.getScreenName());
				payLoad.put("USEREMAIL", user.getEmailAddress());
				payLoad.put("PASSWORD", newPassword);

				NotificationQueueLocalServiceUtil.addNotificationQueue(user.getUserId(), groupId, Constants.USER_04,
						User.class.getName(), String.valueOf(user.getUserId()), payLoad.toJSONString(), "SYSTEM",
						user.getFullName(), user.getUserId(), email, StringPool.BLANK, new Date(), null,
						serviceContext);

			} catch (PortalException e) {
				flag = false;
			}
		}

		return flag;
	}


	@Override
	public File uploadEsign(long userId, long companyId, long groupId, long id, InputStream inputStream,
			String fileName, String fileType, long fileSize, String destination, String desc,
			ServiceContext serviceContext) throws Exception {

		File file = null;

		FileEntry fileEntry = FileUploadUtils.uploadFile(userId, companyId, groupId, inputStream, fileName, fileType,
				fileSize, destination, desc, serviceContext);

		User user = UserLocalServiceUtil.fetchUser(id);

		Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, user.getUserId());

		// if (Validator.isNotNull(l))

		file = DLFileEntryLocalServiceUtil.getFile(fileEntry.getFileEntryId(), fileEntry.getVersion(), false);

		if (Validator.isNotNull(employee)) {
			// update userPayload
			EmployeeLocalServiceUtil.updatePayload(user.getUserId(), groupId, 0, fileEntry.getFileEntryId(),
					StringPool.BLANK, file.getAbsolutePath(), serviceContext);
		}

		return file;
	}

	@Override
	public File uploadCert(long userId, long companyId, long groupId, long id, InputStream inputStream, String fileName,
			String fileType, long fileSize, String destination, String desc, ServiceContext serviceContext)
			throws Exception {

		File file = null;

		FileEntry fileEntry = FileUploadUtils.uploadFile(userId, companyId, groupId, inputStream, fileName, fileType,
				fileSize, destination, desc, serviceContext);

		User user = UserLocalServiceUtil.fetchUser(id);

		Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, user.getUserId());

		// if (Validator.isNotNull(l))

		file = DLFileEntryLocalServiceUtil.getFile(fileEntry.getFileEntryId(), fileEntry.getVersion(), false);

		if (Validator.isNotNull(employee)) {
			// update userPayload
			EmployeeLocalServiceUtil.updatePayload(userId, groupId, fileEntry.getFileEntryId(), 0,
					file.getAbsolutePath(), StringPool.BLANK, serviceContext);
		}

		return file;
	}

	@Override
	public String getEsignPath(long userId, Company company, long groupId, ServiceContext serviceContext)
			throws Exception {

		_log.info("FILE______GROUPID " + groupId);
		_log.info("FILE______USERID " + userId);

		Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, userId);

		String filePath = StringPool.BLANK;

		if (Validator.isNotNull(employee) && employee.getFileSignId() != 0) {
			String portalURL = PortalUtil.getPortalURL(company.getVirtualHostname(),
					PortalUtil.getPortalServerPort(false), false);

			DLFileEntry dlfileEntry = DLFileEntryLocalServiceUtil.getDLFileEntry(employee.getFileSignId());

			filePath = portalURL + "/c/document_library/get_file?uuid=" + dlfileEntry.getUuid() + "&groupId="
					+ dlfileEntry.getGroupId();

		}

		return filePath;
	}

	@Override
	public String getCertPath(long userId, long companyId, long groupId, ServiceContext serviceContext)
			throws Exception {

		Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, userId);

		String filePath = StringPool.BLANK;

		if (Validator.isNotNull(employee)) {
			filePath = employee.getFileCertPath();
		}

		return filePath;

	}

}
