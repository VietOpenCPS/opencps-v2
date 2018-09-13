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
import org.opencps.synchronization.exception.NoSuchPushCollectionException;
import org.opencps.synchronization.model.PushCollection;
import org.opencps.synchronization.service.base.PushCollectionLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the push collection local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.synchronization.service.PushCollectionLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author trungdk
 * @see PushCollectionLocalServiceBaseImpl
 * @see org.opencps.synchronization.service.PushCollectionLocalServiceUtil
 */
@ProviderType
public class PushCollectionLocalServiceImpl
	extends PushCollectionLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.synchronization.service.PushCollectionLocalServiceUtil} to access the push collection local service.
	 */

	private static Log _log = LogFactoryUtil.getLog(PushCollectionLocalServiceImpl.class);

	@Override
	public PushCollection addPushCollection(
			long userId, 
			long groupId, 
			String serverNo,
			String collectionCode,
			String collectionName,
			String collectionNameEN,
			String description,
			String method,
			String dataForm,
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

		long pushCollectionId = counterLocalService.increment(PushCollection.class.getName());
		
		PushCollection pushCollection = pushCollectionPersistence.create(pushCollectionId);
		
		// Group instance
		pushCollection.setGroupId(groupId);

		// Audit fields
		pushCollection.setUuid(serviceContext.getUuid());
		pushCollection.setCompanyId(user.getCompanyId());
		pushCollection.setUserId(user.getUserId());
		pushCollection.setUserName(user.getFullName());
		pushCollection.setCreateDate(serviceContext.getCreateDate(now));
		pushCollection.setModifiedDate(serviceContext.getCreateDate(now));

		pushCollection.setServerNo(serverNo);
		pushCollection.setCollectionCode(collectionCode);
		pushCollection.setCollectionName(collectionName);
		pushCollection.setCollectionNameEN(collectionNameEN);
		pushCollection.setDescription(description);
		pushCollection.setMethod(method);
		pushCollection.setDataForm(dataForm);
		
		return pushCollectionPersistence.update(pushCollection);
	}
	
	@Override
	public PushCollection updatePushCollection(
			long userId, 
			long groupId, 
			long pushCollectionId,
			String serverNo,
			String collectionCode,
			String collectionName,
			String collectionNameEN,
			String description,
			String method,
			String dataForm,
			ServiceContext serviceContext) throws UnauthenticationException,
			UnauthorizationException, NoSuchUserException, NoSuchPushCollectionException, SystemException {
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
		PushCollection pushCollection = pushCollectionPersistence.fetchByPrimaryKey(pushCollectionId);
		
		// Group instance
		pushCollection.setGroupId(groupId);

		// Audit fields
		pushCollection.setUuid(serviceContext.getUuid());
		pushCollection.setCompanyId(user.getCompanyId());
		pushCollection.setUserId(user.getUserId());
		pushCollection.setUserName(user.getFullName());
		pushCollection.setCreateDate(serviceContext.getCreateDate(now));
		pushCollection.setModifiedDate(serviceContext.getCreateDate(now));

		pushCollection.setServerNo(serverNo);
		pushCollection.setCollectionCode(collectionCode);
		pushCollection.setCollectionName(collectionName);
		pushCollection.setCollectionNameEN(collectionNameEN);
		pushCollection.setDescription(description);
		pushCollection.setMethod(method);
		pushCollection.setDataForm(dataForm);
		
		return pushCollectionPersistence.update(pushCollection);		
	}	
	
	@Override
	public PushCollection deletePushCollection(long pushCollectionId, ServiceContext serviceContext)
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
			return pushCollectionPersistence.remove(pushCollectionId);

		} catch (NoSuchPushCollectionException e) {
			_log.error(e);
			//throw new NotFoundException();
		}

		return null;
	}
	
	@Override
	public List<PushCollection> findAll(int start, int end) {
		return pushCollectionPersistence.findAll(start, end);
	}	
	
	@Override
	public PushCollection findByCollectionCode_Method(long groupId, String collectionCode, String method) throws NoSuchPushCollectionException {
		return pushCollectionPersistence.findByF_collectionCode_Method(groupId, collectionCode, method);
	}
	
	@Override
	public List<PushCollection> findByGroupId_ServerNo(long groupId, String serverNo, int start, int end) {
		return pushCollectionPersistence.findByF_groupId_serverNo(groupId, serverNo, start, end);
	}	
}