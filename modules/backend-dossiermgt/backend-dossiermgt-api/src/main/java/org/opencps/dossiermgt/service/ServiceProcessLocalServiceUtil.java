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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for ServiceProcess. This utility wraps
 * {@link org.opencps.dossiermgt.service.impl.ServiceProcessLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author huymq
 * @see ServiceProcessLocalService
 * @see org.opencps.dossiermgt.service.base.ServiceProcessLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.ServiceProcessLocalServiceImpl
 * @generated
 */
@ProviderType
public class ServiceProcessLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.ServiceProcessLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the service process to the database. Also notifies the appropriate model listeners.
	*
	* @param serviceProcess the service process
	* @return the service process that was added
	*/
	public static org.opencps.dossiermgt.model.ServiceProcess addServiceProcess(
		org.opencps.dossiermgt.model.ServiceProcess serviceProcess) {
		return getService().addServiceProcess(serviceProcess);
	}

	public static org.opencps.dossiermgt.model.ServiceProcess adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return getService().adminProcessData(objectData);
	}

	public static org.opencps.dossiermgt.model.ServiceProcess adminProcessDelete(
		Long id) {
		return getService().adminProcessDelete(id);
	}

	public static void cloneServiceProcess(long serviceProcessId, long groupId,
		String processNo,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.cloneServiceProcess(serviceProcessId, groupId, processNo,
			serviceContext);
	}

	public static long countLucene(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return getService().countLucene(params, searchContext);
	}

	/**
	* Creates a new service process with the primary key. Does not add the service process to the database.
	*
	* @param serviceProcessId the primary key for the new service process
	* @return the new service process
	*/
	public static org.opencps.dossiermgt.model.ServiceProcess createServiceProcess(
		long serviceProcessId) {
		return getService().createServiceProcess(serviceProcessId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the service process with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceProcessId the primary key of the service process
	* @return the service process that was removed
	* @throws PortalException if a service process with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.ServiceProcess deleteServiceProcess(
		long serviceProcessId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteServiceProcess(serviceProcessId);
	}

	/**
	* Deletes the service process from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceProcess the service process
	* @return the service process that was removed
	*/
	public static org.opencps.dossiermgt.model.ServiceProcess deleteServiceProcess(
		org.opencps.dossiermgt.model.ServiceProcess serviceProcess) {
		return getService().deleteServiceProcess(serviceProcess);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static org.opencps.dossiermgt.model.ServiceProcess fetchServiceProcess(
		long serviceProcessId) {
		return getService().fetchServiceProcess(serviceProcessId);
	}

	/**
	* Returns the service process matching the UUID and group.
	*
	* @param uuid the service process's UUID
	* @param groupId the primary key of the group
	* @return the matching service process, or <code>null</code> if a matching service process could not be found
	*/
	public static org.opencps.dossiermgt.model.ServiceProcess fetchServiceProcessByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchServiceProcessByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static java.util.List<org.opencps.dossiermgt.model.ServiceProcess> getByG_ID(
		long groupId) {
		return getService().getByG_ID(groupId);
	}

	public static org.opencps.dossiermgt.model.ServiceProcess getByG_PNO(
		long groupId, String processNo) {
		return getService().getByG_PNO(groupId, processNo);
	}

	public static java.util.List<org.opencps.dossiermgt.model.ServiceProcess> getByServerNo(
		String serverNo) {
		return getService().getByServerNo(serverNo);
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	public static org.opencps.dossiermgt.model.ServiceProcess getServiceByCode(
		long groupId, String serviceCode, String govAgencyCode,
		String dossierTemplateNo)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getServiceByCode(groupId, serviceCode, govAgencyCode,
			dossierTemplateNo);
	}

	/**
	* Returns the service process with the primary key.
	*
	* @param serviceProcessId the primary key of the service process
	* @return the service process
	* @throws PortalException if a service process with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.ServiceProcess getServiceProcess(
		long serviceProcessId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getServiceProcess(serviceProcessId);
	}

	/**
	* Returns the service process matching the UUID and group.
	*
	* @param uuid the service process's UUID
	* @param groupId the primary key of the group
	* @return the matching service process
	* @throws PortalException if a matching service process could not be found
	*/
	public static org.opencps.dossiermgt.model.ServiceProcess getServiceProcessByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getServiceProcessByUuidAndGroupId(uuid, groupId);
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
	public static java.util.List<org.opencps.dossiermgt.model.ServiceProcess> getServiceProcesses(
		int start, int end) {
		return getService().getServiceProcesses(start, end);
	}

	/**
	* Returns all the service processes matching the UUID and company.
	*
	* @param uuid the UUID of the service processes
	* @param companyId the primary key of the company
	* @return the matching service processes, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.dossiermgt.model.ServiceProcess> getServiceProcessesByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService()
				   .getServiceProcessesByUuidAndCompanyId(uuid, companyId);
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
	public static java.util.List<org.opencps.dossiermgt.model.ServiceProcess> getServiceProcessesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.ServiceProcess> orderByComparator) {
		return getService()
				   .getServiceProcessesByUuidAndCompanyId(uuid, companyId,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of service processes.
	*
	* @return the number of service processes
	*/
	public static int getServiceProcessesCount() {
		return getService().getServiceProcessesCount();
	}

	public static void initServiceProcess(long groupId,
		com.liferay.portal.kernel.service.ServiceContext context) {
		getService().initServiceProcess(groupId, context);
	}

	public static org.opencps.dossiermgt.model.ServiceProcess removeServiceProcess(
		long serviceProcessId, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().removeServiceProcess(serviceProcessId, groupId);
	}

	public static com.liferay.portal.kernel.search.Hits searchLucene(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return getService()
				   .searchLucene(params, sorts, start, end, searchContext);
	}

	public static org.opencps.dossiermgt.model.ServiceProcess updateServiceProcess(
		long groupId, long serviceProcessId, String processNo,
		String processName, String description, Double durationCount,
		int durationUnit, long counter, boolean generateDossierNo,
		String dossierNoPattern, boolean generateDueDate,
		String dueDatePattern, boolean generatePassword,
		boolean directNotification, String serverNo, String paymentFee,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateServiceProcess(groupId, serviceProcessId, processNo,
			processName, description, durationCount, durationUnit, counter,
			generateDossierNo, dossierNoPattern, generateDueDate,
			dueDatePattern, generatePassword, directNotification, serverNo,
			paymentFee, context);
	}

	/**
	* Updates the service process in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param serviceProcess the service process
	* @return the service process that was updated
	*/
	public static org.opencps.dossiermgt.model.ServiceProcess updateServiceProcess(
		org.opencps.dossiermgt.model.ServiceProcess serviceProcess) {
		return getService().updateServiceProcess(serviceProcess);
	}

	public static org.opencps.dossiermgt.model.ServiceProcess updateServiceProcessDB(
		long userId, long groupId, String processNo, String processName,
		String description, Double durationCount, Integer durationUnit,
		boolean generatePassword, String serverNo, String serverName,
		String dossierNoPattern, String dueDatePattern,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateServiceProcessDB(userId, groupId, processNo,
			processName, description, durationCount, durationUnit,
			generatePassword, serverNo, serverName, dossierNoPattern,
			dueDatePattern, serviceContext);
	}

	public static ServiceProcessLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ServiceProcessLocalService, ServiceProcessLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ServiceProcessLocalService.class);

		ServiceTracker<ServiceProcessLocalService, ServiceProcessLocalService> serviceTracker =
			new ServiceTracker<ServiceProcessLocalService, ServiceProcessLocalService>(bundle.getBundleContext(),
				ServiceProcessLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}