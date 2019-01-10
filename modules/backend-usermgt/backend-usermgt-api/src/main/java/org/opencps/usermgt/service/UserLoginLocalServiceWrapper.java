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
 * Provides a wrapper for {@link UserLoginLocalService}.
 *
 * @author khoavu
 * @see UserLoginLocalService
 * @generated
 */
@ProviderType
public class UserLoginLocalServiceWrapper implements UserLoginLocalService,
	ServiceWrapper<UserLoginLocalService> {
	public UserLoginLocalServiceWrapper(
		UserLoginLocalService userLoginLocalService) {
		_userLoginLocalService = userLoginLocalService;
	}

	/**
	* Adds the user login to the database. Also notifies the appropriate model listeners.
	*
	* @param userLogin the user login
	* @return the user login that was added
	*/
	@Override
	public org.opencps.usermgt.model.UserLogin addUserLogin(
		org.opencps.usermgt.model.UserLogin userLogin) {
		return _userLoginLocalService.addUserLogin(userLogin);
	}

	/**
	* Creates a new user login with the primary key. Does not add the user login to the database.
	*
	* @param userLoginId the primary key for the new user login
	* @return the new user login
	*/
	@Override
	public org.opencps.usermgt.model.UserLogin createUserLogin(long userLoginId) {
		return _userLoginLocalService.createUserLogin(userLoginId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userLoginLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the user login with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userLoginId the primary key of the user login
	* @return the user login that was removed
	* @throws PortalException if a user login with the primary key could not be found
	*/
	@Override
	public org.opencps.usermgt.model.UserLogin deleteUserLogin(long userLoginId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userLoginLocalService.deleteUserLogin(userLoginId);
	}

	/**
	* Deletes the user login from the database. Also notifies the appropriate model listeners.
	*
	* @param userLogin the user login
	* @return the user login that was removed
	*/
	@Override
	public org.opencps.usermgt.model.UserLogin deleteUserLogin(
		org.opencps.usermgt.model.UserLogin userLogin) {
		return _userLoginLocalService.deleteUserLogin(userLogin);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _userLoginLocalService.dynamicQuery();
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
		return _userLoginLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _userLoginLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _userLoginLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _userLoginLocalService.dynamicQueryCount(dynamicQuery);
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
		return _userLoginLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public org.opencps.usermgt.model.UserLogin fetchUserLogin(long userLoginId) {
		return _userLoginLocalService.fetchUserLogin(userLoginId);
	}

	/**
	* Returns the user login matching the UUID and group.
	*
	* @param uuid the user login's UUID
	* @param groupId the primary key of the group
	* @return the matching user login, or <code>null</code> if a matching user login could not be found
	*/
	@Override
	public org.opencps.usermgt.model.UserLogin fetchUserLoginByUuidAndGroupId(
		String uuid, long groupId) {
		return _userLoginLocalService.fetchUserLoginByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _userLoginLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _userLoginLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _userLoginLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _userLoginLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userLoginLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the user login with the primary key.
	*
	* @param userLoginId the primary key of the user login
	* @return the user login
	* @throws PortalException if a user login with the primary key could not be found
	*/
	@Override
	public org.opencps.usermgt.model.UserLogin getUserLogin(long userLoginId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userLoginLocalService.getUserLogin(userLoginId);
	}

	/**
	* Returns the user login matching the UUID and group.
	*
	* @param uuid the user login's UUID
	* @param groupId the primary key of the group
	* @return the matching user login
	* @throws PortalException if a matching user login could not be found
	*/
	@Override
	public org.opencps.usermgt.model.UserLogin getUserLoginByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userLoginLocalService.getUserLoginByUuidAndGroupId(uuid, groupId);
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
	@Override
	public java.util.List<org.opencps.usermgt.model.UserLogin> getUserLogins(
		int start, int end) {
		return _userLoginLocalService.getUserLogins(start, end);
	}

	/**
	* Returns all the user logins matching the UUID and company.
	*
	* @param uuid the UUID of the user logins
	* @param companyId the primary key of the company
	* @return the matching user logins, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.usermgt.model.UserLogin> getUserLoginsByUuidAndCompanyId(
		String uuid, long companyId) {
		return _userLoginLocalService.getUserLoginsByUuidAndCompanyId(uuid,
			companyId);
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
	@Override
	public java.util.List<org.opencps.usermgt.model.UserLogin> getUserLoginsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.usermgt.model.UserLogin> orderByComparator) {
		return _userLoginLocalService.getUserLoginsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of user logins.
	*
	* @return the number of user logins
	*/
	@Override
	public int getUserLoginsCount() {
		return _userLoginLocalService.getUserLoginsCount();
	}

	@Override
	public org.opencps.usermgt.model.UserLogin updateUserLogin(long companyId,
		long groupId, long userId, String userName, java.util.Date createDate,
		java.util.Date modifiedDate, long userLoginId, String sessionId,
		int hits, java.util.Date logout, String ipAddress)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _userLoginLocalService.updateUserLogin(companyId, groupId,
			userId, userName, createDate, modifiedDate, userLoginId, sessionId,
			hits, logout, ipAddress);
	}

	/**
	* Updates the user login in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param userLogin the user login
	* @return the user login that was updated
	*/
	@Override
	public org.opencps.usermgt.model.UserLogin updateUserLogin(
		org.opencps.usermgt.model.UserLogin userLogin) {
		return _userLoginLocalService.updateUserLogin(userLogin);
	}

	@Override
	public UserLoginLocalService getWrappedService() {
		return _userLoginLocalService;
	}

	@Override
	public void setWrappedService(UserLoginLocalService userLoginLocalService) {
		_userLoginLocalService = userLoginLocalService;
	}

	private UserLoginLocalService _userLoginLocalService;
}