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

package org.opencps.statistic.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for OpencpsDossierStatisticMgt. This utility wraps
 * {@link org.opencps.statistic.service.impl.OpencpsDossierStatisticMgtLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author khoavu
 * @see OpencpsDossierStatisticMgtLocalService
 * @see org.opencps.statistic.service.base.OpencpsDossierStatisticMgtLocalServiceBaseImpl
 * @see org.opencps.statistic.service.impl.OpencpsDossierStatisticMgtLocalServiceImpl
 * @generated
 */
@ProviderType
public class OpencpsDossierStatisticMgtLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.statistic.service.impl.OpencpsDossierStatisticMgtLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the opencps dossier statistic mgt to the database. Also notifies the appropriate model listeners.
	*
	* @param opencpsDossierStatisticMgt the opencps dossier statistic mgt
	* @return the opencps dossier statistic mgt that was added
	*/
	public static org.opencps.statistic.model.OpencpsDossierStatisticMgt addOpencpsDossierStatisticMgt(
		org.opencps.statistic.model.OpencpsDossierStatisticMgt opencpsDossierStatisticMgt) {
		return getService()
				   .addOpencpsDossierStatisticMgt(opencpsDossierStatisticMgt);
	}

	public static org.opencps.statistic.model.OpencpsDossierStatisticMgt checkContainGroupBy(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, int groupBy) {
		return getService()
				   .checkContainGroupBy(groupId, month, year, govAgencyCode,
			domainCode, groupBy);
	}

	public static org.opencps.statistic.model.OpencpsDossierStatisticMgt checkContains(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode) {
		return getService()
				   .checkContains(groupId, month, year, govAgencyCode,
			domainCode);
	}

	/**
	* Creates a new opencps dossier statistic mgt with the primary key. Does not add the opencps dossier statistic mgt to the database.
	*
	* @param dossierStatisticMgtId the primary key for the new opencps dossier statistic mgt
	* @return the new opencps dossier statistic mgt
	*/
	public static org.opencps.statistic.model.OpencpsDossierStatisticMgt createOpencpsDossierStatisticMgt(
		long dossierStatisticMgtId) {
		return getService()
				   .createOpencpsDossierStatisticMgt(dossierStatisticMgtId);
	}

	/**
	* Deletes the opencps dossier statistic mgt with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierStatisticMgtId the primary key of the opencps dossier statistic mgt
	* @return the opencps dossier statistic mgt that was removed
	* @throws PortalException if a opencps dossier statistic mgt with the primary key could not be found
	*/
	public static org.opencps.statistic.model.OpencpsDossierStatisticMgt deleteOpencpsDossierStatisticMgt(
		long dossierStatisticMgtId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .deleteOpencpsDossierStatisticMgt(dossierStatisticMgtId);
	}

	/**
	* Deletes the opencps dossier statistic mgt from the database. Also notifies the appropriate model listeners.
	*
	* @param opencpsDossierStatisticMgt the opencps dossier statistic mgt
	* @return the opencps dossier statistic mgt that was removed
	*/
	public static org.opencps.statistic.model.OpencpsDossierStatisticMgt deleteOpencpsDossierStatisticMgt(
		org.opencps.statistic.model.OpencpsDossierStatisticMgt opencpsDossierStatisticMgt) {
		return getService()
				   .deleteOpencpsDossierStatisticMgt(opencpsDossierStatisticMgt);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.statistic.model.impl.OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.statistic.model.impl.OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.statistic.model.OpencpsDossierStatisticMgt fetchOpencpsDossierStatisticMgt(
		long dossierStatisticMgtId) {
		return getService()
				   .fetchOpencpsDossierStatisticMgt(dossierStatisticMgtId);
	}

	/**
	* Returns the opencps dossier statistic mgt matching the UUID and group.
	*
	* @param uuid the opencps dossier statistic mgt's UUID
	* @param groupId the primary key of the group
	* @return the matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	*/
	public static org.opencps.statistic.model.OpencpsDossierStatisticMgt fetchOpencpsDossierStatisticMgtByUuidAndGroupId(
		String uuid, long groupId) {
		return getService()
				   .fetchOpencpsDossierStatisticMgtByUuidAndGroupId(uuid,
			groupId);
	}

	public static java.util.List<org.opencps.statistic.model.OpencpsDossierStatisticMgt> findByG_NM_Y(
		long groupId, int month, int year) {
		return getService().findByG_NM_Y(groupId, month, year);
	}

	public static java.util.List<org.opencps.statistic.model.OpencpsDossierStatisticMgt> findByG_NM_Y_G_D_GB(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, int groupBy) {
		return getService()
				   .findByG_NM_Y_G_D_GB(groupId, month, year, govAgencyCode,
			domainCode, groupBy);
	}

	public static java.util.List<org.opencps.statistic.model.OpencpsDossierStatisticMgt> findByG_NM_Y_GB(
		long groupId, int month, int year, int groupBy) {
		return getService().findByG_NM_Y_GB(groupId, month, year, groupBy);
	}

	public static java.util.List<org.opencps.statistic.model.OpencpsDossierStatisticMgt> findByG_Y_ARR(
		long groupId, int[] yearArr) {
		return getService().findByG_Y_ARR(groupId, yearArr);
	}

	public static java.util.List<org.opencps.statistic.model.OpencpsDossierStatisticMgt> findByG_Y_ARR_GB(
		long groupId, int[] yearArr, int groupBy) {
		return getService().findByG_Y_ARR_GB(groupId, yearArr, groupBy);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the opencps dossier statistic mgt with the primary key.
	*
	* @param dossierStatisticMgtId the primary key of the opencps dossier statistic mgt
	* @return the opencps dossier statistic mgt
	* @throws PortalException if a opencps dossier statistic mgt with the primary key could not be found
	*/
	public static org.opencps.statistic.model.OpencpsDossierStatisticMgt getOpencpsDossierStatisticMgt(
		long dossierStatisticMgtId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getOpencpsDossierStatisticMgt(dossierStatisticMgtId);
	}

	/**
	* Returns the opencps dossier statistic mgt matching the UUID and group.
	*
	* @param uuid the opencps dossier statistic mgt's UUID
	* @param groupId the primary key of the group
	* @return the matching opencps dossier statistic mgt
	* @throws PortalException if a matching opencps dossier statistic mgt could not be found
	*/
	public static org.opencps.statistic.model.OpencpsDossierStatisticMgt getOpencpsDossierStatisticMgtByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getOpencpsDossierStatisticMgtByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the opencps dossier statistic mgts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.statistic.model.impl.OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @return the range of opencps dossier statistic mgts
	*/
	public static java.util.List<org.opencps.statistic.model.OpencpsDossierStatisticMgt> getOpencpsDossierStatisticMgts(
		int start, int end) {
		return getService().getOpencpsDossierStatisticMgts(start, end);
	}

	/**
	* Returns the number of opencps dossier statistic mgts.
	*
	* @return the number of opencps dossier statistic mgts
	*/
	public static int getOpencpsDossierStatisticMgtsCount() {
		return getService().getOpencpsDossierStatisticMgtsCount();
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

	public static void updateBatchStatisticMgt(
		java.util.List<com.liferay.portal.kernel.json.JSONObject> dossierDataObjs)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().updateBatchStatisticMgt(dossierDataObjs);
	}

	/**
	* Updates the opencps dossier statistic mgt in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param opencpsDossierStatisticMgt the opencps dossier statistic mgt
	* @return the opencps dossier statistic mgt that was updated
	*/
	public static org.opencps.statistic.model.OpencpsDossierStatisticMgt updateOpencpsDossierStatisticMgt(
		org.opencps.statistic.model.OpencpsDossierStatisticMgt opencpsDossierStatisticMgt) {
		return getService()
				   .updateOpencpsDossierStatisticMgt(opencpsDossierStatisticMgt);
	}

	public static OpencpsDossierStatisticMgtLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<OpencpsDossierStatisticMgtLocalService, OpencpsDossierStatisticMgtLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(OpencpsDossierStatisticMgtLocalService.class);

		ServiceTracker<OpencpsDossierStatisticMgtLocalService, OpencpsDossierStatisticMgtLocalService> serviceTracker =
			new ServiceTracker<OpencpsDossierStatisticMgtLocalService, OpencpsDossierStatisticMgtLocalService>(bundle.getBundleContext(),
				OpencpsDossierStatisticMgtLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}