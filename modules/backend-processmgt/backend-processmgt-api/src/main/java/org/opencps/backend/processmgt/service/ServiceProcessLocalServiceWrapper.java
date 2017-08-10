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

package org.opencps.backend.processmgt.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ServiceProcessLocalService}.
 *
 * @author khoavu
 * @see ServiceProcessLocalService
 * @generated
 */
@ProviderType
public class ServiceProcessLocalServiceWrapper
	implements ServiceProcessLocalService,
		ServiceWrapper<ServiceProcessLocalService> {
	public ServiceProcessLocalServiceWrapper(
		ServiceProcessLocalService serviceProcessLocalService) {
		_serviceProcessLocalService = serviceProcessLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _serviceProcessLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _serviceProcessLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _serviceProcessLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _serviceProcessLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _serviceProcessLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _serviceProcessLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of service processes.
	*
	* @return the number of service processes
	*/
	@Override
	public int getServiceProcessesCount() {
		return _serviceProcessLocalService.getServiceProcessesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _serviceProcessLocalService.getOSGiServiceIdentifier();
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
		return _serviceProcessLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.backend.processmgt.model.impl.ServiceProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _serviceProcessLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.backend.processmgt.model.impl.ServiceProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _serviceProcessLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns a range of all the service processes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.backend.processmgt.model.impl.ServiceProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of service processes
	* @param end the upper bound of the range of service processes (not inclusive)
	* @return the range of service processes
	*/
	@Override
	public java.util.List<org.opencps.backend.processmgt.model.ServiceProcess> getServiceProcesses(
		int start, int end) {
		return _serviceProcessLocalService.getServiceProcesses(start, end);
	}

	/**
	* Returns all the service processes matching the UUID and company.
	*
	* @param uuid the UUID of the service processes
	* @param companyId the primary key of the company
	* @return the matching service processes, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.backend.processmgt.model.ServiceProcess> getServiceProcessesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _serviceProcessLocalService.getServiceProcessesByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of service processes matching the UUID and company.
	*
	* @param uuid the UUID of the service processes
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of service processes
	* @param end the upper bound of the range of service processes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching service processes, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.backend.processmgt.model.ServiceProcess> getServiceProcessesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.backend.processmgt.model.ServiceProcess> orderByComparator) {
		return _serviceProcessLocalService.getServiceProcessesByUuidAndCompanyId(uuid,
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
		return _serviceProcessLocalService.dynamicQueryCount(dynamicQuery);
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
		return _serviceProcessLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	/**
	* Adds the service process to the database. Also notifies the appropriate model listeners.
	*
	* @param serviceProcess the service process
	* @return the service process that was added
	*/
	@Override
	public org.opencps.backend.processmgt.model.ServiceProcess addServiceProcess(
		org.opencps.backend.processmgt.model.ServiceProcess serviceProcess) {
		return _serviceProcessLocalService.addServiceProcess(serviceProcess);
	}

	/**
	* Creates a new service process with the primary key. Does not add the service process to the database.
	*
	* @param serviceProcessId the primary key for the new service process
	* @return the new service process
	*/
	@Override
	public org.opencps.backend.processmgt.model.ServiceProcess createServiceProcess(
		long serviceProcessId) {
		return _serviceProcessLocalService.createServiceProcess(serviceProcessId);
	}

	/**
	* Deletes the service process with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceProcessId the primary key of the service process
	* @return the service process that was removed
	* @throws PortalException if a service process with the primary key could not be found
	*/
	@Override
	public org.opencps.backend.processmgt.model.ServiceProcess deleteServiceProcess(
		long serviceProcessId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _serviceProcessLocalService.deleteServiceProcess(serviceProcessId);
	}

	/**
	* Deletes the service process from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceProcess the service process
	* @return the service process that was removed
	*/
	@Override
	public org.opencps.backend.processmgt.model.ServiceProcess deleteServiceProcess(
		org.opencps.backend.processmgt.model.ServiceProcess serviceProcess) {
		return _serviceProcessLocalService.deleteServiceProcess(serviceProcess);
	}

	@Override
	public org.opencps.backend.processmgt.model.ServiceProcess fetchServiceProcess(
		long serviceProcessId) {
		return _serviceProcessLocalService.fetchServiceProcess(serviceProcessId);
	}

	/**
	* Returns the service process matching the UUID and group.
	*
	* @param uuid the service process's UUID
	* @param groupId the primary key of the group
	* @return the matching service process, or <code>null</code> if a matching service process could not be found
	*/
	@Override
	public org.opencps.backend.processmgt.model.ServiceProcess fetchServiceProcessByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _serviceProcessLocalService.fetchServiceProcessByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the service process with the primary key.
	*
	* @param serviceProcessId the primary key of the service process
	* @return the service process
	* @throws PortalException if a service process with the primary key could not be found
	*/
	@Override
	public org.opencps.backend.processmgt.model.ServiceProcess getServiceProcess(
		long serviceProcessId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _serviceProcessLocalService.getServiceProcess(serviceProcessId);
	}

	/**
	* Returns the service process matching the UUID and group.
	*
	* @param uuid the service process's UUID
	* @param groupId the primary key of the group
	* @return the matching service process
	* @throws PortalException if a matching service process could not be found
	*/
	@Override
	public org.opencps.backend.processmgt.model.ServiceProcess getServiceProcessByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _serviceProcessLocalService.getServiceProcessByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Updates the service process in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param serviceProcess the service process
	* @return the service process that was updated
	*/
	@Override
	public org.opencps.backend.processmgt.model.ServiceProcess updateServiceProcess(
		org.opencps.backend.processmgt.model.ServiceProcess serviceProcess) {
		return _serviceProcessLocalService.updateServiceProcess(serviceProcess);
	}

	@Override
	public ServiceProcessLocalService getWrappedService() {
		return _serviceProcessLocalService;
	}

	@Override
	public void setWrappedService(
		ServiceProcessLocalService serviceProcessLocalService) {
		_serviceProcessLocalService = serviceProcessLocalService;
	}

	private ServiceProcessLocalService _serviceProcessLocalService;
}