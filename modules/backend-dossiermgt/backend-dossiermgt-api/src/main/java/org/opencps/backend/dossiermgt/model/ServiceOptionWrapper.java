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
 * This class is a wrapper for {@link ServiceOption}.
 * </p>
 *
 * @author huymq
 * @see ServiceOption
 * @generated
 */
@ProviderType
public class ServiceOptionWrapper implements ServiceOption,
	ModelWrapper<ServiceOption> {
	public ServiceOptionWrapper(ServiceOption serviceOption) {
		_serviceOption = serviceOption;
	}

	@Override
	public Class<?> getModelClass() {
		return ServiceOption.class;
	}

	@Override
	public String getModelClassName() {
		return ServiceOption.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("serviceOptionId", getServiceOptionId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("optionCode", getOptionCode());
		attributes.put("optionName", getOptionName());
		attributes.put("optionOrder", getOptionOrder());
		attributes.put("autoSelect", getAutoSelect());
		attributes.put("dossierTemplateId", getDossierTemplateId());
		attributes.put("serviceProcessId", getServiceProcessId());
		attributes.put("instructionNote", getInstructionNote());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long serviceOptionId = (Long)attributes.get("serviceOptionId");

		if (serviceOptionId != null) {
			setServiceOptionId(serviceOptionId);
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

		String optionCode = (String)attributes.get("optionCode");

		if (optionCode != null) {
			setOptionCode(optionCode);
		}

		String optionName = (String)attributes.get("optionName");

		if (optionName != null) {
			setOptionName(optionName);
		}

		Integer optionOrder = (Integer)attributes.get("optionOrder");

		if (optionOrder != null) {
			setOptionOrder(optionOrder);
		}

		String autoSelect = (String)attributes.get("autoSelect");

		if (autoSelect != null) {
			setAutoSelect(autoSelect);
		}

		Long dossierTemplateId = (Long)attributes.get("dossierTemplateId");

		if (dossierTemplateId != null) {
			setDossierTemplateId(dossierTemplateId);
		}

		Long serviceProcessId = (Long)attributes.get("serviceProcessId");

		if (serviceProcessId != null) {
			setServiceProcessId(serviceProcessId);
		}

		String instructionNote = (String)attributes.get("instructionNote");

		if (instructionNote != null) {
			setInstructionNote(instructionNote);
		}
	}

	@Override
	public ServiceOption toEscapedModel() {
		return new ServiceOptionWrapper(_serviceOption.toEscapedModel());
	}

	@Override
	public ServiceOption toUnescapedModel() {
		return new ServiceOptionWrapper(_serviceOption.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _serviceOption.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _serviceOption.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _serviceOption.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _serviceOption.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ServiceOption> toCacheModel() {
		return _serviceOption.toCacheModel();
	}

	@Override
	public int compareTo(ServiceOption serviceOption) {
		return _serviceOption.compareTo(serviceOption);
	}

	/**
	* Returns the option order of this service option.
	*
	* @return the option order of this service option
	*/
	@Override
	public int getOptionOrder() {
		return _serviceOption.getOptionOrder();
	}

	@Override
	public int hashCode() {
		return _serviceOption.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _serviceOption.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new ServiceOptionWrapper((ServiceOption)_serviceOption.clone());
	}

	/**
	* Returns the auto select of this service option.
	*
	* @return the auto select of this service option
	*/
	@Override
	public java.lang.String getAutoSelect() {
		return _serviceOption.getAutoSelect();
	}

	/**
	* Returns the instruction note of this service option.
	*
	* @return the instruction note of this service option
	*/
	@Override
	public java.lang.String getInstructionNote() {
		return _serviceOption.getInstructionNote();
	}

	/**
	* Returns the option code of this service option.
	*
	* @return the option code of this service option
	*/
	@Override
	public java.lang.String getOptionCode() {
		return _serviceOption.getOptionCode();
	}

	/**
	* Returns the option name of this service option.
	*
	* @return the option name of this service option
	*/
	@Override
	public java.lang.String getOptionName() {
		return _serviceOption.getOptionName();
	}

	/**
	* Returns the user name of this service option.
	*
	* @return the user name of this service option
	*/
	@Override
	public java.lang.String getUserName() {
		return _serviceOption.getUserName();
	}

	/**
	* Returns the user uuid of this service option.
	*
	* @return the user uuid of this service option
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _serviceOption.getUserUuid();
	}

	/**
	* Returns the uuid of this service option.
	*
	* @return the uuid of this service option
	*/
	@Override
	public java.lang.String getUuid() {
		return _serviceOption.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _serviceOption.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _serviceOption.toXmlString();
	}

	/**
	* Returns the create date of this service option.
	*
	* @return the create date of this service option
	*/
	@Override
	public Date getCreateDate() {
		return _serviceOption.getCreateDate();
	}

	/**
	* Returns the modified date of this service option.
	*
	* @return the modified date of this service option
	*/
	@Override
	public Date getModifiedDate() {
		return _serviceOption.getModifiedDate();
	}

	/**
	* Returns the company ID of this service option.
	*
	* @return the company ID of this service option
	*/
	@Override
	public long getCompanyId() {
		return _serviceOption.getCompanyId();
	}

	/**
	* Returns the dossier template ID of this service option.
	*
	* @return the dossier template ID of this service option
	*/
	@Override
	public long getDossierTemplateId() {
		return _serviceOption.getDossierTemplateId();
	}

	/**
	* Returns the group ID of this service option.
	*
	* @return the group ID of this service option
	*/
	@Override
	public long getGroupId() {
		return _serviceOption.getGroupId();
	}

	/**
	* Returns the primary key of this service option.
	*
	* @return the primary key of this service option
	*/
	@Override
	public long getPrimaryKey() {
		return _serviceOption.getPrimaryKey();
	}

	/**
	* Returns the service option ID of this service option.
	*
	* @return the service option ID of this service option
	*/
	@Override
	public long getServiceOptionId() {
		return _serviceOption.getServiceOptionId();
	}

	/**
	* Returns the service process ID of this service option.
	*
	* @return the service process ID of this service option
	*/
	@Override
	public long getServiceProcessId() {
		return _serviceOption.getServiceProcessId();
	}

	/**
	* Returns the user ID of this service option.
	*
	* @return the user ID of this service option
	*/
	@Override
	public long getUserId() {
		return _serviceOption.getUserId();
	}

	@Override
	public void persist() {
		_serviceOption.persist();
	}

	/**
	* Sets the auto select of this service option.
	*
	* @param autoSelect the auto select of this service option
	*/
	@Override
	public void setAutoSelect(java.lang.String autoSelect) {
		_serviceOption.setAutoSelect(autoSelect);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_serviceOption.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this service option.
	*
	* @param companyId the company ID of this service option
	*/
	@Override
	public void setCompanyId(long companyId) {
		_serviceOption.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this service option.
	*
	* @param createDate the create date of this service option
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_serviceOption.setCreateDate(createDate);
	}

	/**
	* Sets the dossier template ID of this service option.
	*
	* @param dossierTemplateId the dossier template ID of this service option
	*/
	@Override
	public void setDossierTemplateId(long dossierTemplateId) {
		_serviceOption.setDossierTemplateId(dossierTemplateId);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_serviceOption.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_serviceOption.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_serviceOption.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this service option.
	*
	* @param groupId the group ID of this service option
	*/
	@Override
	public void setGroupId(long groupId) {
		_serviceOption.setGroupId(groupId);
	}

	/**
	* Sets the instruction note of this service option.
	*
	* @param instructionNote the instruction note of this service option
	*/
	@Override
	public void setInstructionNote(java.lang.String instructionNote) {
		_serviceOption.setInstructionNote(instructionNote);
	}

	/**
	* Sets the modified date of this service option.
	*
	* @param modifiedDate the modified date of this service option
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_serviceOption.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_serviceOption.setNew(n);
	}

	/**
	* Sets the option code of this service option.
	*
	* @param optionCode the option code of this service option
	*/
	@Override
	public void setOptionCode(java.lang.String optionCode) {
		_serviceOption.setOptionCode(optionCode);
	}

	/**
	* Sets the option name of this service option.
	*
	* @param optionName the option name of this service option
	*/
	@Override
	public void setOptionName(java.lang.String optionName) {
		_serviceOption.setOptionName(optionName);
	}

	/**
	* Sets the option order of this service option.
	*
	* @param optionOrder the option order of this service option
	*/
	@Override
	public void setOptionOrder(int optionOrder) {
		_serviceOption.setOptionOrder(optionOrder);
	}

	/**
	* Sets the primary key of this service option.
	*
	* @param primaryKey the primary key of this service option
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_serviceOption.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_serviceOption.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the service option ID of this service option.
	*
	* @param serviceOptionId the service option ID of this service option
	*/
	@Override
	public void setServiceOptionId(long serviceOptionId) {
		_serviceOption.setServiceOptionId(serviceOptionId);
	}

	/**
	* Sets the service process ID of this service option.
	*
	* @param serviceProcessId the service process ID of this service option
	*/
	@Override
	public void setServiceProcessId(long serviceProcessId) {
		_serviceOption.setServiceProcessId(serviceProcessId);
	}

	/**
	* Sets the user ID of this service option.
	*
	* @param userId the user ID of this service option
	*/
	@Override
	public void setUserId(long userId) {
		_serviceOption.setUserId(userId);
	}

	/**
	* Sets the user name of this service option.
	*
	* @param userName the user name of this service option
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_serviceOption.setUserName(userName);
	}

	/**
	* Sets the user uuid of this service option.
	*
	* @param userUuid the user uuid of this service option
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_serviceOption.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this service option.
	*
	* @param uuid the uuid of this service option
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_serviceOption.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ServiceOptionWrapper)) {
			return false;
		}

		ServiceOptionWrapper serviceOptionWrapper = (ServiceOptionWrapper)obj;

		if (Objects.equals(_serviceOption, serviceOptionWrapper._serviceOption)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _serviceOption.getStagedModelType();
	}

	@Override
	public ServiceOption getWrappedModel() {
		return _serviceOption;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _serviceOption.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _serviceOption.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_serviceOption.resetOriginalValues();
	}

	private final ServiceOption _serviceOption;
}