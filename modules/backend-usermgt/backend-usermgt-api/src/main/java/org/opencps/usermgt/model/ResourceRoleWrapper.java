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
 * This class is a wrapper for {@link ResourceRole}.
 * </p>
 *
 * @author khoavu
 * @see ResourceRole
 * @generated
 */
@ProviderType
public class ResourceRoleWrapper implements ResourceRole,
	ModelWrapper<ResourceRole> {
	public ResourceRoleWrapper(ResourceRole resourceRole) {
		_resourceRole = resourceRole;
	}

	@Override
	public Class<?> getModelClass() {
		return ResourceRole.class;
	}

	@Override
	public String getModelClassName() {
		return ResourceRole.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("resourceRoleId", getResourceRoleId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("className", getClassName());
		attributes.put("classPK", getClassPK());
		attributes.put("roleId", getRoleId());
		attributes.put("readonly", getReadonly());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long resourceRoleId = (Long)attributes.get("resourceRoleId");

		if (resourceRoleId != null) {
			setResourceRoleId(resourceRoleId);
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

		String className = (String)attributes.get("className");

		if (className != null) {
			setClassName(className);
		}

		String classPK = (String)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Long roleId = (Long)attributes.get("roleId");

		if (roleId != null) {
			setRoleId(roleId);
		}

		Integer readonly = (Integer)attributes.get("readonly");

		if (readonly != null) {
			setReadonly(readonly);
		}
	}

	@Override
	public Object clone() {
		return new ResourceRoleWrapper((ResourceRole)_resourceRole.clone());
	}

	@Override
	public int compareTo(ResourceRole resourceRole) {
		return _resourceRole.compareTo(resourceRole);
	}

	/**
	* Returns the class name of this resource role.
	*
	* @return the class name of this resource role
	*/
	@Override
	public String getClassName() {
		return _resourceRole.getClassName();
	}

	/**
	* Returns the class pk of this resource role.
	*
	* @return the class pk of this resource role
	*/
	@Override
	public String getClassPK() {
		return _resourceRole.getClassPK();
	}

	/**
	* Returns the company ID of this resource role.
	*
	* @return the company ID of this resource role
	*/
	@Override
	public long getCompanyId() {
		return _resourceRole.getCompanyId();
	}

	/**
	* Returns the create date of this resource role.
	*
	* @return the create date of this resource role
	*/
	@Override
	public Date getCreateDate() {
		return _resourceRole.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _resourceRole.getExpandoBridge();
	}

	/**
	* Returns the group ID of this resource role.
	*
	* @return the group ID of this resource role
	*/
	@Override
	public long getGroupId() {
		return _resourceRole.getGroupId();
	}

	/**
	* Returns the modified date of this resource role.
	*
	* @return the modified date of this resource role
	*/
	@Override
	public Date getModifiedDate() {
		return _resourceRole.getModifiedDate();
	}

	/**
	* Returns the primary key of this resource role.
	*
	* @return the primary key of this resource role
	*/
	@Override
	public long getPrimaryKey() {
		return _resourceRole.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _resourceRole.getPrimaryKeyObj();
	}

	/**
	* Returns the readonly of this resource role.
	*
	* @return the readonly of this resource role
	*/
	@Override
	public int getReadonly() {
		return _resourceRole.getReadonly();
	}

	/**
	* Returns the resource role ID of this resource role.
	*
	* @return the resource role ID of this resource role
	*/
	@Override
	public long getResourceRoleId() {
		return _resourceRole.getResourceRoleId();
	}

	/**
	* Returns the role ID of this resource role.
	*
	* @return the role ID of this resource role
	*/
	@Override
	public long getRoleId() {
		return _resourceRole.getRoleId();
	}

	/**
	* Returns the user ID of this resource role.
	*
	* @return the user ID of this resource role
	*/
	@Override
	public long getUserId() {
		return _resourceRole.getUserId();
	}

	/**
	* Returns the user name of this resource role.
	*
	* @return the user name of this resource role
	*/
	@Override
	public String getUserName() {
		return _resourceRole.getUserName();
	}

	/**
	* Returns the user uuid of this resource role.
	*
	* @return the user uuid of this resource role
	*/
	@Override
	public String getUserUuid() {
		return _resourceRole.getUserUuid();
	}

	/**
	* Returns the uuid of this resource role.
	*
	* @return the uuid of this resource role
	*/
	@Override
	public String getUuid() {
		return _resourceRole.getUuid();
	}

	@Override
	public int hashCode() {
		return _resourceRole.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _resourceRole.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _resourceRole.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _resourceRole.isNew();
	}

	@Override
	public void persist() {
		_resourceRole.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_resourceRole.setCachedModel(cachedModel);
	}

	/**
	* Sets the class name of this resource role.
	*
	* @param className the class name of this resource role
	*/
	@Override
	public void setClassName(String className) {
		_resourceRole.setClassName(className);
	}

	/**
	* Sets the class pk of this resource role.
	*
	* @param classPK the class pk of this resource role
	*/
	@Override
	public void setClassPK(String classPK) {
		_resourceRole.setClassPK(classPK);
	}

	/**
	* Sets the company ID of this resource role.
	*
	* @param companyId the company ID of this resource role
	*/
	@Override
	public void setCompanyId(long companyId) {
		_resourceRole.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this resource role.
	*
	* @param createDate the create date of this resource role
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_resourceRole.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_resourceRole.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_resourceRole.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_resourceRole.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this resource role.
	*
	* @param groupId the group ID of this resource role
	*/
	@Override
	public void setGroupId(long groupId) {
		_resourceRole.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this resource role.
	*
	* @param modifiedDate the modified date of this resource role
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_resourceRole.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_resourceRole.setNew(n);
	}

	/**
	* Sets the primary key of this resource role.
	*
	* @param primaryKey the primary key of this resource role
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_resourceRole.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_resourceRole.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the readonly of this resource role.
	*
	* @param readonly the readonly of this resource role
	*/
	@Override
	public void setReadonly(int readonly) {
		_resourceRole.setReadonly(readonly);
	}

	/**
	* Sets the resource role ID of this resource role.
	*
	* @param resourceRoleId the resource role ID of this resource role
	*/
	@Override
	public void setResourceRoleId(long resourceRoleId) {
		_resourceRole.setResourceRoleId(resourceRoleId);
	}

	/**
	* Sets the role ID of this resource role.
	*
	* @param roleId the role ID of this resource role
	*/
	@Override
	public void setRoleId(long roleId) {
		_resourceRole.setRoleId(roleId);
	}

	/**
	* Sets the user ID of this resource role.
	*
	* @param userId the user ID of this resource role
	*/
	@Override
	public void setUserId(long userId) {
		_resourceRole.setUserId(userId);
	}

	/**
	* Sets the user name of this resource role.
	*
	* @param userName the user name of this resource role
	*/
	@Override
	public void setUserName(String userName) {
		_resourceRole.setUserName(userName);
	}

	/**
	* Sets the user uuid of this resource role.
	*
	* @param userUuid the user uuid of this resource role
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_resourceRole.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this resource role.
	*
	* @param uuid the uuid of this resource role
	*/
	@Override
	public void setUuid(String uuid) {
		_resourceRole.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ResourceRole> toCacheModel() {
		return _resourceRole.toCacheModel();
	}

	@Override
	public ResourceRole toEscapedModel() {
		return new ResourceRoleWrapper(_resourceRole.toEscapedModel());
	}

	@Override
	public String toString() {
		return _resourceRole.toString();
	}

	@Override
	public ResourceRole toUnescapedModel() {
		return new ResourceRoleWrapper(_resourceRole.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _resourceRole.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ResourceRoleWrapper)) {
			return false;
		}

		ResourceRoleWrapper resourceRoleWrapper = (ResourceRoleWrapper)obj;

		if (Objects.equals(_resourceRole, resourceRoleWrapper._resourceRole)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _resourceRole.getStagedModelType();
	}

	@Override
	public ResourceRole getWrappedModel() {
		return _resourceRole;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _resourceRole.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _resourceRole.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_resourceRole.resetOriginalValues();
	}

	private final ResourceRole _resourceRole;
}