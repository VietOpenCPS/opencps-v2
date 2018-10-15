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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.dossiermgt.exception.NoSuchProcessStepRoleException;
import org.opencps.dossiermgt.model.ProcessStepRole;

/**
 * The persistence interface for the process step role service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.dossiermgt.service.persistence.impl.ProcessStepRolePersistenceImpl
 * @see ProcessStepRoleUtil
 * @generated
 */
@ProviderType
public interface ProcessStepRolePersistence extends BasePersistence<ProcessStepRole> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ProcessStepRoleUtil} to access the process step role persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the process step roles where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching process step roles
	*/
	public java.util.List<ProcessStepRole> findByUuid(String uuid);

	/**
	* Returns a range of all the process step roles where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of process step roles
	* @param end the upper bound of the range of process step roles (not inclusive)
	* @return the range of matching process step roles
	*/
	public java.util.List<ProcessStepRole> findByUuid(String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the process step roles where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of process step roles
	* @param end the upper bound of the range of process step roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching process step roles
	*/
	public java.util.List<ProcessStepRole> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessStepRole> orderByComparator);

	/**
	* Returns an ordered range of all the process step roles where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of process step roles
	* @param end the upper bound of the range of process step roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching process step roles
	*/
	public java.util.List<ProcessStepRole> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessStepRole> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first process step role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process step role
	* @throws NoSuchProcessStepRoleException if a matching process step role could not be found
	*/
	public ProcessStepRole findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessStepRole> orderByComparator)
		throws NoSuchProcessStepRoleException;

	/**
	* Returns the first process step role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process step role, or <code>null</code> if a matching process step role could not be found
	*/
	public ProcessStepRole fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessStepRole> orderByComparator);

	/**
	* Returns the last process step role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process step role
	* @throws NoSuchProcessStepRoleException if a matching process step role could not be found
	*/
	public ProcessStepRole findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessStepRole> orderByComparator)
		throws NoSuchProcessStepRoleException;

	/**
	* Returns the last process step role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process step role, or <code>null</code> if a matching process step role could not be found
	*/
	public ProcessStepRole fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessStepRole> orderByComparator);

	/**
	* Returns the process step roles before and after the current process step role in the ordered set where uuid = &#63;.
	*
	* @param processStepRolePK the primary key of the current process step role
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process step role
	* @throws NoSuchProcessStepRoleException if a process step role with the primary key could not be found
	*/
	public ProcessStepRole[] findByUuid_PrevAndNext(
		ProcessStepRolePK processStepRolePK, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessStepRole> orderByComparator)
		throws NoSuchProcessStepRoleException;

	/**
	* Removes all the process step roles where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of process step roles where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching process step roles
	*/
	public int countByUuid(String uuid);

	/**
	* Returns all the process step roles where processStepId = &#63;.
	*
	* @param processStepId the process step ID
	* @return the matching process step roles
	*/
	public java.util.List<ProcessStepRole> findByP_S_ID(long processStepId);

	/**
	* Returns a range of all the process step roles where processStepId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param processStepId the process step ID
	* @param start the lower bound of the range of process step roles
	* @param end the upper bound of the range of process step roles (not inclusive)
	* @return the range of matching process step roles
	*/
	public java.util.List<ProcessStepRole> findByP_S_ID(long processStepId,
		int start, int end);

	/**
	* Returns an ordered range of all the process step roles where processStepId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param processStepId the process step ID
	* @param start the lower bound of the range of process step roles
	* @param end the upper bound of the range of process step roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching process step roles
	*/
	public java.util.List<ProcessStepRole> findByP_S_ID(long processStepId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessStepRole> orderByComparator);

	/**
	* Returns an ordered range of all the process step roles where processStepId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param processStepId the process step ID
	* @param start the lower bound of the range of process step roles
	* @param end the upper bound of the range of process step roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching process step roles
	*/
	public java.util.List<ProcessStepRole> findByP_S_ID(long processStepId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessStepRole> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first process step role in the ordered set where processStepId = &#63;.
	*
	* @param processStepId the process step ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process step role
	* @throws NoSuchProcessStepRoleException if a matching process step role could not be found
	*/
	public ProcessStepRole findByP_S_ID_First(long processStepId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessStepRole> orderByComparator)
		throws NoSuchProcessStepRoleException;

	/**
	* Returns the first process step role in the ordered set where processStepId = &#63;.
	*
	* @param processStepId the process step ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process step role, or <code>null</code> if a matching process step role could not be found
	*/
	public ProcessStepRole fetchByP_S_ID_First(long processStepId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessStepRole> orderByComparator);

	/**
	* Returns the last process step role in the ordered set where processStepId = &#63;.
	*
	* @param processStepId the process step ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process step role
	* @throws NoSuchProcessStepRoleException if a matching process step role could not be found
	*/
	public ProcessStepRole findByP_S_ID_Last(long processStepId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessStepRole> orderByComparator)
		throws NoSuchProcessStepRoleException;

	/**
	* Returns the last process step role in the ordered set where processStepId = &#63;.
	*
	* @param processStepId the process step ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process step role, or <code>null</code> if a matching process step role could not be found
	*/
	public ProcessStepRole fetchByP_S_ID_Last(long processStepId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessStepRole> orderByComparator);

	/**
	* Returns the process step roles before and after the current process step role in the ordered set where processStepId = &#63;.
	*
	* @param processStepRolePK the primary key of the current process step role
	* @param processStepId the process step ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process step role
	* @throws NoSuchProcessStepRoleException if a process step role with the primary key could not be found
	*/
	public ProcessStepRole[] findByP_S_ID_PrevAndNext(
		ProcessStepRolePK processStepRolePK, long processStepId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessStepRole> orderByComparator)
		throws NoSuchProcessStepRoleException;

	/**
	* Removes all the process step roles where processStepId = &#63; from the database.
	*
	* @param processStepId the process step ID
	*/
	public void removeByP_S_ID(long processStepId);

	/**
	* Returns the number of process step roles where processStepId = &#63;.
	*
	* @param processStepId the process step ID
	* @return the number of matching process step roles
	*/
	public int countByP_S_ID(long processStepId);

	/**
	* Returns the process step role where processStepId = &#63; and roleId = &#63; or throws a {@link NoSuchProcessStepRoleException} if it could not be found.
	*
	* @param processStepId the process step ID
	* @param roleId the role ID
	* @return the matching process step role
	* @throws NoSuchProcessStepRoleException if a matching process step role could not be found
	*/
	public ProcessStepRole findByF_STEP_ROLE(long processStepId, long roleId)
		throws NoSuchProcessStepRoleException;

	/**
	* Returns the process step role where processStepId = &#63; and roleId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param processStepId the process step ID
	* @param roleId the role ID
	* @return the matching process step role, or <code>null</code> if a matching process step role could not be found
	*/
	public ProcessStepRole fetchByF_STEP_ROLE(long processStepId, long roleId);

	/**
	* Returns the process step role where processStepId = &#63; and roleId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param processStepId the process step ID
	* @param roleId the role ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching process step role, or <code>null</code> if a matching process step role could not be found
	*/
	public ProcessStepRole fetchByF_STEP_ROLE(long processStepId, long roleId,
		boolean retrieveFromCache);

	/**
	* Removes the process step role where processStepId = &#63; and roleId = &#63; from the database.
	*
	* @param processStepId the process step ID
	* @param roleId the role ID
	* @return the process step role that was removed
	*/
	public ProcessStepRole removeByF_STEP_ROLE(long processStepId, long roleId)
		throws NoSuchProcessStepRoleException;

	/**
	* Returns the number of process step roles where processStepId = &#63; and roleId = &#63;.
	*
	* @param processStepId the process step ID
	* @param roleId the role ID
	* @return the number of matching process step roles
	*/
	public int countByF_STEP_ROLE(long processStepId, long roleId);

	/**
	* Returns the process step role where roleCode = &#63; or throws a {@link NoSuchProcessStepRoleException} if it could not be found.
	*
	* @param roleCode the role code
	* @return the matching process step role
	* @throws NoSuchProcessStepRoleException if a matching process step role could not be found
	*/
	public ProcessStepRole findByF_CODE(String roleCode)
		throws NoSuchProcessStepRoleException;

	/**
	* Returns the process step role where roleCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param roleCode the role code
	* @return the matching process step role, or <code>null</code> if a matching process step role could not be found
	*/
	public ProcessStepRole fetchByF_CODE(String roleCode);

	/**
	* Returns the process step role where roleCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param roleCode the role code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching process step role, or <code>null</code> if a matching process step role could not be found
	*/
	public ProcessStepRole fetchByF_CODE(String roleCode,
		boolean retrieveFromCache);

	/**
	* Removes the process step role where roleCode = &#63; from the database.
	*
	* @param roleCode the role code
	* @return the process step role that was removed
	*/
	public ProcessStepRole removeByF_CODE(String roleCode)
		throws NoSuchProcessStepRoleException;

	/**
	* Returns the number of process step roles where roleCode = &#63;.
	*
	* @param roleCode the role code
	* @return the number of matching process step roles
	*/
	public int countByF_CODE(String roleCode);

	/**
	* Caches the process step role in the entity cache if it is enabled.
	*
	* @param processStepRole the process step role
	*/
	public void cacheResult(ProcessStepRole processStepRole);

	/**
	* Caches the process step roles in the entity cache if it is enabled.
	*
	* @param processStepRoles the process step roles
	*/
	public void cacheResult(java.util.List<ProcessStepRole> processStepRoles);

	/**
	* Creates a new process step role with the primary key. Does not add the process step role to the database.
	*
	* @param processStepRolePK the primary key for the new process step role
	* @return the new process step role
	*/
	public ProcessStepRole create(ProcessStepRolePK processStepRolePK);

	/**
	* Removes the process step role with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param processStepRolePK the primary key of the process step role
	* @return the process step role that was removed
	* @throws NoSuchProcessStepRoleException if a process step role with the primary key could not be found
	*/
	public ProcessStepRole remove(ProcessStepRolePK processStepRolePK)
		throws NoSuchProcessStepRoleException;

	public ProcessStepRole updateImpl(ProcessStepRole processStepRole);

	/**
	* Returns the process step role with the primary key or throws a {@link NoSuchProcessStepRoleException} if it could not be found.
	*
	* @param processStepRolePK the primary key of the process step role
	* @return the process step role
	* @throws NoSuchProcessStepRoleException if a process step role with the primary key could not be found
	*/
	public ProcessStepRole findByPrimaryKey(ProcessStepRolePK processStepRolePK)
		throws NoSuchProcessStepRoleException;

	/**
	* Returns the process step role with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param processStepRolePK the primary key of the process step role
	* @return the process step role, or <code>null</code> if a process step role with the primary key could not be found
	*/
	public ProcessStepRole fetchByPrimaryKey(
		ProcessStepRolePK processStepRolePK);

	@Override
	public java.util.Map<java.io.Serializable, ProcessStepRole> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the process step roles.
	*
	* @return the process step roles
	*/
	public java.util.List<ProcessStepRole> findAll();

	/**
	* Returns a range of all the process step roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of process step roles
	* @param end the upper bound of the range of process step roles (not inclusive)
	* @return the range of process step roles
	*/
	public java.util.List<ProcessStepRole> findAll(int start, int end);

	/**
	* Returns an ordered range of all the process step roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of process step roles
	* @param end the upper bound of the range of process step roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of process step roles
	*/
	public java.util.List<ProcessStepRole> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessStepRole> orderByComparator);

	/**
	* Returns an ordered range of all the process step roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of process step roles
	* @param end the upper bound of the range of process step roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of process step roles
	*/
	public java.util.List<ProcessStepRole> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessStepRole> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the process step roles from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of process step roles.
	*
	* @return the number of process step roles
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();

	public java.util.Set<String> getCompoundPKColumnNames();
}