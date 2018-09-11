package org.opencps.usermgt.action.impl;

import java.io.File;
import java.io.InputStream;
import java.util.LinkedHashMap;

import org.opencps.usermgt.action.WorkingUnitInterface;
import org.opencps.usermgt.model.WorkingUnit;
import org.opencps.usermgt.service.WorkingUnitLocalServiceUtil;
import org.opencps.usermgt.utils.DateTimeUtils;

import com.liferay.asset.kernel.exception.DuplicateCategoryException;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import backend.auth.api.exception.NotFoundException;
import backend.auth.api.exception.UnauthenticationException;
import backend.auth.api.exception.UnauthorizationException;
import backend.utils.FileUploadUtils;

public class WorkingUnitActions implements WorkingUnitInterface {

	private static Log _log = LogFactoryUtil.getLog(WorkingUnitActions.class);

	@Override
	public JSONObject getWorkingUnits(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		Hits hits = null;
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		try {

			hits = WorkingUnitLocalServiceUtil.luceneSearchEngine(params, sorts, start, end, searchContext);

			result.put("data", hits.toList());

			long total = WorkingUnitLocalServiceUtil.countLuceneSearchEngine(params, searchContext);

			result.put("total", total);

		} catch (ParseException e) {
			_log.error(e);
		} catch (SearchException e) {
			_log.error(e);
		}

		return result;
	}

	@Override
	public File getLogo(long id, ServiceContext serviceContext) throws NotFoundException {

		WorkingUnit workingUnit = WorkingUnitLocalServiceUtil.fetchWorkingUnit(id);

		if (Validator.isNull(workingUnit)) {
			throw new NotFoundException();
		}

		long fileEntryId = workingUnit.getLogoFileEntryId();

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
	public WorkingUnit create(long userId, long companyId, long groupId, String address, String email, String enName,
			String faxNo, String govAgencyCode, String name, String telNo, String website, long parentWorkingUnitId,
			int sibling, String ceremonyDate, ServiceContext serviceContext) throws NoSuchUserException, UnauthenticationException,
			UnauthorizationException, NumberFormatException, NotFoundException, DuplicateCategoryException {
		WorkingUnit ett = null;
		
		ett = WorkingUnitLocalServiceUtil.addWorkingUnit(userId, groupId, name, enName, govAgencyCode,
				parentWorkingUnitId, String.valueOf(sibling), address, telNo, faxNo, email, website, DateTimeUtils.convertStringToDateAPI(ceremonyDate), serviceContext);

		return ett;
	}

	@Override
	public WorkingUnit update(long userId, long companyId, long groupId, long id, String address, String email,
			String enName, String faxNo, String govAgencyCode, String name, String telNo, String website,
			long parentWorkingUnitId, int sibling, String ceremonyDate, ServiceContext serviceContext)
			throws NoSuchUserException, UnauthenticationException, UnauthorizationException, NumberFormatException,
			NotFoundException, DuplicateCategoryException {

		WorkingUnit workingUnit = WorkingUnitLocalServiceUtil.fetchWorkingUnit(id);

		if (Validator.isNotNull(name)) {

			workingUnit.setName(name);

		}

		if (Validator.isNotNull(enName)) {

			workingUnit.setEnName(enName);

		}

		if (Validator.isNotNull(govAgencyCode)) {

			workingUnit.setGovAgencyCode(govAgencyCode);

		}

		if (parentWorkingUnitId >= 0) {

			workingUnit.setParentWorkingUnitId(parentWorkingUnitId);

		}

		if (Validator.isNotNull(address)) {

			workingUnit.setAddress(address);

		}
		if (Validator.isNotNull(telNo)) {

			workingUnit.setTelNo(telNo);

		}

		if (Validator.isNotNull(faxNo)) {

			workingUnit.setFaxNo(faxNo);

		}

		if (Validator.isNotNull(email)) {

			workingUnit.setEmail(email);

		}

		if (Validator.isNotNull(website)) {

			workingUnit.setWebsite(website);

		}

		if (sibling > 0) {

			workingUnit.setSibling(String.valueOf(sibling));

		}

		if (Validator.isNotNull(ceremonyDate)) {

			workingUnit.setCeremonyDate(DateTimeUtils.convertStringToDateAPI(ceremonyDate));

		}
		
		workingUnit = WorkingUnitLocalServiceUtil.updateWorkingUnit(userId, workingUnit.getWorkingUnitId(),
				workingUnit.getName(), workingUnit.getEnName(), workingUnit.getGovAgencyCode(),
				workingUnit.getParentWorkingUnitId(), workingUnit.getSibling(), workingUnit.getAddress(),
				workingUnit.getTelNo(), workingUnit.getFaxNo(), workingUnit.getEmail(), workingUnit.getWebsite(),
				workingUnit.getLogoFileEntryId(), workingUnit.getCeremonyDate(), serviceContext);

		return workingUnit;
	}

	@Override
	public FileEntry getFileEntry(long id, ServiceContext serviceContext) {
		FileEntry fileEntry = null;

		WorkingUnit workingUnit = WorkingUnitLocalServiceUtil.fetchWorkingUnit(id);

		try {
			fileEntry = DLAppLocalServiceUtil.getFileEntry(workingUnit.getLogoFileEntryId());
		} catch (PortalException e) {
			_log.error(e);
		}

		return fileEntry;
	}

	@Override
	public File updateLogo(long userId, long companyId, long groupId, long id, InputStream inputStream, String fileName,
			String fileType, long fileSize, String destination, String desc, ServiceContext serviceContext)
			throws Exception {
		File file = null;

		FileEntry fileEntry = FileUploadUtils.uploadFile(userId, companyId, groupId, inputStream, fileName, fileType,
				fileSize, destination, desc, serviceContext);

		WorkingUnit workingUnit = WorkingUnitLocalServiceUtil.fetchWorkingUnit(id);

		WorkingUnitLocalServiceUtil.updateWorkingUnit(userId, workingUnit.getWorkingUnitId(),
				workingUnit.getName(), workingUnit.getEnName(), workingUnit.getGovAgencyCode(),
				workingUnit.getParentWorkingUnitId(), workingUnit.getSibling(), workingUnit.getAddress(),
				workingUnit.getTelNo(), workingUnit.getFaxNo(), workingUnit.getEmail(), workingUnit.getWebsite(),
				fileEntry.getFileEntryId(), workingUnit.getCeremonyDate(), serviceContext);

		file = DLFileEntryLocalServiceUtil.getFile(fileEntry.getFileEntryId(), fileEntry.getVersion(), false);

		return file;
	}

}
