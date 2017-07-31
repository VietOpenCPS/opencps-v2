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
 * This class is a wrapper for {@link Contact}.
 * </p>
 *
 * @author Binhth
 * @see Contact
 * @generated
 */
@ProviderType
public class ContactWrapper implements Contact, ModelWrapper<Contact> {
	public ContactWrapper(Contact contact) {
		_contact = contact;
	}

	@Override
	public Class<?> getModelClass() {
		return Contact.class;
	}

	@Override
	public String getModelClassName() {
		return Contact.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("contactId", getContactId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("fullName", getFullName());
		attributes.put("companyName", getCompanyName());
		attributes.put("telNo", getTelNo());
		attributes.put("email", getEmail());
		attributes.put("userMappingId", getUserMappingId());
		attributes.put("isOrg", getIsOrg());
		attributes.put("shared", getShared());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long contactId = (Long)attributes.get("contactId");

		if (contactId != null) {
			setContactId(contactId);
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

		String fullName = (String)attributes.get("fullName");

		if (fullName != null) {
			setFullName(fullName);
		}

		String companyName = (String)attributes.get("companyName");

		if (companyName != null) {
			setCompanyName(companyName);
		}

		String telNo = (String)attributes.get("telNo");

		if (telNo != null) {
			setTelNo(telNo);
		}

		String email = (String)attributes.get("email");

		if (email != null) {
			setEmail(email);
		}

		Long userMappingId = (Long)attributes.get("userMappingId");

		if (userMappingId != null) {
			setUserMappingId(userMappingId);
		}

		Boolean isOrg = (Boolean)attributes.get("isOrg");

		if (isOrg != null) {
			setIsOrg(isOrg);
		}

		Integer shared = (Integer)attributes.get("shared");

		if (shared != null) {
			setShared(shared);
		}
	}

	@Override
	public Contact toEscapedModel() {
		return new ContactWrapper(_contact.toEscapedModel());
	}

	@Override
	public Contact toUnescapedModel() {
		return new ContactWrapper(_contact.toUnescapedModel());
	}

	/**
	* Returns the is org of this contact.
	*
	* @return the is org of this contact
	*/
	@Override
	public boolean getIsOrg() {
		return _contact.getIsOrg();
	}

	@Override
	public boolean isCachedModel() {
		return _contact.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _contact.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this contact is is org.
	*
	* @return <code>true</code> if this contact is is org; <code>false</code> otherwise
	*/
	@Override
	public boolean isIsOrg() {
		return _contact.isIsOrg();
	}

	@Override
	public boolean isNew() {
		return _contact.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _contact.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Contact> toCacheModel() {
		return _contact.toCacheModel();
	}

	@Override
	public int compareTo(Contact contact) {
		return _contact.compareTo(contact);
	}

	/**
	* Returns the shared of this contact.
	*
	* @return the shared of this contact
	*/
	@Override
	public int getShared() {
		return _contact.getShared();
	}

	@Override
	public int hashCode() {
		return _contact.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _contact.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new ContactWrapper((Contact)_contact.clone());
	}

	/**
	* Returns the company name of this contact.
	*
	* @return the company name of this contact
	*/
	@Override
	public java.lang.String getCompanyName() {
		return _contact.getCompanyName();
	}

	/**
	* Returns the email of this contact.
	*
	* @return the email of this contact
	*/
	@Override
	public java.lang.String getEmail() {
		return _contact.getEmail();
	}

	/**
	* Returns the full name of this contact.
	*
	* @return the full name of this contact
	*/
	@Override
	public java.lang.String getFullName() {
		return _contact.getFullName();
	}

	/**
	* Returns the tel no of this contact.
	*
	* @return the tel no of this contact
	*/
	@Override
	public java.lang.String getTelNo() {
		return _contact.getTelNo();
	}

	/**
	* Returns the user name of this contact.
	*
	* @return the user name of this contact
	*/
	@Override
	public java.lang.String getUserName() {
		return _contact.getUserName();
	}

	/**
	* Returns the user uuid of this contact.
	*
	* @return the user uuid of this contact
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _contact.getUserUuid();
	}

	/**
	* Returns the uuid of this contact.
	*
	* @return the uuid of this contact
	*/
	@Override
	public java.lang.String getUuid() {
		return _contact.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _contact.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _contact.toXmlString();
	}

	/**
	* Returns the create date of this contact.
	*
	* @return the create date of this contact
	*/
	@Override
	public Date getCreateDate() {
		return _contact.getCreateDate();
	}

	/**
	* Returns the modified date of this contact.
	*
	* @return the modified date of this contact
	*/
	@Override
	public Date getModifiedDate() {
		return _contact.getModifiedDate();
	}

	/**
	* Returns the company ID of this contact.
	*
	* @return the company ID of this contact
	*/
	@Override
	public long getCompanyId() {
		return _contact.getCompanyId();
	}

	/**
	* Returns the contact ID of this contact.
	*
	* @return the contact ID of this contact
	*/
	@Override
	public long getContactId() {
		return _contact.getContactId();
	}

	/**
	* Returns the group ID of this contact.
	*
	* @return the group ID of this contact
	*/
	@Override
	public long getGroupId() {
		return _contact.getGroupId();
	}

	/**
	* Returns the primary key of this contact.
	*
	* @return the primary key of this contact
	*/
	@Override
	public long getPrimaryKey() {
		return _contact.getPrimaryKey();
	}

	/**
	* Returns the user ID of this contact.
	*
	* @return the user ID of this contact
	*/
	@Override
	public long getUserId() {
		return _contact.getUserId();
	}

	/**
	* Returns the user mapping ID of this contact.
	*
	* @return the user mapping ID of this contact
	*/
	@Override
	public long getUserMappingId() {
		return _contact.getUserMappingId();
	}

	@Override
	public void persist() {
		_contact.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_contact.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this contact.
	*
	* @param companyId the company ID of this contact
	*/
	@Override
	public void setCompanyId(long companyId) {
		_contact.setCompanyId(companyId);
	}

	/**
	* Sets the company name of this contact.
	*
	* @param companyName the company name of this contact
	*/
	@Override
	public void setCompanyName(java.lang.String companyName) {
		_contact.setCompanyName(companyName);
	}

	/**
	* Sets the contact ID of this contact.
	*
	* @param contactId the contact ID of this contact
	*/
	@Override
	public void setContactId(long contactId) {
		_contact.setContactId(contactId);
	}

	/**
	* Sets the create date of this contact.
	*
	* @param createDate the create date of this contact
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_contact.setCreateDate(createDate);
	}

	/**
	* Sets the email of this contact.
	*
	* @param email the email of this contact
	*/
	@Override
	public void setEmail(java.lang.String email) {
		_contact.setEmail(email);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_contact.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_contact.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_contact.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the full name of this contact.
	*
	* @param fullName the full name of this contact
	*/
	@Override
	public void setFullName(java.lang.String fullName) {
		_contact.setFullName(fullName);
	}

	/**
	* Sets the group ID of this contact.
	*
	* @param groupId the group ID of this contact
	*/
	@Override
	public void setGroupId(long groupId) {
		_contact.setGroupId(groupId);
	}

	/**
	* Sets whether this contact is is org.
	*
	* @param isOrg the is org of this contact
	*/
	@Override
	public void setIsOrg(boolean isOrg) {
		_contact.setIsOrg(isOrg);
	}

	/**
	* Sets the modified date of this contact.
	*
	* @param modifiedDate the modified date of this contact
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_contact.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_contact.setNew(n);
	}

	/**
	* Sets the primary key of this contact.
	*
	* @param primaryKey the primary key of this contact
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_contact.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_contact.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the shared of this contact.
	*
	* @param shared the shared of this contact
	*/
	@Override
	public void setShared(int shared) {
		_contact.setShared(shared);
	}

	/**
	* Sets the tel no of this contact.
	*
	* @param telNo the tel no of this contact
	*/
	@Override
	public void setTelNo(java.lang.String telNo) {
		_contact.setTelNo(telNo);
	}

	/**
	* Sets the user ID of this contact.
	*
	* @param userId the user ID of this contact
	*/
	@Override
	public void setUserId(long userId) {
		_contact.setUserId(userId);
	}

	/**
	* Sets the user mapping ID of this contact.
	*
	* @param userMappingId the user mapping ID of this contact
	*/
	@Override
	public void setUserMappingId(long userMappingId) {
		_contact.setUserMappingId(userMappingId);
	}

	/**
	* Sets the user name of this contact.
	*
	* @param userName the user name of this contact
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_contact.setUserName(userName);
	}

	/**
	* Sets the user uuid of this contact.
	*
	* @param userUuid the user uuid of this contact
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_contact.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this contact.
	*
	* @param uuid the uuid of this contact
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_contact.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ContactWrapper)) {
			return false;
		}

		ContactWrapper contactWrapper = (ContactWrapper)obj;

		if (Objects.equals(_contact, contactWrapper._contact)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _contact.getStagedModelType();
	}

	@Override
	public Contact getWrappedModel() {
		return _contact;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _contact.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _contact.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_contact.resetOriginalValues();
	}

	private final Contact _contact;
}