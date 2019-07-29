
package org.opencps.usermgt.action.impl;

import com.liferay.asset.kernel.exception.DuplicateCategoryException;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.webserver.WebServerServletTokenUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.opencps.auth.api.keys.NotificationType;
import org.opencps.communication.model.NotificationQueue;
import org.opencps.communication.model.Notificationtemplate;
import org.opencps.communication.service.NotificationQueueLocalServiceUtil;
import org.opencps.communication.service.NotificationtemplateLocalServiceUtil;
import org.opencps.usermgt.action.UserInterface;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.model.EmployeeJobPos;
import org.opencps.usermgt.model.JobPos;
import org.opencps.usermgt.model.OfficeSite;
import org.opencps.usermgt.model.Preferences;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;
import org.opencps.usermgt.service.EmployeeJobPosLocalServiceUtil;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;
import org.opencps.usermgt.service.JobPosLocalServiceUtil;
import org.opencps.usermgt.service.OfficeSiteLocalServiceUtil;
import org.opencps.usermgt.service.PreferencesLocalServiceUtil;

import backend.auth.api.exception.NotFoundException;
import backend.auth.api.exception.UnauthenticationException;
import backend.auth.api.exception.UnauthorizationException;
import backend.auth.api.keys.Constants;
import backend.utils.FileUploadUtils;

public class UserActions implements UserInterface {

	public static final Locale locale = new Locale("vi", "VN");
	private static Log _log = LogFactoryUtil.getLog(UserActions.class);

	@Override
	public File getPhoto(long id, ServiceContext serviceContext) {

		User user = UserLocalServiceUtil.fetchUser(id);

		File file = null;
		Image image = ImageLocalServiceUtil.fetchImage(user.getPortraitId());

		try {
			file = FileUtil.createTempFile(image.getTextObj());
		}
		catch (IOException e) {
			_log.debug(e);
			// _log.error(e);
		}

		return file;
	}

	@Override
	public File uploadPhoto(
		long userId, long companyId, long groupId, long id,
		InputStream inputStream, String fileName, String fileType,
		long fileSize, String destination, String desc,
		ServiceContext serviceContext)
		throws Exception {

		File file = null;

		FileEntry fileEntry = FileUploadUtils.uploadFile(
			userId, companyId, groupId, inputStream, fileName, fileType,
			fileSize, destination, desc, serviceContext);

		User user = UserLocalServiceUtil.fetchUser(id);

		file = DLFileEntryLocalServiceUtil.getFile(
			fileEntry.getFileEntryId(), fileEntry.getVersion(), false);

		UserLocalServiceUtil.updatePortrait(
			user.getUserId(), FileUtil.getBytes(file));

		return file;
	}

	@Override
	public String getType(long id, ServiceContext serviceContext) {

		User user = UserLocalServiceUtil.fetchUser(id);

		Image image = ImageLocalServiceUtil.fetchImage(user.getPortraitId());

		String type =
			Validator.isNotNull(image) ? image.getType() : StringPool.BLANK;

		return type;
	}

	@Override
	public JSONObject getSites(
		long id, long groupId, ServiceContext serviceContext) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		try {
			List<Group> listGroup =
				GroupLocalServiceUtil.getUserSitesGroups(id);

			List<Document> list = new ArrayList<>();

			for (Group group : listGroup) {

				Document document = new DocumentImpl();

				OfficeSite officeSite =
					OfficeSiteLocalServiceUtil.fetchF_groupId_siteGroupId(
						groupId, group.getGroupId());

				if (Validator.isNotNull(officeSite)) {

					document.addNumberSortable(
						"entryClassPK", officeSite.getOfficeSiteId());
					document.addTextSortable("siteName", officeSite.getName());

				}
				else {

					document.addNumberSortable("entryClassPK", 0);
					document.addTextSortable("siteName", group.getName(locale));

				}

				document.addNumberSortable("siteGroupId", group.getGroupId());

				boolean isCurrent = false;

				if (group.getGroupId() == groupId) {

					isCurrent = true;

				}
				document.addTextSortable(
					"currentSite", String.valueOf(isCurrent));

				list.add(document);

			}

			result.put("data", list);

			long total = listGroup.size();

			result.put("total", total);

		}
		catch (PortalException e) {
			_log.debug(e);
			// _log.error(e);
		}

