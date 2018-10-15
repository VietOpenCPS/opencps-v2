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

import org.opencps.dossiermgt.model.ServiceProcessRole;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the service process role service. This utility wraps {@link org.opencps.dossiermgt.service.persistence.impl.ServiceProcessRolePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see ServiceProcessRolePersistence
 * @see org.opencps.dossiermgt.service.persistence.impl.ServiceProcessRolePersistenceImpl
 * @generated
 */
@ProviderType
public class ServiceProcessRoleUtil {
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
	public static void clearCache(ServiceProcessRole serviceProcessRole) {
		getPersistence().clearCache(serviceProcessRole);
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
	public static List<ServiceProcessRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ServiceProcessRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ServiceProcessRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ServiceProcessRole> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ServiceProcessRole update(
		ServiceProcessRole serviceProcessRole) {
		return getPersistence().update(serviceProcessRole);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ServiceProcessRole update(
		ServiceProcessRole serviceProcessRole, ServiceContext serviceContext) {
		return getPersistence().update(serviceProcessRole, serviceContext);
	}

	/**
	* Returns all the service process roles where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching service process roles
	*/
	public static List<ServiceProcessRole> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the service process roles where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of service process roles
	* @param end the upper bound of the range of service process roles (not inclusive)
	* @return the range of matching service process roles
	*/
	public static List<ServiceProcessRole> findByUuid(String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the service process roles where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of service process roles
	* @param end the upper bound of the range of service process roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching service process roles
	*/
	public static List<ServiceProcessRole> findByUuid(String uuid, int start,
		int end, OrderByComparator<ServiceProcessRole> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the service process roles where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of service process roles
	* @param end the upper bound of the range of service process roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching service process roles
	*/
	public static List<ServiceProcessRole> findByUuid(String uuid, int start,
		int end, OrderByComparator<ServiceProcessRole> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first service process role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service process role
	* @throws NoSuchServiceProcessRoleException if a matching service process role could not be found
	*/
	public static ServiceProcessRole findByUuid_First(String uuid,
		OrderByComparator<ServiceProcessRole> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchServiceProcessRoleException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first service process role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service process role, or <code>null</code> if a matching service process role could not be found
	*/
	public static ServiceProcessRole fetchByUuid_First(String uuid,
		OrderByComparator<ServiceProcessRole> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last service process role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service process role
	* @throws NoSuchServiceProcessRoleException if a matching service process role could not be found
	*/
	public static ServiceProcessRole findByUuid_Last(String uuid,
		OrderByComparator<ServiceProcessRole> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchServiceProcessRoleException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last service process role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service process role, or <code>null</code> if a matching service process role could not be found
	*/
	public static ServiceProcessRole fetchByUuid_Last(String uuid,
		OrderByComparator<ServiceProcessRole> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the service process roles before and after the current service process role in the ordered set where uuid = &#63;.
	*
	* @param serviceProcessRolePK the primary key of the current service process role
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next service process role
	* @throws NoSuchServiceProcessRoleException if a service process role with the primary key could not be found
	*/
	public static ServiceProcessRole[] findByUuid_PrevAndNext(
		ServiceProcessRolePK serviceProcessRolePK, String uuid,
		OrderByComparator<ServiceProcessRole> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchServiceProcessRoleException {
		return getPersistence()
				   .findByUuid_PrevAndNext(serviceProcessRolePK, uuid,
			orderByComparator);
	}

	/**
	* Removes all the service process roles where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of service process roles where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching service process roles
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns all the service process roles where serviceProcessId = &#63;.
	*
	* @param serviceProcessId the service process ID
	* @return the matching service process roles
	*/
	public static List<ServiceProcessRole> findByP_S_ID(long serviceProcessId) {
		return getPersistence().findByP_S_ID(serviceProcessId);
	}

	/**
	* Returns a range of all the service process roles where serviceProcessId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param serviceProcessId the service process ID
	* @param start the lower bound of the range of service process roles
	* @param end the upper bound of the range of service process roles (not inclusive)
	* @return the range of matching service process roles
	*/
	public static List<ServiceProcessRole> findByP_S_ID(long serviceProcessId,
		int start, int end) {
		return getPersistence().findByP_S_ID(serviceProcessId, start, end);
	}

	/**
	* Returns an ordered range of all the service process roles where serviceProcessId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param serviceProcessId the service process ID
	* @param start the lower bound of the range of service process roles
	* @param end the upper bound of the range of service process roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching service process roles
	*/
	public static List<ServiceProcessRole> findByP_S_ID(long serviceProcessId,
		int start, int end,
		OrderByComparator<ServiceProcessRole> orderByComparator) {
		return getPersistence()
				   .findByP_S_ID(serviceProcessId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the service process roles where serviceProcessId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param serviceProcessId the service process ID
	* @param start the lower bound of the range of service process roles
	* @param end the upper bound of the range of service process roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching service process roles
	*/
	public static List<ServiceProcessRole> findByP_S_ID(long serviceProcessId,
		int start, int end,
		OrderByComparator<ServiceProcessRole> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByP_S_ID(serviceProcessId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first service process role in the ordered set where serviceProcessId = &#63;.
	*
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service process role
	* @throws NoSuchServiceProcessRoleException if a matching service process role could not be found
	*/
	public static ServiceProcessRole findByP_S_ID_First(long serviceProcessId,
		OrderByComparator<ServiceProcessRole> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchServiceProcessRoleException {
		return getPersistence()
				   .findByP_S_ID_First(serviceProcessId, orderByComparator);
	}

	/**
	* Returns the first service process role in the ordered set where serviceProcessId = &#63;.
	*
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service process role, or <code>null</code> if a matching service process role could not be found
	*/
	public static ServiceProcessRole fetchByP_S_ID_First(
		long serviceProcessId,
		OrderByComparator<ServiceProcessRole> orderByComparator) {
		return getPersistence()
				   .fetchByP_S_ID_First(serviceProcessId, orderByComparator);
	}

	/**
	* Returns the last service process role in the ordered set where serviceProcessId = &#63;.
	*
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service process role
	* @throws NoSuchServiceProcessRoleException if a matching service process role could not be found
	*/
	public static ServiceProcessRole findByP_S_ID_Last(long serviceProcessId,
		OrderByComparator<ServiceProcessRole> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchServiceProcessRoleException {
		return getPersistence()
				   .findByP_S_ID_Last(serviceProcessId, orderByComparator);
	}

	/**
	* Returns the last service process role in the ordered set where serviceProcessId = &#63;.
	*
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service process role, or <code>null</code> if a matching service process role could not be found
	*/
	public static ServiceProcessRole fetchByP_S_ID_Last(long serviceProcessId,
		OrderByComparator<ServiceProcessRole> orderByComparator) {
		return getPersistence()
				   .fetchByP_S_ID_Last(serviceProcessId, orderByComparator);
	}

	/**
	* Returns the service process roles before and after the current service process role in the ordered set where serviceProcessId = &#63;.
	*
	* @param serviceProcessRolePK the primary key of the current service process role
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next service process role
	* @throws NoSuchServiceProcessRoleException if a service process role with the primary key could not be found
	*/
	public static ServiceProcessRole[] findByP_S_ID_PrevAndNext(
		ServiceProcessRolePK serviceProcessRolePK, long serviceProcessId,
		OrderByComparator<ServiceProcessRole> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchServiceProcessRoleException {
		return getPersistence()
				   .findByP_S_ID_PrevAndNext(serviceProcessRolePK,
			serviceProcessId, orderByComparator);
	}

	/**
	* Removes all the service process roles where serviceProcessId = &#63; from the database.
	*
	* @param serviceProcessId the service process ID
	*/
	public static void removeByP_S_ID(long serviceProcessId) {
		getPersistence().removeByP_S_ID(serviceProcessId);
	}

	/**
	* Returns the number of service process roles where serviceProcessId = &#63;.
	*
	* @param serviceProcessId the service process ID
	* @return the number of matching service process roles
	*/
	public static int countByP_S_ID(long serviceProcessId) {
		return getPersistence().countByP_S_ID(serviceProcessId);
	}

	/**
	* Returns the service process role where roleCode = &#63; or throws a {@link NoSuchServiceProcessRoleException} if it could not be found.
	*
	* @param roleCode the role code
	* @return the matching service process role
	* @throws NoSuchServiceProcessRoleException if a matching service process role could not be found
	*/
	public static ServiceProcessRole findByF_CODE(String roleCode)
		throws org.opencps.dossiermgt.exception.NoSuchServiceProcessRoleException {
		return getPersistence().findByF_CODE(roleCode);
	}

	/**
	* Returns the service process role where roleCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param roleCode the role code
	* @return the matching service process role, or <code>null</code> if a matching service process role could not be found
	*/
	public static ServiceProcessRole fetchByF_CODE(String roleCode) {
		return getPersistence().fetchByF_CODE(roleCode);
	}

	/**
	* Returns the service process role where roleCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param roleCode the role code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching service process role, or <code>null</code> if a matching service process role could not be found
	*/
	public static ServiceProcessRole fetchByF_CODE(String roleCode,
		boolean retrieveFromCache) {
		return getPersistence().fetchByF_CODE(roleCode, retrieveFromCache);
	}

	/**
	* Removes the service process role where roleCode = &#63; from the database.
	*
	* @param roleCode the role code
	* @return the service process role that was removed
	*/
	public static ServiceProcessRole removeByF_CODE(String roleCode)
		throws org.opencps.dossiermgt.exception.NoSuchServiceProcessRoleException {
		return getPersistence().removeByF_CODE(roleCode);
	}

	/**
	* Returns the number of service process roles where roleCode = &#63;.
	*
	* @param roleCode the role code
	* @return the number of matching service process roles
	*/
	public static int countByF_CODE(String roleCode) {
		return getPersistence().countByF_CODE(roleCode);
	}

	/**
	* Caches the service process role in the entity cache if it is enabled.
	*
	* @param serviceProcessRole the service process role
	*/
	public static void cacheResult(ServiceProcessRole serviceProcessRole) {
		getPersistence().cacheResult(serviceProcessRole);
	}

	/**
	* Caches the service process roles in the entity cache if it is enabled.
	*
	* @param serviceProcessRoles the service process roles
	*/
	public static void cacheResult(List<ServiceProcessRole> serviceProcessRoles) {
		getPersistence().cacheResult(serviceProcessRoles);
	}

	/**
	* Creates a new service process role with the primary key. Does not add the service process role to the database.
	*
	* @param serviceProcessRolePK the primary key for the new service process role
	* @return the new service process role
	*/
	public static ServiceProcessRole create(
		ServiceProcessRolePK serviceProcessRolePK) {
		return getPersistence().create(serviceProcessRolePK);
	}

	/**
	* Removes the service process role with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceProcessRolePK the primary key of the service process role
	* @return the service process role that was removed
	* @throws NoSuchServiceProcessRoleException if a service process role with the primary key could not be found
	*/
	public static ServiceProcessRole remove(
		ServiceProcessRolePK serviceProcessRolePK)
		throws org.opencps.dossiermgt.exception.NoSuchServiceProcessRoleException {
		return getPersistence().remove(serviceProcessRolePK);
	}

	public static ServiceProcessRole updateImpl(
		ServiceProcessRole serviceProcessRole) {
		return getPersistence().updateImpl(serviceProcessRole);
	}

	/**
	* Returns the service process role with the primary key or throws a {@link NoSuchServiceProcessRoleException} if it could not be found.
	*
	* @param serviceProcessRolePK the primary key of the service process role
	* @return the service process role
	* @throws NoSuchServiceProcessRoleException if a service process role with the primary key could not be found
	*/
	public static ServiceProcessRole findByPrimaryKey(
		ServiceProcessRolePK serviceProcessRolePK)
		throws org.opencps.dossiermgt.exception.NoSuchServiceProcessRoleException {
		return getPersistence().findByPrimaryKey(serviceProcessRolePK);
	}

	/**
	* Returns the service process role with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param serviceProcessRolePK the primary key of the service process role
	* @return the service process role, or <code>null</code> if a service process role with the primary key could not be found
	*/
	public static ServiceProcessRole fetchByPrimaryKey(
		ServiceProcessRolePK serviceProcessRolePK) {
		return getPersistence().fetchByPrimaryKey(serviceProcessRolePK);
	}

	public static java.util.Map<java.io.Serializable, ServiceProcessRole> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the service process roles.
	*
	* @return the service process roles
	*/
	public static List<ServiceProcessRole> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the service process roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of service process roles
	* @param end the upper bound of the range of service process roles (not inclusive)
	* @return the range of service process roles
	*/
	public static List<ServiceProcessRole> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the service process roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of service process roles
	* @param end the upper bound of the range of service process roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of service process roles
	*/
	public static List<ServiceProcessRole> findAll(int start, int end,
		OrderByComparator<ServiceProcessRole> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the service process roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of service process roles
	* @param end the upper bound of the range of service process roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of service process roles
	*/
	public static List<ServiceProcessRole> findAll(int start, int end,
		OrderByComparator<ServiceProcessRole> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the service process roles from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of service process roles.
	*
	* @return the number of service process roles
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

	public static ServiceProcessRolePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ServiceProcessRolePersistence, ServiceProcessRolePersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ServiceProcessRolePersistence.class);

		ServiceTracker<ServiceProcessRolePersistence, ServiceProcessRolePersistence> serviceTracker =
			new ServiceTracker<ServiceProcessRolePersistence, ServiceProcessRolePersistence>(bundle.getBundleContext(),
				ServiceProcessRolePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}