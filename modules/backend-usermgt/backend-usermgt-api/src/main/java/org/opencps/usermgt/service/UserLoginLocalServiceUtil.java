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
 * Provides the local service utility for UserLogin. This utility wraps
 * {@link org.opencps.usermgt.service.impl.UserLoginLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author khoavu
 * @see UserLoginLocalService
 * @see org.opencps.usermgt.service.base.UserLoginLocalServiceBaseImpl
 * @see org.opencps.usermgt.service.impl.UserLoginLocalServiceImpl
 * @generated
 */
@ProviderType
public class UserLoginLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.usermgt.service.impl.UserLoginLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the user login to the database. Also notifies the appropriate model listeners.
	*
	* @param userLogin the user login
	* @return the user login that was added
	*/
	public static org.opencps.usermgt.model.UserLogin addUserLogin(
		org.opencps.usermgt.model.UserLogin userLogin) {
		return getService().addUserLogin(userLogin);
	}

	/**
	* Creates a new user login with the primary key. Does not add the user login to the database.
	*
	* @param userLoginId the primary key for the new user login
	* @return the new user login
	*/
	public static org.opencps.usermgt.model.UserLogin createUserLogin(
		long userLoginId) {
		return getService().createUserLogin(userLoginId);
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
	* Deletes the user login with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userLoginId the primary key of the user login
	* @return the user login that was removed
	* @throws PortalException if a user login with the primary key could not be found
	*/
	public static org.opencps.usermgt.model.UserLogin deleteUserLogin(
		long userLoginId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteUserLogin(userLoginId);
	}

	/**
	* Deletes the user login from the database. Also notifies the appropriate model listeners.
	*
	* @param userLogin the user login
	* @return the user login that was removed
	*/
	public static org.opencps.usermgt.model.UserLogin deleteUserLogin(
		org.opencps.usermgt.model.UserLogin userLogin) {
		return getService().deleteUserLogin(userLogin);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.UserLoginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.UserLoginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.usermgt.model.UserLogin fetchUserLogin(
		long userLoginId) {
		return getService().fetchUserLogin(userLoginId);
	}

	/**
	* Returns the user login matching the UUID and group.
	*
	* @param uuid the user login's UUID
	* @param groupId the primary key of the group
	* @return the matching user login, or <code>null</code> if a matching user login could not be found
	*/
	public static org.opencps.usermgt.model.UserLogin fetchUserLoginByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchUserLoginByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
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
	* Returns the user login with the primary key.
	*
	* @param userLoginId the primary key of the user login
	* @return the user login
	* @throws PortalException if a user login with the primary key could not be found
	*/
	public static org.opencps.usermgt.model.UserLogin getUserLogin(
		long userLoginId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getUserLogin(userLoginId);
	}

	/**
	* Returns the user login matching the UUID and group.
	*
	* @param uuid the user login's UUID
	* @param groupId the primary key of the group
	* @return the matching user login
	* @throws PortalException if a matching user login could not be found
	*/
	public static org.opencps.usermgt.model.UserLogin getUserLoginByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getUserLoginByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the user logins.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.UserLoginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user logins
	* @param end the upper bound of the range of user logins (not inclusive)
	* @return the range of user logins
	*/
	public static java.util.List<org.opencps.usermgt.model.UserLogin> getUserLogins(
		int start, int end) {
		return getService().getUserLogins(start, end);
	}

	/**
	* Returns all the user logins matching the UUID and company.
	*
	* @param uuid the UUID of the user logins
	* @param companyId the primary key of the company
	* @return the matching user logins, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.usermgt.model.UserLogin> getUserLoginsByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService().getUserLoginsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of user logins matching the UUID and company.
	*
	* @param uuid the UUID of the user logins
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of user logins
	* @param end the upper bound of the range of user logins (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching user logins, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.usermgt.model.UserLogin> getUserLoginsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.usermgt.model.UserLogin> orderByComparator) {
		return getService()
				   .getUserLoginsByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of user logins.
	*
	* @return the number of user logins
	*/
	public static int getUserLoginsCount() {
		return getService().getUserLoginsCount();
	}

	public static org.opencps.usermgt.model.UserLogin updateUserLogin(
		long companyId, long groupId, long userId, String userName,
		java.util.Date createDate, java.util.Date modifiedDate,
		long userLoginId, String sessionId, int hits, java.util.Date logout,
		String ipAddress)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateUserLogin(companyId, groupId, userId, userName,
			createDate, modifiedDate, userLoginId, sessionId, hits, logout,
			ipAddress);
	}

	/**
	* Updates the user login in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param userLogin the user login
	* @return the user login that was updated
	*/
	public static org.opencps.usermgt.model.UserLogin updateUserLogin(
		org.opencps.usermgt.model.UserLogin userLogin) {
		return getService().updateUserLogin(userLogin);
	}

	public static UserLoginLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<UserLoginLocalService, UserLoginLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(UserLoginLocalService.class);

		ServiceTracker<UserLoginLocalService, UserLoginLocalService> serviceTracker =
			new ServiceTracker<UserLoginLocalService, UserLoginLocalService>(bundle.getBundleContext(),
				UserLoginLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}