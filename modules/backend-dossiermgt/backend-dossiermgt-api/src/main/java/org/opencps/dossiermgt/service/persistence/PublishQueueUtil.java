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

import org.opencps.dossiermgt.model.PublishQueue;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the publish queue service. This utility wraps {@link org.opencps.dossiermgt.service.persistence.impl.PublishQueuePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see PublishQueuePersistence
 * @see org.opencps.dossiermgt.service.persistence.impl.PublishQueuePersistenceImpl
 * @generated
 */
@ProviderType
public class PublishQueueUtil {
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
	public static void clearCache(PublishQueue publishQueue) {
		getPersistence().clearCache(publishQueue);
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
	public static List<PublishQueue> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<PublishQueue> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<PublishQueue> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<PublishQueue> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static PublishQueue update(PublishQueue publishQueue) {
		return getPersistence().update(publishQueue);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static PublishQueue update(PublishQueue publishQueue,
		ServiceContext serviceContext) {
		return getPersistence().update(publishQueue, serviceContext);
	}

	/**
	* Returns all the publish queues where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching publish queues
	*/
	public static List<PublishQueue> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the publish queues where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @return the range of matching publish queues
	*/
	public static List<PublishQueue> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the publish queues where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching publish queues
	*/
	public static List<PublishQueue> findByUuid(String uuid, int start,
		int end, OrderByComparator<PublishQueue> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the publish queues where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching publish queues
	*/
	public static List<PublishQueue> findByUuid(String uuid, int start,
		int end, OrderByComparator<PublishQueue> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first publish queue in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching publish queue
	* @throws NoSuchPublishQueueException if a matching publish queue could not be found
	*/
	public static PublishQueue findByUuid_First(String uuid,
		OrderByComparator<PublishQueue> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchPublishQueueException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first publish queue in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching publish queue, or <code>null</code> if a matching publish queue could not be found
	*/
	public static PublishQueue fetchByUuid_First(String uuid,
		OrderByComparator<PublishQueue> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last publish queue in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching publish queue
	* @throws NoSuchPublishQueueException if a matching publish queue could not be found
	*/
	public static PublishQueue findByUuid_Last(String uuid,
		OrderByComparator<PublishQueue> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchPublishQueueException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last publish queue in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching publish queue, or <code>null</code> if a matching publish queue could not be found
	*/
	public static PublishQueue fetchByUuid_Last(String uuid,
		OrderByComparator<PublishQueue> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the publish queues before and after the current publish queue in the ordered set where uuid = &#63;.
	*
	* @param publishQueueId the primary key of the current publish queue
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next publish queue
	* @throws NoSuchPublishQueueException if a publish queue with the primary key could not be found
	*/
	public static PublishQueue[] findByUuid_PrevAndNext(long publishQueueId,
		String uuid, OrderByComparator<PublishQueue> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchPublishQueueException {
		return getPersistence()
				   .findByUuid_PrevAndNext(publishQueueId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the publish queues where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of publish queues where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching publish queues
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the publish queue where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPublishQueueException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching publish queue
	* @throws NoSuchPublishQueueException if a matching publish queue could not be found
	*/
	public static PublishQueue findByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchPublishQueueException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the publish queue where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching publish queue, or <code>null</code> if a matching publish queue could not be found
	*/
	public static PublishQueue fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the publish queue where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching publish queue, or <code>null</code> if a matching publish queue could not be found
	*/
	public static PublishQueue fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the publish queue where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the publish queue that was removed
	*/
	public static PublishQueue removeByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchPublishQueueException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of publish queues where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching publish queues
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the publish queues where status = &#63;.
	*
	* @param status the status
	* @return the matching publish queues
	*/
	public static List<PublishQueue> findByST(int status) {
		return getPersistence().findByST(status);
	}

	/**
	* Returns a range of all the publish queues where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @return the range of matching publish queues
	*/
	public static List<PublishQueue> findByST(int status, int start, int end) {
		return getPersistence().findByST(status, start, end);
	}

	/**
	* Returns an ordered range of all the publish queues where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching publish queues
	*/
	public static List<PublishQueue> findByST(int status, int start, int end,
		OrderByComparator<PublishQueue> orderByComparator) {
		return getPersistence().findByST(status, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the publish queues where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching publish queues
	*/
	public static List<PublishQueue> findByST(int status, int start, int end,
		OrderByComparator<PublishQueue> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByST(status, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first publish queue in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching publish queue
	* @throws NoSuchPublishQueueException if a matching publish queue could not be found
	*/
	public static PublishQueue findByST_First(int status,
		OrderByComparator<PublishQueue> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchPublishQueueException {
		return getPersistence().findByST_First(status, orderByComparator);
	}

	/**
	* Returns the first publish queue in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching publish queue, or <code>null</code> if a matching publish queue could not be found
	*/
	public static PublishQueue fetchByST_First(int status,
		OrderByComparator<PublishQueue> orderByComparator) {
		return getPersistence().fetchByST_First(status, orderByComparator);
	}

	/**
	* Returns the last publish queue in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching publish queue
	* @throws NoSuchPublishQueueException if a matching publish queue could not be found
	*/
	public static PublishQueue findByST_Last(int status,
		OrderByComparator<PublishQueue> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchPublishQueueException {
		return getPersistence().findByST_Last(status, orderByComparator);
	}

	/**
	* Returns the last publish queue in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching publish queue, or <code>null</code> if a matching publish queue could not be found
	*/
	public static PublishQueue fetchByST_Last(int status,
		OrderByComparator<PublishQueue> orderByComparator) {
		return getPersistence().fetchByST_Last(status, orderByComparator);
	}

	/**
	* Returns the publish queues before and after the current publish queue in the ordered set where status = &#63;.
	*
	* @param publishQueueId the primary key of the current publish queue
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next publish queue
	* @throws NoSuchPublishQueueException if a publish queue with the primary key could not be found
	*/
	public static PublishQueue[] findByST_PrevAndNext(long publishQueueId,
		int status, OrderByComparator<PublishQueue> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchPublishQueueException {
		return getPersistence()
				   .findByST_PrevAndNext(publishQueueId, status,
			orderByComparator);
	}

	/**
	* Removes all the publish queues where status = &#63; from the database.
	*
	* @param status the status
	*/
	public static void removeByST(int status) {
		getPersistence().removeByST(status);
	}

	/**
	* Returns the number of publish queues where status = &#63;.
	*
	* @param status the status
	* @return the number of matching publish queues
	*/
	public static int countByST(int status) {
		return getPersistence().countByST(status);
	}

	/**
	* Returns the publish queue where groupId = &#63; and dossierId = &#63; and serverNo = &#63; or throws a {@link NoSuchPublishQueueException} if it could not be found.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @return the matching publish queue
	* @throws NoSuchPublishQueueException if a matching publish queue could not be found
	*/
	public static PublishQueue findByG_DID_SN(long groupId, long dossierId,
		String serverNo)
		throws org.opencps.dossiermgt.exception.NoSuchPublishQueueException {
		return getPersistence().findByG_DID_SN(groupId, dossierId, serverNo);
	}

	/**
	* Returns the publish queue where groupId = &#63; and dossierId = &#63; and serverNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @return the matching publish queue, or <code>null</code> if a matching publish queue could not be found
	*/
	public static PublishQueue fetchByG_DID_SN(long groupId, long dossierId,
		String serverNo) {
		return getPersistence().fetchByG_DID_SN(groupId, dossierId, serverNo);
	}

	/**
	* Returns the publish queue where groupId = &#63; and dossierId = &#63; and serverNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching publish queue, or <code>null</code> if a matching publish queue could not be found
	*/
	public static PublishQueue fetchByG_DID_SN(long groupId, long dossierId,
		String serverNo, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByG_DID_SN(groupId, dossierId, serverNo,
			retrieveFromCache);
	}

	/**
	* Removes the publish queue where groupId = &#63; and dossierId = &#63; and serverNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @return the publish queue that was removed
	*/
	public static PublishQueue removeByG_DID_SN(long groupId, long dossierId,
		String serverNo)
		throws org.opencps.dossiermgt.exception.NoSuchPublishQueueException {
		return getPersistence().removeByG_DID_SN(groupId, dossierId, serverNo);
	}

	/**
	* Returns the number of publish queues where groupId = &#63; and dossierId = &#63; and serverNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @return the number of matching publish queues
	*/
	public static int countByG_DID_SN(long groupId, long dossierId,
		String serverNo) {
		return getPersistence().countByG_DID_SN(groupId, dossierId, serverNo);
	}

	/**
	* Caches the publish queue in the entity cache if it is enabled.
	*
	* @param publishQueue the publish queue
	*/
	public static void cacheResult(PublishQueue publishQueue) {
		getPersistence().cacheResult(publishQueue);
	}

	/**
	* Caches the publish queues in the entity cache if it is enabled.
	*
	* @param publishQueues the publish queues
	*/
	public static void cacheResult(List<PublishQueue> publishQueues) {
		getPersistence().cacheResult(publishQueues);
	}

	/**
	* Creates a new publish queue with the primary key. Does not add the publish queue to the database.
	*
	* @param publishQueueId the primary key for the new publish queue
	* @return the new publish queue
	*/
	public static PublishQueue create(long publishQueueId) {
		return getPersistence().create(publishQueueId);
	}

	/**
	* Removes the publish queue with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param publishQueueId the primary key of the publish queue
	* @return the publish queue that was removed
	* @throws NoSuchPublishQueueException if a publish queue with the primary key could not be found
	*/
	public static PublishQueue remove(long publishQueueId)
		throws org.opencps.dossiermgt.exception.NoSuchPublishQueueException {
		return getPersistence().remove(publishQueueId);
	}

	public static PublishQueue updateImpl(PublishQueue publishQueue) {
		return getPersistence().updateImpl(publishQueue);
	}

	/**
	* Returns the publish queue with the primary key or throws a {@link NoSuchPublishQueueException} if it could not be found.
	*
	* @param publishQueueId the primary key of the publish queue
	* @return the publish queue
	* @throws NoSuchPublishQueueException if a publish queue with the primary key could not be found
	*/
	public static PublishQueue findByPrimaryKey(long publishQueueId)
		throws org.opencps.dossiermgt.exception.NoSuchPublishQueueException {
		return getPersistence().findByPrimaryKey(publishQueueId);
	}

	/**
	* Returns the publish queue with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param publishQueueId the primary key of the publish queue
	* @return the publish queue, or <code>null</code> if a publish queue with the primary key could not be found
	*/
	public static PublishQueue fetchByPrimaryKey(long publishQueueId) {
		return getPersistence().fetchByPrimaryKey(publishQueueId);
	}

	public static java.util.Map<java.io.Serializable, PublishQueue> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the publish queues.
	*
	* @return the publish queues
	*/
	public static List<PublishQueue> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the publish queues.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @return the range of publish queues
	*/
	public static List<PublishQueue> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the publish queues.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of publish queues
	*/
	public static List<PublishQueue> findAll(int start, int end,
		OrderByComparator<PublishQueue> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the publish queues.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of publish queues
	*/
	public static List<PublishQueue> findAll(int start, int end,
		OrderByComparator<PublishQueue> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the publish queues from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of publish queues.
	*
	* @return the number of publish queues
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static PublishQueuePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<PublishQueuePersistence, PublishQueuePersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(PublishQueuePersistence.class);

		ServiceTracker<PublishQueuePersistence, PublishQueuePersistence> serviceTracker =
			new ServiceTracker<PublishQueuePersistence, PublishQueuePersistence>(bundle.getBundleContext(),
				PublishQueuePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}