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
 * Provides the local service utility for ServiceOption. This utility wraps
 * {@link org.opencps.backend.dossiermgt.service.impl.ServiceOptionLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author huymq
 * @see ServiceOptionLocalService
 * @see org.opencps.backend.dossiermgt.service.base.ServiceOptionLocalServiceBaseImpl
 * @see org.opencps.backend.dossiermgt.service.impl.ServiceOptionLocalServiceImpl
 * @generated
 */
@ProviderType
public class ServiceOptionLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.backend.dossiermgt.service.impl.ServiceOptionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
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
	* Returns the number of service options.
	*
	* @return the number of service options
	*/
	public static int getServiceOptionsCount() {
		return getService().getServiceOptionsCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.backend.dossiermgt.model.impl.ServiceOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.backend.dossiermgt.model.impl.ServiceOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the service options.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.backend.dossiermgt.model.impl.ServiceOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of service options
	* @param end the upper bound of the range of service options (not inclusive)
	* @return the range of service options
	*/
	public static java.util.List<org.opencps.backend.dossiermgt.model.ServiceOption> getServiceOptions(
		int start, int end) {
		return getService().getServiceOptions(start, end);
	}

	/**
	* Returns all the service options matching the UUID and company.
	*
	* @param uuid the UUID of the service options
	* @param companyId the primary key of the company
	* @return the matching service options, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.backend.dossiermgt.model.ServiceOption> getServiceOptionsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getServiceOptionsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of service options matching the UUID and company.
	*
	* @param uuid the UUID of the service options
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of service options
	* @param end the upper bound of the range of service options (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching service options, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.backend.dossiermgt.model.ServiceOption> getServiceOptionsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.backend.dossiermgt.model.ServiceOption> orderByComparator) {
		return getService()
				   .getServiceOptionsByUuidAndCompanyId(uuid, companyId, start,
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
	* Adds the service option to the database. Also notifies the appropriate model listeners.
	*
	* @param serviceOption the service option
	* @return the service option that was added
	*/
	public static org.opencps.backend.dossiermgt.model.ServiceOption addServiceOption(
		org.opencps.backend.dossiermgt.model.ServiceOption serviceOption) {
		return getService().addServiceOption(serviceOption);
	}

	/**
	* Creates a new service option with the primary key. Does not add the service option to the database.
	*
	* @param serviceOptionId the primary key for the new service option
	* @return the new service option
	*/
	public static org.opencps.backend.dossiermgt.model.ServiceOption createServiceOption(
		long serviceOptionId) {
		return getService().createServiceOption(serviceOptionId);
	}

	/**
	* Deletes the service option with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceOptionId the primary key of the service option
	* @return the service option that was removed
	* @throws PortalException if a service option with the primary key could not be found
	*/
	public static org.opencps.backend.dossiermgt.model.ServiceOption deleteServiceOption(
		long serviceOptionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteServiceOption(serviceOptionId);
	}

	/**
	* Deletes the service option from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceOption the service option
	* @return the service option that was removed
	*/
	public static org.opencps.backend.dossiermgt.model.ServiceOption deleteServiceOption(
		org.opencps.backend.dossiermgt.model.ServiceOption serviceOption) {
		return getService().deleteServiceOption(serviceOption);
	}

	public static org.opencps.backend.dossiermgt.model.ServiceOption fetchServiceOption(
		long serviceOptionId) {
		return getService().fetchServiceOption(serviceOptionId);
	}

	/**
	* Returns the service option matching the UUID and group.
	*
	* @param uuid the service option's UUID
	* @param groupId the primary key of the group
	* @return the matching service option, or <code>null</code> if a matching service option could not be found
	*/
	public static org.opencps.backend.dossiermgt.model.ServiceOption fetchServiceOptionByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchServiceOptionByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the service option with the primary key.
	*
	* @param serviceOptionId the primary key of the service option
	* @return the service option
	* @throws PortalException if a service option with the primary key could not be found
	*/
	public static org.opencps.backend.dossiermgt.model.ServiceOption getServiceOption(
		long serviceOptionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getServiceOption(serviceOptionId);
	}

	/**
	* Returns the service option matching the UUID and group.
	*
	* @param uuid the service option's UUID
	* @param groupId the primary key of the group
	* @return the matching service option
	* @throws PortalException if a matching service option could not be found
	*/
	public static org.opencps.backend.dossiermgt.model.ServiceOption getServiceOptionByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getServiceOptionByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Updates the service option in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param serviceOption the service option
	* @return the service option that was updated
	*/
	public static org.opencps.backend.dossiermgt.model.ServiceOption updateServiceOption(
		org.opencps.backend.dossiermgt.model.ServiceOption serviceOption) {
		return getService().updateServiceOption(serviceOption);
	}

	public static ServiceOptionLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ServiceOptionLocalService, ServiceOptionLocalService> _serviceTracker =
		ServiceTrackerFactory.open(ServiceOptionLocalService.class);
}