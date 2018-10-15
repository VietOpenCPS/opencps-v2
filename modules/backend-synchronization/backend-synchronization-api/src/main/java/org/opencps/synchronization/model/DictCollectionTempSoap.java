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
public class DictCollectionTempSoap implements Serializable {
	public static DictCollectionTempSoap toSoapModel(DictCollectionTemp model) {
		DictCollectionTempSoap soapModel = new DictCollectionTempSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setDictCollectionId(model.getDictCollectionId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCollectionCode(model.getCollectionCode());
		soapModel.setCollectionName(model.getCollectionName());
		soapModel.setCollectionNameEN(model.getCollectionNameEN());
		soapModel.setDescription(model.getDescription());
		soapModel.setDataForm(model.getDataForm());
		soapModel.setStatus(model.getStatus());
		soapModel.setMustSync(model.getMustSync());

		return soapModel;
	}

	public static DictCollectionTempSoap[] toSoapModels(
		DictCollectionTemp[] models) {
		DictCollectionTempSoap[] soapModels = new DictCollectionTempSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DictCollectionTempSoap[][] toSoapModels(
		DictCollectionTemp[][] models) {
		DictCollectionTempSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DictCollectionTempSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DictCollectionTempSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DictCollectionTempSoap[] toSoapModels(
		List<DictCollectionTemp> models) {
		List<DictCollectionTempSoap> soapModels = new ArrayList<DictCollectionTempSoap>(models.size());

		for (DictCollectionTemp model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DictCollectionTempSoap[soapModels.size()]);
	}

	public DictCollectionTempSoap() {
	}

	public long getPrimaryKey() {
		return _dictCollectionId;
	}

	public void setPrimaryKey(long pk) {
		setDictCollectionId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getDictCollectionId() {
		return _dictCollectionId;
	}

	public void setDictCollectionId(long dictCollectionId) {
		_dictCollectionId = dictCollectionId;
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

	public String getCollectionCode() {
		return _collectionCode;
	}

	public void setCollectionCode(String collectionCode) {
		_collectionCode = collectionCode;
	}

	public String getCollectionName() {
		return _collectionName;
	}

	public void setCollectionName(String collectionName) {
		_collectionName = collectionName;
	}

	public String getCollectionNameEN() {
		return _collectionNameEN;
	}

	public void setCollectionNameEN(String collectionNameEN) {
		_collectionNameEN = collectionNameEN;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getDataForm() {
		return _dataForm;
	}

	public void setDataForm(String dataForm) {
		_dataForm = dataForm;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public int getMustSync() {
		return _mustSync;
	}

	public void setMustSync(int mustSync) {
		_mustSync = mustSync;
	}

	private String _uuid;
	private long _dictCollectionId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _collectionCode;
	private String _collectionName;
	private String _collectionNameEN;
	private String _description;
	private String _dataForm;
	private int _status;
	private int _mustSync;
}