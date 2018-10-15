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
		attributes.put("forCitizen", isForCitizen());
		attributes.put("forBusiness", isForBusiness());
		attributes.put("postService", isPostService());
		attributes.put("registration", isRegistration());

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

		Boolean forCitizen = (Boolean)attributes.get("forCitizen");

		if (forCitizen != null) {
			setForCitizen(forCitizen);
		}

		Boolean forBusiness = (Boolean)attributes.get("forBusiness");

		if (forBusiness != null) {
			setForBusiness(forBusiness);
		}

		Boolean postService = (Boolean)attributes.get("postService");

		if (postService != null) {
			setPostService(postService);
		}

		Boolean registration = (Boolean)attributes.get("registration");

		if (registration != null) {
			setRegistration(registration);
		}
	}

	@Override
	public Object clone() {
		return new ServiceConfigWrapper((ServiceConfig)_serviceConfig.clone());
	}

	@Override
	public int compareTo(ServiceConfig serviceConfig) {
		return _serviceConfig.compareTo(serviceConfig);
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
	* Returns the create date of this service config.
	*
	* @return the create date of this service config
	*/
	@Override
	public Date getCreateDate() {
		return _serviceConfig.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _serviceConfig.getExpandoBridge();
	}

	/**
	* Returns the for business of this service config.
	*
	* @return the for business of this service config
	*/
	@Override
	public boolean getForBusiness() {
		return _serviceConfig.getForBusiness();
	}

	/**
	* Returns the for citizen of this service config.
	*
	* @return the for citizen of this service config
	*/
	@Override
	public boolean getForCitizen() {
		return _serviceConfig.getForCitizen();
	}

	/**
	* Returns the gov agency code of this service config.
	*
	* @return the gov agency code of this service config
	*/
	@Override
	public String getGovAgencyCode() {
		return _serviceConfig.getGovAgencyCode();
	}

	/**
	* Returns the gov agency name of this service config.
	*
	* @return the gov agency name of this service config
	*/
	@Override
	public String getGovAgencyName() {
		return _serviceConfig.getGovAgencyName();
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
	* Returns the modified date of this service config.
	*
	* @return the modified date of this service config
	*/
	@Override
	public Date getModifiedDate() {
		return _serviceConfig.getModifiedDate();
	}

	/**
	* Returns the post service of this service config.
	*
	* @return the post service of this service config
	*/
	@Override
	public boolean getPostService() {
		return _serviceConfig.getPostService();
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

	@Override
	public Serializable getPrimaryKeyObj() {
		return _serviceConfig.getPrimaryKeyObj();
	}

	/**
	* Returns the registration of this service config.
	*
	* @return the registration of this service config
	*/
	@Override
	public boolean getRegistration() {
		return _serviceConfig.getRegistration();
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
	* Returns the service instruction of this service config.
	*
	* @return the service instruction of this service config
	*/
	@Override
	public String getServiceInstruction() {
		return _serviceConfig.getServiceInstruction();
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

	/**
	* Returns the service url of this service config.
	*
	* @return the service url of this service config
	*/
	@Override
	public String getServiceUrl() {
		return _serviceConfig.getServiceUrl();
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

	/**
	* Returns the user name of this service config.
	*
	* @return the user name of this service config
	*/
	@Override
	public String getUserName() {
		return _serviceConfig.getUserName();
	}

	/**
	* Returns the user uuid of this service config.
	*
	* @return the user uuid of this service config
	*/
	@Override
	public String getUserUuid() {
		return _serviceConfig.getUserUuid();
	}

	/**
	* Returns the uuid of this service config.
	*
	* @return the uuid of this service config
	*/
	@Override
	public String getUuid() {
		return _serviceConfig.getUuid();
	}

	@Override
	public int hashCode() {
		return _serviceConfig.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _serviceConfig.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _serviceConfig.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this service config is for business.
	*
	* @return <code>true</code> if this service config is for business; <code>false</code> otherwise
	*/
	@Override
	public boolean isForBusiness() {
		return _serviceConfig.isForBusiness();
	}

	/**
	* Returns <code>true</code> if this service config is for citizen.
	*
	* @return <code>true</code> if this service config is for citizen; <code>false</code> otherwise
	*/
	@Override
	public boolean isForCitizen() {
		return _serviceConfig.isForCitizen();
	}

	@Override
	public boolean isNew() {
		return _serviceConfig.isNew();
	}

	/**
	* Returns <code>true</code> if this service config is post service.
	*
	* @return <code>true</code> if this service config is post service; <code>false</code> otherwise
	*/
	@Override
	public boolean isPostService() {
		return _serviceConfig.isPostService();
	}

	/**
	* Returns <code>true</code> if this service config is registration.
	*
	* @return <code>true</code> if this service config is registration; <code>false</code> otherwise
	*/
	@Override
	public boolean isRegistration() {
		return _serviceConfig.isRegistration();
	}

	@Override
	public void persist() {
		_serviceConfig.persist();
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
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_serviceConfig.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_serviceConfig.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_serviceConfig.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets whether this service config is for business.
	*
	* @param forBusiness the for business of this service config
	*/
	@Override
	public void setForBusiness(boolean forBusiness) {
		_serviceConfig.setForBusiness(forBusiness);
	}

	/**
	* Sets whether this service config is for citizen.
	*
	* @param forCitizen the for citizen of this service config
	*/
	@Override
	public void setForCitizen(boolean forCitizen) {
		_serviceConfig.setForCitizen(forCitizen);
	}

	/**
	* Sets the gov agency code of this service config.
	*
	* @param govAgencyCode the gov agency code of this service config
	*/
	@Override
	public void setGovAgencyCode(String govAgencyCode) {
		_serviceConfig.setGovAgencyCode(govAgencyCode);
	}

	/**
	* Sets the gov agency name of this service config.
	*
	* @param govAgencyName the gov agency name of this service config
	*/
	@Override
	public void setGovAgencyName(String govAgencyName) {
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
	* Sets whether this service config is post service.
	*
	* @param postService the post service of this service config
	*/
	@Override
	public void setPostService(boolean postService) {
		_serviceConfig.setPostService(postService);
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
	* Sets whether this service config is registration.
	*
	* @param registration the registration of this service config
	*/
	@Override
	public void setRegistration(boolean registration) {
		_serviceConfig.setRegistration(registration);
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
	public void setServiceInstruction(String serviceInstruction) {
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
	public void setServiceUrl(String serviceUrl) {
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
	public void setUserName(String userName) {
		_serviceConfig.setUserName(userName);
	}

	/**
	* Sets the user uuid of this service config.
	*
	* @param userUuid the user uuid of this service config
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_serviceConfig.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this service config.
	*
	* @param uuid the uuid of this service config
	*/
	@Override
	public void setUuid(String uuid) {
		_serviceConfig.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ServiceConfig> toCacheModel() {
		return _serviceConfig.toCacheModel();
	}

	@Override
	public ServiceConfig toEscapedModel() {
		return new ServiceConfigWrapper(_serviceConfig.toEscapedModel());
	}

	@Override
	public String toString() {
		return _serviceConfig.toString();
	}

	@Override
	public ServiceConfig toUnescapedModel() {
		return new ServiceConfigWrapper(_serviceConfig.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _serviceConfig.toXmlString();
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