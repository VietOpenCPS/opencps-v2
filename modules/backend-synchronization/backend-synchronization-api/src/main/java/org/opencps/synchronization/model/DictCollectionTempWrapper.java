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
 * This class is a wrapper for {@link DictCollectionTemp}.
 * </p>
 *
 * @author trungdk
 * @see DictCollectionTemp
 * @generated
 */
@ProviderType
public class DictCollectionTempWrapper implements DictCollectionTemp,
	ModelWrapper<DictCollectionTemp> {
	public DictCollectionTempWrapper(DictCollectionTemp dictCollectionTemp) {
		_dictCollectionTemp = dictCollectionTemp;
	}

	@Override
	public Class<?> getModelClass() {
		return DictCollectionTemp.class;
	}

	@Override
	public String getModelClassName() {
		return DictCollectionTemp.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("dictCollectionId", getDictCollectionId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("collectionCode", getCollectionCode());
		attributes.put("collectionName", getCollectionName());
		attributes.put("collectionNameEN", getCollectionNameEN());
		attributes.put("description", getDescription());
		attributes.put("dataForm", getDataForm());
		attributes.put("status", getStatus());
		attributes.put("mustSync", getMustSync());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long dictCollectionId = (Long)attributes.get("dictCollectionId");

		if (dictCollectionId != null) {
			setDictCollectionId(dictCollectionId);
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

		String collectionCode = (String)attributes.get("collectionCode");

		if (collectionCode != null) {
			setCollectionCode(collectionCode);
		}

		String collectionName = (String)attributes.get("collectionName");

		if (collectionName != null) {
			setCollectionName(collectionName);
		}

		String collectionNameEN = (String)attributes.get("collectionNameEN");

		if (collectionNameEN != null) {
			setCollectionNameEN(collectionNameEN);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String dataForm = (String)attributes.get("dataForm");

		if (dataForm != null) {
			setDataForm(dataForm);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Integer mustSync = (Integer)attributes.get("mustSync");

		if (mustSync != null) {
			setMustSync(mustSync);
		}
	}

	@Override
	public Object clone() {
		return new DictCollectionTempWrapper((DictCollectionTemp)_dictCollectionTemp.clone());
	}

	@Override
	public int compareTo(DictCollectionTemp dictCollectionTemp) {
		return _dictCollectionTemp.compareTo(dictCollectionTemp);
	}

	/**
	* Returns the collection code of this dict collection temp.
	*
	* @return the collection code of this dict collection temp
	*/
	@Override
	public String getCollectionCode() {
		return _dictCollectionTemp.getCollectionCode();
	}

	/**
	* Returns the collection name of this dict collection temp.
	*
	* @return the collection name of this dict collection temp
	*/
	@Override
	public String getCollectionName() {
		return _dictCollectionTemp.getCollectionName();
	}

	/**
	* Returns the collection name en of this dict collection temp.
	*
	* @return the collection name en of this dict collection temp
	*/
	@Override
	public String getCollectionNameEN() {
		return _dictCollectionTemp.getCollectionNameEN();
	}

	/**
	* Returns the company ID of this dict collection temp.
	*
	* @return the company ID of this dict collection temp
	*/
	@Override
	public long getCompanyId() {
		return _dictCollectionTemp.getCompanyId();
	}

	/**
	* Returns the create date of this dict collection temp.
	*
	* @return the create date of this dict collection temp
	*/
	@Override
	public Date getCreateDate() {
		return _dictCollectionTemp.getCreateDate();
	}

	/**
	* Returns the data form of this dict collection temp.
	*
	* @return the data form of this dict collection temp
	*/
	@Override
	public String getDataForm() {
		return _dictCollectionTemp.getDataForm();
	}

	/**
	* Returns the description of this dict collection temp.
	*
	* @return the description of this dict collection temp
	*/
	@Override
	public String getDescription() {
		return _dictCollectionTemp.getDescription();
	}

	/**
	* Returns the dict collection ID of this dict collection temp.
	*
	* @return the dict collection ID of this dict collection temp
	*/
	@Override
	public long getDictCollectionId() {
		return _dictCollectionTemp.getDictCollectionId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _dictCollectionTemp.getExpandoBridge();
	}

	/**
	* Returns the group ID of this dict collection temp.
	*
	* @return the group ID of this dict collection temp
	*/
	@Override
	public long getGroupId() {
		return _dictCollectionTemp.getGroupId();
	}

	/**
	* Returns the modified date of this dict collection temp.
	*
	* @return the modified date of this dict collection temp
	*/
	@Override
	public Date getModifiedDate() {
		return _dictCollectionTemp.getModifiedDate();
	}

	/**
	* Returns the must sync of this dict collection temp.
	*
	* @return the must sync of this dict collection temp
	*/
	@Override
	public int getMustSync() {
		return _dictCollectionTemp.getMustSync();
	}

	/**
	* Returns the primary key of this dict collection temp.
	*
	* @return the primary key of this dict collection temp
	*/
	@Override
	public long getPrimaryKey() {
		return _dictCollectionTemp.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _dictCollectionTemp.getPrimaryKeyObj();
	}

	/**
	* Returns the status of this dict collection temp.
	*
	* @return the status of this dict collection temp
	*/
	@Override
	public int getStatus() {
		return _dictCollectionTemp.getStatus();
	}

	/**
	* Returns the user ID of this dict collection temp.
	*
	* @return the user ID of this dict collection temp
	*/
	@Override
	public long getUserId() {
		return _dictCollectionTemp.getUserId();
	}

	/**
	* Returns the user name of this dict collection temp.
	*
	* @return the user name of this dict collection temp
	*/
	@Override
	public String getUserName() {
		return _dictCollectionTemp.getUserName();
	}

	/**
	* Returns the user uuid of this dict collection temp.
	*
	* @return the user uuid of this dict collection temp
	*/
	@Override
	public String getUserUuid() {
		return _dictCollectionTemp.getUserUuid();
	}

	/**
	* Returns the uuid of this dict collection temp.
	*
	* @return the uuid of this dict collection temp
	*/
	@Override
	public String getUuid() {
		return _dictCollectionTemp.getUuid();
	}

	@Override
	public int hashCode() {
		return _dictCollectionTemp.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _dictCollectionTemp.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _dictCollectionTemp.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _dictCollectionTemp.isNew();
	}

	@Override
	public void persist() {
		_dictCollectionTemp.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_dictCollectionTemp.setCachedModel(cachedModel);
	}

	/**
	* Sets the collection code of this dict collection temp.
	*
	* @param collectionCode the collection code of this dict collection temp
	*/
	@Override
	public void setCollectionCode(String collectionCode) {
		_dictCollectionTemp.setCollectionCode(collectionCode);
	}

	/**
	* Sets the collection name of this dict collection temp.
	*
	* @param collectionName the collection name of this dict collection temp
	*/
	@Override
	public void setCollectionName(String collectionName) {
		_dictCollectionTemp.setCollectionName(collectionName);
	}

	/**
	* Sets the collection name en of this dict collection temp.
	*
	* @param collectionNameEN the collection name en of this dict collection temp
	*/
	@Override
	public void setCollectionNameEN(String collectionNameEN) {
		_dictCollectionTemp.setCollectionNameEN(collectionNameEN);
	}

	/**
	* Sets the company ID of this dict collection temp.
	*
	* @param companyId the company ID of this dict collection temp
	*/
	@Override
	public void setCompanyId(long companyId) {
		_dictCollectionTemp.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this dict collection temp.
	*
	* @param createDate the create date of this dict collection temp
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_dictCollectionTemp.setCreateDate(createDate);
	}

	/**
	* Sets the data form of this dict collection temp.
	*
	* @param dataForm the data form of this dict collection temp
	*/
	@Override
	public void setDataForm(String dataForm) {
		_dictCollectionTemp.setDataForm(dataForm);
	}

	/**
	* Sets the description of this dict collection temp.
	*
	* @param description the description of this dict collection temp
	*/
	@Override
	public void setDescription(String description) {
		_dictCollectionTemp.setDescription(description);
	}

	/**
	* Sets the dict collection ID of this dict collection temp.
	*
	* @param dictCollectionId the dict collection ID of this dict collection temp
	*/
	@Override
	public void setDictCollectionId(long dictCollectionId) {
		_dictCollectionTemp.setDictCollectionId(dictCollectionId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_dictCollectionTemp.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_dictCollectionTemp.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_dictCollectionTemp.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this dict collection temp.
	*
	* @param groupId the group ID of this dict collection temp
	*/
	@Override
	public void setGroupId(long groupId) {
		_dictCollectionTemp.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this dict collection temp.
	*
	* @param modifiedDate the modified date of this dict collection temp
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_dictCollectionTemp.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the must sync of this dict collection temp.
	*
	* @param mustSync the must sync of this dict collection temp
	*/
	@Override
	public void setMustSync(int mustSync) {
		_dictCollectionTemp.setMustSync(mustSync);
	}

	@Override
	public void setNew(boolean n) {
		_dictCollectionTemp.setNew(n);
	}

	/**
	* Sets the primary key of this dict collection temp.
	*
	* @param primaryKey the primary key of this dict collection temp
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_dictCollectionTemp.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_dictCollectionTemp.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the status of this dict collection temp.
	*
	* @param status the status of this dict collection temp
	*/
	@Override
	public void setStatus(int status) {
		_dictCollectionTemp.setStatus(status);
	}

	/**
	* Sets the user ID of this dict collection temp.
	*
	* @param userId the user ID of this dict collection temp
	*/
	@Override
	public void setUserId(long userId) {
		_dictCollectionTemp.setUserId(userId);
	}

	/**
	* Sets the user name of this dict collection temp.
	*
	* @param userName the user name of this dict collection temp
	*/
	@Override
	public void setUserName(String userName) {
		_dictCollectionTemp.setUserName(userName);
	}

	/**
	* Sets the user uuid of this dict collection temp.
	*
	* @param userUuid the user uuid of this dict collection temp
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_dictCollectionTemp.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this dict collection temp.
	*
	* @param uuid the uuid of this dict collection temp
	*/
	@Override
	public void setUuid(String uuid) {
		_dictCollectionTemp.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<DictCollectionTemp> toCacheModel() {
		return _dictCollectionTemp.toCacheModel();
	}

	@Override
	public DictCollectionTemp toEscapedModel() {
		return new DictCollectionTempWrapper(_dictCollectionTemp.toEscapedModel());
	}

	@Override
	public String toString() {
		return _dictCollectionTemp.toString();
	}

	@Override
	public DictCollectionTemp toUnescapedModel() {
		return new DictCollectionTempWrapper(_dictCollectionTemp.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _dictCollectionTemp.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DictCollectionTempWrapper)) {
			return false;
		}

		DictCollectionTempWrapper dictCollectionTempWrapper = (DictCollectionTempWrapper)obj;

		if (Objects.equals(_dictCollectionTemp,
					dictCollectionTempWrapper._dictCollectionTemp)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _dictCollectionTemp.getStagedModelType();
	}

	@Override
	public DictCollectionTemp getWrappedModel() {
		return _dictCollectionTemp;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _dictCollectionTemp.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _dictCollectionTemp.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_dictCollectionTemp.resetOriginalValues();
	}

	private final DictCollectionTemp _dictCollectionTemp;
}