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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.sms.model.SMSLookUpQueue;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the sms look up queue service. This utility wraps {@link org.opencps.sms.service.persistence.impl.SMSLookUpQueuePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoa
 * @see SMSLookUpQueuePersistence
 * @see org.opencps.sms.service.persistence.impl.SMSLookUpQueuePersistenceImpl
 * @generated
 */
@ProviderType
public class SMSLookUpQueueUtil {
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
	public static void clearCache(SMSLookUpQueue smsLookUpQueue) {
		getPersistence().clearCache(smsLookUpQueue);
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
	public static List<SMSLookUpQueue> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SMSLookUpQueue> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SMSLookUpQueue> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SMSLookUpQueue> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static SMSLookUpQueue update(SMSLookUpQueue smsLookUpQueue) {
		return getPersistence().update(smsLookUpQueue);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static SMSLookUpQueue update(SMSLookUpQueue smsLookUpQueue,
		ServiceContext serviceContext) {
		return getPersistence().update(smsLookUpQueue, serviceContext);
	}

	/**
	* Returns all the sms look up queues where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching sms look up queues
	*/
	public static List<SMSLookUpQueue> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

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
	public static List<SMSLookUpQueue> findByUuid(String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static List<SMSLookUpQueue> findByUuid(String uuid, int start,
		int end, OrderByComparator<SMSLookUpQueue> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

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
	public static List<SMSLookUpQueue> findByUuid(String uuid, int start,
		int end, OrderByComparator<SMSLookUpQueue> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first sms look up queue in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sms look up queue
	* @throws NoSuchLookUpQueueException if a matching sms look up queue could not be found
	*/
	public static SMSLookUpQueue findByUuid_First(String uuid,
		OrderByComparator<SMSLookUpQueue> orderByComparator)
		throws org.opencps.sms.exception.NoSuchLookUpQueueException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first sms look up queue in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sms look up queue, or <code>null</code> if a matching sms look up queue could not be found
	*/
	public static SMSLookUpQueue fetchByUuid_First(String uuid,
		OrderByComparator<SMSLookUpQueue> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last sms look up queue in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sms look up queue
	* @throws NoSuchLookUpQueueException if a matching sms look up queue could not be found
	*/
	public static SMSLookUpQueue findByUuid_Last(String uuid,
		OrderByComparator<SMSLookUpQueue> orderByComparator)
		throws org.opencps.sms.exception.NoSuchLookUpQueueException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last sms look up queue in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sms look up queue, or <code>null</code> if a matching sms look up queue could not be found
	*/
	public static SMSLookUpQueue fetchByUuid_Last(String uuid,
		OrderByComparator<SMSLookUpQueue> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the sms look up queues before and after the current sms look up queue in the ordered set where uuid = &#63;.
	*
	* @param queueId the primary key of the current sms look up queue
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sms look up queue
	* @throws NoSuchLookUpQueueException if a sms look up queue with the primary key could not be found
	*/
	public static SMSLookUpQueue[] findByUuid_PrevAndNext(long queueId,
		String uuid, OrderByComparator<SMSLookUpQueue> orderByComparator)
		throws org.opencps.sms.exception.NoSuchLookUpQueueException {
		return getPersistence()
				   .findByUuid_PrevAndNext(queueId, uuid, orderByComparator);
	}

	/**
	* Removes all the sms look up queues where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of sms look up queues where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching sms look up queues
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the sms look up queue where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchLookUpQueueException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching sms look up queue
	* @throws NoSuchLookUpQueueException if a matching sms look up queue could not be found
	*/
	public static SMSLookUpQueue findByUUID_G(String uuid, long groupId)
		throws org.opencps.sms.exception.NoSuchLookUpQueueException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the sms look up queue where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching sms look up queue, or <code>null</code> if a matching sms look up queue could not be found
	*/
	public static SMSLookUpQueue fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the sms look up queue where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching sms look up queue, or <code>null</code> if a matching sms look up queue could not be found
	*/
	public static SMSLookUpQueue fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the sms look up queue where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the sms look up queue that was removed
	*/
	public static SMSLookUpQueue removeByUUID_G(String uuid, long groupId)
		throws org.opencps.sms.exception.NoSuchLookUpQueueException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of sms look up queues where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching sms look up queues
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the sms look up queues where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching sms look up queues
	*/
	public static List<SMSLookUpQueue> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

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
	public static List<SMSLookUpQueue> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

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
	public static List<SMSLookUpQueue> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<SMSLookUpQueue> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

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
	public static List<SMSLookUpQueue> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<SMSLookUpQueue> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first sms look up queue in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sms look up queue
	* @throws NoSuchLookUpQueueException if a matching sms look up queue could not be found
	*/
	public static SMSLookUpQueue findByUuid_C_First(String uuid,
		long companyId, OrderByComparator<SMSLookUpQueue> orderByComparator)
		throws org.opencps.sms.exception.NoSuchLookUpQueueException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first sms look up queue in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sms look up queue, or <code>null</code> if a matching sms look up queue could not be found
	*/
	public static SMSLookUpQueue fetchByUuid_C_First(String uuid,
		long companyId, OrderByComparator<SMSLookUpQueue> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last sms look up queue in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sms look up queue
	* @throws NoSuchLookUpQueueException if a matching sms look up queue could not be found
	*/
	public static SMSLookUpQueue findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<SMSLookUpQueue> orderByComparator)
		throws org.opencps.sms.exception.NoSuchLookUpQueueException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last sms look up queue in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sms look up queue, or <code>null</code> if a matching sms look up queue could not be found
	*/
	public static SMSLookUpQueue fetchByUuid_C_Last(String uuid,
		long companyId, OrderByComparator<SMSLookUpQueue> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

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
	public static SMSLookUpQueue[] findByUuid_C_PrevAndNext(long queueId,
		String uuid, long companyId,
		OrderByComparator<SMSLookUpQueue> orderByComparator)
		throws org.opencps.sms.exception.NoSuchLookUpQueueException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(queueId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the sms look up queues where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of sms look up queues where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching sms look up queues
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns the sms look up queue where moid = &#63; or throws a {@link NoSuchLookUpQueueException} if it could not be found.
	*
	* @param moid the moid
	* @return the matching sms look up queue
	* @throws NoSuchLookUpQueueException if a matching sms look up queue could not be found
	*/
	public static SMSLookUpQueue findByM_O_I_D(String moid)
		throws org.opencps.sms.exception.NoSuchLookUpQueueException {
		return getPersistence().findByM_O_I_D(moid);
	}

	/**
	* Returns the sms look up queue where moid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param moid the moid
	* @return the matching sms look up queue, or <code>null</code> if a matching sms look up queue could not be found
	*/
	public static SMSLookUpQueue fetchByM_O_I_D(String moid) {
		return getPersistence().fetchByM_O_I_D(moid);
	}

	/**
	* Returns the sms look up queue where moid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param moid the moid
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching sms look up queue, or <code>null</code> if a matching sms look up queue could not be found
	*/
	public static SMSLookUpQueue fetchByM_O_I_D(String moid,
		boolean retrieveFromCache) {
		return getPersistence().fetchByM_O_I_D(moid, retrieveFromCache);
	}

	/**
	* Removes the sms look up queue where moid = &#63; from the database.
	*
	* @param moid the moid
	* @return the sms look up queue that was removed
	*/
	public static SMSLookUpQueue removeByM_O_I_D(String moid)
		throws org.opencps.sms.exception.NoSuchLookUpQueueException {
		return getPersistence().removeByM_O_I_D(moid);
	}

	/**
	* Returns the number of sms look up queues where moid = &#63;.
	*
	* @param moid the moid
	* @return the number of matching sms look up queues
	*/
	public static int countByM_O_I_D(String moid) {
		return getPersistence().countByM_O_I_D(moid);
	}

	/**
	* Caches the sms look up queue in the entity cache if it is enabled.
	*
	* @param smsLookUpQueue the sms look up queue
	*/
	public static void cacheResult(SMSLookUpQueue smsLookUpQueue) {
		getPersistence().cacheResult(smsLookUpQueue);
	}

	/**
	* Caches the sms look up queues in the entity cache if it is enabled.
	*
	* @param smsLookUpQueues the sms look up queues
	*/
	public static void cacheResult(List<SMSLookUpQueue> smsLookUpQueues) {
		getPersistence().cacheResult(smsLookUpQueues);
	}

	/**
	* Creates a new sms look up queue with the primary key. Does not add the sms look up queue to the database.
	*
	* @param queueId the primary key for the new sms look up queue
	* @return the new sms look up queue
	*/
	public static SMSLookUpQueue create(long queueId) {
		return getPersistence().create(queueId);
	}

	/**
	* Removes the sms look up queue with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param queueId the primary key of the sms look up queue
	* @return the sms look up queue that was removed
	* @throws NoSuchLookUpQueueException if a sms look up queue with the primary key could not be found
	*/
	public static SMSLookUpQueue remove(long queueId)
		throws org.opencps.sms.exception.NoSuchLookUpQueueException {
		return getPersistence().remove(queueId);
	}

	public static SMSLookUpQueue updateImpl(SMSLookUpQueue smsLookUpQueue) {
		return getPersistence().updateImpl(smsLookUpQueue);
	}

	/**
	* Returns the sms look up queue with the primary key or throws a {@link NoSuchLookUpQueueException} if it could not be found.
	*
	* @param queueId the primary key of the sms look up queue
	* @return the sms look up queue
	* @throws NoSuchLookUpQueueException if a sms look up queue with the primary key could not be found
	*/
	public static SMSLookUpQueue findByPrimaryKey(long queueId)
		throws org.opencps.sms.exception.NoSuchLookUpQueueException {
		return getPersistence().findByPrimaryKey(queueId);
	}

	/**
	* Returns the sms look up queue with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param queueId the primary key of the sms look up queue
	* @return the sms look up queue, or <code>null</code> if a sms look up queue with the primary key could not be found
	*/
	public static SMSLookUpQueue fetchByPrimaryKey(long queueId) {
		return getPersistence().fetchByPrimaryKey(queueId);
	}

	public static java.util.Map<java.io.Serializable, SMSLookUpQueue> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the sms look up queues.
	*
	* @return the sms look up queues
	*/
	public static List<SMSLookUpQueue> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<SMSLookUpQueue> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<SMSLookUpQueue> findAll(int start, int end,
		OrderByComparator<SMSLookUpQueue> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<SMSLookUpQueue> findAll(int start, int end,
		OrderByComparator<SMSLookUpQueue> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the sms look up queues from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of sms look up queues.
	*
	* @return the number of sms look up queues
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static SMSLookUpQueuePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<SMSLookUpQueuePersistence, SMSLookUpQueuePersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(SMSLookUpQueuePersistence.class);

		ServiceTracker<SMSLookUpQueuePersistence, SMSLookUpQueuePersistence> serviceTracker =
			new ServiceTracker<SMSLookUpQueuePersistence, SMSLookUpQueuePersistence>(bundle.getBundleContext(),
				SMSLookUpQueuePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}