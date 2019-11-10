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
 * This class is a wrapper for {@link ZaloMap}.
 * </p>
 *
 * @author khoavd
 * @see ZaloMap
 * @generated
 */
@ProviderType
public class ZaloMapWrapper implements ZaloMap, ModelWrapper<ZaloMap> {
	public ZaloMapWrapper(ZaloMap zaloMap) {
		_zaloMap = zaloMap;
	}

	@Override
	public Class<?> getModelClass() {
		return ZaloMap.class;
	}

	@Override
	public String getModelClassName() {
		return ZaloMap.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("zaloMapId", getZaloMapId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("uId", getUId());
		attributes.put("telNo", getTelNo());
		attributes.put("zaloOAId", getZaloOAId());
		attributes.put("isFollowed", getIsFollowed());
		attributes.put("payload", getPayload());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long zaloMapId = (Long)attributes.get("zaloMapId");

		if (zaloMapId != null) {
			setZaloMapId(zaloMapId);
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

		String uId = (String)attributes.get("uId");

		if (uId != null) {
			setUId(uId);
		}

		String telNo = (String)attributes.get("telNo");

		if (telNo != null) {
			setTelNo(telNo);
		}

		String zaloOAId = (String)attributes.get("zaloOAId");

		if (zaloOAId != null) {
			setZaloOAId(zaloOAId);
		}

		Integer isFollowed = (Integer)attributes.get("isFollowed");

		if (isFollowed != null) {
			setIsFollowed(isFollowed);
		}

		String payload = (String)attributes.get("payload");

		if (payload != null) {
			setPayload(payload);
		}
	}

	@Override
	public Object clone() {
		return new ZaloMapWrapper((ZaloMap)_zaloMap.clone());
	}

	@Override
	public int compareTo(ZaloMap zaloMap) {
		return _zaloMap.compareTo(zaloMap);
	}

	/**
	* Returns the company ID of this zalo map.
	*
	* @return the company ID of this zalo map
	*/
	@Override
	public long getCompanyId() {
		return _zaloMap.getCompanyId();
	}

	/**
	* Returns the create date of this zalo map.
	*
	* @return the create date of this zalo map
	*/
	@Override
	public Date getCreateDate() {
		return _zaloMap.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _zaloMap.getExpandoBridge();
	}

	/**
	* Returns the group ID of this zalo map.
	*
	* @return the group ID of this zalo map
	*/
	@Override
	public long getGroupId() {
		return _zaloMap.getGroupId();
	}

	/**
	* Returns the is followed of this zalo map.
	*
	* @return the is followed of this zalo map
	*/
	@Override
	public int getIsFollowed() {
		return _zaloMap.getIsFollowed();
	}

	/**
	* Returns the modified date of this zalo map.
	*
	* @return the modified date of this zalo map
	*/
	@Override
	public Date getModifiedDate() {
		return _zaloMap.getModifiedDate();
	}

	/**
	* Returns the payload of this zalo map.
	*
	* @return the payload of this zalo map
	*/
	@Override
	public String getPayload() {
		return _zaloMap.getPayload();
	}

	/**
	* Returns the primary key of this zalo map.
	*
	* @return the primary key of this zalo map
	*/
	@Override
	public long getPrimaryKey() {
		return _zaloMap.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _zaloMap.getPrimaryKeyObj();
	}

	/**
	* Returns the tel no of this zalo map.
	*
	* @return the tel no of this zalo map
	*/
	@Override
	public String getTelNo() {
		return _zaloMap.getTelNo();
	}

	/**
	* Returns the u ID of this zalo map.
	*
	* @return the u ID of this zalo map
	*/
	@Override
	public String getUId() {
		return _zaloMap.getUId();
	}

	/**
	* Returns the user ID of this zalo map.
	*
	* @return the user ID of this zalo map
	*/
	@Override
	public long getUserId() {
		return _zaloMap.getUserId();
	}

	/**
	* Returns the user name of this zalo map.
	*
	* @return the user name of this zalo map
	*/
	@Override
	public String getUserName() {
		return _zaloMap.getUserName();
	}

	/**
	* Returns the user uuid of this zalo map.
	*
	* @return the user uuid of this zalo map
	*/
	@Override
	public String getUserUuid() {
		return _zaloMap.getUserUuid();
	}

	/**
	* Returns the zalo map ID of this zalo map.
	*
	* @return the zalo map ID of this zalo map
	*/
	@Override
	public long getZaloMapId() {
		return _zaloMap.getZaloMapId();
	}

	/**
	* Returns the zalo oa ID of this zalo map.
	*
	* @return the zalo oa ID of this zalo map
	*/
	@Override
	public String getZaloOAId() {
		return _zaloMap.getZaloOAId();
	}

	@Override
	public int hashCode() {
		return _zaloMap.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _zaloMap.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _zaloMap.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _zaloMap.isNew();
	}

	@Override
	public void persist() {
		_zaloMap.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_zaloMap.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this zalo map.
	*
	* @param companyId the company ID of this zalo map
	*/
	@Override
	public void setCompanyId(long companyId) {
		_zaloMap.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this zalo map.
	*
	* @param createDate the create date of this zalo map
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_zaloMap.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_zaloMap.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_zaloMap.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_zaloMap.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this zalo map.
	*
	* @param groupId the group ID of this zalo map
	*/
	@Override
	public void setGroupId(long groupId) {
		_zaloMap.setGroupId(groupId);
	}

	/**
	* Sets the is followed of this zalo map.
	*
	* @param isFollowed the is followed of this zalo map
	*/
	@Override
	public void setIsFollowed(int isFollowed) {
		_zaloMap.setIsFollowed(isFollowed);
	}

	/**
	* Sets the modified date of this zalo map.
	*
	* @param modifiedDate the modified date of this zalo map
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_zaloMap.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_zaloMap.setNew(n);
	}

	/**
	* Sets the payload of this zalo map.
	*
	* @param payload the payload of this zalo map
	*/
	@Override
	public void setPayload(String payload) {
		_zaloMap.setPayload(payload);
	}

	/**
	* Sets the primary key of this zalo map.
	*
	* @param primaryKey the primary key of this zalo map
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_zaloMap.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_zaloMap.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the tel no of this zalo map.
	*
	* @param telNo the tel no of this zalo map
	*/
	@Override
	public void setTelNo(String telNo) {
		_zaloMap.setTelNo(telNo);
	}

	/**
	* Sets the u ID of this zalo map.
	*
	* @param uId the u ID of this zalo map
	*/
	@Override
	public void setUId(String uId) {
		_zaloMap.setUId(uId);
	}

	/**
	* Sets the user ID of this zalo map.
	*
	* @param userId the user ID of this zalo map
	*/
	@Override
	public void setUserId(long userId) {
		_zaloMap.setUserId(userId);
	}

	/**
	* Sets the user name of this zalo map.
	*
	* @param userName the user name of this zalo map
	*/
	@Override
	public void setUserName(String userName) {
		_zaloMap.setUserName(userName);
	}

	/**
	* Sets the user uuid of this zalo map.
	*
	* @param userUuid the user uuid of this zalo map
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_zaloMap.setUserUuid(userUuid);
	}

	/**
	* Sets the zalo map ID of this zalo map.
	*
	* @param zaloMapId the zalo map ID of this zalo map
	*/
	@Override
	public void setZaloMapId(long zaloMapId) {
		_zaloMap.setZaloMapId(zaloMapId);
	}

	/**
	* Sets the zalo oa ID of this zalo map.
	*
	* @param zaloOAId the zalo oa ID of this zalo map
	*/
	@Override
	public void setZaloOAId(String zaloOAId) {
		_zaloMap.setZaloOAId(zaloOAId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ZaloMap> toCacheModel() {
		return _zaloMap.toCacheModel();
	}

	@Override
	public ZaloMap toEscapedModel() {
		return new ZaloMapWrapper(_zaloMap.toEscapedModel());
	}

	@Override
	public String toString() {
		return _zaloMap.toString();
	}

	@Override
	public ZaloMap toUnescapedModel() {
		return new ZaloMapWrapper(_zaloMap.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _zaloMap.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ZaloMapWrapper)) {
			return false;
		}

		ZaloMapWrapper zaloMapWrapper = (ZaloMapWrapper)obj;

		if (Objects.equals(_zaloMap, zaloMapWrapper._zaloMap)) {
			return true;
		}

		return false;
	}

	@Override
	public ZaloMap getWrappedModel() {
		return _zaloMap;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _zaloMap.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _zaloMap.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_zaloMap.resetOriginalValues();
	}

	private final ZaloMap _zaloMap;
}