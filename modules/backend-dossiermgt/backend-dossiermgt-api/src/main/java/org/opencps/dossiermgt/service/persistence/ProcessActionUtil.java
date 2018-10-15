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

import org.opencps.dossiermgt.model.ProcessAction;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the process action service. This utility wraps {@link org.opencps.dossiermgt.service.persistence.impl.ProcessActionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see ProcessActionPersistence
 * @see org.opencps.dossiermgt.service.persistence.impl.ProcessActionPersistenceImpl
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
	public static List<ProcessAction> findByUuid(String uuid) {
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
	public static List<ProcessAction> findByUuid(String uuid, int start, int end) {
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
	public static List<ProcessAction> findByUuid(String uuid, int start,
		int end, OrderByComparator<ProcessAction> orderByComparator) {
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
	public static List<ProcessAction> findByUuid(String uuid, int start,
		int end, OrderByComparator<ProcessAction> orderByComparator,
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
	public static ProcessAction findByUuid_First(String uuid,
		OrderByComparator<ProcessAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessActionException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first process action in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public static ProcessAction fetchByUuid_First(String uuid,
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
	public static ProcessAction findByUuid_Last(String uuid,
		OrderByComparator<ProcessAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessActionException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last process action in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public static ProcessAction fetchByUuid_Last(String uuid,
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
		String uuid, OrderByComparator<ProcessAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessActionException {
		return getPersistence()
				   .findByUuid_PrevAndNext(processActionId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the process actions where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of process actions where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching process actions
	*/
	public static int countByUuid(String uuid) {
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
	public static ProcessAction findByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchProcessActionException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the process action where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public static ProcessAction fetchByUUID_G(String uuid, long groupId) {
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
	public static ProcessAction fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the process action where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the process action that was removed
	*/
	public static ProcessAction removeByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchProcessActionException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of process actions where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching process actions
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the process actions where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching process actions
	*/
	public static List<ProcessAction> findByUuid_C(String uuid, long companyId) {
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
	public static List<ProcessAction> findByUuid_C(String uuid, long companyId,
		int start, int end) {
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
	public static List<ProcessAction> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ProcessAction> orderByComparator) {
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
	public static List<ProcessAction> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ProcessAction> orderByComparator,
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
	public static ProcessAction findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ProcessAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessActionException {
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
	public static ProcessAction fetchByUuid_C_First(String uuid,
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
	public static ProcessAction findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ProcessAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessActionException {
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
	public static ProcessAction fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ProcessAction> orderByComparator) {
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
		long processActionId, String uuid, long companyId,
		OrderByComparator<ProcessAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessActionException {
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
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of process actions where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching process actions
	*/
	public static int countByUuid_C(String uuid, long companyId) {
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
		throws org.opencps.dossiermgt.exception.NoSuchProcessActionException {
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
		throws org.opencps.dossiermgt.exception.NoSuchProcessActionException {
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
		throws org.opencps.dossiermgt.exception.NoSuchProcessActionException {
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
	* Returns all the process actions where groupId = &#63; and actionCode = &#63;.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @return the matching process actions
	*/
	public static List<ProcessAction> findByGI_AC(long groupId,
		String actionCode) {
		return getPersistence().findByGI_AC(groupId, actionCode);
	}

	/**
	* Returns a range of all the process actions where groupId = &#63; and actionCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param start the lower bound of the range of process actions
	* @param end the upper bound of the range of process actions (not inclusive)
	* @return the range of matching process actions
	*/
	public static List<ProcessAction> findByGI_AC(long groupId,
		String actionCode, int start, int end) {
		return getPersistence().findByGI_AC(groupId, actionCode, start, end);
	}

	/**
	* Returns an ordered range of all the process actions where groupId = &#63; and actionCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param start the lower bound of the range of process actions
	* @param end the upper bound of the range of process actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching process actions
	*/
	public static List<ProcessAction> findByGI_AC(long groupId,
		String actionCode, int start, int end,
		OrderByComparator<ProcessAction> orderByComparator) {
		return getPersistence()
				   .findByGI_AC(groupId, actionCode, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the process actions where groupId = &#63; and actionCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param start the lower bound of the range of process actions
	* @param end the upper bound of the range of process actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching process actions
	*/
	public static List<ProcessAction> findByGI_AC(long groupId,
		String actionCode, int start, int end,
		OrderByComparator<ProcessAction> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGI_AC(groupId, actionCode, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first process action in the ordered set where groupId = &#63; and actionCode = &#63;.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public static ProcessAction findByGI_AC_First(long groupId,
		String actionCode, OrderByComparator<ProcessAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessActionException {
		return getPersistence()
				   .findByGI_AC_First(groupId, actionCode, orderByComparator);
	}

	/**
	* Returns the first process action in the ordered set where groupId = &#63; and actionCode = &#63;.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public static ProcessAction fetchByGI_AC_First(long groupId,
		String actionCode, OrderByComparator<ProcessAction> orderByComparator) {
		return getPersistence()
				   .fetchByGI_AC_First(groupId, actionCode, orderByComparator);
	}

	/**
	* Returns the last process action in the ordered set where groupId = &#63; and actionCode = &#63;.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public static ProcessAction findByGI_AC_Last(long groupId,
		String actionCode, OrderByComparator<ProcessAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessActionException {
		return getPersistence()
				   .findByGI_AC_Last(groupId, actionCode, orderByComparator);
	}

	/**
	* Returns the last process action in the ordered set where groupId = &#63; and actionCode = &#63;.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public static ProcessAction fetchByGI_AC_Last(long groupId,
		String actionCode, OrderByComparator<ProcessAction> orderByComparator) {
		return getPersistence()
				   .fetchByGI_AC_Last(groupId, actionCode, orderByComparator);
	}

	/**
	* Returns the process actions before and after the current process action in the ordered set where groupId = &#63; and actionCode = &#63;.
	*
	* @param processActionId the primary key of the current process action
	* @param groupId the group ID
	* @param actionCode the action code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process action
	* @throws NoSuchProcessActionException if a process action with the primary key could not be found
	*/
	public static ProcessAction[] findByGI_AC_PrevAndNext(
		long processActionId, long groupId, String actionCode,
		OrderByComparator<ProcessAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessActionException {
		return getPersistence()
				   .findByGI_AC_PrevAndNext(processActionId, groupId,
			actionCode, orderByComparator);
	}

	/**
	* Removes all the process actions where groupId = &#63; and actionCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	*/
	public static void removeByGI_AC(long groupId, String actionCode) {
		getPersistence().removeByGI_AC(groupId, actionCode);
	}

	/**
	* Returns the number of process actions where groupId = &#63; and actionCode = &#63;.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @return the number of matching process actions
	*/
	public static int countByGI_AC(long groupId, String actionCode) {
		return getPersistence().countByGI_AC(groupId, actionCode);
	}

	/**
	* Returns all the process actions where groupId = &#63; and actionCode = &#63; and serviceProcessId = &#63;.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param serviceProcessId the service process ID
	* @return the matching process actions
	*/
	public static List<ProcessAction> findByGI_AC_SP(long groupId,
		String actionCode, long serviceProcessId) {
		return getPersistence()
				   .findByGI_AC_SP(groupId, actionCode, serviceProcessId);
	}

	/**
	* Returns a range of all the process actions where groupId = &#63; and actionCode = &#63; and serviceProcessId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param serviceProcessId the service process ID
	* @param start the lower bound of the range of process actions
	* @param end the upper bound of the range of process actions (not inclusive)
	* @return the range of matching process actions
	*/
	public static List<ProcessAction> findByGI_AC_SP(long groupId,
		String actionCode, long serviceProcessId, int start, int end) {
		return getPersistence()
				   .findByGI_AC_SP(groupId, actionCode, serviceProcessId,
			start, end);
	}

	/**
	* Returns an ordered range of all the process actions where groupId = &#63; and actionCode = &#63; and serviceProcessId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param serviceProcessId the service process ID
	* @param start the lower bound of the range of process actions
	* @param end the upper bound of the range of process actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching process actions
	*/
	public static List<ProcessAction> findByGI_AC_SP(long groupId,
		String actionCode, long serviceProcessId, int start, int end,
		OrderByComparator<ProcessAction> orderByComparator) {
		return getPersistence()
				   .findByGI_AC_SP(groupId, actionCode, serviceProcessId,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the process actions where groupId = &#63; and actionCode = &#63; and serviceProcessId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param serviceProcessId the service process ID
	* @param start the lower bound of the range of process actions
	* @param end the upper bound of the range of process actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching process actions
	*/
	public static List<ProcessAction> findByGI_AC_SP(long groupId,
		String actionCode, long serviceProcessId, int start, int end,
		OrderByComparator<ProcessAction> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGI_AC_SP(groupId, actionCode, serviceProcessId,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first process action in the ordered set where groupId = &#63; and actionCode = &#63; and serviceProcessId = &#63;.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public static ProcessAction findByGI_AC_SP_First(long groupId,
		String actionCode, long serviceProcessId,
		OrderByComparator<ProcessAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessActionException {
		return getPersistence()
				   .findByGI_AC_SP_First(groupId, actionCode, serviceProcessId,
			orderByComparator);
	}

	/**
	* Returns the first process action in the ordered set where groupId = &#63; and actionCode = &#63; and serviceProcessId = &#63;.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public static ProcessAction fetchByGI_AC_SP_First(long groupId,
		String actionCode, long serviceProcessId,
		OrderByComparator<ProcessAction> orderByComparator) {
		return getPersistence()
				   .fetchByGI_AC_SP_First(groupId, actionCode,
			serviceProcessId, orderByComparator);
	}

	/**
	* Returns the last process action in the ordered set where groupId = &#63; and actionCode = &#63; and serviceProcessId = &#63;.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public static ProcessAction findByGI_AC_SP_Last(long groupId,
		String actionCode, long serviceProcessId,
		OrderByComparator<ProcessAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessActionException {
		return getPersistence()
				   .findByGI_AC_SP_Last(groupId, actionCode, serviceProcessId,
			orderByComparator);
	}

	/**
	* Returns the last process action in the ordered set where groupId = &#63; and actionCode = &#63; and serviceProcessId = &#63;.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public static ProcessAction fetchByGI_AC_SP_Last(long groupId,
		String actionCode, long serviceProcessId,
		OrderByComparator<ProcessAction> orderByComparator) {
		return getPersistence()
				   .fetchByGI_AC_SP_Last(groupId, actionCode, serviceProcessId,
			orderByComparator);
	}

	/**
	* Returns the process actions before and after the current process action in the ordered set where groupId = &#63; and actionCode = &#63; and serviceProcessId = &#63;.
	*
	* @param processActionId the primary key of the current process action
	* @param groupId the group ID
	* @param actionCode the action code
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process action
	* @throws NoSuchProcessActionException if a process action with the primary key could not be found
	*/
	public static ProcessAction[] findByGI_AC_SP_PrevAndNext(
		long processActionId, long groupId, String actionCode,
		long serviceProcessId,
		OrderByComparator<ProcessAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessActionException {
		return getPersistence()
				   .findByGI_AC_SP_PrevAndNext(processActionId, groupId,
			actionCode, serviceProcessId, orderByComparator);
	}

	/**
	* Removes all the process actions where groupId = &#63; and actionCode = &#63; and serviceProcessId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param serviceProcessId the service process ID
	*/
	public static void removeByGI_AC_SP(long groupId, String actionCode,
		long serviceProcessId) {
		getPersistence().removeByGI_AC_SP(groupId, actionCode, serviceProcessId);
	}

	/**
	* Returns the number of process actions where groupId = &#63; and actionCode = &#63; and serviceProcessId = &#63;.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param serviceProcessId the service process ID
	* @return the number of matching process actions
	*/
	public static int countByGI_AC_SP(long groupId, String actionCode,
		long serviceProcessId) {
		return getPersistence()
				   .countByGI_AC_SP(groupId, actionCode, serviceProcessId);
	}

	/**
	* Returns all the process actions where preStepCode = &#63; and groupId = &#63;.
	*
	* @param preStepCode the pre step code
	* @param groupId the group ID
	* @return the matching process actions
	*/
	public static List<ProcessAction> findByPRE_CODE(String preStepCode,
		long groupId) {
		return getPersistence().findByPRE_CODE(preStepCode, groupId);
	}

	/**
	* Returns a range of all the process actions where preStepCode = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param preStepCode the pre step code
	* @param groupId the group ID
	* @param start the lower bound of the range of process actions
	* @param end the upper bound of the range of process actions (not inclusive)
	* @return the range of matching process actions
	*/
	public static List<ProcessAction> findByPRE_CODE(String preStepCode,
		long groupId, int start, int end) {
		return getPersistence().findByPRE_CODE(preStepCode, groupId, start, end);
	}

	/**
	* Returns an ordered range of all the process actions where preStepCode = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param preStepCode the pre step code
	* @param groupId the group ID
	* @param start the lower bound of the range of process actions
	* @param end the upper bound of the range of process actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching process actions
	*/
	public static List<ProcessAction> findByPRE_CODE(String preStepCode,
		long groupId, int start, int end,
		OrderByComparator<ProcessAction> orderByComparator) {
		return getPersistence()
				   .findByPRE_CODE(preStepCode, groupId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the process actions where preStepCode = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param preStepCode the pre step code
	* @param groupId the group ID
	* @param start the lower bound of the range of process actions
	* @param end the upper bound of the range of process actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching process actions
	*/
	public static List<ProcessAction> findByPRE_CODE(String preStepCode,
		long groupId, int start, int end,
		OrderByComparator<ProcessAction> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByPRE_CODE(preStepCode, groupId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first process action in the ordered set where preStepCode = &#63; and groupId = &#63;.
	*
	* @param preStepCode the pre step code
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public static ProcessAction findByPRE_CODE_First(String preStepCode,
		long groupId, OrderByComparator<ProcessAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessActionException {
		return getPersistence()
				   .findByPRE_CODE_First(preStepCode, groupId, orderByComparator);
	}

	/**
	* Returns the first process action in the ordered set where preStepCode = &#63; and groupId = &#63;.
	*
	* @param preStepCode the pre step code
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public static ProcessAction fetchByPRE_CODE_First(String preStepCode,
		long groupId, OrderByComparator<ProcessAction> orderByComparator) {
		return getPersistence()
				   .fetchByPRE_CODE_First(preStepCode, groupId,
			orderByComparator);
	}

	/**
	* Returns the last process action in the ordered set where preStepCode = &#63; and groupId = &#63;.
	*
	* @param preStepCode the pre step code
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public static ProcessAction findByPRE_CODE_Last(String preStepCode,
		long groupId, OrderByComparator<ProcessAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessActionException {
		return getPersistence()
				   .findByPRE_CODE_Last(preStepCode, groupId, orderByComparator);
	}

	/**
	* Returns the last process action in the ordered set where preStepCode = &#63; and groupId = &#63;.
	*
	* @param preStepCode the pre step code
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public static ProcessAction fetchByPRE_CODE_Last(String preStepCode,
		long groupId, OrderByComparator<ProcessAction> orderByComparator) {
		return getPersistence()
				   .fetchByPRE_CODE_Last(preStepCode, groupId, orderByComparator);
	}

	/**
	* Returns the process actions before and after the current process action in the ordered set where preStepCode = &#63; and groupId = &#63;.
	*
	* @param processActionId the primary key of the current process action
	* @param preStepCode the pre step code
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process action
	* @throws NoSuchProcessActionException if a process action with the primary key could not be found
	*/
	public static ProcessAction[] findByPRE_CODE_PrevAndNext(
		long processActionId, String preStepCode, long groupId,
		OrderByComparator<ProcessAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessActionException {
		return getPersistence()
				   .findByPRE_CODE_PrevAndNext(processActionId, preStepCode,
			groupId, orderByComparator);
	}

	/**
	* Removes all the process actions where preStepCode = &#63; and groupId = &#63; from the database.
	*
	* @param preStepCode the pre step code
	* @param groupId the group ID
	*/
	public static void removeByPRE_CODE(String preStepCode, long groupId) {
		getPersistence().removeByPRE_CODE(preStepCode, groupId);
	}

	/**
	* Returns the number of process actions where preStepCode = &#63; and groupId = &#63;.
	*
	* @param preStepCode the pre step code
	* @param groupId the group ID
	* @return the number of matching process actions
	*/
	public static int countByPRE_CODE(String preStepCode, long groupId) {
		return getPersistence().countByPRE_CODE(preStepCode, groupId);
	}

	/**
	* Returns all the process actions where postStepCode = &#63; and groupId = &#63;.
	*
	* @param postStepCode the post step code
	* @param groupId the group ID
	* @return the matching process actions
	*/
	public static List<ProcessAction> findByPOST_CODE(String postStepCode,
		long groupId) {
		return getPersistence().findByPOST_CODE(postStepCode, groupId);
	}

	/**
	* Returns a range of all the process actions where postStepCode = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param postStepCode the post step code
	* @param groupId the group ID
	* @param start the lower bound of the range of process actions
	* @param end the upper bound of the range of process actions (not inclusive)
	* @return the range of matching process actions
	*/
	public static List<ProcessAction> findByPOST_CODE(String postStepCode,
		long groupId, int start, int end) {
		return getPersistence()
				   .findByPOST_CODE(postStepCode, groupId, start, end);
	}

	/**
	* Returns an ordered range of all the process actions where postStepCode = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param postStepCode the post step code
	* @param groupId the group ID
	* @param start the lower bound of the range of process actions
	* @param end the upper bound of the range of process actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching process actions
	*/
	public static List<ProcessAction> findByPOST_CODE(String postStepCode,
		long groupId, int start, int end,
		OrderByComparator<ProcessAction> orderByComparator) {
		return getPersistence()
				   .findByPOST_CODE(postStepCode, groupId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the process actions where postStepCode = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param postStepCode the post step code
	* @param groupId the group ID
	* @param start the lower bound of the range of process actions
	* @param end the upper bound of the range of process actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching process actions
	*/
	public static List<ProcessAction> findByPOST_CODE(String postStepCode,
		long groupId, int start, int end,
		OrderByComparator<ProcessAction> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByPOST_CODE(postStepCode, groupId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first process action in the ordered set where postStepCode = &#63; and groupId = &#63;.
	*
	* @param postStepCode the post step code
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public static ProcessAction findByPOST_CODE_First(String postStepCode,
		long groupId, OrderByComparator<ProcessAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessActionException {
		return getPersistence()
				   .findByPOST_CODE_First(postStepCode, groupId,
			orderByComparator);
	}

	/**
	* Returns the first process action in the ordered set where postStepCode = &#63; and groupId = &#63;.
	*
	* @param postStepCode the post step code
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public static ProcessAction fetchByPOST_CODE_First(String postStepCode,
		long groupId, OrderByComparator<ProcessAction> orderByComparator) {
		return getPersistence()
				   .fetchByPOST_CODE_First(postStepCode, groupId,
			orderByComparator);
	}

	/**
	* Returns the last process action in the ordered set where postStepCode = &#63; and groupId = &#63;.
	*
	* @param postStepCode the post step code
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public static ProcessAction findByPOST_CODE_Last(String postStepCode,
		long groupId, OrderByComparator<ProcessAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessActionException {
		return getPersistence()
				   .findByPOST_CODE_Last(postStepCode, groupId,
			orderByComparator);
	}

	/**
	* Returns the last process action in the ordered set where postStepCode = &#63; and groupId = &#63;.
	*
	* @param postStepCode the post step code
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public static ProcessAction fetchByPOST_CODE_Last(String postStepCode,
		long groupId, OrderByComparator<ProcessAction> orderByComparator) {
		return getPersistence()
				   .fetchByPOST_CODE_Last(postStepCode, groupId,
			orderByComparator);
	}

	/**
	* Returns the process actions before and after the current process action in the ordered set where postStepCode = &#63; and groupId = &#63;.
	*
	* @param processActionId the primary key of the current process action
	* @param postStepCode the post step code
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process action
	* @throws NoSuchProcessActionException if a process action with the primary key could not be found
	*/
	public static ProcessAction[] findByPOST_CODE_PrevAndNext(
		long processActionId, String postStepCode, long groupId,
		OrderByComparator<ProcessAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessActionException {
		return getPersistence()
				   .findByPOST_CODE_PrevAndNext(processActionId, postStepCode,
			groupId, orderByComparator);
	}

	/**
	* Removes all the process actions where postStepCode = &#63; and groupId = &#63; from the database.
	*
	* @param postStepCode the post step code
	* @param groupId the group ID
	*/
	public static void removeByPOST_CODE(String postStepCode, long groupId) {
		getPersistence().removeByPOST_CODE(postStepCode, groupId);
	}

	/**
	* Returns the number of process actions where postStepCode = &#63; and groupId = &#63;.
	*
	* @param postStepCode the post step code
	* @param groupId the group ID
	* @return the number of matching process actions
	*/
	public static int countByPOST_CODE(String postStepCode, long groupId) {
		return getPersistence().countByPOST_CODE(postStepCode, groupId);
	}

	/**
	* Returns the process action where serviceProcessId = &#63; and preStepCode = &#63; and autoEvent = &#63; or throws a {@link NoSuchProcessActionException} if it could not be found.
	*
	* @param serviceProcessId the service process ID
	* @param preStepCode the pre step code
	* @param autoEvent the auto event
	* @return the matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public static ProcessAction findBySPI_PRESC_AEV(long serviceProcessId,
		String preStepCode, String autoEvent)
		throws org.opencps.dossiermgt.exception.NoSuchProcessActionException {
		return getPersistence()
				   .findBySPI_PRESC_AEV(serviceProcessId, preStepCode, autoEvent);
	}

	/**
	* Returns the process action where serviceProcessId = &#63; and preStepCode = &#63; and autoEvent = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param serviceProcessId the service process ID
	* @param preStepCode the pre step code
	* @param autoEvent the auto event
	* @return the matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public static ProcessAction fetchBySPI_PRESC_AEV(long serviceProcessId,
		String preStepCode, String autoEvent) {
		return getPersistence()
				   .fetchBySPI_PRESC_AEV(serviceProcessId, preStepCode,
			autoEvent);
	}

	/**
	* Returns the process action where serviceProcessId = &#63; and preStepCode = &#63; and autoEvent = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param serviceProcessId the service process ID
	* @param preStepCode the pre step code
	* @param autoEvent the auto event
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public static ProcessAction fetchBySPI_PRESC_AEV(long serviceProcessId,
		String preStepCode, String autoEvent, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchBySPI_PRESC_AEV(serviceProcessId, preStepCode,
			autoEvent, retrieveFromCache);
	}

	/**
	* Removes the process action where serviceProcessId = &#63; and preStepCode = &#63; and autoEvent = &#63; from the database.
	*
	* @param serviceProcessId the service process ID
	* @param preStepCode the pre step code
	* @param autoEvent the auto event
	* @return the process action that was removed
	*/
	public static ProcessAction removeBySPI_PRESC_AEV(long serviceProcessId,
		String preStepCode, String autoEvent)
		throws org.opencps.dossiermgt.exception.NoSuchProcessActionException {
		return getPersistence()
				   .removeBySPI_PRESC_AEV(serviceProcessId, preStepCode,
			autoEvent);
	}

	/**
	* Returns the number of process actions where serviceProcessId = &#63; and preStepCode = &#63; and autoEvent = &#63;.
	*
	* @param serviceProcessId the service process ID
	* @param preStepCode the pre step code
	* @param autoEvent the auto event
	* @return the number of matching process actions
	*/
	public static int countBySPI_PRESC_AEV(long serviceProcessId,
		String preStepCode, String autoEvent) {
		return getPersistence()
				   .countBySPI_PRESC_AEV(serviceProcessId, preStepCode,
			autoEvent);
	}

	/**
	* Returns the process action where serviceProcessId = &#63; and actionCode = &#63; or throws a {@link NoSuchProcessActionException} if it could not be found.
	*
	* @param serviceProcessId the service process ID
	* @param actionCode the action code
	* @return the matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public static ProcessAction findBySPID_AC(long serviceProcessId,
		String actionCode)
		throws org.opencps.dossiermgt.exception.NoSuchProcessActionException {
		return getPersistence().findBySPID_AC(serviceProcessId, actionCode);
	}

	/**
	* Returns the process action where serviceProcessId = &#63; and actionCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param serviceProcessId the service process ID
	* @param actionCode the action code
	* @return the matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public static ProcessAction fetchBySPID_AC(long serviceProcessId,
		String actionCode) {
		return getPersistence().fetchBySPID_AC(serviceProcessId, actionCode);
	}

	/**
	* Returns the process action where serviceProcessId = &#63; and actionCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param serviceProcessId the service process ID
	* @param actionCode the action code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public static ProcessAction fetchBySPID_AC(long serviceProcessId,
		String actionCode, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchBySPID_AC(serviceProcessId, actionCode,
			retrieveFromCache);
	}

	/**
	* Removes the process action where serviceProcessId = &#63; and actionCode = &#63; from the database.
	*
	* @param serviceProcessId the service process ID
	* @param actionCode the action code
	* @return the process action that was removed
	*/
	public static ProcessAction removeBySPID_AC(long serviceProcessId,
		String actionCode)
		throws org.opencps.dossiermgt.exception.NoSuchProcessActionException {
		return getPersistence().removeBySPID_AC(serviceProcessId, actionCode);
	}

	/**
	* Returns the number of process actions where serviceProcessId = &#63; and actionCode = &#63;.
	*
	* @param serviceProcessId the service process ID
	* @param actionCode the action code
	* @return the number of matching process actions
	*/
	public static int countBySPID_AC(long serviceProcessId, String actionCode) {
		return getPersistence().countBySPID_AC(serviceProcessId, actionCode);
	}

	/**
	* Returns all the process actions where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param preStepCode the pre step code
	* @return the matching process actions
	*/
	public static List<ProcessAction> findByG_SPID_PRESC(long groupId,
		long serviceProcessId, String preStepCode) {
		return getPersistence()
				   .findByG_SPID_PRESC(groupId, serviceProcessId, preStepCode);
	}

	/**
	* Returns a range of all the process actions where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param preStepCode the pre step code
	* @param start the lower bound of the range of process actions
	* @param end the upper bound of the range of process actions (not inclusive)
	* @return the range of matching process actions
	*/
	public static List<ProcessAction> findByG_SPID_PRESC(long groupId,
		long serviceProcessId, String preStepCode, int start, int end) {
		return getPersistence()
				   .findByG_SPID_PRESC(groupId, serviceProcessId, preStepCode,
			start, end);
	}

	/**
	* Returns an ordered range of all the process actions where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param preStepCode the pre step code
	* @param start the lower bound of the range of process actions
	* @param end the upper bound of the range of process actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching process actions
	*/
	public static List<ProcessAction> findByG_SPID_PRESC(long groupId,
		long serviceProcessId, String preStepCode, int start, int end,
		OrderByComparator<ProcessAction> orderByComparator) {
		return getPersistence()
				   .findByG_SPID_PRESC(groupId, serviceProcessId, preStepCode,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the process actions where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param preStepCode the pre step code
	* @param start the lower bound of the range of process actions
	* @param end the upper bound of the range of process actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching process actions
	*/
	public static List<ProcessAction> findByG_SPID_PRESC(long groupId,
		long serviceProcessId, String preStepCode, int start, int end,
		OrderByComparator<ProcessAction> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_SPID_PRESC(groupId, serviceProcessId, preStepCode,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first process action in the ordered set where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param preStepCode the pre step code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public static ProcessAction findByG_SPID_PRESC_First(long groupId,
		long serviceProcessId, String preStepCode,
		OrderByComparator<ProcessAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessActionException {
		return getPersistence()
				   .findByG_SPID_PRESC_First(groupId, serviceProcessId,
			preStepCode, orderByComparator);
	}

	/**
	* Returns the first process action in the ordered set where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param preStepCode the pre step code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public static ProcessAction fetchByG_SPID_PRESC_First(long groupId,
		long serviceProcessId, String preStepCode,
		OrderByComparator<ProcessAction> orderByComparator) {
		return getPersistence()
				   .fetchByG_SPID_PRESC_First(groupId, serviceProcessId,
			preStepCode, orderByComparator);
	}

	/**
	* Returns the last process action in the ordered set where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param preStepCode the pre step code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public static ProcessAction findByG_SPID_PRESC_Last(long groupId,
		long serviceProcessId, String preStepCode,
		OrderByComparator<ProcessAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessActionException {
		return getPersistence()
				   .findByG_SPID_PRESC_Last(groupId, serviceProcessId,
			preStepCode, orderByComparator);
	}

	/**
	* Returns the last process action in the ordered set where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param preStepCode the pre step code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public static ProcessAction fetchByG_SPID_PRESC_Last(long groupId,
		long serviceProcessId, String preStepCode,
		OrderByComparator<ProcessAction> orderByComparator) {
		return getPersistence()
				   .fetchByG_SPID_PRESC_Last(groupId, serviceProcessId,
			preStepCode, orderByComparator);
	}

	/**
	* Returns the process actions before and after the current process action in the ordered set where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63;.
	*
	* @param processActionId the primary key of the current process action
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param preStepCode the pre step code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process action
	* @throws NoSuchProcessActionException if a process action with the primary key could not be found
	*/
	public static ProcessAction[] findByG_SPID_PRESC_PrevAndNext(
		long processActionId, long groupId, long serviceProcessId,
		String preStepCode, OrderByComparator<ProcessAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessActionException {
		return getPersistence()
				   .findByG_SPID_PRESC_PrevAndNext(processActionId, groupId,
			serviceProcessId, preStepCode, orderByComparator);
	}

	/**
	* Removes all the process actions where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param preStepCode the pre step code
	*/
	public static void removeByG_SPID_PRESC(long groupId,
		long serviceProcessId, String preStepCode) {
		getPersistence()
			.removeByG_SPID_PRESC(groupId, serviceProcessId, preStepCode);
	}

	/**
	* Returns the number of process actions where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param preStepCode the pre step code
	* @return the number of matching process actions
	*/
	public static int countByG_SPID_PRESC(long groupId, long serviceProcessId,
		String preStepCode) {
		return getPersistence()
				   .countByG_SPID_PRESC(groupId, serviceProcessId, preStepCode);
	}

	/**
	* Returns all the process actions where autoEvent = &#63;.
	*
	* @param autoEvent the auto event
	* @return the matching process actions
	*/
	public static List<ProcessAction> findByPSC_AEV(String autoEvent) {
		return getPersistence().findByPSC_AEV(autoEvent);
	}

	/**
	* Returns a range of all the process actions where autoEvent = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param autoEvent the auto event
	* @param start the lower bound of the range of process actions
	* @param end the upper bound of the range of process actions (not inclusive)
	* @return the range of matching process actions
	*/
	public static List<ProcessAction> findByPSC_AEV(String autoEvent,
		int start, int end) {
		return getPersistence().findByPSC_AEV(autoEvent, start, end);
	}

	/**
	* Returns an ordered range of all the process actions where autoEvent = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param autoEvent the auto event
	* @param start the lower bound of the range of process actions
	* @param end the upper bound of the range of process actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching process actions
	*/
	public static List<ProcessAction> findByPSC_AEV(String autoEvent,
		int start, int end, OrderByComparator<ProcessAction> orderByComparator) {
		return getPersistence()
				   .findByPSC_AEV(autoEvent, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the process actions where autoEvent = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param autoEvent the auto event
	* @param start the lower bound of the range of process actions
	* @param end the upper bound of the range of process actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching process actions
	*/
	public static List<ProcessAction> findByPSC_AEV(String autoEvent,
		int start, int end, OrderByComparator<ProcessAction> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByPSC_AEV(autoEvent, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first process action in the ordered set where autoEvent = &#63;.
	*
	* @param autoEvent the auto event
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public static ProcessAction findByPSC_AEV_First(String autoEvent,
		OrderByComparator<ProcessAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessActionException {
		return getPersistence().findByPSC_AEV_First(autoEvent, orderByComparator);
	}

	/**
	* Returns the first process action in the ordered set where autoEvent = &#63;.
	*
	* @param autoEvent the auto event
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public static ProcessAction fetchByPSC_AEV_First(String autoEvent,
		OrderByComparator<ProcessAction> orderByComparator) {
		return getPersistence()
				   .fetchByPSC_AEV_First(autoEvent, orderByComparator);
	}

	/**
	* Returns the last process action in the ordered set where autoEvent = &#63;.
	*
	* @param autoEvent the auto event
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public static ProcessAction findByPSC_AEV_Last(String autoEvent,
		OrderByComparator<ProcessAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessActionException {
		return getPersistence().findByPSC_AEV_Last(autoEvent, orderByComparator);
	}

	/**
	* Returns the last process action in the ordered set where autoEvent = &#63;.
	*
	* @param autoEvent the auto event
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public static ProcessAction fetchByPSC_AEV_Last(String autoEvent,
		OrderByComparator<ProcessAction> orderByComparator) {
		return getPersistence().fetchByPSC_AEV_Last(autoEvent, orderByComparator);
	}

	/**
	* Returns the process actions before and after the current process action in the ordered set where autoEvent = &#63;.
	*
	* @param processActionId the primary key of the current process action
	* @param autoEvent the auto event
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process action
	* @throws NoSuchProcessActionException if a process action with the primary key could not be found
	*/
	public static ProcessAction[] findByPSC_AEV_PrevAndNext(
		long processActionId, String autoEvent,
		OrderByComparator<ProcessAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessActionException {
		return getPersistence()
				   .findByPSC_AEV_PrevAndNext(processActionId, autoEvent,
			orderByComparator);
	}

	/**
	* Removes all the process actions where autoEvent = &#63; from the database.
	*
	* @param autoEvent the auto event
	*/
	public static void removeByPSC_AEV(String autoEvent) {
		getPersistence().removeByPSC_AEV(autoEvent);
	}

	/**
	* Returns the number of process actions where autoEvent = &#63;.
	*
	* @param autoEvent the auto event
	* @return the number of matching process actions
	*/
	public static int countByPSC_AEV(String autoEvent) {
		return getPersistence().countByPSC_AEV(autoEvent);
	}

	/**
	* Returns all the process actions where groupId = &#63; and autoEvent = &#63;.
	*
	* @param groupId the group ID
	* @param autoEvent the auto event
	* @return the matching process actions
	*/
	public static List<ProcessAction> findByPSC_AEV_GI(long groupId,
		String autoEvent) {
		return getPersistence().findByPSC_AEV_GI(groupId, autoEvent);
	}

	/**
	* Returns a range of all the process actions where groupId = &#63; and autoEvent = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param autoEvent the auto event
	* @param start the lower bound of the range of process actions
	* @param end the upper bound of the range of process actions (not inclusive)
	* @return the range of matching process actions
	*/
	public static List<ProcessAction> findByPSC_AEV_GI(long groupId,
		String autoEvent, int start, int end) {
		return getPersistence().findByPSC_AEV_GI(groupId, autoEvent, start, end);
	}

	/**
	* Returns an ordered range of all the process actions where groupId = &#63; and autoEvent = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param autoEvent the auto event
	* @param start the lower bound of the range of process actions
	* @param end the upper bound of the range of process actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching process actions
	*/
	public static List<ProcessAction> findByPSC_AEV_GI(long groupId,
		String autoEvent, int start, int end,
		OrderByComparator<ProcessAction> orderByComparator) {
		return getPersistence()
				   .findByPSC_AEV_GI(groupId, autoEvent, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the process actions where groupId = &#63; and autoEvent = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param autoEvent the auto event
	* @param start the lower bound of the range of process actions
	* @param end the upper bound of the range of process actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching process actions
	*/
	public static List<ProcessAction> findByPSC_AEV_GI(long groupId,
		String autoEvent, int start, int end,
		OrderByComparator<ProcessAction> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByPSC_AEV_GI(groupId, autoEvent, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first process action in the ordered set where groupId = &#63; and autoEvent = &#63;.
	*
	* @param groupId the group ID
	* @param autoEvent the auto event
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public static ProcessAction findByPSC_AEV_GI_First(long groupId,
		String autoEvent, OrderByComparator<ProcessAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessActionException {
		return getPersistence()
				   .findByPSC_AEV_GI_First(groupId, autoEvent, orderByComparator);
	}

	/**
	* Returns the first process action in the ordered set where groupId = &#63; and autoEvent = &#63;.
	*
	* @param groupId the group ID
	* @param autoEvent the auto event
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public static ProcessAction fetchByPSC_AEV_GI_First(long groupId,
		String autoEvent, OrderByComparator<ProcessAction> orderByComparator) {
		return getPersistence()
				   .fetchByPSC_AEV_GI_First(groupId, autoEvent,
			orderByComparator);
	}

	/**
	* Returns the last process action in the ordered set where groupId = &#63; and autoEvent = &#63;.
	*
	* @param groupId the group ID
	* @param autoEvent the auto event
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public static ProcessAction findByPSC_AEV_GI_Last(long groupId,
		String autoEvent, OrderByComparator<ProcessAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessActionException {
		return getPersistence()
				   .findByPSC_AEV_GI_Last(groupId, autoEvent, orderByComparator);
	}

	/**
	* Returns the last process action in the ordered set where groupId = &#63; and autoEvent = &#63;.
	*
	* @param groupId the group ID
	* @param autoEvent the auto event
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public static ProcessAction fetchByPSC_AEV_GI_Last(long groupId,
		String autoEvent, OrderByComparator<ProcessAction> orderByComparator) {
		return getPersistence()
				   .fetchByPSC_AEV_GI_Last(groupId, autoEvent, orderByComparator);
	}

	/**
	* Returns the process actions before and after the current process action in the ordered set where groupId = &#63; and autoEvent = &#63;.
	*
	* @param processActionId the primary key of the current process action
	* @param groupId the group ID
	* @param autoEvent the auto event
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process action
	* @throws NoSuchProcessActionException if a process action with the primary key could not be found
	*/
	public static ProcessAction[] findByPSC_AEV_GI_PrevAndNext(
		long processActionId, long groupId, String autoEvent,
		OrderByComparator<ProcessAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessActionException {
		return getPersistence()
				   .findByPSC_AEV_GI_PrevAndNext(processActionId, groupId,
			autoEvent, orderByComparator);
	}

	/**
	* Removes all the process actions where groupId = &#63; and autoEvent = &#63; from the database.
	*
	* @param groupId the group ID
	* @param autoEvent the auto event
	*/
	public static void removeByPSC_AEV_GI(long groupId, String autoEvent) {
		getPersistence().removeByPSC_AEV_GI(groupId, autoEvent);
	}

	/**
	* Returns the number of process actions where groupId = &#63; and autoEvent = &#63;.
	*
	* @param groupId the group ID
	* @param autoEvent the auto event
	* @return the number of matching process actions
	*/
	public static int countByPSC_AEV_GI(long groupId, String autoEvent) {
		return getPersistence().countByPSC_AEV_GI(groupId, autoEvent);
	}

	/**
	* Returns the process action where serviceProcessId = &#63; and actionCode = &#63; and actionName = &#63; or throws a {@link NoSuchProcessActionException} if it could not be found.
	*
	* @param serviceProcessId the service process ID
	* @param actionCode the action code
	* @param actionName the action name
	* @return the matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public static ProcessAction findBySPID_AC_AN(long serviceProcessId,
		String actionCode, String actionName)
		throws org.opencps.dossiermgt.exception.NoSuchProcessActionException {
		return getPersistence()
				   .findBySPID_AC_AN(serviceProcessId, actionCode, actionName);
	}

	/**
	* Returns the process action where serviceProcessId = &#63; and actionCode = &#63; and actionName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param serviceProcessId the service process ID
	* @param actionCode the action code
	* @param actionName the action name
	* @return the matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public static ProcessAction fetchBySPID_AC_AN(long serviceProcessId,
		String actionCode, String actionName) {
		return getPersistence()
				   .fetchBySPID_AC_AN(serviceProcessId, actionCode, actionName);
	}

	/**
	* Returns the process action where serviceProcessId = &#63; and actionCode = &#63; and actionName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param serviceProcessId the service process ID
	* @param actionCode the action code
	* @param actionName the action name
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public static ProcessAction fetchBySPID_AC_AN(long serviceProcessId,
		String actionCode, String actionName, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchBySPID_AC_AN(serviceProcessId, actionCode, actionName,
			retrieveFromCache);
	}

	/**
	* Removes the process action where serviceProcessId = &#63; and actionCode = &#63; and actionName = &#63; from the database.
	*
	* @param serviceProcessId the service process ID
	* @param actionCode the action code
	* @param actionName the action name
	* @return the process action that was removed
	*/
	public static ProcessAction removeBySPID_AC_AN(long serviceProcessId,
		String actionCode, String actionName)
		throws org.opencps.dossiermgt.exception.NoSuchProcessActionException {
		return getPersistence()
				   .removeBySPID_AC_AN(serviceProcessId, actionCode, actionName);
	}

	/**
	* Returns the number of process actions where serviceProcessId = &#63; and actionCode = &#63; and actionName = &#63;.
	*
	* @param serviceProcessId the service process ID
	* @param actionCode the action code
	* @param actionName the action name
	* @return the number of matching process actions
	*/
	public static int countBySPID_AC_AN(long serviceProcessId,
		String actionCode, String actionName) {
		return getPersistence()
				   .countBySPID_AC_AN(serviceProcessId, actionCode, actionName);
	}

	/**
	* Returns all the process actions where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param preStepCode the pre step code
	* @return the matching process actions
	*/
	public static List<ProcessAction> findByF_GID_SID_PRE(long groupId,
		long serviceProcessId, String preStepCode) {
		return getPersistence()
				   .findByF_GID_SID_PRE(groupId, serviceProcessId, preStepCode);
	}

	/**
	* Returns a range of all the process actions where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param preStepCode the pre step code
	* @param start the lower bound of the range of process actions
	* @param end the upper bound of the range of process actions (not inclusive)
	* @return the range of matching process actions
	*/
	public static List<ProcessAction> findByF_GID_SID_PRE(long groupId,
		long serviceProcessId, String preStepCode, int start, int end) {
		return getPersistence()
				   .findByF_GID_SID_PRE(groupId, serviceProcessId, preStepCode,
			start, end);
	}

	/**
	* Returns an ordered range of all the process actions where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param preStepCode the pre step code
	* @param start the lower bound of the range of process actions
	* @param end the upper bound of the range of process actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching process actions
	*/
	public static List<ProcessAction> findByF_GID_SID_PRE(long groupId,
		long serviceProcessId, String preStepCode, int start, int end,
		OrderByComparator<ProcessAction> orderByComparator) {
		return getPersistence()
				   .findByF_GID_SID_PRE(groupId, serviceProcessId, preStepCode,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the process actions where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param preStepCode the pre step code
	* @param start the lower bound of the range of process actions
	* @param end the upper bound of the range of process actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching process actions
	*/
	public static List<ProcessAction> findByF_GID_SID_PRE(long groupId,
		long serviceProcessId, String preStepCode, int start, int end,
		OrderByComparator<ProcessAction> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_GID_SID_PRE(groupId, serviceProcessId, preStepCode,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first process action in the ordered set where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param preStepCode the pre step code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public static ProcessAction findByF_GID_SID_PRE_First(long groupId,
		long serviceProcessId, String preStepCode,
		OrderByComparator<ProcessAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessActionException {
		return getPersistence()
				   .findByF_GID_SID_PRE_First(groupId, serviceProcessId,
			preStepCode, orderByComparator);
	}

	/**
	* Returns the first process action in the ordered set where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param preStepCode the pre step code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public static ProcessAction fetchByF_GID_SID_PRE_First(long groupId,
		long serviceProcessId, String preStepCode,
		OrderByComparator<ProcessAction> orderByComparator) {
		return getPersistence()
				   .fetchByF_GID_SID_PRE_First(groupId, serviceProcessId,
			preStepCode, orderByComparator);
	}

	/**
	* Returns the last process action in the ordered set where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param preStepCode the pre step code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public static ProcessAction findByF_GID_SID_PRE_Last(long groupId,
		long serviceProcessId, String preStepCode,
		OrderByComparator<ProcessAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessActionException {
		return getPersistence()
				   .findByF_GID_SID_PRE_Last(groupId, serviceProcessId,
			preStepCode, orderByComparator);
	}

	/**
	* Returns the last process action in the ordered set where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param preStepCode the pre step code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public static ProcessAction fetchByF_GID_SID_PRE_Last(long groupId,
		long serviceProcessId, String preStepCode,
		OrderByComparator<ProcessAction> orderByComparator) {
		return getPersistence()
				   .fetchByF_GID_SID_PRE_Last(groupId, serviceProcessId,
			preStepCode, orderByComparator);
	}

	/**
	* Returns the process actions before and after the current process action in the ordered set where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63;.
	*
	* @param processActionId the primary key of the current process action
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param preStepCode the pre step code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process action
	* @throws NoSuchProcessActionException if a process action with the primary key could not be found
	*/
	public static ProcessAction[] findByF_GID_SID_PRE_PrevAndNext(
		long processActionId, long groupId, long serviceProcessId,
		String preStepCode, OrderByComparator<ProcessAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessActionException {
		return getPersistence()
				   .findByF_GID_SID_PRE_PrevAndNext(processActionId, groupId,
			serviceProcessId, preStepCode, orderByComparator);
	}

	/**
	* Removes all the process actions where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param preStepCode the pre step code
	*/
	public static void removeByF_GID_SID_PRE(long groupId,
		long serviceProcessId, String preStepCode) {
		getPersistence()
			.removeByF_GID_SID_PRE(groupId, serviceProcessId, preStepCode);
	}

	/**
	* Returns the number of process actions where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param preStepCode the pre step code
	* @return the number of matching process actions
	*/
	public static int countByF_GID_SID_PRE(long groupId, long serviceProcessId,
		String preStepCode) {
		return getPersistence()
				   .countByF_GID_SID_PRE(groupId, serviceProcessId, preStepCode);
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
		throws org.opencps.dossiermgt.exception.NoSuchProcessActionException {
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
		throws org.opencps.dossiermgt.exception.NoSuchProcessActionException {
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

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ProcessActionPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ProcessActionPersistence, ProcessActionPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ProcessActionPersistence.class);

		ServiceTracker<ProcessActionPersistence, ProcessActionPersistence> serviceTracker =
			new ServiceTracker<ProcessActionPersistence, ProcessActionPersistence>(bundle.getBundleContext(),
				ProcessActionPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}