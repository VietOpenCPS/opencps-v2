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
 * Provides a wrapper for {@link ConfigCounterLocalService}.
 *
 * @author huymq
 * @see ConfigCounterLocalService
 * @generated
 */
@ProviderType
public class ConfigCounterLocalServiceWrapper
	implements ConfigCounterLocalService,
		ServiceWrapper<ConfigCounterLocalService> {
	public ConfigCounterLocalServiceWrapper(
		ConfigCounterLocalService configCounterLocalService) {
		_configCounterLocalService = configCounterLocalService;
	}

	/**
	* Adds the config counter to the database. Also notifies the appropriate model listeners.
	*
	* @param configCounter the config counter
	* @return the config counter that was added
	*/
	@Override
	public org.opencps.dossiermgt.model.ConfigCounter addConfigCounter(
		org.opencps.dossiermgt.model.ConfigCounter configCounter) {
		return _configCounterLocalService.addConfigCounter(configCounter);
	}

	/**
	* Creates a new config counter with the primary key. Does not add the config counter to the database.
	*
	* @param configCounterId the primary key for the new config counter
	* @return the new config counter
	*/
	@Override
	public org.opencps.dossiermgt.model.ConfigCounter createConfigCounter(
		long configCounterId) {
		return _configCounterLocalService.createConfigCounter(configCounterId);
	}

	/**
	* Deletes the config counter from the database. Also notifies the appropriate model listeners.
	*
	* @param configCounter the config counter
	* @return the config counter that was removed
	*/
	@Override
	public org.opencps.dossiermgt.model.ConfigCounter deleteConfigCounter(
		org.opencps.dossiermgt.model.ConfigCounter configCounter) {
		return _configCounterLocalService.deleteConfigCounter(configCounter);
	}

	/**
	* Deletes the config counter with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param configCounterId the primary key of the config counter
	* @return the config counter that was removed
	* @throws PortalException if a config counter with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.ConfigCounter deleteConfigCounter(
		long configCounterId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _configCounterLocalService.deleteConfigCounter(configCounterId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _configCounterLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _configCounterLocalService.dynamicQuery();
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
		return _configCounterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ConfigCounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _configCounterLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ConfigCounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _configCounterLocalService.dynamicQuery(dynamicQuery, start,
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
		return _configCounterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _configCounterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.dossiermgt.model.ConfigCounter fetchByCountrCode(
		long groupId, String counterCode) {
		return _configCounterLocalService.fetchByCountrCode(groupId, counterCode);
	}

	@Override
	public org.opencps.dossiermgt.model.ConfigCounter fetchConfigCounter(
		long configCounterId) {
		return _configCounterLocalService.fetchConfigCounter(configCounterId);
	}

	/**
	* Returns the config counter matching the UUID and group.
	*
	* @param uuid the config counter's UUID
	* @param groupId the primary key of the group
	* @return the matching config counter, or <code>null</code> if a matching config counter could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.ConfigCounter fetchConfigCounterByUuidAndGroupId(
		String uuid, long groupId) {
		return _configCounterLocalService.fetchConfigCounterByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _configCounterLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the config counter with the primary key.
	*
	* @param configCounterId the primary key of the config counter
	* @return the config counter
	* @throws PortalException if a config counter with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.ConfigCounter getConfigCounter(
		long configCounterId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _configCounterLocalService.getConfigCounter(configCounterId);
	}

	/**
	* Returns the config counter matching the UUID and group.
	*
	* @param uuid the config counter's UUID
	* @param groupId the primary key of the group
	* @return the matching config counter
	* @throws PortalException if a matching config counter could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.ConfigCounter getConfigCounterByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _configCounterLocalService.getConfigCounterByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the config counters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ConfigCounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of config counters
	* @param end the upper bound of the range of config counters (not inclusive)
	* @return the range of config counters
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.ConfigCounter> getConfigCounters(
		int start, int end) {
		return _configCounterLocalService.getConfigCounters(start, end);
	}

	/**
	* Returns all the config counters matching the UUID and company.
	*
	* @param uuid the UUID of the config counters
	* @param companyId the primary key of the company
	* @return the matching config counters, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.ConfigCounter> getConfigCountersByUuidAndCompanyId(
		String uuid, long companyId) {
		return _configCounterLocalService.getConfigCountersByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of config counters matching the UUID and company.
	*
	* @param uuid the UUID of the config counters
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of config counters
	* @param end the upper bound of the range of config counters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching config counters, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.ConfigCounter> getConfigCountersByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.ConfigCounter> orderByComparator) {
		return _configCounterLocalService.getConfigCountersByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of config counters.
	*
	* @return the number of config counters
	*/
	@Override
	public int getConfigCountersCount() {
		return _configCounterLocalService.getConfigCountersCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _configCounterLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _configCounterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _configCounterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _configCounterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the config counter in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param configCounter the config counter
	* @return the config counter that was updated
	*/
	@Override
	public org.opencps.dossiermgt.model.ConfigCounter updateConfigCounter(
		org.opencps.dossiermgt.model.ConfigCounter configCounter) {
		return _configCounterLocalService.updateConfigCounter(configCounter);
	}

	@Override
	public ConfigCounterLocalService getWrappedService() {
		return _configCounterLocalService;
	}

	@Override
	public void setWrappedService(
		ConfigCounterLocalService configCounterLocalService) {
		_configCounterLocalService = configCounterLocalService;
	}

	private ConfigCounterLocalService _configCounterLocalService;
}