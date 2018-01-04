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

package org.opencps.kyso.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.kyso.exception.NoSuchkysoException;
import org.opencps.kyso.model.kyso;

/**
 * The persistence interface for the kyso service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Binhth
 * @see org.opencps.kyso.service.persistence.impl.kysoPersistenceImpl
 * @see kysoUtil
 * @generated
 */
@ProviderType
public interface kysoPersistence extends BasePersistence<kyso> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link kysoUtil} to access the kyso persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the kysos where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching kysos
	*/
	public java.util.List<kyso> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the kysos where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kysoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of kysos
	* @param end the upper bound of the range of kysos (not inclusive)
	* @return the range of matching kysos
	*/
	public java.util.List<kyso> findByUuid(java.lang.String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the kysos where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kysoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of kysos
	* @param end the upper bound of the range of kysos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kysos
	*/
	public java.util.List<kyso> findByUuid(java.lang.String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<kyso> orderByComparator);

	/**
	* Returns an ordered range of all the kysos where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kysoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of kysos
	* @param end the upper bound of the range of kysos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching kysos
	*/
	public java.util.List<kyso> findByUuid(java.lang.String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<kyso> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first kyso in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kyso
	* @throws NoSuchkysoException if a matching kyso could not be found
	*/
	public kyso findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<kyso> orderByComparator)
		throws NoSuchkysoException;

	/**
	* Returns the first kyso in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kyso, or <code>null</code> if a matching kyso could not be found
	*/
	public kyso fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<kyso> orderByComparator);

	/**
	* Returns the last kyso in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kyso
	* @throws NoSuchkysoException if a matching kyso could not be found
	*/
	public kyso findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<kyso> orderByComparator)
		throws NoSuchkysoException;

	/**
	* Returns the last kyso in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kyso, or <code>null</code> if a matching kyso could not be found
	*/
	public kyso fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<kyso> orderByComparator);

	/**
	* Returns the kysos before and after the current kyso in the ordered set where uuid = &#63;.
	*
	* @param jasperId the primary key of the current kyso
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kyso
	* @throws NoSuchkysoException if a kyso with the primary key could not be found
	*/
	public kyso[] findByUuid_PrevAndNext(long jasperId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<kyso> orderByComparator)
		throws NoSuchkysoException;

	/**
	* Removes all the kysos where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of kysos where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching kysos
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Caches the kyso in the entity cache if it is enabled.
	*
	* @param kyso the kyso
	*/
	public void cacheResult(kyso kyso);

	/**
	* Caches the kysos in the entity cache if it is enabled.
	*
	* @param kysos the kysos
	*/
	public void cacheResult(java.util.List<kyso> kysos);

	/**
	* Creates a new kyso with the primary key. Does not add the kyso to the database.
	*
	* @param jasperId the primary key for the new kyso
	* @return the new kyso
	*/
	public kyso create(long jasperId);

	/**
	* Removes the kyso with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param jasperId the primary key of the kyso
	* @return the kyso that was removed
	* @throws NoSuchkysoException if a kyso with the primary key could not be found
	*/
	public kyso remove(long jasperId) throws NoSuchkysoException;

	public kyso updateImpl(kyso kyso);

	/**
	* Returns the kyso with the primary key or throws a {@link NoSuchkysoException} if it could not be found.
	*
	* @param jasperId the primary key of the kyso
	* @return the kyso
	* @throws NoSuchkysoException if a kyso with the primary key could not be found
	*/
	public kyso findByPrimaryKey(long jasperId) throws NoSuchkysoException;

	/**
	* Returns the kyso with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param jasperId the primary key of the kyso
	* @return the kyso, or <code>null</code> if a kyso with the primary key could not be found
	*/
	public kyso fetchByPrimaryKey(long jasperId);

	@Override
	public java.util.Map<java.io.Serializable, kyso> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the kysos.
	*
	* @return the kysos
	*/
	public java.util.List<kyso> findAll();

	/**
	* Returns a range of all the kysos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kysoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kysos
	* @param end the upper bound of the range of kysos (not inclusive)
	* @return the range of kysos
	*/
	public java.util.List<kyso> findAll(int start, int end);

	/**
	* Returns an ordered range of all the kysos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kysoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kysos
	* @param end the upper bound of the range of kysos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of kysos
	*/
	public java.util.List<kyso> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<kyso> orderByComparator);

	/**
	* Returns an ordered range of all the kysos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kysoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kysos
	* @param end the upper bound of the range of kysos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of kysos
	*/
	public java.util.List<kyso> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<kyso> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the kysos from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of kysos.
	*
	* @return the number of kysos
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}