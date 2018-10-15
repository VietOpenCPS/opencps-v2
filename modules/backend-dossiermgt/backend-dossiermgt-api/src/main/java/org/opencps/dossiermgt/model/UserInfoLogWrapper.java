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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link UserInfoLog}.
 * </p>
 *
 * @author huymq
 * @see UserInfoLog
 * @generated
 */
@ProviderType
public class UserInfoLogWrapper implements UserInfoLog,
	ModelWrapper<UserInfoLog> {
	public UserInfoLogWrapper(UserInfoLog userInfoLog) {
		_userInfoLog = userInfoLog;
	}

	@Override
	public Class<?> getModelClass() {
		return UserInfoLog.class;
	}

	@Override
	public String getModelClassName() {
		return UserInfoLog.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("userLogId", getUserLogId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("payload", getPayload());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long userLogId = (Long)attributes.get("userLogId");

		if (userLogId != null) {
			setUserLogId(userLogId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		String payload = (String)attributes.get("payload");

		if (payload != null) {
			setPayload(payload);
		}
	}

	@Override
	public Object clone() {
		return new UserInfoLogWrapper((UserInfoLog)_userInfoLog.clone());
	}

	@Override
	public int compareTo(UserInfoLog userInfoLog) {
		return _userInfoLog.compareTo(userInfoLog);
	}

	/**
	* Returns the create date of this user info log.
	*
	* @return the create date of this user info log
	*/
	@Override
	public Date getCreateDate() {
		return _userInfoLog.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _userInfoLog.getExpandoBridge();
	}

	/**
	* Returns the payload of this user info log.
	*
	* @return the payload of this user info log
	*/
	@Override
	public String getPayload() {
		return _userInfoLog.getPayload();
	}

	/**
	* Returns the primary key of this user info log.
	*
	* @return the primary key of this user info log
	*/
	@Override
	public long getPrimaryKey() {
		return _userInfoLog.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _userInfoLog.getPrimaryKeyObj();
	}

	/**
	* Returns the user ID of this user info log.
	*
	* @return the user ID of this user info log
	*/
	@Override
	public long getUserId() {
		return _userInfoLog.getUserId();
	}

	/**
	* Returns the user log ID of this user info log.
	*
	* @return the user log ID of this user info log
	*/
	@Override
	public long getUserLogId() {
		return _userInfoLog.getUserLogId();
	}

	/**
	* Returns the user uuid of this user info log.
	*
	* @return the user uuid of this user info log
	*/
	@Override
	public String getUserUuid() {
		return _userInfoLog.getUserUuid();
	}

	/**
	* Returns the uuid of this user info log.
	*
	* @return the uuid of this user info log
	*/
	@Override
	public String getUuid() {
		return _userInfoLog.getUuid();
	}

	@Override
	public int hashCode() {
		return _userInfoLog.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _userInfoLog.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _userInfoLog.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _userInfoLog.isNew();
	}

	@Override
	public void persist() {
		_userInfoLog.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_userInfoLog.setCachedModel(cachedModel);
	}

	/**
	* Sets the create date of this user info log.
	*
	* @param createDate the create date of this user info log
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_userInfoLog.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_userInfoLog.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_userInfoLog.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_userInfoLog.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_userInfoLog.setNew(n);
	}

	/**
	* Sets the payload of this user info log.
	*
	* @param payload the payload of this user info log
	*/
	@Override
	public void setPayload(String payload) {
		_userInfoLog.setPayload(payload);
	}

	/**
	* Sets the primary key of this user info log.
	*
	* @param primaryKey the primary key of this user info log
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_userInfoLog.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_userInfoLog.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this user info log.
	*
	* @param userId the user ID of this user info log
	*/
	@Override
	public void setUserId(long userId) {
		_userInfoLog.setUserId(userId);
	}

	/**
	* Sets the user log ID of this user info log.
	*
	* @param userLogId the user log ID of this user info log
	*/
	@Override
	public void setUserLogId(long userLogId) {
		_userInfoLog.setUserLogId(userLogId);
	}

	/**
	* Sets the user uuid of this user info log.
	*
	* @param userUuid the user uuid of this user info log
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_userInfoLog.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this user info log.
	*
	* @param uuid the uuid of this user info log
	*/
	@Override
	public void setUuid(String uuid) {
		_userInfoLog.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<UserInfoLog> toCacheModel() {
		return _userInfoLog.toCacheModel();
	}

	@Override
	public UserInfoLog toEscapedModel() {
		return new UserInfoLogWrapper(_userInfoLog.toEscapedModel());
	}

	@Override
	public String toString() {
		return _userInfoLog.toString();
	}

	@Override
	public UserInfoLog toUnescapedModel() {
		return new UserInfoLogWrapper(_userInfoLog.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _userInfoLog.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof UserInfoLogWrapper)) {
			return false;
		}

		UserInfoLogWrapper userInfoLogWrapper = (UserInfoLogWrapper)obj;

		if (Objects.equals(_userInfoLog, userInfoLogWrapper._userInfoLog)) {
			return true;
		}

		return false;
	}

	@Override
	public UserInfoLog getWrappedModel() {
		return _userInfoLog;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _userInfoLog.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _userInfoLog.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_userInfoLog.resetOriginalValues();
	}

	private final UserInfoLog _userInfoLog;
}