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

package org.opencps.dossiermgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.dossiermgt.exception.NoSuchDeliverableLogException;
import org.opencps.dossiermgt.model.DeliverableLog;

/**
 * The persistence interface for the deliverable log service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.dossiermgt.service.persistence.impl.DeliverableLogPersistenceImpl
 * @see DeliverableLogUtil
 * @generated
 */
@ProviderType
public interface DeliverableLogPersistence extends BasePersistence<DeliverableLog> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DeliverableLogUtil} to access the deliverable log persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the deliverable logs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching deliverable logs
	*/
	public java.util.List<DeliverableLog> findByUuid(String uuid);

	/**
	* Returns a range of all the deliverable logs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of deliverable logs
	* @param end the upper bound of the range of deliverable logs (not inclusive)
	* @return the range of matching deliverable logs
	*/
	public java.util.List<DeliverableLog> findByUuid(String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the deliverable logs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of deliverable logs
	* @param end the upper bound of the range of deliverable logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching deliverable logs
	*/
	public java.util.List<DeliverableLog> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableLog> orderByComparator);

	/**
	* Returns an ordered range of all the deliverable logs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of deliverable logs
	* @param end the upper bound of the range of deliverable logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching deliverable logs
	*/
	public java.util.List<DeliverableLog> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableLog> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first deliverable log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching deliverable log
	* @throws NoSuchDeliverableLogException if a matching deliverable log could not be found
	*/
	public DeliverableLog findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableLog> orderByComparator)
		throws NoSuchDeliverableLogException;

	/**
	* Returns the first deliverable log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching deliverable log, or <code>null</code> if a matching deliverable log could not be found
	*/
	public DeliverableLog fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableLog> orderByComparator);

	/**
	* Returns the last deliverable log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching deliverable log
	* @throws NoSuchDeliverableLogException if a matching deliverable log could not be found
	*/
	public DeliverableLog findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableLog> orderByComparator)
		throws NoSuchDeliverableLogException;

	/**
	* Returns the last deliverable log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching deliverable log, or <code>null</code> if a matching deliverable log could not be found
	*/
	public DeliverableLog fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableLog> orderByComparator);

	/**
	* Returns the deliverable logs before and after the current deliverable log in the ordered set where uuid = &#63;.
	*
	* @param deliverableLogId the primary key of the current deliverable log
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next deliverable log
	* @throws NoSuchDeliverableLogException if a deliverable log with the primary key could not be found
	*/
	public DeliverableLog[] findByUuid_PrevAndNext(long deliverableLogId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableLog> orderByComparator)
		throws NoSuchDeliverableLogException;

	/**
	* Removes all the deliverable logs where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of deliverable logs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching deliverable logs
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the deliverable log where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDeliverableLogException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching deliverable log
	* @throws NoSuchDeliverableLogException if a matching deliverable log could not be found
	*/
	public DeliverableLog findByUUID_G(String uuid, long groupId)
		throws NoSuchDeliverableLogException;

	/**
	* Returns the deliverable log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching deliverable log, or <code>null</code> if a matching deliverable log could not be found
	*/
	public DeliverableLog fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the deliverable log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching deliverable log, or <code>null</code> if a matching deliverable log could not be found
	*/
	public DeliverableLog fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the deliverable log where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the deliverable log that was removed
	*/
	public DeliverableLog removeByUUID_G(String uuid, long groupId)
		throws NoSuchDeliverableLogException;

	/**
	* Returns the number of deliverable logs where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching deliverable logs
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the deliverable logs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching deliverable logs
	*/
	public java.util.List<DeliverableLog> findByUuid_C(String uuid,
		long companyId);

	/**
	* Returns a range of all the deliverable logs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of deliverable logs
	* @param end the upper bound of the range of deliverable logs (not inclusive)
	* @return the range of matching deliverable logs
	*/
	public java.util.List<DeliverableLog> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the deliverable logs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of deliverable logs
	* @param end the upper bound of the range of deliverable logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching deliverable logs
	*/
	public java.util.List<DeliverableLog> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableLog> orderByComparator);

	/**
	* Returns an ordered range of all the deliverable logs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of deliverable logs
	* @param end the upper bound of the range of deliverable logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching deliverable logs
	*/
	public java.util.List<DeliverableLog> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableLog> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first deliverable log in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching deliverable log
	* @throws NoSuchDeliverableLogException if a matching deliverable log could not be found
	*/
	public DeliverableLog findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableLog> orderByComparator)
		throws NoSuchDeliverableLogException;

	/**
	* Returns the first deliverable log in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching deliverable log, or <code>null</code> if a matching deliverable log could not be found
	*/
	public DeliverableLog fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableLog> orderByComparator);

	/**
	* Returns the last deliverable log in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching deliverable log
	* @throws NoSuchDeliverableLogException if a matching deliverable log could not be found
	*/
	public DeliverableLog findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableLog> orderByComparator)
		throws NoSuchDeliverableLogException;

	/**
	* Returns the last deliverable log in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching deliverable log, or <code>null</code> if a matching deliverable log could not be found
	*/
	public DeliverableLog fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableLog> orderByComparator);

	/**
	* Returns the deliverable logs before and after the current deliverable log in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param deliverableLogId the primary key of the current deliverable log
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next deliverable log
	* @throws NoSuchDeliverableLogException if a deliverable log with the primary key could not be found
	*/
	public DeliverableLog[] findByUuid_C_PrevAndNext(long deliverableLogId,
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableLog> orderByComparator)
		throws NoSuchDeliverableLogException;

	/**
	* Removes all the deliverable logs where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of deliverable logs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching deliverable logs
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Caches the deliverable log in the entity cache if it is enabled.
	*
	* @param deliverableLog the deliverable log
	*/
	public void cacheResult(DeliverableLog deliverableLog);

	/**
	* Caches the deliverable logs in the entity cache if it is enabled.
	*
	* @param deliverableLogs the deliverable logs
	*/
	public void cacheResult(java.util.List<DeliverableLog> deliverableLogs);

	/**
	* Creates a new deliverable log with the primary key. Does not add the deliverable log to the database.
	*
	* @param deliverableLogId the primary key for the new deliverable log
	* @return the new deliverable log
	*/
	public DeliverableLog create(long deliverableLogId);

	/**
	* Removes the deliverable log with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param deliverableLogId the primary key of the deliverable log
	* @return the deliverable log that was removed
	* @throws NoSuchDeliverableLogException if a deliverable log with the primary key could not be found
	*/
	public DeliverableLog remove(long deliverableLogId)
		throws NoSuchDeliverableLogException;

	public DeliverableLog updateImpl(DeliverableLog deliverableLog);

	/**
	* Returns the deliverable log with the primary key or throws a {@link NoSuchDeliverableLogException} if it could not be found.
	*
	* @param deliverableLogId the primary key of the deliverable log
	* @return the deliverable log
	* @throws NoSuchDeliverableLogException if a deliverable log with the primary key could not be found
	*/
	public DeliverableLog findByPrimaryKey(long deliverableLogId)
		throws NoSuchDeliverableLogException;

	/**
	* Returns the deliverable log with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param deliverableLogId the primary key of the deliverable log
	* @return the deliverable log, or <code>null</code> if a deliverable log with the primary key could not be found
	*/
	public DeliverableLog fetchByPrimaryKey(long deliverableLogId);

	@Override
	public java.util.Map<java.io.Serializable, DeliverableLog> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the deliverable logs.
	*
	* @return the deliverable logs
	*/
	public java.util.List<DeliverableLog> findAll();

	/**
	* Returns a range of all the deliverable logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of deliverable logs
	* @param end the upper bound of the range of deliverable logs (not inclusive)
	* @return the range of deliverable logs
	*/
	public java.util.List<DeliverableLog> findAll(int start, int end);

	/**
	* Returns an ordered range of all the deliverable logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of deliverable logs
	* @param end the upper bound of the range of deliverable logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of deliverable logs
	*/
	public java.util.List<DeliverableLog> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableLog> orderByComparator);

	/**
	* Returns an ordered range of all the deliverable logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of deliverable logs
	* @param end the upper bound of the range of deliverable logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of deliverable logs
	*/
	public java.util.List<DeliverableLog> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableLog> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the deliverable logs from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of deliverable logs.
	*
	* @return the number of deliverable logs
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}