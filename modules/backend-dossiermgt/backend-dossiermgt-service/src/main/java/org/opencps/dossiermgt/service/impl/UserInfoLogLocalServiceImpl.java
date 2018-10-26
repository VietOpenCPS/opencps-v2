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

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;

import org.opencps.dossiermgt.model.UserInfoLog;
import org.opencps.dossiermgt.service.base.UserInfoLogLocalServiceBaseImpl;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the user info log local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.dossiermgt.service.UserInfoLogLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see UserInfoLogLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.UserInfoLogLocalServiceUtil
 */
@ProviderType
public class UserInfoLogLocalServiceImpl extends UserInfoLogLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.dossiermgt.service.UserInfoLogLocalServiceUtil} to access the
	 * user info log local service.
	 */

	public UserInfoLog addUserInfoLog(long userId, JSONObject jsonData, ServiceContext serviceContext) {

		Date now = new Date();

		long userInfoLogId = counterLocalService.increment(UserInfoLog.class.getName());

		UserInfoLog userInfoLog = userInfoLogPersistence.create(userInfoLogId);

		userInfoLog.setCreateDate(now);
		userInfoLog.setUserId(userId);
		userInfoLog.setPayload(jsonData.toJSONString());

		return userInfoLogPersistence.update(userInfoLog);

	}

	public List<UserInfoLog> getUserInfoLogs(long userId) {

		return userInfoLogPersistence.findByF_USERID(userId, 0, 5);

	}

	// super_admin Generators
	public UserInfoLog adminProcessDelete(Long id) {

		UserInfoLog object = userInfoLogPersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		} else {
			userInfoLogPersistence.remove(object);
		}

		return object;
	}

	public UserInfoLog adminProcessData(JSONObject objectData) {

		UserInfoLog object = null;

		if (objectData.getLong("UserInfoLogId") > 0) {

			object = userInfoLogPersistence.fetchByPrimaryKey(objectData.getLong("userLogId"));

		} else {

			long id = CounterLocalServiceUtil.increment(UserInfoLog.class.getName());

			object = userInfoLogPersistence.create(id);

			object.setCreateDate(new Date());
		}

		object.setUserId(objectData.getLong("userId"));
		object.setPayload(objectData.getString("payload"));

		userInfoLogPersistence.update(object);

		return object;
	}
}