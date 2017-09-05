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

package org.opencps.backend.processmgt.model;

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
 * This class is a wrapper for {@link StepAllowance}.
 * </p>
 *
 * @author khoavu
 * @see StepAllowance
 * @generated
 */
@ProviderType
public class StepAllowanceWrapper implements StepAllowance,
	ModelWrapper<StepAllowance> {
	public StepAllowanceWrapper(StepAllowance stepAllowance) {
		_stepAllowance = stepAllowance;
	}

	@Override
	public Class<?> getModelClass() {
		return StepAllowance.class;
	}

	@Override
	public String getModelClassName() {
		return StepAllowance.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("processStepId", getProcessStepId());
		attributes.put("roleId", getRoleId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("moderator", getModerator());

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

		Long roleId = (Long)attributes.get("roleId");

		if (roleId != null) {
			setRoleId(roleId);
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

		Integer moderator = (Integer)attributes.get("moderator");

		if (moderator != null) {
			setModerator(moderator);
		}
	}

	@Override
	public StepAllowance toEscapedModel() {
		return new StepAllowanceWrapper(_stepAllowance.toEscapedModel());
	}

	@Override
	public StepAllowance toUnescapedModel() {
		return new StepAllowanceWrapper(_stepAllowance.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _stepAllowance.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _stepAllowance.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _stepAllowance.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _stepAllowance.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<StepAllowance> toCacheModel() {
		return _stepAllowance.toCacheModel();
	}

	@Override
	public int compareTo(StepAllowance stepAllowance) {
		return _stepAllowance.compareTo(stepAllowance);
	}

	/**
	* Returns the moderator of this step allowance.
	*
	* @return the moderator of this step allowance
	*/
	@Override
	public int getModerator() {
		return _stepAllowance.getModerator();
	}

	@Override
	public int hashCode() {
		return _stepAllowance.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _stepAllowance.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new StepAllowanceWrapper((StepAllowance)_stepAllowance.clone());
	}

	/**
	* Returns the user name of this step allowance.
	*
	* @return the user name of this step allowance
	*/
	@Override
	public java.lang.String getUserName() {
		return _stepAllowance.getUserName();
	}

	/**
	* Returns the user uuid of this step allowance.
	*
	* @return the user uuid of this step allowance
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _stepAllowance.getUserUuid();
	}

	/**
	* Returns the uuid of this step allowance.
	*
	* @return the uuid of this step allowance
	*/
	@Override
	public java.lang.String getUuid() {
		return _stepAllowance.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _stepAllowance.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _stepAllowance.toXmlString();
	}

	/**
	* Returns the create date of this step allowance.
	*
	* @return the create date of this step allowance
	*/
	@Override
	public Date getCreateDate() {
		return _stepAllowance.getCreateDate();
	}

	/**
	* Returns the modified date of this step allowance.
	*
	* @return the modified date of this step allowance
	*/
	@Override
	public Date getModifiedDate() {
		return _stepAllowance.getModifiedDate();
	}

	/**
	* Returns the company ID of this step allowance.
	*
	* @return the company ID of this step allowance
	*/
	@Override
	public long getCompanyId() {
		return _stepAllowance.getCompanyId();
	}

	/**
	* Returns the group ID of this step allowance.
	*
	* @return the group ID of this step allowance
	*/
	@Override
	public long getGroupId() {
		return _stepAllowance.getGroupId();
	}

	/**
	* Returns the process step ID of this step allowance.
	*
	* @return the process step ID of this step allowance
	*/
	@Override
	public long getProcessStepId() {
		return _stepAllowance.getProcessStepId();
	}

	/**
	* Returns the role ID of this step allowance.
	*
	* @return the role ID of this step allowance
	*/
	@Override
	public long getRoleId() {
		return _stepAllowance.getRoleId();
	}

	/**
	* Returns the user ID of this step allowance.
	*
	* @return the user ID of this step allowance
	*/
	@Override
	public long getUserId() {
		return _stepAllowance.getUserId();
	}

	/**
	* Returns the primary key of this step allowance.
	*
	* @return the primary key of this step allowance
	*/
	@Override
	public org.opencps.backend.processmgt.service.persistence.StepAllowancePK getPrimaryKey() {
		return _stepAllowance.getPrimaryKey();
	}

	@Override
	public void persist() {
		_stepAllowance.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_stepAllowance.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this step allowance.
	*
	* @param companyId the company ID of this step allowance
	*/
	@Override
	public void setCompanyId(long companyId) {
		_stepAllowance.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this step allowance.
	*
	* @param createDate the create date of this step allowance
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_stepAllowance.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_stepAllowance.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_stepAllowance.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_stepAllowance.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this step allowance.
	*
	* @param groupId the group ID of this step allowance
	*/
	@Override
	public void setGroupId(long groupId) {
		_stepAllowance.setGroupId(groupId);
	}

	/**
	* Sets the moderator of this step allowance.
	*
	* @param moderator the moderator of this step allowance
	*/
	@Override
	public void setModerator(int moderator) {
		_stepAllowance.setModerator(moderator);
	}

	/**
	* Sets the modified date of this step allowance.
	*
	* @param modifiedDate the modified date of this step allowance
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_stepAllowance.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_stepAllowance.setNew(n);
	}

	/**
	* Sets the primary key of this step allowance.
	*
	* @param primaryKey the primary key of this step allowance
	*/
	@Override
	public void setPrimaryKey(
		org.opencps.backend.processmgt.service.persistence.StepAllowancePK primaryKey) {
		_stepAllowance.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_stepAllowance.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the process step ID of this step allowance.
	*
	* @param processStepId the process step ID of this step allowance
	*/
	@Override
	public void setProcessStepId(long processStepId) {
		_stepAllowance.setProcessStepId(processStepId);
	}

	/**
	* Sets the role ID of this step allowance.
	*
	* @param roleId the role ID of this step allowance
	*/
	@Override
	public void setRoleId(long roleId) {
		_stepAllowance.setRoleId(roleId);
	}

	/**
	* Sets the user ID of this step allowance.
	*
	* @param userId the user ID of this step allowance
	*/
	@Override
	public void setUserId(long userId) {
		_stepAllowance.setUserId(userId);
	}

	/**
	* Sets the user name of this step allowance.
	*
	* @param userName the user name of this step allowance
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_stepAllowance.setUserName(userName);
	}

	/**
	* Sets the user uuid of this step allowance.
	*
	* @param userUuid the user uuid of this step allowance
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_stepAllowance.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this step allowance.
	*
	* @param uuid the uuid of this step allowance
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_stepAllowance.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StepAllowanceWrapper)) {
			return false;
		}

		StepAllowanceWrapper stepAllowanceWrapper = (StepAllowanceWrapper)obj;

		if (Objects.equals(_stepAllowance, stepAllowanceWrapper._stepAllowance)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _stepAllowance.getStagedModelType();
	}

	@Override
	public StepAllowance getWrappedModel() {
		return _stepAllowance;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _stepAllowance.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _stepAllowance.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_stepAllowance.resetOriginalValues();
	}

	private final StepAllowance _stepAllowance;
}