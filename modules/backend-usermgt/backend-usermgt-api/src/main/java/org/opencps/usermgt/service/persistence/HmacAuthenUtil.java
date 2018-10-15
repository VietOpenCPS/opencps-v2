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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.usermgt.model.HmacAuthen;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the hmac authen service. This utility wraps {@link org.opencps.usermgt.service.persistence.impl.HmacAuthenPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see HmacAuthenPersistence
 * @see org.opencps.usermgt.service.persistence.impl.HmacAuthenPersistenceImpl
 * @generated
 */
@ProviderType
public class HmacAuthenUtil {
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
	public static void clearCache(HmacAuthen hmacAuthen) {
		getPersistence().clearCache(hmacAuthen);
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
	public static List<HmacAuthen> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<HmacAuthen> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<HmacAuthen> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<HmacAuthen> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static HmacAuthen update(HmacAuthen hmacAuthen) {
		return getPersistence().update(hmacAuthen);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static HmacAuthen update(HmacAuthen hmacAuthen,
		ServiceContext serviceContext) {
		return getPersistence().update(hmacAuthen, serviceContext);
	}

	/**
	* Caches the hmac authen in the entity cache if it is enabled.
	*
	* @param hmacAuthen the hmac authen
	*/
	public static void cacheResult(HmacAuthen hmacAuthen) {
		getPersistence().cacheResult(hmacAuthen);
	}

	/**
	* Caches the hmac authens in the entity cache if it is enabled.
	*
	* @param hmacAuthens the hmac authens
	*/
	public static void cacheResult(List<HmacAuthen> hmacAuthens) {
		getPersistence().cacheResult(hmacAuthens);
	}

	/**
	* Creates a new hmac authen with the primary key. Does not add the hmac authen to the database.
	*
	* @param hmacAuthId the primary key for the new hmac authen
	* @return the new hmac authen
	*/
	public static HmacAuthen create(long hmacAuthId) {
		return getPersistence().create(hmacAuthId);
	}

	/**
	* Removes the hmac authen with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hmacAuthId the primary key of the hmac authen
	* @return the hmac authen that was removed
	* @throws NoSuchHmacAuthenException if a hmac authen with the primary key could not be found
	*/
	public static HmacAuthen remove(long hmacAuthId)
		throws org.opencps.usermgt.exception.NoSuchHmacAuthenException {
		return getPersistence().remove(hmacAuthId);
	}

	public static HmacAuthen updateImpl(HmacAuthen hmacAuthen) {
		return getPersistence().updateImpl(hmacAuthen);
	}

	/**
	* Returns the hmac authen with the primary key or throws a {@link NoSuchHmacAuthenException} if it could not be found.
	*
	* @param hmacAuthId the primary key of the hmac authen
	* @return the hmac authen
	* @throws NoSuchHmacAuthenException if a hmac authen with the primary key could not be found
	*/
	public static HmacAuthen findByPrimaryKey(long hmacAuthId)
		throws org.opencps.usermgt.exception.NoSuchHmacAuthenException {
		return getPersistence().findByPrimaryKey(hmacAuthId);
	}

	/**
	* Returns the hmac authen with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hmacAuthId the primary key of the hmac authen
	* @return the hmac authen, or <code>null</code> if a hmac authen with the primary key could not be found
	*/
	public static HmacAuthen fetchByPrimaryKey(long hmacAuthId) {
		return getPersistence().fetchByPrimaryKey(hmacAuthId);
	}

	public static java.util.Map<java.io.Serializable, HmacAuthen> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the hmac authens.
	*
	* @return the hmac authens
	*/
	public static List<HmacAuthen> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<HmacAuthen> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<HmacAuthen> findAll(int start, int end,
		OrderByComparator<HmacAuthen> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<HmacAuthen> findAll(int start, int end,
		OrderByComparator<HmacAuthen> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the hmac authens from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of hmac authens.
	*
	* @return the number of hmac authens
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static HmacAuthenPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<HmacAuthenPersistence, HmacAuthenPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(HmacAuthenPersistence.class);

		ServiceTracker<HmacAuthenPersistence, HmacAuthenPersistence> serviceTracker =
			new ServiceTracker<HmacAuthenPersistence, HmacAuthenPersistence>(bundle.getBundleContext(),
				HmacAuthenPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}