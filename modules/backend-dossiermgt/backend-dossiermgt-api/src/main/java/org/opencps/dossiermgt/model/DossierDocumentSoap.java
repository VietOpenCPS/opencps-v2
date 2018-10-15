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
public class DossierDocumentSoap implements Serializable {
	public static DossierDocumentSoap toSoapModel(DossierDocument model) {
		DossierDocumentSoap soapModel = new DossierDocumentSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setDossierDocumentId(model.getDossierDocumentId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setDossierId(model.getDossierId());
		soapModel.setReferenceUid(model.getReferenceUid());
		soapModel.setDossierActionId(model.getDossierActionId());
		soapModel.setDocumentType(model.getDocumentType());
		soapModel.setDocumentName(model.getDocumentName());
		soapModel.setDocumentCode(model.getDocumentCode());
		soapModel.setDocumentFileId(model.getDocumentFileId());
		soapModel.setDocSync(model.getDocSync());

		return soapModel;
	}

	public static DossierDocumentSoap[] toSoapModels(DossierDocument[] models) {
		DossierDocumentSoap[] soapModels = new DossierDocumentSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DossierDocumentSoap[][] toSoapModels(
		DossierDocument[][] models) {
		DossierDocumentSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DossierDocumentSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DossierDocumentSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DossierDocumentSoap[] toSoapModels(
		List<DossierDocument> models) {
		List<DossierDocumentSoap> soapModels = new ArrayList<DossierDocumentSoap>(models.size());

		for (DossierDocument model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DossierDocumentSoap[soapModels.size()]);
	}

	public DossierDocumentSoap() {
	}

	public long getPrimaryKey() {
		return _DossierDocumentId;
	}

	public void setPrimaryKey(long pk) {
		setDossierDocumentId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getDossierDocumentId() {
		return _DossierDocumentId;
	}

	public void setDossierDocumentId(long DossierDocumentId) {
		_DossierDocumentId = DossierDocumentId;
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

	public String getReferenceUid() {
		return _referenceUid;
	}

	public void setReferenceUid(String referenceUid) {
		_referenceUid = referenceUid;
	}

	public long getDossierActionId() {
		return _dossierActionId;
	}

	public void setDossierActionId(long dossierActionId) {
		_dossierActionId = dossierActionId;
	}

	public String getDocumentType() {
		return _documentType;
	}

	public void setDocumentType(String documentType) {
		_documentType = documentType;
	}

	public String getDocumentName() {
		return _documentName;
	}

	public void setDocumentName(String documentName) {
		_documentName = documentName;
	}

	public String getDocumentCode() {
		return _documentCode;
	}

	public void setDocumentCode(String documentCode) {
		_documentCode = documentCode;
	}

	public long getDocumentFileId() {
		return _documentFileId;
	}

	public void setDocumentFileId(long documentFileId) {
		_documentFileId = documentFileId;
	}

	public int getDocSync() {
		return _docSync;
	}

	public void setDocSync(int docSync) {
		_docSync = docSync;
	}

	private String _uuid;
	private long _DossierDocumentId;
	private long _groupId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _dossierId;
	private String _referenceUid;
	private long _dossierActionId;
	private String _documentType;
	private String _documentName;
	private String _documentCode;
	private long _documentFileId;
	private int _docSync;
}