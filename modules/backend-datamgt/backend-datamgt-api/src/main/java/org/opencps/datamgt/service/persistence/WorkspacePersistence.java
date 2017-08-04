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

import org.opencps.datamgt.exception.NoSuchWorkspaceException;
import org.opencps.datamgt.model.Workspace;

/**
 * The persistence interface for the workspace service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Binhth
 * @see org.mobilink.backend.datamgt.service.persistence.impl.WorkspacePersistenceImpl
 * @see WorkspaceUtil
 * @generated
 */
@ProviderType
public interface WorkspacePersistence extends BasePersistence<Workspace> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WorkspaceUtil} to access the workspace persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the workspaces where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching workspaces
	*/
	public java.util.List<Workspace> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the workspaces where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of workspaces
	* @param end the upper bound of the range of workspaces (not inclusive)
	* @return the range of matching workspaces
	*/
	public java.util.List<Workspace> findByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the workspaces where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of workspaces
	* @param end the upper bound of the range of workspaces (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching workspaces
	*/
	public java.util.List<Workspace> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Workspace> orderByComparator);

	/**
	* Returns an ordered range of all the workspaces where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of workspaces
	* @param end the upper bound of the range of workspaces (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching workspaces
	*/
	public java.util.List<Workspace> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Workspace> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first workspace in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching workspace
	* @throws NoSuchWorkspaceException if a matching workspace could not be found
	*/
	public Workspace findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Workspace> orderByComparator)
		throws NoSuchWorkspaceException;

	/**
	* Returns the first workspace in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching workspace, or <code>null</code> if a matching workspace could not be found
	*/
	public Workspace fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Workspace> orderByComparator);

	/**
	* Returns the last workspace in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching workspace
	* @throws NoSuchWorkspaceException if a matching workspace could not be found
	*/
	public Workspace findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Workspace> orderByComparator)
		throws NoSuchWorkspaceException;

	/**
	* Returns the last workspace in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching workspace, or <code>null</code> if a matching workspace could not be found
	*/
	public Workspace fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Workspace> orderByComparator);

	/**
	* Returns the workspaces before and after the current workspace in the ordered set where uuid = &#63;.
	*
	* @param workspaceId the primary key of the current workspace
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next workspace
	* @throws NoSuchWorkspaceException if a workspace with the primary key could not be found
	*/
	public Workspace[] findByUuid_PrevAndNext(long workspaceId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Workspace> orderByComparator)
		throws NoSuchWorkspaceException;

	/**
	* Removes all the workspaces where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of workspaces where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching workspaces
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the workspace where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchWorkspaceException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching workspace
	* @throws NoSuchWorkspaceException if a matching workspace could not be found
	*/
	public Workspace findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchWorkspaceException;

	/**
	* Returns the workspace where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching workspace, or <code>null</code> if a matching workspace could not be found
	*/
	public Workspace fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the workspace where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching workspace, or <code>null</code> if a matching workspace could not be found
	*/
	public Workspace fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the workspace where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the workspace that was removed
	*/
	public Workspace removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchWorkspaceException;

	/**
	* Returns the number of workspaces where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching workspaces
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the workspaces where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching workspaces
	*/
	public java.util.List<Workspace> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the workspaces where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of workspaces
	* @param end the upper bound of the range of workspaces (not inclusive)
	* @return the range of matching workspaces
	*/
	public java.util.List<Workspace> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the workspaces where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of workspaces
	* @param end the upper bound of the range of workspaces (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching workspaces
	*/
	public java.util.List<Workspace> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Workspace> orderByComparator);

	/**
	* Returns an ordered range of all the workspaces where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of workspaces
	* @param end the upper bound of the range of workspaces (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching workspaces
	*/
	public java.util.List<Workspace> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Workspace> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first workspace in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching workspace
	* @throws NoSuchWorkspaceException if a matching workspace could not be found
	*/
	public Workspace findByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Workspace> orderByComparator)
		throws NoSuchWorkspaceException;

	/**
	* Returns the first workspace in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching workspace, or <code>null</code> if a matching workspace could not be found
	*/
	public Workspace fetchByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Workspace> orderByComparator);

	/**
	* Returns the last workspace in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching workspace
	* @throws NoSuchWorkspaceException if a matching workspace could not be found
	*/
	public Workspace findByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Workspace> orderByComparator)
		throws NoSuchWorkspaceException;

	/**
	* Returns the last workspace in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching workspace, or <code>null</code> if a matching workspace could not be found
	*/
	public Workspace fetchByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Workspace> orderByComparator);

	/**
	* Returns the workspaces before and after the current workspace in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param workspaceId the primary key of the current workspace
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next workspace
	* @throws NoSuchWorkspaceException if a workspace with the primary key could not be found
	*/
	public Workspace[] findByUuid_C_PrevAndNext(long workspaceId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Workspace> orderByComparator)
		throws NoSuchWorkspaceException;

	/**
	* Removes all the workspaces where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of workspaces where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching workspaces
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Caches the workspace in the entity cache if it is enabled.
	*
	* @param workspace the workspace
	*/
	public void cacheResult(Workspace workspace);

	/**
	* Caches the workspaces in the entity cache if it is enabled.
	*
	* @param workspaces the workspaces
	*/
	public void cacheResult(java.util.List<Workspace> workspaces);

	/**
	* Creates a new workspace with the primary key. Does not add the workspace to the database.
	*
	* @param workspaceId the primary key for the new workspace
	* @return the new workspace
	*/
	public Workspace create(long workspaceId);

	/**
	* Removes the workspace with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param workspaceId the primary key of the workspace
	* @return the workspace that was removed
	* @throws NoSuchWorkspaceException if a workspace with the primary key could not be found
	*/
	public Workspace remove(long workspaceId) throws NoSuchWorkspaceException;

	public Workspace updateImpl(Workspace workspace);

	/**
	* Returns the workspace with the primary key or throws a {@link NoSuchWorkspaceException} if it could not be found.
	*
	* @param workspaceId the primary key of the workspace
	* @return the workspace
	* @throws NoSuchWorkspaceException if a workspace with the primary key could not be found
	*/
	public Workspace findByPrimaryKey(long workspaceId)
		throws NoSuchWorkspaceException;

	/**
	* Returns the workspace with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param workspaceId the primary key of the workspace
	* @return the workspace, or <code>null</code> if a workspace with the primary key could not be found
	*/
	public Workspace fetchByPrimaryKey(long workspaceId);

	@Override
	public java.util.Map<java.io.Serializable, Workspace> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the workspaces.
	*
	* @return the workspaces
	*/
	public java.util.List<Workspace> findAll();

	/**
	* Returns a range of all the workspaces.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of workspaces
	* @param end the upper bound of the range of workspaces (not inclusive)
	* @return the range of workspaces
	*/
	public java.util.List<Workspace> findAll(int start, int end);

	/**
	* Returns an ordered range of all the workspaces.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of workspaces
	* @param end the upper bound of the range of workspaces (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of workspaces
	*/
	public java.util.List<Workspace> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Workspace> orderByComparator);

	/**
	* Returns an ordered range of all the workspaces.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of workspaces
	* @param end the upper bound of the range of workspaces (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of workspaces
	*/
	public java.util.List<Workspace> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Workspace> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the workspaces from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of workspaces.
	*
	* @return the number of workspaces
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}