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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author huymq
 * @generated
 */
@ProviderType
public class MenuConfigSoap implements Serializable {
	public static MenuConfigSoap toSoapModel(MenuConfig model) {
		MenuConfigSoap soapModel = new MenuConfigSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setMenuConfigId(model.getMenuConfigId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setMenuGroup(model.getMenuGroup());
		soapModel.setMenuName(model.getMenuName());
		soapModel.setOrder(model.getOrder());
		soapModel.setMenuType(model.getMenuType());
		soapModel.setQueryParams(model.getQueryParams());
		soapModel.setTableConfig(model.getTableConfig());
		soapModel.setButtonConfig(model.getButtonConfig());

		return soapModel;
	}

	public static MenuConfigSoap[] toSoapModels(MenuConfig[] models) {
		MenuConfigSoap[] soapModels = new MenuConfigSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static MenuConfigSoap[][] toSoapModels(MenuConfig[][] models) {
		MenuConfigSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new MenuConfigSoap[models.length][models[0].length];
		}
		else {
			soapModels = new MenuConfigSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static MenuConfigSoap[] toSoapModels(List<MenuConfig> models) {
		List<MenuConfigSoap> soapModels = new ArrayList<MenuConfigSoap>(models.size());

		for (MenuConfig model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new MenuConfigSoap[soapModels.size()]);
	}

	public MenuConfigSoap() {
	}

	public long getPrimaryKey() {
		return _menuConfigId;
	}

	public void setPrimaryKey(long pk) {
		setMenuConfigId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getMenuConfigId() {
		return _menuConfigId;
	}

	public void setMenuConfigId(long menuConfigId) {
		_menuConfigId = menuConfigId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getMenuGroup() {
		return _menuGroup;
	}

	public void setMenuGroup(String menuGroup) {
		_menuGroup = menuGroup;
	}

	public String getMenuName() {
		return _menuName;
	}

	public void setMenuName(String menuName) {
		_menuName = menuName;
	}

	public int getOrder() {
		return _order;
	}

	public void setOrder(int order) {
		_order = order;
	}

	public int getMenuType() {
		return _menuType;
	}

	public void setMenuType(int menuType) {
		_menuType = menuType;
	}

	public String getQueryParams() {
		return _queryParams;
	}

	public void setQueryParams(String queryParams) {
		_queryParams = queryParams;
	}

	public String getTableConfig() {
		return _tableConfig;
	}

	public void setTableConfig(String tableConfig) {
		_tableConfig = tableConfig;
	}

	public String getButtonConfig() {
		return _buttonConfig;
	}

	public void setButtonConfig(String buttonConfig) {
		_buttonConfig = buttonConfig;
	}

	private String _uuid;
	private long _menuConfigId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _menuGroup;
	private String _menuName;
	private int _order;
	private int _menuType;
	private String _queryParams;
	private String _tableConfig;
	private String _buttonConfig;
}