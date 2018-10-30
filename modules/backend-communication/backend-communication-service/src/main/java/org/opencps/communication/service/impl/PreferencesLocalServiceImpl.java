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

package org.opencps.communication.service.impl;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;

import org.opencps.communication.exception.NoSuchPreferencesException;
import org.opencps.communication.model.Preferences;
import org.opencps.communication.service.base.PreferencesLocalServiceBaseImpl;

import backend.auth.api.exception.NotFoundException;

/**
 * The implementation of the preferences local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.communication.service.PreferencesLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author khoavd
 * @see PreferencesLocalServiceBaseImpl
 * @see org.opencps.communication.service.PreferencesLocalServiceUtil
 */
public class PreferencesLocalServiceImpl extends PreferencesLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.communication.service.PreferencesLocalServiceUtil} to access the
	 * preferences local service.
	 */

	private static Log _log = LogFactoryUtil.getLog(PreferencesLocalServiceImpl.class);

	@Indexable(type = IndexableType.REINDEX)
	public Preferences addPreferences(long userId, long groupId, String preferencesData, ServiceContext serviceContext)
			throws NoSuchUserException {

		User user = userPersistence.findByPrimaryKey(userId);

		long preferencesId = counterLocalService.increment(Preferences.class.getName());

		Preferences preferences = preferencesPersistence.create(preferencesId);

		Date now = new Date();

		// Group instance
		preferences.setGroupId(groupId);

		// Audit fields
		preferences.setUuid(serviceContext.getUuid());
		preferences.setCompanyId(user.getCompanyId());
		preferences.setUserId(user.getUserId());
		preferences.setUserName(user.getFullName());
		preferences.setCreateDate(serviceContext.getCreateDate(now));
		preferences.setModifiedDate(serviceContext.getCreateDate(now));

		// Other fields
		preferences.setPreferences(preferencesData);

		preferences.setExpandoBridgeAttributes(serviceContext);

		return preferencesPersistence.update(preferences);
		// return preferences;
	}

	@Indexable(type = IndexableType.DELETE)
	public Preferences deletePreferences(long preferencesId, ServiceContext serviceContext) throws NotFoundException {

		try {
			return preferencesPersistence.remove(preferencesId);
		} catch (NoSuchPreferencesException e) {
			_log.error(e);
		}
		return null;
	}

	@Indexable(type = IndexableType.REINDEX)
	public Preferences updatePreferences(long userId, long preferencesId, String preferencesData,
			ServiceContext serviceContext) throws NoSuchUserException {

		User user = userPersistence.findByPrimaryKey(userId);

		Preferences preferences = preferencesPersistence.fetchByPrimaryKey(preferencesId);

		Date now = new Date();

		// Audit fields
		preferences.setUserId(user.getUserId());
		preferences.setUserName(user.getFullName());
		preferences.setModifiedDate(serviceContext.getCreateDate(now));

		// Other fields
		preferences.setPreferences(preferencesData);

		preferences.setExpandoBridgeAttributes(serviceContext);

		preferencesPersistence.update(preferences);

		return preferences;
	}

	public Preferences fetchByF_userId(long groupId, long userId) {

		return preferencesPersistence.fetchByF_userId(groupId, userId);
	}

	// super_admin Generators
	@Indexable(type = IndexableType.DELETE)
	public Preferences adminProcessDelete(Long id) {

		Preferences object = preferencesPersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		} else {
			preferencesPersistence.remove(object);
		}

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
	public Preferences adminProcessData(JSONObject objectData) {

		Preferences object = null;

		if (objectData.getLong("preferencesId") > 0) {

			object = preferencesPersistence.fetchByPrimaryKey(objectData.getLong("preferencesId"));

			object.setModifiedDate(new Date());

		} else {

			long id = CounterLocalServiceUtil.increment(Preferences.class.getName());

			object = preferencesPersistence.create(id);

			object.setGroupId(objectData.getLong("groupId"));
			object.setCompanyId(objectData.getLong("companyId"));
			object.setCreateDate(new Date());

		}

		object.setUserId(objectData.getLong("userId"));

		object.setPreferences(objectData.getString("preferences"));

		preferencesPersistence.update(object);

		return object;
	}

}