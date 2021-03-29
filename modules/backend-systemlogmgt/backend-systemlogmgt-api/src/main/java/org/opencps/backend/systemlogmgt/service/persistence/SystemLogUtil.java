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

package org.opencps.backend.systemlogmgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.backend.systemlogmgt.model.SystemLog;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the system log service. This utility wraps {@link org.opencps.backend.systemlogmgt.service.persistence.impl.SystemLogPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SystemLogPersistence
 * @see org.opencps.backend.systemlogmgt.service.persistence.impl.SystemLogPersistenceImpl
 * @generated
 */
@ProviderType
public class SystemLogUtil {
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
	public static void clearCache(SystemLog systemLog) {
		getPersistence().clearCache(systemLog);
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
	public static List<SystemLog> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SystemLog> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SystemLog> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static SystemLog update(SystemLog systemLog) {
		return getPersistence().update(systemLog);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static SystemLog update(SystemLog systemLog,
		ServiceContext serviceContext) {
		return getPersistence().update(systemLog, serviceContext);
	}

	/**
	* Returns all the system logs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching system logs
	*/
	public static List<SystemLog> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the system logs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @return the range of matching system logs
	*/
	public static List<SystemLog> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the system logs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching system logs
	*/
	public static List<SystemLog> findByUuid(String uuid, int start, int end,
		OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the system logs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching system logs
	*/
	public static List<SystemLog> findByUuid(String uuid, int start, int end,
		OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first system log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public static SystemLog findByUuid_First(String uuid,
		OrderByComparator<SystemLog> orderByComparator)
		throws org.opencps.backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first system log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public static SystemLog fetchByUuid_First(String uuid,
		OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last system log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public static SystemLog findByUuid_Last(String uuid,
		OrderByComparator<SystemLog> orderByComparator)
		throws org.opencps.backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last system log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public static SystemLog fetchByUuid_Last(String uuid,
		OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the system logs before and after the current system log in the ordered set where uuid = &#63;.
	*
	* @param logId the primary key of the current system log
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next system log
	* @throws NoSuchSystemLogException if a system log with the primary key could not be found
	*/
	public static SystemLog[] findByUuid_PrevAndNext(long logId, String uuid,
		OrderByComparator<SystemLog> orderByComparator)
		throws org.opencps.backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence()
				   .findByUuid_PrevAndNext(logId, uuid, orderByComparator);
	}

	/**
	* Removes all the system logs where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of system logs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching system logs
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the system log where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchSystemLogException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public static SystemLog findByUUID_G(String uuid, long groupId)
		throws org.opencps.backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the system log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public static SystemLog fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the system log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public static SystemLog fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the system log where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the system log that was removed
	*/
	public static SystemLog removeByUUID_G(String uuid, long groupId)
		throws org.opencps.backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of system logs where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching system logs
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the system logs where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching system logs
	*/
	public static List<SystemLog> findBymultipleGroupId(long groupId) {
		return getPersistence().findBymultipleGroupId(groupId);
	}

	/**
	* Returns a range of all the system logs where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @return the range of matching system logs
	*/
	public static List<SystemLog> findBymultipleGroupId(long groupId,
		int start, int end) {
		return getPersistence().findBymultipleGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the system logs where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching system logs
	*/
	public static List<SystemLog> findBymultipleGroupId(long groupId,
		int start, int end, OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence()
				   .findBymultipleGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the system logs where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching system logs
	*/
	public static List<SystemLog> findBymultipleGroupId(long groupId,
		int start, int end, OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findBymultipleGroupId(groupId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first system log in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public static SystemLog findBymultipleGroupId_First(long groupId,
		OrderByComparator<SystemLog> orderByComparator)
		throws org.opencps.backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence()
				   .findBymultipleGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first system log in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public static SystemLog fetchBymultipleGroupId_First(long groupId,
		OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence()
				   .fetchBymultipleGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last system log in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public static SystemLog findBymultipleGroupId_Last(long groupId,
		OrderByComparator<SystemLog> orderByComparator)
		throws org.opencps.backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence()
				   .findBymultipleGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last system log in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public static SystemLog fetchBymultipleGroupId_Last(long groupId,
		OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence()
				   .fetchBymultipleGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the system logs before and after the current system log in the ordered set where groupId = &#63;.
	*
	* @param logId the primary key of the current system log
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next system log
	* @throws NoSuchSystemLogException if a system log with the primary key could not be found
	*/
	public static SystemLog[] findBymultipleGroupId_PrevAndNext(long logId,
		long groupId, OrderByComparator<SystemLog> orderByComparator)
		throws org.opencps.backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence()
				   .findBymultipleGroupId_PrevAndNext(logId, groupId,
			orderByComparator);
	}

	/**
	* Returns all the system logs where groupId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupIds the group IDs
	* @return the matching system logs
	*/
	public static List<SystemLog> findBymultipleGroupId(long[] groupIds) {
		return getPersistence().findBymultipleGroupId(groupIds);
	}

	/**
	* Returns a range of all the system logs where groupId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupIds the group IDs
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @return the range of matching system logs
	*/
	public static List<SystemLog> findBymultipleGroupId(long[] groupIds,
		int start, int end) {
		return getPersistence().findBymultipleGroupId(groupIds, start, end);
	}

	/**
	* Returns an ordered range of all the system logs where groupId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupIds the group IDs
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching system logs
	*/
	public static List<SystemLog> findBymultipleGroupId(long[] groupIds,
		int start, int end, OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence()
				   .findBymultipleGroupId(groupIds, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the system logs where groupId = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching system logs
	*/
	public static List<SystemLog> findBymultipleGroupId(long[] groupIds,
		int start, int end, OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findBymultipleGroupId(groupIds, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the system logs where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeBymultipleGroupId(long groupId) {
		getPersistence().removeBymultipleGroupId(groupId);
	}

	/**
	* Returns the number of system logs where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching system logs
	*/
	public static int countBymultipleGroupId(long groupId) {
		return getPersistence().countBymultipleGroupId(groupId);
	}

	/**
	* Returns the number of system logs where groupId = any &#63;.
	*
	* @param groupIds the group IDs
	* @return the number of matching system logs
	*/
	public static int countBymultipleGroupId(long[] groupIds) {
		return getPersistence().countBymultipleGroupId(groupIds);
	}

	/**
	* Returns all the system logs where type = &#63;.
	*
	* @param type the type
	* @return the matching system logs
	*/
	public static List<SystemLog> findBymultipleType(String type) {
		return getPersistence().findBymultipleType(type);
	}

	/**
	* Returns a range of all the system logs where type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param type the type
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @return the range of matching system logs
	*/
	public static List<SystemLog> findBymultipleType(String type, int start,
		int end) {
		return getPersistence().findBymultipleType(type, start, end);
	}

	/**
	* Returns an ordered range of all the system logs where type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param type the type
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching system logs
	*/
	public static List<SystemLog> findBymultipleType(String type, int start,
		int end, OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence()
				   .findBymultipleType(type, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the system logs where type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param type the type
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching system logs
	*/
	public static List<SystemLog> findBymultipleType(String type, int start,
		int end, OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findBymultipleType(type, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first system log in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public static SystemLog findBymultipleType_First(String type,
		OrderByComparator<SystemLog> orderByComparator)
		throws org.opencps.backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence().findBymultipleType_First(type, orderByComparator);
	}

	/**
	* Returns the first system log in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public static SystemLog fetchBymultipleType_First(String type,
		OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence()
				   .fetchBymultipleType_First(type, orderByComparator);
	}

	/**
	* Returns the last system log in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public static SystemLog findBymultipleType_Last(String type,
		OrderByComparator<SystemLog> orderByComparator)
		throws org.opencps.backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence().findBymultipleType_Last(type, orderByComparator);
	}

	/**
	* Returns the last system log in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public static SystemLog fetchBymultipleType_Last(String type,
		OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence().fetchBymultipleType_Last(type, orderByComparator);
	}

	/**
	* Returns the system logs before and after the current system log in the ordered set where type = &#63;.
	*
	* @param logId the primary key of the current system log
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next system log
	* @throws NoSuchSystemLogException if a system log with the primary key could not be found
	*/
	public static SystemLog[] findBymultipleType_PrevAndNext(long logId,
		String type, OrderByComparator<SystemLog> orderByComparator)
		throws org.opencps.backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence()
				   .findBymultipleType_PrevAndNext(logId, type,
			orderByComparator);
	}

	/**
	* Returns all the system logs where type = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param types the types
	* @return the matching system logs
	*/
	public static List<SystemLog> findBymultipleType(String[] types) {
		return getPersistence().findBymultipleType(types);
	}

	/**
	* Returns a range of all the system logs where type = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param types the types
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @return the range of matching system logs
	*/
	public static List<SystemLog> findBymultipleType(String[] types, int start,
		int end) {
		return getPersistence().findBymultipleType(types, start, end);
	}

	/**
	* Returns an ordered range of all the system logs where type = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param types the types
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching system logs
	*/
	public static List<SystemLog> findBymultipleType(String[] types, int start,
		int end, OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence()
				   .findBymultipleType(types, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the system logs where type = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param type the type
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching system logs
	*/
	public static List<SystemLog> findBymultipleType(String[] types, int start,
		int end, OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findBymultipleType(types, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Removes all the system logs where type = &#63; from the database.
	*
	* @param type the type
	*/
	public static void removeBymultipleType(String type) {
		getPersistence().removeBymultipleType(type);
	}

	/**
	* Returns the number of system logs where type = &#63;.
	*
	* @param type the type
	* @return the number of matching system logs
	*/
	public static int countBymultipleType(String type) {
		return getPersistence().countBymultipleType(type);
	}

	/**
	* Returns the number of system logs where type = any &#63;.
	*
	* @param types the types
	* @return the number of matching system logs
	*/
	public static int countBymultipleType(String[] types) {
		return getPersistence().countBymultipleType(types);
	}

	/**
	* Returns all the system logs where moduleName = &#63;.
	*
	* @param moduleName the module name
	* @return the matching system logs
	*/
	public static List<SystemLog> findBymultiplemoduleName(String moduleName) {
		return getPersistence().findBymultiplemoduleName(moduleName);
	}

	/**
	* Returns a range of all the system logs where moduleName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param moduleName the module name
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @return the range of matching system logs
	*/
	public static List<SystemLog> findBymultiplemoduleName(String moduleName,
		int start, int end) {
		return getPersistence().findBymultiplemoduleName(moduleName, start, end);
	}

	/**
	* Returns an ordered range of all the system logs where moduleName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param moduleName the module name
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching system logs
	*/
	public static List<SystemLog> findBymultiplemoduleName(String moduleName,
		int start, int end, OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence()
				   .findBymultiplemoduleName(moduleName, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the system logs where moduleName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param moduleName the module name
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching system logs
	*/
	public static List<SystemLog> findBymultiplemoduleName(String moduleName,
		int start, int end, OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findBymultiplemoduleName(moduleName, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first system log in the ordered set where moduleName = &#63;.
	*
	* @param moduleName the module name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public static SystemLog findBymultiplemoduleName_First(String moduleName,
		OrderByComparator<SystemLog> orderByComparator)
		throws org.opencps.backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence()
				   .findBymultiplemoduleName_First(moduleName, orderByComparator);
	}

	/**
	* Returns the first system log in the ordered set where moduleName = &#63;.
	*
	* @param moduleName the module name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public static SystemLog fetchBymultiplemoduleName_First(String moduleName,
		OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence()
				   .fetchBymultiplemoduleName_First(moduleName,
			orderByComparator);
	}

	/**
	* Returns the last system log in the ordered set where moduleName = &#63;.
	*
	* @param moduleName the module name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public static SystemLog findBymultiplemoduleName_Last(String moduleName,
		OrderByComparator<SystemLog> orderByComparator)
		throws org.opencps.backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence()
				   .findBymultiplemoduleName_Last(moduleName, orderByComparator);
	}

	/**
	* Returns the last system log in the ordered set where moduleName = &#63;.
	*
	* @param moduleName the module name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public static SystemLog fetchBymultiplemoduleName_Last(String moduleName,
		OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence()
				   .fetchBymultiplemoduleName_Last(moduleName, orderByComparator);
	}

	/**
	* Returns the system logs before and after the current system log in the ordered set where moduleName = &#63;.
	*
	* @param logId the primary key of the current system log
	* @param moduleName the module name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next system log
	* @throws NoSuchSystemLogException if a system log with the primary key could not be found
	*/
	public static SystemLog[] findBymultiplemoduleName_PrevAndNext(long logId,
		String moduleName, OrderByComparator<SystemLog> orderByComparator)
		throws org.opencps.backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence()
				   .findBymultiplemoduleName_PrevAndNext(logId, moduleName,
			orderByComparator);
	}

	/**
	* Returns all the system logs where moduleName = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param moduleNames the module names
	* @return the matching system logs
	*/
	public static List<SystemLog> findBymultiplemoduleName(String[] moduleNames) {
		return getPersistence().findBymultiplemoduleName(moduleNames);
	}

	/**
	* Returns a range of all the system logs where moduleName = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param moduleNames the module names
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @return the range of matching system logs
	*/
	public static List<SystemLog> findBymultiplemoduleName(
		String[] moduleNames, int start, int end) {
		return getPersistence().findBymultiplemoduleName(moduleNames, start, end);
	}

	/**
	* Returns an ordered range of all the system logs where moduleName = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param moduleNames the module names
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching system logs
	*/
	public static List<SystemLog> findBymultiplemoduleName(
		String[] moduleNames, int start, int end,
		OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence()
				   .findBymultiplemoduleName(moduleNames, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the system logs where moduleName = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param moduleName the module name
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching system logs
	*/
	public static List<SystemLog> findBymultiplemoduleName(
		String[] moduleNames, int start, int end,
		OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findBymultiplemoduleName(moduleNames, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the system logs where moduleName = &#63; from the database.
	*
	* @param moduleName the module name
	*/
	public static void removeBymultiplemoduleName(String moduleName) {
		getPersistence().removeBymultiplemoduleName(moduleName);
	}

	/**
	* Returns the number of system logs where moduleName = &#63;.
	*
	* @param moduleName the module name
	* @return the number of matching system logs
	*/
	public static int countBymultiplemoduleName(String moduleName) {
		return getPersistence().countBymultiplemoduleName(moduleName);
	}

	/**
	* Returns the number of system logs where moduleName = any &#63;.
	*
	* @param moduleNames the module names
	* @return the number of matching system logs
	*/
	public static int countBymultiplemoduleName(String[] moduleNames) {
		return getPersistence().countBymultiplemoduleName(moduleNames);
	}

	/**
	* Returns all the system logs where preMethod = &#63;.
	*
	* @param preMethod the pre method
	* @return the matching system logs
	*/
	public static List<SystemLog> findBymultiplePreMethod(String preMethod) {
		return getPersistence().findBymultiplePreMethod(preMethod);
	}

	/**
	* Returns a range of all the system logs where preMethod = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param preMethod the pre method
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @return the range of matching system logs
	*/
	public static List<SystemLog> findBymultiplePreMethod(String preMethod,
		int start, int end) {
		return getPersistence().findBymultiplePreMethod(preMethod, start, end);
	}

	/**
	* Returns an ordered range of all the system logs where preMethod = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param preMethod the pre method
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching system logs
	*/
	public static List<SystemLog> findBymultiplePreMethod(String preMethod,
		int start, int end, OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence()
				   .findBymultiplePreMethod(preMethod, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the system logs where preMethod = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param preMethod the pre method
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching system logs
	*/
	public static List<SystemLog> findBymultiplePreMethod(String preMethod,
		int start, int end, OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findBymultiplePreMethod(preMethod, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first system log in the ordered set where preMethod = &#63;.
	*
	* @param preMethod the pre method
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public static SystemLog findBymultiplePreMethod_First(String preMethod,
		OrderByComparator<SystemLog> orderByComparator)
		throws org.opencps.backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence()
				   .findBymultiplePreMethod_First(preMethod, orderByComparator);
	}

	/**
	* Returns the first system log in the ordered set where preMethod = &#63;.
	*
	* @param preMethod the pre method
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public static SystemLog fetchBymultiplePreMethod_First(String preMethod,
		OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence()
				   .fetchBymultiplePreMethod_First(preMethod, orderByComparator);
	}

	/**
	* Returns the last system log in the ordered set where preMethod = &#63;.
	*
	* @param preMethod the pre method
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public static SystemLog findBymultiplePreMethod_Last(String preMethod,
		OrderByComparator<SystemLog> orderByComparator)
		throws org.opencps.backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence()
				   .findBymultiplePreMethod_Last(preMethod, orderByComparator);
	}

	/**
	* Returns the last system log in the ordered set where preMethod = &#63;.
	*
	* @param preMethod the pre method
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public static SystemLog fetchBymultiplePreMethod_Last(String preMethod,
		OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence()
				   .fetchBymultiplePreMethod_Last(preMethod, orderByComparator);
	}

	/**
	* Returns the system logs before and after the current system log in the ordered set where preMethod = &#63;.
	*
	* @param logId the primary key of the current system log
	* @param preMethod the pre method
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next system log
	* @throws NoSuchSystemLogException if a system log with the primary key could not be found
	*/
	public static SystemLog[] findBymultiplePreMethod_PrevAndNext(long logId,
		String preMethod, OrderByComparator<SystemLog> orderByComparator)
		throws org.opencps.backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence()
				   .findBymultiplePreMethod_PrevAndNext(logId, preMethod,
			orderByComparator);
	}

	/**
	* Returns all the system logs where preMethod = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param preMethods the pre methods
	* @return the matching system logs
	*/
	public static List<SystemLog> findBymultiplePreMethod(String[] preMethods) {
		return getPersistence().findBymultiplePreMethod(preMethods);
	}

	/**
	* Returns a range of all the system logs where preMethod = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param preMethods the pre methods
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @return the range of matching system logs
	*/
	public static List<SystemLog> findBymultiplePreMethod(String[] preMethods,
		int start, int end) {
		return getPersistence().findBymultiplePreMethod(preMethods, start, end);
	}

	/**
	* Returns an ordered range of all the system logs where preMethod = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param preMethods the pre methods
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching system logs
	*/
	public static List<SystemLog> findBymultiplePreMethod(String[] preMethods,
		int start, int end, OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence()
				   .findBymultiplePreMethod(preMethods, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the system logs where preMethod = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param preMethod the pre method
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching system logs
	*/
	public static List<SystemLog> findBymultiplePreMethod(String[] preMethods,
		int start, int end, OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findBymultiplePreMethod(preMethods, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the system logs where preMethod = &#63; from the database.
	*
	* @param preMethod the pre method
	*/
	public static void removeBymultiplePreMethod(String preMethod) {
		getPersistence().removeBymultiplePreMethod(preMethod);
	}

	/**
	* Returns the number of system logs where preMethod = &#63;.
	*
	* @param preMethod the pre method
	* @return the number of matching system logs
	*/
	public static int countBymultiplePreMethod(String preMethod) {
		return getPersistence().countBymultiplePreMethod(preMethod);
	}

	/**
	* Returns the number of system logs where preMethod = any &#63;.
	*
	* @param preMethods the pre methods
	* @return the number of matching system logs
	*/
	public static int countBymultiplePreMethod(String[] preMethods) {
		return getPersistence().countBymultiplePreMethod(preMethods);
	}

	/**
	* Returns all the system logs where method = &#63;.
	*
	* @param method the method
	* @return the matching system logs
	*/
	public static List<SystemLog> findBymultipleMethod(String method) {
		return getPersistence().findBymultipleMethod(method);
	}

	/**
	* Returns a range of all the system logs where method = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param method the method
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @return the range of matching system logs
	*/
	public static List<SystemLog> findBymultipleMethod(String method,
		int start, int end) {
		return getPersistence().findBymultipleMethod(method, start, end);
	}

	/**
	* Returns an ordered range of all the system logs where method = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param method the method
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching system logs
	*/
	public static List<SystemLog> findBymultipleMethod(String method,
		int start, int end, OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence()
				   .findBymultipleMethod(method, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the system logs where method = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param method the method
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching system logs
	*/
	public static List<SystemLog> findBymultipleMethod(String method,
		int start, int end, OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findBymultipleMethod(method, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first system log in the ordered set where method = &#63;.
	*
	* @param method the method
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public static SystemLog findBymultipleMethod_First(String method,
		OrderByComparator<SystemLog> orderByComparator)
		throws org.opencps.backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence()
				   .findBymultipleMethod_First(method, orderByComparator);
	}

	/**
	* Returns the first system log in the ordered set where method = &#63;.
	*
	* @param method the method
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public static SystemLog fetchBymultipleMethod_First(String method,
		OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence()
				   .fetchBymultipleMethod_First(method, orderByComparator);
	}

	/**
	* Returns the last system log in the ordered set where method = &#63;.
	*
	* @param method the method
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public static SystemLog findBymultipleMethod_Last(String method,
		OrderByComparator<SystemLog> orderByComparator)
		throws org.opencps.backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence()
				   .findBymultipleMethod_Last(method, orderByComparator);
	}

	/**
	* Returns the last system log in the ordered set where method = &#63;.
	*
	* @param method the method
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public static SystemLog fetchBymultipleMethod_Last(String method,
		OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence()
				   .fetchBymultipleMethod_Last(method, orderByComparator);
	}

	/**
	* Returns the system logs before and after the current system log in the ordered set where method = &#63;.
	*
	* @param logId the primary key of the current system log
	* @param method the method
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next system log
	* @throws NoSuchSystemLogException if a system log with the primary key could not be found
	*/
	public static SystemLog[] findBymultipleMethod_PrevAndNext(long logId,
		String method, OrderByComparator<SystemLog> orderByComparator)
		throws org.opencps.backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence()
				   .findBymultipleMethod_PrevAndNext(logId, method,
			orderByComparator);
	}

	/**
	* Returns all the system logs where method = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param methods the methods
	* @return the matching system logs
	*/
	public static List<SystemLog> findBymultipleMethod(String[] methods) {
		return getPersistence().findBymultipleMethod(methods);
	}

	/**
	* Returns a range of all the system logs where method = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param methods the methods
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @return the range of matching system logs
	*/
	public static List<SystemLog> findBymultipleMethod(String[] methods,
		int start, int end) {
		return getPersistence().findBymultipleMethod(methods, start, end);
	}

	/**
	* Returns an ordered range of all the system logs where method = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param methods the methods
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching system logs
	*/
	public static List<SystemLog> findBymultipleMethod(String[] methods,
		int start, int end, OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence()
				   .findBymultipleMethod(methods, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the system logs where method = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param method the method
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching system logs
	*/
	public static List<SystemLog> findBymultipleMethod(String[] methods,
		int start, int end, OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findBymultipleMethod(methods, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the system logs where method = &#63; from the database.
	*
	* @param method the method
	*/
	public static void removeBymultipleMethod(String method) {
		getPersistence().removeBymultipleMethod(method);
	}

	/**
	* Returns the number of system logs where method = &#63;.
	*
	* @param method the method
	* @return the number of matching system logs
	*/
	public static int countBymultipleMethod(String method) {
		return getPersistence().countBymultipleMethod(method);
	}

	/**
	* Returns the number of system logs where method = any &#63;.
	*
	* @param methods the methods
	* @return the number of matching system logs
	*/
	public static int countBymultipleMethod(String[] methods) {
		return getPersistence().countBymultipleMethod(methods);
	}

	/**
	* Returns all the system logs where threadId = &#63;.
	*
	* @param threadId the thread ID
	* @return the matching system logs
	*/
	public static List<SystemLog> findBymultipleThreadId(String threadId) {
		return getPersistence().findBymultipleThreadId(threadId);
	}

	/**
	* Returns a range of all the system logs where threadId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param threadId the thread ID
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @return the range of matching system logs
	*/
	public static List<SystemLog> findBymultipleThreadId(String threadId,
		int start, int end) {
		return getPersistence().findBymultipleThreadId(threadId, start, end);
	}

	/**
	* Returns an ordered range of all the system logs where threadId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param threadId the thread ID
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching system logs
	*/
	public static List<SystemLog> findBymultipleThreadId(String threadId,
		int start, int end, OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence()
				   .findBymultipleThreadId(threadId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the system logs where threadId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param threadId the thread ID
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching system logs
	*/
	public static List<SystemLog> findBymultipleThreadId(String threadId,
		int start, int end, OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findBymultipleThreadId(threadId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first system log in the ordered set where threadId = &#63;.
	*
	* @param threadId the thread ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public static SystemLog findBymultipleThreadId_First(String threadId,
		OrderByComparator<SystemLog> orderByComparator)
		throws org.opencps.backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence()
				   .findBymultipleThreadId_First(threadId, orderByComparator);
	}

	/**
	* Returns the first system log in the ordered set where threadId = &#63;.
	*
	* @param threadId the thread ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public static SystemLog fetchBymultipleThreadId_First(String threadId,
		OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence()
				   .fetchBymultipleThreadId_First(threadId, orderByComparator);
	}

	/**
	* Returns the last system log in the ordered set where threadId = &#63;.
	*
	* @param threadId the thread ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public static SystemLog findBymultipleThreadId_Last(String threadId,
		OrderByComparator<SystemLog> orderByComparator)
		throws org.opencps.backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence()
				   .findBymultipleThreadId_Last(threadId, orderByComparator);
	}

	/**
	* Returns the last system log in the ordered set where threadId = &#63;.
	*
	* @param threadId the thread ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public static SystemLog fetchBymultipleThreadId_Last(String threadId,
		OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence()
				   .fetchBymultipleThreadId_Last(threadId, orderByComparator);
	}

	/**
	* Returns the system logs before and after the current system log in the ordered set where threadId = &#63;.
	*
	* @param logId the primary key of the current system log
	* @param threadId the thread ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next system log
	* @throws NoSuchSystemLogException if a system log with the primary key could not be found
	*/
	public static SystemLog[] findBymultipleThreadId_PrevAndNext(long logId,
		String threadId, OrderByComparator<SystemLog> orderByComparator)
		throws org.opencps.backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence()
				   .findBymultipleThreadId_PrevAndNext(logId, threadId,
			orderByComparator);
	}

	/**
	* Returns all the system logs where threadId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param threadIds the thread IDs
	* @return the matching system logs
	*/
	public static List<SystemLog> findBymultipleThreadId(String[] threadIds) {
		return getPersistence().findBymultipleThreadId(threadIds);
	}

	/**
	* Returns a range of all the system logs where threadId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param threadIds the thread IDs
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @return the range of matching system logs
	*/
	public static List<SystemLog> findBymultipleThreadId(String[] threadIds,
		int start, int end) {
		return getPersistence().findBymultipleThreadId(threadIds, start, end);
	}

	/**
	* Returns an ordered range of all the system logs where threadId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param threadIds the thread IDs
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching system logs
	*/
	public static List<SystemLog> findBymultipleThreadId(String[] threadIds,
		int start, int end, OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence()
				   .findBymultipleThreadId(threadIds, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the system logs where threadId = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param threadId the thread ID
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching system logs
	*/
	public static List<SystemLog> findBymultipleThreadId(String[] threadIds,
		int start, int end, OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findBymultipleThreadId(threadIds, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the system logs where threadId = &#63; from the database.
	*
	* @param threadId the thread ID
	*/
	public static void removeBymultipleThreadId(String threadId) {
		getPersistence().removeBymultipleThreadId(threadId);
	}

	/**
	* Returns the number of system logs where threadId = &#63;.
	*
	* @param threadId the thread ID
	* @return the number of matching system logs
	*/
	public static int countBymultipleThreadId(String threadId) {
		return getPersistence().countBymultipleThreadId(threadId);
	}

	/**
	* Returns the number of system logs where threadId = any &#63;.
	*
	* @param threadIds the thread IDs
	* @return the number of matching system logs
	*/
	public static int countBymultipleThreadId(String[] threadIds) {
		return getPersistence().countBymultipleThreadId(threadIds);
	}

	/**
	* Returns all the system logs where message LIKE &#63;.
	*
	* @param message the message
	* @return the matching system logs
	*/
	public static List<SystemLog> findBylikeMessage(String message) {
		return getPersistence().findBylikeMessage(message);
	}

	/**
	* Returns a range of all the system logs where message LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param message the message
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @return the range of matching system logs
	*/
	public static List<SystemLog> findBylikeMessage(String message, int start,
		int end) {
		return getPersistence().findBylikeMessage(message, start, end);
	}

	/**
	* Returns an ordered range of all the system logs where message LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param message the message
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching system logs
	*/
	public static List<SystemLog> findBylikeMessage(String message, int start,
		int end, OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence()
				   .findBylikeMessage(message, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the system logs where message LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param message the message
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching system logs
	*/
	public static List<SystemLog> findBylikeMessage(String message, int start,
		int end, OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findBylikeMessage(message, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first system log in the ordered set where message LIKE &#63;.
	*
	* @param message the message
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public static SystemLog findBylikeMessage_First(String message,
		OrderByComparator<SystemLog> orderByComparator)
		throws org.opencps.backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence()
				   .findBylikeMessage_First(message, orderByComparator);
	}

	/**
	* Returns the first system log in the ordered set where message LIKE &#63;.
	*
	* @param message the message
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public static SystemLog fetchBylikeMessage_First(String message,
		OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence()
				   .fetchBylikeMessage_First(message, orderByComparator);
	}

	/**
	* Returns the last system log in the ordered set where message LIKE &#63;.
	*
	* @param message the message
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public static SystemLog findBylikeMessage_Last(String message,
		OrderByComparator<SystemLog> orderByComparator)
		throws org.opencps.backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence()
				   .findBylikeMessage_Last(message, orderByComparator);
	}

	/**
	* Returns the last system log in the ordered set where message LIKE &#63;.
	*
	* @param message the message
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public static SystemLog fetchBylikeMessage_Last(String message,
		OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence()
				   .fetchBylikeMessage_Last(message, orderByComparator);
	}

	/**
	* Returns the system logs before and after the current system log in the ordered set where message LIKE &#63;.
	*
	* @param logId the primary key of the current system log
	* @param message the message
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next system log
	* @throws NoSuchSystemLogException if a system log with the primary key could not be found
	*/
	public static SystemLog[] findBylikeMessage_PrevAndNext(long logId,
		String message, OrderByComparator<SystemLog> orderByComparator)
		throws org.opencps.backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence()
				   .findBylikeMessage_PrevAndNext(logId, message,
			orderByComparator);
	}

	/**
	* Removes all the system logs where message LIKE &#63; from the database.
	*
	* @param message the message
	*/
	public static void removeBylikeMessage(String message) {
		getPersistence().removeBylikeMessage(message);
	}

	/**
	* Returns the number of system logs where message LIKE &#63;.
	*
	* @param message the message
	* @return the number of matching system logs
	*/
	public static int countBylikeMessage(String message) {
		return getPersistence().countBylikeMessage(message);
	}

	/**
	* Caches the system log in the entity cache if it is enabled.
	*
	* @param systemLog the system log
	*/
	public static void cacheResult(SystemLog systemLog) {
		getPersistence().cacheResult(systemLog);
	}

	/**
	* Caches the system logs in the entity cache if it is enabled.
	*
	* @param systemLogs the system logs
	*/
	public static void cacheResult(List<SystemLog> systemLogs) {
		getPersistence().cacheResult(systemLogs);
	}

	/**
	* Creates a new system log with the primary key. Does not add the system log to the database.
	*
	* @param logId the primary key for the new system log
	* @return the new system log
	*/
	public static SystemLog create(long logId) {
		return getPersistence().create(logId);
	}

	/**
	* Removes the system log with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param logId the primary key of the system log
	* @return the system log that was removed
	* @throws NoSuchSystemLogException if a system log with the primary key could not be found
	*/
	public static SystemLog remove(long logId)
		throws org.opencps.backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence().remove(logId);
	}

	public static SystemLog updateImpl(SystemLog systemLog) {
		return getPersistence().updateImpl(systemLog);
	}

	/**
	* Returns the system log with the primary key or throws a {@link NoSuchSystemLogException} if it could not be found.
	*
	* @param logId the primary key of the system log
	* @return the system log
	* @throws NoSuchSystemLogException if a system log with the primary key could not be found
	*/
	public static SystemLog findByPrimaryKey(long logId)
		throws org.opencps.backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence().findByPrimaryKey(logId);
	}

	/**
	* Returns the system log with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param logId the primary key of the system log
	* @return the system log, or <code>null</code> if a system log with the primary key could not be found
	*/
	public static SystemLog fetchByPrimaryKey(long logId) {
		return getPersistence().fetchByPrimaryKey(logId);
	}

	public static java.util.Map<java.io.Serializable, SystemLog> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the system logs.
	*
	* @return the system logs
	*/
	public static List<SystemLog> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the system logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @return the range of system logs
	*/
	public static List<SystemLog> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the system logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of system logs
	*/
	public static List<SystemLog> findAll(int start, int end,
		OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the system logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of system logs
	*/
	public static List<SystemLog> findAll(int start, int end,
		OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the system logs from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of system logs.
	*
	* @return the number of system logs
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static SystemLogPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<SystemLogPersistence, SystemLogPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(SystemLogPersistence.class);

		ServiceTracker<SystemLogPersistence, SystemLogPersistence> serviceTracker =
			new ServiceTracker<SystemLogPersistence, SystemLogPersistence>(bundle.getBundleContext(),
				SystemLogPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}