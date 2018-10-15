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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.datamgt.exception.NoSuchWorkTimeException;
import org.opencps.datamgt.model.WorkTime;

/**
 * The persistence interface for the work time service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see org.opencps.datamgt.service.persistence.impl.WorkTimePersistenceImpl
 * @see WorkTimeUtil
 * @generated
 */
@ProviderType
public interface WorkTimePersistence extends BasePersistence<WorkTime> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WorkTimeUtil} to access the work time persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the work times where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching work times
	*/
	public java.util.List<WorkTime> findByUuid(String uuid);

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
	public java.util.List<WorkTime> findByUuid(String uuid, int start, int end);

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
	public java.util.List<WorkTime> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WorkTime> orderByComparator);

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
	public java.util.List<WorkTime> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WorkTime> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first work time in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching work time
	* @throws NoSuchWorkTimeException if a matching work time could not be found
	*/
	public WorkTime findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<WorkTime> orderByComparator)
		throws NoSuchWorkTimeException;

	/**
	* Returns the first work time in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching work time, or <code>null</code> if a matching work time could not be found
	*/
	public WorkTime fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<WorkTime> orderByComparator);

	/**
	* Returns the last work time in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching work time
	* @throws NoSuchWorkTimeException if a matching work time could not be found
	*/
	public WorkTime findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<WorkTime> orderByComparator)
		throws NoSuchWorkTimeException;

	/**
	* Returns the last work time in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching work time, or <code>null</code> if a matching work time could not be found
	*/
	public WorkTime fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<WorkTime> orderByComparator);

	/**
	* Returns the work times before and after the current work time in the ordered set where uuid = &#63;.
	*
	* @param workTimeId the primary key of the current work time
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next work time
	* @throws NoSuchWorkTimeException if a work time with the primary key could not be found
	*/
	public WorkTime[] findByUuid_PrevAndNext(long workTimeId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<WorkTime> orderByComparator)
		throws NoSuchWorkTimeException;

	/**
	* Removes all the work times where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of work times where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching work times
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the work time where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchWorkTimeException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching work time
	* @throws NoSuchWorkTimeException if a matching work time could not be found
	*/
	public WorkTime findByUUID_G(String uuid, long groupId)
		throws NoSuchWorkTimeException;

	/**
	* Returns the work time where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching work time, or <code>null</code> if a matching work time could not be found
	*/
	public WorkTime fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the work time where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching work time, or <code>null</code> if a matching work time could not be found
	*/
	public WorkTime fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the work time where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the work time that was removed
	*/
	public WorkTime removeByUUID_G(String uuid, long groupId)
		throws NoSuchWorkTimeException;

	/**
	* Returns the number of work times where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching work times
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the work times where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching work times
	*/
	public java.util.List<WorkTime> findByUuid_C(String uuid, long companyId);

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
	public java.util.List<WorkTime> findByUuid_C(String uuid, long companyId,
		int start, int end);

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
	public java.util.List<WorkTime> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WorkTime> orderByComparator);

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
	public java.util.List<WorkTime> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WorkTime> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first work time in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching work time
	* @throws NoSuchWorkTimeException if a matching work time could not be found
	*/
	public WorkTime findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WorkTime> orderByComparator)
		throws NoSuchWorkTimeException;

	/**
	* Returns the first work time in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching work time, or <code>null</code> if a matching work time could not be found
	*/
	public WorkTime fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WorkTime> orderByComparator);

	/**
	* Returns the last work time in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching work time
	* @throws NoSuchWorkTimeException if a matching work time could not be found
	*/
	public WorkTime findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WorkTime> orderByComparator)
		throws NoSuchWorkTimeException;

	/**
	* Returns the last work time in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching work time, or <code>null</code> if a matching work time could not be found
	*/
	public WorkTime fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WorkTime> orderByComparator);

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
	public WorkTime[] findByUuid_C_PrevAndNext(long workTimeId, String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WorkTime> orderByComparator)
		throws NoSuchWorkTimeException;

	/**
	* Removes all the work times where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of work times where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching work times
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns the work time where groupId = &#63; and day = &#63; or throws a {@link NoSuchWorkTimeException} if it could not be found.
	*
	* @param groupId the group ID
	* @param day the day
	* @return the matching work time
	* @throws NoSuchWorkTimeException if a matching work time could not be found
	*/
	public WorkTime findByF_day(long groupId, int day)
		throws NoSuchWorkTimeException;

	/**
	* Returns the work time where groupId = &#63; and day = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param day the day
	* @return the matching work time, or <code>null</code> if a matching work time could not be found
	*/
	public WorkTime fetchByF_day(long groupId, int day);

	/**
	* Returns the work time where groupId = &#63; and day = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param day the day
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching work time, or <code>null</code> if a matching work time could not be found
	*/
	public WorkTime fetchByF_day(long groupId, int day,
		boolean retrieveFromCache);

	/**
	* Removes the work time where groupId = &#63; and day = &#63; from the database.
	*
	* @param groupId the group ID
	* @param day the day
	* @return the work time that was removed
	*/
	public WorkTime removeByF_day(long groupId, int day)
		throws NoSuchWorkTimeException;

	/**
	* Returns the number of work times where groupId = &#63; and day = &#63;.
	*
	* @param groupId the group ID
	* @param day the day
	* @return the number of matching work times
	*/
	public int countByF_day(long groupId, int day);

	/**
	* Returns all the work times where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching work times
	*/
	public java.util.List<WorkTime> findByF_GID(long groupId);

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
	public java.util.List<WorkTime> findByF_GID(long groupId, int start, int end);

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
	public java.util.List<WorkTime> findByF_GID(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<WorkTime> orderByComparator);

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
	public java.util.List<WorkTime> findByF_GID(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<WorkTime> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first work time in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching work time
	* @throws NoSuchWorkTimeException if a matching work time could not be found
	*/
	public WorkTime findByF_GID_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<WorkTime> orderByComparator)
		throws NoSuchWorkTimeException;

	/**
	* Returns the first work time in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching work time, or <code>null</code> if a matching work time could not be found
	*/
	public WorkTime fetchByF_GID_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<WorkTime> orderByComparator);

	/**
	* Returns the last work time in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching work time
	* @throws NoSuchWorkTimeException if a matching work time could not be found
	*/
	public WorkTime findByF_GID_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<WorkTime> orderByComparator)
		throws NoSuchWorkTimeException;

	/**
	* Returns the last work time in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching work time, or <code>null</code> if a matching work time could not be found
	*/
	public WorkTime fetchByF_GID_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<WorkTime> orderByComparator);

	/**
	* Returns the work times before and after the current work time in the ordered set where groupId = &#63;.
	*
	* @param workTimeId the primary key of the current work time
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next work time
	* @throws NoSuchWorkTimeException if a work time with the primary key could not be found
	*/
	public WorkTime[] findByF_GID_PrevAndNext(long workTimeId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<WorkTime> orderByComparator)
		throws NoSuchWorkTimeException;

	/**
	* Removes all the work times where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByF_GID(long groupId);

	/**
	* Returns the number of work times where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching work times
	*/
	public int countByF_GID(long groupId);

	/**
	* Caches the work time in the entity cache if it is enabled.
	*
	* @param workTime the work time
	*/
	public void cacheResult(WorkTime workTime);

	/**
	* Caches the work times in the entity cache if it is enabled.
	*
	* @param workTimes the work times
	*/
	public void cacheResult(java.util.List<WorkTime> workTimes);

	/**
	* Creates a new work time with the primary key. Does not add the work time to the database.
	*
	* @param workTimeId the primary key for the new work time
	* @return the new work time
	*/
	public WorkTime create(long workTimeId);

	/**
	* Removes the work time with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param workTimeId the primary key of the work time
	* @return the work time that was removed
	* @throws NoSuchWorkTimeException if a work time with the primary key could not be found
	*/
	public WorkTime remove(long workTimeId) throws NoSuchWorkTimeException;

	public WorkTime updateImpl(WorkTime workTime);

	/**
	* Returns the work time with the primary key or throws a {@link NoSuchWorkTimeException} if it could not be found.
	*
	* @param workTimeId the primary key of the work time
	* @return the work time
	* @throws NoSuchWorkTimeException if a work time with the primary key could not be found
	*/
	public WorkTime findByPrimaryKey(long workTimeId)
		throws NoSuchWorkTimeException;

	/**
	* Returns the work time with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param workTimeId the primary key of the work time
	* @return the work time, or <code>null</code> if a work time with the primary key could not be found
	*/
	public WorkTime fetchByPrimaryKey(long workTimeId);

	@Override
	public java.util.Map<java.io.Serializable, WorkTime> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the work times.
	*
	* @return the work times
	*/
	public java.util.List<WorkTime> findAll();

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
	public java.util.List<WorkTime> findAll(int start, int end);

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
	public java.util.List<WorkTime> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WorkTime> orderByComparator);

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
	public java.util.List<WorkTime> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WorkTime> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the work times from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of work times.
	*
	* @return the number of work times
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}