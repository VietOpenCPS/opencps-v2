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

package org.opencps.communication.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.communication.exception.NoSuchLGSPTokenException;
import org.opencps.communication.model.LGSPToken;

/**
 * The persistence interface for the lgsp token service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavd
 * @see org.opencps.communication.service.persistence.impl.LGSPTokenPersistenceImpl
 * @see LGSPTokenUtil
 * @generated
 */
@ProviderType
public interface LGSPTokenPersistence extends BasePersistence<LGSPToken> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LGSPTokenUtil} to access the lgsp token persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the lgsp tokens where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching lgsp tokens
	*/
	public java.util.List<LGSPToken> findByUuid(String uuid);

	/**
	* Returns a range of all the lgsp tokens where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LGSPTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of lgsp tokens
	* @param end the upper bound of the range of lgsp tokens (not inclusive)
	* @return the range of matching lgsp tokens
	*/
	public java.util.List<LGSPToken> findByUuid(String uuid, int start, int end);

	/**
	* Returns an ordered range of all the lgsp tokens where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LGSPTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of lgsp tokens
	* @param end the upper bound of the range of lgsp tokens (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching lgsp tokens
	*/
	public java.util.List<LGSPToken> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<LGSPToken> orderByComparator);

	/**
	* Returns an ordered range of all the lgsp tokens where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LGSPTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of lgsp tokens
	* @param end the upper bound of the range of lgsp tokens (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching lgsp tokens
	*/
	public java.util.List<LGSPToken> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<LGSPToken> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first lgsp token in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching lgsp token
	* @throws NoSuchLGSPTokenException if a matching lgsp token could not be found
	*/
	public LGSPToken findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<LGSPToken> orderByComparator)
		throws NoSuchLGSPTokenException;

	/**
	* Returns the first lgsp token in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching lgsp token, or <code>null</code> if a matching lgsp token could not be found
	*/
	public LGSPToken fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<LGSPToken> orderByComparator);

	/**
	* Returns the last lgsp token in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching lgsp token
	* @throws NoSuchLGSPTokenException if a matching lgsp token could not be found
	*/
	public LGSPToken findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<LGSPToken> orderByComparator)
		throws NoSuchLGSPTokenException;

	/**
	* Returns the last lgsp token in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching lgsp token, or <code>null</code> if a matching lgsp token could not be found
	*/
	public LGSPToken fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<LGSPToken> orderByComparator);

	/**
	* Returns the lgsp tokens before and after the current lgsp token in the ordered set where uuid = &#63;.
	*
	* @param tokenId the primary key of the current lgsp token
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next lgsp token
	* @throws NoSuchLGSPTokenException if a lgsp token with the primary key could not be found
	*/
	public LGSPToken[] findByUuid_PrevAndNext(long tokenId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<LGSPToken> orderByComparator)
		throws NoSuchLGSPTokenException;

	/**
	* Removes all the lgsp tokens where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of lgsp tokens where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching lgsp tokens
	*/
	public int countByUuid(String uuid);

	/**
	* Returns all the lgsp tokens where tokenType = &#63;.
	*
	* @param tokenType the token type
	* @return the matching lgsp tokens
	*/
	public java.util.List<LGSPToken> findByF_TYPE_EXPIRY(String tokenType);

	/**
	* Returns a range of all the lgsp tokens where tokenType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LGSPTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param tokenType the token type
	* @param start the lower bound of the range of lgsp tokens
	* @param end the upper bound of the range of lgsp tokens (not inclusive)
	* @return the range of matching lgsp tokens
	*/
	public java.util.List<LGSPToken> findByF_TYPE_EXPIRY(String tokenType,
		int start, int end);

	/**
	* Returns an ordered range of all the lgsp tokens where tokenType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LGSPTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param tokenType the token type
	* @param start the lower bound of the range of lgsp tokens
	* @param end the upper bound of the range of lgsp tokens (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching lgsp tokens
	*/
	public java.util.List<LGSPToken> findByF_TYPE_EXPIRY(String tokenType,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LGSPToken> orderByComparator);

	/**
	* Returns an ordered range of all the lgsp tokens where tokenType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LGSPTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param tokenType the token type
	* @param start the lower bound of the range of lgsp tokens
	* @param end the upper bound of the range of lgsp tokens (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching lgsp tokens
	*/
	public java.util.List<LGSPToken> findByF_TYPE_EXPIRY(String tokenType,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LGSPToken> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first lgsp token in the ordered set where tokenType = &#63;.
	*
	* @param tokenType the token type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching lgsp token
	* @throws NoSuchLGSPTokenException if a matching lgsp token could not be found
	*/
	public LGSPToken findByF_TYPE_EXPIRY_First(String tokenType,
		com.liferay.portal.kernel.util.OrderByComparator<LGSPToken> orderByComparator)
		throws NoSuchLGSPTokenException;

	/**
	* Returns the first lgsp token in the ordered set where tokenType = &#63;.
	*
	* @param tokenType the token type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching lgsp token, or <code>null</code> if a matching lgsp token could not be found
	*/
	public LGSPToken fetchByF_TYPE_EXPIRY_First(String tokenType,
		com.liferay.portal.kernel.util.OrderByComparator<LGSPToken> orderByComparator);

	/**
	* Returns the last lgsp token in the ordered set where tokenType = &#63;.
	*
	* @param tokenType the token type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching lgsp token
	* @throws NoSuchLGSPTokenException if a matching lgsp token could not be found
	*/
	public LGSPToken findByF_TYPE_EXPIRY_Last(String tokenType,
		com.liferay.portal.kernel.util.OrderByComparator<LGSPToken> orderByComparator)
		throws NoSuchLGSPTokenException;

	/**
	* Returns the last lgsp token in the ordered set where tokenType = &#63;.
	*
	* @param tokenType the token type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching lgsp token, or <code>null</code> if a matching lgsp token could not be found
	*/
	public LGSPToken fetchByF_TYPE_EXPIRY_Last(String tokenType,
		com.liferay.portal.kernel.util.OrderByComparator<LGSPToken> orderByComparator);

	/**
	* Returns the lgsp tokens before and after the current lgsp token in the ordered set where tokenType = &#63;.
	*
	* @param tokenId the primary key of the current lgsp token
	* @param tokenType the token type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next lgsp token
	* @throws NoSuchLGSPTokenException if a lgsp token with the primary key could not be found
	*/
	public LGSPToken[] findByF_TYPE_EXPIRY_PrevAndNext(long tokenId,
		String tokenType,
		com.liferay.portal.kernel.util.OrderByComparator<LGSPToken> orderByComparator)
		throws NoSuchLGSPTokenException;

	/**
	* Removes all the lgsp tokens where tokenType = &#63; from the database.
	*
	* @param tokenType the token type
	*/
	public void removeByF_TYPE_EXPIRY(String tokenType);

	/**
	* Returns the number of lgsp tokens where tokenType = &#63;.
	*
	* @param tokenType the token type
	* @return the number of matching lgsp tokens
	*/
	public int countByF_TYPE_EXPIRY(String tokenType);

	/**
	* Caches the lgsp token in the entity cache if it is enabled.
	*
	* @param lgspToken the lgsp token
	*/
	public void cacheResult(LGSPToken lgspToken);

	/**
	* Caches the lgsp tokens in the entity cache if it is enabled.
	*
	* @param lgspTokens the lgsp tokens
	*/
	public void cacheResult(java.util.List<LGSPToken> lgspTokens);

	/**
	* Creates a new lgsp token with the primary key. Does not add the lgsp token to the database.
	*
	* @param tokenId the primary key for the new lgsp token
	* @return the new lgsp token
	*/
	public LGSPToken create(long tokenId);

	/**
	* Removes the lgsp token with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param tokenId the primary key of the lgsp token
	* @return the lgsp token that was removed
	* @throws NoSuchLGSPTokenException if a lgsp token with the primary key could not be found
	*/
	public LGSPToken remove(long tokenId) throws NoSuchLGSPTokenException;

	public LGSPToken updateImpl(LGSPToken lgspToken);

	/**
	* Returns the lgsp token with the primary key or throws a {@link NoSuchLGSPTokenException} if it could not be found.
	*
	* @param tokenId the primary key of the lgsp token
	* @return the lgsp token
	* @throws NoSuchLGSPTokenException if a lgsp token with the primary key could not be found
	*/
	public LGSPToken findByPrimaryKey(long tokenId)
		throws NoSuchLGSPTokenException;

	/**
	* Returns the lgsp token with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param tokenId the primary key of the lgsp token
	* @return the lgsp token, or <code>null</code> if a lgsp token with the primary key could not be found
	*/
	public LGSPToken fetchByPrimaryKey(long tokenId);

	@Override
	public java.util.Map<java.io.Serializable, LGSPToken> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the lgsp tokens.
	*
	* @return the lgsp tokens
	*/
	public java.util.List<LGSPToken> findAll();

	/**
	* Returns a range of all the lgsp tokens.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LGSPTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of lgsp tokens
	* @param end the upper bound of the range of lgsp tokens (not inclusive)
	* @return the range of lgsp tokens
	*/
	public java.util.List<LGSPToken> findAll(int start, int end);

	/**
	* Returns an ordered range of all the lgsp tokens.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LGSPTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of lgsp tokens
	* @param end the upper bound of the range of lgsp tokens (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of lgsp tokens
	*/
	public java.util.List<LGSPToken> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LGSPToken> orderByComparator);

	/**
	* Returns an ordered range of all the lgsp tokens.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LGSPTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of lgsp tokens
	* @param end the upper bound of the range of lgsp tokens (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of lgsp tokens
	*/
	public java.util.List<LGSPToken> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LGSPToken> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the lgsp tokens from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of lgsp tokens.
	*
	* @return the number of lgsp tokens
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}