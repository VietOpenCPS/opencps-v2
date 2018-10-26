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
 * Provides the local service utility for ServiceInfo. This utility wraps
 * {@link org.opencps.dossiermgt.service.impl.ServiceInfoLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author huymq
 * @see ServiceInfoLocalService
 * @see org.opencps.dossiermgt.service.base.ServiceInfoLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.ServiceInfoLocalServiceImpl
 * @generated
 */
@ProviderType
public class ServiceInfoLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.ServiceInfoLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static org.opencps.dossiermgt.model.ServiceInfo addServiceInfo(
		long userId, long groupId, String serviceCode, String serviceName,
		String processText, String methodText, String dossierText,
		String conditionText, String durationText, String applicantText,
		String resultText, String regularText, String feeText,
		String administrationCode, String domainCode, int maxLevel,
		boolean activeStatus,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addServiceInfo(userId, groupId, serviceCode, serviceName,
			processText, methodText, dossierText, conditionText, durationText,
			applicantText, resultText, regularText, feeText,
			administrationCode, domainCode, maxLevel, activeStatus,
			serviceContext);
	}

	/**
	* Adds the service info to the database. Also notifies the appropriate model listeners.
	*
	* @param serviceInfo the service info
	* @return the service info that was added
	*/
	public static org.opencps.dossiermgt.model.ServiceInfo addServiceInfo(
		org.opencps.dossiermgt.model.ServiceInfo serviceInfo) {
		return getService().addServiceInfo(serviceInfo);
	}

	public static org.opencps.dossiermgt.model.ServiceInfo adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return getService().adminProcessData(objectData);
	}

	public static org.opencps.dossiermgt.model.ServiceInfo adminProcessDelete(
		Long id) {
		return getService().adminProcessDelete(id);
	}

	public static long countLucene(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return getService().countLucene(params, searchContext);
	}

	public static int countServiceInfosByGroupId(long groupId) {
		return getService().countServiceInfosByGroupId(groupId);
	}

	/**
	* Creates a new service info with the primary key. Does not add the service info to the database.
	*
	* @param serviceInfoId the primary key for the new service info
	* @return the new service info
	*/
	public static org.opencps.dossiermgt.model.ServiceInfo createServiceInfo(
		long serviceInfoId) {
		return getService().createServiceInfo(serviceInfoId);
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
	* Deletes the service info with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceInfoId the primary key of the service info
	* @return the service info that was removed
	* @throws PortalException if a service info with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.ServiceInfo deleteServiceInfo(
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
	public static org.opencps.dossiermgt.model.ServiceInfo deleteServiceInfo(
		org.opencps.dossiermgt.model.ServiceInfo serviceInfo) {
		return getService().deleteServiceInfo(serviceInfo);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static java.util.List<org.opencps.dossiermgt.model.ServiceInfo> fetchByDomain(
		long groupId, String domainCode) {
		return getService().fetchByDomain(groupId, domainCode);
	}

	public static org.opencps.dossiermgt.model.ServiceInfo fetchServiceInfo(
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
	public static org.opencps.dossiermgt.model.ServiceInfo fetchServiceInfoByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchServiceInfoByUuidAndGroupId(uuid, groupId);
	}

	public static java.util.List<org.opencps.dossiermgt.model.ServiceInfo> findByGroup(
		long groupId) {
		return getService().findByGroup(groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static org.opencps.dossiermgt.model.ServiceInfo getByCode(
		long groupId, String serviceCode)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getByCode(groupId, serviceCode);
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
	* Returns the service info with the primary key.
	*
	* @param serviceInfoId the primary key of the service info
	* @return the service info
	* @throws PortalException if a service info with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.ServiceInfo getServiceInfo(
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
	public static org.opencps.dossiermgt.model.ServiceInfo getServiceInfoByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getServiceInfoByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the service infos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of service infos
	* @param end the upper bound of the range of service infos (not inclusive)
	* @return the range of service infos
	*/
	public static java.util.List<org.opencps.dossiermgt.model.ServiceInfo> getServiceInfos(
		int start, int end) {
		return getService().getServiceInfos(start, end);
	}

	public static java.util.List<org.opencps.dossiermgt.model.ServiceInfo> getServiceInfosByGroupId(
		long groupId) {
		return getService().getServiceInfosByGroupId(groupId);
	}

	public static java.util.List<org.opencps.dossiermgt.model.ServiceInfo> getServiceInfosByGroupId(
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
	public static java.util.List<org.opencps.dossiermgt.model.ServiceInfo> getServiceInfosByUuidAndCompanyId(
		String uuid, long companyId) {
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
	public static java.util.List<org.opencps.dossiermgt.model.ServiceInfo> getServiceInfosByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.ServiceInfo> orderByComparator) {
		return getService()
				   .getServiceInfosByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of service infos.
	*
	* @return the number of service infos
	*/
	public static int getServiceInfosCount() {
		return getService().getServiceInfosCount();
	}

	public static org.opencps.dossiermgt.model.ServiceInfo removeServiceInfo(
		long serviceInfoId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().removeServiceInfo(serviceInfoId);
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

	public static org.opencps.dossiermgt.model.ServiceInfo updateServiceInfo(
		long groupId, long serviceInfoId, String serviceCode,
		String serviceName, String processText, String methodText,
		String dossierText, String conditionText, String durationText,
		String applicantText, String resultText, String regularText,
		String feeText, String administrationCode, String domainCode,
		int maxLevel, boolean activeStatus,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateServiceInfo(groupId, serviceInfoId, serviceCode,
			serviceName, processText, methodText, dossierText, conditionText,
			durationText, applicantText, resultText, regularText, feeText,
			administrationCode, domainCode, maxLevel, activeStatus,
			serviceContext);
	}

	/**
	* Updates the service info in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param serviceInfo the service info
	* @return the service info that was updated
	*/
	public static org.opencps.dossiermgt.model.ServiceInfo updateServiceInfo(
		org.opencps.dossiermgt.model.ServiceInfo serviceInfo) {
		return getService().updateServiceInfo(serviceInfo);
	}

	public static org.opencps.dossiermgt.model.ServiceInfo updateServiceInfoDB(
		long userId, long groupId, String serviceCode, String serviceName,
		String processText, String methodText, String dossierText,
		String conditionText, String durationText, String applicantText,
		String resultText, String regularText, String feeText,
		String administrationCode, String administrationName,
		String domainCode, String domainName, Integer maxLevel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateServiceInfoDB(userId, groupId, serviceCode,
			serviceName, processText, methodText, dossierText, conditionText,
			durationText, applicantText, resultText, regularText, feeText,
			administrationCode, administrationName, domainCode, domainName,
			maxLevel);
	}

	public static ServiceInfoLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ServiceInfoLocalService, ServiceInfoLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ServiceInfoLocalService.class);

		ServiceTracker<ServiceInfoLocalService, ServiceInfoLocalService> serviceTracker =
			new ServiceTracker<ServiceInfoLocalService, ServiceInfoLocalService>(bundle.getBundleContext(),
				ServiceInfoLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}