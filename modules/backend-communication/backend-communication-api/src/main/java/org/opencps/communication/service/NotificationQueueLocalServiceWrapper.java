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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link NotificationQueueLocalService}.
 *
 * @author khoavd
 * @see NotificationQueueLocalService
 * @generated
 */
@ProviderType
public class NotificationQueueLocalServiceWrapper
	implements NotificationQueueLocalService,
		ServiceWrapper<NotificationQueueLocalService> {
	public NotificationQueueLocalServiceWrapper(
		NotificationQueueLocalService notificationQueueLocalService) {
		_notificationQueueLocalService = notificationQueueLocalService;
	}

	@Override
	public org.opencps.communication.model.NotificationQueue addNotificationQueue(
		long userId, long groupId, String notificationType, String className,
		String classPK, String payload, String fromUsername, String toUsername,
		long toUserId, String toEmail, String toTelNo,
		java.util.Date publicationDate, java.util.Date expireDate,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.NoSuchUserException {
		return _notificationQueueLocalService.addNotificationQueue(userId,
			groupId, notificationType, className, classPK, payload,
			fromUsername, toUsername, toUserId, toEmail, toTelNo,
			publicationDate, expireDate, serviceContext);
	}

	/**
	* Adds the notification queue to the database. Also notifies the appropriate model listeners.
	*
	* @param notificationQueue the notification queue
	* @return the notification queue that was added
	*/
	@Override
	public org.opencps.communication.model.NotificationQueue addNotificationQueue(
		org.opencps.communication.model.NotificationQueue notificationQueue) {
		return _notificationQueueLocalService.addNotificationQueue(notificationQueue);
	}

	@Override
	public org.opencps.communication.model.NotificationQueue adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return _notificationQueueLocalService.adminProcessData(objectData);
	}

	@Override
	public org.opencps.communication.model.NotificationQueue adminProcessDelete(
		Long id) {
		return _notificationQueueLocalService.adminProcessDelete(id);
	}

	/**
	* Creates a new notification queue with the primary key. Does not add the notification queue to the database.
	*
	* @param notificationQueueId the primary key for the new notification queue
	* @return the new notification queue
	*/
	@Override
	public org.opencps.communication.model.NotificationQueue createNotificationQueue(
		long notificationQueueId) {
		return _notificationQueueLocalService.createNotificationQueue(notificationQueueId);
	}

	@Override
	public void deleteByGroup(long groupId) {
		_notificationQueueLocalService.deleteByGroup(groupId);
	}

	@Override
	public void deleteExpiredNotificationQueue(java.util.Date date) {
		_notificationQueueLocalService.deleteExpiredNotificationQueue(date);
	}

	/**
	* Deletes the notification queue with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param notificationQueueId the primary key of the notification queue
	* @return the notification queue that was removed
	* @throws PortalException if a notification queue with the primary key could not be found
	*/
	@Override
	public org.opencps.communication.model.NotificationQueue deleteNotificationQueue(
		long notificationQueueId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _notificationQueueLocalService.deleteNotificationQueue(notificationQueueId);
	}

	/**
	* @param dictCollectionId
	* @param serviceContext
	* @return
	* @throws NoSuchNotificationQueueException
	*/
	@Override
	public org.opencps.communication.model.NotificationQueue deleteNotificationQueue(
		long notificationQueueId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws org.opencps.communication.exception.NoSuchNotificationQueueException {
		return _notificationQueueLocalService.deleteNotificationQueue(notificationQueueId,
			serviceContext);
	}

	/**
	* Deletes the notification queue from the database. Also notifies the appropriate model listeners.
	*
	* @param notificationQueue the notification queue
	* @return the notification queue that was removed
	*/
	@Override
	public org.opencps.communication.model.NotificationQueue deleteNotificationQueue(
		org.opencps.communication.model.NotificationQueue notificationQueue) {
		return _notificationQueueLocalService.deleteNotificationQueue(notificationQueue);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _notificationQueueLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _notificationQueueLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _notificationQueueLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _notificationQueueLocalService.dynamicQuery(dynamicQuery, start,
			end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _notificationQueueLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _notificationQueueLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _notificationQueueLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public java.util.List<org.opencps.communication.model.NotificationQueue> fetchByF_Greate_PublicationDate_Less_ExpireDate(
		String notificationType, java.util.Date publicationDate,
		java.util.Date expireDate) {
		return _notificationQueueLocalService.fetchByF_Greate_PublicationDate_Less_ExpireDate(notificationType,
			publicationDate, expireDate);
	}

	@Override
	public org.opencps.communication.model.NotificationQueue fetchNotificationQueue(
		long notificationQueueId) {
		return _notificationQueueLocalService.fetchNotificationQueue(notificationQueueId);
	}

	@Override
	public java.util.List<org.opencps.communication.model.NotificationQueue> findByF_LessThan_ExpireDate(
		java.util.Date date) {
		return _notificationQueueLocalService.findByF_LessThan_ExpireDate(date);
	}

	@Override
	public java.util.List<org.opencps.communication.model.NotificationQueue> findByF_notificationType_LessThanExpireDate(
		String notificationType, java.util.Date date) {
		return _notificationQueueLocalService.findByF_notificationType_LessThanExpireDate(notificationType,
			date);
	}

	@Override
	public org.opencps.communication.model.NotificationQueue findByF_NT_CN_CPK_EMAIL(
		long groupId, String notificationType, String className,
		String classPK, String email)
		throws org.opencps.communication.exception.NoSuchNotificationQueueException {
		return _notificationQueueLocalService.findByF_NT_CN_CPK_EMAIL(groupId,
			notificationType, className, classPK, email);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _notificationQueueLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _notificationQueueLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the notification queue with the primary key.
	*
	* @param notificationQueueId the primary key of the notification queue
	* @return the notification queue
	* @throws PortalException if a notification queue with the primary key could not be found
	*/
	@Override
	public org.opencps.communication.model.NotificationQueue getNotificationQueue(
		long notificationQueueId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _notificationQueueLocalService.getNotificationQueue(notificationQueueId);
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
	@Override
	public java.util.List<org.opencps.communication.model.NotificationQueue> getNotificationQueues(
		int start, int end) {
		return _notificationQueueLocalService.getNotificationQueues(start, end);
	}

	/**
	* Returns the number of notification queues.
	*
	* @return the number of notification queues
	*/
	@Override
	public int getNotificationQueuesCount() {
		return _notificationQueueLocalService.getNotificationQueuesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _notificationQueueLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _notificationQueueLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the notification queue in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param notificationQueue the notification queue
	* @return the notification queue that was updated
	*/
	@Override
	public org.opencps.communication.model.NotificationQueue updateNotificationQueue(
		org.opencps.communication.model.NotificationQueue notificationQueue) {
		return _notificationQueueLocalService.updateNotificationQueue(notificationQueue);
	}

	@Override
	public NotificationQueueLocalService getWrappedService() {
		return _notificationQueueLocalService;
	}

	@Override
	public void setWrappedService(
		NotificationQueueLocalService notificationQueueLocalService) {
		_notificationQueueLocalService = notificationQueueLocalService;
	}

	private NotificationQueueLocalService _notificationQueueLocalService;
}