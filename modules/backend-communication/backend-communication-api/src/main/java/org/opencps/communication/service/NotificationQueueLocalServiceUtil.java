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

package org.opencps.communication.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for NotificationQueue. This utility wraps
 * {@link org.opencps.communication.service.impl.NotificationQueueLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author khoavd
 * @see NotificationQueueLocalService
 * @see org.opencps.communication.service.base.NotificationQueueLocalServiceBaseImpl
 * @see org.opencps.communication.service.impl.NotificationQueueLocalServiceImpl
 * @generated
 */
@ProviderType
public class NotificationQueueLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.communication.service.impl.NotificationQueueLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static org.opencps.communication.model.NotificationQueue addNotificationQueue(
		long userId, long groupId, String notificationType, String className,
		String classPK, String payload, String fromUsername, String toUsername,
		long toUserId, String toEmail, String toTelNo,
		java.util.Date publicationDate, java.util.Date expireDate,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.NoSuchUserException {
		return getService()
				   .addNotificationQueue(userId, groupId, notificationType,
			className, classPK, payload, fromUsername, toUsername, toUserId,
			toEmail, toTelNo, publicationDate, expireDate, serviceContext);
	}

	/**
	* Adds the notification queue to the database. Also notifies the appropriate model listeners.
	*
	* @param notificationQueue the notification queue
	* @return the notification queue that was added
	*/
	public static org.opencps.communication.model.NotificationQueue addNotificationQueue(
		org.opencps.communication.model.NotificationQueue notificationQueue) {
		return getService().addNotificationQueue(notificationQueue);
	}

	public static org.opencps.communication.model.NotificationQueue adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return getService().adminProcessData(objectData);
	}

	public static org.opencps.communication.model.NotificationQueue adminProcessDelete(
		Long id) {
		return getService().adminProcessDelete(id);
	}

	/**
	* Creates a new notification queue with the primary key. Does not add the notification queue to the database.
	*
	* @param notificationQueueId the primary key for the new notification queue
	* @return the new notification queue
	*/
	public static org.opencps.communication.model.NotificationQueue createNotificationQueue(
		long notificationQueueId) {
		return getService().createNotificationQueue(notificationQueueId);
	}

	public static void deleteByGroup(long groupId) {
		getService().deleteByGroup(groupId);
	}

	public static void deleteExpiredNotificationQueue(java.util.Date date) {
		getService().deleteExpiredNotificationQueue(date);
	}

	/**
	* Deletes the notification queue with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param notificationQueueId the primary key of the notification queue
	* @return the notification queue that was removed
	* @throws PortalException if a notification queue with the primary key could not be found
	*/
	public static org.opencps.communication.model.NotificationQueue deleteNotificationQueue(
		long notificationQueueId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteNotificationQueue(notificationQueueId);
	}

	/**
	* @param dictCollectionId
	* @param serviceContext
	* @return
	* @throws NoSuchNotificationQueueException
	*/
	public static org.opencps.communication.model.NotificationQueue deleteNotificationQueue(
		long notificationQueueId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws org.opencps.communication.exception.NoSuchNotificationQueueException {
		return getService()
				   .deleteNotificationQueue(notificationQueueId, serviceContext);
	}

	/**
	* Deletes the notification queue from the database. Also notifies the appropriate model listeners.
	*
	* @param notificationQueue the notification queue
	* @return the notification queue that was removed
	*/
	public static org.opencps.communication.model.NotificationQueue deleteNotificationQueue(
		org.opencps.communication.model.NotificationQueue notificationQueue) {
		return getService().deleteNotificationQueue(notificationQueue);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.communication.model.impl.NotificationQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.communication.model.impl.NotificationQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static org.opencps.communication.model.NotificationQueue fetchNotificationQueue(
		long notificationQueueId) {
		return getService().fetchNotificationQueue(notificationQueueId);
	}

	public static java.util.List<org.opencps.communication.model.NotificationQueue> findByF_LessThan_ExpireDate(
		java.util.Date date) {
		return getService().findByF_LessThan_ExpireDate(date);
	}

	public static java.util.List<org.opencps.communication.model.NotificationQueue> findByF_notificationType_LessThanExpireDate(
		String notificationType, java.util.Date date) {
		return getService()
				   .findByF_notificationType_LessThanExpireDate(notificationType,
			date);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the notification queue with the primary key.
	*
	* @param notificationQueueId the primary key of the notification queue
	* @return the notification queue
	* @throws PortalException if a notification queue with the primary key could not be found
	*/
	public static org.opencps.communication.model.NotificationQueue getNotificationQueue(
		long notificationQueueId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getNotificationQueue(notificationQueueId);
	}

	/**
	* Returns a range of all the notification queues.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.communication.model.impl.NotificationQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of notification queues
	* @param end the upper bound of the range of notification queues (not inclusive)
	* @return the range of notification queues
	*/
	public static java.util.List<org.opencps.communication.model.NotificationQueue> getNotificationQueues(
		int start, int end) {
		return getService().getNotificationQueues(start, end);
	}

	/**
	* Returns the number of notification queues.
	*
	* @return the number of notification queues
	*/
	public static int getNotificationQueuesCount() {
		return getService().getNotificationQueuesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the notification queue in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param notificationQueue the notification queue
	* @return the notification queue that was updated
	*/
	public static org.opencps.communication.model.NotificationQueue updateNotificationQueue(
		org.opencps.communication.model.NotificationQueue notificationQueue) {
		return getService().updateNotificationQueue(notificationQueue);
	}

	public static NotificationQueueLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<NotificationQueueLocalService, NotificationQueueLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(NotificationQueueLocalService.class);

		ServiceTracker<NotificationQueueLocalService, NotificationQueueLocalService> serviceTracker =
			new ServiceTracker<NotificationQueueLocalService, NotificationQueueLocalService>(bundle.getBundleContext(),
				NotificationQueueLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}