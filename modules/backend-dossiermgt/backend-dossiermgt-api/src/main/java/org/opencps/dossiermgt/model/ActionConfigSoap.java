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
public class ActionConfigSoap implements Serializable {
	public static ActionConfigSoap toSoapModel(ActionConfig model) {
		ActionConfigSoap soapModel = new ActionConfigSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setActionConfigId(model.getActionConfigId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setActionCode(model.getActionCode());
		soapModel.setActionName(model.getActionName());
		soapModel.setExtraForm(model.isExtraForm());
		soapModel.setFormConfig(model.getFormConfig());
		soapModel.setSampleData(model.getSampleData());
		soapModel.setInsideProcess(model.isInsideProcess());
		soapModel.setUserNote(model.getUserNote());
		soapModel.setSyncType(model.getSyncType());
		soapModel.setEventType(model.getEventType());
		soapModel.setInfoType(model.getInfoType());
		soapModel.setPending(model.isPending());
		soapModel.setRollbackable(model.isRollbackable());
		soapModel.setNotificationType(model.getNotificationType());
		soapModel.setDocumentType(model.getDocumentType());
		soapModel.setMappingAction(model.getMappingAction());

		return soapModel;
	}

	public static ActionConfigSoap[] toSoapModels(ActionConfig[] models) {
		ActionConfigSoap[] soapModels = new ActionConfigSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ActionConfigSoap[][] toSoapModels(ActionConfig[][] models) {
		ActionConfigSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ActionConfigSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ActionConfigSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ActionConfigSoap[] toSoapModels(List<ActionConfig> models) {
		List<ActionConfigSoap> soapModels = new ArrayList<ActionConfigSoap>(models.size());

		for (ActionConfig model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ActionConfigSoap[soapModels.size()]);
	}

	public ActionConfigSoap() {
	}

	public long getPrimaryKey() {
		return _actionConfigId;
	}

	public void setPrimaryKey(long pk) {
		setActionConfigId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getActionConfigId() {
		return _actionConfigId;
	}

	public void setActionConfigId(long actionConfigId) {
		_actionConfigId = actionConfigId;
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

	public String getActionCode() {
		return _actionCode;
	}

	public void setActionCode(String actionCode) {
		_actionCode = actionCode;
	}

	public String getActionName() {
		return _actionName;
	}

	public void setActionName(String actionName) {
		_actionName = actionName;
	}

	public boolean getExtraForm() {
		return _extraForm;
	}

	public boolean isExtraForm() {
		return _extraForm;
	}

	public void setExtraForm(boolean extraForm) {
		_extraForm = extraForm;
	}

	public String getFormConfig() {
		return _formConfig;
	}

	public void setFormConfig(String formConfig) {
		_formConfig = formConfig;
	}

	public String getSampleData() {
		return _sampleData;
	}

	public void setSampleData(String sampleData) {
		_sampleData = sampleData;
	}

	public boolean getInsideProcess() {
		return _insideProcess;
	}

	public boolean isInsideProcess() {
		return _insideProcess;
	}

	public void setInsideProcess(boolean insideProcess) {
		_insideProcess = insideProcess;
	}

	public int getUserNote() {
		return _userNote;
	}

	public void setUserNote(int userNote) {
		_userNote = userNote;
	}

	public int getSyncType() {
		return _syncType;
	}

	public void setSyncType(int syncType) {
		_syncType = syncType;
	}

	public int getEventType() {
		return _eventType;
	}

	public void setEventType(int eventType) {
		_eventType = eventType;
	}

	public int getInfoType() {
		return _infoType;
	}

	public void setInfoType(int infoType) {
		_infoType = infoType;
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

	public String getNotificationType() {
		return _notificationType;
	}

	public void setNotificationType(String notificationType) {
		_notificationType = notificationType;
	}

	public String getDocumentType() {
		return _documentType;
	}

	public void setDocumentType(String documentType) {
		_documentType = documentType;
	}

	public String getMappingAction() {
		return _mappingAction;
	}

	public void setMappingAction(String mappingAction) {
		_mappingAction = mappingAction;
	}

	private String _uuid;
	private long _actionConfigId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _actionCode;
	private String _actionName;
	private boolean _extraForm;
	private String _formConfig;
	private String _sampleData;
	private boolean _insideProcess;
	private int _userNote;
	private int _syncType;
	private int _eventType;
	private int _infoType;
	private boolean _pending;
	private boolean _rollbackable;
	private String _notificationType;
	private String _documentType;
	private String _mappingAction;
}