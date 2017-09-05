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

package org.opencps.backend.processmgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.backend.processmgt.model.ServiceProcess;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the service process service. This utility wraps {@link org.opencps.backend.processmgt.service.persistence.impl.ServiceProcessPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see ServiceProcessPersistence
 * @see org.opencps.backend.processmgt.service.persistence.impl.ServiceProcessPersistenceImpl
 * @generated
 */
@ProviderType
public class ServiceProcessUtil {
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
	public static void clearCache(ServiceProcess serviceProcess) {
		getPersistence().clearCache(serviceProcess);
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
	public static List<ServiceProcess> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ServiceProcess> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ServiceProcess> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ServiceProcess> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ServiceProcess update(ServiceProcess serviceProcess) {
		return getPersistence().update(serviceProcess);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ServiceProcess update(ServiceProcess serviceProcess,
		ServiceContext serviceContext) {
		return getPersistence().update(serviceProcess, serviceContext);
	}

	/**
	* Returns all the service processes where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching service processes
	*/
	public static List<ServiceProcess> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the service processes where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of service processes
	* @param end the upper bound of the range of service processes (not inclusive)
	* @return the range of matching service processes
	*/
	public static List<ServiceProcess> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the service processes where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of service processes
	* @param end the upper bound of the range of service processes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching service processes
	*/
	public static List<ServiceProcess> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<ServiceProcess> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the service processes where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of service processes
	* @param end the upper bound of the range of service processes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching service processes
	*/
	public static List<ServiceProcess> findByUuid(java.lang.String uuid,
		int start, int end,
		OrderByComparator<ServiceProcess> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first service process in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service process
	* @throws NoSuchServiceProcessException if a matching service process could not be found
	*/
	public static ServiceProcess findByUuid_First(java.lang.String uuid,
		OrderByComparator<ServiceProcess> orderByComparator)
		throws org.opencps.backend.processmgt.exception.NoSuchServiceProcessException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first service process in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service process, or <code>null</code> if a matching service process could not be found
	*/
	public static ServiceProcess fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<ServiceProcess> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last service process in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service process
	* @throws NoSuchServiceProcessException if a matching service process could not be found
	*/
	public static ServiceProcess findByUuid_Last(java.lang.String uuid,
		OrderByComparator<ServiceProcess> orderByComparator)
		throws org.opencps.backend.processmgt.exception.NoSuchServiceProcessException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last service process in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service process, or <code>null</code> if a matching service process could not be found
	*/
	public static ServiceProcess fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<ServiceProcess> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the service processes before and after the current service process in the ordered set where uuid = &#63;.
	*
	* @param serviceProcessId the primary key of the current service process
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next service process
	* @throws NoSuchServiceProcessException if a service process with the primary key could not be found
	*/
	public static ServiceProcess[] findByUuid_PrevAndNext(
		long serviceProcessId, java.lang.String uuid,
		OrderByComparator<ServiceProcess> orderByComparator)
		throws org.opencps.backend.processmgt.exception.NoSuchServiceProcessException {
		return getPersistence()
				   .findByUuid_PrevAndNext(serviceProcessId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the service processes where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of service processes where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching service processes
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the service process where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchServiceProcessException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching service process
	* @throws NoSuchServiceProcessException if a matching service process could not be found
	*/
	public static ServiceProcess findByUUID_G(java.lang.String uuid,
		long groupId)
		throws org.opencps.backend.processmgt.exception.NoSuchServiceProcessException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the service process where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching service process, or <code>null</code> if a matching service process could not be found
	*/
	public static ServiceProcess fetchByUUID_G(java.lang.String uuid,
		long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the service process where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching service process, or <code>null</code> if a matching service process could not be found
	*/
	public static ServiceProcess fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the service process where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the service process that was removed
	*/
	public static ServiceProcess removeByUUID_G(java.lang.String uuid,
		long groupId)
		throws org.opencps.backend.processmgt.exception.NoSuchServiceProcessException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of service processes where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching service processes
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the service processes where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching service processes
	*/
	public static List<ServiceProcess> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the service processes where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of service processes
	* @param end the upper bound of the range of service processes (not inclusive)
	* @return the range of matching service processes
	*/
	public static List<ServiceProcess> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the service processes where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of service processes
	* @param end the upper bound of the range of service processes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching service processes
	*/
	public static List<ServiceProcess> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<ServiceProcess> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the service processes where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of service processes
	* @param end the upper bound of the range of service processes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching service processes
	*/
	public static List<ServiceProcess> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<ServiceProcess> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first service process in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service process
	* @throws NoSuchServiceProcessException if a matching service process could not be found
	*/
	public static ServiceProcess findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<ServiceProcess> orderByComparator)
		throws org.opencps.backend.processmgt.exception.NoSuchServiceProcessException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first service process in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service process, or <code>null</code> if a matching service process could not be found
	*/
	public static ServiceProcess fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<ServiceProcess> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last service process in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service process
	* @throws NoSuchServiceProcessException if a matching service process could not be found
	*/
	public static ServiceProcess findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<ServiceProcess> orderByComparator)
		throws org.opencps.backend.processmgt.exception.NoSuchServiceProcessException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last service process in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service process, or <code>null</code> if a matching service process could not be found
	*/
	public static ServiceProcess fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<ServiceProcess> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the service processes before and after the current service process in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param serviceProcessId the primary key of the current service process
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next service process
	* @throws NoSuchServiceProcessException if a service process with the primary key could not be found
	*/
	public static ServiceProcess[] findByUuid_C_PrevAndNext(
		long serviceProcessId, java.lang.String uuid, long companyId,
		OrderByComparator<ServiceProcess> orderByComparator)
		throws org.opencps.backend.processmgt.exception.NoSuchServiceProcessException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(serviceProcessId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the service processes where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of service processes where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching service processes
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the service processes where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching service processes
	*/
	public static List<ServiceProcess> findByG_ID(long groupId) {
		return getPersistence().findByG_ID(groupId);
	}

	/**
	* Returns a range of all the service processes where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of service processes
	* @param end the upper bound of the range of service processes (not inclusive)
	* @return the range of matching service processes
	*/
	public static List<ServiceProcess> findByG_ID(long groupId, int start,
		int end) {
		return getPersistence().findByG_ID(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the service processes where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of service processes
	* @param end the upper bound of the range of service processes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching service processes
	*/
	public static List<ServiceProcess> findByG_ID(long groupId, int start,
		int end, OrderByComparator<ServiceProcess> orderByComparator) {
		return getPersistence()
				   .findByG_ID(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the service processes where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of service processes
	* @param end the upper bound of the range of service processes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching service processes
	*/
	public static List<ServiceProcess> findByG_ID(long groupId, int start,
		int end, OrderByComparator<ServiceProcess> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_ID(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first service process in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service process
	* @throws NoSuchServiceProcessException if a matching service process could not be found
	*/
	public static ServiceProcess findByG_ID_First(long groupId,
		OrderByComparator<ServiceProcess> orderByComparator)
		throws org.opencps.backend.processmgt.exception.NoSuchServiceProcessException {
		return getPersistence().findByG_ID_First(groupId, orderByComparator);
	}

	/**
	* Returns the first service process in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service process, or <code>null</code> if a matching service process could not be found
	*/
	public static ServiceProcess fetchByG_ID_First(long groupId,
		OrderByComparator<ServiceProcess> orderByComparator) {
		return getPersistence().fetchByG_ID_First(groupId, orderByComparator);
	}

	/**
	* Returns the last service process in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service process
	* @throws NoSuchServiceProcessException if a matching service process could not be found
	*/
	public static ServiceProcess findByG_ID_Last(long groupId,
		OrderByComparator<ServiceProcess> orderByComparator)
		throws org.opencps.backend.processmgt.exception.NoSuchServiceProcessException {
		return getPersistence().findByG_ID_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last service process in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service process, or <code>null</code> if a matching service process could not be found
	*/
	public static ServiceProcess fetchByG_ID_Last(long groupId,
		OrderByComparator<ServiceProcess> orderByComparator) {
		return getPersistence().fetchByG_ID_Last(groupId, orderByComparator);
	}

	/**
	* Returns the service processes before and after the current service process in the ordered set where groupId = &#63;.
	*
	* @param serviceProcessId the primary key of the current service process
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next service process
	* @throws NoSuchServiceProcessException if a service process with the primary key could not be found
	*/
	public static ServiceProcess[] findByG_ID_PrevAndNext(
		long serviceProcessId, long groupId,
		OrderByComparator<ServiceProcess> orderByComparator)
		throws org.opencps.backend.processmgt.exception.NoSuchServiceProcessException {
		return getPersistence()
				   .findByG_ID_PrevAndNext(serviceProcessId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the service processes where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByG_ID(long groupId) {
		getPersistence().removeByG_ID(groupId);
	}

	/**
	* Returns the number of service processes where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching service processes
	*/
	public static int countByG_ID(long groupId) {
		return getPersistence().countByG_ID(groupId);
	}

	/**
	* Caches the service process in the entity cache if it is enabled.
	*
	* @param serviceProcess the service process
	*/
	public static void cacheResult(ServiceProcess serviceProcess) {
		getPersistence().cacheResult(serviceProcess);
	}

	/**
	* Caches the service processes in the entity cache if it is enabled.
	*
	* @param serviceProcesses the service processes
	*/
	public static void cacheResult(List<ServiceProcess> serviceProcesses) {
		getPersistence().cacheResult(serviceProcesses);
	}

	/**
	* Creates a new service process with the primary key. Does not add the service process to the database.
	*
	* @param serviceProcessId the primary key for the new service process
	* @return the new service process
	*/
	public static ServiceProcess create(long serviceProcessId) {
		return getPersistence().create(serviceProcessId);
	}

	/**
	* Removes the service process with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceProcessId the primary key of the service process
	* @return the service process that was removed
	* @throws NoSuchServiceProcessException if a service process with the primary key could not be found
	*/
	public static ServiceProcess remove(long serviceProcessId)
		throws org.opencps.backend.processmgt.exception.NoSuchServiceProcessException {
		return getPersistence().remove(serviceProcessId);
	}

	public static ServiceProcess updateImpl(ServiceProcess serviceProcess) {
		return getPersistence().updateImpl(serviceProcess);
	}

	/**
	* Returns the service process with the primary key or throws a {@link NoSuchServiceProcessException} if it could not be found.
	*
	* @param serviceProcessId the primary key of the service process
	* @return the service process
	* @throws NoSuchServiceProcessException if a service process with the primary key could not be found
	*/
	public static ServiceProcess findByPrimaryKey(long serviceProcessId)
		throws org.opencps.backend.processmgt.exception.NoSuchServiceProcessException {
		return getPersistence().findByPrimaryKey(serviceProcessId);
	}

	/**
	* Returns the service process with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param serviceProcessId the primary key of the service process
	* @return the service process, or <code>null</code> if a service process with the primary key could not be found
	*/
	public static ServiceProcess fetchByPrimaryKey(long serviceProcessId) {
		return getPersistence().fetchByPrimaryKey(serviceProcessId);
	}

	public static java.util.Map<java.io.Serializable, ServiceProcess> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the service processes.
	*
	* @return the service processes
	*/
	public static List<ServiceProcess> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the service processes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of service processes
	* @param end the upper bound of the range of service processes (not inclusive)
	* @return the range of service processes
	*/
	public static List<ServiceProcess> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the service processes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of service processes
	* @param end the upper bound of the range of service processes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of service processes
	*/
	public static List<ServiceProcess> findAll(int start, int end,
		OrderByComparator<ServiceProcess> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the service processes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of service processes
	* @param end the upper bound of the range of service processes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of service processes
	*/
	public static List<ServiceProcess> findAll(int start, int end,
		OrderByComparator<ServiceProcess> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the service processes from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of service processes.
	*
	* @return the number of service processes
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ServiceProcessPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ServiceProcessPersistence, ServiceProcessPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ServiceProcessPersistence.class);
}