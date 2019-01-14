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

package org.opencps.adminconfig.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.adminconfig.model.DynamicReport;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the dynamic report service. This utility wraps {@link org.opencps.adminconfig.service.persistence.impl.DynamicReportPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author binhth
 * @see DynamicReportPersistence
 * @see org.opencps.adminconfig.service.persistence.impl.DynamicReportPersistenceImpl
 * @generated
 */
@ProviderType
public class DynamicReportUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(DynamicReport dynamicReport) {
		getPersistence().clearCache(dynamicReport);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<DynamicReport> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<DynamicReport> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<DynamicReport> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<DynamicReport> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static DynamicReport update(DynamicReport dynamicReport) {
		return getPersistence().update(dynamicReport);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static DynamicReport update(DynamicReport dynamicReport,
		ServiceContext serviceContext) {
		return getPersistence().update(dynamicReport, serviceContext);
	}

	/**
	* Returns all the dynamic reports where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching dynamic reports
	*/
	public static List<DynamicReport> findByF_GroupId(long groupId) {
		return getPersistence().findByF_GroupId(groupId);
	}

	/**
	* Returns a range of all the dynamic reports where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DynamicReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of dynamic reports
	* @param end the upper bound of the range of dynamic reports (not inclusive)
	* @return the range of matching dynamic reports
	*/
	public static List<DynamicReport> findByF_GroupId(long groupId, int start,
		int end) {
		return getPersistence().findByF_GroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the dynamic reports where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DynamicReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of dynamic reports
	* @param end the upper bound of the range of dynamic reports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dynamic reports
	*/
	public static List<DynamicReport> findByF_GroupId(long groupId, int start,
		int end, OrderByComparator<DynamicReport> orderByComparator) {
		return getPersistence()
				   .findByF_GroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dynamic reports where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DynamicReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of dynamic reports
	* @param end the upper bound of the range of dynamic reports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dynamic reports
	*/
	public static List<DynamicReport> findByF_GroupId(long groupId, int start,
		int end, OrderByComparator<DynamicReport> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_GroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first dynamic report in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dynamic report
	* @throws NoSuchDynamicReportException if a matching dynamic report could not be found
	*/
	public static DynamicReport findByF_GroupId_First(long groupId,
		OrderByComparator<DynamicReport> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchDynamicReportException {
		return getPersistence().findByF_GroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first dynamic report in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dynamic report, or <code>null</code> if a matching dynamic report could not be found
	*/
	public static DynamicReport fetchByF_GroupId_First(long groupId,
		OrderByComparator<DynamicReport> orderByComparator) {
		return getPersistence()
				   .fetchByF_GroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last dynamic report in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dynamic report
	* @throws NoSuchDynamicReportException if a matching dynamic report could not be found
	*/
	public static DynamicReport findByF_GroupId_Last(long groupId,
		OrderByComparator<DynamicReport> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchDynamicReportException {
		return getPersistence().findByF_GroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last dynamic report in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dynamic report, or <code>null</code> if a matching dynamic report could not be found
	*/
	public static DynamicReport fetchByF_GroupId_Last(long groupId,
		OrderByComparator<DynamicReport> orderByComparator) {
		return getPersistence().fetchByF_GroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the dynamic reports before and after the current dynamic report in the ordered set where groupId = &#63;.
	*
	* @param dynamicReportId the primary key of the current dynamic report
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dynamic report
	* @throws NoSuchDynamicReportException if a dynamic report with the primary key could not be found
	*/
	public static DynamicReport[] findByF_GroupId_PrevAndNext(
		long dynamicReportId, long groupId,
		OrderByComparator<DynamicReport> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchDynamicReportException {
		return getPersistence()
				   .findByF_GroupId_PrevAndNext(dynamicReportId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the dynamic reports where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByF_GroupId(long groupId) {
		getPersistence().removeByF_GroupId(groupId);
	}

	/**
	* Returns the number of dynamic reports where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching dynamic reports
	*/
	public static int countByF_GroupId(long groupId) {
		return getPersistence().countByF_GroupId(groupId);
	}

	/**
	* Returns the dynamic report where groupId = &#63; and reportCode = &#63; or throws a {@link NoSuchDynamicReportException} if it could not be found.
	*
	* @param groupId the group ID
	* @param reportCode the report code
	* @return the matching dynamic report
	* @throws NoSuchDynamicReportException if a matching dynamic report could not be found
	*/
	public static DynamicReport findByF_reportCode(long groupId,
		String reportCode)
		throws org.opencps.adminconfig.exception.NoSuchDynamicReportException {
		return getPersistence().findByF_reportCode(groupId, reportCode);
	}

	/**
	* Returns the dynamic report where groupId = &#63; and reportCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param reportCode the report code
	* @return the matching dynamic report, or <code>null</code> if a matching dynamic report could not be found
	*/
	public static DynamicReport fetchByF_reportCode(long groupId,
		String reportCode) {
		return getPersistence().fetchByF_reportCode(groupId, reportCode);
	}

	/**
	* Returns the dynamic report where groupId = &#63; and reportCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param reportCode the report code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dynamic report, or <code>null</code> if a matching dynamic report could not be found
	*/
	public static DynamicReport fetchByF_reportCode(long groupId,
		String reportCode, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_reportCode(groupId, reportCode, retrieveFromCache);
	}

	/**
	* Removes the dynamic report where groupId = &#63; and reportCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param reportCode the report code
	* @return the dynamic report that was removed
	*/
	public static DynamicReport removeByF_reportCode(long groupId,
		String reportCode)
		throws org.opencps.adminconfig.exception.NoSuchDynamicReportException {
		return getPersistence().removeByF_reportCode(groupId, reportCode);
	}

	/**
	* Returns the number of dynamic reports where groupId = &#63; and reportCode = &#63;.
	*
	* @param groupId the group ID
	* @param reportCode the report code
	* @return the number of matching dynamic reports
	*/
	public static int countByF_reportCode(long groupId, String reportCode) {
		return getPersistence().countByF_reportCode(groupId, reportCode);
	}

	/**
	* Returns all the dynamic reports where groupId = &#63; and reportType = &#63;.
	*
	* @param groupId the group ID
	* @param reportType the report type
	* @return the matching dynamic reports
	*/
	public static List<DynamicReport> findByF_reportType(long groupId,
		String reportType) {
		return getPersistence().findByF_reportType(groupId, reportType);
	}

	/**
	* Returns a range of all the dynamic reports where groupId = &#63; and reportType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DynamicReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param reportType the report type
	* @param start the lower bound of the range of dynamic reports
	* @param end the upper bound of the range of dynamic reports (not inclusive)
	* @return the range of matching dynamic reports
	*/
	public static List<DynamicReport> findByF_reportType(long groupId,
		String reportType, int start, int end) {
		return getPersistence()
				   .findByF_reportType(groupId, reportType, start, end);
	}

	/**
	* Returns an ordered range of all the dynamic reports where groupId = &#63; and reportType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DynamicReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param reportType the report type
	* @param start the lower bound of the range of dynamic reports
	* @param end the upper bound of the range of dynamic reports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dynamic reports
	*/
	public static List<DynamicReport> findByF_reportType(long groupId,
		String reportType, int start, int end,
		OrderByComparator<DynamicReport> orderByComparator) {
		return getPersistence()
				   .findByF_reportType(groupId, reportType, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the dynamic reports where groupId = &#63; and reportType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DynamicReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param reportType the report type
	* @param start the lower bound of the range of dynamic reports
	* @param end the upper bound of the range of dynamic reports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dynamic reports
	*/
	public static List<DynamicReport> findByF_reportType(long groupId,
		String reportType, int start, int end,
		OrderByComparator<DynamicReport> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_reportType(groupId, reportType, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dynamic report in the ordered set where groupId = &#63; and reportType = &#63;.
	*
	* @param groupId the group ID
	* @param reportType the report type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dynamic report
	* @throws NoSuchDynamicReportException if a matching dynamic report could not be found
	*/
	public static DynamicReport findByF_reportType_First(long groupId,
		String reportType, OrderByComparator<DynamicReport> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchDynamicReportException {
		return getPersistence()
				   .findByF_reportType_First(groupId, reportType,
			orderByComparator);
	}

	/**
	* Returns the first dynamic report in the ordered set where groupId = &#63; and reportType = &#63;.
	*
	* @param groupId the group ID
	* @param reportType the report type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dynamic report, or <code>null</code> if a matching dynamic report could not be found
	*/
	public static DynamicReport fetchByF_reportType_First(long groupId,
		String reportType, OrderByComparator<DynamicReport> orderByComparator) {
		return getPersistence()
				   .fetchByF_reportType_First(groupId, reportType,
			orderByComparator);
	}

	/**
	* Returns the last dynamic report in the ordered set where groupId = &#63; and reportType = &#63;.
	*
	* @param groupId the group ID
	* @param reportType the report type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dynamic report
	* @throws NoSuchDynamicReportException if a matching dynamic report could not be found
	*/
	public static DynamicReport findByF_reportType_Last(long groupId,
		String reportType, OrderByComparator<DynamicReport> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchDynamicReportException {
		return getPersistence()
				   .findByF_reportType_Last(groupId, reportType,
			orderByComparator);
	}

	/**
	* Returns the last dynamic report in the ordered set where groupId = &#63; and reportType = &#63;.
	*
	* @param groupId the group ID
	* @param reportType the report type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dynamic report, or <code>null</code> if a matching dynamic report could not be found
	*/
	public static DynamicReport fetchByF_reportType_Last(long groupId,
		String reportType, OrderByComparator<DynamicReport> orderByComparator) {
		return getPersistence()
				   .fetchByF_reportType_Last(groupId, reportType,
			orderByComparator);
	}

	/**
	* Returns the dynamic reports before and after the current dynamic report in the ordered set where groupId = &#63; and reportType = &#63;.
	*
	* @param dynamicReportId the primary key of the current dynamic report
	* @param groupId the group ID
	* @param reportType the report type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dynamic report
	* @throws NoSuchDynamicReportException if a dynamic report with the primary key could not be found
	*/
	public static DynamicReport[] findByF_reportType_PrevAndNext(
		long dynamicReportId, long groupId, String reportType,
		OrderByComparator<DynamicReport> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchDynamicReportException {
		return getPersistence()
				   .findByF_reportType_PrevAndNext(dynamicReportId, groupId,
			reportType, orderByComparator);
	}

	/**
	* Removes all the dynamic reports where groupId = &#63; and reportType = &#63; from the database.
	*
	* @param groupId the group ID
	* @param reportType the report type
	*/
	public static void removeByF_reportType(long groupId, String reportType) {
		getPersistence().removeByF_reportType(groupId, reportType);
	}

	/**
	* Returns the number of dynamic reports where groupId = &#63; and reportType = &#63;.
	*
	* @param groupId the group ID
	* @param reportType the report type
	* @return the number of matching dynamic reports
	*/
	public static int countByF_reportType(long groupId, String reportType) {
		return getPersistence().countByF_reportType(groupId, reportType);
	}

	/**
	* Caches the dynamic report in the entity cache if it is enabled.
	*
	* @param dynamicReport the dynamic report
	*/
	public static void cacheResult(DynamicReport dynamicReport) {
		getPersistence().cacheResult(dynamicReport);
	}

	/**
	* Caches the dynamic reports in the entity cache if it is enabled.
	*
	* @param dynamicReports the dynamic reports
	*/
	public static void cacheResult(List<DynamicReport> dynamicReports) {
		getPersistence().cacheResult(dynamicReports);
	}

	/**
	* Creates a new dynamic report with the primary key. Does not add the dynamic report to the database.
	*
	* @param dynamicReportId the primary key for the new dynamic report
	* @return the new dynamic report
	*/
	public static DynamicReport create(long dynamicReportId) {
		return getPersistence().create(dynamicReportId);
	}

	/**
	* Removes the dynamic report with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dynamicReportId the primary key of the dynamic report
	* @return the dynamic report that was removed
	* @throws NoSuchDynamicReportException if a dynamic report with the primary key could not be found
	*/
	public static DynamicReport remove(long dynamicReportId)
		throws org.opencps.adminconfig.exception.NoSuchDynamicReportException {
		return getPersistence().remove(dynamicReportId);
	}

	public static DynamicReport updateImpl(DynamicReport dynamicReport) {
		return getPersistence().updateImpl(dynamicReport);
	}

	/**
	* Returns the dynamic report with the primary key or throws a {@link NoSuchDynamicReportException} if it could not be found.
	*
	* @param dynamicReportId the primary key of the dynamic report
	* @return the dynamic report
	* @throws NoSuchDynamicReportException if a dynamic report with the primary key could not be found
	*/
	public static DynamicReport findByPrimaryKey(long dynamicReportId)
		throws org.opencps.adminconfig.exception.NoSuchDynamicReportException {
		return getPersistence().findByPrimaryKey(dynamicReportId);
	}

	/**
	* Returns the dynamic report with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param dynamicReportId the primary key of the dynamic report
	* @return the dynamic report, or <code>null</code> if a dynamic report with the primary key could not be found
	*/
	public static DynamicReport fetchByPrimaryKey(long dynamicReportId) {
		return getPersistence().fetchByPrimaryKey(dynamicReportId);
	}

	public static java.util.Map<java.io.Serializable, DynamicReport> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the dynamic reports.
	*
	* @return the dynamic reports
	*/
	public static List<DynamicReport> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the dynamic reports.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DynamicReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dynamic reports
	* @param end the upper bound of the range of dynamic reports (not inclusive)
	* @return the range of dynamic reports
	*/
	public static List<DynamicReport> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the dynamic reports.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DynamicReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dynamic reports
	* @param end the upper bound of the range of dynamic reports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of dynamic reports
	*/
	public static List<DynamicReport> findAll(int start, int end,
		OrderByComparator<DynamicReport> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dynamic reports.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DynamicReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dynamic reports
	* @param end the upper bound of the range of dynamic reports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of dynamic reports
	*/
	public static List<DynamicReport> findAll(int start, int end,
		OrderByComparator<DynamicReport> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the dynamic reports from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of dynamic reports.
	*
	* @return the number of dynamic reports
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static DynamicReportPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DynamicReportPersistence, DynamicReportPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(DynamicReportPersistence.class);

		ServiceTracker<DynamicReportPersistence, DynamicReportPersistence> serviceTracker =
			new ServiceTracker<DynamicReportPersistence, DynamicReportPersistence>(bundle.getBundleContext(),
				DynamicReportPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}