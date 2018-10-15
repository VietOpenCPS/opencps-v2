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

import org.opencps.dossiermgt.service.persistence.MenuRolePK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author huymq
 * @generated
 */
@ProviderType
public class MenuRoleSoap implements Serializable {
	public static MenuRoleSoap toSoapModel(MenuRole model) {
		MenuRoleSoap soapModel = new MenuRoleSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setMenuConfigId(model.getMenuConfigId());
		soapModel.setRoleId(model.getRoleId());

		return soapModel;
	}

	public static MenuRoleSoap[] toSoapModels(MenuRole[] models) {
		MenuRoleSoap[] soapModels = new MenuRoleSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static MenuRoleSoap[][] toSoapModels(MenuRole[][] models) {
		MenuRoleSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new MenuRoleSoap[models.length][models[0].length];
		}
		else {
			soapModels = new MenuRoleSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static MenuRoleSoap[] toSoapModels(List<MenuRole> models) {
		List<MenuRoleSoap> soapModels = new ArrayList<MenuRoleSoap>(models.size());

		for (MenuRole model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new MenuRoleSoap[soapModels.size()]);
	}

	public MenuRoleSoap() {
	}

	public MenuRolePK getPrimaryKey() {
		return new MenuRolePK(_menuConfigId, _roleId);
	}

	public void setPrimaryKey(MenuRolePK pk) {
		setMenuConfigId(pk.menuConfigId);
		setRoleId(pk.roleId);
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

	public long getRoleId() {
		return _roleId;
	}

	public void setRoleId(long roleId) {
		_roleId = roleId;
	}

	private String _uuid;
	private long _menuConfigId;
	private long _roleId;
}