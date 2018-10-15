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
 * This class is a wrapper for {@link DictCollection}.
 * </p>
 *
 * @author khoavu
 * @see DictCollection
 * @generated
 */
@ProviderType
public class DictCollectionWrapper implements DictCollection,
	ModelWrapper<DictCollection> {
	public DictCollectionWrapper(DictCollection dictCollection) {
		_dictCollection = dictCollection;
	}

	@Override
	public Class<?> getModelClass() {
		return DictCollection.class;
	}

	@Override
	public String getModelClassName() {
		return DictCollection.class.getName();
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
	}

	@Override
	public Object clone() {
		return new DictCollectionWrapper((DictCollection)_dictCollection.clone());
	}

	@Override
	public int compareTo(DictCollection dictCollection) {
		return _dictCollection.compareTo(dictCollection);
	}

	/**
	* Returns the collection code of this dict collection.
	*
	* @return the collection code of this dict collection
	*/
	@Override
	public String getCollectionCode() {
		return _dictCollection.getCollectionCode();
	}

	/**
	* Returns the collection name of this dict collection.
	*
	* @return the collection name of this dict collection
	*/
	@Override
	public String getCollectionName() {
		return _dictCollection.getCollectionName();
	}

	/**
	* Returns the collection name en of this dict collection.
	*
	* @return the collection name en of this dict collection
	*/
	@Override
	public String getCollectionNameEN() {
		return _dictCollection.getCollectionNameEN();
	}

	/**
	* Returns the company ID of this dict collection.
	*
	* @return the company ID of this dict collection
	*/
	@Override
	public long getCompanyId() {
		return _dictCollection.getCompanyId();
	}

	/**
	* Returns the create date of this dict collection.
	*
	* @return the create date of this dict collection
	*/
	@Override
	public Date getCreateDate() {
		return _dictCollection.getCreateDate();
	}

	/**
	* Returns the data form of this dict collection.
	*
	* @return the data form of this dict collection
	*/
	@Override
	public String getDataForm() {
		return _dictCollection.getDataForm();
	}

	/**
	* Returns the description of this dict collection.
	*
	* @return the description of this dict collection
	*/
	@Override
	public String getDescription() {
		return _dictCollection.getDescription();
	}

	/**
	* Returns the dict collection ID of this dict collection.
	*
	* @return the dict collection ID of this dict collection
	*/
	@Override
	public long getDictCollectionId() {
		return _dictCollection.getDictCollectionId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _dictCollection.getExpandoBridge();
	}

	/**
	* Returns the group ID of this dict collection.
	*
	* @return the group ID of this dict collection
	*/
	@Override
	public long getGroupId() {
		return _dictCollection.getGroupId();
	}

	/**
	* Returns the modified date of this dict collection.
	*
	* @return the modified date of this dict collection
	*/
	@Override
	public Date getModifiedDate() {
		return _dictCollection.getModifiedDate();
	}

	/**
	* Returns the primary key of this dict collection.
	*
	* @return the primary key of this dict collection
	*/
	@Override
	public long getPrimaryKey() {
		return _dictCollection.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _dictCollection.getPrimaryKeyObj();
	}

	/**
	* Returns the status of this dict collection.
	*
	* @return the status of this dict collection
	*/
	@Override
	public int getStatus() {
		return _dictCollection.getStatus();
	}

	/**
	* Returns the user ID of this dict collection.
	*
	* @return the user ID of this dict collection
	*/
	@Override
	public long getUserId() {
		return _dictCollection.getUserId();
	}

	/**
	* Returns the user name of this dict collection.
	*
	* @return the user name of this dict collection
	*/
	@Override
	public String getUserName() {
		return _dictCollection.getUserName();
	}

	/**
	* Returns the user uuid of this dict collection.
	*
	* @return the user uuid of this dict collection
	*/
	@Override
	public String getUserUuid() {
		return _dictCollection.getUserUuid();
	}

	/**
	* Returns the uuid of this dict collection.
	*
	* @return the uuid of this dict collection
	*/
	@Override
	public String getUuid() {
		return _dictCollection.getUuid();
	}

	@Override
	public int hashCode() {
		return _dictCollection.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _dictCollection.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _dictCollection.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _dictCollection.isNew();
	}

	@Override
	public void persist() {
		_dictCollection.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_dictCollection.setCachedModel(cachedModel);
	}

	/**
	* Sets the collection code of this dict collection.
	*
	* @param collectionCode the collection code of this dict collection
	*/
	@Override
	public void setCollectionCode(String collectionCode) {
		_dictCollection.setCollectionCode(collectionCode);
	}

	/**
	* Sets the collection name of this dict collection.
	*
	* @param collectionName the collection name of this dict collection
	*/
	@Override
	public void setCollectionName(String collectionName) {
		_dictCollection.setCollectionName(collectionName);
	}

	/**
	* Sets the collection name en of this dict collection.
	*
	* @param collectionNameEN the collection name en of this dict collection
	*/
	@Override
	public void setCollectionNameEN(String collectionNameEN) {
		_dictCollection.setCollectionNameEN(collectionNameEN);
	}

	/**
	* Sets the company ID of this dict collection.
	*
	* @param companyId the company ID of this dict collection
	*/
	@Override
	public void setCompanyId(long companyId) {
		_dictCollection.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this dict collection.
	*
	* @param createDate the create date of this dict collection
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_dictCollection.setCreateDate(createDate);
	}

	/**
	* Sets the data form of this dict collection.
	*
	* @param dataForm the data form of this dict collection
	*/
	@Override
	public void setDataForm(String dataForm) {
		_dictCollection.setDataForm(dataForm);
	}

	/**
	* Sets the description of this dict collection.
	*
	* @param description the description of this dict collection
	*/
	@Override
	public void setDescription(String description) {
		_dictCollection.setDescription(description);
	}

	/**
	* Sets the dict collection ID of this dict collection.
	*
	* @param dictCollectionId the dict collection ID of this dict collection
	*/
	@Override
	public void setDictCollectionId(long dictCollectionId) {
		_dictCollection.setDictCollectionId(dictCollectionId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_dictCollection.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_dictCollection.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_dictCollection.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this dict collection.
	*
	* @param groupId the group ID of this dict collection
	*/
	@Override
	public void setGroupId(long groupId) {
		_dictCollection.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this dict collection.
	*
	* @param modifiedDate the modified date of this dict collection
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_dictCollection.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_dictCollection.setNew(n);
	}

	/**
	* Sets the primary key of this dict collection.
	*
	* @param primaryKey the primary key of this dict collection
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_dictCollection.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_dictCollection.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the status of this dict collection.
	*
	* @param status the status of this dict collection
	*/
	@Override
	public void setStatus(int status) {
		_dictCollection.setStatus(status);
	}

	/**
	* Sets the user ID of this dict collection.
	*
	* @param userId the user ID of this dict collection
	*/
	@Override
	public void setUserId(long userId) {
		_dictCollection.setUserId(userId);
	}

	/**
	* Sets the user name of this dict collection.
	*
	* @param userName the user name of this dict collection
	*/
	@Override
	public void setUserName(String userName) {
		_dictCollection.setUserName(userName);
	}

	/**
	* Sets the user uuid of this dict collection.
	*
	* @param userUuid the user uuid of this dict collection
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_dictCollection.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this dict collection.
	*
	* @param uuid the uuid of this dict collection
	*/
	@Override
	public void setUuid(String uuid) {
		_dictCollection.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<DictCollection> toCacheModel() {
		return _dictCollection.toCacheModel();
	}

	@Override
	public DictCollection toEscapedModel() {
		return new DictCollectionWrapper(_dictCollection.toEscapedModel());
	}

	@Override
	public String toString() {
		return _dictCollection.toString();
	}

	@Override
	public DictCollection toUnescapedModel() {
		return new DictCollectionWrapper(_dictCollection.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _dictCollection.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DictCollectionWrapper)) {
			return false;
		}

		DictCollectionWrapper dictCollectionWrapper = (DictCollectionWrapper)obj;

		if (Objects.equals(_dictCollection,
					dictCollectionWrapper._dictCollection)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _dictCollection.getStagedModelType();
	}

	@Override
	public DictCollection getWrappedModel() {
		return _dictCollection;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _dictCollection.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _dictCollection.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_dictCollection.resetOriginalValues();
	}

	private final DictCollection _dictCollection;
}