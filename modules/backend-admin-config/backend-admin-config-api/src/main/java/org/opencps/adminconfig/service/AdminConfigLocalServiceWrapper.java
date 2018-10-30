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
 * Provides a wrapper for {@link AdminConfigLocalService}.
 *
 * @author binhth
 * @see AdminConfigLocalService
 * @generated
 */
@ProviderType
public class AdminConfigLocalServiceWrapper implements AdminConfigLocalService,
	ServiceWrapper<AdminConfigLocalService> {
	public AdminConfigLocalServiceWrapper(
		AdminConfigLocalService adminConfigLocalService) {
		_adminConfigLocalService = adminConfigLocalService;
	}

	/**
	* Adds the admin config to the database. Also notifies the appropriate model listeners.
	*
	* @param adminConfig the admin config
	* @return the admin config that was added
	*/
	@Override
	public org.opencps.adminconfig.model.AdminConfig addAdminConfig(
		org.opencps.adminconfig.model.AdminConfig adminConfig) {
		return _adminConfigLocalService.addAdminConfig(adminConfig);
	}

	@Override
	public org.opencps.adminconfig.model.AdminConfig adminProcessData(
		com.liferay.portal.kernel.json.JSONObject adminConfigData) {
		return _adminConfigLocalService.adminProcessData(adminConfigData);
	}

	@Override
	public org.opencps.adminconfig.model.AdminConfig adminProcessDelete(Long id) {
		return _adminConfigLocalService.adminProcessDelete(id);
	}

	/**
	* Creates a new admin config with the primary key. Does not add the admin config to the database.
	*
	* @param id the primary key for the new admin config
	* @return the new admin config
	*/
	@Override
	public org.opencps.adminconfig.model.AdminConfig createAdminConfig(long id) {
		return _adminConfigLocalService.createAdminConfig(id);
	}

	/**
	* Deletes the admin config from the database. Also notifies the appropriate model listeners.
	*
	* @param adminConfig the admin config
	* @return the admin config that was removed
	*/
	@Override
	public org.opencps.adminconfig.model.AdminConfig deleteAdminConfig(
		org.opencps.adminconfig.model.AdminConfig adminConfig) {
		return _adminConfigLocalService.deleteAdminConfig(adminConfig);
	}

	/**
	* Deletes the admin config with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the admin config
	* @return the admin config that was removed
	* @throws PortalException if a admin config with the primary key could not be found
	*/
	@Override
	public org.opencps.adminconfig.model.AdminConfig deleteAdminConfig(long id)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _adminConfigLocalService.deleteAdminConfig(id);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _adminConfigLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _adminConfigLocalService.dynamicQuery();
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
		return _adminConfigLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.adminconfig.model.impl.AdminConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _adminConfigLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.adminconfig.model.impl.AdminConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _adminConfigLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _adminConfigLocalService.dynamicQueryCount(dynamicQuery);
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
		return _adminConfigLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.adminconfig.model.AdminConfig fetchAdminConfig(long id) {
		return _adminConfigLocalService.fetchAdminConfig(id);
	}

	@Override
	public org.opencps.adminconfig.model.AdminConfig fetchByCode(String code) {
		return _adminConfigLocalService.fetchByCode(code);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _adminConfigLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the admin config with the primary key.
	*
	* @param id the primary key of the admin config
	* @return the admin config
	* @throws PortalException if a admin config with the primary key could not be found
	*/
	@Override
	public org.opencps.adminconfig.model.AdminConfig getAdminConfig(long id)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _adminConfigLocalService.getAdminConfig(id);
	}

	/**
	* Returns a range of all the admin configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.adminconfig.model.impl.AdminConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of admin configs
	* @param end the upper bound of the range of admin configs (not inclusive)
	* @return the range of admin configs
	*/
	@Override
	public java.util.List<org.opencps.adminconfig.model.AdminConfig> getAdminConfigs(
		int start, int end) {
		return _adminConfigLocalService.getAdminConfigs(start, end);
	}

	/**
	* Returns the number of admin configs.
	*
	* @return the number of admin configs
	*/
	@Override
	public int getAdminConfigsCount() {
		return _adminConfigLocalService.getAdminConfigsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _adminConfigLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _adminConfigLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _adminConfigLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the admin config in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param adminConfig the admin config
	* @return the admin config that was updated
	*/
	@Override
	public org.opencps.adminconfig.model.AdminConfig updateAdminConfig(
		org.opencps.adminconfig.model.AdminConfig adminConfig) {
		return _adminConfigLocalService.updateAdminConfig(adminConfig);
	}

	@Override
	public AdminConfigLocalService getWrappedService() {
		return _adminConfigLocalService;
	}

	@Override
	public void setWrappedService(
		AdminConfigLocalService adminConfigLocalService) {
		_adminConfigLocalService = adminConfigLocalService;
	}

	private AdminConfigLocalService _adminConfigLocalService;
}