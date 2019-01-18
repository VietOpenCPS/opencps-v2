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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.Date;

import org.opencps.usermgt.model.UserLogin;
import org.opencps.usermgt.service.base.UserLoginLocalServiceBaseImpl;

/**
 * The implementation of the user login local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.usermgt.service.UserLoginLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author khoavu
 * @see UserLoginLocalServiceBaseImpl
 * @see org.opencps.usermgt.service.UserLoginLocalServiceUtil
 */
public class UserLoginLocalServiceImpl extends UserLoginLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.usermgt.service.UserLoginLocalServiceUtil} to access the user login local service.
	 */
	public UserLogin updateUserLogin(long companyId, long groupId, long userId, String userName, 
			Date createDate, Date modifiedDate,
			long userLoginId,
			String sessionId,
			int hits, Date logout, String ipAddress)
			throws PortalException, SystemException {
		UserLogin userLogin = null;
		
		if (userLoginId == 0) {
			userLoginId = counterLocalService.increment(UserLogin.class.getName());
			userLogin = userLoginPersistence.create(userLoginId);
			
			userLogin.setCompanyId(companyId);
			userLogin.setGroupId(groupId);
			userLogin.setUserId(userId);
			userLogin.setUserName(userName);
			userLogin.setCreateDate(createDate);
			userLogin.setModifiedDate(modifiedDate);
			
			userLogin.setSessionId(sessionId);
			userLogin.setHits(hits);
			userLogin.setLogout(logout);
			userLogin.setIpAddress(ipAddress);
			userLogin.setOnline(true);
		}
		else {
			userLogin = userLoginPersistence.fetchByPrimaryKey(userLoginId);
			userLogin.setModifiedDate(modifiedDate);
			
			userLogin.setSessionId(sessionId);
			userLogin.setHits(hits);
			userLogin.setLogout(logout);
			userLogin.setIpAddress(ipAddress);
			
			userLogin.setOnline(true);
		}
		
		return userLoginPersistence.update(userLogin);
	}
	
	public UserLogin updateUserLogin(long companyId, long groupId, long userId, String userName, 
			Date createDate, Date modifiedDate,
			long userLoginId,
			String sessionId,
			int hits, Date logout, String ipAddress,
			boolean online)
			throws PortalException, SystemException {
		UserLogin userLogin = null;
		
		if (userLoginId == 0) {
			userLoginId = counterLocalService.increment(UserLogin.class.getName());
			userLogin = userLoginPersistence.create(userLoginId);
			
			userLogin.setCompanyId(companyId);
			userLogin.setGroupId(groupId);
			userLogin.setUserId(userId);
			userLogin.setUserName(userName);
			userLogin.setCreateDate(createDate);
			userLogin.setModifiedDate(modifiedDate);
			
			userLogin.setSessionId(sessionId);
			userLogin.setHits(hits);
			userLogin.setLogout(logout);
			userLogin.setIpAddress(ipAddress);
			userLogin.setOnline(online);
		}
		else {
			userLogin = userLoginPersistence.fetchByPrimaryKey(userLoginId);
			userLogin.setModifiedDate(modifiedDate);
			
			userLogin.setSessionId(sessionId);
			userLogin.setHits(hits);
			userLogin.setLogout(logout);
			userLogin.setIpAddress(ipAddress);
			userLogin.setOnline(false);
		}
		
		return userLoginPersistence.update(userLogin);
	}
	
	public UserLogin traceLogout(long userId, String sessionId) {
		UserLogin userLogin = userLoginPersistence.fetchByU_S(userId, sessionId);
		if (userLogin != null) {
			if (userLogin.getLogout() != null) {
				userLogin.setLogout(new Date());
			}
			userLogin.setOnline(false);
		}
		return userLogin;
	}
	
	public UserLogin fetchByU_S(long userId, String sessionId) {
		return userLoginPersistence.fetchByU_S(userId, sessionId);
	}
}