		return result;

	}

	@Override
	public JSONObject getRoles(
		long id, long groupId, ServiceContext serviceContext) {

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
	public Document getUserProfile(
		long id, long groupId, ServiceContext serviceContext) {

		Employee employee =
			EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, id);
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
		document.addTextSortable(
			"classPK", String.valueOf(employee.getEmployeeId()));

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
	public String getPreferenceByKey(
		long id, long groupId, String key, ServiceContext serviceContext) {

		Preferences preferences =
			PreferencesLocalServiceUtil.fetchByF_userId(groupId, id);
		String result = StringPool.BLANK;
		try {

			if (Validator.isNull(preferences)) {
				String preferencesData =
					"{\"" + key + "\":\"" + StringPool.BLANK + "\"}";
				preferences = PreferencesLocalServiceUtil.addPreferences(
					id, groupId, preferencesData, serviceContext);
			}
			else {

				JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
					preferences.getPreferences());

				result = jsonObject.getString(key);
			}

			_log.info(preferences);
		}
		catch (Exception e) {
			_log.error(e);
		}

		return result;
	}

	@Override
	public String getPreference(
		long id, long groupId, ServiceContext serviceContext) {

		Preferences preferences =
			PreferencesLocalServiceUtil.fetchByF_userId(groupId, id);

		String result = Validator.isNotNull(preferences)
			? preferences.getPreferences() : StringPool.BLANK;

		return result;
	}

	@Override
	public String addPreferences(
		long id, long groupId, String preferencesData,
		ServiceContext serviceContext) {

		try {
			Preferences preferences = PreferencesLocalServiceUtil.fetchByF_userId(groupId, id);
			if (Validator.isNull(preferences)) {
				preferences = PreferencesLocalServiceUtil.addPreferences(id, groupId, preferencesData, serviceContext);
			} else {
				preferences = PreferencesLocalServiceUtil.updatePreferences(id, preferences.getPreferencesId(),
						preferencesData, serviceContext);
			}
			return preferences.getPreferences();
		} catch (Exception e) {
			_log.error(e);
		}
		return null;

	}

	@Override
	public String updatePreferences(
		long id, long groupId, String key, String value,
		ServiceContext serviceContext)
		throws NoSuchUserException, NotFoundException,
		UnauthenticationException, UnauthorizationException,
		DuplicateCategoryException {

		Preferences preferences =
			PreferencesLocalServiceUtil.fetchByF_userId(groupId, id);

		String result = StringPool.BLANK;

		try {
			if (Validator.isNull(preferences)) {
				String preferencesData = "{\"" + key + "\":\"" + value + "\"}";
				preferences = PreferencesLocalServiceUtil.addPreferences(
					id, groupId, preferencesData, serviceContext);
			}
			else {
				JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
					preferences.getPreferences());

				jsonObject.put(key, value);

				preferences = PreferencesLocalServiceUtil.updatePreferences(
					id, preferences.getPreferencesId(),
					jsonObject.toJSONString(), serviceContext);
			}

			result = preferences.getPreferences();

			_log.info(
				id + " " + groupId + " " + key +
					" DETAIL updatePreferences IMPL " +
					preferences.getPreferences());
			_log.info(
				"========================updatePreferences>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			_log.info(
				"========================>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			_log.info(
				"========================>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

		}
		catch (JSONException e) {
			_log.info(
				id + " " + groupId + " " + key +
					" DETAIL updatePreferences IMPL err " +
					preferences.getPreferences());
			_log.info(
				"========================updatePreferences>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			_log.info(
				"========================>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			_log.info(
				"========================>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			_log.error(e);
			_log.debug(e);
			// _log.error(e);
		}

		return result;
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
	public User getUserById(
		long groupId, long companyId, long id, ServiceContext serviceContext) {

		return UserLocalServiceUtil.fetchUser(id);
	}

	@Override
	public Document getForgot(
		long groupId, long companyId, String screenname_email,
		ServiceContext serviceContext) {

		Document document = new DocumentImpl();

		User user = UserLocalServiceUtil.fetchUserByEmailAddress(
			companyId, screenname_email);

		if (Validator.isNull(user)) {
			user = UserLocalServiceUtil.fetchUserByScreenName(
				companyId, screenname_email);
		}

		long mappingUserId = Validator.isNotNull(user) ? user.getUserId() : 0;
		String userName =
			Validator.isNotNull(user) ? user.getFullName() : StringPool.BLANK;

		// Employee employee =
		// EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId,
		// mappingUserId);
		Employee employee =
			EmployeeLocalServiceUtil.fetchByFB_MUID(mappingUserId);
		Applicant applicant = null;
		if (employee != null) {
			document.addTextSortable("userId", String.valueOf(mappingUserId));
			document.addTextSortable(
				"userName", Validator.isNotNull(employee)
					? employee.getFullName() : userName);
			document.addTextSortable(
				"contactEmail", Validator.isNotNull(employee)
					? employee.getEmail() : StringPool.BLANK);
			document.addTextSortable(
				"contactTelNo", Validator.isNotNull(employee)
					? employee.getTelNo() : StringPool.BLANK);
		}
		else {
			applicant =
				ApplicantLocalServiceUtil.fetchByMappingID(mappingUserId);
			if (applicant != null) {
				document.addTextSortable(
					"userId", String.valueOf(mappingUserId));
				document.addTextSortable(
					"userName", Validator.isNotNull(applicant)
						? applicant.getContactName() : userName);
				document.addTextSortable(
					"contactEmail", Validator.isNotNull(applicant)
						? applicant.getContactEmail() : StringPool.BLANK);
				document.addTextSortable(
					"contactTelNo", Validator.isNotNull(applicant)
						? applicant.getContactTelNo() : StringPool.BLANK);
			}
		}

		// changePassWord
		String secretKey = PwdGenerator.getPassword();
		// _log.info("secretKey:"+secretKey);

		try {

			user.setDigest(secretKey);
			user = UserLocalServiceUtil.updateUser(user);
			// _log.info("secretKey1:"+user.getDigest());

			// _log.info("STRART SEND CHANGE PASS!");
			long notificationQueueId = CounterLocalServiceUtil.increment(
				NotificationQueue.class.getName());
			NotificationQueue queue =
				NotificationQueueLocalServiceUtil.createNotificationQueue(
					notificationQueueId);

			Date now = new Date();
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) + 1);

			queue.setCreateDate(now);
			queue.setModifiedDate(now);
			queue.setGroupId(groupId);
			queue.setCompanyId(user.getCompanyId());

			queue.setNotificationType(NotificationType.USER_05);
			queue.setClassName(User.class.getName());
			if (employee != null) {
				queue.setClassPK(String.valueOf(employee.getPrimaryKey()));
				queue.setToUsername(employee.getFullName());
				queue.setToUserId(employee.getMappingUserId());
				queue.setToEmail(employee.getEmail());
				queue.setToTelNo(employee.getTelNo());
			}
			else if (applicant != null) {
				queue.setClassPK(String.valueOf(applicant.getPrimaryKey()));
				queue.setToUsername(applicant.getApplicantName());
				queue.setToUserId(applicant.getUserId());
				queue.setToEmail(applicant.getContactEmail());
				queue.setToTelNo(applicant.getContactTelNo());
			}

			JSONObject payload = JSONFactoryUtil.createJSONObject();
			// _log.info("START PAYLOAD: ");
			JSONObject subPayload = JSONFactoryUtil.createJSONObject();
			if (employee != null) {
				subPayload.put("userName", employee.getFullName());
				subPayload.put("userId", employee.getMappingUserId());
				subPayload.put("email", employee.getEmail());
				subPayload.put("telNo", employee.getTelNo());
			}
			else if (applicant != null) {
				subPayload.put("userName", applicant.getApplicantName());
				subPayload.put("userId", applicant.getUserId());
				subPayload.put("email", applicant.getContactEmail());
				subPayload.put("telNo", applicant.getContactTelNo());
			}
			subPayload.put("secretKey", secretKey);
			payload.put("User", subPayload);

			// _log.info("payloadTest: "+payload.toJSONString());
			queue.setPayload(payload.toJSONString());
			queue.setExpireDate(cal.getTime());

			NotificationQueueLocalServiceUtil.addNotificationQueue(queue);

		}
		catch (Exception e) {
			_log.error(e);
		}

		return document;
	}

	@Override
	public Document getForgotConfirm(
		long groupId, long companyId, String screenname_email, String code,
		ServiceContext serviceContext)
		throws DigestException {

		Document document = new DocumentImpl();

		User user = UserLocalServiceUtil.fetchUserByEmailAddress(
			companyId, screenname_email);

		if (Validator.isNull(user)) {
			user = UserLocalServiceUtil.fetchUserByScreenName(
				companyId, screenname_email);
		}

		long mappingUserId = Validator.isNotNull(user) ? user.getUserId() : 0;
		String userName =
			Validator.isNotNull(user) ? user.getFullName() : StringPool.BLANK;

		// changePassWord
		String secretKey1 = PwdGenerator.getPassword(2, new String[] {
			"0123456789"
		});
		String secretKey2 = PwdGenerator.getPassword(2, new String[] {
			"ABCDEFGHIJKLMNOPQRSTUVWXYZ"
		});
		String secretKey3 = PwdGenerator.getPassword(2, new String[] {
			"abcdefghijklmnopqrstuvwxyz"
		});
		String secretKey4 = PwdGenerator.getPassword(1, new String[] {
			"@$"
		});
		String secretKey5 = PwdGenerator.getPassword(4, new String[] {
			"0123456789", "ABCDEFGHIJKLMNOPQRSTUVWXYZ",
			"abcdefghijklmnopqrstuvwxyz", "~!@#$%^&*"
		});
		String secretKey =
			secretKey1 + secretKey2 + secretKey3 + secretKey4 + secretKey5;

		// _log.info("secretKey:"+secretKey);

		try {

			if (user.getDigest().equals(code)) {
				user = UserLocalServiceUtil.updatePassword(
					user.getUserId(), secretKey, secretKey, Boolean.TRUE);
				user.setDigest(secretKey + System.currentTimeMillis());
				user.setPasswordReset(false);
				UserLocalServiceUtil.updateUser(user);
			}
			else {
				throw new DigestException();
			}

			Employee employee =
				EmployeeLocalServiceUtil.fetchByFB_MUID(mappingUserId);
			Applicant applicant = null;
			if (employee != null) {
				document.addTextSortable(
					"userId", String.valueOf(mappingUserId));
				document.addTextSortable(
					"userName", Validator.isNotNull(employee)
						? employee.getFullName() : userName);
				document.addTextSortable(
					"contactEmail", Validator.isNotNull(employee)
						? employee.getEmail() : StringPool.BLANK);
				document.addTextSortable(
					"contactTelNo", Validator.isNotNull(employee)
						? employee.getTelNo() : StringPool.BLANK);
			}
			else {
				applicant =
					ApplicantLocalServiceUtil.fetchByMappingID(mappingUserId);
				if (applicant != null) {
					document.addTextSortable(
						"userId", String.valueOf(mappingUserId));
					document.addTextSortable(
						"userName", Validator.isNotNull(applicant)
							? applicant.getContactName() : userName);
					document.addTextSortable(
						"contactEmail", Validator.isNotNull(applicant)
							? applicant.getContactEmail() : StringPool.BLANK);
					document.addTextSortable(
						"contactTelNo", Validator.isNotNull(applicant)
							? applicant.getContactTelNo() : StringPool.BLANK);
				}
			}

			// _log.info("STRART SEND CHANGE PASS!");
			long notificationQueueId = CounterLocalServiceUtil.increment(
				NotificationQueue.class.getName());
			NotificationQueue queue =
				NotificationQueueLocalServiceUtil.createNotificationQueue(
					notificationQueueId);

			Date now = new Date();
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) + 1);

			queue.setCreateDate(now);
			queue.setModifiedDate(now);
			queue.setGroupId(groupId);
			queue.setCompanyId(user.getCompanyId());

			queue.setNotificationType(NotificationType.USER_03);
			queue.setClassName(User.class.getName());
			if (employee != null) {
				queue.setClassPK(String.valueOf(employee.getPrimaryKey()));
				queue.setToUsername(employee.getFullName());
				queue.setToUserId(employee.getMappingUserId());
				queue.setToEmail(employee.getEmail());
				queue.setToTelNo(employee.getTelNo());
			}
			else if (applicant != null) {
				queue.setClassPK(String.valueOf(applicant.getPrimaryKey()));
				queue.setToUsername(applicant.getApplicantName());
				queue.setToUserId(applicant.getUserId());
				queue.setToEmail(applicant.getContactEmail());
				queue.setToTelNo(applicant.getContactTelNo());
			}

			JSONObject payload = JSONFactoryUtil.createJSONObject();
			// _log.info("START PAYLOAD: ");
			JSONObject subPayload = JSONFactoryUtil.createJSONObject();
			if (employee != null) {
				subPayload.put("userName", employee.getFullName());
				subPayload.put("userId", employee.getMappingUserId());
				subPayload.put("email", employee.getEmail());
				subPayload.put("telNo", employee.getTelNo());
			}
			else if (applicant != null) {
				subPayload.put("userName", applicant.getApplicantName());
				subPayload.put("userId", applicant.getUserId());
				subPayload.put("email", applicant.getContactEmail());
				subPayload.put("telNo", applicant.getContactTelNo());
			}
			subPayload.put("secretKey", secretKey);
			payload.put("User", subPayload);

			// _log.info("payloadTest: "+payload.toJSONString());
			queue.setPayload(payload.toJSONString());
			queue.setExpireDate(cal.getTime());

			NotificationQueueLocalServiceUtil.addNotificationQueue(queue);
		}
		catch (PortalException e) {
			_log.error(e);
		}
		return document;
	}

	@Override
	public boolean getCheckpass(
		long groupId, long companyId, long id, String password,
		ServiceContext serviceContext) {

		boolean flag = false;

		try {
			flag = PasswordTrackerLocalServiceUtil.isSameAsCurrentPassword(
				id, password);
		}
		catch (PortalException e) {
			_log.error(e);
		}

		return flag;
	}

	@Override
	public int addChangepass(
		long groupId, long companyId, long id, String oldPassword,
		String newPassword, ServiceContext serviceContext) {

		int flagNo = 0;
		boolean flag =
			getCheckpass(groupId, companyId, id, oldPassword, serviceContext);
		// _log.info("flag: "+flag);
		String phone = StringPool.BLANK;
		
		if (flag) {
			try {

				User user = UserLocalServiceUtil.updatePassword(
					id, newPassword, newPassword, Boolean.FALSE);
				// _log.info("user: "+user);
				if (user != null) {
					String email = StringPool.BLANK;
					// update employee
					Employee employee =
						EmployeeLocalServiceUtil.fetchByF_mappingUserId(
							groupId, id);
					if (employee != null) {
						email = employee.getEmail();
						// _log.info("emailEmployee: "+email);
					}
					else {
						// update application
						Applicant applicant =
							ApplicantLocalServiceUtil.fetchByMappingID(id);
						if (applicant != null) {
							email = applicant.getContactEmail();
							phone = applicant.getContactTelNo();
							// _log.info("emailApplicant: "+email);
						}
					}

					Date now = new Date();
			        Calendar cal = Calendar.getInstance();
			        cal.setTime(now);
			        
			        Notificationtemplate notiTemplate = NotificationtemplateLocalServiceUtil.fetchByF_NotificationtemplateByType(groupId, Constants.USER_04);
			        cal.add(Calendar.MINUTE, 10);
					
			        if (notiTemplate != null) {
						if ("minutely".equals(notiTemplate.getInterval())) {
							cal.add(Calendar.MINUTE, notiTemplate.getExpireDuration());
						} else if ("hourly".equals(notiTemplate.getInterval())) {
							cal.add(Calendar.HOUR, notiTemplate.getExpireDuration());
						} else {
							cal.add(Calendar.MINUTE, notiTemplate.getExpireDuration());
						}
					}
					
					Date expired = cal.getTime();

					JSONObject payLoad = JSONFactoryUtil.createJSONObject();
					// _log.info("user.getScreenName():
					// "+user.getScreenName()+"|user.getEmailAddress():
					// "+user.getEmailAddress());
					payLoad.put("USERNAME", user.getScreenName());
					payLoad.put("USEREMAIL", user.getEmailAddress());
					payLoad.put("PASSWORD", newPassword);
					// _log.info("STRAT addNotificationQueue: ");
					NotificationQueueLocalServiceUtil.addNotificationQueue(
						user.getUserId(), groupId, Constants.USER_04,
						User.class.getName(), String.valueOf(user.getUserId()),
						payLoad.toJSONString(), "SYSTEM", user.getFullName(),
						user.getUserId(), email, phone, new Date(),
						expired, serviceContext);
					// _log.info("END addNotificationQueue: ");
					flagNo = 2;
				}
				else {
					// _log.info("END 1111: ");
					flagNo = 1;
				}
			}
			catch (PortalException e) {
				_log.debug("END 22222: " + e);
				flagNo = 1;
			}
		}

		return flagNo;
	}

	@Override
	public boolean addChangepass(
		long groupId, long companyId, long id, String oldPassword,
		String newPassword, int type, ServiceContext serviceContext) {

		boolean flag =
			getCheckpass(groupId, companyId, id, oldPassword, serviceContext);
		// _log.info("flag: "+flag);
		String phone = StringPool.BLANK;
		
		if (flag) {
			try {

				User user = UserLocalServiceUtil.updatePassword(
					id, newPassword, newPassword, Boolean.FALSE);
				// _log.info("User: "+user);
				String email = StringPool.BLANK;

				if (type == 1) {
					// update application
					Employee employee =
						EmployeeLocalServiceUtil.fetchByF_mappingUserId(
							groupId, id);

					email += employee.getEmail();
				}
				else {
					// update employee
					Applicant applicant =
						ApplicantLocalServiceUtil.fetchByMappingID(id);

					email += applicant.getContactEmail();
					phone = applicant.getContactTelNo();
					// _log.info("email: "+email);
				}

				JSONObject payLoad = JSONFactoryUtil.createJSONObject();

				payLoad.put("USERNAME", user.getScreenName());
				payLoad.put("USEREMAIL", user.getEmailAddress());
				payLoad.put("PASSWORD", newPassword);

				Date now = new Date();
		        Calendar cal = Calendar.getInstance();
		        cal.setTime(now);
		        
		        Notificationtemplate notiTemplate = NotificationtemplateLocalServiceUtil.fetchByF_NotificationtemplateByType(groupId, Constants.USER_04);
		        cal.add(Calendar.MINUTE, 10);
				
		        if (notiTemplate != null) {
					if ("minutely".equals(notiTemplate.getInterval())) {
						cal.add(Calendar.MINUTE, notiTemplate.getExpireDuration());
					} else if ("hourly".equals(notiTemplate.getInterval())) {
						cal.add(Calendar.HOUR, notiTemplate.getExpireDuration());
					} else {
						cal.add(Calendar.MINUTE, notiTemplate.getExpireDuration());
					}
				}
				
				Date expired = cal.getTime();

				// _log.info("STRAT addNotificationQueue: ");
				NotificationQueueLocalServiceUtil.addNotificationQueue(
					user.getUserId(), groupId, Constants.USER_04,
					User.class.getName(), String.valueOf(user.getUserId()),
					payLoad.toJSONString(), "SYSTEM", user.getFullName(),
					user.getUserId(), email, phone, new Date(), expired,
					serviceContext);
				// _log.info("END addNotificationQueue: ");

			}
			catch (PortalException e) {
				flag = false;
				_log.debug("BUGGGG: " + e);
			}
		}

		return flag;
	}

	@Override
	public File uploadEsign(
		long userId, long companyId, long groupId, long id,
		InputStream inputStream, String fileName, String fileType,
		long fileSize, String destination, String desc,
		ServiceContext serviceContext)
		throws Exception {

		File file = null;

		FileEntry fileEntry = FileUploadUtils.uploadFile(
			userId, companyId, groupId, inputStream, fileName, fileType,
			fileSize, destination, desc, serviceContext);

		User user = UserLocalServiceUtil.fetchUser(id);

		Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(
			groupId, user.getUserId());

		// if (Validator.isNotNull(l))

		file = DLFileEntryLocalServiceUtil.getFile(
			fileEntry.getFileEntryId(), fileEntry.getVersion(), false);

		if (Validator.isNotNull(employee)) {
			// update userPayload
			EmployeeLocalServiceUtil.updatePayload(
				user.getUserId(), groupId, 0, fileEntry.getFileEntryId(),
				StringPool.BLANK, file.getAbsolutePath(), serviceContext);
		}

		return file;
	}

	@Override
	public File uploadCert(
		long userId, long companyId, long groupId, long id,
		InputStream inputStream, String fileName, String fileType,
		long fileSize, String destination, String desc,
		ServiceContext serviceContext)
		throws Exception {

		File file = null;

		FileEntry fileEntry = FileUploadUtils.uploadFile(
			userId, companyId, groupId, inputStream, fileName, fileType,
			fileSize, destination, desc, serviceContext);

		User user = UserLocalServiceUtil.fetchUser(id);

		Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(
			groupId, user.getUserId());

		// if (Validator.isNotNull(l))

		file = DLFileEntryLocalServiceUtil.getFile(
			fileEntry.getFileEntryId(), fileEntry.getVersion(), false);

		if (Validator.isNotNull(employee)) {
			// update userPayload
			EmployeeLocalServiceUtil.updatePayload(
				userId, groupId, fileEntry.getFileEntryId(), 0,
				file.getAbsolutePath(), StringPool.BLANK, serviceContext);
		}

		return file;
	}

	@Override
	public String getEsignPath(
		long userId, Company company, long groupId,
		ServiceContext serviceContext)
		throws Exception {

		// _log.info("FILE______GROUPID " + groupId);
		// _log.info("FILE______USERID " + userId);

		Employee employee =
			EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, userId);

		String filePath = StringPool.BLANK;

		if (Validator.isNotNull(employee) && employee.getFileSignId() != 0) {
			String portalURL = PortalUtil.getPortalURL(
				company.getVirtualHostname(),
				PortalUtil.getPortalServerPort(false), false);

			DLFileEntry dlfileEntry =
				DLFileEntryLocalServiceUtil.getDLFileEntry(
					employee.getFileSignId());

			filePath = portalURL + "/c/document_library/get_file?uuid=" +
				dlfileEntry.getUuid() + "&groupId=" + dlfileEntry.getGroupId();

		}

		return filePath;
	}

	@Override
	public String getCertPath(
		long userId, long companyId, long groupId,
		ServiceContext serviceContext)
		throws Exception {

		Employee employee =
			EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, userId);

		String filePath = StringPool.BLANK;

		if (Validator.isNotNull(employee)) {
			filePath = employee.getFileCertPath();
		}

		return filePath;

	}

	@Override
	public String getUserById(long userId) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		User user = UserLocalServiceUtil.fetchUser(userId);

		if (Validator.isNotNull(user)) {
			Employee employee = EmployeeLocalServiceUtil.fetchByFB_MUID(userId);

			result.put("className", User.class.getName());
			result.put("classPK", user.getUserId());
			result.put("userId", user.getUserId());
			result.put("userName", user.getFullName());
			result.put("mappingUserId", user.getUserId());
			result.put("screenName", user.getScreenName());

			result.put("employeeGender", 0);
			result.put("employeeWorkingStatus", 0);
			result.put("employeeMainJobPostId", 0);
			result.put("employeePhotoFileEntryId", 0);
			result.put("employeeFileCerId", 0);
			result.put("employeeFileSignId", 0);
			result.put("applicantLock", 0);

			String avatar = StringPool.BLANK;

			long portraitId = user.getPortraitId();
			String tokenId =
				WebServerServletTokenUtil.getToken(user.getPortraitId());
			try {
				avatar = "/image/user_" +
					((user != null) && user.isFemale() ? "female" : "male") +
					"_portrait?img_id=" + portraitId + "&t=" + tokenId;
			}
			catch (PortalException e) {
				_log.error(e);
			}

			result.put("avatar", avatar);

			if (Validator.isNotNull(employee)) {

				result.put("className", Employee.class.getName());
				result.put("classPK", employee.getEmployeeId());

				result.put("employeeFullName", employee.getFullName());
				result.put("employeeNo", employee.getEmployeeNo());
				result.put("employeeGender", employee.getGender());
				if (Validator.isNotNull(employee.getBirthdate())) {
					result.put(
						"employeeBirthDate", employee.getBirthdate().getTime());
				}
				result.put("employeeTelNo", employee.getTelNo());
				result.put("employeeMobile", employee.getMobile());
				result.put("employeeEmail", employee.getEmail());
				result.put(
					"employeeWorkingStatus", employee.getWorkingStatus());
				result.put(
					"employeePhotoFileEntryId", employee.getPhotoFileEntryId());
				result.put("employeeFileCerId", employee.getFileCertId());
				result.put("employeeFileSignId", employee.getFileSignId());

				result.put("userId", employee.getMappingUserId());
				result.put("userName", employee.getFullName());
				result.put("mappingUserId", employee.getMappingUserId());

				result.put(
					"employeeMainJobPostId", employee.getMainJobPostId());
				result.put("employeeMainJobPostName", StringPool.BLANK);
				result.put("title", employee.getTitle());

				EmployeeJobPos employeeJobPos =
					EmployeeJobPosLocalServiceUtil.fetchEmployeeJobPos(
						employee.getMainJobPostId());

				if (employeeJobPos != null) {
					JobPos jobPos = JobPosLocalServiceUtil.fetchJobPos(
						employeeJobPos.getJobPostId());
					if (jobPos != null) {
						Role role = RoleLocalServiceUtil.fetchRole(
								jobPos.getMappingRoleId());
						result.put(
							"employeeMainJobPostName", role.getTitleCurrentValue());
					}
				}

			}
			else {

				Applicant applicant =
					ApplicantLocalServiceUtil.fetchByMappingID(userId);

				if (Validator.isNotNull(applicant)) {

					result.put("className", Applicant.class.getName());
					result.put("classPK", applicant.getApplicantId());

					result.put("applicantName", applicant.getApplicantName());
					result.put("applicantType", applicant.getApplicantIdType());
					result.put("applicantIdNo", applicant.getApplicantIdNo());
					result.put(
						"applicantIdDate", applicant.getApplicantIdDate());
					result.put("applicantAddress", applicant.getAddress());
					result.put("applicantCityCode", applicant.getCityCode());
					result.put("applicantCityName", applicant.getCityName());
					result.put(
						"applicantDistrictCode", applicant.getDistrictCode());
					result.put(
						"applicantDistrictName", applicant.getDistrictName());
					result.put("applicantWardCode", applicant.getWardCode());
					result.put("applicantWardName", applicant.getWardName());
					result.put(
						"applicantContactName", applicant.getContactName());
					result.put(
						"applicantContactTelNo", applicant.getContactTelNo());
					result.put(
						"applicantContactEmail", applicant.getContactEmail());
					result.put(
						"applicantActivationCode",
						applicant.getActivationCode());
					result.put("applicantLock", applicant.getLock_());
					result.put("applicantTmpPass", applicant.getTmpPass());

					result.put("userId", applicant.getMappingUserId());
					result.put("userName", applicant.getApplicantName());
					result.put("mappingUserId", applicant.getMappingUserId());

				}

			}

			return result.toJSONString();
		}
		else {
			return null;
		}

	}

}
