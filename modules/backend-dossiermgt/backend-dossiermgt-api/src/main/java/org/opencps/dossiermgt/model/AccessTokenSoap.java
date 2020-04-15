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
public class AccessTokenSoap implements Serializable {
	public static AccessTokenSoap toSoapModel(AccessToken model) {
		AccessTokenSoap soapModel = new AccessTokenSoap();

		soapModel.setAccessTokenId(model.getAccessTokenId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setToken(model.getToken());
		soapModel.setExpireDate(model.getExpireDate());
		soapModel.setClassName(model.getClassName());

		return soapModel;
	}

	public static AccessTokenSoap[] toSoapModels(AccessToken[] models) {
		AccessTokenSoap[] soapModels = new AccessTokenSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AccessTokenSoap[][] toSoapModels(AccessToken[][] models) {
		AccessTokenSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AccessTokenSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AccessTokenSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AccessTokenSoap[] toSoapModels(List<AccessToken> models) {
		List<AccessTokenSoap> soapModels = new ArrayList<AccessTokenSoap>(models.size());

		for (AccessToken model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AccessTokenSoap[soapModels.size()]);
	}

	public AccessTokenSoap() {
	}

	public long getPrimaryKey() {
		return _accessTokenId;
	}

	public void setPrimaryKey(long pk) {
		setAccessTokenId(pk);
	}

	public long getAccessTokenId() {
		return _accessTokenId;
	}

	public void setAccessTokenId(long accessTokenId) {
		_accessTokenId = accessTokenId;
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

	public String getToken() {
		return _token;
	}

	public void setToken(String token) {
		_token = token;
	}

	public Date getExpireDate() {
		return _expireDate;
	}

	public void setExpireDate(Date expireDate) {
		_expireDate = expireDate;
	}

	public String getClassName() {
		return _className;
	}

	public void setClassName(String className) {
		_className = className;
	}

	private long _accessTokenId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _token;
	private Date _expireDate;
	private String _className;
}