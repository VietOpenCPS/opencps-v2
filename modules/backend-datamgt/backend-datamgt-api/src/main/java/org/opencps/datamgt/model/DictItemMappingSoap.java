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
public class DictItemMappingSoap implements Serializable {
	public static DictItemMappingSoap toSoapModel(DictItemMapping model) {
		DictItemMappingSoap soapModel = new DictItemMappingSoap();

		soapModel.setMappingId(model.getMappingId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setItemCode(model.getItemCode());
		soapModel.setItemCodeDVCQG(model.getItemCodeDVCQG());
		soapModel.setCollectionId(model.getCollectionId());

		return soapModel;
	}

	public static DictItemMappingSoap[] toSoapModels(DictItemMapping[] models) {
		DictItemMappingSoap[] soapModels = new DictItemMappingSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DictItemMappingSoap[][] toSoapModels(
		DictItemMapping[][] models) {
		DictItemMappingSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DictItemMappingSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DictItemMappingSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DictItemMappingSoap[] toSoapModels(
		List<DictItemMapping> models) {
		List<DictItemMappingSoap> soapModels = new ArrayList<DictItemMappingSoap>(models.size());

		for (DictItemMapping model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DictItemMappingSoap[soapModels.size()]);
	}

	public DictItemMappingSoap() {
	}

	public long getPrimaryKey() {
		return _mappingId;
	}

	public void setPrimaryKey(long pk) {
		setMappingId(pk);
	}

	public long getMappingId() {
		return _mappingId;
	}

	public void setMappingId(long mappingId) {
		_mappingId = mappingId;
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

	public String getItemCode() {
		return _itemCode;
	}

	public void setItemCode(String itemCode) {
		_itemCode = itemCode;
	}

	public String getItemCodeDVCQG() {
		return _itemCodeDVCQG;
	}

	public void setItemCodeDVCQG(String itemCodeDVCQG) {
		_itemCodeDVCQG = itemCodeDVCQG;
	}

	public long getCollectionId() {
		return _collectionId;
	}

	public void setCollectionId(long collectionId) {
		_collectionId = collectionId;
	}

	private long _mappingId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _itemCode;
	private String _itemCodeDVCQG;
	private long _collectionId;
}