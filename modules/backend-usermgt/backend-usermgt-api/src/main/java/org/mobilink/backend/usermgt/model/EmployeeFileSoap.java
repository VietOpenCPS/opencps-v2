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

package org.mobilink.backend.usermgt.model;

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
public class EmployeeFileSoap implements Serializable {
	public static EmployeeFileSoap toSoapModel(EmployeeFile model) {
		EmployeeFileSoap soapModel = new EmployeeFileSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setEmployeeFileId(model.getEmployeeFileId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setEmployeeId(model.getEmployeeId());
		soapModel.setFileEntryId(model.getFileEntryId());

		return soapModel;
	}

	public static EmployeeFileSoap[] toSoapModels(EmployeeFile[] models) {
		EmployeeFileSoap[] soapModels = new EmployeeFileSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static EmployeeFileSoap[][] toSoapModels(EmployeeFile[][] models) {
		EmployeeFileSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new EmployeeFileSoap[models.length][models[0].length];
		}
		else {
			soapModels = new EmployeeFileSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static EmployeeFileSoap[] toSoapModels(List<EmployeeFile> models) {
		List<EmployeeFileSoap> soapModels = new ArrayList<EmployeeFileSoap>(models.size());

		for (EmployeeFile model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new EmployeeFileSoap[soapModels.size()]);
	}

	public EmployeeFileSoap() {
	}

	public long getPrimaryKey() {
		return _employeeFileId;
	}

	public void setPrimaryKey(long pk) {
		setEmployeeFileId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getEmployeeFileId() {
		return _employeeFileId;
	}

	public void setEmployeeFileId(long employeeFileId) {
		_employeeFileId = employeeFileId;
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

	public long getEmployeeId() {
		return _employeeId;
	}

	public void setEmployeeId(long employeeId) {
		_employeeId = employeeId;
	}

	public long getFileEntryId() {
		return _fileEntryId;
	}

	public void setFileEntryId(long fileEntryId) {
		_fileEntryId = fileEntryId;
	}

	private String _uuid;
	private long _employeeFileId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _employeeId;
	private long _fileEntryId;
}