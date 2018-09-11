package org.opencps.usermgt.action.impl;

import java.io.File;
import java.io.InputStream;
import java.util.LinkedHashMap;

import org.opencps.usermgt.action.OfficeSiteInterface;
import org.opencps.usermgt.model.OfficeSite;
import org.opencps.usermgt.service.OfficeSiteLocalServiceUtil;

import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import backend.auth.api.exception.NotFoundException;
import backend.auth.api.exception.UnauthenticationException;
import backend.auth.api.exception.UnauthorizationException;
import backend.utils.FileUploadUtils;

public class OfficeSiteActions implements OfficeSiteInterface {

	private static final Log _log = LogFactoryUtil.getLog(OfficeSiteActions.class);

	@Override
	public OfficeSite create(long userId, long companyId, long groupId, String address, String adminEmail, String email,
			String enName, String faxNo, String govAgencyCode, String name, String preferences, String siteGroupId,
			String telNo, String website, ServiceContext serviceContext)
			throws NoSuchUserException, UnauthenticationException, UnauthorizationException, NumberFormatException {
		OfficeSite ett = null;

		User user = UserLocalServiceUtil.fetchUserByEmailAddress(companyId, adminEmail);

		ett = OfficeSiteLocalServiceUtil.addOfficeSite(userId, groupId, name, enName, govAgencyCode, address, telNo,
				faxNo, email, website, 0, GetterUtil.get(siteGroupId, 0), user.getUserId(), preferences,
				serviceContext);

		return ett;
	}

	@Override
	public OfficeSite update(long userId, long companyId, long groupId, long officeSiteId, String address,
			String adminEmail, String email, String enName, String faxNo, String govAgencyCode, String name,
			String preferences, String siteGroupId, String telNo, String website, ServiceContext serviceContext)
			throws NoSuchUserException, NotFoundException, UnauthenticationException, UnauthorizationException {

		OfficeSite officeSite = OfficeSiteLocalServiceUtil.fetchOfficeSite(officeSiteId);

		if (Validator.isNotNull(name)) {

			officeSite.setName(name);

		}

		if (Validator.isNotNull(enName)) {

			officeSite.setEnName(enName);

		}

		if (Validator.isNotNull(govAgencyCode)) {

			officeSite.setGovAgencyCode(govAgencyCode);

		}

		if (Validator.isNotNull(address)) {

			officeSite.setAddress(address);

		}

		if (Validator.isNotNull(telNo)) {

			officeSite.setTelNo(telNo);

		}

		if (Validator.isNotNull(faxNo)) {

			officeSite.setFaxNo(faxNo);

		}

		if (Validator.isNotNull(email)) {

			officeSite.setEmail(email);

		}

		if (Validator.isNotNull(website)) {

			officeSite.setWebsite(website);

		}

		if (Validator.isNotNull(siteGroupId)) {

			officeSite.setSiteGroupId(GetterUtil.get(siteGroupId, 0));

		}

		if (Validator.isNotNull(adminEmail)) {

			User user = UserLocalServiceUtil.fetchUserByEmailAddress(companyId, adminEmail);

			officeSite.setAdminUserId(user.getUserId());

		}

		if (Validator.isNotNull(preferences)) {

			officeSite.setPreferences(preferences);

		}

		officeSite = OfficeSiteLocalServiceUtil.updateOfficeSite(userId, officeSiteId, officeSite.getName(),
				officeSite.getEnName(), officeSite.getGovAgencyCode(), officeSite.getAddress(), officeSite.getTelNo(),
				officeSite.getFaxNo(), officeSite.getEmail(), officeSite.getWebsite(), officeSite.getLogoFileEntryId(),
				officeSite.getSiteGroupId(), officeSite.getAdminUserId(), officeSite.getPreferences(), serviceContext);

		return officeSite;
	}

	@Override
	public JSONObject getOfficeSites(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		Hits hits = null;
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		try {

			hits = OfficeSiteLocalServiceUtil.luceneSearchEngine(params, sorts, start, end, searchContext);

			result.put("data", hits.toList());

			long total = OfficeSiteLocalServiceUtil.countLuceneSearchEngine(params, searchContext);

			result.put("total", total);

		} catch (ParseException e) {
			_log.error(e);
		} catch (SearchException e) {
			_log.error(e);
		}

		return result;
	}

