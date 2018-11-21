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

package org.opencps.deliverable.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author binhth
 * @generated
 */
@ProviderType
public class OpenCPSDeliverableTypeRoleSoap implements Serializable {
	public static OpenCPSDeliverableTypeRoleSoap toSoapModel(
		OpenCPSDeliverableTypeRole model) {
		OpenCPSDeliverableTypeRoleSoap soapModel = new OpenCPSDeliverableTypeRoleSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setDeliverableTypeRoleId(model.getDeliverableTypeRoleId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setDeliverableTypeId(model.getDeliverableTypeId());
		soapModel.setRoleId(model.getRoleId());
		soapModel.setModerator(model.isModerator());

		return soapModel;
	}

	public static OpenCPSDeliverableTypeRoleSoap[] toSoapModels(
		OpenCPSDeliverableTypeRole[] models) {
		OpenCPSDeliverableTypeRoleSoap[] soapModels = new OpenCPSDeliverableTypeRoleSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static OpenCPSDeliverableTypeRoleSoap[][] toSoapModels(
		OpenCPSDeliverableTypeRole[][] models) {
		OpenCPSDeliverableTypeRoleSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new OpenCPSDeliverableTypeRoleSoap[models.length][models[0].length];
		}
		else {
			soapModels = new OpenCPSDeliverableTypeRoleSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static OpenCPSDeliverableTypeRoleSoap[] toSoapModels(
		List<OpenCPSDeliverableTypeRole> models) {
		List<OpenCPSDeliverableTypeRoleSoap> soapModels = new ArrayList<OpenCPSDeliverableTypeRoleSoap>(models.size());

		for (OpenCPSDeliverableTypeRole model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new OpenCPSDeliverableTypeRoleSoap[soapModels.size()]);
	}

	public OpenCPSDeliverableTypeRoleSoap() {
	}

	public long getPrimaryKey() {
		return _deliverableTypeRoleId;
	}

	public void setPrimaryKey(long pk) {
		setDeliverableTypeRoleId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getDeliverableTypeRoleId() {
		return _deliverableTypeRoleId;
	}

	public void setDeliverableTypeRoleId(long deliverableTypeRoleId) {
		_deliverableTypeRoleId = deliverableTypeRoleId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
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

	public long getDeliverableTypeId() {
		return _deliverableTypeId;
	}

	public void setDeliverableTypeId(long deliverableTypeId) {
		_deliverableTypeId = deliverableTypeId;
	}

	public long getRoleId() {
		return _roleId;
	}

	public void setRoleId(long roleId) {
		_roleId = roleId;
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

	private String _uuid;
	private long _deliverableTypeRoleId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _deliverableTypeId;
	private long _roleId;
	private boolean _moderator;
}