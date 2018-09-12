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
import org.opencps.synchronization.exception.NoSuchSyncQueueException;
import org.opencps.synchronization.model.SyncQueue;
import org.opencps.synchronization.service.base.SyncQueueLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the sync queue local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.synchronization.service.SyncQueueLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author trungdk
 * @see SyncQueueLocalServiceBaseImpl
 * @see org.opencps.synchronization.service.SyncQueueLocalServiceUtil
 */
@ProviderType
public class SyncQueueLocalServiceImpl extends SyncQueueLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.synchronization.service.SyncQueueLocalServiceUtil} to access the sync queue local service.
	 */

	private static Log _log = LogFactoryUtil.getLog(SyncQueueLocalServiceImpl.class);

	@Override
	public SyncQueue addSyncQueue(
			long userId, 
			long groupId, 
			String serverNo,
			String className,
			String jsonObject,
			int status,
			int retryCount,
			int priority,
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

		long syncQueueId = counterLocalService.increment(SyncQueue.class.getName());
		
		SyncQueue syncQueue = syncQueuePersistence.create(syncQueueId);
		
		// Group instance
		syncQueue.setGroupId(groupId);

		// Audit fields
		syncQueue.setUuid(serviceContext.getUuid());
		syncQueue.setCompanyId(user.getCompanyId());
		syncQueue.setUserId(user.getUserId());
		syncQueue.setUserName(user.getFullName());
		syncQueue.setCreateDate(serviceContext.getCreateDate(now));
		syncQueue.setModifiedDate(serviceContext.getCreateDate(now));

		syncQueue.setServerNo(serverNo);
		syncQueue.setClassName(className);
		syncQueue.setJsonObject(jsonObject);
		syncQueue.setStatus(status);
		syncQueue.setRetryCount(retryCount);
		syncQueue.setPriority(priority);
		syncQueue.setMethod(method);
		
		return syncQueuePersistence.update(syncQueue);
	}
	
	@Override
	public SyncQueue updateSyncQueue(
			long userId, 
			long groupId, 
			long syncQueueId,
			String serverNo,
			String className,
			String jsonObject,
			int status,
			int retryCount,
			int priority,
			String method,
			ServiceContext serviceContext) throws UnauthenticationException,
			UnauthorizationException, NoSuchUserException, NoSuchSyncQueueException, SystemException {
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
		SyncQueue syncQueue = syncQueuePersistence.fetchByPrimaryKey(syncQueueId);
		
		// Group instance
		syncQueue.setGroupId(groupId);

		// Audit fields
		syncQueue.setUuid(serviceContext.getUuid());
		syncQueue.setCompanyId(user.getCompanyId());
		syncQueue.setUserId(user.getUserId());
		syncQueue.setUserName(user.getFullName());
		syncQueue.setCreateDate(serviceContext.getCreateDate(now));
		syncQueue.setModifiedDate(serviceContext.getCreateDate(now));

		syncQueue.setServerNo(serverNo);
		syncQueue.setClassName(className);
		syncQueue.setJsonObject(jsonObject);
		syncQueue.setStatus(status);
		syncQueue.setRetryCount(retryCount);
		syncQueue.setPriority(priority);
		syncQueue.setMethod(method);
		
		return syncQueuePersistence.update(syncQueue);		
	}	
	
	@Override
	public SyncQueue deleteSyncQueue(long syncQueueId, ServiceContext serviceContext)
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
			return syncQueuePersistence.remove(syncQueueId);

		} catch (NoSuchSyncQueueException e) {
			_log.error(e);
			//throw new NotFoundException();
		}
		return null;
	}
	
	@Override
	public List<SyncQueue> findAll(int start, int end) {
		return syncQueuePersistence.findAll(start, end);
	}	
	
	@Override
	public List<SyncQueue> findByF_className_Method(long groupId, String className, String method, int start, int end) {
		return syncQueuePersistence.findByF_className_Method(groupId, className, method, start, end);
	}	
	
	@Override
	public List<SyncQueue> findByF_groupId_serverNo(long groupId, String serverNo, int start, int end) {
		return syncQueuePersistence.findByF_groupId_serverNo(groupId, serverNo, start, end);
	}	
}