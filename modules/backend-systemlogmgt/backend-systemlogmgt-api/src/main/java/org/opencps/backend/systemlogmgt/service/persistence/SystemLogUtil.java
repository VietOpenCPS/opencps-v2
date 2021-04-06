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