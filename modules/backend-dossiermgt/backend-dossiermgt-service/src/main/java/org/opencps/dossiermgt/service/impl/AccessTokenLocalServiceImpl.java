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

package org.opencps.dossiermgt.service.impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.opencps.dossiermgt.model.AccessToken;
import org.opencps.dossiermgt.service.base.AccessTokenLocalServiceBaseImpl;
import org.opencps.usermgt.service.util.DateTimeUtils;

/**
 * The implementation of the access token local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.dossiermgt.service.AccessTokenLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see AccessTokenLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.AccessTokenLocalServiceUtil
 */
public class AccessTokenLocalServiceImpl extends AccessTokenLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.dossiermgt.service.AccessTokenLocalServiceUtil} to access the access token local service.
	 */

	public AccessToken addAccessToken(long companyId, long groupId, String token, String className) {
		long tokenId = counterLocalService.increment(AccessToken.class.getName());
		AccessToken accessToken = accessTokenPersistence.create(tokenId);
		Date date = new Date();
		//increment 10 minutes
		Date expireDate = DateTimeUtils.increment(date, 10);
		accessToken.setClassName(className);
		accessToken.setCompanyId(companyId);
		accessToken.setCreateDate(date);
		accessToken.setExpireDate(expireDate);
		accessToken.setGroupId(groupId);
		accessToken.setModifiedDate(date);
		accessToken.setToken(token);
		return accessTokenPersistence.update(accessToken);
	}

	public AccessToken getAccessToken(long groupId, String className) {
		AccessToken accessToken = null;
		try {
			/*accessToken = accessTokenPersistence.findByF_GID_ClassName_First(groupId, className,
					OrderByComparatorFactoryUtil.create("opencps_accesstoken", "expireDate", false));*/
			accessToken = accessTokenPersistence.findByF_GID_ClassName_First(groupId, className, null);
			Date now = new Date();
			if (accessToken.getExpireDate() != null) {
				_log.info(accessToken.getExpireDate());
				_log.info(now);
				_log.info(accessToken.getExpireDate().compareTo(now));
				if (accessToken.getExpireDate().compareTo(now) > 0) {
					return accessToken;
				}else {
					return null;
				}
			}else {
				return null;
			}
		} catch (Exception e) {
			_log.error(e);
		}

		return accessToken;
	}
	
	public void garbageToken() {
		List<AccessToken> accessTokens = new ArrayList<AccessToken>();
		Date date = new Date();
		try {
			accessTokens = accessTokenPersistence.findAll();
			
			if(accessTokens != null) {
				for(AccessToken accessToken : accessTokens) {
					if(Validator.isNull(accessToken.getToken()) || accessToken.getExpireDate() == null) {
						accessTokenPersistence.remove(accessToken);
					}else {
						if(date.compareTo(accessToken.getExpireDate()) >= 0) {
							accessTokenPersistence.remove(accessToken);
						}
					}
					
				}
			}
		} catch (Exception e) {
			_log.debug(e);
		}
	}

	public List<AccessToken> getAccessTokens(long groupId, String className) {
		return accessTokenPersistence.findByF_GID_ClassName(groupId, className);
	}

	private Log _log = LogFactoryUtil.getLog(AccessTokenLocalServiceImpl.class);
}