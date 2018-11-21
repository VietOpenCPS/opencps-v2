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

package org.opencps.deliverable.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.deliverable.model.OpenCPSDeliverableTypeRole;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the open cps deliverable type role service. This utility wraps {@link org.opencps.deliverable.service.persistence.impl.OpenCPSDeliverableTypeRolePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author binhth
 * @see OpenCPSDeliverableTypeRolePersistence
 * @see org.opencps.deliverable.service.persistence.impl.OpenCPSDeliverableTypeRolePersistenceImpl
 * @generated
 */
@ProviderType
public class OpenCPSDeliverableTypeRoleUtil {
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
	public static void clearCache(
		OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole) {
		getPersistence().clearCache(openCPSDeliverableTypeRole);
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
	public static List<OpenCPSDeliverableTypeRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<OpenCPSDeliverableTypeRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<OpenCPSDeliverableTypeRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static OpenCPSDeliverableTypeRole update(
		OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole) {
		return getPersistence().update(openCPSDeliverableTypeRole);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static OpenCPSDeliverableTypeRole update(
		OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole,
		ServiceContext serviceContext) {
		return getPersistence()
				   .update(openCPSDeliverableTypeRole, serviceContext);
	}

	/**
	* Returns all the open cps deliverable type roles where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching open cps deliverable type roles
	*/
	public static List<OpenCPSDeliverableTypeRole> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the open cps deliverable type roles where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of open cps deliverable type roles
	* @param end the upper bound of the range of open cps deliverable type roles (not inclusive)
	* @return the range of matching open cps deliverable type roles
	*/
	public static List<OpenCPSDeliverableTypeRole> findByUuid(String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the open cps deliverable type roles where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of open cps deliverable type roles
	* @param end the upper bound of the range of open cps deliverable type roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching open cps deliverable type roles
	*/
	public static List<OpenCPSDeliverableTypeRole> findByUuid(String uuid,
		int start, int end,
		OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the open cps deliverable type roles where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of open cps deliverable type roles
	* @param end the upper bound of the range of open cps deliverable type roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching open cps deliverable type roles
	*/
	public static List<OpenCPSDeliverableTypeRole> findByUuid(String uuid,
		int start, int end,
		OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first open cps deliverable type role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching open cps deliverable type role
	* @throws NoSuchOpenCPSDeliverableTypeRoleException if a matching open cps deliverable type role could not be found
	*/
	public static OpenCPSDeliverableTypeRole findByUuid_First(String uuid,
		OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator)
		throws org.opencps.deliverable.exception.NoSuchOpenCPSDeliverableTypeRoleException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first open cps deliverable type role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching open cps deliverable type role, or <code>null</code> if a matching open cps deliverable type role could not be found
	*/
	public static OpenCPSDeliverableTypeRole fetchByUuid_First(String uuid,
		OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last open cps deliverable type role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching open cps deliverable type role
	* @throws NoSuchOpenCPSDeliverableTypeRoleException if a matching open cps deliverable type role could not be found
	*/
	public static OpenCPSDeliverableTypeRole findByUuid_Last(String uuid,
		OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator)
		throws org.opencps.deliverable.exception.NoSuchOpenCPSDeliverableTypeRoleException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last open cps deliverable type role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching open cps deliverable type role, or <code>null</code> if a matching open cps deliverable type role could not be found
	*/
	public static OpenCPSDeliverableTypeRole fetchByUuid_Last(String uuid,
		OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the open cps deliverable type roles before and after the current open cps deliverable type role in the ordered set where uuid = &#63;.
	*
	* @param deliverableTypeRoleId the primary key of the current open cps deliverable type role
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next open cps deliverable type role
	* @throws NoSuchOpenCPSDeliverableTypeRoleException if a open cps deliverable type role with the primary key could not be found
	*/
	public static OpenCPSDeliverableTypeRole[] findByUuid_PrevAndNext(
		long deliverableTypeRoleId, String uuid,
		OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator)
		throws org.opencps.deliverable.exception.NoSuchOpenCPSDeliverableTypeRoleException {
		return getPersistence()
				   .findByUuid_PrevAndNext(deliverableTypeRoleId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the open cps deliverable type roles where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of open cps deliverable type roles where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching open cps deliverable type roles
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the open cps deliverable type role where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchOpenCPSDeliverableTypeRoleException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching open cps deliverable type role
	* @throws NoSuchOpenCPSDeliverableTypeRoleException if a matching open cps deliverable type role could not be found
	*/
	public static OpenCPSDeliverableTypeRole findByUUID_G(String uuid,
		long groupId)
		throws org.opencps.deliverable.exception.NoSuchOpenCPSDeliverableTypeRoleException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the open cps deliverable type role where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching open cps deliverable type role, or <code>null</code> if a matching open cps deliverable type role could not be found
	*/
	public static OpenCPSDeliverableTypeRole fetchByUUID_G(String uuid,
		long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the open cps deliverable type role where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching open cps deliverable type role, or <code>null</code> if a matching open cps deliverable type role could not be found
	*/
	public static OpenCPSDeliverableTypeRole fetchByUUID_G(String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the open cps deliverable type role where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the open cps deliverable type role that was removed
	*/
	public static OpenCPSDeliverableTypeRole removeByUUID_G(String uuid,
		long groupId)
		throws org.opencps.deliverable.exception.NoSuchOpenCPSDeliverableTypeRoleException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of open cps deliverable type roles where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching open cps deliverable type roles
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the open cps deliverable type roles where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching open cps deliverable type roles
	*/
	public static List<OpenCPSDeliverableTypeRole> findByUuid_C(String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the open cps deliverable type roles where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of open cps deliverable type roles
	* @param end the upper bound of the range of open cps deliverable type roles (not inclusive)
	* @return the range of matching open cps deliverable type roles
	*/
	public static List<OpenCPSDeliverableTypeRole> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the open cps deliverable type roles where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of open cps deliverable type roles
	* @param end the upper bound of the range of open cps deliverable type roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching open cps deliverable type roles
	*/
	public static List<OpenCPSDeliverableTypeRole> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the open cps deliverable type roles where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of open cps deliverable type roles
	* @param end the upper bound of the range of open cps deliverable type roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching open cps deliverable type roles
	*/
	public static List<OpenCPSDeliverableTypeRole> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first open cps deliverable type role in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching open cps deliverable type role
	* @throws NoSuchOpenCPSDeliverableTypeRoleException if a matching open cps deliverable type role could not be found
	*/
	public static OpenCPSDeliverableTypeRole findByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator)
		throws org.opencps.deliverable.exception.NoSuchOpenCPSDeliverableTypeRoleException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first open cps deliverable type role in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching open cps deliverable type role, or <code>null</code> if a matching open cps deliverable type role could not be found
	*/
	public static OpenCPSDeliverableTypeRole fetchByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last open cps deliverable type role in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching open cps deliverable type role
	* @throws NoSuchOpenCPSDeliverableTypeRoleException if a matching open cps deliverable type role could not be found
	*/
	public static OpenCPSDeliverableTypeRole findByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator)
		throws org.opencps.deliverable.exception.NoSuchOpenCPSDeliverableTypeRoleException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last open cps deliverable type role in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching open cps deliverable type role, or <code>null</code> if a matching open cps deliverable type role could not be found
	*/
	public static OpenCPSDeliverableTypeRole fetchByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the open cps deliverable type roles before and after the current open cps deliverable type role in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param deliverableTypeRoleId the primary key of the current open cps deliverable type role
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next open cps deliverable type role
	* @throws NoSuchOpenCPSDeliverableTypeRoleException if a open cps deliverable type role with the primary key could not be found
	*/
	public static OpenCPSDeliverableTypeRole[] findByUuid_C_PrevAndNext(
		long deliverableTypeRoleId, String uuid, long companyId,
		OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator)
		throws org.opencps.deliverable.exception.NoSuchOpenCPSDeliverableTypeRoleException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(deliverableTypeRoleId, uuid,
			companyId, orderByComparator);
	}

	/**
	* Removes all the open cps deliverable type roles where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of open cps deliverable type roles where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching open cps deliverable type roles
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Caches the open cps deliverable type role in the entity cache if it is enabled.
	*
	* @param openCPSDeliverableTypeRole the open cps deliverable type role
	*/
	public static void cacheResult(
		OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole) {
		getPersistence().cacheResult(openCPSDeliverableTypeRole);
	}

	/**
	* Caches the open cps deliverable type roles in the entity cache if it is enabled.
	*
	* @param openCPSDeliverableTypeRoles the open cps deliverable type roles
	*/
	public static void cacheResult(
		List<OpenCPSDeliverableTypeRole> openCPSDeliverableTypeRoles) {
		getPersistence().cacheResult(openCPSDeliverableTypeRoles);
	}

	/**
	* Creates a new open cps deliverable type role with the primary key. Does not add the open cps deliverable type role to the database.
	*
	* @param deliverableTypeRoleId the primary key for the new open cps deliverable type role
	* @return the new open cps deliverable type role
	*/
	public static OpenCPSDeliverableTypeRole create(long deliverableTypeRoleId) {
		return getPersistence().create(deliverableTypeRoleId);
	}

	/**
	* Removes the open cps deliverable type role with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param deliverableTypeRoleId the primary key of the open cps deliverable type role
	* @return the open cps deliverable type role that was removed
	* @throws NoSuchOpenCPSDeliverableTypeRoleException if a open cps deliverable type role with the primary key could not be found
	*/
	public static OpenCPSDeliverableTypeRole remove(long deliverableTypeRoleId)
		throws org.opencps.deliverable.exception.NoSuchOpenCPSDeliverableTypeRoleException {
		return getPersistence().remove(deliverableTypeRoleId);
	}

	public static OpenCPSDeliverableTypeRole updateImpl(
		OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole) {
		return getPersistence().updateImpl(openCPSDeliverableTypeRole);
	}

	/**
	* Returns the open cps deliverable type role with the primary key or throws a {@link NoSuchOpenCPSDeliverableTypeRoleException} if it could not be found.
	*
	* @param deliverableTypeRoleId the primary key of the open cps deliverable type role
	* @return the open cps deliverable type role
	* @throws NoSuchOpenCPSDeliverableTypeRoleException if a open cps deliverable type role with the primary key could not be found
	*/
	public static OpenCPSDeliverableTypeRole findByPrimaryKey(
		long deliverableTypeRoleId)
		throws org.opencps.deliverable.exception.NoSuchOpenCPSDeliverableTypeRoleException {
		return getPersistence().findByPrimaryKey(deliverableTypeRoleId);
	}

	/**
	* Returns the open cps deliverable type role with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param deliverableTypeRoleId the primary key of the open cps deliverable type role
	* @return the open cps deliverable type role, or <code>null</code> if a open cps deliverable type role with the primary key could not be found
	*/
	public static OpenCPSDeliverableTypeRole fetchByPrimaryKey(
		long deliverableTypeRoleId) {
		return getPersistence().fetchByPrimaryKey(deliverableTypeRoleId);
	}

	public static java.util.Map<java.io.Serializable, OpenCPSDeliverableTypeRole> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the open cps deliverable type roles.
	*
	* @return the open cps deliverable type roles
	*/
	public static List<OpenCPSDeliverableTypeRole> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the open cps deliverable type roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of open cps deliverable type roles
	* @param end the upper bound of the range of open cps deliverable type roles (not inclusive)
	* @return the range of open cps deliverable type roles
	*/
	public static List<OpenCPSDeliverableTypeRole> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the open cps deliverable type roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of open cps deliverable type roles
	* @param end the upper bound of the range of open cps deliverable type roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of open cps deliverable type roles
	*/
	public static List<OpenCPSDeliverableTypeRole> findAll(int start, int end,
		OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the open cps deliverable type roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of open cps deliverable type roles
	* @param end the upper bound of the range of open cps deliverable type roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of open cps deliverable type roles
	*/
	public static List<OpenCPSDeliverableTypeRole> findAll(int start, int end,
		OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the open cps deliverable type roles from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of open cps deliverable type roles.
	*
	* @return the number of open cps deliverable type roles
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static OpenCPSDeliverableTypeRolePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<OpenCPSDeliverableTypeRolePersistence, OpenCPSDeliverableTypeRolePersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(OpenCPSDeliverableTypeRolePersistence.class);

		ServiceTracker<OpenCPSDeliverableTypeRolePersistence, OpenCPSDeliverableTypeRolePersistence> serviceTracker =
			new ServiceTracker<OpenCPSDeliverableTypeRolePersistence, OpenCPSDeliverableTypeRolePersistence>(bundle.getBundleContext(),
				OpenCPSDeliverableTypeRolePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}