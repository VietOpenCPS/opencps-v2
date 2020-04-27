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
 * Provides a wrapper for {@link TrackClientStatisticLocalService}.
 *
 * @author khoavu
 * @see TrackClientStatisticLocalService
 * @generated
 */
@ProviderType
public class TrackClientStatisticLocalServiceWrapper
	implements TrackClientStatisticLocalService,
		ServiceWrapper<TrackClientStatisticLocalService> {
	public TrackClientStatisticLocalServiceWrapper(
		TrackClientStatisticLocalService trackClientStatisticLocalService) {
		_trackClientStatisticLocalService = trackClientStatisticLocalService;
	}

	/**
	* Adds the track client statistic to the database. Also notifies the appropriate model listeners.
	*
	* @param trackClientStatistic the track client statistic
	* @return the track client statistic that was added
	*/
	@Override
	public org.opencps.usermgt.model.TrackClientStatistic addTrackClientStatistic(
		org.opencps.usermgt.model.TrackClientStatistic trackClientStatistic) {
		return _trackClientStatisticLocalService.addTrackClientStatistic(trackClientStatistic);
	}

	/**
	* Creates a new track client statistic with the primary key. Does not add the track client statistic to the database.
	*
	* @param trackClientStatisticId the primary key for the new track client statistic
	* @return the new track client statistic
	*/
	@Override
	public org.opencps.usermgt.model.TrackClientStatistic createTrackClientStatistic(
		long trackClientStatisticId) {
		return _trackClientStatisticLocalService.createTrackClientStatistic(trackClientStatisticId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _trackClientStatisticLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the track client statistic with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trackClientStatisticId the primary key of the track client statistic
	* @return the track client statistic that was removed
	* @throws PortalException if a track client statistic with the primary key could not be found
	*/
	@Override
	public org.opencps.usermgt.model.TrackClientStatistic deleteTrackClientStatistic(
		long trackClientStatisticId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _trackClientStatisticLocalService.deleteTrackClientStatistic(trackClientStatisticId);
	}

	/**
	* Deletes the track client statistic from the database. Also notifies the appropriate model listeners.
	*
	* @param trackClientStatistic the track client statistic
	* @return the track client statistic that was removed
	*/
	@Override
	public org.opencps.usermgt.model.TrackClientStatistic deleteTrackClientStatistic(
		org.opencps.usermgt.model.TrackClientStatistic trackClientStatistic) {
		return _trackClientStatisticLocalService.deleteTrackClientStatistic(trackClientStatistic);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _trackClientStatisticLocalService.dynamicQuery();
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
		return _trackClientStatisticLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.TrackClientStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _trackClientStatisticLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.TrackClientStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _trackClientStatisticLocalService.dynamicQuery(dynamicQuery,
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
		return _trackClientStatisticLocalService.dynamicQueryCount(dynamicQuery);
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
		return _trackClientStatisticLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.usermgt.model.TrackClientStatistic fetchTrackClientStatistic(
		long trackClientStatisticId) {
		return _trackClientStatisticLocalService.fetchTrackClientStatistic(trackClientStatisticId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _trackClientStatisticLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _trackClientStatisticLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _trackClientStatisticLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _trackClientStatisticLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the track client statistic with the primary key.
	*
	* @param trackClientStatisticId the primary key of the track client statistic
	* @return the track client statistic
	* @throws PortalException if a track client statistic with the primary key could not be found
	*/
	@Override
	public org.opencps.usermgt.model.TrackClientStatistic getTrackClientStatistic(
		long trackClientStatisticId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _trackClientStatisticLocalService.getTrackClientStatistic(trackClientStatisticId);
	}

	/**
	* Returns a range of all the track client statistics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.TrackClientStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of track client statistics
	* @param end the upper bound of the range of track client statistics (not inclusive)
	* @return the range of track client statistics
	*/
	@Override
	public java.util.List<org.opencps.usermgt.model.TrackClientStatistic> getTrackClientStatistics(
		int start, int end) {
		return _trackClientStatisticLocalService.getTrackClientStatistics(start,
			end);
	}

	/**
	* Returns the number of track client statistics.
	*
	* @return the number of track client statistics
	*/
	@Override
	public int getTrackClientStatisticsCount() {
		return _trackClientStatisticLocalService.getTrackClientStatisticsCount();
	}

	@Override
	public void updateStatisticTotal(String url, int year, int month, int day,
		String region, boolean desktop, boolean mobile, boolean tablet) {
		_trackClientStatisticLocalService.updateStatisticTotal(url, year,
			month, day, region, desktop, mobile, tablet);
	}

	@Override
	public org.opencps.usermgt.model.TrackClientStatistic updateTrackClientStatistic(
		long trackClientStatisticId, String url, int year, int month, int day,
		String region, boolean desktop, boolean mobile, boolean tablet) {
		return _trackClientStatisticLocalService.updateTrackClientStatistic(trackClientStatisticId,
			url, year, month, day, region, desktop, mobile, tablet);
	}

	/**
	* Updates the track client statistic in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param trackClientStatistic the track client statistic
	* @return the track client statistic that was updated
	*/
	@Override
	public org.opencps.usermgt.model.TrackClientStatistic updateTrackClientStatistic(
		org.opencps.usermgt.model.TrackClientStatistic trackClientStatistic) {
		return _trackClientStatisticLocalService.updateTrackClientStatistic(trackClientStatistic);
	}

	@Override
	public TrackClientStatisticLocalService getWrappedService() {
		return _trackClientStatisticLocalService;
	}

	@Override
	public void setWrappedService(
		TrackClientStatisticLocalService trackClientStatisticLocalService) {
		_trackClientStatisticLocalService = trackClientStatisticLocalService;
	}

	private TrackClientStatisticLocalService _trackClientStatisticLocalService;
}