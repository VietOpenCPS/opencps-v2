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
 * This class is a wrapper for {@link AccessToken}.
 * </p>
 *
 * @author huymq
 * @see AccessToken
 * @generated
 */
@ProviderType
public class AccessTokenWrapper implements AccessToken,
	ModelWrapper<AccessToken> {
	public AccessTokenWrapper(AccessToken accessToken) {
		_accessToken = accessToken;
	}

	@Override
	public Class<?> getModelClass() {
		return AccessToken.class;
	}

	@Override
	public String getModelClassName() {
		return AccessToken.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("accessTokenId", getAccessTokenId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("token", getToken());
		attributes.put("expireDate", getExpireDate());
		attributes.put("className", getClassName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long accessTokenId = (Long)attributes.get("accessTokenId");

		if (accessTokenId != null) {
			setAccessTokenId(accessTokenId);
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

		String token = (String)attributes.get("token");

		if (token != null) {
			setToken(token);
		}

		Date expireDate = (Date)attributes.get("expireDate");

		if (expireDate != null) {
			setExpireDate(expireDate);
		}

		String className = (String)attributes.get("className");

		if (className != null) {
			setClassName(className);
		}
	}

	@Override
	public Object clone() {
		return new AccessTokenWrapper((AccessToken)_accessToken.clone());
	}

	@Override
	public int compareTo(AccessToken accessToken) {
		return _accessToken.compareTo(accessToken);
	}

	/**
	* Returns the access token ID of this access token.
	*
	* @return the access token ID of this access token
	*/
	@Override
	public long getAccessTokenId() {
		return _accessToken.getAccessTokenId();
	}

	/**
	* Returns the class name of this access token.
	*
	* @return the class name of this access token
	*/
	@Override
	public String getClassName() {
		return _accessToken.getClassName();
	}

	/**
	* Returns the company ID of this access token.
	*
	* @return the company ID of this access token
	*/
	@Override
	public long getCompanyId() {
		return _accessToken.getCompanyId();
	}

	/**
	* Returns the create date of this access token.
	*
	* @return the create date of this access token
	*/
	@Override
	public Date getCreateDate() {
		return _accessToken.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _accessToken.getExpandoBridge();
	}

	/**
	* Returns the expire date of this access token.
	*
	* @return the expire date of this access token
	*/
	@Override
	public Date getExpireDate() {
		return _accessToken.getExpireDate();
	}

	/**
	* Returns the group ID of this access token.
	*
	* @return the group ID of this access token
	*/
	@Override
	public long getGroupId() {
		return _accessToken.getGroupId();
	}

	/**
	* Returns the modified date of this access token.
	*
	* @return the modified date of this access token
	*/
	@Override
	public Date getModifiedDate() {
		return _accessToken.getModifiedDate();
	}

	/**
	* Returns the primary key of this access token.
	*
	* @return the primary key of this access token
	*/
	@Override
	public long getPrimaryKey() {
		return _accessToken.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _accessToken.getPrimaryKeyObj();
	}

	/**
	* Returns the token of this access token.
	*
	* @return the token of this access token
	*/
	@Override
	public String getToken() {
		return _accessToken.getToken();
	}

	/**
	* Returns the user ID of this access token.
	*
	* @return the user ID of this access token
	*/
	@Override
	public long getUserId() {
		return _accessToken.getUserId();
	}

	/**
	* Returns the user name of this access token.
	*
	* @return the user name of this access token
	*/
	@Override
	public String getUserName() {
		return _accessToken.getUserName();
	}

	/**
	* Returns the user uuid of this access token.
	*
	* @return the user uuid of this access token
	*/
	@Override
	public String getUserUuid() {
		return _accessToken.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _accessToken.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _accessToken.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _accessToken.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _accessToken.isNew();
	}

	@Override
	public void persist() {
		_accessToken.persist();
	}

	/**
	* Sets the access token ID of this access token.
	*
	* @param accessTokenId the access token ID of this access token
	*/
	@Override
	public void setAccessTokenId(long accessTokenId) {
		_accessToken.setAccessTokenId(accessTokenId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_accessToken.setCachedModel(cachedModel);
	}

	/**
	* Sets the class name of this access token.
	*
	* @param className the class name of this access token
	*/
	@Override
	public void setClassName(String className) {
		_accessToken.setClassName(className);
	}

	/**
	* Sets the company ID of this access token.
	*
	* @param companyId the company ID of this access token
	*/
	@Override
	public void setCompanyId(long companyId) {
		_accessToken.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this access token.
	*
	* @param createDate the create date of this access token
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_accessToken.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_accessToken.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_accessToken.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_accessToken.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the expire date of this access token.
	*
	* @param expireDate the expire date of this access token
	*/
	@Override
	public void setExpireDate(Date expireDate) {
		_accessToken.setExpireDate(expireDate);
	}

	/**
	* Sets the group ID of this access token.
	*
	* @param groupId the group ID of this access token
	*/
	@Override
	public void setGroupId(long groupId) {
		_accessToken.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this access token.
	*
	* @param modifiedDate the modified date of this access token
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_accessToken.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_accessToken.setNew(n);
	}

	/**
	* Sets the primary key of this access token.
	*
	* @param primaryKey the primary key of this access token
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_accessToken.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_accessToken.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the token of this access token.
	*
	* @param token the token of this access token
	*/
	@Override
	public void setToken(String token) {
		_accessToken.setToken(token);
	}

	/**
	* Sets the user ID of this access token.
	*
	* @param userId the user ID of this access token
	*/
	@Override
	public void setUserId(long userId) {
		_accessToken.setUserId(userId);
	}

	/**
	* Sets the user name of this access token.
	*
	* @param userName the user name of this access token
	*/
	@Override
	public void setUserName(String userName) {
		_accessToken.setUserName(userName);
	}

	/**
	* Sets the user uuid of this access token.
	*
	* @param userUuid the user uuid of this access token
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_accessToken.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<AccessToken> toCacheModel() {
		return _accessToken.toCacheModel();
	}

	@Override
	public AccessToken toEscapedModel() {
		return new AccessTokenWrapper(_accessToken.toEscapedModel());
	}

	@Override
	public String toString() {
		return _accessToken.toString();
	}

	@Override
	public AccessToken toUnescapedModel() {
		return new AccessTokenWrapper(_accessToken.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _accessToken.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AccessTokenWrapper)) {
			return false;
		}

		AccessTokenWrapper accessTokenWrapper = (AccessTokenWrapper)obj;

		if (Objects.equals(_accessToken, accessTokenWrapper._accessToken)) {
			return true;
		}

		return false;
	}

	@Override
	public AccessToken getWrappedModel() {
		return _accessToken;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _accessToken.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _accessToken.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_accessToken.resetOriginalValues();
	}

	private final AccessToken _accessToken;
}