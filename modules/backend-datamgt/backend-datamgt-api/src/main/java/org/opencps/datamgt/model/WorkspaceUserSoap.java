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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Binhth
 * @generated
 */
@ProviderType
public class WorkspaceUserSoap implements Serializable {
	public static WorkspaceUserSoap toSoapModel(WorkspaceUser model) {
		WorkspaceUserSoap soapModel = new WorkspaceUserSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setWorkspaceUserId(model.getWorkspaceUserId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setWorkspaceId(model.getWorkspaceId());
		soapModel.setAssignUserId(model.getAssignUserId());

		return soapModel;
	}

	public static WorkspaceUserSoap[] toSoapModels(WorkspaceUser[] models) {
		WorkspaceUserSoap[] soapModels = new WorkspaceUserSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static WorkspaceUserSoap[][] toSoapModels(WorkspaceUser[][] models) {
		WorkspaceUserSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new WorkspaceUserSoap[models.length][models[0].length];
		}
		else {
			soapModels = new WorkspaceUserSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static WorkspaceUserSoap[] toSoapModels(List<WorkspaceUser> models) {
		List<WorkspaceUserSoap> soapModels = new ArrayList<WorkspaceUserSoap>(models.size());

		for (WorkspaceUser model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new WorkspaceUserSoap[soapModels.size()]);
	}

	public WorkspaceUserSoap() {
	}

	public long getPrimaryKey() {
		return _workspaceUserId;
	}

	public void setPrimaryKey(long pk) {
		setWorkspaceUserId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getWorkspaceUserId() {
		return _workspaceUserId;
	}

	public void setWorkspaceUserId(long workspaceUserId) {
		_workspaceUserId = workspaceUserId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getWorkspaceId() {
		return _workspaceId;
	}

	public void setWorkspaceId(long workspaceId) {
		_workspaceId = workspaceId;
	}

	public long getAssignUserId() {
		return _assignUserId;
	}

	public void setAssignUserId(long assignUserId) {
		_assignUserId = assignUserId;
	}

	private String _uuid;
	private long _workspaceUserId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _workspaceId;
	private long _assignUserId;
}