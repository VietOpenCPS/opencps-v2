package org.opencps.datamgt.action;

import java.io.File;
import java.io.InputStream;
import java.util.LinkedHashMap;

import org.opencps.auth.api.exception.NotFoundException;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.datamgt.model.FileAttach;

import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;

public interface FileAttachInterface {

	JSONObject getFileAttachs(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext);

	JSONObject getFileAttachVersions(long fileAttachId, ServiceContext serviceContext);

	File read(long id, ServiceContext serviceContext);

	File read(long id, String version, ServiceContext serviceContext);

	FileEntry getFileEntry(long id, ServiceContext serviceContext);

	FileAttach create(long userId, long companyId, long groupId, String className, String classPK, String fullName,
			String email, long fileEntryId, String source, String sourceUrl, long docFileId, String fileName,
			ServiceContext serviceContext)
			throws NoSuchUserException, UnauthenticationException, UnauthorizationException;

	void delete(long id, ServiceContext serviceContext)
			throws NotFoundException, UnauthenticationException, UnauthorizationException;

	FileAttach upload(long userId, long companyId, long groupId, String className, String classPK,
			InputStream inputStream, String fileName, String fileType, long fileSize, String destination, String desc,
			ServiceContext serviceContext)
			throws NoSuchUserException, UnauthenticationException, UnauthorizationException;

	FileAttach update(long userId, long companyId, long groupId, long id, InputStream inputStream, String fileName,
			String fileType, long fileSize, String destination, String desc, ServiceContext serviceContext)
			throws NoSuchUserException, NotFoundException, UnauthenticationException, UnauthorizationException;

}
