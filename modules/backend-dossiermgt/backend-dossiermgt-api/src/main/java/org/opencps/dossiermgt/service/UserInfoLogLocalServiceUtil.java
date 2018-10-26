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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for UserInfoLog. This utility wraps
 * {@link org.opencps.dossiermgt.service.impl.UserInfoLogLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author huymq
 * @see UserInfoLogLocalService
 * @see org.opencps.dossiermgt.service.base.UserInfoLogLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.UserInfoLogLocalServiceImpl
 * @generated
 */
@ProviderType
public class UserInfoLogLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.UserInfoLogLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static org.opencps.dossiermgt.model.UserInfoLog addUserInfoLog(
		long userId, com.liferay.portal.kernel.json.JSONObject jsonData,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {
		return getService().addUserInfoLog(userId, jsonData, serviceContext);
	}

	/**
	* Adds the user info log to the database. Also notifies the appropriate model listeners.
	*
	* @param userInfoLog the user info log
	* @return the user info log that was added
	*/
	public static org.opencps.dossiermgt.model.UserInfoLog addUserInfoLog(
		org.opencps.dossiermgt.model.UserInfoLog userInfoLog) {
		return getService().addUserInfoLog(userInfoLog);
	}

	public static org.opencps.dossiermgt.model.UserInfoLog adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return getService().adminProcessData(objectData);
	}

	public static org.opencps.dossiermgt.model.UserInfoLog adminProcessDelete(
		Long id) {
		return getService().adminProcessDelete(id);
	}

	/**
	* Creates a new user info log with the primary key. Does not add the user info log to the database.
	*
	* @param userLogId the primary key for the new user info log
	* @return the new user info log
	*/
	public static org.opencps.dossiermgt.model.UserInfoLog createUserInfoLog(
		long userLogId) {
		return getService().createUserInfoLog(userLogId);
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
	* Deletes the user info log with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userLogId the primary key of the user info log
	* @return the user info log that was removed
	* @throws PortalException if a user info log with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.UserInfoLog deleteUserInfoLog(
		long userLogId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteUserInfoLog(userLogId);
	}

	/**
	* Deletes the user info log from the database. Also notifies the appropriate model listeners.
	*
	* @param userInfoLog the user info log
	* @return the user info log that was removed
	*/
	public static org.opencps.dossiermgt.model.UserInfoLog deleteUserInfoLog(
		org.opencps.dossiermgt.model.UserInfoLog userInfoLog) {
		return getService().deleteUserInfoLog(userInfoLog);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.UserInfoLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.UserInfoLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.dossiermgt.model.UserInfoLog fetchUserInfoLog(
		long userLogId) {
		return getService().fetchUserInfoLog(userLogId);
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
	* Returns the user info log with the primary key.
	*
	* @param userLogId the primary key of the user info log
	* @return the user info log
	* @throws PortalException if a user info log with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.UserInfoLog getUserInfoLog(
		long userLogId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getUserInfoLog(userLogId);
	}

	/**
	* Returns a range of all the user info logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.UserInfoLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user info logs
	* @param end the upper bound of the range of user info logs (not inclusive)
	* @return the range of user info logs
	*/
	public static java.util.List<org.opencps.dossiermgt.model.UserInfoLog> getUserInfoLogs(
		int start, int end) {
		return getService().getUserInfoLogs(start, end);
	}

	public static java.util.List<org.opencps.dossiermgt.model.UserInfoLog> getUserInfoLogs(
		long userId) {
		return getService().getUserInfoLogs(userId);
	}

	/**
	* Returns the number of user info logs.
	*
	* @return the number of user info logs
	*/
	public static int getUserInfoLogsCount() {
		return getService().getUserInfoLogsCount();
	}

	/**
	* Updates the user info log in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param userInfoLog the user info log
	* @return the user info log that was updated
	*/
	public static org.opencps.dossiermgt.model.UserInfoLog updateUserInfoLog(
		org.opencps.dossiermgt.model.UserInfoLog userInfoLog) {
		return getService().updateUserInfoLog(userInfoLog);
	}

	public static UserInfoLogLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<UserInfoLogLocalService, UserInfoLogLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(UserInfoLogLocalService.class);

		ServiceTracker<UserInfoLogLocalService, UserInfoLogLocalService> serviceTracker =
			new ServiceTracker<UserInfoLogLocalService, UserInfoLogLocalService>(bundle.getBundleContext(),
				UserInfoLogLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}