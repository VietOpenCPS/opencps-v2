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
public class ProcessStepSoap implements Serializable {
	public static ProcessStepSoap toSoapModel(ProcessStep model) {
		ProcessStepSoap soapModel = new ProcessStepSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setProcessStepId(model.getProcessStepId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setStepCode(model.getStepCode());
		soapModel.setServiceProcessId(model.getServiceProcessId());
		soapModel.setStepName(model.getStepName());
		soapModel.setSequenceNo(model.getSequenceNo());
		soapModel.setDossierStatus(model.getDossierStatus());
		soapModel.setDossierSubStatus(model.getDossierSubStatus());
		soapModel.setDurationCount(model.getDurationCount());
		soapModel.setCustomProcessUrl(model.getCustomProcessUrl());
		soapModel.setStepInstruction(model.getStepInstruction());
		soapModel.setBriefNote(model.getBriefNote());
		soapModel.setEditable(model.isEditable());
		soapModel.setRestrictDossier(model.getRestrictDossier());
		soapModel.setLockState(model.getLockState());
		soapModel.setGroupName(model.getGroupName());
		soapModel.setRoleAsStep(model.getRoleAsStep());
		soapModel.setCheckInput(model.getCheckInput());

		return soapModel;
	}

	public static ProcessStepSoap[] toSoapModels(ProcessStep[] models) {
		ProcessStepSoap[] soapModels = new ProcessStepSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ProcessStepSoap[][] toSoapModels(ProcessStep[][] models) {
		ProcessStepSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ProcessStepSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ProcessStepSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ProcessStepSoap[] toSoapModels(List<ProcessStep> models) {
		List<ProcessStepSoap> soapModels = new ArrayList<ProcessStepSoap>(models.size());

		for (ProcessStep model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ProcessStepSoap[soapModels.size()]);
	}

	public ProcessStepSoap() {
	}

	public long getPrimaryKey() {
		return _processStepId;
	}

	public void setPrimaryKey(long pk) {
		setProcessStepId(pk);
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

	public String getStepName() {
		return _stepName;
	}

	public void setStepName(String stepName) {
		_stepName = stepName;
	}

	public String getSequenceNo() {
		return _sequenceNo;
	}

	public void setSequenceNo(String sequenceNo) {
		_sequenceNo = sequenceNo;
	}

	public String getDossierStatus() {
		return _dossierStatus;
	}

	public void setDossierStatus(String dossierStatus) {
		_dossierStatus = dossierStatus;
	}

	public String getDossierSubStatus() {
		return _dossierSubStatus;
	}

	public void setDossierSubStatus(String dossierSubStatus) {
		_dossierSubStatus = dossierSubStatus;
	}

	public double getDurationCount() {
		return _durationCount;
	}

	public void setDurationCount(double durationCount) {
		_durationCount = durationCount;
	}

	public String getCustomProcessUrl() {
		return _customProcessUrl;
	}

	public void setCustomProcessUrl(String customProcessUrl) {
		_customProcessUrl = customProcessUrl;
	}

	public String getStepInstruction() {
		return _stepInstruction;
	}

	public void setStepInstruction(String stepInstruction) {
		_stepInstruction = stepInstruction;
	}

	public String getBriefNote() {
		return _briefNote;
	}

	public void setBriefNote(String briefNote) {
		_briefNote = briefNote;
	}

	public boolean getEditable() {
		return _editable;
	}

	public boolean isEditable() {
		return _editable;
	}

	public void setEditable(boolean editable) {
		_editable = editable;
	}

	public String getRestrictDossier() {
		return _restrictDossier;
	}

	public void setRestrictDossier(String restrictDossier) {
		_restrictDossier = restrictDossier;
	}

	public String getLockState() {
		return _lockState;
	}

	public void setLockState(String lockState) {
		_lockState = lockState;
	}

	public String getGroupName() {
		return _groupName;
	}

	public void setGroupName(String groupName) {
		_groupName = groupName;
	}

	public String getRoleAsStep() {
		return _roleAsStep;
	}

	public void setRoleAsStep(String roleAsStep) {
		_roleAsStep = roleAsStep;
	}

	public int getCheckInput() {
		return _checkInput;
	}

	public void setCheckInput(int checkInput) {
		_checkInput = checkInput;
	}

	private String _uuid;
	private long _processStepId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _stepCode;
	private long _serviceProcessId;
	private String _stepName;
	private String _sequenceNo;
	private String _dossierStatus;
	private String _dossierSubStatus;
	private double _durationCount;
	private String _customProcessUrl;
	private String _stepInstruction;
	private String _briefNote;
	private boolean _editable;
	private String _restrictDossier;
	private String _lockState;
	private String _groupName;
	private String _roleAsStep;
	private int _checkInput;
}