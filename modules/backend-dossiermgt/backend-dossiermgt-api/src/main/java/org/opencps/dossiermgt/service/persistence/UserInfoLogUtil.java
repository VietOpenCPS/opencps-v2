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

import org.opencps.dossiermgt.model.UserInfoLog;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the user info log service. This utility wraps {@link org.opencps.dossiermgt.service.persistence.impl.UserInfoLogPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see UserInfoLogPersistence
 * @see org.opencps.dossiermgt.service.persistence.impl.UserInfoLogPersistenceImpl
 * @generated
 */
@ProviderType
public class UserInfoLogUtil {
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
	public static void clearCache(UserInfoLog userInfoLog) {
		getPersistence().clearCache(userInfoLog);
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
	public static List<UserInfoLog> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<UserInfoLog> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<UserInfoLog> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<UserInfoLog> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static UserInfoLog update(UserInfoLog userInfoLog) {
		return getPersistence().update(userInfoLog);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static UserInfoLog update(UserInfoLog userInfoLog,
		ServiceContext serviceContext) {
		return getPersistence().update(userInfoLog, serviceContext);
	}

	/**
	* Returns all the user info logs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching user info logs
	*/
	public static List<UserInfoLog> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the user info logs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserInfoLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of user info logs
	* @param end the upper bound of the range of user info logs (not inclusive)
	* @return the range of matching user info logs
	*/
	public static List<UserInfoLog> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the user info logs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserInfoLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of user info logs
	* @param end the upper bound of the range of user info logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user info logs
	*/
	public static List<UserInfoLog> findByUuid(String uuid, int start, int end,
		OrderByComparator<UserInfoLog> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the user info logs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserInfoLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of user info logs
	* @param end the upper bound of the range of user info logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user info logs
	*/
	public static List<UserInfoLog> findByUuid(String uuid, int start, int end,
		OrderByComparator<UserInfoLog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first user info log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user info log
	* @throws NoSuchUserInfoLogException if a matching user info log could not be found
	*/
	public static UserInfoLog findByUuid_First(String uuid,
		OrderByComparator<UserInfoLog> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchUserInfoLogException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first user info log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user info log, or <code>null</code> if a matching user info log could not be found
	*/
	public static UserInfoLog fetchByUuid_First(String uuid,
		OrderByComparator<UserInfoLog> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last user info log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user info log
	* @throws NoSuchUserInfoLogException if a matching user info log could not be found
	*/
	public static UserInfoLog findByUuid_Last(String uuid,
		OrderByComparator<UserInfoLog> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchUserInfoLogException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last user info log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user info log, or <code>null</code> if a matching user info log could not be found
	*/
	public static UserInfoLog fetchByUuid_Last(String uuid,
		OrderByComparator<UserInfoLog> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the user info logs before and after the current user info log in the ordered set where uuid = &#63;.
	*
	* @param userLogId the primary key of the current user info log
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user info log
	* @throws NoSuchUserInfoLogException if a user info log with the primary key could not be found
	*/
	public static UserInfoLog[] findByUuid_PrevAndNext(long userLogId,
		String uuid, OrderByComparator<UserInfoLog> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchUserInfoLogException {
		return getPersistence()
				   .findByUuid_PrevAndNext(userLogId, uuid, orderByComparator);
	}

	/**
	* Removes all the user info logs where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of user info logs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching user info logs
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns all the user info logs where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching user info logs
	*/
	public static List<UserInfoLog> findByF_USERID(long userId) {
		return getPersistence().findByF_USERID(userId);
	}

	/**
	* Returns a range of all the user info logs where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserInfoLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of user info logs
	* @param end the upper bound of the range of user info logs (not inclusive)
	* @return the range of matching user info logs
	*/
	public static List<UserInfoLog> findByF_USERID(long userId, int start,
		int end) {
		return getPersistence().findByF_USERID(userId, start, end);
	}

	/**
	* Returns an ordered range of all the user info logs where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserInfoLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of user info logs
	* @param end the upper bound of the range of user info logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user info logs
	*/
	public static List<UserInfoLog> findByF_USERID(long userId, int start,
		int end, OrderByComparator<UserInfoLog> orderByComparator) {
		return getPersistence()
				   .findByF_USERID(userId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the user info logs where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserInfoLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of user info logs
	* @param end the upper bound of the range of user info logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user info logs
	*/
	public static List<UserInfoLog> findByF_USERID(long userId, int start,
		int end, OrderByComparator<UserInfoLog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_USERID(userId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first user info log in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user info log
	* @throws NoSuchUserInfoLogException if a matching user info log could not be found
	*/
	public static UserInfoLog findByF_USERID_First(long userId,
		OrderByComparator<UserInfoLog> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchUserInfoLogException {
		return getPersistence().findByF_USERID_First(userId, orderByComparator);
	}

	/**
	* Returns the first user info log in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user info log, or <code>null</code> if a matching user info log could not be found
	*/
	public static UserInfoLog fetchByF_USERID_First(long userId,
		OrderByComparator<UserInfoLog> orderByComparator) {
		return getPersistence().fetchByF_USERID_First(userId, orderByComparator);
	}

	/**
	* Returns the last user info log in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user info log
	* @throws NoSuchUserInfoLogException if a matching user info log could not be found
	*/
	public static UserInfoLog findByF_USERID_Last(long userId,
		OrderByComparator<UserInfoLog> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchUserInfoLogException {
		return getPersistence().findByF_USERID_Last(userId, orderByComparator);
	}

	/**
	* Returns the last user info log in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user info log, or <code>null</code> if a matching user info log could not be found
	*/
	public static UserInfoLog fetchByF_USERID_Last(long userId,
		OrderByComparator<UserInfoLog> orderByComparator) {
		return getPersistence().fetchByF_USERID_Last(userId, orderByComparator);
	}

	/**
	* Returns the user info logs before and after the current user info log in the ordered set where userId = &#63;.
	*
	* @param userLogId the primary key of the current user info log
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user info log
	* @throws NoSuchUserInfoLogException if a user info log with the primary key could not be found
	*/
	public static UserInfoLog[] findByF_USERID_PrevAndNext(long userLogId,
		long userId, OrderByComparator<UserInfoLog> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchUserInfoLogException {
		return getPersistence()
				   .findByF_USERID_PrevAndNext(userLogId, userId,
			orderByComparator);
	}

	/**
	* Removes all the user info logs where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public static void removeByF_USERID(long userId) {
		getPersistence().removeByF_USERID(userId);
	}

	/**
	* Returns the number of user info logs where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching user info logs
	*/
	public static int countByF_USERID(long userId) {
		return getPersistence().countByF_USERID(userId);
	}

	/**
	* Caches the user info log in the entity cache if it is enabled.
	*
	* @param userInfoLog the user info log
	*/
	public static void cacheResult(UserInfoLog userInfoLog) {
		getPersistence().cacheResult(userInfoLog);
	}

	/**
	* Caches the user info logs in the entity cache if it is enabled.
	*
	* @param userInfoLogs the user info logs
	*/
	public static void cacheResult(List<UserInfoLog> userInfoLogs) {
		getPersistence().cacheResult(userInfoLogs);
	}

	/**
	* Creates a new user info log with the primary key. Does not add the user info log to the database.
	*
	* @param userLogId the primary key for the new user info log
	* @return the new user info log
	*/
	public static UserInfoLog create(long userLogId) {
		return getPersistence().create(userLogId);
	}

	/**
	* Removes the user info log with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userLogId the primary key of the user info log
	* @return the user info log that was removed
	* @throws NoSuchUserInfoLogException if a user info log with the primary key could not be found
	*/
	public static UserInfoLog remove(long userLogId)
		throws org.opencps.dossiermgt.exception.NoSuchUserInfoLogException {
		return getPersistence().remove(userLogId);
	}

	public static UserInfoLog updateImpl(UserInfoLog userInfoLog) {
		return getPersistence().updateImpl(userInfoLog);
	}

	/**
	* Returns the user info log with the primary key or throws a {@link NoSuchUserInfoLogException} if it could not be found.
	*
	* @param userLogId the primary key of the user info log
	* @return the user info log
	* @throws NoSuchUserInfoLogException if a user info log with the primary key could not be found
	*/
	public static UserInfoLog findByPrimaryKey(long userLogId)
		throws org.opencps.dossiermgt.exception.NoSuchUserInfoLogException {
		return getPersistence().findByPrimaryKey(userLogId);
	}

	/**
	* Returns the user info log with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param userLogId the primary key of the user info log
	* @return the user info log, or <code>null</code> if a user info log with the primary key could not be found
	*/
	public static UserInfoLog fetchByPrimaryKey(long userLogId) {
		return getPersistence().fetchByPrimaryKey(userLogId);
	}

	public static java.util.Map<java.io.Serializable, UserInfoLog> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the user info logs.
	*
	* @return the user info logs
	*/
	public static List<UserInfoLog> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the user info logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserInfoLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user info logs
	* @param end the upper bound of the range of user info logs (not inclusive)
	* @return the range of user info logs
	*/
	public static List<UserInfoLog> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the user info logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserInfoLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user info logs
	* @param end the upper bound of the range of user info logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of user info logs
	*/
	public static List<UserInfoLog> findAll(int start, int end,
		OrderByComparator<UserInfoLog> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the user info logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserInfoLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user info logs
	* @param end the upper bound of the range of user info logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of user info logs
	*/
	public static List<UserInfoLog> findAll(int start, int end,
		OrderByComparator<UserInfoLog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the user info logs from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of user info logs.
	*
	* @return the number of user info logs
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static UserInfoLogPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<UserInfoLogPersistence, UserInfoLogPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(UserInfoLogPersistence.class);

		ServiceTracker<UserInfoLogPersistence, UserInfoLogPersistence> serviceTracker =
			new ServiceTracker<UserInfoLogPersistence, UserInfoLogPersistence>(bundle.getBundleContext(),
				UserInfoLogPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}