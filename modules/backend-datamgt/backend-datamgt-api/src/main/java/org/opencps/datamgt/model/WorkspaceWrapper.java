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

package org.opencps.datamgt.model;

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
 * This class is a wrapper for {@link Workspace}.
 * </p>
 *
 * @author Binhth
 * @see Workspace
 * @generated
 */
@ProviderType
public class WorkspaceWrapper implements Workspace, ModelWrapper<Workspace> {
	public WorkspaceWrapper(Workspace workspace) {
		_workspace = workspace;
	}

	@Override
	public Class<?> getModelClass() {
		return Workspace.class;
	}

	@Override
	public String getModelClassName() {
		return Workspace.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("workspaceId", getWorkspaceId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("seqOrder", getSeqOrder());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long workspaceId = (Long)attributes.get("workspaceId");

		if (workspaceId != null) {
			setWorkspaceId(workspaceId);
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

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Integer seqOrder = (Integer)attributes.get("seqOrder");

		if (seqOrder != null) {
			setSeqOrder(seqOrder);
		}
	}

	@Override
	public Workspace toEscapedModel() {
		return new WorkspaceWrapper(_workspace.toEscapedModel());
	}

	@Override
	public Workspace toUnescapedModel() {
		return new WorkspaceWrapper(_workspace.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _workspace.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _workspace.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _workspace.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _workspace.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Workspace> toCacheModel() {
		return _workspace.toCacheModel();
	}

	@Override
	public int compareTo(Workspace workspace) {
		return _workspace.compareTo(workspace);
	}

	/**
	* Returns the seq order of this workspace.
	*
	* @return the seq order of this workspace
	*/
	@Override
	public int getSeqOrder() {
		return _workspace.getSeqOrder();
	}

	@Override
	public int hashCode() {
		return _workspace.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _workspace.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new WorkspaceWrapper((Workspace)_workspace.clone());
	}

	/**
	* Returns the name of this workspace.
	*
	* @return the name of this workspace
	*/
	@Override
	public java.lang.String getName() {
		return _workspace.getName();
	}

	/**
	* Returns the user name of this workspace.
	*
	* @return the user name of this workspace
	*/
	@Override
	public java.lang.String getUserName() {
		return _workspace.getUserName();
	}

	/**
	* Returns the user uuid of this workspace.
	*
	* @return the user uuid of this workspace
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _workspace.getUserUuid();
	}

	/**
	* Returns the uuid of this workspace.
	*
	* @return the uuid of this workspace
	*/
	@Override
	public java.lang.String getUuid() {
		return _workspace.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _workspace.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _workspace.toXmlString();
	}

	/**
	* Returns the create date of this workspace.
	*
	* @return the create date of this workspace
	*/
	@Override
	public Date getCreateDate() {
		return _workspace.getCreateDate();
	}

	/**
	* Returns the modified date of this workspace.
	*
	* @return the modified date of this workspace
	*/
	@Override
	public Date getModifiedDate() {
		return _workspace.getModifiedDate();
	}

	/**
	* Returns the company ID of this workspace.
	*
	* @return the company ID of this workspace
	*/
	@Override
	public long getCompanyId() {
		return _workspace.getCompanyId();
	}

	/**
	* Returns the group ID of this workspace.
	*
	* @return the group ID of this workspace
	*/
	@Override
	public long getGroupId() {
		return _workspace.getGroupId();
	}

	/**
	* Returns the primary key of this workspace.
	*
	* @return the primary key of this workspace
	*/
	@Override
	public long getPrimaryKey() {
		return _workspace.getPrimaryKey();
	}

	/**
	* Returns the user ID of this workspace.
	*
	* @return the user ID of this workspace
	*/
	@Override
	public long getUserId() {
		return _workspace.getUserId();
	}

	/**
	* Returns the workspace ID of this workspace.
	*
	* @return the workspace ID of this workspace
	*/
	@Override
	public long getWorkspaceId() {
		return _workspace.getWorkspaceId();
	}

	@Override
	public void persist() {
		_workspace.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_workspace.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this workspace.
	*
	* @param companyId the company ID of this workspace
	*/
	@Override
	public void setCompanyId(long companyId) {
		_workspace.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this workspace.
	*
	* @param createDate the create date of this workspace
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_workspace.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_workspace.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_workspace.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_workspace.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this workspace.
	*
	* @param groupId the group ID of this workspace
	*/
	@Override
	public void setGroupId(long groupId) {
		_workspace.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this workspace.
	*
	* @param modifiedDate the modified date of this workspace
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_workspace.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this workspace.
	*
	* @param name the name of this workspace
	*/
	@Override
	public void setName(java.lang.String name) {
		_workspace.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_workspace.setNew(n);
	}

	/**
	* Sets the primary key of this workspace.
	*
	* @param primaryKey the primary key of this workspace
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_workspace.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_workspace.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the seq order of this workspace.
	*
	* @param seqOrder the seq order of this workspace
	*/
	@Override
	public void setSeqOrder(int seqOrder) {
		_workspace.setSeqOrder(seqOrder);
	}

	/**
	* Sets the user ID of this workspace.
	*
	* @param userId the user ID of this workspace
	*/
	@Override
	public void setUserId(long userId) {
		_workspace.setUserId(userId);
	}

	/**
	* Sets the user name of this workspace.
	*
	* @param userName the user name of this workspace
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_workspace.setUserName(userName);
	}

	/**
	* Sets the user uuid of this workspace.
	*
	* @param userUuid the user uuid of this workspace
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_workspace.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this workspace.
	*
	* @param uuid the uuid of this workspace
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_workspace.setUuid(uuid);
	}

	/**
	* Sets the workspace ID of this workspace.
	*
	* @param workspaceId the workspace ID of this workspace
	*/
	@Override
	public void setWorkspaceId(long workspaceId) {
		_workspace.setWorkspaceId(workspaceId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WorkspaceWrapper)) {
			return false;
		}

		WorkspaceWrapper workspaceWrapper = (WorkspaceWrapper)obj;

		if (Objects.equals(_workspace, workspaceWrapper._workspace)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _workspace.getStagedModelType();
	}

	@Override
	public Workspace getWrappedModel() {
		return _workspace;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _workspace.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _workspace.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_workspace.resetOriginalValues();
	}

	private final Workspace _workspace;
}