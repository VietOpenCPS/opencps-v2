
package org.opencps.usermgt.action.impl;

import com.liferay.asset.kernel.exception.DuplicateCategoryException;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
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
import com.liferay.portal.kernel.model.UserTracker;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ImageLocalServiceUtil;
import com.liferay.portal.kernel.service.PasswordTrackerLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PwdGenerator;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.webserver.WebServerServletTokenUtil;
import com.liferay.portal.liveusers.LiveUsers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.opencps.auth.api.keys.NotificationType;
import org.opencps.backend.usermgt.service.util.ConfigConstants;
import org.opencps.backend.usermgt.service.util.ConfigProps;
import org.opencps.communication.model.NotificationQueue;
import org.opencps.communication.model.Notificationtemplate;
import org.opencps.communication.service.NotificationQueueLocalServiceUtil;
import org.opencps.communication.service.NotificationtemplateLocalServiceUtil;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.opencps.usermgt.action.UserInterface;
import org.opencps.usermgt.constants.ApplicantTerm;
import org.opencps.usermgt.constants.CommonTerm;
import org.opencps.usermgt.constants.OfficeSiteTerm;
import org.opencps.usermgt.constants.UserMGTConstants;
import org.opencps.usermgt.constants.UserTerm;
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
import org.opencps.usermgt.service.util.ConstantUtils;
import org.opencps.usermgt.utils.DateTimeUtils;

import backend.auth.api.exception.NotFoundException;
import backend.auth.api.exception.UnauthenticationException;
import backend.auth.api.exception.UnauthorizationException;
import backend.auth.api.keys.Constants;
import backend.utils.FileUploadUtils;

public class UserActions implements UserInterface {

