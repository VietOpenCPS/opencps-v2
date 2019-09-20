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

import org.opencps.dossiermgt.model.Booking;
import org.opencps.dossiermgt.service.persistence.BookingPersistence;

import java.lang.reflect.Field;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author huymq
 * @generated
 */
public class BookingFinderBaseImpl extends BasePersistenceImpl<Booking> {
	public BookingFinderBaseImpl() {
		setModelClass(Booking.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");
			dbColumnNames.put("state", "state_");

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
		return getBookingPersistence().getBadColumnNames();
	}

	/**
	 * Returns the booking persistence.
	 *
	 * @return the booking persistence
	 */
	public BookingPersistence getBookingPersistence() {
		return bookingPersistence;
	}

	/**
	 * Sets the booking persistence.
	 *
	 * @param bookingPersistence the booking persistence
	 */
	public void setBookingPersistence(BookingPersistence bookingPersistence) {
		this.bookingPersistence = bookingPersistence;
	}

	@BeanReference(type = BookingPersistence.class)
	protected BookingPersistence bookingPersistence;
	private static final Log _log = LogFactoryUtil.getLog(BookingFinderBaseImpl.class);
}