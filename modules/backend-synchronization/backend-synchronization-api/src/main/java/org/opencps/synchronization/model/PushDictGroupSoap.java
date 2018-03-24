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
public class PushDictGroupSoap implements Serializable {
	public static PushDictGroupSoap toSoapModel(PushDictGroup model) {
		PushDictGroupSoap soapModel = new PushDictGroupSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setPushDictGroupId(model.getPushDictGroupId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setServerNo(model.getServerNo());
		soapModel.setCollectionCode(model.getCollectionCode());
		soapModel.setGroupCode(model.getGroupCode());
		soapModel.setGroupName(model.getGroupName());
		soapModel.setGroupNameEN(model.getGroupNameEN());
		soapModel.setGroupDescription(model.getGroupDescription());
		soapModel.setItemCode(model.getItemCode());
		soapModel.setMethod(model.getMethod());

		return soapModel;
	}

	public static PushDictGroupSoap[] toSoapModels(PushDictGroup[] models) {
		PushDictGroupSoap[] soapModels = new PushDictGroupSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PushDictGroupSoap[][] toSoapModels(PushDictGroup[][] models) {
		PushDictGroupSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new PushDictGroupSoap[models.length][models[0].length];
		}
		else {
			soapModels = new PushDictGroupSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PushDictGroupSoap[] toSoapModels(List<PushDictGroup> models) {
		List<PushDictGroupSoap> soapModels = new ArrayList<PushDictGroupSoap>(models.size());

		for (PushDictGroup model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PushDictGroupSoap[soapModels.size()]);
	}

	public PushDictGroupSoap() {
	}

	public long getPrimaryKey() {
		return _pushDictGroupId;
	}

	public void setPrimaryKey(long pk) {
		setPushDictGroupId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getPushDictGroupId() {
		return _pushDictGroupId;
	}

	public void setPushDictGroupId(long pushDictGroupId) {
		_pushDictGroupId = pushDictGroupId;
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

	public String getServerNo() {
		return _serverNo;
	}

	public void setServerNo(String serverNo) {
		_serverNo = serverNo;
	}

	public String getCollectionCode() {
		return _collectionCode;
	}

	public void setCollectionCode(String collectionCode) {
		_collectionCode = collectionCode;
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

	public String getItemCode() {
		return _itemCode;
	}

	public void setItemCode(String itemCode) {
		_itemCode = itemCode;
	}

	public String getMethod() {
		return _method;
	}

	public void setMethod(String method) {
		_method = method;
	}

	private String _uuid;
	private long _pushDictGroupId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _serverNo;
	private String _collectionCode;
	private String _groupCode;
	private String _groupName;
	private String _groupNameEN;
	private String _groupDescription;
	private String _itemCode;
	private String _method;
}