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
 * Provides the local service utility for ServiceInfo. This utility wraps
 * {@link org.opencps.backend.dossiermgt.service.impl.ServiceInfoLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author huymq
 * @see ServiceInfoLocalService
 * @see org.opencps.backend.dossiermgt.service.base.ServiceInfoLocalServiceBaseImpl
 * @see org.opencps.backend.dossiermgt.service.impl.ServiceInfoLocalServiceImpl
 * @generated
 */
@ProviderType
public class ServiceInfoLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.backend.dossiermgt.service.impl.ServiceInfoLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static boolean hasFileTemplateServiceInfo(long fileTemplateId,
		long serviceInfoId) {
		return getService()
				   .hasFileTemplateServiceInfo(fileTemplateId, serviceInfoId);
	}

	public static boolean hasFileTemplateServiceInfos(long fileTemplateId) {
		return getService().hasFileTemplateServiceInfos(fileTemplateId);
	}

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

	public static int getFileTemplateServiceInfosCount(long fileTemplateId) {
		return getService().getFileTemplateServiceInfosCount(fileTemplateId);
	}

	/**
	* Returns the number of service infos.
	*
	* @return the number of service infos
	*/
	public static int getServiceInfosCount() {
		return getService().getServiceInfosCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.backend.dossiermgt.model.impl.ServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.backend.dossiermgt.model.impl.ServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static java.util.List<org.opencps.backend.dossiermgt.model.ServiceInfo> getFileTemplateServiceInfos(
		long fileTemplateId) {
		return getService().getFileTemplateServiceInfos(fileTemplateId);
	}

	public static java.util.List<org.opencps.backend.dossiermgt.model.ServiceInfo> getFileTemplateServiceInfos(
		long fileTemplateId, int start, int end) {
		return getService()
				   .getFileTemplateServiceInfos(fileTemplateId, start, end);
	}

	public static java.util.List<org.opencps.backend.dossiermgt.model.ServiceInfo> getFileTemplateServiceInfos(
		long fileTemplateId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.backend.dossiermgt.model.ServiceInfo> orderByComparator) {
		return getService()
				   .getFileTemplateServiceInfos(fileTemplateId, start, end,
			orderByComparator);
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
	public static java.util.List<org.opencps.backend.dossiermgt.model.ServiceInfo> getServiceInfos(
		int start, int end) {
		return getService().getServiceInfos(start, end);
	}

	public static java.util.List<org.opencps.backend.dossiermgt.model.ServiceInfo> getServiceInfosByCompanyId(
		long companyId) {
		return getService().getServiceInfosByCompanyId(companyId);
	}

	public static java.util.List<org.opencps.backend.dossiermgt.model.ServiceInfo> getServiceInfosByCompanyId(
		long companyId, int start, int end) {
		return getService().getServiceInfosByCompanyId(companyId, start, end);
	}

	public static java.util.List<org.opencps.backend.dossiermgt.model.ServiceInfo> getServiceInfosByGroupId(
		long groupId) {
		return getService().getServiceInfosByGroupId(groupId);
	}

	public static java.util.List<org.opencps.backend.dossiermgt.model.ServiceInfo> getServiceInfosByGroupId(
		long groupId, int start, int end) {
		return getService().getServiceInfosByGroupId(groupId, start, end);
	}

	/**
	* Returns all the service infos matching the UUID and company.
	*
	* @param uuid the UUID of the service infos
	* @param companyId the primary key of the company
	* @return the matching service infos, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.backend.dossiermgt.model.ServiceInfo> getServiceInfosByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getServiceInfosByUuidAndCompanyId(uuid, companyId);
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
	public static java.util.List<org.opencps.backend.dossiermgt.model.ServiceInfo> getServiceInfosByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.backend.dossiermgt.model.ServiceInfo> orderByComparator) {
		return getService()
				   .getServiceInfosByUuidAndCompanyId(uuid, companyId, start,
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
	* Returns the fileTemplateIds of the file templates associated with the service info.
	*
	* @param serviceInfoId the serviceInfoId of the service info
	* @return long[] the fileTemplateIds of file templates associated with the service info
	*/
	public static long[] getFileTemplatePrimaryKeys(long serviceInfoId) {
		return getService().getFileTemplatePrimaryKeys(serviceInfoId);
	}

	/**
	* Adds the service info to the database. Also notifies the appropriate model listeners.
	*
	* @param serviceInfo the service info
	* @return the service info that was added
	*/
	public static org.opencps.backend.dossiermgt.model.ServiceInfo addServiceInfo(
		org.opencps.backend.dossiermgt.model.ServiceInfo serviceInfo) {
		return getService().addServiceInfo(serviceInfo);
	}

	/**
	* Creates a new service info with the primary key. Does not add the service info to the database.
	*
	* @param serviceInfoId the primary key for the new service info
	* @return the new service info
	*/
	public static org.opencps.backend.dossiermgt.model.ServiceInfo createServiceInfo(
		long serviceInfoId) {
		return getService().createServiceInfo(serviceInfoId);
	}

	/**
	* Deletes the service info with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceInfoId the primary key of the service info
	* @return the service info that was removed
	* @throws PortalException if a service info with the primary key could not be found
	*/
	public static org.opencps.backend.dossiermgt.model.ServiceInfo deleteServiceInfo(
		long serviceInfoId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteServiceInfo(serviceInfoId);
	}

	/**
	* Deletes the service info from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceInfo the service info
	* @return the service info that was removed
	*/
	public static org.opencps.backend.dossiermgt.model.ServiceInfo deleteServiceInfo(
		org.opencps.backend.dossiermgt.model.ServiceInfo serviceInfo) {
		return getService().deleteServiceInfo(serviceInfo);
	}

	public static org.opencps.backend.dossiermgt.model.ServiceInfo fetchServiceInfo(
		long serviceInfoId) {
		return getService().fetchServiceInfo(serviceInfoId);
	}

	/**
	* Returns the service info matching the UUID and group.
	*
	* @param uuid the service info's UUID
	* @param groupId the primary key of the group
	* @return the matching service info, or <code>null</code> if a matching service info could not be found
	*/
	public static org.opencps.backend.dossiermgt.model.ServiceInfo fetchServiceInfoByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchServiceInfoByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the service info with the primary key.
	*
	* @param serviceInfoId the primary key of the service info
	* @return the service info
	* @throws PortalException if a service info with the primary key could not be found
	*/
	public static org.opencps.backend.dossiermgt.model.ServiceInfo getServiceInfo(
		long serviceInfoId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getServiceInfo(serviceInfoId);
	}

	/**
	* Returns the service info matching the UUID and group.
	*
	* @param uuid the service info's UUID
	* @param groupId the primary key of the group
	* @return the matching service info
	* @throws PortalException if a matching service info could not be found
	*/
	public static org.opencps.backend.dossiermgt.model.ServiceInfo getServiceInfoByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getServiceInfoByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Updates the service info in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param serviceInfo the service info
	* @return the service info that was updated
	*/
	public static org.opencps.backend.dossiermgt.model.ServiceInfo updateServiceInfo(
		org.opencps.backend.dossiermgt.model.ServiceInfo serviceInfo) {
		return getService().updateServiceInfo(serviceInfo);
	}

	public static void addFileTemplateServiceInfo(long fileTemplateId,
		long serviceInfoId) {
		getService().addFileTemplateServiceInfo(fileTemplateId, serviceInfoId);
	}

	public static void addFileTemplateServiceInfo(long fileTemplateId,
		org.opencps.backend.dossiermgt.model.ServiceInfo serviceInfo) {
		getService().addFileTemplateServiceInfo(fileTemplateId, serviceInfo);
	}

	public static void addFileTemplateServiceInfos(long fileTemplateId,
		java.util.List<org.opencps.backend.dossiermgt.model.ServiceInfo> serviceInfos) {
		getService().addFileTemplateServiceInfos(fileTemplateId, serviceInfos);
	}

	public static void addFileTemplateServiceInfos(long fileTemplateId,
		long[] serviceInfoIds) {
		getService().addFileTemplateServiceInfos(fileTemplateId, serviceInfoIds);
	}

	public static void clearFileTemplateServiceInfos(long fileTemplateId) {
		getService().clearFileTemplateServiceInfos(fileTemplateId);
	}

	public static void deleteFileTemplateServiceInfo(long fileTemplateId,
		long serviceInfoId) {
		getService().deleteFileTemplateServiceInfo(fileTemplateId, serviceInfoId);
	}

	public static void deleteFileTemplateServiceInfo(long fileTemplateId,
		org.opencps.backend.dossiermgt.model.ServiceInfo serviceInfo) {
		getService().deleteFileTemplateServiceInfo(fileTemplateId, serviceInfo);
	}

	public static void deleteFileTemplateServiceInfos(long fileTemplateId,
		java.util.List<org.opencps.backend.dossiermgt.model.ServiceInfo> serviceInfos) {
		getService().deleteFileTemplateServiceInfos(fileTemplateId, serviceInfos);
	}

	public static void deleteFileTemplateServiceInfos(long fileTemplateId,
		long[] serviceInfoIds) {
		getService()
			.deleteFileTemplateServiceInfos(fileTemplateId, serviceInfoIds);
	}

	public static void setFileTemplateServiceInfos(long fileTemplateId,
		long[] serviceInfoIds) {
		getService().setFileTemplateServiceInfos(fileTemplateId, serviceInfoIds);
	}

	public static ServiceInfoLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ServiceInfoLocalService, ServiceInfoLocalService> _serviceTracker =
		ServiceTrackerFactory.open(ServiceInfoLocalService.class);
}