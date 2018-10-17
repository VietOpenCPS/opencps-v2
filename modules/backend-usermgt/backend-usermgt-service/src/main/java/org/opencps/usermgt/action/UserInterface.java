package org.opencps.usermgt.action;

import java.io.File;
import java.io.InputStream;
import java.security.DigestException;

import com.liferay.asset.kernel.exception.DuplicateCategoryException;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.service.ServiceContext;

import backend.auth.api.exception.NotFoundException;
import backend.auth.api.exception.UnauthenticationException;
import backend.auth.api.exception.UnauthorizationException;

public interface UserInterface {

	public File getPhoto(long id, ServiceContext serviceContext);

	public File uploadPhoto(long userId, long companyId, long groupId, long id, InputStream inputStream,
			String fileName, String fileType, long fileSize, String destination, String desc,
			ServiceContext serviceContext) throws Exception;

	public String getType(long id, ServiceContext serviceContext);

	public JSONObject getSites(long id, long groupId, ServiceContext serviceContext);

	public JSONObject getRoles(long id, long groupId, ServiceContext serviceContext);

	public Document getUserProfile(long id, long groupId, ServiceContext serviceContext);

	public String getPreference(long id, long groupId, ServiceContext serviceContext);

	public String getPreferenceByKey(long id, long groupId, String key, ServiceContext serviceContext);

	public String addPreferences(long id, long groupId, String preferences, ServiceContext serviceContext)
			throws NoSuchUserException, UnauthenticationException, UnauthorizationException, DuplicateCategoryException,
			NotFoundException;

	public String updatePreferences(long id, long groupId, String key, String value, ServiceContext serviceContext)
			throws NoSuchUserException, NotFoundException, UnauthenticationException, UnauthorizationException,
			DuplicateCategoryException;

	public JSONObject getUsers(long groupId, ServiceContext serviceContext);

	public User getUserById(long groupId, long companyId, long id, ServiceContext serviceContext);

	public Document getForgot(long groupId, long companyId, String screenname_email, ServiceContext serviceContext);

	public Document getForgotConfirm(long groupId, long companyId, String screenname_email, String code,
			ServiceContext serviceContext) throws DigestException;

	public boolean getCheckpass(long groupId, long companyId, long id, String password, ServiceContext serviceContext);

	public int addChangepass(long groupId, long companyId, long id, String oldPassword, String newPassword,
			ServiceContext serviceContext);
	
	public boolean addChangepass(long groupId, long companyId, long id, String oldPassword, String newPassword, int type,
			ServiceContext serviceContext);

	public File uploadEsign(long userId, long companyId, long groupId, long id, InputStream inputStream,
			String fileName, String fileType, long fileSize, String destination, String desc,
			ServiceContext serviceContext) throws Exception;

	public File uploadCert(long userId, long companyId, long groupId, long id, InputStream inputStream, String fileName,
			String fileType, long fileSize, String destination, String desc, ServiceContext serviceContext)
			throws Exception;

	public String getEsignPath(long userId, Company company, long groupId, ServiceContext serviceContext)
			throws Exception;

	public String getCertPath(long userId, long companyId, long groupId, ServiceContext serviceContext)
			throws Exception;

	public String getUserById(long userId);
}
