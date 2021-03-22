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
public class EFormSoap implements Serializable {
	public static EFormSoap toSoapModel(EForm model) {
		EFormSoap soapModel = new EFormSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setEFormId(model.getEFormId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setEFormNo(model.getEFormNo());
		soapModel.setServiceCode(model.getServiceCode());
		soapModel.setFileTemplateNo(model.getFileTemplateNo());
		soapModel.setEFormName(model.getEFormName());
		soapModel.setFormScriptFileId(model.getFormScriptFileId());
		soapModel.setFormReportFileId(model.getFormReportFileId());
		soapModel.setEFormData(model.getEFormData());
		soapModel.setEmail(model.getEmail());
		soapModel.setSecret(model.getSecret());
		soapModel.setGovAgencyCode(model.getGovAgencyCode());

		return soapModel;
	}

	public static EFormSoap[] toSoapModels(EForm[] models) {
		EFormSoap[] soapModels = new EFormSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static EFormSoap[][] toSoapModels(EForm[][] models) {
		EFormSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new EFormSoap[models.length][models[0].length];
		}
		else {
			soapModels = new EFormSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static EFormSoap[] toSoapModels(List<EForm> models) {
		List<EFormSoap> soapModels = new ArrayList<EFormSoap>(models.size());

		for (EForm model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new EFormSoap[soapModels.size()]);
	}

	public EFormSoap() {
	}

	public long getPrimaryKey() {
		return _eFormId;
	}

	public void setPrimaryKey(long pk) {
		setEFormId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getEFormId() {
		return _eFormId;
	}

	public void setEFormId(long eFormId) {
		_eFormId = eFormId;
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

	public String getEFormNo() {
		return _eFormNo;
	}

	public void setEFormNo(String eFormNo) {
		_eFormNo = eFormNo;
	}

	public String getServiceCode() {
		return _serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		_serviceCode = serviceCode;
	}

	public String getFileTemplateNo() {
		return _fileTemplateNo;
	}

	public void setFileTemplateNo(String fileTemplateNo) {
		_fileTemplateNo = fileTemplateNo;
	}

	public String getEFormName() {
		return _eFormName;
	}

	public void setEFormName(String eFormName) {
		_eFormName = eFormName;
	}

	public long getFormScriptFileId() {
		return _formScriptFileId;
	}

	public void setFormScriptFileId(long formScriptFileId) {
		_formScriptFileId = formScriptFileId;
	}

	public long getFormReportFileId() {
		return _formReportFileId;
	}

	public void setFormReportFileId(long formReportFileId) {
		_formReportFileId = formReportFileId;
	}

	public String getEFormData() {
		return _eFormData;
	}

	public void setEFormData(String eFormData) {
		_eFormData = eFormData;
	}

	public String getEmail() {
		return _email;
	}

	public void setEmail(String email) {
		_email = email;
	}

	public String getSecret() {
		return _secret;
	}

	public void setSecret(String secret) {
		_secret = secret;
	}

	public String getGovAgencyCode() {
		return _govAgencyCode;
	}

	public void setGovAgencyCode(String govAgencyCode) {
		_govAgencyCode = govAgencyCode;
	}

	private String _uuid;
	private long _eFormId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _eFormNo;
	private String _serviceCode;
	private String _fileTemplateNo;
	private String _eFormName;
	private long _formScriptFileId;
	private long _formReportFileId;
	private String _eFormData;
	private String _email;
	private String _secret;
	private String _govAgencyCode;
}