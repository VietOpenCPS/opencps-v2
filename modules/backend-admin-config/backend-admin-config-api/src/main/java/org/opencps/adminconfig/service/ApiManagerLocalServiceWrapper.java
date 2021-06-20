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
 * Provides a wrapper for {@link ApiManagerLocalService}.
 *
 * @author binhth
 * @see ApiManagerLocalService
 * @generated
 */
@ProviderType
public class ApiManagerLocalServiceWrapper implements ApiManagerLocalService,
	ServiceWrapper<ApiManagerLocalService> {
	public ApiManagerLocalServiceWrapper(
		ApiManagerLocalService apiManagerLocalService) {
		_apiManagerLocalService = apiManagerLocalService;
	}

	/**
	* Adds the api manager to the database. Also notifies the appropriate model listeners.
	*
	* @param apiManager the api manager
	* @return the api manager that was added
	*/
	@Override
	public org.opencps.adminconfig.model.ApiManager addApiManager(
		org.opencps.adminconfig.model.ApiManager apiManager) {
		return _apiManagerLocalService.addApiManager(apiManager);
	}

	@Override
	public int countByG(long groupId) {
		return _apiManagerLocalService.countByG(groupId);
	}

	/**
	* Creates a new api manager with the primary key. Does not add the api manager to the database.
	*
	* @param apiManagerId the primary key for the new api manager
	* @return the new api manager
	*/
	@Override
	public org.opencps.adminconfig.model.ApiManager createApiManager(
		long apiManagerId) {
		return _apiManagerLocalService.createApiManager(apiManagerId);
	}

	/**
	* Deletes the api manager from the database. Also notifies the appropriate model listeners.
	*
	* @param apiManager the api manager
	* @return the api manager that was removed
	*/
	@Override
	public org.opencps.adminconfig.model.ApiManager deleteApiManager(
		org.opencps.adminconfig.model.ApiManager apiManager) {
		return _apiManagerLocalService.deleteApiManager(apiManager);
	}

	/**
	* Deletes the api manager with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param apiManagerId the primary key of the api manager
	* @return the api manager that was removed
	* @throws PortalException if a api manager with the primary key could not be found
	*/
	@Override
	public org.opencps.adminconfig.model.ApiManager deleteApiManager(
		long apiManagerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _apiManagerLocalService.deleteApiManager(apiManagerId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _apiManagerLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _apiManagerLocalService.dynamicQuery();
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
		return _apiManagerLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.adminconfig.model.impl.ApiManagerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _apiManagerLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.adminconfig.model.impl.ApiManagerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _apiManagerLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _apiManagerLocalService.dynamicQueryCount(dynamicQuery);
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
		return _apiManagerLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.adminconfig.model.ApiManager fetchApiManager(
		long apiManagerId) {
		return _apiManagerLocalService.fetchApiManager(apiManagerId);
	}

	@Override
	public org.opencps.adminconfig.model.ApiManager findByApiCode(
		String apiCode) {
		return _apiManagerLocalService.findByApiCode(apiCode);
	}

	@Override
	public java.util.List<org.opencps.adminconfig.model.ApiManager> findByG(
		long groupId) {
		return _apiManagerLocalService.findByG(groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _apiManagerLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the api manager with the primary key.
	*
	* @param apiManagerId the primary key of the api manager
	* @return the api manager
	* @throws PortalException if a api manager with the primary key could not be found
	*/
	@Override
	public org.opencps.adminconfig.model.ApiManager getApiManager(
		long apiManagerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _apiManagerLocalService.getApiManager(apiManagerId);
	}

	/**
	* Returns a range of all the api managers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.adminconfig.model.impl.ApiManagerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of api managers
	* @param end the upper bound of the range of api managers (not inclusive)
	* @return the range of api managers
	*/
	@Override
	public java.util.List<org.opencps.adminconfig.model.ApiManager> getApiManagers(
		int start, int end) {
		return _apiManagerLocalService.getApiManagers(start, end);
	}

	/**
	* Returns the number of api managers.
	*
	* @return the number of api managers
	*/
	@Override
	public int getApiManagersCount() {
		return _apiManagerLocalService.getApiManagersCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _apiManagerLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _apiManagerLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _apiManagerLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the api manager in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param apiManager the api manager
	* @return the api manager that was updated
	*/
	@Override
	public org.opencps.adminconfig.model.ApiManager updateApiManager(
		org.opencps.adminconfig.model.ApiManager apiManager) {
		return _apiManagerLocalService.updateApiManager(apiManager);
	}

	@Override
	public org.opencps.adminconfig.model.ApiManager updateApiManager(
		long userId, long groupId, long apiManagerId, String apiCode,
		String apiDescription, String apiName, int apiStatus, String className) {
		return _apiManagerLocalService.updateApiManager(userId, groupId,
			apiManagerId, apiCode, apiDescription, apiName, apiStatus, className);
	}

	@Override
	public ApiManagerLocalService getWrappedService() {
		return _apiManagerLocalService;
	}

	@Override
	public void setWrappedService(ApiManagerLocalService apiManagerLocalService) {
		_apiManagerLocalService = apiManagerLocalService;
	}

	private ApiManagerLocalService _apiManagerLocalService;
}