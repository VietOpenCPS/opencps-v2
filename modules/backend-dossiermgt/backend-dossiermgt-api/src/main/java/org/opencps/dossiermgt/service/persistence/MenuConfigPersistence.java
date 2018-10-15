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

import org.opencps.dossiermgt.exception.NoSuchMenuConfigException;
import org.opencps.dossiermgt.model.MenuConfig;

/**
 * The persistence interface for the menu config service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.dossiermgt.service.persistence.impl.MenuConfigPersistenceImpl
 * @see MenuConfigUtil
 * @generated
 */
@ProviderType
public interface MenuConfigPersistence extends BasePersistence<MenuConfig> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MenuConfigUtil} to access the menu config persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the menu configs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching menu configs
	*/
	public java.util.List<MenuConfig> findByUuid(String uuid);

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
	public java.util.List<MenuConfig> findByUuid(String uuid, int start, int end);

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
	public java.util.List<MenuConfig> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<MenuConfig> orderByComparator);

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
	public java.util.List<MenuConfig> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<MenuConfig> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first menu config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching menu config
	* @throws NoSuchMenuConfigException if a matching menu config could not be found
	*/
	public MenuConfig findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<MenuConfig> orderByComparator)
		throws NoSuchMenuConfigException;

	/**
	* Returns the first menu config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching menu config, or <code>null</code> if a matching menu config could not be found
	*/
	public MenuConfig fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<MenuConfig> orderByComparator);

	/**
	* Returns the last menu config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching menu config
	* @throws NoSuchMenuConfigException if a matching menu config could not be found
	*/
	public MenuConfig findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<MenuConfig> orderByComparator)
		throws NoSuchMenuConfigException;

	/**
	* Returns the last menu config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching menu config, or <code>null</code> if a matching menu config could not be found
	*/
	public MenuConfig fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<MenuConfig> orderByComparator);

	/**
	* Returns the menu configs before and after the current menu config in the ordered set where uuid = &#63;.
	*
	* @param menuConfigId the primary key of the current menu config
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next menu config
	* @throws NoSuchMenuConfigException if a menu config with the primary key could not be found
	*/
	public MenuConfig[] findByUuid_PrevAndNext(long menuConfigId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<MenuConfig> orderByComparator)
		throws NoSuchMenuConfigException;

	/**
	* Removes all the menu configs where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of menu configs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching menu configs
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the menu config where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchMenuConfigException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching menu config
	* @throws NoSuchMenuConfigException if a matching menu config could not be found
	*/
	public MenuConfig findByUUID_G(String uuid, long groupId)
		throws NoSuchMenuConfigException;

	/**
	* Returns the menu config where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching menu config, or <code>null</code> if a matching menu config could not be found
	*/
	public MenuConfig fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the menu config where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching menu config, or <code>null</code> if a matching menu config could not be found
	*/
	public MenuConfig fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the menu config where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the menu config that was removed
	*/
	public MenuConfig removeByUUID_G(String uuid, long groupId)
		throws NoSuchMenuConfigException;

	/**
	* Returns the number of menu configs where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching menu configs
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the menu configs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching menu configs
	*/
	public java.util.List<MenuConfig> findByUuid_C(String uuid, long companyId);

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
	public java.util.List<MenuConfig> findByUuid_C(String uuid, long companyId,
		int start, int end);

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
	public java.util.List<MenuConfig> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MenuConfig> orderByComparator);

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
	public java.util.List<MenuConfig> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MenuConfig> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first menu config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching menu config
	* @throws NoSuchMenuConfigException if a matching menu config could not be found
	*/
	public MenuConfig findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<MenuConfig> orderByComparator)
		throws NoSuchMenuConfigException;

	/**
	* Returns the first menu config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching menu config, or <code>null</code> if a matching menu config could not be found
	*/
	public MenuConfig fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<MenuConfig> orderByComparator);

	/**
	* Returns the last menu config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching menu config
	* @throws NoSuchMenuConfigException if a matching menu config could not be found
	*/
	public MenuConfig findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<MenuConfig> orderByComparator)
		throws NoSuchMenuConfigException;

	/**
	* Returns the last menu config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching menu config, or <code>null</code> if a matching menu config could not be found
	*/
	public MenuConfig fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<MenuConfig> orderByComparator);

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
	public MenuConfig[] findByUuid_C_PrevAndNext(long menuConfigId,
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<MenuConfig> orderByComparator)
		throws NoSuchMenuConfigException;

	/**
	* Removes all the menu configs where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of menu configs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching menu configs
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the menu configs where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching menu configs
	*/
	public java.util.List<MenuConfig> findByF_BY_GID(long groupId);

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
	public java.util.List<MenuConfig> findByF_BY_GID(long groupId, int start,
		int end);

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
	public java.util.List<MenuConfig> findByF_BY_GID(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<MenuConfig> orderByComparator);

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
	public java.util.List<MenuConfig> findByF_BY_GID(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<MenuConfig> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first menu config in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching menu config
	* @throws NoSuchMenuConfigException if a matching menu config could not be found
	*/
	public MenuConfig findByF_BY_GID_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<MenuConfig> orderByComparator)
		throws NoSuchMenuConfigException;

	/**
	* Returns the first menu config in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching menu config, or <code>null</code> if a matching menu config could not be found
	*/
	public MenuConfig fetchByF_BY_GID_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<MenuConfig> orderByComparator);

	/**
	* Returns the last menu config in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching menu config
	* @throws NoSuchMenuConfigException if a matching menu config could not be found
	*/
	public MenuConfig findByF_BY_GID_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<MenuConfig> orderByComparator)
		throws NoSuchMenuConfigException;

	/**
	* Returns the last menu config in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching menu config, or <code>null</code> if a matching menu config could not be found
	*/
	public MenuConfig fetchByF_BY_GID_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<MenuConfig> orderByComparator);

	/**
	* Returns the menu configs before and after the current menu config in the ordered set where groupId = &#63;.
	*
	* @param menuConfigId the primary key of the current menu config
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next menu config
	* @throws NoSuchMenuConfigException if a menu config with the primary key could not be found
	*/
	public MenuConfig[] findByF_BY_GID_PrevAndNext(long menuConfigId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<MenuConfig> orderByComparator)
		throws NoSuchMenuConfigException;

	/**
	* Removes all the menu configs where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByF_BY_GID(long groupId);

	/**
	* Returns the number of menu configs where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching menu configs
	*/
	public int countByF_BY_GID(long groupId);

	/**
	* Returns the menu config where menuGroup = &#63; or throws a {@link NoSuchMenuConfigException} if it could not be found.
	*
	* @param menuGroup the menu group
	* @return the matching menu config
	* @throws NoSuchMenuConfigException if a matching menu config could not be found
	*/
	public MenuConfig findByF_BY_menuGroup(String menuGroup)
		throws NoSuchMenuConfigException;

	/**
	* Returns the menu config where menuGroup = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param menuGroup the menu group
	* @return the matching menu config, or <code>null</code> if a matching menu config could not be found
	*/
	public MenuConfig fetchByF_BY_menuGroup(String menuGroup);

	/**
	* Returns the menu config where menuGroup = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param menuGroup the menu group
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching menu config, or <code>null</code> if a matching menu config could not be found
	*/
	public MenuConfig fetchByF_BY_menuGroup(String menuGroup,
		boolean retrieveFromCache);

	/**
	* Removes the menu config where menuGroup = &#63; from the database.
	*
	* @param menuGroup the menu group
	* @return the menu config that was removed
	*/
	public MenuConfig removeByF_BY_menuGroup(String menuGroup)
		throws NoSuchMenuConfigException;

	/**
	* Returns the number of menu configs where menuGroup = &#63;.
	*
	* @param menuGroup the menu group
	* @return the number of matching menu configs
	*/
	public int countByF_BY_menuGroup(String menuGroup);

	/**
	* Returns all the menu configs where menuConfigId = &#63;.
	*
	* @param menuConfigId the menu config ID
	* @return the matching menu configs
	*/
	public java.util.List<MenuConfig> findByF_A_MID(long menuConfigId);

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
	public java.util.List<MenuConfig> findByF_A_MID(long menuConfigId,
		int start, int end);

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
	public java.util.List<MenuConfig> findByF_A_MID(long menuConfigId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MenuConfig> orderByComparator);

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
	public java.util.List<MenuConfig> findByF_A_MID(long menuConfigId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MenuConfig> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first menu config in the ordered set where menuConfigId = &#63;.
	*
	* @param menuConfigId the menu config ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching menu config
	* @throws NoSuchMenuConfigException if a matching menu config could not be found
	*/
	public MenuConfig findByF_A_MID_First(long menuConfigId,
		com.liferay.portal.kernel.util.OrderByComparator<MenuConfig> orderByComparator)
		throws NoSuchMenuConfigException;

	/**
	* Returns the first menu config in the ordered set where menuConfigId = &#63;.
	*
	* @param menuConfigId the menu config ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching menu config, or <code>null</code> if a matching menu config could not be found
	*/
	public MenuConfig fetchByF_A_MID_First(long menuConfigId,
		com.liferay.portal.kernel.util.OrderByComparator<MenuConfig> orderByComparator);

	/**
	* Returns the last menu config in the ordered set where menuConfigId = &#63;.
	*
	* @param menuConfigId the menu config ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching menu config
	* @throws NoSuchMenuConfigException if a matching menu config could not be found
	*/
	public MenuConfig findByF_A_MID_Last(long menuConfigId,
		com.liferay.portal.kernel.util.OrderByComparator<MenuConfig> orderByComparator)
		throws NoSuchMenuConfigException;

	/**
	* Returns the last menu config in the ordered set where menuConfigId = &#63;.
	*
	* @param menuConfigId the menu config ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching menu config, or <code>null</code> if a matching menu config could not be found
	*/
	public MenuConfig fetchByF_A_MID_Last(long menuConfigId,
		com.liferay.portal.kernel.util.OrderByComparator<MenuConfig> orderByComparator);

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
	public java.util.List<MenuConfig> findByF_A_MID(long[] menuConfigIds);

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
	public java.util.List<MenuConfig> findByF_A_MID(long[] menuConfigIds,
		int start, int end);

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
	public java.util.List<MenuConfig> findByF_A_MID(long[] menuConfigIds,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MenuConfig> orderByComparator);

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
	public java.util.List<MenuConfig> findByF_A_MID(long[] menuConfigIds,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MenuConfig> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the menu configs where menuConfigId = &#63; from the database.
	*
	* @param menuConfigId the menu config ID
	*/
	public void removeByF_A_MID(long menuConfigId);

	/**
	* Returns the number of menu configs where menuConfigId = &#63;.
	*
	* @param menuConfigId the menu config ID
	* @return the number of matching menu configs
	*/
	public int countByF_A_MID(long menuConfigId);

	/**
	* Returns the number of menu configs where menuConfigId = any &#63;.
	*
	* @param menuConfigIds the menu config IDs
	* @return the number of matching menu configs
	*/
	public int countByF_A_MID(long[] menuConfigIds);

	/**
	* Caches the menu config in the entity cache if it is enabled.
	*
	* @param menuConfig the menu config
	*/
	public void cacheResult(MenuConfig menuConfig);

	/**
	* Caches the menu configs in the entity cache if it is enabled.
	*
	* @param menuConfigs the menu configs
	*/
	public void cacheResult(java.util.List<MenuConfig> menuConfigs);

	/**
	* Creates a new menu config with the primary key. Does not add the menu config to the database.
	*
	* @param menuConfigId the primary key for the new menu config
	* @return the new menu config
	*/
	public MenuConfig create(long menuConfigId);

	/**
	* Removes the menu config with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param menuConfigId the primary key of the menu config
	* @return the menu config that was removed
	* @throws NoSuchMenuConfigException if a menu config with the primary key could not be found
	*/
	public MenuConfig remove(long menuConfigId)
		throws NoSuchMenuConfigException;

	public MenuConfig updateImpl(MenuConfig menuConfig);

	/**
	* Returns the menu config with the primary key or throws a {@link NoSuchMenuConfigException} if it could not be found.
	*
	* @param menuConfigId the primary key of the menu config
	* @return the menu config
	* @throws NoSuchMenuConfigException if a menu config with the primary key could not be found
	*/
	public MenuConfig findByPrimaryKey(long menuConfigId)
		throws NoSuchMenuConfigException;

	/**
	* Returns the menu config with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param menuConfigId the primary key of the menu config
	* @return the menu config, or <code>null</code> if a menu config with the primary key could not be found
	*/
	public MenuConfig fetchByPrimaryKey(long menuConfigId);

	@Override
	public java.util.Map<java.io.Serializable, MenuConfig> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the menu configs.
	*
	* @return the menu configs
	*/
	public java.util.List<MenuConfig> findAll();

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
	public java.util.List<MenuConfig> findAll(int start, int end);

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
	public java.util.List<MenuConfig> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MenuConfig> orderByComparator);

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
	public java.util.List<MenuConfig> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MenuConfig> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the menu configs from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of menu configs.
	*
	* @return the number of menu configs
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}