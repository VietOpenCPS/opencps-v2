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

import org.opencps.dossiermgt.exception.NoSuchAccessTokenException;
import org.opencps.dossiermgt.model.AccessToken;

/**
 * The persistence interface for the access token service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.dossiermgt.service.persistence.impl.AccessTokenPersistenceImpl
 * @see AccessTokenUtil
 * @generated
 */
@ProviderType
public interface AccessTokenPersistence extends BasePersistence<AccessToken> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AccessTokenUtil} to access the access token persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the access tokens where groupId = &#63; and className = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @return the matching access tokens
	*/
	public java.util.List<AccessToken> findByF_GID_ClassName(long groupId,
		String className);

	/**
	* Returns a range of all the access tokens where groupId = &#63; and className = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccessTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param className the class name
	* @param start the lower bound of the range of access tokens
	* @param end the upper bound of the range of access tokens (not inclusive)
	* @return the range of matching access tokens
	*/
	public java.util.List<AccessToken> findByF_GID_ClassName(long groupId,
		String className, int start, int end);

	/**
	* Returns an ordered range of all the access tokens where groupId = &#63; and className = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccessTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param className the class name
	* @param start the lower bound of the range of access tokens
	* @param end the upper bound of the range of access tokens (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching access tokens
	*/
	public java.util.List<AccessToken> findByF_GID_ClassName(long groupId,
		String className, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccessToken> orderByComparator);

	/**
	* Returns an ordered range of all the access tokens where groupId = &#63; and className = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccessTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param className the class name
	* @param start the lower bound of the range of access tokens
	* @param end the upper bound of the range of access tokens (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching access tokens
	*/
	public java.util.List<AccessToken> findByF_GID_ClassName(long groupId,
		String className, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccessToken> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first access token in the ordered set where groupId = &#63; and className = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching access token
	* @throws NoSuchAccessTokenException if a matching access token could not be found
	*/
	public AccessToken findByF_GID_ClassName_First(long groupId,
		String className,
		com.liferay.portal.kernel.util.OrderByComparator<AccessToken> orderByComparator)
		throws NoSuchAccessTokenException;

	/**
	* Returns the first access token in the ordered set where groupId = &#63; and className = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching access token, or <code>null</code> if a matching access token could not be found
	*/
	public AccessToken fetchByF_GID_ClassName_First(long groupId,
		String className,
		com.liferay.portal.kernel.util.OrderByComparator<AccessToken> orderByComparator);

	/**
	* Returns the last access token in the ordered set where groupId = &#63; and className = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching access token
	* @throws NoSuchAccessTokenException if a matching access token could not be found
	*/
	public AccessToken findByF_GID_ClassName_Last(long groupId,
		String className,
		com.liferay.portal.kernel.util.OrderByComparator<AccessToken> orderByComparator)
		throws NoSuchAccessTokenException;

	/**
	* Returns the last access token in the ordered set where groupId = &#63; and className = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching access token, or <code>null</code> if a matching access token could not be found
	*/
	public AccessToken fetchByF_GID_ClassName_Last(long groupId,
		String className,
		com.liferay.portal.kernel.util.OrderByComparator<AccessToken> orderByComparator);

	/**
	* Returns the access tokens before and after the current access token in the ordered set where groupId = &#63; and className = &#63;.
	*
	* @param accessTokenId the primary key of the current access token
	* @param groupId the group ID
	* @param className the class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next access token
	* @throws NoSuchAccessTokenException if a access token with the primary key could not be found
	*/
	public AccessToken[] findByF_GID_ClassName_PrevAndNext(long accessTokenId,
		long groupId, String className,
		com.liferay.portal.kernel.util.OrderByComparator<AccessToken> orderByComparator)
		throws NoSuchAccessTokenException;

	/**
	* Removes all the access tokens where groupId = &#63; and className = &#63; from the database.
	*
	* @param groupId the group ID
	* @param className the class name
	*/
	public void removeByF_GID_ClassName(long groupId, String className);

	/**
	* Returns the number of access tokens where groupId = &#63; and className = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @return the number of matching access tokens
	*/
	public int countByF_GID_ClassName(long groupId, String className);

	/**
	* Caches the access token in the entity cache if it is enabled.
	*
	* @param accessToken the access token
	*/
	public void cacheResult(AccessToken accessToken);

	/**
	* Caches the access tokens in the entity cache if it is enabled.
	*
	* @param accessTokens the access tokens
	*/
	public void cacheResult(java.util.List<AccessToken> accessTokens);

	/**
	* Creates a new access token with the primary key. Does not add the access token to the database.
	*
	* @param accessTokenId the primary key for the new access token
	* @return the new access token
	*/
	public AccessToken create(long accessTokenId);

	/**
	* Removes the access token with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accessTokenId the primary key of the access token
	* @return the access token that was removed
	* @throws NoSuchAccessTokenException if a access token with the primary key could not be found
	*/
	public AccessToken remove(long accessTokenId)
		throws NoSuchAccessTokenException;

	public AccessToken updateImpl(AccessToken accessToken);

	/**
	* Returns the access token with the primary key or throws a {@link NoSuchAccessTokenException} if it could not be found.
	*
	* @param accessTokenId the primary key of the access token
	* @return the access token
	* @throws NoSuchAccessTokenException if a access token with the primary key could not be found
	*/
	public AccessToken findByPrimaryKey(long accessTokenId)
		throws NoSuchAccessTokenException;

	/**
	* Returns the access token with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param accessTokenId the primary key of the access token
	* @return the access token, or <code>null</code> if a access token with the primary key could not be found
	*/
	public AccessToken fetchByPrimaryKey(long accessTokenId);

	@Override
	public java.util.Map<java.io.Serializable, AccessToken> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the access tokens.
	*
	* @return the access tokens
	*/
	public java.util.List<AccessToken> findAll();

	/**
	* Returns a range of all the access tokens.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccessTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of access tokens
	* @param end the upper bound of the range of access tokens (not inclusive)
	* @return the range of access tokens
	*/
	public java.util.List<AccessToken> findAll(int start, int end);

	/**
	* Returns an ordered range of all the access tokens.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccessTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of access tokens
	* @param end the upper bound of the range of access tokens (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of access tokens
	*/
	public java.util.List<AccessToken> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccessToken> orderByComparator);

	/**
	* Returns an ordered range of all the access tokens.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccessTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of access tokens
	* @param end the upper bound of the range of access tokens (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of access tokens
	*/
	public java.util.List<AccessToken> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccessToken> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the access tokens from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of access tokens.
	*
	* @return the number of access tokens
	*/
	public int countAll();
}