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

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.communication.exception.NoSuchNotificationQueueException;
import org.opencps.communication.model.NotificationQueue;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

/**
 * Provides the local service interface for NotificationQueue. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author khoavd
 * @see NotificationQueueLocalServiceUtil
 * @see org.opencps.communication.service.base.NotificationQueueLocalServiceBaseImpl
 * @see org.opencps.communication.service.impl.NotificationQueueLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface NotificationQueueLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link NotificationQueueLocalServiceUtil} to access the notification queue local service. Add custom service methods to {@link org.opencps.communication.service.impl.NotificationQueueLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public NotificationQueue addNotificationQueue(long userId, long groupId,
		String notificationType, String className, String classPK,
		String payload, String fromUsername, String toUsername, long toUserId,
		String toEmail, String toTelNo, Date publicationDate, Date expireDate,
		ServiceContext serviceContext) throws NoSuchUserException;

	/**
	* Adds the notification queue to the database. Also notifies the appropriate model listeners.
	*
	* @param notificationQueue the notification queue
	* @return the notification queue that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public NotificationQueue addNotificationQueue(
		NotificationQueue notificationQueue);

	@Indexable(type = IndexableType.REINDEX)
	public NotificationQueue adminProcessData(JSONObject objectData);

	@Indexable(type = IndexableType.DELETE)
	public NotificationQueue adminProcessDelete(Long id);

	/**
	* Creates a new notification queue with the primary key. Does not add the notification queue to the database.
	*
	* @param notificationQueueId the primary key for the new notification queue
	* @return the new notification queue
	*/
	@Transactional(enabled = false)
	public NotificationQueue createNotificationQueue(long notificationQueueId);

	public void deleteByGroup(long groupId);

	@Indexable(type = IndexableType.DELETE)
	public void deleteExpiredNotificationQueue(Date date);

	/**
	* Deletes the notification queue with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param notificationQueueId the primary key of the notification queue
	* @return the notification queue that was removed
	* @throws PortalException if a notification queue with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public NotificationQueue deleteNotificationQueue(long notificationQueueId)
		throws PortalException;

	/**
	* @param dictCollectionId
	* @param serviceContext
	* @return
	* @throws NoSuchNotificationQueueException
	*/
	@Indexable(type = IndexableType.DELETE)
	public NotificationQueue deleteNotificationQueue(long notificationQueueId,
		ServiceContext serviceContext) throws NoSuchNotificationQueueException;

	/**
	* Deletes the notification queue from the database. Also notifies the appropriate model listeners.
	*
	* @param notificationQueue the notification queue
	* @return the notification queue that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public NotificationQueue deleteNotificationQueue(
		NotificationQueue notificationQueue);

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	public DynamicQuery dynamicQuery();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public NotificationQueue fetchNotificationQueue(long notificationQueueId);

	public List<NotificationQueue> findByF_LessThan_ExpireDate(Date date);

	public List<NotificationQueue> findByF_notificationType_LessThanExpireDate(
		String notificationType, Date date);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* Returns the notification queue with the primary key.
	*
	* @param notificationQueueId the primary key of the notification queue
	* @return the notification queue
	* @throws PortalException if a notification queue with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public NotificationQueue getNotificationQueue(long notificationQueueId)
		throws PortalException;

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<NotificationQueue> getNotificationQueues(int start, int end);

	/**
	* Returns the number of notification queues.
	*
	* @return the number of notification queues
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getNotificationQueuesCount();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public String getOSGiServiceIdentifier();

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Updates the notification queue in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param notificationQueue the notification queue
	* @return the notification queue that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public NotificationQueue updateNotificationQueue(
		NotificationQueue notificationQueue);
}