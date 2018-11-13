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

import org.opencps.dossiermgt.exception.NoSuchMenuRoleException;
import org.opencps.dossiermgt.model.MenuRole;

/**
 * The persistence interface for the menu role service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.dossiermgt.service.persistence.impl.MenuRolePersistenceImpl
 * @see MenuRoleUtil
 * @generated
 */
@ProviderType
public interface MenuRolePersistence extends BasePersistence<MenuRole> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MenuRoleUtil} to access the menu role persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the menu roles where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching menu roles
	*/
	public java.util.List<MenuRole> findByUuid(String uuid);

	/**
	* Returns a range of all the menu roles where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of menu roles
	* @param end the upper bound of the range of menu roles (not inclusive)
	* @return the range of matching menu roles
	*/
	public java.util.List<MenuRole> findByUuid(String uuid, int start, int end);

	/**
	* Returns an ordered range of all the menu roles where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of menu roles
	* @param end the upper bound of the range of menu roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching menu roles
	*/
	public java.util.List<MenuRole> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MenuRole> orderByComparator);

	/**
	* Returns an ordered range of all the menu roles where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of menu roles
	* @param end the upper bound of the range of menu roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching menu roles
	*/
	public java.util.List<MenuRole> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MenuRole> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first menu role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching menu role
	* @throws NoSuchMenuRoleException if a matching menu role could not be found
	*/
	public MenuRole findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<MenuRole> orderByComparator)
		throws NoSuchMenuRoleException;

	/**
	* Returns the first menu role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching menu role, or <code>null</code> if a matching menu role could not be found
	*/
	public MenuRole fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<MenuRole> orderByComparator);

	/**
	* Returns the last menu role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching menu role
	* @throws NoSuchMenuRoleException if a matching menu role could not be found
	*/
	public MenuRole findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<MenuRole> orderByComparator)
		throws NoSuchMenuRoleException;

	/**
	* Returns the last menu role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching menu role, or <code>null</code> if a matching menu role could not be found
	*/
	public MenuRole fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<MenuRole> orderByComparator);

	/**
	* Returns the menu roles before and after the current menu role in the ordered set where uuid = &#63;.
	*
	* @param menuRoleId the primary key of the current menu role
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next menu role
	* @throws NoSuchMenuRoleException if a menu role with the primary key could not be found
	*/
	public MenuRole[] findByUuid_PrevAndNext(long menuRoleId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<MenuRole> orderByComparator)
		throws NoSuchMenuRoleException;

	/**
	* Removes all the menu roles where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of menu roles where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching menu roles
	*/
	public int countByUuid(String uuid);

	/**
	* Returns all the menu roles where roleId = &#63;.
	*
	* @param roleId the role ID
	* @return the matching menu roles
	*/
	public java.util.List<MenuRole> findByF_RID(long roleId);

	/**
	* Returns a range of all the menu roles where roleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param roleId the role ID
	* @param start the lower bound of the range of menu roles
	* @param end the upper bound of the range of menu roles (not inclusive)
	* @return the range of matching menu roles
	*/
	public java.util.List<MenuRole> findByF_RID(long roleId, int start, int end);

	/**
	* Returns an ordered range of all the menu roles where roleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param roleId the role ID
	* @param start the lower bound of the range of menu roles
	* @param end the upper bound of the range of menu roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching menu roles
	*/
	public java.util.List<MenuRole> findByF_RID(long roleId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<MenuRole> orderByComparator);

	/**
	* Returns an ordered range of all the menu roles where roleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param roleId the role ID
	* @param start the lower bound of the range of menu roles
	* @param end the upper bound of the range of menu roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching menu roles
	*/
	public java.util.List<MenuRole> findByF_RID(long roleId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<MenuRole> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first menu role in the ordered set where roleId = &#63;.
	*
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching menu role
	* @throws NoSuchMenuRoleException if a matching menu role could not be found
	*/
	public MenuRole findByF_RID_First(long roleId,
		com.liferay.portal.kernel.util.OrderByComparator<MenuRole> orderByComparator)
		throws NoSuchMenuRoleException;

	/**
	* Returns the first menu role in the ordered set where roleId = &#63;.
	*
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching menu role, or <code>null</code> if a matching menu role could not be found
	*/
	public MenuRole fetchByF_RID_First(long roleId,
		com.liferay.portal.kernel.util.OrderByComparator<MenuRole> orderByComparator);

	/**
	* Returns the last menu role in the ordered set where roleId = &#63;.
	*
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching menu role
	* @throws NoSuchMenuRoleException if a matching menu role could not be found
	*/
	public MenuRole findByF_RID_Last(long roleId,
		com.liferay.portal.kernel.util.OrderByComparator<MenuRole> orderByComparator)
		throws NoSuchMenuRoleException;

	/**
	* Returns the last menu role in the ordered set where roleId = &#63;.
	*
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching menu role, or <code>null</code> if a matching menu role could not be found
	*/
	public MenuRole fetchByF_RID_Last(long roleId,
		com.liferay.portal.kernel.util.OrderByComparator<MenuRole> orderByComparator);

	/**
	* Returns the menu roles before and after the current menu role in the ordered set where roleId = &#63;.
	*
	* @param menuRoleId the primary key of the current menu role
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next menu role
	* @throws NoSuchMenuRoleException if a menu role with the primary key could not be found
	*/
	public MenuRole[] findByF_RID_PrevAndNext(long menuRoleId, long roleId,
		com.liferay.portal.kernel.util.OrderByComparator<MenuRole> orderByComparator)
		throws NoSuchMenuRoleException;

	/**
	* Returns all the menu roles where roleId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param roleIds the role IDs
	* @return the matching menu roles
	*/
	public java.util.List<MenuRole> findByF_RID(long[] roleIds);

	/**
	* Returns a range of all the menu roles where roleId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param roleIds the role IDs
	* @param start the lower bound of the range of menu roles
	* @param end the upper bound of the range of menu roles (not inclusive)
	* @return the range of matching menu roles
	*/
	public java.util.List<MenuRole> findByF_RID(long[] roleIds, int start,
		int end);

	/**
	* Returns an ordered range of all the menu roles where roleId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param roleIds the role IDs
	* @param start the lower bound of the range of menu roles
	* @param end the upper bound of the range of menu roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching menu roles
	*/
	public java.util.List<MenuRole> findByF_RID(long[] roleIds, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<MenuRole> orderByComparator);

	/**
	* Returns an ordered range of all the menu roles where roleId = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param roleId the role ID
	* @param start the lower bound of the range of menu roles
	* @param end the upper bound of the range of menu roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching menu roles
	*/
	public java.util.List<MenuRole> findByF_RID(long[] roleIds, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<MenuRole> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the menu roles where roleId = &#63; from the database.
	*
	* @param roleId the role ID
	*/
	public void removeByF_RID(long roleId);

	/**
	* Returns the number of menu roles where roleId = &#63;.
	*
	* @param roleId the role ID
	* @return the number of matching menu roles
	*/
	public int countByF_RID(long roleId);

	/**
	* Returns the number of menu roles where roleId = any &#63;.
	*
	* @param roleIds the role IDs
	* @return the number of matching menu roles
	*/
	public int countByF_RID(long[] roleIds);

	/**
	* Returns the menu role where menuConfigId = &#63; and roleId = &#63; or throws a {@link NoSuchMenuRoleException} if it could not be found.
	*
	* @param menuConfigId the menu config ID
	* @param roleId the role ID
	* @return the matching menu role
	* @throws NoSuchMenuRoleException if a matching menu role could not be found
	*/
	public MenuRole findByF_MENU_ROLE(long menuConfigId, long roleId)
		throws NoSuchMenuRoleException;

	/**
	* Returns the menu role where menuConfigId = &#63; and roleId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param menuConfigId the menu config ID
	* @param roleId the role ID
	* @return the matching menu role, or <code>null</code> if a matching menu role could not be found
	*/
	public MenuRole fetchByF_MENU_ROLE(long menuConfigId, long roleId);

	/**
	* Returns the menu role where menuConfigId = &#63; and roleId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param menuConfigId the menu config ID
	* @param roleId the role ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching menu role, or <code>null</code> if a matching menu role could not be found
	*/
	public MenuRole fetchByF_MENU_ROLE(long menuConfigId, long roleId,
		boolean retrieveFromCache);

	/**
	* Removes the menu role where menuConfigId = &#63; and roleId = &#63; from the database.
	*
	* @param menuConfigId the menu config ID
	* @param roleId the role ID
	* @return the menu role that was removed
	*/
	public MenuRole removeByF_MENU_ROLE(long menuConfigId, long roleId)
		throws NoSuchMenuRoleException;

	/**
	* Returns the number of menu roles where menuConfigId = &#63; and roleId = &#63;.
	*
	* @param menuConfigId the menu config ID
	* @param roleId the role ID
	* @return the number of matching menu roles
	*/
	public int countByF_MENU_ROLE(long menuConfigId, long roleId);

	/**
	* Caches the menu role in the entity cache if it is enabled.
	*
	* @param menuRole the menu role
	*/
	public void cacheResult(MenuRole menuRole);

	/**
	* Caches the menu roles in the entity cache if it is enabled.
	*
	* @param menuRoles the menu roles
	*/
	public void cacheResult(java.util.List<MenuRole> menuRoles);

	/**
	* Creates a new menu role with the primary key. Does not add the menu role to the database.
	*
	* @param menuRoleId the primary key for the new menu role
	* @return the new menu role
	*/
	public MenuRole create(long menuRoleId);

	/**
	* Removes the menu role with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param menuRoleId the primary key of the menu role
	* @return the menu role that was removed
	* @throws NoSuchMenuRoleException if a menu role with the primary key could not be found
	*/
	public MenuRole remove(long menuRoleId) throws NoSuchMenuRoleException;

	public MenuRole updateImpl(MenuRole menuRole);

	/**
	* Returns the menu role with the primary key or throws a {@link NoSuchMenuRoleException} if it could not be found.
	*
	* @param menuRoleId the primary key of the menu role
	* @return the menu role
	* @throws NoSuchMenuRoleException if a menu role with the primary key could not be found
	*/
	public MenuRole findByPrimaryKey(long menuRoleId)
		throws NoSuchMenuRoleException;

	/**
	* Returns the menu role with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param menuRoleId the primary key of the menu role
	* @return the menu role, or <code>null</code> if a menu role with the primary key could not be found
	*/
	public MenuRole fetchByPrimaryKey(long menuRoleId);

	@Override
	public java.util.Map<java.io.Serializable, MenuRole> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the menu roles.
	*
	* @return the menu roles
	*/
	public java.util.List<MenuRole> findAll();

	/**
	* Returns a range of all the menu roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of menu roles
	* @param end the upper bound of the range of menu roles (not inclusive)
	* @return the range of menu roles
	*/
	public java.util.List<MenuRole> findAll(int start, int end);

	/**
	* Returns an ordered range of all the menu roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of menu roles
	* @param end the upper bound of the range of menu roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of menu roles
	*/
	public java.util.List<MenuRole> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MenuRole> orderByComparator);

	/**
	* Returns an ordered range of all the menu roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of menu roles
	* @param end the upper bound of the range of menu roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of menu roles
	*/
	public java.util.List<MenuRole> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MenuRole> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the menu roles from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of menu roles.
	*
	* @return the number of menu roles
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}