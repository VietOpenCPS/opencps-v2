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

package org.opencps.dossiermgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.dossiermgt.model.ProcessOption;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the process option service. This utility wraps {@link org.opencps.dossiermgt.service.persistence.impl.ProcessOptionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see ProcessOptionPersistence
 * @see org.opencps.dossiermgt.service.persistence.impl.ProcessOptionPersistenceImpl
 * @generated
 */
@ProviderType
public class ProcessOptionUtil {
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
	public static void clearCache(ProcessOption processOption) {
		getPersistence().clearCache(processOption);
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
	public static List<ProcessOption> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ProcessOption> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ProcessOption> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ProcessOption> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ProcessOption update(ProcessOption processOption) {
		return getPersistence().update(processOption);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ProcessOption update(ProcessOption processOption,
		ServiceContext serviceContext) {
		return getPersistence().update(processOption, serviceContext);
	}

	/**
	* Returns all the process options where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching process options
	*/
	public static List<ProcessOption> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the process options where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of process options
	* @param end the upper bound of the range of process options (not inclusive)
	* @return the range of matching process options
	*/
	public static List<ProcessOption> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the process options where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of process options
	* @param end the upper bound of the range of process options (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching process options
	*/
	public static List<ProcessOption> findByUuid(String uuid, int start,
		int end, OrderByComparator<ProcessOption> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the process options where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of process options
	* @param end the upper bound of the range of process options (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching process options
	*/
	public static List<ProcessOption> findByUuid(String uuid, int start,
		int end, OrderByComparator<ProcessOption> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first process option in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process option
	* @throws NoSuchProcessOptionException if a matching process option could not be found
	*/
	public static ProcessOption findByUuid_First(String uuid,
		OrderByComparator<ProcessOption> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessOptionException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first process option in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process option, or <code>null</code> if a matching process option could not be found
	*/
	public static ProcessOption fetchByUuid_First(String uuid,
		OrderByComparator<ProcessOption> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last process option in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process option
	* @throws NoSuchProcessOptionException if a matching process option could not be found
	*/
	public static ProcessOption findByUuid_Last(String uuid,
		OrderByComparator<ProcessOption> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessOptionException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last process option in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process option, or <code>null</code> if a matching process option could not be found
	*/
	public static ProcessOption fetchByUuid_Last(String uuid,
		OrderByComparator<ProcessOption> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the process options before and after the current process option in the ordered set where uuid = &#63;.
	*
	* @param processOptionId the primary key of the current process option
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process option
	* @throws NoSuchProcessOptionException if a process option with the primary key could not be found
	*/
	public static ProcessOption[] findByUuid_PrevAndNext(long processOptionId,
		String uuid, OrderByComparator<ProcessOption> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessOptionException {
		return getPersistence()
				   .findByUuid_PrevAndNext(processOptionId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the process options where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of process options where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching process options
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the process option where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchProcessOptionException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching process option
	* @throws NoSuchProcessOptionException if a matching process option could not be found
	*/
	public static ProcessOption findByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchProcessOptionException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the process option where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching process option, or <code>null</code> if a matching process option could not be found
	*/
	public static ProcessOption fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the process option where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching process option, or <code>null</code> if a matching process option could not be found
	*/
	public static ProcessOption fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the process option where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the process option that was removed
	*/
	public static ProcessOption removeByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchProcessOptionException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of process options where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching process options
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the process options where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching process options
	*/
	public static List<ProcessOption> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the process options where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of process options
	* @param end the upper bound of the range of process options (not inclusive)
	* @return the range of matching process options
	*/
	public static List<ProcessOption> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the process options where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of process options
	* @param end the upper bound of the range of process options (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching process options
	*/
	public static List<ProcessOption> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ProcessOption> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the process options where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of process options
	* @param end the upper bound of the range of process options (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching process options
	*/
	public static List<ProcessOption> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ProcessOption> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first process option in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process option
	* @throws NoSuchProcessOptionException if a matching process option could not be found
	*/
	public static ProcessOption findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ProcessOption> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessOptionException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first process option in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process option, or <code>null</code> if a matching process option could not be found
	*/
	public static ProcessOption fetchByUuid_C_First(String uuid,
		long companyId, OrderByComparator<ProcessOption> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last process option in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process option
	* @throws NoSuchProcessOptionException if a matching process option could not be found
	*/
	public static ProcessOption findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ProcessOption> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessOptionException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last process option in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process option, or <code>null</code> if a matching process option could not be found
	*/
	public static ProcessOption fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ProcessOption> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the process options before and after the current process option in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param processOptionId the primary key of the current process option
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process option
	* @throws NoSuchProcessOptionException if a process option with the primary key could not be found
	*/
	public static ProcessOption[] findByUuid_C_PrevAndNext(
		long processOptionId, String uuid, long companyId,
		OrderByComparator<ProcessOption> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessOptionException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(processOptionId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the process options where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of process options where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching process options
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the process options where serviceConfigId = &#63;.
	*
	* @param serviceConfigId the service config ID
	* @return the matching process options
	*/
	public static List<ProcessOption> findBySC_ID(long serviceConfigId) {
		return getPersistence().findBySC_ID(serviceConfigId);
	}

	/**
	* Returns a range of all the process options where serviceConfigId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param serviceConfigId the service config ID
	* @param start the lower bound of the range of process options
	* @param end the upper bound of the range of process options (not inclusive)
	* @return the range of matching process options
	*/
	public static List<ProcessOption> findBySC_ID(long serviceConfigId,
		int start, int end) {
		return getPersistence().findBySC_ID(serviceConfigId, start, end);
	}

	/**
	* Returns an ordered range of all the process options where serviceConfigId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param serviceConfigId the service config ID
	* @param start the lower bound of the range of process options
	* @param end the upper bound of the range of process options (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching process options
	*/
	public static List<ProcessOption> findBySC_ID(long serviceConfigId,
		int start, int end, OrderByComparator<ProcessOption> orderByComparator) {
		return getPersistence()
				   .findBySC_ID(serviceConfigId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the process options where serviceConfigId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param serviceConfigId the service config ID
	* @param start the lower bound of the range of process options
	* @param end the upper bound of the range of process options (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching process options
	*/
	public static List<ProcessOption> findBySC_ID(long serviceConfigId,
		int start, int end, OrderByComparator<ProcessOption> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findBySC_ID(serviceConfigId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first process option in the ordered set where serviceConfigId = &#63;.
	*
	* @param serviceConfigId the service config ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process option
	* @throws NoSuchProcessOptionException if a matching process option could not be found
	*/
	public static ProcessOption findBySC_ID_First(long serviceConfigId,
		OrderByComparator<ProcessOption> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessOptionException {
		return getPersistence()
				   .findBySC_ID_First(serviceConfigId, orderByComparator);
	}

	/**
	* Returns the first process option in the ordered set where serviceConfigId = &#63;.
	*
	* @param serviceConfigId the service config ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process option, or <code>null</code> if a matching process option could not be found
	*/
	public static ProcessOption fetchBySC_ID_First(long serviceConfigId,
		OrderByComparator<ProcessOption> orderByComparator) {
		return getPersistence()
				   .fetchBySC_ID_First(serviceConfigId, orderByComparator);
	}

	/**
	* Returns the last process option in the ordered set where serviceConfigId = &#63;.
	*
	* @param serviceConfigId the service config ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process option
	* @throws NoSuchProcessOptionException if a matching process option could not be found
	*/
	public static ProcessOption findBySC_ID_Last(long serviceConfigId,
		OrderByComparator<ProcessOption> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessOptionException {
		return getPersistence()
				   .findBySC_ID_Last(serviceConfigId, orderByComparator);
	}

	/**
	* Returns the last process option in the ordered set where serviceConfigId = &#63;.
	*
	* @param serviceConfigId the service config ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process option, or <code>null</code> if a matching process option could not be found
	*/
	public static ProcessOption fetchBySC_ID_Last(long serviceConfigId,
		OrderByComparator<ProcessOption> orderByComparator) {
		return getPersistence()
				   .fetchBySC_ID_Last(serviceConfigId, orderByComparator);
	}

	/**
	* Returns the process options before and after the current process option in the ordered set where serviceConfigId = &#63;.
	*
	* @param processOptionId the primary key of the current process option
	* @param serviceConfigId the service config ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process option
	* @throws NoSuchProcessOptionException if a process option with the primary key could not be found
	*/
	public static ProcessOption[] findBySC_ID_PrevAndNext(
		long processOptionId, long serviceConfigId,
		OrderByComparator<ProcessOption> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessOptionException {
		return getPersistence()
				   .findBySC_ID_PrevAndNext(processOptionId, serviceConfigId,
			orderByComparator);
	}

	/**
	* Removes all the process options where serviceConfigId = &#63; from the database.
	*
	* @param serviceConfigId the service config ID
	*/
	public static void removeBySC_ID(long serviceConfigId) {
		getPersistence().removeBySC_ID(serviceConfigId);
	}

	/**
	* Returns the number of process options where serviceConfigId = &#63;.
	*
	* @param serviceConfigId the service config ID
	* @return the number of matching process options
	*/
	public static int countBySC_ID(long serviceConfigId) {
		return getPersistence().countBySC_ID(serviceConfigId);
	}

	/**
	* Returns the process option where serviceConfigId = &#63; and optionOrder = &#63; or throws a {@link NoSuchProcessOptionException} if it could not be found.
	*
	* @param serviceConfigId the service config ID
	* @param optionOrder the option order
	* @return the matching process option
	* @throws NoSuchProcessOptionException if a matching process option could not be found
	*/
	public static ProcessOption findBySC_ID_OP(long serviceConfigId,
		int optionOrder)
		throws org.opencps.dossiermgt.exception.NoSuchProcessOptionException {
		return getPersistence().findBySC_ID_OP(serviceConfigId, optionOrder);
	}

	/**
	* Returns the process option where serviceConfigId = &#63; and optionOrder = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param serviceConfigId the service config ID
	* @param optionOrder the option order
	* @return the matching process option, or <code>null</code> if a matching process option could not be found
	*/
	public static ProcessOption fetchBySC_ID_OP(long serviceConfigId,
		int optionOrder) {
		return getPersistence().fetchBySC_ID_OP(serviceConfigId, optionOrder);
	}

	/**
	* Returns the process option where serviceConfigId = &#63; and optionOrder = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param serviceConfigId the service config ID
	* @param optionOrder the option order
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching process option, or <code>null</code> if a matching process option could not be found
	*/
	public static ProcessOption fetchBySC_ID_OP(long serviceConfigId,
		int optionOrder, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchBySC_ID_OP(serviceConfigId, optionOrder,
			retrieveFromCache);
	}

	/**
	* Removes the process option where serviceConfigId = &#63; and optionOrder = &#63; from the database.
	*
	* @param serviceConfigId the service config ID
	* @param optionOrder the option order
	* @return the process option that was removed
	*/
	public static ProcessOption removeBySC_ID_OP(long serviceConfigId,
		int optionOrder)
		throws org.opencps.dossiermgt.exception.NoSuchProcessOptionException {
		return getPersistence().removeBySC_ID_OP(serviceConfigId, optionOrder);
	}

	/**
	* Returns the number of process options where serviceConfigId = &#63; and optionOrder = &#63;.
	*
	* @param serviceConfigId the service config ID
	* @param optionOrder the option order
	* @return the number of matching process options
	*/
	public static int countBySC_ID_OP(long serviceConfigId, int optionOrder) {
		return getPersistence().countBySC_ID_OP(serviceConfigId, optionOrder);
	}

	/**
	* Returns the process option where serviceConfigId = &#63; and dossierTemplateId = &#63; or throws a {@link NoSuchProcessOptionException} if it could not be found.
	*
	* @param serviceConfigId the service config ID
	* @param dossierTemplateId the dossier template ID
	* @return the matching process option
	* @throws NoSuchProcessOptionException if a matching process option could not be found
	*/
	public static ProcessOption findBySC_DT(long serviceConfigId,
		long dossierTemplateId)
		throws org.opencps.dossiermgt.exception.NoSuchProcessOptionException {
		return getPersistence().findBySC_DT(serviceConfigId, dossierTemplateId);
	}

	/**
	* Returns the process option where serviceConfigId = &#63; and dossierTemplateId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param serviceConfigId the service config ID
	* @param dossierTemplateId the dossier template ID
	* @return the matching process option, or <code>null</code> if a matching process option could not be found
	*/
	public static ProcessOption fetchBySC_DT(long serviceConfigId,
		long dossierTemplateId) {
		return getPersistence().fetchBySC_DT(serviceConfigId, dossierTemplateId);
	}

	/**
	* Returns the process option where serviceConfigId = &#63; and dossierTemplateId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param serviceConfigId the service config ID
	* @param dossierTemplateId the dossier template ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching process option, or <code>null</code> if a matching process option could not be found
	*/
	public static ProcessOption fetchBySC_DT(long serviceConfigId,
		long dossierTemplateId, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchBySC_DT(serviceConfigId, dossierTemplateId,
			retrieveFromCache);
	}

	/**
	* Removes the process option where serviceConfigId = &#63; and dossierTemplateId = &#63; from the database.
	*
	* @param serviceConfigId the service config ID
	* @param dossierTemplateId the dossier template ID
	* @return the process option that was removed
	*/
	public static ProcessOption removeBySC_DT(long serviceConfigId,
		long dossierTemplateId)
		throws org.opencps.dossiermgt.exception.NoSuchProcessOptionException {
		return getPersistence().removeBySC_DT(serviceConfigId, dossierTemplateId);
	}

	/**
	* Returns the number of process options where serviceConfigId = &#63; and dossierTemplateId = &#63;.
	*
	* @param serviceConfigId the service config ID
	* @param dossierTemplateId the dossier template ID
	* @return the number of matching process options
	*/
	public static int countBySC_DT(long serviceConfigId, long dossierTemplateId) {
		return getPersistence().countBySC_DT(serviceConfigId, dossierTemplateId);
	}

	/**
	* Caches the process option in the entity cache if it is enabled.
	*
	* @param processOption the process option
	*/
	public static void cacheResult(ProcessOption processOption) {
		getPersistence().cacheResult(processOption);
	}

	/**
	* Caches the process options in the entity cache if it is enabled.
	*
	* @param processOptions the process options
	*/
	public static void cacheResult(List<ProcessOption> processOptions) {
		getPersistence().cacheResult(processOptions);
	}

	/**
	* Creates a new process option with the primary key. Does not add the process option to the database.
	*
	* @param processOptionId the primary key for the new process option
	* @return the new process option
	*/
	public static ProcessOption create(long processOptionId) {
		return getPersistence().create(processOptionId);
	}

	/**
	* Removes the process option with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param processOptionId the primary key of the process option
	* @return the process option that was removed
	* @throws NoSuchProcessOptionException if a process option with the primary key could not be found
	*/
	public static ProcessOption remove(long processOptionId)
		throws org.opencps.dossiermgt.exception.NoSuchProcessOptionException {
		return getPersistence().remove(processOptionId);
	}

	public static ProcessOption updateImpl(ProcessOption processOption) {
		return getPersistence().updateImpl(processOption);
	}

	/**
	* Returns the process option with the primary key or throws a {@link NoSuchProcessOptionException} if it could not be found.
	*
	* @param processOptionId the primary key of the process option
	* @return the process option
	* @throws NoSuchProcessOptionException if a process option with the primary key could not be found
	*/
	public static ProcessOption findByPrimaryKey(long processOptionId)
		throws org.opencps.dossiermgt.exception.NoSuchProcessOptionException {
		return getPersistence().findByPrimaryKey(processOptionId);
	}

	/**
	* Returns the process option with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param processOptionId the primary key of the process option
	* @return the process option, or <code>null</code> if a process option with the primary key could not be found
	*/
	public static ProcessOption fetchByPrimaryKey(long processOptionId) {
		return getPersistence().fetchByPrimaryKey(processOptionId);
	}

	public static java.util.Map<java.io.Serializable, ProcessOption> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the process options.
	*
	* @return the process options
	*/
	public static List<ProcessOption> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the process options.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of process options
	* @param end the upper bound of the range of process options (not inclusive)
	* @return the range of process options
	*/
	public static List<ProcessOption> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the process options.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of process options
	* @param end the upper bound of the range of process options (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of process options
	*/
	public static List<ProcessOption> findAll(int start, int end,
		OrderByComparator<ProcessOption> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the process options.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of process options
	* @param end the upper bound of the range of process options (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of process options
	*/
	public static List<ProcessOption> findAll(int start, int end,
		OrderByComparator<ProcessOption> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the process options from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of process options.
	*
	* @return the number of process options
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ProcessOptionPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ProcessOptionPersistence, ProcessOptionPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ProcessOptionPersistence.class);

		ServiceTracker<ProcessOptionPersistence, ProcessOptionPersistence> serviceTracker =
			new ServiceTracker<ProcessOptionPersistence, ProcessOptionPersistence>(bundle.getBundleContext(),
				ProcessOptionPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}