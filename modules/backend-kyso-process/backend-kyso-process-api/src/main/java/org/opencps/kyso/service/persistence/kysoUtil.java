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

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.kyso.model.kyso;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the kyso service. This utility wraps {@link org.opencps.kyso.service.persistence.impl.kysoPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Binhth
 * @see kysoPersistence
 * @see org.opencps.kyso.service.persistence.impl.kysoPersistenceImpl
 * @generated
 */
@ProviderType
public class kysoUtil {
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
	public static void clearCache(kyso kyso) {
		getPersistence().clearCache(kyso);
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
	public static List<kyso> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<kyso> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<kyso> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator<kyso> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static kyso update(kyso kyso) {
		return getPersistence().update(kyso);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static kyso update(kyso kyso, ServiceContext serviceContext) {
		return getPersistence().update(kyso, serviceContext);
	}

	/**
	* Returns all the kysos where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching kysos
	*/
	public static List<kyso> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

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
	public static List<kyso> findByUuid(java.lang.String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static List<kyso> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<kyso> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

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
	public static List<kyso> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<kyso> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first kyso in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kyso
	* @throws NoSuchkysoException if a matching kyso could not be found
	*/
	public static kyso findByUuid_First(java.lang.String uuid,
		OrderByComparator<kyso> orderByComparator)
		throws org.opencps.kyso.exception.NoSuchkysoException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first kyso in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kyso, or <code>null</code> if a matching kyso could not be found
	*/
	public static kyso fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<kyso> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last kyso in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kyso
	* @throws NoSuchkysoException if a matching kyso could not be found
	*/
	public static kyso findByUuid_Last(java.lang.String uuid,
		OrderByComparator<kyso> orderByComparator)
		throws org.opencps.kyso.exception.NoSuchkysoException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last kyso in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kyso, or <code>null</code> if a matching kyso could not be found
	*/
	public static kyso fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<kyso> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the kysos before and after the current kyso in the ordered set where uuid = &#63;.
	*
	* @param jasperId the primary key of the current kyso
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kyso
	* @throws NoSuchkysoException if a kyso with the primary key could not be found
	*/
	public static kyso[] findByUuid_PrevAndNext(long jasperId,
		java.lang.String uuid, OrderByComparator<kyso> orderByComparator)
		throws org.opencps.kyso.exception.NoSuchkysoException {
		return getPersistence()
				   .findByUuid_PrevAndNext(jasperId, uuid, orderByComparator);
	}

	/**
	* Removes all the kysos where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of kysos where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching kysos
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Caches the kyso in the entity cache if it is enabled.
	*
	* @param kyso the kyso
	*/
	public static void cacheResult(kyso kyso) {
		getPersistence().cacheResult(kyso);
	}

	/**
	* Caches the kysos in the entity cache if it is enabled.
	*
	* @param kysos the kysos
	*/
	public static void cacheResult(List<kyso> kysos) {
		getPersistence().cacheResult(kysos);
	}

	/**
	* Creates a new kyso with the primary key. Does not add the kyso to the database.
	*
	* @param jasperId the primary key for the new kyso
	* @return the new kyso
	*/
	public static kyso create(long jasperId) {
		return getPersistence().create(jasperId);
	}

	/**
	* Removes the kyso with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param jasperId the primary key of the kyso
	* @return the kyso that was removed
	* @throws NoSuchkysoException if a kyso with the primary key could not be found
	*/
	public static kyso remove(long jasperId)
		throws org.opencps.kyso.exception.NoSuchkysoException {
		return getPersistence().remove(jasperId);
	}

	public static kyso updateImpl(kyso kyso) {
		return getPersistence().updateImpl(kyso);
	}

	/**
	* Returns the kyso with the primary key or throws a {@link NoSuchkysoException} if it could not be found.
	*
	* @param jasperId the primary key of the kyso
	* @return the kyso
	* @throws NoSuchkysoException if a kyso with the primary key could not be found
	*/
	public static kyso findByPrimaryKey(long jasperId)
		throws org.opencps.kyso.exception.NoSuchkysoException {
		return getPersistence().findByPrimaryKey(jasperId);
	}

	/**
	* Returns the kyso with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param jasperId the primary key of the kyso
	* @return the kyso, or <code>null</code> if a kyso with the primary key could not be found
	*/
	public static kyso fetchByPrimaryKey(long jasperId) {
		return getPersistence().fetchByPrimaryKey(jasperId);
	}

	public static java.util.Map<java.io.Serializable, kyso> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the kysos.
	*
	* @return the kysos
	*/
	public static List<kyso> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<kyso> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<kyso> findAll(int start, int end,
		OrderByComparator<kyso> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<kyso> findAll(int start, int end,
		OrderByComparator<kyso> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the kysos from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of kysos.
	*
	* @return the number of kysos
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static kysoPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<kysoPersistence, kysoPersistence> _serviceTracker =
		ServiceTrackerFactory.open(kysoPersistence.class);
}