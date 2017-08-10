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

import org.opencps.backend.processmgt.exception.NoSuchServiceProcessException;
import org.opencps.backend.processmgt.model.ServiceProcess;

/**
 * The persistence interface for the service process service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see org.opencps.backend.processmgt.service.persistence.impl.ServiceProcessPersistenceImpl
 * @see ServiceProcessUtil
 * @generated
 */
@ProviderType
public interface ServiceProcessPersistence extends BasePersistence<ServiceProcess> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ServiceProcessUtil} to access the service process persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the service processes where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching service processes
	*/
	public java.util.List<ServiceProcess> findByUuid(java.lang.String uuid);

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
	public java.util.List<ServiceProcess> findByUuid(java.lang.String uuid,
		int start, int end);

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
	public java.util.List<ServiceProcess> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceProcess> orderByComparator);

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
	public java.util.List<ServiceProcess> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceProcess> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first service process in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service process
	* @throws NoSuchServiceProcessException if a matching service process could not be found
	*/
	public ServiceProcess findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceProcess> orderByComparator)
		throws NoSuchServiceProcessException;

	/**
	* Returns the first service process in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service process, or <code>null</code> if a matching service process could not be found
	*/
	public ServiceProcess fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceProcess> orderByComparator);

	/**
	* Returns the last service process in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service process
	* @throws NoSuchServiceProcessException if a matching service process could not be found
	*/
	public ServiceProcess findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceProcess> orderByComparator)
		throws NoSuchServiceProcessException;

	/**
	* Returns the last service process in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service process, or <code>null</code> if a matching service process could not be found
	*/
	public ServiceProcess fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceProcess> orderByComparator);

	/**
	* Returns the service processes before and after the current service process in the ordered set where uuid = &#63;.
	*
	* @param serviceProcessId the primary key of the current service process
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next service process
	* @throws NoSuchServiceProcessException if a service process with the primary key could not be found
	*/
	public ServiceProcess[] findByUuid_PrevAndNext(long serviceProcessId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceProcess> orderByComparator)
		throws NoSuchServiceProcessException;

	/**
	* Removes all the service processes where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of service processes where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching service processes
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the service process where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchServiceProcessException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching service process
	* @throws NoSuchServiceProcessException if a matching service process could not be found
	*/
	public ServiceProcess findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchServiceProcessException;

	/**
	* Returns the service process where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching service process, or <code>null</code> if a matching service process could not be found
	*/
	public ServiceProcess fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the service process where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching service process, or <code>null</code> if a matching service process could not be found
	*/
	public ServiceProcess fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the service process where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the service process that was removed
	*/
	public ServiceProcess removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchServiceProcessException;

	/**
	* Returns the number of service processes where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching service processes
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the service processes where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching service processes
	*/
	public java.util.List<ServiceProcess> findByUuid_C(java.lang.String uuid,
		long companyId);

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
	public java.util.List<ServiceProcess> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

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
	public java.util.List<ServiceProcess> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceProcess> orderByComparator);

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
	public java.util.List<ServiceProcess> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceProcess> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first service process in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service process
	* @throws NoSuchServiceProcessException if a matching service process could not be found
	*/
	public ServiceProcess findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceProcess> orderByComparator)
		throws NoSuchServiceProcessException;

	/**
	* Returns the first service process in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service process, or <code>null</code> if a matching service process could not be found
	*/
	public ServiceProcess fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceProcess> orderByComparator);

	/**
	* Returns the last service process in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service process
	* @throws NoSuchServiceProcessException if a matching service process could not be found
	*/
	public ServiceProcess findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceProcess> orderByComparator)
		throws NoSuchServiceProcessException;

	/**
	* Returns the last service process in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service process, or <code>null</code> if a matching service process could not be found
	*/
	public ServiceProcess fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceProcess> orderByComparator);

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
	public ServiceProcess[] findByUuid_C_PrevAndNext(long serviceProcessId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceProcess> orderByComparator)
		throws NoSuchServiceProcessException;

	/**
	* Removes all the service processes where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of service processes where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching service processes
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the service processes where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching service processes
	*/
	public java.util.List<ServiceProcess> findByG_ID(long groupId);

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
	public java.util.List<ServiceProcess> findByG_ID(long groupId, int start,
		int end);

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
	public java.util.List<ServiceProcess> findByG_ID(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceProcess> orderByComparator);

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
	public java.util.List<ServiceProcess> findByG_ID(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceProcess> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first service process in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service process
	* @throws NoSuchServiceProcessException if a matching service process could not be found
	*/
	public ServiceProcess findByG_ID_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceProcess> orderByComparator)
		throws NoSuchServiceProcessException;

	/**
	* Returns the first service process in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service process, or <code>null</code> if a matching service process could not be found
	*/
	public ServiceProcess fetchByG_ID_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceProcess> orderByComparator);

	/**
	* Returns the last service process in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service process
	* @throws NoSuchServiceProcessException if a matching service process could not be found
	*/
	public ServiceProcess findByG_ID_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceProcess> orderByComparator)
		throws NoSuchServiceProcessException;

	/**
	* Returns the last service process in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service process, or <code>null</code> if a matching service process could not be found
	*/
	public ServiceProcess fetchByG_ID_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceProcess> orderByComparator);

	/**
	* Returns the service processes before and after the current service process in the ordered set where groupId = &#63;.
	*
	* @param serviceProcessId the primary key of the current service process
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next service process
	* @throws NoSuchServiceProcessException if a service process with the primary key could not be found
	*/
	public ServiceProcess[] findByG_ID_PrevAndNext(long serviceProcessId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceProcess> orderByComparator)
		throws NoSuchServiceProcessException;

	/**
	* Removes all the service processes where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByG_ID(long groupId);

	/**
	* Returns the number of service processes where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching service processes
	*/
	public int countByG_ID(long groupId);

	/**
	* Caches the service process in the entity cache if it is enabled.
	*
	* @param serviceProcess the service process
	*/
	public void cacheResult(ServiceProcess serviceProcess);

	/**
	* Caches the service processes in the entity cache if it is enabled.
	*
	* @param serviceProcesses the service processes
	*/
	public void cacheResult(java.util.List<ServiceProcess> serviceProcesses);

	/**
	* Creates a new service process with the primary key. Does not add the service process to the database.
	*
	* @param serviceProcessId the primary key for the new service process
	* @return the new service process
	*/
	public ServiceProcess create(long serviceProcessId);

	/**
	* Removes the service process with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceProcessId the primary key of the service process
	* @return the service process that was removed
	* @throws NoSuchServiceProcessException if a service process with the primary key could not be found
	*/
	public ServiceProcess remove(long serviceProcessId)
		throws NoSuchServiceProcessException;

	public ServiceProcess updateImpl(ServiceProcess serviceProcess);

	/**
	* Returns the service process with the primary key or throws a {@link NoSuchServiceProcessException} if it could not be found.
	*
	* @param serviceProcessId the primary key of the service process
	* @return the service process
	* @throws NoSuchServiceProcessException if a service process with the primary key could not be found
	*/
	public ServiceProcess findByPrimaryKey(long serviceProcessId)
		throws NoSuchServiceProcessException;

	/**
	* Returns the service process with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param serviceProcessId the primary key of the service process
	* @return the service process, or <code>null</code> if a service process with the primary key could not be found
	*/
	public ServiceProcess fetchByPrimaryKey(long serviceProcessId);

	@Override
	public java.util.Map<java.io.Serializable, ServiceProcess> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the service processes.
	*
	* @return the service processes
	*/
	public java.util.List<ServiceProcess> findAll();

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
	public java.util.List<ServiceProcess> findAll(int start, int end);

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
	public java.util.List<ServiceProcess> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceProcess> orderByComparator);

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
	public java.util.List<ServiceProcess> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceProcess> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the service processes from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of service processes.
	*
	* @return the number of service processes
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}