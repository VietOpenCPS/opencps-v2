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

package org.opencps.communication.model;

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
 * This class is a wrapper for {@link LGSPToken}.
 * </p>
 *
 * @author khoavd
 * @see LGSPToken
 * @generated
 */
@ProviderType
public class LGSPTokenWrapper implements LGSPToken, ModelWrapper<LGSPToken> {
	public LGSPTokenWrapper(LGSPToken lgspToken) {
		_lgspToken = lgspToken;
	}

	@Override
	public Class<?> getModelClass() {
		return LGSPToken.class;
	}

	@Override
	public String getModelClassName() {
		return LGSPToken.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("tokenId", getTokenId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("token", getToken());
		attributes.put("tokenType", getTokenType());
		attributes.put("refreshToken", getRefreshToken());
		attributes.put("expiryDate", getExpiryDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long tokenId = (Long)attributes.get("tokenId");

		if (tokenId != null) {
			setTokenId(tokenId);
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

		String tokenType = (String)attributes.get("tokenType");

		if (tokenType != null) {
			setTokenType(tokenType);
		}

		String refreshToken = (String)attributes.get("refreshToken");

		if (refreshToken != null) {
			setRefreshToken(refreshToken);
		}

		Date expiryDate = (Date)attributes.get("expiryDate");

		if (expiryDate != null) {
			setExpiryDate(expiryDate);
		}
	}

	@Override
	public Object clone() {
		return new LGSPTokenWrapper((LGSPToken)_lgspToken.clone());
	}

	@Override
	public int compareTo(LGSPToken lgspToken) {
		return _lgspToken.compareTo(lgspToken);
	}

	/**
	* Returns the create date of this lgsp token.
	*
	* @return the create date of this lgsp token
	*/
	@Override
	public Date getCreateDate() {
		return _lgspToken.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _lgspToken.getExpandoBridge();
	}

	/**
	* Returns the expiry date of this lgsp token.
	*
	* @return the expiry date of this lgsp token
	*/
	@Override
	public Date getExpiryDate() {
		return _lgspToken.getExpiryDate();
	}

	/**
	* Returns the modified date of this lgsp token.
	*
	* @return the modified date of this lgsp token
	*/
	@Override
	public Date getModifiedDate() {
		return _lgspToken.getModifiedDate();
	}

	/**
	* Returns the primary key of this lgsp token.
	*
	* @return the primary key of this lgsp token
	*/
	@Override
	public long getPrimaryKey() {
		return _lgspToken.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _lgspToken.getPrimaryKeyObj();
	}

	/**
	* Returns the refresh token of this lgsp token.
	*
	* @return the refresh token of this lgsp token
	*/
	@Override
	public String getRefreshToken() {
		return _lgspToken.getRefreshToken();
	}

	/**
	* Returns the token of this lgsp token.
	*
	* @return the token of this lgsp token
	*/
	@Override
	public String getToken() {
		return _lgspToken.getToken();
	}

	/**
	* Returns the token ID of this lgsp token.
	*
	* @return the token ID of this lgsp token
	*/
	@Override
	public long getTokenId() {
		return _lgspToken.getTokenId();
	}

	/**
	* Returns the token type of this lgsp token.
	*
	* @return the token type of this lgsp token
	*/
	@Override
	public String getTokenType() {
		return _lgspToken.getTokenType();
	}

	/**
	* Returns the uuid of this lgsp token.
	*
	* @return the uuid of this lgsp token
	*/
	@Override
	public String getUuid() {
		return _lgspToken.getUuid();
	}

	@Override
	public int hashCode() {
		return _lgspToken.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _lgspToken.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _lgspToken.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _lgspToken.isNew();
	}

	@Override
	public void persist() {
		_lgspToken.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_lgspToken.setCachedModel(cachedModel);
	}

	/**
	* Sets the create date of this lgsp token.
	*
	* @param createDate the create date of this lgsp token
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_lgspToken.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_lgspToken.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_lgspToken.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_lgspToken.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the expiry date of this lgsp token.
	*
	* @param expiryDate the expiry date of this lgsp token
	*/
	@Override
	public void setExpiryDate(Date expiryDate) {
		_lgspToken.setExpiryDate(expiryDate);
	}

	/**
	* Sets the modified date of this lgsp token.
	*
	* @param modifiedDate the modified date of this lgsp token
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_lgspToken.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_lgspToken.setNew(n);
	}

	/**
	* Sets the primary key of this lgsp token.
	*
	* @param primaryKey the primary key of this lgsp token
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_lgspToken.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_lgspToken.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the refresh token of this lgsp token.
	*
	* @param refreshToken the refresh token of this lgsp token
	*/
	@Override
	public void setRefreshToken(String refreshToken) {
		_lgspToken.setRefreshToken(refreshToken);
	}

	/**
	* Sets the token of this lgsp token.
	*
	* @param token the token of this lgsp token
	*/
	@Override
	public void setToken(String token) {
		_lgspToken.setToken(token);
	}

	/**
	* Sets the token ID of this lgsp token.
	*
	* @param tokenId the token ID of this lgsp token
	*/
	@Override
	public void setTokenId(long tokenId) {
		_lgspToken.setTokenId(tokenId);
	}

	/**
	* Sets the token type of this lgsp token.
	*
	* @param tokenType the token type of this lgsp token
	*/
	@Override
	public void setTokenType(String tokenType) {
		_lgspToken.setTokenType(tokenType);
	}

	/**
	* Sets the uuid of this lgsp token.
	*
	* @param uuid the uuid of this lgsp token
	*/
	@Override
	public void setUuid(String uuid) {
		_lgspToken.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<LGSPToken> toCacheModel() {
		return _lgspToken.toCacheModel();
	}

	@Override
	public LGSPToken toEscapedModel() {
		return new LGSPTokenWrapper(_lgspToken.toEscapedModel());
	}

	@Override
	public String toString() {
		return _lgspToken.toString();
	}

	@Override
	public LGSPToken toUnescapedModel() {
		return new LGSPTokenWrapper(_lgspToken.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _lgspToken.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LGSPTokenWrapper)) {
			return false;
		}

		LGSPTokenWrapper lgspTokenWrapper = (LGSPTokenWrapper)obj;

		if (Objects.equals(_lgspToken, lgspTokenWrapper._lgspToken)) {
			return true;
		}

		return false;
	}

	@Override
	public LGSPToken getWrappedModel() {
		return _lgspToken;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _lgspToken.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _lgspToken.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_lgspToken.resetOriginalValues();
	}

	private final LGSPToken _lgspToken;
}