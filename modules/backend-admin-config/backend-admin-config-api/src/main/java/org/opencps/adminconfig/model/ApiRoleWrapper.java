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

package org.opencps.adminconfig.model;

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
 * This class is a wrapper for {@link ApiRole}.
 * </p>
 *
 * @author binhth
 * @see ApiRole
 * @generated
 */
@ProviderType
public class ApiRoleWrapper implements ApiRole, ModelWrapper<ApiRole> {
	public ApiRoleWrapper(ApiRole apiRole) {
		_apiRole = apiRole;
	}

	@Override
	public Class<?> getModelClass() {
		return ApiRole.class;
	}

	@Override
	public String getModelClassName() {
		return ApiRole.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("apiRoleId", getApiRoleId());
		attributes.put("groupId", getGroupId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("userId", getUserId());
		attributes.put("apiCode", getApiCode());
		attributes.put("roleId", getRoleId());
		attributes.put("roleCode", getRoleCode());
		attributes.put("apiRoleStatus", getApiRoleStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long apiRoleId = (Long)attributes.get("apiRoleId");

		if (apiRoleId != null) {
			setApiRoleId(apiRoleId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String apiCode = (String)attributes.get("apiCode");

		if (apiCode != null) {
			setApiCode(apiCode);
		}

		Integer roleId = (Integer)attributes.get("roleId");

		if (roleId != null) {
			setRoleId(roleId);
		}

		String roleCode = (String)attributes.get("roleCode");

		if (roleCode != null) {
			setRoleCode(roleCode);
		}

		Integer apiRoleStatus = (Integer)attributes.get("apiRoleStatus");

		if (apiRoleStatus != null) {
			setApiRoleStatus(apiRoleStatus);
		}
	}

	@Override
	public Object clone() {
		return new ApiRoleWrapper((ApiRole)_apiRole.clone());
	}

	@Override
	public int compareTo(ApiRole apiRole) {
		return _apiRole.compareTo(apiRole);
	}

	/**
	* Returns the api code of this api role.
	*
	* @return the api code of this api role
	*/
	@Override
	public String getApiCode() {
		return _apiRole.getApiCode();
	}

	/**
	* Returns the api role ID of this api role.
	*
	* @return the api role ID of this api role
	*/
	@Override
	public long getApiRoleId() {
		return _apiRole.getApiRoleId();
	}

	/**
	* Returns the api role status of this api role.
	*
	* @return the api role status of this api role
	*/
	@Override
	public int getApiRoleStatus() {
		return _apiRole.getApiRoleStatus();
	}

	/**
	* Returns the create date of this api role.
	*
	* @return the create date of this api role
	*/
	@Override
	public Date getCreateDate() {
		return _apiRole.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _apiRole.getExpandoBridge();
	}

	/**
	* Returns the group ID of this api role.
	*
	* @return the group ID of this api role
	*/
	@Override
	public long getGroupId() {
		return _apiRole.getGroupId();
	}

	/**
	* Returns the modified date of this api role.
	*
	* @return the modified date of this api role
	*/
	@Override
	public Date getModifiedDate() {
		return _apiRole.getModifiedDate();
	}

	/**
	* Returns the primary key of this api role.
	*
	* @return the primary key of this api role
	*/
	@Override
	public long getPrimaryKey() {
		return _apiRole.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _apiRole.getPrimaryKeyObj();
	}

	/**
	* Returns the role code of this api role.
	*
	* @return the role code of this api role
	*/
	@Override
	public String getRoleCode() {
		return _apiRole.getRoleCode();
	}

	/**
	* Returns the role ID of this api role.
	*
	* @return the role ID of this api role
	*/
	@Override
	public int getRoleId() {
		return _apiRole.getRoleId();
	}

	/**
	* Returns the user ID of this api role.
	*
	* @return the user ID of this api role
	*/
	@Override
	public long getUserId() {
		return _apiRole.getUserId();
	}

	/**
	* Returns the user uuid of this api role.
	*
	* @return the user uuid of this api role
	*/
	@Override
	public String getUserUuid() {
		return _apiRole.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _apiRole.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _apiRole.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _apiRole.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _apiRole.isNew();
	}

	@Override
	public void persist() {
		_apiRole.persist();
	}

	/**
	* Sets the api code of this api role.
	*
	* @param apiCode the api code of this api role
	*/
	@Override
	public void setApiCode(String apiCode) {
		_apiRole.setApiCode(apiCode);
	}

	/**
	* Sets the api role ID of this api role.
	*
	* @param apiRoleId the api role ID of this api role
	*/
	@Override
	public void setApiRoleId(long apiRoleId) {
		_apiRole.setApiRoleId(apiRoleId);
	}

	/**
	* Sets the api role status of this api role.
	*
	* @param apiRoleStatus the api role status of this api role
	*/
	@Override
	public void setApiRoleStatus(int apiRoleStatus) {
		_apiRole.setApiRoleStatus(apiRoleStatus);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_apiRole.setCachedModel(cachedModel);
	}

	/**
	* Sets the create date of this api role.
	*
	* @param createDate the create date of this api role
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_apiRole.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_apiRole.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_apiRole.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_apiRole.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this api role.
	*
	* @param groupId the group ID of this api role
	*/
	@Override
	public void setGroupId(long groupId) {
		_apiRole.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this api role.
	*
	* @param modifiedDate the modified date of this api role
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_apiRole.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_apiRole.setNew(n);
	}

	/**
	* Sets the primary key of this api role.
	*
	* @param primaryKey the primary key of this api role
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_apiRole.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_apiRole.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the role code of this api role.
	*
	* @param roleCode the role code of this api role
	*/
	@Override
	public void setRoleCode(String roleCode) {
		_apiRole.setRoleCode(roleCode);
	}

	/**
	* Sets the role ID of this api role.
	*
	* @param roleId the role ID of this api role
	*/
	@Override
	public void setRoleId(int roleId) {
		_apiRole.setRoleId(roleId);
	}

	/**
	* Sets the user ID of this api role.
	*
	* @param userId the user ID of this api role
	*/
	@Override
	public void setUserId(long userId) {
		_apiRole.setUserId(userId);
	}

	/**
	* Sets the user uuid of this api role.
	*
	* @param userUuid the user uuid of this api role
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_apiRole.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ApiRole> toCacheModel() {
		return _apiRole.toCacheModel();
	}

	@Override
	public ApiRole toEscapedModel() {
		return new ApiRoleWrapper(_apiRole.toEscapedModel());
	}

	@Override
	public String toString() {
		return _apiRole.toString();
	}

	@Override
	public ApiRole toUnescapedModel() {
		return new ApiRoleWrapper(_apiRole.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _apiRole.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ApiRoleWrapper)) {
			return false;
		}

		ApiRoleWrapper apiRoleWrapper = (ApiRoleWrapper)obj;

		if (Objects.equals(_apiRole, apiRoleWrapper._apiRole)) {
			return true;
		}

		return false;
	}

	@Override
	public ApiRole getWrappedModel() {
		return _apiRole;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _apiRole.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _apiRole.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_apiRole.resetOriginalValues();
	}

	private final ApiRole _apiRole;
}