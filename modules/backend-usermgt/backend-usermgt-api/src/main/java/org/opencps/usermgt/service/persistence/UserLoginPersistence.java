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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.usermgt.exception.NoSuchUserLoginException;
import org.opencps.usermgt.model.UserLogin;

/**
 * The persistence interface for the user login service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see org.opencps.usermgt.service.persistence.impl.UserLoginPersistenceImpl
 * @see UserLoginUtil
 * @generated
 */
@ProviderType
public interface UserLoginPersistence extends BasePersistence<UserLogin> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link UserLoginUtil} to access the user login persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the user logins where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching user logins
	*/
	public java.util.List<UserLogin> findByUuid(String uuid);

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
	public java.util.List<UserLogin> findByUuid(String uuid, int start, int end);

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
	public java.util.List<UserLogin> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserLogin> orderByComparator);

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
	public java.util.List<UserLogin> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserLogin> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first user login in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user login
	* @throws NoSuchUserLoginException if a matching user login could not be found
	*/
	public UserLogin findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<UserLogin> orderByComparator)
		throws NoSuchUserLoginException;

	/**
	* Returns the first user login in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user login, or <code>null</code> if a matching user login could not be found
	*/
	public UserLogin fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<UserLogin> orderByComparator);

	/**
	* Returns the last user login in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user login
	* @throws NoSuchUserLoginException if a matching user login could not be found
	*/
	public UserLogin findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<UserLogin> orderByComparator)
		throws NoSuchUserLoginException;

	/**
	* Returns the last user login in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user login, or <code>null</code> if a matching user login could not be found
	*/
	public UserLogin fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<UserLogin> orderByComparator);

	/**
	* Returns the user logins before and after the current user login in the ordered set where uuid = &#63;.
	*
	* @param userLoginId the primary key of the current user login
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user login
	* @throws NoSuchUserLoginException if a user login with the primary key could not be found
	*/
	public UserLogin[] findByUuid_PrevAndNext(long userLoginId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<UserLogin> orderByComparator)
		throws NoSuchUserLoginException;

	/**
	* Removes all the user logins where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of user logins where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching user logins
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the user login where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchUserLoginException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching user login
	* @throws NoSuchUserLoginException if a matching user login could not be found
	*/
	public UserLogin findByUUID_G(String uuid, long groupId)
		throws NoSuchUserLoginException;

	/**
	* Returns the user login where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching user login, or <code>null</code> if a matching user login could not be found
	*/
	public UserLogin fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the user login where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching user login, or <code>null</code> if a matching user login could not be found
	*/
	public UserLogin fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the user login where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the user login that was removed
	*/
	public UserLogin removeByUUID_G(String uuid, long groupId)
		throws NoSuchUserLoginException;

	/**
	* Returns the number of user logins where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching user logins
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the user logins where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching user logins
	*/
	public java.util.List<UserLogin> findByUuid_C(String uuid, long companyId);

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
	public java.util.List<UserLogin> findByUuid_C(String uuid, long companyId,
		int start, int end);

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
	public java.util.List<UserLogin> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserLogin> orderByComparator);

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
	public java.util.List<UserLogin> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserLogin> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first user login in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user login
	* @throws NoSuchUserLoginException if a matching user login could not be found
	*/
	public UserLogin findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<UserLogin> orderByComparator)
		throws NoSuchUserLoginException;

	/**
	* Returns the first user login in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user login, or <code>null</code> if a matching user login could not be found
	*/
	public UserLogin fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<UserLogin> orderByComparator);

	/**
	* Returns the last user login in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user login
	* @throws NoSuchUserLoginException if a matching user login could not be found
	*/
	public UserLogin findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<UserLogin> orderByComparator)
		throws NoSuchUserLoginException;

	/**
	* Returns the last user login in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user login, or <code>null</code> if a matching user login could not be found
	*/
	public UserLogin fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<UserLogin> orderByComparator);

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
	public UserLogin[] findByUuid_C_PrevAndNext(long userLoginId, String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<UserLogin> orderByComparator)
		throws NoSuchUserLoginException;

	/**
	* Removes all the user logins where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of user logins where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching user logins
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Caches the user login in the entity cache if it is enabled.
	*
	* @param userLogin the user login
	*/
	public void cacheResult(UserLogin userLogin);

	/**
	* Caches the user logins in the entity cache if it is enabled.
	*
	* @param userLogins the user logins
	*/
	public void cacheResult(java.util.List<UserLogin> userLogins);

	/**
	* Creates a new user login with the primary key. Does not add the user login to the database.
	*
	* @param userLoginId the primary key for the new user login
	* @return the new user login
	*/
	public UserLogin create(long userLoginId);

	/**
	* Removes the user login with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userLoginId the primary key of the user login
	* @return the user login that was removed
	* @throws NoSuchUserLoginException if a user login with the primary key could not be found
	*/
	public UserLogin remove(long userLoginId) throws NoSuchUserLoginException;

	public UserLogin updateImpl(UserLogin userLogin);

	/**
	* Returns the user login with the primary key or throws a {@link NoSuchUserLoginException} if it could not be found.
	*
	* @param userLoginId the primary key of the user login
	* @return the user login
	* @throws NoSuchUserLoginException if a user login with the primary key could not be found
	*/
	public UserLogin findByPrimaryKey(long userLoginId)
		throws NoSuchUserLoginException;

	/**
	* Returns the user login with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param userLoginId the primary key of the user login
	* @return the user login, or <code>null</code> if a user login with the primary key could not be found
	*/
	public UserLogin fetchByPrimaryKey(long userLoginId);

	@Override
	public java.util.Map<java.io.Serializable, UserLogin> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the user logins.
	*
	* @return the user logins
	*/
	public java.util.List<UserLogin> findAll();

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
	public java.util.List<UserLogin> findAll(int start, int end);

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
	public java.util.List<UserLogin> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserLogin> orderByComparator);

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
	public java.util.List<UserLogin> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserLogin> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the user logins from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of user logins.
	*
	* @return the number of user logins
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}