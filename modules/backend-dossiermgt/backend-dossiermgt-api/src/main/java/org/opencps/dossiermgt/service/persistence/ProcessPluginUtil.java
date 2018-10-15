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

import org.opencps.dossiermgt.model.ProcessPlugin;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the process plugin service. This utility wraps {@link org.opencps.dossiermgt.service.persistence.impl.ProcessPluginPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see ProcessPluginPersistence
 * @see org.opencps.dossiermgt.service.persistence.impl.ProcessPluginPersistenceImpl
 * @generated
 */
@ProviderType
public class ProcessPluginUtil {
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
	public static void clearCache(ProcessPlugin processPlugin) {
		getPersistence().clearCache(processPlugin);
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
	public static List<ProcessPlugin> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ProcessPlugin> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ProcessPlugin> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ProcessPlugin> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ProcessPlugin update(ProcessPlugin processPlugin) {
		return getPersistence().update(processPlugin);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ProcessPlugin update(ProcessPlugin processPlugin,
		ServiceContext serviceContext) {
		return getPersistence().update(processPlugin, serviceContext);
	}

	/**
	* Returns all the process plugins where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching process plugins
	*/
	public static List<ProcessPlugin> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

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
	public static List<ProcessPlugin> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static List<ProcessPlugin> findByUuid(String uuid, int start,
		int end, OrderByComparator<ProcessPlugin> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

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
	public static List<ProcessPlugin> findByUuid(String uuid, int start,
		int end, OrderByComparator<ProcessPlugin> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first process plugin in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process plugin
	* @throws NoSuchProcessPluginException if a matching process plugin could not be found
	*/
	public static ProcessPlugin findByUuid_First(String uuid,
		OrderByComparator<ProcessPlugin> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessPluginException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first process plugin in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process plugin, or <code>null</code> if a matching process plugin could not be found
	*/
	public static ProcessPlugin fetchByUuid_First(String uuid,
		OrderByComparator<ProcessPlugin> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last process plugin in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process plugin
	* @throws NoSuchProcessPluginException if a matching process plugin could not be found
	*/
	public static ProcessPlugin findByUuid_Last(String uuid,
		OrderByComparator<ProcessPlugin> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessPluginException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last process plugin in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process plugin, or <code>null</code> if a matching process plugin could not be found
	*/
	public static ProcessPlugin fetchByUuid_Last(String uuid,
		OrderByComparator<ProcessPlugin> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the process plugins before and after the current process plugin in the ordered set where uuid = &#63;.
	*
	* @param processPluginId the primary key of the current process plugin
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process plugin
	* @throws NoSuchProcessPluginException if a process plugin with the primary key could not be found
	*/
	public static ProcessPlugin[] findByUuid_PrevAndNext(long processPluginId,
		String uuid, OrderByComparator<ProcessPlugin> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessPluginException {
		return getPersistence()
				   .findByUuid_PrevAndNext(processPluginId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the process plugins where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of process plugins where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching process plugins
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the process plugin where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchProcessPluginException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching process plugin
	* @throws NoSuchProcessPluginException if a matching process plugin could not be found
	*/
	public static ProcessPlugin findByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchProcessPluginException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the process plugin where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching process plugin, or <code>null</code> if a matching process plugin could not be found
	*/
	public static ProcessPlugin fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the process plugin where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching process plugin, or <code>null</code> if a matching process plugin could not be found
	*/
	public static ProcessPlugin fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the process plugin where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the process plugin that was removed
	*/
	public static ProcessPlugin removeByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchProcessPluginException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of process plugins where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching process plugins
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the process plugins where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching process plugins
	*/
	public static List<ProcessPlugin> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

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
	public static List<ProcessPlugin> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

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
	public static List<ProcessPlugin> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ProcessPlugin> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

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
	public static List<ProcessPlugin> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ProcessPlugin> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first process plugin in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process plugin
	* @throws NoSuchProcessPluginException if a matching process plugin could not be found
	*/
	public static ProcessPlugin findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ProcessPlugin> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessPluginException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first process plugin in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process plugin, or <code>null</code> if a matching process plugin could not be found
	*/
	public static ProcessPlugin fetchByUuid_C_First(String uuid,
		long companyId, OrderByComparator<ProcessPlugin> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last process plugin in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process plugin
	* @throws NoSuchProcessPluginException if a matching process plugin could not be found
	*/
	public static ProcessPlugin findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ProcessPlugin> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessPluginException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last process plugin in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process plugin, or <code>null</code> if a matching process plugin could not be found
	*/
	public static ProcessPlugin fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ProcessPlugin> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

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
	public static ProcessPlugin[] findByUuid_C_PrevAndNext(
		long processPluginId, String uuid, long companyId,
		OrderByComparator<ProcessPlugin> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessPluginException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(processPluginId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the process plugins where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of process plugins where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching process plugins
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the process plugins where stepCode = &#63; and serviceProcessId = &#63;.
	*
	* @param stepCode the step code
	* @param serviceProcessId the service process ID
	* @return the matching process plugins
	*/
	public static List<ProcessPlugin> findBySC_SPID(String stepCode,
		long serviceProcessId) {
		return getPersistence().findBySC_SPID(stepCode, serviceProcessId);
	}

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
	public static List<ProcessPlugin> findBySC_SPID(String stepCode,
		long serviceProcessId, int start, int end) {
		return getPersistence()
				   .findBySC_SPID(stepCode, serviceProcessId, start, end);
	}

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
	public static List<ProcessPlugin> findBySC_SPID(String stepCode,
		long serviceProcessId, int start, int end,
		OrderByComparator<ProcessPlugin> orderByComparator) {
		return getPersistence()
				   .findBySC_SPID(stepCode, serviceProcessId, start, end,
			orderByComparator);
	}

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
	public static List<ProcessPlugin> findBySC_SPID(String stepCode,
		long serviceProcessId, int start, int end,
		OrderByComparator<ProcessPlugin> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findBySC_SPID(stepCode, serviceProcessId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first process plugin in the ordered set where stepCode = &#63; and serviceProcessId = &#63;.
	*
	* @param stepCode the step code
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process plugin
	* @throws NoSuchProcessPluginException if a matching process plugin could not be found
	*/
	public static ProcessPlugin findBySC_SPID_First(String stepCode,
		long serviceProcessId,
		OrderByComparator<ProcessPlugin> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessPluginException {
		return getPersistence()
				   .findBySC_SPID_First(stepCode, serviceProcessId,
			orderByComparator);
	}

	/**
	* Returns the first process plugin in the ordered set where stepCode = &#63; and serviceProcessId = &#63;.
	*
	* @param stepCode the step code
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process plugin, or <code>null</code> if a matching process plugin could not be found
	*/
	public static ProcessPlugin fetchBySC_SPID_First(String stepCode,
		long serviceProcessId,
		OrderByComparator<ProcessPlugin> orderByComparator) {
		return getPersistence()
				   .fetchBySC_SPID_First(stepCode, serviceProcessId,
			orderByComparator);
	}

	/**
	* Returns the last process plugin in the ordered set where stepCode = &#63; and serviceProcessId = &#63;.
	*
	* @param stepCode the step code
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process plugin
	* @throws NoSuchProcessPluginException if a matching process plugin could not be found
	*/
	public static ProcessPlugin findBySC_SPID_Last(String stepCode,
		long serviceProcessId,
		OrderByComparator<ProcessPlugin> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessPluginException {
		return getPersistence()
				   .findBySC_SPID_Last(stepCode, serviceProcessId,
			orderByComparator);
	}

	/**
	* Returns the last process plugin in the ordered set where stepCode = &#63; and serviceProcessId = &#63;.
	*
	* @param stepCode the step code
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process plugin, or <code>null</code> if a matching process plugin could not be found
	*/
	public static ProcessPlugin fetchBySC_SPID_Last(String stepCode,
		long serviceProcessId,
		OrderByComparator<ProcessPlugin> orderByComparator) {
		return getPersistence()
				   .fetchBySC_SPID_Last(stepCode, serviceProcessId,
			orderByComparator);
	}

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
	public static ProcessPlugin[] findBySC_SPID_PrevAndNext(
		long processPluginId, String stepCode, long serviceProcessId,
		OrderByComparator<ProcessPlugin> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessPluginException {
		return getPersistence()
				   .findBySC_SPID_PrevAndNext(processPluginId, stepCode,
			serviceProcessId, orderByComparator);
	}

	/**
	* Removes all the process plugins where stepCode = &#63; and serviceProcessId = &#63; from the database.
	*
	* @param stepCode the step code
	* @param serviceProcessId the service process ID
	*/
	public static void removeBySC_SPID(String stepCode, long serviceProcessId) {
		getPersistence().removeBySC_SPID(stepCode, serviceProcessId);
	}

	/**
	* Returns the number of process plugins where stepCode = &#63; and serviceProcessId = &#63;.
	*
	* @param stepCode the step code
	* @param serviceProcessId the service process ID
	* @return the number of matching process plugins
	*/
	public static int countBySC_SPID(String stepCode, long serviceProcessId) {
		return getPersistence().countBySC_SPID(stepCode, serviceProcessId);
	}

	/**
	* Caches the process plugin in the entity cache if it is enabled.
	*
	* @param processPlugin the process plugin
	*/
	public static void cacheResult(ProcessPlugin processPlugin) {
		getPersistence().cacheResult(processPlugin);
	}

	/**
	* Caches the process plugins in the entity cache if it is enabled.
	*
	* @param processPlugins the process plugins
	*/
	public static void cacheResult(List<ProcessPlugin> processPlugins) {
		getPersistence().cacheResult(processPlugins);
	}

	/**
	* Creates a new process plugin with the primary key. Does not add the process plugin to the database.
	*
	* @param processPluginId the primary key for the new process plugin
	* @return the new process plugin
	*/
	public static ProcessPlugin create(long processPluginId) {
		return getPersistence().create(processPluginId);
	}

	/**
	* Removes the process plugin with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param processPluginId the primary key of the process plugin
	* @return the process plugin that was removed
	* @throws NoSuchProcessPluginException if a process plugin with the primary key could not be found
	*/
	public static ProcessPlugin remove(long processPluginId)
		throws org.opencps.dossiermgt.exception.NoSuchProcessPluginException {
		return getPersistence().remove(processPluginId);
	}

	public static ProcessPlugin updateImpl(ProcessPlugin processPlugin) {
		return getPersistence().updateImpl(processPlugin);
	}

	/**
	* Returns the process plugin with the primary key or throws a {@link NoSuchProcessPluginException} if it could not be found.
	*
	* @param processPluginId the primary key of the process plugin
	* @return the process plugin
	* @throws NoSuchProcessPluginException if a process plugin with the primary key could not be found
	*/
	public static ProcessPlugin findByPrimaryKey(long processPluginId)
		throws org.opencps.dossiermgt.exception.NoSuchProcessPluginException {
		return getPersistence().findByPrimaryKey(processPluginId);
	}

	/**
	* Returns the process plugin with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param processPluginId the primary key of the process plugin
	* @return the process plugin, or <code>null</code> if a process plugin with the primary key could not be found
	*/
	public static ProcessPlugin fetchByPrimaryKey(long processPluginId) {
		return getPersistence().fetchByPrimaryKey(processPluginId);
	}

	public static java.util.Map<java.io.Serializable, ProcessPlugin> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the process plugins.
	*
	* @return the process plugins
	*/
	public static List<ProcessPlugin> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<ProcessPlugin> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<ProcessPlugin> findAll(int start, int end,
		OrderByComparator<ProcessPlugin> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<ProcessPlugin> findAll(int start, int end,
		OrderByComparator<ProcessPlugin> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the process plugins from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of process plugins.
	*
	* @return the number of process plugins
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ProcessPluginPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ProcessPluginPersistence, ProcessPluginPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ProcessPluginPersistence.class);

		ServiceTracker<ProcessPluginPersistence, ProcessPluginPersistence> serviceTracker =
			new ServiceTracker<ProcessPluginPersistence, ProcessPluginPersistence>(bundle.getBundleContext(),
				ProcessPluginPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}