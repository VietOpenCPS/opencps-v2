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
 * This class is a wrapper for {@link DictGroup}.
 * </p>
 *
 * @author Binhth
 * @see DictGroup
 * @generated
 */
@ProviderType
public class DictGroupWrapper implements DictGroup, ModelWrapper<DictGroup> {
	public DictGroupWrapper(DictGroup dictGroup) {
		_dictGroup = dictGroup;
	}

	@Override
	public Class<?> getModelClass() {
		return DictGroup.class;
	}

	@Override
	public String getModelClassName() {
		return DictGroup.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("dictGroupId", getDictGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("dictCollectionId", getDictCollectionId());
		attributes.put("groupCode", getGroupCode());
		attributes.put("groupName", getGroupName());
		attributes.put("groupNameEN", getGroupNameEN());
		attributes.put("groupDescription", getGroupDescription());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long dictGroupId = (Long)attributes.get("dictGroupId");

		if (dictGroupId != null) {
			setDictGroupId(dictGroupId);
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

		String groupCode = (String)attributes.get("groupCode");

		if (groupCode != null) {
			setGroupCode(groupCode);
		}

		String groupName = (String)attributes.get("groupName");

		if (groupName != null) {
			setGroupName(groupName);
		}

		String groupNameEN = (String)attributes.get("groupNameEN");

		if (groupNameEN != null) {
			setGroupNameEN(groupNameEN);
		}

		String groupDescription = (String)attributes.get("groupDescription");

		if (groupDescription != null) {
			setGroupDescription(groupDescription);
		}
	}

	@Override
	public DictGroup toEscapedModel() {
		return new DictGroupWrapper(_dictGroup.toEscapedModel());
	}

	@Override
	public DictGroup toUnescapedModel() {
		return new DictGroupWrapper(_dictGroup.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _dictGroup.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _dictGroup.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _dictGroup.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _dictGroup.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<DictGroup> toCacheModel() {
		return _dictGroup.toCacheModel();
	}

	@Override
	public int compareTo(DictGroup dictGroup) {
		return _dictGroup.compareTo(dictGroup);
	}

	@Override
	public int hashCode() {
		return _dictGroup.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _dictGroup.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new DictGroupWrapper((DictGroup)_dictGroup.clone());
	}

	/**
	* Returns the group code of this dict group.
	*
	* @return the group code of this dict group
	*/
	@Override
	public java.lang.String getGroupCode() {
		return _dictGroup.getGroupCode();
	}

	/**
	* Returns the group description of this dict group.
	*
	* @return the group description of this dict group
	*/
	@Override
	public java.lang.String getGroupDescription() {
		return _dictGroup.getGroupDescription();
	}

	/**
	* Returns the group name of this dict group.
	*
	* @return the group name of this dict group
	*/
	@Override
	public java.lang.String getGroupName() {
		return _dictGroup.getGroupName();
	}

	/**
	* Returns the group name en of this dict group.
	*
	* @return the group name en of this dict group
	*/
	@Override
	public java.lang.String getGroupNameEN() {
		return _dictGroup.getGroupNameEN();
	}

	/**
	* Returns the user name of this dict group.
	*
	* @return the user name of this dict group
	*/
	@Override
	public java.lang.String getUserName() {
		return _dictGroup.getUserName();
	}

	/**
	* Returns the user uuid of this dict group.
	*
	* @return the user uuid of this dict group
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _dictGroup.getUserUuid();
	}

	/**
	* Returns the uuid of this dict group.
	*
	* @return the uuid of this dict group
	*/
	@Override
	public java.lang.String getUuid() {
		return _dictGroup.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _dictGroup.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _dictGroup.toXmlString();
	}

	/**
	* Returns the create date of this dict group.
	*
	* @return the create date of this dict group
	*/
	@Override
	public Date getCreateDate() {
		return _dictGroup.getCreateDate();
	}

	/**
	* Returns the modified date of this dict group.
	*
	* @return the modified date of this dict group
	*/
	@Override
	public Date getModifiedDate() {
		return _dictGroup.getModifiedDate();
	}

	/**
	* Returns the company ID of this dict group.
	*
	* @return the company ID of this dict group
	*/
	@Override
	public long getCompanyId() {
		return _dictGroup.getCompanyId();
	}

	/**
	* Returns the dict collection ID of this dict group.
	*
	* @return the dict collection ID of this dict group
	*/
	@Override
	public long getDictCollectionId() {
		return _dictGroup.getDictCollectionId();
	}

	/**
	* Returns the dict group ID of this dict group.
	*
	* @return the dict group ID of this dict group
	*/
	@Override
	public long getDictGroupId() {
		return _dictGroup.getDictGroupId();
	}

	/**
	* Returns the group ID of this dict group.
	*
	* @return the group ID of this dict group
	*/
	@Override
	public long getGroupId() {
		return _dictGroup.getGroupId();
	}

	/**
	* Returns the primary key of this dict group.
	*
	* @return the primary key of this dict group
	*/
	@Override
	public long getPrimaryKey() {
		return _dictGroup.getPrimaryKey();
	}

	/**
	* Returns the user ID of this dict group.
	*
	* @return the user ID of this dict group
	*/
	@Override
	public long getUserId() {
		return _dictGroup.getUserId();
	}

	@Override
	public void persist() {
		_dictGroup.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_dictGroup.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this dict group.
	*
	* @param companyId the company ID of this dict group
	*/
	@Override
	public void setCompanyId(long companyId) {
		_dictGroup.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this dict group.
	*
	* @param createDate the create date of this dict group
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_dictGroup.setCreateDate(createDate);
	}

	/**
	* Sets the dict collection ID of this dict group.
	*
	* @param dictCollectionId the dict collection ID of this dict group
	*/
	@Override
	public void setDictCollectionId(long dictCollectionId) {
		_dictGroup.setDictCollectionId(dictCollectionId);
	}

	/**
	* Sets the dict group ID of this dict group.
	*
	* @param dictGroupId the dict group ID of this dict group
	*/
	@Override
	public void setDictGroupId(long dictGroupId) {
		_dictGroup.setDictGroupId(dictGroupId);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_dictGroup.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_dictGroup.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_dictGroup.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group code of this dict group.
	*
	* @param groupCode the group code of this dict group
	*/
	@Override
	public void setGroupCode(java.lang.String groupCode) {
		_dictGroup.setGroupCode(groupCode);
	}

	/**
	* Sets the group description of this dict group.
	*
	* @param groupDescription the group description of this dict group
	*/
	@Override
	public void setGroupDescription(java.lang.String groupDescription) {
		_dictGroup.setGroupDescription(groupDescription);
	}

	/**
	* Sets the group ID of this dict group.
	*
	* @param groupId the group ID of this dict group
	*/
	@Override
	public void setGroupId(long groupId) {
		_dictGroup.setGroupId(groupId);
	}

	/**
	* Sets the group name of this dict group.
	*
	* @param groupName the group name of this dict group
	*/
	@Override
	public void setGroupName(java.lang.String groupName) {
		_dictGroup.setGroupName(groupName);
	}

	/**
	* Sets the group name en of this dict group.
	*
	* @param groupNameEN the group name en of this dict group
	*/
	@Override
	public void setGroupNameEN(java.lang.String groupNameEN) {
		_dictGroup.setGroupNameEN(groupNameEN);
	}

	/**
	* Sets the modified date of this dict group.
	*
	* @param modifiedDate the modified date of this dict group
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_dictGroup.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_dictGroup.setNew(n);
	}

	/**
	* Sets the primary key of this dict group.
	*
	* @param primaryKey the primary key of this dict group
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_dictGroup.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_dictGroup.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this dict group.
	*
	* @param userId the user ID of this dict group
	*/
	@Override
	public void setUserId(long userId) {
		_dictGroup.setUserId(userId);
	}

	/**
	* Sets the user name of this dict group.
	*
	* @param userName the user name of this dict group
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_dictGroup.setUserName(userName);
	}

	/**
	* Sets the user uuid of this dict group.
	*
	* @param userUuid the user uuid of this dict group
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_dictGroup.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this dict group.
	*
	* @param uuid the uuid of this dict group
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_dictGroup.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DictGroupWrapper)) {
			return false;
		}

		DictGroupWrapper dictGroupWrapper = (DictGroupWrapper)obj;

		if (Objects.equals(_dictGroup, dictGroupWrapper._dictGroup)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _dictGroup.getStagedModelType();
	}

	@Override
	public DictGroup getWrappedModel() {
		return _dictGroup;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _dictGroup.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _dictGroup.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_dictGroup.resetOriginalValues();
	}

	private final DictGroup _dictGroup;
}