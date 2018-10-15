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

import org.opencps.dossiermgt.model.ProcessStepRole;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the process step role service. This utility wraps {@link org.opencps.dossiermgt.service.persistence.impl.ProcessStepRolePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see ProcessStepRolePersistence
 * @see org.opencps.dossiermgt.service.persistence.impl.ProcessStepRolePersistenceImpl
 * @generated
 */
@ProviderType
public class ProcessStepRoleUtil {
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
	public static void clearCache(ProcessStepRole processStepRole) {
		getPersistence().clearCache(processStepRole);
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
	public static List<ProcessStepRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ProcessStepRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ProcessStepRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ProcessStepRole> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ProcessStepRole update(ProcessStepRole processStepRole) {
		return getPersistence().update(processStepRole);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ProcessStepRole update(ProcessStepRole processStepRole,
		ServiceContext serviceContext) {
		return getPersistence().update(processStepRole, serviceContext);
	}

	/**
	* Returns all the process step roles where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching process step roles
	*/
	public static List<ProcessStepRole> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

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
	public static List<ProcessStepRole> findByUuid(String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static List<ProcessStepRole> findByUuid(String uuid, int start,
		int end, OrderByComparator<ProcessStepRole> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

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
	public static List<ProcessStepRole> findByUuid(String uuid, int start,
		int end, OrderByComparator<ProcessStepRole> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first process step role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process step role
	* @throws NoSuchProcessStepRoleException if a matching process step role could not be found
	*/
	public static ProcessStepRole findByUuid_First(String uuid,
		OrderByComparator<ProcessStepRole> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessStepRoleException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first process step role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process step role, or <code>null</code> if a matching process step role could not be found
	*/
	public static ProcessStepRole fetchByUuid_First(String uuid,
		OrderByComparator<ProcessStepRole> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last process step role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process step role
	* @throws NoSuchProcessStepRoleException if a matching process step role could not be found
	*/
	public static ProcessStepRole findByUuid_Last(String uuid,
		OrderByComparator<ProcessStepRole> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessStepRoleException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last process step role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process step role, or <code>null</code> if a matching process step role could not be found
	*/
	public static ProcessStepRole fetchByUuid_Last(String uuid,
		OrderByComparator<ProcessStepRole> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the process step roles before and after the current process step role in the ordered set where uuid = &#63;.
	*
	* @param processStepRolePK the primary key of the current process step role
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process step role
	* @throws NoSuchProcessStepRoleException if a process step role with the primary key could not be found
	*/
	public static ProcessStepRole[] findByUuid_PrevAndNext(
		ProcessStepRolePK processStepRolePK, String uuid,
		OrderByComparator<ProcessStepRole> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessStepRoleException {
		return getPersistence()
				   .findByUuid_PrevAndNext(processStepRolePK, uuid,
			orderByComparator);
	}

	/**
	* Removes all the process step roles where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of process step roles where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching process step roles
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns all the process step roles where processStepId = &#63;.
	*
	* @param processStepId the process step ID
	* @return the matching process step roles
	*/
	public static List<ProcessStepRole> findByP_S_ID(long processStepId) {
		return getPersistence().findByP_S_ID(processStepId);
	}

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
	public static List<ProcessStepRole> findByP_S_ID(long processStepId,
		int start, int end) {
		return getPersistence().findByP_S_ID(processStepId, start, end);
	}

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
	public static List<ProcessStepRole> findByP_S_ID(long processStepId,
		int start, int end, OrderByComparator<ProcessStepRole> orderByComparator) {
		return getPersistence()
				   .findByP_S_ID(processStepId, start, end, orderByComparator);
	}

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
	public static List<ProcessStepRole> findByP_S_ID(long processStepId,
		int start, int end,
		OrderByComparator<ProcessStepRole> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByP_S_ID(processStepId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first process step role in the ordered set where processStepId = &#63;.
	*
	* @param processStepId the process step ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process step role
	* @throws NoSuchProcessStepRoleException if a matching process step role could not be found
	*/
	public static ProcessStepRole findByP_S_ID_First(long processStepId,
		OrderByComparator<ProcessStepRole> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessStepRoleException {
		return getPersistence()
				   .findByP_S_ID_First(processStepId, orderByComparator);
	}

	/**
	* Returns the first process step role in the ordered set where processStepId = &#63;.
	*
	* @param processStepId the process step ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process step role, or <code>null</code> if a matching process step role could not be found
	*/
	public static ProcessStepRole fetchByP_S_ID_First(long processStepId,
		OrderByComparator<ProcessStepRole> orderByComparator) {
		return getPersistence()
				   .fetchByP_S_ID_First(processStepId, orderByComparator);
	}

	/**
	* Returns the last process step role in the ordered set where processStepId = &#63;.
	*
	* @param processStepId the process step ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process step role
	* @throws NoSuchProcessStepRoleException if a matching process step role could not be found
	*/
	public static ProcessStepRole findByP_S_ID_Last(long processStepId,
		OrderByComparator<ProcessStepRole> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessStepRoleException {
		return getPersistence()
				   .findByP_S_ID_Last(processStepId, orderByComparator);
	}

	/**
	* Returns the last process step role in the ordered set where processStepId = &#63;.
	*
	* @param processStepId the process step ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process step role, or <code>null</code> if a matching process step role could not be found
	*/
	public static ProcessStepRole fetchByP_S_ID_Last(long processStepId,
		OrderByComparator<ProcessStepRole> orderByComparator) {
		return getPersistence()
				   .fetchByP_S_ID_Last(processStepId, orderByComparator);
	}

	/**
	* Returns the process step roles before and after the current process step role in the ordered set where processStepId = &#63;.
	*
	* @param processStepRolePK the primary key of the current process step role
	* @param processStepId the process step ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process step role
	* @throws NoSuchProcessStepRoleException if a process step role with the primary key could not be found
	*/
	public static ProcessStepRole[] findByP_S_ID_PrevAndNext(
		ProcessStepRolePK processStepRolePK, long processStepId,
		OrderByComparator<ProcessStepRole> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessStepRoleException {
		return getPersistence()
				   .findByP_S_ID_PrevAndNext(processStepRolePK, processStepId,
			orderByComparator);
	}

	/**
	* Removes all the process step roles where processStepId = &#63; from the database.
	*
	* @param processStepId the process step ID
	*/
	public static void removeByP_S_ID(long processStepId) {
		getPersistence().removeByP_S_ID(processStepId);
	}

	/**
	* Returns the number of process step roles where processStepId = &#63;.
	*
	* @param processStepId the process step ID
	* @return the number of matching process step roles
	*/
	public static int countByP_S_ID(long processStepId) {
		return getPersistence().countByP_S_ID(processStepId);
	}

	/**
	* Returns the process step role where processStepId = &#63; and roleId = &#63; or throws a {@link NoSuchProcessStepRoleException} if it could not be found.
	*
	* @param processStepId the process step ID
	* @param roleId the role ID
	* @return the matching process step role
	* @throws NoSuchProcessStepRoleException if a matching process step role could not be found
	*/
	public static ProcessStepRole findByF_STEP_ROLE(long processStepId,
		long roleId)
		throws org.opencps.dossiermgt.exception.NoSuchProcessStepRoleException {
		return getPersistence().findByF_STEP_ROLE(processStepId, roleId);
	}

	/**
	* Returns the process step role where processStepId = &#63; and roleId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param processStepId the process step ID
	* @param roleId the role ID
	* @return the matching process step role, or <code>null</code> if a matching process step role could not be found
	*/
	public static ProcessStepRole fetchByF_STEP_ROLE(long processStepId,
		long roleId) {
		return getPersistence().fetchByF_STEP_ROLE(processStepId, roleId);
	}

	/**
	* Returns the process step role where processStepId = &#63; and roleId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param processStepId the process step ID
	* @param roleId the role ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching process step role, or <code>null</code> if a matching process step role could not be found
	*/
	public static ProcessStepRole fetchByF_STEP_ROLE(long processStepId,
		long roleId, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_STEP_ROLE(processStepId, roleId, retrieveFromCache);
	}

	/**
	* Removes the process step role where processStepId = &#63; and roleId = &#63; from the database.
	*
	* @param processStepId the process step ID
	* @param roleId the role ID
	* @return the process step role that was removed
	*/
	public static ProcessStepRole removeByF_STEP_ROLE(long processStepId,
		long roleId)
		throws org.opencps.dossiermgt.exception.NoSuchProcessStepRoleException {
		return getPersistence().removeByF_STEP_ROLE(processStepId, roleId);
	}

	/**
	* Returns the number of process step roles where processStepId = &#63; and roleId = &#63;.
	*
	* @param processStepId the process step ID
	* @param roleId the role ID
	* @return the number of matching process step roles
	*/
	public static int countByF_STEP_ROLE(long processStepId, long roleId) {
		return getPersistence().countByF_STEP_ROLE(processStepId, roleId);
	}

	/**
	* Returns the process step role where roleCode = &#63; or throws a {@link NoSuchProcessStepRoleException} if it could not be found.
	*
	* @param roleCode the role code
	* @return the matching process step role
	* @throws NoSuchProcessStepRoleException if a matching process step role could not be found
	*/
	public static ProcessStepRole findByF_CODE(String roleCode)
		throws org.opencps.dossiermgt.exception.NoSuchProcessStepRoleException {
		return getPersistence().findByF_CODE(roleCode);
	}

	/**
	* Returns the process step role where roleCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param roleCode the role code
	* @return the matching process step role, or <code>null</code> if a matching process step role could not be found
	*/
	public static ProcessStepRole fetchByF_CODE(String roleCode) {
		return getPersistence().fetchByF_CODE(roleCode);
	}

	/**
	* Returns the process step role where roleCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param roleCode the role code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching process step role, or <code>null</code> if a matching process step role could not be found
	*/
	public static ProcessStepRole fetchByF_CODE(String roleCode,
		boolean retrieveFromCache) {
		return getPersistence().fetchByF_CODE(roleCode, retrieveFromCache);
	}

	/**
	* Removes the process step role where roleCode = &#63; from the database.
	*
	* @param roleCode the role code
	* @return the process step role that was removed
	*/
	public static ProcessStepRole removeByF_CODE(String roleCode)
		throws org.opencps.dossiermgt.exception.NoSuchProcessStepRoleException {
		return getPersistence().removeByF_CODE(roleCode);
	}

	/**
	* Returns the number of process step roles where roleCode = &#63;.
	*
	* @param roleCode the role code
	* @return the number of matching process step roles
	*/
	public static int countByF_CODE(String roleCode) {
		return getPersistence().countByF_CODE(roleCode);
	}

	/**
	* Caches the process step role in the entity cache if it is enabled.
	*
	* @param processStepRole the process step role
	*/
	public static void cacheResult(ProcessStepRole processStepRole) {
		getPersistence().cacheResult(processStepRole);
	}

	/**
	* Caches the process step roles in the entity cache if it is enabled.
	*
	* @param processStepRoles the process step roles
	*/
	public static void cacheResult(List<ProcessStepRole> processStepRoles) {
		getPersistence().cacheResult(processStepRoles);
	}

	/**
	* Creates a new process step role with the primary key. Does not add the process step role to the database.
	*
	* @param processStepRolePK the primary key for the new process step role
	* @return the new process step role
	*/
	public static ProcessStepRole create(ProcessStepRolePK processStepRolePK) {
		return getPersistence().create(processStepRolePK);
	}

	/**
	* Removes the process step role with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param processStepRolePK the primary key of the process step role
	* @return the process step role that was removed
	* @throws NoSuchProcessStepRoleException if a process step role with the primary key could not be found
	*/
	public static ProcessStepRole remove(ProcessStepRolePK processStepRolePK)
		throws org.opencps.dossiermgt.exception.NoSuchProcessStepRoleException {
		return getPersistence().remove(processStepRolePK);
	}

	public static ProcessStepRole updateImpl(ProcessStepRole processStepRole) {
		return getPersistence().updateImpl(processStepRole);
	}

	/**
	* Returns the process step role with the primary key or throws a {@link NoSuchProcessStepRoleException} if it could not be found.
	*
	* @param processStepRolePK the primary key of the process step role
	* @return the process step role
	* @throws NoSuchProcessStepRoleException if a process step role with the primary key could not be found
	*/
	public static ProcessStepRole findByPrimaryKey(
		ProcessStepRolePK processStepRolePK)
		throws org.opencps.dossiermgt.exception.NoSuchProcessStepRoleException {
		return getPersistence().findByPrimaryKey(processStepRolePK);
	}

	/**
	* Returns the process step role with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param processStepRolePK the primary key of the process step role
	* @return the process step role, or <code>null</code> if a process step role with the primary key could not be found
	*/
	public static ProcessStepRole fetchByPrimaryKey(
		ProcessStepRolePK processStepRolePK) {
		return getPersistence().fetchByPrimaryKey(processStepRolePK);
	}

	public static java.util.Map<java.io.Serializable, ProcessStepRole> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the process step roles.
	*
	* @return the process step roles
	*/
	public static List<ProcessStepRole> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<ProcessStepRole> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<ProcessStepRole> findAll(int start, int end,
		OrderByComparator<ProcessStepRole> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<ProcessStepRole> findAll(int start, int end,
		OrderByComparator<ProcessStepRole> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the process step roles from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of process step roles.
	*
	* @return the number of process step roles
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static java.util.Set<String> getCompoundPKColumnNames() {
		return getPersistence().getCompoundPKColumnNames();
	}

	public static ProcessStepRolePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ProcessStepRolePersistence, ProcessStepRolePersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ProcessStepRolePersistence.class);

		ServiceTracker<ProcessStepRolePersistence, ProcessStepRolePersistence> serviceTracker =
			new ServiceTracker<ProcessStepRolePersistence, ProcessStepRolePersistence>(bundle.getBundleContext(),
				ProcessStepRolePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}