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

import org.opencps.sms.model.SMSGatewayLog;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the sms gateway log service. This utility wraps {@link org.opencps.sms.service.persistence.impl.SMSGatewayLogPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoa
 * @see SMSGatewayLogPersistence
 * @see org.opencps.sms.service.persistence.impl.SMSGatewayLogPersistenceImpl
 * @generated
 */
@ProviderType
public class SMSGatewayLogUtil {
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
	public static void clearCache(SMSGatewayLog smsGatewayLog) {
		getPersistence().clearCache(smsGatewayLog);
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
	public static List<SMSGatewayLog> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SMSGatewayLog> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SMSGatewayLog> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SMSGatewayLog> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static SMSGatewayLog update(SMSGatewayLog smsGatewayLog) {
		return getPersistence().update(smsGatewayLog);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static SMSGatewayLog update(SMSGatewayLog smsGatewayLog,
		ServiceContext serviceContext) {
		return getPersistence().update(smsGatewayLog, serviceContext);
	}

	/**
	* Returns all the sms gateway logs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching sms gateway logs
	*/
	public static List<SMSGatewayLog> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the sms gateway logs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SMSGatewayLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of sms gateway logs
	* @param end the upper bound of the range of sms gateway logs (not inclusive)
	* @return the range of matching sms gateway logs
	*/
	public static List<SMSGatewayLog> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the sms gateway logs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SMSGatewayLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of sms gateway logs
	* @param end the upper bound of the range of sms gateway logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sms gateway logs
	*/
	public static List<SMSGatewayLog> findByUuid(String uuid, int start,
		int end, OrderByComparator<SMSGatewayLog> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the sms gateway logs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SMSGatewayLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of sms gateway logs
	* @param end the upper bound of the range of sms gateway logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sms gateway logs
	*/
	public static List<SMSGatewayLog> findByUuid(String uuid, int start,
		int end, OrderByComparator<SMSGatewayLog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first sms gateway log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sms gateway log
	* @throws NoSuchGatewayLogException if a matching sms gateway log could not be found
	*/
	public static SMSGatewayLog findByUuid_First(String uuid,
		OrderByComparator<SMSGatewayLog> orderByComparator)
		throws org.opencps.sms.exception.NoSuchGatewayLogException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first sms gateway log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sms gateway log, or <code>null</code> if a matching sms gateway log could not be found
	*/
	public static SMSGatewayLog fetchByUuid_First(String uuid,
		OrderByComparator<SMSGatewayLog> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last sms gateway log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sms gateway log
	* @throws NoSuchGatewayLogException if a matching sms gateway log could not be found
	*/
	public static SMSGatewayLog findByUuid_Last(String uuid,
		OrderByComparator<SMSGatewayLog> orderByComparator)
		throws org.opencps.sms.exception.NoSuchGatewayLogException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last sms gateway log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sms gateway log, or <code>null</code> if a matching sms gateway log could not be found
	*/
	public static SMSGatewayLog fetchByUuid_Last(String uuid,
		OrderByComparator<SMSGatewayLog> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the sms gateway logs before and after the current sms gateway log in the ordered set where uuid = &#63;.
	*
	* @param smsId the primary key of the current sms gateway log
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sms gateway log
	* @throws NoSuchGatewayLogException if a sms gateway log with the primary key could not be found
	*/
	public static SMSGatewayLog[] findByUuid_PrevAndNext(long smsId,
		String uuid, OrderByComparator<SMSGatewayLog> orderByComparator)
		throws org.opencps.sms.exception.NoSuchGatewayLogException {
		return getPersistence()
				   .findByUuid_PrevAndNext(smsId, uuid, orderByComparator);
	}

	/**
	* Removes all the sms gateway logs where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of sms gateway logs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching sms gateway logs
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the sms gateway log where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchGatewayLogException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching sms gateway log
	* @throws NoSuchGatewayLogException if a matching sms gateway log could not be found
	*/
	public static SMSGatewayLog findByUUID_G(String uuid, long groupId)
		throws org.opencps.sms.exception.NoSuchGatewayLogException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the sms gateway log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching sms gateway log, or <code>null</code> if a matching sms gateway log could not be found
	*/
	public static SMSGatewayLog fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the sms gateway log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching sms gateway log, or <code>null</code> if a matching sms gateway log could not be found
	*/
	public static SMSGatewayLog fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the sms gateway log where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the sms gateway log that was removed
	*/
	public static SMSGatewayLog removeByUUID_G(String uuid, long groupId)
		throws org.opencps.sms.exception.NoSuchGatewayLogException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of sms gateway logs where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching sms gateway logs
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the sms gateway logs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching sms gateway logs
	*/
	public static List<SMSGatewayLog> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the sms gateway logs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SMSGatewayLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of sms gateway logs
	* @param end the upper bound of the range of sms gateway logs (not inclusive)
	* @return the range of matching sms gateway logs
	*/
	public static List<SMSGatewayLog> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the sms gateway logs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SMSGatewayLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of sms gateway logs
	* @param end the upper bound of the range of sms gateway logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sms gateway logs
	*/
	public static List<SMSGatewayLog> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<SMSGatewayLog> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the sms gateway logs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SMSGatewayLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of sms gateway logs
	* @param end the upper bound of the range of sms gateway logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sms gateway logs
	*/
	public static List<SMSGatewayLog> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<SMSGatewayLog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first sms gateway log in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sms gateway log
	* @throws NoSuchGatewayLogException if a matching sms gateway log could not be found
	*/
	public static SMSGatewayLog findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<SMSGatewayLog> orderByComparator)
		throws org.opencps.sms.exception.NoSuchGatewayLogException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first sms gateway log in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sms gateway log, or <code>null</code> if a matching sms gateway log could not be found
	*/
	public static SMSGatewayLog fetchByUuid_C_First(String uuid,
		long companyId, OrderByComparator<SMSGatewayLog> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last sms gateway log in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sms gateway log
	* @throws NoSuchGatewayLogException if a matching sms gateway log could not be found
	*/
	public static SMSGatewayLog findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<SMSGatewayLog> orderByComparator)
		throws org.opencps.sms.exception.NoSuchGatewayLogException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last sms gateway log in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sms gateway log, or <code>null</code> if a matching sms gateway log could not be found
	*/
	public static SMSGatewayLog fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<SMSGatewayLog> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the sms gateway logs before and after the current sms gateway log in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param smsId the primary key of the current sms gateway log
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sms gateway log
	* @throws NoSuchGatewayLogException if a sms gateway log with the primary key could not be found
	*/
	public static SMSGatewayLog[] findByUuid_C_PrevAndNext(long smsId,
		String uuid, long companyId,
		OrderByComparator<SMSGatewayLog> orderByComparator)
		throws org.opencps.sms.exception.NoSuchGatewayLogException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(smsId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the sms gateway logs where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of sms gateway logs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching sms gateway logs
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Caches the sms gateway log in the entity cache if it is enabled.
	*
	* @param smsGatewayLog the sms gateway log
	*/
	public static void cacheResult(SMSGatewayLog smsGatewayLog) {
		getPersistence().cacheResult(smsGatewayLog);
	}

	/**
	* Caches the sms gateway logs in the entity cache if it is enabled.
	*
	* @param smsGatewayLogs the sms gateway logs
	*/
	public static void cacheResult(List<SMSGatewayLog> smsGatewayLogs) {
		getPersistence().cacheResult(smsGatewayLogs);
	}

	/**
	* Creates a new sms gateway log with the primary key. Does not add the sms gateway log to the database.
	*
	* @param smsId the primary key for the new sms gateway log
	* @return the new sms gateway log
	*/
	public static SMSGatewayLog create(long smsId) {
		return getPersistence().create(smsId);
	}

	/**
	* Removes the sms gateway log with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param smsId the primary key of the sms gateway log
	* @return the sms gateway log that was removed
	* @throws NoSuchGatewayLogException if a sms gateway log with the primary key could not be found
	*/
	public static SMSGatewayLog remove(long smsId)
		throws org.opencps.sms.exception.NoSuchGatewayLogException {
		return getPersistence().remove(smsId);
	}

	public static SMSGatewayLog updateImpl(SMSGatewayLog smsGatewayLog) {
		return getPersistence().updateImpl(smsGatewayLog);
	}

	/**
	* Returns the sms gateway log with the primary key or throws a {@link NoSuchGatewayLogException} if it could not be found.
	*
	* @param smsId the primary key of the sms gateway log
	* @return the sms gateway log
	* @throws NoSuchGatewayLogException if a sms gateway log with the primary key could not be found
	*/
	public static SMSGatewayLog findByPrimaryKey(long smsId)
		throws org.opencps.sms.exception.NoSuchGatewayLogException {
		return getPersistence().findByPrimaryKey(smsId);
	}

	/**
	* Returns the sms gateway log with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param smsId the primary key of the sms gateway log
	* @return the sms gateway log, or <code>null</code> if a sms gateway log with the primary key could not be found
	*/
	public static SMSGatewayLog fetchByPrimaryKey(long smsId) {
		return getPersistence().fetchByPrimaryKey(smsId);
	}

	public static java.util.Map<java.io.Serializable, SMSGatewayLog> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the sms gateway logs.
	*
	* @return the sms gateway logs
	*/
	public static List<SMSGatewayLog> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the sms gateway logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SMSGatewayLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sms gateway logs
	* @param end the upper bound of the range of sms gateway logs (not inclusive)
	* @return the range of sms gateway logs
	*/
	public static List<SMSGatewayLog> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the sms gateway logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SMSGatewayLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sms gateway logs
	* @param end the upper bound of the range of sms gateway logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of sms gateway logs
	*/
	public static List<SMSGatewayLog> findAll(int start, int end,
		OrderByComparator<SMSGatewayLog> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the sms gateway logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SMSGatewayLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sms gateway logs
	* @param end the upper bound of the range of sms gateway logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of sms gateway logs
	*/
	public static List<SMSGatewayLog> findAll(int start, int end,
		OrderByComparator<SMSGatewayLog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the sms gateway logs from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of sms gateway logs.
	*
	* @return the number of sms gateway logs
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static SMSGatewayLogPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<SMSGatewayLogPersistence, SMSGatewayLogPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(SMSGatewayLogPersistence.class);

		ServiceTracker<SMSGatewayLogPersistence, SMSGatewayLogPersistence> serviceTracker =
			new ServiceTracker<SMSGatewayLogPersistence, SMSGatewayLogPersistence>(bundle.getBundleContext(),
				SMSGatewayLogPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}