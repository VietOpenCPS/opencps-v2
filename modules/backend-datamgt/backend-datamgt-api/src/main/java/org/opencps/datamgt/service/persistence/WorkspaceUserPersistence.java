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

package org.opencps.datamgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.datamgt.exception.NoSuchWorkspaceUserException;
import org.opencps.datamgt.model.WorkspaceUser;

/**
 * The persistence interface for the workspace user service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Binhth
 * @see org.mobilink.backend.datamgt.service.persistence.impl.WorkspaceUserPersistenceImpl
 * @see WorkspaceUserUtil
 * @generated
 */
@ProviderType
public interface WorkspaceUserPersistence extends BasePersistence<WorkspaceUser> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WorkspaceUserUtil} to access the workspace user persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the workspace users where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching workspace users
	*/
	public java.util.List<WorkspaceUser> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the workspace users where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of workspace users
	* @param end the upper bound of the range of workspace users (not inclusive)
	* @return the range of matching workspace users
	*/
	public java.util.List<WorkspaceUser> findByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the workspace users where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of workspace users
	* @param end the upper bound of the range of workspace users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching workspace users
	*/
	public java.util.List<WorkspaceUser> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WorkspaceUser> orderByComparator);

	/**
	* Returns an ordered range of all the workspace users where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of workspace users
	* @param end the upper bound of the range of workspace users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching workspace users
	*/
	public java.util.List<WorkspaceUser> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WorkspaceUser> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first workspace user in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching workspace user
	* @throws NoSuchWorkspaceUserException if a matching workspace user could not be found
	*/
	public WorkspaceUser findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<WorkspaceUser> orderByComparator)
		throws NoSuchWorkspaceUserException;

	/**
	* Returns the first workspace user in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching workspace user, or <code>null</code> if a matching workspace user could not be found
	*/
	public WorkspaceUser fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<WorkspaceUser> orderByComparator);

	/**
	* Returns the last workspace user in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching workspace user
	* @throws NoSuchWorkspaceUserException if a matching workspace user could not be found
	*/
	public WorkspaceUser findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<WorkspaceUser> orderByComparator)
		throws NoSuchWorkspaceUserException;

	/**
	* Returns the last workspace user in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching workspace user, or <code>null</code> if a matching workspace user could not be found
	*/
	public WorkspaceUser fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<WorkspaceUser> orderByComparator);

	/**
	* Returns the workspace users before and after the current workspace user in the ordered set where uuid = &#63;.
	*
	* @param workspaceUserId the primary key of the current workspace user
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next workspace user
	* @throws NoSuchWorkspaceUserException if a workspace user with the primary key could not be found
	*/
	public WorkspaceUser[] findByUuid_PrevAndNext(long workspaceUserId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<WorkspaceUser> orderByComparator)
		throws NoSuchWorkspaceUserException;

	/**
	* Removes all the workspace users where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of workspace users where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching workspace users
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the workspace user where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchWorkspaceUserException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching workspace user
	* @throws NoSuchWorkspaceUserException if a matching workspace user could not be found
	*/
	public WorkspaceUser findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchWorkspaceUserException;

	/**
	* Returns the workspace user where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching workspace user, or <code>null</code> if a matching workspace user could not be found
	*/
	public WorkspaceUser fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the workspace user where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching workspace user, or <code>null</code> if a matching workspace user could not be found
	*/
	public WorkspaceUser fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the workspace user where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the workspace user that was removed
	*/
	public WorkspaceUser removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchWorkspaceUserException;

	/**
	* Returns the number of workspace users where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching workspace users
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the workspace users where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching workspace users
	*/
	public java.util.List<WorkspaceUser> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the workspace users where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of workspace users
	* @param end the upper bound of the range of workspace users (not inclusive)
	* @return the range of matching workspace users
	*/
	public java.util.List<WorkspaceUser> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the workspace users where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of workspace users
	* @param end the upper bound of the range of workspace users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching workspace users
	*/
	public java.util.List<WorkspaceUser> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WorkspaceUser> orderByComparator);

	/**
	* Returns an ordered range of all the workspace users where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of workspace users
	* @param end the upper bound of the range of workspace users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching workspace users
	*/
	public java.util.List<WorkspaceUser> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WorkspaceUser> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first workspace user in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching workspace user
	* @throws NoSuchWorkspaceUserException if a matching workspace user could not be found
	*/
	public WorkspaceUser findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WorkspaceUser> orderByComparator)
		throws NoSuchWorkspaceUserException;

	/**
	* Returns the first workspace user in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching workspace user, or <code>null</code> if a matching workspace user could not be found
	*/
	public WorkspaceUser fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WorkspaceUser> orderByComparator);

	/**
	* Returns the last workspace user in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching workspace user
	* @throws NoSuchWorkspaceUserException if a matching workspace user could not be found
	*/
	public WorkspaceUser findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WorkspaceUser> orderByComparator)
		throws NoSuchWorkspaceUserException;

	/**
	* Returns the last workspace user in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching workspace user, or <code>null</code> if a matching workspace user could not be found
	*/
	public WorkspaceUser fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WorkspaceUser> orderByComparator);

	/**
	* Returns the workspace users before and after the current workspace user in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param workspaceUserId the primary key of the current workspace user
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next workspace user
	* @throws NoSuchWorkspaceUserException if a workspace user with the primary key could not be found
	*/
	public WorkspaceUser[] findByUuid_C_PrevAndNext(long workspaceUserId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WorkspaceUser> orderByComparator)
		throws NoSuchWorkspaceUserException;

	/**
	* Removes all the workspace users where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of workspace users where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching workspace users
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the workspace users where workspaceId = &#63;.
	*
	* @param workspaceId the workspace ID
	* @return the matching workspace users
	*/
	public java.util.List<WorkspaceUser> findByF_workspaceId(long workspaceId);

	/**
	* Returns a range of all the workspace users where workspaceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param workspaceId the workspace ID
	* @param start the lower bound of the range of workspace users
	* @param end the upper bound of the range of workspace users (not inclusive)
	* @return the range of matching workspace users
	*/
	public java.util.List<WorkspaceUser> findByF_workspaceId(long workspaceId,
		int start, int end);

	/**
	* Returns an ordered range of all the workspace users where workspaceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param workspaceId the workspace ID
	* @param start the lower bound of the range of workspace users
	* @param end the upper bound of the range of workspace users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching workspace users
	*/
	public java.util.List<WorkspaceUser> findByF_workspaceId(long workspaceId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WorkspaceUser> orderByComparator);

	/**
	* Returns an ordered range of all the workspace users where workspaceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param workspaceId the workspace ID
	* @param start the lower bound of the range of workspace users
	* @param end the upper bound of the range of workspace users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching workspace users
	*/
	public java.util.List<WorkspaceUser> findByF_workspaceId(long workspaceId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WorkspaceUser> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first workspace user in the ordered set where workspaceId = &#63;.
	*
	* @param workspaceId the workspace ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching workspace user
	* @throws NoSuchWorkspaceUserException if a matching workspace user could not be found
	*/
	public WorkspaceUser findByF_workspaceId_First(long workspaceId,
		com.liferay.portal.kernel.util.OrderByComparator<WorkspaceUser> orderByComparator)
		throws NoSuchWorkspaceUserException;

	/**
	* Returns the first workspace user in the ordered set where workspaceId = &#63;.
	*
	* @param workspaceId the workspace ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching workspace user, or <code>null</code> if a matching workspace user could not be found
	*/
	public WorkspaceUser fetchByF_workspaceId_First(long workspaceId,
		com.liferay.portal.kernel.util.OrderByComparator<WorkspaceUser> orderByComparator);

	/**
	* Returns the last workspace user in the ordered set where workspaceId = &#63;.
	*
	* @param workspaceId the workspace ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching workspace user
	* @throws NoSuchWorkspaceUserException if a matching workspace user could not be found
	*/
	public WorkspaceUser findByF_workspaceId_Last(long workspaceId,
		com.liferay.portal.kernel.util.OrderByComparator<WorkspaceUser> orderByComparator)
		throws NoSuchWorkspaceUserException;

	/**
	* Returns the last workspace user in the ordered set where workspaceId = &#63;.
	*
	* @param workspaceId the workspace ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching workspace user, or <code>null</code> if a matching workspace user could not be found
	*/
	public WorkspaceUser fetchByF_workspaceId_Last(long workspaceId,
		com.liferay.portal.kernel.util.OrderByComparator<WorkspaceUser> orderByComparator);

	/**
	* Returns the workspace users before and after the current workspace user in the ordered set where workspaceId = &#63;.
	*
	* @param workspaceUserId the primary key of the current workspace user
	* @param workspaceId the workspace ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next workspace user
	* @throws NoSuchWorkspaceUserException if a workspace user with the primary key could not be found
	*/
	public WorkspaceUser[] findByF_workspaceId_PrevAndNext(
		long workspaceUserId, long workspaceId,
		com.liferay.portal.kernel.util.OrderByComparator<WorkspaceUser> orderByComparator)
		throws NoSuchWorkspaceUserException;

	/**
	* Removes all the workspace users where workspaceId = &#63; from the database.
	*
	* @param workspaceId the workspace ID
	*/
	public void removeByF_workspaceId(long workspaceId);

	/**
	* Returns the number of workspace users where workspaceId = &#63;.
	*
	* @param workspaceId the workspace ID
	* @return the number of matching workspace users
	*/
	public int countByF_workspaceId(long workspaceId);

	/**
	* Caches the workspace user in the entity cache if it is enabled.
	*
	* @param workspaceUser the workspace user
	*/
	public void cacheResult(WorkspaceUser workspaceUser);

	/**
	* Caches the workspace users in the entity cache if it is enabled.
	*
	* @param workspaceUsers the workspace users
	*/
	public void cacheResult(java.util.List<WorkspaceUser> workspaceUsers);

	/**
	* Creates a new workspace user with the primary key. Does not add the workspace user to the database.
	*
	* @param workspaceUserId the primary key for the new workspace user
	* @return the new workspace user
	*/
	public WorkspaceUser create(long workspaceUserId);

	/**
	* Removes the workspace user with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param workspaceUserId the primary key of the workspace user
	* @return the workspace user that was removed
	* @throws NoSuchWorkspaceUserException if a workspace user with the primary key could not be found
	*/
	public WorkspaceUser remove(long workspaceUserId)
		throws NoSuchWorkspaceUserException;

	public WorkspaceUser updateImpl(WorkspaceUser workspaceUser);

	/**
	* Returns the workspace user with the primary key or throws a {@link NoSuchWorkspaceUserException} if it could not be found.
	*
	* @param workspaceUserId the primary key of the workspace user
	* @return the workspace user
	* @throws NoSuchWorkspaceUserException if a workspace user with the primary key could not be found
	*/
	public WorkspaceUser findByPrimaryKey(long workspaceUserId)
		throws NoSuchWorkspaceUserException;

	/**
	* Returns the workspace user with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param workspaceUserId the primary key of the workspace user
	* @return the workspace user, or <code>null</code> if a workspace user with the primary key could not be found
	*/
	public WorkspaceUser fetchByPrimaryKey(long workspaceUserId);

	@Override
	public java.util.Map<java.io.Serializable, WorkspaceUser> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the workspace users.
	*
	* @return the workspace users
	*/
	public java.util.List<WorkspaceUser> findAll();

	/**
	* Returns a range of all the workspace users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of workspace users
	* @param end the upper bound of the range of workspace users (not inclusive)
	* @return the range of workspace users
	*/
	public java.util.List<WorkspaceUser> findAll(int start, int end);

	/**
	* Returns an ordered range of all the workspace users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of workspace users
	* @param end the upper bound of the range of workspace users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of workspace users
	*/
	public java.util.List<WorkspaceUser> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WorkspaceUser> orderByComparator);

	/**
	* Returns an ordered range of all the workspace users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of workspace users
	* @param end the upper bound of the range of workspace users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of workspace users
	*/
	public java.util.List<WorkspaceUser> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WorkspaceUser> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the workspace users from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of workspace users.
	*
	* @return the number of workspace users
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}