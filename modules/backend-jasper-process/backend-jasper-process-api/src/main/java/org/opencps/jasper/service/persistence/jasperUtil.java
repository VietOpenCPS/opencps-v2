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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.jasper.model.jasper;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the jasper service. This utility wraps {@link org.opencps.jasper.service.persistence.impl.jasperPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Binhth
 * @see jasperPersistence
 * @see org.opencps.jasper.service.persistence.impl.jasperPersistenceImpl
 * @generated
 */
@ProviderType
public class jasperUtil {
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
	public static void clearCache(jasper jasper) {
		getPersistence().clearCache(jasper);
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
	public static List<jasper> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<jasper> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<jasper> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator<jasper> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static jasper update(jasper jasper) {
		return getPersistence().update(jasper);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static jasper update(jasper jasper, ServiceContext serviceContext) {
		return getPersistence().update(jasper, serviceContext);
	}

	/**
	* Returns all the jaspers where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching jaspers
	*/
	public static List<jasper> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

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
	public static List<jasper> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static List<jasper> findByUuid(String uuid, int start, int end,
		OrderByComparator<jasper> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

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
	public static List<jasper> findByUuid(String uuid, int start, int end,
		OrderByComparator<jasper> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first jasper in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching jasper
	* @throws NoSuchjasperException if a matching jasper could not be found
	*/
	public static jasper findByUuid_First(String uuid,
		OrderByComparator<jasper> orderByComparator)
		throws org.opencps.jasper.exception.NoSuchjasperException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first jasper in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching jasper, or <code>null</code> if a matching jasper could not be found
	*/
	public static jasper fetchByUuid_First(String uuid,
		OrderByComparator<jasper> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last jasper in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching jasper
	* @throws NoSuchjasperException if a matching jasper could not be found
	*/
	public static jasper findByUuid_Last(String uuid,
		OrderByComparator<jasper> orderByComparator)
		throws org.opencps.jasper.exception.NoSuchjasperException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last jasper in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching jasper, or <code>null</code> if a matching jasper could not be found
	*/
	public static jasper fetchByUuid_Last(String uuid,
		OrderByComparator<jasper> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the jaspers before and after the current jasper in the ordered set where uuid = &#63;.
	*
	* @param jasperId the primary key of the current jasper
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next jasper
	* @throws NoSuchjasperException if a jasper with the primary key could not be found
	*/
	public static jasper[] findByUuid_PrevAndNext(long jasperId, String uuid,
		OrderByComparator<jasper> orderByComparator)
		throws org.opencps.jasper.exception.NoSuchjasperException {
		return getPersistence()
				   .findByUuid_PrevAndNext(jasperId, uuid, orderByComparator);
	}

	/**
	* Removes all the jaspers where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of jaspers where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching jaspers
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Caches the jasper in the entity cache if it is enabled.
	*
	* @param jasper the jasper
	*/
	public static void cacheResult(jasper jasper) {
		getPersistence().cacheResult(jasper);
	}

	/**
	* Caches the jaspers in the entity cache if it is enabled.
	*
	* @param jaspers the jaspers
	*/
	public static void cacheResult(List<jasper> jaspers) {
		getPersistence().cacheResult(jaspers);
	}

	/**
	* Creates a new jasper with the primary key. Does not add the jasper to the database.
	*
	* @param jasperId the primary key for the new jasper
	* @return the new jasper
	*/
	public static jasper create(long jasperId) {
		return getPersistence().create(jasperId);
	}

	/**
	* Removes the jasper with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param jasperId the primary key of the jasper
	* @return the jasper that was removed
	* @throws NoSuchjasperException if a jasper with the primary key could not be found
	*/
	public static jasper remove(long jasperId)
		throws org.opencps.jasper.exception.NoSuchjasperException {
		return getPersistence().remove(jasperId);
	}

	public static jasper updateImpl(jasper jasper) {
		return getPersistence().updateImpl(jasper);
	}

	/**
	* Returns the jasper with the primary key or throws a {@link NoSuchjasperException} if it could not be found.
	*
	* @param jasperId the primary key of the jasper
	* @return the jasper
	* @throws NoSuchjasperException if a jasper with the primary key could not be found
	*/
	public static jasper findByPrimaryKey(long jasperId)
		throws org.opencps.jasper.exception.NoSuchjasperException {
		return getPersistence().findByPrimaryKey(jasperId);
	}

	/**
	* Returns the jasper with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param jasperId the primary key of the jasper
	* @return the jasper, or <code>null</code> if a jasper with the primary key could not be found
	*/
	public static jasper fetchByPrimaryKey(long jasperId) {
		return getPersistence().fetchByPrimaryKey(jasperId);
	}

	public static java.util.Map<java.io.Serializable, jasper> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the jaspers.
	*
	* @return the jaspers
	*/
	public static List<jasper> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<jasper> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<jasper> findAll(int start, int end,
		OrderByComparator<jasper> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<jasper> findAll(int start, int end,
		OrderByComparator<jasper> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the jaspers from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of jaspers.
	*
	* @return the number of jaspers
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static jasperPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<jasperPersistence, jasperPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(jasperPersistence.class);

		ServiceTracker<jasperPersistence, jasperPersistence> serviceTracker = new ServiceTracker<jasperPersistence, jasperPersistence>(bundle.getBundleContext(),
				jasperPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}