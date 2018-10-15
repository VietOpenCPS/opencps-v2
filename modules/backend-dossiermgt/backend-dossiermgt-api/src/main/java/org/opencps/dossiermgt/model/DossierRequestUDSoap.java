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
public class DossierRequestUDSoap implements Serializable {
	public static DossierRequestUDSoap toSoapModel(DossierRequestUD model) {
		DossierRequestUDSoap soapModel = new DossierRequestUDSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setDossierRequestId(model.getDossierRequestId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setDossierId(model.getDossierId());
		soapModel.setReferenceUid(model.getReferenceUid());
		soapModel.setRequestType(model.getRequestType());
		soapModel.setComment(model.getComment());
		soapModel.setIsNew(model.getIsNew());
		soapModel.setStatusReg(model.getStatusReg());

		return soapModel;
	}

	public static DossierRequestUDSoap[] toSoapModels(DossierRequestUD[] models) {
		DossierRequestUDSoap[] soapModels = new DossierRequestUDSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DossierRequestUDSoap[][] toSoapModels(
		DossierRequestUD[][] models) {
		DossierRequestUDSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DossierRequestUDSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DossierRequestUDSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DossierRequestUDSoap[] toSoapModels(
		List<DossierRequestUD> models) {
		List<DossierRequestUDSoap> soapModels = new ArrayList<DossierRequestUDSoap>(models.size());

		for (DossierRequestUD model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DossierRequestUDSoap[soapModels.size()]);
	}

	public DossierRequestUDSoap() {
	}

	public long getPrimaryKey() {
		return _dossierRequestId;
	}

	public void setPrimaryKey(long pk) {
		setDossierRequestId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getDossierRequestId() {
		return _dossierRequestId;
	}

	public void setDossierRequestId(long dossierRequestId) {
		_dossierRequestId = dossierRequestId;
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

	public String getReferenceUid() {
		return _referenceUid;
	}

	public void setReferenceUid(String referenceUid) {
		_referenceUid = referenceUid;
	}

	public String getRequestType() {
		return _requestType;
	}

	public void setRequestType(String requestType) {
		_requestType = requestType;
	}

	public String getComment() {
		return _comment;
	}

	public void setComment(String comment) {
		_comment = comment;
	}

	public int getIsNew() {
		return _isNew;
	}

	public void setIsNew(int isNew) {
		_isNew = isNew;
	}

	public int getStatusReg() {
		return _statusReg;
	}

	public void setStatusReg(int statusReg) {
		_statusReg = statusReg;
	}

	private String _uuid;
	private long _dossierRequestId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _dossierId;
	private String _referenceUid;
	private String _requestType;
	private String _comment;
	private int _isNew;
	private int _statusReg;
}