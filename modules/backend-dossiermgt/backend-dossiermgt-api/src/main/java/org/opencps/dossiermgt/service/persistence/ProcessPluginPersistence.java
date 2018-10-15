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

import org.opencps.dossiermgt.exception.NoSuchProcessPluginException;
import org.opencps.dossiermgt.model.ProcessPlugin;

/**
 * The persistence interface for the process plugin service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.dossiermgt.service.persistence.impl.ProcessPluginPersistenceImpl
 * @see ProcessPluginUtil
 * @generated
 */
@ProviderType
public interface ProcessPluginPersistence extends BasePersistence<ProcessPlugin> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ProcessPluginUtil} to access the process plugin persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the process plugins where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching process plugins
	*/
	public java.util.List<ProcessPlugin> findByUuid(String uuid);

	/**
	* Returns a range of all the process plugins where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessPluginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of process plugins
	* @param end the upper bound of the range of process plugins (not inclusive)
	* @return the range of matching process plugins
	*/
	public java.util.List<ProcessPlugin> findByUuid(String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the process plugins where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessPluginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of process plugins
	* @param end the upper bound of the range of process plugins (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching process plugins
	*/
	public java.util.List<ProcessPlugin> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessPlugin> orderByComparator);

	/**
	* Returns an ordered range of all the process plugins where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessPluginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of process plugins
	* @param end the upper bound of the range of process plugins (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching process plugins
	*/
	public java.util.List<ProcessPlugin> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessPlugin> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first process plugin in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process plugin
	* @throws NoSuchProcessPluginException if a matching process plugin could not be found
	*/
	public ProcessPlugin findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessPlugin> orderByComparator)
		throws NoSuchProcessPluginException;

	/**
	* Returns the first process plugin in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process plugin, or <code>null</code> if a matching process plugin could not be found
	*/
	public ProcessPlugin fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessPlugin> orderByComparator);

	/**
	* Returns the last process plugin in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process plugin
	* @throws NoSuchProcessPluginException if a matching process plugin could not be found
	*/
	public ProcessPlugin findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessPlugin> orderByComparator)
		throws NoSuchProcessPluginException;

	/**
	* Returns the last process plugin in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process plugin, or <code>null</code> if a matching process plugin could not be found
	*/
	public ProcessPlugin fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessPlugin> orderByComparator);

	/**
	* Returns the process plugins before and after the current process plugin in the ordered set where uuid = &#63;.
	*
	* @param processPluginId the primary key of the current process plugin
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process plugin
	* @throws NoSuchProcessPluginException if a process plugin with the primary key could not be found
	*/
	public ProcessPlugin[] findByUuid_PrevAndNext(long processPluginId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessPlugin> orderByComparator)
		throws NoSuchProcessPluginException;

	/**
	* Removes all the process plugins where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of process plugins where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching process plugins
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the process plugin where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchProcessPluginException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching process plugin
	* @throws NoSuchProcessPluginException if a matching process plugin could not be found
	*/
	public ProcessPlugin findByUUID_G(String uuid, long groupId)
		throws NoSuchProcessPluginException;

	/**
	* Returns the process plugin where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching process plugin, or <code>null</code> if a matching process plugin could not be found
	*/
	public ProcessPlugin fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the process plugin where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching process plugin, or <code>null</code> if a matching process plugin could not be found
	*/
	public ProcessPlugin fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the process plugin where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the process plugin that was removed
	*/
	public ProcessPlugin removeByUUID_G(String uuid, long groupId)
		throws NoSuchProcessPluginException;

	/**
	* Returns the number of process plugins where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching process plugins
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the process plugins where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching process plugins
	*/
	public java.util.List<ProcessPlugin> findByUuid_C(String uuid,
		long companyId);

	/**
	* Returns a range of all the process plugins where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessPluginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of process plugins
	* @param end the upper bound of the range of process plugins (not inclusive)
	* @return the range of matching process plugins
	*/
	public java.util.List<ProcessPlugin> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the process plugins where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessPluginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of process plugins
	* @param end the upper bound of the range of process plugins (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching process plugins
	*/
	public java.util.List<ProcessPlugin> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessPlugin> orderByComparator);

	/**
	* Returns an ordered range of all the process plugins where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessPluginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of process plugins
	* @param end the upper bound of the range of process plugins (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching process plugins
	*/
	public java.util.List<ProcessPlugin> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessPlugin> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first process plugin in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process plugin
	* @throws NoSuchProcessPluginException if a matching process plugin could not be found
	*/
	public ProcessPlugin findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessPlugin> orderByComparator)
		throws NoSuchProcessPluginException;

	/**
	* Returns the first process plugin in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process plugin, or <code>null</code> if a matching process plugin could not be found
	*/
	public ProcessPlugin fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessPlugin> orderByComparator);

	/**
	* Returns the last process plugin in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process plugin
	* @throws NoSuchProcessPluginException if a matching process plugin could not be found
	*/
	public ProcessPlugin findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessPlugin> orderByComparator)
		throws NoSuchProcessPluginException;

	/**
	* Returns the last process plugin in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process plugin, or <code>null</code> if a matching process plugin could not be found
	*/
	public ProcessPlugin fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessPlugin> orderByComparator);

	/**
	* Returns the process plugins before and after the current process plugin in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param processPluginId the primary key of the current process plugin
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process plugin
	* @throws NoSuchProcessPluginException if a process plugin with the primary key could not be found
	*/
	public ProcessPlugin[] findByUuid_C_PrevAndNext(long processPluginId,
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessPlugin> orderByComparator)
		throws NoSuchProcessPluginException;

	/**
	* Removes all the process plugins where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of process plugins where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching process plugins
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the process plugins where stepCode = &#63; and serviceProcessId = &#63;.
	*
	* @param stepCode the step code
	* @param serviceProcessId the service process ID
	* @return the matching process plugins
	*/
	public java.util.List<ProcessPlugin> findBySC_SPID(String stepCode,
		long serviceProcessId);

	/**
	* Returns a range of all the process plugins where stepCode = &#63; and serviceProcessId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessPluginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param stepCode the step code
	* @param serviceProcessId the service process ID
	* @param start the lower bound of the range of process plugins
	* @param end the upper bound of the range of process plugins (not inclusive)
	* @return the range of matching process plugins
	*/
	public java.util.List<ProcessPlugin> findBySC_SPID(String stepCode,
		long serviceProcessId, int start, int end);

	/**
	* Returns an ordered range of all the process plugins where stepCode = &#63; and serviceProcessId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessPluginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param stepCode the step code
	* @param serviceProcessId the service process ID
	* @param start the lower bound of the range of process plugins
	* @param end the upper bound of the range of process plugins (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching process plugins
	*/
	public java.util.List<ProcessPlugin> findBySC_SPID(String stepCode,
		long serviceProcessId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessPlugin> orderByComparator);

	/**
	* Returns an ordered range of all the process plugins where stepCode = &#63; and serviceProcessId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessPluginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param stepCode the step code
	* @param serviceProcessId the service process ID
	* @param start the lower bound of the range of process plugins
	* @param end the upper bound of the range of process plugins (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching process plugins
	*/
	public java.util.List<ProcessPlugin> findBySC_SPID(String stepCode,
		long serviceProcessId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessPlugin> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first process plugin in the ordered set where stepCode = &#63; and serviceProcessId = &#63;.
	*
	* @param stepCode the step code
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process plugin
	* @throws NoSuchProcessPluginException if a matching process plugin could not be found
	*/
	public ProcessPlugin findBySC_SPID_First(String stepCode,
		long serviceProcessId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessPlugin> orderByComparator)
		throws NoSuchProcessPluginException;

	/**
	* Returns the first process plugin in the ordered set where stepCode = &#63; and serviceProcessId = &#63;.
	*
	* @param stepCode the step code
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process plugin, or <code>null</code> if a matching process plugin could not be found
	*/
	public ProcessPlugin fetchBySC_SPID_First(String stepCode,
		long serviceProcessId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessPlugin> orderByComparator);

	/**
	* Returns the last process plugin in the ordered set where stepCode = &#63; and serviceProcessId = &#63;.
	*
	* @param stepCode the step code
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process plugin
	* @throws NoSuchProcessPluginException if a matching process plugin could not be found
	*/
	public ProcessPlugin findBySC_SPID_Last(String stepCode,
		long serviceProcessId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessPlugin> orderByComparator)
		throws NoSuchProcessPluginException;

	/**
	* Returns the last process plugin in the ordered set where stepCode = &#63; and serviceProcessId = &#63;.
	*
	* @param stepCode the step code
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process plugin, or <code>null</code> if a matching process plugin could not be found
	*/
	public ProcessPlugin fetchBySC_SPID_Last(String stepCode,
		long serviceProcessId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessPlugin> orderByComparator);

	/**
	* Returns the process plugins before and after the current process plugin in the ordered set where stepCode = &#63; and serviceProcessId = &#63;.
	*
	* @param processPluginId the primary key of the current process plugin
	* @param stepCode the step code
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process plugin
	* @throws NoSuchProcessPluginException if a process plugin with the primary key could not be found
	*/
	public ProcessPlugin[] findBySC_SPID_PrevAndNext(long processPluginId,
		String stepCode, long serviceProcessId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessPlugin> orderByComparator)
		throws NoSuchProcessPluginException;

	/**
	* Removes all the process plugins where stepCode = &#63; and serviceProcessId = &#63; from the database.
	*
	* @param stepCode the step code
	* @param serviceProcessId the service process ID
	*/
	public void removeBySC_SPID(String stepCode, long serviceProcessId);

	/**
	* Returns the number of process plugins where stepCode = &#63; and serviceProcessId = &#63;.
	*
	* @param stepCode the step code
	* @param serviceProcessId the service process ID
	* @return the number of matching process plugins
	*/
	public int countBySC_SPID(String stepCode, long serviceProcessId);

	/**
	* Caches the process plugin in the entity cache if it is enabled.
	*
	* @param processPlugin the process plugin
	*/
	public void cacheResult(ProcessPlugin processPlugin);

	/**
	* Caches the process plugins in the entity cache if it is enabled.
	*
	* @param processPlugins the process plugins
	*/
	public void cacheResult(java.util.List<ProcessPlugin> processPlugins);

	/**
	* Creates a new process plugin with the primary key. Does not add the process plugin to the database.
	*
	* @param processPluginId the primary key for the new process plugin
	* @return the new process plugin
	*/
	public ProcessPlugin create(long processPluginId);

	/**
	* Removes the process plugin with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param processPluginId the primary key of the process plugin
	* @return the process plugin that was removed
	* @throws NoSuchProcessPluginException if a process plugin with the primary key could not be found
	*/
	public ProcessPlugin remove(long processPluginId)
		throws NoSuchProcessPluginException;

	public ProcessPlugin updateImpl(ProcessPlugin processPlugin);

	/**
	* Returns the process plugin with the primary key or throws a {@link NoSuchProcessPluginException} if it could not be found.
	*
	* @param processPluginId the primary key of the process plugin
	* @return the process plugin
	* @throws NoSuchProcessPluginException if a process plugin with the primary key could not be found
	*/
	public ProcessPlugin findByPrimaryKey(long processPluginId)
		throws NoSuchProcessPluginException;

	/**
	* Returns the process plugin with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param processPluginId the primary key of the process plugin
	* @return the process plugin, or <code>null</code> if a process plugin with the primary key could not be found
	*/
	public ProcessPlugin fetchByPrimaryKey(long processPluginId);

	@Override
	public java.util.Map<java.io.Serializable, ProcessPlugin> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the process plugins.
	*
	* @return the process plugins
	*/
	public java.util.List<ProcessPlugin> findAll();

	/**
	* Returns a range of all the process plugins.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessPluginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of process plugins
	* @param end the upper bound of the range of process plugins (not inclusive)
	* @return the range of process plugins
	*/
	public java.util.List<ProcessPlugin> findAll(int start, int end);

	/**
	* Returns an ordered range of all the process plugins.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessPluginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of process plugins
	* @param end the upper bound of the range of process plugins (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of process plugins
	*/
	public java.util.List<ProcessPlugin> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessPlugin> orderByComparator);

	/**
	* Returns an ordered range of all the process plugins.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessPluginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of process plugins
	* @param end the upper bound of the range of process plugins (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of process plugins
	*/
	public java.util.List<ProcessPlugin> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessPlugin> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the process plugins from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of process plugins.
	*
	* @return the number of process plugins
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}