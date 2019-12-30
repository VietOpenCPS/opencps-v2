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
 * This class is a wrapper for {@link ServiceInfoMapping}.
 * </p>
 *
 * @author huymq
 * @see ServiceInfoMapping
 * @generated
 */
@ProviderType
public class ServiceInfoMappingWrapper implements ServiceInfoMapping,
	ModelWrapper<ServiceInfoMapping> {
	public ServiceInfoMappingWrapper(ServiceInfoMapping serviceInfoMapping) {
		_serviceInfoMapping = serviceInfoMapping;
	}

	@Override
	public Class<?> getModelClass() {
		return ServiceInfoMapping.class;
	}

	@Override
	public String getModelClassName() {
		return ServiceInfoMapping.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("serviceInfoMappingId", getServiceInfoMappingId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("serviceCode", getServiceCode());
		attributes.put("serviceCodeDVCQG", getServiceCodeDVCQG());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long serviceInfoMappingId = (Long)attributes.get("serviceInfoMappingId");

		if (serviceInfoMappingId != null) {
			setServiceInfoMappingId(serviceInfoMappingId);
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

		String serviceCode = (String)attributes.get("serviceCode");

		if (serviceCode != null) {
			setServiceCode(serviceCode);
		}

		String serviceCodeDVCQG = (String)attributes.get("serviceCodeDVCQG");

		if (serviceCodeDVCQG != null) {
			setServiceCodeDVCQG(serviceCodeDVCQG);
		}
	}

	@Override
	public Object clone() {
		return new ServiceInfoMappingWrapper((ServiceInfoMapping)_serviceInfoMapping.clone());
	}

	@Override
	public int compareTo(ServiceInfoMapping serviceInfoMapping) {
		return _serviceInfoMapping.compareTo(serviceInfoMapping);
	}

	/**
	* Returns the company ID of this service info mapping.
	*
	* @return the company ID of this service info mapping
	*/
	@Override
	public long getCompanyId() {
		return _serviceInfoMapping.getCompanyId();
	}

	/**
	* Returns the create date of this service info mapping.
	*
	* @return the create date of this service info mapping
	*/
	@Override
	public Date getCreateDate() {
		return _serviceInfoMapping.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _serviceInfoMapping.getExpandoBridge();
	}

	/**
	* Returns the group ID of this service info mapping.
	*
	* @return the group ID of this service info mapping
	*/
	@Override
	public long getGroupId() {
		return _serviceInfoMapping.getGroupId();
	}

	/**
	* Returns the modified date of this service info mapping.
	*
	* @return the modified date of this service info mapping
	*/
	@Override
	public Date getModifiedDate() {
		return _serviceInfoMapping.getModifiedDate();
	}

	/**
	* Returns the primary key of this service info mapping.
	*
	* @return the primary key of this service info mapping
	*/
	@Override
	public long getPrimaryKey() {
		return _serviceInfoMapping.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _serviceInfoMapping.getPrimaryKeyObj();
	}

	/**
	* Returns the service code of this service info mapping.
	*
	* @return the service code of this service info mapping
	*/
	@Override
	public String getServiceCode() {
		return _serviceInfoMapping.getServiceCode();
	}

	/**
	* Returns the service code dvcqg of this service info mapping.
	*
	* @return the service code dvcqg of this service info mapping
	*/
	@Override
	public String getServiceCodeDVCQG() {
		return _serviceInfoMapping.getServiceCodeDVCQG();
	}

	/**
	* Returns the service info mapping ID of this service info mapping.
	*
	* @return the service info mapping ID of this service info mapping
	*/
	@Override
	public long getServiceInfoMappingId() {
		return _serviceInfoMapping.getServiceInfoMappingId();
	}

	/**
	* Returns the user ID of this service info mapping.
	*
	* @return the user ID of this service info mapping
	*/
	@Override
	public long getUserId() {
		return _serviceInfoMapping.getUserId();
	}

	/**
	* Returns the user name of this service info mapping.
	*
	* @return the user name of this service info mapping
	*/
	@Override
	public String getUserName() {
		return _serviceInfoMapping.getUserName();
	}

	/**
	* Returns the user uuid of this service info mapping.
	*
	* @return the user uuid of this service info mapping
	*/
	@Override
	public String getUserUuid() {
		return _serviceInfoMapping.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _serviceInfoMapping.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _serviceInfoMapping.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _serviceInfoMapping.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _serviceInfoMapping.isNew();
	}

	@Override
	public void persist() {
		_serviceInfoMapping.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_serviceInfoMapping.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this service info mapping.
	*
	* @param companyId the company ID of this service info mapping
	*/
	@Override
	public void setCompanyId(long companyId) {
		_serviceInfoMapping.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this service info mapping.
	*
	* @param createDate the create date of this service info mapping
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_serviceInfoMapping.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_serviceInfoMapping.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_serviceInfoMapping.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_serviceInfoMapping.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this service info mapping.
	*
	* @param groupId the group ID of this service info mapping
	*/
	@Override
	public void setGroupId(long groupId) {
		_serviceInfoMapping.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this service info mapping.
	*
	* @param modifiedDate the modified date of this service info mapping
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_serviceInfoMapping.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_serviceInfoMapping.setNew(n);
	}

	/**
	* Sets the primary key of this service info mapping.
	*
	* @param primaryKey the primary key of this service info mapping
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_serviceInfoMapping.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_serviceInfoMapping.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the service code of this service info mapping.
	*
	* @param serviceCode the service code of this service info mapping
	*/
	@Override
	public void setServiceCode(String serviceCode) {
		_serviceInfoMapping.setServiceCode(serviceCode);
	}

	/**
	* Sets the service code dvcqg of this service info mapping.
	*
	* @param serviceCodeDVCQG the service code dvcqg of this service info mapping
	*/
	@Override
	public void setServiceCodeDVCQG(String serviceCodeDVCQG) {
		_serviceInfoMapping.setServiceCodeDVCQG(serviceCodeDVCQG);
	}

	/**
	* Sets the service info mapping ID of this service info mapping.
	*
	* @param serviceInfoMappingId the service info mapping ID of this service info mapping
	*/
	@Override
	public void setServiceInfoMappingId(long serviceInfoMappingId) {
		_serviceInfoMapping.setServiceInfoMappingId(serviceInfoMappingId);
	}

	/**
	* Sets the user ID of this service info mapping.
	*
	* @param userId the user ID of this service info mapping
	*/
	@Override
	public void setUserId(long userId) {
		_serviceInfoMapping.setUserId(userId);
	}

	/**
	* Sets the user name of this service info mapping.
	*
	* @param userName the user name of this service info mapping
	*/
	@Override
	public void setUserName(String userName) {
		_serviceInfoMapping.setUserName(userName);
	}

	/**
	* Sets the user uuid of this service info mapping.
	*
	* @param userUuid the user uuid of this service info mapping
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_serviceInfoMapping.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ServiceInfoMapping> toCacheModel() {
		return _serviceInfoMapping.toCacheModel();
	}

	@Override
	public ServiceInfoMapping toEscapedModel() {
		return new ServiceInfoMappingWrapper(_serviceInfoMapping.toEscapedModel());
	}

	@Override
	public String toString() {
		return _serviceInfoMapping.toString();
	}

	@Override
	public ServiceInfoMapping toUnescapedModel() {
		return new ServiceInfoMappingWrapper(_serviceInfoMapping.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _serviceInfoMapping.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ServiceInfoMappingWrapper)) {
			return false;
		}

		ServiceInfoMappingWrapper serviceInfoMappingWrapper = (ServiceInfoMappingWrapper)obj;

		if (Objects.equals(_serviceInfoMapping,
					serviceInfoMappingWrapper._serviceInfoMapping)) {
			return true;
		}

		return false;
	}

	@Override
	public ServiceInfoMapping getWrappedModel() {
		return _serviceInfoMapping;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _serviceInfoMapping.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _serviceInfoMapping.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_serviceInfoMapping.resetOriginalValues();
	}

	private final ServiceInfoMapping _serviceInfoMapping;
}