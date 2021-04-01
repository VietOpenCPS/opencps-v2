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

package backend.systemlogmgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import backend.systemlogmgt.model.SystemLog;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.Date;
import java.util.List;

/**
 * The persistence utility for the system log service. This utility wraps {@link backend.systemlogmgt.service.persistence.impl.SystemLogPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SystemLogPersistence
 * @see backend.systemlogmgt.service.persistence.impl.SystemLogPersistenceImpl
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
		throws backend.systemlogmgt.exception.NoSuchSystemLogException {
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
		throws backend.systemlogmgt.exception.NoSuchSystemLogException {
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
		throws backend.systemlogmgt.exception.NoSuchSystemLogException {
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
		throws backend.systemlogmgt.exception.NoSuchSystemLogException {
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
		throws backend.systemlogmgt.exception.NoSuchSystemLogException {
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
	* Returns all the system logs where createDate &ge; &#63;.
	*
	* @param createDate the create date
	* @return the matching system logs
	*/
	public static List<SystemLog> findByCreateDateGreater(Date createDate) {
		return getPersistence().findByCreateDateGreater(createDate);
	}

	/**
	* Returns a range of all the system logs where createDate &ge; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createDate the create date
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @return the range of matching system logs
	*/
	public static List<SystemLog> findByCreateDateGreater(Date createDate,
		int start, int end) {
		return getPersistence().findByCreateDateGreater(createDate, start, end);
	}

	/**
	* Returns an ordered range of all the system logs where createDate &ge; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createDate the create date
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching system logs
	*/
	public static List<SystemLog> findByCreateDateGreater(Date createDate,
		int start, int end, OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence()
				   .findByCreateDateGreater(createDate, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the system logs where createDate &ge; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createDate the create date
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching system logs
	*/
	public static List<SystemLog> findByCreateDateGreater(Date createDate,
		int start, int end, OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCreateDateGreater(createDate, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first system log in the ordered set where createDate &ge; &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public static SystemLog findByCreateDateGreater_First(Date createDate,
		OrderByComparator<SystemLog> orderByComparator)
		throws backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence()
				   .findByCreateDateGreater_First(createDate, orderByComparator);
	}

	/**
	* Returns the first system log in the ordered set where createDate &ge; &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public static SystemLog fetchByCreateDateGreater_First(Date createDate,
		OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence()
				   .fetchByCreateDateGreater_First(createDate, orderByComparator);
	}

	/**
	* Returns the last system log in the ordered set where createDate &ge; &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public static SystemLog findByCreateDateGreater_Last(Date createDate,
		OrderByComparator<SystemLog> orderByComparator)
		throws backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence()
				   .findByCreateDateGreater_Last(createDate, orderByComparator);
	}

	/**
	* Returns the last system log in the ordered set where createDate &ge; &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public static SystemLog fetchByCreateDateGreater_Last(Date createDate,
		OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence()
				   .fetchByCreateDateGreater_Last(createDate, orderByComparator);
	}

	/**
	* Returns the system logs before and after the current system log in the ordered set where createDate &ge; &#63;.
	*
	* @param logId the primary key of the current system log
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next system log
	* @throws NoSuchSystemLogException if a system log with the primary key could not be found
	*/
	public static SystemLog[] findByCreateDateGreater_PrevAndNext(long logId,
		Date createDate, OrderByComparator<SystemLog> orderByComparator)
		throws backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence()
				   .findByCreateDateGreater_PrevAndNext(logId, createDate,
			orderByComparator);
	}

	/**
	* Removes all the system logs where createDate &ge; &#63; from the database.
	*
	* @param createDate the create date
	*/
	public static void removeByCreateDateGreater(Date createDate) {
		getPersistence().removeByCreateDateGreater(createDate);
	}

	/**
	* Returns the number of system logs where createDate &ge; &#63;.
	*
	* @param createDate the create date
	* @return the number of matching system logs
	*/
	public static int countByCreateDateGreater(Date createDate) {
		return getPersistence().countByCreateDateGreater(createDate);
	}

	/**
	* Returns all the system logs where createDate &le; &#63;.
	*
	* @param createDate the create date
	* @return the matching system logs
	*/
	public static List<SystemLog> findByCreateDateLesser(Date createDate) {
		return getPersistence().findByCreateDateLesser(createDate);
	}

	/**
	* Returns a range of all the system logs where createDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createDate the create date
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @return the range of matching system logs
	*/
	public static List<SystemLog> findByCreateDateLesser(Date createDate,
		int start, int end) {
		return getPersistence().findByCreateDateLesser(createDate, start, end);
	}

	/**
	* Returns an ordered range of all the system logs where createDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createDate the create date
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching system logs
	*/
	public static List<SystemLog> findByCreateDateLesser(Date createDate,
		int start, int end, OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence()
				   .findByCreateDateLesser(createDate, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the system logs where createDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createDate the create date
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching system logs
	*/
	public static List<SystemLog> findByCreateDateLesser(Date createDate,
		int start, int end, OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCreateDateLesser(createDate, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first system log in the ordered set where createDate &le; &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public static SystemLog findByCreateDateLesser_First(Date createDate,
		OrderByComparator<SystemLog> orderByComparator)
		throws backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence()
				   .findByCreateDateLesser_First(createDate, orderByComparator);
	}

	/**
	* Returns the first system log in the ordered set where createDate &le; &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public static SystemLog fetchByCreateDateLesser_First(Date createDate,
		OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence()
				   .fetchByCreateDateLesser_First(createDate, orderByComparator);
	}

	/**
	* Returns the last system log in the ordered set where createDate &le; &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public static SystemLog findByCreateDateLesser_Last(Date createDate,
		OrderByComparator<SystemLog> orderByComparator)
		throws backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence()
				   .findByCreateDateLesser_Last(createDate, orderByComparator);
	}

	/**
	* Returns the last system log in the ordered set where createDate &le; &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public static SystemLog fetchByCreateDateLesser_Last(Date createDate,
		OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence()
				   .fetchByCreateDateLesser_Last(createDate, orderByComparator);
	}

	/**
	* Returns the system logs before and after the current system log in the ordered set where createDate &le; &#63;.
	*
	* @param logId the primary key of the current system log
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next system log
	* @throws NoSuchSystemLogException if a system log with the primary key could not be found
	*/
	public static SystemLog[] findByCreateDateLesser_PrevAndNext(long logId,
		Date createDate, OrderByComparator<SystemLog> orderByComparator)
		throws backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence()
				   .findByCreateDateLesser_PrevAndNext(logId, createDate,
			orderByComparator);
	}

	/**
	* Removes all the system logs where createDate &le; &#63; from the database.
	*
	* @param createDate the create date
	*/
	public static void removeByCreateDateLesser(Date createDate) {
		getPersistence().removeByCreateDateLesser(createDate);
	}

	/**
	* Returns the number of system logs where createDate &le; &#63;.
	*
	* @param createDate the create date
	* @return the number of matching system logs
	*/
	public static int countByCreateDateLesser(Date createDate) {
		return getPersistence().countByCreateDateLesser(createDate);
	}

	/**
	* Returns the system log where logId = &#63; or throws a {@link NoSuchSystemLogException} if it could not be found.
	*
	* @param logId the log ID
	* @return the matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public static SystemLog findByLogId(long logId)
		throws backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence().findByLogId(logId);
	}

	/**
	* Returns the system log where logId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param logId the log ID
	* @return the matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public static SystemLog fetchByLogId(long logId) {
		return getPersistence().fetchByLogId(logId);
	}

	/**
	* Returns the system log where logId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param logId the log ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public static SystemLog fetchByLogId(long logId, boolean retrieveFromCache) {
		return getPersistence().fetchByLogId(logId, retrieveFromCache);
	}

	/**
	* Removes the system log where logId = &#63; from the database.
	*
	* @param logId the log ID
	* @return the system log that was removed
	*/
	public static SystemLog removeByLogId(long logId)
		throws backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence().removeByLogId(logId);
	}

	/**
	* Returns the number of system logs where logId = &#63;.
	*
	* @param logId the log ID
	* @return the number of matching system logs
	*/
	public static int countByLogId(long logId) {
		return getPersistence().countByLogId(logId);
	}

	/**
	* Returns all the system logs where className = &#63;.
	*
	* @param className the class name
	* @return the matching system logs
	*/
	public static List<SystemLog> findByClassName(String className) {
		return getPersistence().findByClassName(className);
	}

	/**
	* Returns a range of all the system logs where className = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param className the class name
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @return the range of matching system logs
	*/
	public static List<SystemLog> findByClassName(String className, int start,
		int end) {
		return getPersistence().findByClassName(className, start, end);
	}

	/**
	* Returns an ordered range of all the system logs where className = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param className the class name
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching system logs
	*/
	public static List<SystemLog> findByClassName(String className, int start,
		int end, OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence()
				   .findByClassName(className, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the system logs where className = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param className the class name
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching system logs
	*/
	public static List<SystemLog> findByClassName(String className, int start,
		int end, OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByClassName(className, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first system log in the ordered set where className = &#63;.
	*
	* @param className the class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public static SystemLog findByClassName_First(String className,
		OrderByComparator<SystemLog> orderByComparator)
		throws backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence()
				   .findByClassName_First(className, orderByComparator);
	}

	/**
	* Returns the first system log in the ordered set where className = &#63;.
	*
	* @param className the class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public static SystemLog fetchByClassName_First(String className,
		OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence()
				   .fetchByClassName_First(className, orderByComparator);
	}

	/**
	* Returns the last system log in the ordered set where className = &#63;.
	*
	* @param className the class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public static SystemLog findByClassName_Last(String className,
		OrderByComparator<SystemLog> orderByComparator)
		throws backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence()
				   .findByClassName_Last(className, orderByComparator);
	}

	/**
	* Returns the last system log in the ordered set where className = &#63;.
	*
	* @param className the class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public static SystemLog fetchByClassName_Last(String className,
		OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence()
				   .fetchByClassName_Last(className, orderByComparator);
	}

	/**
	* Returns the system logs before and after the current system log in the ordered set where className = &#63;.
	*
	* @param logId the primary key of the current system log
	* @param className the class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next system log
	* @throws NoSuchSystemLogException if a system log with the primary key could not be found
	*/
	public static SystemLog[] findByClassName_PrevAndNext(long logId,
		String className, OrderByComparator<SystemLog> orderByComparator)
		throws backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence()
				   .findByClassName_PrevAndNext(logId, className,
			orderByComparator);
	}

	/**
	* Removes all the system logs where className = &#63; from the database.
	*
	* @param className the class name
	*/
	public static void removeByClassName(String className) {
		getPersistence().removeByClassName(className);
	}

	/**
	* Returns the number of system logs where className = &#63;.
	*
	* @param className the class name
	* @return the number of matching system logs
	*/
	public static int countByClassName(String className) {
		return getPersistence().countByClassName(className);
	}

	/**
	* Returns all the system logs where moduleName = &#63;.
	*
	* @param moduleName the module name
	* @return the matching system logs
	*/
	public static List<SystemLog> findByModuleName(String moduleName) {
		return getPersistence().findByModuleName(moduleName);
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
	public static List<SystemLog> findByModuleName(String moduleName,
		int start, int end) {
		return getPersistence().findByModuleName(moduleName, start, end);
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
	public static List<SystemLog> findByModuleName(String moduleName,
		int start, int end, OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence()
				   .findByModuleName(moduleName, start, end, orderByComparator);
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
	public static List<SystemLog> findByModuleName(String moduleName,
		int start, int end, OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByModuleName(moduleName, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first system log in the ordered set where moduleName = &#63;.
	*
	* @param moduleName the module name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public static SystemLog findByModuleName_First(String moduleName,
		OrderByComparator<SystemLog> orderByComparator)
		throws backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence()
				   .findByModuleName_First(moduleName, orderByComparator);
	}

	/**
	* Returns the first system log in the ordered set where moduleName = &#63;.
	*
	* @param moduleName the module name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public static SystemLog fetchByModuleName_First(String moduleName,
		OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence()
				   .fetchByModuleName_First(moduleName, orderByComparator);
	}

	/**
	* Returns the last system log in the ordered set where moduleName = &#63;.
	*
	* @param moduleName the module name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public static SystemLog findByModuleName_Last(String moduleName,
		OrderByComparator<SystemLog> orderByComparator)
		throws backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence()
				   .findByModuleName_Last(moduleName, orderByComparator);
	}

	/**
	* Returns the last system log in the ordered set where moduleName = &#63;.
	*
	* @param moduleName the module name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public static SystemLog fetchByModuleName_Last(String moduleName,
		OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence()
				   .fetchByModuleName_Last(moduleName, orderByComparator);
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
	public static SystemLog[] findByModuleName_PrevAndNext(long logId,
		String moduleName, OrderByComparator<SystemLog> orderByComparator)
		throws backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence()
				   .findByModuleName_PrevAndNext(logId, moduleName,
			orderByComparator);
	}

	/**
	* Removes all the system logs where moduleName = &#63; from the database.
	*
	* @param moduleName the module name
	*/
	public static void removeByModuleName(String moduleName) {
		getPersistence().removeByModuleName(moduleName);
	}

	/**
	* Returns the number of system logs where moduleName = &#63;.
	*
	* @param moduleName the module name
	* @return the number of matching system logs
	*/
	public static int countByModuleName(String moduleName) {
		return getPersistence().countByModuleName(moduleName);
	}

	/**
	* Returns all the system logs where type = &#63;.
	*
	* @param type the type
	* @return the matching system logs
	*/
	public static List<SystemLog> findByType(String type) {
		return getPersistence().findByType(type);
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
	public static List<SystemLog> findByType(String type, int start, int end) {
		return getPersistence().findByType(type, start, end);
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
	public static List<SystemLog> findByType(String type, int start, int end,
		OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence().findByType(type, start, end, orderByComparator);
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
	public static List<SystemLog> findByType(String type, int start, int end,
		OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByType(type, start, end, orderByComparator,
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
	public static SystemLog findByType_First(String type,
		OrderByComparator<SystemLog> orderByComparator)
		throws backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence().findByType_First(type, orderByComparator);
	}

	/**
	* Returns the first system log in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public static SystemLog fetchByType_First(String type,
		OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence().fetchByType_First(type, orderByComparator);
	}

	/**
	* Returns the last system log in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public static SystemLog findByType_Last(String type,
		OrderByComparator<SystemLog> orderByComparator)
		throws backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence().findByType_Last(type, orderByComparator);
	}

	/**
	* Returns the last system log in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public static SystemLog fetchByType_Last(String type,
		OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence().fetchByType_Last(type, orderByComparator);
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
	public static SystemLog[] findByType_PrevAndNext(long logId, String type,
		OrderByComparator<SystemLog> orderByComparator)
		throws backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence()
				   .findByType_PrevAndNext(logId, type, orderByComparator);
	}

	/**
	* Removes all the system logs where type = &#63; from the database.
	*
	* @param type the type
	*/
	public static void removeByType(String type) {
		getPersistence().removeByType(type);
	}

	/**
	* Returns the number of system logs where type = &#63;.
	*
	* @param type the type
	* @return the number of matching system logs
	*/
	public static int countByType(String type) {
		return getPersistence().countByType(type);
	}

	/**
	* Returns all the system logs where line = &#63;.
	*
	* @param line the line
	* @return the matching system logs
	*/
	public static List<SystemLog> findByLine(int line) {
		return getPersistence().findByLine(line);
	}

	/**
	* Returns a range of all the system logs where line = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param line the line
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @return the range of matching system logs
	*/
	public static List<SystemLog> findByLine(int line, int start, int end) {
		return getPersistence().findByLine(line, start, end);
	}

	/**
	* Returns an ordered range of all the system logs where line = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param line the line
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching system logs
	*/
	public static List<SystemLog> findByLine(int line, int start, int end,
		OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence().findByLine(line, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the system logs where line = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param line the line
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching system logs
	*/
	public static List<SystemLog> findByLine(int line, int start, int end,
		OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByLine(line, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first system log in the ordered set where line = &#63;.
	*
	* @param line the line
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public static SystemLog findByLine_First(int line,
		OrderByComparator<SystemLog> orderByComparator)
		throws backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence().findByLine_First(line, orderByComparator);
	}

	/**
	* Returns the first system log in the ordered set where line = &#63;.
	*
	* @param line the line
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public static SystemLog fetchByLine_First(int line,
		OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence().fetchByLine_First(line, orderByComparator);
	}

	/**
	* Returns the last system log in the ordered set where line = &#63;.
	*
	* @param line the line
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public static SystemLog findByLine_Last(int line,
		OrderByComparator<SystemLog> orderByComparator)
		throws backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence().findByLine_Last(line, orderByComparator);
	}

	/**
	* Returns the last system log in the ordered set where line = &#63;.
	*
	* @param line the line
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public static SystemLog fetchByLine_Last(int line,
		OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence().fetchByLine_Last(line, orderByComparator);
	}

	/**
	* Returns the system logs before and after the current system log in the ordered set where line = &#63;.
	*
	* @param logId the primary key of the current system log
	* @param line the line
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next system log
	* @throws NoSuchSystemLogException if a system log with the primary key could not be found
	*/
	public static SystemLog[] findByLine_PrevAndNext(long logId, int line,
		OrderByComparator<SystemLog> orderByComparator)
		throws backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence()
				   .findByLine_PrevAndNext(logId, line, orderByComparator);
	}

	/**
	* Removes all the system logs where line = &#63; from the database.
	*
	* @param line the line
	*/
	public static void removeByLine(int line) {
		getPersistence().removeByLine(line);
	}

	/**
	* Returns the number of system logs where line = &#63;.
	*
	* @param line the line
	* @return the number of matching system logs
	*/
	public static int countByLine(int line) {
		return getPersistence().countByLine(line);
	}

	/**
	* Returns all the system logs where line &gt; &#63;.
	*
	* @param line the line
	* @return the matching system logs
	*/
	public static List<SystemLog> findByLineGreater(int line) {
		return getPersistence().findByLineGreater(line);
	}

	/**
	* Returns a range of all the system logs where line &gt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param line the line
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @return the range of matching system logs
	*/
	public static List<SystemLog> findByLineGreater(int line, int start, int end) {
		return getPersistence().findByLineGreater(line, start, end);
	}

	/**
	* Returns an ordered range of all the system logs where line &gt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param line the line
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching system logs
	*/
	public static List<SystemLog> findByLineGreater(int line, int start,
		int end, OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence()
				   .findByLineGreater(line, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the system logs where line &gt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param line the line
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching system logs
	*/
	public static List<SystemLog> findByLineGreater(int line, int start,
		int end, OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByLineGreater(line, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first system log in the ordered set where line &gt; &#63;.
	*
	* @param line the line
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public static SystemLog findByLineGreater_First(int line,
		OrderByComparator<SystemLog> orderByComparator)
		throws backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence().findByLineGreater_First(line, orderByComparator);
	}

	/**
	* Returns the first system log in the ordered set where line &gt; &#63;.
	*
	* @param line the line
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public static SystemLog fetchByLineGreater_First(int line,
		OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence().fetchByLineGreater_First(line, orderByComparator);
	}

	/**
	* Returns the last system log in the ordered set where line &gt; &#63;.
	*
	* @param line the line
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public static SystemLog findByLineGreater_Last(int line,
		OrderByComparator<SystemLog> orderByComparator)
		throws backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence().findByLineGreater_Last(line, orderByComparator);
	}

	/**
	* Returns the last system log in the ordered set where line &gt; &#63;.
	*
	* @param line the line
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public static SystemLog fetchByLineGreater_Last(int line,
		OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence().fetchByLineGreater_Last(line, orderByComparator);
	}

	/**
	* Returns the system logs before and after the current system log in the ordered set where line &gt; &#63;.
	*
	* @param logId the primary key of the current system log
	* @param line the line
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next system log
	* @throws NoSuchSystemLogException if a system log with the primary key could not be found
	*/
	public static SystemLog[] findByLineGreater_PrevAndNext(long logId,
		int line, OrderByComparator<SystemLog> orderByComparator)
		throws backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence()
				   .findByLineGreater_PrevAndNext(logId, line, orderByComparator);
	}

	/**
	* Removes all the system logs where line &gt; &#63; from the database.
	*
	* @param line the line
	*/
	public static void removeByLineGreater(int line) {
		getPersistence().removeByLineGreater(line);
	}

	/**
	* Returns the number of system logs where line &gt; &#63;.
	*
	* @param line the line
	* @return the number of matching system logs
	*/
	public static int countByLineGreater(int line) {
		return getPersistence().countByLineGreater(line);
	}

	/**
	* Returns all the system logs where line &lt; &#63;.
	*
	* @param line the line
	* @return the matching system logs
	*/
	public static List<SystemLog> findByLineLesser(int line) {
		return getPersistence().findByLineLesser(line);
	}

	/**
	* Returns a range of all the system logs where line &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param line the line
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @return the range of matching system logs
	*/
	public static List<SystemLog> findByLineLesser(int line, int start, int end) {
		return getPersistence().findByLineLesser(line, start, end);
	}

	/**
	* Returns an ordered range of all the system logs where line &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param line the line
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching system logs
	*/
	public static List<SystemLog> findByLineLesser(int line, int start,
		int end, OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence()
				   .findByLineLesser(line, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the system logs where line &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param line the line
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching system logs
	*/
	public static List<SystemLog> findByLineLesser(int line, int start,
		int end, OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByLineLesser(line, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first system log in the ordered set where line &lt; &#63;.
	*
	* @param line the line
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public static SystemLog findByLineLesser_First(int line,
		OrderByComparator<SystemLog> orderByComparator)
		throws backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence().findByLineLesser_First(line, orderByComparator);
	}

	/**
	* Returns the first system log in the ordered set where line &lt; &#63;.
	*
	* @param line the line
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public static SystemLog fetchByLineLesser_First(int line,
		OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence().fetchByLineLesser_First(line, orderByComparator);
	}

	/**
	* Returns the last system log in the ordered set where line &lt; &#63;.
	*
	* @param line the line
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public static SystemLog findByLineLesser_Last(int line,
		OrderByComparator<SystemLog> orderByComparator)
		throws backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence().findByLineLesser_Last(line, orderByComparator);
	}

	/**
	* Returns the last system log in the ordered set where line &lt; &#63;.
	*
	* @param line the line
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public static SystemLog fetchByLineLesser_Last(int line,
		OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence().fetchByLineLesser_Last(line, orderByComparator);
	}

	/**
	* Returns the system logs before and after the current system log in the ordered set where line &lt; &#63;.
	*
	* @param logId the primary key of the current system log
	* @param line the line
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next system log
	* @throws NoSuchSystemLogException if a system log with the primary key could not be found
	*/
	public static SystemLog[] findByLineLesser_PrevAndNext(long logId,
		int line, OrderByComparator<SystemLog> orderByComparator)
		throws backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence()
				   .findByLineLesser_PrevAndNext(logId, line, orderByComparator);
	}

	/**
	* Removes all the system logs where line &lt; &#63; from the database.
	*
	* @param line the line
	*/
	public static void removeByLineLesser(int line) {
		getPersistence().removeByLineLesser(line);
	}

	/**
	* Returns the number of system logs where line &lt; &#63;.
	*
	* @param line the line
	* @return the number of matching system logs
	*/
	public static int countByLineLesser(int line) {
		return getPersistence().countByLineLesser(line);
	}

	/**
	* Returns all the system logs where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching system logs
	*/
	public static List<SystemLog> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
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
	public static List<SystemLog> findByGroupId(long groupId, int start, int end) {
		return getPersistence().findByGroupId(groupId, start, end);
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
	public static List<SystemLog> findByGroupId(long groupId, int start,
		int end, OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
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
	public static List<SystemLog> findByGroupId(long groupId, int start,
		int end, OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first system log in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public static SystemLog findByGroupId_First(long groupId,
		OrderByComparator<SystemLog> orderByComparator)
		throws backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first system log in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public static SystemLog fetchByGroupId_First(long groupId,
		OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last system log in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public static SystemLog findByGroupId_Last(long groupId,
		OrderByComparator<SystemLog> orderByComparator)
		throws backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last system log in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public static SystemLog fetchByGroupId_Last(long groupId,
		OrderByComparator<SystemLog> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
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
	public static SystemLog[] findByGroupId_PrevAndNext(long logId,
		long groupId, OrderByComparator<SystemLog> orderByComparator)
		throws backend.systemlogmgt.exception.NoSuchSystemLogException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(logId, groupId, orderByComparator);
	}

	/**
	* Removes all the system logs where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of system logs where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching system logs
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
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
		throws backend.systemlogmgt.exception.NoSuchSystemLogException {
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
		throws backend.systemlogmgt.exception.NoSuchSystemLogException {
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