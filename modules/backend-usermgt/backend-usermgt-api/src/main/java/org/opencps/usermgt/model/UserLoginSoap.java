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

package org.opencps.usermgt.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author khoavu
 * @generated
 */
@ProviderType
public class UserLoginSoap implements Serializable {
	public static UserLoginSoap toSoapModel(UserLogin model) {
		UserLoginSoap soapModel = new UserLoginSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setUserLoginId(model.getUserLoginId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setSessionId(model.getSessionId());
		soapModel.setHits(model.getHits());
		soapModel.setLogout(model.getLogout());
		soapModel.setIpAddress(model.getIpAddress());

		return soapModel;
	}

	public static UserLoginSoap[] toSoapModels(UserLogin[] models) {
		UserLoginSoap[] soapModels = new UserLoginSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static UserLoginSoap[][] toSoapModels(UserLogin[][] models) {
		UserLoginSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new UserLoginSoap[models.length][models[0].length];
		}
		else {
			soapModels = new UserLoginSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static UserLoginSoap[] toSoapModels(List<UserLogin> models) {
		List<UserLoginSoap> soapModels = new ArrayList<UserLoginSoap>(models.size());

		for (UserLogin model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new UserLoginSoap[soapModels.size()]);
	}

	public UserLoginSoap() {
	}

	public long getPrimaryKey() {
		return _userLoginId;
	}

	public void setPrimaryKey(long pk) {
		setUserLoginId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getUserLoginId() {
		return _userLoginId;
	}

	public void setUserLoginId(long userLoginId) {
		_userLoginId = userLoginId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
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

	public String getSessionId() {
		return _sessionId;
	}

	public void setSessionId(String sessionId) {
		_sessionId = sessionId;
	}

	public int getHits() {
		return _hits;
	}

	public void setHits(int hits) {
		_hits = hits;
	}

	public Date getLogout() {
		return _logout;
	}

	public void setLogout(Date logout) {
		_logout = logout;
	}

	public String getIpAddress() {
		return _ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		_ipAddress = ipAddress;
	}

	private String _uuid;
	private long _userLoginId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _sessionId;
	private int _hits;
	private Date _logout;
	private String _ipAddress;
}