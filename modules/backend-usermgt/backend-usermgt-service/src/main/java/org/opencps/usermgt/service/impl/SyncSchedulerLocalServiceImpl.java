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

package org.opencps.usermgt.service.impl;

import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;

import org.opencps.usermgt.model.SyncScheduler;
import org.opencps.usermgt.service.base.SyncSchedulerLocalServiceBaseImpl;

/**
 * The implementation of the sync scheduler local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.usermgt.service.SyncSchedulerLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author khoavu
 * @see SyncSchedulerLocalServiceBaseImpl
 * @see org.opencps.usermgt.service.SyncSchedulerLocalServiceUtil
 */
public class SyncSchedulerLocalServiceImpl
	extends SyncSchedulerLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.usermgt.service.SyncSchedulerLocalServiceUtil} to access the sync scheduler local service.
	 */

	public SyncScheduler getByClassNameAndSyncDate(String className, Date syncDate) {
		return syncSchedulerPersistence.fetchByGID_NAME_SYNC(className, syncDate);
	}

	public SyncScheduler getByClassNameAndTypeCode(String className, String typeCode) {
		return syncSchedulerPersistence.fetchByGID_NAME_TYPE(className, typeCode);
	}

	public List<SyncScheduler> getByF_NAME_RETRY(String className, int retry) {
		return syncSchedulerPersistence.findByF_NAME_RETRY(className, retry);
	}

	public SyncScheduler updateSyncScheduler(long syncSchedulerId, long groupId, String className, String typeCode,
			Date syncDate, int retry, ServiceContext serviceContext) {

		Date now = new Date();

		if (syncSchedulerId > 0) {
			SyncScheduler syncScheduler = syncSchedulerPersistence.fetchByPrimaryKey(syncSchedulerId);

			syncScheduler.setModifiedDate(now);

			if (Validator.isNotNull(className))
				syncScheduler.setClassName(className);
			if (Validator.isNotNull(typeCode))
				syncScheduler.setTypeCode(typeCode);
			if (Validator.isNotNull(syncDate)) {
				syncScheduler.setSyncDate(syncDate);
			} else {
				syncScheduler.setSyncDate(now);
			}

			syncScheduler.setRetry(retry);
			//
			return syncSchedulerPersistence.update(syncScheduler);
		} else {
			syncSchedulerId = counterLocalService.increment(SyncScheduler.class.getName());
			SyncScheduler syncScheduler = syncSchedulerPersistence.create(syncSchedulerId);
			//
			syncScheduler.setCreateDate(now);
			syncScheduler.setModifiedDate(now);
			syncScheduler.setGroupId(groupId);
			//
			syncScheduler.setClassName(className);
			syncScheduler.setTypeCode(typeCode);
			syncScheduler.setSyncDate(syncDate);
			syncScheduler.setRetry(retry);

			return syncSchedulerPersistence.update(syncScheduler);
		}
	}

}