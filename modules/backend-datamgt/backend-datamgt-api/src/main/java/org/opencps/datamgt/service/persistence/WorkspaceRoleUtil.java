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

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.datamgt.model.WorkspaceRole;
import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the workspace role service. This utility wraps {@link org.mobilink.backend.datamgt.service.persistence.impl.WorkspaceRolePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Binhth
 * @see WorkspaceRolePersistence
 * @see org.mobilink.backend.datamgt.service.persistence.impl.WorkspaceRolePersistenceImpl
 * @generated
 */
@ProviderType
public class WorkspaceRoleUtil {
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
	public static void clearCache(WorkspaceRole workspaceRole) {
		getPersistence().clearCache(workspaceRole);
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
	public static List<WorkspaceRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<WorkspaceRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<WorkspaceRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<WorkspaceRole> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static WorkspaceRole update(WorkspaceRole workspaceRole) {
		return getPersistence().update(workspaceRole);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static WorkspaceRole update(WorkspaceRole workspaceRole,
		ServiceContext serviceContext) {
		return getPersistence().update(workspaceRole, serviceContext);
	}

	/**
	* Returns all the workspace roles where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching workspace roles
	*/
	public static List<WorkspaceRole> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

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
	public static List<WorkspaceRole> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static List<WorkspaceRole> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<WorkspaceRole> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

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
	public static List<WorkspaceRole> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<WorkspaceRole> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first workspace role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching workspace role
	* @throws NoSuchWorkspaceRoleException if a matching workspace role could not be found
	*/
	public static WorkspaceRole findByUuid_First(java.lang.String uuid,
		OrderByComparator<WorkspaceRole> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchWorkspaceRoleException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first workspace role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching workspace role, or <code>null</code> if a matching workspace role could not be found
	*/
	public static WorkspaceRole fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<WorkspaceRole> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last workspace role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching workspace role
	* @throws NoSuchWorkspaceRoleException if a matching workspace role could not be found
	*/
	public static WorkspaceRole findByUuid_Last(java.lang.String uuid,
		OrderByComparator<WorkspaceRole> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchWorkspaceRoleException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last workspace role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching workspace role, or <code>null</code> if a matching workspace role could not be found
	*/
	public static WorkspaceRole fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<WorkspaceRole> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the workspace roles before and after the current workspace role in the ordered set where uuid = &#63;.
	*
	* @param workspaceRoleId the primary key of the current workspace role
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next workspace role
	* @throws NoSuchWorkspaceRoleException if a workspace role with the primary key could not be found
	*/
	public static WorkspaceRole[] findByUuid_PrevAndNext(long workspaceRoleId,
		java.lang.String uuid,
		OrderByComparator<WorkspaceRole> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchWorkspaceRoleException {
		return getPersistence()
				   .findByUuid_PrevAndNext(workspaceRoleId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the workspace roles where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of workspace roles where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching workspace roles
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the workspace role where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchWorkspaceRoleException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching workspace role
	* @throws NoSuchWorkspaceRoleException if a matching workspace role could not be found
	*/
	public static WorkspaceRole findByUUID_G(java.lang.String uuid, long groupId)
		throws org.opencps.datamgt.exception.NoSuchWorkspaceRoleException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the workspace role where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching workspace role, or <code>null</code> if a matching workspace role could not be found
	*/
	public static WorkspaceRole fetchByUUID_G(java.lang.String uuid,
		long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the workspace role where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching workspace role, or <code>null</code> if a matching workspace role could not be found
	*/
	public static WorkspaceRole fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the workspace role where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the workspace role that was removed
	*/
	public static WorkspaceRole removeByUUID_G(java.lang.String uuid,
		long groupId)
		throws org.opencps.datamgt.exception.NoSuchWorkspaceRoleException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of workspace roles where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching workspace roles
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the workspace roles where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching workspace roles
	*/
	public static List<WorkspaceRole> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

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
	public static List<WorkspaceRole> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

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
	public static List<WorkspaceRole> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<WorkspaceRole> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

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
	public static List<WorkspaceRole> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<WorkspaceRole> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first workspace role in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching workspace role
	* @throws NoSuchWorkspaceRoleException if a matching workspace role could not be found
	*/
	public static WorkspaceRole findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<WorkspaceRole> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchWorkspaceRoleException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first workspace role in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching workspace role, or <code>null</code> if a matching workspace role could not be found
	*/
	public static WorkspaceRole fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<WorkspaceRole> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last workspace role in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching workspace role
	* @throws NoSuchWorkspaceRoleException if a matching workspace role could not be found
	*/
	public static WorkspaceRole findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<WorkspaceRole> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchWorkspaceRoleException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last workspace role in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching workspace role, or <code>null</code> if a matching workspace role could not be found
	*/
	public static WorkspaceRole fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<WorkspaceRole> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

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
	public static WorkspaceRole[] findByUuid_C_PrevAndNext(
		long workspaceRoleId, java.lang.String uuid, long companyId,
		OrderByComparator<WorkspaceRole> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchWorkspaceRoleException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(workspaceRoleId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the workspace roles where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of workspace roles where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching workspace roles
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Caches the workspace role in the entity cache if it is enabled.
	*
	* @param workspaceRole the workspace role
	*/
	public static void cacheResult(WorkspaceRole workspaceRole) {
		getPersistence().cacheResult(workspaceRole);
	}

	/**
	* Caches the workspace roles in the entity cache if it is enabled.
	*
	* @param workspaceRoles the workspace roles
	*/
	public static void cacheResult(List<WorkspaceRole> workspaceRoles) {
		getPersistence().cacheResult(workspaceRoles);
	}

	/**
	* Creates a new workspace role with the primary key. Does not add the workspace role to the database.
	*
	* @param workspaceRoleId the primary key for the new workspace role
	* @return the new workspace role
	*/
	public static WorkspaceRole create(long workspaceRoleId) {
		return getPersistence().create(workspaceRoleId);
	}

	/**
	* Removes the workspace role with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param workspaceRoleId the primary key of the workspace role
	* @return the workspace role that was removed
	* @throws NoSuchWorkspaceRoleException if a workspace role with the primary key could not be found
	*/
	public static WorkspaceRole remove(long workspaceRoleId)
		throws org.opencps.datamgt.exception.NoSuchWorkspaceRoleException {
		return getPersistence().remove(workspaceRoleId);
	}

	public static WorkspaceRole updateImpl(WorkspaceRole workspaceRole) {
		return getPersistence().updateImpl(workspaceRole);
	}

	/**
	* Returns the workspace role with the primary key or throws a {@link NoSuchWorkspaceRoleException} if it could not be found.
	*
	* @param workspaceRoleId the primary key of the workspace role
	* @return the workspace role
	* @throws NoSuchWorkspaceRoleException if a workspace role with the primary key could not be found
	*/
	public static WorkspaceRole findByPrimaryKey(long workspaceRoleId)
		throws org.opencps.datamgt.exception.NoSuchWorkspaceRoleException {
		return getPersistence().findByPrimaryKey(workspaceRoleId);
	}

	/**
	* Returns the workspace role with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param workspaceRoleId the primary key of the workspace role
	* @return the workspace role, or <code>null</code> if a workspace role with the primary key could not be found
	*/
	public static WorkspaceRole fetchByPrimaryKey(long workspaceRoleId) {
		return getPersistence().fetchByPrimaryKey(workspaceRoleId);
	}

	public static java.util.Map<java.io.Serializable, WorkspaceRole> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the workspace roles.
	*
	* @return the workspace roles
	*/
	public static List<WorkspaceRole> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<WorkspaceRole> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<WorkspaceRole> findAll(int start, int end,
		OrderByComparator<WorkspaceRole> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<WorkspaceRole> findAll(int start, int end,
		OrderByComparator<WorkspaceRole> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the workspace roles from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of workspace roles.
	*
	* @return the number of workspace roles
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static WorkspaceRolePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<WorkspaceRolePersistence, WorkspaceRolePersistence> _serviceTracker =
		ServiceTrackerFactory.open(WorkspaceRolePersistence.class);
}