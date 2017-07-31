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
 * This class is a wrapper for {@link Label}.
 * </p>
 *
 * @author Binhth
 * @see Label
 * @generated
 */
@ProviderType
public class LabelWrapper implements Label, ModelWrapper<Label> {
	public LabelWrapper(Label label) {
		_label = label;
	}

	@Override
	public Class<?> getModelClass() {
		return Label.class;
	}

	@Override
	public String getModelClassName() {
		return Label.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("labelId", getLabelId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("color", getColor());
		attributes.put("scope", getScope());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long labelId = (Long)attributes.get("labelId");

		if (labelId != null) {
			setLabelId(labelId);
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

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String color = (String)attributes.get("color");

		if (color != null) {
			setColor(color);
		}

		Integer scope = (Integer)attributes.get("scope");

		if (scope != null) {
			setScope(scope);
		}
	}

	@Override
	public Label toEscapedModel() {
		return new LabelWrapper(_label.toEscapedModel());
	}

	@Override
	public Label toUnescapedModel() {
		return new LabelWrapper(_label.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _label.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _label.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _label.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _label.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Label> toCacheModel() {
		return _label.toCacheModel();
	}

	@Override
	public int compareTo(Label label) {
		return _label.compareTo(label);
	}

	/**
	* Returns the scope of this label.
	*
	* @return the scope of this label
	*/
	@Override
	public int getScope() {
		return _label.getScope();
	}

	@Override
	public int hashCode() {
		return _label.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _label.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new LabelWrapper((Label)_label.clone());
	}

	/**
	* Returns the color of this label.
	*
	* @return the color of this label
	*/
	@Override
	public java.lang.String getColor() {
		return _label.getColor();
	}

	/**
	* Returns the name of this label.
	*
	* @return the name of this label
	*/
	@Override
	public java.lang.String getName() {
		return _label.getName();
	}

	/**
	* Returns the user name of this label.
	*
	* @return the user name of this label
	*/
	@Override
	public java.lang.String getUserName() {
		return _label.getUserName();
	}

	/**
	* Returns the user uuid of this label.
	*
	* @return the user uuid of this label
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _label.getUserUuid();
	}

	/**
	* Returns the uuid of this label.
	*
	* @return the uuid of this label
	*/
	@Override
	public java.lang.String getUuid() {
		return _label.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _label.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _label.toXmlString();
	}

	/**
	* Returns the create date of this label.
	*
	* @return the create date of this label
	*/
	@Override
	public Date getCreateDate() {
		return _label.getCreateDate();
	}

	/**
	* Returns the modified date of this label.
	*
	* @return the modified date of this label
	*/
	@Override
	public Date getModifiedDate() {
		return _label.getModifiedDate();
	}

	/**
	* Returns the company ID of this label.
	*
	* @return the company ID of this label
	*/
	@Override
	public long getCompanyId() {
		return _label.getCompanyId();
	}

	/**
	* Returns the group ID of this label.
	*
	* @return the group ID of this label
	*/
	@Override
	public long getGroupId() {
		return _label.getGroupId();
	}

	/**
	* Returns the label ID of this label.
	*
	* @return the label ID of this label
	*/
	@Override
	public long getLabelId() {
		return _label.getLabelId();
	}

	/**
	* Returns the primary key of this label.
	*
	* @return the primary key of this label
	*/
	@Override
	public long getPrimaryKey() {
		return _label.getPrimaryKey();
	}

	/**
	* Returns the user ID of this label.
	*
	* @return the user ID of this label
	*/
	@Override
	public long getUserId() {
		return _label.getUserId();
	}

	@Override
	public void persist() {
		_label.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_label.setCachedModel(cachedModel);
	}

	/**
	* Sets the color of this label.
	*
	* @param color the color of this label
	*/
	@Override
	public void setColor(java.lang.String color) {
		_label.setColor(color);
	}

	/**
	* Sets the company ID of this label.
	*
	* @param companyId the company ID of this label
	*/
	@Override
	public void setCompanyId(long companyId) {
		_label.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this label.
	*
	* @param createDate the create date of this label
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_label.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_label.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_label.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_label.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this label.
	*
	* @param groupId the group ID of this label
	*/
	@Override
	public void setGroupId(long groupId) {
		_label.setGroupId(groupId);
	}

	/**
	* Sets the label ID of this label.
	*
	* @param labelId the label ID of this label
	*/
	@Override
	public void setLabelId(long labelId) {
		_label.setLabelId(labelId);
	}

	/**
	* Sets the modified date of this label.
	*
	* @param modifiedDate the modified date of this label
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_label.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this label.
	*
	* @param name the name of this label
	*/
	@Override
	public void setName(java.lang.String name) {
		_label.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_label.setNew(n);
	}

	/**
	* Sets the primary key of this label.
	*
	* @param primaryKey the primary key of this label
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_label.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_label.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the scope of this label.
	*
	* @param scope the scope of this label
	*/
	@Override
	public void setScope(int scope) {
		_label.setScope(scope);
	}

	/**
	* Sets the user ID of this label.
	*
	* @param userId the user ID of this label
	*/
	@Override
	public void setUserId(long userId) {
		_label.setUserId(userId);
	}

	/**
	* Sets the user name of this label.
	*
	* @param userName the user name of this label
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_label.setUserName(userName);
	}

	/**
	* Sets the user uuid of this label.
	*
	* @param userUuid the user uuid of this label
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_label.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this label.
	*
	* @param uuid the uuid of this label
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_label.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LabelWrapper)) {
			return false;
		}

		LabelWrapper labelWrapper = (LabelWrapper)obj;

		if (Objects.equals(_label, labelWrapper._label)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _label.getStagedModelType();
	}

	@Override
	public Label getWrappedModel() {
		return _label;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _label.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _label.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_label.resetOriginalValues();
	}

	private final Label _label;
}