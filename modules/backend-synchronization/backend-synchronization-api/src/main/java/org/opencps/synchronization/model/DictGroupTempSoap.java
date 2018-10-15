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

package org.opencps.synchronization.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author trungdk
 * @generated
 */
@ProviderType
public class DictGroupTempSoap implements Serializable {
	public static DictGroupTempSoap toSoapModel(DictGroupTemp model) {
		DictGroupTempSoap soapModel = new DictGroupTempSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setDictGroupId(model.getDictGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setDictCollectionId(model.getDictCollectionId());
		soapModel.setGroupCode(model.getGroupCode());
		soapModel.setGroupName(model.getGroupName());
		soapModel.setGroupNameEN(model.getGroupNameEN());
		soapModel.setGroupDescription(model.getGroupDescription());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static DictGroupTempSoap[] toSoapModels(DictGroupTemp[] models) {
		DictGroupTempSoap[] soapModels = new DictGroupTempSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DictGroupTempSoap[][] toSoapModels(DictGroupTemp[][] models) {
		DictGroupTempSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DictGroupTempSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DictGroupTempSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DictGroupTempSoap[] toSoapModels(List<DictGroupTemp> models) {
		List<DictGroupTempSoap> soapModels = new ArrayList<DictGroupTempSoap>(models.size());

		for (DictGroupTemp model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DictGroupTempSoap[soapModels.size()]);
	}

	public DictGroupTempSoap() {
	}

	public long getPrimaryKey() {
		return _dictGroupId;
	}

	public void setPrimaryKey(long pk) {
		setDictGroupId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getDictGroupId() {
		return _dictGroupId;
	}

	public void setDictGroupId(long dictGroupId) {
		_dictGroupId = dictGroupId;
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

	public long getDictCollectionId() {
		return _dictCollectionId;
	}

	public void setDictCollectionId(long dictCollectionId) {
		_dictCollectionId = dictCollectionId;
	}

	public String getGroupCode() {
		return _groupCode;
	}

	public void setGroupCode(String groupCode) {
		_groupCode = groupCode;
	}

	public String getGroupName() {
		return _groupName;
	}

	public void setGroupName(String groupName) {
		_groupName = groupName;
	}

	public String getGroupNameEN() {
		return _groupNameEN;
	}

	public void setGroupNameEN(String groupNameEN) {
		_groupNameEN = groupNameEN;
	}

	public String getGroupDescription() {
		return _groupDescription;
	}

	public void setGroupDescription(String groupDescription) {
		_groupDescription = groupDescription;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private String _uuid;
	private long _dictGroupId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _dictCollectionId;
	private String _groupCode;
	private String _groupName;
	private String _groupNameEN;
	private String _groupDescription;
	private int _status;
}