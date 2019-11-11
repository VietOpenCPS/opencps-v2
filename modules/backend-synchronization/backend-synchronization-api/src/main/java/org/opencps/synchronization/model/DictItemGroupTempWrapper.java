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
 * This class is a wrapper for {@link DictItemGroupTemp}.
 * </p>
 *
 * @author trungdk
 * @see DictItemGroupTemp
 * @generated
 */
@ProviderType
public class DictItemGroupTempWrapper implements DictItemGroupTemp,
	ModelWrapper<DictItemGroupTemp> {
	public DictItemGroupTempWrapper(DictItemGroupTemp dictItemGroupTemp) {
		_dictItemGroupTemp = dictItemGroupTemp;
	}

	@Override
	public Class<?> getModelClass() {
		return DictItemGroupTemp.class;
	}

	@Override
	public String getModelClassName() {
		return DictItemGroupTemp.class.getName();
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
		attributes.put("dictGroupName", getDictGroupName());

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

		String dictGroupName = (String)attributes.get("dictGroupName");

		if (dictGroupName != null) {
			setDictGroupName(dictGroupName);
		}
	}

	@Override
	public Object clone() {
		return new DictItemGroupTempWrapper((DictItemGroupTemp)_dictItemGroupTemp.clone());
	}

	@Override
	public int compareTo(DictItemGroupTemp dictItemGroupTemp) {
		return _dictItemGroupTemp.compareTo(dictItemGroupTemp);
	}

	/**
	* Returns the company ID of this dict item group temp.
	*
	* @return the company ID of this dict item group temp
	*/
	@Override
	public long getCompanyId() {
		return _dictItemGroupTemp.getCompanyId();
	}

	/**
	* Returns the create date of this dict item group temp.
	*
	* @return the create date of this dict item group temp
	*/
	@Override
	public Date getCreateDate() {
		return _dictItemGroupTemp.getCreateDate();
	}

	/**
	* Returns the dict group ID of this dict item group temp.
	*
	* @return the dict group ID of this dict item group temp
	*/
	@Override
	public long getDictGroupId() {
		return _dictItemGroupTemp.getDictGroupId();
	}

	/**
	* Returns the dict group name of this dict item group temp.
	*
	* @return the dict group name of this dict item group temp
	*/
	@Override
	public String getDictGroupName() {
		return _dictItemGroupTemp.getDictGroupName();
	}

	/**
	* Returns the dict item group ID of this dict item group temp.
	*
	* @return the dict item group ID of this dict item group temp
	*/
	@Override
	public long getDictItemGroupId() {
		return _dictItemGroupTemp.getDictItemGroupId();
	}

	/**
	* Returns the dict item ID of this dict item group temp.
	*
	* @return the dict item ID of this dict item group temp
	*/
	@Override
	public long getDictItemId() {
		return _dictItemGroupTemp.getDictItemId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _dictItemGroupTemp.getExpandoBridge();
	}

	/**
	* Returns the group ID of this dict item group temp.
	*
	* @return the group ID of this dict item group temp
	*/
	@Override
	public long getGroupId() {
		return _dictItemGroupTemp.getGroupId();
	}

	/**
	* Returns the modified date of this dict item group temp.
	*
	* @return the modified date of this dict item group temp
	*/
	@Override
	public Date getModifiedDate() {
		return _dictItemGroupTemp.getModifiedDate();
	}

	/**
	* Returns the primary key of this dict item group temp.
	*
	* @return the primary key of this dict item group temp
	*/
	@Override
	public long getPrimaryKey() {
		return _dictItemGroupTemp.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _dictItemGroupTemp.getPrimaryKeyObj();
	}

	/**
	* Returns the user ID of this dict item group temp.
	*
	* @return the user ID of this dict item group temp
	*/
	@Override
	public long getUserId() {
		return _dictItemGroupTemp.getUserId();
	}

	/**
	* Returns the user name of this dict item group temp.
	*
	* @return the user name of this dict item group temp
	*/
	@Override
	public String getUserName() {
		return _dictItemGroupTemp.getUserName();
	}

	/**
	* Returns the user uuid of this dict item group temp.
	*
	* @return the user uuid of this dict item group temp
	*/
	@Override
	public String getUserUuid() {
		return _dictItemGroupTemp.getUserUuid();
	}

	/**
	* Returns the uuid of this dict item group temp.
	*
	* @return the uuid of this dict item group temp
	*/
	@Override
	public String getUuid() {
		return _dictItemGroupTemp.getUuid();
	}

	@Override
	public int hashCode() {
		return _dictItemGroupTemp.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _dictItemGroupTemp.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _dictItemGroupTemp.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _dictItemGroupTemp.isNew();
	}

	@Override
	public void persist() {
		_dictItemGroupTemp.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_dictItemGroupTemp.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this dict item group temp.
	*
	* @param companyId the company ID of this dict item group temp
	*/
	@Override
	public void setCompanyId(long companyId) {
		_dictItemGroupTemp.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this dict item group temp.
	*
	* @param createDate the create date of this dict item group temp
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_dictItemGroupTemp.setCreateDate(createDate);
	}

	/**
	* Sets the dict group ID of this dict item group temp.
	*
	* @param dictGroupId the dict group ID of this dict item group temp
	*/
	@Override
	public void setDictGroupId(long dictGroupId) {
		_dictItemGroupTemp.setDictGroupId(dictGroupId);
	}

	/**
	* Sets the dict group name of this dict item group temp.
	*
	* @param dictGroupName the dict group name of this dict item group temp
	*/
	@Override
	public void setDictGroupName(String dictGroupName) {
		_dictItemGroupTemp.setDictGroupName(dictGroupName);
	}

	/**
	* Sets the dict item group ID of this dict item group temp.
	*
	* @param dictItemGroupId the dict item group ID of this dict item group temp
	*/
	@Override
	public void setDictItemGroupId(long dictItemGroupId) {
		_dictItemGroupTemp.setDictItemGroupId(dictItemGroupId);
	}

	/**
	* Sets the dict item ID of this dict item group temp.
	*
	* @param dictItemId the dict item ID of this dict item group temp
	*/
	@Override
	public void setDictItemId(long dictItemId) {
		_dictItemGroupTemp.setDictItemId(dictItemId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_dictItemGroupTemp.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_dictItemGroupTemp.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_dictItemGroupTemp.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this dict item group temp.
	*
	* @param groupId the group ID of this dict item group temp
	*/
	@Override
	public void setGroupId(long groupId) {
		_dictItemGroupTemp.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this dict item group temp.
	*
	* @param modifiedDate the modified date of this dict item group temp
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_dictItemGroupTemp.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_dictItemGroupTemp.setNew(n);
	}

	/**
	* Sets the primary key of this dict item group temp.
	*
	* @param primaryKey the primary key of this dict item group temp
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_dictItemGroupTemp.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_dictItemGroupTemp.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this dict item group temp.
	*
	* @param userId the user ID of this dict item group temp
	*/
	@Override
	public void setUserId(long userId) {
		_dictItemGroupTemp.setUserId(userId);
	}

	/**
	* Sets the user name of this dict item group temp.
	*
	* @param userName the user name of this dict item group temp
	*/
	@Override
	public void setUserName(String userName) {
		_dictItemGroupTemp.setUserName(userName);
	}

	/**
	* Sets the user uuid of this dict item group temp.
	*
	* @param userUuid the user uuid of this dict item group temp
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_dictItemGroupTemp.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this dict item group temp.
	*
	* @param uuid the uuid of this dict item group temp
	*/
	@Override
	public void setUuid(String uuid) {
		_dictItemGroupTemp.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<DictItemGroupTemp> toCacheModel() {
		return _dictItemGroupTemp.toCacheModel();
	}

	@Override
	public DictItemGroupTemp toEscapedModel() {
		return new DictItemGroupTempWrapper(_dictItemGroupTemp.toEscapedModel());
	}

	@Override
	public String toString() {
		return _dictItemGroupTemp.toString();
	}

	@Override
	public DictItemGroupTemp toUnescapedModel() {
		return new DictItemGroupTempWrapper(_dictItemGroupTemp.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _dictItemGroupTemp.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DictItemGroupTempWrapper)) {
			return false;
		}

		DictItemGroupTempWrapper dictItemGroupTempWrapper = (DictItemGroupTempWrapper)obj;

		if (Objects.equals(_dictItemGroupTemp,
					dictItemGroupTempWrapper._dictItemGroupTemp)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _dictItemGroupTemp.getStagedModelType();
	}

	@Override
	public DictItemGroupTemp getWrappedModel() {
		return _dictItemGroupTemp;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _dictItemGroupTemp.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _dictItemGroupTemp.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_dictItemGroupTemp.resetOriginalValues();
	}

	private final DictItemGroupTemp _dictItemGroupTemp;
}