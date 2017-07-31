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
 * This class is a wrapper for {@link WorkspaceUser}.
 * </p>
 *
 * @author Binhth
 * @see WorkspaceUser
 * @generated
 */
@ProviderType
public class WorkspaceUserWrapper implements WorkspaceUser,
	ModelWrapper<WorkspaceUser> {
	public WorkspaceUserWrapper(WorkspaceUser workspaceUser) {
		_workspaceUser = workspaceUser;
	}

	@Override
	public Class<?> getModelClass() {
		return WorkspaceUser.class;
	}

	@Override
	public String getModelClassName() {
		return WorkspaceUser.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("workspaceUserId", getWorkspaceUserId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("workspaceId", getWorkspaceId());
		attributes.put("assignUserId", getAssignUserId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long workspaceUserId = (Long)attributes.get("workspaceUserId");

		if (workspaceUserId != null) {
			setWorkspaceUserId(workspaceUserId);
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

		Long assignUserId = (Long)attributes.get("assignUserId");

		if (assignUserId != null) {
			setAssignUserId(assignUserId);
		}
	}

	@Override
	public WorkspaceUser toEscapedModel() {
		return new WorkspaceUserWrapper(_workspaceUser.toEscapedModel());
	}

	@Override
	public WorkspaceUser toUnescapedModel() {
		return new WorkspaceUserWrapper(_workspaceUser.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _workspaceUser.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _workspaceUser.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _workspaceUser.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _workspaceUser.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<WorkspaceUser> toCacheModel() {
		return _workspaceUser.toCacheModel();
	}

	@Override
	public int compareTo(WorkspaceUser workspaceUser) {
		return _workspaceUser.compareTo(workspaceUser);
	}

	@Override
	public int hashCode() {
		return _workspaceUser.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _workspaceUser.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new WorkspaceUserWrapper((WorkspaceUser)_workspaceUser.clone());
	}

	/**
	* Returns the assign user uuid of this workspace user.
	*
	* @return the assign user uuid of this workspace user
	*/
	@Override
	public java.lang.String getAssignUserUuid() {
		return _workspaceUser.getAssignUserUuid();
	}

	/**
	* Returns the user name of this workspace user.
	*
	* @return the user name of this workspace user
	*/
	@Override
	public java.lang.String getUserName() {
		return _workspaceUser.getUserName();
	}

	/**
	* Returns the user uuid of this workspace user.
	*
	* @return the user uuid of this workspace user
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _workspaceUser.getUserUuid();
	}

	/**
	* Returns the uuid of this workspace user.
	*
	* @return the uuid of this workspace user
	*/
	@Override
	public java.lang.String getUuid() {
		return _workspaceUser.getUuid();
	}

	/**
	* Returns the workspace user uuid of this workspace user.
	*
	* @return the workspace user uuid of this workspace user
	*/
	@Override
	public java.lang.String getWorkspaceUserUuid() {
		return _workspaceUser.getWorkspaceUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _workspaceUser.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _workspaceUser.toXmlString();
	}

	/**
	* Returns the create date of this workspace user.
	*
	* @return the create date of this workspace user
	*/
	@Override
	public Date getCreateDate() {
		return _workspaceUser.getCreateDate();
	}

	/**
	* Returns the modified date of this workspace user.
	*
	* @return the modified date of this workspace user
	*/
	@Override
	public Date getModifiedDate() {
		return _workspaceUser.getModifiedDate();
	}

	/**
	* Returns the assign user ID of this workspace user.
	*
	* @return the assign user ID of this workspace user
	*/
	@Override
	public long getAssignUserId() {
		return _workspaceUser.getAssignUserId();
	}

	/**
	* Returns the company ID of this workspace user.
	*
	* @return the company ID of this workspace user
	*/
	@Override
	public long getCompanyId() {
		return _workspaceUser.getCompanyId();
	}

	/**
	* Returns the group ID of this workspace user.
	*
	* @return the group ID of this workspace user
	*/
	@Override
	public long getGroupId() {
		return _workspaceUser.getGroupId();
	}

	/**
	* Returns the primary key of this workspace user.
	*
	* @return the primary key of this workspace user
	*/
	@Override
	public long getPrimaryKey() {
		return _workspaceUser.getPrimaryKey();
	}

	/**
	* Returns the user ID of this workspace user.
	*
	* @return the user ID of this workspace user
	*/
	@Override
	public long getUserId() {
		return _workspaceUser.getUserId();
	}

	/**
	* Returns the workspace ID of this workspace user.
	*
	* @return the workspace ID of this workspace user
	*/
	@Override
	public long getWorkspaceId() {
		return _workspaceUser.getWorkspaceId();
	}

	/**
	* Returns the workspace user ID of this workspace user.
	*
	* @return the workspace user ID of this workspace user
	*/
	@Override
	public long getWorkspaceUserId() {
		return _workspaceUser.getWorkspaceUserId();
	}

	@Override
	public void persist() {
		_workspaceUser.persist();
	}

	/**
	* Sets the assign user ID of this workspace user.
	*
	* @param assignUserId the assign user ID of this workspace user
	*/
	@Override
	public void setAssignUserId(long assignUserId) {
		_workspaceUser.setAssignUserId(assignUserId);
	}

	/**
	* Sets the assign user uuid of this workspace user.
	*
	* @param assignUserUuid the assign user uuid of this workspace user
	*/
	@Override
	public void setAssignUserUuid(java.lang.String assignUserUuid) {
		_workspaceUser.setAssignUserUuid(assignUserUuid);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_workspaceUser.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this workspace user.
	*
	* @param companyId the company ID of this workspace user
	*/
	@Override
	public void setCompanyId(long companyId) {
		_workspaceUser.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this workspace user.
	*
	* @param createDate the create date of this workspace user
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_workspaceUser.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_workspaceUser.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_workspaceUser.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_workspaceUser.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this workspace user.
	*
	* @param groupId the group ID of this workspace user
	*/
	@Override
	public void setGroupId(long groupId) {
		_workspaceUser.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this workspace user.
	*
	* @param modifiedDate the modified date of this workspace user
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_workspaceUser.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_workspaceUser.setNew(n);
	}

	/**
	* Sets the primary key of this workspace user.
	*
	* @param primaryKey the primary key of this workspace user
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_workspaceUser.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_workspaceUser.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this workspace user.
	*
	* @param userId the user ID of this workspace user
	*/
	@Override
	public void setUserId(long userId) {
		_workspaceUser.setUserId(userId);
	}

	/**
	* Sets the user name of this workspace user.
	*
	* @param userName the user name of this workspace user
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_workspaceUser.setUserName(userName);
	}

	/**
	* Sets the user uuid of this workspace user.
	*
	* @param userUuid the user uuid of this workspace user
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_workspaceUser.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this workspace user.
	*
	* @param uuid the uuid of this workspace user
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_workspaceUser.setUuid(uuid);
	}

	/**
	* Sets the workspace ID of this workspace user.
	*
	* @param workspaceId the workspace ID of this workspace user
	*/
	@Override
	public void setWorkspaceId(long workspaceId) {
		_workspaceUser.setWorkspaceId(workspaceId);
	}

	/**
	* Sets the workspace user ID of this workspace user.
	*
	* @param workspaceUserId the workspace user ID of this workspace user
	*/
	@Override
	public void setWorkspaceUserId(long workspaceUserId) {
		_workspaceUser.setWorkspaceUserId(workspaceUserId);
	}

	/**
	* Sets the workspace user uuid of this workspace user.
	*
	* @param workspaceUserUuid the workspace user uuid of this workspace user
	*/
	@Override
	public void setWorkspaceUserUuid(java.lang.String workspaceUserUuid) {
		_workspaceUser.setWorkspaceUserUuid(workspaceUserUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WorkspaceUserWrapper)) {
			return false;
		}

		WorkspaceUserWrapper workspaceUserWrapper = (WorkspaceUserWrapper)obj;

		if (Objects.equals(_workspaceUser, workspaceUserWrapper._workspaceUser)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _workspaceUser.getStagedModelType();
	}

	@Override
	public WorkspaceUser getWrappedModel() {
		return _workspaceUser;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _workspaceUser.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _workspaceUser.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_workspaceUser.resetOriginalValues();
	}

	private final WorkspaceUser _workspaceUser;
}