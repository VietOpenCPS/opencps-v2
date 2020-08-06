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
 * Provides the local service utility for ServiceConfigMapping. This utility wraps
 * {@link org.opencps.dossiermgt.service.impl.ServiceConfigMappingLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author huymq
 * @see ServiceConfigMappingLocalService
 * @see org.opencps.dossiermgt.service.base.ServiceConfigMappingLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.ServiceConfigMappingLocalServiceImpl
 * @generated
 */
@ProviderType
public class ServiceConfigMappingLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.ServiceConfigMappingLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static org.opencps.dossiermgt.model.ServiceConfigMapping addServiceConfigMaping(
		long groupId, String serviceConfigCode, String serviceConfigName,
		String serviceCode, String serviceName, String govAgencyName,
		String domainName,
		com.liferay.portal.kernel.json.JSONArray applicableInfoObj,
		com.liferay.portal.kernel.json.JSONArray paymentFeeInfoObj,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addServiceConfigMaping(groupId, serviceConfigCode,
			serviceConfigName, serviceCode, serviceName, govAgencyName,
			domainName, applicableInfoObj, paymentFeeInfoObj, context);
	}

	/**
	* Adds the service config mapping to the database. Also notifies the appropriate model listeners.
	*
	* @param serviceConfigMapping the service config mapping
	* @return the service config mapping that was added
	*/
	public static org.opencps.dossiermgt.model.ServiceConfigMapping addServiceConfigMapping(
		org.opencps.dossiermgt.model.ServiceConfigMapping serviceConfigMapping) {
		return getService().addServiceConfigMapping(serviceConfigMapping);
	}

	/**
	* Creates a new service config mapping with the primary key. Does not add the service config mapping to the database.
	*
	* @param serviceConfigMappingId the primary key for the new service config mapping
	* @return the new service config mapping
	*/
	public static org.opencps.dossiermgt.model.ServiceConfigMapping createServiceConfigMapping(
		long serviceConfigMappingId) {
		return getService().createServiceConfigMapping(serviceConfigMappingId);
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
	* Deletes the service config mapping with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceConfigMappingId the primary key of the service config mapping
	* @return the service config mapping that was removed
	* @throws PortalException if a service config mapping with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.ServiceConfigMapping deleteServiceConfigMapping(
		long serviceConfigMappingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteServiceConfigMapping(serviceConfigMappingId);
	}

	/**
	* Deletes the service config mapping from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceConfigMapping the service config mapping
	* @return the service config mapping that was removed
	*/
	public static org.opencps.dossiermgt.model.ServiceConfigMapping deleteServiceConfigMapping(
		org.opencps.dossiermgt.model.ServiceConfigMapping serviceConfigMapping) {
		return getService().deleteServiceConfigMapping(serviceConfigMapping);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ServiceConfigMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ServiceConfigMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.dossiermgt.model.ServiceConfigMapping fetchServiceConfigMapping(
		long serviceConfigMappingId) {
		return getService().fetchServiceConfigMapping(serviceConfigMappingId);
	}

	/**
	* Returns the service config mapping matching the UUID and group.
	*
	* @param uuid the service config mapping's UUID
	* @param groupId the primary key of the group
	* @return the matching service config mapping, or <code>null</code> if a matching service config mapping could not be found
	*/
	public static org.opencps.dossiermgt.model.ServiceConfigMapping fetchServiceConfigMappingByUuidAndGroupId(
		String uuid, long groupId) {
		return getService()
				   .fetchServiceConfigMappingByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
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

	/**
	* Returns the service config mapping with the primary key.
	*
	* @param serviceConfigMappingId the primary key of the service config mapping
	* @return the service config mapping
	* @throws PortalException if a service config mapping with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.ServiceConfigMapping getServiceConfigMapping(
		long serviceConfigMappingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getServiceConfigMapping(serviceConfigMappingId);
	}

	/**
	* Returns the service config mapping matching the UUID and group.
	*
	* @param uuid the service config mapping's UUID
	* @param groupId the primary key of the group
	* @return the matching service config mapping
	* @throws PortalException if a matching service config mapping could not be found
	*/
	public static org.opencps.dossiermgt.model.ServiceConfigMapping getServiceConfigMappingByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getServiceConfigMappingByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the service config mappings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ServiceConfigMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of service config mappings
	* @param end the upper bound of the range of service config mappings (not inclusive)
	* @return the range of service config mappings
	*/
	public static java.util.List<org.opencps.dossiermgt.model.ServiceConfigMapping> getServiceConfigMappings(
		int start, int end) {
		return getService().getServiceConfigMappings(start, end);
	}

	/**
	* Returns all the service config mappings matching the UUID and company.
	*
	* @param uuid the UUID of the service config mappings
	* @param companyId the primary key of the company
	* @return the matching service config mappings, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.dossiermgt.model.ServiceConfigMapping> getServiceConfigMappingsByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService()
				   .getServiceConfigMappingsByUuidAndCompanyId(uuid, companyId);
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
	public static java.util.List<org.opencps.dossiermgt.model.ServiceConfigMapping> getServiceConfigMappingsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.ServiceConfigMapping> orderByComparator) {
		return getService()
				   .getServiceConfigMappingsByUuidAndCompanyId(uuid, companyId,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of service config mappings.
	*
	* @return the number of service config mappings
	*/
	public static int getServiceConfigMappingsCount() {
		return getService().getServiceConfigMappingsCount();
	}

	public static org.opencps.dossiermgt.model.ServiceConfigMapping removeServiceConfigMapping(
		long groupId, long serviceConfigMappingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .removeServiceConfigMapping(groupId, serviceConfigMappingId);
	}

	/**
	* Updates the service config mapping in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param serviceConfigMapping the service config mapping
	* @return the service config mapping that was updated
	*/
	public static org.opencps.dossiermgt.model.ServiceConfigMapping updateServiceConfigMapping(
		org.opencps.dossiermgt.model.ServiceConfigMapping serviceConfigMapping) {
		return getService().updateServiceConfigMapping(serviceConfigMapping);
	}

	public static ServiceConfigMappingLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ServiceConfigMappingLocalService, ServiceConfigMappingLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ServiceConfigMappingLocalService.class);

		ServiceTracker<ServiceConfigMappingLocalService, ServiceConfigMappingLocalService> serviceTracker =
			new ServiceTracker<ServiceConfigMappingLocalService, ServiceConfigMappingLocalService>(bundle.getBundleContext(),
				ServiceConfigMappingLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}