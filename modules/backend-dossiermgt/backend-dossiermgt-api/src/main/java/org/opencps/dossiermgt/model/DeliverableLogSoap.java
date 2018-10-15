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
public class DeliverableLogSoap implements Serializable {
	public static DeliverableLogSoap toSoapModel(DeliverableLog model) {
		DeliverableLogSoap soapModel = new DeliverableLogSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setDeliverableLogId(model.getDeliverableLogId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setDeliverableId(model.getDeliverableId());
		soapModel.setDossierUid(model.getDossierUid());
		soapModel.setAuthor(model.getAuthor());
		soapModel.setContent(model.getContent());
		soapModel.setDeliverableAction(model.getDeliverableAction());
		soapModel.setActionDate(model.getActionDate());
		soapModel.setPayload(model.getPayload());

		return soapModel;
	}

	public static DeliverableLogSoap[] toSoapModels(DeliverableLog[] models) {
		DeliverableLogSoap[] soapModels = new DeliverableLogSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DeliverableLogSoap[][] toSoapModels(DeliverableLog[][] models) {
		DeliverableLogSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DeliverableLogSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DeliverableLogSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DeliverableLogSoap[] toSoapModels(List<DeliverableLog> models) {
		List<DeliverableLogSoap> soapModels = new ArrayList<DeliverableLogSoap>(models.size());

		for (DeliverableLog model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DeliverableLogSoap[soapModels.size()]);
	}

	public DeliverableLogSoap() {
	}

	public long getPrimaryKey() {
		return _deliverableLogId;
	}

	public void setPrimaryKey(long pk) {
		setDeliverableLogId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getDeliverableLogId() {
		return _deliverableLogId;
	}

	public void setDeliverableLogId(long deliverableLogId) {
		_deliverableLogId = deliverableLogId;
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

	public String getDeliverableId() {
		return _deliverableId;
	}

	public void setDeliverableId(String deliverableId) {
		_deliverableId = deliverableId;
	}

	public String getDossierUid() {
		return _dossierUid;
	}

	public void setDossierUid(String dossierUid) {
		_dossierUid = dossierUid;
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

	public int getDeliverableAction() {
		return _deliverableAction;
	}

	public void setDeliverableAction(int deliverableAction) {
		_deliverableAction = deliverableAction;
	}

	public Date getActionDate() {
		return _actionDate;
	}

	public void setActionDate(Date actionDate) {
		_actionDate = actionDate;
	}

	public String getPayload() {
		return _payload;
	}

	public void setPayload(String payload) {
		_payload = payload;
	}

	private String _uuid;
	private long _deliverableLogId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _deliverableId;
	private String _dossierUid;
	private String _author;
	private String _content;
	private int _deliverableAction;
	private Date _actionDate;
	private String _payload;
}