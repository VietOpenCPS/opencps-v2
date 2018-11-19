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

package org.opencps.sms.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SMSLookUpQueueLocalService}.
 *
 * @author khoa
 * @see SMSLookUpQueueLocalService
 * @generated
 */
@ProviderType
public class SMSLookUpQueueLocalServiceWrapper
	implements SMSLookUpQueueLocalService,
		ServiceWrapper<SMSLookUpQueueLocalService> {
	public SMSLookUpQueueLocalServiceWrapper(
		SMSLookUpQueueLocalService smsLookUpQueueLocalService) {
		_smsLookUpQueueLocalService = smsLookUpQueueLocalService;
	}

	/**
	* Adds the sms look up queue to the database. Also notifies the appropriate model listeners.
	*
	* @param smsLookUpQueue the sms look up queue
	* @return the sms look up queue that was added
	*/
	@Override
	public org.opencps.sms.model.SMSLookUpQueue addSMSLookUpQueue(
		org.opencps.sms.model.SMSLookUpQueue smsLookUpQueue) {
		return _smsLookUpQueueLocalService.addSMSLookUpQueue(smsLookUpQueue);
	}

	/**
	* Creates a new sms look up queue with the primary key. Does not add the sms look up queue to the database.
	*
	* @param queueId the primary key for the new sms look up queue
	* @return the new sms look up queue
	*/
	@Override
	public org.opencps.sms.model.SMSLookUpQueue createSMSLookUpQueue(
		long queueId) {
		return _smsLookUpQueueLocalService.createSMSLookUpQueue(queueId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _smsLookUpQueueLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the sms look up queue with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param queueId the primary key of the sms look up queue
	* @return the sms look up queue that was removed
	* @throws PortalException if a sms look up queue with the primary key could not be found
	*/
	@Override
	public org.opencps.sms.model.SMSLookUpQueue deleteSMSLookUpQueue(
		long queueId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _smsLookUpQueueLocalService.deleteSMSLookUpQueue(queueId);
	}

	/**
	* Deletes the sms look up queue from the database. Also notifies the appropriate model listeners.
	*
	* @param smsLookUpQueue the sms look up queue
	* @return the sms look up queue that was removed
	*/
	@Override
	public org.opencps.sms.model.SMSLookUpQueue deleteSMSLookUpQueue(
		org.opencps.sms.model.SMSLookUpQueue smsLookUpQueue) {
		return _smsLookUpQueueLocalService.deleteSMSLookUpQueue(smsLookUpQueue);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _smsLookUpQueueLocalService.dynamicQuery();
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
		return _smsLookUpQueueLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.sms.model.impl.SMSLookUpQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _smsLookUpQueueLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.sms.model.impl.SMSLookUpQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _smsLookUpQueueLocalService.dynamicQuery(dynamicQuery, start,
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
		return _smsLookUpQueueLocalService.dynamicQueryCount(dynamicQuery);
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
		return _smsLookUpQueueLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.sms.model.SMSLookUpQueue fetchSMSLookUpQueue(
		long queueId) {
		return _smsLookUpQueueLocalService.fetchSMSLookUpQueue(queueId);
	}

	/**
	* Returns the sms look up queue matching the UUID and group.
	*
	* @param uuid the sms look up queue's UUID
	* @param groupId the primary key of the group
	* @return the matching sms look up queue, or <code>null</code> if a matching sms look up queue could not be found
	*/
	@Override
	public org.opencps.sms.model.SMSLookUpQueue fetchSMSLookUpQueueByUuidAndGroupId(
		String uuid, long groupId) {
		return _smsLookUpQueueLocalService.fetchSMSLookUpQueueByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _smsLookUpQueueLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _smsLookUpQueueLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _smsLookUpQueueLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _smsLookUpQueueLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _smsLookUpQueueLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the sms look up queue with the primary key.
	*
	* @param queueId the primary key of the sms look up queue
	* @return the sms look up queue
	* @throws PortalException if a sms look up queue with the primary key could not be found
	*/
	@Override
	public org.opencps.sms.model.SMSLookUpQueue getSMSLookUpQueue(long queueId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _smsLookUpQueueLocalService.getSMSLookUpQueue(queueId);
	}

	/**
	* Returns the sms look up queue matching the UUID and group.
	*
	* @param uuid the sms look up queue's UUID
	* @param groupId the primary key of the group
	* @return the matching sms look up queue
	* @throws PortalException if a matching sms look up queue could not be found
	*/
	@Override
	public org.opencps.sms.model.SMSLookUpQueue getSMSLookUpQueueByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _smsLookUpQueueLocalService.getSMSLookUpQueueByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the sms look up queues.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.sms.model.impl.SMSLookUpQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sms look up queues
	* @param end the upper bound of the range of sms look up queues (not inclusive)
	* @return the range of sms look up queues
	*/
	@Override
	public java.util.List<org.opencps.sms.model.SMSLookUpQueue> getSMSLookUpQueues(
		int start, int end) {
		return _smsLookUpQueueLocalService.getSMSLookUpQueues(start, end);
	}

	/**
	* Returns all the sms look up queues matching the UUID and company.
	*
	* @param uuid the UUID of the sms look up queues
	* @param companyId the primary key of the company
	* @return the matching sms look up queues, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.sms.model.SMSLookUpQueue> getSMSLookUpQueuesByUuidAndCompanyId(
		String uuid, long companyId) {
		return _smsLookUpQueueLocalService.getSMSLookUpQueuesByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of sms look up queues matching the UUID and company.
	*
	* @param uuid the UUID of the sms look up queues
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of sms look up queues
	* @param end the upper bound of the range of sms look up queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching sms look up queues, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.sms.model.SMSLookUpQueue> getSMSLookUpQueuesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.sms.model.SMSLookUpQueue> orderByComparator) {
		return _smsLookUpQueueLocalService.getSMSLookUpQueuesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of sms look up queues.
	*
	* @return the number of sms look up queues
	*/
	@Override
	public int getSMSLookUpQueuesCount() {
		return _smsLookUpQueueLocalService.getSMSLookUpQueuesCount();
	}

	/**
	* Updates the sms look up queue in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param smsLookUpQueue the sms look up queue
	* @return the sms look up queue that was updated
	*/
	@Override
	public org.opencps.sms.model.SMSLookUpQueue updateSMSLookUpQueue(
		org.opencps.sms.model.SMSLookUpQueue smsLookUpQueue) {
		return _smsLookUpQueueLocalService.updateSMSLookUpQueue(smsLookUpQueue);
	}

	@Override
	public SMSLookUpQueueLocalService getWrappedService() {
		return _smsLookUpQueueLocalService;
	}

	@Override
	public void setWrappedService(
		SMSLookUpQueueLocalService smsLookUpQueueLocalService) {
		_smsLookUpQueueLocalService = smsLookUpQueueLocalService;
	}

	private SMSLookUpQueueLocalService _smsLookUpQueueLocalService;
}