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

import org.opencps.usermgt.model.UserLogin;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the user login service. This utility wraps {@link org.opencps.usermgt.service.persistence.impl.UserLoginPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see UserLoginPersistence
 * @see org.opencps.usermgt.service.persistence.impl.UserLoginPersistenceImpl
 * @generated
 */
@ProviderType
public class UserLoginUtil {
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
	public static void clearCache(UserLogin userLogin) {
		getPersistence().clearCache(userLogin);
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
	public static List<UserLogin> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<UserLogin> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<UserLogin> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<UserLogin> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static UserLogin update(UserLogin userLogin) {
		return getPersistence().update(userLogin);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static UserLogin update(UserLogin userLogin,
		ServiceContext serviceContext) {
		return getPersistence().update(userLogin, serviceContext);
	}

	/**
	* Returns all the user logins where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching user logins
	*/
	public static List<UserLogin> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the user logins where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserLoginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of user logins
	* @param end the upper bound of the range of user logins (not inclusive)
	* @return the range of matching user logins
	*/
	public static List<UserLogin> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the user logins where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserLoginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of user logins
	* @param end the upper bound of the range of user logins (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user logins
	*/
	public static List<UserLogin> findByUuid(String uuid, int start, int end,
		OrderByComparator<UserLogin> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the user logins where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserLoginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of user logins
	* @param end the upper bound of the range of user logins (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user logins
	*/
	public static List<UserLogin> findByUuid(String uuid, int start, int end,
		OrderByComparator<UserLogin> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first user login in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user login
	* @throws NoSuchUserLoginException if a matching user login could not be found
	*/
	public static UserLogin findByUuid_First(String uuid,
		OrderByComparator<UserLogin> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchUserLoginException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first user login in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user login, or <code>null</code> if a matching user login could not be found
	*/
	public static UserLogin fetchByUuid_First(String uuid,
		OrderByComparator<UserLogin> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last user login in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user login
	* @throws NoSuchUserLoginException if a matching user login could not be found
	*/
	public static UserLogin findByUuid_Last(String uuid,
		OrderByComparator<UserLogin> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchUserLoginException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last user login in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user login, or <code>null</code> if a matching user login could not be found
	*/
	public static UserLogin fetchByUuid_Last(String uuid,
		OrderByComparator<UserLogin> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the user logins before and after the current user login in the ordered set where uuid = &#63;.
	*
	* @param userLoginId the primary key of the current user login
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user login
	* @throws NoSuchUserLoginException if a user login with the primary key could not be found
	*/
	public static UserLogin[] findByUuid_PrevAndNext(long userLoginId,
		String uuid, OrderByComparator<UserLogin> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchUserLoginException {
		return getPersistence()
				   .findByUuid_PrevAndNext(userLoginId, uuid, orderByComparator);
	}

	/**
	* Removes all the user logins where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of user logins where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching user logins
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the user login where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchUserLoginException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching user login
	* @throws NoSuchUserLoginException if a matching user login could not be found
	*/
	public static UserLogin findByUUID_G(String uuid, long groupId)
		throws org.opencps.usermgt.exception.NoSuchUserLoginException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the user login where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching user login, or <code>null</code> if a matching user login could not be found
	*/
	public static UserLogin fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the user login where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching user login, or <code>null</code> if a matching user login could not be found
	*/
	public static UserLogin fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the user login where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the user login that was removed
	*/
	public static UserLogin removeByUUID_G(String uuid, long groupId)
		throws org.opencps.usermgt.exception.NoSuchUserLoginException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of user logins where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching user logins
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the user logins where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching user logins
	*/
	public static List<UserLogin> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the user logins where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserLoginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of user logins
	* @param end the upper bound of the range of user logins (not inclusive)
	* @return the range of matching user logins
	*/
	public static List<UserLogin> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the user logins where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserLoginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of user logins
	* @param end the upper bound of the range of user logins (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user logins
	*/
	public static List<UserLogin> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<UserLogin> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the user logins where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserLoginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of user logins
	* @param end the upper bound of the range of user logins (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user logins
	*/
	public static List<UserLogin> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<UserLogin> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first user login in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user login
	* @throws NoSuchUserLoginException if a matching user login could not be found
	*/
	public static UserLogin findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<UserLogin> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchUserLoginException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first user login in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user login, or <code>null</code> if a matching user login could not be found
	*/
	public static UserLogin fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<UserLogin> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last user login in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user login
	* @throws NoSuchUserLoginException if a matching user login could not be found
	*/
	public static UserLogin findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<UserLogin> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchUserLoginException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last user login in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user login, or <code>null</code> if a matching user login could not be found
	*/
	public static UserLogin fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<UserLogin> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the user logins before and after the current user login in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param userLoginId the primary key of the current user login
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user login
	* @throws NoSuchUserLoginException if a user login with the primary key could not be found
	*/
	public static UserLogin[] findByUuid_C_PrevAndNext(long userLoginId,
		String uuid, long companyId,
		OrderByComparator<UserLogin> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchUserLoginException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(userLoginId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the user logins where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of user logins where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching user logins
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns the user login where userId = &#63; and sessionId = &#63; or throws a {@link NoSuchUserLoginException} if it could not be found.
	*
	* @param userId the user ID
	* @param sessionId the session ID
	* @return the matching user login
	* @throws NoSuchUserLoginException if a matching user login could not be found
	*/
	public static UserLogin findByU_S(long userId, String sessionId)
		throws org.opencps.usermgt.exception.NoSuchUserLoginException {
		return getPersistence().findByU_S(userId, sessionId);
	}

	/**
	* Returns the user login where userId = &#63; and sessionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param sessionId the session ID
	* @return the matching user login, or <code>null</code> if a matching user login could not be found
	*/
	public static UserLogin fetchByU_S(long userId, String sessionId) {
		return getPersistence().fetchByU_S(userId, sessionId);
	}

	/**
	* Returns the user login where userId = &#63; and sessionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param sessionId the session ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching user login, or <code>null</code> if a matching user login could not be found
	*/
	public static UserLogin fetchByU_S(long userId, String sessionId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByU_S(userId, sessionId, retrieveFromCache);
	}

	/**
	* Removes the user login where userId = &#63; and sessionId = &#63; from the database.
	*
	* @param userId the user ID
	* @param sessionId the session ID
	* @return the user login that was removed
	*/
	public static UserLogin removeByU_S(long userId, String sessionId)
		throws org.opencps.usermgt.exception.NoSuchUserLoginException {
		return getPersistence().removeByU_S(userId, sessionId);
	}

	/**
	* Returns the number of user logins where userId = &#63; and sessionId = &#63;.
	*
	* @param userId the user ID
	* @param sessionId the session ID
	* @return the number of matching user logins
	*/
	public static int countByU_S(long userId, String sessionId) {
		return getPersistence().countByU_S(userId, sessionId);
	}

	/**
	* Caches the user login in the entity cache if it is enabled.
	*
	* @param userLogin the user login
	*/
	public static void cacheResult(UserLogin userLogin) {
		getPersistence().cacheResult(userLogin);
	}

	/**
	* Caches the user logins in the entity cache if it is enabled.
	*
	* @param userLogins the user logins
	*/
	public static void cacheResult(List<UserLogin> userLogins) {
		getPersistence().cacheResult(userLogins);
	}

	/**
	* Creates a new user login with the primary key. Does not add the user login to the database.
	*
	* @param userLoginId the primary key for the new user login
	* @return the new user login
	*/
	public static UserLogin create(long userLoginId) {
		return getPersistence().create(userLoginId);
	}

	/**
	* Removes the user login with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userLoginId the primary key of the user login
	* @return the user login that was removed
	* @throws NoSuchUserLoginException if a user login with the primary key could not be found
	*/
	public static UserLogin remove(long userLoginId)
		throws org.opencps.usermgt.exception.NoSuchUserLoginException {
		return getPersistence().remove(userLoginId);
	}

	public static UserLogin updateImpl(UserLogin userLogin) {
		return getPersistence().updateImpl(userLogin);
	}

	/**
	* Returns the user login with the primary key or throws a {@link NoSuchUserLoginException} if it could not be found.
	*
	* @param userLoginId the primary key of the user login
	* @return the user login
	* @throws NoSuchUserLoginException if a user login with the primary key could not be found
	*/
	public static UserLogin findByPrimaryKey(long userLoginId)
		throws org.opencps.usermgt.exception.NoSuchUserLoginException {
		return getPersistence().findByPrimaryKey(userLoginId);
	}

	/**
	* Returns the user login with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param userLoginId the primary key of the user login
	* @return the user login, or <code>null</code> if a user login with the primary key could not be found
	*/
	public static UserLogin fetchByPrimaryKey(long userLoginId) {
		return getPersistence().fetchByPrimaryKey(userLoginId);
	}

	public static java.util.Map<java.io.Serializable, UserLogin> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the user logins.
	*
	* @return the user logins
	*/
	public static List<UserLogin> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the user logins.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserLoginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user logins
	* @param end the upper bound of the range of user logins (not inclusive)
	* @return the range of user logins
	*/
	public static List<UserLogin> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the user logins.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserLoginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user logins
	* @param end the upper bound of the range of user logins (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of user logins
	*/
	public static List<UserLogin> findAll(int start, int end,
		OrderByComparator<UserLogin> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the user logins.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserLoginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user logins
	* @param end the upper bound of the range of user logins (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of user logins
	*/
	public static List<UserLogin> findAll(int start, int end,
		OrderByComparator<UserLogin> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the user logins from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of user logins.
	*
	* @return the number of user logins
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static UserLoginPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<UserLoginPersistence, UserLoginPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(UserLoginPersistence.class);

		ServiceTracker<UserLoginPersistence, UserLoginPersistence> serviceTracker =
			new ServiceTracker<UserLoginPersistence, UserLoginPersistence>(bundle.getBundleContext(),
				UserLoginPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}