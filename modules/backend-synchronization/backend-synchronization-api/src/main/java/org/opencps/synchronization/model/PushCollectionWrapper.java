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
 * This class is a wrapper for {@link PushCollection}.
 * </p>
 *
 * @author trungdk
 * @see PushCollection
 * @generated
 */
@ProviderType
public class PushCollectionWrapper implements PushCollection,
	ModelWrapper<PushCollection> {
	public PushCollectionWrapper(PushCollection pushCollection) {
		_pushCollection = pushCollection;
	}

	@Override
	public Class<?> getModelClass() {
		return PushCollection.class;
	}

	@Override
	public String getModelClassName() {
		return PushCollection.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("pushCollectionId", getPushCollectionId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("serverNo", getServerNo());
		attributes.put("collectionCode", getCollectionCode());
		attributes.put("collectionName", getCollectionName());
		attributes.put("collectionNameEN", getCollectionNameEN());
		attributes.put("description", getDescription());
		attributes.put("dataForm", getDataForm());
		attributes.put("method", getMethod());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long pushCollectionId = (Long)attributes.get("pushCollectionId");

		if (pushCollectionId != null) {
			setPushCollectionId(pushCollectionId);
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

		String method = (String)attributes.get("method");

		if (method != null) {
			setMethod(method);
		}
	}

	@Override
	public PushCollection toEscapedModel() {
		return new PushCollectionWrapper(_pushCollection.toEscapedModel());
	}

	@Override
	public PushCollection toUnescapedModel() {
		return new PushCollectionWrapper(_pushCollection.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _pushCollection.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _pushCollection.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _pushCollection.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _pushCollection.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<PushCollection> toCacheModel() {
		return _pushCollection.toCacheModel();
	}

	@Override
	public int compareTo(PushCollection pushCollection) {
		return _pushCollection.compareTo(pushCollection);
	}

	@Override
	public int hashCode() {
		return _pushCollection.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _pushCollection.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new PushCollectionWrapper((PushCollection)_pushCollection.clone());
	}

	/**
	* Returns the collection code of this push collection.
	*
	* @return the collection code of this push collection
	*/
	@Override
	public java.lang.String getCollectionCode() {
		return _pushCollection.getCollectionCode();
	}

	/**
	* Returns the collection name of this push collection.
	*
	* @return the collection name of this push collection
	*/
	@Override
	public java.lang.String getCollectionName() {
		return _pushCollection.getCollectionName();
	}

	/**
	* Returns the collection name en of this push collection.
	*
	* @return the collection name en of this push collection
	*/
	@Override
	public java.lang.String getCollectionNameEN() {
		return _pushCollection.getCollectionNameEN();
	}

	/**
	* Returns the data form of this push collection.
	*
	* @return the data form of this push collection
	*/
	@Override
	public java.lang.String getDataForm() {
		return _pushCollection.getDataForm();
	}

	/**
	* Returns the description of this push collection.
	*
	* @return the description of this push collection
	*/
	@Override
	public java.lang.String getDescription() {
		return _pushCollection.getDescription();
	}

	/**
	* Returns the method of this push collection.
	*
	* @return the method of this push collection
	*/
	@Override
	public java.lang.String getMethod() {
		return _pushCollection.getMethod();
	}

	/**
	* Returns the server no of this push collection.
	*
	* @return the server no of this push collection
	*/
	@Override
	public java.lang.String getServerNo() {
		return _pushCollection.getServerNo();
	}

	/**
	* Returns the user name of this push collection.
	*
	* @return the user name of this push collection
	*/
	@Override
	public java.lang.String getUserName() {
		return _pushCollection.getUserName();
	}

	/**
	* Returns the user uuid of this push collection.
	*
	* @return the user uuid of this push collection
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _pushCollection.getUserUuid();
	}

	/**
	* Returns the uuid of this push collection.
	*
	* @return the uuid of this push collection
	*/
	@Override
	public java.lang.String getUuid() {
		return _pushCollection.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _pushCollection.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _pushCollection.toXmlString();
	}

	/**
	* Returns the create date of this push collection.
	*
	* @return the create date of this push collection
	*/
	@Override
	public Date getCreateDate() {
		return _pushCollection.getCreateDate();
	}

	/**
	* Returns the modified date of this push collection.
	*
	* @return the modified date of this push collection
	*/
	@Override
	public Date getModifiedDate() {
		return _pushCollection.getModifiedDate();
	}

	/**
	* Returns the company ID of this push collection.
	*
	* @return the company ID of this push collection
	*/
	@Override
	public long getCompanyId() {
		return _pushCollection.getCompanyId();
	}

	/**
	* Returns the group ID of this push collection.
	*
	* @return the group ID of this push collection
	*/
	@Override
	public long getGroupId() {
		return _pushCollection.getGroupId();
	}

	/**
	* Returns the primary key of this push collection.
	*
	* @return the primary key of this push collection
	*/
	@Override
	public long getPrimaryKey() {
		return _pushCollection.getPrimaryKey();
	}

	/**
	* Returns the push collection ID of this push collection.
	*
	* @return the push collection ID of this push collection
	*/
	@Override
	public long getPushCollectionId() {
		return _pushCollection.getPushCollectionId();
	}

	/**
	* Returns the user ID of this push collection.
	*
	* @return the user ID of this push collection
	*/
	@Override
	public long getUserId() {
		return _pushCollection.getUserId();
	}

	@Override
	public void persist() {
		_pushCollection.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_pushCollection.setCachedModel(cachedModel);
	}

	/**
	* Sets the collection code of this push collection.
	*
	* @param collectionCode the collection code of this push collection
	*/
	@Override
	public void setCollectionCode(java.lang.String collectionCode) {
		_pushCollection.setCollectionCode(collectionCode);
	}

	/**
	* Sets the collection name of this push collection.
	*
	* @param collectionName the collection name of this push collection
	*/
	@Override
	public void setCollectionName(java.lang.String collectionName) {
		_pushCollection.setCollectionName(collectionName);
	}

	/**
	* Sets the collection name en of this push collection.
	*
	* @param collectionNameEN the collection name en of this push collection
	*/
	@Override
	public void setCollectionNameEN(java.lang.String collectionNameEN) {
		_pushCollection.setCollectionNameEN(collectionNameEN);
	}

	/**
	* Sets the company ID of this push collection.
	*
	* @param companyId the company ID of this push collection
	*/
	@Override
	public void setCompanyId(long companyId) {
		_pushCollection.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this push collection.
	*
	* @param createDate the create date of this push collection
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_pushCollection.setCreateDate(createDate);
	}

	/**
	* Sets the data form of this push collection.
	*
	* @param dataForm the data form of this push collection
	*/
	@Override
	public void setDataForm(java.lang.String dataForm) {
		_pushCollection.setDataForm(dataForm);
	}

	/**
	* Sets the description of this push collection.
	*
	* @param description the description of this push collection
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_pushCollection.setDescription(description);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_pushCollection.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_pushCollection.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_pushCollection.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this push collection.
	*
	* @param groupId the group ID of this push collection
	*/
	@Override
	public void setGroupId(long groupId) {
		_pushCollection.setGroupId(groupId);
	}

	/**
	* Sets the method of this push collection.
	*
	* @param method the method of this push collection
	*/
	@Override
	public void setMethod(java.lang.String method) {
		_pushCollection.setMethod(method);
	}

	/**
	* Sets the modified date of this push collection.
	*
	* @param modifiedDate the modified date of this push collection
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_pushCollection.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_pushCollection.setNew(n);
	}

	/**
	* Sets the primary key of this push collection.
	*
	* @param primaryKey the primary key of this push collection
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_pushCollection.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_pushCollection.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the push collection ID of this push collection.
	*
	* @param pushCollectionId the push collection ID of this push collection
	*/
	@Override
	public void setPushCollectionId(long pushCollectionId) {
		_pushCollection.setPushCollectionId(pushCollectionId);
	}

	/**
	* Sets the server no of this push collection.
	*
	* @param serverNo the server no of this push collection
	*/
	@Override
	public void setServerNo(java.lang.String serverNo) {
		_pushCollection.setServerNo(serverNo);
	}

	/**
	* Sets the user ID of this push collection.
	*
	* @param userId the user ID of this push collection
	*/
	@Override
	public void setUserId(long userId) {
		_pushCollection.setUserId(userId);
	}

	/**
	* Sets the user name of this push collection.
	*
	* @param userName the user name of this push collection
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_pushCollection.setUserName(userName);
	}

	/**
	* Sets the user uuid of this push collection.
	*
	* @param userUuid the user uuid of this push collection
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_pushCollection.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this push collection.
	*
	* @param uuid the uuid of this push collection
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_pushCollection.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PushCollectionWrapper)) {
			return false;
		}

		PushCollectionWrapper pushCollectionWrapper = (PushCollectionWrapper)obj;

		if (Objects.equals(_pushCollection,
					pushCollectionWrapper._pushCollection)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _pushCollection.getStagedModelType();
	}

	@Override
	public PushCollection getWrappedModel() {
		return _pushCollection;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _pushCollection.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _pushCollection.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_pushCollection.resetOriginalValues();
	}

	private final PushCollection _pushCollection;
}