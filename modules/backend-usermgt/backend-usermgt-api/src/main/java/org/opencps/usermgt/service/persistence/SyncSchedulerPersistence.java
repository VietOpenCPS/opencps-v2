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

import org.opencps.usermgt.exception.NoSuchSyncSchedulerException;
import org.opencps.usermgt.model.SyncScheduler;

import java.util.Date;

/**
 * The persistence interface for the sync scheduler service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see org.opencps.usermgt.service.persistence.impl.SyncSchedulerPersistenceImpl
 * @see SyncSchedulerUtil
 * @generated
 */
@ProviderType
public interface SyncSchedulerPersistence extends BasePersistence<SyncScheduler> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SyncSchedulerUtil} to access the sync scheduler persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the sync schedulers where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching sync schedulers
	*/
	public java.util.List<SyncScheduler> findByUuid(String uuid);

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
	public java.util.List<SyncScheduler> findByUuid(String uuid, int start,
		int end);

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
	public java.util.List<SyncScheduler> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncScheduler> orderByComparator);

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
	public java.util.List<SyncScheduler> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncScheduler> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first sync scheduler in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync scheduler
	* @throws NoSuchSyncSchedulerException if a matching sync scheduler could not be found
	*/
	public SyncScheduler findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SyncScheduler> orderByComparator)
		throws NoSuchSyncSchedulerException;

	/**
	* Returns the first sync scheduler in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync scheduler, or <code>null</code> if a matching sync scheduler could not be found
	*/
	public SyncScheduler fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SyncScheduler> orderByComparator);

	/**
	* Returns the last sync scheduler in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync scheduler
	* @throws NoSuchSyncSchedulerException if a matching sync scheduler could not be found
	*/
	public SyncScheduler findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SyncScheduler> orderByComparator)
		throws NoSuchSyncSchedulerException;

	/**
	* Returns the last sync scheduler in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync scheduler, or <code>null</code> if a matching sync scheduler could not be found
	*/
	public SyncScheduler fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SyncScheduler> orderByComparator);

	/**
	* Returns the sync schedulers before and after the current sync scheduler in the ordered set where uuid = &#63;.
	*
	* @param syncSchedulerId the primary key of the current sync scheduler
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync scheduler
	* @throws NoSuchSyncSchedulerException if a sync scheduler with the primary key could not be found
	*/
	public SyncScheduler[] findByUuid_PrevAndNext(long syncSchedulerId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SyncScheduler> orderByComparator)
		throws NoSuchSyncSchedulerException;

	/**
	* Removes all the sync schedulers where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of sync schedulers where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching sync schedulers
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the sync scheduler where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchSyncSchedulerException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching sync scheduler
	* @throws NoSuchSyncSchedulerException if a matching sync scheduler could not be found
	*/
	public SyncScheduler findByUUID_G(String uuid, long groupId)
		throws NoSuchSyncSchedulerException;

	/**
	* Returns the sync scheduler where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching sync scheduler, or <code>null</code> if a matching sync scheduler could not be found
	*/
	public SyncScheduler fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the sync scheduler where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching sync scheduler, or <code>null</code> if a matching sync scheduler could not be found
	*/
	public SyncScheduler fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the sync scheduler where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the sync scheduler that was removed
	*/
	public SyncScheduler removeByUUID_G(String uuid, long groupId)
		throws NoSuchSyncSchedulerException;

	/**
	* Returns the number of sync schedulers where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching sync schedulers
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns the sync scheduler where className = &#63; and syncDate = &#63; or throws a {@link NoSuchSyncSchedulerException} if it could not be found.
	*
	* @param className the class name
	* @param syncDate the sync date
	* @return the matching sync scheduler
	* @throws NoSuchSyncSchedulerException if a matching sync scheduler could not be found
	*/
	public SyncScheduler findByGID_NAME_SYNC(String className, Date syncDate)
		throws NoSuchSyncSchedulerException;

	/**
	* Returns the sync scheduler where className = &#63; and syncDate = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param className the class name
	* @param syncDate the sync date
	* @return the matching sync scheduler, or <code>null</code> if a matching sync scheduler could not be found
	*/
	public SyncScheduler fetchByGID_NAME_SYNC(String className, Date syncDate);

	/**
	* Returns the sync scheduler where className = &#63; and syncDate = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param className the class name
	* @param syncDate the sync date
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching sync scheduler, or <code>null</code> if a matching sync scheduler could not be found
	*/
	public SyncScheduler fetchByGID_NAME_SYNC(String className, Date syncDate,
		boolean retrieveFromCache);

	/**
	* Removes the sync scheduler where className = &#63; and syncDate = &#63; from the database.
	*
	* @param className the class name
	* @param syncDate the sync date
	* @return the sync scheduler that was removed
	*/
	public SyncScheduler removeByGID_NAME_SYNC(String className, Date syncDate)
		throws NoSuchSyncSchedulerException;

	/**
	* Returns the number of sync schedulers where className = &#63; and syncDate = &#63;.
	*
	* @param className the class name
	* @param syncDate the sync date
	* @return the number of matching sync schedulers
	*/
	public int countByGID_NAME_SYNC(String className, Date syncDate);

	/**
	* Returns the sync scheduler where className = &#63; and typeCode = &#63; or throws a {@link NoSuchSyncSchedulerException} if it could not be found.
	*
	* @param className the class name
	* @param typeCode the type code
	* @return the matching sync scheduler
	* @throws NoSuchSyncSchedulerException if a matching sync scheduler could not be found
	*/
	public SyncScheduler findByGID_NAME_TYPE(String className, String typeCode)
		throws NoSuchSyncSchedulerException;

	/**
	* Returns the sync scheduler where className = &#63; and typeCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param className the class name
	* @param typeCode the type code
	* @return the matching sync scheduler, or <code>null</code> if a matching sync scheduler could not be found
	*/
	public SyncScheduler fetchByGID_NAME_TYPE(String className, String typeCode);

	/**
	* Returns the sync scheduler where className = &#63; and typeCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param className the class name
	* @param typeCode the type code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching sync scheduler, or <code>null</code> if a matching sync scheduler could not be found
	*/
	public SyncScheduler fetchByGID_NAME_TYPE(String className,
		String typeCode, boolean retrieveFromCache);

	/**
	* Removes the sync scheduler where className = &#63; and typeCode = &#63; from the database.
	*
	* @param className the class name
	* @param typeCode the type code
	* @return the sync scheduler that was removed
	*/
	public SyncScheduler removeByGID_NAME_TYPE(String className, String typeCode)
		throws NoSuchSyncSchedulerException;

	/**
	* Returns the number of sync schedulers where className = &#63; and typeCode = &#63;.
	*
	* @param className the class name
	* @param typeCode the type code
	* @return the number of matching sync schedulers
	*/
	public int countByGID_NAME_TYPE(String className, String typeCode);

	/**
	* Returns all the sync schedulers where className = &#63; and retry &lt; &#63;.
	*
	* @param className the class name
	* @param retry the retry
	* @return the matching sync schedulers
	*/
	public java.util.List<SyncScheduler> findByF_NAME_RETRY(String className,
		int retry);

	/**
	* Returns a range of all the sync schedulers where className = &#63; and retry &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncSchedulerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param className the class name
	* @param retry the retry
	* @param start the lower bound of the range of sync schedulers
	* @param end the upper bound of the range of sync schedulers (not inclusive)
	* @return the range of matching sync schedulers
	*/
	public java.util.List<SyncScheduler> findByF_NAME_RETRY(String className,
		int retry, int start, int end);

	/**
	* Returns an ordered range of all the sync schedulers where className = &#63; and retry &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncSchedulerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param className the class name
	* @param retry the retry
	* @param start the lower bound of the range of sync schedulers
	* @param end the upper bound of the range of sync schedulers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sync schedulers
	*/
	public java.util.List<SyncScheduler> findByF_NAME_RETRY(String className,
		int retry, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncScheduler> orderByComparator);

	/**
	* Returns an ordered range of all the sync schedulers where className = &#63; and retry &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncSchedulerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param className the class name
	* @param retry the retry
	* @param start the lower bound of the range of sync schedulers
	* @param end the upper bound of the range of sync schedulers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sync schedulers
	*/
	public java.util.List<SyncScheduler> findByF_NAME_RETRY(String className,
		int retry, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncScheduler> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first sync scheduler in the ordered set where className = &#63; and retry &lt; &#63;.
	*
	* @param className the class name
	* @param retry the retry
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync scheduler
	* @throws NoSuchSyncSchedulerException if a matching sync scheduler could not be found
	*/
	public SyncScheduler findByF_NAME_RETRY_First(String className, int retry,
		com.liferay.portal.kernel.util.OrderByComparator<SyncScheduler> orderByComparator)
		throws NoSuchSyncSchedulerException;

	/**
	* Returns the first sync scheduler in the ordered set where className = &#63; and retry &lt; &#63;.
	*
	* @param className the class name
	* @param retry the retry
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync scheduler, or <code>null</code> if a matching sync scheduler could not be found
	*/
	public SyncScheduler fetchByF_NAME_RETRY_First(String className, int retry,
		com.liferay.portal.kernel.util.OrderByComparator<SyncScheduler> orderByComparator);

	/**
	* Returns the last sync scheduler in the ordered set where className = &#63; and retry &lt; &#63;.
	*
	* @param className the class name
	* @param retry the retry
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync scheduler
	* @throws NoSuchSyncSchedulerException if a matching sync scheduler could not be found
	*/
	public SyncScheduler findByF_NAME_RETRY_Last(String className, int retry,
		com.liferay.portal.kernel.util.OrderByComparator<SyncScheduler> orderByComparator)
		throws NoSuchSyncSchedulerException;

	/**
	* Returns the last sync scheduler in the ordered set where className = &#63; and retry &lt; &#63;.
	*
	* @param className the class name
	* @param retry the retry
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync scheduler, or <code>null</code> if a matching sync scheduler could not be found
	*/
	public SyncScheduler fetchByF_NAME_RETRY_Last(String className, int retry,
		com.liferay.portal.kernel.util.OrderByComparator<SyncScheduler> orderByComparator);

	/**
	* Returns the sync schedulers before and after the current sync scheduler in the ordered set where className = &#63; and retry &lt; &#63;.
	*
	* @param syncSchedulerId the primary key of the current sync scheduler
	* @param className the class name
	* @param retry the retry
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync scheduler
	* @throws NoSuchSyncSchedulerException if a sync scheduler with the primary key could not be found
	*/
	public SyncScheduler[] findByF_NAME_RETRY_PrevAndNext(
		long syncSchedulerId, String className, int retry,
		com.liferay.portal.kernel.util.OrderByComparator<SyncScheduler> orderByComparator)
		throws NoSuchSyncSchedulerException;

	/**
	* Removes all the sync schedulers where className = &#63; and retry &lt; &#63; from the database.
	*
	* @param className the class name
	* @param retry the retry
	*/
	public void removeByF_NAME_RETRY(String className, int retry);

	/**
	* Returns the number of sync schedulers where className = &#63; and retry &lt; &#63;.
	*
	* @param className the class name
	* @param retry the retry
	* @return the number of matching sync schedulers
	*/
	public int countByF_NAME_RETRY(String className, int retry);

	/**
	* Caches the sync scheduler in the entity cache if it is enabled.
	*
	* @param syncScheduler the sync scheduler
	*/
	public void cacheResult(SyncScheduler syncScheduler);

	/**
	* Caches the sync schedulers in the entity cache if it is enabled.
	*
	* @param syncSchedulers the sync schedulers
	*/
	public void cacheResult(java.util.List<SyncScheduler> syncSchedulers);

	/**
	* Creates a new sync scheduler with the primary key. Does not add the sync scheduler to the database.
	*
	* @param syncSchedulerId the primary key for the new sync scheduler
	* @return the new sync scheduler
	*/
	public SyncScheduler create(long syncSchedulerId);

	/**
	* Removes the sync scheduler with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param syncSchedulerId the primary key of the sync scheduler
	* @return the sync scheduler that was removed
	* @throws NoSuchSyncSchedulerException if a sync scheduler with the primary key could not be found
	*/
	public SyncScheduler remove(long syncSchedulerId)
		throws NoSuchSyncSchedulerException;

	public SyncScheduler updateImpl(SyncScheduler syncScheduler);

	/**
	* Returns the sync scheduler with the primary key or throws a {@link NoSuchSyncSchedulerException} if it could not be found.
	*
	* @param syncSchedulerId the primary key of the sync scheduler
	* @return the sync scheduler
	* @throws NoSuchSyncSchedulerException if a sync scheduler with the primary key could not be found
	*/
	public SyncScheduler findByPrimaryKey(long syncSchedulerId)
		throws NoSuchSyncSchedulerException;

	/**
	* Returns the sync scheduler with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param syncSchedulerId the primary key of the sync scheduler
	* @return the sync scheduler, or <code>null</code> if a sync scheduler with the primary key could not be found
	*/
	public SyncScheduler fetchByPrimaryKey(long syncSchedulerId);

	@Override
	public java.util.Map<java.io.Serializable, SyncScheduler> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the sync schedulers.
	*
	* @return the sync schedulers
	*/
	public java.util.List<SyncScheduler> findAll();

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
	public java.util.List<SyncScheduler> findAll(int start, int end);

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
	public java.util.List<SyncScheduler> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncScheduler> orderByComparator);

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
	public java.util.List<SyncScheduler> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncScheduler> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the sync schedulers from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of sync schedulers.
	*
	* @return the number of sync schedulers
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}