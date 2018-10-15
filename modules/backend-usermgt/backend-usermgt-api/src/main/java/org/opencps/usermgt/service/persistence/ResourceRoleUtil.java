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

package org.opencps.usermgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.usermgt.model.ResourceRole;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the resource role service. This utility wraps {@link org.opencps.usermgt.service.persistence.impl.ResourceRolePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see ResourceRolePersistence
 * @see org.opencps.usermgt.service.persistence.impl.ResourceRolePersistenceImpl
 * @generated
 */
@ProviderType
public class ResourceRoleUtil {
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
	public static void clearCache(ResourceRole resourceRole) {
		getPersistence().clearCache(resourceRole);
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
	public static List<ResourceRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ResourceRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ResourceRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ResourceRole> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ResourceRole update(ResourceRole resourceRole) {
		return getPersistence().update(resourceRole);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ResourceRole update(ResourceRole resourceRole,
		ServiceContext serviceContext) {
		return getPersistence().update(resourceRole, serviceContext);
	}

	/**
	* Returns all the resource roles where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching resource roles
	*/
	public static List<ResourceRole> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the resource roles where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of resource roles
	* @param end the upper bound of the range of resource roles (not inclusive)
	* @return the range of matching resource roles
	*/
	public static List<ResourceRole> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the resource roles where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of resource roles
	* @param end the upper bound of the range of resource roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching resource roles
	*/
	public static List<ResourceRole> findByUuid(String uuid, int start,
		int end, OrderByComparator<ResourceRole> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the resource roles where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of resource roles
	* @param end the upper bound of the range of resource roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching resource roles
	*/
	public static List<ResourceRole> findByUuid(String uuid, int start,
		int end, OrderByComparator<ResourceRole> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first resource role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching resource role
	* @throws NoSuchResourceRoleException if a matching resource role could not be found
	*/
	public static ResourceRole findByUuid_First(String uuid,
		OrderByComparator<ResourceRole> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchResourceRoleException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first resource role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching resource role, or <code>null</code> if a matching resource role could not be found
	*/
	public static ResourceRole fetchByUuid_First(String uuid,
		OrderByComparator<ResourceRole> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last resource role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching resource role
	* @throws NoSuchResourceRoleException if a matching resource role could not be found
	*/
	public static ResourceRole findByUuid_Last(String uuid,
		OrderByComparator<ResourceRole> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchResourceRoleException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last resource role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching resource role, or <code>null</code> if a matching resource role could not be found
	*/
	public static ResourceRole fetchByUuid_Last(String uuid,
		OrderByComparator<ResourceRole> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the resource roles before and after the current resource role in the ordered set where uuid = &#63;.
	*
	* @param resourceRoleId the primary key of the current resource role
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next resource role
	* @throws NoSuchResourceRoleException if a resource role with the primary key could not be found
	*/
	public static ResourceRole[] findByUuid_PrevAndNext(long resourceRoleId,
		String uuid, OrderByComparator<ResourceRole> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchResourceRoleException {
		return getPersistence()
				   .findByUuid_PrevAndNext(resourceRoleId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the resource roles where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of resource roles where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching resource roles
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the resource role where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchResourceRoleException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching resource role
	* @throws NoSuchResourceRoleException if a matching resource role could not be found
	*/
	public static ResourceRole findByUUID_G(String uuid, long groupId)
		throws org.opencps.usermgt.exception.NoSuchResourceRoleException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the resource role where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching resource role, or <code>null</code> if a matching resource role could not be found
	*/
	public static ResourceRole fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the resource role where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching resource role, or <code>null</code> if a matching resource role could not be found
	*/
	public static ResourceRole fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the resource role where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the resource role that was removed
	*/
	public static ResourceRole removeByUUID_G(String uuid, long groupId)
		throws org.opencps.usermgt.exception.NoSuchResourceRoleException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of resource roles where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching resource roles
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the resource roles where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching resource roles
	*/
	public static List<ResourceRole> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the resource roles where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of resource roles
	* @param end the upper bound of the range of resource roles (not inclusive)
	* @return the range of matching resource roles
	*/
	public static List<ResourceRole> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the resource roles where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of resource roles
	* @param end the upper bound of the range of resource roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching resource roles
	*/
	public static List<ResourceRole> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ResourceRole> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the resource roles where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of resource roles
	* @param end the upper bound of the range of resource roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching resource roles
	*/
	public static List<ResourceRole> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ResourceRole> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first resource role in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching resource role
	* @throws NoSuchResourceRoleException if a matching resource role could not be found
	*/
	public static ResourceRole findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ResourceRole> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchResourceRoleException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first resource role in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching resource role, or <code>null</code> if a matching resource role could not be found
	*/
	public static ResourceRole fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ResourceRole> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last resource role in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching resource role
	* @throws NoSuchResourceRoleException if a matching resource role could not be found
	*/
	public static ResourceRole findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ResourceRole> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchResourceRoleException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last resource role in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching resource role, or <code>null</code> if a matching resource role could not be found
	*/
	public static ResourceRole fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ResourceRole> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the resource roles before and after the current resource role in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param resourceRoleId the primary key of the current resource role
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next resource role
	* @throws NoSuchResourceRoleException if a resource role with the primary key could not be found
	*/
	public static ResourceRole[] findByUuid_C_PrevAndNext(long resourceRoleId,
		String uuid, long companyId,
		OrderByComparator<ResourceRole> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchResourceRoleException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(resourceRoleId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the resource roles where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of resource roles where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching resource roles
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns the resource role where groupId = &#63; and className = &#63; and classPK = &#63; and roleId = &#63; or throws a {@link NoSuchResourceRoleException} if it could not be found.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param roleId the role ID
	* @return the matching resource role
	* @throws NoSuchResourceRoleException if a matching resource role could not be found
	*/
	public static ResourceRole findByF_className_classPK_roleId(long groupId,
		String className, String classPK, long roleId)
		throws org.opencps.usermgt.exception.NoSuchResourceRoleException {
		return getPersistence()
				   .findByF_className_classPK_roleId(groupId, className,
			classPK, roleId);
	}

	/**
	* Returns the resource role where groupId = &#63; and className = &#63; and classPK = &#63; and roleId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param roleId the role ID
	* @return the matching resource role, or <code>null</code> if a matching resource role could not be found
	*/
	public static ResourceRole fetchByF_className_classPK_roleId(long groupId,
		String className, String classPK, long roleId) {
		return getPersistence()
				   .fetchByF_className_classPK_roleId(groupId, className,
			classPK, roleId);
	}

	/**
	* Returns the resource role where groupId = &#63; and className = &#63; and classPK = &#63; and roleId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param roleId the role ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching resource role, or <code>null</code> if a matching resource role could not be found
	*/
	public static ResourceRole fetchByF_className_classPK_roleId(long groupId,
		String className, String classPK, long roleId, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_className_classPK_roleId(groupId, className,
			classPK, roleId, retrieveFromCache);
	}

	/**
	* Removes the resource role where groupId = &#63; and className = &#63; and classPK = &#63; and roleId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param roleId the role ID
	* @return the resource role that was removed
	*/
	public static ResourceRole removeByF_className_classPK_roleId(
		long groupId, String className, String classPK, long roleId)
		throws org.opencps.usermgt.exception.NoSuchResourceRoleException {
		return getPersistence()
				   .removeByF_className_classPK_roleId(groupId, className,
			classPK, roleId);
	}

	/**
	* Returns the number of resource roles where groupId = &#63; and className = &#63; and classPK = &#63; and roleId = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param roleId the role ID
	* @return the number of matching resource roles
	*/
	public static int countByF_className_classPK_roleId(long groupId,
		String className, String classPK, long roleId) {
		return getPersistence()
				   .countByF_className_classPK_roleId(groupId, className,
			classPK, roleId);
	}

	/**
	* Returns all the resource roles where groupId = &#63; and className = &#63; and classPK = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @return the matching resource roles
	*/
	public static List<ResourceRole> findByF_className_classPK(long groupId,
		String className, String classPK) {
		return getPersistence()
				   .findByF_className_classPK(groupId, className, classPK);
	}

	/**
	* Returns a range of all the resource roles where groupId = &#63; and className = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param start the lower bound of the range of resource roles
	* @param end the upper bound of the range of resource roles (not inclusive)
	* @return the range of matching resource roles
	*/
	public static List<ResourceRole> findByF_className_classPK(long groupId,
		String className, String classPK, int start, int end) {
		return getPersistence()
				   .findByF_className_classPK(groupId, className, classPK,
			start, end);
	}

	/**
	* Returns an ordered range of all the resource roles where groupId = &#63; and className = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param start the lower bound of the range of resource roles
	* @param end the upper bound of the range of resource roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching resource roles
	*/
	public static List<ResourceRole> findByF_className_classPK(long groupId,
		String className, String classPK, int start, int end,
		OrderByComparator<ResourceRole> orderByComparator) {
		return getPersistence()
				   .findByF_className_classPK(groupId, className, classPK,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the resource roles where groupId = &#63; and className = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param start the lower bound of the range of resource roles
	* @param end the upper bound of the range of resource roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching resource roles
	*/
	public static List<ResourceRole> findByF_className_classPK(long groupId,
		String className, String classPK, int start, int end,
		OrderByComparator<ResourceRole> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_className_classPK(groupId, className, classPK,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first resource role in the ordered set where groupId = &#63; and className = &#63; and classPK = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching resource role
	* @throws NoSuchResourceRoleException if a matching resource role could not be found
	*/
	public static ResourceRole findByF_className_classPK_First(long groupId,
		String className, String classPK,
		OrderByComparator<ResourceRole> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchResourceRoleException {
		return getPersistence()
				   .findByF_className_classPK_First(groupId, className,
			classPK, orderByComparator);
	}

	/**
	* Returns the first resource role in the ordered set where groupId = &#63; and className = &#63; and classPK = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching resource role, or <code>null</code> if a matching resource role could not be found
	*/
	public static ResourceRole fetchByF_className_classPK_First(long groupId,
		String className, String classPK,
		OrderByComparator<ResourceRole> orderByComparator) {
		return getPersistence()
				   .fetchByF_className_classPK_First(groupId, className,
			classPK, orderByComparator);
	}

	/**
	* Returns the last resource role in the ordered set where groupId = &#63; and className = &#63; and classPK = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching resource role
	* @throws NoSuchResourceRoleException if a matching resource role could not be found
	*/
	public static ResourceRole findByF_className_classPK_Last(long groupId,
		String className, String classPK,
		OrderByComparator<ResourceRole> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchResourceRoleException {
		return getPersistence()
				   .findByF_className_classPK_Last(groupId, className, classPK,
			orderByComparator);
	}

	/**
	* Returns the last resource role in the ordered set where groupId = &#63; and className = &#63; and classPK = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching resource role, or <code>null</code> if a matching resource role could not be found
	*/
	public static ResourceRole fetchByF_className_classPK_Last(long groupId,
		String className, String classPK,
		OrderByComparator<ResourceRole> orderByComparator) {
		return getPersistence()
				   .fetchByF_className_classPK_Last(groupId, className,
			classPK, orderByComparator);
	}

	/**
	* Returns the resource roles before and after the current resource role in the ordered set where groupId = &#63; and className = &#63; and classPK = &#63;.
	*
	* @param resourceRoleId the primary key of the current resource role
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next resource role
	* @throws NoSuchResourceRoleException if a resource role with the primary key could not be found
	*/
	public static ResourceRole[] findByF_className_classPK_PrevAndNext(
		long resourceRoleId, long groupId, String className, String classPK,
		OrderByComparator<ResourceRole> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchResourceRoleException {
		return getPersistence()
				   .findByF_className_classPK_PrevAndNext(resourceRoleId,
			groupId, className, classPK, orderByComparator);
	}

	/**
	* Removes all the resource roles where groupId = &#63; and className = &#63; and classPK = &#63; from the database.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	*/
	public static void removeByF_className_classPK(long groupId,
		String className, String classPK) {
		getPersistence().removeByF_className_classPK(groupId, className, classPK);
	}

	/**
	* Returns the number of resource roles where groupId = &#63; and className = &#63; and classPK = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @return the number of matching resource roles
	*/
	public static int countByF_className_classPK(long groupId,
		String className, String classPK) {
		return getPersistence()
				   .countByF_className_classPK(groupId, className, classPK);
	}

	/**
	* Caches the resource role in the entity cache if it is enabled.
	*
	* @param resourceRole the resource role
	*/
	public static void cacheResult(ResourceRole resourceRole) {
		getPersistence().cacheResult(resourceRole);
	}

	/**
	* Caches the resource roles in the entity cache if it is enabled.
	*
	* @param resourceRoles the resource roles
	*/
	public static void cacheResult(List<ResourceRole> resourceRoles) {
		getPersistence().cacheResult(resourceRoles);
	}

	/**
	* Creates a new resource role with the primary key. Does not add the resource role to the database.
	*
	* @param resourceRoleId the primary key for the new resource role
	* @return the new resource role
	*/
	public static ResourceRole create(long resourceRoleId) {
		return getPersistence().create(resourceRoleId);
	}

	/**
	* Removes the resource role with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param resourceRoleId the primary key of the resource role
	* @return the resource role that was removed
	* @throws NoSuchResourceRoleException if a resource role with the primary key could not be found
	*/
	public static ResourceRole remove(long resourceRoleId)
		throws org.opencps.usermgt.exception.NoSuchResourceRoleException {
		return getPersistence().remove(resourceRoleId);
	}

	public static ResourceRole updateImpl(ResourceRole resourceRole) {
		return getPersistence().updateImpl(resourceRole);
	}

	/**
	* Returns the resource role with the primary key or throws a {@link NoSuchResourceRoleException} if it could not be found.
	*
	* @param resourceRoleId the primary key of the resource role
	* @return the resource role
	* @throws NoSuchResourceRoleException if a resource role with the primary key could not be found
	*/
	public static ResourceRole findByPrimaryKey(long resourceRoleId)
		throws org.opencps.usermgt.exception.NoSuchResourceRoleException {
		return getPersistence().findByPrimaryKey(resourceRoleId);
	}

	/**
	* Returns the resource role with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param resourceRoleId the primary key of the resource role
	* @return the resource role, or <code>null</code> if a resource role with the primary key could not be found
	*/
	public static ResourceRole fetchByPrimaryKey(long resourceRoleId) {
		return getPersistence().fetchByPrimaryKey(resourceRoleId);
	}

	public static java.util.Map<java.io.Serializable, ResourceRole> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the resource roles.
	*
	* @return the resource roles
	*/
	public static List<ResourceRole> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the resource roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of resource roles
	* @param end the upper bound of the range of resource roles (not inclusive)
	* @return the range of resource roles
	*/
	public static List<ResourceRole> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the resource roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of resource roles
	* @param end the upper bound of the range of resource roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of resource roles
	*/
	public static List<ResourceRole> findAll(int start, int end,
		OrderByComparator<ResourceRole> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the resource roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of resource roles
	* @param end the upper bound of the range of resource roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of resource roles
	*/
	public static List<ResourceRole> findAll(int start, int end,
		OrderByComparator<ResourceRole> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the resource roles from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of resource roles.
	*
	* @return the number of resource roles
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ResourceRolePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ResourceRolePersistence, ResourceRolePersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ResourceRolePersistence.class);

		ServiceTracker<ResourceRolePersistence, ResourceRolePersistence> serviceTracker =
			new ServiceTracker<ResourceRolePersistence, ResourceRolePersistence>(bundle.getBundleContext(),
				ResourceRolePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}