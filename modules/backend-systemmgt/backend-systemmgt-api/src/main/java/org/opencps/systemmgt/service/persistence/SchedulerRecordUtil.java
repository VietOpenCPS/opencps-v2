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

package org.opencps.systemmgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.systemmgt.model.SchedulerRecord;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the scheduler record service. This utility wraps {@link org.opencps.systemmgt.service.persistence.impl.SchedulerRecordPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SchedulerRecordPersistence
 * @see org.opencps.systemmgt.service.persistence.impl.SchedulerRecordPersistenceImpl
 * @generated
 */
@ProviderType
public class SchedulerRecordUtil {
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
	public static void clearCache(SchedulerRecord schedulerRecord) {
		getPersistence().clearCache(schedulerRecord);
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
	public static List<SchedulerRecord> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SchedulerRecord> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SchedulerRecord> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SchedulerRecord> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static SchedulerRecord update(SchedulerRecord schedulerRecord) {
		return getPersistence().update(schedulerRecord);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static SchedulerRecord update(SchedulerRecord schedulerRecord,
		ServiceContext serviceContext) {
		return getPersistence().update(schedulerRecord, serviceContext);
	}

	/**
	* Returns the scheduler record where schedulerType = &#63; or throws a {@link NoSuchSchedulerRecordException} if it could not be found.
	*
	* @param schedulerType the scheduler type
	* @return the matching scheduler record
	* @throws NoSuchSchedulerRecordException if a matching scheduler record could not be found
	*/
	public static SchedulerRecord findByST(String schedulerType)
		throws org.opencps.systemmgt.exception.NoSuchSchedulerRecordException {
		return getPersistence().findByST(schedulerType);
	}

	/**
	* Returns the scheduler record where schedulerType = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param schedulerType the scheduler type
	* @return the matching scheduler record, or <code>null</code> if a matching scheduler record could not be found
	*/
	public static SchedulerRecord fetchByST(String schedulerType) {
		return getPersistence().fetchByST(schedulerType);
	}

	/**
	* Returns the scheduler record where schedulerType = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param schedulerType the scheduler type
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching scheduler record, or <code>null</code> if a matching scheduler record could not be found
	*/
	public static SchedulerRecord fetchByST(String schedulerType,
		boolean retrieveFromCache) {
		return getPersistence().fetchByST(schedulerType, retrieveFromCache);
	}

	/**
	* Removes the scheduler record where schedulerType = &#63; from the database.
	*
	* @param schedulerType the scheduler type
	* @return the scheduler record that was removed
	*/
	public static SchedulerRecord removeByST(String schedulerType)
		throws org.opencps.systemmgt.exception.NoSuchSchedulerRecordException {
		return getPersistence().removeByST(schedulerType);
	}

	/**
	* Returns the number of scheduler records where schedulerType = &#63;.
	*
	* @param schedulerType the scheduler type
	* @return the number of matching scheduler records
	*/
	public static int countByST(String schedulerType) {
		return getPersistence().countByST(schedulerType);
	}

	/**
	* Caches the scheduler record in the entity cache if it is enabled.
	*
	* @param schedulerRecord the scheduler record
	*/
	public static void cacheResult(SchedulerRecord schedulerRecord) {
		getPersistence().cacheResult(schedulerRecord);
	}

	/**
	* Caches the scheduler records in the entity cache if it is enabled.
	*
	* @param schedulerRecords the scheduler records
	*/
	public static void cacheResult(List<SchedulerRecord> schedulerRecords) {
		getPersistence().cacheResult(schedulerRecords);
	}

	/**
	* Creates a new scheduler record with the primary key. Does not add the scheduler record to the database.
	*
	* @param schedulerId the primary key for the new scheduler record
	* @return the new scheduler record
	*/
	public static SchedulerRecord create(long schedulerId) {
		return getPersistence().create(schedulerId);
	}

	/**
	* Removes the scheduler record with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param schedulerId the primary key of the scheduler record
	* @return the scheduler record that was removed
	* @throws NoSuchSchedulerRecordException if a scheduler record with the primary key could not be found
	*/
	public static SchedulerRecord remove(long schedulerId)
		throws org.opencps.systemmgt.exception.NoSuchSchedulerRecordException {
		return getPersistence().remove(schedulerId);
	}

	public static SchedulerRecord updateImpl(SchedulerRecord schedulerRecord) {
		return getPersistence().updateImpl(schedulerRecord);
	}

	/**
	* Returns the scheduler record with the primary key or throws a {@link NoSuchSchedulerRecordException} if it could not be found.
	*
	* @param schedulerId the primary key of the scheduler record
	* @return the scheduler record
	* @throws NoSuchSchedulerRecordException if a scheduler record with the primary key could not be found
	*/
	public static SchedulerRecord findByPrimaryKey(long schedulerId)
		throws org.opencps.systemmgt.exception.NoSuchSchedulerRecordException {
		return getPersistence().findByPrimaryKey(schedulerId);
	}

	/**
	* Returns the scheduler record with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param schedulerId the primary key of the scheduler record
	* @return the scheduler record, or <code>null</code> if a scheduler record with the primary key could not be found
	*/
	public static SchedulerRecord fetchByPrimaryKey(long schedulerId) {
		return getPersistence().fetchByPrimaryKey(schedulerId);
	}

	public static java.util.Map<java.io.Serializable, SchedulerRecord> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the scheduler records.
	*
	* @return the scheduler records
	*/
	public static List<SchedulerRecord> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the scheduler records.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SchedulerRecordModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of scheduler records
	* @param end the upper bound of the range of scheduler records (not inclusive)
	* @return the range of scheduler records
	*/
	public static List<SchedulerRecord> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the scheduler records.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SchedulerRecordModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of scheduler records
	* @param end the upper bound of the range of scheduler records (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of scheduler records
	*/
	public static List<SchedulerRecord> findAll(int start, int end,
		OrderByComparator<SchedulerRecord> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the scheduler records.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SchedulerRecordModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of scheduler records
	* @param end the upper bound of the range of scheduler records (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of scheduler records
	*/
	public static List<SchedulerRecord> findAll(int start, int end,
		OrderByComparator<SchedulerRecord> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the scheduler records from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of scheduler records.
	*
	* @return the number of scheduler records
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static SchedulerRecordPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<SchedulerRecordPersistence, SchedulerRecordPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(SchedulerRecordPersistence.class);

		ServiceTracker<SchedulerRecordPersistence, SchedulerRecordPersistence> serviceTracker =
			new ServiceTracker<SchedulerRecordPersistence, SchedulerRecordPersistence>(bundle.getBundleContext(),
				SchedulerRecordPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}