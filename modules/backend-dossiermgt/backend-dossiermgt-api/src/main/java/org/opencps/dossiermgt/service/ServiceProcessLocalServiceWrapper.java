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
 * Provides a wrapper for {@link ServiceProcessLocalService}.
 *
 * @author huymq
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

	/**
	* Adds the service process to the database. Also notifies the appropriate model listeners.
	*
	* @param serviceProcess the service process
	* @return the service process that was added
	*/
	@Override
	public org.opencps.dossiermgt.model.ServiceProcess addServiceProcess(
		org.opencps.dossiermgt.model.ServiceProcess serviceProcess) {
		return _serviceProcessLocalService.addServiceProcess(serviceProcess);
	}

	@Override
	public org.opencps.dossiermgt.model.ServiceProcess adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return _serviceProcessLocalService.adminProcessData(objectData);
	}

	@Override
	public org.opencps.dossiermgt.model.ServiceProcess adminProcessDelete(
		Long id) {
		return _serviceProcessLocalService.adminProcessDelete(id);
	}

	@Override
	public void cloneServiceProcess(long serviceProcessId, long groupId,
		String processNo,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		_serviceProcessLocalService.cloneServiceProcess(serviceProcessId,
			groupId, processNo, serviceContext);
	}

	@Override
	public long countLucene(java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _serviceProcessLocalService.countLucene(params, searchContext);
	}

	/**
	* Creates a new service process with the primary key. Does not add the service process to the database.
	*
	* @param serviceProcessId the primary key for the new service process
	* @return the new service process
	*/
	@Override
	public org.opencps.dossiermgt.model.ServiceProcess createServiceProcess(
		long serviceProcessId) {
		return _serviceProcessLocalService.createServiceProcess(serviceProcessId);
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

	/**
	* Deletes the service process with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceProcessId the primary key of the service process
	* @return the service process that was removed
	* @throws PortalException if a service process with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.ServiceProcess deleteServiceProcess(
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
	public org.opencps.dossiermgt.model.ServiceProcess deleteServiceProcess(
		org.opencps.dossiermgt.model.ServiceProcess serviceProcess) {
		return _serviceProcessLocalService.deleteServiceProcess(serviceProcess);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _serviceProcessLocalService.dynamicQuery();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ServiceProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ServiceProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	@Override
	public org.opencps.dossiermgt.model.ServiceProcess fetchServiceProcess(
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
	public org.opencps.dossiermgt.model.ServiceProcess fetchServiceProcessByUuidAndGroupId(
		String uuid, long groupId) {
		return _serviceProcessLocalService.fetchServiceProcessByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _serviceProcessLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.ServiceProcess> getByG_ID(
		long groupId) {
		return _serviceProcessLocalService.getByG_ID(groupId);
	}

	@Override
	public org.opencps.dossiermgt.model.ServiceProcess getByG_PNO(
		long groupId, String processNo) {
		return _serviceProcessLocalService.getByG_PNO(groupId, processNo);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.ServiceProcess> getByServerNo(
		String serverNo) {
		return _serviceProcessLocalService.getByServerNo(serverNo);
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
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _serviceProcessLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _serviceProcessLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public org.opencps.dossiermgt.model.ServiceProcess getServiceByCode(
		long groupId, String serviceCode, String govAgencyCode,
		String dossierTemplateNo)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _serviceProcessLocalService.getServiceByCode(groupId,
			serviceCode, govAgencyCode, dossierTemplateNo);
	}

	/**
	* Returns the service process with the primary key.
	*
	* @param serviceProcessId the primary key of the service process
	* @return the service process
	* @throws PortalException if a service process with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.ServiceProcess getServiceProcess(
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
	public org.opencps.dossiermgt.model.ServiceProcess getServiceProcessByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _serviceProcessLocalService.getServiceProcessByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the service processes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ServiceProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of service processes
	* @param end the upper bound of the range of service processes (not inclusive)
	* @return the range of service processes
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.ServiceProcess> getServiceProcesses(
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
	public java.util.List<org.opencps.dossiermgt.model.ServiceProcess> getServiceProcessesByUuidAndCompanyId(
		String uuid, long companyId) {
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
	public java.util.List<org.opencps.dossiermgt.model.ServiceProcess> getServiceProcessesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.ServiceProcess> orderByComparator) {
		return _serviceProcessLocalService.getServiceProcessesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
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

	@Override
	public void initServiceProcess(long groupId,
		com.liferay.portal.kernel.service.ServiceContext context) {
		_serviceProcessLocalService.initServiceProcess(groupId, context);
	}

	@Override
	public org.opencps.dossiermgt.model.ServiceProcess removeServiceProcess(
		long serviceProcessId, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _serviceProcessLocalService.removeServiceProcess(serviceProcessId,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits searchLucene(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _serviceProcessLocalService.searchLucene(params, sorts, start,
			end, searchContext);
	}

	@Override
	public org.opencps.dossiermgt.model.ServiceProcess updateServiceProcess(
		long groupId, long serviceProcessId, String processNo,
		String processName, String description, Double durationCount,
		int durationUnit, long counter, boolean generateDossierNo,
		String dossierNoPattern, boolean generateDueDate,
		String dueDatePattern, boolean generatePassword,
		boolean directNotification, String serverNo, String paymentFee,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _serviceProcessLocalService.updateServiceProcess(groupId,
			serviceProcessId, processNo, processName, description,
			durationCount, durationUnit, counter, generateDossierNo,
			dossierNoPattern, generateDueDate, dueDatePattern,
			generatePassword, directNotification, serverNo, paymentFee, context);
	}

	/**
	* Updates the service process in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param serviceProcess the service process
	* @return the service process that was updated
	*/
	@Override
	public org.opencps.dossiermgt.model.ServiceProcess updateServiceProcess(
		org.opencps.dossiermgt.model.ServiceProcess serviceProcess) {
		return _serviceProcessLocalService.updateServiceProcess(serviceProcess);
	}

	@Override
	public org.opencps.dossiermgt.model.ServiceProcess updateServiceProcessDB(
		long userId, long groupId, String processNo, String processName,
		String description, Double durationCount, Integer durationUnit,
		boolean generatePassword, String serverNo, String serverName,
		String dossierNoPattern, String dueDatePattern,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _serviceProcessLocalService.updateServiceProcessDB(userId,
			groupId, processNo, processName, description, durationCount,
			durationUnit, generatePassword, serverNo, serverName,
			dossierNoPattern, dueDatePattern, serviceContext);
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