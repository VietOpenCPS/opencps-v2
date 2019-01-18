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

import org.opencps.usermgt.model.UserTrackPath;
import org.opencps.usermgt.service.base.UserTrackPathLocalServiceBaseImpl;

/**
 * The implementation of the user track path local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.usermgt.service.UserTrackPathLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author khoavu
 * @see UserTrackPathLocalServiceBaseImpl
 * @see org.opencps.usermgt.service.UserTrackPathLocalServiceUtil
 */
public class UserTrackPathLocalServiceImpl
	extends UserTrackPathLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.usermgt.service.UserTrackPathLocalServiceUtil} to access the user track path local service.
	 */
	
	public UserTrackPath updateUserTrackPath(long companyId, long userTrackPathId, long userLoginId, String path, Date pathDate) {
		UserTrackPath userTrackPath = null;
		if (userTrackPathId == 0) {
			userTrackPathId = counterLocalService.increment(UserTrackPath.class.getName());
			userTrackPath = userTrackPathPersistence.create(userTrackPathId);
			
			userTrackPath.setModifiedDate(new Date());
			userTrackPath.setCompanyId(companyId);
			userTrackPath.setUserLoginId(userLoginId);
			userTrackPath.setPath(path);
			userTrackPath.setPathDate(pathDate);
		}
		else {
			userTrackPath = userTrackPathPersistence.fetchByPrimaryKey(userTrackPathId);
			userTrackPath.setModifiedDate(new Date());
			userTrackPath.setCompanyId(companyId);
			userTrackPath.setUserLoginId(userLoginId);
			userTrackPath.setPath(path);
			userTrackPath.setPathDate(pathDate);			
		}
		
		return userTrackPathPersistence.update(userTrackPath);
	}
}