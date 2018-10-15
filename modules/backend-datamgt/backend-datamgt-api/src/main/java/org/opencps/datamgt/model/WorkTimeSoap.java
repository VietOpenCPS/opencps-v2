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
 * @author khoavu
 * @generated
 */
@ProviderType
public class WorkTimeSoap implements Serializable {
	public static WorkTimeSoap toSoapModel(WorkTime model) {
		WorkTimeSoap soapModel = new WorkTimeSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setWorkTimeId(model.getWorkTimeId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setDay(model.getDay());
		soapModel.setHours(model.getHours());

		return soapModel;
	}

	public static WorkTimeSoap[] toSoapModels(WorkTime[] models) {
		WorkTimeSoap[] soapModels = new WorkTimeSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static WorkTimeSoap[][] toSoapModels(WorkTime[][] models) {
		WorkTimeSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new WorkTimeSoap[models.length][models[0].length];
		}
		else {
			soapModels = new WorkTimeSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static WorkTimeSoap[] toSoapModels(List<WorkTime> models) {
		List<WorkTimeSoap> soapModels = new ArrayList<WorkTimeSoap>(models.size());

		for (WorkTime model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new WorkTimeSoap[soapModels.size()]);
	}

	public WorkTimeSoap() {
	}

	public long getPrimaryKey() {
		return _workTimeId;
	}

	public void setPrimaryKey(long pk) {
		setWorkTimeId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getWorkTimeId() {
		return _workTimeId;
	}

	public void setWorkTimeId(long workTimeId) {
		_workTimeId = workTimeId;
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

	public int getDay() {
		return _day;
	}

	public void setDay(int day) {
		_day = day;
	}

	public String getHours() {
		return _hours;
	}

	public void setHours(String hours) {
		_hours = hours;
	}

	private String _uuid;
	private long _workTimeId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private int _day;
	private String _hours;
}