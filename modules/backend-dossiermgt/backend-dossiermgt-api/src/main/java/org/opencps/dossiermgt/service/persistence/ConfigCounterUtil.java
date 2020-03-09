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

import org.opencps.dossiermgt.model.ConfigCounter;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the config counter service. This utility wraps {@link org.opencps.dossiermgt.service.persistence.impl.ConfigCounterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see ConfigCounterPersistence
 * @see org.opencps.dossiermgt.service.persistence.impl.ConfigCounterPersistenceImpl
 * @generated
 */
@ProviderType
public class ConfigCounterUtil {
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
	public static void clearCache(ConfigCounter configCounter) {
		getPersistence().clearCache(configCounter);
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
	public static List<ConfigCounter> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ConfigCounter> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ConfigCounter> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ConfigCounter> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ConfigCounter update(ConfigCounter configCounter) {
		return getPersistence().update(configCounter);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ConfigCounter update(ConfigCounter configCounter,
		ServiceContext serviceContext) {
		return getPersistence().update(configCounter, serviceContext);
	}

	/**
	* Returns all the config counters where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching config counters
	*/
	public static List<ConfigCounter> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the config counters where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigCounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of config counters
	* @param end the upper bound of the range of config counters (not inclusive)
	* @return the range of matching config counters
	*/
	public static List<ConfigCounter> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the config counters where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigCounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of config counters
	* @param end the upper bound of the range of config counters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching config counters
	*/
	public static List<ConfigCounter> findByUuid(String uuid, int start,
		int end, OrderByComparator<ConfigCounter> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the config counters where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigCounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of config counters
	* @param end the upper bound of the range of config counters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching config counters
	*/
	public static List<ConfigCounter> findByUuid(String uuid, int start,
		int end, OrderByComparator<ConfigCounter> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first config counter in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching config counter
	* @throws NoSuchConfigCounterException if a matching config counter could not be found
	*/
	public static ConfigCounter findByUuid_First(String uuid,
		OrderByComparator<ConfigCounter> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchConfigCounterException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first config counter in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching config counter, or <code>null</code> if a matching config counter could not be found
	*/
	public static ConfigCounter fetchByUuid_First(String uuid,
		OrderByComparator<ConfigCounter> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last config counter in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching config counter
	* @throws NoSuchConfigCounterException if a matching config counter could not be found
	*/
	public static ConfigCounter findByUuid_Last(String uuid,
		OrderByComparator<ConfigCounter> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchConfigCounterException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last config counter in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching config counter, or <code>null</code> if a matching config counter could not be found
	*/
	public static ConfigCounter fetchByUuid_Last(String uuid,
		OrderByComparator<ConfigCounter> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the config counters before and after the current config counter in the ordered set where uuid = &#63;.
	*
	* @param configCounterId the primary key of the current config counter
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next config counter
	* @throws NoSuchConfigCounterException if a config counter with the primary key could not be found
	*/
	public static ConfigCounter[] findByUuid_PrevAndNext(long configCounterId,
		String uuid, OrderByComparator<ConfigCounter> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchConfigCounterException {
		return getPersistence()
				   .findByUuid_PrevAndNext(configCounterId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the config counters where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of config counters where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching config counters
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the config counter where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchConfigCounterException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching config counter
	* @throws NoSuchConfigCounterException if a matching config counter could not be found
	*/
	public static ConfigCounter findByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchConfigCounterException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the config counter where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching config counter, or <code>null</code> if a matching config counter could not be found
	*/
	public static ConfigCounter fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the config counter where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching config counter, or <code>null</code> if a matching config counter could not be found
	*/
	public static ConfigCounter fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the config counter where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the config counter that was removed
	*/
	public static ConfigCounter removeByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchConfigCounterException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of config counters where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching config counters
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the config counters where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching config counters
	*/
	public static List<ConfigCounter> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the config counters where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigCounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of config counters
	* @param end the upper bound of the range of config counters (not inclusive)
	* @return the range of matching config counters
	*/
	public static List<ConfigCounter> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the config counters where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigCounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of config counters
	* @param end the upper bound of the range of config counters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching config counters
	*/
	public static List<ConfigCounter> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ConfigCounter> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the config counters where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigCounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of config counters
	* @param end the upper bound of the range of config counters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching config counters
	*/
	public static List<ConfigCounter> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ConfigCounter> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first config counter in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching config counter
	* @throws NoSuchConfigCounterException if a matching config counter could not be found
	*/
	public static ConfigCounter findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ConfigCounter> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchConfigCounterException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first config counter in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching config counter, or <code>null</code> if a matching config counter could not be found
	*/
	public static ConfigCounter fetchByUuid_C_First(String uuid,
		long companyId, OrderByComparator<ConfigCounter> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last config counter in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching config counter
	* @throws NoSuchConfigCounterException if a matching config counter could not be found
	*/
	public static ConfigCounter findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ConfigCounter> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchConfigCounterException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last config counter in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching config counter, or <code>null</code> if a matching config counter could not be found
	*/
	public static ConfigCounter fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ConfigCounter> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the config counters before and after the current config counter in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param configCounterId the primary key of the current config counter
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next config counter
	* @throws NoSuchConfigCounterException if a config counter with the primary key could not be found
	*/
	public static ConfigCounter[] findByUuid_C_PrevAndNext(
		long configCounterId, String uuid, long companyId,
		OrderByComparator<ConfigCounter> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchConfigCounterException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(configCounterId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the config counters where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of config counters where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching config counters
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the config counters where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching config counters
	*/
	public static List<ConfigCounter> findByG_ID(long groupId) {
		return getPersistence().findByG_ID(groupId);
	}

	/**
	* Returns a range of all the config counters where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigCounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of config counters
	* @param end the upper bound of the range of config counters (not inclusive)
	* @return the range of matching config counters
	*/
	public static List<ConfigCounter> findByG_ID(long groupId, int start,
		int end) {
		return getPersistence().findByG_ID(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the config counters where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigCounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of config counters
	* @param end the upper bound of the range of config counters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching config counters
	*/
	public static List<ConfigCounter> findByG_ID(long groupId, int start,
		int end, OrderByComparator<ConfigCounter> orderByComparator) {
		return getPersistence()
				   .findByG_ID(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the config counters where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigCounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of config counters
	* @param end the upper bound of the range of config counters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching config counters
	*/
	public static List<ConfigCounter> findByG_ID(long groupId, int start,
		int end, OrderByComparator<ConfigCounter> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_ID(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first config counter in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching config counter
	* @throws NoSuchConfigCounterException if a matching config counter could not be found
	*/
	public static ConfigCounter findByG_ID_First(long groupId,
		OrderByComparator<ConfigCounter> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchConfigCounterException {
		return getPersistence().findByG_ID_First(groupId, orderByComparator);
	}

	/**
	* Returns the first config counter in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching config counter, or <code>null</code> if a matching config counter could not be found
	*/
	public static ConfigCounter fetchByG_ID_First(long groupId,
		OrderByComparator<ConfigCounter> orderByComparator) {
		return getPersistence().fetchByG_ID_First(groupId, orderByComparator);
	}

	/**
	* Returns the last config counter in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching config counter
	* @throws NoSuchConfigCounterException if a matching config counter could not be found
	*/
	public static ConfigCounter findByG_ID_Last(long groupId,
		OrderByComparator<ConfigCounter> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchConfigCounterException {
		return getPersistence().findByG_ID_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last config counter in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching config counter, or <code>null</code> if a matching config counter could not be found
	*/
	public static ConfigCounter fetchByG_ID_Last(long groupId,
		OrderByComparator<ConfigCounter> orderByComparator) {
		return getPersistence().fetchByG_ID_Last(groupId, orderByComparator);
	}

	/**
	* Returns the config counters before and after the current config counter in the ordered set where groupId = &#63;.
	*
	* @param configCounterId the primary key of the current config counter
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next config counter
	* @throws NoSuchConfigCounterException if a config counter with the primary key could not be found
	*/
	public static ConfigCounter[] findByG_ID_PrevAndNext(long configCounterId,
		long groupId, OrderByComparator<ConfigCounter> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchConfigCounterException {
		return getPersistence()
				   .findByG_ID_PrevAndNext(configCounterId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the config counters where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByG_ID(long groupId) {
		getPersistence().removeByG_ID(groupId);
	}

	/**
	* Returns the number of config counters where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching config counters
	*/
	public static int countByG_ID(long groupId) {
		return getPersistence().countByG_ID(groupId);
	}

	/**
	* Returns the config counter where groupId = &#63; and counterCode = &#63; or throws a {@link NoSuchConfigCounterException} if it could not be found.
	*
	* @param groupId the group ID
	* @param counterCode the counter code
	* @return the matching config counter
	* @throws NoSuchConfigCounterException if a matching config counter could not be found
	*/
	public static ConfigCounter findByGID_CODE(long groupId, String counterCode)
		throws org.opencps.dossiermgt.exception.NoSuchConfigCounterException {
		return getPersistence().findByGID_CODE(groupId, counterCode);
	}

	/**
	* Returns the config counter where groupId = &#63; and counterCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param counterCode the counter code
	* @return the matching config counter, or <code>null</code> if a matching config counter could not be found
	*/
	public static ConfigCounter fetchByGID_CODE(long groupId, String counterCode) {
		return getPersistence().fetchByGID_CODE(groupId, counterCode);
	}

	/**
	* Returns the config counter where groupId = &#63; and counterCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param counterCode the counter code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching config counter, or <code>null</code> if a matching config counter could not be found
	*/
	public static ConfigCounter fetchByGID_CODE(long groupId,
		String counterCode, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByGID_CODE(groupId, counterCode, retrieveFromCache);
	}

	/**
	* Removes the config counter where groupId = &#63; and counterCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param counterCode the counter code
	* @return the config counter that was removed
	*/
	public static ConfigCounter removeByGID_CODE(long groupId,
		String counterCode)
		throws org.opencps.dossiermgt.exception.NoSuchConfigCounterException {
		return getPersistence().removeByGID_CODE(groupId, counterCode);
	}

	/**
	* Returns the number of config counters where groupId = &#63; and counterCode = &#63;.
	*
	* @param groupId the group ID
	* @param counterCode the counter code
	* @return the number of matching config counters
	*/
	public static int countByGID_CODE(long groupId, String counterCode) {
		return getPersistence().countByGID_CODE(groupId, counterCode);
	}

	/**
	* Caches the config counter in the entity cache if it is enabled.
	*
	* @param configCounter the config counter
	*/
	public static void cacheResult(ConfigCounter configCounter) {
		getPersistence().cacheResult(configCounter);
	}

	/**
	* Caches the config counters in the entity cache if it is enabled.
	*
	* @param configCounters the config counters
	*/
	public static void cacheResult(List<ConfigCounter> configCounters) {
		getPersistence().cacheResult(configCounters);
	}

	/**
	* Creates a new config counter with the primary key. Does not add the config counter to the database.
	*
	* @param configCounterId the primary key for the new config counter
	* @return the new config counter
	*/
	public static ConfigCounter create(long configCounterId) {
		return getPersistence().create(configCounterId);
	}

	/**
	* Removes the config counter with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param configCounterId the primary key of the config counter
	* @return the config counter that was removed
	* @throws NoSuchConfigCounterException if a config counter with the primary key could not be found
	*/
	public static ConfigCounter remove(long configCounterId)
		throws org.opencps.dossiermgt.exception.NoSuchConfigCounterException {
		return getPersistence().remove(configCounterId);
	}

	public static ConfigCounter updateImpl(ConfigCounter configCounter) {
		return getPersistence().updateImpl(configCounter);
	}

	/**
	* Returns the config counter with the primary key or throws a {@link NoSuchConfigCounterException} if it could not be found.
	*
	* @param configCounterId the primary key of the config counter
	* @return the config counter
	* @throws NoSuchConfigCounterException if a config counter with the primary key could not be found
	*/
	public static ConfigCounter findByPrimaryKey(long configCounterId)
		throws org.opencps.dossiermgt.exception.NoSuchConfigCounterException {
		return getPersistence().findByPrimaryKey(configCounterId);
	}

	/**
	* Returns the config counter with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param configCounterId the primary key of the config counter
	* @return the config counter, or <code>null</code> if a config counter with the primary key could not be found
	*/
	public static ConfigCounter fetchByPrimaryKey(long configCounterId) {
		return getPersistence().fetchByPrimaryKey(configCounterId);
	}

	public static java.util.Map<java.io.Serializable, ConfigCounter> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the config counters.
	*
	* @return the config counters
	*/
	public static List<ConfigCounter> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the config counters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigCounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of config counters
	* @param end the upper bound of the range of config counters (not inclusive)
	* @return the range of config counters
	*/
	public static List<ConfigCounter> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the config counters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigCounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of config counters
	* @param end the upper bound of the range of config counters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of config counters
	*/
	public static List<ConfigCounter> findAll(int start, int end,
		OrderByComparator<ConfigCounter> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the config counters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigCounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of config counters
	* @param end the upper bound of the range of config counters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of config counters
	*/
	public static List<ConfigCounter> findAll(int start, int end,
		OrderByComparator<ConfigCounter> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the config counters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of config counters.
	*
	* @return the number of config counters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ConfigCounterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ConfigCounterPersistence, ConfigCounterPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ConfigCounterPersistence.class);

		ServiceTracker<ConfigCounterPersistence, ConfigCounterPersistence> serviceTracker =
			new ServiceTracker<ConfigCounterPersistence, ConfigCounterPersistence>(bundle.getBundleContext(),
				ConfigCounterPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}