	public static final Locale locale = new Locale(ConfigProps.get(ConfigConstants.EMP_ACTION_LOCALE), ConfigProps.get(ConfigConstants.EMP_ACTION_LOCALE_UPP));
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
							Field.ENTRY_CLASS_PK, officeSite.getOfficeSiteId());
					document.addTextSortable(UserMGTConstants.SITE_NAME, officeSite.getName());

				}
				else {

					document.addNumberSortable(Field.ENTRY_CLASS_PK, 0);
					document.addTextSortable(UserMGTConstants.SITE_NAME, group.getName(locale));

				}

				document.addNumberSortable(UserMGTConstants.SITE_GROUP_ID, group.getGroupId());

				boolean isCurrent = false;

				if (group.getGroupId() == groupId) {

					isCurrent = true;

				}
				document.addTextSortable(
					OfficeSiteTerm.CURRENT_SITE, String.valueOf(isCurrent));

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

			document.addNumberSortable(Field.ENTRY_CLASS_PK, role.getRoleId());
			document.addTextSortable(CommonTerm.ROLE_NAME, role.getName());

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

		document.addTextSortable(UserTerm.CLASS_NAME, Employee.class.getName());
		document.addTextSortable(
			UserTerm.CLASS_PK, String.valueOf(employee.getEmployeeId()));

		document.addTextSortable(UserTerm.SCREEN_NAME, screenName);
		document.addTextSortable(UserTerm.EMAIL, email);
		document.addTextSortable(UserTerm.FULL_NAME, employee.getFullName());

		document.addTextSortable(UserTerm.CONTACT_EMAIL, employee.getEmail());
		document.addTextSortable(UserTerm.CONTACT_TELNO, employee.getTelNo());
		document.addNumberSortable(UserTerm.GENDER, employee.getGender());
		document.addDateSortable(UserTerm.BIRTHDATE, employee.getBirthdate());

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

		result.put(ApplicantTerm.DATA, users);

		long total = users.size();

		result.put(ApplicantTerm.TOTAL, total);

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
			document.addTextSortable(UserTerm.USER_ID, String.valueOf(mappingUserId));
			document.addTextSortable(
				UserTerm.USER_NAME, Validator.isNotNull(employee)
					? employee.getFullName() : userName);
			document.addTextSortable(
				UserTerm.CONTACT_EMAIL, Validator.isNotNull(employee)
					? employee.getEmail() : StringPool.BLANK);
			document.addTextSortable(
				UserTerm.CONTACT_TELNO, Validator.isNotNull(employee)
					? employee.getTelNo() : StringPool.BLANK);
		}
		else {
			applicant = mappingUserId > 0 ? 
				ApplicantLocalServiceUtil.fetchByMappingID(mappingUserId) : null;
			if (applicant != null) {
				document.addTextSortable(
					UserTerm.USER_ID, String.valueOf(mappingUserId));
				document.addTextSortable(
					UserTerm.USER_NAME, Validator.isNotNull(applicant)
						? applicant.getContactName() : userName);
				document.addTextSortable(
					UserTerm.CONTACT_EMAIL, Validator.isNotNull(applicant)
						? applicant.getContactEmail() : StringPool.BLANK);
				document.addTextSortable(
					UserTerm.CONTACT_TELNO, Validator.isNotNull(applicant)
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
			queue.setPriority(ConfigConstants.PRIORITY_FORGOT_PASS);

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
				subPayload.put(UserTerm.USER_NAME, employee.getFullName());
				subPayload.put(UserTerm.USER_ID, employee.getMappingUserId());
				subPayload.put(UserTerm.EMAIL, employee.getEmail());
				subPayload.put(UserTerm.TELNO, employee.getTelNo());
			}
			else if (applicant != null) {
				subPayload.put(UserTerm.USER_NAME, applicant.getApplicantName());
				subPayload.put(UserTerm.USER_ID, applicant.getUserId());
				subPayload.put(UserTerm.EMAIL, applicant.getContactEmail());
				subPayload.put(UserTerm.TELNO, applicant.getContactTelNo());
			}
			subPayload.put(UserTerm.SECRET_KEY, secretKey);
			payload.put(UserTerm.USER, subPayload);

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
			ConfigProps.get(ConfigConstants.EMP_ACTION_SECRET_GEN_1)
		});
		String secretKey2 = PwdGenerator.getPassword(2, new String[] {
			ConfigProps.get(ConfigConstants.EMP_ACTION_SECRET_GEN_2)
		});
		String secretKey3 = PwdGenerator.getPassword(2, new String[] {
			ConfigProps.get(ConfigConstants.EMP_ACTION_SECRET_GEN_3)
		});
		String secretKey4 = PwdGenerator.getPassword(1, new String[] {
			ConfigProps.get(ConfigConstants.EMP_ACTION_SECRET_GEN_4)
		});
		String secretKey5 = PwdGenerator.getPassword(4, new String[] {
			ConfigProps.get(ConfigConstants.EMP_ACTION_SECRET_GEN_5_1), ConfigProps.get(ConfigConstants.EMP_ACTION_SECRET_GEN_5_2),
			ConfigProps.get(ConfigConstants.EMP_ACTION_SECRET_GEN_5_3), ConfigProps.get(ConfigConstants.EMP_ACTION_SECRET_GEN_5_4)
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
					UserTerm.USER_ID, String.valueOf(mappingUserId));
				document.addTextSortable(
					UserTerm.USER_NAME, Validator.isNotNull(employee)
						? employee.getFullName() : userName);
				document.addTextSortable(
					UserTerm.CONTACT_EMAIL, Validator.isNotNull(employee)
						? employee.getEmail() : StringPool.BLANK);
				document.addTextSortable(
					UserTerm.CONTACT_TELNO, Validator.isNotNull(employee)
						? employee.getTelNo() : StringPool.BLANK);
			}
			else {
				applicant = mappingUserId > 0 ? 
					ApplicantLocalServiceUtil.fetchByMappingID(mappingUserId) : null;
				if (applicant != null) {
					document.addTextSortable(
						UserTerm.USER_ID, String.valueOf(mappingUserId));
					document.addTextSortable(
						UserTerm.USER_NAME, Validator.isNotNull(applicant)
							? applicant.getContactName() : userName);
					document.addTextSortable(
						UserTerm.CONTACT_EMAIL, Validator.isNotNull(applicant)
							? applicant.getContactEmail() : StringPool.BLANK);
					document.addTextSortable(
						UserTerm.CONTACT_TELNO, Validator.isNotNull(applicant)
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
			queue.setPriority(ConfigConstants.PRIORITY_FORGOT_PASS);

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
				subPayload.put(UserTerm.USER_NAME, employee.getFullName());
				subPayload.put(UserTerm.USER_ID, employee.getMappingUserId());
				subPayload.put(UserTerm.EMAIL, employee.getEmail());
				subPayload.put(UserTerm.TELNO, employee.getTelNo());
			}
			else if (applicant != null) {
				subPayload.put(UserTerm.USER_NAME, applicant.getApplicantName());
				subPayload.put(UserTerm.USER_ID, applicant.getUserId());
				subPayload.put(UserTerm.EMAIL, applicant.getContactEmail());
				subPayload.put(UserTerm.TELNO, applicant.getContactTelNo());
			}
			subPayload.put(UserTerm.SECRET_KEY, secretKey);
			payload.put(UserTerm.USER, subPayload);

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
	public Document getLGSPForgotConfirm(long groupId, long companyId, String screenname_email, String code,
			String secretKey, ServiceContext serviceContext) throws DigestException {

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
					UserTerm.USER_ID, String.valueOf(mappingUserId));
				document.addTextSortable(
					UserTerm.USER_NAME, Validator.isNotNull(employee)
						? employee.getFullName() : userName);
				document.addTextSortable(
					UserTerm.CONTACT_EMAIL, Validator.isNotNull(employee)
						? employee.getEmail() : StringPool.BLANK);
				document.addTextSortable(
					UserTerm.CONTACT_TELNO, Validator.isNotNull(employee)
						? employee.getTelNo() : StringPool.BLANK);
			}
			else {
				applicant = mappingUserId > 0 ? 
					ApplicantLocalServiceUtil.fetchByMappingID(mappingUserId) : null;
				if (applicant != null) {
					document.addTextSortable(
						UserTerm.USER_ID, String.valueOf(mappingUserId));
					document.addTextSortable(
						UserTerm.USER_NAME, Validator.isNotNull(applicant)
							? applicant.getContactName() : userName);
					document.addTextSortable(
						UserTerm.CONTACT_EMAIL, Validator.isNotNull(applicant)
							? applicant.getContactEmail() : StringPool.BLANK);
					document.addTextSortable(
						UserTerm.CONTACT_TELNO, Validator.isNotNull(applicant)
							? applicant.getContactTelNo() : StringPool.BLANK);
					//Update applicant
					applicant.setTmpPass(secretKey);
					ApplicantLocalServiceUtil.updateApplicant(applicant);
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
				subPayload.put(UserTerm.USER_NAME, employee.getFullName());
				subPayload.put(UserTerm.USER_ID, employee.getMappingUserId());
				subPayload.put(UserTerm.EMAIL, employee.getEmail());
				subPayload.put(UserTerm.TELNO, employee.getTelNo());
			}
			else if (applicant != null) {
				subPayload.put(UserTerm.USER_NAME, applicant.getApplicantName());
				subPayload.put(UserTerm.USER_ID, applicant.getUserId());
				subPayload.put(UserTerm.EMAIL, applicant.getContactEmail());
				subPayload.put(UserTerm.TELNO, applicant.getContactTelNo());
			}
			_log.info("secretKey: "+secretKey);
			subPayload.put(UserTerm.SECRET_KEY, secretKey);
			payload.put(UserTerm.USER, subPayload);

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
						Applicant applicant = id > 0 ? 
							ApplicantLocalServiceUtil.fetchByMappingID(id) : null;
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
						if (UserTerm.TIME_MINUTELY.equals(notiTemplate.getInterval())) {
							cal.add(Calendar.MINUTE, notiTemplate.getExpireDuration());
						} else if (UserTerm.TIME_HOURLY.equals(notiTemplate.getInterval())) {
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
					payLoad.put(UserMGTConstants.USERNAME, user.getScreenName());
					payLoad.put(UserMGTConstants.USEREMAIL, user.getEmailAddress());
					payLoad.put(UserMGTConstants.SCRECT_CODE, newPassword);
					// _log.info("STRAT addNotificationQueue: ");
					NotificationQueueLocalServiceUtil.addNotificationQueue(
						user.getUserId(), groupId, Constants.USER_04,
						User.class.getName(), String.valueOf(user.getUserId()),
						payLoad.toJSONString(), UserMGTConstants.SYSTEM, user.getFullName(),
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
		_log.info("flag: "+flag);
		String phone = StringPool.BLANK;
		
		if (flag) {
			try {

				_log.info("type: "+type);
				_log.info("id: "+id);
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
					Applicant applicant = id > 0 ? 
						ApplicantLocalServiceUtil.fetchByMappingID(id) : null;
					_log.info("applicant: "+applicant);

					if (applicant != null) {
						email += applicant.getContactEmail();
						phone = applicant.getContactTelNo();
						//Update applicant
						_log.info("---UPDATE PASS APPLICANT----");
						applicant.setTmpPass(newPassword);
						ApplicantLocalServiceUtil.updateApplicant(applicant);
					}
					// _log.info("email: "+email);
				}

				JSONObject payLoad = JSONFactoryUtil.createJSONObject();

//				payLoad.put("USERNAME", user.getScreenName());
//				payLoad.put("USEREMAIL", user.getEmailAddress());
//				payLoad.put("PASSWORD", newPassword);
				payLoad.put(UserMGTConstants.USERNAME, user.getScreenName());
				payLoad.put(UserMGTConstants.USEREMAIL, user.getEmailAddress());
				payLoad.put(UserMGTConstants.SCRECT_CODE, newPassword);

				Date now = new Date();
		        Calendar cal = Calendar.getInstance();
		        cal.setTime(now);
		        
		        Notificationtemplate notiTemplate = NotificationtemplateLocalServiceUtil.fetchByF_NotificationtemplateByType(groupId, Constants.USER_04);
		        cal.add(Calendar.MINUTE, 10);
				
		        if (notiTemplate != null) {
					if (UserMGTConstants.MINUTELY.equals(notiTemplate.getInterval())) {
						cal.add(Calendar.MINUTE, notiTemplate.getExpireDuration());
					} else if (UserMGTConstants.HOURLY.equals(notiTemplate.getInterval())) {
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
					payLoad.toJSONString(), UserMGTConstants.SYSTEM, user.getFullName(),
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

			filePath = portalURL + UserTerm.buildEmpFileSign(dlfileEntry.getUuid(), dlfileEntry.getGroupId());

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
	public String getUserById(long userId, long groupId) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		User user = UserLocalServiceUtil.fetchUser(userId);

		if (Validator.isNotNull(user)) {
			Employee employee = EmployeeLocalServiceUtil.fetchByFB_MUID(userId);

			result.put(UserTerm.CLASS_NAME, User.class.getName());
			result.put(UserTerm.CLASS_PK, user.getUserId());
			result.put(UserTerm.USER_ID, user.getUserId());
			result.put(UserTerm.USER_NAME, user.getFullName());
			result.put(UserTerm.MAPPING_USER_ID, user.getUserId());
			result.put(UserTerm.SCREEN_NAME, user.getScreenName());

			result.put(UserTerm.EMPLOYEE_GENDER, UserTerm.getUserByIdDefault());
			result.put(UserTerm.EMPLOYEE_WORKING_STATUS, UserTerm.getUserByIdDefault());
			result.put(UserTerm.EMPLOYEE_MAIN_JOBPOS_ID, UserTerm.getUserByIdDefault());
			result.put(UserTerm.EMPLOYEE_PHOTO_FILE_ENTRY_ID, UserTerm.getUserByIdDefault());
			result.put(UserTerm.EMPLOYEE_FILE_CER_ID, UserTerm.getUserByIdDefault());
			result.put(UserTerm.EMPLOYEE_FILE_SIGN_ID, UserTerm.getUserByIdDefault());
			result.put(UserTerm.APPLICANT_LOCK, UserTerm.getUserByIdDefault());

			String avatar = StringPool.BLANK;

			long portraitId = user.getPortraitId();
			String tokenId =
				WebServerServletTokenUtil.getToken(user.getPortraitId());
			try {
				avatar = UserTerm.buildEmpAvatar(user, portraitId, tokenId);
			}
			catch (PortalException e) {
				_log.error(e);
			}

			result.put(UserTerm.AVATAR, avatar);

			if (Validator.isNotNull(employee)) {

				result.put(UserTerm.CLASS_NAME, Employee.class.getName());
				result.put(UserTerm.CLASS_PK, employee.getEmployeeId());

				result.put(UserTerm.EMPLOYEE_FULLNAME, employee.getFullName());
				result.put(UserTerm.EMPLOYEE_NO, employee.getEmployeeNo());
				result.put(UserTerm.EMPLOYEE_GENDER, employee.getGender());
				if (Validator.isNotNull(employee.getBirthdate())) {
					result.put(
						UserTerm.EMPLOYEE_BIRTHDATE, employee.getBirthdate().getTime());
				}
				result.put(UserTerm.EMPLOYEE_TELNO, employee.getTelNo());
				result.put(UserTerm.EMPLOYEE_MOBILE, employee.getMobile());
				result.put(UserTerm.EMPLOYEE_EMAIL, employee.getEmail());
				result.put(
					UserTerm.EMPLOYEE_WORKING_STATUS, employee.getWorkingStatus());
				result.put(
					UserTerm.EMPLOYEE_PHOTO_FILE_ENTRY_ID, employee.getPhotoFileEntryId());
				result.put(UserTerm.EMPLOYEE_FILE_CER_ID, employee.getFileCertId());
				result.put(UserTerm.EMPLOYEE_FILE_SIGN_ID, employee.getFileSignId());

				result.put(UserTerm.USER_ID, employee.getMappingUserId());
				result.put(UserTerm.USER_NAME, employee.getFullName());
				result.put(UserTerm.MAPPING_USER_ID, employee.getMappingUserId());

				result.put(
					UserTerm.EMPLOYEE_MAIN_JOBPOS_ID, employee.getMainJobPostId());
				result.put(UserTerm.EMPLOYEE_MAIN_JOBPOS_NAME, StringPool.BLANK);
				result.put(UserTerm.TITLE, employee.getTitle());
				result.put(UserTerm.SCOPE, employee.getScope());

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
							UserTerm.EMPLOYEE_MAIN_JOBPOS_NAME, role.getTitleCurrentValue());
					}
				}
				//Add new scope
				if (Validator.isNotNull(employee.getScope()) && groupId > 0) {
					JSONArray govs = JSONFactoryUtil.createJSONArray();
					JSONObject gov = JSONFactoryUtil.createJSONObject();
					DictCollection dict = DictCollectionLocalServiceUtil
							.fetchByF_dictCollectionCode(ConstantUtils.GOVERNMENT_AGENCY, groupId);
					if (dict != null) {
						String [] scopes = StringUtil.split(employee.getScope());
						for (String scope : scopes) {
							DictItem item = DictItemLocalServiceUtil.fetchByF_dictItemCode(scope, dict.getDictCollectionId(), groupId);
							if (item != null) {
								gov.put(UserTerm.GOV_AGENCY_CODE, item.getItemCode());
								gov.put(UserTerm.GOV_AGENCY_NAME, item.getItemName());
							} else {
								gov.put(UserTerm.GOV_AGENCY_CODE, StringPool.BLANK);
								gov.put(UserTerm.GOV_AGENCY_NAME, StringPool.BLANK);
							}
							govs.put(gov);
						}
					} else {
						gov.put(UserTerm.GOV_AGENCY_CODE, StringPool.BLANK);
						gov.put(UserTerm.GOV_AGENCY_NAME, StringPool.BLANK);
						govs.put(gov);
					}
					result.put(UserTerm.GOV_AGENCYS, govs);
				} else {
					JSONArray govs = JSONFactoryUtil.createJSONArray();
					JSONObject gov = JSONFactoryUtil.createJSONObject();
					gov.put(UserTerm.GOV_AGENCY_CODE, StringPool.BLANK);
					gov.put(UserTerm.GOV_AGENCY_NAME, StringPool.BLANK);
					govs.put(gov);
					result.put(UserTerm.GOV_AGENCYS, govs);
				}
			}
			else {

				Applicant applicant = userId > 0 ? 
					ApplicantLocalServiceUtil.fetchByMappingID(userId) : null;

				if (Validator.isNotNull(applicant)) {

					result.put(UserTerm.CLASS_NAME, Applicant.class.getName());
					result.put(UserTerm.CLASS_PK, applicant.getApplicantId());

					result.put(UserTerm.APPLICANT_NAME, applicant.getApplicantName());
					result.put(UserTerm.APPLICANT_TYPE, applicant.getApplicantIdType());
					result.put(UserTerm.APPLICANT_ID_NO, applicant.getApplicantIdNo());
					result.put(
						UserTerm.APPLICANT_ID_DATE, applicant.getApplicantIdDate());
					result.put(UserTerm.APPLICANT_ADDRESS, applicant.getAddress());
					result.put(UserTerm.APPLICANT_CITY_CODE, applicant.getCityCode());
					result.put(UserTerm.APPLICANT_CITY_NAME, applicant.getCityName());
					result.put(
						UserTerm.APPLICANT_DISTRICT_CODE, applicant.getDistrictCode());
					result.put(
						UserTerm.APPLICANT_DISTRICT_NAME, applicant.getDistrictName());
					result.put(UserTerm.APPLICANT_WARD_CODE, applicant.getWardCode());
					result.put(UserTerm.APPLICANT_WARD_NAME, applicant.getWardName());
					result.put(
						UserTerm.APPLICANT_CONTACT_NAME, applicant.getContactName());
					result.put(
						UserTerm.APPLICANT_CONTACT_TELNO, applicant.getContactTelNo());
					result.put(
						UserTerm.APPLICANT_CONTACT_EMAIL, applicant.getContactEmail());
					result.put(
						UserTerm.APPLICANT_ACTIVATION_CODE,
						applicant.getActivationCode());
					result.put(UserTerm.APPLICANT_LOCK, applicant.getLock_());
					result.put(UserTerm.APPLICANT_TMP_SECRET, applicant.getTmpPass());

					result.put(UserTerm.USER_ID, applicant.getMappingUserId());
					result.put(UserTerm.USER_NAME, applicant.getApplicantName());
					result.put(UserTerm.MAPPING_USER_ID, applicant.getMappingUserId());
					result.put(UserTerm.GOV_AGENCY_CODE, StringPool.BLANK);
					result.put(UserTerm.GOV_AGENCY_NAME, StringPool.BLANK);

				}

			}

			return result.toJSONString();
		}
		else {
			return null;
		}

	}

	public JSONObject unlockAccount(long userId, long companyId, long groupId, long id, String email, 
			boolean unlocked, ServiceContext serviceContext) throws PortalException {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		User user =
				UserLocalServiceUtil.fetchUser(id);

		if (Validator.isNull(user)) {

			jsonObject.put(CommonTerm.SCREEN_NAME, id);
			jsonObject.put(CommonTerm.EMAIL, email);
			jsonObject.put(CommonTerm.EXITS, false);
		}
		else {
			

			if (unlocked) {

				user.setFailedLoginAttempts(0);
				user.setLockout(false);
				user.setLockoutDate(null);
			}

			Indexer<User> indexer =
				IndexerRegistryUtil.nullSafeGetIndexer(User.class);

			indexer.reindex(user);

			jsonObject.put(CommonTerm.SCREEN_NAME, user.getScreenName());
			jsonObject.put(CommonTerm.EMAIL, user.getEmailAddress());
			jsonObject.put(CommonTerm.EXITS, true);

		}

		return jsonObject;
	}

	@Override
	public JSONObject getLiveUser(long userId, long companyId, long groupId, ServiceContext serviceContext) {
		List<UserTracker> userTrackers = null;

		Map<String, UserTracker> sessionUsers = LiveUsers.getSessionUsers(companyId);

		userTrackers = new ArrayList<UserTracker>(sessionUsers.values());

		// userTrackers = ListUtil.sort(userTrackers, new
		// UserTrackerModifiedDateComparator(true));
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray dataArr = JSONFactoryUtil.createJSONArray();
		int total = 0;
		if (userTrackers != null) {
			total = userTrackers.size();
			JSONObject userTrackerObj = null;

			for (UserTracker userTracker : userTrackers) {
				userTrackerObj = JSONFactoryUtil.createJSONObject();
				userTrackerObj.put("userId", userTracker.getUserId());
				userTrackerObj.put("email", userTracker.getEmailAddress());
				userTrackerObj.put("fullName", userTracker.getFullName());
				userTrackerObj.put("hits", userTracker.getHits());
				userTrackerObj.put("lastRequest", DateTimeUtils.convertDateToString(userTracker.getModifiedDate(),
						DateTimeUtils._VN_DATE_TIME_FORMAT));
				dataArr.put(userTrackerObj);
			}

		}

		result.put("total", total);
		result.put("data", dataArr);

		return result;

	}

}
