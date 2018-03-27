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
public class PushDictItemSoap implements Serializable {
	public static PushDictItemSoap toSoapModel(PushDictItem model) {
		PushDictItemSoap soapModel = new PushDictItemSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setPushDictItemId(model.getPushDictItemId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setServerNo(model.getServerNo());
		soapModel.setCollectionCode(model.getCollectionCode());
		soapModel.setItemCode(model.getItemCode());
		soapModel.setItemName(model.getItemName());
		soapModel.setItemNameEN(model.getItemNameEN());
		soapModel.setItemDescription(model.getItemDescription());
		soapModel.setParentItemCode(model.getParentItemCode());
		soapModel.setSibling(model.getSibling());
		soapModel.setMethod(model.getMethod());
		soapModel.setMetaData(model.getMetaData());

		return soapModel;
	}

	public static PushDictItemSoap[] toSoapModels(PushDictItem[] models) {
		PushDictItemSoap[] soapModels = new PushDictItemSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PushDictItemSoap[][] toSoapModels(PushDictItem[][] models) {
		PushDictItemSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new PushDictItemSoap[models.length][models[0].length];
		}
		else {
			soapModels = new PushDictItemSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PushDictItemSoap[] toSoapModels(List<PushDictItem> models) {
		List<PushDictItemSoap> soapModels = new ArrayList<PushDictItemSoap>(models.size());

		for (PushDictItem model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PushDictItemSoap[soapModels.size()]);
	}

	public PushDictItemSoap() {
	}

	public long getPrimaryKey() {
		return _pushDictItemId;
	}

	public void setPrimaryKey(long pk) {
		setPushDictItemId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getPushDictItemId() {
		return _pushDictItemId;
	}

	public void setPushDictItemId(long pushDictItemId) {
		_pushDictItemId = pushDictItemId;
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

	public String getParentItemCode() {
		return _parentItemCode;
	}

	public void setParentItemCode(String parentItemCode) {
		_parentItemCode = parentItemCode;
	}

	public String getSibling() {
		return _sibling;
	}

	public void setSibling(String sibling) {
		_sibling = sibling;
	}

	public String getMethod() {
		return _method;
	}

	public void setMethod(String method) {
		_method = method;
	}

	public String getMetaData() {
		return _metaData;
	}

	public void setMetaData(String metaData) {
		_metaData = metaData;
	}

	private String _uuid;
	private long _pushDictItemId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _serverNo;
	private String _collectionCode;
	private String _itemCode;
	private String _itemName;
	private String _itemNameEN;
	private String _itemDescription;
	private String _parentItemCode;
	private String _sibling;
	private String _method;
	private String _metaData;
}