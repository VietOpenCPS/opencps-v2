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
 * Provides the local service utility for TrackClient. This utility wraps
 * {@link org.opencps.usermgt.service.impl.TrackClientLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author khoavu
 * @see TrackClientLocalService
 * @see org.opencps.usermgt.service.base.TrackClientLocalServiceBaseImpl
 * @see org.opencps.usermgt.service.impl.TrackClientLocalServiceImpl
 * @generated
 */
@ProviderType
public class TrackClientLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.usermgt.service.impl.TrackClientLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the track client to the database. Also notifies the appropriate model listeners.
	*
	* @param trackClient the track client
	* @return the track client that was added
	*/
	public static org.opencps.usermgt.model.TrackClient addTrackClient(
		org.opencps.usermgt.model.TrackClient trackClient) {
		return getService().addTrackClient(trackClient);
	}

	/**
	* Creates a new track client with the primary key. Does not add the track client to the database.
	*
	* @param trackClientId the primary key for the new track client
	* @return the new track client
	*/
	public static org.opencps.usermgt.model.TrackClient createTrackClient(
		long trackClientId) {
		return getService().createTrackClient(trackClientId);
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
	* Deletes the track client with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trackClientId the primary key of the track client
	* @return the track client that was removed
	* @throws PortalException if a track client with the primary key could not be found
	*/
	public static org.opencps.usermgt.model.TrackClient deleteTrackClient(
		long trackClientId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteTrackClient(trackClientId);
	}

	/**
	* Deletes the track client from the database. Also notifies the appropriate model listeners.
	*
	* @param trackClient the track client
	* @return the track client that was removed
	*/
	public static org.opencps.usermgt.model.TrackClient deleteTrackClient(
		org.opencps.usermgt.model.TrackClient trackClient) {
		return getService().deleteTrackClient(trackClient);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.TrackClientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.TrackClientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.usermgt.model.TrackClient fetchTrackClient(
		long trackClientId) {
		return getService().fetchTrackClient(trackClientId);
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
	* Returns the track client with the primary key.
	*
	* @param trackClientId the primary key of the track client
	* @return the track client
	* @throws PortalException if a track client with the primary key could not be found
	*/
	public static org.opencps.usermgt.model.TrackClient getTrackClient(
		long trackClientId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getTrackClient(trackClientId);
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
	public static java.util.List<org.opencps.usermgt.model.TrackClient> getTrackClients(
		int start, int end) {
		return getService().getTrackClients(start, end);
	}

	/**
	* Returns the number of track clients.
	*
	* @return the number of track clients
	*/
	public static int getTrackClientsCount() {
		return getService().getTrackClientsCount();
	}

	/**
	* Updates the track client in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param trackClient the track client
	* @return the track client that was updated
	*/
	public static org.opencps.usermgt.model.TrackClient updateTrackClient(
		org.opencps.usermgt.model.TrackClient trackClient) {
		return getService().updateTrackClient(trackClient);
	}

	public static TrackClientLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<TrackClientLocalService, TrackClientLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(TrackClientLocalService.class);

		ServiceTracker<TrackClientLocalService, TrackClientLocalService> serviceTracker =
			new ServiceTracker<TrackClientLocalService, TrackClientLocalService>(bundle.getBundleContext(),
				TrackClientLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}