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

import org.opencps.backend.processmgt.service.persistence.StepAllowancePK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author khoavu
 * @generated
 */
@ProviderType
public class StepAllowanceSoap implements Serializable {
	public static StepAllowanceSoap toSoapModel(StepAllowance model) {
		StepAllowanceSoap soapModel = new StepAllowanceSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setProcessStepId(model.getProcessStepId());
		soapModel.setRoleId(model.getRoleId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setModerator(model.getModerator());

		return soapModel;
	}

	public static StepAllowanceSoap[] toSoapModels(StepAllowance[] models) {
		StepAllowanceSoap[] soapModels = new StepAllowanceSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static StepAllowanceSoap[][] toSoapModels(StepAllowance[][] models) {
		StepAllowanceSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new StepAllowanceSoap[models.length][models[0].length];
		}
		else {
			soapModels = new StepAllowanceSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static StepAllowanceSoap[] toSoapModels(List<StepAllowance> models) {
		List<StepAllowanceSoap> soapModels = new ArrayList<StepAllowanceSoap>(models.size());

		for (StepAllowance model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new StepAllowanceSoap[soapModels.size()]);
	}

	public StepAllowanceSoap() {
	}

	public StepAllowancePK getPrimaryKey() {
		return new StepAllowancePK(_processStepId, _roleId);
	}

	public void setPrimaryKey(StepAllowancePK pk) {
		setProcessStepId(pk.processStepId);
		setRoleId(pk.roleId);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getProcessStepId() {
		return _processStepId;
	}

	public void setProcessStepId(long processStepId) {
		_processStepId = processStepId;
	}

	public long getRoleId() {
		return _roleId;
	}

	public void setRoleId(long roleId) {
		_roleId = roleId;
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

	public int getModerator() {
		return _moderator;
	}

	public void setModerator(int moderator) {
		_moderator = moderator;
	}

	private String _uuid;
	private long _processStepId;
	private long _roleId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private int _moderator;
}