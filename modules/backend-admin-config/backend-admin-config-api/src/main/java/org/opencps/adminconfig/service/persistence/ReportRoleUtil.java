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

import org.opencps.adminconfig.model.ReportRole;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the report role service. This utility wraps {@link org.opencps.adminconfig.service.persistence.impl.ReportRolePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author binhth
 * @see ReportRolePersistence
 * @see org.opencps.adminconfig.service.persistence.impl.ReportRolePersistenceImpl
 * @generated
 */
@ProviderType
public class ReportRoleUtil {
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
	public static void clearCache(ReportRole reportRole) {
		getPersistence().clearCache(reportRole);
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
	public static List<ReportRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ReportRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ReportRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ReportRole> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ReportRole update(ReportRole reportRole) {
		return getPersistence().update(reportRole);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ReportRole update(ReportRole reportRole,
		ServiceContext serviceContext) {
		return getPersistence().update(reportRole, serviceContext);
	}

	/**
	* Returns all the report roles where dynamicReportId = &#63;.
	*
	* @param dynamicReportId the dynamic report ID
	* @return the matching report roles
	*/
	public static List<ReportRole> findByF_DRID(long dynamicReportId) {
		return getPersistence().findByF_DRID(dynamicReportId);
	}

	/**
	* Returns a range of all the report roles where dynamicReportId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicReportId the dynamic report ID
	* @param start the lower bound of the range of report roles
	* @param end the upper bound of the range of report roles (not inclusive)
	* @return the range of matching report roles
	*/
	public static List<ReportRole> findByF_DRID(long dynamicReportId,
		int start, int end) {
		return getPersistence().findByF_DRID(dynamicReportId, start, end);
	}

	/**
	* Returns an ordered range of all the report roles where dynamicReportId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicReportId the dynamic report ID
	* @param start the lower bound of the range of report roles
	* @param end the upper bound of the range of report roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching report roles
	*/
	public static List<ReportRole> findByF_DRID(long dynamicReportId,
		int start, int end, OrderByComparator<ReportRole> orderByComparator) {
		return getPersistence()
				   .findByF_DRID(dynamicReportId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the report roles where dynamicReportId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicReportId the dynamic report ID
	* @param start the lower bound of the range of report roles
	* @param end the upper bound of the range of report roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching report roles
	*/
	public static List<ReportRole> findByF_DRID(long dynamicReportId,
		int start, int end, OrderByComparator<ReportRole> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_DRID(dynamicReportId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first report role in the ordered set where dynamicReportId = &#63;.
	*
	* @param dynamicReportId the dynamic report ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching report role
	* @throws NoSuchReportRoleException if a matching report role could not be found
	*/
	public static ReportRole findByF_DRID_First(long dynamicReportId,
		OrderByComparator<ReportRole> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchReportRoleException {
		return getPersistence()
				   .findByF_DRID_First(dynamicReportId, orderByComparator);
	}

	/**
	* Returns the first report role in the ordered set where dynamicReportId = &#63;.
	*
	* @param dynamicReportId the dynamic report ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching report role, or <code>null</code> if a matching report role could not be found
	*/
	public static ReportRole fetchByF_DRID_First(long dynamicReportId,
		OrderByComparator<ReportRole> orderByComparator) {
		return getPersistence()
				   .fetchByF_DRID_First(dynamicReportId, orderByComparator);
	}

	/**
	* Returns the last report role in the ordered set where dynamicReportId = &#63;.
	*
	* @param dynamicReportId the dynamic report ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching report role
	* @throws NoSuchReportRoleException if a matching report role could not be found
	*/
	public static ReportRole findByF_DRID_Last(long dynamicReportId,
		OrderByComparator<ReportRole> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchReportRoleException {
		return getPersistence()
				   .findByF_DRID_Last(dynamicReportId, orderByComparator);
	}

	/**
	* Returns the last report role in the ordered set where dynamicReportId = &#63;.
	*
	* @param dynamicReportId the dynamic report ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching report role, or <code>null</code> if a matching report role could not be found
	*/
	public static ReportRole fetchByF_DRID_Last(long dynamicReportId,
		OrderByComparator<ReportRole> orderByComparator) {
		return getPersistence()
				   .fetchByF_DRID_Last(dynamicReportId, orderByComparator);
	}

	/**
	* Returns the report roles before and after the current report role in the ordered set where dynamicReportId = &#63;.
	*
	* @param reportRoleId the primary key of the current report role
	* @param dynamicReportId the dynamic report ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next report role
	* @throws NoSuchReportRoleException if a report role with the primary key could not be found
	*/
	public static ReportRole[] findByF_DRID_PrevAndNext(long reportRoleId,
		long dynamicReportId, OrderByComparator<ReportRole> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchReportRoleException {
		return getPersistence()
				   .findByF_DRID_PrevAndNext(reportRoleId, dynamicReportId,
			orderByComparator);
	}

	/**
	* Removes all the report roles where dynamicReportId = &#63; from the database.
	*
	* @param dynamicReportId the dynamic report ID
	*/
	public static void removeByF_DRID(long dynamicReportId) {
		getPersistence().removeByF_DRID(dynamicReportId);
	}

	/**
	* Returns the number of report roles where dynamicReportId = &#63;.
	*
	* @param dynamicReportId the dynamic report ID
	* @return the number of matching report roles
	*/
	public static int countByF_DRID(long dynamicReportId) {
		return getPersistence().countByF_DRID(dynamicReportId);
	}

	/**
	* Returns all the report roles where roleId = &#63;.
	*
	* @param roleId the role ID
	* @return the matching report roles
	*/
	public static List<ReportRole> findByF_RIDS(long roleId) {
		return getPersistence().findByF_RIDS(roleId);
	}

	/**
	* Returns a range of all the report roles where roleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param roleId the role ID
	* @param start the lower bound of the range of report roles
	* @param end the upper bound of the range of report roles (not inclusive)
	* @return the range of matching report roles
	*/
	public static List<ReportRole> findByF_RIDS(long roleId, int start, int end) {
		return getPersistence().findByF_RIDS(roleId, start, end);
	}

	/**
	* Returns an ordered range of all the report roles where roleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param roleId the role ID
	* @param start the lower bound of the range of report roles
	* @param end the upper bound of the range of report roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching report roles
	*/
	public static List<ReportRole> findByF_RIDS(long roleId, int start,
		int end, OrderByComparator<ReportRole> orderByComparator) {
		return getPersistence()
				   .findByF_RIDS(roleId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the report roles where roleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param roleId the role ID
	* @param start the lower bound of the range of report roles
	* @param end the upper bound of the range of report roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching report roles
	*/
	public static List<ReportRole> findByF_RIDS(long roleId, int start,
		int end, OrderByComparator<ReportRole> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_RIDS(roleId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first report role in the ordered set where roleId = &#63;.
	*
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching report role
	* @throws NoSuchReportRoleException if a matching report role could not be found
	*/
	public static ReportRole findByF_RIDS_First(long roleId,
		OrderByComparator<ReportRole> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchReportRoleException {
		return getPersistence().findByF_RIDS_First(roleId, orderByComparator);
	}

	/**
	* Returns the first report role in the ordered set where roleId = &#63;.
	*
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching report role, or <code>null</code> if a matching report role could not be found
	*/
	public static ReportRole fetchByF_RIDS_First(long roleId,
		OrderByComparator<ReportRole> orderByComparator) {
		return getPersistence().fetchByF_RIDS_First(roleId, orderByComparator);
	}

	/**
	* Returns the last report role in the ordered set where roleId = &#63;.
	*
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching report role
	* @throws NoSuchReportRoleException if a matching report role could not be found
	*/
	public static ReportRole findByF_RIDS_Last(long roleId,
		OrderByComparator<ReportRole> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchReportRoleException {
		return getPersistence().findByF_RIDS_Last(roleId, orderByComparator);
	}

	/**
	* Returns the last report role in the ordered set where roleId = &#63;.
	*
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching report role, or <code>null</code> if a matching report role could not be found
	*/
	public static ReportRole fetchByF_RIDS_Last(long roleId,
		OrderByComparator<ReportRole> orderByComparator) {
		return getPersistence().fetchByF_RIDS_Last(roleId, orderByComparator);
	}

	/**
	* Returns the report roles before and after the current report role in the ordered set where roleId = &#63;.
	*
	* @param reportRoleId the primary key of the current report role
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next report role
	* @throws NoSuchReportRoleException if a report role with the primary key could not be found
	*/
	public static ReportRole[] findByF_RIDS_PrevAndNext(long reportRoleId,
		long roleId, OrderByComparator<ReportRole> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchReportRoleException {
		return getPersistence()
				   .findByF_RIDS_PrevAndNext(reportRoleId, roleId,
			orderByComparator);
	}

	/**
	* Returns all the report roles where roleId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param roleIds the role IDs
	* @return the matching report roles
	*/
	public static List<ReportRole> findByF_RIDS(long[] roleIds) {
		return getPersistence().findByF_RIDS(roleIds);
	}

	/**
	* Returns a range of all the report roles where roleId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param roleIds the role IDs
	* @param start the lower bound of the range of report roles
	* @param end the upper bound of the range of report roles (not inclusive)
	* @return the range of matching report roles
	*/
	public static List<ReportRole> findByF_RIDS(long[] roleIds, int start,
		int end) {
		return getPersistence().findByF_RIDS(roleIds, start, end);
	}

	/**
	* Returns an ordered range of all the report roles where roleId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param roleIds the role IDs
	* @param start the lower bound of the range of report roles
	* @param end the upper bound of the range of report roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching report roles
	*/
	public static List<ReportRole> findByF_RIDS(long[] roleIds, int start,
		int end, OrderByComparator<ReportRole> orderByComparator) {
		return getPersistence()
				   .findByF_RIDS(roleIds, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the report roles where roleId = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param roleId the role ID
	* @param start the lower bound of the range of report roles
	* @param end the upper bound of the range of report roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching report roles
	*/
	public static List<ReportRole> findByF_RIDS(long[] roleIds, int start,
		int end, OrderByComparator<ReportRole> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_RIDS(roleIds, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Removes all the report roles where roleId = &#63; from the database.
	*
	* @param roleId the role ID
	*/
	public static void removeByF_RIDS(long roleId) {
		getPersistence().removeByF_RIDS(roleId);
	}

	/**
	* Returns the number of report roles where roleId = &#63;.
	*
	* @param roleId the role ID
	* @return the number of matching report roles
	*/
	public static int countByF_RIDS(long roleId) {
		return getPersistence().countByF_RIDS(roleId);
	}

	/**
	* Returns the number of report roles where roleId = any &#63;.
	*
	* @param roleIds the role IDs
	* @return the number of matching report roles
	*/
	public static int countByF_RIDS(long[] roleIds) {
		return getPersistence().countByF_RIDS(roleIds);
	}

	/**
	* Returns the report role where dynamicReportId = &#63; and roleId = &#63; or throws a {@link NoSuchReportRoleException} if it could not be found.
	*
	* @param dynamicReportId the dynamic report ID
	* @param roleId the role ID
	* @return the matching report role
	* @throws NoSuchReportRoleException if a matching report role could not be found
	*/
	public static ReportRole findByF_DRID_RID(long dynamicReportId, long roleId)
		throws org.opencps.adminconfig.exception.NoSuchReportRoleException {
		return getPersistence().findByF_DRID_RID(dynamicReportId, roleId);
	}

	/**
	* Returns the report role where dynamicReportId = &#63; and roleId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param dynamicReportId the dynamic report ID
	* @param roleId the role ID
	* @return the matching report role, or <code>null</code> if a matching report role could not be found
	*/
	public static ReportRole fetchByF_DRID_RID(long dynamicReportId, long roleId) {
		return getPersistence().fetchByF_DRID_RID(dynamicReportId, roleId);
	}

	/**
	* Returns the report role where dynamicReportId = &#63; and roleId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param dynamicReportId the dynamic report ID
	* @param roleId the role ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching report role, or <code>null</code> if a matching report role could not be found
	*/
	public static ReportRole fetchByF_DRID_RID(long dynamicReportId,
		long roleId, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_DRID_RID(dynamicReportId, roleId, retrieveFromCache);
	}

	/**
	* Removes the report role where dynamicReportId = &#63; and roleId = &#63; from the database.
	*
	* @param dynamicReportId the dynamic report ID
	* @param roleId the role ID
	* @return the report role that was removed
	*/
	public static ReportRole removeByF_DRID_RID(long dynamicReportId,
		long roleId)
		throws org.opencps.adminconfig.exception.NoSuchReportRoleException {
		return getPersistence().removeByF_DRID_RID(dynamicReportId, roleId);
	}

	/**
	* Returns the number of report roles where dynamicReportId = &#63; and roleId = &#63;.
	*
	* @param dynamicReportId the dynamic report ID
	* @param roleId the role ID
	* @return the number of matching report roles
	*/
	public static int countByF_DRID_RID(long dynamicReportId, long roleId) {
		return getPersistence().countByF_DRID_RID(dynamicReportId, roleId);
	}

	/**
	* Caches the report role in the entity cache if it is enabled.
	*
	* @param reportRole the report role
	*/
	public static void cacheResult(ReportRole reportRole) {
		getPersistence().cacheResult(reportRole);
	}

	/**
	* Caches the report roles in the entity cache if it is enabled.
	*
	* @param reportRoles the report roles
	*/
	public static void cacheResult(List<ReportRole> reportRoles) {
		getPersistence().cacheResult(reportRoles);
	}

	/**
	* Creates a new report role with the primary key. Does not add the report role to the database.
	*
	* @param reportRoleId the primary key for the new report role
	* @return the new report role
	*/
	public static ReportRole create(long reportRoleId) {
		return getPersistence().create(reportRoleId);
	}

	/**
	* Removes the report role with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param reportRoleId the primary key of the report role
	* @return the report role that was removed
	* @throws NoSuchReportRoleException if a report role with the primary key could not be found
	*/
	public static ReportRole remove(long reportRoleId)
		throws org.opencps.adminconfig.exception.NoSuchReportRoleException {
		return getPersistence().remove(reportRoleId);
	}

	public static ReportRole updateImpl(ReportRole reportRole) {
		return getPersistence().updateImpl(reportRole);
	}

	/**
	* Returns the report role with the primary key or throws a {@link NoSuchReportRoleException} if it could not be found.
	*
	* @param reportRoleId the primary key of the report role
	* @return the report role
	* @throws NoSuchReportRoleException if a report role with the primary key could not be found
	*/
	public static ReportRole findByPrimaryKey(long reportRoleId)
		throws org.opencps.adminconfig.exception.NoSuchReportRoleException {
		return getPersistence().findByPrimaryKey(reportRoleId);
	}

	/**
	* Returns the report role with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param reportRoleId the primary key of the report role
	* @return the report role, or <code>null</code> if a report role with the primary key could not be found
	*/
	public static ReportRole fetchByPrimaryKey(long reportRoleId) {
		return getPersistence().fetchByPrimaryKey(reportRoleId);
	}

	public static java.util.Map<java.io.Serializable, ReportRole> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the report roles.
	*
	* @return the report roles
	*/
	public static List<ReportRole> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the report roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of report roles
	* @param end the upper bound of the range of report roles (not inclusive)
	* @return the range of report roles
	*/
	public static List<ReportRole> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the report roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of report roles
	* @param end the upper bound of the range of report roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of report roles
	*/
	public static List<ReportRole> findAll(int start, int end,
		OrderByComparator<ReportRole> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the report roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of report roles
	* @param end the upper bound of the range of report roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of report roles
	*/
	public static List<ReportRole> findAll(int start, int end,
		OrderByComparator<ReportRole> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the report roles from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of report roles.
	*
	* @return the number of report roles
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ReportRolePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ReportRolePersistence, ReportRolePersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ReportRolePersistence.class);

		ServiceTracker<ReportRolePersistence, ReportRolePersistence> serviceTracker =
			new ServiceTracker<ReportRolePersistence, ReportRolePersistence>(bundle.getBundleContext(),
				ReportRolePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}