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

package org.opencps.backend.processmgt.model;

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
		soapModel.setActionCode(model.getActionCode());
		soapModel.setActionUser(model.getActionUser());
		soapModel.setActionName(model.getActionName());
		soapModel.setActionNote(model.getActionNote());
		soapModel.setOverDue(model.getOverDue());
		soapModel.setSyncActionCode(model.getSyncActionCode());
		soapModel.setPending(model.getPending());
		soapModel.setRollback(model.getRollback());
		soapModel.setProcessStepId(model.getProcessStepId());
		soapModel.setDueDate(model.getDueDate());
		soapModel.setNextActionId(model.getNextActionId());

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

	public int getOverDue() {
		return _overDue;
	}

	public void setOverDue(int overDue) {
		_overDue = overDue;
	}

	public String getSyncActionCode() {
		return _syncActionCode;
	}

	public void setSyncActionCode(String syncActionCode) {
		_syncActionCode = syncActionCode;
	}

	public String getPending() {
		return _pending;
	}

	public void setPending(String pending) {
		_pending = pending;
	}

	public String getRollback() {
		return _rollback;
	}

	public void setRollback(String rollback) {
		_rollback = rollback;
	}

	public long getProcessStepId() {
		return _processStepId;
	}

	public void setProcessStepId(long processStepId) {
		_processStepId = processStepId;
	}

	public int getDueDate() {
		return _dueDate;
	}

	public void setDueDate(int dueDate) {
		_dueDate = dueDate;
	}

	public long getNextActionId() {
		return _nextActionId;
	}

	public void setNextActionId(long nextActionId) {
		_nextActionId = nextActionId;
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
	private String _actionCode;
	private String _actionUser;
	private String _actionName;
	private String _actionNote;
	private int _overDue;
	private String _syncActionCode;
	private String _pending;
	private String _rollback;
	private long _processStepId;
	private int _dueDate;
	private long _nextActionId;
}