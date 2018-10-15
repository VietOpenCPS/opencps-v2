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

package org.opencps.dossiermgt.model;

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
 * This class is a wrapper for {@link RegistrationLog}.
 * </p>
 *
 * @author huymq
 * @see RegistrationLog
 * @generated
 */
@ProviderType
public class RegistrationLogWrapper implements RegistrationLog,
	ModelWrapper<RegistrationLog> {
	public RegistrationLogWrapper(RegistrationLog registrationLog) {
		_registrationLog = registrationLog;
	}

	@Override
	public Class<?> getModelClass() {
		return RegistrationLog.class;
	}

	@Override
	public String getModelClassName() {
		return RegistrationLog.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("registrationLogId", getRegistrationLogId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("registrationId", getRegistrationId());
		attributes.put("author", getAuthor());
		attributes.put("content", getContent());
		attributes.put("payload", getPayload());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long registrationLogId = (Long)attributes.get("registrationLogId");

		if (registrationLogId != null) {
			setRegistrationLogId(registrationLogId);
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

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long registrationId = (Long)attributes.get("registrationId");

		if (registrationId != null) {
			setRegistrationId(registrationId);
		}

		String author = (String)attributes.get("author");

		if (author != null) {
			setAuthor(author);
		}

		String content = (String)attributes.get("content");

		if (content != null) {
			setContent(content);
		}

		String payload = (String)attributes.get("payload");

		if (payload != null) {
			setPayload(payload);
		}
	}

	@Override
	public Object clone() {
		return new RegistrationLogWrapper((RegistrationLog)_registrationLog.clone());
	}

	@Override
	public int compareTo(RegistrationLog registrationLog) {
		return _registrationLog.compareTo(registrationLog);
	}

	/**
	* Returns the author of this registration log.
	*
	* @return the author of this registration log
	*/
	@Override
	public String getAuthor() {
		return _registrationLog.getAuthor();
	}

	/**
	* Returns the company ID of this registration log.
	*
	* @return the company ID of this registration log
	*/
	@Override
	public long getCompanyId() {
		return _registrationLog.getCompanyId();
	}

	/**
	* Returns the content of this registration log.
	*
	* @return the content of this registration log
	*/
	@Override
	public String getContent() {
		return _registrationLog.getContent();
	}

	/**
	* Returns the create date of this registration log.
	*
	* @return the create date of this registration log
	*/
	@Override
	public Date getCreateDate() {
		return _registrationLog.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _registrationLog.getExpandoBridge();
	}

	/**
	* Returns the group ID of this registration log.
	*
	* @return the group ID of this registration log
	*/
	@Override
	public long getGroupId() {
		return _registrationLog.getGroupId();
	}

	/**
	* Returns the modified date of this registration log.
	*
	* @return the modified date of this registration log
	*/
	@Override
	public Date getModifiedDate() {
		return _registrationLog.getModifiedDate();
	}

	/**
	* Returns the payload of this registration log.
	*
	* @return the payload of this registration log
	*/
	@Override
	public String getPayload() {
		return _registrationLog.getPayload();
	}

	/**
	* Returns the primary key of this registration log.
	*
	* @return the primary key of this registration log
	*/
	@Override
	public long getPrimaryKey() {
		return _registrationLog.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _registrationLog.getPrimaryKeyObj();
	}

	/**
	* Returns the registration ID of this registration log.
	*
	* @return the registration ID of this registration log
	*/
	@Override
	public long getRegistrationId() {
		return _registrationLog.getRegistrationId();
	}

	/**
	* Returns the registration log ID of this registration log.
	*
	* @return the registration log ID of this registration log
	*/
	@Override
	public long getRegistrationLogId() {
		return _registrationLog.getRegistrationLogId();
	}

	/**
	* Returns the user ID of this registration log.
	*
	* @return the user ID of this registration log
	*/
	@Override
	public long getUserId() {
		return _registrationLog.getUserId();
	}

	/**
	* Returns the user uuid of this registration log.
	*
	* @return the user uuid of this registration log
	*/
	@Override
	public String getUserUuid() {
		return _registrationLog.getUserUuid();
	}

	/**
	* Returns the uuid of this registration log.
	*
	* @return the uuid of this registration log
	*/
	@Override
	public String getUuid() {
		return _registrationLog.getUuid();
	}

	@Override
	public int hashCode() {
		return _registrationLog.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _registrationLog.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _registrationLog.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _registrationLog.isNew();
	}

	@Override
	public void persist() {
		_registrationLog.persist();
	}

	/**
	* Sets the author of this registration log.
	*
	* @param author the author of this registration log
	*/
	@Override
	public void setAuthor(String author) {
		_registrationLog.setAuthor(author);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_registrationLog.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this registration log.
	*
	* @param companyId the company ID of this registration log
	*/
	@Override
	public void setCompanyId(long companyId) {
		_registrationLog.setCompanyId(companyId);
	}

	/**
	* Sets the content of this registration log.
	*
	* @param content the content of this registration log
	*/
	@Override
	public void setContent(String content) {
		_registrationLog.setContent(content);
	}

	/**
	* Sets the create date of this registration log.
	*
	* @param createDate the create date of this registration log
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_registrationLog.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_registrationLog.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_registrationLog.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_registrationLog.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this registration log.
	*
	* @param groupId the group ID of this registration log
	*/
	@Override
	public void setGroupId(long groupId) {
		_registrationLog.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this registration log.
	*
	* @param modifiedDate the modified date of this registration log
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_registrationLog.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_registrationLog.setNew(n);
	}

	/**
	* Sets the payload of this registration log.
	*
	* @param payload the payload of this registration log
	*/
	@Override
	public void setPayload(String payload) {
		_registrationLog.setPayload(payload);
	}

	/**
	* Sets the primary key of this registration log.
	*
	* @param primaryKey the primary key of this registration log
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_registrationLog.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_registrationLog.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the registration ID of this registration log.
	*
	* @param registrationId the registration ID of this registration log
	*/
	@Override
	public void setRegistrationId(long registrationId) {
		_registrationLog.setRegistrationId(registrationId);
	}

	/**
	* Sets the registration log ID of this registration log.
	*
	* @param registrationLogId the registration log ID of this registration log
	*/
	@Override
	public void setRegistrationLogId(long registrationLogId) {
		_registrationLog.setRegistrationLogId(registrationLogId);
	}

	/**
	* Sets the user ID of this registration log.
	*
	* @param userId the user ID of this registration log
	*/
	@Override
	public void setUserId(long userId) {
		_registrationLog.setUserId(userId);
	}

	/**
	* Sets the user uuid of this registration log.
	*
	* @param userUuid the user uuid of this registration log
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_registrationLog.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this registration log.
	*
	* @param uuid the uuid of this registration log
	*/
	@Override
	public void setUuid(String uuid) {
		_registrationLog.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<RegistrationLog> toCacheModel() {
		return _registrationLog.toCacheModel();
	}

	@Override
	public RegistrationLog toEscapedModel() {
		return new RegistrationLogWrapper(_registrationLog.toEscapedModel());
	}

	@Override
	public String toString() {
		return _registrationLog.toString();
	}

	@Override
	public RegistrationLog toUnescapedModel() {
		return new RegistrationLogWrapper(_registrationLog.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _registrationLog.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RegistrationLogWrapper)) {
			return false;
		}

		RegistrationLogWrapper registrationLogWrapper = (RegistrationLogWrapper)obj;

		if (Objects.equals(_registrationLog,
					registrationLogWrapper._registrationLog)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _registrationLog.getStagedModelType();
	}

	@Override
	public RegistrationLog getWrappedModel() {
		return _registrationLog;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _registrationLog.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _registrationLog.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_registrationLog.resetOriginalValues();
	}

	private final RegistrationLog _registrationLog;
}