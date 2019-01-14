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
 * Provides the local service utility for OpencpsVotingStatistic. This utility wraps
 * {@link org.opencps.statistic.service.impl.OpencpsVotingStatisticLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author khoavu
 * @see OpencpsVotingStatisticLocalService
 * @see org.opencps.statistic.service.base.OpencpsVotingStatisticLocalServiceBaseImpl
 * @see org.opencps.statistic.service.impl.OpencpsVotingStatisticLocalServiceImpl
 * @generated
 */
@ProviderType
public class OpencpsVotingStatisticLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.statistic.service.impl.OpencpsVotingStatisticLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the opencps voting statistic to the database. Also notifies the appropriate model listeners.
	*
	* @param opencpsVotingStatistic the opencps voting statistic
	* @return the opencps voting statistic that was added
	*/
	public static org.opencps.statistic.model.OpencpsVotingStatistic addOpencpsVotingStatistic(
		org.opencps.statistic.model.OpencpsVotingStatistic opencpsVotingStatistic) {
		return getService().addOpencpsVotingStatistic(opencpsVotingStatistic);
	}

	public static org.opencps.statistic.model.OpencpsVotingStatistic checkExsit(
		long groupId, int month, int year, String govAgency, String domain,
		String votingCode) {
		return getService()
				   .checkExsit(groupId, month, year, govAgency, domain,
			votingCode);
	}

	/**
	* Creates a new opencps voting statistic with the primary key. Does not add the opencps voting statistic to the database.
	*
	* @param votingStatisticId the primary key for the new opencps voting statistic
	* @return the new opencps voting statistic
	*/
	public static org.opencps.statistic.model.OpencpsVotingStatistic createOpencpsVotingStatistic(
		long votingStatisticId) {
		return getService().createOpencpsVotingStatistic(votingStatisticId);
	}

	/**
	* Deletes the opencps voting statistic with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param votingStatisticId the primary key of the opencps voting statistic
	* @return the opencps voting statistic that was removed
	* @throws PortalException if a opencps voting statistic with the primary key could not be found
	*/
	public static org.opencps.statistic.model.OpencpsVotingStatistic deleteOpencpsVotingStatistic(
		long votingStatisticId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteOpencpsVotingStatistic(votingStatisticId);
	}

	/**
	* Deletes the opencps voting statistic from the database. Also notifies the appropriate model listeners.
	*
	* @param opencpsVotingStatistic the opencps voting statistic
	* @return the opencps voting statistic that was removed
	*/
	public static org.opencps.statistic.model.OpencpsVotingStatistic deleteOpencpsVotingStatistic(
		org.opencps.statistic.model.OpencpsVotingStatistic opencpsVotingStatistic) {
		return getService().deleteOpencpsVotingStatistic(opencpsVotingStatistic);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.statistic.model.impl.OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.statistic.model.impl.OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.statistic.model.OpencpsVotingStatistic fetchOpencpsVotingStatistic(
		long votingStatisticId) {
		return getService().fetchOpencpsVotingStatistic(votingStatisticId);
	}

	/**
	* Returns the opencps voting statistic matching the UUID and group.
	*
	* @param uuid the opencps voting statistic's UUID
	* @param groupId the primary key of the group
	* @return the matching opencps voting statistic, or <code>null</code> if a matching opencps voting statistic could not be found
	*/
	public static org.opencps.statistic.model.OpencpsVotingStatistic fetchOpencpsVotingStatisticByUuidAndGroupId(
		String uuid, long groupId) {
		return getService()
				   .fetchOpencpsVotingStatisticByUuidAndGroupId(uuid, groupId);
	}

	public static java.util.List<org.opencps.statistic.model.OpencpsVotingStatistic> fetchVotingStatistic(
		long groupId, int month, int year, String votingCode, String domain,
		String govAgencyCode, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .fetchVotingStatistic(groupId, month, year, votingCode,
			domain, govAgencyCode, start, end);
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
	* Returns the opencps voting statistic with the primary key.
	*
	* @param votingStatisticId the primary key of the opencps voting statistic
	* @return the opencps voting statistic
	* @throws PortalException if a opencps voting statistic with the primary key could not be found
	*/
	public static org.opencps.statistic.model.OpencpsVotingStatistic getOpencpsVotingStatistic(
		long votingStatisticId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getOpencpsVotingStatistic(votingStatisticId);
	}

	/**
	* Returns the opencps voting statistic matching the UUID and group.
	*
	* @param uuid the opencps voting statistic's UUID
	* @param groupId the primary key of the group
	* @return the matching opencps voting statistic
	* @throws PortalException if a matching opencps voting statistic could not be found
	*/
	public static org.opencps.statistic.model.OpencpsVotingStatistic getOpencpsVotingStatisticByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getOpencpsVotingStatisticByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the opencps voting statistics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.statistic.model.impl.OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of opencps voting statistics
	* @param end the upper bound of the range of opencps voting statistics (not inclusive)
	* @return the range of opencps voting statistics
	*/
	public static java.util.List<org.opencps.statistic.model.OpencpsVotingStatistic> getOpencpsVotingStatistics(
		int start, int end) {
		return getService().getOpencpsVotingStatistics(start, end);
	}

	/**
	* Returns all the opencps voting statistics matching the UUID and company.
	*
	* @param uuid the UUID of the opencps voting statistics
	* @param companyId the primary key of the company
	* @return the matching opencps voting statistics, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.statistic.model.OpencpsVotingStatistic> getOpencpsVotingStatisticsByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService()
				   .getOpencpsVotingStatisticsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of opencps voting statistics matching the UUID and company.
	*
	* @param uuid the UUID of the opencps voting statistics
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of opencps voting statistics
	* @param end the upper bound of the range of opencps voting statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching opencps voting statistics, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.statistic.model.OpencpsVotingStatistic> getOpencpsVotingStatisticsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.statistic.model.OpencpsVotingStatistic> orderByComparator) {
		return getService()
				   .getOpencpsVotingStatisticsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of opencps voting statistics.
	*
	* @return the number of opencps voting statistics
	*/
	public static int getOpencpsVotingStatisticsCount() {
		return getService().getOpencpsVotingStatisticsCount();
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

	public static void removeVotingStatisticByD_M_Y(long groupId,
		String domainCode, int month, int year) {
		getService()
			.removeVotingStatisticByD_M_Y(groupId, domainCode, month, year);
	}

	public static void removeVotingStatisticByMonthYear(long groupId,
		int month, int year) {
		getService().removeVotingStatisticByMonthYear(groupId, month, year);
	}

	public static void removeVotingStatisticByYear(long companyId,
		long groupId, int month, int year) {
		getService().removeVotingStatisticByYear(companyId, groupId, month, year);
	}

	public static java.util.List<org.opencps.statistic.model.OpencpsVotingStatistic> searchVotingStatistic(
		long groupId, int month, int year, String votingCode, String domain,
		String govAgencyCode, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .searchVotingStatistic(groupId, month, year, votingCode,
			domain, govAgencyCode, start, end);
	}

	/**
	* Updates the opencps voting statistic in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param opencpsVotingStatistic the opencps voting statistic
	* @return the opencps voting statistic that was updated
	*/
	public static org.opencps.statistic.model.OpencpsVotingStatistic updateOpencpsVotingStatistic(
		org.opencps.statistic.model.OpencpsVotingStatistic opencpsVotingStatistic) {
		return getService().updateOpencpsVotingStatistic(opencpsVotingStatistic);
	}

	public static org.opencps.statistic.model.OpencpsVotingStatistic updateVotingStatistic(
		long votingStatisticId, long companyId, long groupId, long userId,
		String userName, int month, int year, String votingSubject,
		int totalVoted, int veryGoodCount, int goodCount, int badCount,
		int percentVeryGood, int percentGood, int percentBad,
		String govAgencyCode, String govAgencyName, String domainCode,
		String domainName, String votingCode, int totalCount) {
		return getService()
				   .updateVotingStatistic(votingStatisticId, companyId,
			groupId, userId, userName, month, year, votingSubject, totalVoted,
			veryGoodCount, goodCount, badCount, percentVeryGood, percentGood,
			percentBad, govAgencyCode, govAgencyName, domainCode, domainName,
			votingCode, totalCount);
	}

	public static OpencpsVotingStatisticLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<OpencpsVotingStatisticLocalService, OpencpsVotingStatisticLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(OpencpsVotingStatisticLocalService.class);

		ServiceTracker<OpencpsVotingStatisticLocalService, OpencpsVotingStatisticLocalService> serviceTracker =
			new ServiceTracker<OpencpsVotingStatisticLocalService, OpencpsVotingStatisticLocalService>(bundle.getBundleContext(),
				OpencpsVotingStatisticLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}