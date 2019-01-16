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

package org.opencps.dossiermgt.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PublishQueueLocalService}.
 *
 * @author huymq
 * @see PublishQueueLocalService
 * @generated
 */
@ProviderType
public class PublishQueueLocalServiceWrapper implements PublishQueueLocalService,
	ServiceWrapper<PublishQueueLocalService> {
	public PublishQueueLocalServiceWrapper(
		PublishQueueLocalService publishQueueLocalService) {
		_publishQueueLocalService = publishQueueLocalService;
	}

	/**
	* Adds the publish queue to the database. Also notifies the appropriate model listeners.
	*
	* @param publishQueue the publish queue
	* @return the publish queue that was added
	*/
	@Override
	public org.opencps.dossiermgt.model.PublishQueue addPublishQueue(
		org.opencps.dossiermgt.model.PublishQueue publishQueue) {
		return _publishQueueLocalService.addPublishQueue(publishQueue);
	}

	/**
	* Creates a new publish queue with the primary key. Does not add the publish queue to the database.
	*
	* @param publishQueueId the primary key for the new publish queue
	* @return the new publish queue
	*/
	@Override
	public org.opencps.dossiermgt.model.PublishQueue createPublishQueue(
		long publishQueueId) {
		return _publishQueueLocalService.createPublishQueue(publishQueueId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _publishQueueLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the publish queue with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param publishQueueId the primary key of the publish queue
	* @return the publish queue that was removed
	* @throws PortalException if a publish queue with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.PublishQueue deletePublishQueue(
		long publishQueueId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _publishQueueLocalService.deletePublishQueue(publishQueueId);
	}

	/**
	* Deletes the publish queue from the database. Also notifies the appropriate model listeners.
	*
	* @param publishQueue the publish queue
	* @return the publish queue that was removed
	*/
	@Override
	public org.opencps.dossiermgt.model.PublishQueue deletePublishQueue(
		org.opencps.dossiermgt.model.PublishQueue publishQueue) {
		return _publishQueueLocalService.deletePublishQueue(publishQueue);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _publishQueueLocalService.dynamicQuery();
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
		return _publishQueueLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _publishQueueLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _publishQueueLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
		return _publishQueueLocalService.dynamicQueryCount(dynamicQuery);
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
		return _publishQueueLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.dossiermgt.model.PublishQueue fetchPublishQueue(
		long publishQueueId) {
		return _publishQueueLocalService.fetchPublishQueue(publishQueueId);
	}

	/**
	* Returns the publish queue matching the UUID and group.
	*
	* @param uuid the publish queue's UUID
	* @param groupId the primary key of the group
	* @return the matching publish queue, or <code>null</code> if a matching publish queue could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.PublishQueue fetchPublishQueueByUuidAndGroupId(
		String uuid, long groupId) {
		return _publishQueueLocalService.fetchPublishQueueByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.PublishQueue> findByST_LT_MD(
		int[] statuses, java.util.Date d, int start, int end) {
		return _publishQueueLocalService.findByST_LT_MD(statuses, d, start, end);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _publishQueueLocalService.getActionableDynamicQuery();
	}

	@Override
	public org.opencps.dossiermgt.model.PublishQueue getByG_DID_SN(
		long groupId, long dossierId, String serverNo) {
		return _publishQueueLocalService.getByG_DID_SN(groupId, dossierId,
			serverNo);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.PublishQueue> getByG_DID_SN_NST(
		long groupId, long dossierId, String serverNo, int status) {
		return _publishQueueLocalService.getByG_DID_SN_NST(groupId, dossierId,
			serverNo, status);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.PublishQueue> getByG_DID_SN_ST(
		long groupId, long dossierId, String serverNo, int[] status) {
		return _publishQueueLocalService.getByG_DID_SN_ST(groupId, dossierId,
			serverNo, status);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.PublishQueue> getByStatus(
		int status, int start, int end) {
		return _publishQueueLocalService.getByStatus(status, start, end);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _publishQueueLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _publishQueueLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _publishQueueLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the publish queue with the primary key.
	*
	* @param publishQueueId the primary key of the publish queue
	* @return the publish queue
	* @throws PortalException if a publish queue with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.PublishQueue getPublishQueue(
		long publishQueueId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _publishQueueLocalService.getPublishQueue(publishQueueId);
	}

	/**
	* Returns the publish queue matching the UUID and group.
	*
	* @param uuid the publish queue's UUID
	* @param groupId the primary key of the group
	* @return the matching publish queue
	* @throws PortalException if a matching publish queue could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.PublishQueue getPublishQueueByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _publishQueueLocalService.getPublishQueueByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the publish queues.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @return the range of publish queues
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.PublishQueue> getPublishQueues(
		int start, int end) {
		return _publishQueueLocalService.getPublishQueues(start, end);
	}

	/**
	* Returns the number of publish queues.
	*
	* @return the number of publish queues
	*/
	@Override
	public int getPublishQueuesCount() {
		return _publishQueueLocalService.getPublishQueuesCount();
	}

	@Override
	public org.opencps.dossiermgt.model.PublishQueue removePublishQueue(
		long publishQueueId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _publishQueueLocalService.removePublishQueue(publishQueueId);
	}

	@Override
	public org.opencps.dossiermgt.model.PublishQueue updatePublishQueue(
		long groupId, long publishQueueId, int publishType, long dossierId,
		String serverNo, String publishData, int status, int retry,
		String messageText, String acknowlegement,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _publishQueueLocalService.updatePublishQueue(groupId,
			publishQueueId, publishType, dossierId, serverNo, publishData,
			status, retry, messageText, acknowlegement, context);
	}

	@Override
	public org.opencps.dossiermgt.model.PublishQueue updatePublishQueue(
		long groupId, long publishQueueId, long dossierId, String serverNo,
		int status, int retry,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _publishQueueLocalService.updatePublishQueue(groupId,
			publishQueueId, dossierId, serverNo, status, retry, context);
	}

	/**
	* Updates the publish queue in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param publishQueue the publish queue
	* @return the publish queue that was updated
	*/
	@Override
	public org.opencps.dossiermgt.model.PublishQueue updatePublishQueue(
		org.opencps.dossiermgt.model.PublishQueue publishQueue) {
		return _publishQueueLocalService.updatePublishQueue(publishQueue);
	}

	@Override
	public PublishQueueLocalService getWrappedService() {
		return _publishQueueLocalService;
	}

	@Override
	public void setWrappedService(
		PublishQueueLocalService publishQueueLocalService) {
		_publishQueueLocalService = publishQueueLocalService;
	}

	private PublishQueueLocalService _publishQueueLocalService;
}