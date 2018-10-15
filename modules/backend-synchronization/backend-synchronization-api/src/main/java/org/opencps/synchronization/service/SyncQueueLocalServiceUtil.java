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

package org.opencps.synchronization.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for SyncQueue. This utility wraps
 * {@link org.opencps.synchronization.service.impl.SyncQueueLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author trungdk
 * @see SyncQueueLocalService
 * @see org.opencps.synchronization.service.base.SyncQueueLocalServiceBaseImpl
 * @see org.opencps.synchronization.service.impl.SyncQueueLocalServiceImpl
 * @generated
 */
@ProviderType
public class SyncQueueLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.synchronization.service.impl.SyncQueueLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static org.opencps.synchronization.model.SyncQueue addSyncQueue(
		long userId, long groupId, String serverNo, String className,
		String jsonObject, int status, int retryCount, int priority,
		String method,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			com.liferay.portal.kernel.exception.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addSyncQueue(userId, groupId, serverNo, className,
			jsonObject, status, retryCount, priority, method, serviceContext);
	}

	/**
	* Adds the sync queue to the database. Also notifies the appropriate model listeners.
	*
	* @param syncQueue the sync queue
	* @return the sync queue that was added
	*/
	public static org.opencps.synchronization.model.SyncQueue addSyncQueue(
		org.opencps.synchronization.model.SyncQueue syncQueue) {
		return getService().addSyncQueue(syncQueue);
	}

	/**
	* Creates a new sync queue with the primary key. Does not add the sync queue to the database.
	*
	* @param syncQueueId the primary key for the new sync queue
	* @return the new sync queue
	*/
	public static org.opencps.synchronization.model.SyncQueue createSyncQueue(
		long syncQueueId) {
		return getService().createSyncQueue(syncQueueId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the sync queue with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param syncQueueId the primary key of the sync queue
	* @return the sync queue that was removed
	* @throws PortalException if a sync queue with the primary key could not be found
	*/
	public static org.opencps.synchronization.model.SyncQueue deleteSyncQueue(
		long syncQueueId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteSyncQueue(syncQueueId);
	}

	public static org.opencps.synchronization.model.SyncQueue deleteSyncQueue(
		long syncQueueId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			org.opencps.auth.api.exception.NotFoundException {
		return getService().deleteSyncQueue(syncQueueId, serviceContext);
	}

	/**
	* Deletes the sync queue from the database. Also notifies the appropriate model listeners.
	*
	* @param syncQueue the sync queue
	* @return the sync queue that was removed
	*/
	public static org.opencps.synchronization.model.SyncQueue deleteSyncQueue(
		org.opencps.synchronization.model.SyncQueue syncQueue) {
		return getService().deleteSyncQueue(syncQueue);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.synchronization.model.impl.SyncQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.synchronization.model.impl.SyncQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.synchronization.model.SyncQueue fetchSyncQueue(
		long syncQueueId) {
		return getService().fetchSyncQueue(syncQueueId);
	}

	/**
	* Returns the sync queue matching the UUID and group.
	*
	* @param uuid the sync queue's UUID
	* @param groupId the primary key of the group
	* @return the matching sync queue, or <code>null</code> if a matching sync queue could not be found
	*/
	public static org.opencps.synchronization.model.SyncQueue fetchSyncQueueByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchSyncQueueByUuidAndGroupId(uuid, groupId);
	}

	public static java.util.List<org.opencps.synchronization.model.SyncQueue> findAll(
		int start, int end) {
		return getService().findAll(start, end);
	}

	public static java.util.List<org.opencps.synchronization.model.SyncQueue> findByF_className_Method(
		long groupId, String className, String method, int start, int end) {
		return getService()
				   .findByF_className_Method(groupId, className, method, start,
			end);
	}

	public static java.util.List<org.opencps.synchronization.model.SyncQueue> findByF_groupId_serverNo(
		long groupId, String serverNo, int start, int end) {
		return getService()
				   .findByF_groupId_serverNo(groupId, serverNo, start, end);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
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
	* Returns the sync queue with the primary key.
	*
	* @param syncQueueId the primary key of the sync queue
	* @return the sync queue
	* @throws PortalException if a sync queue with the primary key could not be found
	*/
	public static org.opencps.synchronization.model.SyncQueue getSyncQueue(
		long syncQueueId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getSyncQueue(syncQueueId);
	}

	/**
	* Returns the sync queue matching the UUID and group.
	*
	* @param uuid the sync queue's UUID
	* @param groupId the primary key of the group
	* @return the matching sync queue
	* @throws PortalException if a matching sync queue could not be found
	*/
	public static org.opencps.synchronization.model.SyncQueue getSyncQueueByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getSyncQueueByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the sync queues.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.synchronization.model.impl.SyncQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sync queues
	* @param end the upper bound of the range of sync queues (not inclusive)
	* @return the range of sync queues
	*/
	public static java.util.List<org.opencps.synchronization.model.SyncQueue> getSyncQueues(
		int start, int end) {
		return getService().getSyncQueues(start, end);
	}

	/**
	* Returns all the sync queues matching the UUID and company.
	*
	* @param uuid the UUID of the sync queues
	* @param companyId the primary key of the company
	* @return the matching sync queues, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.synchronization.model.SyncQueue> getSyncQueuesByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService().getSyncQueuesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of sync queues matching the UUID and company.
	*
	* @param uuid the UUID of the sync queues
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of sync queues
	* @param end the upper bound of the range of sync queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching sync queues, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.synchronization.model.SyncQueue> getSyncQueuesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.synchronization.model.SyncQueue> orderByComparator) {
		return getService()
				   .getSyncQueuesByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of sync queues.
	*
	* @return the number of sync queues
	*/
	public static int getSyncQueuesCount() {
		return getService().getSyncQueuesCount();
	}

	public static org.opencps.synchronization.model.SyncQueue updateSyncQueue(
		long userId, long groupId, long syncQueueId, String serverNo,
		String className, String jsonObject, int status, int retryCount,
		int priority, String method,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			com.liferay.portal.kernel.exception.NoSuchUserException,
			org.opencps.synchronization.exception.NoSuchSyncQueueException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateSyncQueue(userId, groupId, syncQueueId, serverNo,
			className, jsonObject, status, retryCount, priority, method,
			serviceContext);
	}

	/**
	* Updates the sync queue in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param syncQueue the sync queue
	* @return the sync queue that was updated
	*/
	public static org.opencps.synchronization.model.SyncQueue updateSyncQueue(
		org.opencps.synchronization.model.SyncQueue syncQueue) {
		return getService().updateSyncQueue(syncQueue);
	}

	public static SyncQueueLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<SyncQueueLocalService, SyncQueueLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(SyncQueueLocalService.class);

		ServiceTracker<SyncQueueLocalService, SyncQueueLocalService> serviceTracker =
			new ServiceTracker<SyncQueueLocalService, SyncQueueLocalService>(bundle.getBundleContext(),
				SyncQueueLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}