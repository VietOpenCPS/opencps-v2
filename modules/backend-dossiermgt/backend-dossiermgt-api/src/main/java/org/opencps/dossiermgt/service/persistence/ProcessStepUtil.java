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

import org.opencps.dossiermgt.model.ProcessStep;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the process step service. This utility wraps {@link org.opencps.dossiermgt.service.persistence.impl.ProcessStepPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see ProcessStepPersistence
 * @see org.opencps.dossiermgt.service.persistence.impl.ProcessStepPersistenceImpl
 * @generated
 */
@ProviderType
public class ProcessStepUtil {
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
	public static void clearCache(ProcessStep processStep) {
		getPersistence().clearCache(processStep);
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
	public static List<ProcessStep> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ProcessStep> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ProcessStep> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ProcessStep> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ProcessStep update(ProcessStep processStep) {
		return getPersistence().update(processStep);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ProcessStep update(ProcessStep processStep,
		ServiceContext serviceContext) {
		return getPersistence().update(processStep, serviceContext);
	}

	/**
	* Returns all the process steps where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching process steps
	*/
	public static List<ProcessStep> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the process steps where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of process steps
	* @param end the upper bound of the range of process steps (not inclusive)
	* @return the range of matching process steps
	*/
	public static List<ProcessStep> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the process steps where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of process steps
	* @param end the upper bound of the range of process steps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching process steps
	*/
	public static List<ProcessStep> findByUuid(String uuid, int start, int end,
		OrderByComparator<ProcessStep> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the process steps where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of process steps
	* @param end the upper bound of the range of process steps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching process steps
	*/
	public static List<ProcessStep> findByUuid(String uuid, int start, int end,
		OrderByComparator<ProcessStep> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first process step in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process step
	* @throws NoSuchProcessStepException if a matching process step could not be found
	*/
	public static ProcessStep findByUuid_First(String uuid,
		OrderByComparator<ProcessStep> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessStepException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first process step in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process step, or <code>null</code> if a matching process step could not be found
	*/
	public static ProcessStep fetchByUuid_First(String uuid,
		OrderByComparator<ProcessStep> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last process step in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process step
	* @throws NoSuchProcessStepException if a matching process step could not be found
	*/
	public static ProcessStep findByUuid_Last(String uuid,
		OrderByComparator<ProcessStep> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessStepException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last process step in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process step, or <code>null</code> if a matching process step could not be found
	*/
	public static ProcessStep fetchByUuid_Last(String uuid,
		OrderByComparator<ProcessStep> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the process steps before and after the current process step in the ordered set where uuid = &#63;.
	*
	* @param processStepId the primary key of the current process step
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process step
	* @throws NoSuchProcessStepException if a process step with the primary key could not be found
	*/
	public static ProcessStep[] findByUuid_PrevAndNext(long processStepId,
		String uuid, OrderByComparator<ProcessStep> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessStepException {
		return getPersistence()
				   .findByUuid_PrevAndNext(processStepId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the process steps where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of process steps where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching process steps
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the process step where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchProcessStepException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching process step
	* @throws NoSuchProcessStepException if a matching process step could not be found
	*/
	public static ProcessStep findByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchProcessStepException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the process step where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching process step, or <code>null</code> if a matching process step could not be found
	*/
	public static ProcessStep fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the process step where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching process step, or <code>null</code> if a matching process step could not be found
	*/
	public static ProcessStep fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the process step where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the process step that was removed
	*/
	public static ProcessStep removeByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchProcessStepException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of process steps where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching process steps
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the process steps where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching process steps
	*/
	public static List<ProcessStep> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the process steps where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of process steps
	* @param end the upper bound of the range of process steps (not inclusive)
	* @return the range of matching process steps
	*/
	public static List<ProcessStep> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the process steps where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of process steps
	* @param end the upper bound of the range of process steps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching process steps
	*/
	public static List<ProcessStep> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ProcessStep> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the process steps where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of process steps
	* @param end the upper bound of the range of process steps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching process steps
	*/
	public static List<ProcessStep> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ProcessStep> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first process step in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process step
	* @throws NoSuchProcessStepException if a matching process step could not be found
	*/
	public static ProcessStep findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ProcessStep> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessStepException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first process step in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process step, or <code>null</code> if a matching process step could not be found
	*/
	public static ProcessStep fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ProcessStep> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last process step in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process step
	* @throws NoSuchProcessStepException if a matching process step could not be found
	*/
	public static ProcessStep findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ProcessStep> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessStepException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last process step in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process step, or <code>null</code> if a matching process step could not be found
	*/
	public static ProcessStep fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ProcessStep> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the process steps before and after the current process step in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param processStepId the primary key of the current process step
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process step
	* @throws NoSuchProcessStepException if a process step with the primary key could not be found
	*/
	public static ProcessStep[] findByUuid_C_PrevAndNext(long processStepId,
		String uuid, long companyId,
		OrderByComparator<ProcessStep> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessStepException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(processStepId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the process steps where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of process steps where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching process steps
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the process steps where serviceProcessId = &#63;.
	*
	* @param serviceProcessId the service process ID
	* @return the matching process steps
	*/
	public static List<ProcessStep> findByS_P_ID(long serviceProcessId) {
		return getPersistence().findByS_P_ID(serviceProcessId);
	}

	/**
	* Returns a range of all the process steps where serviceProcessId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param serviceProcessId the service process ID
	* @param start the lower bound of the range of process steps
	* @param end the upper bound of the range of process steps (not inclusive)
	* @return the range of matching process steps
	*/
	public static List<ProcessStep> findByS_P_ID(long serviceProcessId,
		int start, int end) {
		return getPersistence().findByS_P_ID(serviceProcessId, start, end);
	}

	/**
	* Returns an ordered range of all the process steps where serviceProcessId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param serviceProcessId the service process ID
	* @param start the lower bound of the range of process steps
	* @param end the upper bound of the range of process steps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching process steps
	*/
	public static List<ProcessStep> findByS_P_ID(long serviceProcessId,
		int start, int end, OrderByComparator<ProcessStep> orderByComparator) {
		return getPersistence()
				   .findByS_P_ID(serviceProcessId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the process steps where serviceProcessId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param serviceProcessId the service process ID
	* @param start the lower bound of the range of process steps
	* @param end the upper bound of the range of process steps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching process steps
	*/
	public static List<ProcessStep> findByS_P_ID(long serviceProcessId,
		int start, int end, OrderByComparator<ProcessStep> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByS_P_ID(serviceProcessId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first process step in the ordered set where serviceProcessId = &#63;.
	*
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process step
	* @throws NoSuchProcessStepException if a matching process step could not be found
	*/
	public static ProcessStep findByS_P_ID_First(long serviceProcessId,
		OrderByComparator<ProcessStep> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessStepException {
		return getPersistence()
				   .findByS_P_ID_First(serviceProcessId, orderByComparator);
	}

	/**
	* Returns the first process step in the ordered set where serviceProcessId = &#63;.
	*
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process step, or <code>null</code> if a matching process step could not be found
	*/
	public static ProcessStep fetchByS_P_ID_First(long serviceProcessId,
		OrderByComparator<ProcessStep> orderByComparator) {
		return getPersistence()
				   .fetchByS_P_ID_First(serviceProcessId, orderByComparator);
	}

	/**
	* Returns the last process step in the ordered set where serviceProcessId = &#63;.
	*
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process step
	* @throws NoSuchProcessStepException if a matching process step could not be found
	*/
	public static ProcessStep findByS_P_ID_Last(long serviceProcessId,
		OrderByComparator<ProcessStep> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessStepException {
		return getPersistence()
				   .findByS_P_ID_Last(serviceProcessId, orderByComparator);
	}

	/**
	* Returns the last process step in the ordered set where serviceProcessId = &#63;.
	*
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process step, or <code>null</code> if a matching process step could not be found
	*/
	public static ProcessStep fetchByS_P_ID_Last(long serviceProcessId,
		OrderByComparator<ProcessStep> orderByComparator) {
		return getPersistence()
				   .fetchByS_P_ID_Last(serviceProcessId, orderByComparator);
	}

	/**
	* Returns the process steps before and after the current process step in the ordered set where serviceProcessId = &#63;.
	*
	* @param processStepId the primary key of the current process step
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process step
	* @throws NoSuchProcessStepException if a process step with the primary key could not be found
	*/
	public static ProcessStep[] findByS_P_ID_PrevAndNext(long processStepId,
		long serviceProcessId, OrderByComparator<ProcessStep> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessStepException {
		return getPersistence()
				   .findByS_P_ID_PrevAndNext(processStepId, serviceProcessId,
			orderByComparator);
	}

	/**
	* Removes all the process steps where serviceProcessId = &#63; from the database.
	*
	* @param serviceProcessId the service process ID
	*/
	public static void removeByS_P_ID(long serviceProcessId) {
		getPersistence().removeByS_P_ID(serviceProcessId);
	}

	/**
	* Returns the number of process steps where serviceProcessId = &#63;.
	*
	* @param serviceProcessId the service process ID
	* @return the number of matching process steps
	*/
	public static int countByS_P_ID(long serviceProcessId) {
		return getPersistence().countByS_P_ID(serviceProcessId);
	}

	/**
	* Returns the process step where stepCode = &#63; and groupId = &#63; and serviceProcessId = &#63; or throws a {@link NoSuchProcessStepException} if it could not be found.
	*
	* @param stepCode the step code
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @return the matching process step
	* @throws NoSuchProcessStepException if a matching process step could not be found
	*/
	public static ProcessStep findBySC_GID(String stepCode, long groupId,
		long serviceProcessId)
		throws org.opencps.dossiermgt.exception.NoSuchProcessStepException {
		return getPersistence().findBySC_GID(stepCode, groupId, serviceProcessId);
	}

	/**
	* Returns the process step where stepCode = &#63; and groupId = &#63; and serviceProcessId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param stepCode the step code
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @return the matching process step, or <code>null</code> if a matching process step could not be found
	*/
	public static ProcessStep fetchBySC_GID(String stepCode, long groupId,
		long serviceProcessId) {
		return getPersistence()
				   .fetchBySC_GID(stepCode, groupId, serviceProcessId);
	}

	/**
	* Returns the process step where stepCode = &#63; and groupId = &#63; and serviceProcessId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param stepCode the step code
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching process step, or <code>null</code> if a matching process step could not be found
	*/
	public static ProcessStep fetchBySC_GID(String stepCode, long groupId,
		long serviceProcessId, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchBySC_GID(stepCode, groupId, serviceProcessId,
			retrieveFromCache);
	}

	/**
	* Removes the process step where stepCode = &#63; and groupId = &#63; and serviceProcessId = &#63; from the database.
	*
	* @param stepCode the step code
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @return the process step that was removed
	*/
	public static ProcessStep removeBySC_GID(String stepCode, long groupId,
		long serviceProcessId)
		throws org.opencps.dossiermgt.exception.NoSuchProcessStepException {
		return getPersistence()
				   .removeBySC_GID(stepCode, groupId, serviceProcessId);
	}

	/**
	* Returns the number of process steps where stepCode = &#63; and groupId = &#63; and serviceProcessId = &#63;.
	*
	* @param stepCode the step code
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @return the number of matching process steps
	*/
	public static int countBySC_GID(String stepCode, long groupId,
		long serviceProcessId) {
		return getPersistence()
				   .countBySC_GID(stepCode, groupId, serviceProcessId);
	}

	/**
	* Returns all the process steps where stepCode = &#63; and serviceProcessId = &#63;.
	*
	* @param stepCode the step code
	* @param serviceProcessId the service process ID
	* @return the matching process steps
	*/
	public static List<ProcessStep> findBySC_SPID(String stepCode,
		long serviceProcessId) {
		return getPersistence().findBySC_SPID(stepCode, serviceProcessId);
	}

	/**
	* Returns a range of all the process steps where stepCode = &#63; and serviceProcessId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param stepCode the step code
	* @param serviceProcessId the service process ID
	* @param start the lower bound of the range of process steps
	* @param end the upper bound of the range of process steps (not inclusive)
	* @return the range of matching process steps
	*/
	public static List<ProcessStep> findBySC_SPID(String stepCode,
		long serviceProcessId, int start, int end) {
		return getPersistence()
				   .findBySC_SPID(stepCode, serviceProcessId, start, end);
	}

	/**
	* Returns an ordered range of all the process steps where stepCode = &#63; and serviceProcessId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param stepCode the step code
	* @param serviceProcessId the service process ID
	* @param start the lower bound of the range of process steps
	* @param end the upper bound of the range of process steps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching process steps
	*/
	public static List<ProcessStep> findBySC_SPID(String stepCode,
		long serviceProcessId, int start, int end,
		OrderByComparator<ProcessStep> orderByComparator) {
		return getPersistence()
				   .findBySC_SPID(stepCode, serviceProcessId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the process steps where stepCode = &#63; and serviceProcessId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param stepCode the step code
	* @param serviceProcessId the service process ID
	* @param start the lower bound of the range of process steps
	* @param end the upper bound of the range of process steps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching process steps
	*/
	public static List<ProcessStep> findBySC_SPID(String stepCode,
		long serviceProcessId, int start, int end,
		OrderByComparator<ProcessStep> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findBySC_SPID(stepCode, serviceProcessId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first process step in the ordered set where stepCode = &#63; and serviceProcessId = &#63;.
	*
	* @param stepCode the step code
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process step
	* @throws NoSuchProcessStepException if a matching process step could not be found
	*/
	public static ProcessStep findBySC_SPID_First(String stepCode,
		long serviceProcessId, OrderByComparator<ProcessStep> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessStepException {
		return getPersistence()
				   .findBySC_SPID_First(stepCode, serviceProcessId,
			orderByComparator);
	}

	/**
	* Returns the first process step in the ordered set where stepCode = &#63; and serviceProcessId = &#63;.
	*
	* @param stepCode the step code
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process step, or <code>null</code> if a matching process step could not be found
	*/
	public static ProcessStep fetchBySC_SPID_First(String stepCode,
		long serviceProcessId, OrderByComparator<ProcessStep> orderByComparator) {
		return getPersistence()
				   .fetchBySC_SPID_First(stepCode, serviceProcessId,
			orderByComparator);
	}

	/**
	* Returns the last process step in the ordered set where stepCode = &#63; and serviceProcessId = &#63;.
	*
	* @param stepCode the step code
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process step
	* @throws NoSuchProcessStepException if a matching process step could not be found
	*/
	public static ProcessStep findBySC_SPID_Last(String stepCode,
		long serviceProcessId, OrderByComparator<ProcessStep> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessStepException {
		return getPersistence()
				   .findBySC_SPID_Last(stepCode, serviceProcessId,
			orderByComparator);
	}

	/**
	* Returns the last process step in the ordered set where stepCode = &#63; and serviceProcessId = &#63;.
	*
	* @param stepCode the step code
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process step, or <code>null</code> if a matching process step could not be found
	*/
	public static ProcessStep fetchBySC_SPID_Last(String stepCode,
		long serviceProcessId, OrderByComparator<ProcessStep> orderByComparator) {
		return getPersistence()
				   .fetchBySC_SPID_Last(stepCode, serviceProcessId,
			orderByComparator);
	}

	/**
	* Returns the process steps before and after the current process step in the ordered set where stepCode = &#63; and serviceProcessId = &#63;.
	*
	* @param processStepId the primary key of the current process step
	* @param stepCode the step code
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process step
	* @throws NoSuchProcessStepException if a process step with the primary key could not be found
	*/
	public static ProcessStep[] findBySC_SPID_PrevAndNext(long processStepId,
		String stepCode, long serviceProcessId,
		OrderByComparator<ProcessStep> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessStepException {
		return getPersistence()
				   .findBySC_SPID_PrevAndNext(processStepId, stepCode,
			serviceProcessId, orderByComparator);
	}

	/**
	* Removes all the process steps where stepCode = &#63; and serviceProcessId = &#63; from the database.
	*
	* @param stepCode the step code
	* @param serviceProcessId the service process ID
	*/
	public static void removeBySC_SPID(String stepCode, long serviceProcessId) {
		getPersistence().removeBySC_SPID(stepCode, serviceProcessId);
	}

	/**
	* Returns the number of process steps where stepCode = &#63; and serviceProcessId = &#63;.
	*
	* @param stepCode the step code
	* @param serviceProcessId the service process ID
	* @return the number of matching process steps
	*/
	public static int countBySC_SPID(String stepCode, long serviceProcessId) {
		return getPersistence().countBySC_SPID(stepCode, serviceProcessId);
	}

	/**
	* Returns all the process steps where dossierStatus = &#63; and dossierSubStatus = &#63; and groupId = &#63;.
	*
	* @param dossierStatus the dossier status
	* @param dossierSubStatus the dossier sub status
	* @param groupId the group ID
	* @return the matching process steps
	*/
	public static List<ProcessStep> findByDST_DSST(String dossierStatus,
		String dossierSubStatus, long groupId) {
		return getPersistence()
				   .findByDST_DSST(dossierStatus, dossierSubStatus, groupId);
	}

	/**
	* Returns a range of all the process steps where dossierStatus = &#63; and dossierSubStatus = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierStatus the dossier status
	* @param dossierSubStatus the dossier sub status
	* @param groupId the group ID
	* @param start the lower bound of the range of process steps
	* @param end the upper bound of the range of process steps (not inclusive)
	* @return the range of matching process steps
	*/
	public static List<ProcessStep> findByDST_DSST(String dossierStatus,
		String dossierSubStatus, long groupId, int start, int end) {
		return getPersistence()
				   .findByDST_DSST(dossierStatus, dossierSubStatus, groupId,
			start, end);
	}

	/**
	* Returns an ordered range of all the process steps where dossierStatus = &#63; and dossierSubStatus = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierStatus the dossier status
	* @param dossierSubStatus the dossier sub status
	* @param groupId the group ID
	* @param start the lower bound of the range of process steps
	* @param end the upper bound of the range of process steps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching process steps
	*/
	public static List<ProcessStep> findByDST_DSST(String dossierStatus,
		String dossierSubStatus, long groupId, int start, int end,
		OrderByComparator<ProcessStep> orderByComparator) {
		return getPersistence()
				   .findByDST_DSST(dossierStatus, dossierSubStatus, groupId,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the process steps where dossierStatus = &#63; and dossierSubStatus = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierStatus the dossier status
	* @param dossierSubStatus the dossier sub status
	* @param groupId the group ID
	* @param start the lower bound of the range of process steps
	* @param end the upper bound of the range of process steps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching process steps
	*/
	public static List<ProcessStep> findByDST_DSST(String dossierStatus,
		String dossierSubStatus, long groupId, int start, int end,
		OrderByComparator<ProcessStep> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByDST_DSST(dossierStatus, dossierSubStatus, groupId,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first process step in the ordered set where dossierStatus = &#63; and dossierSubStatus = &#63; and groupId = &#63;.
	*
	* @param dossierStatus the dossier status
	* @param dossierSubStatus the dossier sub status
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process step
	* @throws NoSuchProcessStepException if a matching process step could not be found
	*/
	public static ProcessStep findByDST_DSST_First(String dossierStatus,
		String dossierSubStatus, long groupId,
		OrderByComparator<ProcessStep> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessStepException {
		return getPersistence()
				   .findByDST_DSST_First(dossierStatus, dossierSubStatus,
			groupId, orderByComparator);
	}

	/**
	* Returns the first process step in the ordered set where dossierStatus = &#63; and dossierSubStatus = &#63; and groupId = &#63;.
	*
	* @param dossierStatus the dossier status
	* @param dossierSubStatus the dossier sub status
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process step, or <code>null</code> if a matching process step could not be found
	*/
	public static ProcessStep fetchByDST_DSST_First(String dossierStatus,
		String dossierSubStatus, long groupId,
		OrderByComparator<ProcessStep> orderByComparator) {
		return getPersistence()
				   .fetchByDST_DSST_First(dossierStatus, dossierSubStatus,
			groupId, orderByComparator);
	}

	/**
	* Returns the last process step in the ordered set where dossierStatus = &#63; and dossierSubStatus = &#63; and groupId = &#63;.
	*
	* @param dossierStatus the dossier status
	* @param dossierSubStatus the dossier sub status
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process step
	* @throws NoSuchProcessStepException if a matching process step could not be found
	*/
	public static ProcessStep findByDST_DSST_Last(String dossierStatus,
		String dossierSubStatus, long groupId,
		OrderByComparator<ProcessStep> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessStepException {
		return getPersistence()
				   .findByDST_DSST_Last(dossierStatus, dossierSubStatus,
			groupId, orderByComparator);
	}

	/**
	* Returns the last process step in the ordered set where dossierStatus = &#63; and dossierSubStatus = &#63; and groupId = &#63;.
	*
	* @param dossierStatus the dossier status
	* @param dossierSubStatus the dossier sub status
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process step, or <code>null</code> if a matching process step could not be found
	*/
	public static ProcessStep fetchByDST_DSST_Last(String dossierStatus,
		String dossierSubStatus, long groupId,
		OrderByComparator<ProcessStep> orderByComparator) {
		return getPersistence()
				   .fetchByDST_DSST_Last(dossierStatus, dossierSubStatus,
			groupId, orderByComparator);
	}

	/**
	* Returns the process steps before and after the current process step in the ordered set where dossierStatus = &#63; and dossierSubStatus = &#63; and groupId = &#63;.
	*
	* @param processStepId the primary key of the current process step
	* @param dossierStatus the dossier status
	* @param dossierSubStatus the dossier sub status
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process step
	* @throws NoSuchProcessStepException if a process step with the primary key could not be found
	*/
	public static ProcessStep[] findByDST_DSST_PrevAndNext(long processStepId,
		String dossierStatus, String dossierSubStatus, long groupId,
		OrderByComparator<ProcessStep> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessStepException {
		return getPersistence()
				   .findByDST_DSST_PrevAndNext(processStepId, dossierStatus,
			dossierSubStatus, groupId, orderByComparator);
	}

	/**
	* Removes all the process steps where dossierStatus = &#63; and dossierSubStatus = &#63; and groupId = &#63; from the database.
	*
	* @param dossierStatus the dossier status
	* @param dossierSubStatus the dossier sub status
	* @param groupId the group ID
	*/
	public static void removeByDST_DSST(String dossierStatus,
		String dossierSubStatus, long groupId) {
		getPersistence()
			.removeByDST_DSST(dossierStatus, dossierSubStatus, groupId);
	}

	/**
	* Returns the number of process steps where dossierStatus = &#63; and dossierSubStatus = &#63; and groupId = &#63;.
	*
	* @param dossierStatus the dossier status
	* @param dossierSubStatus the dossier sub status
	* @param groupId the group ID
	* @return the number of matching process steps
	*/
	public static int countByDST_DSST(String dossierStatus,
		String dossierSubStatus, long groupId) {
		return getPersistence()
				   .countByDST_DSST(dossierStatus, dossierSubStatus, groupId);
	}

	/**
	* Returns all the process steps where groupId = &#63; and serviceProcessId = &#63; and sequenceNo = &#63;.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param sequenceNo the sequence no
	* @return the matching process steps
	*/
	public static List<ProcessStep> findByG_SP_SNO(long groupId,
		long serviceProcessId, String sequenceNo) {
		return getPersistence()
				   .findByG_SP_SNO(groupId, serviceProcessId, sequenceNo);
	}

	/**
	* Returns a range of all the process steps where groupId = &#63; and serviceProcessId = &#63; and sequenceNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param sequenceNo the sequence no
	* @param start the lower bound of the range of process steps
	* @param end the upper bound of the range of process steps (not inclusive)
	* @return the range of matching process steps
	*/
	public static List<ProcessStep> findByG_SP_SNO(long groupId,
		long serviceProcessId, String sequenceNo, int start, int end) {
		return getPersistence()
				   .findByG_SP_SNO(groupId, serviceProcessId, sequenceNo,
			start, end);
	}

	/**
	* Returns an ordered range of all the process steps where groupId = &#63; and serviceProcessId = &#63; and sequenceNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param sequenceNo the sequence no
	* @param start the lower bound of the range of process steps
	* @param end the upper bound of the range of process steps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching process steps
	*/
	public static List<ProcessStep> findByG_SP_SNO(long groupId,
		long serviceProcessId, String sequenceNo, int start, int end,
		OrderByComparator<ProcessStep> orderByComparator) {
		return getPersistence()
				   .findByG_SP_SNO(groupId, serviceProcessId, sequenceNo,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the process steps where groupId = &#63; and serviceProcessId = &#63; and sequenceNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param sequenceNo the sequence no
	* @param start the lower bound of the range of process steps
	* @param end the upper bound of the range of process steps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching process steps
	*/
	public static List<ProcessStep> findByG_SP_SNO(long groupId,
		long serviceProcessId, String sequenceNo, int start, int end,
		OrderByComparator<ProcessStep> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_SP_SNO(groupId, serviceProcessId, sequenceNo,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first process step in the ordered set where groupId = &#63; and serviceProcessId = &#63; and sequenceNo = &#63;.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param sequenceNo the sequence no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process step
	* @throws NoSuchProcessStepException if a matching process step could not be found
	*/
	public static ProcessStep findByG_SP_SNO_First(long groupId,
		long serviceProcessId, String sequenceNo,
		OrderByComparator<ProcessStep> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessStepException {
		return getPersistence()
				   .findByG_SP_SNO_First(groupId, serviceProcessId, sequenceNo,
			orderByComparator);
	}

	/**
	* Returns the first process step in the ordered set where groupId = &#63; and serviceProcessId = &#63; and sequenceNo = &#63;.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param sequenceNo the sequence no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process step, or <code>null</code> if a matching process step could not be found
	*/
	public static ProcessStep fetchByG_SP_SNO_First(long groupId,
		long serviceProcessId, String sequenceNo,
		OrderByComparator<ProcessStep> orderByComparator) {
		return getPersistence()
				   .fetchByG_SP_SNO_First(groupId, serviceProcessId,
			sequenceNo, orderByComparator);
	}

	/**
	* Returns the last process step in the ordered set where groupId = &#63; and serviceProcessId = &#63; and sequenceNo = &#63;.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param sequenceNo the sequence no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process step
	* @throws NoSuchProcessStepException if a matching process step could not be found
	*/
	public static ProcessStep findByG_SP_SNO_Last(long groupId,
		long serviceProcessId, String sequenceNo,
		OrderByComparator<ProcessStep> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessStepException {
		return getPersistence()
				   .findByG_SP_SNO_Last(groupId, serviceProcessId, sequenceNo,
			orderByComparator);
	}

	/**
	* Returns the last process step in the ordered set where groupId = &#63; and serviceProcessId = &#63; and sequenceNo = &#63;.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param sequenceNo the sequence no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process step, or <code>null</code> if a matching process step could not be found
	*/
	public static ProcessStep fetchByG_SP_SNO_Last(long groupId,
		long serviceProcessId, String sequenceNo,
		OrderByComparator<ProcessStep> orderByComparator) {
		return getPersistence()
				   .fetchByG_SP_SNO_Last(groupId, serviceProcessId, sequenceNo,
			orderByComparator);
	}

	/**
	* Returns the process steps before and after the current process step in the ordered set where groupId = &#63; and serviceProcessId = &#63; and sequenceNo = &#63;.
	*
	* @param processStepId the primary key of the current process step
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param sequenceNo the sequence no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process step
	* @throws NoSuchProcessStepException if a process step with the primary key could not be found
	*/
	public static ProcessStep[] findByG_SP_SNO_PrevAndNext(long processStepId,
		long groupId, long serviceProcessId, String sequenceNo,
		OrderByComparator<ProcessStep> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessStepException {
		return getPersistence()
				   .findByG_SP_SNO_PrevAndNext(processStepId, groupId,
			serviceProcessId, sequenceNo, orderByComparator);
	}

	/**
	* Removes all the process steps where groupId = &#63; and serviceProcessId = &#63; and sequenceNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param sequenceNo the sequence no
	*/
	public static void removeByG_SP_SNO(long groupId, long serviceProcessId,
		String sequenceNo) {
		getPersistence().removeByG_SP_SNO(groupId, serviceProcessId, sequenceNo);
	}

	/**
	* Returns the number of process steps where groupId = &#63; and serviceProcessId = &#63; and sequenceNo = &#63;.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param sequenceNo the sequence no
	* @return the number of matching process steps
	*/
	public static int countByG_SP_SNO(long groupId, long serviceProcessId,
		String sequenceNo) {
		return getPersistence()
				   .countByG_SP_SNO(groupId, serviceProcessId, sequenceNo);
	}

	/**
	* Caches the process step in the entity cache if it is enabled.
	*
	* @param processStep the process step
	*/
	public static void cacheResult(ProcessStep processStep) {
		getPersistence().cacheResult(processStep);
	}

	/**
	* Caches the process steps in the entity cache if it is enabled.
	*
	* @param processSteps the process steps
	*/
	public static void cacheResult(List<ProcessStep> processSteps) {
		getPersistence().cacheResult(processSteps);
	}

	/**
	* Creates a new process step with the primary key. Does not add the process step to the database.
	*
	* @param processStepId the primary key for the new process step
	* @return the new process step
	*/
	public static ProcessStep create(long processStepId) {
		return getPersistence().create(processStepId);
	}

	/**
	* Removes the process step with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param processStepId the primary key of the process step
	* @return the process step that was removed
	* @throws NoSuchProcessStepException if a process step with the primary key could not be found
	*/
	public static ProcessStep remove(long processStepId)
		throws org.opencps.dossiermgt.exception.NoSuchProcessStepException {
		return getPersistence().remove(processStepId);
	}

	public static ProcessStep updateImpl(ProcessStep processStep) {
		return getPersistence().updateImpl(processStep);
	}

	/**
	* Returns the process step with the primary key or throws a {@link NoSuchProcessStepException} if it could not be found.
	*
	* @param processStepId the primary key of the process step
	* @return the process step
	* @throws NoSuchProcessStepException if a process step with the primary key could not be found
	*/
	public static ProcessStep findByPrimaryKey(long processStepId)
		throws org.opencps.dossiermgt.exception.NoSuchProcessStepException {
		return getPersistence().findByPrimaryKey(processStepId);
	}

	/**
	* Returns the process step with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param processStepId the primary key of the process step
	* @return the process step, or <code>null</code> if a process step with the primary key could not be found
	*/
	public static ProcessStep fetchByPrimaryKey(long processStepId) {
		return getPersistence().fetchByPrimaryKey(processStepId);
	}

	public static java.util.Map<java.io.Serializable, ProcessStep> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the process steps.
	*
	* @return the process steps
	*/
	public static List<ProcessStep> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the process steps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of process steps
	* @param end the upper bound of the range of process steps (not inclusive)
	* @return the range of process steps
	*/
	public static List<ProcessStep> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the process steps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of process steps
	* @param end the upper bound of the range of process steps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of process steps
	*/
	public static List<ProcessStep> findAll(int start, int end,
		OrderByComparator<ProcessStep> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the process steps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of process steps
	* @param end the upper bound of the range of process steps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of process steps
	*/
	public static List<ProcessStep> findAll(int start, int end,
		OrderByComparator<ProcessStep> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the process steps from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of process steps.
	*
	* @return the number of process steps
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ProcessStepPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ProcessStepPersistence, ProcessStepPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ProcessStepPersistence.class);

		ServiceTracker<ProcessStepPersistence, ProcessStepPersistence> serviceTracker =
			new ServiceTracker<ProcessStepPersistence, ProcessStepPersistence>(bundle.getBundleContext(),
				ProcessStepPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}