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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.communication.exception.NoSuchNotificationQueueException;
import org.opencps.communication.model.NotificationQueue;

import java.util.Date;

/**
 * The persistence interface for the notification queue service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavd
 * @see org.opencps.communication.service.persistence.impl.NotificationQueuePersistenceImpl
 * @see NotificationQueueUtil
 * @generated
 */
@ProviderType
public interface NotificationQueuePersistence extends BasePersistence<NotificationQueue> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link NotificationQueueUtil} to access the notification queue persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the notification queues where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching notification queues
	*/
	public java.util.List<NotificationQueue> findByG(long groupId);

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
	public java.util.List<NotificationQueue> findByG(long groupId, int start,
		int end);

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
	public java.util.List<NotificationQueue> findByG(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationQueue> orderByComparator);

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
	public java.util.List<NotificationQueue> findByG(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationQueue> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first notification queue in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching notification queue
	* @throws NoSuchNotificationQueueException if a matching notification queue could not be found
	*/
	public NotificationQueue findByG_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationQueue> orderByComparator)
		throws NoSuchNotificationQueueException;

	/**
	* Returns the first notification queue in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching notification queue, or <code>null</code> if a matching notification queue could not be found
	*/
	public NotificationQueue fetchByG_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationQueue> orderByComparator);

	/**
	* Returns the last notification queue in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching notification queue
	* @throws NoSuchNotificationQueueException if a matching notification queue could not be found
	*/
	public NotificationQueue findByG_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationQueue> orderByComparator)
		throws NoSuchNotificationQueueException;

	/**
	* Returns the last notification queue in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching notification queue, or <code>null</code> if a matching notification queue could not be found
	*/
	public NotificationQueue fetchByG_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationQueue> orderByComparator);

	/**
	* Returns the notification queues before and after the current notification queue in the ordered set where groupId = &#63;.
	*
	* @param notificationQueueId the primary key of the current notification queue
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next notification queue
	* @throws NoSuchNotificationQueueException if a notification queue with the primary key could not be found
	*/
	public NotificationQueue[] findByG_PrevAndNext(long notificationQueueId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationQueue> orderByComparator)
		throws NoSuchNotificationQueueException;

	/**
	* Removes all the notification queues where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByG(long groupId);

	/**
	* Returns the number of notification queues where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching notification queues
	*/
	public int countByG(long groupId);

	/**
	* Returns all the notification queues where expireDate &le; &#63;.
	*
	* @param expireDate the expire date
	* @return the matching notification queues
	*/
	public java.util.List<NotificationQueue> findByF_GreaterThan_ExpireDate(
		Date expireDate);

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
	public java.util.List<NotificationQueue> findByF_GreaterThan_ExpireDate(
		Date expireDate, int start, int end);

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
	public java.util.List<NotificationQueue> findByF_GreaterThan_ExpireDate(
		Date expireDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationQueue> orderByComparator);

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
	public java.util.List<NotificationQueue> findByF_GreaterThan_ExpireDate(
		Date expireDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationQueue> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first notification queue in the ordered set where expireDate &le; &#63;.
	*
	* @param expireDate the expire date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching notification queue
	* @throws NoSuchNotificationQueueException if a matching notification queue could not be found
	*/
	public NotificationQueue findByF_GreaterThan_ExpireDate_First(
		Date expireDate,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationQueue> orderByComparator)
		throws NoSuchNotificationQueueException;

	/**
	* Returns the first notification queue in the ordered set where expireDate &le; &#63;.
	*
	* @param expireDate the expire date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching notification queue, or <code>null</code> if a matching notification queue could not be found
	*/
	public NotificationQueue fetchByF_GreaterThan_ExpireDate_First(
		Date expireDate,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationQueue> orderByComparator);

	/**
	* Returns the last notification queue in the ordered set where expireDate &le; &#63;.
	*
	* @param expireDate the expire date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching notification queue
	* @throws NoSuchNotificationQueueException if a matching notification queue could not be found
	*/
	public NotificationQueue findByF_GreaterThan_ExpireDate_Last(
		Date expireDate,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationQueue> orderByComparator)
		throws NoSuchNotificationQueueException;

	/**
	* Returns the last notification queue in the ordered set where expireDate &le; &#63;.
	*
	* @param expireDate the expire date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching notification queue, or <code>null</code> if a matching notification queue could not be found
	*/
	public NotificationQueue fetchByF_GreaterThan_ExpireDate_Last(
		Date expireDate,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationQueue> orderByComparator);

	/**
	* Returns the notification queues before and after the current notification queue in the ordered set where expireDate &le; &#63;.
	*
	* @param notificationQueueId the primary key of the current notification queue
	* @param expireDate the expire date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next notification queue
	* @throws NoSuchNotificationQueueException if a notification queue with the primary key could not be found
	*/
	public NotificationQueue[] findByF_GreaterThan_ExpireDate_PrevAndNext(
		long notificationQueueId, Date expireDate,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationQueue> orderByComparator)
		throws NoSuchNotificationQueueException;

	/**
	* Removes all the notification queues where expireDate &le; &#63; from the database.
	*
	* @param expireDate the expire date
	*/
	public void removeByF_GreaterThan_ExpireDate(Date expireDate);

	/**
	* Returns the number of notification queues where expireDate &le; &#63;.
	*
	* @param expireDate the expire date
	* @return the number of matching notification queues
	*/
	public int countByF_GreaterThan_ExpireDate(Date expireDate);

	/**
	* Returns all the notification queues where notificationType = &#63; and expireDate &ge; &#63;.
	*
	* @param notificationType the notification type
	* @param expireDate the expire date
	* @return the matching notification queues
	*/
	public java.util.List<NotificationQueue> findByF_notificationType_LessThanExpireDate(
		String notificationType, Date expireDate);

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
	public java.util.List<NotificationQueue> findByF_notificationType_LessThanExpireDate(
		String notificationType, Date expireDate, int start, int end);

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
	public java.util.List<NotificationQueue> findByF_notificationType_LessThanExpireDate(
		String notificationType, Date expireDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationQueue> orderByComparator);

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
	public java.util.List<NotificationQueue> findByF_notificationType_LessThanExpireDate(
		String notificationType, Date expireDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationQueue> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first notification queue in the ordered set where notificationType = &#63; and expireDate &ge; &#63;.
	*
	* @param notificationType the notification type
	* @param expireDate the expire date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching notification queue
	* @throws NoSuchNotificationQueueException if a matching notification queue could not be found
	*/
	public NotificationQueue findByF_notificationType_LessThanExpireDate_First(
		String notificationType, Date expireDate,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationQueue> orderByComparator)
		throws NoSuchNotificationQueueException;

	/**
	* Returns the first notification queue in the ordered set where notificationType = &#63; and expireDate &ge; &#63;.
	*
	* @param notificationType the notification type
	* @param expireDate the expire date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching notification queue, or <code>null</code> if a matching notification queue could not be found
	*/
	public NotificationQueue fetchByF_notificationType_LessThanExpireDate_First(
		String notificationType, Date expireDate,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationQueue> orderByComparator);

	/**
	* Returns the last notification queue in the ordered set where notificationType = &#63; and expireDate &ge; &#63;.
	*
	* @param notificationType the notification type
	* @param expireDate the expire date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching notification queue
	* @throws NoSuchNotificationQueueException if a matching notification queue could not be found
	*/
	public NotificationQueue findByF_notificationType_LessThanExpireDate_Last(
		String notificationType, Date expireDate,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationQueue> orderByComparator)
		throws NoSuchNotificationQueueException;

	/**
	* Returns the last notification queue in the ordered set where notificationType = &#63; and expireDate &ge; &#63;.
	*
	* @param notificationType the notification type
	* @param expireDate the expire date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching notification queue, or <code>null</code> if a matching notification queue could not be found
	*/
	public NotificationQueue fetchByF_notificationType_LessThanExpireDate_Last(
		String notificationType, Date expireDate,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationQueue> orderByComparator);

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
	public NotificationQueue[] findByF_notificationType_LessThanExpireDate_PrevAndNext(
		long notificationQueueId, String notificationType, Date expireDate,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationQueue> orderByComparator)
		throws NoSuchNotificationQueueException;

	/**
	* Removes all the notification queues where notificationType = &#63; and expireDate &ge; &#63; from the database.
	*
	* @param notificationType the notification type
	* @param expireDate the expire date
	*/
	public void removeByF_notificationType_LessThanExpireDate(
		String notificationType, Date expireDate);

	/**
	* Returns the number of notification queues where notificationType = &#63; and expireDate &ge; &#63;.
	*
	* @param notificationType the notification type
	* @param expireDate the expire date
	* @return the number of matching notification queues
	*/
	public int countByF_notificationType_LessThanExpireDate(
		String notificationType, Date expireDate);

	/**
	* Returns all the notification queues where expireDate &ge; &#63;.
	*
	* @param expireDate the expire date
	* @return the matching notification queues
	*/
	public java.util.List<NotificationQueue> findByF_LessThan_ExpireDate(
		Date expireDate);

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
	public java.util.List<NotificationQueue> findByF_LessThan_ExpireDate(
		Date expireDate, int start, int end);

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
	public java.util.List<NotificationQueue> findByF_LessThan_ExpireDate(
		Date expireDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationQueue> orderByComparator);

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
	public java.util.List<NotificationQueue> findByF_LessThan_ExpireDate(
		Date expireDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationQueue> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first notification queue in the ordered set where expireDate &ge; &#63;.
	*
	* @param expireDate the expire date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching notification queue
	* @throws NoSuchNotificationQueueException if a matching notification queue could not be found
	*/
	public NotificationQueue findByF_LessThan_ExpireDate_First(
		Date expireDate,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationQueue> orderByComparator)
		throws NoSuchNotificationQueueException;

	/**
	* Returns the first notification queue in the ordered set where expireDate &ge; &#63;.
	*
	* @param expireDate the expire date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching notification queue, or <code>null</code> if a matching notification queue could not be found
	*/
	public NotificationQueue fetchByF_LessThan_ExpireDate_First(
		Date expireDate,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationQueue> orderByComparator);

	/**
	* Returns the last notification queue in the ordered set where expireDate &ge; &#63;.
	*
	* @param expireDate the expire date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching notification queue
	* @throws NoSuchNotificationQueueException if a matching notification queue could not be found
	*/
	public NotificationQueue findByF_LessThan_ExpireDate_Last(Date expireDate,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationQueue> orderByComparator)
		throws NoSuchNotificationQueueException;

	/**
	* Returns the last notification queue in the ordered set where expireDate &ge; &#63;.
	*
	* @param expireDate the expire date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching notification queue, or <code>null</code> if a matching notification queue could not be found
	*/
	public NotificationQueue fetchByF_LessThan_ExpireDate_Last(
		Date expireDate,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationQueue> orderByComparator);

	/**
	* Returns the notification queues before and after the current notification queue in the ordered set where expireDate &ge; &#63;.
	*
	* @param notificationQueueId the primary key of the current notification queue
	* @param expireDate the expire date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next notification queue
	* @throws NoSuchNotificationQueueException if a notification queue with the primary key could not be found
	*/
	public NotificationQueue[] findByF_LessThan_ExpireDate_PrevAndNext(
		long notificationQueueId, Date expireDate,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationQueue> orderByComparator)
		throws NoSuchNotificationQueueException;

	/**
	* Removes all the notification queues where expireDate &ge; &#63; from the database.
	*
	* @param expireDate the expire date
	*/
	public void removeByF_LessThan_ExpireDate(Date expireDate);

	/**
	* Returns the number of notification queues where expireDate &ge; &#63;.
	*
	* @param expireDate the expire date
	* @return the number of matching notification queues
	*/
	public int countByF_LessThan_ExpireDate(Date expireDate);

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
	public NotificationQueue findByF_NT_CN_CPK_EMAIL(long groupId,
		String notificationType, String className, String classPK,
		String toEmail) throws NoSuchNotificationQueueException;

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
	public NotificationQueue fetchByF_NT_CN_CPK_EMAIL(long groupId,
		String notificationType, String className, String classPK,
		String toEmail);

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
	public NotificationQueue fetchByF_NT_CN_CPK_EMAIL(long groupId,
		String notificationType, String className, String classPK,
		String toEmail, boolean retrieveFromCache);

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
	public NotificationQueue removeByF_NT_CN_CPK_EMAIL(long groupId,
		String notificationType, String className, String classPK,
		String toEmail) throws NoSuchNotificationQueueException;

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
	public int countByF_NT_CN_CPK_EMAIL(long groupId, String notificationType,
		String className, String classPK, String toEmail);

	/**
	* Returns all the notification queues where notificationType = &#63; and publicationDate &lt; &#63; and expireDate &ge; &#63;.
	*
	* @param notificationType the notification type
	* @param publicationDate the publication date
	* @param expireDate the expire date
	* @return the matching notification queues
	*/
	public java.util.List<NotificationQueue> findByF_Greate_PublicationDate_Less_ExpireDate(
		String notificationType, Date publicationDate, Date expireDate);

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
	public java.util.List<NotificationQueue> findByF_Greate_PublicationDate_Less_ExpireDate(
		String notificationType, Date publicationDate, Date expireDate,
		int start, int end);

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
	public java.util.List<NotificationQueue> findByF_Greate_PublicationDate_Less_ExpireDate(
		String notificationType, Date publicationDate, Date expireDate,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationQueue> orderByComparator);

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
	public java.util.List<NotificationQueue> findByF_Greate_PublicationDate_Less_ExpireDate(
		String notificationType, Date publicationDate, Date expireDate,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationQueue> orderByComparator,
		boolean retrieveFromCache);

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
	public NotificationQueue findByF_Greate_PublicationDate_Less_ExpireDate_First(
		String notificationType, Date publicationDate, Date expireDate,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationQueue> orderByComparator)
		throws NoSuchNotificationQueueException;

	/**
	* Returns the first notification queue in the ordered set where notificationType = &#63; and publicationDate &lt; &#63; and expireDate &ge; &#63;.
	*
	* @param notificationType the notification type
	* @param publicationDate the publication date
	* @param expireDate the expire date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching notification queue, or <code>null</code> if a matching notification queue could not be found
	*/
	public NotificationQueue fetchByF_Greate_PublicationDate_Less_ExpireDate_First(
		String notificationType, Date publicationDate, Date expireDate,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationQueue> orderByComparator);

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
	public NotificationQueue findByF_Greate_PublicationDate_Less_ExpireDate_Last(
		String notificationType, Date publicationDate, Date expireDate,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationQueue> orderByComparator)
		throws NoSuchNotificationQueueException;

	/**
	* Returns the last notification queue in the ordered set where notificationType = &#63; and publicationDate &lt; &#63; and expireDate &ge; &#63;.
	*
	* @param notificationType the notification type
	* @param publicationDate the publication date
	* @param expireDate the expire date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching notification queue, or <code>null</code> if a matching notification queue could not be found
	*/
	public NotificationQueue fetchByF_Greate_PublicationDate_Less_ExpireDate_Last(
		String notificationType, Date publicationDate, Date expireDate,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationQueue> orderByComparator);

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
	public NotificationQueue[] findByF_Greate_PublicationDate_Less_ExpireDate_PrevAndNext(
		long notificationQueueId, String notificationType,
		Date publicationDate, Date expireDate,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationQueue> orderByComparator)
		throws NoSuchNotificationQueueException;

	/**
	* Removes all the notification queues where notificationType = &#63; and publicationDate &lt; &#63; and expireDate &ge; &#63; from the database.
	*
	* @param notificationType the notification type
	* @param publicationDate the publication date
	* @param expireDate the expire date
	*/
	public void removeByF_Greate_PublicationDate_Less_ExpireDate(
		String notificationType, Date publicationDate, Date expireDate);

	/**
	* Returns the number of notification queues where notificationType = &#63; and publicationDate &lt; &#63; and expireDate &ge; &#63;.
	*
	* @param notificationType the notification type
	* @param publicationDate the publication date
	* @param expireDate the expire date
	* @return the number of matching notification queues
	*/
	public int countByF_Greate_PublicationDate_Less_ExpireDate(
		String notificationType, Date publicationDate, Date expireDate);

	/**
	* Caches the notification queue in the entity cache if it is enabled.
	*
	* @param notificationQueue the notification queue
	*/
	public void cacheResult(NotificationQueue notificationQueue);

	/**
	* Caches the notification queues in the entity cache if it is enabled.
	*
	* @param notificationQueues the notification queues
	*/
	public void cacheResult(
		java.util.List<NotificationQueue> notificationQueues);

	/**
	* Creates a new notification queue with the primary key. Does not add the notification queue to the database.
	*
	* @param notificationQueueId the primary key for the new notification queue
	* @return the new notification queue
	*/
	public NotificationQueue create(long notificationQueueId);

	/**
	* Removes the notification queue with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param notificationQueueId the primary key of the notification queue
	* @return the notification queue that was removed
	* @throws NoSuchNotificationQueueException if a notification queue with the primary key could not be found
	*/
	public NotificationQueue remove(long notificationQueueId)
		throws NoSuchNotificationQueueException;

	public NotificationQueue updateImpl(NotificationQueue notificationQueue);

	/**
	* Returns the notification queue with the primary key or throws a {@link NoSuchNotificationQueueException} if it could not be found.
	*
	* @param notificationQueueId the primary key of the notification queue
	* @return the notification queue
	* @throws NoSuchNotificationQueueException if a notification queue with the primary key could not be found
	*/
	public NotificationQueue findByPrimaryKey(long notificationQueueId)
		throws NoSuchNotificationQueueException;

	/**
	* Returns the notification queue with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param notificationQueueId the primary key of the notification queue
	* @return the notification queue, or <code>null</code> if a notification queue with the primary key could not be found
	*/
	public NotificationQueue fetchByPrimaryKey(long notificationQueueId);

	@Override
	public java.util.Map<java.io.Serializable, NotificationQueue> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the notification queues.
	*
	* @return the notification queues
	*/
	public java.util.List<NotificationQueue> findAll();

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
	public java.util.List<NotificationQueue> findAll(int start, int end);

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
	public java.util.List<NotificationQueue> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationQueue> orderByComparator);

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
	public java.util.List<NotificationQueue> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationQueue> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the notification queues from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of notification queues.
	*
	* @return the number of notification queues
	*/
	public int countAll();
}