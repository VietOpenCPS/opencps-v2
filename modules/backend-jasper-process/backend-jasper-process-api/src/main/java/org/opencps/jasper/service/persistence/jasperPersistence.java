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

package org.opencps.jasper.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.jasper.exception.NoSuchjasperException;
import org.opencps.jasper.model.jasper;

/**
 * The persistence interface for the jasper service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Binhth
 * @see org.opencps.jasper.service.persistence.impl.jasperPersistenceImpl
 * @see jasperUtil
 * @generated
 */
@ProviderType
public interface jasperPersistence extends BasePersistence<jasper> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link jasperUtil} to access the jasper persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the jaspers where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching jaspers
	*/
	public java.util.List<jasper> findByUuid(String uuid);

	/**
	* Returns a range of all the jaspers where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link jasperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of jaspers
	* @param end the upper bound of the range of jaspers (not inclusive)
	* @return the range of matching jaspers
	*/
	public java.util.List<jasper> findByUuid(String uuid, int start, int end);

	/**
	* Returns an ordered range of all the jaspers where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link jasperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of jaspers
	* @param end the upper bound of the range of jaspers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching jaspers
	*/
	public java.util.List<jasper> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<jasper> orderByComparator);

	/**
	* Returns an ordered range of all the jaspers where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link jasperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of jaspers
	* @param end the upper bound of the range of jaspers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching jaspers
	*/
	public java.util.List<jasper> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<jasper> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first jasper in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching jasper
	* @throws NoSuchjasperException if a matching jasper could not be found
	*/
	public jasper findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<jasper> orderByComparator)
		throws NoSuchjasperException;

	/**
	* Returns the first jasper in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching jasper, or <code>null</code> if a matching jasper could not be found
	*/
	public jasper fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<jasper> orderByComparator);

	/**
	* Returns the last jasper in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching jasper
	* @throws NoSuchjasperException if a matching jasper could not be found
	*/
	public jasper findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<jasper> orderByComparator)
		throws NoSuchjasperException;

	/**
	* Returns the last jasper in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching jasper, or <code>null</code> if a matching jasper could not be found
	*/
	public jasper fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<jasper> orderByComparator);

	/**
	* Returns the jaspers before and after the current jasper in the ordered set where uuid = &#63;.
	*
	* @param jasperId the primary key of the current jasper
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next jasper
	* @throws NoSuchjasperException if a jasper with the primary key could not be found
	*/
	public jasper[] findByUuid_PrevAndNext(long jasperId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<jasper> orderByComparator)
		throws NoSuchjasperException;

	/**
	* Removes all the jaspers where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of jaspers where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching jaspers
	*/
	public int countByUuid(String uuid);

	/**
	* Caches the jasper in the entity cache if it is enabled.
	*
	* @param jasper the jasper
	*/
	public void cacheResult(jasper jasper);

	/**
	* Caches the jaspers in the entity cache if it is enabled.
	*
	* @param jaspers the jaspers
	*/
	public void cacheResult(java.util.List<jasper> jaspers);

	/**
	* Creates a new jasper with the primary key. Does not add the jasper to the database.
	*
	* @param jasperId the primary key for the new jasper
	* @return the new jasper
	*/
	public jasper create(long jasperId);

	/**
	* Removes the jasper with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param jasperId the primary key of the jasper
	* @return the jasper that was removed
	* @throws NoSuchjasperException if a jasper with the primary key could not be found
	*/
	public jasper remove(long jasperId) throws NoSuchjasperException;

	public jasper updateImpl(jasper jasper);

	/**
	* Returns the jasper with the primary key or throws a {@link NoSuchjasperException} if it could not be found.
	*
	* @param jasperId the primary key of the jasper
	* @return the jasper
	* @throws NoSuchjasperException if a jasper with the primary key could not be found
	*/
	public jasper findByPrimaryKey(long jasperId) throws NoSuchjasperException;

	/**
	* Returns the jasper with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param jasperId the primary key of the jasper
	* @return the jasper, or <code>null</code> if a jasper with the primary key could not be found
	*/
	public jasper fetchByPrimaryKey(long jasperId);

	@Override
	public java.util.Map<java.io.Serializable, jasper> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the jaspers.
	*
	* @return the jaspers
	*/
	public java.util.List<jasper> findAll();

	/**
	* Returns a range of all the jaspers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link jasperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of jaspers
	* @param end the upper bound of the range of jaspers (not inclusive)
	* @return the range of jaspers
	*/
	public java.util.List<jasper> findAll(int start, int end);

	/**
	* Returns an ordered range of all the jaspers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link jasperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of jaspers
	* @param end the upper bound of the range of jaspers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of jaspers
	*/
	public java.util.List<jasper> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<jasper> orderByComparator);

	/**
	* Returns an ordered range of all the jaspers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link jasperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of jaspers
	* @param end the upper bound of the range of jaspers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of jaspers
	*/
	public java.util.List<jasper> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<jasper> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the jaspers from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of jaspers.
	*
	* @return the number of jaspers
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}