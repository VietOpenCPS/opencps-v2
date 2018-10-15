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

import org.opencps.usermgt.model.ResourceUser;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the resource user service. This utility wraps {@link org.opencps.usermgt.service.persistence.impl.ResourceUserPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see ResourceUserPersistence
 * @see org.opencps.usermgt.service.persistence.impl.ResourceUserPersistenceImpl
 * @generated
 */
@ProviderType
public class ResourceUserUtil {
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
	public static void clearCache(ResourceUser resourceUser) {
		getPersistence().clearCache(resourceUser);
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
	public static List<ResourceUser> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ResourceUser> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ResourceUser> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ResourceUser> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ResourceUser update(ResourceUser resourceUser) {
		return getPersistence().update(resourceUser);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ResourceUser update(ResourceUser resourceUser,
		ServiceContext serviceContext) {
		return getPersistence().update(resourceUser, serviceContext);
	}

	/**
	* Returns all the resource users where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching resource users
	*/
	public static List<ResourceUser> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the resource users where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of resource users
	* @param end the upper bound of the range of resource users (not inclusive)
	* @return the range of matching resource users
	*/
	public static List<ResourceUser> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the resource users where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of resource users
	* @param end the upper bound of the range of resource users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching resource users
	*/
	public static List<ResourceUser> findByUuid(String uuid, int start,
		int end, OrderByComparator<ResourceUser> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the resource users where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of resource users
	* @param end the upper bound of the range of resource users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching resource users
	*/
	public static List<ResourceUser> findByUuid(String uuid, int start,
		int end, OrderByComparator<ResourceUser> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first resource user in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching resource user
	* @throws NoSuchResourceUserException if a matching resource user could not be found
	*/
	public static ResourceUser findByUuid_First(String uuid,
		OrderByComparator<ResourceUser> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchResourceUserException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first resource user in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching resource user, or <code>null</code> if a matching resource user could not be found
	*/
	public static ResourceUser fetchByUuid_First(String uuid,
		OrderByComparator<ResourceUser> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last resource user in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching resource user
	* @throws NoSuchResourceUserException if a matching resource user could not be found
	*/
	public static ResourceUser findByUuid_Last(String uuid,
		OrderByComparator<ResourceUser> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchResourceUserException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last resource user in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching resource user, or <code>null</code> if a matching resource user could not be found
	*/
	public static ResourceUser fetchByUuid_Last(String uuid,
		OrderByComparator<ResourceUser> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the resource users before and after the current resource user in the ordered set where uuid = &#63;.
	*
	* @param resourceUserId the primary key of the current resource user
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next resource user
	* @throws NoSuchResourceUserException if a resource user with the primary key could not be found
	*/
	public static ResourceUser[] findByUuid_PrevAndNext(long resourceUserId,
		String uuid, OrderByComparator<ResourceUser> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchResourceUserException {
		return getPersistence()
				   .findByUuid_PrevAndNext(resourceUserId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the resource users where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of resource users where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching resource users
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the resource user where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchResourceUserException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching resource user
	* @throws NoSuchResourceUserException if a matching resource user could not be found
	*/
	public static ResourceUser findByUUID_G(String uuid, long groupId)
		throws org.opencps.usermgt.exception.NoSuchResourceUserException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the resource user where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching resource user, or <code>null</code> if a matching resource user could not be found
	*/
	public static ResourceUser fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the resource user where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching resource user, or <code>null</code> if a matching resource user could not be found
	*/
	public static ResourceUser fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the resource user where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the resource user that was removed
	*/
	public static ResourceUser removeByUUID_G(String uuid, long groupId)
		throws org.opencps.usermgt.exception.NoSuchResourceUserException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of resource users where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching resource users
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the resource users where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching resource users
	*/
	public static List<ResourceUser> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the resource users where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of resource users
	* @param end the upper bound of the range of resource users (not inclusive)
	* @return the range of matching resource users
	*/
	public static List<ResourceUser> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the resource users where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of resource users
	* @param end the upper bound of the range of resource users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching resource users
	*/
	public static List<ResourceUser> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ResourceUser> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the resource users where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of resource users
	* @param end the upper bound of the range of resource users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching resource users
	*/
	public static List<ResourceUser> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ResourceUser> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first resource user in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching resource user
	* @throws NoSuchResourceUserException if a matching resource user could not be found
	*/
	public static ResourceUser findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ResourceUser> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchResourceUserException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first resource user in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching resource user, or <code>null</code> if a matching resource user could not be found
	*/
	public static ResourceUser fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ResourceUser> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last resource user in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching resource user
	* @throws NoSuchResourceUserException if a matching resource user could not be found
	*/
	public static ResourceUser findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ResourceUser> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchResourceUserException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last resource user in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching resource user, or <code>null</code> if a matching resource user could not be found
	*/
	public static ResourceUser fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ResourceUser> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the resource users before and after the current resource user in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param resourceUserId the primary key of the current resource user
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next resource user
	* @throws NoSuchResourceUserException if a resource user with the primary key could not be found
	*/
	public static ResourceUser[] findByUuid_C_PrevAndNext(long resourceUserId,
		String uuid, long companyId,
		OrderByComparator<ResourceUser> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchResourceUserException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(resourceUserId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the resource users where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of resource users where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching resource users
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns the resource user where groupId = &#63; and className = &#63; and classPK = &#63; and toUserId = &#63; or throws a {@link NoSuchResourceUserException} if it could not be found.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param toUserId the to user ID
	* @return the matching resource user
	* @throws NoSuchResourceUserException if a matching resource user could not be found
	*/
	public static ResourceUser findByF_className_classPK_toUserId(
		long groupId, String className, String classPK, long toUserId)
		throws org.opencps.usermgt.exception.NoSuchResourceUserException {
		return getPersistence()
				   .findByF_className_classPK_toUserId(groupId, className,
			classPK, toUserId);
	}

	/**
	* Returns the resource user where groupId = &#63; and className = &#63; and classPK = &#63; and toUserId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param toUserId the to user ID
	* @return the matching resource user, or <code>null</code> if a matching resource user could not be found
	*/
	public static ResourceUser fetchByF_className_classPK_toUserId(
		long groupId, String className, String classPK, long toUserId) {
		return getPersistence()
				   .fetchByF_className_classPK_toUserId(groupId, className,
			classPK, toUserId);
	}

	/**
	* Returns the resource user where groupId = &#63; and className = &#63; and classPK = &#63; and toUserId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param toUserId the to user ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching resource user, or <code>null</code> if a matching resource user could not be found
	*/
	public static ResourceUser fetchByF_className_classPK_toUserId(
		long groupId, String className, String classPK, long toUserId,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_className_classPK_toUserId(groupId, className,
			classPK, toUserId, retrieveFromCache);
	}

	/**
	* Removes the resource user where groupId = &#63; and className = &#63; and classPK = &#63; and toUserId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param toUserId the to user ID
	* @return the resource user that was removed
	*/
	public static ResourceUser removeByF_className_classPK_toUserId(
		long groupId, String className, String classPK, long toUserId)
		throws org.opencps.usermgt.exception.NoSuchResourceUserException {
		return getPersistence()
				   .removeByF_className_classPK_toUserId(groupId, className,
			classPK, toUserId);
	}

	/**
	* Returns the number of resource users where groupId = &#63; and className = &#63; and classPK = &#63; and toUserId = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param toUserId the to user ID
	* @return the number of matching resource users
	*/
	public static int countByF_className_classPK_toUserId(long groupId,
		String className, String classPK, long toUserId) {
		return getPersistence()
				   .countByF_className_classPK_toUserId(groupId, className,
			classPK, toUserId);
	}

	/**
	* Returns all the resource users where groupId = &#63; and className = &#63; and classPK = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @return the matching resource users
	*/
	public static List<ResourceUser> findByF_className_classPK(long groupId,
		String className, String classPK) {
		return getPersistence()
				   .findByF_className_classPK(groupId, className, classPK);
	}

	/**
	* Returns a range of all the resource users where groupId = &#63; and className = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param start the lower bound of the range of resource users
	* @param end the upper bound of the range of resource users (not inclusive)
	* @return the range of matching resource users
	*/
	public static List<ResourceUser> findByF_className_classPK(long groupId,
		String className, String classPK, int start, int end) {
		return getPersistence()
				   .findByF_className_classPK(groupId, className, classPK,
			start, end);
	}

	/**
	* Returns an ordered range of all the resource users where groupId = &#63; and className = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param start the lower bound of the range of resource users
	* @param end the upper bound of the range of resource users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching resource users
	*/
	public static List<ResourceUser> findByF_className_classPK(long groupId,
		String className, String classPK, int start, int end,
		OrderByComparator<ResourceUser> orderByComparator) {
		return getPersistence()
				   .findByF_className_classPK(groupId, className, classPK,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the resource users where groupId = &#63; and className = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param start the lower bound of the range of resource users
	* @param end the upper bound of the range of resource users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching resource users
	*/
	public static List<ResourceUser> findByF_className_classPK(long groupId,
		String className, String classPK, int start, int end,
		OrderByComparator<ResourceUser> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_className_classPK(groupId, className, classPK,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first resource user in the ordered set where groupId = &#63; and className = &#63; and classPK = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching resource user
	* @throws NoSuchResourceUserException if a matching resource user could not be found
	*/
	public static ResourceUser findByF_className_classPK_First(long groupId,
		String className, String classPK,
		OrderByComparator<ResourceUser> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchResourceUserException {
		return getPersistence()
				   .findByF_className_classPK_First(groupId, className,
			classPK, orderByComparator);
	}

	/**
	* Returns the first resource user in the ordered set where groupId = &#63; and className = &#63; and classPK = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching resource user, or <code>null</code> if a matching resource user could not be found
	*/
	public static ResourceUser fetchByF_className_classPK_First(long groupId,
		String className, String classPK,
		OrderByComparator<ResourceUser> orderByComparator) {
		return getPersistence()
				   .fetchByF_className_classPK_First(groupId, className,
			classPK, orderByComparator);
	}

	/**
	* Returns the last resource user in the ordered set where groupId = &#63; and className = &#63; and classPK = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching resource user
	* @throws NoSuchResourceUserException if a matching resource user could not be found
	*/
	public static ResourceUser findByF_className_classPK_Last(long groupId,
		String className, String classPK,
		OrderByComparator<ResourceUser> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchResourceUserException {
		return getPersistence()
				   .findByF_className_classPK_Last(groupId, className, classPK,
			orderByComparator);
	}

	/**
	* Returns the last resource user in the ordered set where groupId = &#63; and className = &#63; and classPK = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching resource user, or <code>null</code> if a matching resource user could not be found
	*/
	public static ResourceUser fetchByF_className_classPK_Last(long groupId,
		String className, String classPK,
		OrderByComparator<ResourceUser> orderByComparator) {
		return getPersistence()
				   .fetchByF_className_classPK_Last(groupId, className,
			classPK, orderByComparator);
	}

	/**
	* Returns the resource users before and after the current resource user in the ordered set where groupId = &#63; and className = &#63; and classPK = &#63;.
	*
	* @param resourceUserId the primary key of the current resource user
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next resource user
	* @throws NoSuchResourceUserException if a resource user with the primary key could not be found
	*/
	public static ResourceUser[] findByF_className_classPK_PrevAndNext(
		long resourceUserId, long groupId, String className, String classPK,
		OrderByComparator<ResourceUser> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchResourceUserException {
		return getPersistence()
				   .findByF_className_classPK_PrevAndNext(resourceUserId,
			groupId, className, classPK, orderByComparator);
	}

	/**
	* Removes all the resource users where groupId = &#63; and className = &#63; and classPK = &#63; from the database.
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
	* Returns the number of resource users where groupId = &#63; and className = &#63; and classPK = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @return the number of matching resource users
	*/
	public static int countByF_className_classPK(long groupId,
		String className, String classPK) {
		return getPersistence()
				   .countByF_className_classPK(groupId, className, classPK);
	}

	/**
	* Caches the resource user in the entity cache if it is enabled.
	*
	* @param resourceUser the resource user
	*/
	public static void cacheResult(ResourceUser resourceUser) {
		getPersistence().cacheResult(resourceUser);
	}

	/**
	* Caches the resource users in the entity cache if it is enabled.
	*
	* @param resourceUsers the resource users
	*/
	public static void cacheResult(List<ResourceUser> resourceUsers) {
		getPersistence().cacheResult(resourceUsers);
	}

	/**
	* Creates a new resource user with the primary key. Does not add the resource user to the database.
	*
	* @param resourceUserId the primary key for the new resource user
	* @return the new resource user
	*/
	public static ResourceUser create(long resourceUserId) {
		return getPersistence().create(resourceUserId);
	}

	/**
	* Removes the resource user with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param resourceUserId the primary key of the resource user
	* @return the resource user that was removed
	* @throws NoSuchResourceUserException if a resource user with the primary key could not be found
	*/
	public static ResourceUser remove(long resourceUserId)
		throws org.opencps.usermgt.exception.NoSuchResourceUserException {
		return getPersistence().remove(resourceUserId);
	}

	public static ResourceUser updateImpl(ResourceUser resourceUser) {
		return getPersistence().updateImpl(resourceUser);
	}

	/**
	* Returns the resource user with the primary key or throws a {@link NoSuchResourceUserException} if it could not be found.
	*
	* @param resourceUserId the primary key of the resource user
	* @return the resource user
	* @throws NoSuchResourceUserException if a resource user with the primary key could not be found
	*/
	public static ResourceUser findByPrimaryKey(long resourceUserId)
		throws org.opencps.usermgt.exception.NoSuchResourceUserException {
		return getPersistence().findByPrimaryKey(resourceUserId);
	}

	/**
	* Returns the resource user with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param resourceUserId the primary key of the resource user
	* @return the resource user, or <code>null</code> if a resource user with the primary key could not be found
	*/
	public static ResourceUser fetchByPrimaryKey(long resourceUserId) {
		return getPersistence().fetchByPrimaryKey(resourceUserId);
	}

	public static java.util.Map<java.io.Serializable, ResourceUser> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the resource users.
	*
	* @return the resource users
	*/
	public static List<ResourceUser> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the resource users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of resource users
	* @param end the upper bound of the range of resource users (not inclusive)
	* @return the range of resource users
	*/
	public static List<ResourceUser> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the resource users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of resource users
	* @param end the upper bound of the range of resource users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of resource users
	*/
	public static List<ResourceUser> findAll(int start, int end,
		OrderByComparator<ResourceUser> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the resource users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of resource users
	* @param end the upper bound of the range of resource users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of resource users
	*/
	public static List<ResourceUser> findAll(int start, int end,
		OrderByComparator<ResourceUser> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the resource users from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of resource users.
	*
	* @return the number of resource users
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ResourceUserPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ResourceUserPersistence, ResourceUserPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ResourceUserPersistence.class);

		ServiceTracker<ResourceUserPersistence, ResourceUserPersistence> serviceTracker =
			new ServiceTracker<ResourceUserPersistence, ResourceUserPersistence>(bundle.getBundleContext(),
				ResourceUserPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}