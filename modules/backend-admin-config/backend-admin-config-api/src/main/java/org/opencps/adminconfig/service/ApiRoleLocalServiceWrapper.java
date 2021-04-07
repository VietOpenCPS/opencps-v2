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

package org.opencps.adminconfig.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ApiRoleLocalService}.
 *
 * @author binhth
 * @see ApiRoleLocalService
 * @generated
 */
@ProviderType
public class ApiRoleLocalServiceWrapper implements ApiRoleLocalService,
	ServiceWrapper<ApiRoleLocalService> {
	public ApiRoleLocalServiceWrapper(ApiRoleLocalService apiRoleLocalService) {
		_apiRoleLocalService = apiRoleLocalService;
	}

	/**
	* Adds the api role to the database. Also notifies the appropriate model listeners.
	*
	* @param apiRole the api role
	* @return the api role that was added
	*/
	@Override
	public org.opencps.adminconfig.model.ApiRole addApiRole(
		org.opencps.adminconfig.model.ApiRole apiRole) {
		return _apiRoleLocalService.addApiRole(apiRole);
	}

	@Override
	public int countByG(long groupId) {
		return _apiRoleLocalService.countByG(groupId);
	}

	/**
	* Creates a new api role with the primary key. Does not add the api role to the database.
	*
	* @param apiRoleId the primary key for the new api role
	* @return the new api role
	*/
	@Override
	public org.opencps.adminconfig.model.ApiRole createApiRole(long apiRoleId) {
		return _apiRoleLocalService.createApiRole(apiRoleId);
	}

	/**
	* Deletes the api role from the database. Also notifies the appropriate model listeners.
	*
	* @param apiRole the api role
	* @return the api role that was removed
	*/
	@Override
	public org.opencps.adminconfig.model.ApiRole deleteApiRole(
		org.opencps.adminconfig.model.ApiRole apiRole) {
		return _apiRoleLocalService.deleteApiRole(apiRole);
	}

	/**
	* Deletes the api role with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param apiRoleId the primary key of the api role
	* @return the api role that was removed
	* @throws PortalException if a api role with the primary key could not be found
	*/
	@Override
	public org.opencps.adminconfig.model.ApiRole deleteApiRole(long apiRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _apiRoleLocalService.deleteApiRole(apiRoleId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _apiRoleLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _apiRoleLocalService.dynamicQuery();
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
		return _apiRoleLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.adminconfig.model.impl.ApiRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _apiRoleLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.adminconfig.model.impl.ApiRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _apiRoleLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _apiRoleLocalService.dynamicQueryCount(dynamicQuery);
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
		return _apiRoleLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public org.opencps.adminconfig.model.ApiRole fetchApiRole(long apiRoleId) {
		return _apiRoleLocalService.fetchApiRole(apiRoleId);
	}

	@Override
	public java.util.List<org.opencps.adminconfig.model.ApiRole> findByG(
		long groupId) {
		return _apiRoleLocalService.findByG(groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _apiRoleLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the api role with the primary key.
	*
	* @param apiRoleId the primary key of the api role
	* @return the api role
	* @throws PortalException if a api role with the primary key could not be found
	*/
	@Override
	public org.opencps.adminconfig.model.ApiRole getApiRole(long apiRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _apiRoleLocalService.getApiRole(apiRoleId);
	}

	/**
	* Returns a range of all the api roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.adminconfig.model.impl.ApiRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of api roles
	* @param end the upper bound of the range of api roles (not inclusive)
	* @return the range of api roles
	*/
	@Override
	public java.util.List<org.opencps.adminconfig.model.ApiRole> getApiRoles(
		int start, int end) {
		return _apiRoleLocalService.getApiRoles(start, end);
	}

	/**
	* Returns the number of api roles.
	*
	* @return the number of api roles
	*/
	@Override
	public int getApiRolesCount() {
		return _apiRoleLocalService.getApiRolesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _apiRoleLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _apiRoleLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _apiRoleLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the api role in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param apiRole the api role
	* @return the api role that was updated
	*/
	@Override
	public org.opencps.adminconfig.model.ApiRole updateApiRole(
		org.opencps.adminconfig.model.ApiRole apiRole) {
		return _apiRoleLocalService.updateApiRole(apiRole);
	}

	@Override
	public org.opencps.adminconfig.model.ApiRole updateApiRole(long userId,
		long groupId, long apiRoleId, String apiCode, long roleId,
		String roleCode, int apiRoleStatus) {
		return _apiRoleLocalService.updateApiRole(userId, groupId, apiRoleId,
			apiCode, roleId, roleCode, apiRoleStatus);
	}

	@Override
	public ApiRoleLocalService getWrappedService() {
		return _apiRoleLocalService;
	}

	@Override
	public void setWrappedService(ApiRoleLocalService apiRoleLocalService) {
		_apiRoleLocalService = apiRoleLocalService;
	}

	private ApiRoleLocalService _apiRoleLocalService;
}