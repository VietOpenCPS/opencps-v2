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

import org.opencps.dossiermgt.exception.NoSuchProcessOptionException;
import org.opencps.dossiermgt.model.ProcessOption;

/**
 * The persistence interface for the process option service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.dossiermgt.service.persistence.impl.ProcessOptionPersistenceImpl
 * @see ProcessOptionUtil
 * @generated
 */
@ProviderType
public interface ProcessOptionPersistence extends BasePersistence<ProcessOption> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ProcessOptionUtil} to access the process option persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the process options where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching process options
	*/
	public java.util.List<ProcessOption> findByUuid(String uuid);

	/**
	* Returns a range of all the process options where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of process options
	* @param end the upper bound of the range of process options (not inclusive)
	* @return the range of matching process options
	*/
	public java.util.List<ProcessOption> findByUuid(String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the process options where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of process options
	* @param end the upper bound of the range of process options (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching process options
	*/
	public java.util.List<ProcessOption> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessOption> orderByComparator);

	/**
	* Returns an ordered range of all the process options where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of process options
	* @param end the upper bound of the range of process options (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching process options
	*/
	public java.util.List<ProcessOption> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessOption> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first process option in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process option
	* @throws NoSuchProcessOptionException if a matching process option could not be found
	*/
	public ProcessOption findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessOption> orderByComparator)
		throws NoSuchProcessOptionException;

	/**
	* Returns the first process option in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process option, or <code>null</code> if a matching process option could not be found
	*/
	public ProcessOption fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessOption> orderByComparator);

	/**
	* Returns the last process option in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process option
	* @throws NoSuchProcessOptionException if a matching process option could not be found
	*/
	public ProcessOption findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessOption> orderByComparator)
		throws NoSuchProcessOptionException;

	/**
	* Returns the last process option in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process option, or <code>null</code> if a matching process option could not be found
	*/
	public ProcessOption fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessOption> orderByComparator);

	/**
	* Returns the process options before and after the current process option in the ordered set where uuid = &#63;.
	*
	* @param processOptionId the primary key of the current process option
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process option
	* @throws NoSuchProcessOptionException if a process option with the primary key could not be found
	*/
	public ProcessOption[] findByUuid_PrevAndNext(long processOptionId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessOption> orderByComparator)
		throws NoSuchProcessOptionException;

	/**
	* Removes all the process options where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of process options where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching process options
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the process option where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchProcessOptionException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching process option
	* @throws NoSuchProcessOptionException if a matching process option could not be found
	*/
	public ProcessOption findByUUID_G(String uuid, long groupId)
		throws NoSuchProcessOptionException;

	/**
	* Returns the process option where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching process option, or <code>null</code> if a matching process option could not be found
	*/
	public ProcessOption fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the process option where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching process option, or <code>null</code> if a matching process option could not be found
	*/
	public ProcessOption fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the process option where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the process option that was removed
	*/
	public ProcessOption removeByUUID_G(String uuid, long groupId)
		throws NoSuchProcessOptionException;

	/**
	* Returns the number of process options where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching process options
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the process options where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching process options
	*/
	public java.util.List<ProcessOption> findByUuid_C(String uuid,
		long companyId);

	/**
	* Returns a range of all the process options where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of process options
	* @param end the upper bound of the range of process options (not inclusive)
	* @return the range of matching process options
	*/
	public java.util.List<ProcessOption> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the process options where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of process options
	* @param end the upper bound of the range of process options (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching process options
	*/
	public java.util.List<ProcessOption> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessOption> orderByComparator);

	/**
	* Returns an ordered range of all the process options where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of process options
	* @param end the upper bound of the range of process options (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching process options
	*/
	public java.util.List<ProcessOption> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessOption> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first process option in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process option
	* @throws NoSuchProcessOptionException if a matching process option could not be found
	*/
	public ProcessOption findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessOption> orderByComparator)
		throws NoSuchProcessOptionException;

	/**
	* Returns the first process option in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process option, or <code>null</code> if a matching process option could not be found
	*/
	public ProcessOption fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessOption> orderByComparator);

	/**
	* Returns the last process option in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process option
	* @throws NoSuchProcessOptionException if a matching process option could not be found
	*/
	public ProcessOption findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessOption> orderByComparator)
		throws NoSuchProcessOptionException;

	/**
	* Returns the last process option in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process option, or <code>null</code> if a matching process option could not be found
	*/
	public ProcessOption fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessOption> orderByComparator);

	/**
	* Returns the process options before and after the current process option in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param processOptionId the primary key of the current process option
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process option
	* @throws NoSuchProcessOptionException if a process option with the primary key could not be found
	*/
	public ProcessOption[] findByUuid_C_PrevAndNext(long processOptionId,
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessOption> orderByComparator)
		throws NoSuchProcessOptionException;

	/**
	* Removes all the process options where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of process options where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching process options
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the process options where serviceConfigId = &#63;.
	*
	* @param serviceConfigId the service config ID
	* @return the matching process options
	*/
	public java.util.List<ProcessOption> findBySC_ID(long serviceConfigId);

	/**
	* Returns a range of all the process options where serviceConfigId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param serviceConfigId the service config ID
	* @param start the lower bound of the range of process options
	* @param end the upper bound of the range of process options (not inclusive)
	* @return the range of matching process options
	*/
	public java.util.List<ProcessOption> findBySC_ID(long serviceConfigId,
		int start, int end);

	/**
	* Returns an ordered range of all the process options where serviceConfigId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param serviceConfigId the service config ID
	* @param start the lower bound of the range of process options
	* @param end the upper bound of the range of process options (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching process options
	*/
	public java.util.List<ProcessOption> findBySC_ID(long serviceConfigId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessOption> orderByComparator);

	/**
	* Returns an ordered range of all the process options where serviceConfigId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param serviceConfigId the service config ID
	* @param start the lower bound of the range of process options
	* @param end the upper bound of the range of process options (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching process options
	*/
	public java.util.List<ProcessOption> findBySC_ID(long serviceConfigId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessOption> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first process option in the ordered set where serviceConfigId = &#63;.
	*
	* @param serviceConfigId the service config ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process option
	* @throws NoSuchProcessOptionException if a matching process option could not be found
	*/
	public ProcessOption findBySC_ID_First(long serviceConfigId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessOption> orderByComparator)
		throws NoSuchProcessOptionException;

	/**
	* Returns the first process option in the ordered set where serviceConfigId = &#63;.
	*
	* @param serviceConfigId the service config ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process option, or <code>null</code> if a matching process option could not be found
	*/
	public ProcessOption fetchBySC_ID_First(long serviceConfigId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessOption> orderByComparator);

	/**
	* Returns the last process option in the ordered set where serviceConfigId = &#63;.
	*
	* @param serviceConfigId the service config ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process option
	* @throws NoSuchProcessOptionException if a matching process option could not be found
	*/
	public ProcessOption findBySC_ID_Last(long serviceConfigId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessOption> orderByComparator)
		throws NoSuchProcessOptionException;

	/**
	* Returns the last process option in the ordered set where serviceConfigId = &#63;.
	*
	* @param serviceConfigId the service config ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process option, or <code>null</code> if a matching process option could not be found
	*/
	public ProcessOption fetchBySC_ID_Last(long serviceConfigId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessOption> orderByComparator);

	/**
	* Returns the process options before and after the current process option in the ordered set where serviceConfigId = &#63;.
	*
	* @param processOptionId the primary key of the current process option
	* @param serviceConfigId the service config ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process option
	* @throws NoSuchProcessOptionException if a process option with the primary key could not be found
	*/
	public ProcessOption[] findBySC_ID_PrevAndNext(long processOptionId,
		long serviceConfigId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessOption> orderByComparator)
		throws NoSuchProcessOptionException;

	/**
	* Removes all the process options where serviceConfigId = &#63; from the database.
	*
	* @param serviceConfigId the service config ID
	*/
	public void removeBySC_ID(long serviceConfigId);

	/**
	* Returns the number of process options where serviceConfigId = &#63;.
	*
	* @param serviceConfigId the service config ID
	* @return the number of matching process options
	*/
	public int countBySC_ID(long serviceConfigId);

	/**
	* Returns the process option where serviceConfigId = &#63; and optionOrder = &#63; or throws a {@link NoSuchProcessOptionException} if it could not be found.
	*
	* @param serviceConfigId the service config ID
	* @param optionOrder the option order
	* @return the matching process option
	* @throws NoSuchProcessOptionException if a matching process option could not be found
	*/
	public ProcessOption findBySC_ID_OP(long serviceConfigId, int optionOrder)
		throws NoSuchProcessOptionException;

	/**
	* Returns the process option where serviceConfigId = &#63; and optionOrder = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param serviceConfigId the service config ID
	* @param optionOrder the option order
	* @return the matching process option, or <code>null</code> if a matching process option could not be found
	*/
	public ProcessOption fetchBySC_ID_OP(long serviceConfigId, int optionOrder);

	/**
	* Returns the process option where serviceConfigId = &#63; and optionOrder = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param serviceConfigId the service config ID
	* @param optionOrder the option order
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching process option, or <code>null</code> if a matching process option could not be found
	*/
	public ProcessOption fetchBySC_ID_OP(long serviceConfigId, int optionOrder,
		boolean retrieveFromCache);

	/**
	* Removes the process option where serviceConfigId = &#63; and optionOrder = &#63; from the database.
	*
	* @param serviceConfigId the service config ID
	* @param optionOrder the option order
	* @return the process option that was removed
	*/
	public ProcessOption removeBySC_ID_OP(long serviceConfigId, int optionOrder)
		throws NoSuchProcessOptionException;

	/**
	* Returns the number of process options where serviceConfigId = &#63; and optionOrder = &#63;.
	*
	* @param serviceConfigId the service config ID
	* @param optionOrder the option order
	* @return the number of matching process options
	*/
	public int countBySC_ID_OP(long serviceConfigId, int optionOrder);

	/**
	* Returns the process option where serviceConfigId = &#63; and dossierTemplateId = &#63; or throws a {@link NoSuchProcessOptionException} if it could not be found.
	*
	* @param serviceConfigId the service config ID
	* @param dossierTemplateId the dossier template ID
	* @return the matching process option
	* @throws NoSuchProcessOptionException if a matching process option could not be found
	*/
	public ProcessOption findBySC_DT(long serviceConfigId,
		long dossierTemplateId) throws NoSuchProcessOptionException;

	/**
	* Returns the process option where serviceConfigId = &#63; and dossierTemplateId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param serviceConfigId the service config ID
	* @param dossierTemplateId the dossier template ID
	* @return the matching process option, or <code>null</code> if a matching process option could not be found
	*/
	public ProcessOption fetchBySC_DT(long serviceConfigId,
		long dossierTemplateId);

	/**
	* Returns the process option where serviceConfigId = &#63; and dossierTemplateId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param serviceConfigId the service config ID
	* @param dossierTemplateId the dossier template ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching process option, or <code>null</code> if a matching process option could not be found
	*/
	public ProcessOption fetchBySC_DT(long serviceConfigId,
		long dossierTemplateId, boolean retrieveFromCache);

	/**
	* Removes the process option where serviceConfigId = &#63; and dossierTemplateId = &#63; from the database.
	*
	* @param serviceConfigId the service config ID
	* @param dossierTemplateId the dossier template ID
	* @return the process option that was removed
	*/
	public ProcessOption removeBySC_DT(long serviceConfigId,
		long dossierTemplateId) throws NoSuchProcessOptionException;

	/**
	* Returns the number of process options where serviceConfigId = &#63; and dossierTemplateId = &#63;.
	*
	* @param serviceConfigId the service config ID
	* @param dossierTemplateId the dossier template ID
	* @return the number of matching process options
	*/
	public int countBySC_DT(long serviceConfigId, long dossierTemplateId);

	/**
	* Caches the process option in the entity cache if it is enabled.
	*
	* @param processOption the process option
	*/
	public void cacheResult(ProcessOption processOption);

	/**
	* Caches the process options in the entity cache if it is enabled.
	*
	* @param processOptions the process options
	*/
	public void cacheResult(java.util.List<ProcessOption> processOptions);

	/**
	* Creates a new process option with the primary key. Does not add the process option to the database.
	*
	* @param processOptionId the primary key for the new process option
	* @return the new process option
	*/
	public ProcessOption create(long processOptionId);

	/**
	* Removes the process option with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param processOptionId the primary key of the process option
	* @return the process option that was removed
	* @throws NoSuchProcessOptionException if a process option with the primary key could not be found
	*/
	public ProcessOption remove(long processOptionId)
		throws NoSuchProcessOptionException;

	public ProcessOption updateImpl(ProcessOption processOption);

	/**
	* Returns the process option with the primary key or throws a {@link NoSuchProcessOptionException} if it could not be found.
	*
	* @param processOptionId the primary key of the process option
	* @return the process option
	* @throws NoSuchProcessOptionException if a process option with the primary key could not be found
	*/
	public ProcessOption findByPrimaryKey(long processOptionId)
		throws NoSuchProcessOptionException;

	/**
	* Returns the process option with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param processOptionId the primary key of the process option
	* @return the process option, or <code>null</code> if a process option with the primary key could not be found
	*/
	public ProcessOption fetchByPrimaryKey(long processOptionId);

	@Override
	public java.util.Map<java.io.Serializable, ProcessOption> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the process options.
	*
	* @return the process options
	*/
	public java.util.List<ProcessOption> findAll();

	/**
	* Returns a range of all the process options.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of process options
	* @param end the upper bound of the range of process options (not inclusive)
	* @return the range of process options
	*/
	public java.util.List<ProcessOption> findAll(int start, int end);

	/**
	* Returns an ordered range of all the process options.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of process options
	* @param end the upper bound of the range of process options (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of process options
	*/
	public java.util.List<ProcessOption> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessOption> orderByComparator);

	/**
	* Returns an ordered range of all the process options.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of process options
	* @param end the upper bound of the range of process options (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of process options
	*/
	public java.util.List<ProcessOption> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessOption> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the process options from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of process options.
	*
	* @return the number of process options
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}