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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.backend.processmgt.exception.NoSuchStepAllowanceException;
import org.opencps.backend.processmgt.model.StepAllowance;

/**
 * The persistence interface for the step allowance service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see org.opencps.backend.processmgt.service.persistence.impl.StepAllowancePersistenceImpl
 * @see StepAllowanceUtil
 * @generated
 */
@ProviderType
public interface StepAllowancePersistence extends BasePersistence<StepAllowance> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link StepAllowanceUtil} to access the step allowance persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the step allowances where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching step allowances
	*/
	public java.util.List<StepAllowance> findByUuid(java.lang.String uuid);

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
	public java.util.List<StepAllowance> findByUuid(java.lang.String uuid,
		int start, int end);

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
	public java.util.List<StepAllowance> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StepAllowance> orderByComparator);

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
	public java.util.List<StepAllowance> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StepAllowance> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first step allowance in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching step allowance
	* @throws NoSuchStepAllowanceException if a matching step allowance could not be found
	*/
	public StepAllowance findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<StepAllowance> orderByComparator)
		throws NoSuchStepAllowanceException;

	/**
	* Returns the first step allowance in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching step allowance, or <code>null</code> if a matching step allowance could not be found
	*/
	public StepAllowance fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<StepAllowance> orderByComparator);

	/**
	* Returns the last step allowance in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching step allowance
	* @throws NoSuchStepAllowanceException if a matching step allowance could not be found
	*/
	public StepAllowance findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<StepAllowance> orderByComparator)
		throws NoSuchStepAllowanceException;

	/**
	* Returns the last step allowance in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching step allowance, or <code>null</code> if a matching step allowance could not be found
	*/
	public StepAllowance fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<StepAllowance> orderByComparator);

	/**
	* Returns the step allowances before and after the current step allowance in the ordered set where uuid = &#63;.
	*
	* @param stepAllowancePK the primary key of the current step allowance
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next step allowance
	* @throws NoSuchStepAllowanceException if a step allowance with the primary key could not be found
	*/
	public StepAllowance[] findByUuid_PrevAndNext(
		StepAllowancePK stepAllowancePK, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<StepAllowance> orderByComparator)
		throws NoSuchStepAllowanceException;

	/**
	* Removes all the step allowances where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of step allowances where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching step allowances
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the step allowance where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchStepAllowanceException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching step allowance
	* @throws NoSuchStepAllowanceException if a matching step allowance could not be found
	*/
	public StepAllowance findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchStepAllowanceException;

	/**
	* Returns the step allowance where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching step allowance, or <code>null</code> if a matching step allowance could not be found
	*/
	public StepAllowance fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the step allowance where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching step allowance, or <code>null</code> if a matching step allowance could not be found
	*/
	public StepAllowance fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the step allowance where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the step allowance that was removed
	*/
	public StepAllowance removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchStepAllowanceException;

	/**
	* Returns the number of step allowances where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching step allowances
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the step allowances where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching step allowances
	*/
	public java.util.List<StepAllowance> findByUuid_C(java.lang.String uuid,
		long companyId);

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
	public java.util.List<StepAllowance> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

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
	public java.util.List<StepAllowance> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StepAllowance> orderByComparator);

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
	public java.util.List<StepAllowance> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StepAllowance> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first step allowance in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching step allowance
	* @throws NoSuchStepAllowanceException if a matching step allowance could not be found
	*/
	public StepAllowance findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<StepAllowance> orderByComparator)
		throws NoSuchStepAllowanceException;

	/**
	* Returns the first step allowance in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching step allowance, or <code>null</code> if a matching step allowance could not be found
	*/
	public StepAllowance fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<StepAllowance> orderByComparator);

	/**
	* Returns the last step allowance in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching step allowance
	* @throws NoSuchStepAllowanceException if a matching step allowance could not be found
	*/
	public StepAllowance findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<StepAllowance> orderByComparator)
		throws NoSuchStepAllowanceException;

	/**
	* Returns the last step allowance in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching step allowance, or <code>null</code> if a matching step allowance could not be found
	*/
	public StepAllowance fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<StepAllowance> orderByComparator);

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
	public StepAllowance[] findByUuid_C_PrevAndNext(
		StepAllowancePK stepAllowancePK, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<StepAllowance> orderByComparator)
		throws NoSuchStepAllowanceException;

	/**
	* Removes all the step allowances where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of step allowances where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching step allowances
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the step allowances where processStepId = &#63;.
	*
	* @param processStepId the process step ID
	* @return the matching step allowances
	*/
	public java.util.List<StepAllowance> findByP_S_ID(long processStepId);

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
	public java.util.List<StepAllowance> findByP_S_ID(long processStepId,
		int start, int end);

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
	public java.util.List<StepAllowance> findByP_S_ID(long processStepId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StepAllowance> orderByComparator);

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
	public java.util.List<StepAllowance> findByP_S_ID(long processStepId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StepAllowance> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first step allowance in the ordered set where processStepId = &#63;.
	*
	* @param processStepId the process step ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching step allowance
	* @throws NoSuchStepAllowanceException if a matching step allowance could not be found
	*/
	public StepAllowance findByP_S_ID_First(long processStepId,
		com.liferay.portal.kernel.util.OrderByComparator<StepAllowance> orderByComparator)
		throws NoSuchStepAllowanceException;

	/**
	* Returns the first step allowance in the ordered set where processStepId = &#63;.
	*
	* @param processStepId the process step ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching step allowance, or <code>null</code> if a matching step allowance could not be found
	*/
	public StepAllowance fetchByP_S_ID_First(long processStepId,
		com.liferay.portal.kernel.util.OrderByComparator<StepAllowance> orderByComparator);

	/**
	* Returns the last step allowance in the ordered set where processStepId = &#63;.
	*
	* @param processStepId the process step ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching step allowance
	* @throws NoSuchStepAllowanceException if a matching step allowance could not be found
	*/
	public StepAllowance findByP_S_ID_Last(long processStepId,
		com.liferay.portal.kernel.util.OrderByComparator<StepAllowance> orderByComparator)
		throws NoSuchStepAllowanceException;

	/**
	* Returns the last step allowance in the ordered set where processStepId = &#63;.
	*
	* @param processStepId the process step ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching step allowance, or <code>null</code> if a matching step allowance could not be found
	*/
	public StepAllowance fetchByP_S_ID_Last(long processStepId,
		com.liferay.portal.kernel.util.OrderByComparator<StepAllowance> orderByComparator);

	/**
	* Returns the step allowances before and after the current step allowance in the ordered set where processStepId = &#63;.
	*
	* @param stepAllowancePK the primary key of the current step allowance
	* @param processStepId the process step ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next step allowance
	* @throws NoSuchStepAllowanceException if a step allowance with the primary key could not be found
	*/
	public StepAllowance[] findByP_S_ID_PrevAndNext(
		StepAllowancePK stepAllowancePK, long processStepId,
		com.liferay.portal.kernel.util.OrderByComparator<StepAllowance> orderByComparator)
		throws NoSuchStepAllowanceException;

	/**
	* Removes all the step allowances where processStepId = &#63; from the database.
	*
	* @param processStepId the process step ID
	*/
	public void removeByP_S_ID(long processStepId);

	/**
	* Returns the number of step allowances where processStepId = &#63;.
	*
	* @param processStepId the process step ID
	* @return the number of matching step allowances
	*/
	public int countByP_S_ID(long processStepId);

	/**
	* Caches the step allowance in the entity cache if it is enabled.
	*
	* @param stepAllowance the step allowance
	*/
	public void cacheResult(StepAllowance stepAllowance);

	/**
	* Caches the step allowances in the entity cache if it is enabled.
	*
	* @param stepAllowances the step allowances
	*/
	public void cacheResult(java.util.List<StepAllowance> stepAllowances);

	/**
	* Creates a new step allowance with the primary key. Does not add the step allowance to the database.
	*
	* @param stepAllowancePK the primary key for the new step allowance
	* @return the new step allowance
	*/
	public StepAllowance create(StepAllowancePK stepAllowancePK);

	/**
	* Removes the step allowance with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stepAllowancePK the primary key of the step allowance
	* @return the step allowance that was removed
	* @throws NoSuchStepAllowanceException if a step allowance with the primary key could not be found
	*/
	public StepAllowance remove(StepAllowancePK stepAllowancePK)
		throws NoSuchStepAllowanceException;

	public StepAllowance updateImpl(StepAllowance stepAllowance);

	/**
	* Returns the step allowance with the primary key or throws a {@link NoSuchStepAllowanceException} if it could not be found.
	*
	* @param stepAllowancePK the primary key of the step allowance
	* @return the step allowance
	* @throws NoSuchStepAllowanceException if a step allowance with the primary key could not be found
	*/
	public StepAllowance findByPrimaryKey(StepAllowancePK stepAllowancePK)
		throws NoSuchStepAllowanceException;

	/**
	* Returns the step allowance with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param stepAllowancePK the primary key of the step allowance
	* @return the step allowance, or <code>null</code> if a step allowance with the primary key could not be found
	*/
	public StepAllowance fetchByPrimaryKey(StepAllowancePK stepAllowancePK);

	@Override
	public java.util.Map<java.io.Serializable, StepAllowance> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the step allowances.
	*
	* @return the step allowances
	*/
	public java.util.List<StepAllowance> findAll();

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
	public java.util.List<StepAllowance> findAll(int start, int end);

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
	public java.util.List<StepAllowance> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StepAllowance> orderByComparator);

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
	public java.util.List<StepAllowance> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StepAllowance> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the step allowances from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of step allowances.
	*
	* @return the number of step allowances
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}