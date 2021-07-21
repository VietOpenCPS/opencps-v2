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
 * This class is a wrapper for {@link ApiManager}.
 * </p>
 *
 * @author binhth
 * @see ApiManager
 * @generated
 */
@ProviderType
public class ApiManagerWrapper implements ApiManager, ModelWrapper<ApiManager> {
	public ApiManagerWrapper(ApiManager apiManager) {
		_apiManager = apiManager;
	}

	@Override
	public Class<?> getModelClass() {
		return ApiManager.class;
	}

	@Override
	public String getModelClassName() {
		return ApiManager.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("apiManagerId", getApiManagerId());
		attributes.put("groupId", getGroupId());
		attributes.put("apiCode", getApiCode());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("userId", getUserId());
		attributes.put("apiName", getApiName());
		attributes.put("apiDescription", getApiDescription());
		attributes.put("apiStatus", getApiStatus());
		attributes.put("className", getClassName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long apiManagerId = (Long)attributes.get("apiManagerId");

		if (apiManagerId != null) {
			setApiManagerId(apiManagerId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		String apiCode = (String)attributes.get("apiCode");

		if (apiCode != null) {
			setApiCode(apiCode);
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

		String apiName = (String)attributes.get("apiName");

		if (apiName != null) {
			setApiName(apiName);
		}

		String apiDescription = (String)attributes.get("apiDescription");

		if (apiDescription != null) {
			setApiDescription(apiDescription);
		}

		Integer apiStatus = (Integer)attributes.get("apiStatus");

		if (apiStatus != null) {
			setApiStatus(apiStatus);
		}

		String className = (String)attributes.get("className");

		if (className != null) {
			setClassName(className);
		}
	}

	@Override
	public Object clone() {
		return new ApiManagerWrapper((ApiManager)_apiManager.clone());
	}

	@Override
	public int compareTo(ApiManager apiManager) {
		return _apiManager.compareTo(apiManager);
	}

	/**
	* Returns the api code of this api manager.
	*
	* @return the api code of this api manager
	*/
	@Override
	public String getApiCode() {
		return _apiManager.getApiCode();
	}

	/**
	* Returns the api description of this api manager.
	*
	* @return the api description of this api manager
	*/
	@Override
	public String getApiDescription() {
		return _apiManager.getApiDescription();
	}

	/**
	* Returns the api manager ID of this api manager.
	*
	* @return the api manager ID of this api manager
	*/
	@Override
	public long getApiManagerId() {
		return _apiManager.getApiManagerId();
	}

	/**
	* Returns the api name of this api manager.
	*
	* @return the api name of this api manager
	*/
	@Override
	public String getApiName() {
		return _apiManager.getApiName();
	}

	/**
	* Returns the api status of this api manager.
	*
	* @return the api status of this api manager
	*/
	@Override
	public int getApiStatus() {
		return _apiManager.getApiStatus();
	}

	/**
	* Returns the class name of this api manager.
	*
	* @return the class name of this api manager
	*/
	@Override
	public String getClassName() {
		return _apiManager.getClassName();
	}

	/**
	* Returns the create date of this api manager.
	*
	* @return the create date of this api manager
	*/
	@Override
	public Date getCreateDate() {
		return _apiManager.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _apiManager.getExpandoBridge();
	}

	/**
	* Returns the group ID of this api manager.
	*
	* @return the group ID of this api manager
	*/
	@Override
	public long getGroupId() {
		return _apiManager.getGroupId();
	}

	/**
	* Returns the modified date of this api manager.
	*
	* @return the modified date of this api manager
	*/
	@Override
	public Date getModifiedDate() {
		return _apiManager.getModifiedDate();
	}

	/**
	* Returns the primary key of this api manager.
	*
	* @return the primary key of this api manager
	*/
	@Override
	public long getPrimaryKey() {
		return _apiManager.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _apiManager.getPrimaryKeyObj();
	}

	/**
	* Returns the user ID of this api manager.
	*
	* @return the user ID of this api manager
	*/
	@Override
	public long getUserId() {
		return _apiManager.getUserId();
	}

	/**
	* Returns the user uuid of this api manager.
	*
	* @return the user uuid of this api manager
	*/
	@Override
	public String getUserUuid() {
		return _apiManager.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _apiManager.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _apiManager.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _apiManager.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _apiManager.isNew();
	}

	@Override
	public void persist() {
		_apiManager.persist();
	}

	/**
	* Sets the api code of this api manager.
	*
	* @param apiCode the api code of this api manager
	*/
	@Override
	public void setApiCode(String apiCode) {
		_apiManager.setApiCode(apiCode);
	}

	/**
	* Sets the api description of this api manager.
	*
	* @param apiDescription the api description of this api manager
	*/
	@Override
	public void setApiDescription(String apiDescription) {
		_apiManager.setApiDescription(apiDescription);
	}

	/**
	* Sets the api manager ID of this api manager.
	*
	* @param apiManagerId the api manager ID of this api manager
	*/
	@Override
	public void setApiManagerId(long apiManagerId) {
		_apiManager.setApiManagerId(apiManagerId);
	}

	/**
	* Sets the api name of this api manager.
	*
	* @param apiName the api name of this api manager
	*/
	@Override
	public void setApiName(String apiName) {
		_apiManager.setApiName(apiName);
	}

	/**
	* Sets the api status of this api manager.
	*
	* @param apiStatus the api status of this api manager
	*/
	@Override
	public void setApiStatus(int apiStatus) {
		_apiManager.setApiStatus(apiStatus);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_apiManager.setCachedModel(cachedModel);
	}

	/**
	* Sets the class name of this api manager.
	*
	* @param className the class name of this api manager
	*/
	@Override
	public void setClassName(String className) {
		_apiManager.setClassName(className);
	}

	/**
	* Sets the create date of this api manager.
	*
	* @param createDate the create date of this api manager
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_apiManager.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_apiManager.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_apiManager.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_apiManager.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this api manager.
	*
	* @param groupId the group ID of this api manager
	*/
	@Override
	public void setGroupId(long groupId) {
		_apiManager.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this api manager.
	*
	* @param modifiedDate the modified date of this api manager
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_apiManager.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_apiManager.setNew(n);
	}

	/**
	* Sets the primary key of this api manager.
	*
	* @param primaryKey the primary key of this api manager
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_apiManager.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_apiManager.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this api manager.
	*
	* @param userId the user ID of this api manager
	*/
	@Override
	public void setUserId(long userId) {
		_apiManager.setUserId(userId);
	}

	/**
	* Sets the user uuid of this api manager.
	*
	* @param userUuid the user uuid of this api manager
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_apiManager.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ApiManager> toCacheModel() {
		return _apiManager.toCacheModel();
	}

	@Override
	public ApiManager toEscapedModel() {
		return new ApiManagerWrapper(_apiManager.toEscapedModel());
	}

	@Override
	public String toString() {
		return _apiManager.toString();
	}

	@Override
	public ApiManager toUnescapedModel() {
		return new ApiManagerWrapper(_apiManager.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _apiManager.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ApiManagerWrapper)) {
			return false;
		}

		ApiManagerWrapper apiManagerWrapper = (ApiManagerWrapper)obj;

		if (Objects.equals(_apiManager, apiManagerWrapper._apiManager)) {
			return true;
		}

		return false;
	}

	@Override
	public ApiManager getWrappedModel() {
		return _apiManager;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _apiManager.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _apiManager.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_apiManager.resetOriginalValues();
	}

	private final ApiManager _apiManager;
}