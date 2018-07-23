package org.opencps.usermgt.action;

import java.util.LinkedHashMap;

import org.opencps.usermgt.model.JobPos;

import com.liferay.asset.kernel.exception.DuplicateCategoryException;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;

import backend.auth.api.exception.NotFoundException;
import backend.auth.api.exception.UnauthenticationException;
import backend.auth.api.exception.UnauthorizationException;

public interface JobposInterface {

	JSONObject getJobpos(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params, Sort[] sorts,
			int start, int end, ServiceContext serviceContext);

	JobPos create(long userId, long groupId, String title, String description, int leader,
			ServiceContext serviceContext)
			throws NotFoundException, UnauthenticationException, UnauthorizationException, PortalException;

	JobPos update(long userId, long groupId, long id, String title, String description, int leader,
			ServiceContext serviceContext) throws NoSuchUserException, NotFoundException, UnauthenticationException,
			UnauthorizationException, DuplicateCategoryException;

	JSONObject getJobposPermissions();

	String createPermissions(long companyId, long id, String actionId, ServiceContext serviceContext);

	void deletePermissionByKey(long companyId, long id, String actionId, ServiceContext serviceContext);


	void createPermissionsPatch(long userId, long companyId, long groupId, long id, String permissions,
			ServiceContext serviceContext);

	void updateJobPosDB(long userId, long groupId, String jobCode, String title, String description,
			ServiceContext serviceContext) throws PortalException;

}
