package org.opencps.usermgt.action.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.opencps.usermgt.action.UserInterface;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.model.OfficeSite;
import org.opencps.usermgt.model.Preferences;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;
import org.opencps.usermgt.service.OfficeSiteLocalServiceUtil;
import org.opencps.usermgt.service.PreferencesLocalServiceUtil;

import com.liferay.asset.kernel.exception.DuplicateCategoryException;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Image;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ImageLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import org.opencps.auth.api.exception.NotFoundException;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.auth.utils.FileUploadUtils;

public class UserActions implements UserInterface {

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

				document.addNumberSortable("entryClassPK", officeSite.getOfficeSiteId());
				document.addTextSortable("siteName", officeSite.getName());
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
		//TODO
		// PARTER INFO
		Document document = new DocumentImpl();
		
		User user = UserLocalServiceUtil.fetchUser(employee.getMappingUserId());
		
		String screenName = StringPool.BLANK;
		String email = StringPool.BLANK;
		
		if(Validator.isNotNull(user)){
			
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
			throws NoSuchUserException, UnauthenticationException, UnauthorizationException, DuplicateCategoryException, NotFoundException {
		
		Preferences preferences = PreferencesLocalServiceUtil.fetchByF_userId(groupId, id);
		
		if(Validator.isNull(preferences)){
			preferences = PreferencesLocalServiceUtil.addPreferences(id, groupId, preferencesData, serviceContext);
		} else {
			preferences = PreferencesLocalServiceUtil.updatePreferences(id, preferences.getPreferencesId(), preferencesData, serviceContext);
		}
		
		return preferences.getPreferences();
	}

	@Override
	public String updatePreferences(long id, long groupId, String key, String value, ServiceContext serviceContext)
			throws NoSuchUserException, NotFoundException, UnauthenticationException, UnauthorizationException, DuplicateCategoryException {
		Preferences preferences = PreferencesLocalServiceUtil.fetchByF_userId(groupId, id);
		
		JSONObject jsonObject;
		try {
			jsonObject = JSONFactoryUtil.createJSONObject(preferences.getPreferences());
			
			jsonObject.put(key, value);
			
			preferences = PreferencesLocalServiceUtil.updatePreferences(id, preferences.getPreferencesId(), jsonObject.toJSONString(), serviceContext);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return preferences.getPreferences();
	}

}
