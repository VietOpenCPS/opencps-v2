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
public class RegistrationLogSoap implements Serializable {
	public static RegistrationLogSoap toSoapModel(RegistrationLog model) {
		RegistrationLogSoap soapModel = new RegistrationLogSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setRegistrationLogId(model.getRegistrationLogId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setRegistrationId(model.getRegistrationId());
		soapModel.setAuthor(model.getAuthor());
		soapModel.setContent(model.getContent());
		soapModel.setPayload(model.getPayload());

		return soapModel;
	}

	public static RegistrationLogSoap[] toSoapModels(RegistrationLog[] models) {
		RegistrationLogSoap[] soapModels = new RegistrationLogSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static RegistrationLogSoap[][] toSoapModels(
		RegistrationLog[][] models) {
		RegistrationLogSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new RegistrationLogSoap[models.length][models[0].length];
		}
		else {
			soapModels = new RegistrationLogSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static RegistrationLogSoap[] toSoapModels(
		List<RegistrationLog> models) {
		List<RegistrationLogSoap> soapModels = new ArrayList<RegistrationLogSoap>(models.size());

		for (RegistrationLog model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new RegistrationLogSoap[soapModels.size()]);
	}

	public RegistrationLogSoap() {
	}

	public long getPrimaryKey() {
		return _registrationLogId;
	}

	public void setPrimaryKey(long pk) {
		setRegistrationLogId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getRegistrationLogId() {
		return _registrationLogId;
	}

	public void setRegistrationLogId(long registrationLogId) {
		_registrationLogId = registrationLogId;
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

	public long getRegistrationId() {
		return _registrationId;
	}

	public void setRegistrationId(long registrationId) {
		_registrationId = registrationId;
	}

	public String getAuthor() {
		return _author;
	}

	public void setAuthor(String author) {
		_author = author;
	}

	public String getContent() {
		return _content;
	}

	public void setContent(String content) {
		_content = content;
	}

	public String getPayload() {
		return _payload;
	}

	public void setPayload(String payload) {
		_payload = payload;
	}

	private String _uuid;
	private long _registrationLogId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _registrationId;
	private String _author;
	private String _content;
	private String _payload;
}