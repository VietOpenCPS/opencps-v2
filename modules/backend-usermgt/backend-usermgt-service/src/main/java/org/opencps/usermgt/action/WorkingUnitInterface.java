package org.opencps.usermgt.action;

import java.io.File;
import java.io.InputStream;
import java.util.LinkedHashMap;

import org.opencps.usermgt.model.WorkingUnit;

import com.liferay.asset.kernel.exception.DuplicateCategoryException;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;

import backend.auth.api.exception.NotFoundException;
import backend.auth.api.exception.UnauthenticationException;
import backend.auth.api.exception.UnauthorizationException;

public interface WorkingUnitInterface {

	public JSONObject getWorkingUnits(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext);

	public File getLogo(long id, ServiceContext serviceContext) throws NotFoundException;

	public WorkingUnit create(long userId, long companyId, long groupId, String address, String email, String enName,
			String faxNo, String govAgencyCode, String name, String telNo, String website, long parentWorkingUnitId,
			int sibling, String ceremonyDate, ServiceContext serviceContext) throws NoSuchUserException, UnauthenticationException,
			UnauthorizationException, NumberFormatException, NotFoundException, DuplicateCategoryException;

	public WorkingUnit update(long userId, long companyId, long groupId, long id, String address, String email,
			String enName, String faxNo, String govAgencyCode, String name, String telNo, String website,
			long parentWorkingUnitId, int sibling, String ceremonyDate, ServiceContext serviceContext)
			throws NoSuchUserException, UnauthenticationException, UnauthorizationException, NumberFormatException,
			NotFoundException, DuplicateCategoryException;

	public FileEntry getFileEntry(long id, ServiceContext serviceContext) ;

	public File updateLogo(long userId, long companyId, long groupId, long id, InputStream inputStream, String fileName,
			String fileType, long fileSize, String destination, String desc, ServiceContext serviceContext)
			throws Exception;
}
