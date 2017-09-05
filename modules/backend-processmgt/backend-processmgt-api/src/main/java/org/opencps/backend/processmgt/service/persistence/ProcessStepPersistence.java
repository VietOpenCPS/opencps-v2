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

import org.opencps.backend.processmgt.exception.NoSuchProcessStepException;
import org.opencps.backend.processmgt.model.ProcessStep;

/**
 * The persistence interface for the process step service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see org.opencps.backend.processmgt.service.persistence.impl.ProcessStepPersistenceImpl
 * @see ProcessStepUtil
 * @generated
 */
@ProviderType
public interface ProcessStepPersistence extends BasePersistence<ProcessStep> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ProcessStepUtil} to access the process step persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the process steps where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching process steps
	*/
	public java.util.List<ProcessStep> findByUuid(java.lang.String uuid);

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
	public java.util.List<ProcessStep> findByUuid(java.lang.String uuid,
		int start, int end);

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
	public java.util.List<ProcessStep> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessStep> orderByComparator);

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
	public java.util.List<ProcessStep> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessStep> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first process step in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process step
	* @throws NoSuchProcessStepException if a matching process step could not be found
	*/
	public ProcessStep findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessStep> orderByComparator)
		throws NoSuchProcessStepException;

	/**
	* Returns the first process step in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process step, or <code>null</code> if a matching process step could not be found
	*/
	public ProcessStep fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessStep> orderByComparator);

	/**
	* Returns the last process step in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process step
	* @throws NoSuchProcessStepException if a matching process step could not be found
	*/
	public ProcessStep findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessStep> orderByComparator)
		throws NoSuchProcessStepException;

	/**
	* Returns the last process step in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process step, or <code>null</code> if a matching process step could not be found
	*/
	public ProcessStep fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessStep> orderByComparator);

	/**
	* Returns the process steps before and after the current process step in the ordered set where uuid = &#63;.
	*
	* @param processStepId the primary key of the current process step
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process step
	* @throws NoSuchProcessStepException if a process step with the primary key could not be found
	*/
	public ProcessStep[] findByUuid_PrevAndNext(long processStepId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessStep> orderByComparator)
		throws NoSuchProcessStepException;

	/**
	* Removes all the process steps where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of process steps where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching process steps
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the process step where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchProcessStepException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching process step
	* @throws NoSuchProcessStepException if a matching process step could not be found
	*/
	public ProcessStep findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchProcessStepException;

	/**
	* Returns the process step where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching process step, or <code>null</code> if a matching process step could not be found
	*/
	public ProcessStep fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the process step where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching process step, or <code>null</code> if a matching process step could not be found
	*/
	public ProcessStep fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the process step where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the process step that was removed
	*/
	public ProcessStep removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchProcessStepException;

	/**
	* Returns the number of process steps where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching process steps
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the process steps where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching process steps
	*/
	public java.util.List<ProcessStep> findByUuid_C(java.lang.String uuid,
		long companyId);

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
	public java.util.List<ProcessStep> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

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
	public java.util.List<ProcessStep> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessStep> orderByComparator);

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
	public java.util.List<ProcessStep> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessStep> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first process step in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process step
	* @throws NoSuchProcessStepException if a matching process step could not be found
	*/
	public ProcessStep findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessStep> orderByComparator)
		throws NoSuchProcessStepException;

	/**
	* Returns the first process step in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process step, or <code>null</code> if a matching process step could not be found
	*/
	public ProcessStep fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessStep> orderByComparator);

	/**
	* Returns the last process step in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process step
	* @throws NoSuchProcessStepException if a matching process step could not be found
	*/
	public ProcessStep findByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessStep> orderByComparator)
		throws NoSuchProcessStepException;

	/**
	* Returns the last process step in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process step, or <code>null</code> if a matching process step could not be found
	*/
	public ProcessStep fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessStep> orderByComparator);

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
	public ProcessStep[] findByUuid_C_PrevAndNext(long processStepId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessStep> orderByComparator)
		throws NoSuchProcessStepException;

	/**
	* Removes all the process steps where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of process steps where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching process steps
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the process steps where serviceProcessId = &#63;.
	*
	* @param serviceProcessId the service process ID
	* @return the matching process steps
	*/
	public java.util.List<ProcessStep> findByS_P_ID(long serviceProcessId);

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
	public java.util.List<ProcessStep> findByS_P_ID(long serviceProcessId,
		int start, int end);

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
	public java.util.List<ProcessStep> findByS_P_ID(long serviceProcessId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessStep> orderByComparator);

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
	public java.util.List<ProcessStep> findByS_P_ID(long serviceProcessId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessStep> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first process step in the ordered set where serviceProcessId = &#63;.
	*
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process step
	* @throws NoSuchProcessStepException if a matching process step could not be found
	*/
	public ProcessStep findByS_P_ID_First(long serviceProcessId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessStep> orderByComparator)
		throws NoSuchProcessStepException;

	/**
	* Returns the first process step in the ordered set where serviceProcessId = &#63;.
	*
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process step, or <code>null</code> if a matching process step could not be found
	*/
	public ProcessStep fetchByS_P_ID_First(long serviceProcessId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessStep> orderByComparator);

	/**
	* Returns the last process step in the ordered set where serviceProcessId = &#63;.
	*
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process step
	* @throws NoSuchProcessStepException if a matching process step could not be found
	*/
	public ProcessStep findByS_P_ID_Last(long serviceProcessId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessStep> orderByComparator)
		throws NoSuchProcessStepException;

	/**
	* Returns the last process step in the ordered set where serviceProcessId = &#63;.
	*
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process step, or <code>null</code> if a matching process step could not be found
	*/
	public ProcessStep fetchByS_P_ID_Last(long serviceProcessId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessStep> orderByComparator);

	/**
	* Returns the process steps before and after the current process step in the ordered set where serviceProcessId = &#63;.
	*
	* @param processStepId the primary key of the current process step
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process step
	* @throws NoSuchProcessStepException if a process step with the primary key could not be found
	*/
	public ProcessStep[] findByS_P_ID_PrevAndNext(long processStepId,
		long serviceProcessId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessStep> orderByComparator)
		throws NoSuchProcessStepException;

	/**
	* Removes all the process steps where serviceProcessId = &#63; from the database.
	*
	* @param serviceProcessId the service process ID
	*/
	public void removeByS_P_ID(long serviceProcessId);

	/**
	* Returns the number of process steps where serviceProcessId = &#63;.
	*
	* @param serviceProcessId the service process ID
	* @return the number of matching process steps
	*/
	public int countByS_P_ID(long serviceProcessId);

	/**
	* Caches the process step in the entity cache if it is enabled.
	*
	* @param processStep the process step
	*/
	public void cacheResult(ProcessStep processStep);

	/**
	* Caches the process steps in the entity cache if it is enabled.
	*
	* @param processSteps the process steps
	*/
	public void cacheResult(java.util.List<ProcessStep> processSteps);

	/**
	* Creates a new process step with the primary key. Does not add the process step to the database.
	*
	* @param processStepId the primary key for the new process step
	* @return the new process step
	*/
	public ProcessStep create(long processStepId);

	/**
	* Removes the process step with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param processStepId the primary key of the process step
	* @return the process step that was removed
	* @throws NoSuchProcessStepException if a process step with the primary key could not be found
	*/
	public ProcessStep remove(long processStepId)
		throws NoSuchProcessStepException;

	public ProcessStep updateImpl(ProcessStep processStep);

	/**
	* Returns the process step with the primary key or throws a {@link NoSuchProcessStepException} if it could not be found.
	*
	* @param processStepId the primary key of the process step
	* @return the process step
	* @throws NoSuchProcessStepException if a process step with the primary key could not be found
	*/
	public ProcessStep findByPrimaryKey(long processStepId)
		throws NoSuchProcessStepException;

	/**
	* Returns the process step with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param processStepId the primary key of the process step
	* @return the process step, or <code>null</code> if a process step with the primary key could not be found
	*/
	public ProcessStep fetchByPrimaryKey(long processStepId);

	@Override
	public java.util.Map<java.io.Serializable, ProcessStep> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the process steps.
	*
	* @return the process steps
	*/
	public java.util.List<ProcessStep> findAll();

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
	public java.util.List<ProcessStep> findAll(int start, int end);

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
	public java.util.List<ProcessStep> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessStep> orderByComparator);

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
	public java.util.List<ProcessStep> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessStep> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the process steps from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of process steps.
	*
	* @return the number of process steps
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}