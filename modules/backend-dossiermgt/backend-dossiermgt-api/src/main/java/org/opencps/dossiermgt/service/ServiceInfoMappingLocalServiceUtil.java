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
 * Provides the local service utility for ServiceInfoMapping. This utility wraps
 * {@link org.opencps.dossiermgt.service.impl.ServiceInfoMappingLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author huymq
 * @see ServiceInfoMappingLocalService
 * @see org.opencps.dossiermgt.service.base.ServiceInfoMappingLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.ServiceInfoMappingLocalServiceImpl
 * @generated
 */
@ProviderType
public class ServiceInfoMappingLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.ServiceInfoMappingLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static org.opencps.dossiermgt.model.ServiceInfoMapping addServiceInfoMapping(
		long groupId, long companyId, long userId, String serviceCode,
		String serviceCodeDVCQG, String serviceNameDVCQG, int synced)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addServiceInfoMapping(groupId, companyId, userId,
			serviceCode, serviceCodeDVCQG, serviceNameDVCQG, synced);
	}

	/**
	* Adds the service info mapping to the database. Also notifies the appropriate model listeners.
	*
	* @param serviceInfoMapping the service info mapping
	* @return the service info mapping that was added
	*/
	public static org.opencps.dossiermgt.model.ServiceInfoMapping addServiceInfoMapping(
		org.opencps.dossiermgt.model.ServiceInfoMapping serviceInfoMapping) {
		return getService().addServiceInfoMapping(serviceInfoMapping);
	}

	public static org.opencps.dossiermgt.model.ServiceInfoMapping adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return getService().adminProcessData(objectData);
	}

	public static org.opencps.dossiermgt.model.ServiceInfoMapping adminProcessDelete(
		Long id) {
		return getService().adminProcessDelete(id);
	}

	/**
	* Creates a new service info mapping with the primary key. Does not add the service info mapping to the database.
	*
	* @param serviceInfoMappingId the primary key for the new service info mapping
	* @return the new service info mapping
	*/
	public static org.opencps.dossiermgt.model.ServiceInfoMapping createServiceInfoMapping(
		long serviceInfoMappingId) {
		return getService().createServiceInfoMapping(serviceInfoMappingId);
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
	* Deletes the service info mapping with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceInfoMappingId the primary key of the service info mapping
	* @return the service info mapping that was removed
	* @throws PortalException if a service info mapping with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.ServiceInfoMapping deleteServiceInfoMapping(
		long serviceInfoMappingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteServiceInfoMapping(serviceInfoMappingId);
	}

	public static boolean deleteServiceInfoMapping(long groupId,
		String serviceCode) {
		return getService().deleteServiceInfoMapping(groupId, serviceCode);
	}

	/**
	* Deletes the service info mapping from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceInfoMapping the service info mapping
	* @return the service info mapping that was removed
	*/
	public static org.opencps.dossiermgt.model.ServiceInfoMapping deleteServiceInfoMapping(
		org.opencps.dossiermgt.model.ServiceInfoMapping serviceInfoMapping) {
		return getService().deleteServiceInfoMapping(serviceInfoMapping);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ServiceInfoMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ServiceInfoMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.dossiermgt.model.ServiceInfoMapping fetchByGID_SCDVCQG(
		long groupId, String serviceCodeDVCQG) {
		return getService().fetchByGID_SCDVCQG(groupId, serviceCodeDVCQG);
	}

	public static org.opencps.dossiermgt.model.ServiceInfoMapping fetchDVCQGServiceCode(
		long groupId, String serviceCode) {
		return getService().fetchDVCQGServiceCode(groupId, serviceCode);
	}

	public static org.opencps.dossiermgt.model.ServiceInfoMapping fetchServiceInfoMapping(
		long serviceInfoMappingId) {
		return getService().fetchServiceInfoMapping(serviceInfoMappingId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
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
	* Returns the service info mapping with the primary key.
	*
	* @param serviceInfoMappingId the primary key of the service info mapping
	* @return the service info mapping
	* @throws PortalException if a service info mapping with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.ServiceInfoMapping getServiceInfoMapping(
		long serviceInfoMappingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getServiceInfoMapping(serviceInfoMappingId);
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
	public static java.util.List<org.opencps.dossiermgt.model.ServiceInfoMapping> getServiceInfoMappings(
		int start, int end) {
		return getService().getServiceInfoMappings(start, end);
	}

	/**
	* Returns the number of service info mappings.
	*
	* @return the number of service info mappings
	*/
	public static int getServiceInfoMappingsCount() {
		return getService().getServiceInfoMappingsCount();
	}

	public static boolean removeServiceInfoMapping(long mappingId) {
		return getService().removeServiceInfoMapping(mappingId);
	}

	/**
	* Updates the service info mapping in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param serviceInfoMapping the service info mapping
	* @return the service info mapping that was updated
	*/
	public static org.opencps.dossiermgt.model.ServiceInfoMapping updateServiceInfoMapping(
		org.opencps.dossiermgt.model.ServiceInfoMapping serviceInfoMapping) {
		return getService().updateServiceInfoMapping(serviceInfoMapping);
	}

	public static ServiceInfoMappingLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ServiceInfoMappingLocalService, ServiceInfoMappingLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ServiceInfoMappingLocalService.class);

		ServiceTracker<ServiceInfoMappingLocalService, ServiceInfoMappingLocalService> serviceTracker =
			new ServiceTracker<ServiceInfoMappingLocalService, ServiceInfoMappingLocalService>(bundle.getBundleContext(),
				ServiceInfoMappingLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}