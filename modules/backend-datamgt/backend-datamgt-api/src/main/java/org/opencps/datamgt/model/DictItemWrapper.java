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
 * This class is a wrapper for {@link DictItem}.
 * </p>
 *
 * @author Binhth
 * @see DictItem
 * @generated
 */
@ProviderType
public class DictItemWrapper implements DictItem, ModelWrapper<DictItem> {
	public DictItemWrapper(DictItem dictItem) {
		_dictItem = dictItem;
	}

	@Override
	public Class<?> getModelClass() {
		return DictItem.class;
	}

	@Override
	public String getModelClassName() {
		return DictItem.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("dictItemId", getDictItemId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("dictCollectionId", getDictCollectionId());
		attributes.put("itemCode", getItemCode());
		attributes.put("itemName", getItemName());
		attributes.put("itemNameEN", getItemNameEN());
		attributes.put("itemDescription", getItemDescription());
		attributes.put("parentItemId", getParentItemId());
		attributes.put("level", getLevel());
		attributes.put("sibling", getSibling());
		attributes.put("treeIndex", getTreeIndex());
		attributes.put("dataForm", getDataForm());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long dictItemId = (Long)attributes.get("dictItemId");

		if (dictItemId != null) {
			setDictItemId(dictItemId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
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

		Long dictCollectionId = (Long)attributes.get("dictCollectionId");

		if (dictCollectionId != null) {
			setDictCollectionId(dictCollectionId);
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

		Long parentItemId = (Long)attributes.get("parentItemId");

		if (parentItemId != null) {
			setParentItemId(parentItemId);
		}

		Integer level = (Integer)attributes.get("level");

		if (level != null) {
			setLevel(level);
		}

		String sibling = (String)attributes.get("sibling");

		if (sibling != null) {
			setSibling(sibling);
		}

		String treeIndex = (String)attributes.get("treeIndex");

		if (treeIndex != null) {
			setTreeIndex(treeIndex);
		}

		String dataForm = (String)attributes.get("dataForm");

		if (dataForm != null) {
			setDataForm(dataForm);
		}
	}

	@Override
	public DictItem toEscapedModel() {
		return new DictItemWrapper(_dictItem.toEscapedModel());
	}

	@Override
	public DictItem toUnescapedModel() {
		return new DictItemWrapper(_dictItem.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _dictItem.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _dictItem.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _dictItem.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _dictItem.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<DictItem> toCacheModel() {
		return _dictItem.toCacheModel();
	}

	@Override
	public int compareTo(DictItem dictItem) {
		return _dictItem.compareTo(dictItem);
	}

	/**
	* Returns the level of this dict item.
	*
	* @return the level of this dict item
	*/
	@Override
	public int getLevel() {
		return _dictItem.getLevel();
	}

	@Override
	public int hashCode() {
		return _dictItem.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _dictItem.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new DictItemWrapper((DictItem)_dictItem.clone());
	}

	/**
	* Returns the data form of this dict item.
	*
	* @return the data form of this dict item
	*/
	@Override
	public java.lang.String getDataForm() {
		return _dictItem.getDataForm();
	}

	/**
	* Returns the item code of this dict item.
	*
	* @return the item code of this dict item
	*/
	@Override
	public java.lang.String getItemCode() {
		return _dictItem.getItemCode();
	}

	/**
	* Returns the item description of this dict item.
	*
	* @return the item description of this dict item
	*/
	@Override
	public java.lang.String getItemDescription() {
		return _dictItem.getItemDescription();
	}

	/**
	* Returns the item name of this dict item.
	*
	* @return the item name of this dict item
	*/
	@Override
	public java.lang.String getItemName() {
		return _dictItem.getItemName();
	}

	/**
	* Returns the item name en of this dict item.
	*
	* @return the item name en of this dict item
	*/
	@Override
	public java.lang.String getItemNameEN() {
		return _dictItem.getItemNameEN();
	}

	/**
	* Returns the sibling of this dict item.
	*
	* @return the sibling of this dict item
	*/
	@Override
	public java.lang.String getSibling() {
		return _dictItem.getSibling();
	}

	/**
	* Returns the tree index of this dict item.
	*
	* @return the tree index of this dict item
	*/
	@Override
	public java.lang.String getTreeIndex() {
		return _dictItem.getTreeIndex();
	}

	/**
	* Returns the user name of this dict item.
	*
	* @return the user name of this dict item
	*/
	@Override
	public java.lang.String getUserName() {
		return _dictItem.getUserName();
	}

	/**
	* Returns the user uuid of this dict item.
	*
	* @return the user uuid of this dict item
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _dictItem.getUserUuid();
	}

	/**
	* Returns the uuid of this dict item.
	*
	* @return the uuid of this dict item
	*/
	@Override
	public java.lang.String getUuid() {
		return _dictItem.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _dictItem.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _dictItem.toXmlString();
	}

	/**
	* Returns the create date of this dict item.
	*
	* @return the create date of this dict item
	*/
	@Override
	public Date getCreateDate() {
		return _dictItem.getCreateDate();
	}

	/**
	* Returns the modified date of this dict item.
	*
	* @return the modified date of this dict item
	*/
	@Override
	public Date getModifiedDate() {
		return _dictItem.getModifiedDate();
	}

	/**
	* Returns the company ID of this dict item.
	*
	* @return the company ID of this dict item
	*/
	@Override
	public long getCompanyId() {
		return _dictItem.getCompanyId();
	}

	/**
	* Returns the dict collection ID of this dict item.
	*
	* @return the dict collection ID of this dict item
	*/
	@Override
	public long getDictCollectionId() {
		return _dictItem.getDictCollectionId();
	}

	/**
	* Returns the dict item ID of this dict item.
	*
	* @return the dict item ID of this dict item
	*/
	@Override
	public long getDictItemId() {
		return _dictItem.getDictItemId();
	}

	/**
	* Returns the group ID of this dict item.
	*
	* @return the group ID of this dict item
	*/
	@Override
	public long getGroupId() {
		return _dictItem.getGroupId();
	}

	/**
	* Returns the parent item ID of this dict item.
	*
	* @return the parent item ID of this dict item
	*/
	@Override
	public long getParentItemId() {
		return _dictItem.getParentItemId();
	}

	/**
	* Returns the primary key of this dict item.
	*
	* @return the primary key of this dict item
	*/
	@Override
	public long getPrimaryKey() {
		return _dictItem.getPrimaryKey();
	}

	/**
	* Returns the user ID of this dict item.
	*
	* @return the user ID of this dict item
	*/
	@Override
	public long getUserId() {
		return _dictItem.getUserId();
	}

	@Override
	public void persist() {
		_dictItem.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_dictItem.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this dict item.
	*
	* @param companyId the company ID of this dict item
	*/
	@Override
	public void setCompanyId(long companyId) {
		_dictItem.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this dict item.
	*
	* @param createDate the create date of this dict item
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_dictItem.setCreateDate(createDate);
	}

	/**
	* Sets the data form of this dict item.
	*
	* @param dataForm the data form of this dict item
	*/
	@Override
	public void setDataForm(java.lang.String dataForm) {
		_dictItem.setDataForm(dataForm);
	}

	/**
	* Sets the dict collection ID of this dict item.
	*
	* @param dictCollectionId the dict collection ID of this dict item
	*/
	@Override
	public void setDictCollectionId(long dictCollectionId) {
		_dictItem.setDictCollectionId(dictCollectionId);
	}

	/**
	* Sets the dict item ID of this dict item.
	*
	* @param dictItemId the dict item ID of this dict item
	*/
	@Override
	public void setDictItemId(long dictItemId) {
		_dictItem.setDictItemId(dictItemId);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_dictItem.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_dictItem.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_dictItem.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this dict item.
	*
	* @param groupId the group ID of this dict item
	*/
	@Override
	public void setGroupId(long groupId) {
		_dictItem.setGroupId(groupId);
	}

	/**
	* Sets the item code of this dict item.
	*
	* @param itemCode the item code of this dict item
	*/
	@Override
	public void setItemCode(java.lang.String itemCode) {
		_dictItem.setItemCode(itemCode);
	}

	/**
	* Sets the item description of this dict item.
	*
	* @param itemDescription the item description of this dict item
	*/
	@Override
	public void setItemDescription(java.lang.String itemDescription) {
		_dictItem.setItemDescription(itemDescription);
	}

	/**
	* Sets the item name of this dict item.
	*
	* @param itemName the item name of this dict item
	*/
	@Override
	public void setItemName(java.lang.String itemName) {
		_dictItem.setItemName(itemName);
	}

	/**
	* Sets the item name en of this dict item.
	*
	* @param itemNameEN the item name en of this dict item
	*/
	@Override
	public void setItemNameEN(java.lang.String itemNameEN) {
		_dictItem.setItemNameEN(itemNameEN);
	}

	/**
	* Sets the level of this dict item.
	*
	* @param level the level of this dict item
	*/
	@Override
	public void setLevel(int level) {
		_dictItem.setLevel(level);
	}

	/**
	* Sets the modified date of this dict item.
	*
	* @param modifiedDate the modified date of this dict item
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_dictItem.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_dictItem.setNew(n);
	}

	/**
	* Sets the parent item ID of this dict item.
	*
	* @param parentItemId the parent item ID of this dict item
	*/
	@Override
	public void setParentItemId(long parentItemId) {
		_dictItem.setParentItemId(parentItemId);
	}

	/**
	* Sets the primary key of this dict item.
	*
	* @param primaryKey the primary key of this dict item
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_dictItem.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_dictItem.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the sibling of this dict item.
	*
	* @param sibling the sibling of this dict item
	*/
	@Override
	public void setSibling(java.lang.String sibling) {
		_dictItem.setSibling(sibling);
	}

	/**
	* Sets the tree index of this dict item.
	*
	* @param treeIndex the tree index of this dict item
	*/
	@Override
	public void setTreeIndex(java.lang.String treeIndex) {
		_dictItem.setTreeIndex(treeIndex);
	}

	/**
	* Sets the user ID of this dict item.
	*
	* @param userId the user ID of this dict item
	*/
	@Override
	public void setUserId(long userId) {
		_dictItem.setUserId(userId);
	}

	/**
	* Sets the user name of this dict item.
	*
	* @param userName the user name of this dict item
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_dictItem.setUserName(userName);
	}

	/**
	* Sets the user uuid of this dict item.
	*
	* @param userUuid the user uuid of this dict item
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_dictItem.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this dict item.
	*
	* @param uuid the uuid of this dict item
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_dictItem.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DictItemWrapper)) {
			return false;
		}

		DictItemWrapper dictItemWrapper = (DictItemWrapper)obj;

		if (Objects.equals(_dictItem, dictItemWrapper._dictItem)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _dictItem.getStagedModelType();
	}

	@Override
	public DictItem getWrappedModel() {
		return _dictItem;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _dictItem.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _dictItem.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_dictItem.resetOriginalValues();
	}

	private final DictItem _dictItem;
}