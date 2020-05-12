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

package org.opencps.usermgt.service.persistence.impl;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

import org.opencps.usermgt.model.TrackClient;
import org.opencps.usermgt.service.persistence.TrackClientPersistence;

import java.lang.reflect.Field;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author khoavu
 * @generated
 */
public class TrackClientFinderBaseImpl extends BasePersistenceImpl<TrackClient> {
	public TrackClientFinderBaseImpl() {
		setModelClass(TrackClient.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	@Override
	public Set<String> getBadColumnNames() {
		return getTrackClientPersistence().getBadColumnNames();
	}

	/**
	 * Returns the track client persistence.
	 *
	 * @return the track client persistence
	 */
	public TrackClientPersistence getTrackClientPersistence() {
		return trackClientPersistence;
	}

	/**
	 * Sets the track client persistence.
	 *
	 * @param trackClientPersistence the track client persistence
	 */
	public void setTrackClientPersistence(
		TrackClientPersistence trackClientPersistence) {
		this.trackClientPersistence = trackClientPersistence;
	}

	@BeanReference(type = TrackClientPersistence.class)
	protected TrackClientPersistence trackClientPersistence;
	private static final Log _log = LogFactoryUtil.getLog(TrackClientFinderBaseImpl.class);
}