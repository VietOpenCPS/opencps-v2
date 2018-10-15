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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.usermgt.exception.NoSuchResourceRoleException;
import org.opencps.usermgt.model.ResourceRole;

/**
 * The persistence interface for the resource role service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see org.opencps.usermgt.service.persistence.impl.ResourceRolePersistenceImpl
 * @see ResourceRoleUtil
 * @generated
 */
@ProviderType
public interface ResourceRolePersistence extends BasePersistence<ResourceRole> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ResourceRoleUtil} to access the resource role persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the resource roles where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching resource roles
	*/
	public java.util.List<ResourceRole> findByUuid(String uuid);

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
	public java.util.List<ResourceRole> findByUuid(String uuid, int start,
		int end);

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
	public java.util.List<ResourceRole> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ResourceRole> orderByComparator);

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
	public java.util.List<ResourceRole> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ResourceRole> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first resource role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching resource role
	* @throws NoSuchResourceRoleException if a matching resource role could not be found
	*/
	public ResourceRole findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ResourceRole> orderByComparator)
		throws NoSuchResourceRoleException;

	/**
	* Returns the first resource role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching resource role, or <code>null</code> if a matching resource role could not be found
	*/
	public ResourceRole fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ResourceRole> orderByComparator);

	/**
	* Returns the last resource role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching resource role
	* @throws NoSuchResourceRoleException if a matching resource role could not be found
	*/
	public ResourceRole findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ResourceRole> orderByComparator)
		throws NoSuchResourceRoleException;

	/**
	* Returns the last resource role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching resource role, or <code>null</code> if a matching resource role could not be found
	*/
	public ResourceRole fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ResourceRole> orderByComparator);

	/**
	* Returns the resource roles before and after the current resource role in the ordered set where uuid = &#63;.
	*
	* @param resourceRoleId the primary key of the current resource role
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next resource role
	* @throws NoSuchResourceRoleException if a resource role with the primary key could not be found
	*/
	public ResourceRole[] findByUuid_PrevAndNext(long resourceRoleId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ResourceRole> orderByComparator)
		throws NoSuchResourceRoleException;

	/**
	* Removes all the resource roles where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of resource roles where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching resource roles
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the resource role where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchResourceRoleException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching resource role
	* @throws NoSuchResourceRoleException if a matching resource role could not be found
	*/
	public ResourceRole findByUUID_G(String uuid, long groupId)
		throws NoSuchResourceRoleException;

	/**
	* Returns the resource role where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching resource role, or <code>null</code> if a matching resource role could not be found
	*/
	public ResourceRole fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the resource role where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching resource role, or <code>null</code> if a matching resource role could not be found
	*/
	public ResourceRole fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the resource role where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the resource role that was removed
	*/
	public ResourceRole removeByUUID_G(String uuid, long groupId)
		throws NoSuchResourceRoleException;

	/**
	* Returns the number of resource roles where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching resource roles
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the resource roles where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching resource roles
	*/
	public java.util.List<ResourceRole> findByUuid_C(String uuid, long companyId);

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
	public java.util.List<ResourceRole> findByUuid_C(String uuid,
		long companyId, int start, int end);

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
	public java.util.List<ResourceRole> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ResourceRole> orderByComparator);

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
	public java.util.List<ResourceRole> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ResourceRole> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first resource role in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching resource role
	* @throws NoSuchResourceRoleException if a matching resource role could not be found
	*/
	public ResourceRole findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ResourceRole> orderByComparator)
		throws NoSuchResourceRoleException;

	/**
	* Returns the first resource role in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching resource role, or <code>null</code> if a matching resource role could not be found
	*/
	public ResourceRole fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ResourceRole> orderByComparator);

	/**
	* Returns the last resource role in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching resource role
	* @throws NoSuchResourceRoleException if a matching resource role could not be found
	*/
	public ResourceRole findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ResourceRole> orderByComparator)
		throws NoSuchResourceRoleException;

	/**
	* Returns the last resource role in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching resource role, or <code>null</code> if a matching resource role could not be found
	*/
	public ResourceRole fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ResourceRole> orderByComparator);

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
	public ResourceRole[] findByUuid_C_PrevAndNext(long resourceRoleId,
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ResourceRole> orderByComparator)
		throws NoSuchResourceRoleException;

	/**
	* Removes all the resource roles where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of resource roles where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching resource roles
	*/
	public int countByUuid_C(String uuid, long companyId);

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
	public ResourceRole findByF_className_classPK_roleId(long groupId,
		String className, String classPK, long roleId)
		throws NoSuchResourceRoleException;

	/**
	* Returns the resource role where groupId = &#63; and className = &#63; and classPK = &#63; and roleId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param roleId the role ID
	* @return the matching resource role, or <code>null</code> if a matching resource role could not be found
	*/
	public ResourceRole fetchByF_className_classPK_roleId(long groupId,
		String className, String classPK, long roleId);

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
	public ResourceRole fetchByF_className_classPK_roleId(long groupId,
		String className, String classPK, long roleId, boolean retrieveFromCache);

	/**
	* Removes the resource role where groupId = &#63; and className = &#63; and classPK = &#63; and roleId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param roleId the role ID
	* @return the resource role that was removed
	*/
	public ResourceRole removeByF_className_classPK_roleId(long groupId,
		String className, String classPK, long roleId)
		throws NoSuchResourceRoleException;

	/**
	* Returns the number of resource roles where groupId = &#63; and className = &#63; and classPK = &#63; and roleId = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param roleId the role ID
	* @return the number of matching resource roles
	*/
	public int countByF_className_classPK_roleId(long groupId,
		String className, String classPK, long roleId);

	/**
	* Returns all the resource roles where groupId = &#63; and className = &#63; and classPK = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @return the matching resource roles
	*/
	public java.util.List<ResourceRole> findByF_className_classPK(
		long groupId, String className, String classPK);

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
	public java.util.List<ResourceRole> findByF_className_classPK(
		long groupId, String className, String classPK, int start, int end);

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
	public java.util.List<ResourceRole> findByF_className_classPK(
		long groupId, String className, String classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ResourceRole> orderByComparator);

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
	public java.util.List<ResourceRole> findByF_className_classPK(
		long groupId, String className, String classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ResourceRole> orderByComparator,
		boolean retrieveFromCache);

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
	public ResourceRole findByF_className_classPK_First(long groupId,
		String className, String classPK,
		com.liferay.portal.kernel.util.OrderByComparator<ResourceRole> orderByComparator)
		throws NoSuchResourceRoleException;

	/**
	* Returns the first resource role in the ordered set where groupId = &#63; and className = &#63; and classPK = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching resource role, or <code>null</code> if a matching resource role could not be found
	*/
	public ResourceRole fetchByF_className_classPK_First(long groupId,
		String className, String classPK,
		com.liferay.portal.kernel.util.OrderByComparator<ResourceRole> orderByComparator);

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
	public ResourceRole findByF_className_classPK_Last(long groupId,
		String className, String classPK,
		com.liferay.portal.kernel.util.OrderByComparator<ResourceRole> orderByComparator)
		throws NoSuchResourceRoleException;

	/**
	* Returns the last resource role in the ordered set where groupId = &#63; and className = &#63; and classPK = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching resource role, or <code>null</code> if a matching resource role could not be found
	*/
	public ResourceRole fetchByF_className_classPK_Last(long groupId,
		String className, String classPK,
		com.liferay.portal.kernel.util.OrderByComparator<ResourceRole> orderByComparator);

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
	public ResourceRole[] findByF_className_classPK_PrevAndNext(
		long resourceRoleId, long groupId, String className, String classPK,
		com.liferay.portal.kernel.util.OrderByComparator<ResourceRole> orderByComparator)
		throws NoSuchResourceRoleException;

	/**
	* Removes all the resource roles where groupId = &#63; and className = &#63; and classPK = &#63; from the database.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	*/
	public void removeByF_className_classPK(long groupId, String className,
		String classPK);

	/**
	* Returns the number of resource roles where groupId = &#63; and className = &#63; and classPK = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @return the number of matching resource roles
	*/
	public int countByF_className_classPK(long groupId, String className,
		String classPK);

	/**
	* Caches the resource role in the entity cache if it is enabled.
	*
	* @param resourceRole the resource role
	*/
	public void cacheResult(ResourceRole resourceRole);

	/**
	* Caches the resource roles in the entity cache if it is enabled.
	*
	* @param resourceRoles the resource roles
	*/
	public void cacheResult(java.util.List<ResourceRole> resourceRoles);

	/**
	* Creates a new resource role with the primary key. Does not add the resource role to the database.
	*
	* @param resourceRoleId the primary key for the new resource role
	* @return the new resource role
	*/
	public ResourceRole create(long resourceRoleId);

	/**
	* Removes the resource role with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param resourceRoleId the primary key of the resource role
	* @return the resource role that was removed
	* @throws NoSuchResourceRoleException if a resource role with the primary key could not be found
	*/
	public ResourceRole remove(long resourceRoleId)
		throws NoSuchResourceRoleException;

	public ResourceRole updateImpl(ResourceRole resourceRole);

	/**
	* Returns the resource role with the primary key or throws a {@link NoSuchResourceRoleException} if it could not be found.
	*
	* @param resourceRoleId the primary key of the resource role
	* @return the resource role
	* @throws NoSuchResourceRoleException if a resource role with the primary key could not be found
	*/
	public ResourceRole findByPrimaryKey(long resourceRoleId)
		throws NoSuchResourceRoleException;

	/**
	* Returns the resource role with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param resourceRoleId the primary key of the resource role
	* @return the resource role, or <code>null</code> if a resource role with the primary key could not be found
	*/
	public ResourceRole fetchByPrimaryKey(long resourceRoleId);

	@Override
	public java.util.Map<java.io.Serializable, ResourceRole> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the resource roles.
	*
	* @return the resource roles
	*/
	public java.util.List<ResourceRole> findAll();

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
	public java.util.List<ResourceRole> findAll(int start, int end);

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
	public java.util.List<ResourceRole> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ResourceRole> orderByComparator);

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
	public java.util.List<ResourceRole> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ResourceRole> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the resource roles from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of resource roles.
	*
	* @return the number of resource roles
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}