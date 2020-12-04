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

package org.opencps.synctracking.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SyncTrackingLocalService}.
 *
 * @author giaonn
 * @see SyncTrackingLocalService
 * @generated
 */
@ProviderType
public class SyncTrackingLocalServiceWrapper implements SyncTrackingLocalService,
	ServiceWrapper<SyncTrackingLocalService> {
	public SyncTrackingLocalServiceWrapper(
		SyncTrackingLocalService syncTrackingLocalService) {
		_syncTrackingLocalService = syncTrackingLocalService;
	}

	/**
	* Adds the sync tracking to the database. Also notifies the appropriate model listeners.
	*
	* @param syncTracking the sync tracking
	* @return the sync tracking that was added
	*/
	@Override
	public org.opencps.synctracking.model.SyncTracking addSyncTracking(
		org.opencps.synctracking.model.SyncTracking syncTracking) {
		return _syncTrackingLocalService.addSyncTracking(syncTracking);
	}

	/**
	* Creates a new sync tracking with the primary key. Does not add the sync tracking to the database.
	*
	* @param trackingId the primary key for the new sync tracking
	* @return the new sync tracking
	*/
	@Override
	public org.opencps.synctracking.model.SyncTracking createSyncTracking(
		long trackingId) {
		return _syncTrackingLocalService.createSyncTracking(trackingId);
	}

	@Override
	public org.opencps.synctracking.model.SyncTracking createSyncTrackingManual(
		org.opencps.synctracking.model.SyncTrackingQuery syncTrackingQuery) {
		return _syncTrackingLocalService.createSyncTrackingManual(syncTrackingQuery);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _syncTrackingLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the sync tracking with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trackingId the primary key of the sync tracking
	* @return the sync tracking that was removed
	* @throws PortalException if a sync tracking with the primary key could not be found
	*/
	@Override
	public org.opencps.synctracking.model.SyncTracking deleteSyncTracking(
		long trackingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _syncTrackingLocalService.deleteSyncTracking(trackingId);
	}

	/**
	* Deletes the sync tracking from the database. Also notifies the appropriate model listeners.
	*
	* @param syncTracking the sync tracking
	* @return the sync tracking that was removed
	*/
	@Override
	public org.opencps.synctracking.model.SyncTracking deleteSyncTracking(
		org.opencps.synctracking.model.SyncTracking syncTracking) {
		return _syncTrackingLocalService.deleteSyncTracking(syncTracking);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _syncTrackingLocalService.dynamicQuery();
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
		return _syncTrackingLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.synctracking.model.impl.SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _syncTrackingLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.synctracking.model.impl.SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _syncTrackingLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _syncTrackingLocalService.dynamicQueryCount(dynamicQuery);
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
		return _syncTrackingLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.synctracking.model.SyncTracking fetchSyncTracking(
		long trackingId) {
		return _syncTrackingLocalService.fetchSyncTracking(trackingId);
	}

	/**
	* Returns the sync tracking matching the UUID and group.
	*
	* @param uuid the sync tracking's UUID
	* @param groupId the primary key of the group
	* @return the matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	@Override
	public org.opencps.synctracking.model.SyncTracking fetchSyncTrackingByUuidAndGroupId(
		String uuid, long groupId) {
		return _syncTrackingLocalService.fetchSyncTrackingByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _syncTrackingLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<org.opencps.synctracking.model.SyncTracking> getByGroupId(
		long groupId, int start, int end) {
		return _syncTrackingLocalService.getByGroupId(groupId, start, end);
	}

	@Override
	public java.util.List<org.opencps.synctracking.model.SyncTracking> getByGroupIdAndDate(
		long groupId, java.util.Date fromDate, java.util.Date toDate,
		int start, int end) {
		return _syncTrackingLocalService.getByGroupIdAndDate(groupId, fromDate,
			toDate, start, end);
	}

	@Override
	public java.util.List<org.opencps.synctracking.model.SyncTracking> getByGroupIdAndDossierNoAndDate(
		long groupId, String dossierNo, java.util.Date fromDate,
		java.util.Date toDate, int start, int end) {
		return _syncTrackingLocalService.getByGroupIdAndDossierNoAndDate(groupId,
			dossierNo, fromDate, toDate, start, end);
	}

	@Override
	public java.util.List<org.opencps.synctracking.model.SyncTracking> getByGroupIdAndDossierNoAndServiceCodeAndDate(
		long groupId, String dossierNo, String serviceCode,
		java.util.Date fromDate, java.util.Date toDate, int start, int end) {
		return _syncTrackingLocalService.getByGroupIdAndDossierNoAndServiceCodeAndDate(groupId,
			dossierNo, serviceCode, fromDate, toDate, start, end);
	}

	@Override
	public java.util.List<org.opencps.synctracking.model.SyncTracking> getByGroupIdAndServiceCodeAndDate(
		long groupId, String serviceCode, java.util.Date fromDate,
		java.util.Date toDate, int start, int end) {
		return _syncTrackingLocalService.getByGroupIdAndServiceCodeAndDate(groupId,
			serviceCode, fromDate, toDate, start, end);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _syncTrackingLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _syncTrackingLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _syncTrackingLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _syncTrackingLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the sync tracking with the primary key.
	*
	* @param trackingId the primary key of the sync tracking
	* @return the sync tracking
	* @throws PortalException if a sync tracking with the primary key could not be found
	*/
	@Override
	public org.opencps.synctracking.model.SyncTracking getSyncTracking(
		long trackingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _syncTrackingLocalService.getSyncTracking(trackingId);
	}

	/**
	* Returns the sync tracking matching the UUID and group.
	*
	* @param uuid the sync tracking's UUID
	* @param groupId the primary key of the group
	* @return the matching sync tracking
	* @throws PortalException if a matching sync tracking could not be found
	*/
	@Override
	public org.opencps.synctracking.model.SyncTracking getSyncTrackingByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _syncTrackingLocalService.getSyncTrackingByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the sync trackings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.synctracking.model.impl.SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @return the range of sync trackings
	*/
	@Override
	public java.util.List<org.opencps.synctracking.model.SyncTracking> getSyncTrackings(
		int start, int end) {
		return _syncTrackingLocalService.getSyncTrackings(start, end);
	}

	/**
	* Returns all the sync trackings matching the UUID and company.
	*
	* @param uuid the UUID of the sync trackings
	* @param companyId the primary key of the company
	* @return the matching sync trackings, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.synctracking.model.SyncTracking> getSyncTrackingsByUuidAndCompanyId(
		String uuid, long companyId) {
		return _syncTrackingLocalService.getSyncTrackingsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of sync trackings matching the UUID and company.
	*
	* @param uuid the UUID of the sync trackings
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching sync trackings, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.synctracking.model.SyncTracking> getSyncTrackingsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.synctracking.model.SyncTracking> orderByComparator) {
		return _syncTrackingLocalService.getSyncTrackingsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of sync trackings.
	*
	* @return the number of sync trackings
	*/
	@Override
	public int getSyncTrackingsCount() {
		return _syncTrackingLocalService.getSyncTrackingsCount();
	}

	/**
	* Updates the sync tracking in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param syncTracking the sync tracking
	* @return the sync tracking that was updated
	*/
	@Override
	public org.opencps.synctracking.model.SyncTracking updateSyncTracking(
		org.opencps.synctracking.model.SyncTracking syncTracking) {
		return _syncTrackingLocalService.updateSyncTracking(syncTracking);
	}

	@Override
	public SyncTrackingLocalService getWrappedService() {
		return _syncTrackingLocalService;
	}

	@Override
	public void setWrappedService(
		SyncTrackingLocalService syncTrackingLocalService) {
		_syncTrackingLocalService = syncTrackingLocalService;
	}

	private SyncTrackingLocalService _syncTrackingLocalService;
}