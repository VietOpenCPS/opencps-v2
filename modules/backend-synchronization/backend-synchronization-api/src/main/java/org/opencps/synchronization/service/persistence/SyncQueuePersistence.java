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

package org.opencps.synchronization.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.synchronization.exception.NoSuchSyncQueueException;
import org.opencps.synchronization.model.SyncQueue;

/**
 * The persistence interface for the sync queue service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author trungdk
 * @see org.opencps.synchronization.service.persistence.impl.SyncQueuePersistenceImpl
 * @see SyncQueueUtil
 * @generated
 */
@ProviderType
public interface SyncQueuePersistence extends BasePersistence<SyncQueue> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SyncQueueUtil} to access the sync queue persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the sync queues where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching sync queues
	*/
	public java.util.List<SyncQueue> findByUuid(String uuid);

	/**
	* Returns a range of all the sync queues where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of sync queues
	* @param end the upper bound of the range of sync queues (not inclusive)
	* @return the range of matching sync queues
	*/
	public java.util.List<SyncQueue> findByUuid(String uuid, int start, int end);

	/**
	* Returns an ordered range of all the sync queues where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of sync queues
	* @param end the upper bound of the range of sync queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sync queues
	*/
	public java.util.List<SyncQueue> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncQueue> orderByComparator);

	/**
	* Returns an ordered range of all the sync queues where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of sync queues
	* @param end the upper bound of the range of sync queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sync queues
	*/
	public java.util.List<SyncQueue> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncQueue> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first sync queue in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync queue
	* @throws NoSuchSyncQueueException if a matching sync queue could not be found
	*/
	public SyncQueue findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SyncQueue> orderByComparator)
		throws NoSuchSyncQueueException;

	/**
	* Returns the first sync queue in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync queue, or <code>null</code> if a matching sync queue could not be found
	*/
	public SyncQueue fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SyncQueue> orderByComparator);

	/**
	* Returns the last sync queue in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync queue
	* @throws NoSuchSyncQueueException if a matching sync queue could not be found
	*/
	public SyncQueue findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SyncQueue> orderByComparator)
		throws NoSuchSyncQueueException;

	/**
	* Returns the last sync queue in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync queue, or <code>null</code> if a matching sync queue could not be found
	*/
	public SyncQueue fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SyncQueue> orderByComparator);

	/**
	* Returns the sync queues before and after the current sync queue in the ordered set where uuid = &#63;.
	*
	* @param syncQueueId the primary key of the current sync queue
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync queue
	* @throws NoSuchSyncQueueException if a sync queue with the primary key could not be found
	*/
	public SyncQueue[] findByUuid_PrevAndNext(long syncQueueId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SyncQueue> orderByComparator)
		throws NoSuchSyncQueueException;

	/**
	* Removes all the sync queues where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of sync queues where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching sync queues
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the sync queue where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchSyncQueueException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching sync queue
	* @throws NoSuchSyncQueueException if a matching sync queue could not be found
	*/
	public SyncQueue findByUUID_G(String uuid, long groupId)
		throws NoSuchSyncQueueException;

	/**
	* Returns the sync queue where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching sync queue, or <code>null</code> if a matching sync queue could not be found
	*/
	public SyncQueue fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the sync queue where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching sync queue, or <code>null</code> if a matching sync queue could not be found
	*/
	public SyncQueue fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the sync queue where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the sync queue that was removed
	*/
	public SyncQueue removeByUUID_G(String uuid, long groupId)
		throws NoSuchSyncQueueException;

	/**
	* Returns the number of sync queues where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching sync queues
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the sync queues where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching sync queues
	*/
	public java.util.List<SyncQueue> findByUuid_C(String uuid, long companyId);

	/**
	* Returns a range of all the sync queues where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of sync queues
	* @param end the upper bound of the range of sync queues (not inclusive)
	* @return the range of matching sync queues
	*/
	public java.util.List<SyncQueue> findByUuid_C(String uuid, long companyId,
		int start, int end);

	/**
	* Returns an ordered range of all the sync queues where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of sync queues
	* @param end the upper bound of the range of sync queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sync queues
	*/
	public java.util.List<SyncQueue> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncQueue> orderByComparator);

	/**
	* Returns an ordered range of all the sync queues where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of sync queues
	* @param end the upper bound of the range of sync queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sync queues
	*/
	public java.util.List<SyncQueue> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncQueue> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first sync queue in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync queue
	* @throws NoSuchSyncQueueException if a matching sync queue could not be found
	*/
	public SyncQueue findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SyncQueue> orderByComparator)
		throws NoSuchSyncQueueException;

	/**
	* Returns the first sync queue in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync queue, or <code>null</code> if a matching sync queue could not be found
	*/
	public SyncQueue fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SyncQueue> orderByComparator);

	/**
	* Returns the last sync queue in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync queue
	* @throws NoSuchSyncQueueException if a matching sync queue could not be found
	*/
	public SyncQueue findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SyncQueue> orderByComparator)
		throws NoSuchSyncQueueException;

	/**
	* Returns the last sync queue in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync queue, or <code>null</code> if a matching sync queue could not be found
	*/
	public SyncQueue fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SyncQueue> orderByComparator);

	/**
	* Returns the sync queues before and after the current sync queue in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param syncQueueId the primary key of the current sync queue
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync queue
	* @throws NoSuchSyncQueueException if a sync queue with the primary key could not be found
	*/
	public SyncQueue[] findByUuid_C_PrevAndNext(long syncQueueId, String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SyncQueue> orderByComparator)
		throws NoSuchSyncQueueException;

	/**
	* Removes all the sync queues where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of sync queues where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching sync queues
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the sync queues where groupId = &#63; and className = &#63; and method = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param method the method
	* @return the matching sync queues
	*/
	public java.util.List<SyncQueue> findByF_className_Method(long groupId,
		String className, String method);

	/**
	* Returns a range of all the sync queues where groupId = &#63; and className = &#63; and method = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param className the class name
	* @param method the method
	* @param start the lower bound of the range of sync queues
	* @param end the upper bound of the range of sync queues (not inclusive)
	* @return the range of matching sync queues
	*/
	public java.util.List<SyncQueue> findByF_className_Method(long groupId,
		String className, String method, int start, int end);

	/**
	* Returns an ordered range of all the sync queues where groupId = &#63; and className = &#63; and method = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param className the class name
	* @param method the method
	* @param start the lower bound of the range of sync queues
	* @param end the upper bound of the range of sync queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sync queues
	*/
	public java.util.List<SyncQueue> findByF_className_Method(long groupId,
		String className, String method, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncQueue> orderByComparator);

	/**
	* Returns an ordered range of all the sync queues where groupId = &#63; and className = &#63; and method = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param className the class name
	* @param method the method
	* @param start the lower bound of the range of sync queues
	* @param end the upper bound of the range of sync queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sync queues
	*/
	public java.util.List<SyncQueue> findByF_className_Method(long groupId,
		String className, String method, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncQueue> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first sync queue in the ordered set where groupId = &#63; and className = &#63; and method = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param method the method
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync queue
	* @throws NoSuchSyncQueueException if a matching sync queue could not be found
	*/
	public SyncQueue findByF_className_Method_First(long groupId,
		String className, String method,
		com.liferay.portal.kernel.util.OrderByComparator<SyncQueue> orderByComparator)
		throws NoSuchSyncQueueException;

	/**
	* Returns the first sync queue in the ordered set where groupId = &#63; and className = &#63; and method = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param method the method
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync queue, or <code>null</code> if a matching sync queue could not be found
	*/
	public SyncQueue fetchByF_className_Method_First(long groupId,
		String className, String method,
		com.liferay.portal.kernel.util.OrderByComparator<SyncQueue> orderByComparator);

	/**
	* Returns the last sync queue in the ordered set where groupId = &#63; and className = &#63; and method = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param method the method
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync queue
	* @throws NoSuchSyncQueueException if a matching sync queue could not be found
	*/
	public SyncQueue findByF_className_Method_Last(long groupId,
		String className, String method,
		com.liferay.portal.kernel.util.OrderByComparator<SyncQueue> orderByComparator)
		throws NoSuchSyncQueueException;

	/**
	* Returns the last sync queue in the ordered set where groupId = &#63; and className = &#63; and method = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param method the method
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync queue, or <code>null</code> if a matching sync queue could not be found
	*/
	public SyncQueue fetchByF_className_Method_Last(long groupId,
		String className, String method,
		com.liferay.portal.kernel.util.OrderByComparator<SyncQueue> orderByComparator);

	/**
	* Returns the sync queues before and after the current sync queue in the ordered set where groupId = &#63; and className = &#63; and method = &#63;.
	*
	* @param syncQueueId the primary key of the current sync queue
	* @param groupId the group ID
	* @param className the class name
	* @param method the method
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync queue
	* @throws NoSuchSyncQueueException if a sync queue with the primary key could not be found
	*/
	public SyncQueue[] findByF_className_Method_PrevAndNext(long syncQueueId,
		long groupId, String className, String method,
		com.liferay.portal.kernel.util.OrderByComparator<SyncQueue> orderByComparator)
		throws NoSuchSyncQueueException;

	/**
	* Removes all the sync queues where groupId = &#63; and className = &#63; and method = &#63; from the database.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param method the method
	*/
	public void removeByF_className_Method(long groupId, String className,
		String method);

	/**
	* Returns the number of sync queues where groupId = &#63; and className = &#63; and method = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param method the method
	* @return the number of matching sync queues
	*/
	public int countByF_className_Method(long groupId, String className,
		String method);

	/**
	* Returns all the sync queues where groupId = &#63; and serverNo = &#63;.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @return the matching sync queues
	*/
	public java.util.List<SyncQueue> findByF_groupId_serverNo(long groupId,
		String serverNo);

	/**
	* Returns a range of all the sync queues where groupId = &#63; and serverNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param start the lower bound of the range of sync queues
	* @param end the upper bound of the range of sync queues (not inclusive)
	* @return the range of matching sync queues
	*/
	public java.util.List<SyncQueue> findByF_groupId_serverNo(long groupId,
		String serverNo, int start, int end);

	/**
	* Returns an ordered range of all the sync queues where groupId = &#63; and serverNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param start the lower bound of the range of sync queues
	* @param end the upper bound of the range of sync queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sync queues
	*/
	public java.util.List<SyncQueue> findByF_groupId_serverNo(long groupId,
		String serverNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncQueue> orderByComparator);

	/**
	* Returns an ordered range of all the sync queues where groupId = &#63; and serverNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param start the lower bound of the range of sync queues
	* @param end the upper bound of the range of sync queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sync queues
	*/
	public java.util.List<SyncQueue> findByF_groupId_serverNo(long groupId,
		String serverNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncQueue> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first sync queue in the ordered set where groupId = &#63; and serverNo = &#63;.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync queue
	* @throws NoSuchSyncQueueException if a matching sync queue could not be found
	*/
	public SyncQueue findByF_groupId_serverNo_First(long groupId,
		String serverNo,
		com.liferay.portal.kernel.util.OrderByComparator<SyncQueue> orderByComparator)
		throws NoSuchSyncQueueException;

	/**
	* Returns the first sync queue in the ordered set where groupId = &#63; and serverNo = &#63;.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync queue, or <code>null</code> if a matching sync queue could not be found
	*/
	public SyncQueue fetchByF_groupId_serverNo_First(long groupId,
		String serverNo,
		com.liferay.portal.kernel.util.OrderByComparator<SyncQueue> orderByComparator);

	/**
	* Returns the last sync queue in the ordered set where groupId = &#63; and serverNo = &#63;.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync queue
	* @throws NoSuchSyncQueueException if a matching sync queue could not be found
	*/
	public SyncQueue findByF_groupId_serverNo_Last(long groupId,
		String serverNo,
		com.liferay.portal.kernel.util.OrderByComparator<SyncQueue> orderByComparator)
		throws NoSuchSyncQueueException;

	/**
	* Returns the last sync queue in the ordered set where groupId = &#63; and serverNo = &#63;.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync queue, or <code>null</code> if a matching sync queue could not be found
	*/
	public SyncQueue fetchByF_groupId_serverNo_Last(long groupId,
		String serverNo,
		com.liferay.portal.kernel.util.OrderByComparator<SyncQueue> orderByComparator);

	/**
	* Returns the sync queues before and after the current sync queue in the ordered set where groupId = &#63; and serverNo = &#63;.
	*
	* @param syncQueueId the primary key of the current sync queue
	* @param groupId the group ID
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync queue
	* @throws NoSuchSyncQueueException if a sync queue with the primary key could not be found
	*/
	public SyncQueue[] findByF_groupId_serverNo_PrevAndNext(long syncQueueId,
		long groupId, String serverNo,
		com.liferay.portal.kernel.util.OrderByComparator<SyncQueue> orderByComparator)
		throws NoSuchSyncQueueException;

	/**
	* Removes all the sync queues where groupId = &#63; and serverNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	*/
	public void removeByF_groupId_serverNo(long groupId, String serverNo);

	/**
	* Returns the number of sync queues where groupId = &#63; and serverNo = &#63;.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @return the number of matching sync queues
	*/
	public int countByF_groupId_serverNo(long groupId, String serverNo);

	/**
	* Caches the sync queue in the entity cache if it is enabled.
	*
	* @param syncQueue the sync queue
	*/
	public void cacheResult(SyncQueue syncQueue);

	/**
	* Caches the sync queues in the entity cache if it is enabled.
	*
	* @param syncQueues the sync queues
	*/
	public void cacheResult(java.util.List<SyncQueue> syncQueues);

	/**
	* Creates a new sync queue with the primary key. Does not add the sync queue to the database.
	*
	* @param syncQueueId the primary key for the new sync queue
	* @return the new sync queue
	*/
	public SyncQueue create(long syncQueueId);

	/**
	* Removes the sync queue with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param syncQueueId the primary key of the sync queue
	* @return the sync queue that was removed
	* @throws NoSuchSyncQueueException if a sync queue with the primary key could not be found
	*/
	public SyncQueue remove(long syncQueueId) throws NoSuchSyncQueueException;

	public SyncQueue updateImpl(SyncQueue syncQueue);

	/**
	* Returns the sync queue with the primary key or throws a {@link NoSuchSyncQueueException} if it could not be found.
	*
	* @param syncQueueId the primary key of the sync queue
	* @return the sync queue
	* @throws NoSuchSyncQueueException if a sync queue with the primary key could not be found
	*/
	public SyncQueue findByPrimaryKey(long syncQueueId)
		throws NoSuchSyncQueueException;

	/**
	* Returns the sync queue with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param syncQueueId the primary key of the sync queue
	* @return the sync queue, or <code>null</code> if a sync queue with the primary key could not be found
	*/
	public SyncQueue fetchByPrimaryKey(long syncQueueId);

	@Override
	public java.util.Map<java.io.Serializable, SyncQueue> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the sync queues.
	*
	* @return the sync queues
	*/
	public java.util.List<SyncQueue> findAll();

	/**
	* Returns a range of all the sync queues.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sync queues
	* @param end the upper bound of the range of sync queues (not inclusive)
	* @return the range of sync queues
	*/
	public java.util.List<SyncQueue> findAll(int start, int end);

	/**
	* Returns an ordered range of all the sync queues.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sync queues
	* @param end the upper bound of the range of sync queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of sync queues
	*/
	public java.util.List<SyncQueue> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncQueue> orderByComparator);

	/**
	* Returns an ordered range of all the sync queues.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sync queues
	* @param end the upper bound of the range of sync queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of sync queues
	*/
	public java.util.List<SyncQueue> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncQueue> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the sync queues from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of sync queues.
	*
	* @return the number of sync queues
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}