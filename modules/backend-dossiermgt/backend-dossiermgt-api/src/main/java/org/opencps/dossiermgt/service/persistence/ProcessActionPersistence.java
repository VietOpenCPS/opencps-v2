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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.dossiermgt.exception.NoSuchProcessActionException;
import org.opencps.dossiermgt.model.ProcessAction;

/**
 * The persistence interface for the process action service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.dossiermgt.service.persistence.impl.ProcessActionPersistenceImpl
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
	public java.util.List<ProcessAction> findByUuid(String uuid);

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
	public java.util.List<ProcessAction> findByUuid(String uuid, int start,
		int end);

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
	public java.util.List<ProcessAction> findByUuid(String uuid, int start,
		int end,
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
	public java.util.List<ProcessAction> findByUuid(String uuid, int start,
		int end,
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
	public ProcessAction findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException;

	/**
	* Returns the first process action in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public ProcessAction fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator);

	/**
	* Returns the last process action in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public ProcessAction findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException;

	/**
	* Returns the last process action in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public ProcessAction fetchByUuid_Last(String uuid,
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
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException;

	/**
	* Removes all the process actions where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of process actions where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching process actions
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the process action where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchProcessActionException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public ProcessAction findByUUID_G(String uuid, long groupId)
		throws NoSuchProcessActionException;

	/**
	* Returns the process action where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public ProcessAction fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the process action where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public ProcessAction fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the process action where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the process action that was removed
	*/
	public ProcessAction removeByUUID_G(String uuid, long groupId)
		throws NoSuchProcessActionException;

	/**
	* Returns the number of process actions where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching process actions
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the process actions where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching process actions
	*/
	public java.util.List<ProcessAction> findByUuid_C(String uuid,
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
	public java.util.List<ProcessAction> findByUuid_C(String uuid,
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
	public java.util.List<ProcessAction> findByUuid_C(String uuid,
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
	public java.util.List<ProcessAction> findByUuid_C(String uuid,
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
	public ProcessAction findByUuid_C_First(String uuid, long companyId,
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
	public ProcessAction fetchByUuid_C_First(String uuid, long companyId,
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
	public ProcessAction findByUuid_C_Last(String uuid, long companyId,
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
	public ProcessAction fetchByUuid_C_Last(String uuid, long companyId,
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
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException;

	/**
	* Removes all the process actions where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of process actions where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching process actions
	*/
	public int countByUuid_C(String uuid, long companyId);

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
	* Returns all the process actions where groupId = &#63; and actionCode = &#63;.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @return the matching process actions
	*/
	public java.util.List<ProcessAction> findByGI_AC(long groupId,
		String actionCode);

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
	public java.util.List<ProcessAction> findByGI_AC(long groupId,
		String actionCode, int start, int end);

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
	public java.util.List<ProcessAction> findByGI_AC(long groupId,
		String actionCode, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator);

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
	public java.util.List<ProcessAction> findByGI_AC(long groupId,
		String actionCode, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first process action in the ordered set where groupId = &#63; and actionCode = &#63;.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public ProcessAction findByGI_AC_First(long groupId, String actionCode,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException;

	/**
	* Returns the first process action in the ordered set where groupId = &#63; and actionCode = &#63;.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public ProcessAction fetchByGI_AC_First(long groupId, String actionCode,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator);

	/**
	* Returns the last process action in the ordered set where groupId = &#63; and actionCode = &#63;.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public ProcessAction findByGI_AC_Last(long groupId, String actionCode,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException;

	/**
	* Returns the last process action in the ordered set where groupId = &#63; and actionCode = &#63;.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public ProcessAction fetchByGI_AC_Last(long groupId, String actionCode,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator);

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
	public ProcessAction[] findByGI_AC_PrevAndNext(long processActionId,
		long groupId, String actionCode,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException;

	/**
	* Removes all the process actions where groupId = &#63; and actionCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	*/
	public void removeByGI_AC(long groupId, String actionCode);

	/**
	* Returns the number of process actions where groupId = &#63; and actionCode = &#63;.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @return the number of matching process actions
	*/
	public int countByGI_AC(long groupId, String actionCode);

	/**
	* Returns all the process actions where groupId = &#63; and actionCode = &#63; and serviceProcessId = &#63;.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param serviceProcessId the service process ID
	* @return the matching process actions
	*/
	public java.util.List<ProcessAction> findByGI_AC_SP(long groupId,
		String actionCode, long serviceProcessId);

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
	public java.util.List<ProcessAction> findByGI_AC_SP(long groupId,
		String actionCode, long serviceProcessId, int start, int end);

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
	public java.util.List<ProcessAction> findByGI_AC_SP(long groupId,
		String actionCode, long serviceProcessId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator);

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
	public java.util.List<ProcessAction> findByGI_AC_SP(long groupId,
		String actionCode, long serviceProcessId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator,
		boolean retrieveFromCache);

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
	public ProcessAction findByGI_AC_SP_First(long groupId, String actionCode,
		long serviceProcessId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException;

	/**
	* Returns the first process action in the ordered set where groupId = &#63; and actionCode = &#63; and serviceProcessId = &#63;.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public ProcessAction fetchByGI_AC_SP_First(long groupId, String actionCode,
		long serviceProcessId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator);

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
	public ProcessAction findByGI_AC_SP_Last(long groupId, String actionCode,
		long serviceProcessId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException;

	/**
	* Returns the last process action in the ordered set where groupId = &#63; and actionCode = &#63; and serviceProcessId = &#63;.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public ProcessAction fetchByGI_AC_SP_Last(long groupId, String actionCode,
		long serviceProcessId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator);

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
	public ProcessAction[] findByGI_AC_SP_PrevAndNext(long processActionId,
		long groupId, String actionCode, long serviceProcessId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException;

	/**
	* Removes all the process actions where groupId = &#63; and actionCode = &#63; and serviceProcessId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param serviceProcessId the service process ID
	*/
	public void removeByGI_AC_SP(long groupId, String actionCode,
		long serviceProcessId);

	/**
	* Returns the number of process actions where groupId = &#63; and actionCode = &#63; and serviceProcessId = &#63;.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param serviceProcessId the service process ID
	* @return the number of matching process actions
	*/
	public int countByGI_AC_SP(long groupId, String actionCode,
		long serviceProcessId);

	/**
	* Returns all the process actions where preStepCode = &#63; and groupId = &#63;.
	*
	* @param preStepCode the pre step code
	* @param groupId the group ID
	* @return the matching process actions
	*/
	public java.util.List<ProcessAction> findByPRE_CODE(String preStepCode,
		long groupId);

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
	public java.util.List<ProcessAction> findByPRE_CODE(String preStepCode,
		long groupId, int start, int end);

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
	public java.util.List<ProcessAction> findByPRE_CODE(String preStepCode,
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator);

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
	public java.util.List<ProcessAction> findByPRE_CODE(String preStepCode,
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first process action in the ordered set where preStepCode = &#63; and groupId = &#63;.
	*
	* @param preStepCode the pre step code
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public ProcessAction findByPRE_CODE_First(String preStepCode, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException;

	/**
	* Returns the first process action in the ordered set where preStepCode = &#63; and groupId = &#63;.
	*
	* @param preStepCode the pre step code
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public ProcessAction fetchByPRE_CODE_First(String preStepCode,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator);

	/**
	* Returns the last process action in the ordered set where preStepCode = &#63; and groupId = &#63;.
	*
	* @param preStepCode the pre step code
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public ProcessAction findByPRE_CODE_Last(String preStepCode, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException;

	/**
	* Returns the last process action in the ordered set where preStepCode = &#63; and groupId = &#63;.
	*
	* @param preStepCode the pre step code
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public ProcessAction fetchByPRE_CODE_Last(String preStepCode, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator);

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
	public ProcessAction[] findByPRE_CODE_PrevAndNext(long processActionId,
		String preStepCode, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException;

	/**
	* Removes all the process actions where preStepCode = &#63; and groupId = &#63; from the database.
	*
	* @param preStepCode the pre step code
	* @param groupId the group ID
	*/
	public void removeByPRE_CODE(String preStepCode, long groupId);

	/**
	* Returns the number of process actions where preStepCode = &#63; and groupId = &#63;.
	*
	* @param preStepCode the pre step code
	* @param groupId the group ID
	* @return the number of matching process actions
	*/
	public int countByPRE_CODE(String preStepCode, long groupId);

	/**
	* Returns all the process actions where postStepCode = &#63; and groupId = &#63;.
	*
	* @param postStepCode the post step code
	* @param groupId the group ID
	* @return the matching process actions
	*/
	public java.util.List<ProcessAction> findByPOST_CODE(String postStepCode,
		long groupId);

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
	public java.util.List<ProcessAction> findByPOST_CODE(String postStepCode,
		long groupId, int start, int end);

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
	public java.util.List<ProcessAction> findByPOST_CODE(String postStepCode,
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator);

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
	public java.util.List<ProcessAction> findByPOST_CODE(String postStepCode,
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first process action in the ordered set where postStepCode = &#63; and groupId = &#63;.
	*
	* @param postStepCode the post step code
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public ProcessAction findByPOST_CODE_First(String postStepCode,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException;

	/**
	* Returns the first process action in the ordered set where postStepCode = &#63; and groupId = &#63;.
	*
	* @param postStepCode the post step code
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public ProcessAction fetchByPOST_CODE_First(String postStepCode,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator);

	/**
	* Returns the last process action in the ordered set where postStepCode = &#63; and groupId = &#63;.
	*
	* @param postStepCode the post step code
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public ProcessAction findByPOST_CODE_Last(String postStepCode,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException;

	/**
	* Returns the last process action in the ordered set where postStepCode = &#63; and groupId = &#63;.
	*
	* @param postStepCode the post step code
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public ProcessAction fetchByPOST_CODE_Last(String postStepCode,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator);

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
	public ProcessAction[] findByPOST_CODE_PrevAndNext(long processActionId,
		String postStepCode, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException;

	/**
	* Removes all the process actions where postStepCode = &#63; and groupId = &#63; from the database.
	*
	* @param postStepCode the post step code
	* @param groupId the group ID
	*/
	public void removeByPOST_CODE(String postStepCode, long groupId);

	/**
	* Returns the number of process actions where postStepCode = &#63; and groupId = &#63;.
	*
	* @param postStepCode the post step code
	* @param groupId the group ID
	* @return the number of matching process actions
	*/
	public int countByPOST_CODE(String postStepCode, long groupId);

	/**
	* Returns the process action where serviceProcessId = &#63; and preStepCode = &#63; and autoEvent = &#63; or throws a {@link NoSuchProcessActionException} if it could not be found.
	*
	* @param serviceProcessId the service process ID
	* @param preStepCode the pre step code
	* @param autoEvent the auto event
	* @return the matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public ProcessAction findBySPI_PRESC_AEV(long serviceProcessId,
		String preStepCode, String autoEvent)
		throws NoSuchProcessActionException;

	/**
	* Returns the process action where serviceProcessId = &#63; and preStepCode = &#63; and autoEvent = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param serviceProcessId the service process ID
	* @param preStepCode the pre step code
	* @param autoEvent the auto event
	* @return the matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public ProcessAction fetchBySPI_PRESC_AEV(long serviceProcessId,
		String preStepCode, String autoEvent);

	/**
	* Returns the process action where serviceProcessId = &#63; and preStepCode = &#63; and autoEvent = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param serviceProcessId the service process ID
	* @param preStepCode the pre step code
	* @param autoEvent the auto event
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public ProcessAction fetchBySPI_PRESC_AEV(long serviceProcessId,
		String preStepCode, String autoEvent, boolean retrieveFromCache);

	/**
	* Removes the process action where serviceProcessId = &#63; and preStepCode = &#63; and autoEvent = &#63; from the database.
	*
	* @param serviceProcessId the service process ID
	* @param preStepCode the pre step code
	* @param autoEvent the auto event
	* @return the process action that was removed
	*/
	public ProcessAction removeBySPI_PRESC_AEV(long serviceProcessId,
		String preStepCode, String autoEvent)
		throws NoSuchProcessActionException;

	/**
	* Returns the number of process actions where serviceProcessId = &#63; and preStepCode = &#63; and autoEvent = &#63;.
	*
	* @param serviceProcessId the service process ID
	* @param preStepCode the pre step code
	* @param autoEvent the auto event
	* @return the number of matching process actions
	*/
	public int countBySPI_PRESC_AEV(long serviceProcessId, String preStepCode,
		String autoEvent);

	/**
	* Returns the process action where serviceProcessId = &#63; and actionCode = &#63; or throws a {@link NoSuchProcessActionException} if it could not be found.
	*
	* @param serviceProcessId the service process ID
	* @param actionCode the action code
	* @return the matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public ProcessAction findBySPID_AC(long serviceProcessId, String actionCode)
		throws NoSuchProcessActionException;

	/**
	* Returns the process action where serviceProcessId = &#63; and actionCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param serviceProcessId the service process ID
	* @param actionCode the action code
	* @return the matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public ProcessAction fetchBySPID_AC(long serviceProcessId, String actionCode);

	/**
	* Returns the process action where serviceProcessId = &#63; and actionCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param serviceProcessId the service process ID
	* @param actionCode the action code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public ProcessAction fetchBySPID_AC(long serviceProcessId,
		String actionCode, boolean retrieveFromCache);

	/**
	* Removes the process action where serviceProcessId = &#63; and actionCode = &#63; from the database.
	*
	* @param serviceProcessId the service process ID
	* @param actionCode the action code
	* @return the process action that was removed
	*/
	public ProcessAction removeBySPID_AC(long serviceProcessId,
		String actionCode) throws NoSuchProcessActionException;

	/**
	* Returns the number of process actions where serviceProcessId = &#63; and actionCode = &#63;.
	*
	* @param serviceProcessId the service process ID
	* @param actionCode the action code
	* @return the number of matching process actions
	*/
	public int countBySPID_AC(long serviceProcessId, String actionCode);

	/**
	* Returns all the process actions where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param preStepCode the pre step code
	* @return the matching process actions
	*/
	public java.util.List<ProcessAction> findByG_SPID_PRESC(long groupId,
		long serviceProcessId, String preStepCode);

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
	public java.util.List<ProcessAction> findByG_SPID_PRESC(long groupId,
		long serviceProcessId, String preStepCode, int start, int end);

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
	public java.util.List<ProcessAction> findByG_SPID_PRESC(long groupId,
		long serviceProcessId, String preStepCode, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator);

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
	public java.util.List<ProcessAction> findByG_SPID_PRESC(long groupId,
		long serviceProcessId, String preStepCode, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator,
		boolean retrieveFromCache);

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
	public ProcessAction findByG_SPID_PRESC_First(long groupId,
		long serviceProcessId, String preStepCode,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException;

	/**
	* Returns the first process action in the ordered set where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param preStepCode the pre step code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public ProcessAction fetchByG_SPID_PRESC_First(long groupId,
		long serviceProcessId, String preStepCode,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator);

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
	public ProcessAction findByG_SPID_PRESC_Last(long groupId,
		long serviceProcessId, String preStepCode,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException;

	/**
	* Returns the last process action in the ordered set where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param preStepCode the pre step code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public ProcessAction fetchByG_SPID_PRESC_Last(long groupId,
		long serviceProcessId, String preStepCode,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator);

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
	public ProcessAction[] findByG_SPID_PRESC_PrevAndNext(
		long processActionId, long groupId, long serviceProcessId,
		String preStepCode,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException;

	/**
	* Removes all the process actions where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param preStepCode the pre step code
	*/
	public void removeByG_SPID_PRESC(long groupId, long serviceProcessId,
		String preStepCode);

	/**
	* Returns the number of process actions where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param preStepCode the pre step code
	* @return the number of matching process actions
	*/
	public int countByG_SPID_PRESC(long groupId, long serviceProcessId,
		String preStepCode);

	/**
	* Returns all the process actions where autoEvent = &#63;.
	*
	* @param autoEvent the auto event
	* @return the matching process actions
	*/
	public java.util.List<ProcessAction> findByPSC_AEV(String autoEvent);

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
	public java.util.List<ProcessAction> findByPSC_AEV(String autoEvent,
		int start, int end);

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
	public java.util.List<ProcessAction> findByPSC_AEV(String autoEvent,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator);

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
	public java.util.List<ProcessAction> findByPSC_AEV(String autoEvent,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first process action in the ordered set where autoEvent = &#63;.
	*
	* @param autoEvent the auto event
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public ProcessAction findByPSC_AEV_First(String autoEvent,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException;

	/**
	* Returns the first process action in the ordered set where autoEvent = &#63;.
	*
	* @param autoEvent the auto event
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public ProcessAction fetchByPSC_AEV_First(String autoEvent,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator);

	/**
	* Returns the last process action in the ordered set where autoEvent = &#63;.
	*
	* @param autoEvent the auto event
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public ProcessAction findByPSC_AEV_Last(String autoEvent,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException;

	/**
	* Returns the last process action in the ordered set where autoEvent = &#63;.
	*
	* @param autoEvent the auto event
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public ProcessAction fetchByPSC_AEV_Last(String autoEvent,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator);

	/**
	* Returns the process actions before and after the current process action in the ordered set where autoEvent = &#63;.
	*
	* @param processActionId the primary key of the current process action
	* @param autoEvent the auto event
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process action
	* @throws NoSuchProcessActionException if a process action with the primary key could not be found
	*/
	public ProcessAction[] findByPSC_AEV_PrevAndNext(long processActionId,
		String autoEvent,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException;

	/**
	* Removes all the process actions where autoEvent = &#63; from the database.
	*
	* @param autoEvent the auto event
	*/
	public void removeByPSC_AEV(String autoEvent);

	/**
	* Returns the number of process actions where autoEvent = &#63;.
	*
	* @param autoEvent the auto event
	* @return the number of matching process actions
	*/
	public int countByPSC_AEV(String autoEvent);

	/**
	* Returns all the process actions where groupId = &#63; and autoEvent = &#63;.
	*
	* @param groupId the group ID
	* @param autoEvent the auto event
	* @return the matching process actions
	*/
	public java.util.List<ProcessAction> findByPSC_AEV_GI(long groupId,
		String autoEvent);

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
	public java.util.List<ProcessAction> findByPSC_AEV_GI(long groupId,
		String autoEvent, int start, int end);

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
	public java.util.List<ProcessAction> findByPSC_AEV_GI(long groupId,
		String autoEvent, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator);

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
	public java.util.List<ProcessAction> findByPSC_AEV_GI(long groupId,
		String autoEvent, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first process action in the ordered set where groupId = &#63; and autoEvent = &#63;.
	*
	* @param groupId the group ID
	* @param autoEvent the auto event
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public ProcessAction findByPSC_AEV_GI_First(long groupId, String autoEvent,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException;

	/**
	* Returns the first process action in the ordered set where groupId = &#63; and autoEvent = &#63;.
	*
	* @param groupId the group ID
	* @param autoEvent the auto event
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public ProcessAction fetchByPSC_AEV_GI_First(long groupId,
		String autoEvent,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator);

	/**
	* Returns the last process action in the ordered set where groupId = &#63; and autoEvent = &#63;.
	*
	* @param groupId the group ID
	* @param autoEvent the auto event
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public ProcessAction findByPSC_AEV_GI_Last(long groupId, String autoEvent,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException;

	/**
	* Returns the last process action in the ordered set where groupId = &#63; and autoEvent = &#63;.
	*
	* @param groupId the group ID
	* @param autoEvent the auto event
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public ProcessAction fetchByPSC_AEV_GI_Last(long groupId, String autoEvent,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator);

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
	public ProcessAction[] findByPSC_AEV_GI_PrevAndNext(long processActionId,
		long groupId, String autoEvent,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException;

	/**
	* Removes all the process actions where groupId = &#63; and autoEvent = &#63; from the database.
	*
	* @param groupId the group ID
	* @param autoEvent the auto event
	*/
	public void removeByPSC_AEV_GI(long groupId, String autoEvent);

	/**
	* Returns the number of process actions where groupId = &#63; and autoEvent = &#63;.
	*
	* @param groupId the group ID
	* @param autoEvent the auto event
	* @return the number of matching process actions
	*/
	public int countByPSC_AEV_GI(long groupId, String autoEvent);

	/**
	* Returns the process action where serviceProcessId = &#63; and actionCode = &#63; and actionName = &#63; or throws a {@link NoSuchProcessActionException} if it could not be found.
	*
	* @param serviceProcessId the service process ID
	* @param actionCode the action code
	* @param actionName the action name
	* @return the matching process action
	* @throws NoSuchProcessActionException if a matching process action could not be found
	*/
	public ProcessAction findBySPID_AC_AN(long serviceProcessId,
		String actionCode, String actionName)
		throws NoSuchProcessActionException;

	/**
	* Returns the process action where serviceProcessId = &#63; and actionCode = &#63; and actionName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param serviceProcessId the service process ID
	* @param actionCode the action code
	* @param actionName the action name
	* @return the matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public ProcessAction fetchBySPID_AC_AN(long serviceProcessId,
		String actionCode, String actionName);

	/**
	* Returns the process action where serviceProcessId = &#63; and actionCode = &#63; and actionName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param serviceProcessId the service process ID
	* @param actionCode the action code
	* @param actionName the action name
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public ProcessAction fetchBySPID_AC_AN(long serviceProcessId,
		String actionCode, String actionName, boolean retrieveFromCache);

	/**
	* Removes the process action where serviceProcessId = &#63; and actionCode = &#63; and actionName = &#63; from the database.
	*
	* @param serviceProcessId the service process ID
	* @param actionCode the action code
	* @param actionName the action name
	* @return the process action that was removed
	*/
	public ProcessAction removeBySPID_AC_AN(long serviceProcessId,
		String actionCode, String actionName)
		throws NoSuchProcessActionException;

	/**
	* Returns the number of process actions where serviceProcessId = &#63; and actionCode = &#63; and actionName = &#63;.
	*
	* @param serviceProcessId the service process ID
	* @param actionCode the action code
	* @param actionName the action name
	* @return the number of matching process actions
	*/
	public int countBySPID_AC_AN(long serviceProcessId, String actionCode,
		String actionName);

	/**
	* Returns all the process actions where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param preStepCode the pre step code
	* @return the matching process actions
	*/
	public java.util.List<ProcessAction> findByF_GID_SID_PRE(long groupId,
		long serviceProcessId, String preStepCode);

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
	public java.util.List<ProcessAction> findByF_GID_SID_PRE(long groupId,
		long serviceProcessId, String preStepCode, int start, int end);

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
	public java.util.List<ProcessAction> findByF_GID_SID_PRE(long groupId,
		long serviceProcessId, String preStepCode, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator);

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
	public java.util.List<ProcessAction> findByF_GID_SID_PRE(long groupId,
		long serviceProcessId, String preStepCode, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator,
		boolean retrieveFromCache);

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
	public ProcessAction findByF_GID_SID_PRE_First(long groupId,
		long serviceProcessId, String preStepCode,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException;

	/**
	* Returns the first process action in the ordered set where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param preStepCode the pre step code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public ProcessAction fetchByF_GID_SID_PRE_First(long groupId,
		long serviceProcessId, String preStepCode,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator);

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
	public ProcessAction findByF_GID_SID_PRE_Last(long groupId,
		long serviceProcessId, String preStepCode,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException;

	/**
	* Returns the last process action in the ordered set where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param preStepCode the pre step code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process action, or <code>null</code> if a matching process action could not be found
	*/
	public ProcessAction fetchByF_GID_SID_PRE_Last(long groupId,
		long serviceProcessId, String preStepCode,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator);

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
	public ProcessAction[] findByF_GID_SID_PRE_PrevAndNext(
		long processActionId, long groupId, long serviceProcessId,
		String preStepCode,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException;

	/**
	* Removes all the process actions where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param preStepCode the pre step code
	*/
	public void removeByF_GID_SID_PRE(long groupId, long serviceProcessId,
		String preStepCode);

	/**
	* Returns the number of process actions where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param preStepCode the pre step code
	* @return the number of matching process actions
	*/
	public int countByF_GID_SID_PRE(long groupId, long serviceProcessId,
		String preStepCode);

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
	public java.util.Set<String> getBadColumnNames();
}