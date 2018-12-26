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
 * Provides the local service utility for OpencpsDossierStatistic. This utility wraps
 * {@link org.opencps.dossiermgt.service.impl.OpencpsDossierStatisticLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author huymq
 * @see OpencpsDossierStatisticLocalService
 * @see org.opencps.dossiermgt.service.base.OpencpsDossierStatisticLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.OpencpsDossierStatisticLocalServiceImpl
 * @generated
 */
@ProviderType
public class OpencpsDossierStatisticLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.OpencpsDossierStatisticLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the opencps dossier statistic to the database. Also notifies the appropriate model listeners.
	*
	* @param opencpsDossierStatistic the opencps dossier statistic
	* @return the opencps dossier statistic that was added
	*/
	public static org.opencps.dossiermgt.model.OpencpsDossierStatistic addOpencpsDossierStatistic(
		org.opencps.dossiermgt.model.OpencpsDossierStatistic opencpsDossierStatistic) {
		return getService().addOpencpsDossierStatistic(opencpsDossierStatistic);
	}

	/**
	* Creates a new opencps dossier statistic with the primary key. Does not add the opencps dossier statistic to the database.
	*
	* @param dossierStatisticId the primary key for the new opencps dossier statistic
	* @return the new opencps dossier statistic
	*/
	public static org.opencps.dossiermgt.model.OpencpsDossierStatistic createOpencpsDossierStatistic(
		long dossierStatisticId) {
		return getService().createOpencpsDossierStatistic(dossierStatisticId);
	}

	/**
	* Deletes the opencps dossier statistic with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierStatisticId the primary key of the opencps dossier statistic
	* @return the opencps dossier statistic that was removed
	* @throws PortalException if a opencps dossier statistic with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.OpencpsDossierStatistic deleteOpencpsDossierStatistic(
		long dossierStatisticId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteOpencpsDossierStatistic(dossierStatisticId);
	}

	/**
	* Deletes the opencps dossier statistic from the database. Also notifies the appropriate model listeners.
	*
	* @param opencpsDossierStatistic the opencps dossier statistic
	* @return the opencps dossier statistic that was removed
	*/
	public static org.opencps.dossiermgt.model.OpencpsDossierStatistic deleteOpencpsDossierStatistic(
		org.opencps.dossiermgt.model.OpencpsDossierStatistic opencpsDossierStatistic) {
		return getService()
				   .deleteOpencpsDossierStatistic(opencpsDossierStatistic);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.dossiermgt.model.OpencpsDossierStatistic fetchByG_M_Y_G_D(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode) {
		return getService()
				   .fetchByG_M_Y_G_D(groupId, month, year, govAgencyCode,
			domainCode);
	}

	public static org.opencps.dossiermgt.model.OpencpsDossierStatistic fetchOpencpsDossierStatistic(
		long dossierStatisticId) {
		return getService().fetchOpencpsDossierStatistic(dossierStatisticId);
	}

	/**
	* Returns the opencps dossier statistic matching the UUID and group.
	*
	* @param uuid the opencps dossier statistic's UUID
	* @param groupId the primary key of the group
	* @return the matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static org.opencps.dossiermgt.model.OpencpsDossierStatistic fetchOpencpsDossierStatisticByUuidAndGroupId(
		String uuid, long groupId) {
		return getService()
				   .fetchOpencpsDossierStatisticByUuidAndGroupId(uuid, groupId);
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
	* Returns the opencps dossier statistic with the primary key.
	*
	* @param dossierStatisticId the primary key of the opencps dossier statistic
	* @return the opencps dossier statistic
	* @throws PortalException if a opencps dossier statistic with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.OpencpsDossierStatistic getOpencpsDossierStatistic(
		long dossierStatisticId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getOpencpsDossierStatistic(dossierStatisticId);
	}

	/**
	* Returns the opencps dossier statistic matching the UUID and group.
	*
	* @param uuid the opencps dossier statistic's UUID
	* @param groupId the primary key of the group
	* @return the matching opencps dossier statistic
	* @throws PortalException if a matching opencps dossier statistic could not be found
	*/
	public static org.opencps.dossiermgt.model.OpencpsDossierStatistic getOpencpsDossierStatisticByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getOpencpsDossierStatisticByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the opencps dossier statistics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @return the range of opencps dossier statistics
	*/
	public static java.util.List<org.opencps.dossiermgt.model.OpencpsDossierStatistic> getOpencpsDossierStatistics(
		int start, int end) {
		return getService().getOpencpsDossierStatistics(start, end);
	}

	/**
	* Returns all the opencps dossier statistics matching the UUID and company.
	*
	* @param uuid the UUID of the opencps dossier statistics
	* @param companyId the primary key of the company
	* @return the matching opencps dossier statistics, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.dossiermgt.model.OpencpsDossierStatistic> getOpencpsDossierStatisticsByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService()
				   .getOpencpsDossierStatisticsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of opencps dossier statistics matching the UUID and company.
	*
	* @param uuid the UUID of the opencps dossier statistics
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching opencps dossier statistics, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.dossiermgt.model.OpencpsDossierStatistic> getOpencpsDossierStatisticsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.OpencpsDossierStatistic> orderByComparator) {
		return getService()
				   .getOpencpsDossierStatisticsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of opencps dossier statistics.
	*
	* @return the number of opencps dossier statistics
	*/
	public static int getOpencpsDossierStatisticsCount() {
		return getService().getOpencpsDossierStatisticsCount();
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
	* Updates the opencps dossier statistic in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param opencpsDossierStatistic the opencps dossier statistic
	* @return the opencps dossier statistic that was updated
	*/
	public static org.opencps.dossiermgt.model.OpencpsDossierStatistic updateOpencpsDossierStatistic(
		org.opencps.dossiermgt.model.OpencpsDossierStatistic opencpsDossierStatistic) {
		return getService()
				   .updateOpencpsDossierStatistic(opencpsDossierStatistic);
	}

	public static OpencpsDossierStatisticLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<OpencpsDossierStatisticLocalService, OpencpsDossierStatisticLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(OpencpsDossierStatisticLocalService.class);

		ServiceTracker<OpencpsDossierStatisticLocalService, OpencpsDossierStatisticLocalService> serviceTracker =
			new ServiceTracker<OpencpsDossierStatisticLocalService, OpencpsDossierStatisticLocalService>(bundle.getBundleContext(),
				OpencpsDossierStatisticLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}