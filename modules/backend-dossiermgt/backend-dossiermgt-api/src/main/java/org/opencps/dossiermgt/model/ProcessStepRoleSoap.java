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

import org.opencps.dossiermgt.service.persistence.ProcessStepRolePK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author huymq
 * @generated
 */
@ProviderType
public class ProcessStepRoleSoap implements Serializable {
	public static ProcessStepRoleSoap toSoapModel(ProcessStepRole model) {
		ProcessStepRoleSoap soapModel = new ProcessStepRoleSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setProcessStepId(model.getProcessStepId());
		soapModel.setRoleId(model.getRoleId());
		soapModel.setRoleCode(model.getRoleCode());
		soapModel.setRoleName(model.getRoleName());
		soapModel.setModerator(model.isModerator());
		soapModel.setCondition(model.getCondition());

		return soapModel;
	}

	public static ProcessStepRoleSoap[] toSoapModels(ProcessStepRole[] models) {
		ProcessStepRoleSoap[] soapModels = new ProcessStepRoleSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ProcessStepRoleSoap[][] toSoapModels(
		ProcessStepRole[][] models) {
		ProcessStepRoleSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ProcessStepRoleSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ProcessStepRoleSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ProcessStepRoleSoap[] toSoapModels(
		List<ProcessStepRole> models) {
		List<ProcessStepRoleSoap> soapModels = new ArrayList<ProcessStepRoleSoap>(models.size());

		for (ProcessStepRole model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ProcessStepRoleSoap[soapModels.size()]);
	}

	public ProcessStepRoleSoap() {
	}

	public ProcessStepRolePK getPrimaryKey() {
		return new ProcessStepRolePK(_processStepId, _roleId);
	}

	public void setPrimaryKey(ProcessStepRolePK pk) {
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

	public String getRoleCode() {
		return _roleCode;
	}

	public void setRoleCode(String roleCode) {
		_roleCode = roleCode;
	}

	public String getRoleName() {
		return _roleName;
	}

	public void setRoleName(String roleName) {
		_roleName = roleName;
	}

	public boolean getModerator() {
		return _moderator;
	}

	public boolean isModerator() {
		return _moderator;
	}

	public void setModerator(boolean moderator) {
		_moderator = moderator;
	}

	public String getCondition() {
		return _condition;
	}

	public void setCondition(String condition) {
		_condition = condition;
	}

	private String _uuid;
	private long _processStepId;
	private long _roleId;
	private String _roleCode;
	private String _roleName;
	private boolean _moderator;
	private String _condition;
}