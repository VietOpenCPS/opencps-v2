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
public class DossierLogSoap implements Serializable {
	public static DossierLogSoap toSoapModel(DossierLog model) {
		DossierLogSoap soapModel = new DossierLogSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setDossierLogId(model.getDossierLogId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setDossierId(model.getDossierId());
		soapModel.setAuthor(model.getAuthor());
		soapModel.setContent(model.getContent());
		soapModel.setNotificationType(model.getNotificationType());
		soapModel.setPayload(model.getPayload());

		return soapModel;
	}

	public static DossierLogSoap[] toSoapModels(DossierLog[] models) {
		DossierLogSoap[] soapModels = new DossierLogSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DossierLogSoap[][] toSoapModels(DossierLog[][] models) {
		DossierLogSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DossierLogSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DossierLogSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DossierLogSoap[] toSoapModels(List<DossierLog> models) {
		List<DossierLogSoap> soapModels = new ArrayList<DossierLogSoap>(models.size());

		for (DossierLog model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DossierLogSoap[soapModels.size()]);
	}

	public DossierLogSoap() {
	}

	public long getPrimaryKey() {
		return _dossierLogId;
	}

	public void setPrimaryKey(long pk) {
		setDossierLogId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getDossierLogId() {
		return _dossierLogId;
	}

	public void setDossierLogId(long dossierLogId) {
		_dossierLogId = dossierLogId;
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

	public long getDossierId() {
		return _dossierId;
	}

	public void setDossierId(long dossierId) {
		_dossierId = dossierId;
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

	public String getNotificationType() {
		return _notificationType;
	}

	public void setNotificationType(String notificationType) {
		_notificationType = notificationType;
	}

	public String getPayload() {
		return _payload;
	}

	public void setPayload(String payload) {
		_payload = payload;
	}

	private String _uuid;
	private long _dossierLogId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _dossierId;
	private String _author;
	private String _content;
	private String _notificationType;
	private String _payload;
}