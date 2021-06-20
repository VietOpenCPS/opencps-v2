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

package org.opencps.adminconfig.service.impl;

import java.util.Date;
import java.util.List;

import org.opencps.adminconfig.model.ApiRole;
import org.opencps.adminconfig.service.base.ApiRoleLocalServiceBaseImpl;

/**
 * The implementation of the api role local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.adminconfig.service.ApiRoleLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author binhth
 * @see ApiRoleLocalServiceBaseImpl
 * @see org.opencps.adminconfig.service.ApiRoleLocalServiceUtil
 */
public class ApiRoleLocalServiceImpl extends ApiRoleLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.adminconfig.service.ApiRoleLocalServiceUtil} to access the api role local service.
	 */
	
	public ApiRole updateApiRole(
			long userId, long groupId, long apiRoleId, String apiCode,
			long roleId, String roleCode, int apiRoleStatus) {
			ApiRole apiRole = null;
			Date now = new Date();
			
			if (apiRoleId == 0) {
				apiRoleId = counterLocalService.increment(ApiRole.class.getName());
				apiRole = apiRolePersistence.create(apiRoleId);
				
				apiRole.setCreateDate(now);
				apiRole.setModifiedDate(now);
				apiRole.setUserId(userId);
				apiRole.setGroupId(groupId);
				apiRole.setApiCode(apiCode);
				apiRole.setRoleId((int)roleId);
				apiRole.setRoleCode(roleCode);
				apiRole.setApiRoleStatus(apiRoleStatus);
			}
			else {
				apiRole = apiRolePersistence.fetchByPrimaryKey(apiRoleId);
				
				apiRole.setModifiedDate(now);
				apiRole.setUserId(userId);
				apiRole.setGroupId(groupId);
				apiRole.setApiCode(apiCode);
				apiRole.setRoleId((int)roleId);
				apiRole.setRoleCode(roleCode);
				apiRole.setApiRoleStatus(apiRoleStatus);
			}
			return apiRolePersistence.update(apiRole);
		}
	
	public List<ApiRole> findByG(long groupId) {
		return apiRolePersistence.findByF_GID(groupId);
	}
	
	public int countByG(long groupId) {
		return apiRolePersistence.countByF_GID(groupId);
	}
}