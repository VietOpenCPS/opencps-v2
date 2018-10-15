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

package org.opencps.usermgt.model;

import aQute.bnd.annotation.ProviderType;

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
public class EmployeeJobPosSoap implements Serializable {
	public static EmployeeJobPosSoap toSoapModel(EmployeeJobPos model) {
		EmployeeJobPosSoap soapModel = new EmployeeJobPosSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setEmployeeJobPosId(model.getEmployeeJobPosId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setEmployeeId(model.getEmployeeId());
		soapModel.setJobPostId(model.getJobPostId());
		soapModel.setWorkingUnitId(model.getWorkingUnitId());

		return soapModel;
	}

	public static EmployeeJobPosSoap[] toSoapModels(EmployeeJobPos[] models) {
		EmployeeJobPosSoap[] soapModels = new EmployeeJobPosSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static EmployeeJobPosSoap[][] toSoapModels(EmployeeJobPos[][] models) {
		EmployeeJobPosSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new EmployeeJobPosSoap[models.length][models[0].length];
		}
		else {
			soapModels = new EmployeeJobPosSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static EmployeeJobPosSoap[] toSoapModels(List<EmployeeJobPos> models) {
		List<EmployeeJobPosSoap> soapModels = new ArrayList<EmployeeJobPosSoap>(models.size());

		for (EmployeeJobPos model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new EmployeeJobPosSoap[soapModels.size()]);
	}

	public EmployeeJobPosSoap() {
	}

	public long getPrimaryKey() {
		return _employeeJobPosId;
	}

	public void setPrimaryKey(long pk) {
		setEmployeeJobPosId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getEmployeeJobPosId() {
		return _employeeJobPosId;
	}

	public void setEmployeeJobPosId(long employeeJobPosId) {
		_employeeJobPosId = employeeJobPosId;
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

	public long getJobPostId() {
		return _jobPostId;
	}

	public void setJobPostId(long jobPostId) {
		_jobPostId = jobPostId;
	}

	public long getWorkingUnitId() {
		return _workingUnitId;
	}

	public void setWorkingUnitId(long workingUnitId) {
		_workingUnitId = workingUnitId;
	}

	private String _uuid;
	private long _employeeJobPosId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _employeeId;
	private long _jobPostId;
	private long _workingUnitId;
}