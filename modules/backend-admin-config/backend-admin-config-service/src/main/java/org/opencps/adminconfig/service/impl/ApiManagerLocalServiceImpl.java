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

import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;

import org.opencps.adminconfig.model.ApiManager;
import org.opencps.adminconfig.service.base.ApiManagerLocalServiceBaseImpl;

/**
 * The implementation of the api manager local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.adminconfig.service.ApiManagerLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author binhth
 * @see ApiManagerLocalServiceBaseImpl
 * @see org.opencps.adminconfig.service.ApiManagerLocalServiceUtil
 */
public class ApiManagerLocalServiceImpl extends ApiManagerLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.adminconfig.service.ApiManagerLocalServiceUtil} to access the api manager local service.
	 */
	
	public ApiManager updateApiManager(
			long userId, long groupId, long apiManagerId, String apiCode,
			String apiDescription, String apiName, int apiStatus, String className) {
			ApiManager apiManager = null;
			Date now = new Date();
			
			if (apiManagerId == 0) {
				apiManagerId = counterLocalService.increment(ApiManager.class.getName());
				apiManager = apiManagerPersistence.create(apiManagerId);
				
				apiManager.setCreateDate(now);
				apiManager.setModifiedDate(now);
				apiManager.setUserId(userId);
				apiManager.setGroupId(groupId);
				apiManager.setApiCode(apiCode);
				apiManager.setApiDescription(apiDescription);
				apiManager.setApiName(apiName);
				apiManager.setApiStatus(apiStatus);
				apiManager.setClassName(className);
			}
			else {
				apiManager = apiManagerPersistence.fetchByPrimaryKey(apiManagerId);
				
				apiManager.setModifiedDate(now);
				apiManager.setUserId(userId);
				apiManager.setGroupId(groupId);
				apiManager.setApiCode(apiCode);
				apiManager.setApiDescription(apiDescription);
				apiManager.setApiName(apiName);
				apiManager.setApiStatus(apiStatus);
				apiManager.setClassName(className);
			}
			return apiManagerPersistence.update(apiManager);
		}
	
	public List<ApiManager> findByG(long groupId) {
		return apiManagerPersistence.findByF_GID(groupId);
	}
	
	public int countByG(long groupId) {
		return apiManagerPersistence.countByF_GID(groupId);
	}
	
	public ApiManager findByApiCode(String apiCode) {
		ApiManager apiManager = apiManagerPersistence.fetchByF_apiCode(apiCode);
		if (Validator.isNull(apiManager)) {
			return null;
		}
		return apiManager;
	}
}