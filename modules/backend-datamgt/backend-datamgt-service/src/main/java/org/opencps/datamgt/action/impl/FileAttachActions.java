package org.opencps.datamgt.action.impl;

import com.liferay.document.library.kernel.model.DLFileVersion;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileVersionLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
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
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.File;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.auth.api.exception.NotFoundException;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.datamgt.action.FileAttachInterface;
import org.opencps.datamgt.model.FileAttach;
import org.opencps.datamgt.service.FileAttachLocalServiceUtil;
import org.opencps.datamgt.utils.DateTimeUtils;

import backend.utils.FileUploadUtils;

public class FileAttachActions implements FileAttachInterface {

	private static final Log _log = LogFactoryUtil.getLog(FileAttachActions.class);

	@Override
	public JSONObject getFileAttachs(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		Hits hits = null;

		SearchContext searchContext = new SearchContext();

		searchContext.setCompanyId(companyId);

		try {

			hits = FileAttachLocalServiceUtil.luceneSearchEngine(params, sorts, start, end, searchContext);

			result.put("data", hits.toList());

			long total = FileAttachLocalServiceUtil.countLuceneSearchEngine(params, searchContext);

			result.put("total", total);

		} catch (ParseException e) {
			_log.debug(e);
			//_log.error(e);
		} catch (SearchException e) {
			_log.debug(e);
			//_log.error(e);
		}

		return result;
	}

	@Override
	public JSONObject getFileAttachVersions(long fileAttachId, ServiceContext serviceContext) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		FileAttach fileAttach = FileAttachLocalServiceUtil.fetchFileAttach(fileAttachId);

		if (fileAttach != null && fileAttach.getFileEntryId() > 0) {
			JSONArray versions = JSONFactoryUtil.createJSONArray();
			List<DLFileVersion> fileVersions = DLFileVersionLocalServiceUtil
					.getFileVersions(fileAttach.getFileEntryId(), WorkflowConstants.STATUS_APPROVED);
			result.put("total", fileVersions.size());

			for (DLFileVersion dlFileVersion : fileVersions) {
				JSONObject version = JSONFactoryUtil.createJSONObject();
				version.put("fileEntryId", dlFileVersion.getFileEntryId());
				version.put("createdDate",
						DateTimeUtils.convertDateToString(dlFileVersion.getCreateDate(), DateTimeUtils._TIMESTAMP));
				version.put("userId", dlFileVersion.getUserId());
				version.put("userName", dlFileVersion.getUserName());
				version.put("version", dlFileVersion.getVersion());
				versions.put(version);
			}

			result.put("versions", versions);
			result.put("fileName", fileAttach.getFileName());
		}

		return result;
	}

	@Override
	public File read(long id, ServiceContext serviceContext) {

		FileAttach fileAttach = FileAttachLocalServiceUtil.fetchFileAttach(id);

		long fileEntryId = fileAttach.getFileEntryId();

		FileEntry fileEntry;

		File file = null;
		try {

			fileEntry = DLAppLocalServiceUtil.getFileEntry(fileEntryId);

			file = DLFileEntryLocalServiceUtil.getFile(fileEntry.getFileEntryId(), fileEntry.getVersion(), true);

		} catch (PortalException e) {
			_log.debug(e);
			//_log.error(e);
		}

		return file;
	}

	@Override
	public FileEntry getFileEntry(long id, ServiceContext serviceContext) {

		FileEntry fileEntry = null;

		FileAttach fileAttach = FileAttachLocalServiceUtil.fetchFileAttach(id);

		try {
			fileEntry = DLAppLocalServiceUtil.getFileEntry(fileAttach.getFileEntryId());
		} catch (PortalException e) {
			_log.debug(e);
			//_log.error(e);
		}

		return fileEntry;
	}

	@Override
	public FileAttach create(long userId, long companyId, long groupId, String className, String classPK,
			String fullName, String email, long fileEntryId, String source, String sourceUrl, long docFileId,
			String fileName, ServiceContext serviceContext)
			throws NoSuchUserException, org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException {

		FileAttach fileAttach = FileAttachLocalServiceUtil.addFileAttach(userId, groupId, className, classPK, fullName,
				email, fileEntryId, source, sourceUrl, docFileId, fileName, serviceContext);

		return fileAttach;
	}

	@Override
	public FileAttach update(long userId, long companyId, long groupId, long id, InputStream inputStream,
			String fileName, String fileType, long fileSize, String destination, String desc,
			ServiceContext serviceContext) {

		FileAttach fileAttach = FileAttachLocalServiceUtil.fetchFileAttach(id);

		try {

			FileEntry fileEntry = FileUploadUtils.updateFile(userId, companyId, groupId, fileAttach.getFileEntryId(),
					inputStream, fileName, fileType, fileSize, destination, desc, serviceContext);

			long fileEntryId = fileEntry.getFileEntryId();

			fileAttach = FileAttachLocalServiceUtil.updateFileAttach(userId, id, fileAttach.getClassName(),
					fileAttach.getClassPK(), fileAttach.getFullName(), fileAttach.getEmail(), fileEntryId,
					fileAttach.getSource(), fileAttach.getSourceUrl(), fileAttach.getDocFileId(), fileName,
					serviceContext);

		} catch (Exception e) {
			_log.debug(e);
			//_log.error(e);
		}

		return fileAttach;
	}

	@Override
	public void delete(long id, ServiceContext serviceContext)
			throws NotFoundException, UnauthenticationException, UnauthorizationException {

		FileAttachLocalServiceUtil.deleteFileAttach(id, serviceContext);
	}

	@Override
	public FileAttach upload(long userId, long companyId, long groupId, String className, String classPK,
			InputStream inputStream, String fileName, String fileType, long fileSize, String destination, String desc,
			ServiceContext serviceContext)
			throws NoSuchUserException, UnauthenticationException, UnauthorizationException {

		FileAttach fileAttach = null;

		try {
			FileEntry fileEntry = FileUploadUtils.uploadFile(userId, companyId, groupId, inputStream, fileName,
					fileType, fileSize, destination, desc, serviceContext);

			long fileEntryId = fileEntry.getFileEntryId();

			fileAttach = FileAttachLocalServiceUtil.addFileAttach(userId, groupId, className, classPK, StringPool.BLANK,
					StringPool.BLANK, fileEntryId, StringPool.BLANK, StringPool.BLANK, 0, fileName, serviceContext);

		} catch (Exception e) {
			_log.debug(e);
			//_log.error(e);
		}
		return fileAttach;
	}

	public List<FileAttach> getFileAttachs(long groupId, String className, String classPK, long docFileId) {

		return FileAttachLocalServiceUtil.findByF_docFileId(groupId, className, classPK, docFileId);
	}

	@Override
	public File read(long id, String version, ServiceContext serviceContext) {

		FileAttach fileAttach = FileAttachLocalServiceUtil.fetchFileAttach(id);

		long fileEntryId = fileAttach.getFileEntryId();

		FileEntry fileEntry;

		File file = null;
		try {

			fileEntry = DLAppLocalServiceUtil.getFileEntry(fileEntryId);

			file = DLFileEntryLocalServiceUtil.getFile(fileEntry.getFileEntryId(), version, true);

		} catch (PortalException e) {
			_log.debug(e);
			//_log.error(e);
		}

		return file;
	}

}
