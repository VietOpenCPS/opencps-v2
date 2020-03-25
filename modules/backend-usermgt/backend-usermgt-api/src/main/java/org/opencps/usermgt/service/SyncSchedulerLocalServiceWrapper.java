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

package org.opencps.usermgt.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SyncSchedulerLocalService}.
 *
 * @author khoavu
 * @see SyncSchedulerLocalService
 * @generated
 */
@ProviderType
public class SyncSchedulerLocalServiceWrapper
	implements SyncSchedulerLocalService,
		ServiceWrapper<SyncSchedulerLocalService> {
	public SyncSchedulerLocalServiceWrapper(
		SyncSchedulerLocalService syncSchedulerLocalService) {
		_syncSchedulerLocalService = syncSchedulerLocalService;
	}

	/**
	* Adds the sync scheduler to the database. Also notifies the appropriate model listeners.
	*
	* @param syncScheduler the sync scheduler
	* @return the sync scheduler that was added
	*/
	@Override
	public org.opencps.usermgt.model.SyncScheduler addSyncScheduler(
		org.opencps.usermgt.model.SyncScheduler syncScheduler) {
		return _syncSchedulerLocalService.addSyncScheduler(syncScheduler);
	}

	/**
	* Creates a new sync scheduler with the primary key. Does not add the sync scheduler to the database.
	*
	* @param syncSchedulerId the primary key for the new sync scheduler
	* @return the new sync scheduler
	*/
	@Override
	public org.opencps.usermgt.model.SyncScheduler createSyncScheduler(
		long syncSchedulerId) {
		return _syncSchedulerLocalService.createSyncScheduler(syncSchedulerId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _syncSchedulerLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the sync scheduler with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param syncSchedulerId the primary key of the sync scheduler
	* @return the sync scheduler that was removed
	* @throws PortalException if a sync scheduler with the primary key could not be found
	*/
	@Override
	public org.opencps.usermgt.model.SyncScheduler deleteSyncScheduler(
		long syncSchedulerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _syncSchedulerLocalService.deleteSyncScheduler(syncSchedulerId);
	}

	/**
	* Deletes the sync scheduler from the database. Also notifies the appropriate model listeners.
	*
	* @param syncScheduler the sync scheduler
	* @return the sync scheduler that was removed
	*/
	@Override
	public org.opencps.usermgt.model.SyncScheduler deleteSyncScheduler(
		org.opencps.usermgt.model.SyncScheduler syncScheduler) {
		return _syncSchedulerLocalService.deleteSyncScheduler(syncScheduler);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _syncSchedulerLocalService.dynamicQuery();
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
		return _syncSchedulerLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.SyncSchedulerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _syncSchedulerLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.SyncSchedulerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _syncSchedulerLocalService.dynamicQuery(dynamicQuery, start,
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
		return _syncSchedulerLocalService.dynamicQueryCount(dynamicQuery);
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
		return _syncSchedulerLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.usermgt.model.SyncScheduler fetchSyncScheduler(
		long syncSchedulerId) {
		return _syncSchedulerLocalService.fetchSyncScheduler(syncSchedulerId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _syncSchedulerLocalService.getActionableDynamicQuery();
	}

	@Override
	public org.opencps.usermgt.model.SyncScheduler getByClassNameAndSyncDate(
		String className, java.util.Date syncDate) {
		return _syncSchedulerLocalService.getByClassNameAndSyncDate(className,
			syncDate);
	}

	@Override
	public org.opencps.usermgt.model.SyncScheduler getByClassNameAndTypeCode(
		String className, String typeCode) {
		return _syncSchedulerLocalService.getByClassNameAndTypeCode(className,
			typeCode);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _syncSchedulerLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _syncSchedulerLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _syncSchedulerLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the sync scheduler with the primary key.
	*
	* @param syncSchedulerId the primary key of the sync scheduler
	* @return the sync scheduler
	* @throws PortalException if a sync scheduler with the primary key could not be found
	*/
	@Override
	public org.opencps.usermgt.model.SyncScheduler getSyncScheduler(
		long syncSchedulerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _syncSchedulerLocalService.getSyncScheduler(syncSchedulerId);
	}

	/**
	* Returns a range of all the sync schedulers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.SyncSchedulerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sync schedulers
	* @param end the upper bound of the range of sync schedulers (not inclusive)
	* @return the range of sync schedulers
	*/
	@Override
	public java.util.List<org.opencps.usermgt.model.SyncScheduler> getSyncSchedulers(
		int start, int end) {
		return _syncSchedulerLocalService.getSyncSchedulers(start, end);
	}

	/**
	* Returns the number of sync schedulers.
	*
	* @return the number of sync schedulers
	*/
	@Override
	public int getSyncSchedulersCount() {
		return _syncSchedulerLocalService.getSyncSchedulersCount();
	}

	/**
	* Updates the sync scheduler in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param syncScheduler the sync scheduler
	* @return the sync scheduler that was updated
	*/
	@Override
	public org.opencps.usermgt.model.SyncScheduler updateSyncScheduler(
		org.opencps.usermgt.model.SyncScheduler syncScheduler) {
		return _syncSchedulerLocalService.updateSyncScheduler(syncScheduler);
	}

	@Override
	public SyncSchedulerLocalService getWrappedService() {
		return _syncSchedulerLocalService;
	}

	@Override
	public void setWrappedService(
		SyncSchedulerLocalService syncSchedulerLocalService) {
		_syncSchedulerLocalService = syncSchedulerLocalService;
	}

	private SyncSchedulerLocalService _syncSchedulerLocalService;
}