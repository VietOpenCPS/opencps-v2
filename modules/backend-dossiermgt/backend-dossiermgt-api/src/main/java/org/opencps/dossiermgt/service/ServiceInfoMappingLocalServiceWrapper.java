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
 * Provides a wrapper for {@link ServiceInfoMappingLocalService}.
 *
 * @author huymq
 * @see ServiceInfoMappingLocalService
 * @generated
 */
@ProviderType
public class ServiceInfoMappingLocalServiceWrapper
	implements ServiceInfoMappingLocalService,
		ServiceWrapper<ServiceInfoMappingLocalService> {
	public ServiceInfoMappingLocalServiceWrapper(
		ServiceInfoMappingLocalService serviceInfoMappingLocalService) {
		_serviceInfoMappingLocalService = serviceInfoMappingLocalService;
	}

	@Override
	public org.opencps.dossiermgt.model.ServiceInfoMapping addServiceInfoMapping(
		long groupId, long companyId, long userId, String serviceCode,
		String serviceCodeDVCQG, String serviceNameDVCQG, int synced)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _serviceInfoMappingLocalService.addServiceInfoMapping(groupId,
			companyId, userId, serviceCode, serviceCodeDVCQG, serviceNameDVCQG,
			synced);
	}

	/**
	* Adds the service info mapping to the database. Also notifies the appropriate model listeners.
	*
	* @param serviceInfoMapping the service info mapping
	* @return the service info mapping that was added
	*/
	@Override
	public org.opencps.dossiermgt.model.ServiceInfoMapping addServiceInfoMapping(
		org.opencps.dossiermgt.model.ServiceInfoMapping serviceInfoMapping) {
		return _serviceInfoMappingLocalService.addServiceInfoMapping(serviceInfoMapping);
	}

	@Override
	public org.opencps.dossiermgt.model.ServiceInfoMapping adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return _serviceInfoMappingLocalService.adminProcessData(objectData);
	}

	@Override
	public org.opencps.dossiermgt.model.ServiceInfoMapping adminProcessDelete(
		Long id) {
		return _serviceInfoMappingLocalService.adminProcessDelete(id);
	}

	/**
	* Creates a new service info mapping with the primary key. Does not add the service info mapping to the database.
	*
	* @param serviceInfoMappingId the primary key for the new service info mapping
	* @return the new service info mapping
	*/
	@Override
	public org.opencps.dossiermgt.model.ServiceInfoMapping createServiceInfoMapping(
		long serviceInfoMappingId) {
		return _serviceInfoMappingLocalService.createServiceInfoMapping(serviceInfoMappingId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _serviceInfoMappingLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the service info mapping with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceInfoMappingId the primary key of the service info mapping
	* @return the service info mapping that was removed
	* @throws PortalException if a service info mapping with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.ServiceInfoMapping deleteServiceInfoMapping(
		long serviceInfoMappingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _serviceInfoMappingLocalService.deleteServiceInfoMapping(serviceInfoMappingId);
	}

	@Override
	public boolean deleteServiceInfoMapping(long groupId, String serviceCode) {
		return _serviceInfoMappingLocalService.deleteServiceInfoMapping(groupId,
			serviceCode);
	}

	/**
	* Deletes the service info mapping from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceInfoMapping the service info mapping
	* @return the service info mapping that was removed
	*/
	@Override
	public org.opencps.dossiermgt.model.ServiceInfoMapping deleteServiceInfoMapping(
		org.opencps.dossiermgt.model.ServiceInfoMapping serviceInfoMapping) {
		return _serviceInfoMappingLocalService.deleteServiceInfoMapping(serviceInfoMapping);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _serviceInfoMappingLocalService.dynamicQuery();
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
		return _serviceInfoMappingLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ServiceInfoMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _serviceInfoMappingLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ServiceInfoMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _serviceInfoMappingLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
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
		return _serviceInfoMappingLocalService.dynamicQueryCount(dynamicQuery);
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
		return _serviceInfoMappingLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.dossiermgt.model.ServiceInfoMapping fetchByGID_SCDVCQG(
		long groupId, String serviceCodeDVCQG) {
		return _serviceInfoMappingLocalService.fetchByGID_SCDVCQG(groupId,
			serviceCodeDVCQG);
	}

	@Override
	public org.opencps.dossiermgt.model.ServiceInfoMapping fetchDVCQGServiceCode(
		long groupId, String serviceCode) {
		return _serviceInfoMappingLocalService.fetchDVCQGServiceCode(groupId,
			serviceCode);
	}

	@Override
	public org.opencps.dossiermgt.model.ServiceInfoMapping fetchServiceInfoMapping(
		long serviceInfoMappingId) {
		return _serviceInfoMappingLocalService.fetchServiceInfoMapping(serviceInfoMappingId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _serviceInfoMappingLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _serviceInfoMappingLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _serviceInfoMappingLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _serviceInfoMappingLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the service info mapping with the primary key.
	*
	* @param serviceInfoMappingId the primary key of the service info mapping
	* @return the service info mapping
	* @throws PortalException if a service info mapping with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.ServiceInfoMapping getServiceInfoMapping(
		long serviceInfoMappingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _serviceInfoMappingLocalService.getServiceInfoMapping(serviceInfoMappingId);
	}

	/**
	* Returns a range of all the service info mappings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ServiceInfoMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of service info mappings
	* @param end the upper bound of the range of service info mappings (not inclusive)
	* @return the range of service info mappings
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.ServiceInfoMapping> getServiceInfoMappings(
		int start, int end) {
		return _serviceInfoMappingLocalService.getServiceInfoMappings(start, end);
	}

	/**
	* Returns the number of service info mappings.
	*
	* @return the number of service info mappings
	*/
	@Override
	public int getServiceInfoMappingsCount() {
		return _serviceInfoMappingLocalService.getServiceInfoMappingsCount();
	}

	@Override
	public boolean removeServiceInfoMapping(long mappingId) {
		return _serviceInfoMappingLocalService.removeServiceInfoMapping(mappingId);
	}

	/**
	* Updates the service info mapping in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param serviceInfoMapping the service info mapping
	* @return the service info mapping that was updated
	*/
	@Override
	public org.opencps.dossiermgt.model.ServiceInfoMapping updateServiceInfoMapping(
		org.opencps.dossiermgt.model.ServiceInfoMapping serviceInfoMapping) {
		return _serviceInfoMappingLocalService.updateServiceInfoMapping(serviceInfoMapping);
	}

	@Override
	public ServiceInfoMappingLocalService getWrappedService() {
		return _serviceInfoMappingLocalService;
	}

	@Override
	public void setWrappedService(
		ServiceInfoMappingLocalService serviceInfoMappingLocalService) {
		_serviceInfoMappingLocalService = serviceInfoMappingLocalService;
	}

	private ServiceInfoMappingLocalService _serviceInfoMappingLocalService;
}