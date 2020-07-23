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
 * Provides the local service utility for ApplicableInfo. This utility wraps
 * {@link org.opencps.dossiermgt.service.impl.ApplicableInfoLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author huymq
 * @see ApplicableInfoLocalService
 * @see org.opencps.dossiermgt.service.base.ApplicableInfoLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.ApplicableInfoLocalServiceImpl
 * @generated
 */
@ProviderType
public class ApplicableInfoLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.ApplicableInfoLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the applicable info to the database. Also notifies the appropriate model listeners.
	*
	* @param applicableInfo the applicable info
	* @return the applicable info that was added
	*/
	public static org.opencps.dossiermgt.model.ApplicableInfo addApplicableInfo(
		org.opencps.dossiermgt.model.ApplicableInfo applicableInfo) {
		return getService().addApplicableInfo(applicableInfo);
	}

	public static org.opencps.dossiermgt.model.ApplicableInfo addApplicableInfo(
		long groupId, long serviceConfigMappingId, String govAgencyCode,
		String serviceCode, int serviceLevel,
		com.liferay.portal.kernel.service.ServiceContext context) {
		return getService()
				   .addApplicableInfo(groupId, serviceConfigMappingId,
			govAgencyCode, serviceCode, serviceLevel, context);
	}

	/**
	* Creates a new applicable info with the primary key. Does not add the applicable info to the database.
	*
	* @param applicableInfoId the primary key for the new applicable info
	* @return the new applicable info
	*/
	public static org.opencps.dossiermgt.model.ApplicableInfo createApplicableInfo(
		long applicableInfoId) {
		return getService().createApplicableInfo(applicableInfoId);
	}

	/**
	* Deletes the applicable info from the database. Also notifies the appropriate model listeners.
	*
	* @param applicableInfo the applicable info
	* @return the applicable info that was removed
	*/
	public static org.opencps.dossiermgt.model.ApplicableInfo deleteApplicableInfo(
		org.opencps.dossiermgt.model.ApplicableInfo applicableInfo) {
		return getService().deleteApplicableInfo(applicableInfo);
	}

	/**
	* Deletes the applicable info with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param applicableInfoId the primary key of the applicable info
	* @return the applicable info that was removed
	* @throws PortalException if a applicable info with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.ApplicableInfo deleteApplicableInfo(
		long applicableInfoId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteApplicableInfo(applicableInfoId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ApplicableInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ApplicableInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.dossiermgt.model.ApplicableInfo fetchApplicableInfo(
		long applicableInfoId) {
		return getService().fetchApplicableInfo(applicableInfoId);
	}

	/**
	* Returns the applicable info matching the UUID and group.
	*
	* @param uuid the applicable info's UUID
	* @param groupId the primary key of the group
	* @return the matching applicable info, or <code>null</code> if a matching applicable info could not be found
	*/
	public static org.opencps.dossiermgt.model.ApplicableInfo fetchApplicableInfoByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchApplicableInfoByUuidAndGroupId(uuid, groupId);
	}

	public static org.opencps.dossiermgt.model.ApplicableInfo fetchByG_SC_GC_SL(
		long groupId, String serviceCode, String govAgencyCode, int serviceLevel) {
		return getService()
				   .fetchByG_SC_GC_SL(groupId, serviceCode, govAgencyCode,
			serviceLevel);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the applicable info with the primary key.
	*
	* @param applicableInfoId the primary key of the applicable info
	* @return the applicable info
	* @throws PortalException if a applicable info with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.ApplicableInfo getApplicableInfo(
		long applicableInfoId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getApplicableInfo(applicableInfoId);
	}

	/**
	* Returns the applicable info matching the UUID and group.
	*
	* @param uuid the applicable info's UUID
	* @param groupId the primary key of the group
	* @return the matching applicable info
	* @throws PortalException if a matching applicable info could not be found
	*/
	public static org.opencps.dossiermgt.model.ApplicableInfo getApplicableInfoByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getApplicableInfoByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the applicable infos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ApplicableInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of applicable infos
	* @param end the upper bound of the range of applicable infos (not inclusive)
	* @return the range of applicable infos
	*/
	public static java.util.List<org.opencps.dossiermgt.model.ApplicableInfo> getApplicableInfos(
		int start, int end) {
		return getService().getApplicableInfos(start, end);
	}

	/**
	* Returns all the applicable infos matching the UUID and company.
	*
	* @param uuid the UUID of the applicable infos
	* @param companyId the primary key of the company
	* @return the matching applicable infos, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.dossiermgt.model.ApplicableInfo> getApplicableInfosByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService().getApplicableInfosByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of applicable infos matching the UUID and company.
	*
	* @param uuid the UUID of the applicable infos
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of applicable infos
	* @param end the upper bound of the range of applicable infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching applicable infos, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.dossiermgt.model.ApplicableInfo> getApplicableInfosByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.ApplicableInfo> orderByComparator) {
		return getService()
				   .getApplicableInfosByUuidAndCompanyId(uuid, companyId,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of applicable infos.
	*
	* @return the number of applicable infos
	*/
	public static int getApplicableInfosCount() {
		return getService().getApplicableInfosCount();
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
	* Updates the applicable info in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param applicableInfo the applicable info
	* @return the applicable info that was updated
	*/
	public static org.opencps.dossiermgt.model.ApplicableInfo updateApplicableInfo(
		org.opencps.dossiermgt.model.ApplicableInfo applicableInfo) {
		return getService().updateApplicableInfo(applicableInfo);
	}

	public static ApplicableInfoLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ApplicableInfoLocalService, ApplicableInfoLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ApplicableInfoLocalService.class);

		ServiceTracker<ApplicableInfoLocalService, ApplicableInfoLocalService> serviceTracker =
			new ServiceTracker<ApplicableInfoLocalService, ApplicableInfoLocalService>(bundle.getBundleContext(),
				ApplicableInfoLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}