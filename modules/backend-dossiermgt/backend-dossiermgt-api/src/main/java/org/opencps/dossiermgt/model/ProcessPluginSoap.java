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
public class ProcessPluginSoap implements Serializable {
	public static ProcessPluginSoap toSoapModel(ProcessPlugin model) {
		ProcessPluginSoap soapModel = new ProcessPluginSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setProcessPluginId(model.getProcessPluginId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setStepCode(model.getStepCode());
		soapModel.setServiceProcessId(model.getServiceProcessId());
		soapModel.setPluginName(model.getPluginName());
		soapModel.setPluginType(model.getPluginType());
		soapModel.setSequenceNo(model.getSequenceNo());
		soapModel.setPluginForm(model.getPluginForm());
		soapModel.setSampleData(model.getSampleData());
		soapModel.setAutoRun(model.isAutoRun());

		return soapModel;
	}

	public static ProcessPluginSoap[] toSoapModels(ProcessPlugin[] models) {
		ProcessPluginSoap[] soapModels = new ProcessPluginSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ProcessPluginSoap[][] toSoapModels(ProcessPlugin[][] models) {
		ProcessPluginSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ProcessPluginSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ProcessPluginSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ProcessPluginSoap[] toSoapModels(List<ProcessPlugin> models) {
		List<ProcessPluginSoap> soapModels = new ArrayList<ProcessPluginSoap>(models.size());

		for (ProcessPlugin model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ProcessPluginSoap[soapModels.size()]);
	}

	public ProcessPluginSoap() {
	}

	public long getPrimaryKey() {
		return _processPluginId;
	}

	public void setPrimaryKey(long pk) {
		setProcessPluginId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getProcessPluginId() {
		return _processPluginId;
	}

	public void setProcessPluginId(long processPluginId) {
		_processPluginId = processPluginId;
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

	public String getStepCode() {
		return _stepCode;
	}

	public void setStepCode(String stepCode) {
		_stepCode = stepCode;
	}

	public long getServiceProcessId() {
		return _serviceProcessId;
	}

	public void setServiceProcessId(long serviceProcessId) {
		_serviceProcessId = serviceProcessId;
	}

	public String getPluginName() {
		return _pluginName;
	}

	public void setPluginName(String pluginName) {
		_pluginName = pluginName;
	}

	public int getPluginType() {
		return _pluginType;
	}

	public void setPluginType(int pluginType) {
		_pluginType = pluginType;
	}

	public String getSequenceNo() {
		return _sequenceNo;
	}

	public void setSequenceNo(String sequenceNo) {
		_sequenceNo = sequenceNo;
	}

	public String getPluginForm() {
		return _pluginForm;
	}

	public void setPluginForm(String pluginForm) {
		_pluginForm = pluginForm;
	}

	public String getSampleData() {
		return _sampleData;
	}

	public void setSampleData(String sampleData) {
		_sampleData = sampleData;
	}

	public boolean getAutoRun() {
		return _autoRun;
	}

	public boolean isAutoRun() {
		return _autoRun;
	}

	public void setAutoRun(boolean autoRun) {
		_autoRun = autoRun;
	}

	private String _uuid;
	private long _processPluginId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _stepCode;
	private long _serviceProcessId;
	private String _pluginName;
	private int _pluginType;
	private String _sequenceNo;
	private String _pluginForm;
	private String _sampleData;
	private boolean _autoRun;
}