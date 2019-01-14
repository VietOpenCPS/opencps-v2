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
 * Provides the local service utility for OpencpsPersonStatistic. This utility wraps
 * {@link org.opencps.statistic.service.impl.OpencpsPersonStatisticLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author khoavu
 * @see OpencpsPersonStatisticLocalService
 * @see org.opencps.statistic.service.base.OpencpsPersonStatisticLocalServiceBaseImpl
 * @see org.opencps.statistic.service.impl.OpencpsPersonStatisticLocalServiceImpl
 * @generated
 */
@ProviderType
public class OpencpsPersonStatisticLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.statistic.service.impl.OpencpsPersonStatisticLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the opencps person statistic to the database. Also notifies the appropriate model listeners.
	*
	* @param opencpsPersonStatistic the opencps person statistic
	* @return the opencps person statistic that was added
	*/
	public static org.opencps.statistic.model.OpencpsPersonStatistic addOpencpsPersonStatistic(
		org.opencps.statistic.model.OpencpsPersonStatistic opencpsPersonStatistic) {
		return getService().addOpencpsPersonStatistic(opencpsPersonStatistic);
	}

	public static org.opencps.statistic.model.OpencpsPersonStatistic checkExsit(
		long groupId, int month, int year, String govAgency, Long employeeId,
		String votingCode) {
		return getService()
				   .checkExsit(groupId, month, year, govAgency, employeeId,
			votingCode);
	}

	/**
	* Creates a new opencps person statistic with the primary key. Does not add the opencps person statistic to the database.
	*
	* @param personStatisticId the primary key for the new opencps person statistic
	* @return the new opencps person statistic
	*/
	public static org.opencps.statistic.model.OpencpsPersonStatistic createOpencpsPersonStatistic(
		long personStatisticId) {
		return getService().createOpencpsPersonStatistic(personStatisticId);
	}

	/**
	* Deletes the opencps person statistic with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param personStatisticId the primary key of the opencps person statistic
	* @return the opencps person statistic that was removed
	* @throws PortalException if a opencps person statistic with the primary key could not be found
	*/
	public static org.opencps.statistic.model.OpencpsPersonStatistic deleteOpencpsPersonStatistic(
		long personStatisticId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteOpencpsPersonStatistic(personStatisticId);
	}

	/**
	* Deletes the opencps person statistic from the database. Also notifies the appropriate model listeners.
	*
	* @param opencpsPersonStatistic the opencps person statistic
	* @return the opencps person statistic that was removed
	*/
	public static org.opencps.statistic.model.OpencpsPersonStatistic deleteOpencpsPersonStatistic(
		org.opencps.statistic.model.OpencpsPersonStatistic opencpsPersonStatistic) {
		return getService().deleteOpencpsPersonStatistic(opencpsPersonStatistic);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.statistic.model.impl.OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.statistic.model.impl.OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.statistic.model.OpencpsPersonStatistic fetchOpencpsPersonStatistic(
		long personStatisticId) {
		return getService().fetchOpencpsPersonStatistic(personStatisticId);
	}

	/**
	* Returns the opencps person statistic matching the UUID and group.
	*
	* @param uuid the opencps person statistic's UUID
	* @param groupId the primary key of the group
	* @return the matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	*/
	public static org.opencps.statistic.model.OpencpsPersonStatistic fetchOpencpsPersonStatisticByUuidAndGroupId(
		String uuid, long groupId) {
		return getService()
				   .fetchOpencpsPersonStatisticByUuidAndGroupId(uuid, groupId);
	}

	public static java.util.List<org.opencps.statistic.model.OpencpsPersonStatistic> fetchPersonStatistic(
		long groupId, int month, int year, String votingCode, Long employeeId,
		String govAgencyCode, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .fetchPersonStatistic(groupId, month, year, votingCode,
			employeeId, govAgencyCode, start, end);
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
	* Returns the opencps person statistic with the primary key.
	*
	* @param personStatisticId the primary key of the opencps person statistic
	* @return the opencps person statistic
	* @throws PortalException if a opencps person statistic with the primary key could not be found
	*/
	public static org.opencps.statistic.model.OpencpsPersonStatistic getOpencpsPersonStatistic(
		long personStatisticId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getOpencpsPersonStatistic(personStatisticId);
	}

	/**
	* Returns the opencps person statistic matching the UUID and group.
	*
	* @param uuid the opencps person statistic's UUID
	* @param groupId the primary key of the group
	* @return the matching opencps person statistic
	* @throws PortalException if a matching opencps person statistic could not be found
	*/
	public static org.opencps.statistic.model.OpencpsPersonStatistic getOpencpsPersonStatisticByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getOpencpsPersonStatisticByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the opencps person statistics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.statistic.model.impl.OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of opencps person statistics
	* @param end the upper bound of the range of opencps person statistics (not inclusive)
	* @return the range of opencps person statistics
	*/
	public static java.util.List<org.opencps.statistic.model.OpencpsPersonStatistic> getOpencpsPersonStatistics(
		int start, int end) {
		return getService().getOpencpsPersonStatistics(start, end);
	}

	/**
	* Returns all the opencps person statistics matching the UUID and company.
	*
	* @param uuid the UUID of the opencps person statistics
	* @param companyId the primary key of the company
	* @return the matching opencps person statistics, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.statistic.model.OpencpsPersonStatistic> getOpencpsPersonStatisticsByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService()
				   .getOpencpsPersonStatisticsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of opencps person statistics matching the UUID and company.
	*
	* @param uuid the UUID of the opencps person statistics
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of opencps person statistics
	* @param end the upper bound of the range of opencps person statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching opencps person statistics, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.statistic.model.OpencpsPersonStatistic> getOpencpsPersonStatisticsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.statistic.model.OpencpsPersonStatistic> orderByComparator) {
		return getService()
				   .getOpencpsPersonStatisticsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of opencps person statistics.
	*
	* @return the number of opencps person statistics
	*/
	public static int getOpencpsPersonStatisticsCount() {
		return getService().getOpencpsPersonStatisticsCount();
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

	public static void removePersonStatisticByMonthYear(long groupId,
		int month, int year) {
		getService().removePersonStatisticByMonthYear(groupId, month, year);
	}

	public static void removePersonStatisticByYear(long companyId,
		long groupId, int month, int year) {
		getService().removePersonStatisticByYear(companyId, groupId, month, year);
	}

	public static java.util.List<org.opencps.statistic.model.OpencpsPersonStatistic> searchPersonStatistic(
		long groupId, int month, int year, String votingCode, Long employeeId,
		String govAgencyCode, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .searchPersonStatistic(groupId, month, year, votingCode,
			employeeId, govAgencyCode, start, end);
	}

	/**
	* Updates the opencps person statistic in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param opencpsPersonStatistic the opencps person statistic
	* @return the opencps person statistic that was updated
	*/
	public static org.opencps.statistic.model.OpencpsPersonStatistic updateOpencpsPersonStatistic(
		org.opencps.statistic.model.OpencpsPersonStatistic opencpsPersonStatistic) {
		return getService().updateOpencpsPersonStatistic(opencpsPersonStatistic);
	}

	public static org.opencps.statistic.model.OpencpsPersonStatistic updatePersonStatistic(
		long personStatisticId, long companyId, long groupId, long userId,
		String userName, int month, int year, String votingSubject,
		int totalVoted, int veryGoodCount, int goodCount, int badCount,
		int percentVeryGood, int percentGood, int percentBad,
		String govAgencyCode, String govAgencyName, long employeeId,
		String votingCode, int totalCount) {
		return getService()
				   .updatePersonStatistic(personStatisticId, companyId,
			groupId, userId, userName, month, year, votingSubject, totalVoted,
			veryGoodCount, goodCount, badCount, percentVeryGood, percentGood,
			percentBad, govAgencyCode, govAgencyName, employeeId, votingCode,
			totalCount);
	}

	public static OpencpsPersonStatisticLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<OpencpsPersonStatisticLocalService, OpencpsPersonStatisticLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(OpencpsPersonStatisticLocalService.class);

		ServiceTracker<OpencpsPersonStatisticLocalService, OpencpsPersonStatisticLocalService> serviceTracker =
			new ServiceTracker<OpencpsPersonStatisticLocalService, OpencpsPersonStatisticLocalService>(bundle.getBundleContext(),
				OpencpsPersonStatisticLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}