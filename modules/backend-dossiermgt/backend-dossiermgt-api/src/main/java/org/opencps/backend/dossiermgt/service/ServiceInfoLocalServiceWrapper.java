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

package org.opencps.backend.dossiermgt.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ServiceInfoLocalService}.
 *
 * @author huymq
 * @see ServiceInfoLocalService
 * @generated
 */
@ProviderType
public class ServiceInfoLocalServiceWrapper implements ServiceInfoLocalService,
	ServiceWrapper<ServiceInfoLocalService> {
	public ServiceInfoLocalServiceWrapper(
		ServiceInfoLocalService serviceInfoLocalService) {
		_serviceInfoLocalService = serviceInfoLocalService;
	}

	@Override
	public boolean hasFileTemplateServiceInfo(long fileTemplateId,
		long serviceInfoId) {
		return _serviceInfoLocalService.hasFileTemplateServiceInfo(fileTemplateId,
			serviceInfoId);
	}

	@Override
	public boolean hasFileTemplateServiceInfos(long fileTemplateId) {
		return _serviceInfoLocalService.hasFileTemplateServiceInfos(fileTemplateId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _serviceInfoLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _serviceInfoLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _serviceInfoLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _serviceInfoLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _serviceInfoLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _serviceInfoLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public int getFileTemplateServiceInfosCount(long fileTemplateId) {
		return _serviceInfoLocalService.getFileTemplateServiceInfosCount(fileTemplateId);
	}

	/**
	* Returns the number of service infos.
	*
	* @return the number of service infos
	*/
	@Override
	public int getServiceInfosCount() {
		return _serviceInfoLocalService.getServiceInfosCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _serviceInfoLocalService.getOSGiServiceIdentifier();
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
		return _serviceInfoLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.backend.dossiermgt.model.impl.ServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _serviceInfoLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.backend.dossiermgt.model.impl.ServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _serviceInfoLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	@Override
	public java.util.List<org.opencps.backend.dossiermgt.model.ServiceInfo> getFileTemplateServiceInfos(
		long fileTemplateId) {
		return _serviceInfoLocalService.getFileTemplateServiceInfos(fileTemplateId);
	}

	@Override
	public java.util.List<org.opencps.backend.dossiermgt.model.ServiceInfo> getFileTemplateServiceInfos(
		long fileTemplateId, int start, int end) {
		return _serviceInfoLocalService.getFileTemplateServiceInfos(fileTemplateId,
			start, end);
	}

	@Override
	public java.util.List<org.opencps.backend.dossiermgt.model.ServiceInfo> getFileTemplateServiceInfos(
		long fileTemplateId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.backend.dossiermgt.model.ServiceInfo> orderByComparator) {
		return _serviceInfoLocalService.getFileTemplateServiceInfos(fileTemplateId,
			start, end, orderByComparator);
	}

	/**
	* Returns a range of all the service infos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.backend.dossiermgt.model.impl.ServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of service infos
	* @param end the upper bound of the range of service infos (not inclusive)
	* @return the range of service infos
	*/
	@Override
	public java.util.List<org.opencps.backend.dossiermgt.model.ServiceInfo> getServiceInfos(
		int start, int end) {
		return _serviceInfoLocalService.getServiceInfos(start, end);
	}

	@Override
	public java.util.List<org.opencps.backend.dossiermgt.model.ServiceInfo> getServiceInfosByCompanyId(
		long companyId) {
		return _serviceInfoLocalService.getServiceInfosByCompanyId(companyId);
	}

	@Override
	public java.util.List<org.opencps.backend.dossiermgt.model.ServiceInfo> getServiceInfosByCompanyId(
		long companyId, int start, int end) {
		return _serviceInfoLocalService.getServiceInfosByCompanyId(companyId,
			start, end);
	}

	@Override
	public java.util.List<org.opencps.backend.dossiermgt.model.ServiceInfo> getServiceInfosByGroupId(
		long groupId) {
		return _serviceInfoLocalService.getServiceInfosByGroupId(groupId);
	}

	@Override
	public java.util.List<org.opencps.backend.dossiermgt.model.ServiceInfo> getServiceInfosByGroupId(
		long groupId, int start, int end) {
		return _serviceInfoLocalService.getServiceInfosByGroupId(groupId,
			start, end);
	}

	/**
	* Returns all the service infos matching the UUID and company.
	*
	* @param uuid the UUID of the service infos
	* @param companyId the primary key of the company
	* @return the matching service infos, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.backend.dossiermgt.model.ServiceInfo> getServiceInfosByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _serviceInfoLocalService.getServiceInfosByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of service infos matching the UUID and company.
	*
	* @param uuid the UUID of the service infos
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of service infos
	* @param end the upper bound of the range of service infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching service infos, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.backend.dossiermgt.model.ServiceInfo> getServiceInfosByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.backend.dossiermgt.model.ServiceInfo> orderByComparator) {
		return _serviceInfoLocalService.getServiceInfosByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
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
		return _serviceInfoLocalService.dynamicQueryCount(dynamicQuery);
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
		return _serviceInfoLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	/**
	* Returns the fileTemplateIds of the file templates associated with the service info.
	*
	* @param serviceInfoId the serviceInfoId of the service info
	* @return long[] the fileTemplateIds of file templates associated with the service info
	*/
	@Override
	public long[] getFileTemplatePrimaryKeys(long serviceInfoId) {
		return _serviceInfoLocalService.getFileTemplatePrimaryKeys(serviceInfoId);
	}

	/**
	* Adds the service info to the database. Also notifies the appropriate model listeners.
	*
	* @param serviceInfo the service info
	* @return the service info that was added
	*/
	@Override
	public org.opencps.backend.dossiermgt.model.ServiceInfo addServiceInfo(
		org.opencps.backend.dossiermgt.model.ServiceInfo serviceInfo) {
		return _serviceInfoLocalService.addServiceInfo(serviceInfo);
	}

	/**
	* Creates a new service info with the primary key. Does not add the service info to the database.
	*
	* @param serviceInfoId the primary key for the new service info
	* @return the new service info
	*/
	@Override
	public org.opencps.backend.dossiermgt.model.ServiceInfo createServiceInfo(
		long serviceInfoId) {
		return _serviceInfoLocalService.createServiceInfo(serviceInfoId);
	}

	/**
	* Deletes the service info with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceInfoId the primary key of the service info
	* @return the service info that was removed
	* @throws PortalException if a service info with the primary key could not be found
	*/
	@Override
	public org.opencps.backend.dossiermgt.model.ServiceInfo deleteServiceInfo(
		long serviceInfoId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _serviceInfoLocalService.deleteServiceInfo(serviceInfoId);
	}

	/**
	* Deletes the service info from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceInfo the service info
	* @return the service info that was removed
	*/
	@Override
	public org.opencps.backend.dossiermgt.model.ServiceInfo deleteServiceInfo(
		org.opencps.backend.dossiermgt.model.ServiceInfo serviceInfo) {
		return _serviceInfoLocalService.deleteServiceInfo(serviceInfo);
	}

	@Override
	public org.opencps.backend.dossiermgt.model.ServiceInfo fetchServiceInfo(
		long serviceInfoId) {
		return _serviceInfoLocalService.fetchServiceInfo(serviceInfoId);
	}

	/**
	* Returns the service info matching the UUID and group.
	*
	* @param uuid the service info's UUID
	* @param groupId the primary key of the group
	* @return the matching service info, or <code>null</code> if a matching service info could not be found
	*/
	@Override
	public org.opencps.backend.dossiermgt.model.ServiceInfo fetchServiceInfoByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _serviceInfoLocalService.fetchServiceInfoByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the service info with the primary key.
	*
	* @param serviceInfoId the primary key of the service info
	* @return the service info
	* @throws PortalException if a service info with the primary key could not be found
	*/
	@Override
	public org.opencps.backend.dossiermgt.model.ServiceInfo getServiceInfo(
		long serviceInfoId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _serviceInfoLocalService.getServiceInfo(serviceInfoId);
	}

	/**
	* Returns the service info matching the UUID and group.
	*
	* @param uuid the service info's UUID
	* @param groupId the primary key of the group
	* @return the matching service info
	* @throws PortalException if a matching service info could not be found
	*/
	@Override
	public org.opencps.backend.dossiermgt.model.ServiceInfo getServiceInfoByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _serviceInfoLocalService.getServiceInfoByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Updates the service info in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param serviceInfo the service info
	* @return the service info that was updated
	*/
	@Override
	public org.opencps.backend.dossiermgt.model.ServiceInfo updateServiceInfo(
		org.opencps.backend.dossiermgt.model.ServiceInfo serviceInfo) {
		return _serviceInfoLocalService.updateServiceInfo(serviceInfo);
	}

	@Override
	public void addFileTemplateServiceInfo(long fileTemplateId,
		long serviceInfoId) {
		_serviceInfoLocalService.addFileTemplateServiceInfo(fileTemplateId,
			serviceInfoId);
	}

	@Override
	public void addFileTemplateServiceInfo(long fileTemplateId,
		org.opencps.backend.dossiermgt.model.ServiceInfo serviceInfo) {
		_serviceInfoLocalService.addFileTemplateServiceInfo(fileTemplateId,
			serviceInfo);
	}

	@Override
	public void addFileTemplateServiceInfos(long fileTemplateId,
		java.util.List<org.opencps.backend.dossiermgt.model.ServiceInfo> serviceInfos) {
		_serviceInfoLocalService.addFileTemplateServiceInfos(fileTemplateId,
			serviceInfos);
	}

	@Override
	public void addFileTemplateServiceInfos(long fileTemplateId,
		long[] serviceInfoIds) {
		_serviceInfoLocalService.addFileTemplateServiceInfos(fileTemplateId,
			serviceInfoIds);
	}

	@Override
	public void clearFileTemplateServiceInfos(long fileTemplateId) {
		_serviceInfoLocalService.clearFileTemplateServiceInfos(fileTemplateId);
	}

	@Override
	public void deleteFileTemplateServiceInfo(long fileTemplateId,
		long serviceInfoId) {
		_serviceInfoLocalService.deleteFileTemplateServiceInfo(fileTemplateId,
			serviceInfoId);
	}

	@Override
	public void deleteFileTemplateServiceInfo(long fileTemplateId,
		org.opencps.backend.dossiermgt.model.ServiceInfo serviceInfo) {
		_serviceInfoLocalService.deleteFileTemplateServiceInfo(fileTemplateId,
			serviceInfo);
	}

	@Override
	public void deleteFileTemplateServiceInfos(long fileTemplateId,
		java.util.List<org.opencps.backend.dossiermgt.model.ServiceInfo> serviceInfos) {
		_serviceInfoLocalService.deleteFileTemplateServiceInfos(fileTemplateId,
			serviceInfos);
	}

	@Override
	public void deleteFileTemplateServiceInfos(long fileTemplateId,
		long[] serviceInfoIds) {
		_serviceInfoLocalService.deleteFileTemplateServiceInfos(fileTemplateId,
			serviceInfoIds);
	}

	@Override
	public void setFileTemplateServiceInfos(long fileTemplateId,
		long[] serviceInfoIds) {
		_serviceInfoLocalService.setFileTemplateServiceInfos(fileTemplateId,
			serviceInfoIds);
	}

	@Override
	public ServiceInfoLocalService getWrappedService() {
		return _serviceInfoLocalService;
	}

	@Override
	public void setWrappedService(
		ServiceInfoLocalService serviceInfoLocalService) {
		_serviceInfoLocalService = serviceInfoLocalService;
	}

	private ServiceInfoLocalService _serviceInfoLocalService;
}