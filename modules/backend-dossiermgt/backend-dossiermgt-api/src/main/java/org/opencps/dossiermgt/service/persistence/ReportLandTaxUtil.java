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

package org.opencps.dossiermgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.dossiermgt.model.ReportLandTax;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the report land tax service. This utility wraps {@link org.opencps.dossiermgt.service.persistence.impl.ReportLandTaxPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see ReportLandTaxPersistence
 * @see org.opencps.dossiermgt.service.persistence.impl.ReportLandTaxPersistenceImpl
 * @generated
 */
@ProviderType
public class ReportLandTaxUtil {
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
	public static void clearCache(ReportLandTax reportLandTax) {
		getPersistence().clearCache(reportLandTax);
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
	public static List<ReportLandTax> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ReportLandTax> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ReportLandTax> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ReportLandTax> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ReportLandTax update(ReportLandTax reportLandTax) {
		return getPersistence().update(reportLandTax);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ReportLandTax update(ReportLandTax reportLandTax,
		ServiceContext serviceContext) {
		return getPersistence().update(reportLandTax, serviceContext);
	}

	/**
	* Returns all the report land taxs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching report land taxs
	*/
	public static List<ReportLandTax> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the report land taxs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportLandTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of report land taxs
	* @param end the upper bound of the range of report land taxs (not inclusive)
	* @return the range of matching report land taxs
	*/
	public static List<ReportLandTax> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the report land taxs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportLandTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of report land taxs
	* @param end the upper bound of the range of report land taxs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching report land taxs
	*/
	public static List<ReportLandTax> findByUuid(String uuid, int start,
		int end, OrderByComparator<ReportLandTax> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the report land taxs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportLandTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of report land taxs
	* @param end the upper bound of the range of report land taxs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching report land taxs
	*/
	public static List<ReportLandTax> findByUuid(String uuid, int start,
		int end, OrderByComparator<ReportLandTax> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first report land tax in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching report land tax
	* @throws NoSuchReportLandTaxException if a matching report land tax could not be found
	*/
	public static ReportLandTax findByUuid_First(String uuid,
		OrderByComparator<ReportLandTax> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchReportLandTaxException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first report land tax in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching report land tax, or <code>null</code> if a matching report land tax could not be found
	*/
	public static ReportLandTax fetchByUuid_First(String uuid,
		OrderByComparator<ReportLandTax> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last report land tax in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching report land tax
	* @throws NoSuchReportLandTaxException if a matching report land tax could not be found
	*/
	public static ReportLandTax findByUuid_Last(String uuid,
		OrderByComparator<ReportLandTax> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchReportLandTaxException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last report land tax in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching report land tax, or <code>null</code> if a matching report land tax could not be found
	*/
	public static ReportLandTax fetchByUuid_Last(String uuid,
		OrderByComparator<ReportLandTax> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the report land taxs before and after the current report land tax in the ordered set where uuid = &#63;.
	*
	* @param reportId the primary key of the current report land tax
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next report land tax
	* @throws NoSuchReportLandTaxException if a report land tax with the primary key could not be found
	*/
	public static ReportLandTax[] findByUuid_PrevAndNext(long reportId,
		String uuid, OrderByComparator<ReportLandTax> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchReportLandTaxException {
		return getPersistence()
				   .findByUuid_PrevAndNext(reportId, uuid, orderByComparator);
	}

	/**
	* Removes all the report land taxs where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of report land taxs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching report land taxs
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the report land tax where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchReportLandTaxException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching report land tax
	* @throws NoSuchReportLandTaxException if a matching report land tax could not be found
	*/
	public static ReportLandTax findByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchReportLandTaxException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the report land tax where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching report land tax, or <code>null</code> if a matching report land tax could not be found
	*/
	public static ReportLandTax fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the report land tax where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching report land tax, or <code>null</code> if a matching report land tax could not be found
	*/
	public static ReportLandTax fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the report land tax where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the report land tax that was removed
	*/
	public static ReportLandTax removeByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchReportLandTaxException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of report land taxs where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching report land taxs
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the report land taxs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching report land taxs
	*/
	public static List<ReportLandTax> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the report land taxs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportLandTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of report land taxs
	* @param end the upper bound of the range of report land taxs (not inclusive)
	* @return the range of matching report land taxs
	*/
	public static List<ReportLandTax> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the report land taxs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportLandTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of report land taxs
	* @param end the upper bound of the range of report land taxs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching report land taxs
	*/
	public static List<ReportLandTax> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ReportLandTax> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the report land taxs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportLandTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of report land taxs
	* @param end the upper bound of the range of report land taxs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching report land taxs
	*/
	public static List<ReportLandTax> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ReportLandTax> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first report land tax in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching report land tax
	* @throws NoSuchReportLandTaxException if a matching report land tax could not be found
	*/
	public static ReportLandTax findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ReportLandTax> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchReportLandTaxException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first report land tax in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching report land tax, or <code>null</code> if a matching report land tax could not be found
	*/
	public static ReportLandTax fetchByUuid_C_First(String uuid,
		long companyId, OrderByComparator<ReportLandTax> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last report land tax in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching report land tax
	* @throws NoSuchReportLandTaxException if a matching report land tax could not be found
	*/
	public static ReportLandTax findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ReportLandTax> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchReportLandTaxException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last report land tax in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching report land tax, or <code>null</code> if a matching report land tax could not be found
	*/
	public static ReportLandTax fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ReportLandTax> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the report land taxs before and after the current report land tax in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param reportId the primary key of the current report land tax
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next report land tax
	* @throws NoSuchReportLandTaxException if a report land tax with the primary key could not be found
	*/
	public static ReportLandTax[] findByUuid_C_PrevAndNext(long reportId,
		String uuid, long companyId,
		OrderByComparator<ReportLandTax> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchReportLandTaxException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(reportId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the report land taxs where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of report land taxs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching report land taxs
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Caches the report land tax in the entity cache if it is enabled.
	*
	* @param reportLandTax the report land tax
	*/
	public static void cacheResult(ReportLandTax reportLandTax) {
		getPersistence().cacheResult(reportLandTax);
	}

	/**
	* Caches the report land taxs in the entity cache if it is enabled.
	*
	* @param reportLandTaxs the report land taxs
	*/
	public static void cacheResult(List<ReportLandTax> reportLandTaxs) {
		getPersistence().cacheResult(reportLandTaxs);
	}

	/**
	* Creates a new report land tax with the primary key. Does not add the report land tax to the database.
	*
	* @param reportId the primary key for the new report land tax
	* @return the new report land tax
	*/
	public static ReportLandTax create(long reportId) {
		return getPersistence().create(reportId);
	}

	/**
	* Removes the report land tax with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param reportId the primary key of the report land tax
	* @return the report land tax that was removed
	* @throws NoSuchReportLandTaxException if a report land tax with the primary key could not be found
	*/
	public static ReportLandTax remove(long reportId)
		throws org.opencps.dossiermgt.exception.NoSuchReportLandTaxException {
		return getPersistence().remove(reportId);
	}

	public static ReportLandTax updateImpl(ReportLandTax reportLandTax) {
		return getPersistence().updateImpl(reportLandTax);
	}

	/**
	* Returns the report land tax with the primary key or throws a {@link NoSuchReportLandTaxException} if it could not be found.
	*
	* @param reportId the primary key of the report land tax
	* @return the report land tax
	* @throws NoSuchReportLandTaxException if a report land tax with the primary key could not be found
	*/
	public static ReportLandTax findByPrimaryKey(long reportId)
		throws org.opencps.dossiermgt.exception.NoSuchReportLandTaxException {
		return getPersistence().findByPrimaryKey(reportId);
	}

	/**
	* Returns the report land tax with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param reportId the primary key of the report land tax
	* @return the report land tax, or <code>null</code> if a report land tax with the primary key could not be found
	*/
	public static ReportLandTax fetchByPrimaryKey(long reportId) {
		return getPersistence().fetchByPrimaryKey(reportId);
	}

	public static java.util.Map<java.io.Serializable, ReportLandTax> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the report land taxs.
	*
	* @return the report land taxs
	*/
	public static List<ReportLandTax> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the report land taxs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportLandTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of report land taxs
	* @param end the upper bound of the range of report land taxs (not inclusive)
	* @return the range of report land taxs
	*/
	public static List<ReportLandTax> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the report land taxs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportLandTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of report land taxs
	* @param end the upper bound of the range of report land taxs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of report land taxs
	*/
	public static List<ReportLandTax> findAll(int start, int end,
		OrderByComparator<ReportLandTax> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the report land taxs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportLandTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of report land taxs
	* @param end the upper bound of the range of report land taxs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of report land taxs
	*/
	public static List<ReportLandTax> findAll(int start, int end,
		OrderByComparator<ReportLandTax> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the report land taxs from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of report land taxs.
	*
	* @return the number of report land taxs
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ReportLandTaxPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ReportLandTaxPersistence, ReportLandTaxPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ReportLandTaxPersistence.class);

		ServiceTracker<ReportLandTaxPersistence, ReportLandTaxPersistence> serviceTracker =
			new ServiceTracker<ReportLandTaxPersistence, ReportLandTaxPersistence>(bundle.getBundleContext(),
				ReportLandTaxPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}