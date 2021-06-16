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

package org.opencps.adminconfig.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author binhth
 * @generated
 */
@ProviderType
public class ApiRoleSoap implements Serializable {
	public static ApiRoleSoap toSoapModel(ApiRole model) {
		ApiRoleSoap soapModel = new ApiRoleSoap();

		soapModel.setApiRoleId(model.getApiRoleId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setUserId(model.getUserId());
		soapModel.setApiCode(model.getApiCode());
		soapModel.setRoleId(model.getRoleId());
		soapModel.setRoleCode(model.getRoleCode());
		soapModel.setApiRoleStatus(model.getApiRoleStatus());

		return soapModel;
	}

	public static ApiRoleSoap[] toSoapModels(ApiRole[] models) {
		ApiRoleSoap[] soapModels = new ApiRoleSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ApiRoleSoap[][] toSoapModels(ApiRole[][] models) {
		ApiRoleSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ApiRoleSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ApiRoleSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ApiRoleSoap[] toSoapModels(List<ApiRole> models) {
		List<ApiRoleSoap> soapModels = new ArrayList<ApiRoleSoap>(models.size());

		for (ApiRole model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ApiRoleSoap[soapModels.size()]);
	}

	public ApiRoleSoap() {
	}

	public long getPrimaryKey() {
		return _apiRoleId;
	}

	public void setPrimaryKey(long pk) {
		setApiRoleId(pk);
	}

	public long getApiRoleId() {
		return _apiRoleId;
	}

	public void setApiRoleId(long apiRoleId) {
		_apiRoleId = apiRoleId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
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

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getApiCode() {
		return _apiCode;
	}

	public void setApiCode(String apiCode) {
		_apiCode = apiCode;
	}

	public int getRoleId() {
		return _roleId;
	}

	public void setRoleId(int roleId) {
		_roleId = roleId;
	}

	public String getRoleCode() {
		return _roleCode;
	}

	public void setRoleCode(String roleCode) {
		_roleCode = roleCode;
	}

	public int getApiRoleStatus() {
		return _apiRoleStatus;
	}

	public void setApiRoleStatus(int apiRoleStatus) {
		_apiRoleStatus = apiRoleStatus;
	}

	private long _apiRoleId;
	private long _groupId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _userId;
	private String _apiCode;
	private int _roleId;
	private String _roleCode;
	private int _apiRoleStatus;
}