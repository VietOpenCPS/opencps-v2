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

import org.opencps.dossiermgt.model.MenuConfig;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the menu config service. This utility wraps {@link org.opencps.dossiermgt.service.persistence.impl.MenuConfigPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see MenuConfigPersistence
 * @see org.opencps.dossiermgt.service.persistence.impl.MenuConfigPersistenceImpl
 * @generated
 */
@ProviderType
public class MenuConfigUtil {
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
	public static void clearCache(MenuConfig menuConfig) {
		getPersistence().clearCache(menuConfig);
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
	public static List<MenuConfig> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<MenuConfig> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<MenuConfig> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<MenuConfig> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static MenuConfig update(MenuConfig menuConfig) {
		return getPersistence().update(menuConfig);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static MenuConfig update(MenuConfig menuConfig,
		ServiceContext serviceContext) {
		return getPersistence().update(menuConfig, serviceContext);
	}

	/**
	* Returns all the menu configs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching menu configs
	*/
	public static List<MenuConfig> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the menu configs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of menu configs
	* @param end the upper bound of the range of menu configs (not inclusive)
	* @return the range of matching menu configs
	*/
	public static List<MenuConfig> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the menu configs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of menu configs
	* @param end the upper bound of the range of menu configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching menu configs
	*/
	public static List<MenuConfig> findByUuid(String uuid, int start, int end,
		OrderByComparator<MenuConfig> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the menu configs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of menu configs
	* @param end the upper bound of the range of menu configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching menu configs
	*/
	public static List<MenuConfig> findByUuid(String uuid, int start, int end,
		OrderByComparator<MenuConfig> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first menu config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching menu config
	* @throws NoSuchMenuConfigException if a matching menu config could not be found
	*/
	public static MenuConfig findByUuid_First(String uuid,
		OrderByComparator<MenuConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchMenuConfigException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first menu config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching menu config, or <code>null</code> if a matching menu config could not be found
	*/
	public static MenuConfig fetchByUuid_First(String uuid,
		OrderByComparator<MenuConfig> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last menu config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching menu config
	* @throws NoSuchMenuConfigException if a matching menu config could not be found
	*/
	public static MenuConfig findByUuid_Last(String uuid,
		OrderByComparator<MenuConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchMenuConfigException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last menu config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching menu config, or <code>null</code> if a matching menu config could not be found
	*/
	public static MenuConfig fetchByUuid_Last(String uuid,
		OrderByComparator<MenuConfig> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the menu configs before and after the current menu config in the ordered set where uuid = &#63;.
	*
	* @param menuConfigId the primary key of the current menu config
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next menu config
	* @throws NoSuchMenuConfigException if a menu config with the primary key could not be found
	*/
	public static MenuConfig[] findByUuid_PrevAndNext(long menuConfigId,
		String uuid, OrderByComparator<MenuConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchMenuConfigException {
		return getPersistence()
				   .findByUuid_PrevAndNext(menuConfigId, uuid, orderByComparator);
	}

	/**
	* Removes all the menu configs where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of menu configs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching menu configs
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the menu config where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchMenuConfigException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching menu config
	* @throws NoSuchMenuConfigException if a matching menu config could not be found
	*/
	public static MenuConfig findByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchMenuConfigException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the menu config where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching menu config, or <code>null</code> if a matching menu config could not be found
	*/
	public static MenuConfig fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the menu config where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching menu config, or <code>null</code> if a matching menu config could not be found
	*/
	public static MenuConfig fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the menu config where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the menu config that was removed
	*/
	public static MenuConfig removeByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchMenuConfigException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of menu configs where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching menu configs
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the menu configs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching menu configs
	*/
	public static List<MenuConfig> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the menu configs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of menu configs
	* @param end the upper bound of the range of menu configs (not inclusive)
	* @return the range of matching menu configs
	*/
	public static List<MenuConfig> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the menu configs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of menu configs
	* @param end the upper bound of the range of menu configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching menu configs
	*/
	public static List<MenuConfig> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<MenuConfig> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the menu configs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of menu configs
	* @param end the upper bound of the range of menu configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching menu configs
	*/
	public static List<MenuConfig> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<MenuConfig> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first menu config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching menu config
	* @throws NoSuchMenuConfigException if a matching menu config could not be found
	*/
	public static MenuConfig findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<MenuConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchMenuConfigException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first menu config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching menu config, or <code>null</code> if a matching menu config could not be found
	*/
	public static MenuConfig fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<MenuConfig> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last menu config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching menu config
	* @throws NoSuchMenuConfigException if a matching menu config could not be found
	*/
	public static MenuConfig findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<MenuConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchMenuConfigException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last menu config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching menu config, or <code>null</code> if a matching menu config could not be found
	*/
	public static MenuConfig fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<MenuConfig> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the menu configs before and after the current menu config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param menuConfigId the primary key of the current menu config
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next menu config
	* @throws NoSuchMenuConfigException if a menu config with the primary key could not be found
	*/
	public static MenuConfig[] findByUuid_C_PrevAndNext(long menuConfigId,
		String uuid, long companyId,
		OrderByComparator<MenuConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchMenuConfigException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(menuConfigId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the menu configs where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of menu configs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching menu configs
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the menu configs where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching menu configs
	*/
	public static List<MenuConfig> findByF_BY_GID(long groupId) {
		return getPersistence().findByF_BY_GID(groupId);
	}

	/**
	* Returns a range of all the menu configs where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of menu configs
	* @param end the upper bound of the range of menu configs (not inclusive)
	* @return the range of matching menu configs
	*/
	public static List<MenuConfig> findByF_BY_GID(long groupId, int start,
		int end) {
		return getPersistence().findByF_BY_GID(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the menu configs where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of menu configs
	* @param end the upper bound of the range of menu configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching menu configs
	*/
	public static List<MenuConfig> findByF_BY_GID(long groupId, int start,
		int end, OrderByComparator<MenuConfig> orderByComparator) {
		return getPersistence()
				   .findByF_BY_GID(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the menu configs where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of menu configs
	* @param end the upper bound of the range of menu configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching menu configs
	*/
	public static List<MenuConfig> findByF_BY_GID(long groupId, int start,
		int end, OrderByComparator<MenuConfig> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_BY_GID(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first menu config in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching menu config
	* @throws NoSuchMenuConfigException if a matching menu config could not be found
	*/
	public static MenuConfig findByF_BY_GID_First(long groupId,
		OrderByComparator<MenuConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchMenuConfigException {
		return getPersistence().findByF_BY_GID_First(groupId, orderByComparator);
	}

	/**
	* Returns the first menu config in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching menu config, or <code>null</code> if a matching menu config could not be found
	*/
	public static MenuConfig fetchByF_BY_GID_First(long groupId,
		OrderByComparator<MenuConfig> orderByComparator) {
		return getPersistence().fetchByF_BY_GID_First(groupId, orderByComparator);
	}

	/**
	* Returns the last menu config in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching menu config
	* @throws NoSuchMenuConfigException if a matching menu config could not be found
	*/
	public static MenuConfig findByF_BY_GID_Last(long groupId,
		OrderByComparator<MenuConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchMenuConfigException {
		return getPersistence().findByF_BY_GID_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last menu config in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching menu config, or <code>null</code> if a matching menu config could not be found
	*/
	public static MenuConfig fetchByF_BY_GID_Last(long groupId,
		OrderByComparator<MenuConfig> orderByComparator) {
		return getPersistence().fetchByF_BY_GID_Last(groupId, orderByComparator);
	}

	/**
	* Returns the menu configs before and after the current menu config in the ordered set where groupId = &#63;.
	*
	* @param menuConfigId the primary key of the current menu config
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next menu config
	* @throws NoSuchMenuConfigException if a menu config with the primary key could not be found
	*/
	public static MenuConfig[] findByF_BY_GID_PrevAndNext(long menuConfigId,
		long groupId, OrderByComparator<MenuConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchMenuConfigException {
		return getPersistence()
				   .findByF_BY_GID_PrevAndNext(menuConfigId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the menu configs where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByF_BY_GID(long groupId) {
		getPersistence().removeByF_BY_GID(groupId);
	}

	/**
	* Returns the number of menu configs where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching menu configs
	*/
	public static int countByF_BY_GID(long groupId) {
		return getPersistence().countByF_BY_GID(groupId);
	}

	/**
	* Returns the menu config where menuGroup = &#63; or throws a {@link NoSuchMenuConfigException} if it could not be found.
	*
	* @param menuGroup the menu group
	* @return the matching menu config
	* @throws NoSuchMenuConfigException if a matching menu config could not be found
	*/
	public static MenuConfig findByF_BY_menuGroup(String menuGroup)
		throws org.opencps.dossiermgt.exception.NoSuchMenuConfigException {
		return getPersistence().findByF_BY_menuGroup(menuGroup);
	}

	/**
	* Returns the menu config where menuGroup = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param menuGroup the menu group
	* @return the matching menu config, or <code>null</code> if a matching menu config could not be found
	*/
	public static MenuConfig fetchByF_BY_menuGroup(String menuGroup) {
		return getPersistence().fetchByF_BY_menuGroup(menuGroup);
	}

	/**
	* Returns the menu config where menuGroup = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param menuGroup the menu group
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching menu config, or <code>null</code> if a matching menu config could not be found
	*/
	public static MenuConfig fetchByF_BY_menuGroup(String menuGroup,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_BY_menuGroup(menuGroup, retrieveFromCache);
	}

	/**
	* Removes the menu config where menuGroup = &#63; from the database.
	*
	* @param menuGroup the menu group
	* @return the menu config that was removed
	*/
	public static MenuConfig removeByF_BY_menuGroup(String menuGroup)
		throws org.opencps.dossiermgt.exception.NoSuchMenuConfigException {
		return getPersistence().removeByF_BY_menuGroup(menuGroup);
	}

	/**
	* Returns the number of menu configs where menuGroup = &#63;.
	*
	* @param menuGroup the menu group
	* @return the number of matching menu configs
	*/
	public static int countByF_BY_menuGroup(String menuGroup) {
		return getPersistence().countByF_BY_menuGroup(menuGroup);
	}

	/**
	* Returns all the menu configs where menuConfigId = &#63;.
	*
	* @param menuConfigId the menu config ID
	* @return the matching menu configs
	*/
	public static List<MenuConfig> findByF_A_MID(long menuConfigId) {
		return getPersistence().findByF_A_MID(menuConfigId);
	}

	/**
	* Returns a range of all the menu configs where menuConfigId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param menuConfigId the menu config ID
	* @param start the lower bound of the range of menu configs
	* @param end the upper bound of the range of menu configs (not inclusive)
	* @return the range of matching menu configs
	*/
	public static List<MenuConfig> findByF_A_MID(long menuConfigId, int start,
		int end) {
		return getPersistence().findByF_A_MID(menuConfigId, start, end);
	}

	/**
	* Returns an ordered range of all the menu configs where menuConfigId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param menuConfigId the menu config ID
	* @param start the lower bound of the range of menu configs
	* @param end the upper bound of the range of menu configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching menu configs
	*/
	public static List<MenuConfig> findByF_A_MID(long menuConfigId, int start,
		int end, OrderByComparator<MenuConfig> orderByComparator) {
		return getPersistence()
				   .findByF_A_MID(menuConfigId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the menu configs where menuConfigId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param menuConfigId the menu config ID
	* @param start the lower bound of the range of menu configs
	* @param end the upper bound of the range of menu configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching menu configs
	*/
	public static List<MenuConfig> findByF_A_MID(long menuConfigId, int start,
		int end, OrderByComparator<MenuConfig> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_A_MID(menuConfigId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first menu config in the ordered set where menuConfigId = &#63;.
	*
	* @param menuConfigId the menu config ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching menu config
	* @throws NoSuchMenuConfigException if a matching menu config could not be found
	*/
	public static MenuConfig findByF_A_MID_First(long menuConfigId,
		OrderByComparator<MenuConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchMenuConfigException {
		return getPersistence()
				   .findByF_A_MID_First(menuConfigId, orderByComparator);
	}

	/**
	* Returns the first menu config in the ordered set where menuConfigId = &#63;.
	*
	* @param menuConfigId the menu config ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching menu config, or <code>null</code> if a matching menu config could not be found
	*/
	public static MenuConfig fetchByF_A_MID_First(long menuConfigId,
		OrderByComparator<MenuConfig> orderByComparator) {
		return getPersistence()
				   .fetchByF_A_MID_First(menuConfigId, orderByComparator);
	}

	/**
	* Returns the last menu config in the ordered set where menuConfigId = &#63;.
	*
	* @param menuConfigId the menu config ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching menu config
	* @throws NoSuchMenuConfigException if a matching menu config could not be found
	*/
	public static MenuConfig findByF_A_MID_Last(long menuConfigId,
		OrderByComparator<MenuConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchMenuConfigException {
		return getPersistence()
				   .findByF_A_MID_Last(menuConfigId, orderByComparator);
	}

	/**
	* Returns the last menu config in the ordered set where menuConfigId = &#63;.
	*
	* @param menuConfigId the menu config ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching menu config, or <code>null</code> if a matching menu config could not be found
	*/
	public static MenuConfig fetchByF_A_MID_Last(long menuConfigId,
		OrderByComparator<MenuConfig> orderByComparator) {
		return getPersistence()
				   .fetchByF_A_MID_Last(menuConfigId, orderByComparator);
	}

	/**
	* Returns all the menu configs where menuConfigId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param menuConfigIds the menu config IDs
	* @return the matching menu configs
	*/
	public static List<MenuConfig> findByF_A_MID(long[] menuConfigIds) {
		return getPersistence().findByF_A_MID(menuConfigIds);
	}

	/**
	* Returns a range of all the menu configs where menuConfigId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param menuConfigIds the menu config IDs
	* @param start the lower bound of the range of menu configs
	* @param end the upper bound of the range of menu configs (not inclusive)
	* @return the range of matching menu configs
	*/
	public static List<MenuConfig> findByF_A_MID(long[] menuConfigIds,
		int start, int end) {
		return getPersistence().findByF_A_MID(menuConfigIds, start, end);
	}

	/**
	* Returns an ordered range of all the menu configs where menuConfigId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param menuConfigIds the menu config IDs
	* @param start the lower bound of the range of menu configs
	* @param end the upper bound of the range of menu configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching menu configs
	*/
	public static List<MenuConfig> findByF_A_MID(long[] menuConfigIds,
		int start, int end, OrderByComparator<MenuConfig> orderByComparator) {
		return getPersistence()
				   .findByF_A_MID(menuConfigIds, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the menu configs where menuConfigId = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param menuConfigId the menu config ID
	* @param start the lower bound of the range of menu configs
	* @param end the upper bound of the range of menu configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching menu configs
	*/
	public static List<MenuConfig> findByF_A_MID(long[] menuConfigIds,
		int start, int end, OrderByComparator<MenuConfig> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_A_MID(menuConfigIds, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Removes all the menu configs where menuConfigId = &#63; from the database.
	*
	* @param menuConfigId the menu config ID
	*/
	public static void removeByF_A_MID(long menuConfigId) {
		getPersistence().removeByF_A_MID(menuConfigId);
	}

	/**
	* Returns the number of menu configs where menuConfigId = &#63;.
	*
	* @param menuConfigId the menu config ID
	* @return the number of matching menu configs
	*/
	public static int countByF_A_MID(long menuConfigId) {
		return getPersistence().countByF_A_MID(menuConfigId);
	}

	/**
	* Returns the number of menu configs where menuConfigId = any &#63;.
	*
	* @param menuConfigIds the menu config IDs
	* @return the number of matching menu configs
	*/
	public static int countByF_A_MID(long[] menuConfigIds) {
		return getPersistence().countByF_A_MID(menuConfigIds);
	}

	/**
	* Caches the menu config in the entity cache if it is enabled.
	*
	* @param menuConfig the menu config
	*/
	public static void cacheResult(MenuConfig menuConfig) {
		getPersistence().cacheResult(menuConfig);
	}

	/**
	* Caches the menu configs in the entity cache if it is enabled.
	*
	* @param menuConfigs the menu configs
	*/
	public static void cacheResult(List<MenuConfig> menuConfigs) {
		getPersistence().cacheResult(menuConfigs);
	}

	/**
	* Creates a new menu config with the primary key. Does not add the menu config to the database.
	*
	* @param menuConfigId the primary key for the new menu config
	* @return the new menu config
	*/
	public static MenuConfig create(long menuConfigId) {
		return getPersistence().create(menuConfigId);
	}

	/**
	* Removes the menu config with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param menuConfigId the primary key of the menu config
	* @return the menu config that was removed
	* @throws NoSuchMenuConfigException if a menu config with the primary key could not be found
	*/
	public static MenuConfig remove(long menuConfigId)
		throws org.opencps.dossiermgt.exception.NoSuchMenuConfigException {
		return getPersistence().remove(menuConfigId);
	}

	public static MenuConfig updateImpl(MenuConfig menuConfig) {
		return getPersistence().updateImpl(menuConfig);
	}

	/**
	* Returns the menu config with the primary key or throws a {@link NoSuchMenuConfigException} if it could not be found.
	*
	* @param menuConfigId the primary key of the menu config
	* @return the menu config
	* @throws NoSuchMenuConfigException if a menu config with the primary key could not be found
	*/
	public static MenuConfig findByPrimaryKey(long menuConfigId)
		throws org.opencps.dossiermgt.exception.NoSuchMenuConfigException {
		return getPersistence().findByPrimaryKey(menuConfigId);
	}

	/**
	* Returns the menu config with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param menuConfigId the primary key of the menu config
	* @return the menu config, or <code>null</code> if a menu config with the primary key could not be found
	*/
	public static MenuConfig fetchByPrimaryKey(long menuConfigId) {
		return getPersistence().fetchByPrimaryKey(menuConfigId);
	}

	public static java.util.Map<java.io.Serializable, MenuConfig> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the menu configs.
	*
	* @return the menu configs
	*/
	public static List<MenuConfig> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the menu configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of menu configs
	* @param end the upper bound of the range of menu configs (not inclusive)
	* @return the range of menu configs
	*/
	public static List<MenuConfig> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the menu configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of menu configs
	* @param end the upper bound of the range of menu configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of menu configs
	*/
	public static List<MenuConfig> findAll(int start, int end,
		OrderByComparator<MenuConfig> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the menu configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of menu configs
	* @param end the upper bound of the range of menu configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of menu configs
	*/
	public static List<MenuConfig> findAll(int start, int end,
		OrderByComparator<MenuConfig> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the menu configs from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of menu configs.
	*
	* @return the number of menu configs
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static MenuConfigPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<MenuConfigPersistence, MenuConfigPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(MenuConfigPersistence.class);

		ServiceTracker<MenuConfigPersistence, MenuConfigPersistence> serviceTracker =
			new ServiceTracker<MenuConfigPersistence, MenuConfigPersistence>(bundle.getBundleContext(),
				MenuConfigPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}