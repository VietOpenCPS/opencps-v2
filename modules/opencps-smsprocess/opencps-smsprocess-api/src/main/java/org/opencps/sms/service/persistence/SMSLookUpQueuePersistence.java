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

package org.opencps.sms.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.sms.exception.NoSuchLookUpQueueException;
import org.opencps.sms.model.SMSLookUpQueue;

/**
 * The persistence interface for the sms look up queue service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoa
 * @see org.opencps.sms.service.persistence.impl.SMSLookUpQueuePersistenceImpl
 * @see SMSLookUpQueueUtil
 * @generated
 */
@ProviderType
public interface SMSLookUpQueuePersistence extends BasePersistence<SMSLookUpQueue> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SMSLookUpQueueUtil} to access the sms look up queue persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the sms look up queues where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching sms look up queues
	*/
	public java.util.List<SMSLookUpQueue> findByUuid(String uuid);

	/**
	* Returns a range of all the sms look up queues where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SMSLookUpQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of sms look up queues
	* @param end the upper bound of the range of sms look up queues (not inclusive)
	* @return the range of matching sms look up queues
	*/
	public java.util.List<SMSLookUpQueue> findByUuid(String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the sms look up queues where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SMSLookUpQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of sms look up queues
	* @param end the upper bound of the range of sms look up queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sms look up queues
	*/
	public java.util.List<SMSLookUpQueue> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<SMSLookUpQueue> orderByComparator);

	/**
	* Returns an ordered range of all the sms look up queues where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SMSLookUpQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of sms look up queues
	* @param end the upper bound of the range of sms look up queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sms look up queues
	*/
	public java.util.List<SMSLookUpQueue> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<SMSLookUpQueue> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first sms look up queue in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sms look up queue
	* @throws NoSuchLookUpQueueException if a matching sms look up queue could not be found
	*/
	public SMSLookUpQueue findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SMSLookUpQueue> orderByComparator)
		throws NoSuchLookUpQueueException;

	/**
	* Returns the first sms look up queue in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sms look up queue, or <code>null</code> if a matching sms look up queue could not be found
	*/
	public SMSLookUpQueue fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SMSLookUpQueue> orderByComparator);

	/**
	* Returns the last sms look up queue in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sms look up queue
	* @throws NoSuchLookUpQueueException if a matching sms look up queue could not be found
	*/
	public SMSLookUpQueue findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SMSLookUpQueue> orderByComparator)
		throws NoSuchLookUpQueueException;

	/**
	* Returns the last sms look up queue in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sms look up queue, or <code>null</code> if a matching sms look up queue could not be found
	*/
	public SMSLookUpQueue fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SMSLookUpQueue> orderByComparator);

	/**
	* Returns the sms look up queues before and after the current sms look up queue in the ordered set where uuid = &#63;.
	*
	* @param queueId the primary key of the current sms look up queue
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sms look up queue
	* @throws NoSuchLookUpQueueException if a sms look up queue with the primary key could not be found
	*/
	public SMSLookUpQueue[] findByUuid_PrevAndNext(long queueId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SMSLookUpQueue> orderByComparator)
		throws NoSuchLookUpQueueException;

	/**
	* Removes all the sms look up queues where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of sms look up queues where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching sms look up queues
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the sms look up queue where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchLookUpQueueException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching sms look up queue
	* @throws NoSuchLookUpQueueException if a matching sms look up queue could not be found
	*/
	public SMSLookUpQueue findByUUID_G(String uuid, long groupId)
		throws NoSuchLookUpQueueException;

	/**
	* Returns the sms look up queue where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching sms look up queue, or <code>null</code> if a matching sms look up queue could not be found
	*/
	public SMSLookUpQueue fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the sms look up queue where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching sms look up queue, or <code>null</code> if a matching sms look up queue could not be found
	*/
	public SMSLookUpQueue fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the sms look up queue where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the sms look up queue that was removed
	*/
	public SMSLookUpQueue removeByUUID_G(String uuid, long groupId)
		throws NoSuchLookUpQueueException;

	/**
	* Returns the number of sms look up queues where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching sms look up queues
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the sms look up queues where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching sms look up queues
	*/
	public java.util.List<SMSLookUpQueue> findByUuid_C(String uuid,
		long companyId);

	/**
	* Returns a range of all the sms look up queues where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SMSLookUpQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of sms look up queues
	* @param end the upper bound of the range of sms look up queues (not inclusive)
	* @return the range of matching sms look up queues
	*/
	public java.util.List<SMSLookUpQueue> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the sms look up queues where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SMSLookUpQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of sms look up queues
	* @param end the upper bound of the range of sms look up queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sms look up queues
	*/
	public java.util.List<SMSLookUpQueue> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SMSLookUpQueue> orderByComparator);

	/**
	* Returns an ordered range of all the sms look up queues where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SMSLookUpQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of sms look up queues
	* @param end the upper bound of the range of sms look up queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sms look up queues
	*/
	public java.util.List<SMSLookUpQueue> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SMSLookUpQueue> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first sms look up queue in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sms look up queue
	* @throws NoSuchLookUpQueueException if a matching sms look up queue could not be found
	*/
	public SMSLookUpQueue findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SMSLookUpQueue> orderByComparator)
		throws NoSuchLookUpQueueException;

	/**
	* Returns the first sms look up queue in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sms look up queue, or <code>null</code> if a matching sms look up queue could not be found
	*/
	public SMSLookUpQueue fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SMSLookUpQueue> orderByComparator);

	/**
	* Returns the last sms look up queue in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sms look up queue
	* @throws NoSuchLookUpQueueException if a matching sms look up queue could not be found
	*/
	public SMSLookUpQueue findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SMSLookUpQueue> orderByComparator)
		throws NoSuchLookUpQueueException;

	/**
	* Returns the last sms look up queue in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sms look up queue, or <code>null</code> if a matching sms look up queue could not be found
	*/
	public SMSLookUpQueue fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SMSLookUpQueue> orderByComparator);

	/**
	* Returns the sms look up queues before and after the current sms look up queue in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param queueId the primary key of the current sms look up queue
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sms look up queue
	* @throws NoSuchLookUpQueueException if a sms look up queue with the primary key could not be found
	*/
	public SMSLookUpQueue[] findByUuid_C_PrevAndNext(long queueId, String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SMSLookUpQueue> orderByComparator)
		throws NoSuchLookUpQueueException;

	/**
	* Removes all the sms look up queues where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of sms look up queues where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching sms look up queues
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns the sms look up queue where moid = &#63; or throws a {@link NoSuchLookUpQueueException} if it could not be found.
	*
	* @param moid the moid
	* @return the matching sms look up queue
	* @throws NoSuchLookUpQueueException if a matching sms look up queue could not be found
	*/
	public SMSLookUpQueue findByM_O_I_D(String moid)
		throws NoSuchLookUpQueueException;

	/**
	* Returns the sms look up queue where moid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param moid the moid
	* @return the matching sms look up queue, or <code>null</code> if a matching sms look up queue could not be found
	*/
	public SMSLookUpQueue fetchByM_O_I_D(String moid);

	/**
	* Returns the sms look up queue where moid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param moid the moid
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching sms look up queue, or <code>null</code> if a matching sms look up queue could not be found
	*/
	public SMSLookUpQueue fetchByM_O_I_D(String moid, boolean retrieveFromCache);

	/**
	* Removes the sms look up queue where moid = &#63; from the database.
	*
	* @param moid the moid
	* @return the sms look up queue that was removed
	*/
	public SMSLookUpQueue removeByM_O_I_D(String moid)
		throws NoSuchLookUpQueueException;

	/**
	* Returns the number of sms look up queues where moid = &#63;.
	*
	* @param moid the moid
	* @return the number of matching sms look up queues
	*/
	public int countByM_O_I_D(String moid);

	/**
	* Caches the sms look up queue in the entity cache if it is enabled.
	*
	* @param smsLookUpQueue the sms look up queue
	*/
	public void cacheResult(SMSLookUpQueue smsLookUpQueue);

	/**
	* Caches the sms look up queues in the entity cache if it is enabled.
	*
	* @param smsLookUpQueues the sms look up queues
	*/
	public void cacheResult(java.util.List<SMSLookUpQueue> smsLookUpQueues);

	/**
	* Creates a new sms look up queue with the primary key. Does not add the sms look up queue to the database.
	*
	* @param queueId the primary key for the new sms look up queue
	* @return the new sms look up queue
	*/
	public SMSLookUpQueue create(long queueId);

	/**
	* Removes the sms look up queue with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param queueId the primary key of the sms look up queue
	* @return the sms look up queue that was removed
	* @throws NoSuchLookUpQueueException if a sms look up queue with the primary key could not be found
	*/
	public SMSLookUpQueue remove(long queueId)
		throws NoSuchLookUpQueueException;

	public SMSLookUpQueue updateImpl(SMSLookUpQueue smsLookUpQueue);

	/**
	* Returns the sms look up queue with the primary key or throws a {@link NoSuchLookUpQueueException} if it could not be found.
	*
	* @param queueId the primary key of the sms look up queue
	* @return the sms look up queue
	* @throws NoSuchLookUpQueueException if a sms look up queue with the primary key could not be found
	*/
	public SMSLookUpQueue findByPrimaryKey(long queueId)
		throws NoSuchLookUpQueueException;

	/**
	* Returns the sms look up queue with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param queueId the primary key of the sms look up queue
	* @return the sms look up queue, or <code>null</code> if a sms look up queue with the primary key could not be found
	*/
	public SMSLookUpQueue fetchByPrimaryKey(long queueId);

	@Override
	public java.util.Map<java.io.Serializable, SMSLookUpQueue> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the sms look up queues.
	*
	* @return the sms look up queues
	*/
	public java.util.List<SMSLookUpQueue> findAll();

	/**
	* Returns a range of all the sms look up queues.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SMSLookUpQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sms look up queues
	* @param end the upper bound of the range of sms look up queues (not inclusive)
	* @return the range of sms look up queues
	*/
	public java.util.List<SMSLookUpQueue> findAll(int start, int end);

	/**
	* Returns an ordered range of all the sms look up queues.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SMSLookUpQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sms look up queues
	* @param end the upper bound of the range of sms look up queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of sms look up queues
	*/
	public java.util.List<SMSLookUpQueue> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SMSLookUpQueue> orderByComparator);

	/**
	* Returns an ordered range of all the sms look up queues.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SMSLookUpQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sms look up queues
	* @param end the upper bound of the range of sms look up queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of sms look up queues
	*/
	public java.util.List<SMSLookUpQueue> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SMSLookUpQueue> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the sms look up queues from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of sms look up queues.
	*
	* @return the number of sms look up queues
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}