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
 * Provides a wrapper for {@link UserTrackPathLocalService}.
 *
 * @author khoavu
 * @see UserTrackPathLocalService
 * @generated
 */
@ProviderType
public class UserTrackPathLocalServiceWrapper
	implements UserTrackPathLocalService,
		ServiceWrapper<UserTrackPathLocalService> {
	public UserTrackPathLocalServiceWrapper(
		UserTrackPathLocalService userTrackPathLocalService) {
		_userTrackPathLocalService = userTrackPathLocalService;
	}

	/**
	* Adds the user track path to the database. Also notifies the appropriate model listeners.
	*
	* @param userTrackPath the user track path
	* @return the user track path that was added
	*/
	@Override
	public org.opencps.usermgt.model.UserTrackPath addUserTrackPath(
		org.opencps.usermgt.model.UserTrackPath userTrackPath) {
		return _userTrackPathLocalService.addUserTrackPath(userTrackPath);
	}

	/**
	* Creates a new user track path with the primary key. Does not add the user track path to the database.
	*
	* @param userTrackPathId the primary key for the new user track path
	* @return the new user track path
	*/
	@Override
	public org.opencps.usermgt.model.UserTrackPath createUserTrackPath(
		long userTrackPathId) {
		return _userTrackPathLocalService.createUserTrackPath(userTrackPathId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userTrackPathLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the user track path with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userTrackPathId the primary key of the user track path
	* @return the user track path that was removed
	* @throws PortalException if a user track path with the primary key could not be found
	*/
	@Override
	public org.opencps.usermgt.model.UserTrackPath deleteUserTrackPath(
		long userTrackPathId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userTrackPathLocalService.deleteUserTrackPath(userTrackPathId);
	}

	/**
	* Deletes the user track path from the database. Also notifies the appropriate model listeners.
	*
	* @param userTrackPath the user track path
	* @return the user track path that was removed
	*/
	@Override
	public org.opencps.usermgt.model.UserTrackPath deleteUserTrackPath(
		org.opencps.usermgt.model.UserTrackPath userTrackPath) {
		return _userTrackPathLocalService.deleteUserTrackPath(userTrackPath);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _userTrackPathLocalService.dynamicQuery();
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
		return _userTrackPathLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _userTrackPathLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _userTrackPathLocalService.dynamicQuery(dynamicQuery, start,
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
		return _userTrackPathLocalService.dynamicQueryCount(dynamicQuery);
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
		return _userTrackPathLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.usermgt.model.UserTrackPath fetchUserTrackPath(
		long userTrackPathId) {
		return _userTrackPathLocalService.fetchUserTrackPath(userTrackPathId);
	}

	/**
	* Returns the user track path with the matching UUID and company.
	*
	* @param uuid the user track path's UUID
	* @param companyId the primary key of the company
	* @return the matching user track path, or <code>null</code> if a matching user track path could not be found
	*/
	@Override
	public org.opencps.usermgt.model.UserTrackPath fetchUserTrackPathByUuidAndCompanyId(
		String uuid, long companyId) {
		return _userTrackPathLocalService.fetchUserTrackPathByUuidAndCompanyId(uuid,
			companyId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _userTrackPathLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _userTrackPathLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _userTrackPathLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userTrackPathLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the user track path with the primary key.
	*
	* @param userTrackPathId the primary key of the user track path
	* @return the user track path
	* @throws PortalException if a user track path with the primary key could not be found
	*/
	@Override
	public org.opencps.usermgt.model.UserTrackPath getUserTrackPath(
		long userTrackPathId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userTrackPathLocalService.getUserTrackPath(userTrackPathId);
	}

	/**
	* Returns the user track path with the matching UUID and company.
	*
	* @param uuid the user track path's UUID
	* @param companyId the primary key of the company
	* @return the matching user track path
	* @throws PortalException if a matching user track path could not be found
	*/
	@Override
	public org.opencps.usermgt.model.UserTrackPath getUserTrackPathByUuidAndCompanyId(
		String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userTrackPathLocalService.getUserTrackPathByUuidAndCompanyId(uuid,
			companyId);
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
	@Override
	public java.util.List<org.opencps.usermgt.model.UserTrackPath> getUserTrackPaths(
		int start, int end) {
		return _userTrackPathLocalService.getUserTrackPaths(start, end);
	}

	/**
	* Returns the number of user track paths.
	*
	* @return the number of user track paths
	*/
	@Override
	public int getUserTrackPathsCount() {
		return _userTrackPathLocalService.getUserTrackPathsCount();
	}

	@Override
	public org.opencps.usermgt.model.UserTrackPath updateUserTrackPath(
		long companyId, long userTrackPathId, long userLoginId, String path,
		java.util.Date pathDate) {
		return _userTrackPathLocalService.updateUserTrackPath(companyId,
			userTrackPathId, userLoginId, path, pathDate);
	}

	/**
	* Updates the user track path in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param userTrackPath the user track path
	* @return the user track path that was updated
	*/
	@Override
	public org.opencps.usermgt.model.UserTrackPath updateUserTrackPath(
		org.opencps.usermgt.model.UserTrackPath userTrackPath) {
		return _userTrackPathLocalService.updateUserTrackPath(userTrackPath);
	}

	@Override
	public UserTrackPathLocalService getWrappedService() {
		return _userTrackPathLocalService;
	}

	@Override
	public void setWrappedService(
		UserTrackPathLocalService userTrackPathLocalService) {
		_userTrackPathLocalService = userTrackPathLocalService;
	}

	private UserTrackPathLocalService _userTrackPathLocalService;
}