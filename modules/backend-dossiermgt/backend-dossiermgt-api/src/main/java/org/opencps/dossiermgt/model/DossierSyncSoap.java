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
public class DossierSyncSoap implements Serializable {
	public static DossierSyncSoap toSoapModel(DossierSync model) {
		DossierSyncSoap soapModel = new DossierSyncSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setDossierSyncId(model.getDossierSyncId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setDossierId(model.getDossierId());
		soapModel.setDossierRefUid(model.getDossierRefUid());
		soapModel.setSyncRefUid(model.getSyncRefUid());
		soapModel.setDossierActionId(model.getDossierActionId());
		soapModel.setActionCode(model.getActionCode());
		soapModel.setActionName(model.getActionName());
		soapModel.setActionUser(model.getActionUser());
		soapModel.setActionNote(model.getActionNote());
		soapModel.setSyncType(model.getSyncType());
		soapModel.setInfoType(model.getInfoType());
		soapModel.setPayload(model.getPayload());
		soapModel.setServerNo(model.getServerNo());
		soapModel.setState(model.getState());
		soapModel.setRetry(model.getRetry());
		soapModel.setMessageText(model.getMessageText());
		soapModel.setAcknowlegement(model.getAcknowlegement());

		return soapModel;
	}

	public static DossierSyncSoap[] toSoapModels(DossierSync[] models) {
		DossierSyncSoap[] soapModels = new DossierSyncSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DossierSyncSoap[][] toSoapModels(DossierSync[][] models) {
		DossierSyncSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DossierSyncSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DossierSyncSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DossierSyncSoap[] toSoapModels(List<DossierSync> models) {
		List<DossierSyncSoap> soapModels = new ArrayList<DossierSyncSoap>(models.size());

		for (DossierSync model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DossierSyncSoap[soapModels.size()]);
	}

	public DossierSyncSoap() {
	}

	public long getPrimaryKey() {
		return _DossierSyncId;
	}

	public void setPrimaryKey(long pk) {
		setDossierSyncId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getDossierSyncId() {
		return _DossierSyncId;
	}

	public void setDossierSyncId(long DossierSyncId) {
		_DossierSyncId = DossierSyncId;
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

	public long getDossierId() {
		return _dossierId;
	}

	public void setDossierId(long dossierId) {
		_dossierId = dossierId;
	}

	public String getDossierRefUid() {
		return _dossierRefUid;
	}

	public void setDossierRefUid(String dossierRefUid) {
		_dossierRefUid = dossierRefUid;
	}

	public String getSyncRefUid() {
		return _syncRefUid;
	}

	public void setSyncRefUid(String syncRefUid) {
		_syncRefUid = syncRefUid;
	}

	public long getDossierActionId() {
		return _dossierActionId;
	}

	public void setDossierActionId(long dossierActionId) {
		_dossierActionId = dossierActionId;
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

	public String getActionUser() {
		return _actionUser;
	}

	public void setActionUser(String actionUser) {
		_actionUser = actionUser;
	}

	public String getActionNote() {
		return _actionNote;
	}

	public void setActionNote(String actionNote) {
		_actionNote = actionNote;
	}

	public int getSyncType() {
		return _syncType;
	}

	public void setSyncType(int syncType) {
		_syncType = syncType;
	}

	public int getInfoType() {
		return _infoType;
	}

	public void setInfoType(int infoType) {
		_infoType = infoType;
	}

	public String getPayload() {
		return _payload;
	}

	public void setPayload(String payload) {
		_payload = payload;
	}

	public String getServerNo() {
		return _serverNo;
	}

	public void setServerNo(String serverNo) {
		_serverNo = serverNo;
	}

	public int getState() {
		return _state;
	}

	public void setState(int state) {
		_state = state;
	}

	public int getRetry() {
		return _retry;
	}

	public void setRetry(int retry) {
		_retry = retry;
	}

	public String getMessageText() {
		return _messageText;
	}

	public void setMessageText(String messageText) {
		_messageText = messageText;
	}

	public String getAcknowlegement() {
		return _acknowlegement;
	}

	public void setAcknowlegement(String acknowlegement) {
		_acknowlegement = acknowlegement;
	}

	private String _uuid;
	private long _DossierSyncId;
	private long _groupId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _dossierId;
	private String _dossierRefUid;
	private String _syncRefUid;
	private long _dossierActionId;
	private String _actionCode;
	private String _actionName;
	private String _actionUser;
	private String _actionNote;
	private int _syncType;
	private int _infoType;
	private String _payload;
	private String _serverNo;
	private int _state;
	private int _retry;
	private String _messageText;
	private String _acknowlegement;
}