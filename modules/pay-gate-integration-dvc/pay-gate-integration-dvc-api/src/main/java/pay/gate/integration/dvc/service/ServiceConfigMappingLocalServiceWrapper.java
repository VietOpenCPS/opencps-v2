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

package pay.gate.integration.dvc.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ServiceConfigMappingLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ServiceConfigMappingLocalService
 * @generated
 */
@ProviderType
public class ServiceConfigMappingLocalServiceWrapper
	implements ServiceConfigMappingLocalService,
		ServiceWrapper<ServiceConfigMappingLocalService> {
	public ServiceConfigMappingLocalServiceWrapper(
		ServiceConfigMappingLocalService serviceConfigMappingLocalService) {
		_serviceConfigMappingLocalService = serviceConfigMappingLocalService;
	}

	/**
	* Adds the service config mapping to the database. Also notifies the appropriate model listeners.
	*
	* @param serviceConfigMapping the service config mapping
	* @return the service config mapping that was added
	*/
	@Override
	public pay.gate.integration.dvc.model.ServiceConfigMapping addServiceConfigMapping(
		pay.gate.integration.dvc.model.ServiceConfigMapping serviceConfigMapping) {
		return _serviceConfigMappingLocalService.addServiceConfigMapping(serviceConfigMapping);
	}

	/**
	* Creates a new service config mapping with the primary key. Does not add the service config mapping to the database.
	*
	* @param serviceConfigMappingId the primary key for the new service config mapping
	* @return the new service config mapping
	*/
	@Override
	public pay.gate.integration.dvc.model.ServiceConfigMapping createServiceConfigMapping(
		long serviceConfigMappingId) {
		return _serviceConfigMappingLocalService.createServiceConfigMapping(serviceConfigMappingId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _serviceConfigMappingLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the service config mapping with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceConfigMappingId the primary key of the service config mapping
	* @return the service config mapping that was removed
	* @throws PortalException if a service config mapping with the primary key could not be found
	*/
	@Override
	public pay.gate.integration.dvc.model.ServiceConfigMapping deleteServiceConfigMapping(
		long serviceConfigMappingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _serviceConfigMappingLocalService.deleteServiceConfigMapping(serviceConfigMappingId);
	}

	/**
	* Deletes the service config mapping from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceConfigMapping the service config mapping
	* @return the service config mapping that was removed
	*/
	@Override
	public pay.gate.integration.dvc.model.ServiceConfigMapping deleteServiceConfigMapping(
		pay.gate.integration.dvc.model.ServiceConfigMapping serviceConfigMapping) {
		return _serviceConfigMappingLocalService.deleteServiceConfigMapping(serviceConfigMapping);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _serviceConfigMappingLocalService.dynamicQuery();
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
		return _serviceConfigMappingLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link pay.gate.integration.dvc.model.impl.ServiceConfigMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _serviceConfigMappingLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link pay.gate.integration.dvc.model.impl.ServiceConfigMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _serviceConfigMappingLocalService.dynamicQuery(dynamicQuery,
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
		return _serviceConfigMappingLocalService.dynamicQueryCount(dynamicQuery);
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
		return _serviceConfigMappingLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public pay.gate.integration.dvc.model.ServiceConfigMapping fetchServiceConfigMapping(
		long serviceConfigMappingId) {
		return _serviceConfigMappingLocalService.fetchServiceConfigMapping(serviceConfigMappingId);
	}

	/**
	* Returns the service config mapping matching the UUID and group.
	*
	* @param uuid the service config mapping's UUID
	* @param groupId the primary key of the group
	* @return the matching service config mapping, or <code>null</code> if a matching service config mapping could not be found
	*/
	@Override
	public pay.gate.integration.dvc.model.ServiceConfigMapping fetchServiceConfigMappingByUuidAndGroupId(
		String uuid, long groupId) {
		return _serviceConfigMappingLocalService.fetchServiceConfigMappingByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _serviceConfigMappingLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _serviceConfigMappingLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _serviceConfigMappingLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _serviceConfigMappingLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _serviceConfigMappingLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the service config mapping with the primary key.
	*
	* @param serviceConfigMappingId the primary key of the service config mapping
	* @return the service config mapping
	* @throws PortalException if a service config mapping with the primary key could not be found
	*/
	@Override
	public pay.gate.integration.dvc.model.ServiceConfigMapping getServiceConfigMapping(
		long serviceConfigMappingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _serviceConfigMappingLocalService.getServiceConfigMapping(serviceConfigMappingId);
	}

	/**
	* Returns the service config mapping matching the UUID and group.
	*
	* @param uuid the service config mapping's UUID
	* @param groupId the primary key of the group
	* @return the matching service config mapping
	* @throws PortalException if a matching service config mapping could not be found
	*/
	@Override
	public pay.gate.integration.dvc.model.ServiceConfigMapping getServiceConfigMappingByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _serviceConfigMappingLocalService.getServiceConfigMappingByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the service config mappings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link pay.gate.integration.dvc.model.impl.ServiceConfigMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of service config mappings
	* @param end the upper bound of the range of service config mappings (not inclusive)
	* @return the range of service config mappings
	*/
	@Override
	public java.util.List<pay.gate.integration.dvc.model.ServiceConfigMapping> getServiceConfigMappings(
		int start, int end) {
		return _serviceConfigMappingLocalService.getServiceConfigMappings(start,
			end);
	}

	/**
	* Returns all the service config mappings matching the UUID and company.
	*
	* @param uuid the UUID of the service config mappings
	* @param companyId the primary key of the company
	* @return the matching service config mappings, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<pay.gate.integration.dvc.model.ServiceConfigMapping> getServiceConfigMappingsByUuidAndCompanyId(
		String uuid, long companyId) {
		return _serviceConfigMappingLocalService.getServiceConfigMappingsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of service config mappings matching the UUID and company.
	*
	* @param uuid the UUID of the service config mappings
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of service config mappings
	* @param end the upper bound of the range of service config mappings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching service config mappings, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<pay.gate.integration.dvc.model.ServiceConfigMapping> getServiceConfigMappingsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<pay.gate.integration.dvc.model.ServiceConfigMapping> orderByComparator) {
		return _serviceConfigMappingLocalService.getServiceConfigMappingsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of service config mappings.
	*
	* @return the number of service config mappings
	*/
	@Override
	public int getServiceConfigMappingsCount() {
		return _serviceConfigMappingLocalService.getServiceConfigMappingsCount();
	}

	@Override
	public pay.gate.integration.dvc.model.ServiceConfigMapping initServiceConfigMaping(
		long groupId, long serviceConfigMappingId, long apdungDVCId,
		String maDVC, String tenDVC, String maTTHC, String tenTTHC,
		String tenCQBH, String tenLinhVuc, String apdungDVC, String maCQTH,
		int mucdo, com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _serviceConfigMappingLocalService.initServiceConfigMaping(groupId,
			serviceConfigMappingId, apdungDVCId, maDVC, tenDVC, maTTHC,
			tenTTHC, tenCQBH, tenLinhVuc, apdungDVC, maCQTH, mucdo, context);
	}

	@Override
	public pay.gate.integration.dvc.model.ServiceConfigMapping removeServiceConfigMapping(
		long groupId, long serviceConfigMappingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _serviceConfigMappingLocalService.removeServiceConfigMapping(groupId,
			serviceConfigMappingId);
	}

	/**
	* Updates the service config mapping in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param serviceConfigMapping the service config mapping
	* @return the service config mapping that was updated
	*/
	@Override
	public pay.gate.integration.dvc.model.ServiceConfigMapping updateServiceConfigMapping(
		pay.gate.integration.dvc.model.ServiceConfigMapping serviceConfigMapping) {
		return _serviceConfigMappingLocalService.updateServiceConfigMapping(serviceConfigMapping);
	}

	@Override
	public ServiceConfigMappingLocalService getWrappedService() {
		return _serviceConfigMappingLocalService;
	}

	@Override
	public void setWrappedService(
		ServiceConfigMappingLocalService serviceConfigMappingLocalService) {
		_serviceConfigMappingLocalService = serviceConfigMappingLocalService;
	}

	private ServiceConfigMappingLocalService _serviceConfigMappingLocalService;
}