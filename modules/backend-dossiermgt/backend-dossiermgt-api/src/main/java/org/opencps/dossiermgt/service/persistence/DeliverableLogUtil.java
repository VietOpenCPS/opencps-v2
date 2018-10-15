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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.dossiermgt.model.DeliverableLog;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the deliverable log service. This utility wraps {@link org.opencps.dossiermgt.service.persistence.impl.DeliverableLogPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see DeliverableLogPersistence
 * @see org.opencps.dossiermgt.service.persistence.impl.DeliverableLogPersistenceImpl
 * @generated
 */
@ProviderType
public class DeliverableLogUtil {
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
	public static void clearCache(DeliverableLog deliverableLog) {
		getPersistence().clearCache(deliverableLog);
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
	public static List<DeliverableLog> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<DeliverableLog> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<DeliverableLog> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<DeliverableLog> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static DeliverableLog update(DeliverableLog deliverableLog) {
		return getPersistence().update(deliverableLog);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static DeliverableLog update(DeliverableLog deliverableLog,
		ServiceContext serviceContext) {
		return getPersistence().update(deliverableLog, serviceContext);
	}

	/**
	* Returns all the deliverable logs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching deliverable logs
	*/
	public static List<DeliverableLog> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

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
	public static List<DeliverableLog> findByUuid(String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static List<DeliverableLog> findByUuid(String uuid, int start,
		int end, OrderByComparator<DeliverableLog> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

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
	public static List<DeliverableLog> findByUuid(String uuid, int start,
		int end, OrderByComparator<DeliverableLog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first deliverable log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching deliverable log
	* @throws NoSuchDeliverableLogException if a matching deliverable log could not be found
	*/
	public static DeliverableLog findByUuid_First(String uuid,
		OrderByComparator<DeliverableLog> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableLogException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first deliverable log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching deliverable log, or <code>null</code> if a matching deliverable log could not be found
	*/
	public static DeliverableLog fetchByUuid_First(String uuid,
		OrderByComparator<DeliverableLog> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last deliverable log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching deliverable log
	* @throws NoSuchDeliverableLogException if a matching deliverable log could not be found
	*/
	public static DeliverableLog findByUuid_Last(String uuid,
		OrderByComparator<DeliverableLog> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableLogException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last deliverable log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching deliverable log, or <code>null</code> if a matching deliverable log could not be found
	*/
	public static DeliverableLog fetchByUuid_Last(String uuid,
		OrderByComparator<DeliverableLog> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the deliverable logs before and after the current deliverable log in the ordered set where uuid = &#63;.
	*
	* @param deliverableLogId the primary key of the current deliverable log
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next deliverable log
	* @throws NoSuchDeliverableLogException if a deliverable log with the primary key could not be found
	*/
	public static DeliverableLog[] findByUuid_PrevAndNext(
		long deliverableLogId, String uuid,
		OrderByComparator<DeliverableLog> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableLogException {
		return getPersistence()
				   .findByUuid_PrevAndNext(deliverableLogId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the deliverable logs where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of deliverable logs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching deliverable logs
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the deliverable log where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDeliverableLogException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching deliverable log
	* @throws NoSuchDeliverableLogException if a matching deliverable log could not be found
	*/
	public static DeliverableLog findByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableLogException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the deliverable log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching deliverable log, or <code>null</code> if a matching deliverable log could not be found
	*/
	public static DeliverableLog fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the deliverable log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching deliverable log, or <code>null</code> if a matching deliverable log could not be found
	*/
	public static DeliverableLog fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the deliverable log where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the deliverable log that was removed
	*/
	public static DeliverableLog removeByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableLogException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of deliverable logs where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching deliverable logs
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the deliverable logs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching deliverable logs
	*/
	public static List<DeliverableLog> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

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
	public static List<DeliverableLog> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

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
	public static List<DeliverableLog> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<DeliverableLog> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

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
	public static List<DeliverableLog> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<DeliverableLog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first deliverable log in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching deliverable log
	* @throws NoSuchDeliverableLogException if a matching deliverable log could not be found
	*/
	public static DeliverableLog findByUuid_C_First(String uuid,
		long companyId, OrderByComparator<DeliverableLog> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableLogException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first deliverable log in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching deliverable log, or <code>null</code> if a matching deliverable log could not be found
	*/
	public static DeliverableLog fetchByUuid_C_First(String uuid,
		long companyId, OrderByComparator<DeliverableLog> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last deliverable log in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching deliverable log
	* @throws NoSuchDeliverableLogException if a matching deliverable log could not be found
	*/
	public static DeliverableLog findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DeliverableLog> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableLogException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last deliverable log in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching deliverable log, or <code>null</code> if a matching deliverable log could not be found
	*/
	public static DeliverableLog fetchByUuid_C_Last(String uuid,
		long companyId, OrderByComparator<DeliverableLog> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

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
	public static DeliverableLog[] findByUuid_C_PrevAndNext(
		long deliverableLogId, String uuid, long companyId,
		OrderByComparator<DeliverableLog> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableLogException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(deliverableLogId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the deliverable logs where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of deliverable logs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching deliverable logs
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Caches the deliverable log in the entity cache if it is enabled.
	*
	* @param deliverableLog the deliverable log
	*/
	public static void cacheResult(DeliverableLog deliverableLog) {
		getPersistence().cacheResult(deliverableLog);
	}

	/**
	* Caches the deliverable logs in the entity cache if it is enabled.
	*
	* @param deliverableLogs the deliverable logs
	*/
	public static void cacheResult(List<DeliverableLog> deliverableLogs) {
		getPersistence().cacheResult(deliverableLogs);
	}

	/**
	* Creates a new deliverable log with the primary key. Does not add the deliverable log to the database.
	*
	* @param deliverableLogId the primary key for the new deliverable log
	* @return the new deliverable log
	*/
	public static DeliverableLog create(long deliverableLogId) {
		return getPersistence().create(deliverableLogId);
	}

	/**
	* Removes the deliverable log with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param deliverableLogId the primary key of the deliverable log
	* @return the deliverable log that was removed
	* @throws NoSuchDeliverableLogException if a deliverable log with the primary key could not be found
	*/
	public static DeliverableLog remove(long deliverableLogId)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableLogException {
		return getPersistence().remove(deliverableLogId);
	}

	public static DeliverableLog updateImpl(DeliverableLog deliverableLog) {
		return getPersistence().updateImpl(deliverableLog);
	}

	/**
	* Returns the deliverable log with the primary key or throws a {@link NoSuchDeliverableLogException} if it could not be found.
	*
	* @param deliverableLogId the primary key of the deliverable log
	* @return the deliverable log
	* @throws NoSuchDeliverableLogException if a deliverable log with the primary key could not be found
	*/
	public static DeliverableLog findByPrimaryKey(long deliverableLogId)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableLogException {
		return getPersistence().findByPrimaryKey(deliverableLogId);
	}

	/**
	* Returns the deliverable log with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param deliverableLogId the primary key of the deliverable log
	* @return the deliverable log, or <code>null</code> if a deliverable log with the primary key could not be found
	*/
	public static DeliverableLog fetchByPrimaryKey(long deliverableLogId) {
		return getPersistence().fetchByPrimaryKey(deliverableLogId);
	}

	public static java.util.Map<java.io.Serializable, DeliverableLog> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the deliverable logs.
	*
	* @return the deliverable logs
	*/
	public static List<DeliverableLog> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<DeliverableLog> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<DeliverableLog> findAll(int start, int end,
		OrderByComparator<DeliverableLog> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<DeliverableLog> findAll(int start, int end,
		OrderByComparator<DeliverableLog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the deliverable logs from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of deliverable logs.
	*
	* @return the number of deliverable logs
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static DeliverableLogPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DeliverableLogPersistence, DeliverableLogPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(DeliverableLogPersistence.class);

		ServiceTracker<DeliverableLogPersistence, DeliverableLogPersistence> serviceTracker =
			new ServiceTracker<DeliverableLogPersistence, DeliverableLogPersistence>(bundle.getBundleContext(),
				DeliverableLogPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}