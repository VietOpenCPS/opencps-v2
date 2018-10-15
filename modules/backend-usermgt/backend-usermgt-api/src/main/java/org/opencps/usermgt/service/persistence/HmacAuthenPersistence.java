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

import org.opencps.usermgt.exception.NoSuchHmacAuthenException;
import org.opencps.usermgt.model.HmacAuthen;

/**
 * The persistence interface for the hmac authen service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see org.opencps.usermgt.service.persistence.impl.HmacAuthenPersistenceImpl
 * @see HmacAuthenUtil
 * @generated
 */
@ProviderType
public interface HmacAuthenPersistence extends BasePersistence<HmacAuthen> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HmacAuthenUtil} to access the hmac authen persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the hmac authen in the entity cache if it is enabled.
	*
	* @param hmacAuthen the hmac authen
	*/
	public void cacheResult(HmacAuthen hmacAuthen);

	/**
	* Caches the hmac authens in the entity cache if it is enabled.
	*
	* @param hmacAuthens the hmac authens
	*/
	public void cacheResult(java.util.List<HmacAuthen> hmacAuthens);

	/**
	* Creates a new hmac authen with the primary key. Does not add the hmac authen to the database.
	*
	* @param hmacAuthId the primary key for the new hmac authen
	* @return the new hmac authen
	*/
	public HmacAuthen create(long hmacAuthId);

	/**
	* Removes the hmac authen with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hmacAuthId the primary key of the hmac authen
	* @return the hmac authen that was removed
	* @throws NoSuchHmacAuthenException if a hmac authen with the primary key could not be found
	*/
	public HmacAuthen remove(long hmacAuthId) throws NoSuchHmacAuthenException;

	public HmacAuthen updateImpl(HmacAuthen hmacAuthen);

	/**
	* Returns the hmac authen with the primary key or throws a {@link NoSuchHmacAuthenException} if it could not be found.
	*
	* @param hmacAuthId the primary key of the hmac authen
	* @return the hmac authen
	* @throws NoSuchHmacAuthenException if a hmac authen with the primary key could not be found
	*/
	public HmacAuthen findByPrimaryKey(long hmacAuthId)
		throws NoSuchHmacAuthenException;

	/**
	* Returns the hmac authen with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hmacAuthId the primary key of the hmac authen
	* @return the hmac authen, or <code>null</code> if a hmac authen with the primary key could not be found
	*/
	public HmacAuthen fetchByPrimaryKey(long hmacAuthId);

	@Override
	public java.util.Map<java.io.Serializable, HmacAuthen> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the hmac authens.
	*
	* @return the hmac authens
	*/
	public java.util.List<HmacAuthen> findAll();

	/**
	* Returns a range of all the hmac authens.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HmacAuthenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hmac authens
	* @param end the upper bound of the range of hmac authens (not inclusive)
	* @return the range of hmac authens
	*/
	public java.util.List<HmacAuthen> findAll(int start, int end);

	/**
	* Returns an ordered range of all the hmac authens.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HmacAuthenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hmac authens
	* @param end the upper bound of the range of hmac authens (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of hmac authens
	*/
	public java.util.List<HmacAuthen> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HmacAuthen> orderByComparator);

	/**
	* Returns an ordered range of all the hmac authens.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HmacAuthenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hmac authens
	* @param end the upper bound of the range of hmac authens (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of hmac authens
	*/
	public java.util.List<HmacAuthen> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HmacAuthen> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the hmac authens from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of hmac authens.
	*
	* @return the number of hmac authens
	*/
	public int countAll();
}