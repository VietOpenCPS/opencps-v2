package org.opencps.usermgt.action;

import java.util.LinkedHashMap;

import org.opencps.usermgt.model.ResourceUser;

import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;

import backend.auth.api.exception.NotFoundException;
import backend.auth.api.exception.UnauthenticationException;
import backend.auth.api.exception.UnauthorizationException;

public interface ResourceUserInterface {

	public JSONObject getResourceUsers(String className, String classPK, long userId, long companyId, long groupId,
			LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end, ServiceContext serviceContext,
			boolean full);

	public ResourceUser create(long userId, long groupId, String className, String classPK, Long toUserId, String fullname, String email, boolean readonly, 
			ServiceContext serviceContext)
			throws NoSuchUserException, UnauthenticationException, UnauthorizationException, NotFoundException;

	public boolean delete(long userId, long groupId, long companyId, String className, String classPK, String email,
			ServiceContext serviceContext)
			throws NotFoundException, UnauthenticationException, UnauthorizationException;

	public void createResourceUserPatch(String className, String classPK, long userId, long companyId, long groupId,
			String users, ServiceContext serviceContext)
			throws NotFoundException, UnauthenticationException, UnauthorizationException, NoSuchUserException;
	
	public void clone(String className, String classPK, long userId, long companyId, long groupId, String sourcePK,
			ServiceContext serviceContext)
			throws NotFoundException, UnauthenticationException, UnauthorizationException, NoSuchUserException;
}
