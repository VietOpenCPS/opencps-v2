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
public class UserInfoLogSoap implements Serializable {
	public static UserInfoLogSoap toSoapModel(UserInfoLog model) {
		UserInfoLogSoap soapModel = new UserInfoLogSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setUserLogId(model.getUserLogId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setPayload(model.getPayload());

		return soapModel;
	}

	public static UserInfoLogSoap[] toSoapModels(UserInfoLog[] models) {
		UserInfoLogSoap[] soapModels = new UserInfoLogSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static UserInfoLogSoap[][] toSoapModels(UserInfoLog[][] models) {
		UserInfoLogSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new UserInfoLogSoap[models.length][models[0].length];
		}
		else {
			soapModels = new UserInfoLogSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static UserInfoLogSoap[] toSoapModels(List<UserInfoLog> models) {
		List<UserInfoLogSoap> soapModels = new ArrayList<UserInfoLogSoap>(models.size());

		for (UserInfoLog model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new UserInfoLogSoap[soapModels.size()]);
	}

	public UserInfoLogSoap() {
	}

	public long getPrimaryKey() {
		return _userLogId;
	}

	public void setPrimaryKey(long pk) {
		setUserLogId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getUserLogId() {
		return _userLogId;
	}

	public void setUserLogId(long userLogId) {
		_userLogId = userLogId;
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

	public String getPayload() {
		return _payload;
	}

	public void setPayload(String payload) {
		_payload = payload;
	}

	private String _uuid;
	private long _userLogId;
	private long _userId;
	private Date _createDate;
	private String _payload;
}