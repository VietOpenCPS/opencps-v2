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

import org.opencps.adminconfig.model.ApiManager;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the api manager service. This utility wraps {@link org.opencps.adminconfig.service.persistence.impl.ApiManagerPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author binhth
 * @see ApiManagerPersistence
 * @see org.opencps.adminconfig.service.persistence.impl.ApiManagerPersistenceImpl
 * @generated
 */
@ProviderType
public class ApiManagerUtil {
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
	public static void clearCache(ApiManager apiManager) {
		getPersistence().clearCache(apiManager);
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
	public static List<ApiManager> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ApiManager> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ApiManager> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ApiManager> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ApiManager update(ApiManager apiManager) {
		return getPersistence().update(apiManager);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ApiManager update(ApiManager apiManager,
		ServiceContext serviceContext) {
		return getPersistence().update(apiManager, serviceContext);
	}

	/**
	* Returns the api manager where apiManagerId = &#63; or throws a {@link NoSuchApiManagerException} if it could not be found.
	*
	* @param apiManagerId the api manager ID
	* @return the matching api manager
	* @throws NoSuchApiManagerException if a matching api manager could not be found
	*/
	public static ApiManager findByF_ID(long apiManagerId)
		throws org.opencps.adminconfig.exception.NoSuchApiManagerException {
		return getPersistence().findByF_ID(apiManagerId);
	}

	/**
	* Returns the api manager where apiManagerId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param apiManagerId the api manager ID
	* @return the matching api manager, or <code>null</code> if a matching api manager could not be found
	*/
	public static ApiManager fetchByF_ID(long apiManagerId) {
		return getPersistence().fetchByF_ID(apiManagerId);
	}

	/**
	* Returns the api manager where apiManagerId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param apiManagerId the api manager ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching api manager, or <code>null</code> if a matching api manager could not be found
	*/
	public static ApiManager fetchByF_ID(long apiManagerId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByF_ID(apiManagerId, retrieveFromCache);
	}

	/**
	* Removes the api manager where apiManagerId = &#63; from the database.
	*
	* @param apiManagerId the api manager ID
	* @return the api manager that was removed
	*/
	public static ApiManager removeByF_ID(long apiManagerId)
		throws org.opencps.adminconfig.exception.NoSuchApiManagerException {
		return getPersistence().removeByF_ID(apiManagerId);
	}

	/**
	* Returns the number of api managers where apiManagerId = &#63;.
	*
	* @param apiManagerId the api manager ID
	* @return the number of matching api managers
	*/
	public static int countByF_ID(long apiManagerId) {
		return getPersistence().countByF_ID(apiManagerId);
	}

	/**
	* Returns the api manager where apiCode = &#63; or throws a {@link NoSuchApiManagerException} if it could not be found.
	*
	* @param apiCode the api code
	* @return the matching api manager
	* @throws NoSuchApiManagerException if a matching api manager could not be found
	*/
	public static ApiManager findByF_apiCode(String apiCode)
		throws org.opencps.adminconfig.exception.NoSuchApiManagerException {
		return getPersistence().findByF_apiCode(apiCode);
	}

	/**
	* Returns the api manager where apiCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param apiCode the api code
	* @return the matching api manager, or <code>null</code> if a matching api manager could not be found
	*/
	public static ApiManager fetchByF_apiCode(String apiCode) {
		return getPersistence().fetchByF_apiCode(apiCode);
	}

	/**
	* Returns the api manager where apiCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param apiCode the api code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching api manager, or <code>null</code> if a matching api manager could not be found
	*/
	public static ApiManager fetchByF_apiCode(String apiCode,
		boolean retrieveFromCache) {
		return getPersistence().fetchByF_apiCode(apiCode, retrieveFromCache);
	}

	/**
	* Removes the api manager where apiCode = &#63; from the database.
	*
	* @param apiCode the api code
	* @return the api manager that was removed
	*/
	public static ApiManager removeByF_apiCode(String apiCode)
		throws org.opencps.adminconfig.exception.NoSuchApiManagerException {
		return getPersistence().removeByF_apiCode(apiCode);
	}

	/**
	* Returns the number of api managers where apiCode = &#63;.
	*
	* @param apiCode the api code
	* @return the number of matching api managers
	*/
	public static int countByF_apiCode(String apiCode) {
		return getPersistence().countByF_apiCode(apiCode);
	}

	/**
	* Returns all the api managers where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching api managers
	*/
	public static List<ApiManager> findByF_GID(long groupId) {
		return getPersistence().findByF_GID(groupId);
	}

	/**
	* Returns a range of all the api managers where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApiManagerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of api managers
	* @param end the upper bound of the range of api managers (not inclusive)
	* @return the range of matching api managers
	*/
	public static List<ApiManager> findByF_GID(long groupId, int start, int end) {
		return getPersistence().findByF_GID(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the api managers where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApiManagerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of api managers
	* @param end the upper bound of the range of api managers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching api managers
	*/
	public static List<ApiManager> findByF_GID(long groupId, int start,
		int end, OrderByComparator<ApiManager> orderByComparator) {
		return getPersistence()
				   .findByF_GID(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the api managers where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApiManagerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of api managers
	* @param end the upper bound of the range of api managers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching api managers
	*/
	public static List<ApiManager> findByF_GID(long groupId, int start,
		int end, OrderByComparator<ApiManager> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_GID(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first api manager in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching api manager
	* @throws NoSuchApiManagerException if a matching api manager could not be found
	*/
	public static ApiManager findByF_GID_First(long groupId,
		OrderByComparator<ApiManager> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchApiManagerException {
		return getPersistence().findByF_GID_First(groupId, orderByComparator);
	}

	/**
	* Returns the first api manager in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching api manager, or <code>null</code> if a matching api manager could not be found
	*/
	public static ApiManager fetchByF_GID_First(long groupId,
		OrderByComparator<ApiManager> orderByComparator) {
		return getPersistence().fetchByF_GID_First(groupId, orderByComparator);
	}

	/**
	* Returns the last api manager in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching api manager
	* @throws NoSuchApiManagerException if a matching api manager could not be found
	*/
	public static ApiManager findByF_GID_Last(long groupId,
		OrderByComparator<ApiManager> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchApiManagerException {
		return getPersistence().findByF_GID_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last api manager in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching api manager, or <code>null</code> if a matching api manager could not be found
	*/
	public static ApiManager fetchByF_GID_Last(long groupId,
		OrderByComparator<ApiManager> orderByComparator) {
		return getPersistence().fetchByF_GID_Last(groupId, orderByComparator);
	}

	/**
	* Returns the api managers before and after the current api manager in the ordered set where groupId = &#63;.
	*
	* @param apiManagerId the primary key of the current api manager
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next api manager
	* @throws NoSuchApiManagerException if a api manager with the primary key could not be found
	*/
	public static ApiManager[] findByF_GID_PrevAndNext(long apiManagerId,
		long groupId, OrderByComparator<ApiManager> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchApiManagerException {
		return getPersistence()
				   .findByF_GID_PrevAndNext(apiManagerId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the api managers where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByF_GID(long groupId) {
		getPersistence().removeByF_GID(groupId);
	}

	/**
	* Returns the number of api managers where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching api managers
	*/
	public static int countByF_GID(long groupId) {
		return getPersistence().countByF_GID(groupId);
	}

	/**
	* Caches the api manager in the entity cache if it is enabled.
	*
	* @param apiManager the api manager
	*/
	public static void cacheResult(ApiManager apiManager) {
		getPersistence().cacheResult(apiManager);
	}

	/**
	* Caches the api managers in the entity cache if it is enabled.
	*
	* @param apiManagers the api managers
	*/
	public static void cacheResult(List<ApiManager> apiManagers) {
		getPersistence().cacheResult(apiManagers);
	}

	/**
	* Creates a new api manager with the primary key. Does not add the api manager to the database.
	*
	* @param apiManagerId the primary key for the new api manager
	* @return the new api manager
	*/
	public static ApiManager create(long apiManagerId) {
		return getPersistence().create(apiManagerId);
	}

	/**
	* Removes the api manager with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param apiManagerId the primary key of the api manager
	* @return the api manager that was removed
	* @throws NoSuchApiManagerException if a api manager with the primary key could not be found
	*/
	public static ApiManager remove(long apiManagerId)
		throws org.opencps.adminconfig.exception.NoSuchApiManagerException {
		return getPersistence().remove(apiManagerId);
	}

	public static ApiManager updateImpl(ApiManager apiManager) {
		return getPersistence().updateImpl(apiManager);
	}

	/**
	* Returns the api manager with the primary key or throws a {@link NoSuchApiManagerException} if it could not be found.
	*
	* @param apiManagerId the primary key of the api manager
	* @return the api manager
	* @throws NoSuchApiManagerException if a api manager with the primary key could not be found
	*/
	public static ApiManager findByPrimaryKey(long apiManagerId)
		throws org.opencps.adminconfig.exception.NoSuchApiManagerException {
		return getPersistence().findByPrimaryKey(apiManagerId);
	}

	/**
	* Returns the api manager with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param apiManagerId the primary key of the api manager
	* @return the api manager, or <code>null</code> if a api manager with the primary key could not be found
	*/
	public static ApiManager fetchByPrimaryKey(long apiManagerId) {
		return getPersistence().fetchByPrimaryKey(apiManagerId);
	}

	public static java.util.Map<java.io.Serializable, ApiManager> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the api managers.
	*
	* @return the api managers
	*/
	public static List<ApiManager> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the api managers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApiManagerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of api managers
	* @param end the upper bound of the range of api managers (not inclusive)
	* @return the range of api managers
	*/
	public static List<ApiManager> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the api managers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApiManagerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of api managers
	* @param end the upper bound of the range of api managers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of api managers
	*/
	public static List<ApiManager> findAll(int start, int end,
		OrderByComparator<ApiManager> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the api managers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApiManagerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of api managers
	* @param end the upper bound of the range of api managers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of api managers
	*/
	public static List<ApiManager> findAll(int start, int end,
		OrderByComparator<ApiManager> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the api managers from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of api managers.
	*
	* @return the number of api managers
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ApiManagerPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ApiManagerPersistence, ApiManagerPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ApiManagerPersistence.class);

		ServiceTracker<ApiManagerPersistence, ApiManagerPersistence> serviceTracker =
			new ServiceTracker<ApiManagerPersistence, ApiManagerPersistence>(bundle.getBundleContext(),
				ApiManagerPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}