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
 * Provides the local service utility for UserTrackPath. This utility wraps
 * {@link org.opencps.usermgt.service.impl.UserTrackPathLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author khoavu
 * @see UserTrackPathLocalService
 * @see org.opencps.usermgt.service.base.UserTrackPathLocalServiceBaseImpl
 * @see org.opencps.usermgt.service.impl.UserTrackPathLocalServiceImpl
 * @generated
 */
@ProviderType
public class UserTrackPathLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.usermgt.service.impl.UserTrackPathLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the user track path to the database. Also notifies the appropriate model listeners.
	*
	* @param userTrackPath the user track path
	* @return the user track path that was added
	*/
	public static org.opencps.usermgt.model.UserTrackPath addUserTrackPath(
		org.opencps.usermgt.model.UserTrackPath userTrackPath) {
		return getService().addUserTrackPath(userTrackPath);
	}

	/**
	* Creates a new user track path with the primary key. Does not add the user track path to the database.
	*
	* @param userTrackPathId the primary key for the new user track path
	* @return the new user track path
	*/
	public static org.opencps.usermgt.model.UserTrackPath createUserTrackPath(
		long userTrackPathId) {
		return getService().createUserTrackPath(userTrackPathId);
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
	* Deletes the user track path with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userTrackPathId the primary key of the user track path
	* @return the user track path that was removed
	* @throws PortalException if a user track path with the primary key could not be found
	*/
	public static org.opencps.usermgt.model.UserTrackPath deleteUserTrackPath(
		long userTrackPathId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteUserTrackPath(userTrackPathId);
	}

	/**
	* Deletes the user track path from the database. Also notifies the appropriate model listeners.
	*
	* @param userTrackPath the user track path
	* @return the user track path that was removed
	*/
	public static org.opencps.usermgt.model.UserTrackPath deleteUserTrackPath(
		org.opencps.usermgt.model.UserTrackPath userTrackPath) {
		return getService().deleteUserTrackPath(userTrackPath);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.UserTrackPathModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.UserTrackPathModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.usermgt.model.UserTrackPath fetchUserTrackPath(
		long userTrackPathId) {
		return getService().fetchUserTrackPath(userTrackPathId);
	}

	/**
	* Returns the user track path with the matching UUID and company.
	*
	* @param uuid the user track path's UUID
	* @param companyId the primary key of the company
	* @return the matching user track path, or <code>null</code> if a matching user track path could not be found
	*/
	public static org.opencps.usermgt.model.UserTrackPath fetchUserTrackPathByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService().fetchUserTrackPathByUuidAndCompanyId(uuid, companyId);
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
	* Returns the user track path with the primary key.
	*
	* @param userTrackPathId the primary key of the user track path
	* @return the user track path
	* @throws PortalException if a user track path with the primary key could not be found
	*/
	public static org.opencps.usermgt.model.UserTrackPath getUserTrackPath(
		long userTrackPathId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getUserTrackPath(userTrackPathId);
	}

	/**
	* Returns the user track path with the matching UUID and company.
	*
	* @param uuid the user track path's UUID
	* @param companyId the primary key of the company
	* @return the matching user track path
	* @throws PortalException if a matching user track path could not be found
	*/
	public static org.opencps.usermgt.model.UserTrackPath getUserTrackPathByUuidAndCompanyId(
		String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getUserTrackPathByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of all the user track paths.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.UserTrackPathModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user track paths
	* @param end the upper bound of the range of user track paths (not inclusive)
	* @return the range of user track paths
	*/
	public static java.util.List<org.opencps.usermgt.model.UserTrackPath> getUserTrackPaths(
		int start, int end) {
		return getService().getUserTrackPaths(start, end);
	}

	/**
	* Returns the number of user track paths.
	*
	* @return the number of user track paths
	*/
	public static int getUserTrackPathsCount() {
		return getService().getUserTrackPathsCount();
	}

	public static org.opencps.usermgt.model.UserTrackPath updateUserTrackPath(
		long companyId, long userTrackPathId, long userLoginId, String path,
		java.util.Date pathDate) {
		return getService()
				   .updateUserTrackPath(companyId, userTrackPathId,
			userLoginId, path, pathDate);
	}

	/**
	* Updates the user track path in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param userTrackPath the user track path
	* @return the user track path that was updated
	*/
	public static org.opencps.usermgt.model.UserTrackPath updateUserTrackPath(
		org.opencps.usermgt.model.UserTrackPath userTrackPath) {
		return getService().updateUserTrackPath(userTrackPath);
	}

	public static UserTrackPathLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<UserTrackPathLocalService, UserTrackPathLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(UserTrackPathLocalService.class);

		ServiceTracker<UserTrackPathLocalService, UserTrackPathLocalService> serviceTracker =
			new ServiceTracker<UserTrackPathLocalService, UserTrackPathLocalService>(bundle.getBundleContext(),
				UserTrackPathLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}