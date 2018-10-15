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
 * This class is a wrapper for {@link ProcessStep}.
 * </p>
 *
 * @author huymq
 * @see ProcessStep
 * @generated
 */
@ProviderType
public class ProcessStepWrapper implements ProcessStep,
	ModelWrapper<ProcessStep> {
	public ProcessStepWrapper(ProcessStep processStep) {
		_processStep = processStep;
	}

	@Override
	public Class<?> getModelClass() {
		return ProcessStep.class;
	}

	@Override
	public String getModelClassName() {
		return ProcessStep.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("processStepId", getProcessStepId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("stepCode", getStepCode());
		attributes.put("serviceProcessId", getServiceProcessId());
		attributes.put("stepName", getStepName());
		attributes.put("sequenceNo", getSequenceNo());
		attributes.put("dossierStatus", getDossierStatus());
		attributes.put("dossierSubStatus", getDossierSubStatus());
		attributes.put("durationCount", getDurationCount());
		attributes.put("customProcessUrl", getCustomProcessUrl());
		attributes.put("stepInstruction", getStepInstruction());
		attributes.put("briefNote", getBriefNote());
		attributes.put("editable", isEditable());
		attributes.put("restrictDossier", getRestrictDossier());
		attributes.put("lockState", getLockState());
		attributes.put("groupName", getGroupName());
		attributes.put("roleAsStep", getRoleAsStep());
		attributes.put("checkInput", getCheckInput());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long processStepId = (Long)attributes.get("processStepId");

		if (processStepId != null) {
			setProcessStepId(processStepId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
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

		String stepCode = (String)attributes.get("stepCode");

		if (stepCode != null) {
			setStepCode(stepCode);
		}

		Long serviceProcessId = (Long)attributes.get("serviceProcessId");

		if (serviceProcessId != null) {
			setServiceProcessId(serviceProcessId);
		}

		String stepName = (String)attributes.get("stepName");

		if (stepName != null) {
			setStepName(stepName);
		}

		String sequenceNo = (String)attributes.get("sequenceNo");

		if (sequenceNo != null) {
			setSequenceNo(sequenceNo);
		}

		String dossierStatus = (String)attributes.get("dossierStatus");

		if (dossierStatus != null) {
			setDossierStatus(dossierStatus);
		}

		String dossierSubStatus = (String)attributes.get("dossierSubStatus");

		if (dossierSubStatus != null) {
			setDossierSubStatus(dossierSubStatus);
		}

		Double durationCount = (Double)attributes.get("durationCount");

		if (durationCount != null) {
			setDurationCount(durationCount);
		}

		String customProcessUrl = (String)attributes.get("customProcessUrl");

		if (customProcessUrl != null) {
			setCustomProcessUrl(customProcessUrl);
		}

		String stepInstruction = (String)attributes.get("stepInstruction");

		if (stepInstruction != null) {
			setStepInstruction(stepInstruction);
		}

		String briefNote = (String)attributes.get("briefNote");

		if (briefNote != null) {
			setBriefNote(briefNote);
		}

		Boolean editable = (Boolean)attributes.get("editable");

		if (editable != null) {
			setEditable(editable);
		}

		String restrictDossier = (String)attributes.get("restrictDossier");

		if (restrictDossier != null) {
			setRestrictDossier(restrictDossier);
		}

		String lockState = (String)attributes.get("lockState");

		if (lockState != null) {
			setLockState(lockState);
		}

		String groupName = (String)attributes.get("groupName");

		if (groupName != null) {
			setGroupName(groupName);
		}

		String roleAsStep = (String)attributes.get("roleAsStep");

		if (roleAsStep != null) {
			setRoleAsStep(roleAsStep);
		}

		Integer checkInput = (Integer)attributes.get("checkInput");

		if (checkInput != null) {
			setCheckInput(checkInput);
		}
	}

	@Override
	public Object clone() {
		return new ProcessStepWrapper((ProcessStep)_processStep.clone());
	}

	@Override
	public int compareTo(ProcessStep processStep) {
		return _processStep.compareTo(processStep);
	}

	/**
	* Returns the brief note of this process step.
	*
	* @return the brief note of this process step
	*/
	@Override
	public String getBriefNote() {
		return _processStep.getBriefNote();
	}

	/**
	* Returns the check input of this process step.
	*
	* @return the check input of this process step
	*/
	@Override
	public int getCheckInput() {
		return _processStep.getCheckInput();
	}

	/**
	* Returns the company ID of this process step.
	*
	* @return the company ID of this process step
	*/
	@Override
	public long getCompanyId() {
		return _processStep.getCompanyId();
	}

	/**
	* Returns the create date of this process step.
	*
	* @return the create date of this process step
	*/
	@Override
	public Date getCreateDate() {
		return _processStep.getCreateDate();
	}

	/**
	* Returns the custom process url of this process step.
	*
	* @return the custom process url of this process step
	*/
	@Override
	public String getCustomProcessUrl() {
		return _processStep.getCustomProcessUrl();
	}

	/**
	* Returns the dossier status of this process step.
	*
	* @return the dossier status of this process step
	*/
	@Override
	public String getDossierStatus() {
		return _processStep.getDossierStatus();
	}

	/**
	* Returns the dossier sub status of this process step.
	*
	* @return the dossier sub status of this process step
	*/
	@Override
	public String getDossierSubStatus() {
		return _processStep.getDossierSubStatus();
	}

	/**
	* Returns the duration count of this process step.
	*
	* @return the duration count of this process step
	*/
	@Override
	public double getDurationCount() {
		return _processStep.getDurationCount();
	}

	/**
	* Returns the editable of this process step.
	*
	* @return the editable of this process step
	*/
	@Override
	public boolean getEditable() {
		return _processStep.getEditable();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _processStep.getExpandoBridge();
	}

	/**
	* Returns the group ID of this process step.
	*
	* @return the group ID of this process step
	*/
	@Override
	public long getGroupId() {
		return _processStep.getGroupId();
	}

	/**
	* Returns the group name of this process step.
	*
	* @return the group name of this process step
	*/
	@Override
	public String getGroupName() {
		return _processStep.getGroupName();
	}

	/**
	* Returns the lock state of this process step.
	*
	* @return the lock state of this process step
	*/
	@Override
	public String getLockState() {
		return _processStep.getLockState();
	}

	/**
	* Returns the modified date of this process step.
	*
	* @return the modified date of this process step
	*/
	@Override
	public Date getModifiedDate() {
		return _processStep.getModifiedDate();
	}

	/**
	* Returns the primary key of this process step.
	*
	* @return the primary key of this process step
	*/
	@Override
	public long getPrimaryKey() {
		return _processStep.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _processStep.getPrimaryKeyObj();
	}

	/**
	* Returns the process step ID of this process step.
	*
	* @return the process step ID of this process step
	*/
	@Override
	public long getProcessStepId() {
		return _processStep.getProcessStepId();
	}

	/**
	* Returns the restrict dossier of this process step.
	*
	* @return the restrict dossier of this process step
	*/
	@Override
	public String getRestrictDossier() {
		return _processStep.getRestrictDossier();
	}

	/**
	* Returns the role as step of this process step.
	*
	* @return the role as step of this process step
	*/
	@Override
	public String getRoleAsStep() {
		return _processStep.getRoleAsStep();
	}

	/**
	* Returns the sequence no of this process step.
	*
	* @return the sequence no of this process step
	*/
	@Override
	public String getSequenceNo() {
		return _processStep.getSequenceNo();
	}

	/**
	* Returns the service process ID of this process step.
	*
	* @return the service process ID of this process step
	*/
	@Override
	public long getServiceProcessId() {
		return _processStep.getServiceProcessId();
	}

	/**
	* Returns the step code of this process step.
	*
	* @return the step code of this process step
	*/
	@Override
	public String getStepCode() {
		return _processStep.getStepCode();
	}

	/**
	* Returns the step instruction of this process step.
	*
	* @return the step instruction of this process step
	*/
	@Override
	public String getStepInstruction() {
		return _processStep.getStepInstruction();
	}

	/**
	* Returns the step name of this process step.
	*
	* @return the step name of this process step
	*/
	@Override
	public String getStepName() {
		return _processStep.getStepName();
	}

	/**
	* Returns the user ID of this process step.
	*
	* @return the user ID of this process step
	*/
	@Override
	public long getUserId() {
		return _processStep.getUserId();
	}

	/**
	* Returns the user name of this process step.
	*
	* @return the user name of this process step
	*/
	@Override
	public String getUserName() {
		return _processStep.getUserName();
	}

	/**
	* Returns the user uuid of this process step.
	*
	* @return the user uuid of this process step
	*/
	@Override
	public String getUserUuid() {
		return _processStep.getUserUuid();
	}

	/**
	* Returns the uuid of this process step.
	*
	* @return the uuid of this process step
	*/
	@Override
	public String getUuid() {
		return _processStep.getUuid();
	}

	@Override
	public int hashCode() {
		return _processStep.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _processStep.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this process step is editable.
	*
	* @return <code>true</code> if this process step is editable; <code>false</code> otherwise
	*/
	@Override
	public boolean isEditable() {
		return _processStep.isEditable();
	}

	@Override
	public boolean isEscapedModel() {
		return _processStep.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _processStep.isNew();
	}

	@Override
	public void persist() {
		_processStep.persist();
	}

	/**
	* Sets the brief note of this process step.
	*
	* @param briefNote the brief note of this process step
	*/
	@Override
	public void setBriefNote(String briefNote) {
		_processStep.setBriefNote(briefNote);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_processStep.setCachedModel(cachedModel);
	}

	/**
	* Sets the check input of this process step.
	*
	* @param checkInput the check input of this process step
	*/
	@Override
	public void setCheckInput(int checkInput) {
		_processStep.setCheckInput(checkInput);
	}

	/**
	* Sets the company ID of this process step.
	*
	* @param companyId the company ID of this process step
	*/
	@Override
	public void setCompanyId(long companyId) {
		_processStep.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this process step.
	*
	* @param createDate the create date of this process step
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_processStep.setCreateDate(createDate);
	}

	/**
	* Sets the custom process url of this process step.
	*
	* @param customProcessUrl the custom process url of this process step
	*/
	@Override
	public void setCustomProcessUrl(String customProcessUrl) {
		_processStep.setCustomProcessUrl(customProcessUrl);
	}

	/**
	* Sets the dossier status of this process step.
	*
	* @param dossierStatus the dossier status of this process step
	*/
	@Override
	public void setDossierStatus(String dossierStatus) {
		_processStep.setDossierStatus(dossierStatus);
	}

	/**
	* Sets the dossier sub status of this process step.
	*
	* @param dossierSubStatus the dossier sub status of this process step
	*/
	@Override
	public void setDossierSubStatus(String dossierSubStatus) {
		_processStep.setDossierSubStatus(dossierSubStatus);
	}

	/**
	* Sets the duration count of this process step.
	*
	* @param durationCount the duration count of this process step
	*/
	@Override
	public void setDurationCount(double durationCount) {
		_processStep.setDurationCount(durationCount);
	}

	/**
	* Sets whether this process step is editable.
	*
	* @param editable the editable of this process step
	*/
	@Override
	public void setEditable(boolean editable) {
		_processStep.setEditable(editable);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_processStep.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_processStep.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_processStep.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this process step.
	*
	* @param groupId the group ID of this process step
	*/
	@Override
	public void setGroupId(long groupId) {
		_processStep.setGroupId(groupId);
	}

	/**
	* Sets the group name of this process step.
	*
	* @param groupName the group name of this process step
	*/
	@Override
	public void setGroupName(String groupName) {
		_processStep.setGroupName(groupName);
	}

	/**
	* Sets the lock state of this process step.
	*
	* @param lockState the lock state of this process step
	*/
	@Override
	public void setLockState(String lockState) {
		_processStep.setLockState(lockState);
	}

	/**
	* Sets the modified date of this process step.
	*
	* @param modifiedDate the modified date of this process step
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_processStep.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_processStep.setNew(n);
	}

	/**
	* Sets the primary key of this process step.
	*
	* @param primaryKey the primary key of this process step
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_processStep.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_processStep.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the process step ID of this process step.
	*
	* @param processStepId the process step ID of this process step
	*/
	@Override
	public void setProcessStepId(long processStepId) {
		_processStep.setProcessStepId(processStepId);
	}

	/**
	* Sets the restrict dossier of this process step.
	*
	* @param restrictDossier the restrict dossier of this process step
	*/
	@Override
	public void setRestrictDossier(String restrictDossier) {
		_processStep.setRestrictDossier(restrictDossier);
	}

	/**
	* Sets the role as step of this process step.
	*
	* @param roleAsStep the role as step of this process step
	*/
	@Override
	public void setRoleAsStep(String roleAsStep) {
		_processStep.setRoleAsStep(roleAsStep);
	}

	/**
	* Sets the sequence no of this process step.
	*
	* @param sequenceNo the sequence no of this process step
	*/
	@Override
	public void setSequenceNo(String sequenceNo) {
		_processStep.setSequenceNo(sequenceNo);
	}

	/**
	* Sets the service process ID of this process step.
	*
	* @param serviceProcessId the service process ID of this process step
	*/
	@Override
	public void setServiceProcessId(long serviceProcessId) {
		_processStep.setServiceProcessId(serviceProcessId);
	}

	/**
	* Sets the step code of this process step.
	*
	* @param stepCode the step code of this process step
	*/
	@Override
	public void setStepCode(String stepCode) {
		_processStep.setStepCode(stepCode);
	}

	/**
	* Sets the step instruction of this process step.
	*
	* @param stepInstruction the step instruction of this process step
	*/
	@Override
	public void setStepInstruction(String stepInstruction) {
		_processStep.setStepInstruction(stepInstruction);
	}

	/**
	* Sets the step name of this process step.
	*
	* @param stepName the step name of this process step
	*/
	@Override
	public void setStepName(String stepName) {
		_processStep.setStepName(stepName);
	}

	/**
	* Sets the user ID of this process step.
	*
	* @param userId the user ID of this process step
	*/
	@Override
	public void setUserId(long userId) {
		_processStep.setUserId(userId);
	}

	/**
	* Sets the user name of this process step.
	*
	* @param userName the user name of this process step
	*/
	@Override
	public void setUserName(String userName) {
		_processStep.setUserName(userName);
	}

	/**
	* Sets the user uuid of this process step.
	*
	* @param userUuid the user uuid of this process step
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_processStep.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this process step.
	*
	* @param uuid the uuid of this process step
	*/
	@Override
	public void setUuid(String uuid) {
		_processStep.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ProcessStep> toCacheModel() {
		return _processStep.toCacheModel();
	}

	@Override
	public ProcessStep toEscapedModel() {
		return new ProcessStepWrapper(_processStep.toEscapedModel());
	}

	@Override
	public String toString() {
		return _processStep.toString();
	}

	@Override
	public ProcessStep toUnescapedModel() {
		return new ProcessStepWrapper(_processStep.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _processStep.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProcessStepWrapper)) {
			return false;
		}

		ProcessStepWrapper processStepWrapper = (ProcessStepWrapper)obj;

		if (Objects.equals(_processStep, processStepWrapper._processStep)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _processStep.getStagedModelType();
	}

	@Override
	public ProcessStep getWrappedModel() {
		return _processStep;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _processStep.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _processStep.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_processStep.resetOriginalValues();
	}

	private final ProcessStep _processStep;
}