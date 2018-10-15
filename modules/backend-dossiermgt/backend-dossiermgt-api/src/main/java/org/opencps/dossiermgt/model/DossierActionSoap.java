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
public class DossierActionSoap implements Serializable {
	public static DossierActionSoap toSoapModel(DossierAction model) {
		DossierActionSoap soapModel = new DossierActionSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setDossierActionId(model.getDossierActionId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setDossierId(model.getDossierId());
		soapModel.setServiceProcessId(model.getServiceProcessId());
		soapModel.setPreviousActionId(model.getPreviousActionId());
		soapModel.setFromStepCode(model.getFromStepCode());
		soapModel.setFromStepName(model.getFromStepName());
		soapModel.setFromSequenceNo(model.getFromSequenceNo());
		soapModel.setActionCode(model.getActionCode());
		soapModel.setActionUser(model.getActionUser());
		soapModel.setActionName(model.getActionName());
		soapModel.setActionNote(model.getActionNote());
		soapModel.setActionOverdue(model.getActionOverdue());
		soapModel.setSyncActionCode(model.getSyncActionCode());
		soapModel.setPending(model.isPending());
		soapModel.setRollbackable(model.isRollbackable());
		soapModel.setStepCode(model.getStepCode());
		soapModel.setStepName(model.getStepName());
		soapModel.setSequenceNo(model.getSequenceNo());
		soapModel.setDueDate(model.getDueDate());
		soapModel.setNextActionId(model.getNextActionId());
		soapModel.setPayload(model.getPayload());
		soapModel.setStepInstruction(model.getStepInstruction());
		soapModel.setState(model.getState());
		soapModel.setEventStatus(model.getEventStatus());

		return soapModel;
	}

	public static DossierActionSoap[] toSoapModels(DossierAction[] models) {
		DossierActionSoap[] soapModels = new DossierActionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DossierActionSoap[][] toSoapModels(DossierAction[][] models) {
		DossierActionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DossierActionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DossierActionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DossierActionSoap[] toSoapModels(List<DossierAction> models) {
		List<DossierActionSoap> soapModels = new ArrayList<DossierActionSoap>(models.size());

		for (DossierAction model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DossierActionSoap[soapModels.size()]);
	}

	public DossierActionSoap() {
	}

	public long getPrimaryKey() {
		return _dossierActionId;
	}

	public void setPrimaryKey(long pk) {
		setDossierActionId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getDossierActionId() {
		return _dossierActionId;
	}

	public void setDossierActionId(long dossierActionId) {
		_dossierActionId = dossierActionId;
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

	public long getDossierId() {
		return _dossierId;
	}

	public void setDossierId(long dossierId) {
		_dossierId = dossierId;
	}

	public long getServiceProcessId() {
		return _serviceProcessId;
	}

	public void setServiceProcessId(long serviceProcessId) {
		_serviceProcessId = serviceProcessId;
	}

	public long getPreviousActionId() {
		return _previousActionId;
	}

	public void setPreviousActionId(long previousActionId) {
		_previousActionId = previousActionId;
	}

	public String getFromStepCode() {
		return _fromStepCode;
	}

	public void setFromStepCode(String fromStepCode) {
		_fromStepCode = fromStepCode;
	}

	public String getFromStepName() {
		return _fromStepName;
	}

	public void setFromStepName(String fromStepName) {
		_fromStepName = fromStepName;
	}

	public String getFromSequenceNo() {
		return _fromSequenceNo;
	}

	public void setFromSequenceNo(String fromSequenceNo) {
		_fromSequenceNo = fromSequenceNo;
	}

	public String getActionCode() {
		return _actionCode;
	}

	public void setActionCode(String actionCode) {
		_actionCode = actionCode;
	}

	public String getActionUser() {
		return _actionUser;
	}

	public void setActionUser(String actionUser) {
		_actionUser = actionUser;
	}

	public String getActionName() {
		return _actionName;
	}

	public void setActionName(String actionName) {
		_actionName = actionName;
	}

	public String getActionNote() {
		return _actionNote;
	}

	public void setActionNote(String actionNote) {
		_actionNote = actionNote;
	}

	public int getActionOverdue() {
		return _actionOverdue;
	}

	public void setActionOverdue(int actionOverdue) {
		_actionOverdue = actionOverdue;
	}

	public String getSyncActionCode() {
		return _syncActionCode;
	}

	public void setSyncActionCode(String syncActionCode) {
		_syncActionCode = syncActionCode;
	}

	public boolean getPending() {
		return _pending;
	}

	public boolean isPending() {
		return _pending;
	}

	public void setPending(boolean pending) {
		_pending = pending;
	}

	public boolean getRollbackable() {
		return _rollbackable;
	}

	public boolean isRollbackable() {
		return _rollbackable;
	}

	public void setRollbackable(boolean rollbackable) {
		_rollbackable = rollbackable;
	}

	public String getStepCode() {
		return _stepCode;
	}

	public void setStepCode(String stepCode) {
		_stepCode = stepCode;
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

	public Date getDueDate() {
		return _dueDate;
	}

	public void setDueDate(Date dueDate) {
		_dueDate = dueDate;
	}

	public long getNextActionId() {
		return _nextActionId;
	}

	public void setNextActionId(long nextActionId) {
		_nextActionId = nextActionId;
	}

	public String getPayload() {
		return _payload;
	}

	public void setPayload(String payload) {
		_payload = payload;
	}

	public String getStepInstruction() {
		return _stepInstruction;
	}

	public void setStepInstruction(String stepInstruction) {
		_stepInstruction = stepInstruction;
	}

	public int getState() {
		return _state;
	}

	public void setState(int state) {
		_state = state;
	}

	public int getEventStatus() {
		return _eventStatus;
	}

	public void setEventStatus(int eventStatus) {
		_eventStatus = eventStatus;
	}

	private String _uuid;
	private long _dossierActionId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _dossierId;
	private long _serviceProcessId;
	private long _previousActionId;
	private String _fromStepCode;
	private String _fromStepName;
	private String _fromSequenceNo;
	private String _actionCode;
	private String _actionUser;
	private String _actionName;
	private String _actionNote;
	private int _actionOverdue;
	private String _syncActionCode;
	private boolean _pending;
	private boolean _rollbackable;
	private String _stepCode;
	private String _stepName;
	private String _sequenceNo;
	private Date _dueDate;
	private long _nextActionId;
	private String _payload;
	private String _stepInstruction;
	private int _state;
	private int _eventStatus;
}