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
public class DictItemSoap implements Serializable {
	public static DictItemSoap toSoapModel(DictItem model) {
		DictItemSoap soapModel = new DictItemSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setDictItemId(model.getDictItemId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setDictCollectionId(model.getDictCollectionId());
		soapModel.setItemCode(model.getItemCode());
		soapModel.setItemName(model.getItemName());
		soapModel.setItemNameEN(model.getItemNameEN());
		soapModel.setItemDescription(model.getItemDescription());
		soapModel.setParentItemId(model.getParentItemId());
		soapModel.setLevel(model.getLevel());
		soapModel.setSibling(model.getSibling());
		soapModel.setTreeIndex(model.getTreeIndex());
		soapModel.setMetaData(model.getMetaData());

		return soapModel;
	}

	public static DictItemSoap[] toSoapModels(DictItem[] models) {
		DictItemSoap[] soapModels = new DictItemSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DictItemSoap[][] toSoapModels(DictItem[][] models) {
		DictItemSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DictItemSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DictItemSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DictItemSoap[] toSoapModels(List<DictItem> models) {
		List<DictItemSoap> soapModels = new ArrayList<DictItemSoap>(models.size());

		for (DictItem model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DictItemSoap[soapModels.size()]);
	}

	public DictItemSoap() {
	}

	public long getPrimaryKey() {
		return _dictItemId;
	}

	public void setPrimaryKey(long pk) {
		setDictItemId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getDictItemId() {
		return _dictItemId;
	}

	public void setDictItemId(long dictItemId) {
		_dictItemId = dictItemId;
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

	public String getItemCode() {
		return _itemCode;
	}

	public void setItemCode(String itemCode) {
		_itemCode = itemCode;
	}

	public String getItemName() {
		return _itemName;
	}

	public void setItemName(String itemName) {
		_itemName = itemName;
	}

	public String getItemNameEN() {
		return _itemNameEN;
	}

	public void setItemNameEN(String itemNameEN) {
		_itemNameEN = itemNameEN;
	}

	public String getItemDescription() {
		return _itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		_itemDescription = itemDescription;
	}

	public long getParentItemId() {
		return _parentItemId;
	}

	public void setParentItemId(long parentItemId) {
		_parentItemId = parentItemId;
	}

	public int getLevel() {
		return _level;
	}

	public void setLevel(int level) {
		_level = level;
	}

	public String getSibling() {
		return _sibling;
	}

	public void setSibling(String sibling) {
		_sibling = sibling;
	}

	public String getTreeIndex() {
		return _treeIndex;
	}

	public void setTreeIndex(String treeIndex) {
		_treeIndex = treeIndex;
	}

	public String getMetaData() {
		return _metaData;
	}

	public void setMetaData(String metaData) {
		_metaData = metaData;
	}

	private String _uuid;
	private long _dictItemId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _dictCollectionId;
	private String _itemCode;
	private String _itemName;
	private String _itemNameEN;
	private String _itemDescription;
	private long _parentItemId;
	private int _level;
	private String _sibling;
	private String _treeIndex;
	private String _metaData;
}