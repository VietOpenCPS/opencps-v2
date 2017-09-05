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

package org.opencps.backend.dossiermgt.model;

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
 * This class is a wrapper for {@link ServiceConfig}.
 * </p>
 *
 * @author huymq
 * @see ServiceConfig
 * @generated
 */
@ProviderType
public class ServiceConfigWrapper implements ServiceConfig,
	ModelWrapper<ServiceConfig> {
	public ServiceConfigWrapper(ServiceConfig serviceConfig) {
		_serviceConfig = serviceConfig;
	}

	@Override
	public Class<?> getModelClass() {
		return ServiceConfig.class;
	}

	@Override
	public String getModelClassName() {
		return ServiceConfig.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("serviceConfigId", getServiceConfigId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("serviceInfoId", getServiceInfoId());
		attributes.put("govAgencyCode", getGovAgencyCode());
		attributes.put("govAgencyName", getGovAgencyName());
		attributes.put("serviceInstruction", getServiceInstruction());
		attributes.put("serviceLevel", getServiceLevel());
		attributes.put("serviceUrl", getServiceUrl());
		attributes.put("authentication", getAuthentication());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long serviceConfigId = (Long)attributes.get("serviceConfigId");

		if (serviceConfigId != null) {
			setServiceConfigId(serviceConfigId);
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

		Long serviceInfoId = (Long)attributes.get("serviceInfoId");

		if (serviceInfoId != null) {
			setServiceInfoId(serviceInfoId);
		}

		String govAgencyCode = (String)attributes.get("govAgencyCode");

		if (govAgencyCode != null) {
			setGovAgencyCode(govAgencyCode);
		}

		String govAgencyName = (String)attributes.get("govAgencyName");

		if (govAgencyName != null) {
			setGovAgencyName(govAgencyName);
		}

		String serviceInstruction = (String)attributes.get("serviceInstruction");

		if (serviceInstruction != null) {
			setServiceInstruction(serviceInstruction);
		}

		Integer serviceLevel = (Integer)attributes.get("serviceLevel");

		if (serviceLevel != null) {
			setServiceLevel(serviceLevel);
		}

		String serviceUrl = (String)attributes.get("serviceUrl");

		if (serviceUrl != null) {
			setServiceUrl(serviceUrl);
		}

		Integer authentication = (Integer)attributes.get("authentication");

		if (authentication != null) {
			setAuthentication(authentication);
		}
	}

	@Override
	public ServiceConfig toEscapedModel() {
		return new ServiceConfigWrapper(_serviceConfig.toEscapedModel());
	}

	@Override
	public ServiceConfig toUnescapedModel() {
		return new ServiceConfigWrapper(_serviceConfig.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _serviceConfig.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _serviceConfig.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _serviceConfig.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _serviceConfig.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ServiceConfig> toCacheModel() {
		return _serviceConfig.toCacheModel();
	}

	@Override
	public int compareTo(ServiceConfig serviceConfig) {
		return _serviceConfig.compareTo(serviceConfig);
	}

	/**
	* Returns the authentication of this service config.
	*
	* @return the authentication of this service config
	*/
	@Override
	public int getAuthentication() {
		return _serviceConfig.getAuthentication();
	}

	/**
	* Returns the service level of this service config.
	*
	* @return the service level of this service config
	*/
	@Override
	public int getServiceLevel() {
		return _serviceConfig.getServiceLevel();
	}

	@Override
	public int hashCode() {
		return _serviceConfig.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _serviceConfig.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new ServiceConfigWrapper((ServiceConfig)_serviceConfig.clone());
	}

	/**
	* Returns the gov agency code of this service config.
	*
	* @return the gov agency code of this service config
	*/
	@Override
	public java.lang.String getGovAgencyCode() {
		return _serviceConfig.getGovAgencyCode();
	}

	/**
	* Returns the gov agency name of this service config.
	*
	* @return the gov agency name of this service config
	*/
	@Override
	public java.lang.String getGovAgencyName() {
		return _serviceConfig.getGovAgencyName();
	}

	/**
	* Returns the service instruction of this service config.
	*
	* @return the service instruction of this service config
	*/
	@Override
	public java.lang.String getServiceInstruction() {
		return _serviceConfig.getServiceInstruction();
	}

	/**
	* Returns the service url of this service config.
	*
	* @return the service url of this service config
	*/
	@Override
	public java.lang.String getServiceUrl() {
		return _serviceConfig.getServiceUrl();
	}

	/**
	* Returns the user name of this service config.
	*
	* @return the user name of this service config
	*/
	@Override
	public java.lang.String getUserName() {
		return _serviceConfig.getUserName();
	}

	/**
	* Returns the user uuid of this service config.
	*
	* @return the user uuid of this service config
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _serviceConfig.getUserUuid();
	}

	/**
	* Returns the uuid of this service config.
	*
	* @return the uuid of this service config
	*/
	@Override
	public java.lang.String getUuid() {
		return _serviceConfig.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _serviceConfig.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _serviceConfig.toXmlString();
	}

	/**
	* Returns the create date of this service config.
	*
	* @return the create date of this service config
	*/
	@Override
	public Date getCreateDate() {
		return _serviceConfig.getCreateDate();
	}

	/**
	* Returns the modified date of this service config.
	*
	* @return the modified date of this service config
	*/
	@Override
	public Date getModifiedDate() {
		return _serviceConfig.getModifiedDate();
	}

	/**
	* Returns the company ID of this service config.
	*
	* @return the company ID of this service config
	*/
	@Override
	public long getCompanyId() {
		return _serviceConfig.getCompanyId();
	}

	/**
	* Returns the group ID of this service config.
	*
	* @return the group ID of this service config
	*/
	@Override
	public long getGroupId() {
		return _serviceConfig.getGroupId();
	}

	/**
	* Returns the primary key of this service config.
	*
	* @return the primary key of this service config
	*/
	@Override
	public long getPrimaryKey() {
		return _serviceConfig.getPrimaryKey();
	}

	/**
	* Returns the service config ID of this service config.
	*
	* @return the service config ID of this service config
	*/
	@Override
	public long getServiceConfigId() {
		return _serviceConfig.getServiceConfigId();
	}

	/**
	* Returns the service info ID of this service config.
	*
	* @return the service info ID of this service config
	*/
	@Override
	public long getServiceInfoId() {
		return _serviceConfig.getServiceInfoId();
	}

	/**
	* Returns the user ID of this service config.
	*
	* @return the user ID of this service config
	*/
	@Override
	public long getUserId() {
		return _serviceConfig.getUserId();
	}

	@Override
	public void persist() {
		_serviceConfig.persist();
	}

	/**
	* Sets the authentication of this service config.
	*
	* @param authentication the authentication of this service config
	*/
	@Override
	public void setAuthentication(int authentication) {
		_serviceConfig.setAuthentication(authentication);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_serviceConfig.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this service config.
	*
	* @param companyId the company ID of this service config
	*/
	@Override
	public void setCompanyId(long companyId) {
		_serviceConfig.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this service config.
	*
	* @param createDate the create date of this service config
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_serviceConfig.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_serviceConfig.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_serviceConfig.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_serviceConfig.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the gov agency code of this service config.
	*
	* @param govAgencyCode the gov agency code of this service config
	*/
	@Override
	public void setGovAgencyCode(java.lang.String govAgencyCode) {
		_serviceConfig.setGovAgencyCode(govAgencyCode);
	}

	/**
	* Sets the gov agency name of this service config.
	*
	* @param govAgencyName the gov agency name of this service config
	*/
	@Override
	public void setGovAgencyName(java.lang.String govAgencyName) {
		_serviceConfig.setGovAgencyName(govAgencyName);
	}

	/**
	* Sets the group ID of this service config.
	*
	* @param groupId the group ID of this service config
	*/
	@Override
	public void setGroupId(long groupId) {
		_serviceConfig.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this service config.
	*
	* @param modifiedDate the modified date of this service config
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_serviceConfig.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_serviceConfig.setNew(n);
	}

	/**
	* Sets the primary key of this service config.
	*
	* @param primaryKey the primary key of this service config
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_serviceConfig.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_serviceConfig.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the service config ID of this service config.
	*
	* @param serviceConfigId the service config ID of this service config
	*/
	@Override
	public void setServiceConfigId(long serviceConfigId) {
		_serviceConfig.setServiceConfigId(serviceConfigId);
	}

	/**
	* Sets the service info ID of this service config.
	*
	* @param serviceInfoId the service info ID of this service config
	*/
	@Override
	public void setServiceInfoId(long serviceInfoId) {
		_serviceConfig.setServiceInfoId(serviceInfoId);
	}

	/**
	* Sets the service instruction of this service config.
	*
	* @param serviceInstruction the service instruction of this service config
	*/
	@Override
	public void setServiceInstruction(java.lang.String serviceInstruction) {
		_serviceConfig.setServiceInstruction(serviceInstruction);
	}

	/**
	* Sets the service level of this service config.
	*
	* @param serviceLevel the service level of this service config
	*/
	@Override
	public void setServiceLevel(int serviceLevel) {
		_serviceConfig.setServiceLevel(serviceLevel);
	}

	/**
	* Sets the service url of this service config.
	*
	* @param serviceUrl the service url of this service config
	*/
	@Override
	public void setServiceUrl(java.lang.String serviceUrl) {
		_serviceConfig.setServiceUrl(serviceUrl);
	}

	/**
	* Sets the user ID of this service config.
	*
	* @param userId the user ID of this service config
	*/
	@Override
	public void setUserId(long userId) {
		_serviceConfig.setUserId(userId);
	}

	/**
	* Sets the user name of this service config.
	*
	* @param userName the user name of this service config
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_serviceConfig.setUserName(userName);
	}

	/**
	* Sets the user uuid of this service config.
	*
	* @param userUuid the user uuid of this service config
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_serviceConfig.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this service config.
	*
	* @param uuid the uuid of this service config
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_serviceConfig.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ServiceConfigWrapper)) {
			return false;
		}

		ServiceConfigWrapper serviceConfigWrapper = (ServiceConfigWrapper)obj;

		if (Objects.equals(_serviceConfig, serviceConfigWrapper._serviceConfig)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _serviceConfig.getStagedModelType();
	}

	@Override
	public ServiceConfig getWrappedModel() {
		return _serviceConfig;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _serviceConfig.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _serviceConfig.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_serviceConfig.resetOriginalValues();
	}

	private final ServiceConfig _serviceConfig;
}