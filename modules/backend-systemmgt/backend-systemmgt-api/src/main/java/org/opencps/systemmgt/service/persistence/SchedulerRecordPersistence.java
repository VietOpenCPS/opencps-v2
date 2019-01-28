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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.systemmgt.exception.NoSuchSchedulerRecordException;
import org.opencps.systemmgt.model.SchedulerRecord;

/**
 * The persistence interface for the scheduler record service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see org.opencps.systemmgt.service.persistence.impl.SchedulerRecordPersistenceImpl
 * @see SchedulerRecordUtil
 * @generated
 */
@ProviderType
public interface SchedulerRecordPersistence extends BasePersistence<SchedulerRecord> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SchedulerRecordUtil} to access the scheduler record persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the scheduler record where schedulerType = &#63; or throws a {@link NoSuchSchedulerRecordException} if it could not be found.
	*
	* @param schedulerType the scheduler type
	* @return the matching scheduler record
	* @throws NoSuchSchedulerRecordException if a matching scheduler record could not be found
	*/
	public SchedulerRecord findByST(String schedulerType)
		throws NoSuchSchedulerRecordException;

	/**
	* Returns the scheduler record where schedulerType = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param schedulerType the scheduler type
	* @return the matching scheduler record, or <code>null</code> if a matching scheduler record could not be found
	*/
	public SchedulerRecord fetchByST(String schedulerType);

	/**
	* Returns the scheduler record where schedulerType = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param schedulerType the scheduler type
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching scheduler record, or <code>null</code> if a matching scheduler record could not be found
	*/
	public SchedulerRecord fetchByST(String schedulerType,
		boolean retrieveFromCache);

	/**
	* Removes the scheduler record where schedulerType = &#63; from the database.
	*
	* @param schedulerType the scheduler type
	* @return the scheduler record that was removed
	*/
	public SchedulerRecord removeByST(String schedulerType)
		throws NoSuchSchedulerRecordException;

	/**
	* Returns the number of scheduler records where schedulerType = &#63;.
	*
	* @param schedulerType the scheduler type
	* @return the number of matching scheduler records
	*/
	public int countByST(String schedulerType);

	/**
	* Caches the scheduler record in the entity cache if it is enabled.
	*
	* @param schedulerRecord the scheduler record
	*/
	public void cacheResult(SchedulerRecord schedulerRecord);

	/**
	* Caches the scheduler records in the entity cache if it is enabled.
	*
	* @param schedulerRecords the scheduler records
	*/
	public void cacheResult(java.util.List<SchedulerRecord> schedulerRecords);

	/**
	* Creates a new scheduler record with the primary key. Does not add the scheduler record to the database.
	*
	* @param schedulerId the primary key for the new scheduler record
	* @return the new scheduler record
	*/
	public SchedulerRecord create(long schedulerId);

	/**
	* Removes the scheduler record with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param schedulerId the primary key of the scheduler record
	* @return the scheduler record that was removed
	* @throws NoSuchSchedulerRecordException if a scheduler record with the primary key could not be found
	*/
	public SchedulerRecord remove(long schedulerId)
		throws NoSuchSchedulerRecordException;

	public SchedulerRecord updateImpl(SchedulerRecord schedulerRecord);

	/**
	* Returns the scheduler record with the primary key or throws a {@link NoSuchSchedulerRecordException} if it could not be found.
	*
	* @param schedulerId the primary key of the scheduler record
	* @return the scheduler record
	* @throws NoSuchSchedulerRecordException if a scheduler record with the primary key could not be found
	*/
	public SchedulerRecord findByPrimaryKey(long schedulerId)
		throws NoSuchSchedulerRecordException;

	/**
	* Returns the scheduler record with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param schedulerId the primary key of the scheduler record
	* @return the scheduler record, or <code>null</code> if a scheduler record with the primary key could not be found
	*/
	public SchedulerRecord fetchByPrimaryKey(long schedulerId);

	@Override
	public java.util.Map<java.io.Serializable, SchedulerRecord> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the scheduler records.
	*
	* @return the scheduler records
	*/
	public java.util.List<SchedulerRecord> findAll();

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
	public java.util.List<SchedulerRecord> findAll(int start, int end);

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
	public java.util.List<SchedulerRecord> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SchedulerRecord> orderByComparator);

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
	public java.util.List<SchedulerRecord> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SchedulerRecord> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the scheduler records from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of scheduler records.
	*
	* @return the number of scheduler records
	*/
	public int countAll();
}