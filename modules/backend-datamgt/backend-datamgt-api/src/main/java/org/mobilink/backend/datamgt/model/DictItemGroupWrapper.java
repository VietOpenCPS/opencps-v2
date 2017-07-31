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

package org.mobilink.backend.datamgt.model;

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
 * This class is a wrapper for {@link DictItemGroup}.
 * </p>
 *
 * @author Binhth
 * @see DictItemGroup
 * @generated
 */
@ProviderType
public class DictItemGroupWrapper implements DictItemGroup,
	ModelWrapper<DictItemGroup> {
	public DictItemGroupWrapper(DictItemGroup dictItemGroup) {
		_dictItemGroup = dictItemGroup;
	}

	@Override
	public Class<?> getModelClass() {
		return DictItemGroup.class;
	}

	@Override
	public String getModelClassName() {
		return DictItemGroup.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("dictItemGroupId", getDictItemGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("dictGroupId", getDictGroupId());
		attributes.put("dictItemId", getDictItemId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long dictItemGroupId = (Long)attributes.get("dictItemGroupId");

		if (dictItemGroupId != null) {
			setDictItemGroupId(dictItemGroupId);
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

		Long dictGroupId = (Long)attributes.get("dictGroupId");

		if (dictGroupId != null) {
			setDictGroupId(dictGroupId);
		}

		Long dictItemId = (Long)attributes.get("dictItemId");

		if (dictItemId != null) {
			setDictItemId(dictItemId);
		}
	}

	@Override
	public DictItemGroup toEscapedModel() {
		return new DictItemGroupWrapper(_dictItemGroup.toEscapedModel());
	}

	@Override
	public DictItemGroup toUnescapedModel() {
		return new DictItemGroupWrapper(_dictItemGroup.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _dictItemGroup.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _dictItemGroup.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _dictItemGroup.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _dictItemGroup.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<DictItemGroup> toCacheModel() {
		return _dictItemGroup.toCacheModel();
	}

	@Override
	public int compareTo(DictItemGroup dictItemGroup) {
		return _dictItemGroup.compareTo(dictItemGroup);
	}

	@Override
	public int hashCode() {
		return _dictItemGroup.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _dictItemGroup.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new DictItemGroupWrapper((DictItemGroup)_dictItemGroup.clone());
	}

	/**
	* Returns the user name of this dict item group.
	*
	* @return the user name of this dict item group
	*/
	@Override
	public java.lang.String getUserName() {
		return _dictItemGroup.getUserName();
	}

	/**
	* Returns the user uuid of this dict item group.
	*
	* @return the user uuid of this dict item group
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _dictItemGroup.getUserUuid();
	}

	/**
	* Returns the uuid of this dict item group.
	*
	* @return the uuid of this dict item group
	*/
	@Override
	public java.lang.String getUuid() {
		return _dictItemGroup.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _dictItemGroup.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _dictItemGroup.toXmlString();
	}

	/**
	* Returns the create date of this dict item group.
	*
	* @return the create date of this dict item group
	*/
	@Override
	public Date getCreateDate() {
		return _dictItemGroup.getCreateDate();
	}

	/**
	* Returns the modified date of this dict item group.
	*
	* @return the modified date of this dict item group
	*/
	@Override
	public Date getModifiedDate() {
		return _dictItemGroup.getModifiedDate();
	}

	/**
	* Returns the company ID of this dict item group.
	*
	* @return the company ID of this dict item group
	*/
	@Override
	public long getCompanyId() {
		return _dictItemGroup.getCompanyId();
	}

	/**
	* Returns the dict group ID of this dict item group.
	*
	* @return the dict group ID of this dict item group
	*/
	@Override
	public long getDictGroupId() {
		return _dictItemGroup.getDictGroupId();
	}

	/**
	* Returns the dict item group ID of this dict item group.
	*
	* @return the dict item group ID of this dict item group
	*/
	@Override
	public long getDictItemGroupId() {
		return _dictItemGroup.getDictItemGroupId();
	}

	/**
	* Returns the dict item ID of this dict item group.
	*
	* @return the dict item ID of this dict item group
	*/
	@Override
	public long getDictItemId() {
		return _dictItemGroup.getDictItemId();
	}

	/**
	* Returns the group ID of this dict item group.
	*
	* @return the group ID of this dict item group
	*/
	@Override
	public long getGroupId() {
		return _dictItemGroup.getGroupId();
	}

	/**
	* Returns the primary key of this dict item group.
	*
	* @return the primary key of this dict item group
	*/
	@Override
	public long getPrimaryKey() {
		return _dictItemGroup.getPrimaryKey();
	}

	/**
	* Returns the user ID of this dict item group.
	*
	* @return the user ID of this dict item group
	*/
	@Override
	public long getUserId() {
		return _dictItemGroup.getUserId();
	}

	@Override
	public void persist() {
		_dictItemGroup.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_dictItemGroup.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this dict item group.
	*
	* @param companyId the company ID of this dict item group
	*/
	@Override
	public void setCompanyId(long companyId) {
		_dictItemGroup.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this dict item group.
	*
	* @param createDate the create date of this dict item group
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_dictItemGroup.setCreateDate(createDate);
	}

	/**
	* Sets the dict group ID of this dict item group.
	*
	* @param dictGroupId the dict group ID of this dict item group
	*/
	@Override
	public void setDictGroupId(long dictGroupId) {
		_dictItemGroup.setDictGroupId(dictGroupId);
	}

	/**
	* Sets the dict item group ID of this dict item group.
	*
	* @param dictItemGroupId the dict item group ID of this dict item group
	*/
	@Override
	public void setDictItemGroupId(long dictItemGroupId) {
		_dictItemGroup.setDictItemGroupId(dictItemGroupId);
	}

	/**
	* Sets the dict item ID of this dict item group.
	*
	* @param dictItemId the dict item ID of this dict item group
	*/
	@Override
	public void setDictItemId(long dictItemId) {
		_dictItemGroup.setDictItemId(dictItemId);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_dictItemGroup.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_dictItemGroup.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_dictItemGroup.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this dict item group.
	*
	* @param groupId the group ID of this dict item group
	*/
	@Override
	public void setGroupId(long groupId) {
		_dictItemGroup.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this dict item group.
	*
	* @param modifiedDate the modified date of this dict item group
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_dictItemGroup.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_dictItemGroup.setNew(n);
	}

	/**
	* Sets the primary key of this dict item group.
	*
	* @param primaryKey the primary key of this dict item group
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_dictItemGroup.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_dictItemGroup.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this dict item group.
	*
	* @param userId the user ID of this dict item group
	*/
	@Override
	public void setUserId(long userId) {
		_dictItemGroup.setUserId(userId);
	}

	/**
	* Sets the user name of this dict item group.
	*
	* @param userName the user name of this dict item group
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_dictItemGroup.setUserName(userName);
	}

	/**
	* Sets the user uuid of this dict item group.
	*
	* @param userUuid the user uuid of this dict item group
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_dictItemGroup.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this dict item group.
	*
	* @param uuid the uuid of this dict item group
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_dictItemGroup.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DictItemGroupWrapper)) {
			return false;
		}

		DictItemGroupWrapper dictItemGroupWrapper = (DictItemGroupWrapper)obj;

		if (Objects.equals(_dictItemGroup, dictItemGroupWrapper._dictItemGroup)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _dictItemGroup.getStagedModelType();
	}

	@Override
	public DictItemGroup getWrappedModel() {
		return _dictItemGroup;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _dictItemGroup.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _dictItemGroup.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_dictItemGroup.resetOriginalValues();
	}

	private final DictItemGroup _dictItemGroup;
}