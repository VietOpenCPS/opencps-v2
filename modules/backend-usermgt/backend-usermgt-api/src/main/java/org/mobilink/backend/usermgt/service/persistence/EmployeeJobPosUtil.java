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

package org.mobilink.backend.usermgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.mobilink.backend.usermgt.model.EmployeeJobPos;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the employee job pos service. This utility wraps {@link org.mobilink.backend.usermgt.service.persistence.impl.EmployeeJobPosPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Binhth
 * @see EmployeeJobPosPersistence
 * @see org.mobilink.backend.usermgt.service.persistence.impl.EmployeeJobPosPersistenceImpl
 * @generated
 */
@ProviderType
public class EmployeeJobPosUtil {
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
	public static void clearCache(EmployeeJobPos employeeJobPos) {
		getPersistence().clearCache(employeeJobPos);
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
	public static List<EmployeeJobPos> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<EmployeeJobPos> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<EmployeeJobPos> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<EmployeeJobPos> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static EmployeeJobPos update(EmployeeJobPos employeeJobPos) {
		return getPersistence().update(employeeJobPos);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static EmployeeJobPos update(EmployeeJobPos employeeJobPos,
		ServiceContext serviceContext) {
		return getPersistence().update(employeeJobPos, serviceContext);
	}

	/**
	* Returns all the employee job poses where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching employee job poses
	*/
	public static List<EmployeeJobPos> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the employee job poses where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeJobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of employee job poses
	* @param end the upper bound of the range of employee job poses (not inclusive)
	* @return the range of matching employee job poses
	*/
	public static List<EmployeeJobPos> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the employee job poses where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeJobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of employee job poses
	* @param end the upper bound of the range of employee job poses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching employee job poses
	*/
	public static List<EmployeeJobPos> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<EmployeeJobPos> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the employee job poses where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeJobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of employee job poses
	* @param end the upper bound of the range of employee job poses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching employee job poses
	*/
	public static List<EmployeeJobPos> findByUuid(java.lang.String uuid,
		int start, int end,
		OrderByComparator<EmployeeJobPos> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first employee job pos in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching employee job pos
	* @throws NoSuchEmployeeJobPosException if a matching employee job pos could not be found
	*/
	public static EmployeeJobPos findByUuid_First(java.lang.String uuid,
		OrderByComparator<EmployeeJobPos> orderByComparator)
		throws org.mobilink.backend.usermgt.exception.NoSuchEmployeeJobPosException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first employee job pos in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching employee job pos, or <code>null</code> if a matching employee job pos could not be found
	*/
	public static EmployeeJobPos fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<EmployeeJobPos> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last employee job pos in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching employee job pos
	* @throws NoSuchEmployeeJobPosException if a matching employee job pos could not be found
	*/
	public static EmployeeJobPos findByUuid_Last(java.lang.String uuid,
		OrderByComparator<EmployeeJobPos> orderByComparator)
		throws org.mobilink.backend.usermgt.exception.NoSuchEmployeeJobPosException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last employee job pos in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching employee job pos, or <code>null</code> if a matching employee job pos could not be found
	*/
	public static EmployeeJobPos fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<EmployeeJobPos> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the employee job poses before and after the current employee job pos in the ordered set where uuid = &#63;.
	*
	* @param employeeJobPosId the primary key of the current employee job pos
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next employee job pos
	* @throws NoSuchEmployeeJobPosException if a employee job pos with the primary key could not be found
	*/
	public static EmployeeJobPos[] findByUuid_PrevAndNext(
		long employeeJobPosId, java.lang.String uuid,
		OrderByComparator<EmployeeJobPos> orderByComparator)
		throws org.mobilink.backend.usermgt.exception.NoSuchEmployeeJobPosException {
		return getPersistence()
				   .findByUuid_PrevAndNext(employeeJobPosId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the employee job poses where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of employee job poses where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching employee job poses
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the employee job pos where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchEmployeeJobPosException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching employee job pos
	* @throws NoSuchEmployeeJobPosException if a matching employee job pos could not be found
	*/
	public static EmployeeJobPos findByUUID_G(java.lang.String uuid,
		long groupId)
		throws org.mobilink.backend.usermgt.exception.NoSuchEmployeeJobPosException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the employee job pos where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching employee job pos, or <code>null</code> if a matching employee job pos could not be found
	*/
	public static EmployeeJobPos fetchByUUID_G(java.lang.String uuid,
		long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the employee job pos where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching employee job pos, or <code>null</code> if a matching employee job pos could not be found
	*/
	public static EmployeeJobPos fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the employee job pos where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the employee job pos that was removed
	*/
	public static EmployeeJobPos removeByUUID_G(java.lang.String uuid,
		long groupId)
		throws org.mobilink.backend.usermgt.exception.NoSuchEmployeeJobPosException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of employee job poses where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching employee job poses
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the employee job poses where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching employee job poses
	*/
	public static List<EmployeeJobPos> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the employee job poses where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeJobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of employee job poses
	* @param end the upper bound of the range of employee job poses (not inclusive)
	* @return the range of matching employee job poses
	*/
	public static List<EmployeeJobPos> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the employee job poses where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeJobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of employee job poses
	* @param end the upper bound of the range of employee job poses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching employee job poses
	*/
	public static List<EmployeeJobPos> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<EmployeeJobPos> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the employee job poses where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeJobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of employee job poses
	* @param end the upper bound of the range of employee job poses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching employee job poses
	*/
	public static List<EmployeeJobPos> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<EmployeeJobPos> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first employee job pos in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching employee job pos
	* @throws NoSuchEmployeeJobPosException if a matching employee job pos could not be found
	*/
	public static EmployeeJobPos findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<EmployeeJobPos> orderByComparator)
		throws org.mobilink.backend.usermgt.exception.NoSuchEmployeeJobPosException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first employee job pos in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching employee job pos, or <code>null</code> if a matching employee job pos could not be found
	*/
	public static EmployeeJobPos fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<EmployeeJobPos> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last employee job pos in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching employee job pos
	* @throws NoSuchEmployeeJobPosException if a matching employee job pos could not be found
	*/
	public static EmployeeJobPos findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<EmployeeJobPos> orderByComparator)
		throws org.mobilink.backend.usermgt.exception.NoSuchEmployeeJobPosException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last employee job pos in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching employee job pos, or <code>null</code> if a matching employee job pos could not be found
	*/
	public static EmployeeJobPos fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<EmployeeJobPos> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the employee job poses before and after the current employee job pos in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param employeeJobPosId the primary key of the current employee job pos
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next employee job pos
	* @throws NoSuchEmployeeJobPosException if a employee job pos with the primary key could not be found
	*/
	public static EmployeeJobPos[] findByUuid_C_PrevAndNext(
		long employeeJobPosId, java.lang.String uuid, long companyId,
		OrderByComparator<EmployeeJobPos> orderByComparator)
		throws org.mobilink.backend.usermgt.exception.NoSuchEmployeeJobPosException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(employeeJobPosId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the employee job poses where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of employee job poses where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching employee job poses
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the employee job poses where employeeId = &#63;.
	*
	* @param employeeId the employee ID
	* @return the matching employee job poses
	*/
	public static List<EmployeeJobPos> findByF_EmployeeId(long employeeId) {
		return getPersistence().findByF_EmployeeId(employeeId);
	}

	/**
	* Returns a range of all the employee job poses where employeeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeJobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param employeeId the employee ID
	* @param start the lower bound of the range of employee job poses
	* @param end the upper bound of the range of employee job poses (not inclusive)
	* @return the range of matching employee job poses
	*/
	public static List<EmployeeJobPos> findByF_EmployeeId(long employeeId,
		int start, int end) {
		return getPersistence().findByF_EmployeeId(employeeId, start, end);
	}

	/**
	* Returns an ordered range of all the employee job poses where employeeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeJobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param employeeId the employee ID
	* @param start the lower bound of the range of employee job poses
	* @param end the upper bound of the range of employee job poses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching employee job poses
	*/
	public static List<EmployeeJobPos> findByF_EmployeeId(long employeeId,
		int start, int end, OrderByComparator<EmployeeJobPos> orderByComparator) {
		return getPersistence()
				   .findByF_EmployeeId(employeeId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the employee job poses where employeeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeJobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param employeeId the employee ID
	* @param start the lower bound of the range of employee job poses
	* @param end the upper bound of the range of employee job poses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching employee job poses
	*/
	public static List<EmployeeJobPos> findByF_EmployeeId(long employeeId,
		int start, int end,
		OrderByComparator<EmployeeJobPos> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_EmployeeId(employeeId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first employee job pos in the ordered set where employeeId = &#63;.
	*
	* @param employeeId the employee ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching employee job pos
	* @throws NoSuchEmployeeJobPosException if a matching employee job pos could not be found
	*/
	public static EmployeeJobPos findByF_EmployeeId_First(long employeeId,
		OrderByComparator<EmployeeJobPos> orderByComparator)
		throws org.mobilink.backend.usermgt.exception.NoSuchEmployeeJobPosException {
		return getPersistence()
				   .findByF_EmployeeId_First(employeeId, orderByComparator);
	}

	/**
	* Returns the first employee job pos in the ordered set where employeeId = &#63;.
	*
	* @param employeeId the employee ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching employee job pos, or <code>null</code> if a matching employee job pos could not be found
	*/
	public static EmployeeJobPos fetchByF_EmployeeId_First(long employeeId,
		OrderByComparator<EmployeeJobPos> orderByComparator) {
		return getPersistence()
				   .fetchByF_EmployeeId_First(employeeId, orderByComparator);
	}

	/**
	* Returns the last employee job pos in the ordered set where employeeId = &#63;.
	*
	* @param employeeId the employee ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching employee job pos
	* @throws NoSuchEmployeeJobPosException if a matching employee job pos could not be found
	*/
	public static EmployeeJobPos findByF_EmployeeId_Last(long employeeId,
		OrderByComparator<EmployeeJobPos> orderByComparator)
		throws org.mobilink.backend.usermgt.exception.NoSuchEmployeeJobPosException {
		return getPersistence()
				   .findByF_EmployeeId_Last(employeeId, orderByComparator);
	}

	/**
	* Returns the last employee job pos in the ordered set where employeeId = &#63;.
	*
	* @param employeeId the employee ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching employee job pos, or <code>null</code> if a matching employee job pos could not be found
	*/
	public static EmployeeJobPos fetchByF_EmployeeId_Last(long employeeId,
		OrderByComparator<EmployeeJobPos> orderByComparator) {
		return getPersistence()
				   .fetchByF_EmployeeId_Last(employeeId, orderByComparator);
	}

	/**
	* Returns the employee job poses before and after the current employee job pos in the ordered set where employeeId = &#63;.
	*
	* @param employeeJobPosId the primary key of the current employee job pos
	* @param employeeId the employee ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next employee job pos
	* @throws NoSuchEmployeeJobPosException if a employee job pos with the primary key could not be found
	*/
	public static EmployeeJobPos[] findByF_EmployeeId_PrevAndNext(
		long employeeJobPosId, long employeeId,
		OrderByComparator<EmployeeJobPos> orderByComparator)
		throws org.mobilink.backend.usermgt.exception.NoSuchEmployeeJobPosException {
		return getPersistence()
				   .findByF_EmployeeId_PrevAndNext(employeeJobPosId,
			employeeId, orderByComparator);
	}

	/**
	* Removes all the employee job poses where employeeId = &#63; from the database.
	*
	* @param employeeId the employee ID
	*/
	public static void removeByF_EmployeeId(long employeeId) {
		getPersistence().removeByF_EmployeeId(employeeId);
	}

	/**
	* Returns the number of employee job poses where employeeId = &#63;.
	*
	* @param employeeId the employee ID
	* @return the number of matching employee job poses
	*/
	public static int countByF_EmployeeId(long employeeId) {
		return getPersistence().countByF_EmployeeId(employeeId);
	}

	/**
	* Caches the employee job pos in the entity cache if it is enabled.
	*
	* @param employeeJobPos the employee job pos
	*/
	public static void cacheResult(EmployeeJobPos employeeJobPos) {
		getPersistence().cacheResult(employeeJobPos);
	}

	/**
	* Caches the employee job poses in the entity cache if it is enabled.
	*
	* @param employeeJobPoses the employee job poses
	*/
	public static void cacheResult(List<EmployeeJobPos> employeeJobPoses) {
		getPersistence().cacheResult(employeeJobPoses);
	}

	/**
	* Creates a new employee job pos with the primary key. Does not add the employee job pos to the database.
	*
	* @param employeeJobPosId the primary key for the new employee job pos
	* @return the new employee job pos
	*/
	public static EmployeeJobPos create(long employeeJobPosId) {
		return getPersistence().create(employeeJobPosId);
	}

	/**
	* Removes the employee job pos with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param employeeJobPosId the primary key of the employee job pos
	* @return the employee job pos that was removed
	* @throws NoSuchEmployeeJobPosException if a employee job pos with the primary key could not be found
	*/
	public static EmployeeJobPos remove(long employeeJobPosId)
		throws org.mobilink.backend.usermgt.exception.NoSuchEmployeeJobPosException {
		return getPersistence().remove(employeeJobPosId);
	}

	public static EmployeeJobPos updateImpl(EmployeeJobPos employeeJobPos) {
		return getPersistence().updateImpl(employeeJobPos);
	}

	/**
	* Returns the employee job pos with the primary key or throws a {@link NoSuchEmployeeJobPosException} if it could not be found.
	*
	* @param employeeJobPosId the primary key of the employee job pos
	* @return the employee job pos
	* @throws NoSuchEmployeeJobPosException if a employee job pos with the primary key could not be found
	*/
	public static EmployeeJobPos findByPrimaryKey(long employeeJobPosId)
		throws org.mobilink.backend.usermgt.exception.NoSuchEmployeeJobPosException {
		return getPersistence().findByPrimaryKey(employeeJobPosId);
	}

	/**
	* Returns the employee job pos with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param employeeJobPosId the primary key of the employee job pos
	* @return the employee job pos, or <code>null</code> if a employee job pos with the primary key could not be found
	*/
	public static EmployeeJobPos fetchByPrimaryKey(long employeeJobPosId) {
		return getPersistence().fetchByPrimaryKey(employeeJobPosId);
	}

	public static java.util.Map<java.io.Serializable, EmployeeJobPos> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the employee job poses.
	*
	* @return the employee job poses
	*/
	public static List<EmployeeJobPos> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the employee job poses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeJobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of employee job poses
	* @param end the upper bound of the range of employee job poses (not inclusive)
	* @return the range of employee job poses
	*/
	public static List<EmployeeJobPos> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the employee job poses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeJobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of employee job poses
	* @param end the upper bound of the range of employee job poses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of employee job poses
	*/
	public static List<EmployeeJobPos> findAll(int start, int end,
		OrderByComparator<EmployeeJobPos> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the employee job poses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeJobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of employee job poses
	* @param end the upper bound of the range of employee job poses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of employee job poses
	*/
	public static List<EmployeeJobPos> findAll(int start, int end,
		OrderByComparator<EmployeeJobPos> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the employee job poses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of employee job poses.
	*
	* @return the number of employee job poses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static EmployeeJobPosPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<EmployeeJobPosPersistence, EmployeeJobPosPersistence> _serviceTracker =
		ServiceTrackerFactory.open(EmployeeJobPosPersistence.class);
}