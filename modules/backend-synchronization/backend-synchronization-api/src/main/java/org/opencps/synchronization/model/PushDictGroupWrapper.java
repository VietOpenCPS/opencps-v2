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
 * This class is a wrapper for {@link PushDictGroup}.
 * </p>
 *
 * @author trungdk
 * @see PushDictGroup
 * @generated
 */
@ProviderType
public class PushDictGroupWrapper implements PushDictGroup,
	ModelWrapper<PushDictGroup> {
	public PushDictGroupWrapper(PushDictGroup pushDictGroup) {
		_pushDictGroup = pushDictGroup;
	}

	@Override
	public Class<?> getModelClass() {
		return PushDictGroup.class;
	}

	@Override
	public String getModelClassName() {
		return PushDictGroup.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("pushDictGroupId", getPushDictGroupId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("serverNo", getServerNo());
		attributes.put("collectionCode", getCollectionCode());
		attributes.put("groupCode", getGroupCode());
		attributes.put("groupName", getGroupName());
		attributes.put("groupNameEN", getGroupNameEN());
		attributes.put("groupDescription", getGroupDescription());
		attributes.put("itemCode", getItemCode());
		attributes.put("method", getMethod());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long pushDictGroupId = (Long)attributes.get("pushDictGroupId");

		if (pushDictGroupId != null) {
			setPushDictGroupId(pushDictGroupId);
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

		String itemCode = (String)attributes.get("itemCode");

		if (itemCode != null) {
			setItemCode(itemCode);
		}

		String method = (String)attributes.get("method");

		if (method != null) {
			setMethod(method);
		}
	}

	@Override
	public PushDictGroup toEscapedModel() {
		return new PushDictGroupWrapper(_pushDictGroup.toEscapedModel());
	}

	@Override
	public PushDictGroup toUnescapedModel() {
		return new PushDictGroupWrapper(_pushDictGroup.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _pushDictGroup.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _pushDictGroup.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _pushDictGroup.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _pushDictGroup.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<PushDictGroup> toCacheModel() {
		return _pushDictGroup.toCacheModel();
	}

	@Override
	public int compareTo(PushDictGroup pushDictGroup) {
		return _pushDictGroup.compareTo(pushDictGroup);
	}

	@Override
	public int hashCode() {
		return _pushDictGroup.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _pushDictGroup.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new PushDictGroupWrapper((PushDictGroup)_pushDictGroup.clone());
	}

	/**
	* Returns the collection code of this push dict group.
	*
	* @return the collection code of this push dict group
	*/
	@Override
	public java.lang.String getCollectionCode() {
		return _pushDictGroup.getCollectionCode();
	}

	/**
	* Returns the group code of this push dict group.
	*
	* @return the group code of this push dict group
	*/
	@Override
	public java.lang.String getGroupCode() {
		return _pushDictGroup.getGroupCode();
	}

	/**
	* Returns the group description of this push dict group.
	*
	* @return the group description of this push dict group
	*/
	@Override
	public java.lang.String getGroupDescription() {
		return _pushDictGroup.getGroupDescription();
	}

	/**
	* Returns the group name of this push dict group.
	*
	* @return the group name of this push dict group
	*/
	@Override
	public java.lang.String getGroupName() {
		return _pushDictGroup.getGroupName();
	}

	/**
	* Returns the group name en of this push dict group.
	*
	* @return the group name en of this push dict group
	*/
	@Override
	public java.lang.String getGroupNameEN() {
		return _pushDictGroup.getGroupNameEN();
	}

	/**
	* Returns the item code of this push dict group.
	*
	* @return the item code of this push dict group
	*/
	@Override
	public java.lang.String getItemCode() {
		return _pushDictGroup.getItemCode();
	}

	/**
	* Returns the method of this push dict group.
	*
	* @return the method of this push dict group
	*/
	@Override
	public java.lang.String getMethod() {
		return _pushDictGroup.getMethod();
	}

	/**
	* Returns the server no of this push dict group.
	*
	* @return the server no of this push dict group
	*/
	@Override
	public java.lang.String getServerNo() {
		return _pushDictGroup.getServerNo();
	}

	/**
	* Returns the user name of this push dict group.
	*
	* @return the user name of this push dict group
	*/
	@Override
	public java.lang.String getUserName() {
		return _pushDictGroup.getUserName();
	}

	/**
	* Returns the user uuid of this push dict group.
	*
	* @return the user uuid of this push dict group
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _pushDictGroup.getUserUuid();
	}

	/**
	* Returns the uuid of this push dict group.
	*
	* @return the uuid of this push dict group
	*/
	@Override
	public java.lang.String getUuid() {
		return _pushDictGroup.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _pushDictGroup.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _pushDictGroup.toXmlString();
	}

	/**
	* Returns the create date of this push dict group.
	*
	* @return the create date of this push dict group
	*/
	@Override
	public Date getCreateDate() {
		return _pushDictGroup.getCreateDate();
	}

	/**
	* Returns the modified date of this push dict group.
	*
	* @return the modified date of this push dict group
	*/
	@Override
	public Date getModifiedDate() {
		return _pushDictGroup.getModifiedDate();
	}

	/**
	* Returns the company ID of this push dict group.
	*
	* @return the company ID of this push dict group
	*/
	@Override
	public long getCompanyId() {
		return _pushDictGroup.getCompanyId();
	}

	/**
	* Returns the group ID of this push dict group.
	*
	* @return the group ID of this push dict group
	*/
	@Override
	public long getGroupId() {
		return _pushDictGroup.getGroupId();
	}

	/**
	* Returns the primary key of this push dict group.
	*
	* @return the primary key of this push dict group
	*/
	@Override
	public long getPrimaryKey() {
		return _pushDictGroup.getPrimaryKey();
	}

	/**
	* Returns the push dict group ID of this push dict group.
	*
	* @return the push dict group ID of this push dict group
	*/
	@Override
	public long getPushDictGroupId() {
		return _pushDictGroup.getPushDictGroupId();
	}

	/**
	* Returns the user ID of this push dict group.
	*
	* @return the user ID of this push dict group
	*/
	@Override
	public long getUserId() {
		return _pushDictGroup.getUserId();
	}

	@Override
	public void persist() {
		_pushDictGroup.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_pushDictGroup.setCachedModel(cachedModel);
	}

	/**
	* Sets the collection code of this push dict group.
	*
	* @param collectionCode the collection code of this push dict group
	*/
	@Override
	public void setCollectionCode(java.lang.String collectionCode) {
		_pushDictGroup.setCollectionCode(collectionCode);
	}

	/**
	* Sets the company ID of this push dict group.
	*
	* @param companyId the company ID of this push dict group
	*/
	@Override
	public void setCompanyId(long companyId) {
		_pushDictGroup.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this push dict group.
	*
	* @param createDate the create date of this push dict group
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_pushDictGroup.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_pushDictGroup.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_pushDictGroup.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_pushDictGroup.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group code of this push dict group.
	*
	* @param groupCode the group code of this push dict group
	*/
	@Override
	public void setGroupCode(java.lang.String groupCode) {
		_pushDictGroup.setGroupCode(groupCode);
	}

	/**
	* Sets the group description of this push dict group.
	*
	* @param groupDescription the group description of this push dict group
	*/
	@Override
	public void setGroupDescription(java.lang.String groupDescription) {
		_pushDictGroup.setGroupDescription(groupDescription);
	}

	/**
	* Sets the group ID of this push dict group.
	*
	* @param groupId the group ID of this push dict group
	*/
	@Override
	public void setGroupId(long groupId) {
		_pushDictGroup.setGroupId(groupId);
	}

	/**
	* Sets the group name of this push dict group.
	*
	* @param groupName the group name of this push dict group
	*/
	@Override
	public void setGroupName(java.lang.String groupName) {
		_pushDictGroup.setGroupName(groupName);
	}

	/**
	* Sets the group name en of this push dict group.
	*
	* @param groupNameEN the group name en of this push dict group
	*/
	@Override
	public void setGroupNameEN(java.lang.String groupNameEN) {
		_pushDictGroup.setGroupNameEN(groupNameEN);
	}

	/**
	* Sets the item code of this push dict group.
	*
	* @param itemCode the item code of this push dict group
	*/
	@Override
	public void setItemCode(java.lang.String itemCode) {
		_pushDictGroup.setItemCode(itemCode);
	}

	/**
	* Sets the method of this push dict group.
	*
	* @param method the method of this push dict group
	*/
	@Override
	public void setMethod(java.lang.String method) {
		_pushDictGroup.setMethod(method);
	}

	/**
	* Sets the modified date of this push dict group.
	*
	* @param modifiedDate the modified date of this push dict group
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_pushDictGroup.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_pushDictGroup.setNew(n);
	}

	/**
	* Sets the primary key of this push dict group.
	*
	* @param primaryKey the primary key of this push dict group
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_pushDictGroup.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_pushDictGroup.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the push dict group ID of this push dict group.
	*
	* @param pushDictGroupId the push dict group ID of this push dict group
	*/
	@Override
	public void setPushDictGroupId(long pushDictGroupId) {
		_pushDictGroup.setPushDictGroupId(pushDictGroupId);
	}

	/**
	* Sets the server no of this push dict group.
	*
	* @param serverNo the server no of this push dict group
	*/
	@Override
	public void setServerNo(java.lang.String serverNo) {
		_pushDictGroup.setServerNo(serverNo);
	}

	/**
	* Sets the user ID of this push dict group.
	*
	* @param userId the user ID of this push dict group
	*/
	@Override
	public void setUserId(long userId) {
		_pushDictGroup.setUserId(userId);
	}

	/**
	* Sets the user name of this push dict group.
	*
	* @param userName the user name of this push dict group
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_pushDictGroup.setUserName(userName);
	}

	/**
	* Sets the user uuid of this push dict group.
	*
	* @param userUuid the user uuid of this push dict group
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_pushDictGroup.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this push dict group.
	*
	* @param uuid the uuid of this push dict group
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_pushDictGroup.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PushDictGroupWrapper)) {
			return false;
		}

		PushDictGroupWrapper pushDictGroupWrapper = (PushDictGroupWrapper)obj;

		if (Objects.equals(_pushDictGroup, pushDictGroupWrapper._pushDictGroup)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _pushDictGroup.getStagedModelType();
	}

	@Override
	public PushDictGroup getWrappedModel() {
		return _pushDictGroup;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _pushDictGroup.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _pushDictGroup.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_pushDictGroup.resetOriginalValues();
	}

	private final PushDictGroup _pushDictGroup;
}