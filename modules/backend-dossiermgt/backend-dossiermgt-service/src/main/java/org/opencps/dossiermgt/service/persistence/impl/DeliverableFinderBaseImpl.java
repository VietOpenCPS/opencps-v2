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

package org.opencps.dossiermgt.service.persistence.impl;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

import org.opencps.dossiermgt.model.Deliverable;
import org.opencps.dossiermgt.service.persistence.DeliverablePersistence;

import java.lang.reflect.Field;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author huymq
 * @generated
 */
public class DeliverableFinderBaseImpl extends BasePersistenceImpl<Deliverable> {
	public DeliverableFinderBaseImpl() {
		setModelClass(Deliverable.class);

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
		return getDeliverablePersistence().getBadColumnNames();
	}

	/**
	 * Returns the deliverable persistence.
	 *
	 * @return the deliverable persistence
	 */
	public DeliverablePersistence getDeliverablePersistence() {
		return deliverablePersistence;
	}

	/**
	 * Sets the deliverable persistence.
	 *
	 * @param deliverablePersistence the deliverable persistence
	 */
	public void setDeliverablePersistence(
		DeliverablePersistence deliverablePersistence) {
		this.deliverablePersistence = deliverablePersistence;
	}

	@BeanReference(type = DeliverablePersistence.class)
	protected DeliverablePersistence deliverablePersistence;
	private static final Log _log = LogFactoryUtil.getLog(DeliverableFinderBaseImpl.class);
}