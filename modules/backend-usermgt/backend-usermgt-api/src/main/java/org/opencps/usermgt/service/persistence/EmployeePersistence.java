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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.usermgt.exception.NoSuchEmployeeException;
import org.opencps.usermgt.model.Employee;

/**
 * The persistence interface for the employee service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see org.opencps.usermgt.service.persistence.impl.EmployeePersistenceImpl
 * @see EmployeeUtil
 * @generated
 */
@ProviderType
public interface EmployeePersistence extends BasePersistence<Employee> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link EmployeeUtil} to access the employee persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the employees where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching employees
	*/
	public java.util.List<Employee> findByUuid(String uuid);

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
	public java.util.List<Employee> findByUuid(String uuid, int start, int end);

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
	public java.util.List<Employee> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Employee> orderByComparator);

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
	public java.util.List<Employee> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Employee> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first employee in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching employee
	* @throws NoSuchEmployeeException if a matching employee could not be found
	*/
	public Employee findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Employee> orderByComparator)
		throws NoSuchEmployeeException;

	/**
	* Returns the first employee in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching employee, or <code>null</code> if a matching employee could not be found
	*/
	public Employee fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Employee> orderByComparator);

	/**
	* Returns the last employee in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching employee
	* @throws NoSuchEmployeeException if a matching employee could not be found
	*/
	public Employee findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Employee> orderByComparator)
		throws NoSuchEmployeeException;

	/**
	* Returns the last employee in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching employee, or <code>null</code> if a matching employee could not be found
	*/
	public Employee fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Employee> orderByComparator);

	/**
	* Returns the employees before and after the current employee in the ordered set where uuid = &#63;.
	*
	* @param employeeId the primary key of the current employee
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next employee
	* @throws NoSuchEmployeeException if a employee with the primary key could not be found
	*/
	public Employee[] findByUuid_PrevAndNext(long employeeId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Employee> orderByComparator)
		throws NoSuchEmployeeException;

	/**
	* Removes all the employees where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of employees where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching employees
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the employee where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchEmployeeException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching employee
	* @throws NoSuchEmployeeException if a matching employee could not be found
	*/
	public Employee findByUUID_G(String uuid, long groupId)
		throws NoSuchEmployeeException;

	/**
	* Returns the employee where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching employee, or <code>null</code> if a matching employee could not be found
	*/
	public Employee fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the employee where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching employee, or <code>null</code> if a matching employee could not be found
	*/
	public Employee fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the employee where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the employee that was removed
	*/
	public Employee removeByUUID_G(String uuid, long groupId)
		throws NoSuchEmployeeException;

	/**
	* Returns the number of employees where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching employees
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the employees where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching employees
	*/
	public java.util.List<Employee> findByUuid_C(String uuid, long companyId);

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
	public java.util.List<Employee> findByUuid_C(String uuid, long companyId,
		int start, int end);

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
	public java.util.List<Employee> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Employee> orderByComparator);

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
	public java.util.List<Employee> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Employee> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first employee in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching employee
	* @throws NoSuchEmployeeException if a matching employee could not be found
	*/
	public Employee findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Employee> orderByComparator)
		throws NoSuchEmployeeException;

	/**
	* Returns the first employee in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching employee, or <code>null</code> if a matching employee could not be found
	*/
	public Employee fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Employee> orderByComparator);

	/**
	* Returns the last employee in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching employee
	* @throws NoSuchEmployeeException if a matching employee could not be found
	*/
	public Employee findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Employee> orderByComparator)
		throws NoSuchEmployeeException;

	/**
	* Returns the last employee in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching employee, or <code>null</code> if a matching employee could not be found
	*/
	public Employee fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Employee> orderByComparator);

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
	public Employee[] findByUuid_C_PrevAndNext(long employeeId, String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Employee> orderByComparator)
		throws NoSuchEmployeeException;

	/**
	* Removes all the employees where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of employees where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching employees
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns the employee where groupId = &#63; and mappingUserId = &#63; or throws a {@link NoSuchEmployeeException} if it could not be found.
	*
	* @param groupId the group ID
	* @param mappingUserId the mapping user ID
	* @return the matching employee
	* @throws NoSuchEmployeeException if a matching employee could not be found
	*/
	public Employee findByF_mappingUserId(long groupId, long mappingUserId)
		throws NoSuchEmployeeException;

	/**
	* Returns the employee where groupId = &#63; and mappingUserId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param mappingUserId the mapping user ID
	* @return the matching employee, or <code>null</code> if a matching employee could not be found
	*/
	public Employee fetchByF_mappingUserId(long groupId, long mappingUserId);

	/**
	* Returns the employee where groupId = &#63; and mappingUserId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param mappingUserId the mapping user ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching employee, or <code>null</code> if a matching employee could not be found
	*/
	public Employee fetchByF_mappingUserId(long groupId, long mappingUserId,
		boolean retrieveFromCache);

	/**
	* Removes the employee where groupId = &#63; and mappingUserId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param mappingUserId the mapping user ID
	* @return the employee that was removed
	*/
	public Employee removeByF_mappingUserId(long groupId, long mappingUserId)
		throws NoSuchEmployeeException;

	/**
	* Returns the number of employees where groupId = &#63; and mappingUserId = &#63;.
	*
	* @param groupId the group ID
	* @param mappingUserId the mapping user ID
	* @return the number of matching employees
	*/
	public int countByF_mappingUserId(long groupId, long mappingUserId);

	/**
	* Returns the employee where mappingUserId = &#63; or throws a {@link NoSuchEmployeeException} if it could not be found.
	*
	* @param mappingUserId the mapping user ID
	* @return the matching employee
	* @throws NoSuchEmployeeException if a matching employee could not be found
	*/
	public Employee findByFB_MUID(long mappingUserId)
		throws NoSuchEmployeeException;

	/**
	* Returns the employee where mappingUserId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param mappingUserId the mapping user ID
	* @return the matching employee, or <code>null</code> if a matching employee could not be found
	*/
	public Employee fetchByFB_MUID(long mappingUserId);

	/**
	* Returns the employee where mappingUserId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param mappingUserId the mapping user ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching employee, or <code>null</code> if a matching employee could not be found
	*/
	public Employee fetchByFB_MUID(long mappingUserId, boolean retrieveFromCache);

	/**
	* Removes the employee where mappingUserId = &#63; from the database.
	*
	* @param mappingUserId the mapping user ID
	* @return the employee that was removed
	*/
	public Employee removeByFB_MUID(long mappingUserId)
		throws NoSuchEmployeeException;

	/**
	* Returns the number of employees where mappingUserId = &#63;.
	*
	* @param mappingUserId the mapping user ID
	* @return the number of matching employees
	*/
	public int countByFB_MUID(long mappingUserId);

	/**
	* Returns all the employees where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching employees
	*/
	public java.util.List<Employee> findByF_groupId(long groupId);

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
	public java.util.List<Employee> findByF_groupId(long groupId, int start,
		int end);

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
	public java.util.List<Employee> findByF_groupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Employee> orderByComparator);

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
	public java.util.List<Employee> findByF_groupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Employee> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first employee in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching employee
	* @throws NoSuchEmployeeException if a matching employee could not be found
	*/
	public Employee findByF_groupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Employee> orderByComparator)
		throws NoSuchEmployeeException;

	/**
	* Returns the first employee in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching employee, or <code>null</code> if a matching employee could not be found
	*/
	public Employee fetchByF_groupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Employee> orderByComparator);

	/**
	* Returns the last employee in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching employee
	* @throws NoSuchEmployeeException if a matching employee could not be found
	*/
	public Employee findByF_groupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Employee> orderByComparator)
		throws NoSuchEmployeeException;

	/**
	* Returns the last employee in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching employee, or <code>null</code> if a matching employee could not be found
	*/
	public Employee fetchByF_groupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Employee> orderByComparator);

	/**
	* Returns the employees before and after the current employee in the ordered set where groupId = &#63;.
	*
	* @param employeeId the primary key of the current employee
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next employee
	* @throws NoSuchEmployeeException if a employee with the primary key could not be found
	*/
	public Employee[] findByF_groupId_PrevAndNext(long employeeId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Employee> orderByComparator)
		throws NoSuchEmployeeException;

	/**
	* Removes all the employees where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByF_groupId(long groupId);

	/**
	* Returns the number of employees where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching employees
	*/
	public int countByF_groupId(long groupId);

	/**
	* Returns all the employees where groupId = &#63; and employeeNo = &#63;.
	*
	* @param groupId the group ID
	* @param employeeNo the employee no
	* @return the matching employees
	*/
	public java.util.List<Employee> findByF_employeeNo(long groupId,
		String employeeNo);

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
	public java.util.List<Employee> findByF_employeeNo(long groupId,
		String employeeNo, int start, int end);

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
	public java.util.List<Employee> findByF_employeeNo(long groupId,
		String employeeNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Employee> orderByComparator);

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
	public java.util.List<Employee> findByF_employeeNo(long groupId,
		String employeeNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Employee> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first employee in the ordered set where groupId = &#63; and employeeNo = &#63;.
	*
	* @param groupId the group ID
	* @param employeeNo the employee no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching employee
	* @throws NoSuchEmployeeException if a matching employee could not be found
	*/
	public Employee findByF_employeeNo_First(long groupId, String employeeNo,
		com.liferay.portal.kernel.util.OrderByComparator<Employee> orderByComparator)
		throws NoSuchEmployeeException;

	/**
	* Returns the first employee in the ordered set where groupId = &#63; and employeeNo = &#63;.
	*
	* @param groupId the group ID
	* @param employeeNo the employee no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching employee, or <code>null</code> if a matching employee could not be found
	*/
	public Employee fetchByF_employeeNo_First(long groupId, String employeeNo,
		com.liferay.portal.kernel.util.OrderByComparator<Employee> orderByComparator);

	/**
	* Returns the last employee in the ordered set where groupId = &#63; and employeeNo = &#63;.
	*
	* @param groupId the group ID
	* @param employeeNo the employee no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching employee
	* @throws NoSuchEmployeeException if a matching employee could not be found
	*/
	public Employee findByF_employeeNo_Last(long groupId, String employeeNo,
		com.liferay.portal.kernel.util.OrderByComparator<Employee> orderByComparator)
		throws NoSuchEmployeeException;

	/**
	* Returns the last employee in the ordered set where groupId = &#63; and employeeNo = &#63;.
	*
	* @param groupId the group ID
	* @param employeeNo the employee no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching employee, or <code>null</code> if a matching employee could not be found
	*/
	public Employee fetchByF_employeeNo_Last(long groupId, String employeeNo,
		com.liferay.portal.kernel.util.OrderByComparator<Employee> orderByComparator);

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
	public Employee[] findByF_employeeNo_PrevAndNext(long employeeId,
		long groupId, String employeeNo,
		com.liferay.portal.kernel.util.OrderByComparator<Employee> orderByComparator)
		throws NoSuchEmployeeException;

	/**
	* Removes all the employees where groupId = &#63; and employeeNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param employeeNo the employee no
	*/
	public void removeByF_employeeNo(long groupId, String employeeNo);

	/**
	* Returns the number of employees where groupId = &#63; and employeeNo = &#63;.
	*
	* @param groupId the group ID
	* @param employeeNo the employee no
	* @return the number of matching employees
	*/
	public int countByF_employeeNo(long groupId, String employeeNo);

	/**
	* Returns the employee where groupId = &#63; and employeeNo = &#63; or throws a {@link NoSuchEmployeeException} if it could not be found.
	*
	* @param groupId the group ID
	* @param employeeNo the employee no
	* @return the matching employee
	* @throws NoSuchEmployeeException if a matching employee could not be found
	*/
	public Employee findByF_GID_EMPNO(long groupId, String employeeNo)
		throws NoSuchEmployeeException;

	/**
	* Returns the employee where groupId = &#63; and employeeNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param employeeNo the employee no
	* @return the matching employee, or <code>null</code> if a matching employee could not be found
	*/
	public Employee fetchByF_GID_EMPNO(long groupId, String employeeNo);

	/**
	* Returns the employee where groupId = &#63; and employeeNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param employeeNo the employee no
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching employee, or <code>null</code> if a matching employee could not be found
	*/
	public Employee fetchByF_GID_EMPNO(long groupId, String employeeNo,
		boolean retrieveFromCache);

	/**
	* Removes the employee where groupId = &#63; and employeeNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param employeeNo the employee no
	* @return the employee that was removed
	*/
	public Employee removeByF_GID_EMPNO(long groupId, String employeeNo)
		throws NoSuchEmployeeException;

	/**
	* Returns the number of employees where groupId = &#63; and employeeNo = &#63;.
	*
	* @param groupId the group ID
	* @param employeeNo the employee no
	* @return the number of matching employees
	*/
	public int countByF_GID_EMPNO(long groupId, String employeeNo);

	/**
	* Returns all the employees where groupId = &#63; and email = &#63;.
	*
	* @param groupId the group ID
	* @param email the email
	* @return the matching employees
	*/
	public java.util.List<Employee> findByF_email(long groupId, String email);

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
	public java.util.List<Employee> findByF_email(long groupId, String email,
		int start, int end);

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
	public java.util.List<Employee> findByF_email(long groupId, String email,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Employee> orderByComparator);

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
	public java.util.List<Employee> findByF_email(long groupId, String email,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Employee> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first employee in the ordered set where groupId = &#63; and email = &#63;.
	*
	* @param groupId the group ID
	* @param email the email
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching employee
	* @throws NoSuchEmployeeException if a matching employee could not be found
	*/
	public Employee findByF_email_First(long groupId, String email,
		com.liferay.portal.kernel.util.OrderByComparator<Employee> orderByComparator)
		throws NoSuchEmployeeException;

	/**
	* Returns the first employee in the ordered set where groupId = &#63; and email = &#63;.
	*
	* @param groupId the group ID
	* @param email the email
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching employee, or <code>null</code> if a matching employee could not be found
	*/
	public Employee fetchByF_email_First(long groupId, String email,
		com.liferay.portal.kernel.util.OrderByComparator<Employee> orderByComparator);

	/**
	* Returns the last employee in the ordered set where groupId = &#63; and email = &#63;.
	*
	* @param groupId the group ID
	* @param email the email
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching employee
	* @throws NoSuchEmployeeException if a matching employee could not be found
	*/
	public Employee findByF_email_Last(long groupId, String email,
		com.liferay.portal.kernel.util.OrderByComparator<Employee> orderByComparator)
		throws NoSuchEmployeeException;

	/**
	* Returns the last employee in the ordered set where groupId = &#63; and email = &#63;.
	*
	* @param groupId the group ID
	* @param email the email
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching employee, or <code>null</code> if a matching employee could not be found
	*/
	public Employee fetchByF_email_Last(long groupId, String email,
		com.liferay.portal.kernel.util.OrderByComparator<Employee> orderByComparator);

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
	public Employee[] findByF_email_PrevAndNext(long employeeId, long groupId,
		String email,
		com.liferay.portal.kernel.util.OrderByComparator<Employee> orderByComparator)
		throws NoSuchEmployeeException;

	/**
	* Removes all the employees where groupId = &#63; and email = &#63; from the database.
	*
	* @param groupId the group ID
	* @param email the email
	*/
	public void removeByF_email(long groupId, String email);

	/**
	* Returns the number of employees where groupId = &#63; and email = &#63;.
	*
	* @param groupId the group ID
	* @param email the email
	* @return the number of matching employees
	*/
	public int countByF_email(long groupId, String email);

	/**
	* Returns all the employees where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the matching employees
	*/
	public java.util.List<Employee> findByG_UID(long groupId, long userId);

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
	public java.util.List<Employee> findByG_UID(long groupId, long userId,
		int start, int end);

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
	public java.util.List<Employee> findByG_UID(long groupId, long userId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Employee> orderByComparator);

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
	public java.util.List<Employee> findByG_UID(long groupId, long userId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Employee> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first employee in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching employee
	* @throws NoSuchEmployeeException if a matching employee could not be found
	*/
	public Employee findByG_UID_First(long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<Employee> orderByComparator)
		throws NoSuchEmployeeException;

	/**
	* Returns the first employee in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching employee, or <code>null</code> if a matching employee could not be found
	*/
	public Employee fetchByG_UID_First(long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<Employee> orderByComparator);

	/**
	* Returns the last employee in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching employee
	* @throws NoSuchEmployeeException if a matching employee could not be found
	*/
	public Employee findByG_UID_Last(long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<Employee> orderByComparator)
		throws NoSuchEmployeeException;

	/**
	* Returns the last employee in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching employee, or <code>null</code> if a matching employee could not be found
	*/
	public Employee fetchByG_UID_Last(long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<Employee> orderByComparator);

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
	public Employee[] findByG_UID_PrevAndNext(long employeeId, long groupId,
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<Employee> orderByComparator)
		throws NoSuchEmployeeException;

	/**
	* Removes all the employees where groupId = &#63; and userId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
	*/
	public void removeByG_UID(long groupId, long userId);

	/**
	* Returns the number of employees where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the number of matching employees
	*/
	public int countByG_UID(long groupId, long userId);

	/**
	* Caches the employee in the entity cache if it is enabled.
	*
	* @param employee the employee
	*/
	public void cacheResult(Employee employee);

	/**
	* Caches the employees in the entity cache if it is enabled.
	*
	* @param employees the employees
	*/
	public void cacheResult(java.util.List<Employee> employees);

	/**
	* Creates a new employee with the primary key. Does not add the employee to the database.
	*
	* @param employeeId the primary key for the new employee
	* @return the new employee
	*/
	public Employee create(long employeeId);

	/**
	* Removes the employee with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param employeeId the primary key of the employee
	* @return the employee that was removed
	* @throws NoSuchEmployeeException if a employee with the primary key could not be found
	*/
	public Employee remove(long employeeId) throws NoSuchEmployeeException;

	public Employee updateImpl(Employee employee);

	/**
	* Returns the employee with the primary key or throws a {@link NoSuchEmployeeException} if it could not be found.
	*
	* @param employeeId the primary key of the employee
	* @return the employee
	* @throws NoSuchEmployeeException if a employee with the primary key could not be found
	*/
	public Employee findByPrimaryKey(long employeeId)
		throws NoSuchEmployeeException;

	/**
	* Returns the employee with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param employeeId the primary key of the employee
	* @return the employee, or <code>null</code> if a employee with the primary key could not be found
	*/
	public Employee fetchByPrimaryKey(long employeeId);

	@Override
	public java.util.Map<java.io.Serializable, Employee> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the employees.
	*
	* @return the employees
	*/
	public java.util.List<Employee> findAll();

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
	public java.util.List<Employee> findAll(int start, int end);

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
	public java.util.List<Employee> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Employee> orderByComparator);

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
	public java.util.List<Employee> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Employee> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the employees from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of employees.
	*
	* @return the number of employees
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}