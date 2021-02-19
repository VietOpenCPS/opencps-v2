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
 * Provides the local service utility for OpencpsDossierStatistic. This utility wraps
 * {@link org.opencps.statistic.service.impl.OpencpsDossierStatisticLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author khoavu
 * @see OpencpsDossierStatisticLocalService
 * @see org.opencps.statistic.service.base.OpencpsDossierStatisticLocalServiceBaseImpl
 * @see org.opencps.statistic.service.impl.OpencpsDossierStatisticLocalServiceImpl
 * @generated
 */
@ProviderType
public class OpencpsDossierStatisticLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.statistic.service.impl.OpencpsDossierStatisticLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the opencps dossier statistic to the database. Also notifies the appropriate model listeners.
	*
	* @param opencpsDossierStatistic the opencps dossier statistic
	* @return the opencps dossier statistic that was added
	*/
	public static org.opencps.statistic.model.OpencpsDossierStatistic addOpencpsDossierStatistic(
		org.opencps.statistic.model.OpencpsDossierStatistic opencpsDossierStatistic) {
		return getService().addOpencpsDossierStatistic(opencpsDossierStatistic);
	}

	public static org.opencps.statistic.model.OpencpsDossierStatistic checkExsit(
		long groupId, int month, int year, String govAgency, String domain) {
		return getService().checkExsit(groupId, month, year, govAgency, domain);
	}

	public static org.opencps.statistic.model.OpencpsDossierStatistic checkExsitSystem(
		long groupId, int month, int year, String govAgency, String domain,
		String system, String groupGovAgencyCode) {
		return getService()
				   .checkExsitSystem(groupId, month, year, govAgency, domain,
			system, groupGovAgencyCode);
	}

	public static org.opencps.statistic.model.OpencpsDossierStatistic checkNotDuplicate(
		long groupId, String govAgencyCode, int month, int year,
		String domainCode) {
		return getService()
				   .checkNotDuplicate(groupId, govAgencyCode, month, year,
			domainCode);
	}

	public static org.opencps.statistic.model.OpencpsDossierStatistic createOnlyStatistic(
		long companyId, long groupId, long userId, String userName, int month,
		int year, int totalCount, int deniedCount, int cancelledCount,
		int processCount, int remainingCount, int receivedCount,
		int onlineCount, int releaseCount, int betimesCount, int ontimeCount,
		int overtimeCount, int doneCount, int releasingCount,
		int unresolvedCount, int processingCount, int undueCount,
		int overdueCount, int pausingCount, int ontimePercentage,
		int overtimeInside, int overtimeOutside, int interoperatingCount,
		int waitingCount, String govAgencyCode, String govAgencyName,
		String domainCode, String domainName, int reporting, int onegateCount,
		int outsideCount, int insideCount, int fromViaPostalCount)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .createOnlyStatistic(companyId, groupId, userId, userName,
			month, year, totalCount, deniedCount, cancelledCount, processCount,
			remainingCount, receivedCount, onlineCount, releaseCount,
			betimesCount, ontimeCount, overtimeCount, doneCount,
			releasingCount, unresolvedCount, processingCount, undueCount,
			overdueCount, pausingCount, ontimePercentage, overtimeInside,
			overtimeOutside, interoperatingCount, waitingCount, govAgencyCode,
			govAgencyName, domainCode, domainName, reporting, onegateCount,
			outsideCount, insideCount, fromViaPostalCount);
	}

	public static org.opencps.statistic.model.OpencpsDossierStatistic createOnlyStatistic(
		long companyId, long groupId, long userId, String userName, int month,
		int year, int totalCount, int deniedCount, int cancelledCount,
		int processCount, int remainingCount, int receivedCount,
		int onlineCount, int releaseCount, int betimesCount, int ontimeCount,
		int overtimeCount, int doneCount, int releasingCount,
		int unresolvedCount, int processingCount, int undueCount,
		int overdueCount, int pausingCount, int ontimePercentage,
		int overtimeInside, int overtimeOutside, int interoperatingCount,
		int waitingCount, String govAgencyCode, String govAgencyName,
		String domainCode, String domainName, int reporting, int onegateCount,
		int outsideCount, int insideCount, int viaPostalCount,
		int saturdayCount, int dossierOnline3Count, int dossierOnline4Count,
		int receiveDossierSatCount, int releaseDossierSatCount,
		int fromViaPostalCount)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .createOnlyStatistic(companyId, groupId, userId, userName,
			month, year, totalCount, deniedCount, cancelledCount, processCount,
			remainingCount, receivedCount, onlineCount, releaseCount,
			betimesCount, ontimeCount, overtimeCount, doneCount,
			releasingCount, unresolvedCount, processingCount, undueCount,
			overdueCount, pausingCount, ontimePercentage, overtimeInside,
			overtimeOutside, interoperatingCount, waitingCount, govAgencyCode,
			govAgencyName, domainCode, domainName, reporting, onegateCount,
			outsideCount, insideCount, viaPostalCount, saturdayCount,
			dossierOnline3Count, dossierOnline4Count, receiveDossierSatCount,
			releaseDossierSatCount, fromViaPostalCount);
	}

	/**
	* Creates a new opencps dossier statistic with the primary key. Does not add the opencps dossier statistic to the database.
	*
	* @param dossierStatisticId the primary key for the new opencps dossier statistic
	* @return the new opencps dossier statistic
	*/
	public static org.opencps.statistic.model.OpencpsDossierStatistic createOpencpsDossierStatistic(
		long dossierStatisticId) {
		return getService().createOpencpsDossierStatistic(dossierStatisticId);
	}

	public static org.opencps.statistic.model.OpencpsDossierStatistic createOrUpdateStatistic(
		long companyId, long groupId, long userId, String userName, int month,
		int year, String system, int totalCount, int deniedCount,
		int cancelledCount, int processCount, int remainingCount,
		int receivedCount, int onlineCount, int releaseCount, int betimesCount,
		int ontimeCount, int overtimeCount, int doneCount, int releasingCount,
		int unresolvedCount, int processingCount, int undueCount,
		int overdueCount, int pausingCount, int ontimePercentage,
		int overtimeInside, int overtimeOutside, int interoperatingCount,
		int waitingCount, String govAgencyCode, String govAgencyName,
		String domainCode, String domainName, int reporting, int onegateCount,
		int outsideCount, int insideCount, int fromViaPostalCount)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .createOrUpdateStatistic(companyId, groupId, userId,
			userName, month, year, system, totalCount, deniedCount,
			cancelledCount, processCount, remainingCount, receivedCount,
			onlineCount, releaseCount, betimesCount, ontimeCount,
			overtimeCount, doneCount, releasingCount, unresolvedCount,
			processingCount, undueCount, overdueCount, pausingCount,
			ontimePercentage, overtimeInside, overtimeOutside,
			interoperatingCount, waitingCount, govAgencyCode, govAgencyName,
			domainCode, domainName, reporting, onegateCount, outsideCount,
			insideCount, fromViaPostalCount);
	}

	public static org.opencps.statistic.model.OpencpsDossierStatistic createOrUpdateStatistic(
		long companyId, long groupId, long userId, String userName, int month,
		int year, String system, int totalCount, int deniedCount,
		int cancelledCount, int processCount, int remainingCount,
		int receivedCount, int onlineCount, int releaseCount, int betimesCount,
		int ontimeCount, int overtimeCount, int doneCount, int releasingCount,
		int unresolvedCount, int processingCount, int undueCount,
		int overdueCount, int pausingCount, int ontimePercentage,
		int overtimeInside, int overtimeOutside, int interoperatingCount,
		int waitingCount, String govAgencyCode, String govAgencyName,
		String domainCode, String domainName, int reporting, int onegateCount,
		int outsideCount, int insideCount, int viaPostalCount,
		int saturdayCount, int dossierOnline3Count, int dossierOnline4Count,
		int receiveDossierSatCount, int releaseDossierSatCount,
		int fromViaPostalCount)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .createOrUpdateStatistic(companyId, groupId, userId,
			userName, month, year, system, totalCount, deniedCount,
			cancelledCount, processCount, remainingCount, receivedCount,
			onlineCount, releaseCount, betimesCount, ontimeCount,
			overtimeCount, doneCount, releasingCount, unresolvedCount,
			processingCount, undueCount, overdueCount, pausingCount,
			ontimePercentage, overtimeInside, overtimeOutside,
			interoperatingCount, waitingCount, govAgencyCode, govAgencyName,
			domainCode, domainName, reporting, onegateCount, outsideCount,
			insideCount, viaPostalCount, saturdayCount, dossierOnline3Count,
			dossierOnline4Count, receiveDossierSatCount,
			releaseDossierSatCount, fromViaPostalCount);
	}

	public static org.opencps.statistic.model.OpencpsDossierStatistic createOrUpdateStatistic(
		long companyId, long groupId, long userId, String userName, int month,
		int year, String system, int totalCount, int deniedCount,
		int cancelledCount, int processCount, int remainingCount,
		int receivedCount, int onlineCount, int releaseCount, int betimesCount,
		int ontimeCount, int overtimeCount, int doneCount, int releasingCount,
		int unresolvedCount, int processingCount, int undueCount,
		int overdueCount, int pausingCount, int ontimePercentage,
		int overtimeInside, int overtimeOutside, int interoperatingCount,
		int waitingCount, String govAgencyCode, String govAgencyName,
		String domainCode, String domainName, String groupGovAgencyCode,
		int reporting, int onegateCount, int outsideCount, int insideCount,
		int viaPostalCount, int saturdayCount, int dossierOnline3Count,
		int dossierOnline4Count, int receiveDossierSatCount,
		int releaseDossierSatCount, int fromViaPostalCount)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .createOrUpdateStatistic(companyId, groupId, userId,
			userName, month, year, system, totalCount, deniedCount,
			cancelledCount, processCount, remainingCount, receivedCount,
			onlineCount, releaseCount, betimesCount, ontimeCount,
			overtimeCount, doneCount, releasingCount, unresolvedCount,
			processingCount, undueCount, overdueCount, pausingCount,
			ontimePercentage, overtimeInside, overtimeOutside,
			interoperatingCount, waitingCount, govAgencyCode, govAgencyName,
			domainCode, domainName, groupGovAgencyCode, reporting,
			onegateCount, outsideCount, insideCount, viaPostalCount,
			saturdayCount, dossierOnline3Count, dossierOnline4Count,
			receiveDossierSatCount, releaseDossierSatCount, fromViaPostalCount);
	}

	public static org.opencps.statistic.model.OpencpsDossierStatistic createUniqueByGovMonthYearNonSystem(
		long companyId, long groupId, String govAgencyCode,
		String govAgencyName, int month, int year) {
		return getService()
				   .createUniqueByGovMonthYearNonSystem(companyId, groupId,
			govAgencyCode, govAgencyName, month, year);
	}

	public static org.opencps.statistic.model.OpencpsDossierStatistic createUniqueByGovMonthYearSystem(
		long companyId, long groupId, String govAgencyCode,
		String govAgencyName, int month, int year, String system)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .createUniqueByGovMonthYearSystem(companyId, groupId,
			govAgencyCode, govAgencyName, month, year, system);
	}

	public static org.opencps.statistic.model.OpencpsDossierStatistic createUniqueByMonthYearDomainNonSystem(
		long companyId, long groupId, int month, int year, String domainCode,
		String domainName) {
		return getService()
				   .createUniqueByMonthYearDomainNonSystem(companyId, groupId,
			month, year, domainCode, domainName);
	}

	public static org.opencps.statistic.model.OpencpsDossierStatistic createUniqueByMonthYearDomainSystem(
		long companyId, long groupId, int month, int year, String domainCode,
		String domainName, String system) {
		return getService()
				   .createUniqueByMonthYearDomainSystem(companyId, groupId,
			month, year, domainCode, domainName, system);
	}

	/**
	* Deletes the opencps dossier statistic with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierStatisticId the primary key of the opencps dossier statistic
	* @return the opencps dossier statistic that was removed
	* @throws PortalException if a opencps dossier statistic with the primary key could not be found
	*/
	public static org.opencps.statistic.model.OpencpsDossierStatistic deleteOpencpsDossierStatistic(
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
	public static org.opencps.statistic.model.OpencpsDossierStatistic deleteOpencpsDossierStatistic(
		org.opencps.statistic.model.OpencpsDossierStatistic opencpsDossierStatistic) {
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.statistic.model.impl.OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.statistic.model.impl.OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.statistic.model.OpencpsDossierStatistic fetchByG_M_Y_G_D(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode) {
		return getService()
				   .fetchByG_M_Y_G_D(groupId, month, year, govAgencyCode,
			domainCode);
	}

	public static java.util.List<org.opencps.statistic.model.OpencpsDossierStatistic> fetchDossierStatistic(
		long groupId, int month, int year, String domain, String govAgencyCode,
		String groupAgenvyCode, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .fetchDossierStatistic(groupId, month, year, domain,
			govAgencyCode, groupAgenvyCode, start, end);
	}

	public static java.util.List<org.opencps.statistic.model.OpencpsDossierStatistic> fetchDossierStatistic(
		long groupId, int month, int year, String domain, String govAgencyCode,
		String system, String groupAgenvyCode, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .fetchDossierStatistic(groupId, month, year, domain,
			govAgencyCode, system, groupAgenvyCode, start, end);
	}

	public static org.opencps.statistic.model.OpencpsDossierStatistic fetchOpencpsDossierStatistic(
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
	public static org.opencps.statistic.model.OpencpsDossierStatistic fetchOpencpsDossierStatisticByUuidAndGroupId(
		String uuid, long groupId) {
		return getService()
				   .fetchOpencpsDossierStatisticByUuidAndGroupId(uuid, groupId);
	}

	public static java.util.List<org.opencps.statistic.model.OpencpsDossierStatistic> findByG(
		long groupId) {
		return getService().findByG(groupId);
	}

	public static java.util.List<org.opencps.statistic.model.OpencpsDossierStatistic> findByG_NM_Y(
		long groupId, int notMonth, int year) {
		return getService().findByG_NM_Y(groupId, notMonth, year);
	}

	public static java.util.List<org.opencps.statistic.model.OpencpsDossierStatistic> findByM_Y_GOV_DOM_GRO_SYS(
		int month, int year, String govAgencyCode, String domainCode,
		String groupAgencyCode, String system) {
		return getService()
				   .findByM_Y_GOV_DOM_GRO_SYS(month, year, govAgencyCode,
			domainCode, groupAgencyCode, system);
	}

	public static java.util.List<org.opencps.statistic.model.OpencpsDossierStatistic> findByNOT_GROUPID(
		long groupId) {
		return getService().findByNOT_GROUPID(groupId);
	}

	public static java.util.List<org.opencps.statistic.model.OpencpsDossierStatistic> findByREPO_ARR(
		int[] reporting) {
		return getService().findByREPO_ARR(reporting);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static java.util.List<org.opencps.statistic.model.OpencpsDossierStatistic> getByG_Y_GO_DO_GR_SY(
		long groupId, int year, String[] groupAgencyArr, String domainCode,
		String groupAgency, String system) {
		return getService()
				   .getByG_Y_GO_DO_GR_SY(groupId, year, groupAgencyArr,
			domainCode, groupAgency, system);
	}

	public static org.opencps.statistic.model.OpencpsDossierStatistic getByGovMonthYear(
		long groupId, String govAgencyCode, int month, int year)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getByGovMonthYear(groupId, govAgencyCode, month, year);
	}

	public static org.opencps.statistic.model.OpencpsDossierStatistic getByGovMonthYearDomain(
		long groupId, String govAgencyCode, int month, int year,
		String domainCode, int reporting) {
		return getService()
				   .getByGovMonthYearDomain(groupId, govAgencyCode, month,
			year, domainCode, reporting);
	}

	public static java.util.List<org.opencps.statistic.model.OpencpsDossierStatistic> getByMonthYearAndNotReport(
		long groupId, int month, int year, int reporting) {
		return getService()
				   .getByMonthYearAndNotReport(groupId, month, year, reporting);
	}

	public static java.util.List<org.opencps.statistic.model.OpencpsDossierStatistic> getByNOT_G_M_Y_GOV_DOM_GRO_SYS(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, String groupAgencyCode, String system) {
		return getService()
				   .getByNOT_G_M_Y_GOV_DOM_GRO_SYS(groupId, month, year,
			govAgencyCode, domainCode, groupAgencyCode, system);
	}

	public static java.util.List<org.opencps.statistic.model.OpencpsDossierStatistic> getDomainCodes() {
		return getService().getDomainCodes();
	}

	public static java.util.List<org.opencps.statistic.model.OpencpsDossierStatistic> getDossierStatisticByMonthsYearAndReport(
		long groupId, int[] month, int year, int reporting) {
		return getService()
				   .getDossierStatisticByMonthsYearAndReport(groupId, month,
			year, reporting);
	}

	public static java.util.List<org.opencps.statistic.model.OpencpsDossierStatistic> getDossierStatisticByMonthYear(
		long groupId, int month, int year) {
		return getService().getDossierStatisticByMonthYear(groupId, month, year);
	}

	public static java.util.List<org.opencps.statistic.model.OpencpsDossierStatistic> getDossierStatisticByMonthYearAndReport(
		long groupId, int month, int year, int reporting) {
		return getService()
				   .getDossierStatisticByMonthYearAndReport(groupId, month,
			year, reporting);
	}

	public static java.util.List<org.opencps.statistic.model.OpencpsDossierStatistic> getDossierStatisticByYear(
		long companyId, long groupId, int month, int year) {
		return getService()
				   .getDossierStatisticByYear(companyId, groupId, month, year);
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static java.util.List<org.opencps.statistic.model.OpencpsDossierStatistic> getGovAgencyCodes() {
		return getService().getGovAgencyCodes();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	public static org.opencps.statistic.model.OpencpsDossierStatistic getInfoByMonthYearAndNotReport(
		long groupId, int month, int year, int reporting) {
		return getService()
				   .getInfoByMonthYearAndNotReport(groupId, month, year,
			reporting);
	}

	public static java.util.List<org.opencps.statistic.model.OpencpsDossierStatistic> getListByReporting(
		int reporting) {
		return getService().getListByReporting(reporting);
	}

	/**
	* Returns the opencps dossier statistic with the primary key.
	*
	* @param dossierStatisticId the primary key of the opencps dossier statistic
	* @return the opencps dossier statistic
	* @throws PortalException if a opencps dossier statistic with the primary key could not be found
	*/
	public static org.opencps.statistic.model.OpencpsDossierStatistic getOpencpsDossierStatistic(
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
	public static org.opencps.statistic.model.OpencpsDossierStatistic getOpencpsDossierStatisticByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getOpencpsDossierStatisticByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the opencps dossier statistics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.statistic.model.impl.OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @return the range of opencps dossier statistics
	*/
	public static java.util.List<org.opencps.statistic.model.OpencpsDossierStatistic> getOpencpsDossierStatistics(
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
	public static java.util.List<org.opencps.statistic.model.OpencpsDossierStatistic> getOpencpsDossierStatisticsByUuidAndCompanyId(
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
	public static java.util.List<org.opencps.statistic.model.OpencpsDossierStatistic> getOpencpsDossierStatisticsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.statistic.model.OpencpsDossierStatistic> orderByComparator) {
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

	public static org.opencps.statistic.model.OpencpsDossierStatistic getUniqueByGovMonthYearNonSystem(
		long groupId, String govAgencyCode, int month, int year) {
		return getService()
				   .getUniqueByGovMonthYearNonSystem(groupId, govAgencyCode,
			month, year);
	}

	public static org.opencps.statistic.model.OpencpsDossierStatistic getUniqueByGovMonthYearSystem(
		long groupId, String govAgencyCode, int month, int year, String system)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getUniqueByGovMonthYearSystem(groupId, govAgencyCode,
			month, year, system);
	}

	public static org.opencps.statistic.model.OpencpsDossierStatistic getUniqueByMonthYearDomainNonSystem(
		long groupId, int month, int year, String domainCode) {
		return getService()
				   .getUniqueByMonthYearDomainNonSystem(groupId, month, year,
			domainCode);
	}

	public static org.opencps.statistic.model.OpencpsDossierStatistic getUniqueByMonthYearDomainSystem(
		long groupId, int month, int year, String domainCode, String system) {
		return getService()
				   .getUniqueByMonthYearDomainSystem(groupId, month, year,
			domainCode, system);
	}

	public static org.opencps.statistic.model.OpencpsDossierStatistic removeByG_M_Y_G_D(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getService()
				   .removeByG_M_Y_G_D(groupId, month, year, govAgencyCode,
			domainCode);
	}

	public static void removeDossierStatisticByD_M_Y(long groupId,
		String domainCode, int month, int year)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		getService()
			.removeDossierStatisticByD_M_Y(groupId, domainCode, month, year);
	}

	public static void removeDossierStatisticByMonthYear(long groupId,
		int month, int year)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		getService().removeDossierStatisticByMonthYear(groupId, month, year);
	}

	public static void removeDossierStatisticByYear(long companyId,
		long groupId, int month, int year)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		getService()
			.removeDossierStatisticByYear(companyId, groupId, month, year);
	}

	public static java.util.List<org.opencps.statistic.model.OpencpsDossierStatistic> searchDossierStatisticSystem(
		long groupId, int month, int year, String domain, String govAgencyCode,
		String system, String groupAgenvyCode, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .searchDossierStatisticSystem(groupId, month, year, domain,
			govAgencyCode, system, groupAgenvyCode, start, end);
	}

	public static void updateBatchStatistic(
		java.util.List<com.liferay.portal.kernel.json.JSONObject> dossierDataObjs)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().updateBatchStatistic(dossierDataObjs);
	}

	public static org.opencps.statistic.model.OpencpsDossierStatistic updateDossierStatistic(
		org.opencps.statistic.model.OpencpsDossierStatistic statistic) {
		return getService().updateDossierStatistic(statistic);
	}

	public static org.opencps.statistic.model.OpencpsDossierStatistic updateOnlyStatistic(
		org.opencps.statistic.model.OpencpsDossierStatistic dossierStatistic,
		long companyId, long groupId, long userId, String userName, int month,
		int year, int totalCount, int deniedCount, int cancelledCount,
		int processCount, int remainingCount, int receivedCount,
		int onlineCount, int releaseCount, int betimesCount, int ontimeCount,
		int overtimeCount, int doneCount, int releasingCount,
		int unresolvedCount, int processingCount, int undueCount,
		int overdueCount, int pausingCount, int ontimePercentage,
		int overtimeInside, int overtimeOutside, int interoperatingCount,
		int waitingCount, String govAgencyCode, String govAgencyName,
		String domainCode, String domainName, int reporting, int onegateCount,
		int outsideCount, int insideCount, int fromViaPostalCount)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateOnlyStatistic(dossierStatistic, companyId, groupId,
			userId, userName, month, year, totalCount, deniedCount,
			cancelledCount, processCount, remainingCount, receivedCount,
			onlineCount, releaseCount, betimesCount, ontimeCount,
			overtimeCount, doneCount, releasingCount, unresolvedCount,
			processingCount, undueCount, overdueCount, pausingCount,
			ontimePercentage, overtimeInside, overtimeOutside,
			interoperatingCount, waitingCount, govAgencyCode, govAgencyName,
			domainCode, domainName, reporting, onegateCount, outsideCount,
			insideCount, fromViaPostalCount);
	}

	public static org.opencps.statistic.model.OpencpsDossierStatistic updateOnlyStatistic(
		org.opencps.statistic.model.OpencpsDossierStatistic dossierStatistic,
		long companyId, long groupId, long userId, String userName, int month,
		int year, int totalCount, int deniedCount, int cancelledCount,
		int processCount, int remainingCount, int receivedCount,
		int onlineCount, int releaseCount, int betimesCount, int ontimeCount,
		int overtimeCount, int doneCount, int releasingCount,
		int unresolvedCount, int processingCount, int undueCount,
		int overdueCount, int pausingCount, int ontimePercentage,
		int overtimeInside, int overtimeOutside, int interoperatingCount,
		int waitingCount, String govAgencyCode, String govAgencyName,
		String domainCode, String domainName, int reporting, int onegateCount,
		int outsideCount, int insideCount, int viaPostalCount,
		int saturdayCount, int dossierOnline3Count, int dossierOnline4Count,
		int receiveDossierSatCount, int releaseDossierSatCount,
		int fromViaPostalCount)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateOnlyStatistic(dossierStatistic, companyId, groupId,
			userId, userName, month, year, totalCount, deniedCount,
			cancelledCount, processCount, remainingCount, receivedCount,
			onlineCount, releaseCount, betimesCount, ontimeCount,
			overtimeCount, doneCount, releasingCount, unresolvedCount,
			processingCount, undueCount, overdueCount, pausingCount,
			ontimePercentage, overtimeInside, overtimeOutside,
			interoperatingCount, waitingCount, govAgencyCode, govAgencyName,
			domainCode, domainName, reporting, onegateCount, outsideCount,
			insideCount, viaPostalCount, saturdayCount, dossierOnline3Count,
			dossierOnline4Count, receiveDossierSatCount,
			releaseDossierSatCount, fromViaPostalCount);
	}

	/**
	* Updates the opencps dossier statistic in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param opencpsDossierStatistic the opencps dossier statistic
	* @return the opencps dossier statistic that was updated
	*/
	public static org.opencps.statistic.model.OpencpsDossierStatistic updateOpencpsDossierStatistic(
		org.opencps.statistic.model.OpencpsDossierStatistic opencpsDossierStatistic) {
		return getService()
				   .updateOpencpsDossierStatistic(opencpsDossierStatistic);
	}

	public static org.opencps.statistic.model.OpencpsDossierStatistic updateStatistic(
		long dossierStatisticId, long companyId, long groupId, long userId,
		String userName, int month, int year, int totalCount, int deniedCount,
		int cancelledCount, int processCount, int remainingCount,
		int receivedCount, int onlineCount, int releaseCount, int betimesCount,
		int ontimeCount, int overtimeCount, int doneCount, int releasingCount,
		int unresolvedCount, int processingCount, int undueCount,
		int overdueCount, int pausingCount, int ontimePercentage,
		int overtimeInside, int overtimeOutside, int interoperatingCount,
		int waitingCount, int viaPostalCount, int saturdayCount,
		int dossierOnline3Count, int dossierOnline4Count,
		int receiveDossierSatCount, int releaseDossierSatCount,
		String govAgencyCode, String govAgencyName, String domainCode,
		String domainName, int reporting, int onegateCount, int outsideCount,
		int insideCount, int fromViaPostalCount)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateStatistic(dossierStatisticId, companyId, groupId,
			userId, userName, month, year, totalCount, deniedCount,
			cancelledCount, processCount, remainingCount, receivedCount,
			onlineCount, releaseCount, betimesCount, ontimeCount,
			overtimeCount, doneCount, releasingCount, unresolvedCount,
			processingCount, undueCount, overdueCount, pausingCount,
			ontimePercentage, overtimeInside, overtimeOutside,
			interoperatingCount, waitingCount, viaPostalCount, saturdayCount,
			dossierOnline3Count, dossierOnline4Count, receiveDossierSatCount,
			releaseDossierSatCount, govAgencyCode, govAgencyName, domainCode,
			domainName, reporting, onegateCount, outsideCount, insideCount,
			fromViaPostalCount);
	}

	public static org.opencps.statistic.model.OpencpsDossierStatistic updateStatistic(
		long dossierStatisticId, long companyId, long groupId, long userId,
		String userName, int month, int year, int totalCount, int deniedCount,
		int cancelledCount, int processCount, int remainingCount,
		int receivedCount, int onlineCount, int releaseCount, int betimesCount,
		int ontimeCount, int overtimeCount, int doneCount, int releasingCount,
		int unresolvedCount, int processingCount, int undueCount,
		int overdueCount, int pausingCount, int ontimePercentage,
		int overtimeInside, int overtimeOutside, int interoperatingCount,
		int waitingCount, String govAgencyCode, String govAgencyName,
		String domainCode, String domainName, int reporting, int onegateCount,
		int outsideCount, int insideCount, int fromViaPostalCount)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateStatistic(dossierStatisticId, companyId, groupId,
			userId, userName, month, year, totalCount, deniedCount,
			cancelledCount, processCount, remainingCount, receivedCount,
			onlineCount, releaseCount, betimesCount, ontimeCount,
			overtimeCount, doneCount, releasingCount, unresolvedCount,
			processingCount, undueCount, overdueCount, pausingCount,
			ontimePercentage, overtimeInside, overtimeOutside,
			interoperatingCount, waitingCount, govAgencyCode, govAgencyName,
			domainCode, domainName, reporting, onegateCount, outsideCount,
			insideCount, fromViaPostalCount);
	}

	public static void updateStatisticData(
		java.util.Map<String, org.opencps.statistic.dto.DossierStatisticData> statisticData)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.kernel.exception.PortalException {
		getService().updateStatisticData(statisticData);
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