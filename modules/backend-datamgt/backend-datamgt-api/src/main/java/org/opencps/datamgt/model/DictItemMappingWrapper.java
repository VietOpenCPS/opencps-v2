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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link DictItemMapping}.
 * </p>
 *
 * @author khoavu
 * @see DictItemMapping
 * @generated
 */
@ProviderType
public class DictItemMappingWrapper implements DictItemMapping,
	ModelWrapper<DictItemMapping> {
	public DictItemMappingWrapper(DictItemMapping dictItemMapping) {
		_dictItemMapping = dictItemMapping;
	}

	@Override
	public Class<?> getModelClass() {
		return DictItemMapping.class;
	}

	@Override
	public String getModelClassName() {
		return DictItemMapping.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mappingId", getMappingId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("itemCode", getItemCode());
		attributes.put("itemCodeDVCQG", getItemCodeDVCQG());
		attributes.put("collectionId", getCollectionId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mappingId = (Long)attributes.get("mappingId");

		if (mappingId != null) {
			setMappingId(mappingId);
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

		String itemCode = (String)attributes.get("itemCode");

		if (itemCode != null) {
			setItemCode(itemCode);
		}

		String itemCodeDVCQG = (String)attributes.get("itemCodeDVCQG");

		if (itemCodeDVCQG != null) {
			setItemCodeDVCQG(itemCodeDVCQG);
		}

		Long collectionId = (Long)attributes.get("collectionId");

		if (collectionId != null) {
			setCollectionId(collectionId);
		}
	}

	@Override
	public Object clone() {
		return new DictItemMappingWrapper((DictItemMapping)_dictItemMapping.clone());
	}

	@Override
	public int compareTo(DictItemMapping dictItemMapping) {
		return _dictItemMapping.compareTo(dictItemMapping);
	}

	/**
	* Returns the collection ID of this dict item mapping.
	*
	* @return the collection ID of this dict item mapping
	*/
	@Override
	public long getCollectionId() {
		return _dictItemMapping.getCollectionId();
	}

	/**
	* Returns the company ID of this dict item mapping.
	*
	* @return the company ID of this dict item mapping
	*/
	@Override
	public long getCompanyId() {
		return _dictItemMapping.getCompanyId();
	}

	/**
	* Returns the create date of this dict item mapping.
	*
	* @return the create date of this dict item mapping
	*/
	@Override
	public Date getCreateDate() {
		return _dictItemMapping.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _dictItemMapping.getExpandoBridge();
	}

	/**
	* Returns the group ID of this dict item mapping.
	*
	* @return the group ID of this dict item mapping
	*/
	@Override
	public long getGroupId() {
		return _dictItemMapping.getGroupId();
	}

	/**
	* Returns the item code of this dict item mapping.
	*
	* @return the item code of this dict item mapping
	*/
	@Override
	public String getItemCode() {
		return _dictItemMapping.getItemCode();
	}

	/**
	* Returns the item code dvcqg of this dict item mapping.
	*
	* @return the item code dvcqg of this dict item mapping
	*/
	@Override
	public String getItemCodeDVCQG() {
		return _dictItemMapping.getItemCodeDVCQG();
	}

	/**
	* Returns the mapping ID of this dict item mapping.
	*
	* @return the mapping ID of this dict item mapping
	*/
	@Override
	public long getMappingId() {
		return _dictItemMapping.getMappingId();
	}

	/**
	* Returns the modified date of this dict item mapping.
	*
	* @return the modified date of this dict item mapping
	*/
	@Override
	public Date getModifiedDate() {
		return _dictItemMapping.getModifiedDate();
	}

	/**
	* Returns the primary key of this dict item mapping.
	*
	* @return the primary key of this dict item mapping
	*/
	@Override
	public long getPrimaryKey() {
		return _dictItemMapping.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _dictItemMapping.getPrimaryKeyObj();
	}

	/**
	* Returns the user ID of this dict item mapping.
	*
	* @return the user ID of this dict item mapping
	*/
	@Override
	public long getUserId() {
		return _dictItemMapping.getUserId();
	}

	/**
	* Returns the user name of this dict item mapping.
	*
	* @return the user name of this dict item mapping
	*/
	@Override
	public String getUserName() {
		return _dictItemMapping.getUserName();
	}

	/**
	* Returns the user uuid of this dict item mapping.
	*
	* @return the user uuid of this dict item mapping
	*/
	@Override
	public String getUserUuid() {
		return _dictItemMapping.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _dictItemMapping.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _dictItemMapping.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _dictItemMapping.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _dictItemMapping.isNew();
	}

	@Override
	public void persist() {
		_dictItemMapping.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_dictItemMapping.setCachedModel(cachedModel);
	}

	/**
	* Sets the collection ID of this dict item mapping.
	*
	* @param collectionId the collection ID of this dict item mapping
	*/
	@Override
	public void setCollectionId(long collectionId) {
		_dictItemMapping.setCollectionId(collectionId);
	}

	/**
	* Sets the company ID of this dict item mapping.
	*
	* @param companyId the company ID of this dict item mapping
	*/
	@Override
	public void setCompanyId(long companyId) {
		_dictItemMapping.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this dict item mapping.
	*
	* @param createDate the create date of this dict item mapping
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_dictItemMapping.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_dictItemMapping.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_dictItemMapping.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_dictItemMapping.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this dict item mapping.
	*
	* @param groupId the group ID of this dict item mapping
	*/
	@Override
	public void setGroupId(long groupId) {
		_dictItemMapping.setGroupId(groupId);
	}

	/**
	* Sets the item code of this dict item mapping.
	*
	* @param itemCode the item code of this dict item mapping
	*/
	@Override
	public void setItemCode(String itemCode) {
		_dictItemMapping.setItemCode(itemCode);
	}

	/**
	* Sets the item code dvcqg of this dict item mapping.
	*
	* @param itemCodeDVCQG the item code dvcqg of this dict item mapping
	*/
	@Override
	public void setItemCodeDVCQG(String itemCodeDVCQG) {
		_dictItemMapping.setItemCodeDVCQG(itemCodeDVCQG);
	}

	/**
	* Sets the mapping ID of this dict item mapping.
	*
	* @param mappingId the mapping ID of this dict item mapping
	*/
	@Override
	public void setMappingId(long mappingId) {
		_dictItemMapping.setMappingId(mappingId);
	}

	/**
	* Sets the modified date of this dict item mapping.
	*
	* @param modifiedDate the modified date of this dict item mapping
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_dictItemMapping.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_dictItemMapping.setNew(n);
	}

	/**
	* Sets the primary key of this dict item mapping.
	*
	* @param primaryKey the primary key of this dict item mapping
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_dictItemMapping.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_dictItemMapping.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this dict item mapping.
	*
	* @param userId the user ID of this dict item mapping
	*/
	@Override
	public void setUserId(long userId) {
		_dictItemMapping.setUserId(userId);
	}

	/**
	* Sets the user name of this dict item mapping.
	*
	* @param userName the user name of this dict item mapping
	*/
	@Override
	public void setUserName(String userName) {
		_dictItemMapping.setUserName(userName);
	}

	/**
	* Sets the user uuid of this dict item mapping.
	*
	* @param userUuid the user uuid of this dict item mapping
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_dictItemMapping.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<DictItemMapping> toCacheModel() {
		return _dictItemMapping.toCacheModel();
	}

	@Override
	public DictItemMapping toEscapedModel() {
		return new DictItemMappingWrapper(_dictItemMapping.toEscapedModel());
	}

	@Override
	public String toString() {
		return _dictItemMapping.toString();
	}

	@Override
	public DictItemMapping toUnescapedModel() {
		return new DictItemMappingWrapper(_dictItemMapping.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _dictItemMapping.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DictItemMappingWrapper)) {
			return false;
		}

		DictItemMappingWrapper dictItemMappingWrapper = (DictItemMappingWrapper)obj;

		if (Objects.equals(_dictItemMapping,
					dictItemMappingWrapper._dictItemMapping)) {
			return true;
		}

		return false;
	}

	@Override
	public DictItemMapping getWrappedModel() {
		return _dictItemMapping;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _dictItemMapping.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _dictItemMapping.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_dictItemMapping.resetOriginalValues();
	}

	private final DictItemMapping _dictItemMapping;
}