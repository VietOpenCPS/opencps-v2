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

import org.opencps.dossiermgt.exception.NoSuchUserInfoLogException;
import org.opencps.dossiermgt.model.UserInfoLog;

/**
 * The persistence interface for the user info log service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.dossiermgt.service.persistence.impl.UserInfoLogPersistenceImpl
 * @see UserInfoLogUtil
 * @generated
 */
@ProviderType
public interface UserInfoLogPersistence extends BasePersistence<UserInfoLog> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link UserInfoLogUtil} to access the user info log persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the user info logs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching user info logs
	*/
	public java.util.List<UserInfoLog> findByUuid(String uuid);

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
	public java.util.List<UserInfoLog> findByUuid(String uuid, int start,
		int end);

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
	public java.util.List<UserInfoLog> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserInfoLog> orderByComparator);

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
	public java.util.List<UserInfoLog> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserInfoLog> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first user info log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user info log
	* @throws NoSuchUserInfoLogException if a matching user info log could not be found
	*/
	public UserInfoLog findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<UserInfoLog> orderByComparator)
		throws NoSuchUserInfoLogException;

	/**
	* Returns the first user info log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user info log, or <code>null</code> if a matching user info log could not be found
	*/
	public UserInfoLog fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<UserInfoLog> orderByComparator);

	/**
	* Returns the last user info log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user info log
	* @throws NoSuchUserInfoLogException if a matching user info log could not be found
	*/
	public UserInfoLog findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<UserInfoLog> orderByComparator)
		throws NoSuchUserInfoLogException;

	/**
	* Returns the last user info log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user info log, or <code>null</code> if a matching user info log could not be found
	*/
	public UserInfoLog fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<UserInfoLog> orderByComparator);

	/**
	* Returns the user info logs before and after the current user info log in the ordered set where uuid = &#63;.
	*
	* @param userLogId the primary key of the current user info log
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user info log
	* @throws NoSuchUserInfoLogException if a user info log with the primary key could not be found
	*/
	public UserInfoLog[] findByUuid_PrevAndNext(long userLogId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<UserInfoLog> orderByComparator)
		throws NoSuchUserInfoLogException;

	/**
	* Removes all the user info logs where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of user info logs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching user info logs
	*/
	public int countByUuid(String uuid);

	/**
	* Returns all the user info logs where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching user info logs
	*/
	public java.util.List<UserInfoLog> findByF_USERID(long userId);

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
	public java.util.List<UserInfoLog> findByF_USERID(long userId, int start,
		int end);

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
	public java.util.List<UserInfoLog> findByF_USERID(long userId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserInfoLog> orderByComparator);

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
	public java.util.List<UserInfoLog> findByF_USERID(long userId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserInfoLog> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first user info log in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user info log
	* @throws NoSuchUserInfoLogException if a matching user info log could not be found
	*/
	public UserInfoLog findByF_USERID_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<UserInfoLog> orderByComparator)
		throws NoSuchUserInfoLogException;

	/**
	* Returns the first user info log in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user info log, or <code>null</code> if a matching user info log could not be found
	*/
	public UserInfoLog fetchByF_USERID_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<UserInfoLog> orderByComparator);

	/**
	* Returns the last user info log in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user info log
	* @throws NoSuchUserInfoLogException if a matching user info log could not be found
	*/
	public UserInfoLog findByF_USERID_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<UserInfoLog> orderByComparator)
		throws NoSuchUserInfoLogException;

	/**
	* Returns the last user info log in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user info log, or <code>null</code> if a matching user info log could not be found
	*/
	public UserInfoLog fetchByF_USERID_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<UserInfoLog> orderByComparator);

	/**
	* Returns the user info logs before and after the current user info log in the ordered set where userId = &#63;.
	*
	* @param userLogId the primary key of the current user info log
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user info log
	* @throws NoSuchUserInfoLogException if a user info log with the primary key could not be found
	*/
	public UserInfoLog[] findByF_USERID_PrevAndNext(long userLogId,
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<UserInfoLog> orderByComparator)
		throws NoSuchUserInfoLogException;

	/**
	* Removes all the user info logs where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public void removeByF_USERID(long userId);

	/**
	* Returns the number of user info logs where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching user info logs
	*/
	public int countByF_USERID(long userId);

	/**
	* Caches the user info log in the entity cache if it is enabled.
	*
	* @param userInfoLog the user info log
	*/
	public void cacheResult(UserInfoLog userInfoLog);

	/**
	* Caches the user info logs in the entity cache if it is enabled.
	*
	* @param userInfoLogs the user info logs
	*/
	public void cacheResult(java.util.List<UserInfoLog> userInfoLogs);

	/**
	* Creates a new user info log with the primary key. Does not add the user info log to the database.
	*
	* @param userLogId the primary key for the new user info log
	* @return the new user info log
	*/
	public UserInfoLog create(long userLogId);

	/**
	* Removes the user info log with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userLogId the primary key of the user info log
	* @return the user info log that was removed
	* @throws NoSuchUserInfoLogException if a user info log with the primary key could not be found
	*/
	public UserInfoLog remove(long userLogId) throws NoSuchUserInfoLogException;

	public UserInfoLog updateImpl(UserInfoLog userInfoLog);

	/**
	* Returns the user info log with the primary key or throws a {@link NoSuchUserInfoLogException} if it could not be found.
	*
	* @param userLogId the primary key of the user info log
	* @return the user info log
	* @throws NoSuchUserInfoLogException if a user info log with the primary key could not be found
	*/
	public UserInfoLog findByPrimaryKey(long userLogId)
		throws NoSuchUserInfoLogException;

	/**
	* Returns the user info log with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param userLogId the primary key of the user info log
	* @return the user info log, or <code>null</code> if a user info log with the primary key could not be found
	*/
	public UserInfoLog fetchByPrimaryKey(long userLogId);

	@Override
	public java.util.Map<java.io.Serializable, UserInfoLog> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the user info logs.
	*
	* @return the user info logs
	*/
	public java.util.List<UserInfoLog> findAll();

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
	public java.util.List<UserInfoLog> findAll(int start, int end);

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
	public java.util.List<UserInfoLog> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserInfoLog> orderByComparator);

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
	public java.util.List<UserInfoLog> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserInfoLog> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the user info logs from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of user info logs.
	*
	* @return the number of user info logs
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}