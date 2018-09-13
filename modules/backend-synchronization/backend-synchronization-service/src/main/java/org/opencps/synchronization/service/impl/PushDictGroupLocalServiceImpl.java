/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package org.opencps.synchronization.service.impl;

import java.util.Date;
import java.util.List;

import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.NotFoundException;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.auth.api.keys.ActionKeys;
import org.opencps.auth.api.keys.ModelNameKeys;
import org.opencps.synchronization.exception.NoSuchPushDictGroupException;
import org.opencps.synchronization.model.PushDictGroup;
import org.opencps.synchronization.service.base.PushDictGroupLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the push dict group local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.synchronization.service.PushDictGroupLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author trungdk
 * @see PushDictGroupLocalServiceBaseImpl
 * @see org.opencps.synchronization.service.PushDictGroupLocalServiceUtil
 */
@ProviderType
public class PushDictGroupLocalServiceImpl
	extends PushDictGroupLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.synchronization.service.PushDictGroupLocalServiceUtil} to access the push dict group local service.
	 */
	
	@Override
	public PushDictGroup addPushDictGroup(
			long userId, 
			long groupId, 
			String serverNo,
			String collectionCode,
			String groupCode,
			String groupName,
			String groupNameEN,
			String groupDescription,
			String itemCode,
			String method,
			ServiceContext serviceContext) throws UnauthenticationException,
			UnauthorizationException, NoSuchUserException, SystemException {
	
		// authen
		BackendAuthImpl authImpl = new BackendAuthImpl();

		boolean isAuth = authImpl.isAuth(serviceContext);

		if (!isAuth) {
			throw new UnauthenticationException();
		}

		boolean hasPermission = authImpl.hasResource(serviceContext, ModelNameKeys.WORKINGUNIT_MGT_CENTER,
				ActionKeys.EDIT_DATA);

		if (!hasPermission) {
			throw new UnauthorizationException();
		}

		Date now = new Date();
		
		User user = userPersistence.findByPrimaryKey(userId);

		long pushDictGroupId = counterLocalService.increment(PushDictGroup.class.getName());
		
		PushDictGroup pushDictGroup = pushDictGroupPersistence.create(pushDictGroupId);
		
		// Group instance
		pushDictGroup.setGroupId(groupId);

		// Audit fields
		pushDictGroup.setUuid(serviceContext.getUuid());
		pushDictGroup.setCompanyId(user.getCompanyId());
		pushDictGroup.setUserId(user.getUserId());
		pushDictGroup.setUserName(user.getFullName());
		pushDictGroup.setCreateDate(serviceContext.getCreateDate(now));
		pushDictGroup.setModifiedDate(serviceContext.getCreateDate(now));

		pushDictGroup.setServerNo(serverNo);
		pushDictGroup.setCollectionCode(collectionCode);
		pushDictGroup.setGroupCode(groupCode);
		pushDictGroup.setGroupName(groupName);
		pushDictGroup.setGroupNameEN(groupNameEN);
		pushDictGroup.setGroupDescription(groupDescription);
		pushDictGroup.setMethod(method);
		pushDictGroup.setItemCode(itemCode);
		
		return pushDictGroupPersistence.update(pushDictGroup);
	}
	
	@Override
	public PushDictGroup updatePushDictGroup(
			long userId, 
			long groupId, 
			long pushDictGroupId,
			String serverNo,
			String collectionCode,
			String groupCode,
			String groupName,
			String groupNameEN,
			String groupDescription,
			String itemCode,
			String method,
			ServiceContext serviceContext) throws UnauthenticationException,
			UnauthorizationException, NoSuchUserException, NoSuchPushDictGroupException, SystemException {
		// authen
		BackendAuthImpl authImpl = new BackendAuthImpl();

		boolean isAuth = authImpl.isAuth(serviceContext);

		if (!isAuth) {
			throw new UnauthenticationException();
		}

		boolean hasPermission = authImpl.hasResource(serviceContext, ModelNameKeys.WORKINGUNIT_MGT_CENTER,
				ActionKeys.EDIT_DATA);

		if (!hasPermission) {
			throw new UnauthorizationException();
		}
	
		Date now = new Date();

		User user = userPersistence.findByPrimaryKey(userId);
		PushDictGroup pushDictGroup = pushDictGroupPersistence.fetchByPrimaryKey(pushDictGroupId);
		
		// Group instance
		pushDictGroup.setGroupId(groupId);

		// Audit fields
		pushDictGroup.setUuid(serviceContext.getUuid());
		pushDictGroup.setCompanyId(user.getCompanyId());
		pushDictGroup.setUserId(user.getUserId());
		pushDictGroup.setUserName(user.getFullName());
		pushDictGroup.setCreateDate(serviceContext.getCreateDate(now));
		pushDictGroup.setModifiedDate(serviceContext.getCreateDate(now));

		pushDictGroup.setServerNo(serverNo);
		pushDictGroup.setCollectionCode(collectionCode);
		pushDictGroup.setGroupCode(groupCode);
		pushDictGroup.setGroupName(groupName);
		pushDictGroup.setGroupNameEN(groupNameEN);
		pushDictGroup.setGroupDescription(groupDescription);
		pushDictGroup.setMethod(method);
		pushDictGroup.setItemCode(itemCode);
		
		return pushDictGroupPersistence.update(pushDictGroup);		
	}	
	
	@Override
	public PushDictGroup deletePushDictGroup(long pushDictGroupId, ServiceContext serviceContext)
			throws UnauthenticationException, UnauthorizationException, NotFoundException {
		// authen
		BackendAuthImpl authImpl = new BackendAuthImpl();

		boolean isAuth = authImpl.isAuth(serviceContext);

		if (!isAuth) {
			throw new UnauthenticationException();
		}

		boolean hasPermission = authImpl.hasResource(serviceContext, ModelNameKeys.WORKINGUNIT_MGT_CENTER,
				ActionKeys.EDIT_DATA);

		if (!hasPermission) {
			throw new UnauthorizationException();
		}

		try {
			return pushDictGroupPersistence.remove(pushDictGroupId);

		} catch (NoSuchPushDictGroupException e) {
			_log.error(e);
			//throw new NotFoundException();
		}
		return null;
	}
	
	@Override
	public List<PushDictGroup> findAll(int start, int end) {
		return pushDictGroupPersistence.findAll(start, end);
	}	
	
	@Override
	public PushDictGroup findByCollectionCode_GroupCode_Method(long groupId, String collectionCode, String groupCode, String method) throws NoSuchPushDictGroupException {
		return pushDictGroupPersistence.findByF_collectionCode_groupCode_Method(groupId, collectionCode, groupCode, method);
	}	
	@Override
	public PushDictGroup findByCollectionCode_GroupCode_ItemCode_Method(long groupId, String collectionCode, String groupCode, String itemCode, String method) throws NoSuchPushDictGroupException {
		return pushDictGroupPersistence.findByF_collectionCode_groupCode_itemCode_Method(groupId, collectionCode, groupCode, itemCode, method);
	}		
	@Override
	public List<PushDictGroup> findByGroupId_ServerNo(long groupId, String serverNo, int start, int end) {
		return pushDictGroupPersistence.findByF_groupId_serverNo(groupId, serverNo, start, end);
	}

	private static Log _log = LogFactoryUtil.getLog(PushDictGroupLocalServiceImpl.class);
}