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

import org.opencps.datamgt.model.WorkTime;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the work time service. This utility wraps {@link org.opencps.datamgt.service.persistence.impl.WorkTimePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see WorkTimePersistence
 * @see org.opencps.datamgt.service.persistence.impl.WorkTimePersistenceImpl
 * @generated
 */
@ProviderType
public class WorkTimeUtil {
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
	public static void clearCache(WorkTime workTime) {
		getPersistence().clearCache(workTime);
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
	public static List<WorkTime> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<WorkTime> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<WorkTime> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<WorkTime> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static WorkTime update(WorkTime workTime) {
		return getPersistence().update(workTime);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static WorkTime update(WorkTime workTime,
		ServiceContext serviceContext) {
		return getPersistence().update(workTime, serviceContext);
	}

	/**
	* Returns all the work times where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching work times
	*/
	public static List<WorkTime> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the work times where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkTimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of work times
	* @param end the upper bound of the range of work times (not inclusive)
	* @return the range of matching work times
	*/
	public static List<WorkTime> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the work times where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkTimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of work times
	* @param end the upper bound of the range of work times (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching work times
	*/
	public static List<WorkTime> findByUuid(String uuid, int start, int end,
		OrderByComparator<WorkTime> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the work times where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkTimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of work times
	* @param end the upper bound of the range of work times (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching work times
	*/
	public static List<WorkTime> findByUuid(String uuid, int start, int end,
		OrderByComparator<WorkTime> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first work time in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching work time
	* @throws NoSuchWorkTimeException if a matching work time could not be found
	*/
	public static WorkTime findByUuid_First(String uuid,
		OrderByComparator<WorkTime> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchWorkTimeException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first work time in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching work time, or <code>null</code> if a matching work time could not be found
	*/
	public static WorkTime fetchByUuid_First(String uuid,
		OrderByComparator<WorkTime> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last work time in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching work time
	* @throws NoSuchWorkTimeException if a matching work time could not be found
	*/
	public static WorkTime findByUuid_Last(String uuid,
		OrderByComparator<WorkTime> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchWorkTimeException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last work time in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching work time, or <code>null</code> if a matching work time could not be found
	*/
	public static WorkTime fetchByUuid_Last(String uuid,
		OrderByComparator<WorkTime> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the work times before and after the current work time in the ordered set where uuid = &#63;.
	*
	* @param workTimeId the primary key of the current work time
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next work time
	* @throws NoSuchWorkTimeException if a work time with the primary key could not be found
	*/
	public static WorkTime[] findByUuid_PrevAndNext(long workTimeId,
		String uuid, OrderByComparator<WorkTime> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchWorkTimeException {
		return getPersistence()
				   .findByUuid_PrevAndNext(workTimeId, uuid, orderByComparator);
	}

	/**
	* Removes all the work times where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of work times where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching work times
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the work time where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchWorkTimeException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching work time
	* @throws NoSuchWorkTimeException if a matching work time could not be found
	*/
	public static WorkTime findByUUID_G(String uuid, long groupId)
		throws org.opencps.datamgt.exception.NoSuchWorkTimeException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the work time where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching work time, or <code>null</code> if a matching work time could not be found
	*/
	public static WorkTime fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the work time where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching work time, or <code>null</code> if a matching work time could not be found
	*/
	public static WorkTime fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the work time where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the work time that was removed
	*/
	public static WorkTime removeByUUID_G(String uuid, long groupId)
		throws org.opencps.datamgt.exception.NoSuchWorkTimeException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of work times where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching work times
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the work times where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching work times
	*/
	public static List<WorkTime> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the work times where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkTimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of work times
	* @param end the upper bound of the range of work times (not inclusive)
	* @return the range of matching work times
	*/
	public static List<WorkTime> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the work times where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkTimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of work times
	* @param end the upper bound of the range of work times (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching work times
	*/
	public static List<WorkTime> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<WorkTime> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the work times where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkTimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of work times
	* @param end the upper bound of the range of work times (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching work times
	*/
	public static List<WorkTime> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<WorkTime> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first work time in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching work time
	* @throws NoSuchWorkTimeException if a matching work time could not be found
	*/
	public static WorkTime findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<WorkTime> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchWorkTimeException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first work time in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching work time, or <code>null</code> if a matching work time could not be found
	*/
	public static WorkTime fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<WorkTime> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last work time in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching work time
	* @throws NoSuchWorkTimeException if a matching work time could not be found
	*/
	public static WorkTime findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<WorkTime> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchWorkTimeException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last work time in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching work time, or <code>null</code> if a matching work time could not be found
	*/
	public static WorkTime fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<WorkTime> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the work times before and after the current work time in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param workTimeId the primary key of the current work time
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next work time
	* @throws NoSuchWorkTimeException if a work time with the primary key could not be found
	*/
	public static WorkTime[] findByUuid_C_PrevAndNext(long workTimeId,
		String uuid, long companyId,
		OrderByComparator<WorkTime> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchWorkTimeException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(workTimeId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the work times where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of work times where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching work times
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns the work time where groupId = &#63; and day = &#63; or throws a {@link NoSuchWorkTimeException} if it could not be found.
	*
	* @param groupId the group ID
	* @param day the day
	* @return the matching work time
	* @throws NoSuchWorkTimeException if a matching work time could not be found
	*/
	public static WorkTime findByF_day(long groupId, int day)
		throws org.opencps.datamgt.exception.NoSuchWorkTimeException {
		return getPersistence().findByF_day(groupId, day);
	}

	/**
	* Returns the work time where groupId = &#63; and day = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param day the day
	* @return the matching work time, or <code>null</code> if a matching work time could not be found
	*/
	public static WorkTime fetchByF_day(long groupId, int day) {
		return getPersistence().fetchByF_day(groupId, day);
	}

	/**
	* Returns the work time where groupId = &#63; and day = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param day the day
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching work time, or <code>null</code> if a matching work time could not be found
	*/
	public static WorkTime fetchByF_day(long groupId, int day,
		boolean retrieveFromCache) {
		return getPersistence().fetchByF_day(groupId, day, retrieveFromCache);
	}

	/**
	* Removes the work time where groupId = &#63; and day = &#63; from the database.
	*
	* @param groupId the group ID
	* @param day the day
	* @return the work time that was removed
	*/
	public static WorkTime removeByF_day(long groupId, int day)
		throws org.opencps.datamgt.exception.NoSuchWorkTimeException {
		return getPersistence().removeByF_day(groupId, day);
	}

	/**
	* Returns the number of work times where groupId = &#63; and day = &#63;.
	*
	* @param groupId the group ID
	* @param day the day
	* @return the number of matching work times
	*/
	public static int countByF_day(long groupId, int day) {
		return getPersistence().countByF_day(groupId, day);
	}

	/**
	* Returns all the work times where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching work times
	*/
	public static List<WorkTime> findByF_GID(long groupId) {
		return getPersistence().findByF_GID(groupId);
	}

	/**
	* Returns a range of all the work times where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkTimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of work times
	* @param end the upper bound of the range of work times (not inclusive)
	* @return the range of matching work times
	*/
	public static List<WorkTime> findByF_GID(long groupId, int start, int end) {
		return getPersistence().findByF_GID(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the work times where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkTimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of work times
	* @param end the upper bound of the range of work times (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching work times
	*/
	public static List<WorkTime> findByF_GID(long groupId, int start, int end,
		OrderByComparator<WorkTime> orderByComparator) {
		return getPersistence()
				   .findByF_GID(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the work times where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkTimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of work times
	* @param end the upper bound of the range of work times (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching work times
	*/
	public static List<WorkTime> findByF_GID(long groupId, int start, int end,
		OrderByComparator<WorkTime> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_GID(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first work time in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching work time
	* @throws NoSuchWorkTimeException if a matching work time could not be found
	*/
	public static WorkTime findByF_GID_First(long groupId,
		OrderByComparator<WorkTime> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchWorkTimeException {
		return getPersistence().findByF_GID_First(groupId, orderByComparator);
	}

	/**
	* Returns the first work time in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching work time, or <code>null</code> if a matching work time could not be found
	*/
	public static WorkTime fetchByF_GID_First(long groupId,
		OrderByComparator<WorkTime> orderByComparator) {
		return getPersistence().fetchByF_GID_First(groupId, orderByComparator);
	}

	/**
	* Returns the last work time in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching work time
	* @throws NoSuchWorkTimeException if a matching work time could not be found
	*/
	public static WorkTime findByF_GID_Last(long groupId,
		OrderByComparator<WorkTime> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchWorkTimeException {
		return getPersistence().findByF_GID_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last work time in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching work time, or <code>null</code> if a matching work time could not be found
	*/
	public static WorkTime fetchByF_GID_Last(long groupId,
		OrderByComparator<WorkTime> orderByComparator) {
		return getPersistence().fetchByF_GID_Last(groupId, orderByComparator);
	}

	/**
	* Returns the work times before and after the current work time in the ordered set where groupId = &#63;.
	*
	* @param workTimeId the primary key of the current work time
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next work time
	* @throws NoSuchWorkTimeException if a work time with the primary key could not be found
	*/
	public static WorkTime[] findByF_GID_PrevAndNext(long workTimeId,
		long groupId, OrderByComparator<WorkTime> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchWorkTimeException {
		return getPersistence()
				   .findByF_GID_PrevAndNext(workTimeId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the work times where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByF_GID(long groupId) {
		getPersistence().removeByF_GID(groupId);
	}

	/**
	* Returns the number of work times where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching work times
	*/
	public static int countByF_GID(long groupId) {
		return getPersistence().countByF_GID(groupId);
	}

	/**
	* Caches the work time in the entity cache if it is enabled.
	*
	* @param workTime the work time
	*/
	public static void cacheResult(WorkTime workTime) {
		getPersistence().cacheResult(workTime);
	}

	/**
	* Caches the work times in the entity cache if it is enabled.
	*
	* @param workTimes the work times
	*/
	public static void cacheResult(List<WorkTime> workTimes) {
		getPersistence().cacheResult(workTimes);
	}

	/**
	* Creates a new work time with the primary key. Does not add the work time to the database.
	*
	* @param workTimeId the primary key for the new work time
	* @return the new work time
	*/
	public static WorkTime create(long workTimeId) {
		return getPersistence().create(workTimeId);
	}

	/**
	* Removes the work time with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param workTimeId the primary key of the work time
	* @return the work time that was removed
	* @throws NoSuchWorkTimeException if a work time with the primary key could not be found
	*/
	public static WorkTime remove(long workTimeId)
		throws org.opencps.datamgt.exception.NoSuchWorkTimeException {
		return getPersistence().remove(workTimeId);
	}

	public static WorkTime updateImpl(WorkTime workTime) {
		return getPersistence().updateImpl(workTime);
	}

	/**
	* Returns the work time with the primary key or throws a {@link NoSuchWorkTimeException} if it could not be found.
	*
	* @param workTimeId the primary key of the work time
	* @return the work time
	* @throws NoSuchWorkTimeException if a work time with the primary key could not be found
	*/
	public static WorkTime findByPrimaryKey(long workTimeId)
		throws org.opencps.datamgt.exception.NoSuchWorkTimeException {
		return getPersistence().findByPrimaryKey(workTimeId);
	}

	/**
	* Returns the work time with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param workTimeId the primary key of the work time
	* @return the work time, or <code>null</code> if a work time with the primary key could not be found
	*/
	public static WorkTime fetchByPrimaryKey(long workTimeId) {
		return getPersistence().fetchByPrimaryKey(workTimeId);
	}

	public static java.util.Map<java.io.Serializable, WorkTime> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the work times.
	*
	* @return the work times
	*/
	public static List<WorkTime> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the work times.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkTimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of work times
	* @param end the upper bound of the range of work times (not inclusive)
	* @return the range of work times
	*/
	public static List<WorkTime> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the work times.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkTimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of work times
	* @param end the upper bound of the range of work times (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of work times
	*/
	public static List<WorkTime> findAll(int start, int end,
		OrderByComparator<WorkTime> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the work times.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkTimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of work times
	* @param end the upper bound of the range of work times (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of work times
	*/
	public static List<WorkTime> findAll(int start, int end,
		OrderByComparator<WorkTime> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the work times from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of work times.
	*
	* @return the number of work times
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static WorkTimePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<WorkTimePersistence, WorkTimePersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(WorkTimePersistence.class);

		ServiceTracker<WorkTimePersistence, WorkTimePersistence> serviceTracker = new ServiceTracker<WorkTimePersistence, WorkTimePersistence>(bundle.getBundleContext(),
				WorkTimePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}