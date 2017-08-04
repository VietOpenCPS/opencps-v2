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

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.usermgt.model.JoinSiteStatus;
import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the join site status service. This utility wraps {@link org.mobilink.backend.usermgt.service.persistence.impl.JoinSiteStatusPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Binhth
 * @see JoinSiteStatusPersistence
 * @see org.mobilink.backend.usermgt.service.persistence.impl.JoinSiteStatusPersistenceImpl
 * @generated
 */
@ProviderType
public class JoinSiteStatusUtil {
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
	public static void clearCache(JoinSiteStatus joinSiteStatus) {
		getPersistence().clearCache(joinSiteStatus);
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
	public static List<JoinSiteStatus> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<JoinSiteStatus> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<JoinSiteStatus> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<JoinSiteStatus> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static JoinSiteStatus update(JoinSiteStatus joinSiteStatus) {
		return getPersistence().update(joinSiteStatus);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static JoinSiteStatus update(JoinSiteStatus joinSiteStatus,
		ServiceContext serviceContext) {
		return getPersistence().update(joinSiteStatus, serviceContext);
	}

	/**
	* Returns all the join site statuses where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching join site statuses
	*/
	public static List<JoinSiteStatus> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the join site statuses where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JoinSiteStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of join site statuses
	* @param end the upper bound of the range of join site statuses (not inclusive)
	* @return the range of matching join site statuses
	*/
	public static List<JoinSiteStatus> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the join site statuses where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JoinSiteStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of join site statuses
	* @param end the upper bound of the range of join site statuses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching join site statuses
	*/
	public static List<JoinSiteStatus> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<JoinSiteStatus> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the join site statuses where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JoinSiteStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of join site statuses
	* @param end the upper bound of the range of join site statuses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching join site statuses
	*/
	public static List<JoinSiteStatus> findByUuid(java.lang.String uuid,
		int start, int end,
		OrderByComparator<JoinSiteStatus> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first join site status in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching join site status
	* @throws NoSuchJoinSiteStatusException if a matching join site status could not be found
	*/
	public static JoinSiteStatus findByUuid_First(java.lang.String uuid,
		OrderByComparator<JoinSiteStatus> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchJoinSiteStatusException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first join site status in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching join site status, or <code>null</code> if a matching join site status could not be found
	*/
	public static JoinSiteStatus fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<JoinSiteStatus> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last join site status in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching join site status
	* @throws NoSuchJoinSiteStatusException if a matching join site status could not be found
	*/
	public static JoinSiteStatus findByUuid_Last(java.lang.String uuid,
		OrderByComparator<JoinSiteStatus> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchJoinSiteStatusException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last join site status in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching join site status, or <code>null</code> if a matching join site status could not be found
	*/
	public static JoinSiteStatus fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<JoinSiteStatus> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the join site statuses before and after the current join site status in the ordered set where uuid = &#63;.
	*
	* @param JoinSiteStatusId the primary key of the current join site status
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next join site status
	* @throws NoSuchJoinSiteStatusException if a join site status with the primary key could not be found
	*/
	public static JoinSiteStatus[] findByUuid_PrevAndNext(
		long JoinSiteStatusId, java.lang.String uuid,
		OrderByComparator<JoinSiteStatus> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchJoinSiteStatusException {
		return getPersistence()
				   .findByUuid_PrevAndNext(JoinSiteStatusId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the join site statuses where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of join site statuses where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching join site statuses
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the join site status where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchJoinSiteStatusException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching join site status
	* @throws NoSuchJoinSiteStatusException if a matching join site status could not be found
	*/
	public static JoinSiteStatus findByUUID_G(java.lang.String uuid,
		long groupId)
		throws org.opencps.usermgt.exception.NoSuchJoinSiteStatusException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the join site status where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching join site status, or <code>null</code> if a matching join site status could not be found
	*/
	public static JoinSiteStatus fetchByUUID_G(java.lang.String uuid,
		long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the join site status where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching join site status, or <code>null</code> if a matching join site status could not be found
	*/
	public static JoinSiteStatus fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the join site status where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the join site status that was removed
	*/
	public static JoinSiteStatus removeByUUID_G(java.lang.String uuid,
		long groupId)
		throws org.opencps.usermgt.exception.NoSuchJoinSiteStatusException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of join site statuses where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching join site statuses
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the join site statuses where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching join site statuses
	*/
	public static List<JoinSiteStatus> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the join site statuses where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JoinSiteStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of join site statuses
	* @param end the upper bound of the range of join site statuses (not inclusive)
	* @return the range of matching join site statuses
	*/
	public static List<JoinSiteStatus> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the join site statuses where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JoinSiteStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of join site statuses
	* @param end the upper bound of the range of join site statuses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching join site statuses
	*/
	public static List<JoinSiteStatus> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<JoinSiteStatus> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the join site statuses where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JoinSiteStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of join site statuses
	* @param end the upper bound of the range of join site statuses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching join site statuses
	*/
	public static List<JoinSiteStatus> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<JoinSiteStatus> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first join site status in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching join site status
	* @throws NoSuchJoinSiteStatusException if a matching join site status could not be found
	*/
	public static JoinSiteStatus findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<JoinSiteStatus> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchJoinSiteStatusException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first join site status in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching join site status, or <code>null</code> if a matching join site status could not be found
	*/
	public static JoinSiteStatus fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<JoinSiteStatus> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last join site status in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching join site status
	* @throws NoSuchJoinSiteStatusException if a matching join site status could not be found
	*/
	public static JoinSiteStatus findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<JoinSiteStatus> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchJoinSiteStatusException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last join site status in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching join site status, or <code>null</code> if a matching join site status could not be found
	*/
	public static JoinSiteStatus fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<JoinSiteStatus> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the join site statuses before and after the current join site status in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param JoinSiteStatusId the primary key of the current join site status
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next join site status
	* @throws NoSuchJoinSiteStatusException if a join site status with the primary key could not be found
	*/
	public static JoinSiteStatus[] findByUuid_C_PrevAndNext(
		long JoinSiteStatusId, java.lang.String uuid, long companyId,
		OrderByComparator<JoinSiteStatus> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchJoinSiteStatusException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(JoinSiteStatusId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the join site statuses where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of join site statuses where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching join site statuses
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns the join site status where employeeId = &#63; and joinSiteGroupId = &#63; or throws a {@link NoSuchJoinSiteStatusException} if it could not be found.
	*
	* @param employeeId the employee ID
	* @param joinSiteGroupId the join site group ID
	* @return the matching join site status
	* @throws NoSuchJoinSiteStatusException if a matching join site status could not be found
	*/
	public static JoinSiteStatus findByF_employeeId_joinSiteGroupId(
		long employeeId, long joinSiteGroupId)
		throws org.opencps.usermgt.exception.NoSuchJoinSiteStatusException {
		return getPersistence()
				   .findByF_employeeId_joinSiteGroupId(employeeId,
			joinSiteGroupId);
	}

	/**
	* Returns the join site status where employeeId = &#63; and joinSiteGroupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param employeeId the employee ID
	* @param joinSiteGroupId the join site group ID
	* @return the matching join site status, or <code>null</code> if a matching join site status could not be found
	*/
	public static JoinSiteStatus fetchByF_employeeId_joinSiteGroupId(
		long employeeId, long joinSiteGroupId) {
		return getPersistence()
				   .fetchByF_employeeId_joinSiteGroupId(employeeId,
			joinSiteGroupId);
	}

	/**
	* Returns the join site status where employeeId = &#63; and joinSiteGroupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param employeeId the employee ID
	* @param joinSiteGroupId the join site group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching join site status, or <code>null</code> if a matching join site status could not be found
	*/
	public static JoinSiteStatus fetchByF_employeeId_joinSiteGroupId(
		long employeeId, long joinSiteGroupId, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_employeeId_joinSiteGroupId(employeeId,
			joinSiteGroupId, retrieveFromCache);
	}

	/**
	* Removes the join site status where employeeId = &#63; and joinSiteGroupId = &#63; from the database.
	*
	* @param employeeId the employee ID
	* @param joinSiteGroupId the join site group ID
	* @return the join site status that was removed
	*/
	public static JoinSiteStatus removeByF_employeeId_joinSiteGroupId(
		long employeeId, long joinSiteGroupId)
		throws org.opencps.usermgt.exception.NoSuchJoinSiteStatusException {
		return getPersistence()
				   .removeByF_employeeId_joinSiteGroupId(employeeId,
			joinSiteGroupId);
	}

	/**
	* Returns the number of join site statuses where employeeId = &#63; and joinSiteGroupId = &#63;.
	*
	* @param employeeId the employee ID
	* @param joinSiteGroupId the join site group ID
	* @return the number of matching join site statuses
	*/
	public static int countByF_employeeId_joinSiteGroupId(long employeeId,
		long joinSiteGroupId) {
		return getPersistence()
				   .countByF_employeeId_joinSiteGroupId(employeeId,
			joinSiteGroupId);
	}

	/**
	* Caches the join site status in the entity cache if it is enabled.
	*
	* @param joinSiteStatus the join site status
	*/
	public static void cacheResult(JoinSiteStatus joinSiteStatus) {
		getPersistence().cacheResult(joinSiteStatus);
	}

	/**
	* Caches the join site statuses in the entity cache if it is enabled.
	*
	* @param joinSiteStatuses the join site statuses
	*/
	public static void cacheResult(List<JoinSiteStatus> joinSiteStatuses) {
		getPersistence().cacheResult(joinSiteStatuses);
	}

	/**
	* Creates a new join site status with the primary key. Does not add the join site status to the database.
	*
	* @param JoinSiteStatusId the primary key for the new join site status
	* @return the new join site status
	*/
	public static JoinSiteStatus create(long JoinSiteStatusId) {
		return getPersistence().create(JoinSiteStatusId);
	}

	/**
	* Removes the join site status with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param JoinSiteStatusId the primary key of the join site status
	* @return the join site status that was removed
	* @throws NoSuchJoinSiteStatusException if a join site status with the primary key could not be found
	*/
	public static JoinSiteStatus remove(long JoinSiteStatusId)
		throws org.opencps.usermgt.exception.NoSuchJoinSiteStatusException {
		return getPersistence().remove(JoinSiteStatusId);
	}

	public static JoinSiteStatus updateImpl(JoinSiteStatus joinSiteStatus) {
		return getPersistence().updateImpl(joinSiteStatus);
	}

	/**
	* Returns the join site status with the primary key or throws a {@link NoSuchJoinSiteStatusException} if it could not be found.
	*
	* @param JoinSiteStatusId the primary key of the join site status
	* @return the join site status
	* @throws NoSuchJoinSiteStatusException if a join site status with the primary key could not be found
	*/
	public static JoinSiteStatus findByPrimaryKey(long JoinSiteStatusId)
		throws org.opencps.usermgt.exception.NoSuchJoinSiteStatusException {
		return getPersistence().findByPrimaryKey(JoinSiteStatusId);
	}

	/**
	* Returns the join site status with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param JoinSiteStatusId the primary key of the join site status
	* @return the join site status, or <code>null</code> if a join site status with the primary key could not be found
	*/
	public static JoinSiteStatus fetchByPrimaryKey(long JoinSiteStatusId) {
		return getPersistence().fetchByPrimaryKey(JoinSiteStatusId);
	}

	public static java.util.Map<java.io.Serializable, JoinSiteStatus> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the join site statuses.
	*
	* @return the join site statuses
	*/
	public static List<JoinSiteStatus> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the join site statuses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JoinSiteStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of join site statuses
	* @param end the upper bound of the range of join site statuses (not inclusive)
	* @return the range of join site statuses
	*/
	public static List<JoinSiteStatus> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the join site statuses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JoinSiteStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of join site statuses
	* @param end the upper bound of the range of join site statuses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of join site statuses
	*/
	public static List<JoinSiteStatus> findAll(int start, int end,
		OrderByComparator<JoinSiteStatus> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the join site statuses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JoinSiteStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of join site statuses
	* @param end the upper bound of the range of join site statuses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of join site statuses
	*/
	public static List<JoinSiteStatus> findAll(int start, int end,
		OrderByComparator<JoinSiteStatus> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the join site statuses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of join site statuses.
	*
	* @return the number of join site statuses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static JoinSiteStatusPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<JoinSiteStatusPersistence, JoinSiteStatusPersistence> _serviceTracker =
		ServiceTrackerFactory.open(JoinSiteStatusPersistence.class);
}