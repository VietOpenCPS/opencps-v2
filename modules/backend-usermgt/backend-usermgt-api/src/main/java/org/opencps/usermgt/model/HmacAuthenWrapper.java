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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link HmacAuthen}.
 * </p>
 *
 * @author khoavu
 * @see HmacAuthen
 * @generated
 */
@ProviderType
public class HmacAuthenWrapper implements HmacAuthen, ModelWrapper<HmacAuthen> {
	public HmacAuthenWrapper(HmacAuthen hmacAuthen) {
		_hmacAuthen = hmacAuthen;
	}

	@Override
	public Class<?> getModelClass() {
		return HmacAuthen.class;
	}

	@Override
	public String getModelClassName() {
		return HmacAuthen.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("hmacAuthId", getHmacAuthId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("secret", getSecret());
		attributes.put("permanent", isPermanent());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long hmacAuthId = (Long)attributes.get("hmacAuthId");

		if (hmacAuthId != null) {
			setHmacAuthId(hmacAuthId);
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

		String secret = (String)attributes.get("secret");

		if (secret != null) {
			setSecret(secret);
		}

		Boolean permanent = (Boolean)attributes.get("permanent");

		if (permanent != null) {
			setPermanent(permanent);
		}
	}

	@Override
	public Object clone() {
		return new HmacAuthenWrapper((HmacAuthen)_hmacAuthen.clone());
	}

	@Override
	public int compareTo(HmacAuthen hmacAuthen) {
		return _hmacAuthen.compareTo(hmacAuthen);
	}

	/**
	* Returns the company ID of this hmac authen.
	*
	* @return the company ID of this hmac authen
	*/
	@Override
	public long getCompanyId() {
		return _hmacAuthen.getCompanyId();
	}

	/**
	* Returns the create date of this hmac authen.
	*
	* @return the create date of this hmac authen
	*/
	@Override
	public Date getCreateDate() {
		return _hmacAuthen.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _hmacAuthen.getExpandoBridge();
	}

	/**
	* Returns the group ID of this hmac authen.
	*
	* @return the group ID of this hmac authen
	*/
	@Override
	public long getGroupId() {
		return _hmacAuthen.getGroupId();
	}

	/**
	* Returns the hmac auth ID of this hmac authen.
	*
	* @return the hmac auth ID of this hmac authen
	*/
	@Override
	public long getHmacAuthId() {
		return _hmacAuthen.getHmacAuthId();
	}

	/**
	* Returns the modified date of this hmac authen.
	*
	* @return the modified date of this hmac authen
	*/
	@Override
	public Date getModifiedDate() {
		return _hmacAuthen.getModifiedDate();
	}

	/**
	* Returns the permanent of this hmac authen.
	*
	* @return the permanent of this hmac authen
	*/
	@Override
	public boolean getPermanent() {
		return _hmacAuthen.getPermanent();
	}

	/**
	* Returns the primary key of this hmac authen.
	*
	* @return the primary key of this hmac authen
	*/
	@Override
	public long getPrimaryKey() {
		return _hmacAuthen.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _hmacAuthen.getPrimaryKeyObj();
	}

	/**
	* Returns the secret of this hmac authen.
	*
	* @return the secret of this hmac authen
	*/
	@Override
	public String getSecret() {
		return _hmacAuthen.getSecret();
	}

	/**
	* Returns the user ID of this hmac authen.
	*
	* @return the user ID of this hmac authen
	*/
	@Override
	public long getUserId() {
		return _hmacAuthen.getUserId();
	}

	/**
	* Returns the user name of this hmac authen.
	*
	* @return the user name of this hmac authen
	*/
	@Override
	public String getUserName() {
		return _hmacAuthen.getUserName();
	}

	/**
	* Returns the user uuid of this hmac authen.
	*
	* @return the user uuid of this hmac authen
	*/
	@Override
	public String getUserUuid() {
		return _hmacAuthen.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _hmacAuthen.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _hmacAuthen.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _hmacAuthen.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _hmacAuthen.isNew();
	}

	/**
	* Returns <code>true</code> if this hmac authen is permanent.
	*
	* @return <code>true</code> if this hmac authen is permanent; <code>false</code> otherwise
	*/
	@Override
	public boolean isPermanent() {
		return _hmacAuthen.isPermanent();
	}

	@Override
	public void persist() {
		_hmacAuthen.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_hmacAuthen.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this hmac authen.
	*
	* @param companyId the company ID of this hmac authen
	*/
	@Override
	public void setCompanyId(long companyId) {
		_hmacAuthen.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this hmac authen.
	*
	* @param createDate the create date of this hmac authen
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_hmacAuthen.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_hmacAuthen.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_hmacAuthen.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_hmacAuthen.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this hmac authen.
	*
	* @param groupId the group ID of this hmac authen
	*/
	@Override
	public void setGroupId(long groupId) {
		_hmacAuthen.setGroupId(groupId);
	}

	/**
	* Sets the hmac auth ID of this hmac authen.
	*
	* @param hmacAuthId the hmac auth ID of this hmac authen
	*/
	@Override
	public void setHmacAuthId(long hmacAuthId) {
		_hmacAuthen.setHmacAuthId(hmacAuthId);
	}

	/**
	* Sets the modified date of this hmac authen.
	*
	* @param modifiedDate the modified date of this hmac authen
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_hmacAuthen.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_hmacAuthen.setNew(n);
	}

	/**
	* Sets whether this hmac authen is permanent.
	*
	* @param permanent the permanent of this hmac authen
	*/
	@Override
	public void setPermanent(boolean permanent) {
		_hmacAuthen.setPermanent(permanent);
	}

	/**
	* Sets the primary key of this hmac authen.
	*
	* @param primaryKey the primary key of this hmac authen
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_hmacAuthen.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_hmacAuthen.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the secret of this hmac authen.
	*
	* @param secret the secret of this hmac authen
	*/
	@Override
	public void setSecret(String secret) {
		_hmacAuthen.setSecret(secret);
	}

	/**
	* Sets the user ID of this hmac authen.
	*
	* @param userId the user ID of this hmac authen
	*/
	@Override
	public void setUserId(long userId) {
		_hmacAuthen.setUserId(userId);
	}

	/**
	* Sets the user name of this hmac authen.
	*
	* @param userName the user name of this hmac authen
	*/
	@Override
	public void setUserName(String userName) {
		_hmacAuthen.setUserName(userName);
	}

	/**
	* Sets the user uuid of this hmac authen.
	*
	* @param userUuid the user uuid of this hmac authen
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_hmacAuthen.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<HmacAuthen> toCacheModel() {
		return _hmacAuthen.toCacheModel();
	}

	@Override
	public HmacAuthen toEscapedModel() {
		return new HmacAuthenWrapper(_hmacAuthen.toEscapedModel());
	}

	@Override
	public String toString() {
		return _hmacAuthen.toString();
	}

	@Override
	public HmacAuthen toUnescapedModel() {
		return new HmacAuthenWrapper(_hmacAuthen.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _hmacAuthen.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof HmacAuthenWrapper)) {
			return false;
		}

		HmacAuthenWrapper hmacAuthenWrapper = (HmacAuthenWrapper)obj;

		if (Objects.equals(_hmacAuthen, hmacAuthenWrapper._hmacAuthen)) {
			return true;
		}

		return false;
	}

	@Override
	public HmacAuthen getWrappedModel() {
		return _hmacAuthen;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _hmacAuthen.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _hmacAuthen.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_hmacAuthen.resetOriginalValues();
	}

	private final HmacAuthen _hmacAuthen;
}