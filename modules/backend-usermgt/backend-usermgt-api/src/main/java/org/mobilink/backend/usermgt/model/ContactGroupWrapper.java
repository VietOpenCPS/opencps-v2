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

package org.mobilink.backend.usermgt.model;

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
 * This class is a wrapper for {@link ContactGroup}.
 * </p>
 *
 * @author Binhth
 * @see ContactGroup
 * @generated
 */
@ProviderType
public class ContactGroupWrapper implements ContactGroup,
	ModelWrapper<ContactGroup> {
	public ContactGroupWrapper(ContactGroup contactGroup) {
		_contactGroup = contactGroup;
	}

	@Override
	public Class<?> getModelClass() {
		return ContactGroup.class;
	}

	@Override
	public String getModelClassName() {
		return ContactGroup.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("contactGroupId", getContactGroupId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("groupName", getGroupName());
		attributes.put("contactList", getContactList());
		attributes.put("shared", getShared());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long contactGroupId = (Long)attributes.get("contactGroupId");

		if (contactGroupId != null) {
			setContactGroupId(contactGroupId);
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

		String groupName = (String)attributes.get("groupName");

		if (groupName != null) {
			setGroupName(groupName);
		}

		String contactList = (String)attributes.get("contactList");

		if (contactList != null) {
			setContactList(contactList);
		}

		Integer shared = (Integer)attributes.get("shared");

		if (shared != null) {
			setShared(shared);
		}
	}

	@Override
	public ContactGroup toEscapedModel() {
		return new ContactGroupWrapper(_contactGroup.toEscapedModel());
	}

	@Override
	public ContactGroup toUnescapedModel() {
		return new ContactGroupWrapper(_contactGroup.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _contactGroup.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _contactGroup.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _contactGroup.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _contactGroup.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ContactGroup> toCacheModel() {
		return _contactGroup.toCacheModel();
	}

	@Override
	public int compareTo(ContactGroup contactGroup) {
		return _contactGroup.compareTo(contactGroup);
	}

	/**
	* Returns the shared of this contact group.
	*
	* @return the shared of this contact group
	*/
	@Override
	public int getShared() {
		return _contactGroup.getShared();
	}

	@Override
	public int hashCode() {
		return _contactGroup.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _contactGroup.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new ContactGroupWrapper((ContactGroup)_contactGroup.clone());
	}

	/**
	* Returns the contact list of this contact group.
	*
	* @return the contact list of this contact group
	*/
	@Override
	public java.lang.String getContactList() {
		return _contactGroup.getContactList();
	}

	/**
	* Returns the group name of this contact group.
	*
	* @return the group name of this contact group
	*/
	@Override
	public java.lang.String getGroupName() {
		return _contactGroup.getGroupName();
	}

	/**
	* Returns the user name of this contact group.
	*
	* @return the user name of this contact group
	*/
	@Override
	public java.lang.String getUserName() {
		return _contactGroup.getUserName();
	}

	/**
	* Returns the user uuid of this contact group.
	*
	* @return the user uuid of this contact group
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _contactGroup.getUserUuid();
	}

	/**
	* Returns the uuid of this contact group.
	*
	* @return the uuid of this contact group
	*/
	@Override
	public java.lang.String getUuid() {
		return _contactGroup.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _contactGroup.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _contactGroup.toXmlString();
	}

	/**
	* Returns the create date of this contact group.
	*
	* @return the create date of this contact group
	*/
	@Override
	public Date getCreateDate() {
		return _contactGroup.getCreateDate();
	}

	/**
	* Returns the modified date of this contact group.
	*
	* @return the modified date of this contact group
	*/
	@Override
	public Date getModifiedDate() {
		return _contactGroup.getModifiedDate();
	}

	/**
	* Returns the company ID of this contact group.
	*
	* @return the company ID of this contact group
	*/
	@Override
	public long getCompanyId() {
		return _contactGroup.getCompanyId();
	}

	/**
	* Returns the contact group ID of this contact group.
	*
	* @return the contact group ID of this contact group
	*/
	@Override
	public long getContactGroupId() {
		return _contactGroup.getContactGroupId();
	}

	/**
	* Returns the group ID of this contact group.
	*
	* @return the group ID of this contact group
	*/
	@Override
	public long getGroupId() {
		return _contactGroup.getGroupId();
	}

	/**
	* Returns the primary key of this contact group.
	*
	* @return the primary key of this contact group
	*/
	@Override
	public long getPrimaryKey() {
		return _contactGroup.getPrimaryKey();
	}

	/**
	* Returns the user ID of this contact group.
	*
	* @return the user ID of this contact group
	*/
	@Override
	public long getUserId() {
		return _contactGroup.getUserId();
	}

	@Override
	public void persist() {
		_contactGroup.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_contactGroup.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this contact group.
	*
	* @param companyId the company ID of this contact group
	*/
	@Override
	public void setCompanyId(long companyId) {
		_contactGroup.setCompanyId(companyId);
	}

	/**
	* Sets the contact group ID of this contact group.
	*
	* @param contactGroupId the contact group ID of this contact group
	*/
	@Override
	public void setContactGroupId(long contactGroupId) {
		_contactGroup.setContactGroupId(contactGroupId);
	}

	/**
	* Sets the contact list of this contact group.
	*
	* @param contactList the contact list of this contact group
	*/
	@Override
	public void setContactList(java.lang.String contactList) {
		_contactGroup.setContactList(contactList);
	}

	/**
	* Sets the create date of this contact group.
	*
	* @param createDate the create date of this contact group
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_contactGroup.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_contactGroup.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_contactGroup.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_contactGroup.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this contact group.
	*
	* @param groupId the group ID of this contact group
	*/
	@Override
	public void setGroupId(long groupId) {
		_contactGroup.setGroupId(groupId);
	}

	/**
	* Sets the group name of this contact group.
	*
	* @param groupName the group name of this contact group
	*/
	@Override
	public void setGroupName(java.lang.String groupName) {
		_contactGroup.setGroupName(groupName);
	}

	/**
	* Sets the modified date of this contact group.
	*
	* @param modifiedDate the modified date of this contact group
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_contactGroup.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_contactGroup.setNew(n);
	}

	/**
	* Sets the primary key of this contact group.
	*
	* @param primaryKey the primary key of this contact group
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_contactGroup.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_contactGroup.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the shared of this contact group.
	*
	* @param shared the shared of this contact group
	*/
	@Override
	public void setShared(int shared) {
		_contactGroup.setShared(shared);
	}

	/**
	* Sets the user ID of this contact group.
	*
	* @param userId the user ID of this contact group
	*/
	@Override
	public void setUserId(long userId) {
		_contactGroup.setUserId(userId);
	}

	/**
	* Sets the user name of this contact group.
	*
	* @param userName the user name of this contact group
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_contactGroup.setUserName(userName);
	}

	/**
	* Sets the user uuid of this contact group.
	*
	* @param userUuid the user uuid of this contact group
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_contactGroup.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this contact group.
	*
	* @param uuid the uuid of this contact group
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_contactGroup.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ContactGroupWrapper)) {
			return false;
		}

		ContactGroupWrapper contactGroupWrapper = (ContactGroupWrapper)obj;

		if (Objects.equals(_contactGroup, contactGroupWrapper._contactGroup)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _contactGroup.getStagedModelType();
	}

	@Override
	public ContactGroup getWrappedModel() {
		return _contactGroup;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _contactGroup.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _contactGroup.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_contactGroup.resetOriginalValues();
	}

	private final ContactGroup _contactGroup;
}