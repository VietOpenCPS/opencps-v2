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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link ProcessStepRole}.
 * </p>
 *
 * @author huymq
 * @see ProcessStepRole
 * @generated
 */
@ProviderType
public class ProcessStepRoleWrapper implements ProcessStepRole,
	ModelWrapper<ProcessStepRole> {
	public ProcessStepRoleWrapper(ProcessStepRole processStepRole) {
		_processStepRole = processStepRole;
	}

	@Override
	public Class<?> getModelClass() {
		return ProcessStepRole.class;
	}

	@Override
	public String getModelClassName() {
		return ProcessStepRole.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("processStepId", getProcessStepId());
		attributes.put("roleId", getRoleId());
		attributes.put("roleCode", getRoleCode());
		attributes.put("roleName", getRoleName());
		attributes.put("moderator", isModerator());
		attributes.put("condition", getCondition());

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

		String roleCode = (String)attributes.get("roleCode");

		if (roleCode != null) {
			setRoleCode(roleCode);
		}

		String roleName = (String)attributes.get("roleName");

		if (roleName != null) {
			setRoleName(roleName);
		}

		Boolean moderator = (Boolean)attributes.get("moderator");

		if (moderator != null) {
			setModerator(moderator);
		}

		String condition = (String)attributes.get("condition");

		if (condition != null) {
			setCondition(condition);
		}
	}

	@Override
	public Object clone() {
		return new ProcessStepRoleWrapper((ProcessStepRole)_processStepRole.clone());
	}

	@Override
	public int compareTo(ProcessStepRole processStepRole) {
		return _processStepRole.compareTo(processStepRole);
	}

	/**
	* Returns the condition of this process step role.
	*
	* @return the condition of this process step role
	*/
	@Override
	public String getCondition() {
		return _processStepRole.getCondition();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _processStepRole.getExpandoBridge();
	}

	/**
	* Returns the moderator of this process step role.
	*
	* @return the moderator of this process step role
	*/
	@Override
	public boolean getModerator() {
		return _processStepRole.getModerator();
	}

	/**
	* Returns the primary key of this process step role.
	*
	* @return the primary key of this process step role
	*/
	@Override
	public org.opencps.dossiermgt.service.persistence.ProcessStepRolePK getPrimaryKey() {
		return _processStepRole.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _processStepRole.getPrimaryKeyObj();
	}

	/**
	* Returns the process step ID of this process step role.
	*
	* @return the process step ID of this process step role
	*/
	@Override
	public long getProcessStepId() {
		return _processStepRole.getProcessStepId();
	}

	/**
	* Returns the role code of this process step role.
	*
	* @return the role code of this process step role
	*/
	@Override
	public String getRoleCode() {
		return _processStepRole.getRoleCode();
	}

	/**
	* Returns the role ID of this process step role.
	*
	* @return the role ID of this process step role
	*/
	@Override
	public long getRoleId() {
		return _processStepRole.getRoleId();
	}

	/**
	* Returns the role name of this process step role.
	*
	* @return the role name of this process step role
	*/
	@Override
	public String getRoleName() {
		return _processStepRole.getRoleName();
	}

	/**
	* Returns the uuid of this process step role.
	*
	* @return the uuid of this process step role
	*/
	@Override
	public String getUuid() {
		return _processStepRole.getUuid();
	}

	@Override
	public int hashCode() {
		return _processStepRole.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _processStepRole.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _processStepRole.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this process step role is moderator.
	*
	* @return <code>true</code> if this process step role is moderator; <code>false</code> otherwise
	*/
	@Override
	public boolean isModerator() {
		return _processStepRole.isModerator();
	}

	@Override
	public boolean isNew() {
		return _processStepRole.isNew();
	}

	@Override
	public void persist() {
		_processStepRole.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_processStepRole.setCachedModel(cachedModel);
	}

	/**
	* Sets the condition of this process step role.
	*
	* @param condition the condition of this process step role
	*/
	@Override
	public void setCondition(String condition) {
		_processStepRole.setCondition(condition);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_processStepRole.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_processStepRole.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_processStepRole.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets whether this process step role is moderator.
	*
	* @param moderator the moderator of this process step role
	*/
	@Override
	public void setModerator(boolean moderator) {
		_processStepRole.setModerator(moderator);
	}

	@Override
	public void setNew(boolean n) {
		_processStepRole.setNew(n);
	}

	/**
	* Sets the primary key of this process step role.
	*
	* @param primaryKey the primary key of this process step role
	*/
	@Override
	public void setPrimaryKey(
		org.opencps.dossiermgt.service.persistence.ProcessStepRolePK primaryKey) {
		_processStepRole.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_processStepRole.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the process step ID of this process step role.
	*
	* @param processStepId the process step ID of this process step role
	*/
	@Override
	public void setProcessStepId(long processStepId) {
		_processStepRole.setProcessStepId(processStepId);
	}

	/**
	* Sets the role code of this process step role.
	*
	* @param roleCode the role code of this process step role
	*/
	@Override
	public void setRoleCode(String roleCode) {
		_processStepRole.setRoleCode(roleCode);
	}

	/**
	* Sets the role ID of this process step role.
	*
	* @param roleId the role ID of this process step role
	*/
	@Override
	public void setRoleId(long roleId) {
		_processStepRole.setRoleId(roleId);
	}

	/**
	* Sets the role name of this process step role.
	*
	* @param roleName the role name of this process step role
	*/
	@Override
	public void setRoleName(String roleName) {
		_processStepRole.setRoleName(roleName);
	}

	/**
	* Sets the uuid of this process step role.
	*
	* @param uuid the uuid of this process step role
	*/
	@Override
	public void setUuid(String uuid) {
		_processStepRole.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ProcessStepRole> toCacheModel() {
		return _processStepRole.toCacheModel();
	}

	@Override
	public ProcessStepRole toEscapedModel() {
		return new ProcessStepRoleWrapper(_processStepRole.toEscapedModel());
	}

	@Override
	public String toString() {
		return _processStepRole.toString();
	}

	@Override
	public ProcessStepRole toUnescapedModel() {
		return new ProcessStepRoleWrapper(_processStepRole.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _processStepRole.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProcessStepRoleWrapper)) {
			return false;
		}

		ProcessStepRoleWrapper processStepRoleWrapper = (ProcessStepRoleWrapper)obj;

		if (Objects.equals(_processStepRole,
					processStepRoleWrapper._processStepRole)) {
			return true;
		}

		return false;
	}

	@Override
	public ProcessStepRole getWrappedModel() {
		return _processStepRole;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _processStepRole.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _processStepRole.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_processStepRole.resetOriginalValues();
	}

	private final ProcessStepRole _processStepRole;
}