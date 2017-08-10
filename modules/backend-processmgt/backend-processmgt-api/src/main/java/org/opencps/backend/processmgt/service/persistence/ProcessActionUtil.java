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

import org.opencps.backend.processmgt.model.ProcessAction;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the process action service. This utility wraps {@link org.opencps.backend.processmgt.service.persistence.impl.ProcessActionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see ProcessActionPersistence
 * @see org.opencps.backend.processmgt.service.persistence.impl.ProcessActionPersistenceImpl
 * @generated
 */
@ProviderType
public class ProcessActionUtil {
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
	public static void clearCache(ProcessAction processAction) {
		getPersistence().clearCache(processAction);
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
	public static List<ProcessAction> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ProcessAction> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ProcessAction> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ProcessAction> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ProcessAction update(ProcessAction processAction) {
		return getPersistence().update(processAction);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ProcessAction update(ProcessAction processAction,
		ServiceContext serviceContext) {
		return getPersistence().update(processAction, serviceContext);
	}

	/**
	* Returns all the process actions where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching process actions
	*/
	public static List<ProcessAction> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the process actions where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of process actions
	* @param end the upper bound of the range of process actions (not inclusive)
	* @return the range of matching process actions
	*/
	public static List<ProcessAction> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the process actions where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of process actions
	* @param end the upper bound of the range of process actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching process actions
	*/
	public static List<ProcessAction> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<ProcessAction> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the process actions where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of process actions
	* @param end the upper bound of the range of process actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching process actions
	*/
	public static List<ProcessAction> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<ProcessAction> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first process action in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public static ProcessAction findByUuid_First(java.lang.String uuid,
		OrderByComparator<ProcessAction> orderByComparator)
		throws org.opencps.backend.processmgt.exception.NoSuchProcessActionException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first process action in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public static ProcessAction fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<ProcessAction> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last process action in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public static ProcessAction findByUuid_Last(java.lang.String uuid,
		OrderByComparator<ProcessAction> orderByComparator)
		throws org.opencps.backend.processmgt.exception.NoSuchProcessActionException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last process action in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public static ProcessAction fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<ProcessAction> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the process actions before and after the current process action in the ordered set where uuid = &#63;.
	*
	* @param processActionId the primary key of the current process action
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process action
	* @throws NoSuchProcessActionException if a process action with the primary key could not be found
	*/
	public static ProcessAction[] findByUuid_PrevAndNext(long processActionId,
		java.lang.String uuid,
		OrderByComparator<ProcessAction> orderByComparator)
		throws org.opencps.backend.processmgt.exception.NoSuchProcessActionException {
		return getPersistence()
				   .findByUuid_PrevAndNext(processActionId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the process actions where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of process actions where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching process actions
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the process action where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchProcessActionException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public static ProcessAction findByUUID_G(java.lang.String uuid, long groupId)
		throws org.opencps.backend.processmgt.exception.NoSuchProcessActionException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the process action where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public static ProcessAction fetchByUUID_G(java.lang.String uuid,
		long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the process action where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public static ProcessAction fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the process action where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the process action that was removed
	*/
	public static ProcessAction removeByUUID_G(java.lang.String uuid,
		long groupId)
		throws org.opencps.backend.processmgt.exception.NoSuchProcessActionException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of process actions where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching process actions
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the process actions where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching process actions
	*/
	public static List<ProcessAction> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the process actions where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of process actions
	* @param end the upper bound of the range of process actions (not inclusive)
	* @return the range of matching process actions
	*/
	public static List<ProcessAction> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the process actions where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of process actions
	* @param end the upper bound of the range of process actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching process actions
	*/
	public static List<ProcessAction> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<ProcessAction> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the process actions where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of process actions
	* @param end the upper bound of the range of process actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching process actions
	*/
	public static List<ProcessAction> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<ProcessAction> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first process action in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public static ProcessAction findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<ProcessAction> orderByComparator)
		throws org.opencps.backend.processmgt.exception.NoSuchProcessActionException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first process action in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public static ProcessAction fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<ProcessAction> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last process action in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public static ProcessAction findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<ProcessAction> orderByComparator)
		throws org.opencps.backend.processmgt.exception.NoSuchProcessActionException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last process action in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public static ProcessAction fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<ProcessAction> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the process actions before and after the current process action in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param processActionId the primary key of the current process action
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process action
	* @throws NoSuchProcessActionException if a process action with the primary key could not be found
	*/
	public static ProcessAction[] findByUuid_C_PrevAndNext(
		long processActionId, java.lang.String uuid, long companyId,
		OrderByComparator<ProcessAction> orderByComparator)
		throws org.opencps.backend.processmgt.exception.NoSuchProcessActionException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(processActionId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the process actions where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of process actions where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching process actions
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the process actions where serviceProcessId = &#63;.
	*
	* @param serviceProcessId the service process ID
	* @return the matching process actions
	*/
	public static List<ProcessAction> findByS_P_ID(long serviceProcessId) {
		return getPersistence().findByS_P_ID(serviceProcessId);
	}

	/**
	* Returns a range of all the process actions where serviceProcessId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param serviceProcessId the service process ID
	* @param start the lower bound of the range of process actions
	* @param end the upper bound of the range of process actions (not inclusive)
	* @return the range of matching process actions
	*/
	public static List<ProcessAction> findByS_P_ID(long serviceProcessId,
		int start, int end) {
		return getPersistence().findByS_P_ID(serviceProcessId, start, end);
	}

	/**
	* Returns an ordered range of all the process actions where serviceProcessId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param serviceProcessId the service process ID
	* @param start the lower bound of the range of process actions
	* @param end the upper bound of the range of process actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching process actions
	*/
	public static List<ProcessAction> findByS_P_ID(long serviceProcessId,
		int start, int end, OrderByComparator<ProcessAction> orderByComparator) {
		return getPersistence()
				   .findByS_P_ID(serviceProcessId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the process actions where serviceProcessId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param serviceProcessId the service process ID
	* @param start the lower bound of the range of process actions
	* @param end the upper bound of the range of process actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching process actions
	*/
	public static List<ProcessAction> findByS_P_ID(long serviceProcessId,
		int start, int end, OrderByComparator<ProcessAction> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByS_P_ID(serviceProcessId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first process action in the ordered set where serviceProcessId = &#63;.
	*
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public static ProcessAction findByS_P_ID_First(long serviceProcessId,
		OrderByComparator<ProcessAction> orderByComparator)
		throws org.opencps.backend.processmgt.exception.NoSuchProcessActionException {
		return getPersistence()
				   .findByS_P_ID_First(serviceProcessId, orderByComparator);
	}

	/**
	* Returns the first process action in the ordered set where serviceProcessId = &#63;.
	*
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public static ProcessAction fetchByS_P_ID_First(long serviceProcessId,
		OrderByComparator<ProcessAction> orderByComparator) {
		return getPersistence()
				   .fetchByS_P_ID_First(serviceProcessId, orderByComparator);
	}

	/**
	* Returns the last process action in the ordered set where serviceProcessId = &#63;.
	*
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public static ProcessAction findByS_P_ID_Last(long serviceProcessId,
		OrderByComparator<ProcessAction> orderByComparator)
		throws org.opencps.backend.processmgt.exception.NoSuchProcessActionException {
		return getPersistence()
				   .findByS_P_ID_Last(serviceProcessId, orderByComparator);
	}

	/**
	* Returns the last process action in the ordered set where serviceProcessId = &#63;.
	*
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public static ProcessAction fetchByS_P_ID_Last(long serviceProcessId,
		OrderByComparator<ProcessAction> orderByComparator) {
		return getPersistence()
				   .fetchByS_P_ID_Last(serviceProcessId, orderByComparator);
	}

	/**
	* Returns the process actions before and after the current process action in the ordered set where serviceProcessId = &#63;.
	*
	* @param processActionId the primary key of the current process action
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process action
	* @throws NoSuchProcessActionException if a process action with the primary key could not be found
	*/
	public static ProcessAction[] findByS_P_ID_PrevAndNext(
		long processActionId, long serviceProcessId,
		OrderByComparator<ProcessAction> orderByComparator)
		throws org.opencps.backend.processmgt.exception.NoSuchProcessActionException {
		return getPersistence()
				   .findByS_P_ID_PrevAndNext(processActionId, serviceProcessId,
			orderByComparator);
	}

	/**
	* Removes all the process actions where serviceProcessId = &#63; from the database.
	*
	* @param serviceProcessId the service process ID
	*/
	public static void removeByS_P_ID(long serviceProcessId) {
		getPersistence().removeByS_P_ID(serviceProcessId);
	}

	/**
	* Returns the number of process actions where serviceProcessId = &#63;.
	*
	* @param serviceProcessId the service process ID
	* @return the number of matching process actions
	*/
	public static int countByS_P_ID(long serviceProcessId) {
		return getPersistence().countByS_P_ID(serviceProcessId);
	}

	/**
	* Returns all the process actions where preProcessStepId = &#63;.
	*
	* @param preProcessStepId the pre process step ID
	* @return the matching process actions
	*/
	public static List<ProcessAction> findByPR_P_S_ID(long preProcessStepId) {
		return getPersistence().findByPR_P_S_ID(preProcessStepId);
	}

	/**
	* Returns a range of all the process actions where preProcessStepId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param preProcessStepId the pre process step ID
	* @param start the lower bound of the range of process actions
	* @param end the upper bound of the range of process actions (not inclusive)
	* @return the range of matching process actions
	*/
	public static List<ProcessAction> findByPR_P_S_ID(long preProcessStepId,
		int start, int end) {
		return getPersistence().findByPR_P_S_ID(preProcessStepId, start, end);
	}

	/**
	* Returns an ordered range of all the process actions where preProcessStepId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param preProcessStepId the pre process step ID
	* @param start the lower bound of the range of process actions
	* @param end the upper bound of the range of process actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching process actions
	*/
	public static List<ProcessAction> findByPR_P_S_ID(long preProcessStepId,
		int start, int end, OrderByComparator<ProcessAction> orderByComparator) {
		return getPersistence()
				   .findByPR_P_S_ID(preProcessStepId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the process actions where preProcessStepId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param preProcessStepId the pre process step ID
	* @param start the lower bound of the range of process actions
	* @param end the upper bound of the range of process actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching process actions
	*/
	public static List<ProcessAction> findByPR_P_S_ID(long preProcessStepId,
		int start, int end, OrderByComparator<ProcessAction> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByPR_P_S_ID(preProcessStepId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first process action in the ordered set where preProcessStepId = &#63;.
	*
	* @param preProcessStepId the pre process step ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public static ProcessAction findByPR_P_S_ID_First(long preProcessStepId,
		OrderByComparator<ProcessAction> orderByComparator)
		throws org.opencps.backend.processmgt.exception.NoSuchProcessActionException {
		return getPersistence()
				   .findByPR_P_S_ID_First(preProcessStepId, orderByComparator);
	}

	/**
	* Returns the first process action in the ordered set where preProcessStepId = &#63;.
	*
	* @param preProcessStepId the pre process step ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public static ProcessAction fetchByPR_P_S_ID_First(long preProcessStepId,
		OrderByComparator<ProcessAction> orderByComparator) {
		return getPersistence()
				   .fetchByPR_P_S_ID_First(preProcessStepId, orderByComparator);
	}

	/**
	* Returns the last process action in the ordered set where preProcessStepId = &#63;.
	*
	* @param preProcessStepId the pre process step ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public static ProcessAction findByPR_P_S_ID_Last(long preProcessStepId,
		OrderByComparator<ProcessAction> orderByComparator)
		throws org.opencps.backend.processmgt.exception.NoSuchProcessActionException {
		return getPersistence()
				   .findByPR_P_S_ID_Last(preProcessStepId, orderByComparator);
	}

	/**
	* Returns the last process action in the ordered set where preProcessStepId = &#63;.
	*
	* @param preProcessStepId the pre process step ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public static ProcessAction fetchByPR_P_S_ID_Last(long preProcessStepId,
		OrderByComparator<ProcessAction> orderByComparator) {
		return getPersistence()
				   .fetchByPR_P_S_ID_Last(preProcessStepId, orderByComparator);
	}

	/**
	* Returns the process actions before and after the current process action in the ordered set where preProcessStepId = &#63;.
	*
	* @param processActionId the primary key of the current process action
	* @param preProcessStepId the pre process step ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process action
	* @throws NoSuchProcessActionException if a process action with the primary key could not be found
	*/
	public static ProcessAction[] findByPR_P_S_ID_PrevAndNext(
		long processActionId, long preProcessStepId,
		OrderByComparator<ProcessAction> orderByComparator)
		throws org.opencps.backend.processmgt.exception.NoSuchProcessActionException {
		return getPersistence()
				   .findByPR_P_S_ID_PrevAndNext(processActionId,
			preProcessStepId, orderByComparator);
	}

	/**
	* Removes all the process actions where preProcessStepId = &#63; from the database.
	*
	* @param preProcessStepId the pre process step ID
	*/
	public static void removeByPR_P_S_ID(long preProcessStepId) {
		getPersistence().removeByPR_P_S_ID(preProcessStepId);
	}

	/**
	* Returns the number of process actions where preProcessStepId = &#63;.
	*
	* @param preProcessStepId the pre process step ID
	* @return the number of matching process actions
	*/
	public static int countByPR_P_S_ID(long preProcessStepId) {
		return getPersistence().countByPR_P_S_ID(preProcessStepId);
	}

	/**
	* Returns all the process actions where postProcessStepId = &#63;.
	*
	* @param postProcessStepId the post process step ID
	* @return the matching process actions
	*/
	public static List<ProcessAction> findByPO_P_S_ID(long postProcessStepId) {
		return getPersistence().findByPO_P_S_ID(postProcessStepId);
	}

	/**
	* Returns a range of all the process actions where postProcessStepId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param postProcessStepId the post process step ID
	* @param start the lower bound of the range of process actions
	* @param end the upper bound of the range of process actions (not inclusive)
	* @return the range of matching process actions
	*/
	public static List<ProcessAction> findByPO_P_S_ID(long postProcessStepId,
		int start, int end) {
		return getPersistence().findByPO_P_S_ID(postProcessStepId, start, end);
	}

	/**
	* Returns an ordered range of all the process actions where postProcessStepId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param postProcessStepId the post process step ID
	* @param start the lower bound of the range of process actions
	* @param end the upper bound of the range of process actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching process actions
	*/
	public static List<ProcessAction> findByPO_P_S_ID(long postProcessStepId,
		int start, int end, OrderByComparator<ProcessAction> orderByComparator) {
		return getPersistence()
				   .findByPO_P_S_ID(postProcessStepId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the process actions where postProcessStepId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param postProcessStepId the post process step ID
	* @param start the lower bound of the range of process actions
	* @param end the upper bound of the range of process actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching process actions
	*/
	public static List<ProcessAction> findByPO_P_S_ID(long postProcessStepId,
		int start, int end, OrderByComparator<ProcessAction> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByPO_P_S_ID(postProcessStepId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first process action in the ordered set where postProcessStepId = &#63;.
	*
	* @param postProcessStepId the post process step ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public static ProcessAction findByPO_P_S_ID_First(long postProcessStepId,
		OrderByComparator<ProcessAction> orderByComparator)
		throws org.opencps.backend.processmgt.exception.NoSuchProcessActionException {
		return getPersistence()
				   .findByPO_P_S_ID_First(postProcessStepId, orderByComparator);
	}

	/**
	* Returns the first process action in the ordered set where postProcessStepId = &#63;.
	*
	* @param postProcessStepId the post process step ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public static ProcessAction fetchByPO_P_S_ID_First(long postProcessStepId,
		OrderByComparator<ProcessAction> orderByComparator) {
		return getPersistence()
				   .fetchByPO_P_S_ID_First(postProcessStepId, orderByComparator);
	}

	/**
	* Returns the last process action in the ordered set where postProcessStepId = &#63;.
	*
	* @param postProcessStepId the post process step ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public static ProcessAction findByPO_P_S_ID_Last(long postProcessStepId,
		OrderByComparator<ProcessAction> orderByComparator)
		throws org.opencps.backend.processmgt.exception.NoSuchProcessActionException {
		return getPersistence()
				   .findByPO_P_S_ID_Last(postProcessStepId, orderByComparator);
	}

	/**
	* Returns the last process action in the ordered set where postProcessStepId = &#63;.
	*
	* @param postProcessStepId the post process step ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public static ProcessAction fetchByPO_P_S_ID_Last(long postProcessStepId,
		OrderByComparator<ProcessAction> orderByComparator) {
		return getPersistence()
				   .fetchByPO_P_S_ID_Last(postProcessStepId, orderByComparator);
	}

	/**
	* Returns the process actions before and after the current process action in the ordered set where postProcessStepId = &#63;.
	*
	* @param processActionId the primary key of the current process action
	* @param postProcessStepId the post process step ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process action
	* @throws NoSuchProcessActionException if a process action with the primary key could not be found
	*/
	public static ProcessAction[] findByPO_P_S_ID_PrevAndNext(
		long processActionId, long postProcessStepId,
		OrderByComparator<ProcessAction> orderByComparator)
		throws org.opencps.backend.processmgt.exception.NoSuchProcessActionException {
		return getPersistence()
				   .findByPO_P_S_ID_PrevAndNext(processActionId,
			postProcessStepId, orderByComparator);
	}

	/**
	* Removes all the process actions where postProcessStepId = &#63; from the database.
	*
	* @param postProcessStepId the post process step ID
	*/
	public static void removeByPO_P_S_ID(long postProcessStepId) {
		getPersistence().removeByPO_P_S_ID(postProcessStepId);
	}

	/**
	* Returns the number of process actions where postProcessStepId = &#63;.
	*
	* @param postProcessStepId the post process step ID
	* @return the number of matching process actions
	*/
	public static int countByPO_P_S_ID(long postProcessStepId) {
		return getPersistence().countByPO_P_S_ID(postProcessStepId);
	}

	/**
	* Caches the process action in the entity cache if it is enabled.
	*
	* @param processAction the process action
	*/
	public static void cacheResult(ProcessAction processAction) {
		getPersistence().cacheResult(processAction);
	}

	/**
	* Caches the process actions in the entity cache if it is enabled.
	*
	* @param processActions the process actions
	*/
	public static void cacheResult(List<ProcessAction> processActions) {
		getPersistence().cacheResult(processActions);
	}

	/**
	* Creates a new process action with the primary key. Does not add the process action to the database.
	*
	* @param processActionId the primary key for the new process action
	* @return the new process action
	*/
	public static ProcessAction create(long processActionId) {
		return getPersistence().create(processActionId);
	}

	/**
	* Removes the process action with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param processActionId the primary key of the process action
	* @return the process action that was removed
	* @throws NoSuchProcessActionException if a process action with the primary key could not be found
	*/
	public static ProcessAction remove(long processActionId)
		throws org.opencps.backend.processmgt.exception.NoSuchProcessActionException {
		return getPersistence().remove(processActionId);
	}

	public static ProcessAction updateImpl(ProcessAction processAction) {
		return getPersistence().updateImpl(processAction);
	}

	/**
	* Returns the process action with the primary key or throws a {@link NoSuchProcessActionException} if it could not be found.
	*
	* @param processActionId the primary key of the process action
	* @return the process action
	* @throws NoSuchProcessActionException if a process action with the primary key could not be found
	*/
	public static ProcessAction findByPrimaryKey(long processActionId)
		throws org.opencps.backend.processmgt.exception.NoSuchProcessActionException {
		return getPersistence().findByPrimaryKey(processActionId);
	}

	/**
	* Returns the process action with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param processActionId the primary key of the process action
	* @return the process action, or <code>null</code> if a process action with the primary key could not be found
	*/
	public static ProcessAction fetchByPrimaryKey(long processActionId) {
		return getPersistence().fetchByPrimaryKey(processActionId);
	}

	public static java.util.Map<java.io.Serializable, ProcessAction> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the process actions.
	*
	* @return the process actions
	*/
	public static List<ProcessAction> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the process actions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of process actions
	* @param end the upper bound of the range of process actions (not inclusive)
	* @return the range of process actions
	*/
	public static List<ProcessAction> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the process actions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of process actions
	* @param end the upper bound of the range of process actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of process actions
	*/
	public static List<ProcessAction> findAll(int start, int end,
		OrderByComparator<ProcessAction> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the process actions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of process actions
	* @param end the upper bound of the range of process actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of process actions
	*/
	public static List<ProcessAction> findAll(int start, int end,
		OrderByComparator<ProcessAction> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the process actions from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of process actions.
	*
	* @return the number of process actions
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ProcessActionPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ProcessActionPersistence, ProcessActionPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ProcessActionPersistence.class);
}