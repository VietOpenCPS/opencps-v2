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

import org.opencps.usermgt.model.WorkingUnit;
import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the working unit service. This utility wraps {@link org.mobilink.backend.usermgt.service.persistence.impl.WorkingUnitPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Binhth
 * @see WorkingUnitPersistence
 * @see org.mobilink.backend.usermgt.service.persistence.impl.WorkingUnitPersistenceImpl
 * @generated
 */
@ProviderType
public class WorkingUnitUtil {
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
	public static void clearCache(WorkingUnit workingUnit) {
		getPersistence().clearCache(workingUnit);
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
	public static List<WorkingUnit> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<WorkingUnit> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<WorkingUnit> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<WorkingUnit> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static WorkingUnit update(WorkingUnit workingUnit) {
		return getPersistence().update(workingUnit);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static WorkingUnit update(WorkingUnit workingUnit,
		ServiceContext serviceContext) {
		return getPersistence().update(workingUnit, serviceContext);
	}

	/**
	* Returns all the working units where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching working units
	*/
	public static List<WorkingUnit> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

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
	public static List<WorkingUnit> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static List<WorkingUnit> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<WorkingUnit> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

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
	public static List<WorkingUnit> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<WorkingUnit> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first working unit in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching working unit
	* @throws NoSuchWorkingUnitException if a matching working unit could not be found
	*/
	public static WorkingUnit findByUuid_First(java.lang.String uuid,
		OrderByComparator<WorkingUnit> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchWorkingUnitException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first working unit in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching working unit, or <code>null</code> if a matching working unit could not be found
	*/
	public static WorkingUnit fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<WorkingUnit> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last working unit in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching working unit
	* @throws NoSuchWorkingUnitException if a matching working unit could not be found
	*/
	public static WorkingUnit findByUuid_Last(java.lang.String uuid,
		OrderByComparator<WorkingUnit> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchWorkingUnitException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last working unit in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching working unit, or <code>null</code> if a matching working unit could not be found
	*/
	public static WorkingUnit fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<WorkingUnit> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the working units before and after the current working unit in the ordered set where uuid = &#63;.
	*
	* @param workingUnitId the primary key of the current working unit
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next working unit
	* @throws NoSuchWorkingUnitException if a working unit with the primary key could not be found
	*/
	public static WorkingUnit[] findByUuid_PrevAndNext(long workingUnitId,
		java.lang.String uuid, OrderByComparator<WorkingUnit> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchWorkingUnitException {
		return getPersistence()
				   .findByUuid_PrevAndNext(workingUnitId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the working units where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of working units where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching working units
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the working unit where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchWorkingUnitException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching working unit
	* @throws NoSuchWorkingUnitException if a matching working unit could not be found
	*/
	public static WorkingUnit findByUUID_G(java.lang.String uuid, long groupId)
		throws org.opencps.usermgt.exception.NoSuchWorkingUnitException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the working unit where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching working unit, or <code>null</code> if a matching working unit could not be found
	*/
	public static WorkingUnit fetchByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the working unit where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching working unit, or <code>null</code> if a matching working unit could not be found
	*/
	public static WorkingUnit fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the working unit where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the working unit that was removed
	*/
	public static WorkingUnit removeByUUID_G(java.lang.String uuid, long groupId)
		throws org.opencps.usermgt.exception.NoSuchWorkingUnitException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of working units where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching working units
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the working units where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching working units
	*/
	public static List<WorkingUnit> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

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
	public static List<WorkingUnit> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

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
	public static List<WorkingUnit> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<WorkingUnit> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

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
	public static List<WorkingUnit> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<WorkingUnit> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first working unit in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching working unit
	* @throws NoSuchWorkingUnitException if a matching working unit could not be found
	*/
	public static WorkingUnit findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<WorkingUnit> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchWorkingUnitException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first working unit in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching working unit, or <code>null</code> if a matching working unit could not be found
	*/
	public static WorkingUnit fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<WorkingUnit> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last working unit in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching working unit
	* @throws NoSuchWorkingUnitException if a matching working unit could not be found
	*/
	public static WorkingUnit findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<WorkingUnit> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchWorkingUnitException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last working unit in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching working unit, or <code>null</code> if a matching working unit could not be found
	*/
	public static WorkingUnit fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<WorkingUnit> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

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
	public static WorkingUnit[] findByUuid_C_PrevAndNext(long workingUnitId,
		java.lang.String uuid, long companyId,
		OrderByComparator<WorkingUnit> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchWorkingUnitException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(workingUnitId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the working units where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of working units where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching working units
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Caches the working unit in the entity cache if it is enabled.
	*
	* @param workingUnit the working unit
	*/
	public static void cacheResult(WorkingUnit workingUnit) {
		getPersistence().cacheResult(workingUnit);
	}

	/**
	* Caches the working units in the entity cache if it is enabled.
	*
	* @param workingUnits the working units
	*/
	public static void cacheResult(List<WorkingUnit> workingUnits) {
		getPersistence().cacheResult(workingUnits);
	}

	/**
	* Creates a new working unit with the primary key. Does not add the working unit to the database.
	*
	* @param workingUnitId the primary key for the new working unit
	* @return the new working unit
	*/
	public static WorkingUnit create(long workingUnitId) {
		return getPersistence().create(workingUnitId);
	}

	/**
	* Removes the working unit with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param workingUnitId the primary key of the working unit
	* @return the working unit that was removed
	* @throws NoSuchWorkingUnitException if a working unit with the primary key could not be found
	*/
	public static WorkingUnit remove(long workingUnitId)
		throws org.opencps.usermgt.exception.NoSuchWorkingUnitException {
		return getPersistence().remove(workingUnitId);
	}

	public static WorkingUnit updateImpl(WorkingUnit workingUnit) {
		return getPersistence().updateImpl(workingUnit);
	}

	/**
	* Returns the working unit with the primary key or throws a {@link NoSuchWorkingUnitException} if it could not be found.
	*
	* @param workingUnitId the primary key of the working unit
	* @return the working unit
	* @throws NoSuchWorkingUnitException if a working unit with the primary key could not be found
	*/
	public static WorkingUnit findByPrimaryKey(long workingUnitId)
		throws org.opencps.usermgt.exception.NoSuchWorkingUnitException {
		return getPersistence().findByPrimaryKey(workingUnitId);
	}

	/**
	* Returns the working unit with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param workingUnitId the primary key of the working unit
	* @return the working unit, or <code>null</code> if a working unit with the primary key could not be found
	*/
	public static WorkingUnit fetchByPrimaryKey(long workingUnitId) {
		return getPersistence().fetchByPrimaryKey(workingUnitId);
	}

	public static java.util.Map<java.io.Serializable, WorkingUnit> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the working units.
	*
	* @return the working units
	*/
	public static List<WorkingUnit> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<WorkingUnit> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<WorkingUnit> findAll(int start, int end,
		OrderByComparator<WorkingUnit> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<WorkingUnit> findAll(int start, int end,
		OrderByComparator<WorkingUnit> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the working units from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of working units.
	*
	* @return the number of working units
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static WorkingUnitPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<WorkingUnitPersistence, WorkingUnitPersistence> _serviceTracker =
		ServiceTrackerFactory.open(WorkingUnitPersistence.class);
}