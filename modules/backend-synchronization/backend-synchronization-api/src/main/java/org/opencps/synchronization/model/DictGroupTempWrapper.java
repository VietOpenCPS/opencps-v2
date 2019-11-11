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
 * This class is a wrapper for {@link DictGroupTemp}.
 * </p>
 *
 * @author trungdk
 * @see DictGroupTemp
 * @generated
 */
@ProviderType
public class DictGroupTempWrapper implements DictGroupTemp,
	ModelWrapper<DictGroupTemp> {
	public DictGroupTempWrapper(DictGroupTemp dictGroupTemp) {
		_dictGroupTemp = dictGroupTemp;
	}

	@Override
	public Class<?> getModelClass() {
		return DictGroupTemp.class;
	}

	@Override
	public String getModelClassName() {
		return DictGroupTemp.class.getName();
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
		attributes.put("status", getStatus());

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

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public Object clone() {
		return new DictGroupTempWrapper((DictGroupTemp)_dictGroupTemp.clone());
	}

	@Override
	public int compareTo(DictGroupTemp dictGroupTemp) {
		return _dictGroupTemp.compareTo(dictGroupTemp);
	}

	/**
	* Returns the company ID of this dict group temp.
	*
	* @return the company ID of this dict group temp
	*/
	@Override
	public long getCompanyId() {
		return _dictGroupTemp.getCompanyId();
	}

	/**
	* Returns the create date of this dict group temp.
	*
	* @return the create date of this dict group temp
	*/
	@Override
	public Date getCreateDate() {
		return _dictGroupTemp.getCreateDate();
	}

	/**
	* Returns the dict collection ID of this dict group temp.
	*
	* @return the dict collection ID of this dict group temp
	*/
	@Override
	public long getDictCollectionId() {
		return _dictGroupTemp.getDictCollectionId();
	}

	/**
	* Returns the dict group ID of this dict group temp.
	*
	* @return the dict group ID of this dict group temp
	*/
	@Override
	public long getDictGroupId() {
		return _dictGroupTemp.getDictGroupId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _dictGroupTemp.getExpandoBridge();
	}

	/**
	* Returns the group code of this dict group temp.
	*
	* @return the group code of this dict group temp
	*/
	@Override
	public String getGroupCode() {
		return _dictGroupTemp.getGroupCode();
	}

	/**
	* Returns the group description of this dict group temp.
	*
	* @return the group description of this dict group temp
	*/
	@Override
	public String getGroupDescription() {
		return _dictGroupTemp.getGroupDescription();
	}

	/**
	* Returns the group ID of this dict group temp.
	*
	* @return the group ID of this dict group temp
	*/
	@Override
	public long getGroupId() {
		return _dictGroupTemp.getGroupId();
	}

	/**
	* Returns the group name of this dict group temp.
	*
	* @return the group name of this dict group temp
	*/
	@Override
	public String getGroupName() {
		return _dictGroupTemp.getGroupName();
	}

	/**
	* Returns the group name en of this dict group temp.
	*
	* @return the group name en of this dict group temp
	*/
	@Override
	public String getGroupNameEN() {
		return _dictGroupTemp.getGroupNameEN();
	}

	/**
	* Returns the modified date of this dict group temp.
	*
	* @return the modified date of this dict group temp
	*/
	@Override
	public Date getModifiedDate() {
		return _dictGroupTemp.getModifiedDate();
	}

	/**
	* Returns the primary key of this dict group temp.
	*
	* @return the primary key of this dict group temp
	*/
	@Override
	public long getPrimaryKey() {
		return _dictGroupTemp.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _dictGroupTemp.getPrimaryKeyObj();
	}

	/**
	* Returns the status of this dict group temp.
	*
	* @return the status of this dict group temp
	*/
	@Override
	public int getStatus() {
		return _dictGroupTemp.getStatus();
	}

	/**
	* Returns the user ID of this dict group temp.
	*
	* @return the user ID of this dict group temp
	*/
	@Override
	public long getUserId() {
		return _dictGroupTemp.getUserId();
	}

	/**
	* Returns the user name of this dict group temp.
	*
	* @return the user name of this dict group temp
	*/
	@Override
	public String getUserName() {
		return _dictGroupTemp.getUserName();
	}

	/**
	* Returns the user uuid of this dict group temp.
	*
	* @return the user uuid of this dict group temp
	*/
	@Override
	public String getUserUuid() {
		return _dictGroupTemp.getUserUuid();
	}

	/**
	* Returns the uuid of this dict group temp.
	*
	* @return the uuid of this dict group temp
	*/
	@Override
	public String getUuid() {
		return _dictGroupTemp.getUuid();
	}

	@Override
	public int hashCode() {
		return _dictGroupTemp.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _dictGroupTemp.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _dictGroupTemp.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _dictGroupTemp.isNew();
	}

	@Override
	public void persist() {
		_dictGroupTemp.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_dictGroupTemp.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this dict group temp.
	*
	* @param companyId the company ID of this dict group temp
	*/
	@Override
	public void setCompanyId(long companyId) {
		_dictGroupTemp.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this dict group temp.
	*
	* @param createDate the create date of this dict group temp
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_dictGroupTemp.setCreateDate(createDate);
	}

	/**
	* Sets the dict collection ID of this dict group temp.
	*
	* @param dictCollectionId the dict collection ID of this dict group temp
	*/
	@Override
	public void setDictCollectionId(long dictCollectionId) {
		_dictGroupTemp.setDictCollectionId(dictCollectionId);
	}

	/**
	* Sets the dict group ID of this dict group temp.
	*
	* @param dictGroupId the dict group ID of this dict group temp
	*/
	@Override
	public void setDictGroupId(long dictGroupId) {
		_dictGroupTemp.setDictGroupId(dictGroupId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_dictGroupTemp.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_dictGroupTemp.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_dictGroupTemp.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group code of this dict group temp.
	*
	* @param groupCode the group code of this dict group temp
	*/
	@Override
	public void setGroupCode(String groupCode) {
		_dictGroupTemp.setGroupCode(groupCode);
	}

	/**
	* Sets the group description of this dict group temp.
	*
	* @param groupDescription the group description of this dict group temp
	*/
	@Override
	public void setGroupDescription(String groupDescription) {
		_dictGroupTemp.setGroupDescription(groupDescription);
	}

	/**
	* Sets the group ID of this dict group temp.
	*
	* @param groupId the group ID of this dict group temp
	*/
	@Override
	public void setGroupId(long groupId) {
		_dictGroupTemp.setGroupId(groupId);
	}

	/**
	* Sets the group name of this dict group temp.
	*
	* @param groupName the group name of this dict group temp
	*/
	@Override
	public void setGroupName(String groupName) {
		_dictGroupTemp.setGroupName(groupName);
	}

	/**
	* Sets the group name en of this dict group temp.
	*
	* @param groupNameEN the group name en of this dict group temp
	*/
	@Override
	public void setGroupNameEN(String groupNameEN) {
		_dictGroupTemp.setGroupNameEN(groupNameEN);
	}

	/**
	* Sets the modified date of this dict group temp.
	*
	* @param modifiedDate the modified date of this dict group temp
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_dictGroupTemp.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_dictGroupTemp.setNew(n);
	}

	/**
	* Sets the primary key of this dict group temp.
	*
	* @param primaryKey the primary key of this dict group temp
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_dictGroupTemp.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_dictGroupTemp.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the status of this dict group temp.
	*
	* @param status the status of this dict group temp
	*/
	@Override
	public void setStatus(int status) {
		_dictGroupTemp.setStatus(status);
	}

	/**
	* Sets the user ID of this dict group temp.
	*
	* @param userId the user ID of this dict group temp
	*/
	@Override
	public void setUserId(long userId) {
		_dictGroupTemp.setUserId(userId);
	}

	/**
	* Sets the user name of this dict group temp.
	*
	* @param userName the user name of this dict group temp
	*/
	@Override
	public void setUserName(String userName) {
		_dictGroupTemp.setUserName(userName);
	}

	/**
	* Sets the user uuid of this dict group temp.
	*
	* @param userUuid the user uuid of this dict group temp
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_dictGroupTemp.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this dict group temp.
	*
	* @param uuid the uuid of this dict group temp
	*/
	@Override
	public void setUuid(String uuid) {
		_dictGroupTemp.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<DictGroupTemp> toCacheModel() {
		return _dictGroupTemp.toCacheModel();
	}

	@Override
	public DictGroupTemp toEscapedModel() {
		return new DictGroupTempWrapper(_dictGroupTemp.toEscapedModel());
	}

	@Override
	public String toString() {
		return _dictGroupTemp.toString();
	}

	@Override
	public DictGroupTemp toUnescapedModel() {
		return new DictGroupTempWrapper(_dictGroupTemp.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _dictGroupTemp.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DictGroupTempWrapper)) {
			return false;
		}

		DictGroupTempWrapper dictGroupTempWrapper = (DictGroupTempWrapper)obj;

		if (Objects.equals(_dictGroupTemp, dictGroupTempWrapper._dictGroupTemp)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _dictGroupTemp.getStagedModelType();
	}

	@Override
	public DictGroupTemp getWrappedModel() {
		return _dictGroupTemp;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _dictGroupTemp.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _dictGroupTemp.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_dictGroupTemp.resetOriginalValues();
	}

	private final DictGroupTemp _dictGroupTemp;
}