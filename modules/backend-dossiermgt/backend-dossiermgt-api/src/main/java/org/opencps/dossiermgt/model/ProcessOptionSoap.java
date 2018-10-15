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
public class ProcessOptionSoap implements Serializable {
	public static ProcessOptionSoap toSoapModel(ProcessOption model) {
		ProcessOptionSoap soapModel = new ProcessOptionSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setProcessOptionId(model.getProcessOptionId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setServiceConfigId(model.getServiceConfigId());
		soapModel.setOptionOrder(model.getOptionOrder());
		soapModel.setOptionName(model.getOptionName());
		soapModel.setAutoSelect(model.getAutoSelect());
		soapModel.setDossierTemplateId(model.getDossierTemplateId());
		soapModel.setServiceProcessId(model.getServiceProcessId());
		soapModel.setInstructionNote(model.getInstructionNote());
		soapModel.setSubmissionNote(model.getSubmissionNote());
		soapModel.setSampleCount(model.getSampleCount());

		return soapModel;
	}

	public static ProcessOptionSoap[] toSoapModels(ProcessOption[] models) {
		ProcessOptionSoap[] soapModels = new ProcessOptionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ProcessOptionSoap[][] toSoapModels(ProcessOption[][] models) {
		ProcessOptionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ProcessOptionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ProcessOptionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ProcessOptionSoap[] toSoapModels(List<ProcessOption> models) {
		List<ProcessOptionSoap> soapModels = new ArrayList<ProcessOptionSoap>(models.size());

		for (ProcessOption model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ProcessOptionSoap[soapModels.size()]);
	}

	public ProcessOptionSoap() {
	}

	public long getPrimaryKey() {
		return _processOptionId;
	}

	public void setPrimaryKey(long pk) {
		setProcessOptionId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getProcessOptionId() {
		return _processOptionId;
	}

	public void setProcessOptionId(long processOptionId) {
		_processOptionId = processOptionId;
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

	public long getServiceConfigId() {
		return _serviceConfigId;
	}

	public void setServiceConfigId(long serviceConfigId) {
		_serviceConfigId = serviceConfigId;
	}

	public int getOptionOrder() {
		return _optionOrder;
	}

	public void setOptionOrder(int optionOrder) {
		_optionOrder = optionOrder;
	}

	public String getOptionName() {
		return _optionName;
	}

	public void setOptionName(String optionName) {
		_optionName = optionName;
	}

	public String getAutoSelect() {
		return _autoSelect;
	}

	public void setAutoSelect(String autoSelect) {
		_autoSelect = autoSelect;
	}

	public long getDossierTemplateId() {
		return _dossierTemplateId;
	}

	public void setDossierTemplateId(long dossierTemplateId) {
		_dossierTemplateId = dossierTemplateId;
	}

	public long getServiceProcessId() {
		return _serviceProcessId;
	}

	public void setServiceProcessId(long serviceProcessId) {
		_serviceProcessId = serviceProcessId;
	}

	public String getInstructionNote() {
		return _instructionNote;
	}

	public void setInstructionNote(String instructionNote) {
		_instructionNote = instructionNote;
	}

	public String getSubmissionNote() {
		return _submissionNote;
	}

	public void setSubmissionNote(String submissionNote) {
		_submissionNote = submissionNote;
	}

	public long getSampleCount() {
		return _sampleCount;
	}

	public void setSampleCount(long sampleCount) {
		_sampleCount = sampleCount;
	}

	private String _uuid;
	private long _processOptionId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _serviceConfigId;
	private int _optionOrder;
	private String _optionName;
	private String _autoSelect;
	private long _dossierTemplateId;
	private long _serviceProcessId;
	private String _instructionNote;
	private String _submissionNote;
	private long _sampleCount;
}