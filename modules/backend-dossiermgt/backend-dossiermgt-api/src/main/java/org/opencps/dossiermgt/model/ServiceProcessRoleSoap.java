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

import org.opencps.dossiermgt.service.persistence.ServiceProcessRolePK;

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
public class ServiceProcessRoleSoap implements Serializable {
	public static ServiceProcessRoleSoap toSoapModel(ServiceProcessRole model) {
		ServiceProcessRoleSoap soapModel = new ServiceProcessRoleSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setServiceProcessId(model.getServiceProcessId());
		soapModel.setRoleId(model.getRoleId());
		soapModel.setRoleCode(model.getRoleCode());
		soapModel.setRoleName(model.getRoleName());
		soapModel.setModerator(model.isModerator());
		soapModel.setCondition(model.getCondition());

		return soapModel;
	}

	public static ServiceProcessRoleSoap[] toSoapModels(
		ServiceProcessRole[] models) {
		ServiceProcessRoleSoap[] soapModels = new ServiceProcessRoleSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ServiceProcessRoleSoap[][] toSoapModels(
		ServiceProcessRole[][] models) {
		ServiceProcessRoleSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ServiceProcessRoleSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ServiceProcessRoleSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ServiceProcessRoleSoap[] toSoapModels(
		List<ServiceProcessRole> models) {
		List<ServiceProcessRoleSoap> soapModels = new ArrayList<ServiceProcessRoleSoap>(models.size());

		for (ServiceProcessRole model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ServiceProcessRoleSoap[soapModels.size()]);
	}

	public ServiceProcessRoleSoap() {
	}

	public ServiceProcessRolePK getPrimaryKey() {
		return new ServiceProcessRolePK(_serviceProcessId, _roleId);
	}

	public void setPrimaryKey(ServiceProcessRolePK pk) {
		setServiceProcessId(pk.serviceProcessId);
		setRoleId(pk.roleId);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getServiceProcessId() {
		return _serviceProcessId;
	}

	public void setServiceProcessId(long serviceProcessId) {
		_serviceProcessId = serviceProcessId;
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
	private long _serviceProcessId;
	private long _roleId;
	private String _roleCode;
	private String _roleName;
	private boolean _moderator;
	private String _condition;
}