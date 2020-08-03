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
 * This class is a wrapper for {@link ServiceConfigMapping}.
 * </p>
 *
 * @author huymq
 * @see ServiceConfigMapping
 * @generated
 */
@ProviderType
public class ServiceConfigMappingWrapper implements ServiceConfigMapping,
	ModelWrapper<ServiceConfigMapping> {
	public ServiceConfigMappingWrapper(
		ServiceConfigMapping serviceConfigMapping) {
		_serviceConfigMapping = serviceConfigMapping;
	}

	@Override
	public Class<?> getModelClass() {
		return ServiceConfigMapping.class;
	}

	@Override
	public String getModelClassName() {
		return ServiceConfigMapping.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("serviceConfigMappingId", getServiceConfigMappingId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("serviceConfigCode", getServiceConfigCode());
		attributes.put("serviceConfigName", getServiceConfigName());
		attributes.put("serviceCode", getServiceCode());
		attributes.put("serviceName", getServiceName());
		attributes.put("govAgencyName", getGovAgencyName());
		attributes.put("domainName", getDomainName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long serviceConfigMappingId = (Long)attributes.get(
				"serviceConfigMappingId");

		if (serviceConfigMappingId != null) {
			setServiceConfigMappingId(serviceConfigMappingId);
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

		String serviceConfigCode = (String)attributes.get("serviceConfigCode");

		if (serviceConfigCode != null) {
			setServiceConfigCode(serviceConfigCode);
		}

		String serviceConfigName = (String)attributes.get("serviceConfigName");

		if (serviceConfigName != null) {
			setServiceConfigName(serviceConfigName);
		}

		String serviceCode = (String)attributes.get("serviceCode");

		if (serviceCode != null) {
			setServiceCode(serviceCode);
		}

		String serviceName = (String)attributes.get("serviceName");

		if (serviceName != null) {
			setServiceName(serviceName);
		}

		String govAgencyName = (String)attributes.get("govAgencyName");

		if (govAgencyName != null) {
			setGovAgencyName(govAgencyName);
		}

		String domainName = (String)attributes.get("domainName");

		if (domainName != null) {
			setDomainName(domainName);
		}
	}

	@Override
	public Object clone() {
		return new ServiceConfigMappingWrapper((ServiceConfigMapping)_serviceConfigMapping.clone());
	}

	@Override
	public int compareTo(ServiceConfigMapping serviceConfigMapping) {
		return _serviceConfigMapping.compareTo(serviceConfigMapping);
	}

	/**
	* Returns the company ID of this service config mapping.
	*
	* @return the company ID of this service config mapping
	*/
	@Override
	public long getCompanyId() {
		return _serviceConfigMapping.getCompanyId();
	}

	/**
	* Returns the create date of this service config mapping.
	*
	* @return the create date of this service config mapping
	*/
	@Override
	public Date getCreateDate() {
		return _serviceConfigMapping.getCreateDate();
	}

	/**
	* Returns the domain name of this service config mapping.
	*
	* @return the domain name of this service config mapping
	*/
	@Override
	public String getDomainName() {
		return _serviceConfigMapping.getDomainName();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _serviceConfigMapping.getExpandoBridge();
	}

	/**
	* Returns the gov agency name of this service config mapping.
	*
	* @return the gov agency name of this service config mapping
	*/
	@Override
	public String getGovAgencyName() {
		return _serviceConfigMapping.getGovAgencyName();
	}

	/**
	* Returns the group ID of this service config mapping.
	*
	* @return the group ID of this service config mapping
	*/
	@Override
	public long getGroupId() {
		return _serviceConfigMapping.getGroupId();
	}

	/**
	* Returns the modified date of this service config mapping.
	*
	* @return the modified date of this service config mapping
	*/
	@Override
	public Date getModifiedDate() {
		return _serviceConfigMapping.getModifiedDate();
	}

	/**
	* Returns the primary key of this service config mapping.
	*
	* @return the primary key of this service config mapping
	*/
	@Override
	public long getPrimaryKey() {
		return _serviceConfigMapping.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _serviceConfigMapping.getPrimaryKeyObj();
	}

	/**
	* Returns the service code of this service config mapping.
	*
	* @return the service code of this service config mapping
	*/
	@Override
	public String getServiceCode() {
		return _serviceConfigMapping.getServiceCode();
	}

	/**
	* Returns the service config code of this service config mapping.
	*
	* @return the service config code of this service config mapping
	*/
	@Override
	public String getServiceConfigCode() {
		return _serviceConfigMapping.getServiceConfigCode();
	}

	/**
	* Returns the service config mapping ID of this service config mapping.
	*
	* @return the service config mapping ID of this service config mapping
	*/
	@Override
	public long getServiceConfigMappingId() {
		return _serviceConfigMapping.getServiceConfigMappingId();
	}

	/**
	* Returns the service config name of this service config mapping.
	*
	* @return the service config name of this service config mapping
	*/
	@Override
	public String getServiceConfigName() {
		return _serviceConfigMapping.getServiceConfigName();
	}

	/**
	* Returns the service name of this service config mapping.
	*
	* @return the service name of this service config mapping
	*/
	@Override
	public String getServiceName() {
		return _serviceConfigMapping.getServiceName();
	}

	/**
	* Returns the user ID of this service config mapping.
	*
	* @return the user ID of this service config mapping
	*/
	@Override
	public long getUserId() {
		return _serviceConfigMapping.getUserId();
	}

	/**
	* Returns the user name of this service config mapping.
	*
	* @return the user name of this service config mapping
	*/
	@Override
	public String getUserName() {
		return _serviceConfigMapping.getUserName();
	}

	/**
	* Returns the user uuid of this service config mapping.
	*
	* @return the user uuid of this service config mapping
	*/
	@Override
	public String getUserUuid() {
		return _serviceConfigMapping.getUserUuid();
	}

	/**
	* Returns the uuid of this service config mapping.
	*
	* @return the uuid of this service config mapping
	*/
	@Override
	public String getUuid() {
		return _serviceConfigMapping.getUuid();
	}

	@Override
	public int hashCode() {
		return _serviceConfigMapping.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _serviceConfigMapping.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _serviceConfigMapping.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _serviceConfigMapping.isNew();
	}

	@Override
	public void persist() {
		_serviceConfigMapping.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_serviceConfigMapping.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this service config mapping.
	*
	* @param companyId the company ID of this service config mapping
	*/
	@Override
	public void setCompanyId(long companyId) {
		_serviceConfigMapping.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this service config mapping.
	*
	* @param createDate the create date of this service config mapping
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_serviceConfigMapping.setCreateDate(createDate);
	}

	/**
	* Sets the domain name of this service config mapping.
	*
	* @param domainName the domain name of this service config mapping
	*/
	@Override
	public void setDomainName(String domainName) {
		_serviceConfigMapping.setDomainName(domainName);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_serviceConfigMapping.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_serviceConfigMapping.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_serviceConfigMapping.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the gov agency name of this service config mapping.
	*
	* @param govAgencyName the gov agency name of this service config mapping
	*/
	@Override
	public void setGovAgencyName(String govAgencyName) {
		_serviceConfigMapping.setGovAgencyName(govAgencyName);
	}

	/**
	* Sets the group ID of this service config mapping.
	*
	* @param groupId the group ID of this service config mapping
	*/
	@Override
	public void setGroupId(long groupId) {
		_serviceConfigMapping.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this service config mapping.
	*
	* @param modifiedDate the modified date of this service config mapping
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_serviceConfigMapping.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_serviceConfigMapping.setNew(n);
	}

	/**
	* Sets the primary key of this service config mapping.
	*
	* @param primaryKey the primary key of this service config mapping
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_serviceConfigMapping.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_serviceConfigMapping.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the service code of this service config mapping.
	*
	* @param serviceCode the service code of this service config mapping
	*/
	@Override
	public void setServiceCode(String serviceCode) {
		_serviceConfigMapping.setServiceCode(serviceCode);
	}

	/**
	* Sets the service config code of this service config mapping.
	*
	* @param serviceConfigCode the service config code of this service config mapping
	*/
	@Override
	public void setServiceConfigCode(String serviceConfigCode) {
		_serviceConfigMapping.setServiceConfigCode(serviceConfigCode);
	}

	/**
	* Sets the service config mapping ID of this service config mapping.
	*
	* @param serviceConfigMappingId the service config mapping ID of this service config mapping
	*/
	@Override
	public void setServiceConfigMappingId(long serviceConfigMappingId) {
		_serviceConfigMapping.setServiceConfigMappingId(serviceConfigMappingId);
	}

	/**
	* Sets the service config name of this service config mapping.
	*
	* @param serviceConfigName the service config name of this service config mapping
	*/
	@Override
	public void setServiceConfigName(String serviceConfigName) {
		_serviceConfigMapping.setServiceConfigName(serviceConfigName);
	}

	/**
	* Sets the service name of this service config mapping.
	*
	* @param serviceName the service name of this service config mapping
	*/
	@Override
	public void setServiceName(String serviceName) {
		_serviceConfigMapping.setServiceName(serviceName);
	}

	/**
	* Sets the user ID of this service config mapping.
	*
	* @param userId the user ID of this service config mapping
	*/
	@Override
	public void setUserId(long userId) {
		_serviceConfigMapping.setUserId(userId);
	}

	/**
	* Sets the user name of this service config mapping.
	*
	* @param userName the user name of this service config mapping
	*/
	@Override
	public void setUserName(String userName) {
		_serviceConfigMapping.setUserName(userName);
	}

	/**
	* Sets the user uuid of this service config mapping.
	*
	* @param userUuid the user uuid of this service config mapping
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_serviceConfigMapping.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this service config mapping.
	*
	* @param uuid the uuid of this service config mapping
	*/
	@Override
	public void setUuid(String uuid) {
		_serviceConfigMapping.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ServiceConfigMapping> toCacheModel() {
		return _serviceConfigMapping.toCacheModel();
	}

	@Override
	public ServiceConfigMapping toEscapedModel() {
		return new ServiceConfigMappingWrapper(_serviceConfigMapping.toEscapedModel());
	}

	@Override
	public String toString() {
		return _serviceConfigMapping.toString();
	}

	@Override
	public ServiceConfigMapping toUnescapedModel() {
		return new ServiceConfigMappingWrapper(_serviceConfigMapping.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _serviceConfigMapping.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ServiceConfigMappingWrapper)) {
			return false;
		}

		ServiceConfigMappingWrapper serviceConfigMappingWrapper = (ServiceConfigMappingWrapper)obj;

		if (Objects.equals(_serviceConfigMapping,
					serviceConfigMappingWrapper._serviceConfigMapping)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _serviceConfigMapping.getStagedModelType();
	}

	@Override
	public ServiceConfigMapping getWrappedModel() {
		return _serviceConfigMapping;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _serviceConfigMapping.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _serviceConfigMapping.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_serviceConfigMapping.resetOriginalValues();
	}

	private final ServiceConfigMapping _serviceConfigMapping;
}