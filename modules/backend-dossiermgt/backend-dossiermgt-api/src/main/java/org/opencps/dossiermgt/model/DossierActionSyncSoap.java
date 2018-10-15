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
public class DossierActionSyncSoap implements Serializable {
	public static DossierActionSyncSoap toSoapModel(DossierActionSync model) {
		DossierActionSyncSoap soapModel = new DossierActionSyncSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setDossierActionSyncId(model.getDossierActionSyncId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setDossierId(model.getDossierId());
		soapModel.setDossierActionId(model.getDossierActionId());
		soapModel.setCreateDossier(model.isCreateDossier());
		soapModel.setReferenceUid(model.getReferenceUid());
		soapModel.setActionCode(model.getActionCode());
		soapModel.setActionUser(model.getActionUser());
		soapModel.setActionNote(model.getActionNote());

		return soapModel;
	}

	public static DossierActionSyncSoap[] toSoapModels(
		DossierActionSync[] models) {
		DossierActionSyncSoap[] soapModels = new DossierActionSyncSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DossierActionSyncSoap[][] toSoapModels(
		DossierActionSync[][] models) {
		DossierActionSyncSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DossierActionSyncSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DossierActionSyncSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DossierActionSyncSoap[] toSoapModels(
		List<DossierActionSync> models) {
		List<DossierActionSyncSoap> soapModels = new ArrayList<DossierActionSyncSoap>(models.size());

		for (DossierActionSync model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DossierActionSyncSoap[soapModels.size()]);
	}

	public DossierActionSyncSoap() {
	}

	public long getPrimaryKey() {
		return _dossierActionSyncId;
	}

	public void setPrimaryKey(long pk) {
		setDossierActionSyncId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getDossierActionSyncId() {
		return _dossierActionSyncId;
	}

	public void setDossierActionSyncId(long dossierActionSyncId) {
		_dossierActionSyncId = dossierActionSyncId;
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

	public long getDossierActionId() {
		return _dossierActionId;
	}

	public void setDossierActionId(long dossierActionId) {
		_dossierActionId = dossierActionId;
	}

	public boolean getCreateDossier() {
		return _createDossier;
	}

	public boolean isCreateDossier() {
		return _createDossier;
	}

	public void setCreateDossier(boolean createDossier) {
		_createDossier = createDossier;
	}

	public String getReferenceUid() {
		return _referenceUid;
	}

	public void setReferenceUid(String referenceUid) {
		_referenceUid = referenceUid;
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

	public String getActionNote() {
		return _actionNote;
	}

	public void setActionNote(String actionNote) {
		_actionNote = actionNote;
	}

	private String _uuid;
	private long _dossierActionSyncId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _dossierId;
	private long _dossierActionId;
	private boolean _createDossier;
	private String _referenceUid;
	private String _actionCode;
	private String _actionUser;
	private String _actionNote;
}