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
 * This class is a wrapper for {@link ProcessOption}.
 * </p>
 *
 * @author huymq
 * @see ProcessOption
 * @generated
 */
@ProviderType
public class ProcessOptionWrapper implements ProcessOption,
	ModelWrapper<ProcessOption> {
	public ProcessOptionWrapper(ProcessOption processOption) {
		_processOption = processOption;
	}

	@Override
	public Class<?> getModelClass() {
		return ProcessOption.class;
	}

	@Override
	public String getModelClassName() {
		return ProcessOption.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("processOptionId", getProcessOptionId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("serviceConfigId", getServiceConfigId());
		attributes.put("optionOrder", getOptionOrder());
		attributes.put("optionName", getOptionName());
		attributes.put("autoSelect", getAutoSelect());
		attributes.put("dossierTemplateId", getDossierTemplateId());
		attributes.put("serviceProcessId", getServiceProcessId());
		attributes.put("instructionNote", getInstructionNote());
		attributes.put("submissionNote", getSubmissionNote());
		attributes.put("sampleCount", getSampleCount());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long processOptionId = (Long)attributes.get("processOptionId");

		if (processOptionId != null) {
			setProcessOptionId(processOptionId);
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

		Long serviceConfigId = (Long)attributes.get("serviceConfigId");

		if (serviceConfigId != null) {
			setServiceConfigId(serviceConfigId);
		}

		Integer optionOrder = (Integer)attributes.get("optionOrder");

		if (optionOrder != null) {
			setOptionOrder(optionOrder);
		}

		String optionName = (String)attributes.get("optionName");

		if (optionName != null) {
			setOptionName(optionName);
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

		String submissionNote = (String)attributes.get("submissionNote");

		if (submissionNote != null) {
			setSubmissionNote(submissionNote);
		}

		Long sampleCount = (Long)attributes.get("sampleCount");

		if (sampleCount != null) {
			setSampleCount(sampleCount);
		}
	}

	@Override
	public Object clone() {
		return new ProcessOptionWrapper((ProcessOption)_processOption.clone());
	}

	@Override
	public int compareTo(ProcessOption processOption) {
		return _processOption.compareTo(processOption);
	}

	/**
	* Returns the auto select of this process option.
	*
	* @return the auto select of this process option
	*/
	@Override
	public String getAutoSelect() {
		return _processOption.getAutoSelect();
	}

	/**
	* Returns the company ID of this process option.
	*
	* @return the company ID of this process option
	*/
	@Override
	public long getCompanyId() {
		return _processOption.getCompanyId();
	}

	/**
	* Returns the create date of this process option.
	*
	* @return the create date of this process option
	*/
	@Override
	public Date getCreateDate() {
		return _processOption.getCreateDate();
	}

	/**
	* Returns the dossier template ID of this process option.
	*
	* @return the dossier template ID of this process option
	*/
	@Override
	public long getDossierTemplateId() {
		return _processOption.getDossierTemplateId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _processOption.getExpandoBridge();
	}

	/**
	* Returns the group ID of this process option.
	*
	* @return the group ID of this process option
	*/
	@Override
	public long getGroupId() {
		return _processOption.getGroupId();
	}

	/**
	* Returns the instruction note of this process option.
	*
	* @return the instruction note of this process option
	*/
	@Override
	public String getInstructionNote() {
		return _processOption.getInstructionNote();
	}

	/**
	* Returns the modified date of this process option.
	*
	* @return the modified date of this process option
	*/
	@Override
	public Date getModifiedDate() {
		return _processOption.getModifiedDate();
	}

	/**
	* Returns the option name of this process option.
	*
	* @return the option name of this process option
	*/
	@Override
	public String getOptionName() {
		return _processOption.getOptionName();
	}

	/**
	* Returns the option order of this process option.
	*
	* @return the option order of this process option
	*/
	@Override
	public int getOptionOrder() {
		return _processOption.getOptionOrder();
	}

	/**
	* Returns the primary key of this process option.
	*
	* @return the primary key of this process option
	*/
	@Override
	public long getPrimaryKey() {
		return _processOption.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _processOption.getPrimaryKeyObj();
	}

	/**
	* Returns the process option ID of this process option.
	*
	* @return the process option ID of this process option
	*/
	@Override
	public long getProcessOptionId() {
		return _processOption.getProcessOptionId();
	}

	/**
	* Returns the sample count of this process option.
	*
	* @return the sample count of this process option
	*/
	@Override
	public long getSampleCount() {
		return _processOption.getSampleCount();
	}

	/**
	* Returns the service config ID of this process option.
	*
	* @return the service config ID of this process option
	*/
	@Override
	public long getServiceConfigId() {
		return _processOption.getServiceConfigId();
	}

	/**
	* Returns the service process ID of this process option.
	*
	* @return the service process ID of this process option
	*/
	@Override
	public long getServiceProcessId() {
		return _processOption.getServiceProcessId();
	}

	/**
	* Returns the submission note of this process option.
	*
	* @return the submission note of this process option
	*/
	@Override
	public String getSubmissionNote() {
		return _processOption.getSubmissionNote();
	}

	/**
	* Returns the user ID of this process option.
	*
	* @return the user ID of this process option
	*/
	@Override
	public long getUserId() {
		return _processOption.getUserId();
	}

	/**
	* Returns the user name of this process option.
	*
	* @return the user name of this process option
	*/
	@Override
	public String getUserName() {
		return _processOption.getUserName();
	}

	/**
	* Returns the user uuid of this process option.
	*
	* @return the user uuid of this process option
	*/
	@Override
	public String getUserUuid() {
		return _processOption.getUserUuid();
	}

	/**
	* Returns the uuid of this process option.
	*
	* @return the uuid of this process option
	*/
	@Override
	public String getUuid() {
		return _processOption.getUuid();
	}

	@Override
	public int hashCode() {
		return _processOption.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _processOption.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _processOption.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _processOption.isNew();
	}

	@Override
	public void persist() {
		_processOption.persist();
	}

	/**
	* Sets the auto select of this process option.
	*
	* @param autoSelect the auto select of this process option
	*/
	@Override
	public void setAutoSelect(String autoSelect) {
		_processOption.setAutoSelect(autoSelect);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_processOption.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this process option.
	*
	* @param companyId the company ID of this process option
	*/
	@Override
	public void setCompanyId(long companyId) {
		_processOption.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this process option.
	*
	* @param createDate the create date of this process option
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_processOption.setCreateDate(createDate);
	}

	/**
	* Sets the dossier template ID of this process option.
	*
	* @param dossierTemplateId the dossier template ID of this process option
	*/
	@Override
	public void setDossierTemplateId(long dossierTemplateId) {
		_processOption.setDossierTemplateId(dossierTemplateId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_processOption.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_processOption.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_processOption.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this process option.
	*
	* @param groupId the group ID of this process option
	*/
	@Override
	public void setGroupId(long groupId) {
		_processOption.setGroupId(groupId);
	}

	/**
	* Sets the instruction note of this process option.
	*
	* @param instructionNote the instruction note of this process option
	*/
	@Override
	public void setInstructionNote(String instructionNote) {
		_processOption.setInstructionNote(instructionNote);
	}

	/**
	* Sets the modified date of this process option.
	*
	* @param modifiedDate the modified date of this process option
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_processOption.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_processOption.setNew(n);
	}

	/**
	* Sets the option name of this process option.
	*
	* @param optionName the option name of this process option
	*/
	@Override
	public void setOptionName(String optionName) {
		_processOption.setOptionName(optionName);
	}

	/**
	* Sets the option order of this process option.
	*
	* @param optionOrder the option order of this process option
	*/
	@Override
	public void setOptionOrder(int optionOrder) {
		_processOption.setOptionOrder(optionOrder);
	}

	/**
	* Sets the primary key of this process option.
	*
	* @param primaryKey the primary key of this process option
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_processOption.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_processOption.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the process option ID of this process option.
	*
	* @param processOptionId the process option ID of this process option
	*/
	@Override
	public void setProcessOptionId(long processOptionId) {
		_processOption.setProcessOptionId(processOptionId);
	}

	/**
	* Sets the sample count of this process option.
	*
	* @param sampleCount the sample count of this process option
	*/
	@Override
	public void setSampleCount(long sampleCount) {
		_processOption.setSampleCount(sampleCount);
	}

	/**
	* Sets the service config ID of this process option.
	*
	* @param serviceConfigId the service config ID of this process option
	*/
	@Override
	public void setServiceConfigId(long serviceConfigId) {
		_processOption.setServiceConfigId(serviceConfigId);
	}

	/**
	* Sets the service process ID of this process option.
	*
	* @param serviceProcessId the service process ID of this process option
	*/
	@Override
	public void setServiceProcessId(long serviceProcessId) {
		_processOption.setServiceProcessId(serviceProcessId);
	}

	/**
	* Sets the submission note of this process option.
	*
	* @param submissionNote the submission note of this process option
	*/
	@Override
	public void setSubmissionNote(String submissionNote) {
		_processOption.setSubmissionNote(submissionNote);
	}

	/**
	* Sets the user ID of this process option.
	*
	* @param userId the user ID of this process option
	*/
	@Override
	public void setUserId(long userId) {
		_processOption.setUserId(userId);
	}

	/**
	* Sets the user name of this process option.
	*
	* @param userName the user name of this process option
	*/
	@Override
	public void setUserName(String userName) {
		_processOption.setUserName(userName);
	}

	/**
	* Sets the user uuid of this process option.
	*
	* @param userUuid the user uuid of this process option
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_processOption.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this process option.
	*
	* @param uuid the uuid of this process option
	*/
	@Override
	public void setUuid(String uuid) {
		_processOption.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ProcessOption> toCacheModel() {
		return _processOption.toCacheModel();
	}

	@Override
	public ProcessOption toEscapedModel() {
		return new ProcessOptionWrapper(_processOption.toEscapedModel());
	}

	@Override
	public String toString() {
		return _processOption.toString();
	}

	@Override
	public ProcessOption toUnescapedModel() {
		return new ProcessOptionWrapper(_processOption.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _processOption.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProcessOptionWrapper)) {
			return false;
		}

		ProcessOptionWrapper processOptionWrapper = (ProcessOptionWrapper)obj;

		if (Objects.equals(_processOption, processOptionWrapper._processOption)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _processOption.getStagedModelType();
	}

	@Override
	public ProcessOption getWrappedModel() {
		return _processOption;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _processOption.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _processOption.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_processOption.resetOriginalValues();
	}

	private final ProcessOption _processOption;
}