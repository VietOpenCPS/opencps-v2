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

package org.opencps.adminconfig.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.adminconfig.model.ApiRole;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the api role service. This utility wraps {@link org.opencps.adminconfig.service.persistence.impl.ApiRolePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author binhth
 * @see ApiRolePersistence
 * @see org.opencps.adminconfig.service.persistence.impl.ApiRolePersistenceImpl
 * @generated
 */
@ProviderType
public class ApiRoleUtil {
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
	public static void clearCache(ApiRole apiRole) {
		getPersistence().clearCache(apiRole);
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
	public static List<ApiRole> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ApiRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ApiRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ApiRole> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ApiRole update(ApiRole apiRole) {
		return getPersistence().update(apiRole);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ApiRole update(ApiRole apiRole, ServiceContext serviceContext) {
		return getPersistence().update(apiRole, serviceContext);
	}

	/**
	* Returns the api role where apiRoleId = &#63; or throws a {@link NoSuchApiRoleException} if it could not be found.
	*
	* @param apiRoleId the api role ID
	* @return the matching api role
	* @throws NoSuchApiRoleException if a matching api role could not be found
	*/
	public static ApiRole findByF_ID(long apiRoleId)
		throws org.opencps.adminconfig.exception.NoSuchApiRoleException {
		return getPersistence().findByF_ID(apiRoleId);
	}

	/**
	* Returns the api role where apiRoleId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param apiRoleId the api role ID
	* @return the matching api role, or <code>null</code> if a matching api role could not be found
	*/
	public static ApiRole fetchByF_ID(long apiRoleId) {
		return getPersistence().fetchByF_ID(apiRoleId);
	}

	/**
	* Returns the api role where apiRoleId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param apiRoleId the api role ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching api role, or <code>null</code> if a matching api role could not be found
	*/
	public static ApiRole fetchByF_ID(long apiRoleId, boolean retrieveFromCache) {
		return getPersistence().fetchByF_ID(apiRoleId, retrieveFromCache);
	}

	/**
	* Removes the api role where apiRoleId = &#63; from the database.
	*
	* @param apiRoleId the api role ID
	* @return the api role that was removed
	*/
	public static ApiRole removeByF_ID(long apiRoleId)
		throws org.opencps.adminconfig.exception.NoSuchApiRoleException {
		return getPersistence().removeByF_ID(apiRoleId);
	}

	/**
	* Returns the number of api roles where apiRoleId = &#63;.
	*
	* @param apiRoleId the api role ID
	* @return the number of matching api roles
	*/
	public static int countByF_ID(long apiRoleId) {
		return getPersistence().countByF_ID(apiRoleId);
	}

	/**
	* Returns the api role where roleCode = &#63; or throws a {@link NoSuchApiRoleException} if it could not be found.
	*
	* @param roleCode the role code
	* @return the matching api role
	* @throws NoSuchApiRoleException if a matching api role could not be found
	*/
	public static ApiRole findByF_roleCode(String roleCode)
		throws org.opencps.adminconfig.exception.NoSuchApiRoleException {
		return getPersistence().findByF_roleCode(roleCode);
	}

	/**
	* Returns the api role where roleCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param roleCode the role code
	* @return the matching api role, or <code>null</code> if a matching api role could not be found
	*/
	public static ApiRole fetchByF_roleCode(String roleCode) {
		return getPersistence().fetchByF_roleCode(roleCode);
	}

	/**
	* Returns the api role where roleCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param roleCode the role code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching api role, or <code>null</code> if a matching api role could not be found
	*/
	public static ApiRole fetchByF_roleCode(String roleCode,
		boolean retrieveFromCache) {
		return getPersistence().fetchByF_roleCode(roleCode, retrieveFromCache);
	}

	/**
	* Removes the api role where roleCode = &#63; from the database.
	*
	* @param roleCode the role code
	* @return the api role that was removed
	*/
	public static ApiRole removeByF_roleCode(String roleCode)
		throws org.opencps.adminconfig.exception.NoSuchApiRoleException {
		return getPersistence().removeByF_roleCode(roleCode);
	}

	/**
	* Returns the number of api roles where roleCode = &#63;.
	*
	* @param roleCode the role code
	* @return the number of matching api roles
	*/
	public static int countByF_roleCode(String roleCode) {
		return getPersistence().countByF_roleCode(roleCode);
	}

	/**
	* Returns all the api roles where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching api roles
	*/
	public static List<ApiRole> findByF_GID(long groupId) {
		return getPersistence().findByF_GID(groupId);
	}

	/**
	* Returns a range of all the api roles where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApiRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of api roles
	* @param end the upper bound of the range of api roles (not inclusive)
	* @return the range of matching api roles
	*/
	public static List<ApiRole> findByF_GID(long groupId, int start, int end) {
		return getPersistence().findByF_GID(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the api roles where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApiRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of api roles
	* @param end the upper bound of the range of api roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching api roles
	*/
	public static List<ApiRole> findByF_GID(long groupId, int start, int end,
		OrderByComparator<ApiRole> orderByComparator) {
		return getPersistence()
				   .findByF_GID(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the api roles where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApiRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of api roles
	* @param end the upper bound of the range of api roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching api roles
	*/
	public static List<ApiRole> findByF_GID(long groupId, int start, int end,
		OrderByComparator<ApiRole> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_GID(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first api role in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching api role
	* @throws NoSuchApiRoleException if a matching api role could not be found
	*/
	public static ApiRole findByF_GID_First(long groupId,
		OrderByComparator<ApiRole> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchApiRoleException {
		return getPersistence().findByF_GID_First(groupId, orderByComparator);
	}

	/**
	* Returns the first api role in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching api role, or <code>null</code> if a matching api role could not be found
	*/
	public static ApiRole fetchByF_GID_First(long groupId,
		OrderByComparator<ApiRole> orderByComparator) {
		return getPersistence().fetchByF_GID_First(groupId, orderByComparator);
	}

	/**
	* Returns the last api role in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching api role
	* @throws NoSuchApiRoleException if a matching api role could not be found
	*/
	public static ApiRole findByF_GID_Last(long groupId,
		OrderByComparator<ApiRole> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchApiRoleException {
		return getPersistence().findByF_GID_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last api role in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching api role, or <code>null</code> if a matching api role could not be found
	*/
	public static ApiRole fetchByF_GID_Last(long groupId,
		OrderByComparator<ApiRole> orderByComparator) {
		return getPersistence().fetchByF_GID_Last(groupId, orderByComparator);
	}

	/**
	* Returns the api roles before and after the current api role in the ordered set where groupId = &#63;.
	*
	* @param apiRoleId the primary key of the current api role
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next api role
	* @throws NoSuchApiRoleException if a api role with the primary key could not be found
	*/
	public static ApiRole[] findByF_GID_PrevAndNext(long apiRoleId,
		long groupId, OrderByComparator<ApiRole> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchApiRoleException {
		return getPersistence()
				   .findByF_GID_PrevAndNext(apiRoleId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the api roles where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByF_GID(long groupId) {
		getPersistence().removeByF_GID(groupId);
	}

	/**
	* Returns the number of api roles where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching api roles
	*/
	public static int countByF_GID(long groupId) {
		return getPersistence().countByF_GID(groupId);
	}

	/**
	* Caches the api role in the entity cache if it is enabled.
	*
	* @param apiRole the api role
	*/
	public static void cacheResult(ApiRole apiRole) {
		getPersistence().cacheResult(apiRole);
	}

	/**
	* Caches the api roles in the entity cache if it is enabled.
	*
	* @param apiRoles the api roles
	*/
	public static void cacheResult(List<ApiRole> apiRoles) {
		getPersistence().cacheResult(apiRoles);
	}

	/**
	* Creates a new api role with the primary key. Does not add the api role to the database.
	*
	* @param apiRoleId the primary key for the new api role
	* @return the new api role
	*/
	public static ApiRole create(long apiRoleId) {
		return getPersistence().create(apiRoleId);
	}

	/**
	* Removes the api role with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param apiRoleId the primary key of the api role
	* @return the api role that was removed
	* @throws NoSuchApiRoleException if a api role with the primary key could not be found
	*/
	public static ApiRole remove(long apiRoleId)
		throws org.opencps.adminconfig.exception.NoSuchApiRoleException {
		return getPersistence().remove(apiRoleId);
	}

	public static ApiRole updateImpl(ApiRole apiRole) {
		return getPersistence().updateImpl(apiRole);
	}

	/**
	* Returns the api role with the primary key or throws a {@link NoSuchApiRoleException} if it could not be found.
	*
	* @param apiRoleId the primary key of the api role
	* @return the api role
	* @throws NoSuchApiRoleException if a api role with the primary key could not be found
	*/
	public static ApiRole findByPrimaryKey(long apiRoleId)
		throws org.opencps.adminconfig.exception.NoSuchApiRoleException {
		return getPersistence().findByPrimaryKey(apiRoleId);
	}

	/**
	* Returns the api role with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param apiRoleId the primary key of the api role
	* @return the api role, or <code>null</code> if a api role with the primary key could not be found
	*/
	public static ApiRole fetchByPrimaryKey(long apiRoleId) {
		return getPersistence().fetchByPrimaryKey(apiRoleId);
	}

	public static java.util.Map<java.io.Serializable, ApiRole> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the api roles.
	*
	* @return the api roles
	*/
	public static List<ApiRole> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the api roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApiRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of api roles
	* @param end the upper bound of the range of api roles (not inclusive)
	* @return the range of api roles
	*/
	public static List<ApiRole> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the api roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApiRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of api roles
	* @param end the upper bound of the range of api roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of api roles
	*/
	public static List<ApiRole> findAll(int start, int end,
		OrderByComparator<ApiRole> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the api roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApiRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of api roles
	* @param end the upper bound of the range of api roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of api roles
	*/
	public static List<ApiRole> findAll(int start, int end,
		OrderByComparator<ApiRole> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the api roles from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of api roles.
	*
	* @return the number of api roles
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ApiRolePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ApiRolePersistence, ApiRolePersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ApiRolePersistence.class);

		ServiceTracker<ApiRolePersistence, ApiRolePersistence> serviceTracker = new ServiceTracker<ApiRolePersistence, ApiRolePersistence>(bundle.getBundleContext(),
				ApiRolePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}