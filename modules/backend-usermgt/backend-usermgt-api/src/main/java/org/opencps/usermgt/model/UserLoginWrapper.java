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
 * This class is a wrapper for {@link UserLogin}.
 * </p>
 *
 * @author khoavu
 * @see UserLogin
 * @generated
 */
@ProviderType
public class UserLoginWrapper implements UserLogin, ModelWrapper<UserLogin> {
	public UserLoginWrapper(UserLogin userLogin) {
		_userLogin = userLogin;
	}

	@Override
	public Class<?> getModelClass() {
		return UserLogin.class;
	}

	@Override
	public String getModelClassName() {
		return UserLogin.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("userLoginId", getUserLoginId());
		attributes.put(Field.GROUP_ID, getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("sessionId", getSessionId());
		attributes.put("hits", getHits());
		attributes.put("logout", getLogout());
		attributes.put("ipAddress", getIpAddress());
		attributes.put("online", isOnline());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long userLoginId = (Long)attributes.get("userLoginId");

		if (userLoginId != null) {
			setUserLoginId(userLoginId);
		}

		Long groupId = (Long)attributes.get(Field.GROUP_ID);

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

		String sessionId = (String)attributes.get("sessionId");

		if (sessionId != null) {
			setSessionId(sessionId);
		}

		Integer hits = (Integer)attributes.get("hits");

		if (hits != null) {
			setHits(hits);
		}

		Date logout = (Date)attributes.get("logout");

		if (logout != null) {
			setLogout(logout);
		}

		String ipAddress = (String)attributes.get("ipAddress");

		if (ipAddress != null) {
			setIpAddress(ipAddress);
		}

		Boolean online = (Boolean)attributes.get("online");

		if (online != null) {
			setOnline(online);
		}
	}

	@Override
	public Object clone() {
		return new UserLoginWrapper((UserLogin)_userLogin.clone());
	}

	@Override
	public int compareTo(UserLogin userLogin) {
		return _userLogin.compareTo(userLogin);
	}

	/**
	* Returns the company ID of this user login.
	*
	* @return the company ID of this user login
	*/
	@Override
	public long getCompanyId() {
		return _userLogin.getCompanyId();
	}

	/**
	* Returns the create date of this user login.
	*
	* @return the create date of this user login
	*/
	@Override
	public Date getCreateDate() {
		return _userLogin.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _userLogin.getExpandoBridge();
	}

	/**
	* Returns the group ID of this user login.
	*
	* @return the group ID of this user login
	*/
	@Override
	public long getGroupId() {
		return _userLogin.getGroupId();
	}

	/**
	* Returns the hits of this user login.
	*
	* @return the hits of this user login
	*/
	@Override
	public int getHits() {
		return _userLogin.getHits();
	}

	/**
	* Returns the ip address of this user login.
	*
	* @return the ip address of this user login
	*/
	@Override
	public String getIpAddress() {
		return _userLogin.getIpAddress();
	}

	/**
	* Returns the logout of this user login.
	*
	* @return the logout of this user login
	*/
	@Override
	public Date getLogout() {
		return _userLogin.getLogout();
	}

	/**
	* Returns the modified date of this user login.
	*
	* @return the modified date of this user login
	*/
	@Override
	public Date getModifiedDate() {
		return _userLogin.getModifiedDate();
	}

	/**
	* Returns the online of this user login.
	*
	* @return the online of this user login
	*/
	@Override
	public boolean getOnline() {
		return _userLogin.getOnline();
	}

	/**
	* Returns the primary key of this user login.
	*
	* @return the primary key of this user login
	*/
	@Override
	public long getPrimaryKey() {
		return _userLogin.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _userLogin.getPrimaryKeyObj();
	}

	/**
	* Returns the session ID of this user login.
	*
	* @return the session ID of this user login
	*/
	@Override
	public String getSessionId() {
		return _userLogin.getSessionId();
	}

	/**
	* Returns the user ID of this user login.
	*
	* @return the user ID of this user login
	*/
	@Override
	public long getUserId() {
		return _userLogin.getUserId();
	}

	/**
	* Returns the user login ID of this user login.
	*
	* @return the user login ID of this user login
	*/
	@Override
	public long getUserLoginId() {
		return _userLogin.getUserLoginId();
	}

	/**
	* Returns the user name of this user login.
	*
	* @return the user name of this user login
	*/
	@Override
	public String getUserName() {
		return _userLogin.getUserName();
	}

	/**
	* Returns the user uuid of this user login.
	*
	* @return the user uuid of this user login
	*/
	@Override
	public String getUserUuid() {
		return _userLogin.getUserUuid();
	}

	/**
	* Returns the uuid of this user login.
	*
	* @return the uuid of this user login
	*/
	@Override
	public String getUuid() {
		return _userLogin.getUuid();
	}

	@Override
	public int hashCode() {
		return _userLogin.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _userLogin.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _userLogin.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _userLogin.isNew();
	}

	/**
	* Returns <code>true</code> if this user login is online.
	*
	* @return <code>true</code> if this user login is online; <code>false</code> otherwise
	*/
	@Override
	public boolean isOnline() {
		return _userLogin.isOnline();
	}

	@Override
	public void persist() {
		_userLogin.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_userLogin.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this user login.
	*
	* @param companyId the company ID of this user login
	*/
	@Override
	public void setCompanyId(long companyId) {
		_userLogin.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this user login.
	*
	* @param createDate the create date of this user login
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_userLogin.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_userLogin.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_userLogin.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_userLogin.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this user login.
	*
	* @param groupId the group ID of this user login
	*/
	@Override
	public void setGroupId(long groupId) {
		_userLogin.setGroupId(groupId);
	}

	/**
	* Sets the hits of this user login.
	*
	* @param hits the hits of this user login
	*/
	@Override
	public void setHits(int hits) {
		_userLogin.setHits(hits);
	}

	/**
	* Sets the ip address of this user login.
	*
	* @param ipAddress the ip address of this user login
	*/
	@Override
	public void setIpAddress(String ipAddress) {
		_userLogin.setIpAddress(ipAddress);
	}

	/**
	* Sets the logout of this user login.
	*
	* @param logout the logout of this user login
	*/
	@Override
	public void setLogout(Date logout) {
		_userLogin.setLogout(logout);
	}

	/**
	* Sets the modified date of this user login.
	*
	* @param modifiedDate the modified date of this user login
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_userLogin.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_userLogin.setNew(n);
	}

	/**
	* Sets whether this user login is online.
	*
	* @param online the online of this user login
	*/
	@Override
	public void setOnline(boolean online) {
		_userLogin.setOnline(online);
	}

	/**
	* Sets the primary key of this user login.
	*
	* @param primaryKey the primary key of this user login
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_userLogin.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_userLogin.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the session ID of this user login.
	*
	* @param sessionId the session ID of this user login
	*/
	@Override
	public void setSessionId(String sessionId) {
		_userLogin.setSessionId(sessionId);
	}

	/**
	* Sets the user ID of this user login.
	*
	* @param userId the user ID of this user login
	*/
	@Override
	public void setUserId(long userId) {
		_userLogin.setUserId(userId);
	}

	/**
	* Sets the user login ID of this user login.
	*
	* @param userLoginId the user login ID of this user login
	*/
	@Override
	public void setUserLoginId(long userLoginId) {
		_userLogin.setUserLoginId(userLoginId);
	}

	/**
	* Sets the user name of this user login.
	*
	* @param userName the user name of this user login
	*/
	@Override
	public void setUserName(String userName) {
		_userLogin.setUserName(userName);
	}

	/**
	* Sets the user uuid of this user login.
	*
	* @param userUuid the user uuid of this user login
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_userLogin.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this user login.
	*
	* @param uuid the uuid of this user login
	*/
	@Override
	public void setUuid(String uuid) {
		_userLogin.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<UserLogin> toCacheModel() {
		return _userLogin.toCacheModel();
	}

	@Override
	public UserLogin toEscapedModel() {
		return new UserLoginWrapper(_userLogin.toEscapedModel());
	}

	@Override
	public String toString() {
		return _userLogin.toString();
	}

	@Override
	public UserLogin toUnescapedModel() {
		return new UserLoginWrapper(_userLogin.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _userLogin.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof UserLoginWrapper)) {
			return false;
		}

		UserLoginWrapper userLoginWrapper = (UserLoginWrapper)obj;

		if (Objects.equals(_userLogin, userLoginWrapper._userLogin)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _userLogin.getStagedModelType();
	}

	@Override
	public UserLogin getWrappedModel() {
		return _userLogin;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _userLogin.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _userLogin.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_userLogin.resetOriginalValues();
	}

	private final UserLogin _userLogin;
}