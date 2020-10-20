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

package org.opencps.communication.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.communication.model.NotificationQueue;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.Date;
import java.util.List;

/**
 * The persistence utility for the notification queue service. This utility wraps {@link org.opencps.communication.service.persistence.impl.NotificationQueuePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavd
 * @see NotificationQueuePersistence
 * @see org.opencps.communication.service.persistence.impl.NotificationQueuePersistenceImpl
 * @generated
 */
@ProviderType
public class NotificationQueueUtil {
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
	public static void clearCache(NotificationQueue notificationQueue) {
		getPersistence().clearCache(notificationQueue);
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
	public static List<NotificationQueue> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<NotificationQueue> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<NotificationQueue> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<NotificationQueue> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static NotificationQueue update(NotificationQueue notificationQueue) {
		return getPersistence().update(notificationQueue);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static NotificationQueue update(
		NotificationQueue notificationQueue, ServiceContext serviceContext) {
		return getPersistence().update(notificationQueue, serviceContext);
	}

	/**
	* Returns all the notification queues where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching notification queues
	*/
	public static List<NotificationQueue> findByG(long groupId) {
		return getPersistence().findByG(groupId);
	}

	/**
	* Returns a range of all the notification queues where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of notification queues
	* @param end the upper bound of the range of notification queues (not inclusive)
	* @return the range of matching notification queues
	*/
	public static List<NotificationQueue> findByG(long groupId, int start,
		int end) {
		return getPersistence().findByG(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the notification queues where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of notification queues
	* @param end the upper bound of the range of notification queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching notification queues
	*/
	public static List<NotificationQueue> findByG(long groupId, int start,
		int end, OrderByComparator<NotificationQueue> orderByComparator) {
		return getPersistence().findByG(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the notification queues where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of notification queues
	* @param end the upper bound of the range of notification queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching notification queues
	*/
	public static List<NotificationQueue> findByG(long groupId, int start,
		int end, OrderByComparator<NotificationQueue> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first notification queue in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching notification queue
	* @throws NoSuchNotificationQueueException if a matching notification queue could not be found
	*/
	public static NotificationQueue findByG_First(long groupId,
		OrderByComparator<NotificationQueue> orderByComparator)
		throws org.opencps.communication.exception.NoSuchNotificationQueueException {
		return getPersistence().findByG_First(groupId, orderByComparator);
	}

	/**
	* Returns the first notification queue in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching notification queue, or <code>null</code> if a matching notification queue could not be found
	*/
	public static NotificationQueue fetchByG_First(long groupId,
		OrderByComparator<NotificationQueue> orderByComparator) {
		return getPersistence().fetchByG_First(groupId, orderByComparator);
	}

	/**
	* Returns the last notification queue in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching notification queue
	* @throws NoSuchNotificationQueueException if a matching notification queue could not be found
	*/
	public static NotificationQueue findByG_Last(long groupId,
		OrderByComparator<NotificationQueue> orderByComparator)
		throws org.opencps.communication.exception.NoSuchNotificationQueueException {
		return getPersistence().findByG_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last notification queue in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching notification queue, or <code>null</code> if a matching notification queue could not be found
	*/
	public static NotificationQueue fetchByG_Last(long groupId,
		OrderByComparator<NotificationQueue> orderByComparator) {
		return getPersistence().fetchByG_Last(groupId, orderByComparator);
	}

	/**
	* Returns the notification queues before and after the current notification queue in the ordered set where groupId = &#63;.
	*
	* @param notificationQueueId the primary key of the current notification queue
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next notification queue
	* @throws NoSuchNotificationQueueException if a notification queue with the primary key could not be found
	*/
	public static NotificationQueue[] findByG_PrevAndNext(
		long notificationQueueId, long groupId,
		OrderByComparator<NotificationQueue> orderByComparator)
		throws org.opencps.communication.exception.NoSuchNotificationQueueException {
		return getPersistence()
				   .findByG_PrevAndNext(notificationQueueId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the notification queues where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByG(long groupId) {
		getPersistence().removeByG(groupId);
	}

	/**
	* Returns the number of notification queues where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching notification queues
	*/
	public static int countByG(long groupId) {
		return getPersistence().countByG(groupId);
	}

	/**
	* Returns all the notification queues where expireDate &le; &#63;.
	*
	* @param expireDate the expire date
	* @return the matching notification queues
	*/
	public static List<NotificationQueue> findByF_GreaterThan_ExpireDate(
		Date expireDate) {
		return getPersistence().findByF_GreaterThan_ExpireDate(expireDate);
	}

	/**
	* Returns a range of all the notification queues where expireDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param expireDate the expire date
	* @param start the lower bound of the range of notification queues
	* @param end the upper bound of the range of notification queues (not inclusive)
	* @return the range of matching notification queues
	*/
	public static List<NotificationQueue> findByF_GreaterThan_ExpireDate(
		Date expireDate, int start, int end) {
		return getPersistence()
				   .findByF_GreaterThan_ExpireDate(expireDate, start, end);
	}

	/**
	* Returns an ordered range of all the notification queues where expireDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param expireDate the expire date
	* @param start the lower bound of the range of notification queues
	* @param end the upper bound of the range of notification queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching notification queues
	*/
	public static List<NotificationQueue> findByF_GreaterThan_ExpireDate(
		Date expireDate, int start, int end,
		OrderByComparator<NotificationQueue> orderByComparator) {
		return getPersistence()
				   .findByF_GreaterThan_ExpireDate(expireDate, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the notification queues where expireDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param expireDate the expire date
	* @param start the lower bound of the range of notification queues
	* @param end the upper bound of the range of notification queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching notification queues
	*/
	public static List<NotificationQueue> findByF_GreaterThan_ExpireDate(
		Date expireDate, int start, int end,
		OrderByComparator<NotificationQueue> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_GreaterThan_ExpireDate(expireDate, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first notification queue in the ordered set where expireDate &le; &#63;.
	*
	* @param expireDate the expire date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching notification queue
	* @throws NoSuchNotificationQueueException if a matching notification queue could not be found
	*/
	public static NotificationQueue findByF_GreaterThan_ExpireDate_First(
		Date expireDate, OrderByComparator<NotificationQueue> orderByComparator)
		throws org.opencps.communication.exception.NoSuchNotificationQueueException {
		return getPersistence()
				   .findByF_GreaterThan_ExpireDate_First(expireDate,
			orderByComparator);
	}

	/**
	* Returns the first notification queue in the ordered set where expireDate &le; &#63;.
	*
	* @param expireDate the expire date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching notification queue, or <code>null</code> if a matching notification queue could not be found
	*/
	public static NotificationQueue fetchByF_GreaterThan_ExpireDate_First(
		Date expireDate, OrderByComparator<NotificationQueue> orderByComparator) {
		return getPersistence()
				   .fetchByF_GreaterThan_ExpireDate_First(expireDate,
			orderByComparator);
	}

	/**
	* Returns the last notification queue in the ordered set where expireDate &le; &#63;.
	*
	* @param expireDate the expire date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching notification queue
	* @throws NoSuchNotificationQueueException if a matching notification queue could not be found
	*/
	public static NotificationQueue findByF_GreaterThan_ExpireDate_Last(
		Date expireDate, OrderByComparator<NotificationQueue> orderByComparator)
		throws org.opencps.communication.exception.NoSuchNotificationQueueException {
		return getPersistence()
				   .findByF_GreaterThan_ExpireDate_Last(expireDate,
			orderByComparator);
	}

	/**
	* Returns the last notification queue in the ordered set where expireDate &le; &#63;.
	*
	* @param expireDate the expire date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching notification queue, or <code>null</code> if a matching notification queue could not be found
	*/
	public static NotificationQueue fetchByF_GreaterThan_ExpireDate_Last(
		Date expireDate, OrderByComparator<NotificationQueue> orderByComparator) {
		return getPersistence()
				   .fetchByF_GreaterThan_ExpireDate_Last(expireDate,
			orderByComparator);
	}

	/**
	* Returns the notification queues before and after the current notification queue in the ordered set where expireDate &le; &#63;.
	*
	* @param notificationQueueId the primary key of the current notification queue
	* @param expireDate the expire date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next notification queue
	* @throws NoSuchNotificationQueueException if a notification queue with the primary key could not be found
	*/
	public static NotificationQueue[] findByF_GreaterThan_ExpireDate_PrevAndNext(
		long notificationQueueId, Date expireDate,
		OrderByComparator<NotificationQueue> orderByComparator)
		throws org.opencps.communication.exception.NoSuchNotificationQueueException {
		return getPersistence()
				   .findByF_GreaterThan_ExpireDate_PrevAndNext(notificationQueueId,
			expireDate, orderByComparator);
	}

	/**
	* Removes all the notification queues where expireDate &le; &#63; from the database.
	*
	* @param expireDate the expire date
	*/
	public static void removeByF_GreaterThan_ExpireDate(Date expireDate) {
		getPersistence().removeByF_GreaterThan_ExpireDate(expireDate);
	}

	/**
	* Returns the number of notification queues where expireDate &le; &#63;.
	*
	* @param expireDate the expire date
	* @return the number of matching notification queues
	*/
	public static int countByF_GreaterThan_ExpireDate(Date expireDate) {
		return getPersistence().countByF_GreaterThan_ExpireDate(expireDate);
	}

	/**
	* Returns all the notification queues where notificationType = &#63; and expireDate &ge; &#63;.
	*
	* @param notificationType the notification type
	* @param expireDate the expire date
	* @return the matching notification queues
	*/
	public static List<NotificationQueue> findByF_notificationType_LessThanExpireDate(
		String notificationType, Date expireDate) {
		return getPersistence()
				   .findByF_notificationType_LessThanExpireDate(notificationType,
			expireDate);
	}

	/**
	* Returns a range of all the notification queues where notificationType = &#63; and expireDate &ge; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param notificationType the notification type
	* @param expireDate the expire date
	* @param start the lower bound of the range of notification queues
	* @param end the upper bound of the range of notification queues (not inclusive)
	* @return the range of matching notification queues
	*/
	public static List<NotificationQueue> findByF_notificationType_LessThanExpireDate(
		String notificationType, Date expireDate, int start, int end) {
		return getPersistence()
				   .findByF_notificationType_LessThanExpireDate(notificationType,
			expireDate, start, end);
	}

	/**
	* Returns an ordered range of all the notification queues where notificationType = &#63; and expireDate &ge; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param notificationType the notification type
	* @param expireDate the expire date
	* @param start the lower bound of the range of notification queues
	* @param end the upper bound of the range of notification queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching notification queues
	*/
	public static List<NotificationQueue> findByF_notificationType_LessThanExpireDate(
		String notificationType, Date expireDate, int start, int end,
		OrderByComparator<NotificationQueue> orderByComparator) {
		return getPersistence()
				   .findByF_notificationType_LessThanExpireDate(notificationType,
			expireDate, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the notification queues where notificationType = &#63; and expireDate &ge; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param notificationType the notification type
	* @param expireDate the expire date
	* @param start the lower bound of the range of notification queues
	* @param end the upper bound of the range of notification queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching notification queues
	*/
	public static List<NotificationQueue> findByF_notificationType_LessThanExpireDate(
		String notificationType, Date expireDate, int start, int end,
		OrderByComparator<NotificationQueue> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_notificationType_LessThanExpireDate(notificationType,
			expireDate, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first notification queue in the ordered set where notificationType = &#63; and expireDate &ge; &#63;.
	*
	* @param notificationType the notification type
	* @param expireDate the expire date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching notification queue
	* @throws NoSuchNotificationQueueException if a matching notification queue could not be found
	*/
	public static NotificationQueue findByF_notificationType_LessThanExpireDate_First(
		String notificationType, Date expireDate,
		OrderByComparator<NotificationQueue> orderByComparator)
		throws org.opencps.communication.exception.NoSuchNotificationQueueException {
		return getPersistence()
				   .findByF_notificationType_LessThanExpireDate_First(notificationType,
			expireDate, orderByComparator);
	}

	/**
	* Returns the first notification queue in the ordered set where notificationType = &#63; and expireDate &ge; &#63;.
	*
	* @param notificationType the notification type
	* @param expireDate the expire date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching notification queue, or <code>null</code> if a matching notification queue could not be found
	*/
	public static NotificationQueue fetchByF_notificationType_LessThanExpireDate_First(
		String notificationType, Date expireDate,
		OrderByComparator<NotificationQueue> orderByComparator) {
		return getPersistence()
				   .fetchByF_notificationType_LessThanExpireDate_First(notificationType,
			expireDate, orderByComparator);
	}

	/**
	* Returns the last notification queue in the ordered set where notificationType = &#63; and expireDate &ge; &#63;.
	*
	* @param notificationType the notification type
	* @param expireDate the expire date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching notification queue
	* @throws NoSuchNotificationQueueException if a matching notification queue could not be found
	*/
	public static NotificationQueue findByF_notificationType_LessThanExpireDate_Last(
		String notificationType, Date expireDate,
		OrderByComparator<NotificationQueue> orderByComparator)
		throws org.opencps.communication.exception.NoSuchNotificationQueueException {
		return getPersistence()
				   .findByF_notificationType_LessThanExpireDate_Last(notificationType,
			expireDate, orderByComparator);
	}

	/**
	* Returns the last notification queue in the ordered set where notificationType = &#63; and expireDate &ge; &#63;.
	*
	* @param notificationType the notification type
	* @param expireDate the expire date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching notification queue, or <code>null</code> if a matching notification queue could not be found
	*/
	public static NotificationQueue fetchByF_notificationType_LessThanExpireDate_Last(
		String notificationType, Date expireDate,
		OrderByComparator<NotificationQueue> orderByComparator) {
		return getPersistence()
				   .fetchByF_notificationType_LessThanExpireDate_Last(notificationType,
			expireDate, orderByComparator);
	}

	/**
	* Returns the notification queues before and after the current notification queue in the ordered set where notificationType = &#63; and expireDate &ge; &#63;.
	*
	* @param notificationQueueId the primary key of the current notification queue
	* @param notificationType the notification type
	* @param expireDate the expire date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next notification queue
	* @throws NoSuchNotificationQueueException if a notification queue with the primary key could not be found
	*/
	public static NotificationQueue[] findByF_notificationType_LessThanExpireDate_PrevAndNext(
		long notificationQueueId, String notificationType, Date expireDate,
		OrderByComparator<NotificationQueue> orderByComparator)
		throws org.opencps.communication.exception.NoSuchNotificationQueueException {
		return getPersistence()
				   .findByF_notificationType_LessThanExpireDate_PrevAndNext(notificationQueueId,
			notificationType, expireDate, orderByComparator);
	}

	/**
	* Removes all the notification queues where notificationType = &#63; and expireDate &ge; &#63; from the database.
	*
	* @param notificationType the notification type
	* @param expireDate the expire date
	*/
	public static void removeByF_notificationType_LessThanExpireDate(
		String notificationType, Date expireDate) {
		getPersistence()
			.removeByF_notificationType_LessThanExpireDate(notificationType,
			expireDate);
	}

	/**
	* Returns the number of notification queues where notificationType = &#63; and expireDate &ge; &#63;.
	*
	* @param notificationType the notification type
	* @param expireDate the expire date
	* @return the number of matching notification queues
	*/
	public static int countByF_notificationType_LessThanExpireDate(
		String notificationType, Date expireDate) {
		return getPersistence()
				   .countByF_notificationType_LessThanExpireDate(notificationType,
			expireDate);
	}

	/**
	* Returns all the notification queues where expireDate &ge; &#63;.
	*
	* @param expireDate the expire date
	* @return the matching notification queues
	*/
	public static List<NotificationQueue> findByF_LessThan_ExpireDate(
		Date expireDate) {
		return getPersistence().findByF_LessThan_ExpireDate(expireDate);
	}

	/**
	* Returns a range of all the notification queues where expireDate &ge; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param expireDate the expire date
	* @param start the lower bound of the range of notification queues
	* @param end the upper bound of the range of notification queues (not inclusive)
	* @return the range of matching notification queues
	*/
	public static List<NotificationQueue> findByF_LessThan_ExpireDate(
		Date expireDate, int start, int end) {
		return getPersistence()
				   .findByF_LessThan_ExpireDate(expireDate, start, end);
	}

	/**
	* Returns an ordered range of all the notification queues where expireDate &ge; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param expireDate the expire date
	* @param start the lower bound of the range of notification queues
	* @param end the upper bound of the range of notification queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching notification queues
	*/
	public static List<NotificationQueue> findByF_LessThan_ExpireDate(
		Date expireDate, int start, int end,
		OrderByComparator<NotificationQueue> orderByComparator) {
		return getPersistence()
				   .findByF_LessThan_ExpireDate(expireDate, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the notification queues where expireDate &ge; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param expireDate the expire date
	* @param start the lower bound of the range of notification queues
	* @param end the upper bound of the range of notification queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching notification queues
	*/
	public static List<NotificationQueue> findByF_LessThan_ExpireDate(
		Date expireDate, int start, int end,
		OrderByComparator<NotificationQueue> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_LessThan_ExpireDate(expireDate, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first notification queue in the ordered set where expireDate &ge; &#63;.
	*
	* @param expireDate the expire date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching notification queue
	* @throws NoSuchNotificationQueueException if a matching notification queue could not be found
	*/
	public static NotificationQueue findByF_LessThan_ExpireDate_First(
		Date expireDate, OrderByComparator<NotificationQueue> orderByComparator)
		throws org.opencps.communication.exception.NoSuchNotificationQueueException {
		return getPersistence()
				   .findByF_LessThan_ExpireDate_First(expireDate,
			orderByComparator);
	}

	/**
	* Returns the first notification queue in the ordered set where expireDate &ge; &#63;.
	*
	* @param expireDate the expire date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching notification queue, or <code>null</code> if a matching notification queue could not be found
	*/
	public static NotificationQueue fetchByF_LessThan_ExpireDate_First(
		Date expireDate, OrderByComparator<NotificationQueue> orderByComparator) {
		return getPersistence()
				   .fetchByF_LessThan_ExpireDate_First(expireDate,
			orderByComparator);
	}

	/**
	* Returns the last notification queue in the ordered set where expireDate &ge; &#63;.
	*
	* @param expireDate the expire date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching notification queue
	* @throws NoSuchNotificationQueueException if a matching notification queue could not be found
	*/
	public static NotificationQueue findByF_LessThan_ExpireDate_Last(
		Date expireDate, OrderByComparator<NotificationQueue> orderByComparator)
		throws org.opencps.communication.exception.NoSuchNotificationQueueException {
		return getPersistence()
				   .findByF_LessThan_ExpireDate_Last(expireDate,
			orderByComparator);
	}

	/**
	* Returns the last notification queue in the ordered set where expireDate &ge; &#63;.
	*
	* @param expireDate the expire date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching notification queue, or <code>null</code> if a matching notification queue could not be found
	*/
	public static NotificationQueue fetchByF_LessThan_ExpireDate_Last(
		Date expireDate, OrderByComparator<NotificationQueue> orderByComparator) {
		return getPersistence()
				   .fetchByF_LessThan_ExpireDate_Last(expireDate,
			orderByComparator);
	}

	/**
	* Returns the notification queues before and after the current notification queue in the ordered set where expireDate &ge; &#63;.
	*
	* @param notificationQueueId the primary key of the current notification queue
	* @param expireDate the expire date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next notification queue
	* @throws NoSuchNotificationQueueException if a notification queue with the primary key could not be found
	*/
	public static NotificationQueue[] findByF_LessThan_ExpireDate_PrevAndNext(
		long notificationQueueId, Date expireDate,
		OrderByComparator<NotificationQueue> orderByComparator)
		throws org.opencps.communication.exception.NoSuchNotificationQueueException {
		return getPersistence()
				   .findByF_LessThan_ExpireDate_PrevAndNext(notificationQueueId,
			expireDate, orderByComparator);
	}

	/**
	* Removes all the notification queues where expireDate &ge; &#63; from the database.
	*
	* @param expireDate the expire date
	*/
	public static void removeByF_LessThan_ExpireDate(Date expireDate) {
		getPersistence().removeByF_LessThan_ExpireDate(expireDate);
	}

	/**
	* Returns the number of notification queues where expireDate &ge; &#63;.
	*
	* @param expireDate the expire date
	* @return the number of matching notification queues
	*/
	public static int countByF_LessThan_ExpireDate(Date expireDate) {
		return getPersistence().countByF_LessThan_ExpireDate(expireDate);
	}

	/**
	* Returns the notification queue where groupId = &#63; and notificationType = &#63; and className = &#63; and classPK = &#63; and toEmail = &#63; or throws a {@link NoSuchNotificationQueueException} if it could not be found.
	*
	* @param groupId the group ID
	* @param notificationType the notification type
	* @param className the class name
	* @param classPK the class pk
	* @param toEmail the to email
	* @return the matching notification queue
	* @throws NoSuchNotificationQueueException if a matching notification queue could not be found
	*/
	public static NotificationQueue findByF_NT_CN_CPK_EMAIL(long groupId,
		String notificationType, String className, String classPK,
		String toEmail)
		throws org.opencps.communication.exception.NoSuchNotificationQueueException {
		return getPersistence()
				   .findByF_NT_CN_CPK_EMAIL(groupId, notificationType,
			className, classPK, toEmail);
	}

	/**
	* Returns the notification queue where groupId = &#63; and notificationType = &#63; and className = &#63; and classPK = &#63; and toEmail = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param notificationType the notification type
	* @param className the class name
	* @param classPK the class pk
	* @param toEmail the to email
	* @return the matching notification queue, or <code>null</code> if a matching notification queue could not be found
	*/
	public static NotificationQueue fetchByF_NT_CN_CPK_EMAIL(long groupId,
		String notificationType, String className, String classPK,
		String toEmail) {
		return getPersistence()
				   .fetchByF_NT_CN_CPK_EMAIL(groupId, notificationType,
			className, classPK, toEmail);
	}

	/**
	* Returns the notification queue where groupId = &#63; and notificationType = &#63; and className = &#63; and classPK = &#63; and toEmail = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param notificationType the notification type
	* @param className the class name
	* @param classPK the class pk
	* @param toEmail the to email
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching notification queue, or <code>null</code> if a matching notification queue could not be found
	*/
	public static NotificationQueue fetchByF_NT_CN_CPK_EMAIL(long groupId,
		String notificationType, String className, String classPK,
		String toEmail, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_NT_CN_CPK_EMAIL(groupId, notificationType,
			className, classPK, toEmail, retrieveFromCache);
	}

	/**
	* Removes the notification queue where groupId = &#63; and notificationType = &#63; and className = &#63; and classPK = &#63; and toEmail = &#63; from the database.
	*
	* @param groupId the group ID
	* @param notificationType the notification type
	* @param className the class name
	* @param classPK the class pk
	* @param toEmail the to email
	* @return the notification queue that was removed
	*/
	public static NotificationQueue removeByF_NT_CN_CPK_EMAIL(long groupId,
		String notificationType, String className, String classPK,
		String toEmail)
		throws org.opencps.communication.exception.NoSuchNotificationQueueException {
		return getPersistence()
				   .removeByF_NT_CN_CPK_EMAIL(groupId, notificationType,
			className, classPK, toEmail);
	}

	/**
	* Returns the number of notification queues where groupId = &#63; and notificationType = &#63; and className = &#63; and classPK = &#63; and toEmail = &#63;.
	*
	* @param groupId the group ID
	* @param notificationType the notification type
	* @param className the class name
	* @param classPK the class pk
	* @param toEmail the to email
	* @return the number of matching notification queues
	*/
	public static int countByF_NT_CN_CPK_EMAIL(long groupId,
		String notificationType, String className, String classPK,
		String toEmail) {
		return getPersistence()
				   .countByF_NT_CN_CPK_EMAIL(groupId, notificationType,
			className, classPK, toEmail);
	}

	/**
	* Returns all the notification queues where notificationType = &#63; and publicationDate &lt; &#63; and expireDate &ge; &#63;.
	*
	* @param notificationType the notification type
	* @param publicationDate the publication date
	* @param expireDate the expire date
	* @return the matching notification queues
	*/
	public static List<NotificationQueue> findByF_Greate_PublicationDate_Less_ExpireDate(
		String notificationType, Date publicationDate, Date expireDate) {
		return getPersistence()
				   .findByF_Greate_PublicationDate_Less_ExpireDate(notificationType,
			publicationDate, expireDate);
	}

	/**
	* Returns a range of all the notification queues where notificationType = &#63; and publicationDate &lt; &#63; and expireDate &ge; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param notificationType the notification type
	* @param publicationDate the publication date
	* @param expireDate the expire date
	* @param start the lower bound of the range of notification queues
	* @param end the upper bound of the range of notification queues (not inclusive)
	* @return the range of matching notification queues
	*/
	public static List<NotificationQueue> findByF_Greate_PublicationDate_Less_ExpireDate(
		String notificationType, Date publicationDate, Date expireDate,
		int start, int end) {
		return getPersistence()
				   .findByF_Greate_PublicationDate_Less_ExpireDate(notificationType,
			publicationDate, expireDate, start, end);
	}

	/**
	* Returns an ordered range of all the notification queues where notificationType = &#63; and publicationDate &lt; &#63; and expireDate &ge; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param notificationType the notification type
	* @param publicationDate the publication date
	* @param expireDate the expire date
	* @param start the lower bound of the range of notification queues
	* @param end the upper bound of the range of notification queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching notification queues
	*/
	public static List<NotificationQueue> findByF_Greate_PublicationDate_Less_ExpireDate(
		String notificationType, Date publicationDate, Date expireDate,
		int start, int end,
		OrderByComparator<NotificationQueue> orderByComparator) {
		return getPersistence()
				   .findByF_Greate_PublicationDate_Less_ExpireDate(notificationType,
			publicationDate, expireDate, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the notification queues where notificationType = &#63; and publicationDate &lt; &#63; and expireDate &ge; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param notificationType the notification type
	* @param publicationDate the publication date
	* @param expireDate the expire date
	* @param start the lower bound of the range of notification queues
	* @param end the upper bound of the range of notification queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching notification queues
	*/
	public static List<NotificationQueue> findByF_Greate_PublicationDate_Less_ExpireDate(
		String notificationType, Date publicationDate, Date expireDate,
		int start, int end,
		OrderByComparator<NotificationQueue> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_Greate_PublicationDate_Less_ExpireDate(notificationType,
			publicationDate, expireDate, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first notification queue in the ordered set where notificationType = &#63; and publicationDate &lt; &#63; and expireDate &ge; &#63;.
	*
	* @param notificationType the notification type
	* @param publicationDate the publication date
	* @param expireDate the expire date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching notification queue
	* @throws NoSuchNotificationQueueException if a matching notification queue could not be found
	*/
	public static NotificationQueue findByF_Greate_PublicationDate_Less_ExpireDate_First(
		String notificationType, Date publicationDate, Date expireDate,
		OrderByComparator<NotificationQueue> orderByComparator)
		throws org.opencps.communication.exception.NoSuchNotificationQueueException {
		return getPersistence()
				   .findByF_Greate_PublicationDate_Less_ExpireDate_First(notificationType,
			publicationDate, expireDate, orderByComparator);
	}

	/**
	* Returns the first notification queue in the ordered set where notificationType = &#63; and publicationDate &lt; &#63; and expireDate &ge; &#63;.
	*
	* @param notificationType the notification type
	* @param publicationDate the publication date
	* @param expireDate the expire date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching notification queue, or <code>null</code> if a matching notification queue could not be found
	*/
	public static NotificationQueue fetchByF_Greate_PublicationDate_Less_ExpireDate_First(
		String notificationType, Date publicationDate, Date expireDate,
		OrderByComparator<NotificationQueue> orderByComparator) {
		return getPersistence()
				   .fetchByF_Greate_PublicationDate_Less_ExpireDate_First(notificationType,
			publicationDate, expireDate, orderByComparator);
	}

	/**
	* Returns the last notification queue in the ordered set where notificationType = &#63; and publicationDate &lt; &#63; and expireDate &ge; &#63;.
	*
	* @param notificationType the notification type
	* @param publicationDate the publication date
	* @param expireDate the expire date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching notification queue
	* @throws NoSuchNotificationQueueException if a matching notification queue could not be found
	*/
	public static NotificationQueue findByF_Greate_PublicationDate_Less_ExpireDate_Last(
		String notificationType, Date publicationDate, Date expireDate,
		OrderByComparator<NotificationQueue> orderByComparator)
		throws org.opencps.communication.exception.NoSuchNotificationQueueException {
		return getPersistence()
				   .findByF_Greate_PublicationDate_Less_ExpireDate_Last(notificationType,
			publicationDate, expireDate, orderByComparator);
	}

	/**
	* Returns the last notification queue in the ordered set where notificationType = &#63; and publicationDate &lt; &#63; and expireDate &ge; &#63;.
	*
	* @param notificationType the notification type
	* @param publicationDate the publication date
	* @param expireDate the expire date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching notification queue, or <code>null</code> if a matching notification queue could not be found
	*/
	public static NotificationQueue fetchByF_Greate_PublicationDate_Less_ExpireDate_Last(
		String notificationType, Date publicationDate, Date expireDate,
		OrderByComparator<NotificationQueue> orderByComparator) {
		return getPersistence()
				   .fetchByF_Greate_PublicationDate_Less_ExpireDate_Last(notificationType,
			publicationDate, expireDate, orderByComparator);
	}

	/**
	* Returns the notification queues before and after the current notification queue in the ordered set where notificationType = &#63; and publicationDate &lt; &#63; and expireDate &ge; &#63;.
	*
	* @param notificationQueueId the primary key of the current notification queue
	* @param notificationType the notification type
	* @param publicationDate the publication date
	* @param expireDate the expire date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next notification queue
	* @throws NoSuchNotificationQueueException if a notification queue with the primary key could not be found
	*/
	public static NotificationQueue[] findByF_Greate_PublicationDate_Less_ExpireDate_PrevAndNext(
		long notificationQueueId, String notificationType,
		Date publicationDate, Date expireDate,
		OrderByComparator<NotificationQueue> orderByComparator)
		throws org.opencps.communication.exception.NoSuchNotificationQueueException {
		return getPersistence()
				   .findByF_Greate_PublicationDate_Less_ExpireDate_PrevAndNext(notificationQueueId,
			notificationType, publicationDate, expireDate, orderByComparator);
	}

	/**
	* Removes all the notification queues where notificationType = &#63; and publicationDate &lt; &#63; and expireDate &ge; &#63; from the database.
	*
	* @param notificationType the notification type
	* @param publicationDate the publication date
	* @param expireDate the expire date
	*/
	public static void removeByF_Greate_PublicationDate_Less_ExpireDate(
		String notificationType, Date publicationDate, Date expireDate) {
		getPersistence()
			.removeByF_Greate_PublicationDate_Less_ExpireDate(notificationType,
			publicationDate, expireDate);
	}

	/**
	* Returns the number of notification queues where notificationType = &#63; and publicationDate &lt; &#63; and expireDate &ge; &#63;.
	*
	* @param notificationType the notification type
	* @param publicationDate the publication date
	* @param expireDate the expire date
	* @return the number of matching notification queues
	*/
	public static int countByF_Greate_PublicationDate_Less_ExpireDate(
		String notificationType, Date publicationDate, Date expireDate) {
		return getPersistence()
				   .countByF_Greate_PublicationDate_Less_ExpireDate(notificationType,
			publicationDate, expireDate);
	}

	/**
	* Caches the notification queue in the entity cache if it is enabled.
	*
	* @param notificationQueue the notification queue
	*/
	public static void cacheResult(NotificationQueue notificationQueue) {
		getPersistence().cacheResult(notificationQueue);
	}

	/**
	* Caches the notification queues in the entity cache if it is enabled.
	*
	* @param notificationQueues the notification queues
	*/
	public static void cacheResult(List<NotificationQueue> notificationQueues) {
		getPersistence().cacheResult(notificationQueues);
	}

	/**
	* Creates a new notification queue with the primary key. Does not add the notification queue to the database.
	*
	* @param notificationQueueId the primary key for the new notification queue
	* @return the new notification queue
	*/
	public static NotificationQueue create(long notificationQueueId) {
		return getPersistence().create(notificationQueueId);
	}

	/**
	* Removes the notification queue with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param notificationQueueId the primary key of the notification queue
	* @return the notification queue that was removed
	* @throws NoSuchNotificationQueueException if a notification queue with the primary key could not be found
	*/
	public static NotificationQueue remove(long notificationQueueId)
		throws org.opencps.communication.exception.NoSuchNotificationQueueException {
		return getPersistence().remove(notificationQueueId);
	}

	public static NotificationQueue updateImpl(
		NotificationQueue notificationQueue) {
		return getPersistence().updateImpl(notificationQueue);
	}

	/**
	* Returns the notification queue with the primary key or throws a {@link NoSuchNotificationQueueException} if it could not be found.
	*
	* @param notificationQueueId the primary key of the notification queue
	* @return the notification queue
	* @throws NoSuchNotificationQueueException if a notification queue with the primary key could not be found
	*/
	public static NotificationQueue findByPrimaryKey(long notificationQueueId)
		throws org.opencps.communication.exception.NoSuchNotificationQueueException {
		return getPersistence().findByPrimaryKey(notificationQueueId);
	}

	/**
	* Returns the notification queue with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param notificationQueueId the primary key of the notification queue
	* @return the notification queue, or <code>null</code> if a notification queue with the primary key could not be found
	*/
	public static NotificationQueue fetchByPrimaryKey(long notificationQueueId) {
		return getPersistence().fetchByPrimaryKey(notificationQueueId);
	}

	public static java.util.Map<java.io.Serializable, NotificationQueue> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the notification queues.
	*
	* @return the notification queues
	*/
	public static List<NotificationQueue> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the notification queues.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of notification queues
	* @param end the upper bound of the range of notification queues (not inclusive)
	* @return the range of notification queues
	*/
	public static List<NotificationQueue> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the notification queues.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of notification queues
	* @param end the upper bound of the range of notification queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of notification queues
	*/
	public static List<NotificationQueue> findAll(int start, int end,
		OrderByComparator<NotificationQueue> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the notification queues.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of notification queues
	* @param end the upper bound of the range of notification queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of notification queues
	*/
	public static List<NotificationQueue> findAll(int start, int end,
		OrderByComparator<NotificationQueue> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the notification queues from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of notification queues.
	*
	* @return the number of notification queues
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static NotificationQueuePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<NotificationQueuePersistence, NotificationQueuePersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(NotificationQueuePersistence.class);

		ServiceTracker<NotificationQueuePersistence, NotificationQueuePersistence> serviceTracker =
			new ServiceTracker<NotificationQueuePersistence, NotificationQueuePersistence>(bundle.getBundleContext(),
				NotificationQueuePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}