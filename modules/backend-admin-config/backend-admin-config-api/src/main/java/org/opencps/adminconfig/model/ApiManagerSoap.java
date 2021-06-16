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
public class ApiManagerSoap implements Serializable {
	public static ApiManagerSoap toSoapModel(ApiManager model) {
		ApiManagerSoap soapModel = new ApiManagerSoap();

		soapModel.setApiManagerId(model.getApiManagerId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setApiCode(model.getApiCode());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setUserId(model.getUserId());
		soapModel.setApiName(model.getApiName());
		soapModel.setApiDescription(model.getApiDescription());
		soapModel.setApiStatus(model.getApiStatus());
		soapModel.setClassName(model.getClassName());

		return soapModel;
	}

	public static ApiManagerSoap[] toSoapModels(ApiManager[] models) {
		ApiManagerSoap[] soapModels = new ApiManagerSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ApiManagerSoap[][] toSoapModels(ApiManager[][] models) {
		ApiManagerSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ApiManagerSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ApiManagerSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ApiManagerSoap[] toSoapModels(List<ApiManager> models) {
		List<ApiManagerSoap> soapModels = new ArrayList<ApiManagerSoap>(models.size());

		for (ApiManager model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ApiManagerSoap[soapModels.size()]);
	}

	public ApiManagerSoap() {
	}

	public long getPrimaryKey() {
		return _apiManagerId;
	}

	public void setPrimaryKey(long pk) {
		setApiManagerId(pk);
	}

	public long getApiManagerId() {
		return _apiManagerId;
	}

	public void setApiManagerId(long apiManagerId) {
		_apiManagerId = apiManagerId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public String getApiCode() {
		return _apiCode;
	}

	public void setApiCode(String apiCode) {
		_apiCode = apiCode;
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

	public String getApiName() {
		return _apiName;
	}

	public void setApiName(String apiName) {
		_apiName = apiName;
	}

	public String getApiDescription() {
		return _apiDescription;
	}

	public void setApiDescription(String apiDescription) {
		_apiDescription = apiDescription;
	}

	public int getApiStatus() {
		return _apiStatus;
	}

	public void setApiStatus(int apiStatus) {
		_apiStatus = apiStatus;
	}

	public String getClassName() {
		return _className;
	}

	public void setClassName(String className) {
		_className = className;
	}

	private long _apiManagerId;
	private long _groupId;
	private String _apiCode;
	private Date _createDate;
	private Date _modifiedDate;
	private long _userId;
	private String _apiName;
	private String _apiDescription;
	private int _apiStatus;
	private String _className;
}