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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for ServiceConfig. This utility wraps
 * {@link org.opencps.backend.dossiermgt.service.impl.ServiceConfigLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author huymq
 * @see ServiceConfigLocalService
 * @see org.opencps.backend.dossiermgt.service.base.ServiceConfigLocalServiceBaseImpl
 * @see org.opencps.backend.dossiermgt.service.impl.ServiceConfigLocalServiceImpl
 * @generated
 */
@ProviderType
public class ServiceConfigLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.backend.dossiermgt.service.impl.ServiceConfigLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of service configs.
	*
	* @return the number of service configs
	*/
	public static int getServiceConfigsCount() {
		return getService().getServiceConfigsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.backend.dossiermgt.model.impl.ServiceConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.backend.dossiermgt.model.impl.ServiceConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the service configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.backend.dossiermgt.model.impl.ServiceConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of service configs
	* @param end the upper bound of the range of service configs (not inclusive)
	* @return the range of service configs
	*/
	public static java.util.List<org.opencps.backend.dossiermgt.model.ServiceConfig> getServiceConfigs(
		int start, int end) {
		return getService().getServiceConfigs(start, end);
	}

	/**
	* Returns all the service configs matching the UUID and company.
	*
	* @param uuid the UUID of the service configs
	* @param companyId the primary key of the company
	* @return the matching service configs, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.backend.dossiermgt.model.ServiceConfig> getServiceConfigsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getServiceConfigsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of service configs matching the UUID and company.
	*
	* @param uuid the UUID of the service configs
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of service configs
	* @param end the upper bound of the range of service configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching service configs, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.backend.dossiermgt.model.ServiceConfig> getServiceConfigsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.backend.dossiermgt.model.ServiceConfig> orderByComparator) {
		return getService()
				   .getServiceConfigsByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
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

	/**
	* Adds the service config to the database. Also notifies the appropriate model listeners.
	*
	* @param serviceConfig the service config
	* @return the service config that was added
	*/
	public static org.opencps.backend.dossiermgt.model.ServiceConfig addServiceConfig(
		org.opencps.backend.dossiermgt.model.ServiceConfig serviceConfig) {
		return getService().addServiceConfig(serviceConfig);
	}

	/**
	* Creates a new service config with the primary key. Does not add the service config to the database.
	*
	* @param serviceConfigId the primary key for the new service config
	* @return the new service config
	*/
	public static org.opencps.backend.dossiermgt.model.ServiceConfig createServiceConfig(
		long serviceConfigId) {
		return getService().createServiceConfig(serviceConfigId);
	}

	/**
	* Deletes the service config with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceConfigId the primary key of the service config
	* @return the service config that was removed
	* @throws PortalException if a service config with the primary key could not be found
	*/
	public static org.opencps.backend.dossiermgt.model.ServiceConfig deleteServiceConfig(
		long serviceConfigId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteServiceConfig(serviceConfigId);
	}

	/**
	* Deletes the service config from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceConfig the service config
	* @return the service config that was removed
	*/
	public static org.opencps.backend.dossiermgt.model.ServiceConfig deleteServiceConfig(
		org.opencps.backend.dossiermgt.model.ServiceConfig serviceConfig) {
		return getService().deleteServiceConfig(serviceConfig);
	}

	public static org.opencps.backend.dossiermgt.model.ServiceConfig fetchServiceConfig(
		long serviceConfigId) {
		return getService().fetchServiceConfig(serviceConfigId);
	}

	/**
	* Returns the service config matching the UUID and group.
	*
	* @param uuid the service config's UUID
	* @param groupId the primary key of the group
	* @return the matching service config, or <code>null</code> if a matching service config could not be found
	*/
	public static org.opencps.backend.dossiermgt.model.ServiceConfig fetchServiceConfigByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchServiceConfigByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the service config with the primary key.
	*
	* @param serviceConfigId the primary key of the service config
	* @return the service config
	* @throws PortalException if a service config with the primary key could not be found
	*/
	public static org.opencps.backend.dossiermgt.model.ServiceConfig getServiceConfig(
		long serviceConfigId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getServiceConfig(serviceConfigId);
	}

	/**
	* Returns the service config matching the UUID and group.
	*
	* @param uuid the service config's UUID
	* @param groupId the primary key of the group
	* @return the matching service config
	* @throws PortalException if a matching service config could not be found
	*/
	public static org.opencps.backend.dossiermgt.model.ServiceConfig getServiceConfigByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getServiceConfigByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Updates the service config in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param serviceConfig the service config
	* @return the service config that was updated
	*/
	public static org.opencps.backend.dossiermgt.model.ServiceConfig updateServiceConfig(
		org.opencps.backend.dossiermgt.model.ServiceConfig serviceConfig) {
		return getService().updateServiceConfig(serviceConfig);
	}

	public static ServiceConfigLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ServiceConfigLocalService, ServiceConfigLocalService> _serviceTracker =
		ServiceTrackerFactory.open(ServiceConfigLocalService.class);
}