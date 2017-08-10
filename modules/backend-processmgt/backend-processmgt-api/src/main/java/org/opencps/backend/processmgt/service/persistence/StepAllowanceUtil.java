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

import org.opencps.backend.processmgt.model.StepAllowance;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the step allowance service. This utility wraps {@link org.opencps.backend.processmgt.service.persistence.impl.StepAllowancePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see StepAllowancePersistence
 * @see org.opencps.backend.processmgt.service.persistence.impl.StepAllowancePersistenceImpl
 * @generated
 */
@ProviderType
public class StepAllowanceUtil {
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
	public static void clearCache(StepAllowance stepAllowance) {
		getPersistence().clearCache(stepAllowance);
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
	public static List<StepAllowance> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<StepAllowance> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<StepAllowance> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<StepAllowance> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static StepAllowance update(StepAllowance stepAllowance) {
		return getPersistence().update(stepAllowance);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static StepAllowance update(StepAllowance stepAllowance,
		ServiceContext serviceContext) {
		return getPersistence().update(stepAllowance, serviceContext);
	}

	/**
	* Returns all the step allowances where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching step allowances
	*/
	public static List<StepAllowance> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the step allowances where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepAllowanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of step allowances
	* @param end the upper bound of the range of step allowances (not inclusive)
	* @return the range of matching step allowances
	*/
	public static List<StepAllowance> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the step allowances where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepAllowanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of step allowances
	* @param end the upper bound of the range of step allowances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching step allowances
	*/
	public static List<StepAllowance> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<StepAllowance> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the step allowances where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepAllowanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of step allowances
	* @param end the upper bound of the range of step allowances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching step allowances
	*/
	public static List<StepAllowance> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<StepAllowance> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first step allowance in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching step allowance
	* @throws NoSuchStepAllowanceException if a matching step allowance could not be found
	*/
	public static StepAllowance findByUuid_First(java.lang.String uuid,
		OrderByComparator<StepAllowance> orderByComparator)
		throws org.opencps.backend.processmgt.exception.NoSuchStepAllowanceException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first step allowance in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching step allowance, or <code>null</code> if a matching step allowance could not be found
	*/
	public static StepAllowance fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<StepAllowance> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last step allowance in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching step allowance
	* @throws NoSuchStepAllowanceException if a matching step allowance could not be found
	*/
	public static StepAllowance findByUuid_Last(java.lang.String uuid,
		OrderByComparator<StepAllowance> orderByComparator)
		throws org.opencps.backend.processmgt.exception.NoSuchStepAllowanceException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last step allowance in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching step allowance, or <code>null</code> if a matching step allowance could not be found
	*/
	public static StepAllowance fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<StepAllowance> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the step allowances before and after the current step allowance in the ordered set where uuid = &#63;.
	*
	* @param stepAllowancePK the primary key of the current step allowance
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next step allowance
	* @throws NoSuchStepAllowanceException if a step allowance with the primary key could not be found
	*/
	public static StepAllowance[] findByUuid_PrevAndNext(
		StepAllowancePK stepAllowancePK, java.lang.String uuid,
		OrderByComparator<StepAllowance> orderByComparator)
		throws org.opencps.backend.processmgt.exception.NoSuchStepAllowanceException {
		return getPersistence()
				   .findByUuid_PrevAndNext(stepAllowancePK, uuid,
			orderByComparator);
	}

	/**
	* Removes all the step allowances where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of step allowances where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching step allowances
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the step allowance where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchStepAllowanceException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching step allowance
	* @throws NoSuchStepAllowanceException if a matching step allowance could not be found
	*/
	public static StepAllowance findByUUID_G(java.lang.String uuid, long groupId)
		throws org.opencps.backend.processmgt.exception.NoSuchStepAllowanceException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the step allowance where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching step allowance, or <code>null</code> if a matching step allowance could not be found
	*/
	public static StepAllowance fetchByUUID_G(java.lang.String uuid,
		long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the step allowance where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching step allowance, or <code>null</code> if a matching step allowance could not be found
	*/
	public static StepAllowance fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the step allowance where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the step allowance that was removed
	*/
	public static StepAllowance removeByUUID_G(java.lang.String uuid,
		long groupId)
		throws org.opencps.backend.processmgt.exception.NoSuchStepAllowanceException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of step allowances where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching step allowances
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the step allowances where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching step allowances
	*/
	public static List<StepAllowance> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the step allowances where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepAllowanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of step allowances
	* @param end the upper bound of the range of step allowances (not inclusive)
	* @return the range of matching step allowances
	*/
	public static List<StepAllowance> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the step allowances where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepAllowanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of step allowances
	* @param end the upper bound of the range of step allowances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching step allowances
	*/
	public static List<StepAllowance> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<StepAllowance> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the step allowances where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepAllowanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of step allowances
	* @param end the upper bound of the range of step allowances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching step allowances
	*/
	public static List<StepAllowance> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<StepAllowance> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first step allowance in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching step allowance
	* @throws NoSuchStepAllowanceException if a matching step allowance could not be found
	*/
	public static StepAllowance findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<StepAllowance> orderByComparator)
		throws org.opencps.backend.processmgt.exception.NoSuchStepAllowanceException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first step allowance in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching step allowance, or <code>null</code> if a matching step allowance could not be found
	*/
	public static StepAllowance fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<StepAllowance> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last step allowance in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching step allowance
	* @throws NoSuchStepAllowanceException if a matching step allowance could not be found
	*/
	public static StepAllowance findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<StepAllowance> orderByComparator)
		throws org.opencps.backend.processmgt.exception.NoSuchStepAllowanceException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last step allowance in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching step allowance, or <code>null</code> if a matching step allowance could not be found
	*/
	public static StepAllowance fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<StepAllowance> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the step allowances before and after the current step allowance in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param stepAllowancePK the primary key of the current step allowance
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next step allowance
	* @throws NoSuchStepAllowanceException if a step allowance with the primary key could not be found
	*/
	public static StepAllowance[] findByUuid_C_PrevAndNext(
		StepAllowancePK stepAllowancePK, java.lang.String uuid, long companyId,
		OrderByComparator<StepAllowance> orderByComparator)
		throws org.opencps.backend.processmgt.exception.NoSuchStepAllowanceException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(stepAllowancePK, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the step allowances where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of step allowances where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching step allowances
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the step allowances where processStepId = &#63;.
	*
	* @param processStepId the process step ID
	* @return the matching step allowances
	*/
	public static List<StepAllowance> findByP_S_ID(long processStepId) {
		return getPersistence().findByP_S_ID(processStepId);
	}

	/**
	* Returns a range of all the step allowances where processStepId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepAllowanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param processStepId the process step ID
	* @param start the lower bound of the range of step allowances
	* @param end the upper bound of the range of step allowances (not inclusive)
	* @return the range of matching step allowances
	*/
	public static List<StepAllowance> findByP_S_ID(long processStepId,
		int start, int end) {
		return getPersistence().findByP_S_ID(processStepId, start, end);
	}

	/**
	* Returns an ordered range of all the step allowances where processStepId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepAllowanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param processStepId the process step ID
	* @param start the lower bound of the range of step allowances
	* @param end the upper bound of the range of step allowances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching step allowances
	*/
	public static List<StepAllowance> findByP_S_ID(long processStepId,
		int start, int end, OrderByComparator<StepAllowance> orderByComparator) {
		return getPersistence()
				   .findByP_S_ID(processStepId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the step allowances where processStepId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepAllowanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param processStepId the process step ID
	* @param start the lower bound of the range of step allowances
	* @param end the upper bound of the range of step allowances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching step allowances
	*/
	public static List<StepAllowance> findByP_S_ID(long processStepId,
		int start, int end, OrderByComparator<StepAllowance> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByP_S_ID(processStepId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first step allowance in the ordered set where processStepId = &#63;.
	*
	* @param processStepId the process step ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching step allowance
	* @throws NoSuchStepAllowanceException if a matching step allowance could not be found
	*/
	public static StepAllowance findByP_S_ID_First(long processStepId,
		OrderByComparator<StepAllowance> orderByComparator)
		throws org.opencps.backend.processmgt.exception.NoSuchStepAllowanceException {
		return getPersistence()
				   .findByP_S_ID_First(processStepId, orderByComparator);
	}

	/**
	* Returns the first step allowance in the ordered set where processStepId = &#63;.
	*
	* @param processStepId the process step ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching step allowance, or <code>null</code> if a matching step allowance could not be found
	*/
	public static StepAllowance fetchByP_S_ID_First(long processStepId,
		OrderByComparator<StepAllowance> orderByComparator) {
		return getPersistence()
				   .fetchByP_S_ID_First(processStepId, orderByComparator);
	}

	/**
	* Returns the last step allowance in the ordered set where processStepId = &#63;.
	*
	* @param processStepId the process step ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching step allowance
	* @throws NoSuchStepAllowanceException if a matching step allowance could not be found
	*/
	public static StepAllowance findByP_S_ID_Last(long processStepId,
		OrderByComparator<StepAllowance> orderByComparator)
		throws org.opencps.backend.processmgt.exception.NoSuchStepAllowanceException {
		return getPersistence()
				   .findByP_S_ID_Last(processStepId, orderByComparator);
	}

	/**
	* Returns the last step allowance in the ordered set where processStepId = &#63;.
	*
	* @param processStepId the process step ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching step allowance, or <code>null</code> if a matching step allowance could not be found
	*/
	public static StepAllowance fetchByP_S_ID_Last(long processStepId,
		OrderByComparator<StepAllowance> orderByComparator) {
		return getPersistence()
				   .fetchByP_S_ID_Last(processStepId, orderByComparator);
	}

	/**
	* Returns the step allowances before and after the current step allowance in the ordered set where processStepId = &#63;.
	*
	* @param stepAllowancePK the primary key of the current step allowance
	* @param processStepId the process step ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next step allowance
	* @throws NoSuchStepAllowanceException if a step allowance with the primary key could not be found
	*/
	public static StepAllowance[] findByP_S_ID_PrevAndNext(
		StepAllowancePK stepAllowancePK, long processStepId,
		OrderByComparator<StepAllowance> orderByComparator)
		throws org.opencps.backend.processmgt.exception.NoSuchStepAllowanceException {
		return getPersistence()
				   .findByP_S_ID_PrevAndNext(stepAllowancePK, processStepId,
			orderByComparator);
	}

	/**
	* Removes all the step allowances where processStepId = &#63; from the database.
	*
	* @param processStepId the process step ID
	*/
	public static void removeByP_S_ID(long processStepId) {
		getPersistence().removeByP_S_ID(processStepId);
	}

	/**
	* Returns the number of step allowances where processStepId = &#63;.
	*
	* @param processStepId the process step ID
	* @return the number of matching step allowances
	*/
	public static int countByP_S_ID(long processStepId) {
		return getPersistence().countByP_S_ID(processStepId);
	}

	/**
	* Caches the step allowance in the entity cache if it is enabled.
	*
	* @param stepAllowance the step allowance
	*/
	public static void cacheResult(StepAllowance stepAllowance) {
		getPersistence().cacheResult(stepAllowance);
	}

	/**
	* Caches the step allowances in the entity cache if it is enabled.
	*
	* @param stepAllowances the step allowances
	*/
	public static void cacheResult(List<StepAllowance> stepAllowances) {
		getPersistence().cacheResult(stepAllowances);
	}

	/**
	* Creates a new step allowance with the primary key. Does not add the step allowance to the database.
	*
	* @param stepAllowancePK the primary key for the new step allowance
	* @return the new step allowance
	*/
	public static StepAllowance create(StepAllowancePK stepAllowancePK) {
		return getPersistence().create(stepAllowancePK);
	}

	/**
	* Removes the step allowance with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stepAllowancePK the primary key of the step allowance
	* @return the step allowance that was removed
	* @throws NoSuchStepAllowanceException if a step allowance with the primary key could not be found
	*/
	public static StepAllowance remove(StepAllowancePK stepAllowancePK)
		throws org.opencps.backend.processmgt.exception.NoSuchStepAllowanceException {
		return getPersistence().remove(stepAllowancePK);
	}

	public static StepAllowance updateImpl(StepAllowance stepAllowance) {
		return getPersistence().updateImpl(stepAllowance);
	}

	/**
	* Returns the step allowance with the primary key or throws a {@link NoSuchStepAllowanceException} if it could not be found.
	*
	* @param stepAllowancePK the primary key of the step allowance
	* @return the step allowance
	* @throws NoSuchStepAllowanceException if a step allowance with the primary key could not be found
	*/
	public static StepAllowance findByPrimaryKey(
		StepAllowancePK stepAllowancePK)
		throws org.opencps.backend.processmgt.exception.NoSuchStepAllowanceException {
		return getPersistence().findByPrimaryKey(stepAllowancePK);
	}

	/**
	* Returns the step allowance with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param stepAllowancePK the primary key of the step allowance
	* @return the step allowance, or <code>null</code> if a step allowance with the primary key could not be found
	*/
	public static StepAllowance fetchByPrimaryKey(
		StepAllowancePK stepAllowancePK) {
		return getPersistence().fetchByPrimaryKey(stepAllowancePK);
	}

	public static java.util.Map<java.io.Serializable, StepAllowance> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the step allowances.
	*
	* @return the step allowances
	*/
	public static List<StepAllowance> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the step allowances.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepAllowanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of step allowances
	* @param end the upper bound of the range of step allowances (not inclusive)
	* @return the range of step allowances
	*/
	public static List<StepAllowance> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the step allowances.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepAllowanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of step allowances
	* @param end the upper bound of the range of step allowances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of step allowances
	*/
	public static List<StepAllowance> findAll(int start, int end,
		OrderByComparator<StepAllowance> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the step allowances.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepAllowanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of step allowances
	* @param end the upper bound of the range of step allowances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of step allowances
	*/
	public static List<StepAllowance> findAll(int start, int end,
		OrderByComparator<StepAllowance> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the step allowances from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of step allowances.
	*
	* @return the number of step allowances
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static StepAllowancePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<StepAllowancePersistence, StepAllowancePersistence> _serviceTracker =
		ServiceTrackerFactory.open(StepAllowancePersistence.class);
}