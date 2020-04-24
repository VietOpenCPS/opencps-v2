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
 * Provides a wrapper for {@link TrackClientLocalService}.
 *
 * @author khoavu
 * @see TrackClientLocalService
 * @generated
 */
@ProviderType
public class TrackClientLocalServiceWrapper implements TrackClientLocalService,
	ServiceWrapper<TrackClientLocalService> {
	public TrackClientLocalServiceWrapper(
		TrackClientLocalService trackClientLocalService) {
		_trackClientLocalService = trackClientLocalService;
	}

	/**
	* Adds the track client to the database. Also notifies the appropriate model listeners.
	*
	* @param trackClient the track client
	* @return the track client that was added
	*/
	@Override
	public org.opencps.usermgt.model.TrackClient addTrackClient(
		org.opencps.usermgt.model.TrackClient trackClient) {
		return _trackClientLocalService.addTrackClient(trackClient);
	}

	/**
	* Creates a new track client with the primary key. Does not add the track client to the database.
	*
	* @param trackClientId the primary key for the new track client
	* @return the new track client
	*/
	@Override
	public org.opencps.usermgt.model.TrackClient createTrackClient(
		long trackClientId) {
		return _trackClientLocalService.createTrackClient(trackClientId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _trackClientLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the track client with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trackClientId the primary key of the track client
	* @return the track client that was removed
	* @throws PortalException if a track client with the primary key could not be found
	*/
	@Override
	public org.opencps.usermgt.model.TrackClient deleteTrackClient(
		long trackClientId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _trackClientLocalService.deleteTrackClient(trackClientId);
	}

	/**
	* Deletes the track client from the database. Also notifies the appropriate model listeners.
	*
	* @param trackClient the track client
	* @return the track client that was removed
	*/
	@Override
	public org.opencps.usermgt.model.TrackClient deleteTrackClient(
		org.opencps.usermgt.model.TrackClient trackClient) {
		return _trackClientLocalService.deleteTrackClient(trackClient);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _trackClientLocalService.dynamicQuery();
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
		return _trackClientLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.TrackClientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _trackClientLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.TrackClientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _trackClientLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _trackClientLocalService.dynamicQueryCount(dynamicQuery);
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
		return _trackClientLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.usermgt.model.TrackClient fetchTrackClient(
		long trackClientId) {
		return _trackClientLocalService.fetchTrackClient(trackClientId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _trackClientLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _trackClientLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _trackClientLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _trackClientLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the track client with the primary key.
	*
	* @param trackClientId the primary key of the track client
	* @return the track client
	* @throws PortalException if a track client with the primary key could not be found
	*/
	@Override
	public org.opencps.usermgt.model.TrackClient getTrackClient(
		long trackClientId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _trackClientLocalService.getTrackClient(trackClientId);
	}

	/**
	* Returns a range of all the track clients.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.TrackClientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of track clients
	* @param end the upper bound of the range of track clients (not inclusive)
	* @return the range of track clients
	*/
	@Override
	public java.util.List<org.opencps.usermgt.model.TrackClient> getTrackClients(
		int start, int end) {
		return _trackClientLocalService.getTrackClients(start, end);
	}

	/**
	* Returns the number of track clients.
	*
	* @return the number of track clients
	*/
	@Override
	public int getTrackClientsCount() {
		return _trackClientLocalService.getTrackClientsCount();
	}

	/**
	* Updates the track client in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param trackClient the track client
	* @return the track client that was updated
	*/
	@Override
	public org.opencps.usermgt.model.TrackClient updateTrackClient(
		org.opencps.usermgt.model.TrackClient trackClient) {
		return _trackClientLocalService.updateTrackClient(trackClient);
	}

	@Override
	public org.opencps.usermgt.model.TrackClient updateUserTrackPath(
		long trackClientId, String sessionId, String url, int year, int month,
		int day, java.util.Date visitDate, java.util.Date leaveDate,
		String clientIP, String macAddress, String region, long timeOnPage,
		boolean desktop, boolean mobile, boolean tablet) {
		return _trackClientLocalService.updateUserTrackPath(trackClientId,
			sessionId, url, year, month, day, visitDate, leaveDate, clientIP,
			macAddress, region, timeOnPage, desktop, mobile, tablet);
	}

	@Override
	public TrackClientLocalService getWrappedService() {
		return _trackClientLocalService;
	}

	@Override
	public void setWrappedService(
		TrackClientLocalService trackClientLocalService) {
		_trackClientLocalService = trackClientLocalService;
	}

	private TrackClientLocalService _trackClientLocalService;
}