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
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author huymq
 * @generated
 */
@ProviderType
public class CsdlDcUserSoap implements Serializable {
	public static CsdlDcUserSoap toSoapModel(CsdlDcUser model) {
		CsdlDcUserSoap soapModel = new CsdlDcUserSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setIdDcUser(model.getIdDcUser());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setGovAgencyCode(model.getGovAgencyCode());
		soapModel.setGovAgencyCodeDvcqg(model.getGovAgencyCodeDvcqg());
		soapModel.setKeyName(model.getKeyName());
		soapModel.setKeyPass(model.getKeyPass());
		soapModel.setUserName(model.getUserName());
		soapModel.setEmployeeEmail(model.getEmployeeEmail());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static CsdlDcUserSoap[] toSoapModels(CsdlDcUser[] models) {
		CsdlDcUserSoap[] soapModels = new CsdlDcUserSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CsdlDcUserSoap[][] toSoapModels(CsdlDcUser[][] models) {
		CsdlDcUserSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CsdlDcUserSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CsdlDcUserSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CsdlDcUserSoap[] toSoapModels(List<CsdlDcUser> models) {
		List<CsdlDcUserSoap> soapModels = new ArrayList<CsdlDcUserSoap>(models.size());

		for (CsdlDcUser model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CsdlDcUserSoap[soapModels.size()]);
	}

	public CsdlDcUserSoap() {
	}

	public long getPrimaryKey() {
		return _idDcUser;
	}

	public void setPrimaryKey(long pk) {
		setIdDcUser(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getIdDcUser() {
		return _idDcUser;
	}

	public void setIdDcUser(long idDcUser) {
		_idDcUser = idDcUser;
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

	public String getGovAgencyCode() {
		return _govAgencyCode;
	}

	public void setGovAgencyCode(String govAgencyCode) {
		_govAgencyCode = govAgencyCode;
	}

	public String getGovAgencyCodeDvcqg() {
		return _govAgencyCodeDvcqg;
	}

	public void setGovAgencyCodeDvcqg(String govAgencyCodeDvcqg) {
		_govAgencyCodeDvcqg = govAgencyCodeDvcqg;
	}

	public String getKeyName() {
		return _keyName;
	}

	public void setKeyName(String keyName) {
		_keyName = keyName;
	}

	public String getKeyPass() {
		return _keyPass;
	}

	public void setKeyPass(String keyPass) {
		_keyPass = keyPass;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public String getEmployeeEmail() {
		return _employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		_employeeEmail = employeeEmail;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private String _uuid;
	private long _idDcUser;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _govAgencyCode;
	private String _govAgencyCodeDvcqg;
	private String _keyName;
	private String _keyPass;
	private String _userName;
	private String _employeeEmail;
	private int _status;
}