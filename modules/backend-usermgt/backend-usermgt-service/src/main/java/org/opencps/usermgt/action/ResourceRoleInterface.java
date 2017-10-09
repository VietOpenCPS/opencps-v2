package org.opencps.usermgt.action;

import java.util.LinkedHashMap;

import org.opencps.usermgt.model.ResourceRole;

import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;

import org.opencps.auth.api.exception.NotFoundException;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;

public interface ResourceRoleInterface {

	public JSONObject getResourceRoles(String className, String classPK, long userId, long companyId, long groupId,
			LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end, ServiceContext serviceContext,
			boolean full);

	public ResourceRole create(long userId, long groupId, String className, String classPK, Long roleId,
			ServiceContext serviceContext)
			throws NoSuchUserException, UnauthenticationException, UnauthorizationException;

	public boolean delete(long userId, long groupId, String className, String classPK, long roleId,
			ServiceContext serviceContext)
			throws NotFoundException, UnauthenticationException, UnauthorizationException;

	public void createResourceRolePatch(String className, String classPK, long userId, long companyId, long groupId,
			String roles, ServiceContext serviceContext)
			throws NotFoundException, UnauthenticationException, UnauthorizationException, NoSuchUserException;
}