	@Override
	public File getOfficeSiteLogo(long id, ServiceContext serviceContext) {

		OfficeSite officeSite = OfficeSiteLocalServiceUtil.fetchOfficeSite(id);

		long fileEntryId = officeSite.getLogoFileEntryId();

		FileEntry fileEntry;

		File file = null;
		try {

			fileEntry = DLAppLocalServiceUtil.getFileEntry(fileEntryId);

			file = DLFileEntryLocalServiceUtil.getFile(fileEntry.getFileEntryId(), fileEntry.getVersion(), true);

		} catch (PortalException e) {
			_log.error(e);
		}

		return file;

	}

	@Override
	public File uploadOfficeSiteLogo(long userId, long companyId, long groupId, long id, InputStream inputStream,
			String fileName, String fileType, long fileSize, String destination, String desc,
			ServiceContext serviceContext) throws Exception {
		File file = null;

		FileEntry fileEntry = FileUploadUtils.uploadFile(userId, companyId, groupId, inputStream, fileName, fileType,
				fileSize, destination, desc, serviceContext);

		OfficeSite officeSite = OfficeSiteLocalServiceUtil.fetchOfficeSite(id);

		OfficeSiteLocalServiceUtil.updateOfficeSite(userId, officeSite.getOfficeSiteId(),
				officeSite.getName(), officeSite.getEnName(), officeSite.getGovAgencyCode(), officeSite.getAddress(),
				officeSite.getTelNo(), officeSite.getFaxNo(), officeSite.getEmail(), officeSite.getWebsite(),
				fileEntry.getFileEntryId(), officeSite.getSiteGroupId(), officeSite.getAdminUserId(), officeSite.getPreferences(), serviceContext);

		file = DLFileEntryLocalServiceUtil.getFile(fileEntry.getFileEntryId(), fileEntry.getVersion(), false);

		return file;
	}

	@Override
	public FileEntry getFileEntry(long id, ServiceContext serviceContext) {
		FileEntry fileEntry = null;
		
		OfficeSite officeSite = OfficeSiteLocalServiceUtil.fetchOfficeSite(id);
		
		try {
			fileEntry = DLAppLocalServiceUtil.getFileEntry(officeSite.getLogoFileEntryId());
		} catch (PortalException e) {
			_log.error(e);
		}
		
		return fileEntry;
	}

	@Override
	public OfficeSite updateOfficeSitePreferences(long userId, long companyId, long groupId, long id,
			String preferences, ServiceContext serviceContext)
			throws NoSuchUserException, NotFoundException, UnauthenticationException, UnauthorizationException {
		OfficeSite officeSite = OfficeSiteLocalServiceUtil.fetchOfficeSite(id);

		if (Validator.isNotNull(preferences)) {

			officeSite.setPreferences(preferences);

		}

		officeSite = OfficeSiteLocalServiceUtil.updateOfficeSite(userId, officeSite.getOfficeSiteId(), officeSite.getName(),
				officeSite.getEnName(), officeSite.getGovAgencyCode(), officeSite.getAddress(), officeSite.getTelNo(),
				officeSite.getFaxNo(), officeSite.getEmail(), officeSite.getWebsite(), officeSite.getLogoFileEntryId(),
				officeSite.getSiteGroupId(), officeSite.getAdminUserId(), officeSite.getPreferences(), serviceContext);

		return officeSite;
	}

	@Override
	public OfficeSite updateOfficeSitePreferencesByKey(long userId, long companyId, long groupId, long id, String key,
			String value, ServiceContext serviceContext)
			throws NoSuchUserException, NotFoundException, UnauthenticationException, UnauthorizationException {
		OfficeSite officeSite = OfficeSiteLocalServiceUtil.fetchOfficeSite(id);

		try {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject(officeSite.getPreferences());
			
			jsonObject.put(key, value);
			
			officeSite = OfficeSiteLocalServiceUtil.updateOfficeSite(userId, officeSite.getOfficeSiteId(), officeSite.getName(),
					officeSite.getEnName(), officeSite.getGovAgencyCode(), officeSite.getAddress(), officeSite.getTelNo(),
					officeSite.getFaxNo(), officeSite.getEmail(), officeSite.getWebsite(), officeSite.getLogoFileEntryId(),
					officeSite.getSiteGroupId(), officeSite.getAdminUserId(), jsonObject.toJSONString(), serviceContext);
			
			
		} catch (JSONException e) {
			_log.error(e);
		}

		return officeSite;
	}

}
