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

package org.opencps.deliverable.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.deliverable.exception.NoSuchOpenCPSDeliverableLogException;
import org.opencps.deliverable.model.OpenCPSDeliverableLog;

/**
 * The persistence interface for the open cps deliverable log service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author binhth
 * @see org.opencps.deliverable.service.persistence.impl.OpenCPSDeliverableLogPersistenceImpl
 * @see OpenCPSDeliverableLogUtil
 * @generated
 */
@ProviderType
public interface OpenCPSDeliverableLogPersistence extends BasePersistence<OpenCPSDeliverableLog> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link OpenCPSDeliverableLogUtil} to access the open cps deliverable log persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the open cps deliverable logs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching open cps deliverable logs
	*/
	public java.util.List<OpenCPSDeliverableLog> findByUuid(String uuid);

	/**
	* Returns a range of all the open cps deliverable logs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of open cps deliverable logs
	* @param end the upper bound of the range of open cps deliverable logs (not inclusive)
	* @return the range of matching open cps deliverable logs
	*/
	public java.util.List<OpenCPSDeliverableLog> findByUuid(String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the open cps deliverable logs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of open cps deliverable logs
	* @param end the upper bound of the range of open cps deliverable logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching open cps deliverable logs
	*/
	public java.util.List<OpenCPSDeliverableLog> findByUuid(String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableLog> orderByComparator);

	/**
	* Returns an ordered range of all the open cps deliverable logs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of open cps deliverable logs
	* @param end the upper bound of the range of open cps deliverable logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching open cps deliverable logs
	*/
	public java.util.List<OpenCPSDeliverableLog> findByUuid(String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableLog> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first open cps deliverable log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching open cps deliverable log
	* @throws NoSuchOpenCPSDeliverableLogException if a matching open cps deliverable log could not be found
	*/
	public OpenCPSDeliverableLog findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableLog> orderByComparator)
		throws NoSuchOpenCPSDeliverableLogException;

	/**
	* Returns the first open cps deliverable log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching open cps deliverable log, or <code>null</code> if a matching open cps deliverable log could not be found
	*/
	public OpenCPSDeliverableLog fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableLog> orderByComparator);

	/**
	* Returns the last open cps deliverable log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching open cps deliverable log
	* @throws NoSuchOpenCPSDeliverableLogException if a matching open cps deliverable log could not be found
	*/
	public OpenCPSDeliverableLog findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableLog> orderByComparator)
		throws NoSuchOpenCPSDeliverableLogException;

	/**
	* Returns the last open cps deliverable log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching open cps deliverable log, or <code>null</code> if a matching open cps deliverable log could not be found
	*/
	public OpenCPSDeliverableLog fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableLog> orderByComparator);

	/**
	* Returns the open cps deliverable logs before and after the current open cps deliverable log in the ordered set where uuid = &#63;.
	*
	* @param deliverableLogId the primary key of the current open cps deliverable log
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next open cps deliverable log
	* @throws NoSuchOpenCPSDeliverableLogException if a open cps deliverable log with the primary key could not be found
	*/
	public OpenCPSDeliverableLog[] findByUuid_PrevAndNext(
		long deliverableLogId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableLog> orderByComparator)
		throws NoSuchOpenCPSDeliverableLogException;

	/**
	* Removes all the open cps deliverable logs where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of open cps deliverable logs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching open cps deliverable logs
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the open cps deliverable log where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchOpenCPSDeliverableLogException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching open cps deliverable log
	* @throws NoSuchOpenCPSDeliverableLogException if a matching open cps deliverable log could not be found
	*/
	public OpenCPSDeliverableLog findByUUID_G(String uuid, long groupId)
		throws NoSuchOpenCPSDeliverableLogException;

	/**
	* Returns the open cps deliverable log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching open cps deliverable log, or <code>null</code> if a matching open cps deliverable log could not be found
	*/
	public OpenCPSDeliverableLog fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the open cps deliverable log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching open cps deliverable log, or <code>null</code> if a matching open cps deliverable log could not be found
	*/
	public OpenCPSDeliverableLog fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the open cps deliverable log where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the open cps deliverable log that was removed
	*/
	public OpenCPSDeliverableLog removeByUUID_G(String uuid, long groupId)
		throws NoSuchOpenCPSDeliverableLogException;

	/**
	* Returns the number of open cps deliverable logs where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching open cps deliverable logs
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the open cps deliverable logs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching open cps deliverable logs
	*/
	public java.util.List<OpenCPSDeliverableLog> findByUuid_C(String uuid,
		long companyId);

	/**
	* Returns a range of all the open cps deliverable logs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of open cps deliverable logs
	* @param end the upper bound of the range of open cps deliverable logs (not inclusive)
	* @return the range of matching open cps deliverable logs
	*/
	public java.util.List<OpenCPSDeliverableLog> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the open cps deliverable logs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of open cps deliverable logs
	* @param end the upper bound of the range of open cps deliverable logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching open cps deliverable logs
	*/
	public java.util.List<OpenCPSDeliverableLog> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableLog> orderByComparator);

	/**
	* Returns an ordered range of all the open cps deliverable logs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of open cps deliverable logs
	* @param end the upper bound of the range of open cps deliverable logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching open cps deliverable logs
	*/
	public java.util.List<OpenCPSDeliverableLog> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableLog> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first open cps deliverable log in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching open cps deliverable log
	* @throws NoSuchOpenCPSDeliverableLogException if a matching open cps deliverable log could not be found
	*/
	public OpenCPSDeliverableLog findByUuid_C_First(String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableLog> orderByComparator)
		throws NoSuchOpenCPSDeliverableLogException;

	/**
	* Returns the first open cps deliverable log in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching open cps deliverable log, or <code>null</code> if a matching open cps deliverable log could not be found
	*/
	public OpenCPSDeliverableLog fetchByUuid_C_First(String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableLog> orderByComparator);

	/**
	* Returns the last open cps deliverable log in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching open cps deliverable log
	* @throws NoSuchOpenCPSDeliverableLogException if a matching open cps deliverable log could not be found
	*/
	public OpenCPSDeliverableLog findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableLog> orderByComparator)
		throws NoSuchOpenCPSDeliverableLogException;

	/**
	* Returns the last open cps deliverable log in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching open cps deliverable log, or <code>null</code> if a matching open cps deliverable log could not be found
	*/
	public OpenCPSDeliverableLog fetchByUuid_C_Last(String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableLog> orderByComparator);

	/**
	* Returns the open cps deliverable logs before and after the current open cps deliverable log in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param deliverableLogId the primary key of the current open cps deliverable log
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next open cps deliverable log
	* @throws NoSuchOpenCPSDeliverableLogException if a open cps deliverable log with the primary key could not be found
	*/
	public OpenCPSDeliverableLog[] findByUuid_C_PrevAndNext(
		long deliverableLogId, String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableLog> orderByComparator)
		throws NoSuchOpenCPSDeliverableLogException;

	/**
	* Removes all the open cps deliverable logs where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of open cps deliverable logs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching open cps deliverable logs
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the open cps deliverable logs where deliverableId = &#63;.
	*
	* @param deliverableId the deliverable ID
	* @return the matching open cps deliverable logs
	*/
	public java.util.List<OpenCPSDeliverableLog> findByF_deliverableId(
		long deliverableId);

	/**
	* Returns a range of all the open cps deliverable logs where deliverableId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param deliverableId the deliverable ID
	* @param start the lower bound of the range of open cps deliverable logs
	* @param end the upper bound of the range of open cps deliverable logs (not inclusive)
	* @return the range of matching open cps deliverable logs
	*/
	public java.util.List<OpenCPSDeliverableLog> findByF_deliverableId(
		long deliverableId, int start, int end);

	/**
	* Returns an ordered range of all the open cps deliverable logs where deliverableId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param deliverableId the deliverable ID
	* @param start the lower bound of the range of open cps deliverable logs
	* @param end the upper bound of the range of open cps deliverable logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching open cps deliverable logs
	*/
	public java.util.List<OpenCPSDeliverableLog> findByF_deliverableId(
		long deliverableId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableLog> orderByComparator);

	/**
	* Returns an ordered range of all the open cps deliverable logs where deliverableId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param deliverableId the deliverable ID
	* @param start the lower bound of the range of open cps deliverable logs
	* @param end the upper bound of the range of open cps deliverable logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching open cps deliverable logs
	*/
	public java.util.List<OpenCPSDeliverableLog> findByF_deliverableId(
		long deliverableId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableLog> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first open cps deliverable log in the ordered set where deliverableId = &#63;.
	*
	* @param deliverableId the deliverable ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching open cps deliverable log
	* @throws NoSuchOpenCPSDeliverableLogException if a matching open cps deliverable log could not be found
	*/
	public OpenCPSDeliverableLog findByF_deliverableId_First(
		long deliverableId,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableLog> orderByComparator)
		throws NoSuchOpenCPSDeliverableLogException;

	/**
	* Returns the first open cps deliverable log in the ordered set where deliverableId = &#63;.
	*
	* @param deliverableId the deliverable ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching open cps deliverable log, or <code>null</code> if a matching open cps deliverable log could not be found
	*/
	public OpenCPSDeliverableLog fetchByF_deliverableId_First(
		long deliverableId,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableLog> orderByComparator);

	/**
	* Returns the last open cps deliverable log in the ordered set where deliverableId = &#63;.
	*
	* @param deliverableId the deliverable ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching open cps deliverable log
	* @throws NoSuchOpenCPSDeliverableLogException if a matching open cps deliverable log could not be found
	*/
	public OpenCPSDeliverableLog findByF_deliverableId_Last(
		long deliverableId,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableLog> orderByComparator)
		throws NoSuchOpenCPSDeliverableLogException;

	/**
	* Returns the last open cps deliverable log in the ordered set where deliverableId = &#63;.
	*
	* @param deliverableId the deliverable ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching open cps deliverable log, or <code>null</code> if a matching open cps deliverable log could not be found
	*/
	public OpenCPSDeliverableLog fetchByF_deliverableId_Last(
		long deliverableId,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableLog> orderByComparator);

	/**
	* Returns the open cps deliverable logs before and after the current open cps deliverable log in the ordered set where deliverableId = &#63;.
	*
	* @param deliverableLogId the primary key of the current open cps deliverable log
	* @param deliverableId the deliverable ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next open cps deliverable log
	* @throws NoSuchOpenCPSDeliverableLogException if a open cps deliverable log with the primary key could not be found
	*/
	public OpenCPSDeliverableLog[] findByF_deliverableId_PrevAndNext(
		long deliverableLogId, long deliverableId,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableLog> orderByComparator)
		throws NoSuchOpenCPSDeliverableLogException;

	/**
	* Removes all the open cps deliverable logs where deliverableId = &#63; from the database.
	*
	* @param deliverableId the deliverable ID
	*/
	public void removeByF_deliverableId(long deliverableId);

	/**
	* Returns the number of open cps deliverable logs where deliverableId = &#63;.
	*
	* @param deliverableId the deliverable ID
	* @return the number of matching open cps deliverable logs
	*/
	public int countByF_deliverableId(long deliverableId);

	/**
	* Caches the open cps deliverable log in the entity cache if it is enabled.
	*
	* @param openCPSDeliverableLog the open cps deliverable log
	*/
	public void cacheResult(OpenCPSDeliverableLog openCPSDeliverableLog);

	/**
	* Caches the open cps deliverable logs in the entity cache if it is enabled.
	*
	* @param openCPSDeliverableLogs the open cps deliverable logs
	*/
	public void cacheResult(
		java.util.List<OpenCPSDeliverableLog> openCPSDeliverableLogs);

	/**
	* Creates a new open cps deliverable log with the primary key. Does not add the open cps deliverable log to the database.
	*
	* @param deliverableLogId the primary key for the new open cps deliverable log
	* @return the new open cps deliverable log
	*/
	public OpenCPSDeliverableLog create(long deliverableLogId);

	/**
	* Removes the open cps deliverable log with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param deliverableLogId the primary key of the open cps deliverable log
	* @return the open cps deliverable log that was removed
	* @throws NoSuchOpenCPSDeliverableLogException if a open cps deliverable log with the primary key could not be found
	*/
	public OpenCPSDeliverableLog remove(long deliverableLogId)
		throws NoSuchOpenCPSDeliverableLogException;

	public OpenCPSDeliverableLog updateImpl(
		OpenCPSDeliverableLog openCPSDeliverableLog);

	/**
	* Returns the open cps deliverable log with the primary key or throws a {@link NoSuchOpenCPSDeliverableLogException} if it could not be found.
	*
	* @param deliverableLogId the primary key of the open cps deliverable log
	* @return the open cps deliverable log
	* @throws NoSuchOpenCPSDeliverableLogException if a open cps deliverable log with the primary key could not be found
	*/
	public OpenCPSDeliverableLog findByPrimaryKey(long deliverableLogId)
		throws NoSuchOpenCPSDeliverableLogException;

	/**
	* Returns the open cps deliverable log with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param deliverableLogId the primary key of the open cps deliverable log
	* @return the open cps deliverable log, or <code>null</code> if a open cps deliverable log with the primary key could not be found
	*/
	public OpenCPSDeliverableLog fetchByPrimaryKey(long deliverableLogId);

	@Override
	public java.util.Map<java.io.Serializable, OpenCPSDeliverableLog> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the open cps deliverable logs.
	*
	* @return the open cps deliverable logs
	*/
	public java.util.List<OpenCPSDeliverableLog> findAll();

	/**
	* Returns a range of all the open cps deliverable logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of open cps deliverable logs
	* @param end the upper bound of the range of open cps deliverable logs (not inclusive)
	* @return the range of open cps deliverable logs
	*/
	public java.util.List<OpenCPSDeliverableLog> findAll(int start, int end);

	/**
	* Returns an ordered range of all the open cps deliverable logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of open cps deliverable logs
	* @param end the upper bound of the range of open cps deliverable logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of open cps deliverable logs
	*/
	public java.util.List<OpenCPSDeliverableLog> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableLog> orderByComparator);

	/**
	* Returns an ordered range of all the open cps deliverable logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of open cps deliverable logs
	* @param end the upper bound of the range of open cps deliverable logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of open cps deliverable logs
	*/
	public java.util.List<OpenCPSDeliverableLog> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableLog> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the open cps deliverable logs from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of open cps deliverable logs.
	*
	* @return the number of open cps deliverable logs
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}