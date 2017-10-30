package org.opencps.usermgt.action;

import java.io.File;
import java.io.InputStream;
import java.util.LinkedHashMap;

import org.opencps.usermgt.model.OfficeSite;

import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;

import backend.auth.api.exception.NotFoundException;
import backend.auth.api.exception.UnauthenticationException;
import backend.auth.api.exception.UnauthorizationException;

public interface OfficeSiteInterface {

	public OfficeSite create(long userId, long companyId, long groupId, String address, String adminEmail, String email,
			String enName, String faxNo, String govAgencyCode, String name, String preferences, String siteGroupId,
			String telNo, String website, ServiceContext serviceContext)
			throws NoSuchUserException, UnauthenticationException, UnauthorizationException, NumberFormatException;

	public OfficeSite update(long userId, long companyId, long groupId, long id, String address, String adminEmail,
			String email, String enName, String faxNo, String govAgencyCode, String name, String preferences,
			String siteGroupId, String telNo, String website, ServiceContext serviceContext)
			throws NoSuchUserException, NotFoundException, UnauthenticationException, UnauthorizationException;

	public JSONObject getOfficeSites(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext);

	public File getOfficeSiteLogo(long id, ServiceContext serviceContext);

	public File uploadOfficeSiteLogo(long userId, long companyId, long groupId, long id, InputStream inputStream,
			String fileName, String fileType, long fileSize, String destination, String desc,
			ServiceContext serviceContext) throws Exception;

	public FileEntry getFileEntry(long id, ServiceContext serviceContext);

	public OfficeSite updateOfficeSitePreferences(long userId, long companyId, long groupId, long id,
			String preferences, ServiceContext serviceContext)
			throws NoSuchUserException, NotFoundException, UnauthenticationException, UnauthorizationException;

	public OfficeSite updateOfficeSitePreferencesByKey(long userId, long companyId, long groupId, long id, String key,
			String value, ServiceContext serviceContext)
			throws NoSuchUserException, NotFoundException, UnauthenticationException, UnauthorizationException;
}
