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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author huymq
 * @generated
 */
@ProviderType
public class ProcessSequenceSoap implements Serializable {
	public static ProcessSequenceSoap toSoapModel(ProcessSequence model) {
		ProcessSequenceSoap soapModel = new ProcessSequenceSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setProcessSequenceId(model.getProcessSequenceId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setServiceProcessId(model.getServiceProcessId());
		soapModel.setSequenceNo(model.getSequenceNo());
		soapModel.setSequenceName(model.getSequenceName());
		soapModel.setSequenceRole(model.getSequenceRole());
		soapModel.setDurationCount(model.getDurationCount());

		return soapModel;
	}

	public static ProcessSequenceSoap[] toSoapModels(ProcessSequence[] models) {
		ProcessSequenceSoap[] soapModels = new ProcessSequenceSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ProcessSequenceSoap[][] toSoapModels(
		ProcessSequence[][] models) {
		ProcessSequenceSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ProcessSequenceSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ProcessSequenceSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ProcessSequenceSoap[] toSoapModels(
		List<ProcessSequence> models) {
		List<ProcessSequenceSoap> soapModels = new ArrayList<ProcessSequenceSoap>(models.size());

		for (ProcessSequence model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ProcessSequenceSoap[soapModels.size()]);
	}

	public ProcessSequenceSoap() {
	}

	public long getPrimaryKey() {
		return _processSequenceId;
	}

	public void setPrimaryKey(long pk) {
		setProcessSequenceId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getProcessSequenceId() {
		return _processSequenceId;
	}

	public void setProcessSequenceId(long processSequenceId) {
		_processSequenceId = processSequenceId;
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

	public long getServiceProcessId() {
		return _serviceProcessId;
	}

	public void setServiceProcessId(long serviceProcessId) {
		_serviceProcessId = serviceProcessId;
	}

	public String getSequenceNo() {
		return _sequenceNo;
	}

	public void setSequenceNo(String sequenceNo) {
		_sequenceNo = sequenceNo;
	}

	public String getSequenceName() {
		return _sequenceName;
	}

	public void setSequenceName(String sequenceName) {
		_sequenceName = sequenceName;
	}

	public String getSequenceRole() {
		return _sequenceRole;
	}

	public void setSequenceRole(String sequenceRole) {
		_sequenceRole = sequenceRole;
	}

	public double getDurationCount() {
		return _durationCount;
	}

	public void setDurationCount(double durationCount) {
		_durationCount = durationCount;
	}

	private String _uuid;
	private long _processSequenceId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _serviceProcessId;
	private String _sequenceNo;
	private String _sequenceName;
	private String _sequenceRole;
	private double _durationCount;
}