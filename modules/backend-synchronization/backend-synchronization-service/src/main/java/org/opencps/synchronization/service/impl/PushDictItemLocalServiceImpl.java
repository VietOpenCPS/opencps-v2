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
import org.opencps.synchronization.exception.NoSuchPushDictItemException;
import org.opencps.synchronization.model.PushDictItem;
import org.opencps.synchronization.service.base.PushDictItemLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the push dict item local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.synchronization.service.PushDictItemLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author trungdk
 * @see PushDictItemLocalServiceBaseImpl
 * @see org.opencps.synchronization.service.PushDictItemLocalServiceUtil
 */
@ProviderType
public class PushDictItemLocalServiceImpl
	extends PushDictItemLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.synchronization.service.PushDictItemLocalServiceUtil} to access the push dict item local service.
	 */

	private static Log _log = LogFactoryUtil.getLog(PushDictItemLocalServiceImpl.class);

	@Override
	public PushDictItem addPushDictItem(
			long userId, 
			long groupId, 
			String serverNo,
			String collectionCode,
			String itemCode, String itemName,
			String itemNameEN, 
			String itemDescription, 
			String parentItemCode, 
			String sibling,
			String method,
			String metaData,
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

		long pushDictItemId = counterLocalService.increment(PushDictItem.class.getName());
		
		PushDictItem pushDictItem = pushDictItemPersistence.create(pushDictItemId);
		
		// Group instance
		pushDictItem.setGroupId(groupId);

		// Audit fields
		pushDictItem.setUuid(serviceContext.getUuid());
		pushDictItem.setCompanyId(user.getCompanyId());
		pushDictItem.setUserId(user.getUserId());
		pushDictItem.setUserName(user.getFullName());
		pushDictItem.setCreateDate(serviceContext.getCreateDate(now));
		pushDictItem.setModifiedDate(serviceContext.getCreateDate(now));

		pushDictItem.setServerNo(serverNo);
		pushDictItem.setCollectionCode(collectionCode);
		pushDictItem.setItemCode(itemCode);
		pushDictItem.setItemName(itemName);
		pushDictItem.setItemNameEN(itemNameEN);
		pushDictItem.setItemDescription(itemDescription);
		pushDictItem.setParentItemCode(parentItemCode);
		pushDictItem.setSibling(sibling);
		pushDictItem.setMethod(method);
		pushDictItem.setMetaData(metaData);
		
		return pushDictItemPersistence.update(pushDictItem);
	}
	
	@Override
	public PushDictItem updatePushDictItem(
			long userId, 
			long groupId, 
			long pushDictItemId,
			String serverNo,
			String collectionCode,
			String itemCode, String itemName,
			String itemNameEN, 
			String itemDescription, 
			String parentItemCode, 
			String sibling,
			String method,
			String metaData,
			ServiceContext serviceContext) throws UnauthenticationException,
			UnauthorizationException, NoSuchUserException, NoSuchPushDictItemException, SystemException {
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
		PushDictItem pushDictItem = pushDictItemPersistence.fetchByPrimaryKey(pushDictItemId);
		
		// Group instance
		pushDictItem.setGroupId(groupId);

		// Audit fields
		pushDictItem.setUuid(serviceContext.getUuid());
		pushDictItem.setCompanyId(user.getCompanyId());
		pushDictItem.setUserId(user.getUserId());
		pushDictItem.setUserName(user.getFullName());
		pushDictItem.setCreateDate(serviceContext.getCreateDate(now));
		pushDictItem.setModifiedDate(serviceContext.getCreateDate(now));

		pushDictItem.setServerNo(serverNo);
		pushDictItem.setCollectionCode(collectionCode);
		pushDictItem.setItemCode(itemCode);
		pushDictItem.setItemName(itemName);
		pushDictItem.setItemNameEN(itemNameEN);
		pushDictItem.setItemDescription(itemDescription);
		pushDictItem.setParentItemCode(parentItemCode);
		pushDictItem.setSibling(sibling);
		pushDictItem.setMethod(method);
		pushDictItem.setMetaData(metaData);
		
		return pushDictItemPersistence.update(pushDictItem);		
	}	
	
	@Override
	public PushDictItem deletePushDictItem(long pushDictItemId, ServiceContext serviceContext)
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
			return pushDictItemPersistence.remove(pushDictItemId);

		} catch (NoSuchPushDictItemException e) {
			_log.error(e);
			//throw new NotFoundException();
		}
		return null;
	}
	
	@Override
	public List<PushDictItem> findAll(int start, int end) {
		return pushDictItemPersistence.findAll(start, end);
	}
	
	@Override
	public PushDictItem findByCollectionCode_ItemCode_Method(long groupId, String collectionCode, String itemCode, String method) throws NoSuchPushDictItemException {
		return pushDictItemPersistence.findByF_collectionCode_itemCode_Method(groupId, collectionCode, itemCode, method);
	}

	@Override
	public List<PushDictItem> findByGroupId_ServerNo(long groupId, String serverNo, int start, int end) {
		return pushDictItemPersistence.findByF_groupId_serverNo(groupId, serverNo, start, end);
	}
}