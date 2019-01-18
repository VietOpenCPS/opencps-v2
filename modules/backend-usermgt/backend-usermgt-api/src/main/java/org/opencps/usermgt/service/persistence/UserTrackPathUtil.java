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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.usermgt.model.UserTrackPath;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the user track path service. This utility wraps {@link org.opencps.usermgt.service.persistence.impl.UserTrackPathPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see UserTrackPathPersistence
 * @see org.opencps.usermgt.service.persistence.impl.UserTrackPathPersistenceImpl
 * @generated
 */
@ProviderType
public class UserTrackPathUtil {
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
	public static void clearCache(UserTrackPath userTrackPath) {
		getPersistence().clearCache(userTrackPath);
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
	public static List<UserTrackPath> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<UserTrackPath> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<UserTrackPath> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<UserTrackPath> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static UserTrackPath update(UserTrackPath userTrackPath) {
		return getPersistence().update(userTrackPath);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static UserTrackPath update(UserTrackPath userTrackPath,
		ServiceContext serviceContext) {
		return getPersistence().update(userTrackPath, serviceContext);
	}

	/**
	* Returns all the user track paths where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching user track paths
	*/
	public static List<UserTrackPath> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the user track paths where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserTrackPathModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of user track paths
	* @param end the upper bound of the range of user track paths (not inclusive)
	* @return the range of matching user track paths
	*/
	public static List<UserTrackPath> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the user track paths where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserTrackPathModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of user track paths
	* @param end the upper bound of the range of user track paths (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user track paths
	*/
	public static List<UserTrackPath> findByUuid(String uuid, int start,
		int end, OrderByComparator<UserTrackPath> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the user track paths where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserTrackPathModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of user track paths
	* @param end the upper bound of the range of user track paths (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user track paths
	*/
	public static List<UserTrackPath> findByUuid(String uuid, int start,
		int end, OrderByComparator<UserTrackPath> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first user track path in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user track path
	* @throws NoSuchUserTrackPathException if a matching user track path could not be found
	*/
	public static UserTrackPath findByUuid_First(String uuid,
		OrderByComparator<UserTrackPath> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchUserTrackPathException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first user track path in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user track path, or <code>null</code> if a matching user track path could not be found
	*/
	public static UserTrackPath fetchByUuid_First(String uuid,
		OrderByComparator<UserTrackPath> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last user track path in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user track path
	* @throws NoSuchUserTrackPathException if a matching user track path could not be found
	*/
	public static UserTrackPath findByUuid_Last(String uuid,
		OrderByComparator<UserTrackPath> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchUserTrackPathException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last user track path in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user track path, or <code>null</code> if a matching user track path could not be found
	*/
	public static UserTrackPath fetchByUuid_Last(String uuid,
		OrderByComparator<UserTrackPath> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the user track paths before and after the current user track path in the ordered set where uuid = &#63;.
	*
	* @param userTrackPathId the primary key of the current user track path
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user track path
	* @throws NoSuchUserTrackPathException if a user track path with the primary key could not be found
	*/
	public static UserTrackPath[] findByUuid_PrevAndNext(long userTrackPathId,
		String uuid, OrderByComparator<UserTrackPath> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchUserTrackPathException {
		return getPersistence()
				   .findByUuid_PrevAndNext(userTrackPathId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the user track paths where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of user track paths where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching user track paths
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns all the user track paths where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching user track paths
	*/
	public static List<UserTrackPath> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the user track paths where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserTrackPathModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of user track paths
	* @param end the upper bound of the range of user track paths (not inclusive)
	* @return the range of matching user track paths
	*/
	public static List<UserTrackPath> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the user track paths where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserTrackPathModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of user track paths
	* @param end the upper bound of the range of user track paths (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user track paths
	*/
	public static List<UserTrackPath> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<UserTrackPath> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the user track paths where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserTrackPathModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of user track paths
	* @param end the upper bound of the range of user track paths (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user track paths
	*/
	public static List<UserTrackPath> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<UserTrackPath> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first user track path in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user track path
	* @throws NoSuchUserTrackPathException if a matching user track path could not be found
	*/
	public static UserTrackPath findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<UserTrackPath> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchUserTrackPathException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first user track path in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user track path, or <code>null</code> if a matching user track path could not be found
	*/
	public static UserTrackPath fetchByUuid_C_First(String uuid,
		long companyId, OrderByComparator<UserTrackPath> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last user track path in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user track path
	* @throws NoSuchUserTrackPathException if a matching user track path could not be found
	*/
	public static UserTrackPath findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<UserTrackPath> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchUserTrackPathException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last user track path in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user track path, or <code>null</code> if a matching user track path could not be found
	*/
	public static UserTrackPath fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<UserTrackPath> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the user track paths before and after the current user track path in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param userTrackPathId the primary key of the current user track path
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user track path
	* @throws NoSuchUserTrackPathException if a user track path with the primary key could not be found
	*/
	public static UserTrackPath[] findByUuid_C_PrevAndNext(
		long userTrackPathId, String uuid, long companyId,
		OrderByComparator<UserTrackPath> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchUserTrackPathException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(userTrackPathId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the user track paths where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of user track paths where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching user track paths
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Caches the user track path in the entity cache if it is enabled.
	*
	* @param userTrackPath the user track path
	*/
	public static void cacheResult(UserTrackPath userTrackPath) {
		getPersistence().cacheResult(userTrackPath);
	}

	/**
	* Caches the user track paths in the entity cache if it is enabled.
	*
	* @param userTrackPaths the user track paths
	*/
	public static void cacheResult(List<UserTrackPath> userTrackPaths) {
		getPersistence().cacheResult(userTrackPaths);
	}

	/**
	* Creates a new user track path with the primary key. Does not add the user track path to the database.
	*
	* @param userTrackPathId the primary key for the new user track path
	* @return the new user track path
	*/
	public static UserTrackPath create(long userTrackPathId) {
		return getPersistence().create(userTrackPathId);
	}

	/**
	* Removes the user track path with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userTrackPathId the primary key of the user track path
	* @return the user track path that was removed
	* @throws NoSuchUserTrackPathException if a user track path with the primary key could not be found
	*/
	public static UserTrackPath remove(long userTrackPathId)
		throws org.opencps.usermgt.exception.NoSuchUserTrackPathException {
		return getPersistence().remove(userTrackPathId);
	}

	public static UserTrackPath updateImpl(UserTrackPath userTrackPath) {
		return getPersistence().updateImpl(userTrackPath);
	}

	/**
	* Returns the user track path with the primary key or throws a {@link NoSuchUserTrackPathException} if it could not be found.
	*
	* @param userTrackPathId the primary key of the user track path
	* @return the user track path
	* @throws NoSuchUserTrackPathException if a user track path with the primary key could not be found
	*/
	public static UserTrackPath findByPrimaryKey(long userTrackPathId)
		throws org.opencps.usermgt.exception.NoSuchUserTrackPathException {
		return getPersistence().findByPrimaryKey(userTrackPathId);
	}

	/**
	* Returns the user track path with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param userTrackPathId the primary key of the user track path
	* @return the user track path, or <code>null</code> if a user track path with the primary key could not be found
	*/
	public static UserTrackPath fetchByPrimaryKey(long userTrackPathId) {
		return getPersistence().fetchByPrimaryKey(userTrackPathId);
	}

	public static java.util.Map<java.io.Serializable, UserTrackPath> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the user track paths.
	*
	* @return the user track paths
	*/
	public static List<UserTrackPath> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the user track paths.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserTrackPathModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user track paths
	* @param end the upper bound of the range of user track paths (not inclusive)
	* @return the range of user track paths
	*/
	public static List<UserTrackPath> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the user track paths.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserTrackPathModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user track paths
	* @param end the upper bound of the range of user track paths (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of user track paths
	*/
	public static List<UserTrackPath> findAll(int start, int end,
		OrderByComparator<UserTrackPath> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the user track paths.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserTrackPathModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user track paths
	* @param end the upper bound of the range of user track paths (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of user track paths
	*/
	public static List<UserTrackPath> findAll(int start, int end,
		OrderByComparator<UserTrackPath> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the user track paths from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of user track paths.
	*
	* @return the number of user track paths
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static UserTrackPathPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<UserTrackPathPersistence, UserTrackPathPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(UserTrackPathPersistence.class);

		ServiceTracker<UserTrackPathPersistence, UserTrackPathPersistence> serviceTracker =
			new ServiceTracker<UserTrackPathPersistence, UserTrackPathPersistence>(bundle.getBundleContext(),
				UserTrackPathPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}