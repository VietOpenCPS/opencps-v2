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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link UserInfoLogLocalService}.
 *
 * @author huymq
 * @see UserInfoLogLocalService
 * @generated
 */
@ProviderType
public class UserInfoLogLocalServiceWrapper implements UserInfoLogLocalService,
	ServiceWrapper<UserInfoLogLocalService> {
	public UserInfoLogLocalServiceWrapper(
		UserInfoLogLocalService userInfoLogLocalService) {
		_userInfoLogLocalService = userInfoLogLocalService;
	}

	@Override
	public org.opencps.dossiermgt.model.UserInfoLog addUserInfoLog(
		long userId, com.liferay.portal.kernel.json.JSONObject jsonData,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {
		return _userInfoLogLocalService.addUserInfoLog(userId, jsonData,
			serviceContext);
	}

	/**
	* Adds the user info log to the database. Also notifies the appropriate model listeners.
	*
	* @param userInfoLog the user info log
	* @return the user info log that was added
	*/
	@Override
	public org.opencps.dossiermgt.model.UserInfoLog addUserInfoLog(
		org.opencps.dossiermgt.model.UserInfoLog userInfoLog) {
		return _userInfoLogLocalService.addUserInfoLog(userInfoLog);
	}

	/**
	* Creates a new user info log with the primary key. Does not add the user info log to the database.
	*
	* @param userLogId the primary key for the new user info log
	* @return the new user info log
	*/
	@Override
	public org.opencps.dossiermgt.model.UserInfoLog createUserInfoLog(
		long userLogId) {
		return _userInfoLogLocalService.createUserInfoLog(userLogId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userInfoLogLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the user info log with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userLogId the primary key of the user info log
	* @return the user info log that was removed
	* @throws PortalException if a user info log with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.UserInfoLog deleteUserInfoLog(
		long userLogId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userInfoLogLocalService.deleteUserInfoLog(userLogId);
	}

	/**
	* Deletes the user info log from the database. Also notifies the appropriate model listeners.
	*
	* @param userInfoLog the user info log
	* @return the user info log that was removed
	*/
	@Override
	public org.opencps.dossiermgt.model.UserInfoLog deleteUserInfoLog(
		org.opencps.dossiermgt.model.UserInfoLog userInfoLog) {
		return _userInfoLogLocalService.deleteUserInfoLog(userInfoLog);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _userInfoLogLocalService.dynamicQuery();
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
		return _userInfoLogLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _userInfoLogLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _userInfoLogLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _userInfoLogLocalService.dynamicQueryCount(dynamicQuery);
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
		return _userInfoLogLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.dossiermgt.model.UserInfoLog fetchUserInfoLog(
		long userLogId) {
		return _userInfoLogLocalService.fetchUserInfoLog(userLogId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _userInfoLogLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _userInfoLogLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _userInfoLogLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userInfoLogLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the user info log with the primary key.
	*
	* @param userLogId the primary key of the user info log
	* @return the user info log
	* @throws PortalException if a user info log with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.UserInfoLog getUserInfoLog(
		long userLogId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userInfoLogLocalService.getUserInfoLog(userLogId);
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
	@Override
	public java.util.List<org.opencps.dossiermgt.model.UserInfoLog> getUserInfoLogs(
		int start, int end) {
		return _userInfoLogLocalService.getUserInfoLogs(start, end);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.UserInfoLog> getUserInfoLogs(
		long userId) {
		return _userInfoLogLocalService.getUserInfoLogs(userId);
	}

	/**
	* Returns the number of user info logs.
	*
	* @return the number of user info logs
	*/
	@Override
	public int getUserInfoLogsCount() {
		return _userInfoLogLocalService.getUserInfoLogsCount();
	}

	/**
	* Updates the user info log in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param userInfoLog the user info log
	* @return the user info log that was updated
	*/
	@Override
	public org.opencps.dossiermgt.model.UserInfoLog updateUserInfoLog(
		org.opencps.dossiermgt.model.UserInfoLog userInfoLog) {
		return _userInfoLogLocalService.updateUserInfoLog(userInfoLog);
	}

	@Override
	public UserInfoLogLocalService getWrappedService() {
		return _userInfoLogLocalService;
	}

	@Override
	public void setWrappedService(
		UserInfoLogLocalService userInfoLogLocalService) {
		_userInfoLogLocalService = userInfoLogLocalService;
	}

	private UserInfoLogLocalService _userInfoLogLocalService;
}