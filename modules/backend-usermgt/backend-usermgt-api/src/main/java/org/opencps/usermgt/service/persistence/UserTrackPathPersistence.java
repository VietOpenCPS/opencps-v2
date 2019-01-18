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

import org.opencps.usermgt.exception.NoSuchUserTrackPathException;
import org.opencps.usermgt.model.UserTrackPath;

/**
 * The persistence interface for the user track path service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see org.opencps.usermgt.service.persistence.impl.UserTrackPathPersistenceImpl
 * @see UserTrackPathUtil
 * @generated
 */
@ProviderType
public interface UserTrackPathPersistence extends BasePersistence<UserTrackPath> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link UserTrackPathUtil} to access the user track path persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the user track paths where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching user track paths
	*/
	public java.util.List<UserTrackPath> findByUuid(String uuid);

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
	public java.util.List<UserTrackPath> findByUuid(String uuid, int start,
		int end);

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
	public java.util.List<UserTrackPath> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserTrackPath> orderByComparator);

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
	public java.util.List<UserTrackPath> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserTrackPath> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first user track path in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user track path
	* @throws NoSuchUserTrackPathException if a matching user track path could not be found
	*/
	public UserTrackPath findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<UserTrackPath> orderByComparator)
		throws NoSuchUserTrackPathException;

	/**
	* Returns the first user track path in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user track path, or <code>null</code> if a matching user track path could not be found
	*/
	public UserTrackPath fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<UserTrackPath> orderByComparator);

	/**
	* Returns the last user track path in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user track path
	* @throws NoSuchUserTrackPathException if a matching user track path could not be found
	*/
	public UserTrackPath findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<UserTrackPath> orderByComparator)
		throws NoSuchUserTrackPathException;

	/**
	* Returns the last user track path in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user track path, or <code>null</code> if a matching user track path could not be found
	*/
	public UserTrackPath fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<UserTrackPath> orderByComparator);

	/**
	* Returns the user track paths before and after the current user track path in the ordered set where uuid = &#63;.
	*
	* @param userTrackPathId the primary key of the current user track path
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user track path
	* @throws NoSuchUserTrackPathException if a user track path with the primary key could not be found
	*/
	public UserTrackPath[] findByUuid_PrevAndNext(long userTrackPathId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<UserTrackPath> orderByComparator)
		throws NoSuchUserTrackPathException;

	/**
	* Removes all the user track paths where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of user track paths where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching user track paths
	*/
	public int countByUuid(String uuid);

	/**
	* Returns all the user track paths where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching user track paths
	*/
	public java.util.List<UserTrackPath> findByUuid_C(String uuid,
		long companyId);

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
	public java.util.List<UserTrackPath> findByUuid_C(String uuid,
		long companyId, int start, int end);

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
	public java.util.List<UserTrackPath> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserTrackPath> orderByComparator);

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
	public java.util.List<UserTrackPath> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserTrackPath> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first user track path in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user track path
	* @throws NoSuchUserTrackPathException if a matching user track path could not be found
	*/
	public UserTrackPath findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<UserTrackPath> orderByComparator)
		throws NoSuchUserTrackPathException;

	/**
	* Returns the first user track path in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user track path, or <code>null</code> if a matching user track path could not be found
	*/
	public UserTrackPath fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<UserTrackPath> orderByComparator);

	/**
	* Returns the last user track path in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user track path
	* @throws NoSuchUserTrackPathException if a matching user track path could not be found
	*/
	public UserTrackPath findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<UserTrackPath> orderByComparator)
		throws NoSuchUserTrackPathException;

	/**
	* Returns the last user track path in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user track path, or <code>null</code> if a matching user track path could not be found
	*/
	public UserTrackPath fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<UserTrackPath> orderByComparator);

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
	public UserTrackPath[] findByUuid_C_PrevAndNext(long userTrackPathId,
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<UserTrackPath> orderByComparator)
		throws NoSuchUserTrackPathException;

	/**
	* Removes all the user track paths where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of user track paths where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching user track paths
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Caches the user track path in the entity cache if it is enabled.
	*
	* @param userTrackPath the user track path
	*/
	public void cacheResult(UserTrackPath userTrackPath);

	/**
	* Caches the user track paths in the entity cache if it is enabled.
	*
	* @param userTrackPaths the user track paths
	*/
	public void cacheResult(java.util.List<UserTrackPath> userTrackPaths);

	/**
	* Creates a new user track path with the primary key. Does not add the user track path to the database.
	*
	* @param userTrackPathId the primary key for the new user track path
	* @return the new user track path
	*/
	public UserTrackPath create(long userTrackPathId);

	/**
	* Removes the user track path with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userTrackPathId the primary key of the user track path
	* @return the user track path that was removed
	* @throws NoSuchUserTrackPathException if a user track path with the primary key could not be found
	*/
	public UserTrackPath remove(long userTrackPathId)
		throws NoSuchUserTrackPathException;

	public UserTrackPath updateImpl(UserTrackPath userTrackPath);

	/**
	* Returns the user track path with the primary key or throws a {@link NoSuchUserTrackPathException} if it could not be found.
	*
	* @param userTrackPathId the primary key of the user track path
	* @return the user track path
	* @throws NoSuchUserTrackPathException if a user track path with the primary key could not be found
	*/
	public UserTrackPath findByPrimaryKey(long userTrackPathId)
		throws NoSuchUserTrackPathException;

	/**
	* Returns the user track path with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param userTrackPathId the primary key of the user track path
	* @return the user track path, or <code>null</code> if a user track path with the primary key could not be found
	*/
	public UserTrackPath fetchByPrimaryKey(long userTrackPathId);

	@Override
	public java.util.Map<java.io.Serializable, UserTrackPath> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the user track paths.
	*
	* @return the user track paths
	*/
	public java.util.List<UserTrackPath> findAll();

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
	public java.util.List<UserTrackPath> findAll(int start, int end);

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
	public java.util.List<UserTrackPath> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserTrackPath> orderByComparator);

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
	public java.util.List<UserTrackPath> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserTrackPath> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the user track paths from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of user track paths.
	*
	* @return the number of user track paths
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}