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

package org.mobilink.backend.datamgt.model;

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
 * This class is a wrapper for {@link WorkspaceRole}.
 * </p>
 *
 * @author Binhth
 * @see WorkspaceRole
 * @generated
 */
@ProviderType
public class WorkspaceRoleWrapper implements WorkspaceRole,
	ModelWrapper<WorkspaceRole> {
	public WorkspaceRoleWrapper(WorkspaceRole workspaceRole) {
		_workspaceRole = workspaceRole;
	}

	@Override
	public Class<?> getModelClass() {
		return WorkspaceRole.class;
	}

	@Override
	public String getModelClassName() {
		return WorkspaceRole.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("workspaceRoleId", getWorkspaceRoleId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("workspaceId", getWorkspaceId());
		attributes.put("roleId", getRoleId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long workspaceRoleId = (Long)attributes.get("workspaceRoleId");

		if (workspaceRoleId != null) {
			setWorkspaceRoleId(workspaceRoleId);
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

		Long workspaceId = (Long)attributes.get("workspaceId");

		if (workspaceId != null) {
			setWorkspaceId(workspaceId);
		}

		Long roleId = (Long)attributes.get("roleId");

		if (roleId != null) {
			setRoleId(roleId);
		}
	}

	@Override
	public WorkspaceRole toEscapedModel() {
		return new WorkspaceRoleWrapper(_workspaceRole.toEscapedModel());
	}

	@Override
	public WorkspaceRole toUnescapedModel() {
		return new WorkspaceRoleWrapper(_workspaceRole.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _workspaceRole.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _workspaceRole.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _workspaceRole.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _workspaceRole.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<WorkspaceRole> toCacheModel() {
		return _workspaceRole.toCacheModel();
	}

	@Override
	public int compareTo(WorkspaceRole workspaceRole) {
		return _workspaceRole.compareTo(workspaceRole);
	}

	@Override
	public int hashCode() {
		return _workspaceRole.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _workspaceRole.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new WorkspaceRoleWrapper((WorkspaceRole)_workspaceRole.clone());
	}

	/**
	* Returns the user name of this workspace role.
	*
	* @return the user name of this workspace role
	*/
	@Override
	public java.lang.String getUserName() {
		return _workspaceRole.getUserName();
	}

	/**
	* Returns the user uuid of this workspace role.
	*
	* @return the user uuid of this workspace role
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _workspaceRole.getUserUuid();
	}

	/**
	* Returns the uuid of this workspace role.
	*
	* @return the uuid of this workspace role
	*/
	@Override
	public java.lang.String getUuid() {
		return _workspaceRole.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _workspaceRole.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _workspaceRole.toXmlString();
	}

	/**
	* Returns the create date of this workspace role.
	*
	* @return the create date of this workspace role
	*/
	@Override
	public Date getCreateDate() {
		return _workspaceRole.getCreateDate();
	}

	/**
	* Returns the modified date of this workspace role.
	*
	* @return the modified date of this workspace role
	*/
	@Override
	public Date getModifiedDate() {
		return _workspaceRole.getModifiedDate();
	}

	/**
	* Returns the company ID of this workspace role.
	*
	* @return the company ID of this workspace role
	*/
	@Override
	public long getCompanyId() {
		return _workspaceRole.getCompanyId();
	}

	/**
	* Returns the group ID of this workspace role.
	*
	* @return the group ID of this workspace role
	*/
	@Override
	public long getGroupId() {
		return _workspaceRole.getGroupId();
	}

	/**
	* Returns the primary key of this workspace role.
	*
	* @return the primary key of this workspace role
	*/
	@Override
	public long getPrimaryKey() {
		return _workspaceRole.getPrimaryKey();
	}

	/**
	* Returns the role ID of this workspace role.
	*
	* @return the role ID of this workspace role
	*/
	@Override
	public long getRoleId() {
		return _workspaceRole.getRoleId();
	}

	/**
	* Returns the user ID of this workspace role.
	*
	* @return the user ID of this workspace role
	*/
	@Override
	public long getUserId() {
		return _workspaceRole.getUserId();
	}

	/**
	* Returns the workspace ID of this workspace role.
	*
	* @return the workspace ID of this workspace role
	*/
	@Override
	public long getWorkspaceId() {
		return _workspaceRole.getWorkspaceId();
	}

	/**
	* Returns the workspace role ID of this workspace role.
	*
	* @return the workspace role ID of this workspace role
	*/
	@Override
	public long getWorkspaceRoleId() {
		return _workspaceRole.getWorkspaceRoleId();
	}

	@Override
	public void persist() {
		_workspaceRole.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_workspaceRole.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this workspace role.
	*
	* @param companyId the company ID of this workspace role
	*/
	@Override
	public void setCompanyId(long companyId) {
		_workspaceRole.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this workspace role.
	*
	* @param createDate the create date of this workspace role
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_workspaceRole.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_workspaceRole.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_workspaceRole.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_workspaceRole.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this workspace role.
	*
	* @param groupId the group ID of this workspace role
	*/
	@Override
	public void setGroupId(long groupId) {
		_workspaceRole.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this workspace role.
	*
	* @param modifiedDate the modified date of this workspace role
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_workspaceRole.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_workspaceRole.setNew(n);
	}

	/**
	* Sets the primary key of this workspace role.
	*
	* @param primaryKey the primary key of this workspace role
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_workspaceRole.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_workspaceRole.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the role ID of this workspace role.
	*
	* @param roleId the role ID of this workspace role
	*/
	@Override
	public void setRoleId(long roleId) {
		_workspaceRole.setRoleId(roleId);
	}

	/**
	* Sets the user ID of this workspace role.
	*
	* @param userId the user ID of this workspace role
	*/
	@Override
	public void setUserId(long userId) {
		_workspaceRole.setUserId(userId);
	}

	/**
	* Sets the user name of this workspace role.
	*
	* @param userName the user name of this workspace role
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_workspaceRole.setUserName(userName);
	}

	/**
	* Sets the user uuid of this workspace role.
	*
	* @param userUuid the user uuid of this workspace role
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_workspaceRole.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this workspace role.
	*
	* @param uuid the uuid of this workspace role
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_workspaceRole.setUuid(uuid);
	}

	/**
	* Sets the workspace ID of this workspace role.
	*
	* @param workspaceId the workspace ID of this workspace role
	*/
	@Override
	public void setWorkspaceId(long workspaceId) {
		_workspaceRole.setWorkspaceId(workspaceId);
	}

	/**
	* Sets the workspace role ID of this workspace role.
	*
	* @param workspaceRoleId the workspace role ID of this workspace role
	*/
	@Override
	public void setWorkspaceRoleId(long workspaceRoleId) {
		_workspaceRole.setWorkspaceRoleId(workspaceRoleId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WorkspaceRoleWrapper)) {
			return false;
		}

		WorkspaceRoleWrapper workspaceRoleWrapper = (WorkspaceRoleWrapper)obj;

		if (Objects.equals(_workspaceRole, workspaceRoleWrapper._workspaceRole)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _workspaceRole.getStagedModelType();
	}

	@Override
	public WorkspaceRole getWrappedModel() {
		return _workspaceRole;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _workspaceRole.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _workspaceRole.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_workspaceRole.resetOriginalValues();
	}

	private final WorkspaceRole _workspaceRole;
}