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

package org.opencps.datamgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.datamgt.model.Holiday;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.Date;
import java.util.List;

/**
 * The persistence utility for the holiday service. This utility wraps {@link org.opencps.datamgt.service.persistence.impl.HolidayPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see HolidayPersistence
 * @see org.opencps.datamgt.service.persistence.impl.HolidayPersistenceImpl
 * @generated
 */
@ProviderType
public class HolidayUtil {
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
	public static void clearCache(Holiday holiday) {
		getPersistence().clearCache(holiday);
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
	public static List<Holiday> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Holiday> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Holiday> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Holiday> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Holiday update(Holiday holiday) {
		return getPersistence().update(holiday);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Holiday update(Holiday holiday, ServiceContext serviceContext) {
		return getPersistence().update(holiday, serviceContext);
	}

	/**
	* Returns all the holidaies where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching holidaies
	*/
	public static List<Holiday> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the holidaies where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HolidayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of holidaies
	* @param end the upper bound of the range of holidaies (not inclusive)
	* @return the range of matching holidaies
	*/
	public static List<Holiday> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the holidaies where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HolidayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of holidaies
	* @param end the upper bound of the range of holidaies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching holidaies
	*/
	public static List<Holiday> findByUuid(String uuid, int start, int end,
		OrderByComparator<Holiday> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the holidaies where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HolidayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of holidaies
	* @param end the upper bound of the range of holidaies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching holidaies
	*/
	public static List<Holiday> findByUuid(String uuid, int start, int end,
		OrderByComparator<Holiday> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first holiday in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching holiday
	* @throws NoSuchHolidayException if a matching holiday could not be found
	*/
	public static Holiday findByUuid_First(String uuid,
		OrderByComparator<Holiday> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchHolidayException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first holiday in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching holiday, or <code>null</code> if a matching holiday could not be found
	*/
	public static Holiday fetchByUuid_First(String uuid,
		OrderByComparator<Holiday> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last holiday in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching holiday
	* @throws NoSuchHolidayException if a matching holiday could not be found
	*/
	public static Holiday findByUuid_Last(String uuid,
		OrderByComparator<Holiday> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchHolidayException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last holiday in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching holiday, or <code>null</code> if a matching holiday could not be found
	*/
	public static Holiday fetchByUuid_Last(String uuid,
		OrderByComparator<Holiday> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the holidaies before and after the current holiday in the ordered set where uuid = &#63;.
	*
	* @param holidayId the primary key of the current holiday
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next holiday
	* @throws NoSuchHolidayException if a holiday with the primary key could not be found
	*/
	public static Holiday[] findByUuid_PrevAndNext(long holidayId, String uuid,
		OrderByComparator<Holiday> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchHolidayException {
		return getPersistence()
				   .findByUuid_PrevAndNext(holidayId, uuid, orderByComparator);
	}

	/**
	* Removes all the holidaies where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of holidaies where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching holidaies
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the holiday where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchHolidayException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching holiday
	* @throws NoSuchHolidayException if a matching holiday could not be found
	*/
	public static Holiday findByUUID_G(String uuid, long groupId)
		throws org.opencps.datamgt.exception.NoSuchHolidayException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the holiday where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching holiday, or <code>null</code> if a matching holiday could not be found
	*/
	public static Holiday fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the holiday where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching holiday, or <code>null</code> if a matching holiday could not be found
	*/
	public static Holiday fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the holiday where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the holiday that was removed
	*/
	public static Holiday removeByUUID_G(String uuid, long groupId)
		throws org.opencps.datamgt.exception.NoSuchHolidayException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of holidaies where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching holidaies
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the holidaies where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching holidaies
	*/
	public static List<Holiday> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the holidaies where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HolidayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of holidaies
	* @param end the upper bound of the range of holidaies (not inclusive)
	* @return the range of matching holidaies
	*/
	public static List<Holiday> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the holidaies where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HolidayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of holidaies
	* @param end the upper bound of the range of holidaies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching holidaies
	*/
	public static List<Holiday> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<Holiday> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the holidaies where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HolidayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of holidaies
	* @param end the upper bound of the range of holidaies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching holidaies
	*/
	public static List<Holiday> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<Holiday> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first holiday in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching holiday
	* @throws NoSuchHolidayException if a matching holiday could not be found
	*/
	public static Holiday findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Holiday> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchHolidayException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first holiday in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching holiday, or <code>null</code> if a matching holiday could not be found
	*/
	public static Holiday fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Holiday> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last holiday in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching holiday
	* @throws NoSuchHolidayException if a matching holiday could not be found
	*/
	public static Holiday findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Holiday> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchHolidayException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last holiday in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching holiday, or <code>null</code> if a matching holiday could not be found
	*/
	public static Holiday fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Holiday> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the holidaies before and after the current holiday in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param holidayId the primary key of the current holiday
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next holiday
	* @throws NoSuchHolidayException if a holiday with the primary key could not be found
	*/
	public static Holiday[] findByUuid_C_PrevAndNext(long holidayId,
		String uuid, long companyId,
		OrderByComparator<Holiday> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchHolidayException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(holidayId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the holidaies where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of holidaies where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching holidaies
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns the holiday where groupId = &#63; and holidayDate = &#63; or throws a {@link NoSuchHolidayException} if it could not be found.
	*
	* @param groupId the group ID
	* @param holidayDate the holiday date
	* @return the matching holiday
	* @throws NoSuchHolidayException if a matching holiday could not be found
	*/
	public static Holiday findByF_holidayDate(long groupId, Date holidayDate)
		throws org.opencps.datamgt.exception.NoSuchHolidayException {
		return getPersistence().findByF_holidayDate(groupId, holidayDate);
	}

	/**
	* Returns the holiday where groupId = &#63; and holidayDate = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param holidayDate the holiday date
	* @return the matching holiday, or <code>null</code> if a matching holiday could not be found
	*/
	public static Holiday fetchByF_holidayDate(long groupId, Date holidayDate) {
		return getPersistence().fetchByF_holidayDate(groupId, holidayDate);
	}

	/**
	* Returns the holiday where groupId = &#63; and holidayDate = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param holidayDate the holiday date
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching holiday, or <code>null</code> if a matching holiday could not be found
	*/
	public static Holiday fetchByF_holidayDate(long groupId, Date holidayDate,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_holidayDate(groupId, holidayDate, retrieveFromCache);
	}

	/**
	* Removes the holiday where groupId = &#63; and holidayDate = &#63; from the database.
	*
	* @param groupId the group ID
	* @param holidayDate the holiday date
	* @return the holiday that was removed
	*/
	public static Holiday removeByF_holidayDate(long groupId, Date holidayDate)
		throws org.opencps.datamgt.exception.NoSuchHolidayException {
		return getPersistence().removeByF_holidayDate(groupId, holidayDate);
	}

	/**
	* Returns the number of holidaies where groupId = &#63; and holidayDate = &#63;.
	*
	* @param groupId the group ID
	* @param holidayDate the holiday date
	* @return the number of matching holidaies
	*/
	public static int countByF_holidayDate(long groupId, Date holidayDate) {
		return getPersistence().countByF_holidayDate(groupId, holidayDate);
	}

	/**
	* Returns all the holidaies where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching holidaies
	*/
	public static List<Holiday> findByF_GID(long groupId) {
		return getPersistence().findByF_GID(groupId);
	}

	/**
	* Returns a range of all the holidaies where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HolidayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of holidaies
	* @param end the upper bound of the range of holidaies (not inclusive)
	* @return the range of matching holidaies
	*/
	public static List<Holiday> findByF_GID(long groupId, int start, int end) {
		return getPersistence().findByF_GID(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the holidaies where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HolidayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of holidaies
	* @param end the upper bound of the range of holidaies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching holidaies
	*/
	public static List<Holiday> findByF_GID(long groupId, int start, int end,
		OrderByComparator<Holiday> orderByComparator) {
		return getPersistence()
				   .findByF_GID(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the holidaies where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HolidayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of holidaies
	* @param end the upper bound of the range of holidaies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching holidaies
	*/
	public static List<Holiday> findByF_GID(long groupId, int start, int end,
		OrderByComparator<Holiday> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_GID(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first holiday in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching holiday
	* @throws NoSuchHolidayException if a matching holiday could not be found
	*/
	public static Holiday findByF_GID_First(long groupId,
		OrderByComparator<Holiday> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchHolidayException {
		return getPersistence().findByF_GID_First(groupId, orderByComparator);
	}

	/**
	* Returns the first holiday in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching holiday, or <code>null</code> if a matching holiday could not be found
	*/
	public static Holiday fetchByF_GID_First(long groupId,
		OrderByComparator<Holiday> orderByComparator) {
		return getPersistence().fetchByF_GID_First(groupId, orderByComparator);
	}

	/**
	* Returns the last holiday in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching holiday
	* @throws NoSuchHolidayException if a matching holiday could not be found
	*/
	public static Holiday findByF_GID_Last(long groupId,
		OrderByComparator<Holiday> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchHolidayException {
		return getPersistence().findByF_GID_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last holiday in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching holiday, or <code>null</code> if a matching holiday could not be found
	*/
	public static Holiday fetchByF_GID_Last(long groupId,
		OrderByComparator<Holiday> orderByComparator) {
		return getPersistence().fetchByF_GID_Last(groupId, orderByComparator);
	}

	/**
	* Returns the holidaies before and after the current holiday in the ordered set where groupId = &#63;.
	*
	* @param holidayId the primary key of the current holiday
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next holiday
	* @throws NoSuchHolidayException if a holiday with the primary key could not be found
	*/
	public static Holiday[] findByF_GID_PrevAndNext(long holidayId,
		long groupId, OrderByComparator<Holiday> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchHolidayException {
		return getPersistence()
				   .findByF_GID_PrevAndNext(holidayId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the holidaies where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByF_GID(long groupId) {
		getPersistence().removeByF_GID(groupId);
	}

	/**
	* Returns the number of holidaies where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching holidaies
	*/
	public static int countByF_GID(long groupId) {
		return getPersistence().countByF_GID(groupId);
	}

	/**
	* Caches the holiday in the entity cache if it is enabled.
	*
	* @param holiday the holiday
	*/
	public static void cacheResult(Holiday holiday) {
		getPersistence().cacheResult(holiday);
	}

	/**
	* Caches the holidaies in the entity cache if it is enabled.
	*
	* @param holidaies the holidaies
	*/
	public static void cacheResult(List<Holiday> holidaies) {
		getPersistence().cacheResult(holidaies);
	}

	/**
	* Creates a new holiday with the primary key. Does not add the holiday to the database.
	*
	* @param holidayId the primary key for the new holiday
	* @return the new holiday
	*/
	public static Holiday create(long holidayId) {
		return getPersistence().create(holidayId);
	}

	/**
	* Removes the holiday with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param holidayId the primary key of the holiday
	* @return the holiday that was removed
	* @throws NoSuchHolidayException if a holiday with the primary key could not be found
	*/
	public static Holiday remove(long holidayId)
		throws org.opencps.datamgt.exception.NoSuchHolidayException {
		return getPersistence().remove(holidayId);
	}

	public static Holiday updateImpl(Holiday holiday) {
		return getPersistence().updateImpl(holiday);
	}

	/**
	* Returns the holiday with the primary key or throws a {@link NoSuchHolidayException} if it could not be found.
	*
	* @param holidayId the primary key of the holiday
	* @return the holiday
	* @throws NoSuchHolidayException if a holiday with the primary key could not be found
	*/
	public static Holiday findByPrimaryKey(long holidayId)
		throws org.opencps.datamgt.exception.NoSuchHolidayException {
		return getPersistence().findByPrimaryKey(holidayId);
	}

	/**
	* Returns the holiday with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param holidayId the primary key of the holiday
	* @return the holiday, or <code>null</code> if a holiday with the primary key could not be found
	*/
	public static Holiday fetchByPrimaryKey(long holidayId) {
		return getPersistence().fetchByPrimaryKey(holidayId);
	}

	public static java.util.Map<java.io.Serializable, Holiday> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the holidaies.
	*
	* @return the holidaies
	*/
	public static List<Holiday> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the holidaies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HolidayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of holidaies
	* @param end the upper bound of the range of holidaies (not inclusive)
	* @return the range of holidaies
	*/
	public static List<Holiday> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the holidaies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HolidayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of holidaies
	* @param end the upper bound of the range of holidaies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of holidaies
	*/
	public static List<Holiday> findAll(int start, int end,
		OrderByComparator<Holiday> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the holidaies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HolidayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of holidaies
	* @param end the upper bound of the range of holidaies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of holidaies
	*/
	public static List<Holiday> findAll(int start, int end,
		OrderByComparator<Holiday> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the holidaies from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of holidaies.
	*
	* @return the number of holidaies
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static HolidayPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<HolidayPersistence, HolidayPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(HolidayPersistence.class);

		ServiceTracker<HolidayPersistence, HolidayPersistence> serviceTracker = new ServiceTracker<HolidayPersistence, HolidayPersistence>(bundle.getBundleContext(),
				HolidayPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}