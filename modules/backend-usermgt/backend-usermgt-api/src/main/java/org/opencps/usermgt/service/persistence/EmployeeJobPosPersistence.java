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

import org.opencps.usermgt.exception.NoSuchEmployeeJobPosException;
import org.opencps.usermgt.model.EmployeeJobPos;

/**
 * The persistence interface for the employee job pos service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Binhth
 * @see org.mobilink.backend.usermgt.service.persistence.impl.EmployeeJobPosPersistenceImpl
 * @see EmployeeJobPosUtil
 * @generated
 */
@ProviderType
public interface EmployeeJobPosPersistence extends BasePersistence<EmployeeJobPos> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link EmployeeJobPosUtil} to access the employee job pos persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the employee job poses where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching employee job poses
	*/
	public java.util.List<EmployeeJobPos> findByUuid(java.lang.String uuid);

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
	public java.util.List<EmployeeJobPos> findByUuid(java.lang.String uuid,
		int start, int end);

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
	public java.util.List<EmployeeJobPos> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EmployeeJobPos> orderByComparator);

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
	public java.util.List<EmployeeJobPos> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EmployeeJobPos> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first employee job pos in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching employee job pos
	* @throws NoSuchEmployeeJobPosException if a matching employee job pos could not be found
	*/
	public EmployeeJobPos findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<EmployeeJobPos> orderByComparator)
		throws NoSuchEmployeeJobPosException;

	/**
	* Returns the first employee job pos in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching employee job pos, or <code>null</code> if a matching employee job pos could not be found
	*/
	public EmployeeJobPos fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<EmployeeJobPos> orderByComparator);

	/**
	* Returns the last employee job pos in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching employee job pos
	* @throws NoSuchEmployeeJobPosException if a matching employee job pos could not be found
	*/
	public EmployeeJobPos findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<EmployeeJobPos> orderByComparator)
		throws NoSuchEmployeeJobPosException;

	/**
	* Returns the last employee job pos in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching employee job pos, or <code>null</code> if a matching employee job pos could not be found
	*/
	public EmployeeJobPos fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<EmployeeJobPos> orderByComparator);

	/**
	* Returns the employee job poses before and after the current employee job pos in the ordered set where uuid = &#63;.
	*
	* @param employeeJobPosId the primary key of the current employee job pos
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next employee job pos
	* @throws NoSuchEmployeeJobPosException if a employee job pos with the primary key could not be found
	*/
	public EmployeeJobPos[] findByUuid_PrevAndNext(long employeeJobPosId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<EmployeeJobPos> orderByComparator)
		throws NoSuchEmployeeJobPosException;

	/**
	* Removes all the employee job poses where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of employee job poses where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching employee job poses
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the employee job pos where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchEmployeeJobPosException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching employee job pos
	* @throws NoSuchEmployeeJobPosException if a matching employee job pos could not be found
	*/
	public EmployeeJobPos findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchEmployeeJobPosException;

	/**
	* Returns the employee job pos where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching employee job pos, or <code>null</code> if a matching employee job pos could not be found
	*/
	public EmployeeJobPos fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the employee job pos where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching employee job pos, or <code>null</code> if a matching employee job pos could not be found
	*/
	public EmployeeJobPos fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the employee job pos where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the employee job pos that was removed
	*/
	public EmployeeJobPos removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchEmployeeJobPosException;

	/**
	* Returns the number of employee job poses where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching employee job poses
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the employee job poses where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching employee job poses
	*/
	public java.util.List<EmployeeJobPos> findByUuid_C(java.lang.String uuid,
		long companyId);

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
	public java.util.List<EmployeeJobPos> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

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
	public java.util.List<EmployeeJobPos> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EmployeeJobPos> orderByComparator);

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
	public java.util.List<EmployeeJobPos> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EmployeeJobPos> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first employee job pos in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching employee job pos
	* @throws NoSuchEmployeeJobPosException if a matching employee job pos could not be found
	*/
	public EmployeeJobPos findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<EmployeeJobPos> orderByComparator)
		throws NoSuchEmployeeJobPosException;

	/**
	* Returns the first employee job pos in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching employee job pos, or <code>null</code> if a matching employee job pos could not be found
	*/
	public EmployeeJobPos fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<EmployeeJobPos> orderByComparator);

	/**
	* Returns the last employee job pos in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching employee job pos
	* @throws NoSuchEmployeeJobPosException if a matching employee job pos could not be found
	*/
	public EmployeeJobPos findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<EmployeeJobPos> orderByComparator)
		throws NoSuchEmployeeJobPosException;

	/**
	* Returns the last employee job pos in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching employee job pos, or <code>null</code> if a matching employee job pos could not be found
	*/
	public EmployeeJobPos fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<EmployeeJobPos> orderByComparator);

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
	public EmployeeJobPos[] findByUuid_C_PrevAndNext(long employeeJobPosId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<EmployeeJobPos> orderByComparator)
		throws NoSuchEmployeeJobPosException;

	/**
	* Removes all the employee job poses where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of employee job poses where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching employee job poses
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the employee job poses where employeeId = &#63;.
	*
	* @param employeeId the employee ID
	* @return the matching employee job poses
	*/
	public java.util.List<EmployeeJobPos> findByF_EmployeeId(long employeeId);

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
	public java.util.List<EmployeeJobPos> findByF_EmployeeId(long employeeId,
		int start, int end);

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
	public java.util.List<EmployeeJobPos> findByF_EmployeeId(long employeeId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EmployeeJobPos> orderByComparator);

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
	public java.util.List<EmployeeJobPos> findByF_EmployeeId(long employeeId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EmployeeJobPos> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first employee job pos in the ordered set where employeeId = &#63;.
	*
	* @param employeeId the employee ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching employee job pos
	* @throws NoSuchEmployeeJobPosException if a matching employee job pos could not be found
	*/
	public EmployeeJobPos findByF_EmployeeId_First(long employeeId,
		com.liferay.portal.kernel.util.OrderByComparator<EmployeeJobPos> orderByComparator)
		throws NoSuchEmployeeJobPosException;

	/**
	* Returns the first employee job pos in the ordered set where employeeId = &#63;.
	*
	* @param employeeId the employee ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching employee job pos, or <code>null</code> if a matching employee job pos could not be found
	*/
	public EmployeeJobPos fetchByF_EmployeeId_First(long employeeId,
		com.liferay.portal.kernel.util.OrderByComparator<EmployeeJobPos> orderByComparator);

	/**
	* Returns the last employee job pos in the ordered set where employeeId = &#63;.
	*
	* @param employeeId the employee ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching employee job pos
	* @throws NoSuchEmployeeJobPosException if a matching employee job pos could not be found
	*/
	public EmployeeJobPos findByF_EmployeeId_Last(long employeeId,
		com.liferay.portal.kernel.util.OrderByComparator<EmployeeJobPos> orderByComparator)
		throws NoSuchEmployeeJobPosException;

	/**
	* Returns the last employee job pos in the ordered set where employeeId = &#63;.
	*
	* @param employeeId the employee ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching employee job pos, or <code>null</code> if a matching employee job pos could not be found
	*/
	public EmployeeJobPos fetchByF_EmployeeId_Last(long employeeId,
		com.liferay.portal.kernel.util.OrderByComparator<EmployeeJobPos> orderByComparator);

	/**
	* Returns the employee job poses before and after the current employee job pos in the ordered set where employeeId = &#63;.
	*
	* @param employeeJobPosId the primary key of the current employee job pos
	* @param employeeId the employee ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next employee job pos
	* @throws NoSuchEmployeeJobPosException if a employee job pos with the primary key could not be found
	*/
	public EmployeeJobPos[] findByF_EmployeeId_PrevAndNext(
		long employeeJobPosId, long employeeId,
		com.liferay.portal.kernel.util.OrderByComparator<EmployeeJobPos> orderByComparator)
		throws NoSuchEmployeeJobPosException;

	/**
	* Removes all the employee job poses where employeeId = &#63; from the database.
	*
	* @param employeeId the employee ID
	*/
	public void removeByF_EmployeeId(long employeeId);

	/**
	* Returns the number of employee job poses where employeeId = &#63;.
	*
	* @param employeeId the employee ID
	* @return the number of matching employee job poses
	*/
	public int countByF_EmployeeId(long employeeId);

	/**
	* Caches the employee job pos in the entity cache if it is enabled.
	*
	* @param employeeJobPos the employee job pos
	*/
	public void cacheResult(EmployeeJobPos employeeJobPos);

	/**
	* Caches the employee job poses in the entity cache if it is enabled.
	*
	* @param employeeJobPoses the employee job poses
	*/
	public void cacheResult(java.util.List<EmployeeJobPos> employeeJobPoses);

	/**
	* Creates a new employee job pos with the primary key. Does not add the employee job pos to the database.
	*
	* @param employeeJobPosId the primary key for the new employee job pos
	* @return the new employee job pos
	*/
	public EmployeeJobPos create(long employeeJobPosId);

	/**
	* Removes the employee job pos with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param employeeJobPosId the primary key of the employee job pos
	* @return the employee job pos that was removed
	* @throws NoSuchEmployeeJobPosException if a employee job pos with the primary key could not be found
	*/
	public EmployeeJobPos remove(long employeeJobPosId)
		throws NoSuchEmployeeJobPosException;

	public EmployeeJobPos updateImpl(EmployeeJobPos employeeJobPos);

	/**
	* Returns the employee job pos with the primary key or throws a {@link NoSuchEmployeeJobPosException} if it could not be found.
	*
	* @param employeeJobPosId the primary key of the employee job pos
	* @return the employee job pos
	* @throws NoSuchEmployeeJobPosException if a employee job pos with the primary key could not be found
	*/
	public EmployeeJobPos findByPrimaryKey(long employeeJobPosId)
		throws NoSuchEmployeeJobPosException;

	/**
	* Returns the employee job pos with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param employeeJobPosId the primary key of the employee job pos
	* @return the employee job pos, or <code>null</code> if a employee job pos with the primary key could not be found
	*/
	public EmployeeJobPos fetchByPrimaryKey(long employeeJobPosId);

	@Override
	public java.util.Map<java.io.Serializable, EmployeeJobPos> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the employee job poses.
	*
	* @return the employee job poses
	*/
	public java.util.List<EmployeeJobPos> findAll();

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
	public java.util.List<EmployeeJobPos> findAll(int start, int end);

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
	public java.util.List<EmployeeJobPos> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EmployeeJobPos> orderByComparator);

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
	public java.util.List<EmployeeJobPos> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EmployeeJobPos> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the employee job poses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of employee job poses.
	*
	* @return the number of employee job poses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}