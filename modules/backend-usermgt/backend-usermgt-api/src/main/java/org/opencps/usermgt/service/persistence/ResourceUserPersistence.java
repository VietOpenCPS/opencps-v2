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

import org.opencps.usermgt.exception.NoSuchResourceUserException;
import org.opencps.usermgt.model.ResourceUser;

/**
 * The persistence interface for the resource user service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see org.opencps.usermgt.service.persistence.impl.ResourceUserPersistenceImpl
 * @see ResourceUserUtil
 * @generated
 */
@ProviderType
public interface ResourceUserPersistence extends BasePersistence<ResourceUser> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ResourceUserUtil} to access the resource user persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the resource users where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching resource users
	*/
	public java.util.List<ResourceUser> findByUuid(String uuid);

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
	public java.util.List<ResourceUser> findByUuid(String uuid, int start,
		int end);

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
	public java.util.List<ResourceUser> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ResourceUser> orderByComparator);

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
	public java.util.List<ResourceUser> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ResourceUser> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first resource user in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching resource user
	* @throws NoSuchResourceUserException if a matching resource user could not be found
	*/
	public ResourceUser findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ResourceUser> orderByComparator)
		throws NoSuchResourceUserException;

	/**
	* Returns the first resource user in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching resource user, or <code>null</code> if a matching resource user could not be found
	*/
	public ResourceUser fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ResourceUser> orderByComparator);

	/**
	* Returns the last resource user in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching resource user
	* @throws NoSuchResourceUserException if a matching resource user could not be found
	*/
	public ResourceUser findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ResourceUser> orderByComparator)
		throws NoSuchResourceUserException;

	/**
	* Returns the last resource user in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching resource user, or <code>null</code> if a matching resource user could not be found
	*/
	public ResourceUser fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ResourceUser> orderByComparator);

	/**
	* Returns the resource users before and after the current resource user in the ordered set where uuid = &#63;.
	*
	* @param resourceUserId the primary key of the current resource user
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next resource user
	* @throws NoSuchResourceUserException if a resource user with the primary key could not be found
	*/
	public ResourceUser[] findByUuid_PrevAndNext(long resourceUserId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ResourceUser> orderByComparator)
		throws NoSuchResourceUserException;

	/**
	* Removes all the resource users where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of resource users where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching resource users
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the resource user where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchResourceUserException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching resource user
	* @throws NoSuchResourceUserException if a matching resource user could not be found
	*/
	public ResourceUser findByUUID_G(String uuid, long groupId)
		throws NoSuchResourceUserException;

	/**
	* Returns the resource user where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching resource user, or <code>null</code> if a matching resource user could not be found
	*/
	public ResourceUser fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the resource user where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching resource user, or <code>null</code> if a matching resource user could not be found
	*/
	public ResourceUser fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the resource user where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the resource user that was removed
	*/
	public ResourceUser removeByUUID_G(String uuid, long groupId)
		throws NoSuchResourceUserException;

	/**
	* Returns the number of resource users where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching resource users
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the resource users where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching resource users
	*/
	public java.util.List<ResourceUser> findByUuid_C(String uuid, long companyId);

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
	public java.util.List<ResourceUser> findByUuid_C(String uuid,
		long companyId, int start, int end);

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
	public java.util.List<ResourceUser> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ResourceUser> orderByComparator);

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
	public java.util.List<ResourceUser> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ResourceUser> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first resource user in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching resource user
	* @throws NoSuchResourceUserException if a matching resource user could not be found
	*/
	public ResourceUser findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ResourceUser> orderByComparator)
		throws NoSuchResourceUserException;

	/**
	* Returns the first resource user in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching resource user, or <code>null</code> if a matching resource user could not be found
	*/
	public ResourceUser fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ResourceUser> orderByComparator);

	/**
	* Returns the last resource user in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching resource user
	* @throws NoSuchResourceUserException if a matching resource user could not be found
	*/
	public ResourceUser findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ResourceUser> orderByComparator)
		throws NoSuchResourceUserException;

	/**
	* Returns the last resource user in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching resource user, or <code>null</code> if a matching resource user could not be found
	*/
	public ResourceUser fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ResourceUser> orderByComparator);

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
	public ResourceUser[] findByUuid_C_PrevAndNext(long resourceUserId,
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ResourceUser> orderByComparator)
		throws NoSuchResourceUserException;

	/**
	* Removes all the resource users where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of resource users where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching resource users
	*/
	public int countByUuid_C(String uuid, long companyId);

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
	public ResourceUser findByF_className_classPK_toUserId(long groupId,
		String className, String classPK, long toUserId)
		throws NoSuchResourceUserException;

	/**
	* Returns the resource user where groupId = &#63; and className = &#63; and classPK = &#63; and toUserId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param toUserId the to user ID
	* @return the matching resource user, or <code>null</code> if a matching resource user could not be found
	*/
	public ResourceUser fetchByF_className_classPK_toUserId(long groupId,
		String className, String classPK, long toUserId);

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
	public ResourceUser fetchByF_className_classPK_toUserId(long groupId,
		String className, String classPK, long toUserId,
		boolean retrieveFromCache);

	/**
	* Removes the resource user where groupId = &#63; and className = &#63; and classPK = &#63; and toUserId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param toUserId the to user ID
	* @return the resource user that was removed
	*/
	public ResourceUser removeByF_className_classPK_toUserId(long groupId,
		String className, String classPK, long toUserId)
		throws NoSuchResourceUserException;

	/**
	* Returns the number of resource users where groupId = &#63; and className = &#63; and classPK = &#63; and toUserId = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param toUserId the to user ID
	* @return the number of matching resource users
	*/
	public int countByF_className_classPK_toUserId(long groupId,
		String className, String classPK, long toUserId);

	/**
	* Returns all the resource users where groupId = &#63; and className = &#63; and classPK = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @return the matching resource users
	*/
	public java.util.List<ResourceUser> findByF_className_classPK(
		long groupId, String className, String classPK);

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
	public java.util.List<ResourceUser> findByF_className_classPK(
		long groupId, String className, String classPK, int start, int end);

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
	public java.util.List<ResourceUser> findByF_className_classPK(
		long groupId, String className, String classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ResourceUser> orderByComparator);

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
	public java.util.List<ResourceUser> findByF_className_classPK(
		long groupId, String className, String classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ResourceUser> orderByComparator,
		boolean retrieveFromCache);

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
	public ResourceUser findByF_className_classPK_First(long groupId,
		String className, String classPK,
		com.liferay.portal.kernel.util.OrderByComparator<ResourceUser> orderByComparator)
		throws NoSuchResourceUserException;

	/**
	* Returns the first resource user in the ordered set where groupId = &#63; and className = &#63; and classPK = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching resource user, or <code>null</code> if a matching resource user could not be found
	*/
	public ResourceUser fetchByF_className_classPK_First(long groupId,
		String className, String classPK,
		com.liferay.portal.kernel.util.OrderByComparator<ResourceUser> orderByComparator);

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
	public ResourceUser findByF_className_classPK_Last(long groupId,
		String className, String classPK,
		com.liferay.portal.kernel.util.OrderByComparator<ResourceUser> orderByComparator)
		throws NoSuchResourceUserException;

	/**
	* Returns the last resource user in the ordered set where groupId = &#63; and className = &#63; and classPK = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching resource user, or <code>null</code> if a matching resource user could not be found
	*/
	public ResourceUser fetchByF_className_classPK_Last(long groupId,
		String className, String classPK,
		com.liferay.portal.kernel.util.OrderByComparator<ResourceUser> orderByComparator);

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
	public ResourceUser[] findByF_className_classPK_PrevAndNext(
		long resourceUserId, long groupId, String className, String classPK,
		com.liferay.portal.kernel.util.OrderByComparator<ResourceUser> orderByComparator)
		throws NoSuchResourceUserException;

	/**
	* Removes all the resource users where groupId = &#63; and className = &#63; and classPK = &#63; from the database.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	*/
	public void removeByF_className_classPK(long groupId, String className,
		String classPK);

	/**
	* Returns the number of resource users where groupId = &#63; and className = &#63; and classPK = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param classPK the class pk
	* @return the number of matching resource users
	*/
	public int countByF_className_classPK(long groupId, String className,
		String classPK);

	/**
	* Caches the resource user in the entity cache if it is enabled.
	*
	* @param resourceUser the resource user
	*/
	public void cacheResult(ResourceUser resourceUser);

	/**
	* Caches the resource users in the entity cache if it is enabled.
	*
	* @param resourceUsers the resource users
	*/
	public void cacheResult(java.util.List<ResourceUser> resourceUsers);

	/**
	* Creates a new resource user with the primary key. Does not add the resource user to the database.
	*
	* @param resourceUserId the primary key for the new resource user
	* @return the new resource user
	*/
	public ResourceUser create(long resourceUserId);

	/**
	* Removes the resource user with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param resourceUserId the primary key of the resource user
	* @return the resource user that was removed
	* @throws NoSuchResourceUserException if a resource user with the primary key could not be found
	*/
	public ResourceUser remove(long resourceUserId)
		throws NoSuchResourceUserException;

	public ResourceUser updateImpl(ResourceUser resourceUser);

	/**
	* Returns the resource user with the primary key or throws a {@link NoSuchResourceUserException} if it could not be found.
	*
	* @param resourceUserId the primary key of the resource user
	* @return the resource user
	* @throws NoSuchResourceUserException if a resource user with the primary key could not be found
	*/
	public ResourceUser findByPrimaryKey(long resourceUserId)
		throws NoSuchResourceUserException;

	/**
	* Returns the resource user with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param resourceUserId the primary key of the resource user
	* @return the resource user, or <code>null</code> if a resource user with the primary key could not be found
	*/
	public ResourceUser fetchByPrimaryKey(long resourceUserId);

	@Override
	public java.util.Map<java.io.Serializable, ResourceUser> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the resource users.
	*
	* @return the resource users
	*/
	public java.util.List<ResourceUser> findAll();

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
	public java.util.List<ResourceUser> findAll(int start, int end);

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
	public java.util.List<ResourceUser> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ResourceUser> orderByComparator);

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
	public java.util.List<ResourceUser> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ResourceUser> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the resource users from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of resource users.
	*
	* @return the number of resource users
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}