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

package org.opencps.datamgt.model;

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
public class DictItemGroupSoap implements Serializable {
	public static DictItemGroupSoap toSoapModel(DictItemGroup model) {
		DictItemGroupSoap soapModel = new DictItemGroupSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setDictItemGroupId(model.getDictItemGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setDictGroupId(model.getDictGroupId());
		soapModel.setDictItemId(model.getDictItemId());
		soapModel.setDictGroupName(model.getDictGroupName());

		return soapModel;
	}

	public static DictItemGroupSoap[] toSoapModels(DictItemGroup[] models) {
		DictItemGroupSoap[] soapModels = new DictItemGroupSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DictItemGroupSoap[][] toSoapModels(DictItemGroup[][] models) {
		DictItemGroupSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DictItemGroupSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DictItemGroupSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DictItemGroupSoap[] toSoapModels(List<DictItemGroup> models) {
		List<DictItemGroupSoap> soapModels = new ArrayList<DictItemGroupSoap>(models.size());

		for (DictItemGroup model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DictItemGroupSoap[soapModels.size()]);
	}

	public DictItemGroupSoap() {
	}

	public long getPrimaryKey() {
		return _dictItemGroupId;
	}

	public void setPrimaryKey(long pk) {
		setDictItemGroupId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getDictItemGroupId() {
		return _dictItemGroupId;
	}

	public void setDictItemGroupId(long dictItemGroupId) {
		_dictItemGroupId = dictItemGroupId;
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

	public long getDictGroupId() {
		return _dictGroupId;
	}

	public void setDictGroupId(long dictGroupId) {
		_dictGroupId = dictGroupId;
	}

	public long getDictItemId() {
		return _dictItemId;
	}

	public void setDictItemId(long dictItemId) {
		_dictItemId = dictItemId;
	}

	public String getDictGroupName() {
		return _dictGroupName;
	}

	public void setDictGroupName(String dictGroupName) {
		_dictGroupName = dictGroupName;
	}

	private String _uuid;
	private long _dictItemGroupId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _dictGroupId;
	private long _dictItemId;
	private String _dictGroupName;
}