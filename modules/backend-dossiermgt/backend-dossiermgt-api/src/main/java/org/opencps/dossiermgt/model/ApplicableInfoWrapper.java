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
 * This class is a wrapper for {@link ApplicableInfo}.
 * </p>
 *
 * @author huymq
 * @see ApplicableInfo
 * @generated
 */
@ProviderType
public class ApplicableInfoWrapper implements ApplicableInfo,
	ModelWrapper<ApplicableInfo> {
	public ApplicableInfoWrapper(ApplicableInfo applicableInfo) {
		_applicableInfo = applicableInfo;
	}

	@Override
	public Class<?> getModelClass() {
		return ApplicableInfo.class;
	}

	@Override
	public String getModelClassName() {
		return ApplicableInfo.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("applicableInfoId", getApplicableInfoId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("serviceCode", getServiceCode());
		attributes.put("govAgencyCode", getGovAgencyCode());
		attributes.put("serviceLevel", getServiceLevel());
		attributes.put("serviceConfigMappingId", getServiceConfigMappingId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long applicableInfoId = (Long)attributes.get("applicableInfoId");

		if (applicableInfoId != null) {
			setApplicableInfoId(applicableInfoId);
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

		String govAgencyCode = (String)attributes.get("govAgencyCode");

		if (govAgencyCode != null) {
			setGovAgencyCode(govAgencyCode);
		}

		Integer serviceLevel = (Integer)attributes.get("serviceLevel");

		if (serviceLevel != null) {
			setServiceLevel(serviceLevel);
		}

		Long serviceConfigMappingId = (Long)attributes.get(
				"serviceConfigMappingId");

		if (serviceConfigMappingId != null) {
			setServiceConfigMappingId(serviceConfigMappingId);
		}
	}

	@Override
	public Object clone() {
		return new ApplicableInfoWrapper((ApplicableInfo)_applicableInfo.clone());
	}

	@Override
	public int compareTo(ApplicableInfo applicableInfo) {
		return _applicableInfo.compareTo(applicableInfo);
	}

	/**
	* Returns the applicable info ID of this applicable info.
	*
	* @return the applicable info ID of this applicable info
	*/
	@Override
	public long getApplicableInfoId() {
		return _applicableInfo.getApplicableInfoId();
	}

	/**
	* Returns the company ID of this applicable info.
	*
	* @return the company ID of this applicable info
	*/
	@Override
	public long getCompanyId() {
		return _applicableInfo.getCompanyId();
	}

	/**
	* Returns the create date of this applicable info.
	*
	* @return the create date of this applicable info
	*/
	@Override
	public Date getCreateDate() {
		return _applicableInfo.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _applicableInfo.getExpandoBridge();
	}

	/**
	* Returns the gov agency code of this applicable info.
	*
	* @return the gov agency code of this applicable info
	*/
	@Override
	public String getGovAgencyCode() {
		return _applicableInfo.getGovAgencyCode();
	}

	/**
	* Returns the group ID of this applicable info.
	*
	* @return the group ID of this applicable info
	*/
	@Override
	public long getGroupId() {
		return _applicableInfo.getGroupId();
	}

	/**
	* Returns the modified date of this applicable info.
	*
	* @return the modified date of this applicable info
	*/
	@Override
	public Date getModifiedDate() {
		return _applicableInfo.getModifiedDate();
	}

	/**
	* Returns the primary key of this applicable info.
	*
	* @return the primary key of this applicable info
	*/
	@Override
	public long getPrimaryKey() {
		return _applicableInfo.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _applicableInfo.getPrimaryKeyObj();
	}

	/**
	* Returns the service code of this applicable info.
	*
	* @return the service code of this applicable info
	*/
	@Override
	public String getServiceCode() {
		return _applicableInfo.getServiceCode();
	}

	/**
	* Returns the service config mapping ID of this applicable info.
	*
	* @return the service config mapping ID of this applicable info
	*/
	@Override
	public long getServiceConfigMappingId() {
		return _applicableInfo.getServiceConfigMappingId();
	}

	/**
	* Returns the service level of this applicable info.
	*
	* @return the service level of this applicable info
	*/
	@Override
	public int getServiceLevel() {
		return _applicableInfo.getServiceLevel();
	}

	/**
	* Returns the user ID of this applicable info.
	*
	* @return the user ID of this applicable info
	*/
	@Override
	public long getUserId() {
		return _applicableInfo.getUserId();
	}

	/**
	* Returns the user name of this applicable info.
	*
	* @return the user name of this applicable info
	*/
	@Override
	public String getUserName() {
		return _applicableInfo.getUserName();
	}

	/**
	* Returns the user uuid of this applicable info.
	*
	* @return the user uuid of this applicable info
	*/
	@Override
	public String getUserUuid() {
		return _applicableInfo.getUserUuid();
	}

	/**
	* Returns the uuid of this applicable info.
	*
	* @return the uuid of this applicable info
	*/
	@Override
	public String getUuid() {
		return _applicableInfo.getUuid();
	}

	@Override
	public int hashCode() {
		return _applicableInfo.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _applicableInfo.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _applicableInfo.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _applicableInfo.isNew();
	}

	@Override
	public void persist() {
		_applicableInfo.persist();
	}

	/**
	* Sets the applicable info ID of this applicable info.
	*
	* @param applicableInfoId the applicable info ID of this applicable info
	*/
	@Override
	public void setApplicableInfoId(long applicableInfoId) {
		_applicableInfo.setApplicableInfoId(applicableInfoId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_applicableInfo.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this applicable info.
	*
	* @param companyId the company ID of this applicable info
	*/
	@Override
	public void setCompanyId(long companyId) {
		_applicableInfo.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this applicable info.
	*
	* @param createDate the create date of this applicable info
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_applicableInfo.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_applicableInfo.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_applicableInfo.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_applicableInfo.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the gov agency code of this applicable info.
	*
	* @param govAgencyCode the gov agency code of this applicable info
	*/
	@Override
	public void setGovAgencyCode(String govAgencyCode) {
		_applicableInfo.setGovAgencyCode(govAgencyCode);
	}

	/**
	* Sets the group ID of this applicable info.
	*
	* @param groupId the group ID of this applicable info
	*/
	@Override
	public void setGroupId(long groupId) {
		_applicableInfo.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this applicable info.
	*
	* @param modifiedDate the modified date of this applicable info
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_applicableInfo.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_applicableInfo.setNew(n);
	}

	/**
	* Sets the primary key of this applicable info.
	*
	* @param primaryKey the primary key of this applicable info
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_applicableInfo.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_applicableInfo.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the service code of this applicable info.
	*
	* @param serviceCode the service code of this applicable info
	*/
	@Override
	public void setServiceCode(String serviceCode) {
		_applicableInfo.setServiceCode(serviceCode);
	}

	/**
	* Sets the service config mapping ID of this applicable info.
	*
	* @param serviceConfigMappingId the service config mapping ID of this applicable info
	*/
	@Override
	public void setServiceConfigMappingId(long serviceConfigMappingId) {
		_applicableInfo.setServiceConfigMappingId(serviceConfigMappingId);
	}

	/**
	* Sets the service level of this applicable info.
	*
	* @param serviceLevel the service level of this applicable info
	*/
	@Override
	public void setServiceLevel(int serviceLevel) {
		_applicableInfo.setServiceLevel(serviceLevel);
	}

	/**
	* Sets the user ID of this applicable info.
	*
	* @param userId the user ID of this applicable info
	*/
	@Override
	public void setUserId(long userId) {
		_applicableInfo.setUserId(userId);
	}

	/**
	* Sets the user name of this applicable info.
	*
	* @param userName the user name of this applicable info
	*/
	@Override
	public void setUserName(String userName) {
		_applicableInfo.setUserName(userName);
	}

	/**
	* Sets the user uuid of this applicable info.
	*
	* @param userUuid the user uuid of this applicable info
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_applicableInfo.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this applicable info.
	*
	* @param uuid the uuid of this applicable info
	*/
	@Override
	public void setUuid(String uuid) {
		_applicableInfo.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ApplicableInfo> toCacheModel() {
		return _applicableInfo.toCacheModel();
	}

	@Override
	public ApplicableInfo toEscapedModel() {
		return new ApplicableInfoWrapper(_applicableInfo.toEscapedModel());
	}

	@Override
	public String toString() {
		return _applicableInfo.toString();
	}

	@Override
	public ApplicableInfo toUnescapedModel() {
		return new ApplicableInfoWrapper(_applicableInfo.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _applicableInfo.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ApplicableInfoWrapper)) {
			return false;
		}

		ApplicableInfoWrapper applicableInfoWrapper = (ApplicableInfoWrapper)obj;

		if (Objects.equals(_applicableInfo,
					applicableInfoWrapper._applicableInfo)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _applicableInfo.getStagedModelType();
	}

	@Override
	public ApplicableInfo getWrappedModel() {
		return _applicableInfo;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _applicableInfo.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _applicableInfo.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_applicableInfo.resetOriginalValues();
	}

	private final ApplicableInfo _applicableInfo;
}