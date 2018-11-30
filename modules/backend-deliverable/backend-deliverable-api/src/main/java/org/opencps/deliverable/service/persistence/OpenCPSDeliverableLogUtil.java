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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.deliverable.model.OpenCPSDeliverableLog;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the open cps deliverable log service. This utility wraps {@link org.opencps.deliverable.service.persistence.impl.OpenCPSDeliverableLogPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author binhth
 * @see OpenCPSDeliverableLogPersistence
 * @see org.opencps.deliverable.service.persistence.impl.OpenCPSDeliverableLogPersistenceImpl
 * @generated
 */
@ProviderType
public class OpenCPSDeliverableLogUtil {
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
	public static void clearCache(OpenCPSDeliverableLog openCPSDeliverableLog) {
		getPersistence().clearCache(openCPSDeliverableLog);
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
	public static List<OpenCPSDeliverableLog> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<OpenCPSDeliverableLog> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<OpenCPSDeliverableLog> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<OpenCPSDeliverableLog> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static OpenCPSDeliverableLog update(
		OpenCPSDeliverableLog openCPSDeliverableLog) {
		return getPersistence().update(openCPSDeliverableLog);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static OpenCPSDeliverableLog update(
		OpenCPSDeliverableLog openCPSDeliverableLog,
		ServiceContext serviceContext) {
		return getPersistence().update(openCPSDeliverableLog, serviceContext);
	}

	/**
	* Returns all the open cps deliverable logs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching open cps deliverable logs
	*/
	public static List<OpenCPSDeliverableLog> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

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
	public static List<OpenCPSDeliverableLog> findByUuid(String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static List<OpenCPSDeliverableLog> findByUuid(String uuid,
		int start, int end,
		OrderByComparator<OpenCPSDeliverableLog> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

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
	public static List<OpenCPSDeliverableLog> findByUuid(String uuid,
		int start, int end,
		OrderByComparator<OpenCPSDeliverableLog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first open cps deliverable log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching open cps deliverable log
	* @throws NoSuchOpenCPSDeliverableLogException if a matching open cps deliverable log could not be found
	*/
	public static OpenCPSDeliverableLog findByUuid_First(String uuid,
		OrderByComparator<OpenCPSDeliverableLog> orderByComparator)
		throws org.opencps.deliverable.exception.NoSuchOpenCPSDeliverableLogException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first open cps deliverable log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching open cps deliverable log, or <code>null</code> if a matching open cps deliverable log could not be found
	*/
	public static OpenCPSDeliverableLog fetchByUuid_First(String uuid,
		OrderByComparator<OpenCPSDeliverableLog> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last open cps deliverable log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching open cps deliverable log
	* @throws NoSuchOpenCPSDeliverableLogException if a matching open cps deliverable log could not be found
	*/
	public static OpenCPSDeliverableLog findByUuid_Last(String uuid,
		OrderByComparator<OpenCPSDeliverableLog> orderByComparator)
		throws org.opencps.deliverable.exception.NoSuchOpenCPSDeliverableLogException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last open cps deliverable log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching open cps deliverable log, or <code>null</code> if a matching open cps deliverable log could not be found
	*/
	public static OpenCPSDeliverableLog fetchByUuid_Last(String uuid,
		OrderByComparator<OpenCPSDeliverableLog> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the open cps deliverable logs before and after the current open cps deliverable log in the ordered set where uuid = &#63;.
	*
	* @param deliverableLogId the primary key of the current open cps deliverable log
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next open cps deliverable log
	* @throws NoSuchOpenCPSDeliverableLogException if a open cps deliverable log with the primary key could not be found
	*/
	public static OpenCPSDeliverableLog[] findByUuid_PrevAndNext(
		long deliverableLogId, String uuid,
		OrderByComparator<OpenCPSDeliverableLog> orderByComparator)
		throws org.opencps.deliverable.exception.NoSuchOpenCPSDeliverableLogException {
		return getPersistence()
				   .findByUuid_PrevAndNext(deliverableLogId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the open cps deliverable logs where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of open cps deliverable logs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching open cps deliverable logs
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the open cps deliverable log where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchOpenCPSDeliverableLogException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching open cps deliverable log
	* @throws NoSuchOpenCPSDeliverableLogException if a matching open cps deliverable log could not be found
	*/
	public static OpenCPSDeliverableLog findByUUID_G(String uuid, long groupId)
		throws org.opencps.deliverable.exception.NoSuchOpenCPSDeliverableLogException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the open cps deliverable log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching open cps deliverable log, or <code>null</code> if a matching open cps deliverable log could not be found
	*/
	public static OpenCPSDeliverableLog fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the open cps deliverable log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching open cps deliverable log, or <code>null</code> if a matching open cps deliverable log could not be found
	*/
	public static OpenCPSDeliverableLog fetchByUUID_G(String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the open cps deliverable log where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the open cps deliverable log that was removed
	*/
	public static OpenCPSDeliverableLog removeByUUID_G(String uuid, long groupId)
		throws org.opencps.deliverable.exception.NoSuchOpenCPSDeliverableLogException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of open cps deliverable logs where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching open cps deliverable logs
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the open cps deliverable logs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching open cps deliverable logs
	*/
	public static List<OpenCPSDeliverableLog> findByUuid_C(String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

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
	public static List<OpenCPSDeliverableLog> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

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
	public static List<OpenCPSDeliverableLog> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<OpenCPSDeliverableLog> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

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
	public static List<OpenCPSDeliverableLog> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<OpenCPSDeliverableLog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first open cps deliverable log in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching open cps deliverable log
	* @throws NoSuchOpenCPSDeliverableLogException if a matching open cps deliverable log could not be found
	*/
	public static OpenCPSDeliverableLog findByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<OpenCPSDeliverableLog> orderByComparator)
		throws org.opencps.deliverable.exception.NoSuchOpenCPSDeliverableLogException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first open cps deliverable log in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching open cps deliverable log, or <code>null</code> if a matching open cps deliverable log could not be found
	*/
	public static OpenCPSDeliverableLog fetchByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<OpenCPSDeliverableLog> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last open cps deliverable log in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching open cps deliverable log
	* @throws NoSuchOpenCPSDeliverableLogException if a matching open cps deliverable log could not be found
	*/
	public static OpenCPSDeliverableLog findByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<OpenCPSDeliverableLog> orderByComparator)
		throws org.opencps.deliverable.exception.NoSuchOpenCPSDeliverableLogException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last open cps deliverable log in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching open cps deliverable log, or <code>null</code> if a matching open cps deliverable log could not be found
	*/
	public static OpenCPSDeliverableLog fetchByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<OpenCPSDeliverableLog> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

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
	public static OpenCPSDeliverableLog[] findByUuid_C_PrevAndNext(
		long deliverableLogId, String uuid, long companyId,
		OrderByComparator<OpenCPSDeliverableLog> orderByComparator)
		throws org.opencps.deliverable.exception.NoSuchOpenCPSDeliverableLogException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(deliverableLogId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the open cps deliverable logs where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of open cps deliverable logs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching open cps deliverable logs
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the open cps deliverable logs where deliverableId = &#63;.
	*
	* @param deliverableId the deliverable ID
	* @return the matching open cps deliverable logs
	*/
	public static List<OpenCPSDeliverableLog> findByF_deliverableId(
		long deliverableId) {
		return getPersistence().findByF_deliverableId(deliverableId);
	}

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
	public static List<OpenCPSDeliverableLog> findByF_deliverableId(
		long deliverableId, int start, int end) {
		return getPersistence().findByF_deliverableId(deliverableId, start, end);
	}

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
	public static List<OpenCPSDeliverableLog> findByF_deliverableId(
		long deliverableId, int start, int end,
		OrderByComparator<OpenCPSDeliverableLog> orderByComparator) {
		return getPersistence()
				   .findByF_deliverableId(deliverableId, start, end,
			orderByComparator);
	}

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
	public static List<OpenCPSDeliverableLog> findByF_deliverableId(
		long deliverableId, int start, int end,
		OrderByComparator<OpenCPSDeliverableLog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_deliverableId(deliverableId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first open cps deliverable log in the ordered set where deliverableId = &#63;.
	*
	* @param deliverableId the deliverable ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching open cps deliverable log
	* @throws NoSuchOpenCPSDeliverableLogException if a matching open cps deliverable log could not be found
	*/
	public static OpenCPSDeliverableLog findByF_deliverableId_First(
		long deliverableId,
		OrderByComparator<OpenCPSDeliverableLog> orderByComparator)
		throws org.opencps.deliverable.exception.NoSuchOpenCPSDeliverableLogException {
		return getPersistence()
				   .findByF_deliverableId_First(deliverableId, orderByComparator);
	}

	/**
	* Returns the first open cps deliverable log in the ordered set where deliverableId = &#63;.
	*
	* @param deliverableId the deliverable ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching open cps deliverable log, or <code>null</code> if a matching open cps deliverable log could not be found
	*/
	public static OpenCPSDeliverableLog fetchByF_deliverableId_First(
		long deliverableId,
		OrderByComparator<OpenCPSDeliverableLog> orderByComparator) {
		return getPersistence()
				   .fetchByF_deliverableId_First(deliverableId,
			orderByComparator);
	}

	/**
	* Returns the last open cps deliverable log in the ordered set where deliverableId = &#63;.
	*
	* @param deliverableId the deliverable ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching open cps deliverable log
	* @throws NoSuchOpenCPSDeliverableLogException if a matching open cps deliverable log could not be found
	*/
	public static OpenCPSDeliverableLog findByF_deliverableId_Last(
		long deliverableId,
		OrderByComparator<OpenCPSDeliverableLog> orderByComparator)
		throws org.opencps.deliverable.exception.NoSuchOpenCPSDeliverableLogException {
		return getPersistence()
				   .findByF_deliverableId_Last(deliverableId, orderByComparator);
	}

	/**
	* Returns the last open cps deliverable log in the ordered set where deliverableId = &#63;.
	*
	* @param deliverableId the deliverable ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching open cps deliverable log, or <code>null</code> if a matching open cps deliverable log could not be found
	*/
	public static OpenCPSDeliverableLog fetchByF_deliverableId_Last(
		long deliverableId,
		OrderByComparator<OpenCPSDeliverableLog> orderByComparator) {
		return getPersistence()
				   .fetchByF_deliverableId_Last(deliverableId, orderByComparator);
	}

	/**
	* Returns the open cps deliverable logs before and after the current open cps deliverable log in the ordered set where deliverableId = &#63;.
	*
	* @param deliverableLogId the primary key of the current open cps deliverable log
	* @param deliverableId the deliverable ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next open cps deliverable log
	* @throws NoSuchOpenCPSDeliverableLogException if a open cps deliverable log with the primary key could not be found
	*/
	public static OpenCPSDeliverableLog[] findByF_deliverableId_PrevAndNext(
		long deliverableLogId, long deliverableId,
		OrderByComparator<OpenCPSDeliverableLog> orderByComparator)
		throws org.opencps.deliverable.exception.NoSuchOpenCPSDeliverableLogException {
		return getPersistence()
				   .findByF_deliverableId_PrevAndNext(deliverableLogId,
			deliverableId, orderByComparator);
	}

	/**
	* Removes all the open cps deliverable logs where deliverableId = &#63; from the database.
	*
	* @param deliverableId the deliverable ID
	*/
	public static void removeByF_deliverableId(long deliverableId) {
		getPersistence().removeByF_deliverableId(deliverableId);
	}

	/**
	* Returns the number of open cps deliverable logs where deliverableId = &#63;.
	*
	* @param deliverableId the deliverable ID
	* @return the number of matching open cps deliverable logs
	*/
	public static int countByF_deliverableId(long deliverableId) {
		return getPersistence().countByF_deliverableId(deliverableId);
	}

	/**
	* Caches the open cps deliverable log in the entity cache if it is enabled.
	*
	* @param openCPSDeliverableLog the open cps deliverable log
	*/
	public static void cacheResult(OpenCPSDeliverableLog openCPSDeliverableLog) {
		getPersistence().cacheResult(openCPSDeliverableLog);
	}

	/**
	* Caches the open cps deliverable logs in the entity cache if it is enabled.
	*
	* @param openCPSDeliverableLogs the open cps deliverable logs
	*/
	public static void cacheResult(
		List<OpenCPSDeliverableLog> openCPSDeliverableLogs) {
		getPersistence().cacheResult(openCPSDeliverableLogs);
	}

	/**
	* Creates a new open cps deliverable log with the primary key. Does not add the open cps deliverable log to the database.
	*
	* @param deliverableLogId the primary key for the new open cps deliverable log
	* @return the new open cps deliverable log
	*/
	public static OpenCPSDeliverableLog create(long deliverableLogId) {
		return getPersistence().create(deliverableLogId);
	}

	/**
	* Removes the open cps deliverable log with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param deliverableLogId the primary key of the open cps deliverable log
	* @return the open cps deliverable log that was removed
	* @throws NoSuchOpenCPSDeliverableLogException if a open cps deliverable log with the primary key could not be found
	*/
	public static OpenCPSDeliverableLog remove(long deliverableLogId)
		throws org.opencps.deliverable.exception.NoSuchOpenCPSDeliverableLogException {
		return getPersistence().remove(deliverableLogId);
	}

	public static OpenCPSDeliverableLog updateImpl(
		OpenCPSDeliverableLog openCPSDeliverableLog) {
		return getPersistence().updateImpl(openCPSDeliverableLog);
	}

	/**
	* Returns the open cps deliverable log with the primary key or throws a {@link NoSuchOpenCPSDeliverableLogException} if it could not be found.
	*
	* @param deliverableLogId the primary key of the open cps deliverable log
	* @return the open cps deliverable log
	* @throws NoSuchOpenCPSDeliverableLogException if a open cps deliverable log with the primary key could not be found
	*/
	public static OpenCPSDeliverableLog findByPrimaryKey(long deliverableLogId)
		throws org.opencps.deliverable.exception.NoSuchOpenCPSDeliverableLogException {
		return getPersistence().findByPrimaryKey(deliverableLogId);
	}

	/**
	* Returns the open cps deliverable log with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param deliverableLogId the primary key of the open cps deliverable log
	* @return the open cps deliverable log, or <code>null</code> if a open cps deliverable log with the primary key could not be found
	*/
	public static OpenCPSDeliverableLog fetchByPrimaryKey(long deliverableLogId) {
		return getPersistence().fetchByPrimaryKey(deliverableLogId);
	}

	public static java.util.Map<java.io.Serializable, OpenCPSDeliverableLog> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the open cps deliverable logs.
	*
	* @return the open cps deliverable logs
	*/
	public static List<OpenCPSDeliverableLog> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<OpenCPSDeliverableLog> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<OpenCPSDeliverableLog> findAll(int start, int end,
		OrderByComparator<OpenCPSDeliverableLog> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<OpenCPSDeliverableLog> findAll(int start, int end,
		OrderByComparator<OpenCPSDeliverableLog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the open cps deliverable logs from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of open cps deliverable logs.
	*
	* @return the number of open cps deliverable logs
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static OpenCPSDeliverableLogPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<OpenCPSDeliverableLogPersistence, OpenCPSDeliverableLogPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(OpenCPSDeliverableLogPersistence.class);

		ServiceTracker<OpenCPSDeliverableLogPersistence, OpenCPSDeliverableLogPersistence> serviceTracker =
			new ServiceTracker<OpenCPSDeliverableLogPersistence, OpenCPSDeliverableLogPersistence>(bundle.getBundleContext(),
				OpenCPSDeliverableLogPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}