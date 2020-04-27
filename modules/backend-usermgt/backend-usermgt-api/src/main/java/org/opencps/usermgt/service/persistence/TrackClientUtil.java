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

import org.opencps.usermgt.model.TrackClient;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the track client service. This utility wraps {@link org.opencps.usermgt.service.persistence.impl.TrackClientPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see TrackClientPersistence
 * @see org.opencps.usermgt.service.persistence.impl.TrackClientPersistenceImpl
 * @generated
 */
@ProviderType
public class TrackClientUtil {
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
	public static void clearCache(TrackClient trackClient) {
		getPersistence().clearCache(trackClient);
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
	public static List<TrackClient> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TrackClient> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TrackClient> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<TrackClient> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static TrackClient update(TrackClient trackClient) {
		return getPersistence().update(trackClient);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static TrackClient update(TrackClient trackClient,
		ServiceContext serviceContext) {
		return getPersistence().update(trackClient, serviceContext);
	}

	/**
	* Returns all the track clients where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching track clients
	*/
	public static List<TrackClient> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the track clients where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of track clients
	* @param end the upper bound of the range of track clients (not inclusive)
	* @return the range of matching track clients
	*/
	public static List<TrackClient> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the track clients where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of track clients
	* @param end the upper bound of the range of track clients (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching track clients
	*/
	public static List<TrackClient> findByUuid(String uuid, int start, int end,
		OrderByComparator<TrackClient> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the track clients where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of track clients
	* @param end the upper bound of the range of track clients (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching track clients
	*/
	public static List<TrackClient> findByUuid(String uuid, int start, int end,
		OrderByComparator<TrackClient> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first track client in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching track client
	* @throws NoSuchTrackClientException if a matching track client could not be found
	*/
	public static TrackClient findByUuid_First(String uuid,
		OrderByComparator<TrackClient> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchTrackClientException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first track client in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching track client, or <code>null</code> if a matching track client could not be found
	*/
	public static TrackClient fetchByUuid_First(String uuid,
		OrderByComparator<TrackClient> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last track client in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching track client
	* @throws NoSuchTrackClientException if a matching track client could not be found
	*/
	public static TrackClient findByUuid_Last(String uuid,
		OrderByComparator<TrackClient> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchTrackClientException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last track client in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching track client, or <code>null</code> if a matching track client could not be found
	*/
	public static TrackClient fetchByUuid_Last(String uuid,
		OrderByComparator<TrackClient> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the track clients before and after the current track client in the ordered set where uuid = &#63;.
	*
	* @param trackClientId the primary key of the current track client
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next track client
	* @throws NoSuchTrackClientException if a track client with the primary key could not be found
	*/
	public static TrackClient[] findByUuid_PrevAndNext(long trackClientId,
		String uuid, OrderByComparator<TrackClient> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchTrackClientException {
		return getPersistence()
				   .findByUuid_PrevAndNext(trackClientId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the track clients where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of track clients where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching track clients
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Caches the track client in the entity cache if it is enabled.
	*
	* @param trackClient the track client
	*/
	public static void cacheResult(TrackClient trackClient) {
		getPersistence().cacheResult(trackClient);
	}

	/**
	* Caches the track clients in the entity cache if it is enabled.
	*
	* @param trackClients the track clients
	*/
	public static void cacheResult(List<TrackClient> trackClients) {
		getPersistence().cacheResult(trackClients);
	}

	/**
	* Creates a new track client with the primary key. Does not add the track client to the database.
	*
	* @param trackClientId the primary key for the new track client
	* @return the new track client
	*/
	public static TrackClient create(long trackClientId) {
		return getPersistence().create(trackClientId);
	}

	/**
	* Removes the track client with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trackClientId the primary key of the track client
	* @return the track client that was removed
	* @throws NoSuchTrackClientException if a track client with the primary key could not be found
	*/
	public static TrackClient remove(long trackClientId)
		throws org.opencps.usermgt.exception.NoSuchTrackClientException {
		return getPersistence().remove(trackClientId);
	}

	public static TrackClient updateImpl(TrackClient trackClient) {
		return getPersistence().updateImpl(trackClient);
	}

	/**
	* Returns the track client with the primary key or throws a {@link NoSuchTrackClientException} if it could not be found.
	*
	* @param trackClientId the primary key of the track client
	* @return the track client
	* @throws NoSuchTrackClientException if a track client with the primary key could not be found
	*/
	public static TrackClient findByPrimaryKey(long trackClientId)
		throws org.opencps.usermgt.exception.NoSuchTrackClientException {
		return getPersistence().findByPrimaryKey(trackClientId);
	}

	/**
	* Returns the track client with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param trackClientId the primary key of the track client
	* @return the track client, or <code>null</code> if a track client with the primary key could not be found
	*/
	public static TrackClient fetchByPrimaryKey(long trackClientId) {
		return getPersistence().fetchByPrimaryKey(trackClientId);
	}

	public static java.util.Map<java.io.Serializable, TrackClient> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the track clients.
	*
	* @return the track clients
	*/
	public static List<TrackClient> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the track clients.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of track clients
	* @param end the upper bound of the range of track clients (not inclusive)
	* @return the range of track clients
	*/
	public static List<TrackClient> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the track clients.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of track clients
	* @param end the upper bound of the range of track clients (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of track clients
	*/
	public static List<TrackClient> findAll(int start, int end,
		OrderByComparator<TrackClient> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the track clients.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of track clients
	* @param end the upper bound of the range of track clients (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of track clients
	*/
	public static List<TrackClient> findAll(int start, int end,
		OrderByComparator<TrackClient> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the track clients from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of track clients.
	*
	* @return the number of track clients
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static TrackClientPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<TrackClientPersistence, TrackClientPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(TrackClientPersistence.class);

		ServiceTracker<TrackClientPersistence, TrackClientPersistence> serviceTracker =
			new ServiceTracker<TrackClientPersistence, TrackClientPersistence>(bundle.getBundleContext(),
				TrackClientPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}