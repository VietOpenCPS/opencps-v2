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

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link PushDictItem}.
 * </p>
 *
 * @author trungdk
 * @see PushDictItem
 * @generated
 */
@ProviderType
public class PushDictItemWrapper implements PushDictItem,
	ModelWrapper<PushDictItem> {
	public PushDictItemWrapper(PushDictItem pushDictItem) {
		_pushDictItem = pushDictItem;
	}

	@Override
	public Class<?> getModelClass() {
		return PushDictItem.class;
	}

	@Override
	public String getModelClassName() {
		return PushDictItem.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("pushDictItemId", getPushDictItemId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("serverNo", getServerNo());
		attributes.put("collectionCode", getCollectionCode());
		attributes.put("itemCode", getItemCode());
		attributes.put("itemName", getItemName());
		attributes.put("itemNameEN", getItemNameEN());
		attributes.put("itemDescription", getItemDescription());
		attributes.put("parentItemCode", getParentItemCode());
		attributes.put("sibling", getSibling());
		attributes.put("method", getMethod());
		attributes.put("metaData", getMetaData());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long pushDictItemId = (Long)attributes.get("pushDictItemId");

		if (pushDictItemId != null) {
			setPushDictItemId(pushDictItemId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String serverNo = (String)attributes.get("serverNo");

		if (serverNo != null) {
			setServerNo(serverNo);
		}

		String collectionCode = (String)attributes.get("collectionCode");

		if (collectionCode != null) {
			setCollectionCode(collectionCode);
		}

		String itemCode = (String)attributes.get("itemCode");

		if (itemCode != null) {
			setItemCode(itemCode);
		}

		String itemName = (String)attributes.get("itemName");

		if (itemName != null) {
			setItemName(itemName);
		}

		String itemNameEN = (String)attributes.get("itemNameEN");

		if (itemNameEN != null) {
			setItemNameEN(itemNameEN);
		}

		String itemDescription = (String)attributes.get("itemDescription");

		if (itemDescription != null) {
			setItemDescription(itemDescription);
		}

		String parentItemCode = (String)attributes.get("parentItemCode");

		if (parentItemCode != null) {
			setParentItemCode(parentItemCode);
		}

		String sibling = (String)attributes.get("sibling");

		if (sibling != null) {
			setSibling(sibling);
		}

		String method = (String)attributes.get("method");

		if (method != null) {
			setMethod(method);
		}

		String metaData = (String)attributes.get("metaData");

		if (metaData != null) {
			setMetaData(metaData);
		}
	}

	@Override
	public PushDictItem toEscapedModel() {
		return new PushDictItemWrapper(_pushDictItem.toEscapedModel());
	}

	@Override
	public PushDictItem toUnescapedModel() {
		return new PushDictItemWrapper(_pushDictItem.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _pushDictItem.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _pushDictItem.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _pushDictItem.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _pushDictItem.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<PushDictItem> toCacheModel() {
		return _pushDictItem.toCacheModel();
	}

	@Override
	public int compareTo(PushDictItem pushDictItem) {
		return _pushDictItem.compareTo(pushDictItem);
	}

	@Override
	public int hashCode() {
		return _pushDictItem.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _pushDictItem.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new PushDictItemWrapper((PushDictItem)_pushDictItem.clone());
	}

	/**
	* Returns the collection code of this push dict item.
	*
	* @return the collection code of this push dict item
	*/
	@Override
	public java.lang.String getCollectionCode() {
		return _pushDictItem.getCollectionCode();
	}

	/**
	* Returns the item code of this push dict item.
	*
	* @return the item code of this push dict item
	*/
	@Override
	public java.lang.String getItemCode() {
		return _pushDictItem.getItemCode();
	}

	/**
	* Returns the item description of this push dict item.
	*
	* @return the item description of this push dict item
	*/
	@Override
	public java.lang.String getItemDescription() {
		return _pushDictItem.getItemDescription();
	}

	/**
	* Returns the item name of this push dict item.
	*
	* @return the item name of this push dict item
	*/
	@Override
	public java.lang.String getItemName() {
		return _pushDictItem.getItemName();
	}

	/**
	* Returns the item name en of this push dict item.
	*
	* @return the item name en of this push dict item
	*/
	@Override
	public java.lang.String getItemNameEN() {
		return _pushDictItem.getItemNameEN();
	}

	/**
	* Returns the meta data of this push dict item.
	*
	* @return the meta data of this push dict item
	*/
	@Override
	public java.lang.String getMetaData() {
		return _pushDictItem.getMetaData();
	}

	/**
	* Returns the method of this push dict item.
	*
	* @return the method of this push dict item
	*/
	@Override
	public java.lang.String getMethod() {
		return _pushDictItem.getMethod();
	}

	/**
	* Returns the parent item code of this push dict item.
	*
	* @return the parent item code of this push dict item
	*/
	@Override
	public java.lang.String getParentItemCode() {
		return _pushDictItem.getParentItemCode();
	}

	/**
	* Returns the server no of this push dict item.
	*
	* @return the server no of this push dict item
	*/
	@Override
	public java.lang.String getServerNo() {
		return _pushDictItem.getServerNo();
	}

	/**
	* Returns the sibling of this push dict item.
	*
	* @return the sibling of this push dict item
	*/
	@Override
	public java.lang.String getSibling() {
		return _pushDictItem.getSibling();
	}

	/**
	* Returns the user name of this push dict item.
	*
	* @return the user name of this push dict item
	*/
	@Override
	public java.lang.String getUserName() {
		return _pushDictItem.getUserName();
	}

	/**
	* Returns the user uuid of this push dict item.
	*
	* @return the user uuid of this push dict item
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _pushDictItem.getUserUuid();
	}

	/**
	* Returns the uuid of this push dict item.
	*
	* @return the uuid of this push dict item
	*/
	@Override
	public java.lang.String getUuid() {
		return _pushDictItem.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _pushDictItem.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _pushDictItem.toXmlString();
	}

	/**
	* Returns the create date of this push dict item.
	*
	* @return the create date of this push dict item
	*/
	@Override
	public Date getCreateDate() {
		return _pushDictItem.getCreateDate();
	}

	/**
	* Returns the modified date of this push dict item.
	*
	* @return the modified date of this push dict item
	*/
	@Override
	public Date getModifiedDate() {
		return _pushDictItem.getModifiedDate();
	}

	/**
	* Returns the company ID of this push dict item.
	*
	* @return the company ID of this push dict item
	*/
	@Override
	public long getCompanyId() {
		return _pushDictItem.getCompanyId();
	}

	/**
	* Returns the group ID of this push dict item.
	*
	* @return the group ID of this push dict item
	*/
	@Override
	public long getGroupId() {
		return _pushDictItem.getGroupId();
	}

	/**
	* Returns the primary key of this push dict item.
	*
	* @return the primary key of this push dict item
	*/
	@Override
	public long getPrimaryKey() {
		return _pushDictItem.getPrimaryKey();
	}

	/**
	* Returns the push dict item ID of this push dict item.
	*
	* @return the push dict item ID of this push dict item
	*/
	@Override
	public long getPushDictItemId() {
		return _pushDictItem.getPushDictItemId();
	}

	/**
	* Returns the user ID of this push dict item.
	*
	* @return the user ID of this push dict item
	*/
	@Override
	public long getUserId() {
		return _pushDictItem.getUserId();
	}

	@Override
	public void persist() {
		_pushDictItem.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_pushDictItem.setCachedModel(cachedModel);
	}

	/**
	* Sets the collection code of this push dict item.
	*
	* @param collectionCode the collection code of this push dict item
	*/
	@Override
	public void setCollectionCode(java.lang.String collectionCode) {
		_pushDictItem.setCollectionCode(collectionCode);
	}

	/**
	* Sets the company ID of this push dict item.
	*
	* @param companyId the company ID of this push dict item
	*/
	@Override
	public void setCompanyId(long companyId) {
		_pushDictItem.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this push dict item.
	*
	* @param createDate the create date of this push dict item
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_pushDictItem.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_pushDictItem.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_pushDictItem.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_pushDictItem.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this push dict item.
	*
	* @param groupId the group ID of this push dict item
	*/
	@Override
	public void setGroupId(long groupId) {
		_pushDictItem.setGroupId(groupId);
	}

	/**
	* Sets the item code of this push dict item.
	*
	* @param itemCode the item code of this push dict item
	*/
	@Override
	public void setItemCode(java.lang.String itemCode) {
		_pushDictItem.setItemCode(itemCode);
	}

	/**
	* Sets the item description of this push dict item.
	*
	* @param itemDescription the item description of this push dict item
	*/
	@Override
	public void setItemDescription(java.lang.String itemDescription) {
		_pushDictItem.setItemDescription(itemDescription);
	}

	/**
	* Sets the item name of this push dict item.
	*
	* @param itemName the item name of this push dict item
	*/
	@Override
	public void setItemName(java.lang.String itemName) {
		_pushDictItem.setItemName(itemName);
	}

	/**
	* Sets the item name en of this push dict item.
	*
	* @param itemNameEN the item name en of this push dict item
	*/
	@Override
	public void setItemNameEN(java.lang.String itemNameEN) {
		_pushDictItem.setItemNameEN(itemNameEN);
	}

	/**
	* Sets the meta data of this push dict item.
	*
	* @param metaData the meta data of this push dict item
	*/
	@Override
	public void setMetaData(java.lang.String metaData) {
		_pushDictItem.setMetaData(metaData);
	}

	/**
	* Sets the method of this push dict item.
	*
	* @param method the method of this push dict item
	*/
	@Override
	public void setMethod(java.lang.String method) {
		_pushDictItem.setMethod(method);
	}

	/**
	* Sets the modified date of this push dict item.
	*
	* @param modifiedDate the modified date of this push dict item
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_pushDictItem.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_pushDictItem.setNew(n);
	}

	/**
	* Sets the parent item code of this push dict item.
	*
	* @param parentItemCode the parent item code of this push dict item
	*/
	@Override
	public void setParentItemCode(java.lang.String parentItemCode) {
		_pushDictItem.setParentItemCode(parentItemCode);
	}

	/**
	* Sets the primary key of this push dict item.
	*
	* @param primaryKey the primary key of this push dict item
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_pushDictItem.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_pushDictItem.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the push dict item ID of this push dict item.
	*
	* @param pushDictItemId the push dict item ID of this push dict item
	*/
	@Override
	public void setPushDictItemId(long pushDictItemId) {
		_pushDictItem.setPushDictItemId(pushDictItemId);
	}

	/**
	* Sets the server no of this push dict item.
	*
	* @param serverNo the server no of this push dict item
	*/
	@Override
	public void setServerNo(java.lang.String serverNo) {
		_pushDictItem.setServerNo(serverNo);
	}

	/**
	* Sets the sibling of this push dict item.
	*
	* @param sibling the sibling of this push dict item
	*/
	@Override
	public void setSibling(java.lang.String sibling) {
		_pushDictItem.setSibling(sibling);
	}

	/**
	* Sets the user ID of this push dict item.
	*
	* @param userId the user ID of this push dict item
	*/
	@Override
	public void setUserId(long userId) {
		_pushDictItem.setUserId(userId);
	}

	/**
	* Sets the user name of this push dict item.
	*
	* @param userName the user name of this push dict item
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_pushDictItem.setUserName(userName);
	}

	/**
	* Sets the user uuid of this push dict item.
	*
	* @param userUuid the user uuid of this push dict item
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_pushDictItem.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this push dict item.
	*
	* @param uuid the uuid of this push dict item
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_pushDictItem.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PushDictItemWrapper)) {
			return false;
		}

		PushDictItemWrapper pushDictItemWrapper = (PushDictItemWrapper)obj;

		if (Objects.equals(_pushDictItem, pushDictItemWrapper._pushDictItem)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _pushDictItem.getStagedModelType();
	}

	@Override
	public PushDictItem getWrappedModel() {
		return _pushDictItem;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _pushDictItem.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _pushDictItem.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_pushDictItem.resetOriginalValues();
	}

	private final PushDictItem _pushDictItem;
}