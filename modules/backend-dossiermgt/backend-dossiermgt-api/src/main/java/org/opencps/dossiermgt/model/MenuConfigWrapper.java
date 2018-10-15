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

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link MenuConfig}.
 * </p>
 *
 * @author huymq
 * @see MenuConfig
 * @generated
 */
@ProviderType
public class MenuConfigWrapper implements MenuConfig, ModelWrapper<MenuConfig> {
	public MenuConfigWrapper(MenuConfig menuConfig) {
		_menuConfig = menuConfig;
	}

	@Override
	public Class<?> getModelClass() {
		return MenuConfig.class;
	}

	@Override
	public String getModelClassName() {
		return MenuConfig.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("menuConfigId", getMenuConfigId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("menuGroup", getMenuGroup());
		attributes.put("menuName", getMenuName());
		attributes.put("order", getOrder());
		attributes.put("menuType", getMenuType());
		attributes.put("queryParams", getQueryParams());
		attributes.put("tableConfig", getTableConfig());
		attributes.put("buttonConfig", getButtonConfig());

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

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String menuGroup = (String)attributes.get("menuGroup");

		if (menuGroup != null) {
			setMenuGroup(menuGroup);
		}

		String menuName = (String)attributes.get("menuName");

		if (menuName != null) {
			setMenuName(menuName);
		}

		Integer order = (Integer)attributes.get("order");

		if (order != null) {
			setOrder(order);
		}

		Integer menuType = (Integer)attributes.get("menuType");

		if (menuType != null) {
			setMenuType(menuType);
		}

		String queryParams = (String)attributes.get("queryParams");

		if (queryParams != null) {
			setQueryParams(queryParams);
		}

		String tableConfig = (String)attributes.get("tableConfig");

		if (tableConfig != null) {
			setTableConfig(tableConfig);
		}

		String buttonConfig = (String)attributes.get("buttonConfig");

		if (buttonConfig != null) {
			setButtonConfig(buttonConfig);
		}
	}

	@Override
	public Object clone() {
		return new MenuConfigWrapper((MenuConfig)_menuConfig.clone());
	}

	@Override
	public int compareTo(MenuConfig menuConfig) {
		return _menuConfig.compareTo(menuConfig);
	}

	/**
	* Returns the button config of this menu config.
	*
	* @return the button config of this menu config
	*/
	@Override
	public String getButtonConfig() {
		return _menuConfig.getButtonConfig();
	}

	/**
	* Returns the company ID of this menu config.
	*
	* @return the company ID of this menu config
	*/
	@Override
	public long getCompanyId() {
		return _menuConfig.getCompanyId();
	}

	/**
	* Returns the create date of this menu config.
	*
	* @return the create date of this menu config
	*/
	@Override
	public Date getCreateDate() {
		return _menuConfig.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _menuConfig.getExpandoBridge();
	}

	/**
	* Returns the group ID of this menu config.
	*
	* @return the group ID of this menu config
	*/
	@Override
	public long getGroupId() {
		return _menuConfig.getGroupId();
	}

	/**
	* Returns the menu config ID of this menu config.
	*
	* @return the menu config ID of this menu config
	*/
	@Override
	public long getMenuConfigId() {
		return _menuConfig.getMenuConfigId();
	}

	/**
	* Returns the menu group of this menu config.
	*
	* @return the menu group of this menu config
	*/
	@Override
	public String getMenuGroup() {
		return _menuConfig.getMenuGroup();
	}

	/**
	* Returns the menu name of this menu config.
	*
	* @return the menu name of this menu config
	*/
	@Override
	public String getMenuName() {
		return _menuConfig.getMenuName();
	}

	/**
	* Returns the menu type of this menu config.
	*
	* @return the menu type of this menu config
	*/
	@Override
	public int getMenuType() {
		return _menuConfig.getMenuType();
	}

	/**
	* Returns the modified date of this menu config.
	*
	* @return the modified date of this menu config
	*/
	@Override
	public Date getModifiedDate() {
		return _menuConfig.getModifiedDate();
	}

	/**
	* Returns the order of this menu config.
	*
	* @return the order of this menu config
	*/
	@Override
	public int getOrder() {
		return _menuConfig.getOrder();
	}

	/**
	* Returns the primary key of this menu config.
	*
	* @return the primary key of this menu config
	*/
	@Override
	public long getPrimaryKey() {
		return _menuConfig.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _menuConfig.getPrimaryKeyObj();
	}

	/**
	* Returns the query params of this menu config.
	*
	* @return the query params of this menu config
	*/
	@Override
	public String getQueryParams() {
		return _menuConfig.getQueryParams();
	}

	/**
	* Returns the table config of this menu config.
	*
	* @return the table config of this menu config
	*/
	@Override
	public String getTableConfig() {
		return _menuConfig.getTableConfig();
	}

	/**
	* Returns the user ID of this menu config.
	*
	* @return the user ID of this menu config
	*/
	@Override
	public long getUserId() {
		return _menuConfig.getUserId();
	}

	/**
	* Returns the user uuid of this menu config.
	*
	* @return the user uuid of this menu config
	*/
	@Override
	public String getUserUuid() {
		return _menuConfig.getUserUuid();
	}

	/**
	* Returns the uuid of this menu config.
	*
	* @return the uuid of this menu config
	*/
	@Override
	public String getUuid() {
		return _menuConfig.getUuid();
	}

	@Override
	public int hashCode() {
		return _menuConfig.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _menuConfig.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _menuConfig.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _menuConfig.isNew();
	}

	@Override
	public void persist() {
		_menuConfig.persist();
	}

	/**
	* Sets the button config of this menu config.
	*
	* @param buttonConfig the button config of this menu config
	*/
	@Override
	public void setButtonConfig(String buttonConfig) {
		_menuConfig.setButtonConfig(buttonConfig);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_menuConfig.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this menu config.
	*
	* @param companyId the company ID of this menu config
	*/
	@Override
	public void setCompanyId(long companyId) {
		_menuConfig.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this menu config.
	*
	* @param createDate the create date of this menu config
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_menuConfig.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_menuConfig.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_menuConfig.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_menuConfig.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this menu config.
	*
	* @param groupId the group ID of this menu config
	*/
	@Override
	public void setGroupId(long groupId) {
		_menuConfig.setGroupId(groupId);
	}

	/**
	* Sets the menu config ID of this menu config.
	*
	* @param menuConfigId the menu config ID of this menu config
	*/
	@Override
	public void setMenuConfigId(long menuConfigId) {
		_menuConfig.setMenuConfigId(menuConfigId);
	}

	/**
	* Sets the menu group of this menu config.
	*
	* @param menuGroup the menu group of this menu config
	*/
	@Override
	public void setMenuGroup(String menuGroup) {
		_menuConfig.setMenuGroup(menuGroup);
	}

	/**
	* Sets the menu name of this menu config.
	*
	* @param menuName the menu name of this menu config
	*/
	@Override
	public void setMenuName(String menuName) {
		_menuConfig.setMenuName(menuName);
	}

	/**
	* Sets the menu type of this menu config.
	*
	* @param menuType the menu type of this menu config
	*/
	@Override
	public void setMenuType(int menuType) {
		_menuConfig.setMenuType(menuType);
	}

	/**
	* Sets the modified date of this menu config.
	*
	* @param modifiedDate the modified date of this menu config
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_menuConfig.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_menuConfig.setNew(n);
	}

	/**
	* Sets the order of this menu config.
	*
	* @param order the order of this menu config
	*/
	@Override
	public void setOrder(int order) {
		_menuConfig.setOrder(order);
	}

	/**
	* Sets the primary key of this menu config.
	*
	* @param primaryKey the primary key of this menu config
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_menuConfig.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_menuConfig.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the query params of this menu config.
	*
	* @param queryParams the query params of this menu config
	*/
	@Override
	public void setQueryParams(String queryParams) {
		_menuConfig.setQueryParams(queryParams);
	}

	/**
	* Sets the table config of this menu config.
	*
	* @param tableConfig the table config of this menu config
	*/
	@Override
	public void setTableConfig(String tableConfig) {
		_menuConfig.setTableConfig(tableConfig);
	}

	/**
	* Sets the user ID of this menu config.
	*
	* @param userId the user ID of this menu config
	*/
	@Override
	public void setUserId(long userId) {
		_menuConfig.setUserId(userId);
	}

	/**
	* Sets the user uuid of this menu config.
	*
	* @param userUuid the user uuid of this menu config
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_menuConfig.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this menu config.
	*
	* @param uuid the uuid of this menu config
	*/
	@Override
	public void setUuid(String uuid) {
		_menuConfig.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<MenuConfig> toCacheModel() {
		return _menuConfig.toCacheModel();
	}

	@Override
	public MenuConfig toEscapedModel() {
		return new MenuConfigWrapper(_menuConfig.toEscapedModel());
	}

	@Override
	public String toString() {
		return _menuConfig.toString();
	}

	@Override
	public MenuConfig toUnescapedModel() {
		return new MenuConfigWrapper(_menuConfig.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _menuConfig.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MenuConfigWrapper)) {
			return false;
		}

		MenuConfigWrapper menuConfigWrapper = (MenuConfigWrapper)obj;

		if (Objects.equals(_menuConfig, menuConfigWrapper._menuConfig)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _menuConfig.getStagedModelType();
	}

	@Override
	public MenuConfig getWrappedModel() {
		return _menuConfig;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _menuConfig.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _menuConfig.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_menuConfig.resetOriginalValues();
	}

	private final MenuConfig _menuConfig;
}