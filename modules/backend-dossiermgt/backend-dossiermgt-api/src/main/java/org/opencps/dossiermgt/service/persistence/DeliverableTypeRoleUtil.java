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

import org.opencps.dossiermgt.model.DeliverableTypeRole;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the deliverable type role service. This utility wraps {@link org.opencps.dossiermgt.service.persistence.impl.DeliverableTypeRolePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see DeliverableTypeRolePersistence
 * @see org.opencps.dossiermgt.service.persistence.impl.DeliverableTypeRolePersistenceImpl
 * @generated
 */
@ProviderType
public class DeliverableTypeRoleUtil {
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
	public static void clearCache(DeliverableTypeRole deliverableTypeRole) {
		getPersistence().clearCache(deliverableTypeRole);
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
	public static List<DeliverableTypeRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<DeliverableTypeRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<DeliverableTypeRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<DeliverableTypeRole> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static DeliverableTypeRole update(
		DeliverableTypeRole deliverableTypeRole) {
		return getPersistence().update(deliverableTypeRole);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static DeliverableTypeRole update(
		DeliverableTypeRole deliverableTypeRole, ServiceContext serviceContext) {
		return getPersistence().update(deliverableTypeRole, serviceContext);
	}

	/**
	* Returns all the deliverable type roles where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching deliverable type roles
	*/
	public static List<DeliverableTypeRole> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the deliverable type roles where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of deliverable type roles
	* @param end the upper bound of the range of deliverable type roles (not inclusive)
	* @return the range of matching deliverable type roles
	*/
	public static List<DeliverableTypeRole> findByUuid(String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the deliverable type roles where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of deliverable type roles
	* @param end the upper bound of the range of deliverable type roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching deliverable type roles
	*/
	public static List<DeliverableTypeRole> findByUuid(String uuid, int start,
		int end, OrderByComparator<DeliverableTypeRole> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the deliverable type roles where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of deliverable type roles
	* @param end the upper bound of the range of deliverable type roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching deliverable type roles
	*/
	public static List<DeliverableTypeRole> findByUuid(String uuid, int start,
		int end, OrderByComparator<DeliverableTypeRole> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first deliverable type role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching deliverable type role
	* @throws NoSuchDeliverableTypeRoleException if a matching deliverable type role could not be found
	*/
	public static DeliverableTypeRole findByUuid_First(String uuid,
		OrderByComparator<DeliverableTypeRole> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableTypeRoleException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first deliverable type role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching deliverable type role, or <code>null</code> if a matching deliverable type role could not be found
	*/
	public static DeliverableTypeRole fetchByUuid_First(String uuid,
		OrderByComparator<DeliverableTypeRole> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last deliverable type role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching deliverable type role
	* @throws NoSuchDeliverableTypeRoleException if a matching deliverable type role could not be found
	*/
	public static DeliverableTypeRole findByUuid_Last(String uuid,
		OrderByComparator<DeliverableTypeRole> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableTypeRoleException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last deliverable type role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching deliverable type role, or <code>null</code> if a matching deliverable type role could not be found
	*/
	public static DeliverableTypeRole fetchByUuid_Last(String uuid,
		OrderByComparator<DeliverableTypeRole> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the deliverable type roles before and after the current deliverable type role in the ordered set where uuid = &#63;.
	*
	* @param deliverableTypeRoleId the primary key of the current deliverable type role
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next deliverable type role
	* @throws NoSuchDeliverableTypeRoleException if a deliverable type role with the primary key could not be found
	*/
	public static DeliverableTypeRole[] findByUuid_PrevAndNext(
		long deliverableTypeRoleId, String uuid,
		OrderByComparator<DeliverableTypeRole> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableTypeRoleException {
		return getPersistence()
				   .findByUuid_PrevAndNext(deliverableTypeRoleId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the deliverable type roles where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of deliverable type roles where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching deliverable type roles
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the deliverable type role where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDeliverableTypeRoleException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching deliverable type role
	* @throws NoSuchDeliverableTypeRoleException if a matching deliverable type role could not be found
	*/
	public static DeliverableTypeRole findByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableTypeRoleException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the deliverable type role where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching deliverable type role, or <code>null</code> if a matching deliverable type role could not be found
	*/
	public static DeliverableTypeRole fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the deliverable type role where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching deliverable type role, or <code>null</code> if a matching deliverable type role could not be found
	*/
	public static DeliverableTypeRole fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the deliverable type role where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the deliverable type role that was removed
	*/
	public static DeliverableTypeRole removeByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableTypeRoleException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of deliverable type roles where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching deliverable type roles
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the deliverable type roles where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching deliverable type roles
	*/
	public static List<DeliverableTypeRole> findByUuid_C(String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the deliverable type roles where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of deliverable type roles
	* @param end the upper bound of the range of deliverable type roles (not inclusive)
	* @return the range of matching deliverable type roles
	*/
	public static List<DeliverableTypeRole> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the deliverable type roles where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of deliverable type roles
	* @param end the upper bound of the range of deliverable type roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching deliverable type roles
	*/
	public static List<DeliverableTypeRole> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<DeliverableTypeRole> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the deliverable type roles where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of deliverable type roles
	* @param end the upper bound of the range of deliverable type roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching deliverable type roles
	*/
	public static List<DeliverableTypeRole> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<DeliverableTypeRole> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first deliverable type role in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching deliverable type role
	* @throws NoSuchDeliverableTypeRoleException if a matching deliverable type role could not be found
	*/
	public static DeliverableTypeRole findByUuid_C_First(String uuid,
		long companyId, OrderByComparator<DeliverableTypeRole> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableTypeRoleException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first deliverable type role in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching deliverable type role, or <code>null</code> if a matching deliverable type role could not be found
	*/
	public static DeliverableTypeRole fetchByUuid_C_First(String uuid,
		long companyId, OrderByComparator<DeliverableTypeRole> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last deliverable type role in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching deliverable type role
	* @throws NoSuchDeliverableTypeRoleException if a matching deliverable type role could not be found
	*/
	public static DeliverableTypeRole findByUuid_C_Last(String uuid,
		long companyId, OrderByComparator<DeliverableTypeRole> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableTypeRoleException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last deliverable type role in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching deliverable type role, or <code>null</code> if a matching deliverable type role could not be found
	*/
	public static DeliverableTypeRole fetchByUuid_C_Last(String uuid,
		long companyId, OrderByComparator<DeliverableTypeRole> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the deliverable type roles before and after the current deliverable type role in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param deliverableTypeRoleId the primary key of the current deliverable type role
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next deliverable type role
	* @throws NoSuchDeliverableTypeRoleException if a deliverable type role with the primary key could not be found
	*/
	public static DeliverableTypeRole[] findByUuid_C_PrevAndNext(
		long deliverableTypeRoleId, String uuid, long companyId,
		OrderByComparator<DeliverableTypeRole> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableTypeRoleException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(deliverableTypeRoleId, uuid,
			companyId, orderByComparator);
	}

	/**
	* Removes all the deliverable type roles where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of deliverable type roles where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching deliverable type roles
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the deliverable type roles where deliverableTypeId = &#63;.
	*
	* @param deliverableTypeId the deliverable type ID
	* @return the matching deliverable type roles
	*/
	public static List<DeliverableTypeRole> findByF_deliverableTypeId(
		long deliverableTypeId) {
		return getPersistence().findByF_deliverableTypeId(deliverableTypeId);
	}

	/**
	* Returns a range of all the deliverable type roles where deliverableTypeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param deliverableTypeId the deliverable type ID
	* @param start the lower bound of the range of deliverable type roles
	* @param end the upper bound of the range of deliverable type roles (not inclusive)
	* @return the range of matching deliverable type roles
	*/
	public static List<DeliverableTypeRole> findByF_deliverableTypeId(
		long deliverableTypeId, int start, int end) {
		return getPersistence()
				   .findByF_deliverableTypeId(deliverableTypeId, start, end);
	}

	/**
	* Returns an ordered range of all the deliverable type roles where deliverableTypeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param deliverableTypeId the deliverable type ID
	* @param start the lower bound of the range of deliverable type roles
	* @param end the upper bound of the range of deliverable type roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching deliverable type roles
	*/
	public static List<DeliverableTypeRole> findByF_deliverableTypeId(
		long deliverableTypeId, int start, int end,
		OrderByComparator<DeliverableTypeRole> orderByComparator) {
		return getPersistence()
				   .findByF_deliverableTypeId(deliverableTypeId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the deliverable type roles where deliverableTypeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param deliverableTypeId the deliverable type ID
	* @param start the lower bound of the range of deliverable type roles
	* @param end the upper bound of the range of deliverable type roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching deliverable type roles
	*/
	public static List<DeliverableTypeRole> findByF_deliverableTypeId(
		long deliverableTypeId, int start, int end,
		OrderByComparator<DeliverableTypeRole> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_deliverableTypeId(deliverableTypeId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first deliverable type role in the ordered set where deliverableTypeId = &#63;.
	*
	* @param deliverableTypeId the deliverable type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching deliverable type role
	* @throws NoSuchDeliverableTypeRoleException if a matching deliverable type role could not be found
	*/
	public static DeliverableTypeRole findByF_deliverableTypeId_First(
		long deliverableTypeId,
		OrderByComparator<DeliverableTypeRole> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableTypeRoleException {
		return getPersistence()
				   .findByF_deliverableTypeId_First(deliverableTypeId,
			orderByComparator);
	}

	/**
	* Returns the first deliverable type role in the ordered set where deliverableTypeId = &#63;.
	*
	* @param deliverableTypeId the deliverable type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching deliverable type role, or <code>null</code> if a matching deliverable type role could not be found
	*/
	public static DeliverableTypeRole fetchByF_deliverableTypeId_First(
		long deliverableTypeId,
		OrderByComparator<DeliverableTypeRole> orderByComparator) {
		return getPersistence()
				   .fetchByF_deliverableTypeId_First(deliverableTypeId,
			orderByComparator);
	}

	/**
	* Returns the last deliverable type role in the ordered set where deliverableTypeId = &#63;.
	*
	* @param deliverableTypeId the deliverable type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching deliverable type role
	* @throws NoSuchDeliverableTypeRoleException if a matching deliverable type role could not be found
	*/
	public static DeliverableTypeRole findByF_deliverableTypeId_Last(
		long deliverableTypeId,
		OrderByComparator<DeliverableTypeRole> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableTypeRoleException {
		return getPersistence()
				   .findByF_deliverableTypeId_Last(deliverableTypeId,
			orderByComparator);
	}

	/**
	* Returns the last deliverable type role in the ordered set where deliverableTypeId = &#63;.
	*
	* @param deliverableTypeId the deliverable type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching deliverable type role, or <code>null</code> if a matching deliverable type role could not be found
	*/
	public static DeliverableTypeRole fetchByF_deliverableTypeId_Last(
		long deliverableTypeId,
		OrderByComparator<DeliverableTypeRole> orderByComparator) {
		return getPersistence()
				   .fetchByF_deliverableTypeId_Last(deliverableTypeId,
			orderByComparator);
	}

	/**
	* Returns the deliverable type roles before and after the current deliverable type role in the ordered set where deliverableTypeId = &#63;.
	*
	* @param deliverableTypeRoleId the primary key of the current deliverable type role
	* @param deliverableTypeId the deliverable type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next deliverable type role
	* @throws NoSuchDeliverableTypeRoleException if a deliverable type role with the primary key could not be found
	*/
	public static DeliverableTypeRole[] findByF_deliverableTypeId_PrevAndNext(
		long deliverableTypeRoleId, long deliverableTypeId,
		OrderByComparator<DeliverableTypeRole> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableTypeRoleException {
		return getPersistence()
				   .findByF_deliverableTypeId_PrevAndNext(deliverableTypeRoleId,
			deliverableTypeId, orderByComparator);
	}

	/**
	* Removes all the deliverable type roles where deliverableTypeId = &#63; from the database.
	*
	* @param deliverableTypeId the deliverable type ID
	*/
	public static void removeByF_deliverableTypeId(long deliverableTypeId) {
		getPersistence().removeByF_deliverableTypeId(deliverableTypeId);
	}

	/**
	* Returns the number of deliverable type roles where deliverableTypeId = &#63;.
	*
	* @param deliverableTypeId the deliverable type ID
	* @return the number of matching deliverable type roles
	*/
	public static int countByF_deliverableTypeId(long deliverableTypeId) {
		return getPersistence().countByF_deliverableTypeId(deliverableTypeId);
	}

	/**
	* Returns the deliverable type role where groupId = &#63; and deliverableTypeId = &#63; and roleId = &#63; or throws a {@link NoSuchDeliverableTypeRoleException} if it could not be found.
	*
	* @param groupId the group ID
	* @param deliverableTypeId the deliverable type ID
	* @param roleId the role ID
	* @return the matching deliverable type role
	* @throws NoSuchDeliverableTypeRoleException if a matching deliverable type role could not be found
	*/
	public static DeliverableTypeRole findByF_GID_DID_RID(long groupId,
		long deliverableTypeId, long roleId)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableTypeRoleException {
		return getPersistence()
				   .findByF_GID_DID_RID(groupId, deliverableTypeId, roleId);
	}

	/**
	* Returns the deliverable type role where groupId = &#63; and deliverableTypeId = &#63; and roleId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param deliverableTypeId the deliverable type ID
	* @param roleId the role ID
	* @return the matching deliverable type role, or <code>null</code> if a matching deliverable type role could not be found
	*/
	public static DeliverableTypeRole fetchByF_GID_DID_RID(long groupId,
		long deliverableTypeId, long roleId) {
		return getPersistence()
				   .fetchByF_GID_DID_RID(groupId, deliverableTypeId, roleId);
	}

	/**
	* Returns the deliverable type role where groupId = &#63; and deliverableTypeId = &#63; and roleId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param deliverableTypeId the deliverable type ID
	* @param roleId the role ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching deliverable type role, or <code>null</code> if a matching deliverable type role could not be found
	*/
	public static DeliverableTypeRole fetchByF_GID_DID_RID(long groupId,
		long deliverableTypeId, long roleId, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_GID_DID_RID(groupId, deliverableTypeId, roleId,
			retrieveFromCache);
	}

	/**
	* Removes the deliverable type role where groupId = &#63; and deliverableTypeId = &#63; and roleId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param deliverableTypeId the deliverable type ID
	* @param roleId the role ID
	* @return the deliverable type role that was removed
	*/
	public static DeliverableTypeRole removeByF_GID_DID_RID(long groupId,
		long deliverableTypeId, long roleId)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableTypeRoleException {
		return getPersistence()
				   .removeByF_GID_DID_RID(groupId, deliverableTypeId, roleId);
	}

	/**
	* Returns the number of deliverable type roles where groupId = &#63; and deliverableTypeId = &#63; and roleId = &#63;.
	*
	* @param groupId the group ID
	* @param deliverableTypeId the deliverable type ID
	* @param roleId the role ID
	* @return the number of matching deliverable type roles
	*/
	public static int countByF_GID_DID_RID(long groupId,
		long deliverableTypeId, long roleId) {
		return getPersistence()
				   .countByF_GID_DID_RID(groupId, deliverableTypeId, roleId);
	}

	/**
	* Caches the deliverable type role in the entity cache if it is enabled.
	*
	* @param deliverableTypeRole the deliverable type role
	*/
	public static void cacheResult(DeliverableTypeRole deliverableTypeRole) {
		getPersistence().cacheResult(deliverableTypeRole);
	}

	/**
	* Caches the deliverable type roles in the entity cache if it is enabled.
	*
	* @param deliverableTypeRoles the deliverable type roles
	*/
	public static void cacheResult(
		List<DeliverableTypeRole> deliverableTypeRoles) {
		getPersistence().cacheResult(deliverableTypeRoles);
	}

	/**
	* Creates a new deliverable type role with the primary key. Does not add the deliverable type role to the database.
	*
	* @param deliverableTypeRoleId the primary key for the new deliverable type role
	* @return the new deliverable type role
	*/
	public static DeliverableTypeRole create(long deliverableTypeRoleId) {
		return getPersistence().create(deliverableTypeRoleId);
	}

	/**
	* Removes the deliverable type role with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param deliverableTypeRoleId the primary key of the deliverable type role
	* @return the deliverable type role that was removed
	* @throws NoSuchDeliverableTypeRoleException if a deliverable type role with the primary key could not be found
	*/
	public static DeliverableTypeRole remove(long deliverableTypeRoleId)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableTypeRoleException {
		return getPersistence().remove(deliverableTypeRoleId);
	}

	public static DeliverableTypeRole updateImpl(
		DeliverableTypeRole deliverableTypeRole) {
		return getPersistence().updateImpl(deliverableTypeRole);
	}

	/**
	* Returns the deliverable type role with the primary key or throws a {@link NoSuchDeliverableTypeRoleException} if it could not be found.
	*
	* @param deliverableTypeRoleId the primary key of the deliverable type role
	* @return the deliverable type role
	* @throws NoSuchDeliverableTypeRoleException if a deliverable type role with the primary key could not be found
	*/
	public static DeliverableTypeRole findByPrimaryKey(
		long deliverableTypeRoleId)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableTypeRoleException {
		return getPersistence().findByPrimaryKey(deliverableTypeRoleId);
	}

	/**
	* Returns the deliverable type role with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param deliverableTypeRoleId the primary key of the deliverable type role
	* @return the deliverable type role, or <code>null</code> if a deliverable type role with the primary key could not be found
	*/
	public static DeliverableTypeRole fetchByPrimaryKey(
		long deliverableTypeRoleId) {
		return getPersistence().fetchByPrimaryKey(deliverableTypeRoleId);
	}

	public static java.util.Map<java.io.Serializable, DeliverableTypeRole> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the deliverable type roles.
	*
	* @return the deliverable type roles
	*/
	public static List<DeliverableTypeRole> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the deliverable type roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of deliverable type roles
	* @param end the upper bound of the range of deliverable type roles (not inclusive)
	* @return the range of deliverable type roles
	*/
	public static List<DeliverableTypeRole> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the deliverable type roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of deliverable type roles
	* @param end the upper bound of the range of deliverable type roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of deliverable type roles
	*/
	public static List<DeliverableTypeRole> findAll(int start, int end,
		OrderByComparator<DeliverableTypeRole> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the deliverable type roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of deliverable type roles
	* @param end the upper bound of the range of deliverable type roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of deliverable type roles
	*/
	public static List<DeliverableTypeRole> findAll(int start, int end,
		OrderByComparator<DeliverableTypeRole> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the deliverable type roles from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of deliverable type roles.
	*
	* @return the number of deliverable type roles
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static DeliverableTypeRolePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DeliverableTypeRolePersistence, DeliverableTypeRolePersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(DeliverableTypeRolePersistence.class);

		ServiceTracker<DeliverableTypeRolePersistence, DeliverableTypeRolePersistence> serviceTracker =
			new ServiceTracker<DeliverableTypeRolePersistence, DeliverableTypeRolePersistence>(bundle.getBundleContext(),
				DeliverableTypeRolePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}