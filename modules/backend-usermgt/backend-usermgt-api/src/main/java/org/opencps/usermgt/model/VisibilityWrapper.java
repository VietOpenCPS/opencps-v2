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
 * This class is a wrapper for {@link Visibility}.
 * </p>
 *
 * @author khoavu
 * @see Visibility
 * @generated
 */
@ProviderType
public class VisibilityWrapper implements Visibility, ModelWrapper<Visibility> {
	public VisibilityWrapper(Visibility visibility) {
		_visibility = visibility;
	}

	@Override
	public Class<?> getModelClass() {
		return Visibility.class;
	}

	@Override
	public String getModelClassName() {
		return Visibility.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("visibilityId", getVisibilityId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("className", getClassName());
		attributes.put("classPK", getClassPK());
		attributes.put("visibility", getVisibility());
		attributes.put("security", getSecurity());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long visibilityId = (Long)attributes.get("visibilityId");

		if (visibilityId != null) {
			setVisibilityId(visibilityId);
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

		String className = (String)attributes.get("className");

		if (className != null) {
			setClassName(className);
		}

		String classPK = (String)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Integer visibility = (Integer)attributes.get("visibility");

		if (visibility != null) {
			setVisibility(visibility);
		}

		String security = (String)attributes.get("security");

		if (security != null) {
			setSecurity(security);
		}
	}

	@Override
	public Object clone() {
		return new VisibilityWrapper((Visibility)_visibility.clone());
	}

	@Override
	public int compareTo(Visibility visibility) {
		return _visibility.compareTo(visibility);
	}

	/**
	* Returns the class name of this visibility.
	*
	* @return the class name of this visibility
	*/
	@Override
	public String getClassName() {
		return _visibility.getClassName();
	}

	/**
	* Returns the class pk of this visibility.
	*
	* @return the class pk of this visibility
	*/
	@Override
	public String getClassPK() {
		return _visibility.getClassPK();
	}

	/**
	* Returns the company ID of this visibility.
	*
	* @return the company ID of this visibility
	*/
	@Override
	public long getCompanyId() {
		return _visibility.getCompanyId();
	}

	/**
	* Returns the create date of this visibility.
	*
	* @return the create date of this visibility
	*/
	@Override
	public Date getCreateDate() {
		return _visibility.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _visibility.getExpandoBridge();
	}

	/**
	* Returns the group ID of this visibility.
	*
	* @return the group ID of this visibility
	*/
	@Override
	public long getGroupId() {
		return _visibility.getGroupId();
	}

	/**
	* Returns the modified date of this visibility.
	*
	* @return the modified date of this visibility
	*/
	@Override
	public Date getModifiedDate() {
		return _visibility.getModifiedDate();
	}

	/**
	* Returns the primary key of this visibility.
	*
	* @return the primary key of this visibility
	*/
	@Override
	public long getPrimaryKey() {
		return _visibility.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _visibility.getPrimaryKeyObj();
	}

	/**
	* Returns the security of this visibility.
	*
	* @return the security of this visibility
	*/
	@Override
	public String getSecurity() {
		return _visibility.getSecurity();
	}

	/**
	* Returns the user ID of this visibility.
	*
	* @return the user ID of this visibility
	*/
	@Override
	public long getUserId() {
		return _visibility.getUserId();
	}

	/**
	* Returns the user name of this visibility.
	*
	* @return the user name of this visibility
	*/
	@Override
	public String getUserName() {
		return _visibility.getUserName();
	}

	/**
	* Returns the user uuid of this visibility.
	*
	* @return the user uuid of this visibility
	*/
	@Override
	public String getUserUuid() {
		return _visibility.getUserUuid();
	}

	/**
	* Returns the uuid of this visibility.
	*
	* @return the uuid of this visibility
	*/
	@Override
	public String getUuid() {
		return _visibility.getUuid();
	}

	/**
	* Returns the visibility of this visibility.
	*
	* @return the visibility of this visibility
	*/
	@Override
	public int getVisibility() {
		return _visibility.getVisibility();
	}

	/**
	* Returns the visibility ID of this visibility.
	*
	* @return the visibility ID of this visibility
	*/
	@Override
	public long getVisibilityId() {
		return _visibility.getVisibilityId();
	}

	@Override
	public int hashCode() {
		return _visibility.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _visibility.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _visibility.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _visibility.isNew();
	}

	@Override
	public void persist() {
		_visibility.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_visibility.setCachedModel(cachedModel);
	}

	/**
	* Sets the class name of this visibility.
	*
	* @param className the class name of this visibility
	*/
	@Override
	public void setClassName(String className) {
		_visibility.setClassName(className);
	}

	/**
	* Sets the class pk of this visibility.
	*
	* @param classPK the class pk of this visibility
	*/
	@Override
	public void setClassPK(String classPK) {
		_visibility.setClassPK(classPK);
	}

	/**
	* Sets the company ID of this visibility.
	*
	* @param companyId the company ID of this visibility
	*/
	@Override
	public void setCompanyId(long companyId) {
		_visibility.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this visibility.
	*
	* @param createDate the create date of this visibility
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_visibility.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_visibility.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_visibility.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_visibility.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this visibility.
	*
	* @param groupId the group ID of this visibility
	*/
	@Override
	public void setGroupId(long groupId) {
		_visibility.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this visibility.
	*
	* @param modifiedDate the modified date of this visibility
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_visibility.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_visibility.setNew(n);
	}

	/**
	* Sets the primary key of this visibility.
	*
	* @param primaryKey the primary key of this visibility
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_visibility.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_visibility.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the security of this visibility.
	*
	* @param security the security of this visibility
	*/
	@Override
	public void setSecurity(String security) {
		_visibility.setSecurity(security);
	}

	/**
	* Sets the user ID of this visibility.
	*
	* @param userId the user ID of this visibility
	*/
	@Override
	public void setUserId(long userId) {
		_visibility.setUserId(userId);
	}

	/**
	* Sets the user name of this visibility.
	*
	* @param userName the user name of this visibility
	*/
	@Override
	public void setUserName(String userName) {
		_visibility.setUserName(userName);
	}

	/**
	* Sets the user uuid of this visibility.
	*
	* @param userUuid the user uuid of this visibility
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_visibility.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this visibility.
	*
	* @param uuid the uuid of this visibility
	*/
	@Override
	public void setUuid(String uuid) {
		_visibility.setUuid(uuid);
	}

	/**
	* Sets the visibility of this visibility.
	*
	* @param visibility the visibility of this visibility
	*/
	@Override
	public void setVisibility(int visibility) {
		_visibility.setVisibility(visibility);
	}

	/**
	* Sets the visibility ID of this visibility.
	*
	* @param visibilityId the visibility ID of this visibility
	*/
	@Override
	public void setVisibilityId(long visibilityId) {
		_visibility.setVisibilityId(visibilityId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Visibility> toCacheModel() {
		return _visibility.toCacheModel();
	}

	@Override
	public Visibility toEscapedModel() {
		return new VisibilityWrapper(_visibility.toEscapedModel());
	}

	@Override
	public String toString() {
		return _visibility.toString();
	}

	@Override
	public Visibility toUnescapedModel() {
		return new VisibilityWrapper(_visibility.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _visibility.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof VisibilityWrapper)) {
			return false;
		}

		VisibilityWrapper visibilityWrapper = (VisibilityWrapper)obj;

		if (Objects.equals(_visibility, visibilityWrapper._visibility)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _visibility.getStagedModelType();
	}

	@Override
	public Visibility getWrappedModel() {
		return _visibility;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _visibility.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _visibility.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_visibility.resetOriginalValues();
	}

	private final Visibility _visibility;
}