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
public class ReportLandTaxSoap implements Serializable {
	public static ReportLandTaxSoap toSoapModel(ReportLandTax model) {
		ReportLandTaxSoap soapModel = new ReportLandTaxSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setReportId(model.getReportId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setDossierNo(model.getDossierNo());
		soapModel.setBodyRequest(model.getBodyRequest());
		soapModel.setResponse(model.getResponse());

		return soapModel;
	}

	public static ReportLandTaxSoap[] toSoapModels(ReportLandTax[] models) {
		ReportLandTaxSoap[] soapModels = new ReportLandTaxSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ReportLandTaxSoap[][] toSoapModels(ReportLandTax[][] models) {
		ReportLandTaxSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ReportLandTaxSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ReportLandTaxSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ReportLandTaxSoap[] toSoapModels(List<ReportLandTax> models) {
		List<ReportLandTaxSoap> soapModels = new ArrayList<ReportLandTaxSoap>(models.size());

		for (ReportLandTax model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ReportLandTaxSoap[soapModels.size()]);
	}

	public ReportLandTaxSoap() {
	}

	public long getPrimaryKey() {
		return _reportId;
	}

	public void setPrimaryKey(long pk) {
		setReportId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getReportId() {
		return _reportId;
	}

	public void setReportId(long reportId) {
		_reportId = reportId;
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

	public String getDossierNo() {
		return _dossierNo;
	}

	public void setDossierNo(String dossierNo) {
		_dossierNo = dossierNo;
	}

	public String getBodyRequest() {
		return _bodyRequest;
	}

	public void setBodyRequest(String bodyRequest) {
		_bodyRequest = bodyRequest;
	}

	public String getResponse() {
		return _response;
	}

	public void setResponse(String response) {
		_response = response;
	}

	private String _uuid;
	private long _reportId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _dossierNo;
	private String _bodyRequest;
	private String _response;
}