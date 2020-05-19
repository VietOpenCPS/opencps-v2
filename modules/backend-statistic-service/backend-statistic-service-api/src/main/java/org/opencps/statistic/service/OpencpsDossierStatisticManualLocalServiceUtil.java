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
 * Provides the local service utility for OpencpsDossierStatisticManual. This utility wraps
 * {@link org.opencps.statistic.service.impl.OpencpsDossierStatisticManualLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author khoavu
 * @see OpencpsDossierStatisticManualLocalService
 * @see org.opencps.statistic.service.base.OpencpsDossierStatisticManualLocalServiceBaseImpl
 * @see org.opencps.statistic.service.impl.OpencpsDossierStatisticManualLocalServiceImpl
 * @generated
 */
@ProviderType
public class OpencpsDossierStatisticManualLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.statistic.service.impl.OpencpsDossierStatisticManualLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the opencps dossier statistic manual to the database. Also notifies the appropriate model listeners.
	*
	* @param opencpsDossierStatisticManual the opencps dossier statistic manual
	* @return the opencps dossier statistic manual that was added
	*/
	public static org.opencps.statistic.model.OpencpsDossierStatisticManual addOpencpsDossierStatisticManual(
		org.opencps.statistic.model.OpencpsDossierStatisticManual opencpsDossierStatisticManual) {
		return getService()
				   .addOpencpsDossierStatisticManual(opencpsDossierStatisticManual);
	}

	public static org.opencps.statistic.model.OpencpsDossierStatisticManual checkNotDuplicate(
		long groupId, String govAgencyCode, int month, int year,
		String domainCode) {
		return getService()
				   .checkNotDuplicate(groupId, govAgencyCode, month, year,
			domainCode);
	}

	/**
	* Creates a new opencps dossier statistic manual with the primary key. Does not add the opencps dossier statistic manual to the database.
	*
	* @param dossierStatisticId the primary key for the new opencps dossier statistic manual
	* @return the new opencps dossier statistic manual
	*/
	public static org.opencps.statistic.model.OpencpsDossierStatisticManual createOpencpsDossierStatisticManual(
		long dossierStatisticId) {
		return getService()
				   .createOpencpsDossierStatisticManual(dossierStatisticId);
	}

	/**
	* Deletes the opencps dossier statistic manual with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierStatisticId the primary key of the opencps dossier statistic manual
	* @return the opencps dossier statistic manual that was removed
	* @throws PortalException if a opencps dossier statistic manual with the primary key could not be found
	*/
	public static org.opencps.statistic.model.OpencpsDossierStatisticManual deleteOpencpsDossierStatisticManual(
		long dossierStatisticId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .deleteOpencpsDossierStatisticManual(dossierStatisticId);
	}

	/**
	* Deletes the opencps dossier statistic manual from the database. Also notifies the appropriate model listeners.
	*
	* @param opencpsDossierStatisticManual the opencps dossier statistic manual
	* @return the opencps dossier statistic manual that was removed
	*/
	public static org.opencps.statistic.model.OpencpsDossierStatisticManual deleteOpencpsDossierStatisticManual(
		org.opencps.statistic.model.OpencpsDossierStatisticManual opencpsDossierStatisticManual) {
		return getService()
				   .deleteOpencpsDossierStatisticManual(opencpsDossierStatisticManual);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.statistic.model.impl.OpencpsDossierStatisticManualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.statistic.model.impl.OpencpsDossierStatisticManualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.statistic.model.OpencpsDossierStatisticManual fetchByG_M_Y_G_D(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode) {
		return getService()
				   .fetchByG_M_Y_G_D(groupId, month, year, govAgencyCode,
			domainCode);
	}

	public static java.util.List<org.opencps.statistic.model.OpencpsDossierStatisticManual> fetchDossierStatistic(
		long groupId, int month, int year, String domain, String govAgencyCode,
		String groupAgenvyCode, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .fetchDossierStatistic(groupId, month, year, domain,
			govAgencyCode, groupAgenvyCode, start, end);
	}

	public static java.util.List<org.opencps.statistic.model.OpencpsDossierStatisticManual> fetchDossierStatistic(
		long groupId, int month, int year, String domain, String govAgencyCode,
		String system, String groupAgenvyCode, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .fetchDossierStatistic(groupId, month, year, domain,
			govAgencyCode, system, groupAgenvyCode, start, end);
	}

	public static org.opencps.statistic.model.OpencpsDossierStatisticManual fetchOpencpsDossierStatisticManual(
		long dossierStatisticId) {
		return getService()
				   .fetchOpencpsDossierStatisticManual(dossierStatisticId);
	}

	/**
	* Returns the opencps dossier statistic manual matching the UUID and group.
	*
	* @param uuid the opencps dossier statistic manual's UUID
	* @param groupId the primary key of the group
	* @return the matching opencps dossier statistic manual, or <code>null</code> if a matching opencps dossier statistic manual could not be found
	*/
	public static org.opencps.statistic.model.OpencpsDossierStatisticManual fetchOpencpsDossierStatisticManualByUuidAndGroupId(
		String uuid, long groupId) {
		return getService()
				   .fetchOpencpsDossierStatisticManualByUuidAndGroupId(uuid,
			groupId);
	}

	public static java.util.List<org.opencps.statistic.model.OpencpsDossierStatisticManual> findByG(
		long groupId) {
		return getService().findByG(groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static org.opencps.statistic.model.OpencpsDossierStatisticManual getByGovMonthYear(
		long groupId, String govAgencyCode, int month, int year)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getByGovMonthYear(groupId, govAgencyCode, month, year);
	}

	public static org.opencps.statistic.model.OpencpsDossierStatisticManual getByGovMonthYearDomain(
		long groupId, String govAgencyCode, int month, int year,
		String domainCode, boolean reporting) {
		return getService()
				   .getByGovMonthYearDomain(groupId, govAgencyCode, month,
			year, domainCode, reporting);
	}

	public static java.util.List<org.opencps.statistic.model.OpencpsDossierStatisticManual> getDossierStatisticByMonthsYearAndReport(
		long groupId, int[] month, int year, boolean reporting) {
		return getService()
				   .getDossierStatisticByMonthsYearAndReport(groupId, month,
			year, reporting);
	}

	public static java.util.List<org.opencps.statistic.model.OpencpsDossierStatisticManual> getDossierStatisticManualByMonthYear(
		long groupId, int month, int year) {
		return getService()
				   .getDossierStatisticManualByMonthYear(groupId, month, year);
	}

	public static java.util.List<org.opencps.statistic.model.OpencpsDossierStatisticManual> getDossierStatisticManualByMonthYearAndReport(
		long groupId, int month, int year, boolean reporting) {
		return getService()
				   .getDossierStatisticManualByMonthYearAndReport(groupId,
			month, year, reporting);
	}

	public static java.util.List<org.opencps.statistic.model.OpencpsDossierStatisticManual> getDossierStatisticManualByYear(
		long companyId, long groupId, int month, int year) {
		return getService()
				   .getDossierStatisticManualByYear(companyId, groupId, month,
			year);
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the opencps dossier statistic manual with the primary key.
	*
	* @param dossierStatisticId the primary key of the opencps dossier statistic manual
	* @return the opencps dossier statistic manual
	* @throws PortalException if a opencps dossier statistic manual with the primary key could not be found
	*/
	public static org.opencps.statistic.model.OpencpsDossierStatisticManual getOpencpsDossierStatisticManual(
		long dossierStatisticId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getOpencpsDossierStatisticManual(dossierStatisticId);
	}

	/**
	* Returns the opencps dossier statistic manual matching the UUID and group.
	*
	* @param uuid the opencps dossier statistic manual's UUID
	* @param groupId the primary key of the group
	* @return the matching opencps dossier statistic manual
	* @throws PortalException if a matching opencps dossier statistic manual could not be found
	*/
	public static org.opencps.statistic.model.OpencpsDossierStatisticManual getOpencpsDossierStatisticManualByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getOpencpsDossierStatisticManualByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the opencps dossier statistic manuals.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.statistic.model.impl.OpencpsDossierStatisticManualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of opencps dossier statistic manuals
	* @param end the upper bound of the range of opencps dossier statistic manuals (not inclusive)
	* @return the range of opencps dossier statistic manuals
	*/
	public static java.util.List<org.opencps.statistic.model.OpencpsDossierStatisticManual> getOpencpsDossierStatisticManuals(
		int start, int end) {
		return getService().getOpencpsDossierStatisticManuals(start, end);
	}

	/**
	* Returns all the opencps dossier statistic manuals matching the UUID and company.
	*
	* @param uuid the UUID of the opencps dossier statistic manuals
	* @param companyId the primary key of the company
	* @return the matching opencps dossier statistic manuals, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.statistic.model.OpencpsDossierStatisticManual> getOpencpsDossierStatisticManualsByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService()
				   .getOpencpsDossierStatisticManualsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of opencps dossier statistic manuals matching the UUID and company.
	*
	* @param uuid the UUID of the opencps dossier statistic manuals
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of opencps dossier statistic manuals
	* @param end the upper bound of the range of opencps dossier statistic manuals (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching opencps dossier statistic manuals, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.statistic.model.OpencpsDossierStatisticManual> getOpencpsDossierStatisticManualsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.statistic.model.OpencpsDossierStatisticManual> orderByComparator) {
		return getService()
				   .getOpencpsDossierStatisticManualsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of opencps dossier statistic manuals.
	*
	* @return the number of opencps dossier statistic manuals
	*/
	public static int getOpencpsDossierStatisticManualsCount() {
		return getService().getOpencpsDossierStatisticManualsCount();
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

	public static void removeAll() {
		getService().removeAll();
	}

	public static org.opencps.statistic.model.OpencpsDossierStatisticManual removeByG_M_Y_G_D(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticManualException {
		return getService()
				   .removeByG_M_Y_G_D(groupId, month, year, govAgencyCode,
			domainCode);
	}

	public static void removeDossierStatisticManualByD_M_Y(long groupId,
		String domainCode, int month, int year)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticManualException {
		getService()
			.removeDossierStatisticManualByD_M_Y(groupId, domainCode, month,
			year);
	}

	public static void removeDossierStatisticManualByMonthYear(long groupId,
		int month, int year)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticManualException {
		getService()
			.removeDossierStatisticManualByMonthYear(groupId, month, year);
	}

	public static void removeDossierStatisticManualByYear(long companyId,
		long groupId, int month, int year)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticManualException {
		getService()
			.removeDossierStatisticManualByYear(companyId, groupId, month, year);
	}

	public static java.util.List<org.opencps.statistic.model.OpencpsDossierStatisticManual> searchDossierStatisticSystem(
		long groupId, int month, int year, String domain, String govAgencyCode,
		String system, String groupAgenvyCode, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .searchDossierStatisticSystem(groupId, month, year, domain,
			govAgencyCode, system, groupAgenvyCode, start, end);
	}

	/**
	* Updates the opencps dossier statistic manual in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param opencpsDossierStatisticManual the opencps dossier statistic manual
	* @return the opencps dossier statistic manual that was updated
	*/
	public static org.opencps.statistic.model.OpencpsDossierStatisticManual updateOpencpsDossierStatisticManual(
		org.opencps.statistic.model.OpencpsDossierStatisticManual opencpsDossierStatisticManual) {
		return getService()
				   .updateOpencpsDossierStatisticManual(opencpsDossierStatisticManual);
	}

	public static org.opencps.statistic.model.OpencpsDossierStatisticManual updateStatisticManual(
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
		String domainName, boolean reporting, int onegateCount,
		int outsideCount, int insideCount, int fromViaPostalCount)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateStatisticManual(dossierStatisticId, companyId,
			groupId, userId, userName, month, year, totalCount, deniedCount,
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

	public static org.opencps.statistic.model.OpencpsDossierStatisticManual updateStatisticManual(
		long dossierStatisticId, long companyId, long groupId, long userId,
		String userName, int month, int year, int totalCount, int deniedCount,
		int cancelledCount, int processCount, int remainingCount,
		int receivedCount, int onlineCount, int releaseCount, int betimesCount,
		int ontimeCount, int overtimeCount, int doneCount, int releasingCount,
		int unresolvedCount, int processingCount, int undueCount,
		int overdueCount, int pausingCount, int ontimePercentage,
		int overtimeInside, int overtimeOutside, int interoperatingCount,
		int waitingCount, String govAgencyCode, String govAgencyName,
		String domainCode, String domainName, boolean reporting,
		int onegateCount, int outsideCount, int insideCount,
		int fromViaPostalCount)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateStatisticManual(dossierStatisticId, companyId,
			groupId, userId, userName, month, year, totalCount, deniedCount,
			cancelledCount, processCount, remainingCount, receivedCount,
			onlineCount, releaseCount, betimesCount, ontimeCount,
			overtimeCount, doneCount, releasingCount, unresolvedCount,
			processingCount, undueCount, overdueCount, pausingCount,
			ontimePercentage, overtimeInside, overtimeOutside,
			interoperatingCount, waitingCount, govAgencyCode, govAgencyName,
			domainCode, domainName, reporting, onegateCount, outsideCount,
			insideCount, fromViaPostalCount);
	}

	public static OpencpsDossierStatisticManualLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<OpencpsDossierStatisticManualLocalService, OpencpsDossierStatisticManualLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(OpencpsDossierStatisticManualLocalService.class);

		ServiceTracker<OpencpsDossierStatisticManualLocalService, OpencpsDossierStatisticManualLocalService> serviceTracker =
			new ServiceTracker<OpencpsDossierStatisticManualLocalService, OpencpsDossierStatisticManualLocalService>(bundle.getBundleContext(),
				OpencpsDossierStatisticManualLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}