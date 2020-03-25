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

import java.util.Date;

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

}