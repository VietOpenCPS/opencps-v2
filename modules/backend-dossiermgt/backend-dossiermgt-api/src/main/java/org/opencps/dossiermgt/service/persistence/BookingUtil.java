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

package org.opencps.dossiermgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.dossiermgt.model.Booking;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.Date;
import java.util.List;

/**
 * The persistence utility for the booking service. This utility wraps {@link org.opencps.dossiermgt.service.persistence.impl.BookingPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see BookingPersistence
 * @see org.opencps.dossiermgt.service.persistence.impl.BookingPersistenceImpl
 * @generated
 */
@ProviderType
public class BookingUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(Booking booking) {
		getPersistence().clearCache(booking);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Booking> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Booking> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Booking> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Booking> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Booking update(Booking booking) {
		return getPersistence().update(booking);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Booking update(Booking booking, ServiceContext serviceContext) {
		return getPersistence().update(booking, serviceContext);
	}

	/**
	* Returns all the bookings where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching bookings
	*/
	public static List<Booking> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the bookings where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of bookings
	* @param end the upper bound of the range of bookings (not inclusive)
	* @return the range of matching bookings
	*/
	public static List<Booking> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the bookings where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of bookings
	* @param end the upper bound of the range of bookings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching bookings
	*/
	public static List<Booking> findByUuid(String uuid, int start, int end,
		OrderByComparator<Booking> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the bookings where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of bookings
	* @param end the upper bound of the range of bookings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching bookings
	*/
	public static List<Booking> findByUuid(String uuid, int start, int end,
		OrderByComparator<Booking> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first booking in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching booking
	* @throws NoSuchBookingException if a matching booking could not be found
	*/
	public static Booking findByUuid_First(String uuid,
		OrderByComparator<Booking> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchBookingException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first booking in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching booking, or <code>null</code> if a matching booking could not be found
	*/
	public static Booking fetchByUuid_First(String uuid,
		OrderByComparator<Booking> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last booking in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching booking
	* @throws NoSuchBookingException if a matching booking could not be found
	*/
	public static Booking findByUuid_Last(String uuid,
		OrderByComparator<Booking> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchBookingException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last booking in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching booking, or <code>null</code> if a matching booking could not be found
	*/
	public static Booking fetchByUuid_Last(String uuid,
		OrderByComparator<Booking> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the bookings before and after the current booking in the ordered set where uuid = &#63;.
	*
	* @param bookingId the primary key of the current booking
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next booking
	* @throws NoSuchBookingException if a booking with the primary key could not be found
	*/
	public static Booking[] findByUuid_PrevAndNext(long bookingId, String uuid,
		OrderByComparator<Booking> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchBookingException {
		return getPersistence()
				   .findByUuid_PrevAndNext(bookingId, uuid, orderByComparator);
	}

	/**
	* Removes all the bookings where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of bookings where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching bookings
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the booking where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchBookingException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching booking
	* @throws NoSuchBookingException if a matching booking could not be found
	*/
	public static Booking findByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchBookingException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the booking where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching booking, or <code>null</code> if a matching booking could not be found
	*/
	public static Booking fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the booking where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching booking, or <code>null</code> if a matching booking could not be found
	*/
	public static Booking fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the booking where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the booking that was removed
	*/
	public static Booking removeByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchBookingException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of bookings where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching bookings
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the bookings where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching bookings
	*/
	public static List<Booking> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the bookings where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of bookings
	* @param end the upper bound of the range of bookings (not inclusive)
	* @return the range of matching bookings
	*/
	public static List<Booking> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the bookings where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of bookings
	* @param end the upper bound of the range of bookings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching bookings
	*/
	public static List<Booking> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<Booking> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the bookings where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of bookings
	* @param end the upper bound of the range of bookings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching bookings
	*/
	public static List<Booking> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<Booking> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first booking in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching booking
	* @throws NoSuchBookingException if a matching booking could not be found
	*/
	public static Booking findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Booking> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchBookingException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first booking in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching booking, or <code>null</code> if a matching booking could not be found
	*/
	public static Booking fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Booking> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last booking in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching booking
	* @throws NoSuchBookingException if a matching booking could not be found
	*/
	public static Booking findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Booking> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchBookingException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last booking in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching booking, or <code>null</code> if a matching booking could not be found
	*/
	public static Booking fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Booking> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the bookings before and after the current booking in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param bookingId the primary key of the current booking
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next booking
	* @throws NoSuchBookingException if a booking with the primary key could not be found
	*/
	public static Booking[] findByUuid_C_PrevAndNext(long bookingId,
		String uuid, long companyId,
		OrderByComparator<Booking> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchBookingException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(bookingId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the bookings where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of bookings where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching bookings
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns the booking where groupId = &#63; and className = &#63; and classPK = &#63; or throws a {@link NoSuchBookingException} if it could not be found.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @return the matching booking
	* @throws NoSuchBookingException if a matching booking could not be found
	*/
	public static Booking findByF_CLASS_NAME_PK(long groupId, String className,
		long classPK)
		throws org.opencps.dossiermgt.exception.NoSuchBookingException {
		return getPersistence()
				   .findByF_CLASS_NAME_PK(groupId, className, classPK);
	}

	/**
	* Returns the booking where groupId = &#63; and className = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @return the matching booking, or <code>null</code> if a matching booking could not be found
	*/
	public static Booking fetchByF_CLASS_NAME_PK(long groupId,
		String className, long classPK) {
		return getPersistence()
				   .fetchByF_CLASS_NAME_PK(groupId, className, classPK);
	}

	/**
	* Returns the booking where groupId = &#63; and className = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching booking, or <code>null</code> if a matching booking could not be found
	*/
	public static Booking fetchByF_CLASS_NAME_PK(long groupId,
		String className, long classPK, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_CLASS_NAME_PK(groupId, className, classPK,
			retrieveFromCache);
	}

	/**
	* Removes the booking where groupId = &#63; and className = &#63; and classPK = &#63; from the database.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @return the booking that was removed
	*/
	public static Booking removeByF_CLASS_NAME_PK(long groupId,
		String className, long classPK)
		throws org.opencps.dossiermgt.exception.NoSuchBookingException {
		return getPersistence()
				   .removeByF_CLASS_NAME_PK(groupId, className, classPK);
	}

	/**
	* Returns the number of bookings where groupId = &#63; and className = &#63; and classPK = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @return the number of matching bookings
	*/
	public static int countByF_CLASS_NAME_PK(long groupId, String className,
		long classPK) {
		return getPersistence()
				   .countByF_CLASS_NAME_PK(groupId, className, classPK);
	}

	/**
	* Returns the booking where groupId = &#63; and serviceCode = &#63; or throws a {@link NoSuchBookingException} if it could not be found.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @return the matching booking
	* @throws NoSuchBookingException if a matching booking could not be found
	*/
	public static Booking findByF_GID_SC_DATE_MAX(long groupId,
		String serviceCode)
		throws org.opencps.dossiermgt.exception.NoSuchBookingException {
		return getPersistence().findByF_GID_SC_DATE_MAX(groupId, serviceCode);
	}

	/**
	* Returns the booking where groupId = &#63; and serviceCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @return the matching booking, or <code>null</code> if a matching booking could not be found
	*/
	public static Booking fetchByF_GID_SC_DATE_MAX(long groupId,
		String serviceCode) {
		return getPersistence().fetchByF_GID_SC_DATE_MAX(groupId, serviceCode);
	}

	/**
	* Returns the booking where groupId = &#63; and serviceCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching booking, or <code>null</code> if a matching booking could not be found
	*/
	public static Booking fetchByF_GID_SC_DATE_MAX(long groupId,
		String serviceCode, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_GID_SC_DATE_MAX(groupId, serviceCode,
			retrieveFromCache);
	}

	/**
	* Removes the booking where groupId = &#63; and serviceCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @return the booking that was removed
	*/
	public static Booking removeByF_GID_SC_DATE_MAX(long groupId,
		String serviceCode)
		throws org.opencps.dossiermgt.exception.NoSuchBookingException {
		return getPersistence().removeByF_GID_SC_DATE_MAX(groupId, serviceCode);
	}

	/**
	* Returns the number of bookings where groupId = &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @return the number of matching bookings
	*/
	public static int countByF_GID_SC_DATE_MAX(long groupId, String serviceCode) {
		return getPersistence().countByF_GID_SC_DATE_MAX(groupId, serviceCode);
	}

	/**
	* Returns all the bookings where groupId = &#63; and bookingDate = &#63; and online = &#63;.
	*
	* @param groupId the group ID
	* @param bookingDate the booking date
	* @param online the online
	* @return the matching bookings
	*/
	public static List<Booking> findByF_GID_BOOKING_DATE(long groupId,
		Date bookingDate, boolean online) {
		return getPersistence()
				   .findByF_GID_BOOKING_DATE(groupId, bookingDate, online);
	}

	/**
	* Returns a range of all the bookings where groupId = &#63; and bookingDate = &#63; and online = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param bookingDate the booking date
	* @param online the online
	* @param start the lower bound of the range of bookings
	* @param end the upper bound of the range of bookings (not inclusive)
	* @return the range of matching bookings
	*/
	public static List<Booking> findByF_GID_BOOKING_DATE(long groupId,
		Date bookingDate, boolean online, int start, int end) {
		return getPersistence()
				   .findByF_GID_BOOKING_DATE(groupId, bookingDate, online,
			start, end);
	}

	/**
	* Returns an ordered range of all the bookings where groupId = &#63; and bookingDate = &#63; and online = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param bookingDate the booking date
	* @param online the online
	* @param start the lower bound of the range of bookings
	* @param end the upper bound of the range of bookings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching bookings
	*/
	public static List<Booking> findByF_GID_BOOKING_DATE(long groupId,
		Date bookingDate, boolean online, int start, int end,
		OrderByComparator<Booking> orderByComparator) {
		return getPersistence()
				   .findByF_GID_BOOKING_DATE(groupId, bookingDate, online,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the bookings where groupId = &#63; and bookingDate = &#63; and online = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param bookingDate the booking date
	* @param online the online
	* @param start the lower bound of the range of bookings
	* @param end the upper bound of the range of bookings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching bookings
	*/
	public static List<Booking> findByF_GID_BOOKING_DATE(long groupId,
		Date bookingDate, boolean online, int start, int end,
		OrderByComparator<Booking> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_GID_BOOKING_DATE(groupId, bookingDate, online,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first booking in the ordered set where groupId = &#63; and bookingDate = &#63; and online = &#63;.
	*
	* @param groupId the group ID
	* @param bookingDate the booking date
	* @param online the online
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching booking
	* @throws NoSuchBookingException if a matching booking could not be found
	*/
	public static Booking findByF_GID_BOOKING_DATE_First(long groupId,
		Date bookingDate, boolean online,
		OrderByComparator<Booking> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchBookingException {
		return getPersistence()
				   .findByF_GID_BOOKING_DATE_First(groupId, bookingDate,
			online, orderByComparator);
	}

	/**
	* Returns the first booking in the ordered set where groupId = &#63; and bookingDate = &#63; and online = &#63;.
	*
	* @param groupId the group ID
	* @param bookingDate the booking date
	* @param online the online
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching booking, or <code>null</code> if a matching booking could not be found
	*/
	public static Booking fetchByF_GID_BOOKING_DATE_First(long groupId,
		Date bookingDate, boolean online,
		OrderByComparator<Booking> orderByComparator) {
		return getPersistence()
				   .fetchByF_GID_BOOKING_DATE_First(groupId, bookingDate,
			online, orderByComparator);
	}

	/**
	* Returns the last booking in the ordered set where groupId = &#63; and bookingDate = &#63; and online = &#63;.
	*
	* @param groupId the group ID
	* @param bookingDate the booking date
	* @param online the online
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching booking
	* @throws NoSuchBookingException if a matching booking could not be found
	*/
	public static Booking findByF_GID_BOOKING_DATE_Last(long groupId,
		Date bookingDate, boolean online,
		OrderByComparator<Booking> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchBookingException {
		return getPersistence()
				   .findByF_GID_BOOKING_DATE_Last(groupId, bookingDate, online,
			orderByComparator);
	}

	/**
	* Returns the last booking in the ordered set where groupId = &#63; and bookingDate = &#63; and online = &#63;.
	*
	* @param groupId the group ID
	* @param bookingDate the booking date
	* @param online the online
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching booking, or <code>null</code> if a matching booking could not be found
	*/
	public static Booking fetchByF_GID_BOOKING_DATE_Last(long groupId,
		Date bookingDate, boolean online,
		OrderByComparator<Booking> orderByComparator) {
		return getPersistence()
				   .fetchByF_GID_BOOKING_DATE_Last(groupId, bookingDate,
			online, orderByComparator);
	}

	/**
	* Returns the bookings before and after the current booking in the ordered set where groupId = &#63; and bookingDate = &#63; and online = &#63;.
	*
	* @param bookingId the primary key of the current booking
	* @param groupId the group ID
	* @param bookingDate the booking date
	* @param online the online
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next booking
	* @throws NoSuchBookingException if a booking with the primary key could not be found
	*/
	public static Booking[] findByF_GID_BOOKING_DATE_PrevAndNext(
		long bookingId, long groupId, Date bookingDate, boolean online,
		OrderByComparator<Booking> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchBookingException {
		return getPersistence()
				   .findByF_GID_BOOKING_DATE_PrevAndNext(bookingId, groupId,
			bookingDate, online, orderByComparator);
	}

	/**
	* Removes all the bookings where groupId = &#63; and bookingDate = &#63; and online = &#63; from the database.
	*
	* @param groupId the group ID
	* @param bookingDate the booking date
	* @param online the online
	*/
	public static void removeByF_GID_BOOKING_DATE(long groupId,
		Date bookingDate, boolean online) {
		getPersistence().removeByF_GID_BOOKING_DATE(groupId, bookingDate, online);
	}

	/**
	* Returns the number of bookings where groupId = &#63; and bookingDate = &#63; and online = &#63;.
	*
	* @param groupId the group ID
	* @param bookingDate the booking date
	* @param online the online
	* @return the number of matching bookings
	*/
	public static int countByF_GID_BOOKING_DATE(long groupId, Date bookingDate,
		boolean online) {
		return getPersistence()
				   .countByF_GID_BOOKING_DATE(groupId, bookingDate, online);
	}

	/**
	* Returns the booking where codeNumber = &#63; or throws a {@link NoSuchBookingException} if it could not be found.
	*
	* @param codeNumber the code number
	* @return the matching booking
	* @throws NoSuchBookingException if a matching booking could not be found
	*/
	public static Booking findByF_CODE_NUMBER(String codeNumber)
		throws org.opencps.dossiermgt.exception.NoSuchBookingException {
		return getPersistence().findByF_CODE_NUMBER(codeNumber);
	}

	/**
	* Returns the booking where codeNumber = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param codeNumber the code number
	* @return the matching booking, or <code>null</code> if a matching booking could not be found
	*/
	public static Booking fetchByF_CODE_NUMBER(String codeNumber) {
		return getPersistence().fetchByF_CODE_NUMBER(codeNumber);
	}

	/**
	* Returns the booking where codeNumber = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param codeNumber the code number
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching booking, or <code>null</code> if a matching booking could not be found
	*/
	public static Booking fetchByF_CODE_NUMBER(String codeNumber,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_CODE_NUMBER(codeNumber, retrieveFromCache);
	}

	/**
	* Removes the booking where codeNumber = &#63; from the database.
	*
	* @param codeNumber the code number
	* @return the booking that was removed
	*/
	public static Booking removeByF_CODE_NUMBER(String codeNumber)
		throws org.opencps.dossiermgt.exception.NoSuchBookingException {
		return getPersistence().removeByF_CODE_NUMBER(codeNumber);
	}

	/**
	* Returns the number of bookings where codeNumber = &#63;.
	*
	* @param codeNumber the code number
	* @return the number of matching bookings
	*/
	public static int countByF_CODE_NUMBER(String codeNumber) {
		return getPersistence().countByF_CODE_NUMBER(codeNumber);
	}

	/**
	* Caches the booking in the entity cache if it is enabled.
	*
	* @param booking the booking
	*/
	public static void cacheResult(Booking booking) {
		getPersistence().cacheResult(booking);
	}

	/**
	* Caches the bookings in the entity cache if it is enabled.
	*
	* @param bookings the bookings
	*/
	public static void cacheResult(List<Booking> bookings) {
		getPersistence().cacheResult(bookings);
	}

	/**
	* Creates a new booking with the primary key. Does not add the booking to the database.
	*
	* @param bookingId the primary key for the new booking
	* @return the new booking
	*/
	public static Booking create(long bookingId) {
		return getPersistence().create(bookingId);
	}

	/**
	* Removes the booking with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param bookingId the primary key of the booking
	* @return the booking that was removed
	* @throws NoSuchBookingException if a booking with the primary key could not be found
	*/
	public static Booking remove(long bookingId)
		throws org.opencps.dossiermgt.exception.NoSuchBookingException {
		return getPersistence().remove(bookingId);
	}

	public static Booking updateImpl(Booking booking) {
		return getPersistence().updateImpl(booking);
	}

	/**
	* Returns the booking with the primary key or throws a {@link NoSuchBookingException} if it could not be found.
	*
	* @param bookingId the primary key of the booking
	* @return the booking
	* @throws NoSuchBookingException if a booking with the primary key could not be found
	*/
	public static Booking findByPrimaryKey(long bookingId)
		throws org.opencps.dossiermgt.exception.NoSuchBookingException {
		return getPersistence().findByPrimaryKey(bookingId);
	}

	/**
	* Returns the booking with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param bookingId the primary key of the booking
	* @return the booking, or <code>null</code> if a booking with the primary key could not be found
	*/
	public static Booking fetchByPrimaryKey(long bookingId) {
		return getPersistence().fetchByPrimaryKey(bookingId);
	}

	public static java.util.Map<java.io.Serializable, Booking> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the bookings.
	*
	* @return the bookings
	*/
	public static List<Booking> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the bookings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of bookings
	* @param end the upper bound of the range of bookings (not inclusive)
	* @return the range of bookings
	*/
	public static List<Booking> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the bookings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of bookings
	* @param end the upper bound of the range of bookings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of bookings
	*/
	public static List<Booking> findAll(int start, int end,
		OrderByComparator<Booking> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the bookings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of bookings
	* @param end the upper bound of the range of bookings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of bookings
	*/
	public static List<Booking> findAll(int start, int end,
		OrderByComparator<Booking> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the bookings from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of bookings.
	*
	* @return the number of bookings
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static BookingPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<BookingPersistence, BookingPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(BookingPersistence.class);

		ServiceTracker<BookingPersistence, BookingPersistence> serviceTracker = new ServiceTracker<BookingPersistence, BookingPersistence>(bundle.getBundleContext(),
				BookingPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}