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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for TrackClientStatistic. This utility wraps
 * {@link org.opencps.usermgt.service.impl.TrackClientStatisticLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author khoavu
 * @see TrackClientStatisticLocalService
 * @see org.opencps.usermgt.service.base.TrackClientStatisticLocalServiceBaseImpl
 * @see org.opencps.usermgt.service.impl.TrackClientStatisticLocalServiceImpl
 * @generated
 */
@ProviderType
public class TrackClientStatisticLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.usermgt.service.impl.TrackClientStatisticLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static java.util.List<org.opencps.usermgt.model.TrackClientStatistic> accessStatisticsURL(
		int day, int month, int year) {
		return getService().accessStatisticsURL(day, month, year);
	}

	public static com.liferay.portal.kernel.json.JSONObject accessStatisticsURLForAllYear() {
		return getService().accessStatisticsURLForAllYear();
	}

	public static com.liferay.portal.kernel.json.JSONObject accessStatisticsURLForPeriod(
		String startDay, String endDay) {
		return getService().accessStatisticsURLForPeriod(startDay, endDay);
	}

	/**
	* Adds the track client statistic to the database. Also notifies the appropriate model listeners.
	*
	* @param trackClientStatistic the track client statistic
	* @return the track client statistic that was added
	*/
	public static org.opencps.usermgt.model.TrackClientStatistic addTrackClientStatistic(
		org.opencps.usermgt.model.TrackClientStatistic trackClientStatistic) {
		return getService().addTrackClientStatistic(trackClientStatistic);
	}

	public static long countAccess(int day, int month, int year) {
		return getService().countAccess(day, month, year);
	}

	public static long countAccessAllYear() {
		return getService().countAccessAllYear();
	}

	public static long countAccessPeriod(String startDay, String endDay) {
		return getService().countAccessPeriod(startDay, endDay);
	}

	/**
	* Creates a new track client statistic with the primary key. Does not add the track client statistic to the database.
	*
	* @param trackClientStatisticId the primary key for the new track client statistic
	* @return the new track client statistic
	*/
	public static org.opencps.usermgt.model.TrackClientStatistic createTrackClientStatistic(
		long trackClientStatisticId) {
		return getService().createTrackClientStatistic(trackClientStatisticId);
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
	* Deletes the track client statistic with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trackClientStatisticId the primary key of the track client statistic
	* @return the track client statistic that was removed
	* @throws PortalException if a track client statistic with the primary key could not be found
	*/
	public static org.opencps.usermgt.model.TrackClientStatistic deleteTrackClientStatistic(
		long trackClientStatisticId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteTrackClientStatistic(trackClientStatisticId);
	}

	/**
	* Deletes the track client statistic from the database. Also notifies the appropriate model listeners.
	*
	* @param trackClientStatistic the track client statistic
	* @return the track client statistic that was removed
	*/
	public static org.opencps.usermgt.model.TrackClientStatistic deleteTrackClientStatistic(
		org.opencps.usermgt.model.TrackClientStatistic trackClientStatistic) {
		return getService().deleteTrackClientStatistic(trackClientStatistic);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.TrackClientStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.TrackClientStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.usermgt.model.TrackClientStatistic fetchTrackClientStatistic(
		long trackClientStatisticId) {
		return getService().fetchTrackClientStatistic(trackClientStatisticId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
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
	* Returns the track client statistic with the primary key.
	*
	* @param trackClientStatisticId the primary key of the track client statistic
	* @return the track client statistic
	* @throws PortalException if a track client statistic with the primary key could not be found
	*/
	public static org.opencps.usermgt.model.TrackClientStatistic getTrackClientStatistic(
		long trackClientStatisticId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getTrackClientStatistic(trackClientStatisticId);
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
	public static java.util.List<org.opencps.usermgt.model.TrackClientStatistic> getTrackClientStatistics(
		int start, int end) {
		return getService().getTrackClientStatistics(start, end);
	}

	/**
	* Returns the number of track client statistics.
	*
	* @return the number of track client statistics
	*/
	public static int getTrackClientStatisticsCount() {
		return getService().getTrackClientStatisticsCount();
	}

	public static void updateStatisticTotal(String url, int year, int month,
		int day, String region, boolean desktop, boolean mobile, boolean tablet) {
		getService()
			.updateStatisticTotal(url, year, month, day, region, desktop,
			mobile, tablet);
	}

	public static org.opencps.usermgt.model.TrackClientStatistic updateTrackClientStatistic(
		long trackClientStatisticId, String url, int year, int month, int day,
		String region, boolean desktop, boolean mobile, boolean tablet) {
		return getService()
				   .updateTrackClientStatistic(trackClientStatisticId, url,
			year, month, day, region, desktop, mobile, tablet);
	}

	/**
	* Updates the track client statistic in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param trackClientStatistic the track client statistic
	* @return the track client statistic that was updated
	*/
	public static org.opencps.usermgt.model.TrackClientStatistic updateTrackClientStatistic(
		org.opencps.usermgt.model.TrackClientStatistic trackClientStatistic) {
		return getService().updateTrackClientStatistic(trackClientStatistic);
	}

	public static TrackClientStatisticLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<TrackClientStatisticLocalService, TrackClientStatisticLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(TrackClientStatisticLocalService.class);

		ServiceTracker<TrackClientStatisticLocalService, TrackClientStatisticLocalService> serviceTracker =
			new ServiceTracker<TrackClientStatisticLocalService, TrackClientStatisticLocalService>(bundle.getBundleContext(),
				TrackClientStatisticLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}