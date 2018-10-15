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
 * This class is a wrapper for {@link DictItemTemp}.
 * </p>
 *
 * @author trungdk
 * @see DictItemTemp
 * @generated
 */
@ProviderType
public class DictItemTempWrapper implements DictItemTemp,
	ModelWrapper<DictItemTemp> {
	public DictItemTempWrapper(DictItemTemp dictItemTemp) {
		_dictItemTemp = dictItemTemp;
	}

	@Override
	public Class<?> getModelClass() {
		return DictItemTemp.class;
	}

	@Override
	public String getModelClassName() {
		return DictItemTemp.class.getName();
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
		attributes.put("metaData", getMetaData());
		attributes.put("status", getStatus());

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

		String metaData = (String)attributes.get("metaData");

		if (metaData != null) {
			setMetaData(metaData);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public Object clone() {
		return new DictItemTempWrapper((DictItemTemp)_dictItemTemp.clone());
	}

	@Override
	public int compareTo(DictItemTemp dictItemTemp) {
		return _dictItemTemp.compareTo(dictItemTemp);
	}

	/**
	* Returns the company ID of this dict item temp.
	*
	* @return the company ID of this dict item temp
	*/
	@Override
	public long getCompanyId() {
		return _dictItemTemp.getCompanyId();
	}

	/**
	* Returns the create date of this dict item temp.
	*
	* @return the create date of this dict item temp
	*/
	@Override
	public Date getCreateDate() {
		return _dictItemTemp.getCreateDate();
	}

	/**
	* Returns the dict collection ID of this dict item temp.
	*
	* @return the dict collection ID of this dict item temp
	*/
	@Override
	public long getDictCollectionId() {
		return _dictItemTemp.getDictCollectionId();
	}

	/**
	* Returns the dict item ID of this dict item temp.
	*
	* @return the dict item ID of this dict item temp
	*/
	@Override
	public long getDictItemId() {
		return _dictItemTemp.getDictItemId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _dictItemTemp.getExpandoBridge();
	}

	/**
	* Returns the group ID of this dict item temp.
	*
	* @return the group ID of this dict item temp
	*/
	@Override
	public long getGroupId() {
		return _dictItemTemp.getGroupId();
	}

	/**
	* Returns the item code of this dict item temp.
	*
	* @return the item code of this dict item temp
	*/
	@Override
	public String getItemCode() {
		return _dictItemTemp.getItemCode();
	}

	/**
	* Returns the item description of this dict item temp.
	*
	* @return the item description of this dict item temp
	*/
	@Override
	public String getItemDescription() {
		return _dictItemTemp.getItemDescription();
	}

	/**
	* Returns the item name of this dict item temp.
	*
	* @return the item name of this dict item temp
	*/
	@Override
	public String getItemName() {
		return _dictItemTemp.getItemName();
	}

	/**
	* Returns the item name en of this dict item temp.
	*
	* @return the item name en of this dict item temp
	*/
	@Override
	public String getItemNameEN() {
		return _dictItemTemp.getItemNameEN();
	}

	/**
	* Returns the level of this dict item temp.
	*
	* @return the level of this dict item temp
	*/
	@Override
	public int getLevel() {
		return _dictItemTemp.getLevel();
	}

	/**
	* Returns the meta data of this dict item temp.
	*
	* @return the meta data of this dict item temp
	*/
	@Override
	public String getMetaData() {
		return _dictItemTemp.getMetaData();
	}

	/**
	* Returns the modified date of this dict item temp.
	*
	* @return the modified date of this dict item temp
	*/
	@Override
	public Date getModifiedDate() {
		return _dictItemTemp.getModifiedDate();
	}

	/**
	* Returns the parent item ID of this dict item temp.
	*
	* @return the parent item ID of this dict item temp
	*/
	@Override
	public long getParentItemId() {
		return _dictItemTemp.getParentItemId();
	}

	/**
	* Returns the primary key of this dict item temp.
	*
	* @return the primary key of this dict item temp
	*/
	@Override
	public long getPrimaryKey() {
		return _dictItemTemp.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _dictItemTemp.getPrimaryKeyObj();
	}

	/**
	* Returns the sibling of this dict item temp.
	*
	* @return the sibling of this dict item temp
	*/
	@Override
	public String getSibling() {
		return _dictItemTemp.getSibling();
	}

	/**
	* Returns the status of this dict item temp.
	*
	* @return the status of this dict item temp
	*/
	@Override
	public int getStatus() {
		return _dictItemTemp.getStatus();
	}

	/**
	* Returns the tree index of this dict item temp.
	*
	* @return the tree index of this dict item temp
	*/
	@Override
	public String getTreeIndex() {
		return _dictItemTemp.getTreeIndex();
	}

	/**
	* Returns the user ID of this dict item temp.
	*
	* @return the user ID of this dict item temp
	*/
	@Override
	public long getUserId() {
		return _dictItemTemp.getUserId();
	}

	/**
	* Returns the user name of this dict item temp.
	*
	* @return the user name of this dict item temp
	*/
	@Override
	public String getUserName() {
		return _dictItemTemp.getUserName();
	}

	/**
	* Returns the user uuid of this dict item temp.
	*
	* @return the user uuid of this dict item temp
	*/
	@Override
	public String getUserUuid() {
		return _dictItemTemp.getUserUuid();
	}

	/**
	* Returns the uuid of this dict item temp.
	*
	* @return the uuid of this dict item temp
	*/
	@Override
	public String getUuid() {
		return _dictItemTemp.getUuid();
	}

	@Override
	public int hashCode() {
		return _dictItemTemp.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _dictItemTemp.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _dictItemTemp.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _dictItemTemp.isNew();
	}

	@Override
	public void persist() {
		_dictItemTemp.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_dictItemTemp.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this dict item temp.
	*
	* @param companyId the company ID of this dict item temp
	*/
	@Override
	public void setCompanyId(long companyId) {
		_dictItemTemp.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this dict item temp.
	*
	* @param createDate the create date of this dict item temp
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_dictItemTemp.setCreateDate(createDate);
	}

	/**
	* Sets the dict collection ID of this dict item temp.
	*
	* @param dictCollectionId the dict collection ID of this dict item temp
	*/
	@Override
	public void setDictCollectionId(long dictCollectionId) {
		_dictItemTemp.setDictCollectionId(dictCollectionId);
	}

	/**
	* Sets the dict item ID of this dict item temp.
	*
	* @param dictItemId the dict item ID of this dict item temp
	*/
	@Override
	public void setDictItemId(long dictItemId) {
		_dictItemTemp.setDictItemId(dictItemId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_dictItemTemp.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_dictItemTemp.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_dictItemTemp.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this dict item temp.
	*
	* @param groupId the group ID of this dict item temp
	*/
	@Override
	public void setGroupId(long groupId) {
		_dictItemTemp.setGroupId(groupId);
	}

	/**
	* Sets the item code of this dict item temp.
	*
	* @param itemCode the item code of this dict item temp
	*/
	@Override
	public void setItemCode(String itemCode) {
		_dictItemTemp.setItemCode(itemCode);
	}

	/**
	* Sets the item description of this dict item temp.
	*
	* @param itemDescription the item description of this dict item temp
	*/
	@Override
	public void setItemDescription(String itemDescription) {
		_dictItemTemp.setItemDescription(itemDescription);
	}

	/**
	* Sets the item name of this dict item temp.
	*
	* @param itemName the item name of this dict item temp
	*/
	@Override
	public void setItemName(String itemName) {
		_dictItemTemp.setItemName(itemName);
	}

	/**
	* Sets the item name en of this dict item temp.
	*
	* @param itemNameEN the item name en of this dict item temp
	*/
	@Override
	public void setItemNameEN(String itemNameEN) {
		_dictItemTemp.setItemNameEN(itemNameEN);
	}

	/**
	* Sets the level of this dict item temp.
	*
	* @param level the level of this dict item temp
	*/
	@Override
	public void setLevel(int level) {
		_dictItemTemp.setLevel(level);
	}

	/**
	* Sets the meta data of this dict item temp.
	*
	* @param metaData the meta data of this dict item temp
	*/
	@Override
	public void setMetaData(String metaData) {
		_dictItemTemp.setMetaData(metaData);
	}

	/**
	* Sets the modified date of this dict item temp.
	*
	* @param modifiedDate the modified date of this dict item temp
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_dictItemTemp.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_dictItemTemp.setNew(n);
	}

	/**
	* Sets the parent item ID of this dict item temp.
	*
	* @param parentItemId the parent item ID of this dict item temp
	*/
	@Override
	public void setParentItemId(long parentItemId) {
		_dictItemTemp.setParentItemId(parentItemId);
	}

	/**
	* Sets the primary key of this dict item temp.
	*
	* @param primaryKey the primary key of this dict item temp
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_dictItemTemp.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_dictItemTemp.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the sibling of this dict item temp.
	*
	* @param sibling the sibling of this dict item temp
	*/
	@Override
	public void setSibling(String sibling) {
		_dictItemTemp.setSibling(sibling);
	}

	/**
	* Sets the status of this dict item temp.
	*
	* @param status the status of this dict item temp
	*/
	@Override
	public void setStatus(int status) {
		_dictItemTemp.setStatus(status);
	}

	/**
	* Sets the tree index of this dict item temp.
	*
	* @param treeIndex the tree index of this dict item temp
	*/
	@Override
	public void setTreeIndex(String treeIndex) {
		_dictItemTemp.setTreeIndex(treeIndex);
	}

	/**
	* Sets the user ID of this dict item temp.
	*
	* @param userId the user ID of this dict item temp
	*/
	@Override
	public void setUserId(long userId) {
		_dictItemTemp.setUserId(userId);
	}

	/**
	* Sets the user name of this dict item temp.
	*
	* @param userName the user name of this dict item temp
	*/
	@Override
	public void setUserName(String userName) {
		_dictItemTemp.setUserName(userName);
	}

	/**
	* Sets the user uuid of this dict item temp.
	*
	* @param userUuid the user uuid of this dict item temp
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_dictItemTemp.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this dict item temp.
	*
	* @param uuid the uuid of this dict item temp
	*/
	@Override
	public void setUuid(String uuid) {
		_dictItemTemp.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<DictItemTemp> toCacheModel() {
		return _dictItemTemp.toCacheModel();
	}

	@Override
	public DictItemTemp toEscapedModel() {
		return new DictItemTempWrapper(_dictItemTemp.toEscapedModel());
	}

	@Override
	public String toString() {
		return _dictItemTemp.toString();
	}

	@Override
	public DictItemTemp toUnescapedModel() {
		return new DictItemTempWrapper(_dictItemTemp.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _dictItemTemp.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DictItemTempWrapper)) {
			return false;
		}

		DictItemTempWrapper dictItemTempWrapper = (DictItemTempWrapper)obj;

		if (Objects.equals(_dictItemTemp, dictItemTempWrapper._dictItemTemp)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _dictItemTemp.getStagedModelType();
	}

	@Override
	public DictItemTemp getWrappedModel() {
		return _dictItemTemp;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _dictItemTemp.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _dictItemTemp.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_dictItemTemp.resetOriginalValues();
	}

	private final DictItemTemp _dictItemTemp;
}