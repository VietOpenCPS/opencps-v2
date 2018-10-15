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

package org.opencps.synchronization.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.synchronization.model.SyncQueue;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the sync queue service. This utility wraps {@link org.opencps.synchronization.service.persistence.impl.SyncQueuePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author trungdk
 * @see SyncQueuePersistence
 * @see org.opencps.synchronization.service.persistence.impl.SyncQueuePersistenceImpl
 * @generated
 */
@ProviderType
public class SyncQueueUtil {
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
	public static void clearCache(SyncQueue syncQueue) {
		getPersistence().clearCache(syncQueue);
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
	public static List<SyncQueue> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SyncQueue> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SyncQueue> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SyncQueue> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static SyncQueue update(SyncQueue syncQueue) {
		return getPersistence().update(syncQueue);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static SyncQueue update(SyncQueue syncQueue,
		ServiceContext serviceContext) {
		return getPersistence().update(syncQueue, serviceContext);
	}

	/**
	* Returns all the sync queues where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching sync queues
	*/
	public static List<SyncQueue> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the sync queues where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of sync queues
	* @param end the upper bound of the range of sync queues (not inclusive)
	* @return the range of matching sync queues
	*/
	public static List<SyncQueue> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the sync queues where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of sync queues
	* @param end the upper bound of the range of sync queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sync queues
	*/
	public static List<SyncQueue> findByUuid(String uuid, int start, int end,
		OrderByComparator<SyncQueue> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the sync queues where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of sync queues
	* @param end the upper bound of the range of sync queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sync queues
	*/
	public static List<SyncQueue> findByUuid(String uuid, int start, int end,
		OrderByComparator<SyncQueue> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first sync queue in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync queue
	* @throws NoSuchSyncQueueException if a matching sync queue could not be found
	*/
	public static SyncQueue findByUuid_First(String uuid,
		OrderByComparator<SyncQueue> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchSyncQueueException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first sync queue in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync queue, or <code>null</code> if a matching sync queue could not be found
	*/
	public static SyncQueue fetchByUuid_First(String uuid,
		OrderByComparator<SyncQueue> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last sync queue in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync queue
	* @throws NoSuchSyncQueueException if a matching sync queue could not be found
	*/
	public static SyncQueue findByUuid_Last(String uuid,
		OrderByComparator<SyncQueue> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchSyncQueueException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last sync queue in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync queue, or <code>null</code> if a matching sync queue could not be found
	*/
	public static SyncQueue fetchByUuid_Last(String uuid,
		OrderByComparator<SyncQueue> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the sync queues before and after the current sync queue in the ordered set where uuid = &#63;.
	*
	* @param syncQueueId the primary key of the current sync queue
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync queue
	* @throws NoSuchSyncQueueException if a sync queue with the primary key could not be found
	*/
	public static SyncQueue[] findByUuid_PrevAndNext(long syncQueueId,
		String uuid, OrderByComparator<SyncQueue> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchSyncQueueException {
		return getPersistence()
				   .findByUuid_PrevAndNext(syncQueueId, uuid, orderByComparator);
	}

	/**
	* Removes all the sync queues where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of sync queues where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching sync queues
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the sync queue where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchSyncQueueException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching sync queue
	* @throws NoSuchSyncQueueException if a matching sync queue could not be found
	*/
	public static SyncQueue findByUUID_G(String uuid, long groupId)
		throws org.opencps.synchronization.exception.NoSuchSyncQueueException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the sync queue where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching sync queue, or <code>null</code> if a matching sync queue could not be found
	*/
	public static SyncQueue fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the sync queue where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching sync queue, or <code>null</code> if a matching sync queue could not be found
	*/
	public static SyncQueue fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the sync queue where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the sync queue that was removed
	*/
	public static SyncQueue removeByUUID_G(String uuid, long groupId)
		throws org.opencps.synchronization.exception.NoSuchSyncQueueException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of sync queues where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching sync queues
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the sync queues where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching sync queues
	*/
	public static List<SyncQueue> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the sync queues where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of sync queues
	* @param end the upper bound of the range of sync queues (not inclusive)
	* @return the range of matching sync queues
	*/
	public static List<SyncQueue> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the sync queues where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of sync queues
	* @param end the upper bound of the range of sync queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sync queues
	*/
	public static List<SyncQueue> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<SyncQueue> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the sync queues where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of sync queues
	* @param end the upper bound of the range of sync queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sync queues
	*/
	public static List<SyncQueue> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<SyncQueue> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first sync queue in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync queue
	* @throws NoSuchSyncQueueException if a matching sync queue could not be found
	*/
	public static SyncQueue findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<SyncQueue> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchSyncQueueException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first sync queue in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync queue, or <code>null</code> if a matching sync queue could not be found
	*/
	public static SyncQueue fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<SyncQueue> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last sync queue in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync queue
	* @throws NoSuchSyncQueueException if a matching sync queue could not be found
	*/
	public static SyncQueue findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<SyncQueue> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchSyncQueueException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last sync queue in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync queue, or <code>null</code> if a matching sync queue could not be found
	*/
	public static SyncQueue fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<SyncQueue> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the sync queues before and after the current sync queue in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param syncQueueId the primary key of the current sync queue
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync queue
	* @throws NoSuchSyncQueueException if a sync queue with the primary key could not be found
	*/
	public static SyncQueue[] findByUuid_C_PrevAndNext(long syncQueueId,
		String uuid, long companyId,
		OrderByComparator<SyncQueue> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchSyncQueueException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(syncQueueId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the sync queues where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of sync queues where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching sync queues
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the sync queues where groupId = &#63; and className = &#63; and method = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param method the method
	* @return the matching sync queues
	*/
	public static List<SyncQueue> findByF_className_Method(long groupId,
		String className, String method) {
		return getPersistence()
				   .findByF_className_Method(groupId, className, method);
	}

	/**
	* Returns a range of all the sync queues where groupId = &#63; and className = &#63; and method = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param className the class name
	* @param method the method
	* @param start the lower bound of the range of sync queues
	* @param end the upper bound of the range of sync queues (not inclusive)
	* @return the range of matching sync queues
	*/
	public static List<SyncQueue> findByF_className_Method(long groupId,
		String className, String method, int start, int end) {
		return getPersistence()
				   .findByF_className_Method(groupId, className, method, start,
			end);
	}

	/**
	* Returns an ordered range of all the sync queues where groupId = &#63; and className = &#63; and method = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param className the class name
	* @param method the method
	* @param start the lower bound of the range of sync queues
	* @param end the upper bound of the range of sync queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sync queues
	*/
	public static List<SyncQueue> findByF_className_Method(long groupId,
		String className, String method, int start, int end,
		OrderByComparator<SyncQueue> orderByComparator) {
		return getPersistence()
				   .findByF_className_Method(groupId, className, method, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the sync queues where groupId = &#63; and className = &#63; and method = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param className the class name
	* @param method the method
	* @param start the lower bound of the range of sync queues
	* @param end the upper bound of the range of sync queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sync queues
	*/
	public static List<SyncQueue> findByF_className_Method(long groupId,
		String className, String method, int start, int end,
		OrderByComparator<SyncQueue> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_className_Method(groupId, className, method, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first sync queue in the ordered set where groupId = &#63; and className = &#63; and method = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param method the method
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync queue
	* @throws NoSuchSyncQueueException if a matching sync queue could not be found
	*/
	public static SyncQueue findByF_className_Method_First(long groupId,
		String className, String method,
		OrderByComparator<SyncQueue> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchSyncQueueException {
		return getPersistence()
				   .findByF_className_Method_First(groupId, className, method,
			orderByComparator);
	}

	/**
	* Returns the first sync queue in the ordered set where groupId = &#63; and className = &#63; and method = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param method the method
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync queue, or <code>null</code> if a matching sync queue could not be found
	*/
	public static SyncQueue fetchByF_className_Method_First(long groupId,
		String className, String method,
		OrderByComparator<SyncQueue> orderByComparator) {
		return getPersistence()
				   .fetchByF_className_Method_First(groupId, className, method,
			orderByComparator);
	}

	/**
	* Returns the last sync queue in the ordered set where groupId = &#63; and className = &#63; and method = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param method the method
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync queue
	* @throws NoSuchSyncQueueException if a matching sync queue could not be found
	*/
	public static SyncQueue findByF_className_Method_Last(long groupId,
		String className, String method,
		OrderByComparator<SyncQueue> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchSyncQueueException {
		return getPersistence()
				   .findByF_className_Method_Last(groupId, className, method,
			orderByComparator);
	}

	/**
	* Returns the last sync queue in the ordered set where groupId = &#63; and className = &#63; and method = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param method the method
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync queue, or <code>null</code> if a matching sync queue could not be found
	*/
	public static SyncQueue fetchByF_className_Method_Last(long groupId,
		String className, String method,
		OrderByComparator<SyncQueue> orderByComparator) {
		return getPersistence()
				   .fetchByF_className_Method_Last(groupId, className, method,
			orderByComparator);
	}

	/**
	* Returns the sync queues before and after the current sync queue in the ordered set where groupId = &#63; and className = &#63; and method = &#63;.
	*
	* @param syncQueueId the primary key of the current sync queue
	* @param groupId the group ID
	* @param className the class name
	* @param method the method
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync queue
	* @throws NoSuchSyncQueueException if a sync queue with the primary key could not be found
	*/
	public static SyncQueue[] findByF_className_Method_PrevAndNext(
		long syncQueueId, long groupId, String className, String method,
		OrderByComparator<SyncQueue> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchSyncQueueException {
		return getPersistence()
				   .findByF_className_Method_PrevAndNext(syncQueueId, groupId,
			className, method, orderByComparator);
	}

	/**
	* Removes all the sync queues where groupId = &#63; and className = &#63; and method = &#63; from the database.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param method the method
	*/
	public static void removeByF_className_Method(long groupId,
		String className, String method) {
		getPersistence().removeByF_className_Method(groupId, className, method);
	}

	/**
	* Returns the number of sync queues where groupId = &#63; and className = &#63; and method = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param method the method
	* @return the number of matching sync queues
	*/
	public static int countByF_className_Method(long groupId, String className,
		String method) {
		return getPersistence()
				   .countByF_className_Method(groupId, className, method);
	}

	/**
	* Returns all the sync queues where groupId = &#63; and serverNo = &#63;.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @return the matching sync queues
	*/
	public static List<SyncQueue> findByF_groupId_serverNo(long groupId,
		String serverNo) {
		return getPersistence().findByF_groupId_serverNo(groupId, serverNo);
	}

	/**
	* Returns a range of all the sync queues where groupId = &#63; and serverNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param start the lower bound of the range of sync queues
	* @param end the upper bound of the range of sync queues (not inclusive)
	* @return the range of matching sync queues
	*/
	public static List<SyncQueue> findByF_groupId_serverNo(long groupId,
		String serverNo, int start, int end) {
		return getPersistence()
				   .findByF_groupId_serverNo(groupId, serverNo, start, end);
	}

	/**
	* Returns an ordered range of all the sync queues where groupId = &#63; and serverNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param start the lower bound of the range of sync queues
	* @param end the upper bound of the range of sync queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sync queues
	*/
	public static List<SyncQueue> findByF_groupId_serverNo(long groupId,
		String serverNo, int start, int end,
		OrderByComparator<SyncQueue> orderByComparator) {
		return getPersistence()
				   .findByF_groupId_serverNo(groupId, serverNo, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the sync queues where groupId = &#63; and serverNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param start the lower bound of the range of sync queues
	* @param end the upper bound of the range of sync queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sync queues
	*/
	public static List<SyncQueue> findByF_groupId_serverNo(long groupId,
		String serverNo, int start, int end,
		OrderByComparator<SyncQueue> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_groupId_serverNo(groupId, serverNo, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first sync queue in the ordered set where groupId = &#63; and serverNo = &#63;.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync queue
	* @throws NoSuchSyncQueueException if a matching sync queue could not be found
	*/
	public static SyncQueue findByF_groupId_serverNo_First(long groupId,
		String serverNo, OrderByComparator<SyncQueue> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchSyncQueueException {
		return getPersistence()
				   .findByF_groupId_serverNo_First(groupId, serverNo,
			orderByComparator);
	}

	/**
	* Returns the first sync queue in the ordered set where groupId = &#63; and serverNo = &#63;.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync queue, or <code>null</code> if a matching sync queue could not be found
	*/
	public static SyncQueue fetchByF_groupId_serverNo_First(long groupId,
		String serverNo, OrderByComparator<SyncQueue> orderByComparator) {
		return getPersistence()
				   .fetchByF_groupId_serverNo_First(groupId, serverNo,
			orderByComparator);
	}

	/**
	* Returns the last sync queue in the ordered set where groupId = &#63; and serverNo = &#63;.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync queue
	* @throws NoSuchSyncQueueException if a matching sync queue could not be found
	*/
	public static SyncQueue findByF_groupId_serverNo_Last(long groupId,
		String serverNo, OrderByComparator<SyncQueue> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchSyncQueueException {
		return getPersistence()
				   .findByF_groupId_serverNo_Last(groupId, serverNo,
			orderByComparator);
	}

	/**
	* Returns the last sync queue in the ordered set where groupId = &#63; and serverNo = &#63;.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync queue, or <code>null</code> if a matching sync queue could not be found
	*/
	public static SyncQueue fetchByF_groupId_serverNo_Last(long groupId,
		String serverNo, OrderByComparator<SyncQueue> orderByComparator) {
		return getPersistence()
				   .fetchByF_groupId_serverNo_Last(groupId, serverNo,
			orderByComparator);
	}

	/**
	* Returns the sync queues before and after the current sync queue in the ordered set where groupId = &#63; and serverNo = &#63;.
	*
	* @param syncQueueId the primary key of the current sync queue
	* @param groupId the group ID
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync queue
	* @throws NoSuchSyncQueueException if a sync queue with the primary key could not be found
	*/
	public static SyncQueue[] findByF_groupId_serverNo_PrevAndNext(
		long syncQueueId, long groupId, String serverNo,
		OrderByComparator<SyncQueue> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchSyncQueueException {
		return getPersistence()
				   .findByF_groupId_serverNo_PrevAndNext(syncQueueId, groupId,
			serverNo, orderByComparator);
	}

	/**
	* Removes all the sync queues where groupId = &#63; and serverNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	*/
	public static void removeByF_groupId_serverNo(long groupId, String serverNo) {
		getPersistence().removeByF_groupId_serverNo(groupId, serverNo);
	}

	/**
	* Returns the number of sync queues where groupId = &#63; and serverNo = &#63;.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @return the number of matching sync queues
	*/
	public static int countByF_groupId_serverNo(long groupId, String serverNo) {
		return getPersistence().countByF_groupId_serverNo(groupId, serverNo);
	}

	/**
	* Caches the sync queue in the entity cache if it is enabled.
	*
	* @param syncQueue the sync queue
	*/
	public static void cacheResult(SyncQueue syncQueue) {
		getPersistence().cacheResult(syncQueue);
	}

	/**
	* Caches the sync queues in the entity cache if it is enabled.
	*
	* @param syncQueues the sync queues
	*/
	public static void cacheResult(List<SyncQueue> syncQueues) {
		getPersistence().cacheResult(syncQueues);
	}

	/**
	* Creates a new sync queue with the primary key. Does not add the sync queue to the database.
	*
	* @param syncQueueId the primary key for the new sync queue
	* @return the new sync queue
	*/
	public static SyncQueue create(long syncQueueId) {
		return getPersistence().create(syncQueueId);
	}

	/**
	* Removes the sync queue with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param syncQueueId the primary key of the sync queue
	* @return the sync queue that was removed
	* @throws NoSuchSyncQueueException if a sync queue with the primary key could not be found
	*/
	public static SyncQueue remove(long syncQueueId)
		throws org.opencps.synchronization.exception.NoSuchSyncQueueException {
		return getPersistence().remove(syncQueueId);
	}

	public static SyncQueue updateImpl(SyncQueue syncQueue) {
		return getPersistence().updateImpl(syncQueue);
	}

	/**
	* Returns the sync queue with the primary key or throws a {@link NoSuchSyncQueueException} if it could not be found.
	*
	* @param syncQueueId the primary key of the sync queue
	* @return the sync queue
	* @throws NoSuchSyncQueueException if a sync queue with the primary key could not be found
	*/
	public static SyncQueue findByPrimaryKey(long syncQueueId)
		throws org.opencps.synchronization.exception.NoSuchSyncQueueException {
		return getPersistence().findByPrimaryKey(syncQueueId);
	}

	/**
	* Returns the sync queue with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param syncQueueId the primary key of the sync queue
	* @return the sync queue, or <code>null</code> if a sync queue with the primary key could not be found
	*/
	public static SyncQueue fetchByPrimaryKey(long syncQueueId) {
		return getPersistence().fetchByPrimaryKey(syncQueueId);
	}

	public static java.util.Map<java.io.Serializable, SyncQueue> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the sync queues.
	*
	* @return the sync queues
	*/
	public static List<SyncQueue> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the sync queues.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sync queues
	* @param end the upper bound of the range of sync queues (not inclusive)
	* @return the range of sync queues
	*/
	public static List<SyncQueue> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the sync queues.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sync queues
	* @param end the upper bound of the range of sync queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of sync queues
	*/
	public static List<SyncQueue> findAll(int start, int end,
		OrderByComparator<SyncQueue> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the sync queues.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sync queues
	* @param end the upper bound of the range of sync queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of sync queues
	*/
	public static List<SyncQueue> findAll(int start, int end,
		OrderByComparator<SyncQueue> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the sync queues from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of sync queues.
	*
	* @return the number of sync queues
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static SyncQueuePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<SyncQueuePersistence, SyncQueuePersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(SyncQueuePersistence.class);

		ServiceTracker<SyncQueuePersistence, SyncQueuePersistence> serviceTracker =
			new ServiceTracker<SyncQueuePersistence, SyncQueuePersistence>(bundle.getBundleContext(),
				SyncQueuePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}