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

package org.opencps.communication.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author khoavd
 * @generated
 */
@ProviderType
public class ZaloMapSoap implements Serializable {
	public static ZaloMapSoap toSoapModel(ZaloMap model) {
		ZaloMapSoap soapModel = new ZaloMapSoap();

		soapModel.setZaloMapId(model.getZaloMapId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setUId(model.getUId());
		soapModel.setTelNo(model.getTelNo());
		soapModel.setOAId(model.getOAId());
		soapModel.setIsFollowed(model.getIsFollowed());
		soapModel.setPayload(model.getPayload());

		return soapModel;
	}

	public static ZaloMapSoap[] toSoapModels(ZaloMap[] models) {
		ZaloMapSoap[] soapModels = new ZaloMapSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ZaloMapSoap[][] toSoapModels(ZaloMap[][] models) {
		ZaloMapSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ZaloMapSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ZaloMapSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ZaloMapSoap[] toSoapModels(List<ZaloMap> models) {
		List<ZaloMapSoap> soapModels = new ArrayList<ZaloMapSoap>(models.size());

		for (ZaloMap model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ZaloMapSoap[soapModels.size()]);
	}

	public ZaloMapSoap() {
	}

	public long getPrimaryKey() {
		return _zaloMapId;
	}

	public void setPrimaryKey(long pk) {
		setZaloMapId(pk);
	}

	public long getZaloMapId() {
		return _zaloMapId;
	}

	public void setZaloMapId(long zaloMapId) {
		_zaloMapId = zaloMapId;
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

	public String getUId() {
		return _uId;
	}

	public void setUId(String uId) {
		_uId = uId;
	}

	public String getTelNo() {
		return _telNo;
	}

	public void setTelNo(String telNo) {
		_telNo = telNo;
	}

	public String getOAId() {
		return _oAId;
	}

	public void setOAId(String oAId) {
		_oAId = oAId;
	}

	public int getIsFollowed() {
		return _isFollowed;
	}

	public void setIsFollowed(int isFollowed) {
		_isFollowed = isFollowed;
	}

	public String getPayload() {
		return _payload;
	}

	public void setPayload(String payload) {
		_payload = payload;
	}

	private long _zaloMapId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _uId;
	private String _telNo;
	private String _oAId;
	private int _isFollowed;
	private String _payload;
}