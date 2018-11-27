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

package org.opencps.deliverable.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link OpenCPSDeliverableLogLocalService}.
 *
 * @author binhth
 * @see OpenCPSDeliverableLogLocalService
 * @generated
 */
@ProviderType
public class OpenCPSDeliverableLogLocalServiceWrapper
	implements OpenCPSDeliverableLogLocalService,
		ServiceWrapper<OpenCPSDeliverableLogLocalService> {
	public OpenCPSDeliverableLogLocalServiceWrapper(
		OpenCPSDeliverableLogLocalService openCPSDeliverableLogLocalService) {
		_openCPSDeliverableLogLocalService = openCPSDeliverableLogLocalService;
	}

	/**
	* Adds the open cps deliverable log to the database. Also notifies the appropriate model listeners.
	*
	* @param openCPSDeliverableLog the open cps deliverable log
	* @return the open cps deliverable log that was added
	*/
	@Override
	public org.opencps.deliverable.model.OpenCPSDeliverableLog addOpenCPSDeliverableLog(
		org.opencps.deliverable.model.OpenCPSDeliverableLog openCPSDeliverableLog) {
		return _openCPSDeliverableLogLocalService.addOpenCPSDeliverableLog(openCPSDeliverableLog);
	}

	@Override
	public org.opencps.deliverable.model.OpenCPSDeliverableLog adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return _openCPSDeliverableLogLocalService.adminProcessData(objectData);
	}

	@Override
	public org.opencps.deliverable.model.OpenCPSDeliverableLog adminProcessDelete(
		Long id) {
		return _openCPSDeliverableLogLocalService.adminProcessDelete(id);
	}

	/**
	* Creates a new open cps deliverable log with the primary key. Does not add the open cps deliverable log to the database.
	*
	* @param deliverableLogId the primary key for the new open cps deliverable log
	* @return the new open cps deliverable log
	*/
	@Override
	public org.opencps.deliverable.model.OpenCPSDeliverableLog createOpenCPSDeliverableLog(
		long deliverableLogId) {
		return _openCPSDeliverableLogLocalService.createOpenCPSDeliverableLog(deliverableLogId);
	}

	/**
	* Deletes the open cps deliverable log with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param deliverableLogId the primary key of the open cps deliverable log
	* @return the open cps deliverable log that was removed
	* @throws PortalException if a open cps deliverable log with the primary key could not be found
	*/
	@Override
	public org.opencps.deliverable.model.OpenCPSDeliverableLog deleteOpenCPSDeliverableLog(
		long deliverableLogId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _openCPSDeliverableLogLocalService.deleteOpenCPSDeliverableLog(deliverableLogId);
	}

	/**
	* Deletes the open cps deliverable log from the database. Also notifies the appropriate model listeners.
	*
	* @param openCPSDeliverableLog the open cps deliverable log
	* @return the open cps deliverable log that was removed
	*/
	@Override
	public org.opencps.deliverable.model.OpenCPSDeliverableLog deleteOpenCPSDeliverableLog(
		org.opencps.deliverable.model.OpenCPSDeliverableLog openCPSDeliverableLog) {
		return _openCPSDeliverableLogLocalService.deleteOpenCPSDeliverableLog(openCPSDeliverableLog);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _openCPSDeliverableLogLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _openCPSDeliverableLogLocalService.dynamicQuery();
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
		return _openCPSDeliverableLogLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.deliverable.model.impl.OpenCPSDeliverableLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _openCPSDeliverableLogLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.deliverable.model.impl.OpenCPSDeliverableLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _openCPSDeliverableLogLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
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
		return _openCPSDeliverableLogLocalService.dynamicQueryCount(dynamicQuery);
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
		return _openCPSDeliverableLogLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.deliverable.model.OpenCPSDeliverableLog fetchOpenCPSDeliverableLog(
		long deliverableLogId) {
		return _openCPSDeliverableLogLocalService.fetchOpenCPSDeliverableLog(deliverableLogId);
	}

	/**
	* Returns the open cps deliverable log matching the UUID and group.
	*
	* @param uuid the open cps deliverable log's UUID
	* @param groupId the primary key of the group
	* @return the matching open cps deliverable log, or <code>null</code> if a matching open cps deliverable log could not be found
	*/
	@Override
	public org.opencps.deliverable.model.OpenCPSDeliverableLog fetchOpenCPSDeliverableLogByUuidAndGroupId(
		String uuid, long groupId) {
		return _openCPSDeliverableLogLocalService.fetchOpenCPSDeliverableLogByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public java.util.List<org.opencps.deliverable.model.OpenCPSDeliverableLog> findByF_deliverableId(
		long deliverableId, int start, int end) {
		return _openCPSDeliverableLogLocalService.findByF_deliverableId(deliverableId,
			start, end);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _openCPSDeliverableLogLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _openCPSDeliverableLogLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _openCPSDeliverableLogLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the open cps deliverable log with the primary key.
	*
	* @param deliverableLogId the primary key of the open cps deliverable log
	* @return the open cps deliverable log
	* @throws PortalException if a open cps deliverable log with the primary key could not be found
	*/
	@Override
	public org.opencps.deliverable.model.OpenCPSDeliverableLog getOpenCPSDeliverableLog(
		long deliverableLogId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _openCPSDeliverableLogLocalService.getOpenCPSDeliverableLog(deliverableLogId);
	}

	/**
	* Returns the open cps deliverable log matching the UUID and group.
	*
	* @param uuid the open cps deliverable log's UUID
	* @param groupId the primary key of the group
	* @return the matching open cps deliverable log
	* @throws PortalException if a matching open cps deliverable log could not be found
	*/
	@Override
	public org.opencps.deliverable.model.OpenCPSDeliverableLog getOpenCPSDeliverableLogByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _openCPSDeliverableLogLocalService.getOpenCPSDeliverableLogByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the open cps deliverable logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.deliverable.model.impl.OpenCPSDeliverableLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of open cps deliverable logs
	* @param end the upper bound of the range of open cps deliverable logs (not inclusive)
	* @return the range of open cps deliverable logs
	*/
	@Override
	public java.util.List<org.opencps.deliverable.model.OpenCPSDeliverableLog> getOpenCPSDeliverableLogs(
		int start, int end) {
		return _openCPSDeliverableLogLocalService.getOpenCPSDeliverableLogs(start,
			end);
	}

	/**
	* Returns all the open cps deliverable logs matching the UUID and company.
	*
	* @param uuid the UUID of the open cps deliverable logs
	* @param companyId the primary key of the company
	* @return the matching open cps deliverable logs, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.deliverable.model.OpenCPSDeliverableLog> getOpenCPSDeliverableLogsByUuidAndCompanyId(
		String uuid, long companyId) {
		return _openCPSDeliverableLogLocalService.getOpenCPSDeliverableLogsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of open cps deliverable logs matching the UUID and company.
	*
	* @param uuid the UUID of the open cps deliverable logs
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of open cps deliverable logs
	* @param end the upper bound of the range of open cps deliverable logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching open cps deliverable logs, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.deliverable.model.OpenCPSDeliverableLog> getOpenCPSDeliverableLogsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.deliverable.model.OpenCPSDeliverableLog> orderByComparator) {
		return _openCPSDeliverableLogLocalService.getOpenCPSDeliverableLogsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of open cps deliverable logs.
	*
	* @return the number of open cps deliverable logs
	*/
	@Override
	public int getOpenCPSDeliverableLogsCount() {
		return _openCPSDeliverableLogLocalService.getOpenCPSDeliverableLogsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _openCPSDeliverableLogLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _openCPSDeliverableLogLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the open cps deliverable log in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param openCPSDeliverableLog the open cps deliverable log
	* @return the open cps deliverable log that was updated
	*/
	@Override
	public org.opencps.deliverable.model.OpenCPSDeliverableLog updateOpenCPSDeliverableLog(
		org.opencps.deliverable.model.OpenCPSDeliverableLog openCPSDeliverableLog) {
		return _openCPSDeliverableLogLocalService.updateOpenCPSDeliverableLog(openCPSDeliverableLog);
	}

	@Override
	public OpenCPSDeliverableLogLocalService getWrappedService() {
		return _openCPSDeliverableLogLocalService;
	}

	@Override
	public void setWrappedService(
		OpenCPSDeliverableLogLocalService openCPSDeliverableLogLocalService) {
		_openCPSDeliverableLogLocalService = openCPSDeliverableLogLocalService;
	}

	private OpenCPSDeliverableLogLocalService _openCPSDeliverableLogLocalService;
}