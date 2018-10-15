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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.usermgt.model.Employee;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the employee service. This utility wraps {@link org.opencps.usermgt.service.persistence.impl.EmployeePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see EmployeePersistence
 * @see org.opencps.usermgt.service.persistence.impl.EmployeePersistenceImpl
 * @generated
 */
@ProviderType
public class EmployeeUtil {
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
	public static void clearCache(Employee employee) {
		getPersistence().clearCache(employee);
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
	public static List<Employee> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Employee> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Employee> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Employee> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Employee update(Employee employee) {
		return getPersistence().update(employee);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Employee update(Employee employee,
		ServiceContext serviceContext) {
		return getPersistence().update(employee, serviceContext);
	}

	/**
	* Returns all the employees where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching employees
	*/
	public static List<Employee> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the employees where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of employees
	* @param end the upper bound of the range of employees (not inclusive)
	* @return the range of matching employees
	*/
	public static List<Employee> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the employees where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of employees
	* @param end the upper bound of the range of employees (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching employees
	*/
	public static List<Employee> findByUuid(String uuid, int start, int end,
		OrderByComparator<Employee> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the employees where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of employees
	* @param end the upper bound of the range of employees (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching employees
	*/
	public static List<Employee> findByUuid(String uuid, int start, int end,
		OrderByComparator<Employee> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first employee in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching employee
	* @throws NoSuchEmployeeException if a matching employee could not be found
	*/
	public static Employee findByUuid_First(String uuid,
		OrderByComparator<Employee> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchEmployeeException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first employee in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching employee, or <code>null</code> if a matching employee could not be found
	*/
	public static Employee fetchByUuid_First(String uuid,
		OrderByComparator<Employee> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last employee in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching employee
	* @throws NoSuchEmployeeException if a matching employee could not be found
	*/
	public static Employee findByUuid_Last(String uuid,
		OrderByComparator<Employee> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchEmployeeException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last employee in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching employee, or <code>null</code> if a matching employee could not be found
	*/
	public static Employee fetchByUuid_Last(String uuid,
		OrderByComparator<Employee> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the employees before and after the current employee in the ordered set where uuid = &#63;.
	*
	* @param employeeId the primary key of the current employee
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next employee
	* @throws NoSuchEmployeeException if a employee with the primary key could not be found
	*/
	public static Employee[] findByUuid_PrevAndNext(long employeeId,
		String uuid, OrderByComparator<Employee> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchEmployeeException {
		return getPersistence()
				   .findByUuid_PrevAndNext(employeeId, uuid, orderByComparator);
	}

	/**
	* Removes all the employees where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of employees where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching employees
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the employee where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchEmployeeException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching employee
	* @throws NoSuchEmployeeException if a matching employee could not be found
	*/
	public static Employee findByUUID_G(String uuid, long groupId)
		throws org.opencps.usermgt.exception.NoSuchEmployeeException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the employee where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching employee, or <code>null</code> if a matching employee could not be found
	*/
	public static Employee fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the employee where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching employee, or <code>null</code> if a matching employee could not be found
	*/
	public static Employee fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the employee where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the employee that was removed
	*/
	public static Employee removeByUUID_G(String uuid, long groupId)
		throws org.opencps.usermgt.exception.NoSuchEmployeeException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of employees where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching employees
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the employees where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching employees
	*/
	public static List<Employee> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the employees where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of employees
	* @param end the upper bound of the range of employees (not inclusive)
	* @return the range of matching employees
	*/
	public static List<Employee> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the employees where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of employees
	* @param end the upper bound of the range of employees (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching employees
	*/
	public static List<Employee> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<Employee> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the employees where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of employees
	* @param end the upper bound of the range of employees (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching employees
	*/
	public static List<Employee> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<Employee> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first employee in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching employee
	* @throws NoSuchEmployeeException if a matching employee could not be found
	*/
	public static Employee findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Employee> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchEmployeeException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first employee in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching employee, or <code>null</code> if a matching employee could not be found
	*/
	public static Employee fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Employee> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last employee in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching employee
	* @throws NoSuchEmployeeException if a matching employee could not be found
	*/
	public static Employee findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Employee> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchEmployeeException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last employee in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching employee, or <code>null</code> if a matching employee could not be found
	*/
	public static Employee fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Employee> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the employees before and after the current employee in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param employeeId the primary key of the current employee
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next employee
	* @throws NoSuchEmployeeException if a employee with the primary key could not be found
	*/
	public static Employee[] findByUuid_C_PrevAndNext(long employeeId,
		String uuid, long companyId,
		OrderByComparator<Employee> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchEmployeeException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(employeeId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the employees where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of employees where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching employees
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns the employee where groupId = &#63; and mappingUserId = &#63; or throws a {@link NoSuchEmployeeException} if it could not be found.
	*
	* @param groupId the group ID
	* @param mappingUserId the mapping user ID
	* @return the matching employee
	* @throws NoSuchEmployeeException if a matching employee could not be found
	*/
	public static Employee findByF_mappingUserId(long groupId,
		long mappingUserId)
		throws org.opencps.usermgt.exception.NoSuchEmployeeException {
		return getPersistence().findByF_mappingUserId(groupId, mappingUserId);
	}

	/**
	* Returns the employee where groupId = &#63; and mappingUserId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param mappingUserId the mapping user ID
	* @return the matching employee, or <code>null</code> if a matching employee could not be found
	*/
	public static Employee fetchByF_mappingUserId(long groupId,
		long mappingUserId) {
		return getPersistence().fetchByF_mappingUserId(groupId, mappingUserId);
	}

	/**
	* Returns the employee where groupId = &#63; and mappingUserId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param mappingUserId the mapping user ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching employee, or <code>null</code> if a matching employee could not be found
	*/
	public static Employee fetchByF_mappingUserId(long groupId,
		long mappingUserId, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_mappingUserId(groupId, mappingUserId,
			retrieveFromCache);
	}

	/**
	* Removes the employee where groupId = &#63; and mappingUserId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param mappingUserId the mapping user ID
	* @return the employee that was removed
	*/
	public static Employee removeByF_mappingUserId(long groupId,
		long mappingUserId)
		throws org.opencps.usermgt.exception.NoSuchEmployeeException {
		return getPersistence().removeByF_mappingUserId(groupId, mappingUserId);
	}

	/**
	* Returns the number of employees where groupId = &#63; and mappingUserId = &#63;.
	*
	* @param groupId the group ID
	* @param mappingUserId the mapping user ID
	* @return the number of matching employees
	*/
	public static int countByF_mappingUserId(long groupId, long mappingUserId) {
		return getPersistence().countByF_mappingUserId(groupId, mappingUserId);
	}

	/**
	* Returns the employee where mappingUserId = &#63; or throws a {@link NoSuchEmployeeException} if it could not be found.
	*
	* @param mappingUserId the mapping user ID
	* @return the matching employee
	* @throws NoSuchEmployeeException if a matching employee could not be found
	*/
	public static Employee findByFB_MUID(long mappingUserId)
		throws org.opencps.usermgt.exception.NoSuchEmployeeException {
		return getPersistence().findByFB_MUID(mappingUserId);
	}

	/**
	* Returns the employee where mappingUserId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param mappingUserId the mapping user ID
	* @return the matching employee, or <code>null</code> if a matching employee could not be found
	*/
	public static Employee fetchByFB_MUID(long mappingUserId) {
		return getPersistence().fetchByFB_MUID(mappingUserId);
	}

	/**
	* Returns the employee where mappingUserId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param mappingUserId the mapping user ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching employee, or <code>null</code> if a matching employee could not be found
	*/
	public static Employee fetchByFB_MUID(long mappingUserId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByFB_MUID(mappingUserId, retrieveFromCache);
	}

	/**
	* Removes the employee where mappingUserId = &#63; from the database.
	*
	* @param mappingUserId the mapping user ID
	* @return the employee that was removed
	*/
	public static Employee removeByFB_MUID(long mappingUserId)
		throws org.opencps.usermgt.exception.NoSuchEmployeeException {
		return getPersistence().removeByFB_MUID(mappingUserId);
	}

	/**
	* Returns the number of employees where mappingUserId = &#63;.
	*
	* @param mappingUserId the mapping user ID
	* @return the number of matching employees
	*/
	public static int countByFB_MUID(long mappingUserId) {
		return getPersistence().countByFB_MUID(mappingUserId);
	}

	/**
	* Returns all the employees where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching employees
	*/
	public static List<Employee> findByF_groupId(long groupId) {
		return getPersistence().findByF_groupId(groupId);
	}

	/**
	* Returns a range of all the employees where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of employees
	* @param end the upper bound of the range of employees (not inclusive)
	* @return the range of matching employees
	*/
	public static List<Employee> findByF_groupId(long groupId, int start,
		int end) {
		return getPersistence().findByF_groupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the employees where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of employees
	* @param end the upper bound of the range of employees (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching employees
	*/
	public static List<Employee> findByF_groupId(long groupId, int start,
		int end, OrderByComparator<Employee> orderByComparator) {
		return getPersistence()
				   .findByF_groupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the employees where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of employees
	* @param end the upper bound of the range of employees (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching employees
	*/
	public static List<Employee> findByF_groupId(long groupId, int start,
		int end, OrderByComparator<Employee> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_groupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first employee in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching employee
	* @throws NoSuchEmployeeException if a matching employee could not be found
	*/
	public static Employee findByF_groupId_First(long groupId,
		OrderByComparator<Employee> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchEmployeeException {
		return getPersistence().findByF_groupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first employee in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching employee, or <code>null</code> if a matching employee could not be found
	*/
	public static Employee fetchByF_groupId_First(long groupId,
		OrderByComparator<Employee> orderByComparator) {
		return getPersistence()
				   .fetchByF_groupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last employee in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching employee
	* @throws NoSuchEmployeeException if a matching employee could not be found
	*/
	public static Employee findByF_groupId_Last(long groupId,
		OrderByComparator<Employee> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchEmployeeException {
		return getPersistence().findByF_groupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last employee in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching employee, or <code>null</code> if a matching employee could not be found
	*/
	public static Employee fetchByF_groupId_Last(long groupId,
		OrderByComparator<Employee> orderByComparator) {
		return getPersistence().fetchByF_groupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the employees before and after the current employee in the ordered set where groupId = &#63;.
	*
	* @param employeeId the primary key of the current employee
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next employee
	* @throws NoSuchEmployeeException if a employee with the primary key could not be found
	*/
	public static Employee[] findByF_groupId_PrevAndNext(long employeeId,
		long groupId, OrderByComparator<Employee> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchEmployeeException {
		return getPersistence()
				   .findByF_groupId_PrevAndNext(employeeId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the employees where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByF_groupId(long groupId) {
		getPersistence().removeByF_groupId(groupId);
	}

	/**
	* Returns the number of employees where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching employees
	*/
	public static int countByF_groupId(long groupId) {
		return getPersistence().countByF_groupId(groupId);
	}

	/**
	* Returns all the employees where groupId = &#63; and employeeNo = &#63;.
	*
	* @param groupId the group ID
	* @param employeeNo the employee no
	* @return the matching employees
	*/
	public static List<Employee> findByF_employeeNo(long groupId,
		String employeeNo) {
		return getPersistence().findByF_employeeNo(groupId, employeeNo);
	}

	/**
	* Returns a range of all the employees where groupId = &#63; and employeeNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param employeeNo the employee no
	* @param start the lower bound of the range of employees
	* @param end the upper bound of the range of employees (not inclusive)
	* @return the range of matching employees
	*/
	public static List<Employee> findByF_employeeNo(long groupId,
		String employeeNo, int start, int end) {
		return getPersistence()
				   .findByF_employeeNo(groupId, employeeNo, start, end);
	}

	/**
	* Returns an ordered range of all the employees where groupId = &#63; and employeeNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param employeeNo the employee no
	* @param start the lower bound of the range of employees
	* @param end the upper bound of the range of employees (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching employees
	*/
	public static List<Employee> findByF_employeeNo(long groupId,
		String employeeNo, int start, int end,
		OrderByComparator<Employee> orderByComparator) {
		return getPersistence()
				   .findByF_employeeNo(groupId, employeeNo, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the employees where groupId = &#63; and employeeNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param employeeNo the employee no
	* @param start the lower bound of the range of employees
	* @param end the upper bound of the range of employees (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching employees
	*/
	public static List<Employee> findByF_employeeNo(long groupId,
		String employeeNo, int start, int end,
		OrderByComparator<Employee> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_employeeNo(groupId, employeeNo, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first employee in the ordered set where groupId = &#63; and employeeNo = &#63;.
	*
	* @param groupId the group ID
	* @param employeeNo the employee no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching employee
	* @throws NoSuchEmployeeException if a matching employee could not be found
	*/
	public static Employee findByF_employeeNo_First(long groupId,
		String employeeNo, OrderByComparator<Employee> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchEmployeeException {
		return getPersistence()
				   .findByF_employeeNo_First(groupId, employeeNo,
			orderByComparator);
	}

	/**
	* Returns the first employee in the ordered set where groupId = &#63; and employeeNo = &#63;.
	*
	* @param groupId the group ID
	* @param employeeNo the employee no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching employee, or <code>null</code> if a matching employee could not be found
	*/
	public static Employee fetchByF_employeeNo_First(long groupId,
		String employeeNo, OrderByComparator<Employee> orderByComparator) {
		return getPersistence()
				   .fetchByF_employeeNo_First(groupId, employeeNo,
			orderByComparator);
	}

	/**
	* Returns the last employee in the ordered set where groupId = &#63; and employeeNo = &#63;.
	*
	* @param groupId the group ID
	* @param employeeNo the employee no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching employee
	* @throws NoSuchEmployeeException if a matching employee could not be found
	*/
	public static Employee findByF_employeeNo_Last(long groupId,
		String employeeNo, OrderByComparator<Employee> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchEmployeeException {
		return getPersistence()
				   .findByF_employeeNo_Last(groupId, employeeNo,
			orderByComparator);
	}

	/**
	* Returns the last employee in the ordered set where groupId = &#63; and employeeNo = &#63;.
	*
	* @param groupId the group ID
	* @param employeeNo the employee no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching employee, or <code>null</code> if a matching employee could not be found
	*/
	public static Employee fetchByF_employeeNo_Last(long groupId,
		String employeeNo, OrderByComparator<Employee> orderByComparator) {
		return getPersistence()
				   .fetchByF_employeeNo_Last(groupId, employeeNo,
			orderByComparator);
	}

	/**
	* Returns the employees before and after the current employee in the ordered set where groupId = &#63; and employeeNo = &#63;.
	*
	* @param employeeId the primary key of the current employee
	* @param groupId the group ID
	* @param employeeNo the employee no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next employee
	* @throws NoSuchEmployeeException if a employee with the primary key could not be found
	*/
	public static Employee[] findByF_employeeNo_PrevAndNext(long employeeId,
		long groupId, String employeeNo,
		OrderByComparator<Employee> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchEmployeeException {
		return getPersistence()
				   .findByF_employeeNo_PrevAndNext(employeeId, groupId,
			employeeNo, orderByComparator);
	}

	/**
	* Removes all the employees where groupId = &#63; and employeeNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param employeeNo the employee no
	*/
	public static void removeByF_employeeNo(long groupId, String employeeNo) {
		getPersistence().removeByF_employeeNo(groupId, employeeNo);
	}

	/**
	* Returns the number of employees where groupId = &#63; and employeeNo = &#63;.
	*
	* @param groupId the group ID
	* @param employeeNo the employee no
	* @return the number of matching employees
	*/
	public static int countByF_employeeNo(long groupId, String employeeNo) {
		return getPersistence().countByF_employeeNo(groupId, employeeNo);
	}

	/**
	* Returns the employee where groupId = &#63; and employeeNo = &#63; or throws a {@link NoSuchEmployeeException} if it could not be found.
	*
	* @param groupId the group ID
	* @param employeeNo the employee no
	* @return the matching employee
	* @throws NoSuchEmployeeException if a matching employee could not be found
	*/
	public static Employee findByF_GID_EMPNO(long groupId, String employeeNo)
		throws org.opencps.usermgt.exception.NoSuchEmployeeException {
		return getPersistence().findByF_GID_EMPNO(groupId, employeeNo);
	}

	/**
	* Returns the employee where groupId = &#63; and employeeNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param employeeNo the employee no
	* @return the matching employee, or <code>null</code> if a matching employee could not be found
	*/
	public static Employee fetchByF_GID_EMPNO(long groupId, String employeeNo) {
		return getPersistence().fetchByF_GID_EMPNO(groupId, employeeNo);
	}

	/**
	* Returns the employee where groupId = &#63; and employeeNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param employeeNo the employee no
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching employee, or <code>null</code> if a matching employee could not be found
	*/
	public static Employee fetchByF_GID_EMPNO(long groupId, String employeeNo,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_GID_EMPNO(groupId, employeeNo, retrieveFromCache);
	}

	/**
	* Removes the employee where groupId = &#63; and employeeNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param employeeNo the employee no
	* @return the employee that was removed
	*/
	public static Employee removeByF_GID_EMPNO(long groupId, String employeeNo)
		throws org.opencps.usermgt.exception.NoSuchEmployeeException {
		return getPersistence().removeByF_GID_EMPNO(groupId, employeeNo);
	}

	/**
	* Returns the number of employees where groupId = &#63; and employeeNo = &#63;.
	*
	* @param groupId the group ID
	* @param employeeNo the employee no
	* @return the number of matching employees
	*/
	public static int countByF_GID_EMPNO(long groupId, String employeeNo) {
		return getPersistence().countByF_GID_EMPNO(groupId, employeeNo);
	}

	/**
	* Returns all the employees where groupId = &#63; and email = &#63;.
	*
	* @param groupId the group ID
	* @param email the email
	* @return the matching employees
	*/
	public static List<Employee> findByF_email(long groupId, String email) {
		return getPersistence().findByF_email(groupId, email);
	}

	/**
	* Returns a range of all the employees where groupId = &#63; and email = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param email the email
	* @param start the lower bound of the range of employees
	* @param end the upper bound of the range of employees (not inclusive)
	* @return the range of matching employees
	*/
	public static List<Employee> findByF_email(long groupId, String email,
		int start, int end) {
		return getPersistence().findByF_email(groupId, email, start, end);
	}

	/**
	* Returns an ordered range of all the employees where groupId = &#63; and email = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param email the email
	* @param start the lower bound of the range of employees
	* @param end the upper bound of the range of employees (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching employees
	*/
	public static List<Employee> findByF_email(long groupId, String email,
		int start, int end, OrderByComparator<Employee> orderByComparator) {
		return getPersistence()
				   .findByF_email(groupId, email, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the employees where groupId = &#63; and email = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param email the email
	* @param start the lower bound of the range of employees
	* @param end the upper bound of the range of employees (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching employees
	*/
	public static List<Employee> findByF_email(long groupId, String email,
		int start, int end, OrderByComparator<Employee> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_email(groupId, email, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first employee in the ordered set where groupId = &#63; and email = &#63;.
	*
	* @param groupId the group ID
	* @param email the email
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching employee
	* @throws NoSuchEmployeeException if a matching employee could not be found
	*/
	public static Employee findByF_email_First(long groupId, String email,
		OrderByComparator<Employee> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchEmployeeException {
		return getPersistence()
				   .findByF_email_First(groupId, email, orderByComparator);
	}

	/**
	* Returns the first employee in the ordered set where groupId = &#63; and email = &#63;.
	*
	* @param groupId the group ID
	* @param email the email
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching employee, or <code>null</code> if a matching employee could not be found
	*/
	public static Employee fetchByF_email_First(long groupId, String email,
		OrderByComparator<Employee> orderByComparator) {
		return getPersistence()
				   .fetchByF_email_First(groupId, email, orderByComparator);
	}

	/**
	* Returns the last employee in the ordered set where groupId = &#63; and email = &#63;.
	*
	* @param groupId the group ID
	* @param email the email
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching employee
	* @throws NoSuchEmployeeException if a matching employee could not be found
	*/
	public static Employee findByF_email_Last(long groupId, String email,
		OrderByComparator<Employee> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchEmployeeException {
		return getPersistence()
				   .findByF_email_Last(groupId, email, orderByComparator);
	}

	/**
	* Returns the last employee in the ordered set where groupId = &#63; and email = &#63;.
	*
	* @param groupId the group ID
	* @param email the email
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching employee, or <code>null</code> if a matching employee could not be found
	*/
	public static Employee fetchByF_email_Last(long groupId, String email,
		OrderByComparator<Employee> orderByComparator) {
		return getPersistence()
				   .fetchByF_email_Last(groupId, email, orderByComparator);
	}

	/**
	* Returns the employees before and after the current employee in the ordered set where groupId = &#63; and email = &#63;.
	*
	* @param employeeId the primary key of the current employee
	* @param groupId the group ID
	* @param email the email
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next employee
	* @throws NoSuchEmployeeException if a employee with the primary key could not be found
	*/
	public static Employee[] findByF_email_PrevAndNext(long employeeId,
		long groupId, String email,
		OrderByComparator<Employee> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchEmployeeException {
		return getPersistence()
				   .findByF_email_PrevAndNext(employeeId, groupId, email,
			orderByComparator);
	}

	/**
	* Removes all the employees where groupId = &#63; and email = &#63; from the database.
	*
	* @param groupId the group ID
	* @param email the email
	*/
	public static void removeByF_email(long groupId, String email) {
		getPersistence().removeByF_email(groupId, email);
	}

	/**
	* Returns the number of employees where groupId = &#63; and email = &#63;.
	*
	* @param groupId the group ID
	* @param email the email
	* @return the number of matching employees
	*/
	public static int countByF_email(long groupId, String email) {
		return getPersistence().countByF_email(groupId, email);
	}

	/**
	* Returns all the employees where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the matching employees
	*/
	public static List<Employee> findByG_UID(long groupId, long userId) {
		return getPersistence().findByG_UID(groupId, userId);
	}

	/**
	* Returns a range of all the employees where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param start the lower bound of the range of employees
	* @param end the upper bound of the range of employees (not inclusive)
	* @return the range of matching employees
	*/
	public static List<Employee> findByG_UID(long groupId, long userId,
		int start, int end) {
		return getPersistence().findByG_UID(groupId, userId, start, end);
	}

	/**
	* Returns an ordered range of all the employees where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param start the lower bound of the range of employees
	* @param end the upper bound of the range of employees (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching employees
	*/
	public static List<Employee> findByG_UID(long groupId, long userId,
		int start, int end, OrderByComparator<Employee> orderByComparator) {
		return getPersistence()
				   .findByG_UID(groupId, userId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the employees where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param start the lower bound of the range of employees
	* @param end the upper bound of the range of employees (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching employees
	*/
	public static List<Employee> findByG_UID(long groupId, long userId,
		int start, int end, OrderByComparator<Employee> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_UID(groupId, userId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first employee in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching employee
	* @throws NoSuchEmployeeException if a matching employee could not be found
	*/
	public static Employee findByG_UID_First(long groupId, long userId,
		OrderByComparator<Employee> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchEmployeeException {
		return getPersistence()
				   .findByG_UID_First(groupId, userId, orderByComparator);
	}

	/**
	* Returns the first employee in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching employee, or <code>null</code> if a matching employee could not be found
	*/
	public static Employee fetchByG_UID_First(long groupId, long userId,
		OrderByComparator<Employee> orderByComparator) {
		return getPersistence()
				   .fetchByG_UID_First(groupId, userId, orderByComparator);
	}

	/**
	* Returns the last employee in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching employee
	* @throws NoSuchEmployeeException if a matching employee could not be found
	*/
	public static Employee findByG_UID_Last(long groupId, long userId,
		OrderByComparator<Employee> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchEmployeeException {
		return getPersistence()
				   .findByG_UID_Last(groupId, userId, orderByComparator);
	}

	/**
	* Returns the last employee in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching employee, or <code>null</code> if a matching employee could not be found
	*/
	public static Employee fetchByG_UID_Last(long groupId, long userId,
		OrderByComparator<Employee> orderByComparator) {
		return getPersistence()
				   .fetchByG_UID_Last(groupId, userId, orderByComparator);
	}

	/**
	* Returns the employees before and after the current employee in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param employeeId the primary key of the current employee
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next employee
	* @throws NoSuchEmployeeException if a employee with the primary key could not be found
	*/
	public static Employee[] findByG_UID_PrevAndNext(long employeeId,
		long groupId, long userId, OrderByComparator<Employee> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchEmployeeException {
		return getPersistence()
				   .findByG_UID_PrevAndNext(employeeId, groupId, userId,
			orderByComparator);
	}

	/**
	* Removes all the employees where groupId = &#63; and userId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
	*/
	public static void removeByG_UID(long groupId, long userId) {
		getPersistence().removeByG_UID(groupId, userId);
	}

	/**
	* Returns the number of employees where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the number of matching employees
	*/
	public static int countByG_UID(long groupId, long userId) {
		return getPersistence().countByG_UID(groupId, userId);
	}

	/**
	* Caches the employee in the entity cache if it is enabled.
	*
	* @param employee the employee
	*/
	public static void cacheResult(Employee employee) {
		getPersistence().cacheResult(employee);
	}

	/**
	* Caches the employees in the entity cache if it is enabled.
	*
	* @param employees the employees
	*/
	public static void cacheResult(List<Employee> employees) {
		getPersistence().cacheResult(employees);
	}

	/**
	* Creates a new employee with the primary key. Does not add the employee to the database.
	*
	* @param employeeId the primary key for the new employee
	* @return the new employee
	*/
	public static Employee create(long employeeId) {
		return getPersistence().create(employeeId);
	}

	/**
	* Removes the employee with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param employeeId the primary key of the employee
	* @return the employee that was removed
	* @throws NoSuchEmployeeException if a employee with the primary key could not be found
	*/
	public static Employee remove(long employeeId)
		throws org.opencps.usermgt.exception.NoSuchEmployeeException {
		return getPersistence().remove(employeeId);
	}

	public static Employee updateImpl(Employee employee) {
		return getPersistence().updateImpl(employee);
	}

	/**
	* Returns the employee with the primary key or throws a {@link NoSuchEmployeeException} if it could not be found.
	*
	* @param employeeId the primary key of the employee
	* @return the employee
	* @throws NoSuchEmployeeException if a employee with the primary key could not be found
	*/
	public static Employee findByPrimaryKey(long employeeId)
		throws org.opencps.usermgt.exception.NoSuchEmployeeException {
		return getPersistence().findByPrimaryKey(employeeId);
	}

	/**
	* Returns the employee with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param employeeId the primary key of the employee
	* @return the employee, or <code>null</code> if a employee with the primary key could not be found
	*/
	public static Employee fetchByPrimaryKey(long employeeId) {
		return getPersistence().fetchByPrimaryKey(employeeId);
	}

	public static java.util.Map<java.io.Serializable, Employee> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the employees.
	*
	* @return the employees
	*/
	public static List<Employee> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the employees.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of employees
	* @param end the upper bound of the range of employees (not inclusive)
	* @return the range of employees
	*/
	public static List<Employee> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the employees.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of employees
	* @param end the upper bound of the range of employees (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of employees
	*/
	public static List<Employee> findAll(int start, int end,
		OrderByComparator<Employee> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the employees.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of employees
	* @param end the upper bound of the range of employees (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of employees
	*/
	public static List<Employee> findAll(int start, int end,
		OrderByComparator<Employee> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the employees from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of employees.
	*
	* @return the number of employees
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static EmployeePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<EmployeePersistence, EmployeePersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(EmployeePersistence.class);

		ServiceTracker<EmployeePersistence, EmployeePersistence> serviceTracker = new ServiceTracker<EmployeePersistence, EmployeePersistence>(bundle.getBundleContext(),
				EmployeePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}