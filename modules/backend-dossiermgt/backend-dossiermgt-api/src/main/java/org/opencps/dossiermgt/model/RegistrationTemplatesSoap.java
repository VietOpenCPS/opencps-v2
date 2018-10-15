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
public class RegistrationTemplatesSoap implements Serializable {
	public static RegistrationTemplatesSoap toSoapModel(
		RegistrationTemplates model) {
		RegistrationTemplatesSoap soapModel = new RegistrationTemplatesSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setRegistrationTemplateId(model.getRegistrationTemplateId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setGovAgencyCode(model.getGovAgencyCode());
		soapModel.setGovAgencyName(model.getGovAgencyName());
		soapModel.setFormNo(model.getFormNo());
		soapModel.setFormName(model.getFormName());
		soapModel.setMultiple(model.isMultiple());
		soapModel.setFormScript(model.getFormScript());
		soapModel.setFormReport(model.getFormReport());
		soapModel.setSampleData(model.getSampleData());

		return soapModel;
	}

	public static RegistrationTemplatesSoap[] toSoapModels(
		RegistrationTemplates[] models) {
		RegistrationTemplatesSoap[] soapModels = new RegistrationTemplatesSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static RegistrationTemplatesSoap[][] toSoapModels(
		RegistrationTemplates[][] models) {
		RegistrationTemplatesSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new RegistrationTemplatesSoap[models.length][models[0].length];
		}
		else {
			soapModels = new RegistrationTemplatesSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static RegistrationTemplatesSoap[] toSoapModels(
		List<RegistrationTemplates> models) {
		List<RegistrationTemplatesSoap> soapModels = new ArrayList<RegistrationTemplatesSoap>(models.size());

		for (RegistrationTemplates model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new RegistrationTemplatesSoap[soapModels.size()]);
	}

	public RegistrationTemplatesSoap() {
	}

	public long getPrimaryKey() {
		return _registrationTemplateId;
	}

	public void setPrimaryKey(long pk) {
		setRegistrationTemplateId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getRegistrationTemplateId() {
		return _registrationTemplateId;
	}

	public void setRegistrationTemplateId(long registrationTemplateId) {
		_registrationTemplateId = registrationTemplateId;
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

	public String getGovAgencyCode() {
		return _govAgencyCode;
	}

	public void setGovAgencyCode(String govAgencyCode) {
		_govAgencyCode = govAgencyCode;
	}

	public String getGovAgencyName() {
		return _govAgencyName;
	}

	public void setGovAgencyName(String govAgencyName) {
		_govAgencyName = govAgencyName;
	}

	public String getFormNo() {
		return _formNo;
	}

	public void setFormNo(String formNo) {
		_formNo = formNo;
	}

	public String getFormName() {
		return _formName;
	}

	public void setFormName(String formName) {
		_formName = formName;
	}

	public boolean getMultiple() {
		return _multiple;
	}

	public boolean isMultiple() {
		return _multiple;
	}

	public void setMultiple(boolean multiple) {
		_multiple = multiple;
	}

	public String getFormScript() {
		return _formScript;
	}

	public void setFormScript(String formScript) {
		_formScript = formScript;
	}

	public String getFormReport() {
		return _formReport;
	}

	public void setFormReport(String formReport) {
		_formReport = formReport;
	}

	public String getSampleData() {
		return _sampleData;
	}

	public void setSampleData(String sampleData) {
		_sampleData = sampleData;
	}

	private String _uuid;
	private long _registrationTemplateId;
	private long _groupId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _govAgencyCode;
	private String _govAgencyName;
	private String _formNo;
	private String _formName;
	private boolean _multiple;
	private String _formScript;
	private String _formReport;
	private String _sampleData;
}