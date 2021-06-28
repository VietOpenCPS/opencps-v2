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
 * Provides the local service utility for CsdlDcServiceInfo. This utility wraps
 * {@link org.opencps.dossiermgt.service.impl.CsdlDcServiceInfoLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author huymq
 * @see CsdlDcServiceInfoLocalService
 * @see org.opencps.dossiermgt.service.base.CsdlDcServiceInfoLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.CsdlDcServiceInfoLocalServiceImpl
 * @generated
 */
@ProviderType
public class CsdlDcServiceInfoLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.CsdlDcServiceInfoLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the csdl dc service info to the database. Also notifies the appropriate model listeners.
	*
	* @param csdlDcServiceInfo the csdl dc service info
	* @return the csdl dc service info that was added
	*/
	public static org.opencps.dossiermgt.model.CsdlDcServiceInfo addCsdlDcServiceInfo(
		org.opencps.dossiermgt.model.CsdlDcServiceInfo csdlDcServiceInfo) {
		return getService().addCsdlDcServiceInfo(csdlDcServiceInfo);
	}

	/**
	* Creates a new csdl dc service info with the primary key. Does not add the csdl dc service info to the database.
	*
	* @param idDcService the primary key for the new csdl dc service info
	* @return the new csdl dc service info
	*/
	public static org.opencps.dossiermgt.model.CsdlDcServiceInfo createCsdlDcServiceInfo(
		long idDcService) {
		return getService().createCsdlDcServiceInfo(idDcService);
	}

	/**
	* Deletes the csdl dc service info from the database. Also notifies the appropriate model listeners.
	*
	* @param csdlDcServiceInfo the csdl dc service info
	* @return the csdl dc service info that was removed
	*/
	public static org.opencps.dossiermgt.model.CsdlDcServiceInfo deleteCsdlDcServiceInfo(
		org.opencps.dossiermgt.model.CsdlDcServiceInfo csdlDcServiceInfo) {
		return getService().deleteCsdlDcServiceInfo(csdlDcServiceInfo);
	}

	/**
	* Deletes the csdl dc service info with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param idDcService the primary key of the csdl dc service info
	* @return the csdl dc service info that was removed
	* @throws PortalException if a csdl dc service info with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.CsdlDcServiceInfo deleteCsdlDcServiceInfo(
		long idDcService)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteCsdlDcServiceInfo(idDcService);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.CsdlDcServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.CsdlDcServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.dossiermgt.model.CsdlDcServiceInfo fetchCsdlDcServiceInfo(
		long idDcService) {
		return getService().fetchCsdlDcServiceInfo(idDcService);
	}

	/**
	* Returns the csdl dc service info matching the UUID and group.
	*
	* @param uuid the csdl dc service info's UUID
	* @param groupId the primary key of the group
	* @return the matching csdl dc service info, or <code>null</code> if a matching csdl dc service info could not be found
	*/
	public static org.opencps.dossiermgt.model.CsdlDcServiceInfo fetchCsdlDcServiceInfoByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchCsdlDcServiceInfoByUuidAndGroupId(uuid, groupId);
	}

	public static org.opencps.dossiermgt.model.CsdlDcServiceInfo findByServiceCodeAndStatus(
		String serviceCode, int status) {
		return getService().findByServiceCodeAndStatus(serviceCode, status);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the csdl dc service info with the primary key.
	*
	* @param idDcService the primary key of the csdl dc service info
	* @return the csdl dc service info
	* @throws PortalException if a csdl dc service info with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.CsdlDcServiceInfo getCsdlDcServiceInfo(
		long idDcService)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCsdlDcServiceInfo(idDcService);
	}

	/**
	* Returns the csdl dc service info matching the UUID and group.
	*
	* @param uuid the csdl dc service info's UUID
	* @param groupId the primary key of the group
	* @return the matching csdl dc service info
	* @throws PortalException if a matching csdl dc service info could not be found
	*/
	public static org.opencps.dossiermgt.model.CsdlDcServiceInfo getCsdlDcServiceInfoByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCsdlDcServiceInfoByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the csdl dc service infos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.CsdlDcServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of csdl dc service infos
	* @param end the upper bound of the range of csdl dc service infos (not inclusive)
	* @return the range of csdl dc service infos
	*/
	public static java.util.List<org.opencps.dossiermgt.model.CsdlDcServiceInfo> getCsdlDcServiceInfos(
		int start, int end) {
		return getService().getCsdlDcServiceInfos(start, end);
	}

	/**
	* Returns all the csdl dc service infos matching the UUID and company.
	*
	* @param uuid the UUID of the csdl dc service infos
	* @param companyId the primary key of the company
	* @return the matching csdl dc service infos, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.dossiermgt.model.CsdlDcServiceInfo> getCsdlDcServiceInfosByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService()
				   .getCsdlDcServiceInfosByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of csdl dc service infos matching the UUID and company.
	*
	* @param uuid the UUID of the csdl dc service infos
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of csdl dc service infos
	* @param end the upper bound of the range of csdl dc service infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching csdl dc service infos, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.dossiermgt.model.CsdlDcServiceInfo> getCsdlDcServiceInfosByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.CsdlDcServiceInfo> orderByComparator) {
		return getService()
				   .getCsdlDcServiceInfosByUuidAndCompanyId(uuid, companyId,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of csdl dc service infos.
	*
	* @return the number of csdl dc service infos
	*/
	public static int getCsdlDcServiceInfosCount() {
		return getService().getCsdlDcServiceInfosCount();
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
	* Updates the csdl dc service info in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param csdlDcServiceInfo the csdl dc service info
	* @return the csdl dc service info that was updated
	*/
	public static org.opencps.dossiermgt.model.CsdlDcServiceInfo updateCsdlDcServiceInfo(
		org.opencps.dossiermgt.model.CsdlDcServiceInfo csdlDcServiceInfo) {
		return getService().updateCsdlDcServiceInfo(csdlDcServiceInfo);
	}

	public static CsdlDcServiceInfoLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CsdlDcServiceInfoLocalService, CsdlDcServiceInfoLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CsdlDcServiceInfoLocalService.class);

		ServiceTracker<CsdlDcServiceInfoLocalService, CsdlDcServiceInfoLocalService> serviceTracker =
			new ServiceTracker<CsdlDcServiceInfoLocalService, CsdlDcServiceInfoLocalService>(bundle.getBundleContext(),
				CsdlDcServiceInfoLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}