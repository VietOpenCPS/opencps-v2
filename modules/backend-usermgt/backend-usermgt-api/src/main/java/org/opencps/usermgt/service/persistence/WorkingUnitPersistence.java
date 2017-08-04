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

import org.opencps.usermgt.exception.NoSuchWorkingUnitException;
import org.opencps.usermgt.model.WorkingUnit;

/**
 * The persistence interface for the working unit service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Binhth
 * @see org.mobilink.backend.usermgt.service.persistence.impl.WorkingUnitPersistenceImpl
 * @see WorkingUnitUtil
 * @generated
 */
@ProviderType
public interface WorkingUnitPersistence extends BasePersistence<WorkingUnit> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WorkingUnitUtil} to access the working unit persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the working units where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching working units
	*/
	public java.util.List<WorkingUnit> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the working units where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkingUnitModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of working units
	* @param end the upper bound of the range of working units (not inclusive)
	* @return the range of matching working units
	*/
	public java.util.List<WorkingUnit> findByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the working units where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkingUnitModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of working units
	* @param end the upper bound of the range of working units (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching working units
	*/
	public java.util.List<WorkingUnit> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WorkingUnit> orderByComparator);

	/**
	* Returns an ordered range of all the working units where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkingUnitModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of working units
	* @param end the upper bound of the range of working units (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching working units
	*/
	public java.util.List<WorkingUnit> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WorkingUnit> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first working unit in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching working unit
	* @throws NoSuchWorkingUnitException if a matching working unit could not be found
	*/
	public WorkingUnit findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<WorkingUnit> orderByComparator)
		throws NoSuchWorkingUnitException;

	/**
	* Returns the first working unit in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching working unit, or <code>null</code> if a matching working unit could not be found
	*/
	public WorkingUnit fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<WorkingUnit> orderByComparator);

	/**
	* Returns the last working unit in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching working unit
	* @throws NoSuchWorkingUnitException if a matching working unit could not be found
	*/
	public WorkingUnit findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<WorkingUnit> orderByComparator)
		throws NoSuchWorkingUnitException;

	/**
	* Returns the last working unit in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching working unit, or <code>null</code> if a matching working unit could not be found
	*/
	public WorkingUnit fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<WorkingUnit> orderByComparator);

	/**
	* Returns the working units before and after the current working unit in the ordered set where uuid = &#63;.
	*
	* @param workingUnitId the primary key of the current working unit
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next working unit
	* @throws NoSuchWorkingUnitException if a working unit with the primary key could not be found
	*/
	public WorkingUnit[] findByUuid_PrevAndNext(long workingUnitId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<WorkingUnit> orderByComparator)
		throws NoSuchWorkingUnitException;

	/**
	* Removes all the working units where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of working units where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching working units
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the working unit where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchWorkingUnitException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching working unit
	* @throws NoSuchWorkingUnitException if a matching working unit could not be found
	*/
	public WorkingUnit findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchWorkingUnitException;

	/**
	* Returns the working unit where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching working unit, or <code>null</code> if a matching working unit could not be found
	*/
	public WorkingUnit fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the working unit where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching working unit, or <code>null</code> if a matching working unit could not be found
	*/
	public WorkingUnit fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the working unit where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the working unit that was removed
	*/
	public WorkingUnit removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchWorkingUnitException;

	/**
	* Returns the number of working units where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching working units
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the working units where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching working units
	*/
	public java.util.List<WorkingUnit> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the working units where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkingUnitModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of working units
	* @param end the upper bound of the range of working units (not inclusive)
	* @return the range of matching working units
	*/
	public java.util.List<WorkingUnit> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the working units where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkingUnitModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of working units
	* @param end the upper bound of the range of working units (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching working units
	*/
	public java.util.List<WorkingUnit> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WorkingUnit> orderByComparator);

	/**
	* Returns an ordered range of all the working units where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkingUnitModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of working units
	* @param end the upper bound of the range of working units (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching working units
	*/
	public java.util.List<WorkingUnit> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WorkingUnit> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first working unit in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching working unit
	* @throws NoSuchWorkingUnitException if a matching working unit could not be found
	*/
	public WorkingUnit findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WorkingUnit> orderByComparator)
		throws NoSuchWorkingUnitException;

	/**
	* Returns the first working unit in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching working unit, or <code>null</code> if a matching working unit could not be found
	*/
	public WorkingUnit fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WorkingUnit> orderByComparator);

	/**
	* Returns the last working unit in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching working unit
	* @throws NoSuchWorkingUnitException if a matching working unit could not be found
	*/
	public WorkingUnit findByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WorkingUnit> orderByComparator)
		throws NoSuchWorkingUnitException;

	/**
	* Returns the last working unit in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching working unit, or <code>null</code> if a matching working unit could not be found
	*/
	public WorkingUnit fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WorkingUnit> orderByComparator);

	/**
	* Returns the working units before and after the current working unit in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param workingUnitId the primary key of the current working unit
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next working unit
	* @throws NoSuchWorkingUnitException if a working unit with the primary key could not be found
	*/
	public WorkingUnit[] findByUuid_C_PrevAndNext(long workingUnitId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WorkingUnit> orderByComparator)
		throws NoSuchWorkingUnitException;

	/**
	* Removes all the working units where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of working units where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching working units
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Caches the working unit in the entity cache if it is enabled.
	*
	* @param workingUnit the working unit
	*/
	public void cacheResult(WorkingUnit workingUnit);

	/**
	* Caches the working units in the entity cache if it is enabled.
	*
	* @param workingUnits the working units
	*/
	public void cacheResult(java.util.List<WorkingUnit> workingUnits);

	/**
	* Creates a new working unit with the primary key. Does not add the working unit to the database.
	*
	* @param workingUnitId the primary key for the new working unit
	* @return the new working unit
	*/
	public WorkingUnit create(long workingUnitId);

	/**
	* Removes the working unit with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param workingUnitId the primary key of the working unit
	* @return the working unit that was removed
	* @throws NoSuchWorkingUnitException if a working unit with the primary key could not be found
	*/
	public WorkingUnit remove(long workingUnitId)
		throws NoSuchWorkingUnitException;

	public WorkingUnit updateImpl(WorkingUnit workingUnit);

	/**
	* Returns the working unit with the primary key or throws a {@link NoSuchWorkingUnitException} if it could not be found.
	*
	* @param workingUnitId the primary key of the working unit
	* @return the working unit
	* @throws NoSuchWorkingUnitException if a working unit with the primary key could not be found
	*/
	public WorkingUnit findByPrimaryKey(long workingUnitId)
		throws NoSuchWorkingUnitException;

	/**
	* Returns the working unit with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param workingUnitId the primary key of the working unit
	* @return the working unit, or <code>null</code> if a working unit with the primary key could not be found
	*/
	public WorkingUnit fetchByPrimaryKey(long workingUnitId);

	@Override
	public java.util.Map<java.io.Serializable, WorkingUnit> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the working units.
	*
	* @return the working units
	*/
	public java.util.List<WorkingUnit> findAll();

	/**
	* Returns a range of all the working units.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkingUnitModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of working units
	* @param end the upper bound of the range of working units (not inclusive)
	* @return the range of working units
	*/
	public java.util.List<WorkingUnit> findAll(int start, int end);

	/**
	* Returns an ordered range of all the working units.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkingUnitModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of working units
	* @param end the upper bound of the range of working units (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of working units
	*/
	public java.util.List<WorkingUnit> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WorkingUnit> orderByComparator);

	/**
	* Returns an ordered range of all the working units.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkingUnitModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of working units
	* @param end the upper bound of the range of working units (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of working units
	*/
	public java.util.List<WorkingUnit> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WorkingUnit> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the working units from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of working units.
	*
	* @return the number of working units
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}