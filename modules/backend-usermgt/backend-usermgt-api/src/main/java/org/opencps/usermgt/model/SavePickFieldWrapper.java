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

package org.opencps.usermgt.model;

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
 * This class is a wrapper for {@link SavePickField}.
 * </p>
 *
 * @author khoavu
 * @see SavePickField
 * @generated
 */
@ProviderType
public class SavePickFieldWrapper implements SavePickField,
	ModelWrapper<SavePickField> {
	public SavePickFieldWrapper(SavePickField savePickField) {
		_savePickField = savePickField;
	}

	@Override
	public Class<?> getModelClass() {
		return SavePickField.class;
	}

	@Override
	public String getModelClassName() {
		return SavePickField.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("fieldPickId", getFieldPickId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("formData", getFormData());
		attributes.put("classPK", getClassPK());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long fieldPickId = (Long)attributes.get("fieldPickId");

		if (fieldPickId != null) {
			setFieldPickId(fieldPickId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String formData = (String)attributes.get("formData");

		if (formData != null) {
			setFormData(formData);
		}

		String classPK = (String)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}
	}

	@Override
	public Object clone() {
		return new SavePickFieldWrapper((SavePickField)_savePickField.clone());
	}

	@Override
	public int compareTo(SavePickField savePickField) {
		return _savePickField.compareTo(savePickField);
	}

	/**
	* Returns the class pk of this save pick field.
	*
	* @return the class pk of this save pick field
	*/
	@Override
	public String getClassPK() {
		return _savePickField.getClassPK();
	}

	/**
	* Returns the create date of this save pick field.
	*
	* @return the create date of this save pick field
	*/
	@Override
	public Date getCreateDate() {
		return _savePickField.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _savePickField.getExpandoBridge();
	}

	/**
	* Returns the field pick ID of this save pick field.
	*
	* @return the field pick ID of this save pick field
	*/
	@Override
	public long getFieldPickId() {
		return _savePickField.getFieldPickId();
	}

	/**
	* Returns the form data of this save pick field.
	*
	* @return the form data of this save pick field
	*/
	@Override
	public String getFormData() {
		return _savePickField.getFormData();
	}

	/**
	* Returns the group ID of this save pick field.
	*
	* @return the group ID of this save pick field
	*/
	@Override
	public long getGroupId() {
		return _savePickField.getGroupId();
	}

	/**
	* Returns the modified date of this save pick field.
	*
	* @return the modified date of this save pick field
	*/
	@Override
	public Date getModifiedDate() {
		return _savePickField.getModifiedDate();
	}

	/**
	* Returns the primary key of this save pick field.
	*
	* @return the primary key of this save pick field
	*/
	@Override
	public long getPrimaryKey() {
		return _savePickField.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _savePickField.getPrimaryKeyObj();
	}

	/**
	* Returns the user ID of this save pick field.
	*
	* @return the user ID of this save pick field
	*/
	@Override
	public long getUserId() {
		return _savePickField.getUserId();
	}

	/**
	* Returns the user uuid of this save pick field.
	*
	* @return the user uuid of this save pick field
	*/
	@Override
	public String getUserUuid() {
		return _savePickField.getUserUuid();
	}

	/**
	* Returns the uuid of this save pick field.
	*
	* @return the uuid of this save pick field
	*/
	@Override
	public String getUuid() {
		return _savePickField.getUuid();
	}

	@Override
	public int hashCode() {
		return _savePickField.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _savePickField.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _savePickField.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _savePickField.isNew();
	}

	@Override
	public void persist() {
		_savePickField.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_savePickField.setCachedModel(cachedModel);
	}

	/**
	* Sets the class pk of this save pick field.
	*
	* @param classPK the class pk of this save pick field
	*/
	@Override
	public void setClassPK(String classPK) {
		_savePickField.setClassPK(classPK);
	}

	/**
	* Sets the create date of this save pick field.
	*
	* @param createDate the create date of this save pick field
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_savePickField.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_savePickField.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_savePickField.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_savePickField.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the field pick ID of this save pick field.
	*
	* @param fieldPickId the field pick ID of this save pick field
	*/
	@Override
	public void setFieldPickId(long fieldPickId) {
		_savePickField.setFieldPickId(fieldPickId);
	}

	/**
	* Sets the form data of this save pick field.
	*
	* @param formData the form data of this save pick field
	*/
	@Override
	public void setFormData(String formData) {
		_savePickField.setFormData(formData);
	}

	/**
	* Sets the group ID of this save pick field.
	*
	* @param groupId the group ID of this save pick field
	*/
	@Override
	public void setGroupId(long groupId) {
		_savePickField.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this save pick field.
	*
	* @param modifiedDate the modified date of this save pick field
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_savePickField.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_savePickField.setNew(n);
	}

	/**
	* Sets the primary key of this save pick field.
	*
	* @param primaryKey the primary key of this save pick field
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_savePickField.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_savePickField.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this save pick field.
	*
	* @param userId the user ID of this save pick field
	*/
	@Override
	public void setUserId(long userId) {
		_savePickField.setUserId(userId);
	}

	/**
	* Sets the user uuid of this save pick field.
	*
	* @param userUuid the user uuid of this save pick field
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_savePickField.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this save pick field.
	*
	* @param uuid the uuid of this save pick field
	*/
	@Override
	public void setUuid(String uuid) {
		_savePickField.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<SavePickField> toCacheModel() {
		return _savePickField.toCacheModel();
	}

	@Override
	public SavePickField toEscapedModel() {
		return new SavePickFieldWrapper(_savePickField.toEscapedModel());
	}

	@Override
	public String toString() {
		return _savePickField.toString();
	}

	@Override
	public SavePickField toUnescapedModel() {
		return new SavePickFieldWrapper(_savePickField.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _savePickField.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SavePickFieldWrapper)) {
			return false;
		}

		SavePickFieldWrapper savePickFieldWrapper = (SavePickFieldWrapper)obj;

		if (Objects.equals(_savePickField, savePickFieldWrapper._savePickField)) {
			return true;
		}

		return false;
	}

	@Override
	public SavePickField getWrappedModel() {
		return _savePickField;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _savePickField.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _savePickField.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_savePickField.resetOriginalValues();
	}

	private final SavePickField _savePickField;
}