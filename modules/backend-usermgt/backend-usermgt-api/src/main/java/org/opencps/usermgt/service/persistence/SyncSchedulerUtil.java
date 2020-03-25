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

import org.opencps.usermgt.model.SyncScheduler;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.Date;
import java.util.List;

/**
 * The persistence utility for the sync scheduler service. This utility wraps {@link org.opencps.usermgt.service.persistence.impl.SyncSchedulerPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see SyncSchedulerPersistence
 * @see org.opencps.usermgt.service.persistence.impl.SyncSchedulerPersistenceImpl
 * @generated
 */
@ProviderType
public class SyncSchedulerUtil {
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
	public static void clearCache(SyncScheduler syncScheduler) {
		getPersistence().clearCache(syncScheduler);
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
	public static List<SyncScheduler> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SyncScheduler> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SyncScheduler> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SyncScheduler> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static SyncScheduler update(SyncScheduler syncScheduler) {
		return getPersistence().update(syncScheduler);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static SyncScheduler update(SyncScheduler syncScheduler,
		ServiceContext serviceContext) {
		return getPersistence().update(syncScheduler, serviceContext);
	}

	/**
	* Returns all the sync schedulers where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching sync schedulers
	*/
	public static List<SyncScheduler> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the sync schedulers where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncSchedulerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of sync schedulers
	* @param end the upper bound of the range of sync schedulers (not inclusive)
	* @return the range of matching sync schedulers
	*/
	public static List<SyncScheduler> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the sync schedulers where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncSchedulerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of sync schedulers
	* @param end the upper bound of the range of sync schedulers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sync schedulers
	*/
	public static List<SyncScheduler> findByUuid(String uuid, int start,
		int end, OrderByComparator<SyncScheduler> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the sync schedulers where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncSchedulerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of sync schedulers
	* @param end the upper bound of the range of sync schedulers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sync schedulers
	*/
	public static List<SyncScheduler> findByUuid(String uuid, int start,
		int end, OrderByComparator<SyncScheduler> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first sync scheduler in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync scheduler
	* @throws NoSuchSyncSchedulerException if a matching sync scheduler could not be found
	*/
	public static SyncScheduler findByUuid_First(String uuid,
		OrderByComparator<SyncScheduler> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchSyncSchedulerException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first sync scheduler in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync scheduler, or <code>null</code> if a matching sync scheduler could not be found
	*/
	public static SyncScheduler fetchByUuid_First(String uuid,
		OrderByComparator<SyncScheduler> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last sync scheduler in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync scheduler
	* @throws NoSuchSyncSchedulerException if a matching sync scheduler could not be found
	*/
	public static SyncScheduler findByUuid_Last(String uuid,
		OrderByComparator<SyncScheduler> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchSyncSchedulerException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last sync scheduler in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync scheduler, or <code>null</code> if a matching sync scheduler could not be found
	*/
	public static SyncScheduler fetchByUuid_Last(String uuid,
		OrderByComparator<SyncScheduler> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the sync schedulers before and after the current sync scheduler in the ordered set where uuid = &#63;.
	*
	* @param syncSchedulerId the primary key of the current sync scheduler
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync scheduler
	* @throws NoSuchSyncSchedulerException if a sync scheduler with the primary key could not be found
	*/
	public static SyncScheduler[] findByUuid_PrevAndNext(long syncSchedulerId,
		String uuid, OrderByComparator<SyncScheduler> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchSyncSchedulerException {
		return getPersistence()
				   .findByUuid_PrevAndNext(syncSchedulerId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the sync schedulers where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of sync schedulers where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching sync schedulers
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the sync scheduler where className = &#63; and syncDate = &#63; or throws a {@link NoSuchSyncSchedulerException} if it could not be found.
	*
	* @param className the class name
	* @param syncDate the sync date
	* @return the matching sync scheduler
	* @throws NoSuchSyncSchedulerException if a matching sync scheduler could not be found
	*/
	public static SyncScheduler findByGID_NAME_SYNC(String className,
		Date syncDate)
		throws org.opencps.usermgt.exception.NoSuchSyncSchedulerException {
		return getPersistence().findByGID_NAME_SYNC(className, syncDate);
	}

	/**
	* Returns the sync scheduler where className = &#63; and syncDate = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param className the class name
	* @param syncDate the sync date
	* @return the matching sync scheduler, or <code>null</code> if a matching sync scheduler could not be found
	*/
	public static SyncScheduler fetchByGID_NAME_SYNC(String className,
		Date syncDate) {
		return getPersistence().fetchByGID_NAME_SYNC(className, syncDate);
	}

	/**
	* Returns the sync scheduler where className = &#63; and syncDate = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param className the class name
	* @param syncDate the sync date
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching sync scheduler, or <code>null</code> if a matching sync scheduler could not be found
	*/
	public static SyncScheduler fetchByGID_NAME_SYNC(String className,
		Date syncDate, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByGID_NAME_SYNC(className, syncDate, retrieveFromCache);
	}

	/**
	* Removes the sync scheduler where className = &#63; and syncDate = &#63; from the database.
	*
	* @param className the class name
	* @param syncDate the sync date
	* @return the sync scheduler that was removed
	*/
	public static SyncScheduler removeByGID_NAME_SYNC(String className,
		Date syncDate)
		throws org.opencps.usermgt.exception.NoSuchSyncSchedulerException {
		return getPersistence().removeByGID_NAME_SYNC(className, syncDate);
	}

	/**
	* Returns the number of sync schedulers where className = &#63; and syncDate = &#63;.
	*
	* @param className the class name
	* @param syncDate the sync date
	* @return the number of matching sync schedulers
	*/
	public static int countByGID_NAME_SYNC(String className, Date syncDate) {
		return getPersistence().countByGID_NAME_SYNC(className, syncDate);
	}

	/**
	* Returns the sync scheduler where className = &#63; and typeCode = &#63; or throws a {@link NoSuchSyncSchedulerException} if it could not be found.
	*
	* @param className the class name
	* @param typeCode the type code
	* @return the matching sync scheduler
	* @throws NoSuchSyncSchedulerException if a matching sync scheduler could not be found
	*/
	public static SyncScheduler findByGID_NAME_TYPE(String className,
		String typeCode)
		throws org.opencps.usermgt.exception.NoSuchSyncSchedulerException {
		return getPersistence().findByGID_NAME_TYPE(className, typeCode);
	}

	/**
	* Returns the sync scheduler where className = &#63; and typeCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param className the class name
	* @param typeCode the type code
	* @return the matching sync scheduler, or <code>null</code> if a matching sync scheduler could not be found
	*/
	public static SyncScheduler fetchByGID_NAME_TYPE(String className,
		String typeCode) {
		return getPersistence().fetchByGID_NAME_TYPE(className, typeCode);
	}

	/**
	* Returns the sync scheduler where className = &#63; and typeCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param className the class name
	* @param typeCode the type code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching sync scheduler, or <code>null</code> if a matching sync scheduler could not be found
	*/
	public static SyncScheduler fetchByGID_NAME_TYPE(String className,
		String typeCode, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByGID_NAME_TYPE(className, typeCode, retrieveFromCache);
	}

	/**
	* Removes the sync scheduler where className = &#63; and typeCode = &#63; from the database.
	*
	* @param className the class name
	* @param typeCode the type code
	* @return the sync scheduler that was removed
	*/
	public static SyncScheduler removeByGID_NAME_TYPE(String className,
		String typeCode)
		throws org.opencps.usermgt.exception.NoSuchSyncSchedulerException {
		return getPersistence().removeByGID_NAME_TYPE(className, typeCode);
	}

	/**
	* Returns the number of sync schedulers where className = &#63; and typeCode = &#63;.
	*
	* @param className the class name
	* @param typeCode the type code
	* @return the number of matching sync schedulers
	*/
	public static int countByGID_NAME_TYPE(String className, String typeCode) {
		return getPersistence().countByGID_NAME_TYPE(className, typeCode);
	}

	/**
	* Caches the sync scheduler in the entity cache if it is enabled.
	*
	* @param syncScheduler the sync scheduler
	*/
	public static void cacheResult(SyncScheduler syncScheduler) {
		getPersistence().cacheResult(syncScheduler);
	}

	/**
	* Caches the sync schedulers in the entity cache if it is enabled.
	*
	* @param syncSchedulers the sync schedulers
	*/
	public static void cacheResult(List<SyncScheduler> syncSchedulers) {
		getPersistence().cacheResult(syncSchedulers);
	}

	/**
	* Creates a new sync scheduler with the primary key. Does not add the sync scheduler to the database.
	*
	* @param syncSchedulerId the primary key for the new sync scheduler
	* @return the new sync scheduler
	*/
	public static SyncScheduler create(long syncSchedulerId) {
		return getPersistence().create(syncSchedulerId);
	}

	/**
	* Removes the sync scheduler with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param syncSchedulerId the primary key of the sync scheduler
	* @return the sync scheduler that was removed
	* @throws NoSuchSyncSchedulerException if a sync scheduler with the primary key could not be found
	*/
	public static SyncScheduler remove(long syncSchedulerId)
		throws org.opencps.usermgt.exception.NoSuchSyncSchedulerException {
		return getPersistence().remove(syncSchedulerId);
	}

	public static SyncScheduler updateImpl(SyncScheduler syncScheduler) {
		return getPersistence().updateImpl(syncScheduler);
	}

	/**
	* Returns the sync scheduler with the primary key or throws a {@link NoSuchSyncSchedulerException} if it could not be found.
	*
	* @param syncSchedulerId the primary key of the sync scheduler
	* @return the sync scheduler
	* @throws NoSuchSyncSchedulerException if a sync scheduler with the primary key could not be found
	*/
	public static SyncScheduler findByPrimaryKey(long syncSchedulerId)
		throws org.opencps.usermgt.exception.NoSuchSyncSchedulerException {
		return getPersistence().findByPrimaryKey(syncSchedulerId);
	}

	/**
	* Returns the sync scheduler with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param syncSchedulerId the primary key of the sync scheduler
	* @return the sync scheduler, or <code>null</code> if a sync scheduler with the primary key could not be found
	*/
	public static SyncScheduler fetchByPrimaryKey(long syncSchedulerId) {
		return getPersistence().fetchByPrimaryKey(syncSchedulerId);
	}

	public static java.util.Map<java.io.Serializable, SyncScheduler> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the sync schedulers.
	*
	* @return the sync schedulers
	*/
	public static List<SyncScheduler> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the sync schedulers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncSchedulerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sync schedulers
	* @param end the upper bound of the range of sync schedulers (not inclusive)
	* @return the range of sync schedulers
	*/
	public static List<SyncScheduler> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the sync schedulers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncSchedulerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sync schedulers
	* @param end the upper bound of the range of sync schedulers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of sync schedulers
	*/
	public static List<SyncScheduler> findAll(int start, int end,
		OrderByComparator<SyncScheduler> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the sync schedulers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncSchedulerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sync schedulers
	* @param end the upper bound of the range of sync schedulers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of sync schedulers
	*/
	public static List<SyncScheduler> findAll(int start, int end,
		OrderByComparator<SyncScheduler> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the sync schedulers from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of sync schedulers.
	*
	* @return the number of sync schedulers
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static SyncSchedulerPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<SyncSchedulerPersistence, SyncSchedulerPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(SyncSchedulerPersistence.class);

		ServiceTracker<SyncSchedulerPersistence, SyncSchedulerPersistence> serviceTracker =
			new ServiceTracker<SyncSchedulerPersistence, SyncSchedulerPersistence>(bundle.getBundleContext(),
				SyncSchedulerPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}