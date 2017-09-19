package org.opencps.usermgt.action;

import java.io.File;
import java.io.InputStream;

import com.liferay.asset.kernel.exception.DuplicateCategoryException;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.service.ServiceContext;

import org.opencps.auth.api.exception.NotFoundException;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;

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
			throws NoSuchUserException, UnauthenticationException, UnauthorizationException, DuplicateCategoryException, NotFoundException;

	public String updatePreferences(long id, long groupId, String key, String value, ServiceContext serviceContext)
			throws NoSuchUserException, NotFoundException, UnauthenticationException, UnauthorizationException,
			DuplicateCategoryException;
}
