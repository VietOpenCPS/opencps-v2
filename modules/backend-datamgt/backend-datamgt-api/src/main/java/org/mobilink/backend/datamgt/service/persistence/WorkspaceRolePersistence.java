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

package org.mobilink.backend.datamgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.mobilink.backend.datamgt.exception.NoSuchWorkspaceRoleException;
import org.mobilink.backend.datamgt.model.WorkspaceRole;

/**
 * The persistence interface for the workspace role service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Binhth
 * @see org.mobilink.backend.datamgt.service.persistence.impl.WorkspaceRolePersistenceImpl
 * @see WorkspaceRoleUtil
 * @generated
 */
@ProviderType
public interface WorkspaceRolePersistence extends BasePersistence<WorkspaceRole> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WorkspaceRoleUtil} to access the workspace role persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the workspace roles where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching workspace roles
	*/
	public java.util.List<WorkspaceRole> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the workspace roles where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of workspace roles
	* @param end the upper bound of the range of workspace roles (not inclusive)
	* @return the range of matching workspace roles
	*/
	public java.util.List<WorkspaceRole> findByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the workspace roles where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of workspace roles
	* @param end the upper bound of the range of workspace roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching workspace roles
	*/
	public java.util.List<WorkspaceRole> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WorkspaceRole> orderByComparator);

	/**
	* Returns an ordered range of all the workspace roles where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of workspace roles
	* @param end the upper bound of the range of workspace roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching workspace roles
	*/
	public java.util.List<WorkspaceRole> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WorkspaceRole> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first workspace role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching workspace role
	* @throws NoSuchWorkspaceRoleException if a matching workspace role could not be found
	*/
	public WorkspaceRole findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<WorkspaceRole> orderByComparator)
		throws NoSuchWorkspaceRoleException;

	/**
	* Returns the first workspace role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching workspace role, or <code>null</code> if a matching workspace role could not be found
	*/
	public WorkspaceRole fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<WorkspaceRole> orderByComparator);

	/**
	* Returns the last workspace role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching workspace role
	* @throws NoSuchWorkspaceRoleException if a matching workspace role could not be found
	*/
	public WorkspaceRole findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<WorkspaceRole> orderByComparator)
		throws NoSuchWorkspaceRoleException;

	/**
	* Returns the last workspace role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching workspace role, or <code>null</code> if a matching workspace role could not be found
	*/
	public WorkspaceRole fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<WorkspaceRole> orderByComparator);

	/**
	* Returns the workspace roles before and after the current workspace role in the ordered set where uuid = &#63;.
	*
	* @param workspaceRoleId the primary key of the current workspace role
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next workspace role
	* @throws NoSuchWorkspaceRoleException if a workspace role with the primary key could not be found
	*/
	public WorkspaceRole[] findByUuid_PrevAndNext(long workspaceRoleId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<WorkspaceRole> orderByComparator)
		throws NoSuchWorkspaceRoleException;

	/**
	* Removes all the workspace roles where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of workspace roles where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching workspace roles
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the workspace role where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchWorkspaceRoleException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching workspace role
	* @throws NoSuchWorkspaceRoleException if a matching workspace role could not be found
	*/
	public WorkspaceRole findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchWorkspaceRoleException;

	/**
	* Returns the workspace role where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching workspace role, or <code>null</code> if a matching workspace role could not be found
	*/
	public WorkspaceRole fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the workspace role where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching workspace role, or <code>null</code> if a matching workspace role could not be found
	*/
	public WorkspaceRole fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the workspace role where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the workspace role that was removed
	*/
	public WorkspaceRole removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchWorkspaceRoleException;

	/**
	* Returns the number of workspace roles where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching workspace roles
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the workspace roles where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching workspace roles
	*/
	public java.util.List<WorkspaceRole> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the workspace roles where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of workspace roles
	* @param end the upper bound of the range of workspace roles (not inclusive)
	* @return the range of matching workspace roles
	*/
	public java.util.List<WorkspaceRole> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the workspace roles where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of workspace roles
	* @param end the upper bound of the range of workspace roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching workspace roles
	*/
	public java.util.List<WorkspaceRole> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WorkspaceRole> orderByComparator);

	/**
	* Returns an ordered range of all the workspace roles where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of workspace roles
	* @param end the upper bound of the range of workspace roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching workspace roles
	*/
	public java.util.List<WorkspaceRole> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WorkspaceRole> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first workspace role in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching workspace role
	* @throws NoSuchWorkspaceRoleException if a matching workspace role could not be found
	*/
	public WorkspaceRole findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WorkspaceRole> orderByComparator)
		throws NoSuchWorkspaceRoleException;

	/**
	* Returns the first workspace role in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching workspace role, or <code>null</code> if a matching workspace role could not be found
	*/
	public WorkspaceRole fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WorkspaceRole> orderByComparator);

	/**
	* Returns the last workspace role in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching workspace role
	* @throws NoSuchWorkspaceRoleException if a matching workspace role could not be found
	*/
	public WorkspaceRole findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WorkspaceRole> orderByComparator)
		throws NoSuchWorkspaceRoleException;

	/**
	* Returns the last workspace role in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching workspace role, or <code>null</code> if a matching workspace role could not be found
	*/
	public WorkspaceRole fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WorkspaceRole> orderByComparator);

	/**
	* Returns the workspace roles before and after the current workspace role in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param workspaceRoleId the primary key of the current workspace role
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next workspace role
	* @throws NoSuchWorkspaceRoleException if a workspace role with the primary key could not be found
	*/
	public WorkspaceRole[] findByUuid_C_PrevAndNext(long workspaceRoleId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WorkspaceRole> orderByComparator)
		throws NoSuchWorkspaceRoleException;

	/**
	* Removes all the workspace roles where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of workspace roles where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching workspace roles
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Caches the workspace role in the entity cache if it is enabled.
	*
	* @param workspaceRole the workspace role
	*/
	public void cacheResult(WorkspaceRole workspaceRole);

	/**
	* Caches the workspace roles in the entity cache if it is enabled.
	*
	* @param workspaceRoles the workspace roles
	*/
	public void cacheResult(java.util.List<WorkspaceRole> workspaceRoles);

	/**
	* Creates a new workspace role with the primary key. Does not add the workspace role to the database.
	*
	* @param workspaceRoleId the primary key for the new workspace role
	* @return the new workspace role
	*/
	public WorkspaceRole create(long workspaceRoleId);

	/**
	* Removes the workspace role with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param workspaceRoleId the primary key of the workspace role
	* @return the workspace role that was removed
	* @throws NoSuchWorkspaceRoleException if a workspace role with the primary key could not be found
	*/
	public WorkspaceRole remove(long workspaceRoleId)
		throws NoSuchWorkspaceRoleException;

	public WorkspaceRole updateImpl(WorkspaceRole workspaceRole);

	/**
	* Returns the workspace role with the primary key or throws a {@link NoSuchWorkspaceRoleException} if it could not be found.
	*
	* @param workspaceRoleId the primary key of the workspace role
	* @return the workspace role
	* @throws NoSuchWorkspaceRoleException if a workspace role with the primary key could not be found
	*/
	public WorkspaceRole findByPrimaryKey(long workspaceRoleId)
		throws NoSuchWorkspaceRoleException;

	/**
	* Returns the workspace role with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param workspaceRoleId the primary key of the workspace role
	* @return the workspace role, or <code>null</code> if a workspace role with the primary key could not be found
	*/
	public WorkspaceRole fetchByPrimaryKey(long workspaceRoleId);

	@Override
	public java.util.Map<java.io.Serializable, WorkspaceRole> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the workspace roles.
	*
	* @return the workspace roles
	*/
	public java.util.List<WorkspaceRole> findAll();

	/**
	* Returns a range of all the workspace roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of workspace roles
	* @param end the upper bound of the range of workspace roles (not inclusive)
	* @return the range of workspace roles
	*/
	public java.util.List<WorkspaceRole> findAll(int start, int end);

	/**
	* Returns an ordered range of all the workspace roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of workspace roles
	* @param end the upper bound of the range of workspace roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of workspace roles
	*/
	public java.util.List<WorkspaceRole> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WorkspaceRole> orderByComparator);

	/**
	* Returns an ordered range of all the workspace roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of workspace roles
	* @param end the upper bound of the range of workspace roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of workspace roles
	*/
	public java.util.List<WorkspaceRole> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WorkspaceRole> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the workspace roles from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of workspace roles.
	*
	* @return the number of workspace roles
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}