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
 * This class is a wrapper for {@link ResourceUser}.
 * </p>
 *
 * @author khoavu
 * @see ResourceUser
 * @generated
 */
@ProviderType
public class ResourceUserWrapper implements ResourceUser,
	ModelWrapper<ResourceUser> {
	public ResourceUserWrapper(ResourceUser resourceUser) {
		_resourceUser = resourceUser;
	}

	@Override
	public Class<?> getModelClass() {
		return ResourceUser.class;
	}

	@Override
	public String getModelClassName() {
		return ResourceUser.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("resourceUserId", getResourceUserId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("className", getClassName());
		attributes.put("classPK", getClassPK());
		attributes.put("toUserId", getToUserId());
		attributes.put("fullname", getFullname());
		attributes.put("email", getEmail());
		attributes.put("readonly", isReadonly());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long resourceUserId = (Long)attributes.get("resourceUserId");

		if (resourceUserId != null) {
			setResourceUserId(resourceUserId);
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

		Long toUserId = (Long)attributes.get("toUserId");

		if (toUserId != null) {
			setToUserId(toUserId);
		}

		String fullname = (String)attributes.get("fullname");

		if (fullname != null) {
			setFullname(fullname);
		}

		String email = (String)attributes.get("email");

		if (email != null) {
			setEmail(email);
		}

		Boolean readonly = (Boolean)attributes.get("readonly");

		if (readonly != null) {
			setReadonly(readonly);
		}
	}

	@Override
	public Object clone() {
		return new ResourceUserWrapper((ResourceUser)_resourceUser.clone());
	}

	@Override
	public int compareTo(ResourceUser resourceUser) {
		return _resourceUser.compareTo(resourceUser);
	}

	/**
	* Returns the class name of this resource user.
	*
	* @return the class name of this resource user
	*/
	@Override
	public String getClassName() {
		return _resourceUser.getClassName();
	}

	/**
	* Returns the class pk of this resource user.
	*
	* @return the class pk of this resource user
	*/
	@Override
	public String getClassPK() {
		return _resourceUser.getClassPK();
	}

	/**
	* Returns the company ID of this resource user.
	*
	* @return the company ID of this resource user
	*/
	@Override
	public long getCompanyId() {
		return _resourceUser.getCompanyId();
	}

	/**
	* Returns the create date of this resource user.
	*
	* @return the create date of this resource user
	*/
	@Override
	public Date getCreateDate() {
		return _resourceUser.getCreateDate();
	}

	/**
	* Returns the email of this resource user.
	*
	* @return the email of this resource user
	*/
	@Override
	public String getEmail() {
		return _resourceUser.getEmail();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _resourceUser.getExpandoBridge();
	}

	/**
	* Returns the fullname of this resource user.
	*
	* @return the fullname of this resource user
	*/
	@Override
	public String getFullname() {
		return _resourceUser.getFullname();
	}

	/**
	* Returns the group ID of this resource user.
	*
	* @return the group ID of this resource user
	*/
	@Override
	public long getGroupId() {
		return _resourceUser.getGroupId();
	}

	/**
	* Returns the modified date of this resource user.
	*
	* @return the modified date of this resource user
	*/
	@Override
	public Date getModifiedDate() {
		return _resourceUser.getModifiedDate();
	}

	/**
	* Returns the primary key of this resource user.
	*
	* @return the primary key of this resource user
	*/
	@Override
	public long getPrimaryKey() {
		return _resourceUser.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _resourceUser.getPrimaryKeyObj();
	}

	/**
	* Returns the readonly of this resource user.
	*
	* @return the readonly of this resource user
	*/
	@Override
	public boolean getReadonly() {
		return _resourceUser.getReadonly();
	}

	/**
	* Returns the resource user ID of this resource user.
	*
	* @return the resource user ID of this resource user
	*/
	@Override
	public long getResourceUserId() {
		return _resourceUser.getResourceUserId();
	}

	/**
	* Returns the resource user uuid of this resource user.
	*
	* @return the resource user uuid of this resource user
	*/
	@Override
	public String getResourceUserUuid() {
		return _resourceUser.getResourceUserUuid();
	}

	/**
	* Returns the to user ID of this resource user.
	*
	* @return the to user ID of this resource user
	*/
	@Override
	public long getToUserId() {
		return _resourceUser.getToUserId();
	}

	/**
	* Returns the to user uuid of this resource user.
	*
	* @return the to user uuid of this resource user
	*/
	@Override
	public String getToUserUuid() {
		return _resourceUser.getToUserUuid();
	}

	/**
	* Returns the user ID of this resource user.
	*
	* @return the user ID of this resource user
	*/
	@Override
	public long getUserId() {
		return _resourceUser.getUserId();
	}

	/**
	* Returns the user name of this resource user.
	*
	* @return the user name of this resource user
	*/
	@Override
	public String getUserName() {
		return _resourceUser.getUserName();
	}

	/**
	* Returns the user uuid of this resource user.
	*
	* @return the user uuid of this resource user
	*/
	@Override
	public String getUserUuid() {
		return _resourceUser.getUserUuid();
	}

	/**
	* Returns the uuid of this resource user.
	*
	* @return the uuid of this resource user
	*/
	@Override
	public String getUuid() {
		return _resourceUser.getUuid();
	}

	@Override
	public int hashCode() {
		return _resourceUser.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _resourceUser.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _resourceUser.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _resourceUser.isNew();
	}

	/**
	* Returns <code>true</code> if this resource user is readonly.
	*
	* @return <code>true</code> if this resource user is readonly; <code>false</code> otherwise
	*/
	@Override
	public boolean isReadonly() {
		return _resourceUser.isReadonly();
	}

	@Override
	public void persist() {
		_resourceUser.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_resourceUser.setCachedModel(cachedModel);
	}

	/**
	* Sets the class name of this resource user.
	*
	* @param className the class name of this resource user
	*/
	@Override
	public void setClassName(String className) {
		_resourceUser.setClassName(className);
	}

	/**
	* Sets the class pk of this resource user.
	*
	* @param classPK the class pk of this resource user
	*/
	@Override
	public void setClassPK(String classPK) {
		_resourceUser.setClassPK(classPK);
	}

	/**
	* Sets the company ID of this resource user.
	*
	* @param companyId the company ID of this resource user
	*/
	@Override
	public void setCompanyId(long companyId) {
		_resourceUser.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this resource user.
	*
	* @param createDate the create date of this resource user
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_resourceUser.setCreateDate(createDate);
	}

	/**
	* Sets the email of this resource user.
	*
	* @param email the email of this resource user
	*/
	@Override
	public void setEmail(String email) {
		_resourceUser.setEmail(email);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_resourceUser.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_resourceUser.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_resourceUser.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the fullname of this resource user.
	*
	* @param fullname the fullname of this resource user
	*/
	@Override
	public void setFullname(String fullname) {
		_resourceUser.setFullname(fullname);
	}

	/**
	* Sets the group ID of this resource user.
	*
	* @param groupId the group ID of this resource user
	*/
	@Override
	public void setGroupId(long groupId) {
		_resourceUser.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this resource user.
	*
	* @param modifiedDate the modified date of this resource user
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_resourceUser.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_resourceUser.setNew(n);
	}

	/**
	* Sets the primary key of this resource user.
	*
	* @param primaryKey the primary key of this resource user
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_resourceUser.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_resourceUser.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this resource user is readonly.
	*
	* @param readonly the readonly of this resource user
	*/
	@Override
	public void setReadonly(boolean readonly) {
		_resourceUser.setReadonly(readonly);
	}

	/**
	* Sets the resource user ID of this resource user.
	*
	* @param resourceUserId the resource user ID of this resource user
	*/
	@Override
	public void setResourceUserId(long resourceUserId) {
		_resourceUser.setResourceUserId(resourceUserId);
	}

	/**
	* Sets the resource user uuid of this resource user.
	*
	* @param resourceUserUuid the resource user uuid of this resource user
	*/
	@Override
	public void setResourceUserUuid(String resourceUserUuid) {
		_resourceUser.setResourceUserUuid(resourceUserUuid);
	}

	/**
	* Sets the to user ID of this resource user.
	*
	* @param toUserId the to user ID of this resource user
	*/
	@Override
	public void setToUserId(long toUserId) {
		_resourceUser.setToUserId(toUserId);
	}

	/**
	* Sets the to user uuid of this resource user.
	*
	* @param toUserUuid the to user uuid of this resource user
	*/
	@Override
	public void setToUserUuid(String toUserUuid) {
		_resourceUser.setToUserUuid(toUserUuid);
	}

	/**
	* Sets the user ID of this resource user.
	*
	* @param userId the user ID of this resource user
	*/
	@Override
	public void setUserId(long userId) {
		_resourceUser.setUserId(userId);
	}

	/**
	* Sets the user name of this resource user.
	*
	* @param userName the user name of this resource user
	*/
	@Override
	public void setUserName(String userName) {
		_resourceUser.setUserName(userName);
	}

	/**
	* Sets the user uuid of this resource user.
	*
	* @param userUuid the user uuid of this resource user
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_resourceUser.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this resource user.
	*
	* @param uuid the uuid of this resource user
	*/
	@Override
	public void setUuid(String uuid) {
		_resourceUser.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ResourceUser> toCacheModel() {
		return _resourceUser.toCacheModel();
	}

	@Override
	public ResourceUser toEscapedModel() {
		return new ResourceUserWrapper(_resourceUser.toEscapedModel());
	}

	@Override
	public String toString() {
		return _resourceUser.toString();
	}

	@Override
	public ResourceUser toUnescapedModel() {
		return new ResourceUserWrapper(_resourceUser.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _resourceUser.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ResourceUserWrapper)) {
			return false;
		}

		ResourceUserWrapper resourceUserWrapper = (ResourceUserWrapper)obj;

		if (Objects.equals(_resourceUser, resourceUserWrapper._resourceUser)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _resourceUser.getStagedModelType();
	}

	@Override
	public ResourceUser getWrappedModel() {
		return _resourceUser;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _resourceUser.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _resourceUser.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_resourceUser.resetOriginalValues();
	}

	private final ResourceUser _resourceUser;
}