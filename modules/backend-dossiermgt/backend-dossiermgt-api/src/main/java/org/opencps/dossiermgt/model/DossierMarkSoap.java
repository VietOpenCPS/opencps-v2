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
public class DossierMarkSoap implements Serializable {
	public static DossierMarkSoap toSoapModel(DossierMark model) {
		DossierMarkSoap soapModel = new DossierMarkSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setDossierMarkId(model.getDossierMarkId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setDossierId(model.getDossierId());
		soapModel.setDossierPartNo(model.getDossierPartNo());
		soapModel.setFileCheck(model.getFileCheck());
		soapModel.setFileMark(model.getFileMark());
		soapModel.setFileComment(model.getFileComment());

		return soapModel;
	}

	public static DossierMarkSoap[] toSoapModels(DossierMark[] models) {
		DossierMarkSoap[] soapModels = new DossierMarkSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DossierMarkSoap[][] toSoapModels(DossierMark[][] models) {
		DossierMarkSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DossierMarkSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DossierMarkSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DossierMarkSoap[] toSoapModels(List<DossierMark> models) {
		List<DossierMarkSoap> soapModels = new ArrayList<DossierMarkSoap>(models.size());

		for (DossierMark model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DossierMarkSoap[soapModels.size()]);
	}

	public DossierMarkSoap() {
	}

	public long getPrimaryKey() {
		return _dossierMarkId;
	}

	public void setPrimaryKey(long pk) {
		setDossierMarkId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getDossierMarkId() {
		return _dossierMarkId;
	}

	public void setDossierMarkId(long dossierMarkId) {
		_dossierMarkId = dossierMarkId;
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

	public long getDossierId() {
		return _dossierId;
	}

	public void setDossierId(long dossierId) {
		_dossierId = dossierId;
	}

	public String getDossierPartNo() {
		return _dossierPartNo;
	}

	public void setDossierPartNo(String dossierPartNo) {
		_dossierPartNo = dossierPartNo;
	}

	public int getFileCheck() {
		return _fileCheck;
	}

	public void setFileCheck(int fileCheck) {
		_fileCheck = fileCheck;
	}

	public int getFileMark() {
		return _fileMark;
	}

	public void setFileMark(int fileMark) {
		_fileMark = fileMark;
	}

	public String getFileComment() {
		return _fileComment;
	}

	public void setFileComment(String fileComment) {
		_fileComment = fileComment;
	}

	private String _uuid;
	private long _dossierMarkId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _dossierId;
	private String _dossierPartNo;
	private int _fileCheck;
	private int _fileMark;
	private String _fileComment;
}