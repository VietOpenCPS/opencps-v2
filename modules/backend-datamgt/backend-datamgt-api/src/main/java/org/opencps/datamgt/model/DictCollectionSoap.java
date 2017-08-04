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
 * @author Binhth
 * @generated
 */
@ProviderType
public class DictCollectionSoap implements Serializable {
	public static DictCollectionSoap toSoapModel(DictCollection model) {
		DictCollectionSoap soapModel = new DictCollectionSoap();

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

		return soapModel;
	}

	public static DictCollectionSoap[] toSoapModels(DictCollection[] models) {
		DictCollectionSoap[] soapModels = new DictCollectionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DictCollectionSoap[][] toSoapModels(DictCollection[][] models) {
		DictCollectionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DictCollectionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DictCollectionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DictCollectionSoap[] toSoapModels(List<DictCollection> models) {
		List<DictCollectionSoap> soapModels = new ArrayList<DictCollectionSoap>(models.size());

		for (DictCollection model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DictCollectionSoap[soapModels.size()]);
	}

	public DictCollectionSoap() {
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
}