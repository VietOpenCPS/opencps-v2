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
 * This class is a wrapper for {@link ServiceInfo}.
 * </p>
 *
 * @author huymq
 * @see ServiceInfo
 * @generated
 */
@ProviderType
public class ServiceInfoWrapper implements ServiceInfo,
	ModelWrapper<ServiceInfo> {
	public ServiceInfoWrapper(ServiceInfo serviceInfo) {
		_serviceInfo = serviceInfo;
	}

	@Override
	public Class<?> getModelClass() {
		return ServiceInfo.class;
	}

	@Override
	public String getModelClassName() {
		return ServiceInfo.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("serviceInfoId", getServiceInfoId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("serviceCode", getServiceCode());
		attributes.put("serviceName", getServiceName());
		attributes.put("processText", getProcessText());
		attributes.put("methodText", getMethodText());
		attributes.put("dossierText", getDossierText());
		attributes.put("conditionText", getConditionText());
		attributes.put("durationText", getDurationText());
		attributes.put("applicantText", getApplicantText());
		attributes.put("resultText", getResultText());
		attributes.put("regularText", getRegularText());
		attributes.put("feeText", getFeeText());
		attributes.put("administrationCode", getAdministrationCode());
		attributes.put("administrationName", getAdministrationName());
		attributes.put("administrationIndex", getAdministrationIndex());
		attributes.put("domainCode", getDomainCode());
		attributes.put("domainName", getDomainName());
		attributes.put("domainIndex", getDomainIndex());
		attributes.put("maxLevel", getMaxLevel());
		attributes.put("public_", isPublic_());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long serviceInfoId = (Long)attributes.get("serviceInfoId");

		if (serviceInfoId != null) {
			setServiceInfoId(serviceInfoId);
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

		String serviceName = (String)attributes.get("serviceName");

		if (serviceName != null) {
			setServiceName(serviceName);
		}

		String processText = (String)attributes.get("processText");

		if (processText != null) {
			setProcessText(processText);
		}

		String methodText = (String)attributes.get("methodText");

		if (methodText != null) {
			setMethodText(methodText);
		}

		String dossierText = (String)attributes.get("dossierText");

		if (dossierText != null) {
			setDossierText(dossierText);
		}

		String conditionText = (String)attributes.get("conditionText");

		if (conditionText != null) {
			setConditionText(conditionText);
		}

		String durationText = (String)attributes.get("durationText");

		if (durationText != null) {
			setDurationText(durationText);
		}

		String applicantText = (String)attributes.get("applicantText");

		if (applicantText != null) {
			setApplicantText(applicantText);
		}

		String resultText = (String)attributes.get("resultText");

		if (resultText != null) {
			setResultText(resultText);
		}

		String regularText = (String)attributes.get("regularText");

		if (regularText != null) {
			setRegularText(regularText);
		}

		String feeText = (String)attributes.get("feeText");

		if (feeText != null) {
			setFeeText(feeText);
		}

		String administrationCode = (String)attributes.get("administrationCode");

		if (administrationCode != null) {
			setAdministrationCode(administrationCode);
		}

		String administrationName = (String)attributes.get("administrationName");

		if (administrationName != null) {
			setAdministrationName(administrationName);
		}

		String administrationIndex = (String)attributes.get(
				"administrationIndex");

		if (administrationIndex != null) {
			setAdministrationIndex(administrationIndex);
		}

		String domainCode = (String)attributes.get("domainCode");

		if (domainCode != null) {
			setDomainCode(domainCode);
		}

		String domainName = (String)attributes.get("domainName");

		if (domainName != null) {
			setDomainName(domainName);
		}

		String domainIndex = (String)attributes.get("domainIndex");

		if (domainIndex != null) {
			setDomainIndex(domainIndex);
		}

		Integer maxLevel = (Integer)attributes.get("maxLevel");

		if (maxLevel != null) {
			setMaxLevel(maxLevel);
		}

		Boolean public_ = (Boolean)attributes.get("public_");

		if (public_ != null) {
			setPublic_(public_);
		}
	}

	@Override
	public Object clone() {
		return new ServiceInfoWrapper((ServiceInfo)_serviceInfo.clone());
	}

	@Override
	public int compareTo(ServiceInfo serviceInfo) {
		return _serviceInfo.compareTo(serviceInfo);
	}

	/**
	* Returns the administration code of this service info.
	*
	* @return the administration code of this service info
	*/
	@Override
	public String getAdministrationCode() {
		return _serviceInfo.getAdministrationCode();
	}

	/**
	* Returns the administration index of this service info.
	*
	* @return the administration index of this service info
	*/
	@Override
	public String getAdministrationIndex() {
		return _serviceInfo.getAdministrationIndex();
	}

	/**
	* Returns the administration name of this service info.
	*
	* @return the administration name of this service info
	*/
	@Override
	public String getAdministrationName() {
		return _serviceInfo.getAdministrationName();
	}

	/**
	* Returns the applicant text of this service info.
	*
	* @return the applicant text of this service info
	*/
	@Override
	public String getApplicantText() {
		return _serviceInfo.getApplicantText();
	}

	/**
	* Returns the company ID of this service info.
	*
	* @return the company ID of this service info
	*/
	@Override
	public long getCompanyId() {
		return _serviceInfo.getCompanyId();
	}

	/**
	* Returns the condition text of this service info.
	*
	* @return the condition text of this service info
	*/
	@Override
	public String getConditionText() {
		return _serviceInfo.getConditionText();
	}

	/**
	* Returns the create date of this service info.
	*
	* @return the create date of this service info
	*/
	@Override
	public Date getCreateDate() {
		return _serviceInfo.getCreateDate();
	}

	/**
	* Returns the domain code of this service info.
	*
	* @return the domain code of this service info
	*/
	@Override
	public String getDomainCode() {
		return _serviceInfo.getDomainCode();
	}

	/**
	* Returns the domain index of this service info.
	*
	* @return the domain index of this service info
	*/
	@Override
	public String getDomainIndex() {
		return _serviceInfo.getDomainIndex();
	}

	/**
	* Returns the domain name of this service info.
	*
	* @return the domain name of this service info
	*/
	@Override
	public String getDomainName() {
		return _serviceInfo.getDomainName();
	}

	/**
	* Returns the dossier text of this service info.
	*
	* @return the dossier text of this service info
	*/
	@Override
	public String getDossierText() {
		return _serviceInfo.getDossierText();
	}

	/**
	* Returns the duration text of this service info.
	*
	* @return the duration text of this service info
	*/
	@Override
	public String getDurationText() {
		return _serviceInfo.getDurationText();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _serviceInfo.getExpandoBridge();
	}

	/**
	* Returns the fee text of this service info.
	*
	* @return the fee text of this service info
	*/
	@Override
	public String getFeeText() {
		return _serviceInfo.getFeeText();
	}

	/**
	* Returns the group ID of this service info.
	*
	* @return the group ID of this service info
	*/
	@Override
	public long getGroupId() {
		return _serviceInfo.getGroupId();
	}

	/**
	* Returns the max level of this service info.
	*
	* @return the max level of this service info
	*/
	@Override
	public int getMaxLevel() {
		return _serviceInfo.getMaxLevel();
	}

	/**
	* Returns the method text of this service info.
	*
	* @return the method text of this service info
	*/
	@Override
	public String getMethodText() {
		return _serviceInfo.getMethodText();
	}

	/**
	* Returns the modified date of this service info.
	*
	* @return the modified date of this service info
	*/
	@Override
	public Date getModifiedDate() {
		return _serviceInfo.getModifiedDate();
	}

	/**
	* Returns the primary key of this service info.
	*
	* @return the primary key of this service info
	*/
	@Override
	public long getPrimaryKey() {
		return _serviceInfo.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _serviceInfo.getPrimaryKeyObj();
	}

	/**
	* Returns the process text of this service info.
	*
	* @return the process text of this service info
	*/
	@Override
	public String getProcessText() {
		return _serviceInfo.getProcessText();
	}

	/**
	* Returns the public_ of this service info.
	*
	* @return the public_ of this service info
	*/
	@Override
	public boolean getPublic_() {
		return _serviceInfo.getPublic_();
	}

	/**
	* Returns the regular text of this service info.
	*
	* @return the regular text of this service info
	*/
	@Override
	public String getRegularText() {
		return _serviceInfo.getRegularText();
	}

	/**
	* Returns the result text of this service info.
	*
	* @return the result text of this service info
	*/
	@Override
	public String getResultText() {
		return _serviceInfo.getResultText();
	}

	/**
	* Returns the service code of this service info.
	*
	* @return the service code of this service info
	*/
	@Override
	public String getServiceCode() {
		return _serviceInfo.getServiceCode();
	}

	/**
	* Returns the service info ID of this service info.
	*
	* @return the service info ID of this service info
	*/
	@Override
	public long getServiceInfoId() {
		return _serviceInfo.getServiceInfoId();
	}

	/**
	* Returns the service name of this service info.
	*
	* @return the service name of this service info
	*/
	@Override
	public String getServiceName() {
		return _serviceInfo.getServiceName();
	}

	/**
	* Returns the user ID of this service info.
	*
	* @return the user ID of this service info
	*/
	@Override
	public long getUserId() {
		return _serviceInfo.getUserId();
	}

	/**
	* Returns the user name of this service info.
	*
	* @return the user name of this service info
	*/
	@Override
	public String getUserName() {
		return _serviceInfo.getUserName();
	}

	/**
	* Returns the user uuid of this service info.
	*
	* @return the user uuid of this service info
	*/
	@Override
	public String getUserUuid() {
		return _serviceInfo.getUserUuid();
	}

	/**
	* Returns the uuid of this service info.
	*
	* @return the uuid of this service info
	*/
	@Override
	public String getUuid() {
		return _serviceInfo.getUuid();
	}

	@Override
	public int hashCode() {
		return _serviceInfo.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _serviceInfo.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _serviceInfo.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _serviceInfo.isNew();
	}

	/**
	* Returns <code>true</code> if this service info is public_.
	*
	* @return <code>true</code> if this service info is public_; <code>false</code> otherwise
	*/
	@Override
	public boolean isPublic_() {
		return _serviceInfo.isPublic_();
	}

	@Override
	public void persist() {
		_serviceInfo.persist();
	}

	/**
	* Sets the administration code of this service info.
	*
	* @param administrationCode the administration code of this service info
	*/
	@Override
	public void setAdministrationCode(String administrationCode) {
		_serviceInfo.setAdministrationCode(administrationCode);
	}

	/**
	* Sets the administration index of this service info.
	*
	* @param administrationIndex the administration index of this service info
	*/
	@Override
	public void setAdministrationIndex(String administrationIndex) {
		_serviceInfo.setAdministrationIndex(administrationIndex);
	}

	/**
	* Sets the administration name of this service info.
	*
	* @param administrationName the administration name of this service info
	*/
	@Override
	public void setAdministrationName(String administrationName) {
		_serviceInfo.setAdministrationName(administrationName);
	}

	/**
	* Sets the applicant text of this service info.
	*
	* @param applicantText the applicant text of this service info
	*/
	@Override
	public void setApplicantText(String applicantText) {
		_serviceInfo.setApplicantText(applicantText);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_serviceInfo.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this service info.
	*
	* @param companyId the company ID of this service info
	*/
	@Override
	public void setCompanyId(long companyId) {
		_serviceInfo.setCompanyId(companyId);
	}

	/**
	* Sets the condition text of this service info.
	*
	* @param conditionText the condition text of this service info
	*/
	@Override
	public void setConditionText(String conditionText) {
		_serviceInfo.setConditionText(conditionText);
	}

	/**
	* Sets the create date of this service info.
	*
	* @param createDate the create date of this service info
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_serviceInfo.setCreateDate(createDate);
	}

	/**
	* Sets the domain code of this service info.
	*
	* @param domainCode the domain code of this service info
	*/
	@Override
	public void setDomainCode(String domainCode) {
		_serviceInfo.setDomainCode(domainCode);
	}

	/**
	* Sets the domain index of this service info.
	*
	* @param domainIndex the domain index of this service info
	*/
	@Override
	public void setDomainIndex(String domainIndex) {
		_serviceInfo.setDomainIndex(domainIndex);
	}

	/**
	* Sets the domain name of this service info.
	*
	* @param domainName the domain name of this service info
	*/
	@Override
	public void setDomainName(String domainName) {
		_serviceInfo.setDomainName(domainName);
	}

	/**
	* Sets the dossier text of this service info.
	*
	* @param dossierText the dossier text of this service info
	*/
	@Override
	public void setDossierText(String dossierText) {
		_serviceInfo.setDossierText(dossierText);
	}

	/**
	* Sets the duration text of this service info.
	*
	* @param durationText the duration text of this service info
	*/
	@Override
	public void setDurationText(String durationText) {
		_serviceInfo.setDurationText(durationText);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_serviceInfo.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_serviceInfo.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_serviceInfo.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the fee text of this service info.
	*
	* @param feeText the fee text of this service info
	*/
	@Override
	public void setFeeText(String feeText) {
		_serviceInfo.setFeeText(feeText);
	}

	/**
	* Sets the group ID of this service info.
	*
	* @param groupId the group ID of this service info
	*/
	@Override
	public void setGroupId(long groupId) {
		_serviceInfo.setGroupId(groupId);
	}

	/**
	* Sets the max level of this service info.
	*
	* @param maxLevel the max level of this service info
	*/
	@Override
	public void setMaxLevel(int maxLevel) {
		_serviceInfo.setMaxLevel(maxLevel);
	}

	/**
	* Sets the method text of this service info.
	*
	* @param methodText the method text of this service info
	*/
	@Override
	public void setMethodText(String methodText) {
		_serviceInfo.setMethodText(methodText);
	}

	/**
	* Sets the modified date of this service info.
	*
	* @param modifiedDate the modified date of this service info
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_serviceInfo.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_serviceInfo.setNew(n);
	}

	/**
	* Sets the primary key of this service info.
	*
	* @param primaryKey the primary key of this service info
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_serviceInfo.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_serviceInfo.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the process text of this service info.
	*
	* @param processText the process text of this service info
	*/
	@Override
	public void setProcessText(String processText) {
		_serviceInfo.setProcessText(processText);
	}

	/**
	* Sets whether this service info is public_.
	*
	* @param public_ the public_ of this service info
	*/
	@Override
	public void setPublic_(boolean public_) {
		_serviceInfo.setPublic_(public_);
	}

	/**
	* Sets the regular text of this service info.
	*
	* @param regularText the regular text of this service info
	*/
	@Override
	public void setRegularText(String regularText) {
		_serviceInfo.setRegularText(regularText);
	}

	/**
	* Sets the result text of this service info.
	*
	* @param resultText the result text of this service info
	*/
	@Override
	public void setResultText(String resultText) {
		_serviceInfo.setResultText(resultText);
	}

	/**
	* Sets the service code of this service info.
	*
	* @param serviceCode the service code of this service info
	*/
	@Override
	public void setServiceCode(String serviceCode) {
		_serviceInfo.setServiceCode(serviceCode);
	}

	/**
	* Sets the service info ID of this service info.
	*
	* @param serviceInfoId the service info ID of this service info
	*/
	@Override
	public void setServiceInfoId(long serviceInfoId) {
		_serviceInfo.setServiceInfoId(serviceInfoId);
	}

	/**
	* Sets the service name of this service info.
	*
	* @param serviceName the service name of this service info
	*/
	@Override
	public void setServiceName(String serviceName) {
		_serviceInfo.setServiceName(serviceName);
	}

	/**
	* Sets the user ID of this service info.
	*
	* @param userId the user ID of this service info
	*/
	@Override
	public void setUserId(long userId) {
		_serviceInfo.setUserId(userId);
	}

	/**
	* Sets the user name of this service info.
	*
	* @param userName the user name of this service info
	*/
	@Override
	public void setUserName(String userName) {
		_serviceInfo.setUserName(userName);
	}

	/**
	* Sets the user uuid of this service info.
	*
	* @param userUuid the user uuid of this service info
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_serviceInfo.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this service info.
	*
	* @param uuid the uuid of this service info
	*/
	@Override
	public void setUuid(String uuid) {
		_serviceInfo.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ServiceInfo> toCacheModel() {
		return _serviceInfo.toCacheModel();
	}

	@Override
	public ServiceInfo toEscapedModel() {
		return new ServiceInfoWrapper(_serviceInfo.toEscapedModel());
	}

	@Override
	public String toString() {
		return _serviceInfo.toString();
	}

	@Override
	public ServiceInfo toUnescapedModel() {
		return new ServiceInfoWrapper(_serviceInfo.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _serviceInfo.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ServiceInfoWrapper)) {
			return false;
		}

		ServiceInfoWrapper serviceInfoWrapper = (ServiceInfoWrapper)obj;

		if (Objects.equals(_serviceInfo, serviceInfoWrapper._serviceInfo)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _serviceInfo.getStagedModelType();
	}

	@Override
	public ServiceInfo getWrappedModel() {
		return _serviceInfo;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _serviceInfo.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _serviceInfo.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_serviceInfo.resetOriginalValues();
	}

	private final ServiceInfo _serviceInfo;
}