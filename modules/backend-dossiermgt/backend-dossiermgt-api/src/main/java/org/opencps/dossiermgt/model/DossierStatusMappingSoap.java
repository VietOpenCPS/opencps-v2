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
public class DossierStatusMappingSoap implements Serializable {
	public static DossierStatusMappingSoap toSoapModel(
		DossierStatusMapping model) {
		DossierStatusMappingSoap soapModel = new DossierStatusMappingSoap();

		soapModel.setDossierStatusMappingId(model.getDossierStatusMappingId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setStatusCode(model.getStatusCode());
		soapModel.setStatusCodeDVCQG(model.getStatusCodeDVCQG());
		soapModel.setSubStatusCode(model.getSubStatusCode());

		return soapModel;
	}

	public static DossierStatusMappingSoap[] toSoapModels(
		DossierStatusMapping[] models) {
		DossierStatusMappingSoap[] soapModels = new DossierStatusMappingSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DossierStatusMappingSoap[][] toSoapModels(
		DossierStatusMapping[][] models) {
		DossierStatusMappingSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DossierStatusMappingSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DossierStatusMappingSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DossierStatusMappingSoap[] toSoapModels(
		List<DossierStatusMapping> models) {
		List<DossierStatusMappingSoap> soapModels = new ArrayList<DossierStatusMappingSoap>(models.size());

		for (DossierStatusMapping model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DossierStatusMappingSoap[soapModels.size()]);
	}

	public DossierStatusMappingSoap() {
	}

	public long getPrimaryKey() {
		return _dossierStatusMappingId;
	}

	public void setPrimaryKey(long pk) {
		setDossierStatusMappingId(pk);
	}

	public long getDossierStatusMappingId() {
		return _dossierStatusMappingId;
	}

	public void setDossierStatusMappingId(long dossierStatusMappingId) {
		_dossierStatusMappingId = dossierStatusMappingId;
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

	public String getStatusCode() {
		return _statusCode;
	}

	public void setStatusCode(String statusCode) {
		_statusCode = statusCode;
	}

	public String getStatusCodeDVCQG() {
		return _statusCodeDVCQG;
	}

	public void setStatusCodeDVCQG(String statusCodeDVCQG) {
		_statusCodeDVCQG = statusCodeDVCQG;
	}

	public String getSubStatusCode() {
		return _subStatusCode;
	}

	public void setSubStatusCode(String subStatusCode) {
		_subStatusCode = subStatusCode;
	}

	private long _dossierStatusMappingId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _statusCode;
	private String _statusCodeDVCQG;
	private String _subStatusCode;
}