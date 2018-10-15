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

package org.opencps.dossiermgt.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link MenuRole}.
 * </p>
 *
 * @author huymq
 * @see MenuRole
 * @generated
 */
@ProviderType
public class MenuRoleWrapper implements MenuRole, ModelWrapper<MenuRole> {
	public MenuRoleWrapper(MenuRole menuRole) {
		_menuRole = menuRole;
	}

	@Override
	public Class<?> getModelClass() {
		return MenuRole.class;
	}

	@Override
	public String getModelClassName() {
		return MenuRole.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("menuConfigId", getMenuConfigId());
		attributes.put("roleId", getRoleId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long menuConfigId = (Long)attributes.get("menuConfigId");

		if (menuConfigId != null) {
			setMenuConfigId(menuConfigId);
		}

		Long roleId = (Long)attributes.get("roleId");

		if (roleId != null) {
			setRoleId(roleId);
		}
	}

	@Override
	public Object clone() {
		return new MenuRoleWrapper((MenuRole)_menuRole.clone());
	}

	@Override
	public int compareTo(MenuRole menuRole) {
		return _menuRole.compareTo(menuRole);
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _menuRole.getExpandoBridge();
	}

	/**
	* Returns the menu config ID of this menu role.
	*
	* @return the menu config ID of this menu role
	*/
	@Override
	public long getMenuConfigId() {
		return _menuRole.getMenuConfigId();
	}

	/**
	* Returns the primary key of this menu role.
	*
	* @return the primary key of this menu role
	*/
	@Override
	public org.opencps.dossiermgt.service.persistence.MenuRolePK getPrimaryKey() {
		return _menuRole.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _menuRole.getPrimaryKeyObj();
	}

	/**
	* Returns the role ID of this menu role.
	*
	* @return the role ID of this menu role
	*/
	@Override
	public long getRoleId() {
		return _menuRole.getRoleId();
	}

	/**
	* Returns the uuid of this menu role.
	*
	* @return the uuid of this menu role
	*/
	@Override
	public String getUuid() {
		return _menuRole.getUuid();
	}

	@Override
	public int hashCode() {
		return _menuRole.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _menuRole.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _menuRole.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _menuRole.isNew();
	}

	@Override
	public void persist() {
		_menuRole.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_menuRole.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_menuRole.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_menuRole.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_menuRole.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the menu config ID of this menu role.
	*
	* @param menuConfigId the menu config ID of this menu role
	*/
	@Override
	public void setMenuConfigId(long menuConfigId) {
		_menuRole.setMenuConfigId(menuConfigId);
	}

	@Override
	public void setNew(boolean n) {
		_menuRole.setNew(n);
	}

	/**
	* Sets the primary key of this menu role.
	*
	* @param primaryKey the primary key of this menu role
	*/
	@Override
	public void setPrimaryKey(
		org.opencps.dossiermgt.service.persistence.MenuRolePK primaryKey) {
		_menuRole.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_menuRole.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the role ID of this menu role.
	*
	* @param roleId the role ID of this menu role
	*/
	@Override
	public void setRoleId(long roleId) {
		_menuRole.setRoleId(roleId);
	}

	/**
	* Sets the uuid of this menu role.
	*
	* @param uuid the uuid of this menu role
	*/
	@Override
	public void setUuid(String uuid) {
		_menuRole.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<MenuRole> toCacheModel() {
		return _menuRole.toCacheModel();
	}

	@Override
	public MenuRole toEscapedModel() {
		return new MenuRoleWrapper(_menuRole.toEscapedModel());
	}

	@Override
	public String toString() {
		return _menuRole.toString();
	}

	@Override
	public MenuRole toUnescapedModel() {
		return new MenuRoleWrapper(_menuRole.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _menuRole.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MenuRoleWrapper)) {
			return false;
		}

		MenuRoleWrapper menuRoleWrapper = (MenuRoleWrapper)obj;

		if (Objects.equals(_menuRole, menuRoleWrapper._menuRole)) {
			return true;
		}

		return false;
	}

	@Override
	public MenuRole getWrappedModel() {
		return _menuRole;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _menuRole.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _menuRole.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_menuRole.resetOriginalValues();
	}

	private final MenuRole _menuRole;
}