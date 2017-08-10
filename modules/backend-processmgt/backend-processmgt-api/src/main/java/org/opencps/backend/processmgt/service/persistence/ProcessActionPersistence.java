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

import org.opencps.backend.processmgt.exception.NoSuchProcessActionException;
import org.opencps.backend.processmgt.model.ProcessAction;

/**
 * The persistence interface for the process action service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see org.opencps.backend.processmgt.service.persistence.impl.ProcessActionPersistenceImpl
 * @see ProcessActionUtil
 * @generated
 */
@ProviderType
public interface ProcessActionPersistence extends BasePersistence<ProcessAction> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ProcessActionUtil} to access the process action persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the process actions where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching process actions
	*/
	public java.util.List<ProcessAction> findByUuid(java.lang.String uuid);

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
	public java.util.List<ProcessAction> findByUuid(java.lang.String uuid,
		int start, int end);

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
	public java.util.List<ProcessAction> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator);

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
	public java.util.List<ProcessAction> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first process action in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public ProcessAction findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException;

	/**
	* Returns the first process action in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public ProcessAction fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator);

	/**
	* Returns the last process action in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public ProcessAction findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException;

	/**
	* Returns the last process action in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public ProcessAction fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator);

	/**
	* Returns the process actions before and after the current process action in the ordered set where uuid = &#63;.
	*
	* @param processActionId the primary key of the current process action
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process action
	* @throws NoSuchProcessActionException if a process action with the primary key could not be found
	*/
	public ProcessAction[] findByUuid_PrevAndNext(long processActionId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException;

	/**
	* Removes all the process actions where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of process actions where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching process actions
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the process action where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchProcessActionException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public ProcessAction findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchProcessActionException;

	/**
	* Returns the process action where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public ProcessAction fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the process action where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public ProcessAction fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the process action where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the process action that was removed
	*/
	public ProcessAction removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchProcessActionException;

	/**
	* Returns the number of process actions where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching process actions
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the process actions where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching process actions
	*/
	public java.util.List<ProcessAction> findByUuid_C(java.lang.String uuid,
		long companyId);

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
	public java.util.List<ProcessAction> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

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
	public java.util.List<ProcessAction> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator);

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
	public java.util.List<ProcessAction> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first process action in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public ProcessAction findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException;

	/**
	* Returns the first process action in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public ProcessAction fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator);

	/**
	* Returns the last process action in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public ProcessAction findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException;

	/**
	* Returns the last process action in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public ProcessAction fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator);

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
	public ProcessAction[] findByUuid_C_PrevAndNext(long processActionId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException;

	/**
	* Removes all the process actions where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of process actions where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching process actions
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the process actions where serviceProcessId = &#63;.
	*
	* @param serviceProcessId the service process ID
	* @return the matching process actions
	*/
	public java.util.List<ProcessAction> findByS_P_ID(long serviceProcessId);

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
	public java.util.List<ProcessAction> findByS_P_ID(long serviceProcessId,
		int start, int end);

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
	public java.util.List<ProcessAction> findByS_P_ID(long serviceProcessId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator);

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
	public java.util.List<ProcessAction> findByS_P_ID(long serviceProcessId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first process action in the ordered set where serviceProcessId = &#63;.
	*
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public ProcessAction findByS_P_ID_First(long serviceProcessId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException;

	/**
	* Returns the first process action in the ordered set where serviceProcessId = &#63;.
	*
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public ProcessAction fetchByS_P_ID_First(long serviceProcessId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator);

	/**
	* Returns the last process action in the ordered set where serviceProcessId = &#63;.
	*
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public ProcessAction findByS_P_ID_Last(long serviceProcessId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException;

	/**
	* Returns the last process action in the ordered set where serviceProcessId = &#63;.
	*
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public ProcessAction fetchByS_P_ID_Last(long serviceProcessId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator);

	/**
	* Returns the process actions before and after the current process action in the ordered set where serviceProcessId = &#63;.
	*
	* @param processActionId the primary key of the current process action
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process action
	* @throws NoSuchProcessActionException if a process action with the primary key could not be found
	*/
	public ProcessAction[] findByS_P_ID_PrevAndNext(long processActionId,
		long serviceProcessId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException;

	/**
	* Removes all the process actions where serviceProcessId = &#63; from the database.
	*
	* @param serviceProcessId the service process ID
	*/
	public void removeByS_P_ID(long serviceProcessId);

	/**
	* Returns the number of process actions where serviceProcessId = &#63;.
	*
	* @param serviceProcessId the service process ID
	* @return the number of matching process actions
	*/
	public int countByS_P_ID(long serviceProcessId);

	/**
	* Returns all the process actions where preProcessStepId = &#63;.
	*
	* @param preProcessStepId the pre process step ID
	* @return the matching process actions
	*/
	public java.util.List<ProcessAction> findByPR_P_S_ID(long preProcessStepId);

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
	public java.util.List<ProcessAction> findByPR_P_S_ID(
		long preProcessStepId, int start, int end);

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
	public java.util.List<ProcessAction> findByPR_P_S_ID(
		long preProcessStepId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator);

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
	public java.util.List<ProcessAction> findByPR_P_S_ID(
		long preProcessStepId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first process action in the ordered set where preProcessStepId = &#63;.
	*
	* @param preProcessStepId the pre process step ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public ProcessAction findByPR_P_S_ID_First(long preProcessStepId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException;

	/**
	* Returns the first process action in the ordered set where preProcessStepId = &#63;.
	*
	* @param preProcessStepId the pre process step ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public ProcessAction fetchByPR_P_S_ID_First(long preProcessStepId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator);

	/**
	* Returns the last process action in the ordered set where preProcessStepId = &#63;.
	*
	* @param preProcessStepId the pre process step ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public ProcessAction findByPR_P_S_ID_Last(long preProcessStepId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException;

	/**
	* Returns the last process action in the ordered set where preProcessStepId = &#63;.
	*
	* @param preProcessStepId the pre process step ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public ProcessAction fetchByPR_P_S_ID_Last(long preProcessStepId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator);

	/**
	* Returns the process actions before and after the current process action in the ordered set where preProcessStepId = &#63;.
	*
	* @param processActionId the primary key of the current process action
	* @param preProcessStepId the pre process step ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process action
	* @throws NoSuchProcessActionException if a process action with the primary key could not be found
	*/
	public ProcessAction[] findByPR_P_S_ID_PrevAndNext(long processActionId,
		long preProcessStepId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException;

	/**
	* Removes all the process actions where preProcessStepId = &#63; from the database.
	*
	* @param preProcessStepId the pre process step ID
	*/
	public void removeByPR_P_S_ID(long preProcessStepId);

	/**
	* Returns the number of process actions where preProcessStepId = &#63;.
	*
	* @param preProcessStepId the pre process step ID
	* @return the number of matching process actions
	*/
	public int countByPR_P_S_ID(long preProcessStepId);

	/**
	* Returns all the process actions where postProcessStepId = &#63;.
	*
	* @param postProcessStepId the post process step ID
	* @return the matching process actions
	*/
	public java.util.List<ProcessAction> findByPO_P_S_ID(long postProcessStepId);

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
	public java.util.List<ProcessAction> findByPO_P_S_ID(
		long postProcessStepId, int start, int end);

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
	public java.util.List<ProcessAction> findByPO_P_S_ID(
		long postProcessStepId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator);

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
	public java.util.List<ProcessAction> findByPO_P_S_ID(
		long postProcessStepId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first process action in the ordered set where postProcessStepId = &#63;.
	*
	* @param postProcessStepId the post process step ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public ProcessAction findByPO_P_S_ID_First(long postProcessStepId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException;

	/**
	* Returns the first process action in the ordered set where postProcessStepId = &#63;.
	*
	* @param postProcessStepId the post process step ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public ProcessAction fetchByPO_P_S_ID_First(long postProcessStepId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator);

	/**
	* Returns the last process action in the ordered set where postProcessStepId = &#63;.
	*
	* @param postProcessStepId the post process step ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public ProcessAction findByPO_P_S_ID_Last(long postProcessStepId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException;

	/**
	* Returns the last process action in the ordered set where postProcessStepId = &#63;.
	*
	* @param postProcessStepId the post process step ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public ProcessAction fetchByPO_P_S_ID_Last(long postProcessStepId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator);

	/**
	* Returns the process actions before and after the current process action in the ordered set where postProcessStepId = &#63;.
	*
	* @param processActionId the primary key of the current process action
	* @param postProcessStepId the post process step ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process action
	* @throws NoSuchProcessActionException if a process action with the primary key could not be found
	*/
	public ProcessAction[] findByPO_P_S_ID_PrevAndNext(long processActionId,
		long postProcessStepId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException;

	/**
	* Removes all the process actions where postProcessStepId = &#63; from the database.
	*
	* @param postProcessStepId the post process step ID
	*/
	public void removeByPO_P_S_ID(long postProcessStepId);

	/**
	* Returns the number of process actions where postProcessStepId = &#63;.
	*
	* @param postProcessStepId the post process step ID
	* @return the number of matching process actions
	*/
	public int countByPO_P_S_ID(long postProcessStepId);

	/**
	* Caches the process action in the entity cache if it is enabled.
	*
	* @param processAction the process action
	*/
	public void cacheResult(ProcessAction processAction);

	/**
	* Caches the process actions in the entity cache if it is enabled.
	*
	* @param processActions the process actions
	*/
	public void cacheResult(java.util.List<ProcessAction> processActions);

	/**
	* Creates a new process action with the primary key. Does not add the process action to the database.
	*
	* @param processActionId the primary key for the new process action
	* @return the new process action
	*/
	public ProcessAction create(long processActionId);

	/**
	* Removes the process action with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param processActionId the primary key of the process action
	* @return the process action that was removed
	* @throws NoSuchProcessActionException if a process action with the primary key could not be found
	*/
	public ProcessAction remove(long processActionId)
		throws NoSuchProcessActionException;

	public ProcessAction updateImpl(ProcessAction processAction);

	/**
	* Returns the process action with the primary key or throws a {@link NoSuchProcessActionException} if it could not be found.
	*
	* @param processActionId the primary key of the process action
	* @return the process action
	* @throws NoSuchProcessActionException if a process action with the primary key could not be found
	*/
	public ProcessAction findByPrimaryKey(long processActionId)
		throws NoSuchProcessActionException;

	/**
	* Returns the process action with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param processActionId the primary key of the process action
	* @return the process action, or <code>null</code> if a process action with the primary key could not be found
	*/
	public ProcessAction fetchByPrimaryKey(long processActionId);

	@Override
	public java.util.Map<java.io.Serializable, ProcessAction> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the process actions.
	*
	* @return the process actions
	*/
	public java.util.List<ProcessAction> findAll();

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
	public java.util.List<ProcessAction> findAll(int start, int end);

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
	public java.util.List<ProcessAction> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator);

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
	public java.util.List<ProcessAction> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the process actions from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of process actions.
	*
	* @return the number of process actions
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}