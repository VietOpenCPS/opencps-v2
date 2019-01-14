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
public class PublishQueueSoap implements Serializable {
	public static PublishQueueSoap toSoapModel(PublishQueue model) {
		PublishQueueSoap soapModel = new PublishQueueSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setPublishQueueId(model.getPublishQueueId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setDossierId(model.getDossierId());
		soapModel.setServerNo(model.getServerNo());
		soapModel.setStatus(model.getStatus());
		soapModel.setRetry(model.getRetry());
		soapModel.setPublishType(model.getPublishType());
		soapModel.setPublishData(model.getPublishData());
		soapModel.setMessageText(model.getMessageText());
		soapModel.setAcknowlegement(model.getAcknowlegement());

		return soapModel;
	}

	public static PublishQueueSoap[] toSoapModels(PublishQueue[] models) {
		PublishQueueSoap[] soapModels = new PublishQueueSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PublishQueueSoap[][] toSoapModels(PublishQueue[][] models) {
		PublishQueueSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new PublishQueueSoap[models.length][models[0].length];
		}
		else {
			soapModels = new PublishQueueSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PublishQueueSoap[] toSoapModels(List<PublishQueue> models) {
		List<PublishQueueSoap> soapModels = new ArrayList<PublishQueueSoap>(models.size());

		for (PublishQueue model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PublishQueueSoap[soapModels.size()]);
	}

	public PublishQueueSoap() {
	}

	public long getPrimaryKey() {
		return _publishQueueId;
	}

	public void setPrimaryKey(long pk) {
		setPublishQueueId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getPublishQueueId() {
		return _publishQueueId;
	}

	public void setPublishQueueId(long publishQueueId) {
		_publishQueueId = publishQueueId;
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

	public String getServerNo() {
		return _serverNo;
	}

	public void setServerNo(String serverNo) {
		_serverNo = serverNo;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public int getRetry() {
		return _retry;
	}

	public void setRetry(int retry) {
		_retry = retry;
	}

	public int getPublishType() {
		return _publishType;
	}

	public void setPublishType(int publishType) {
		_publishType = publishType;
	}

	public String getPublishData() {
		return _publishData;
	}

	public void setPublishData(String publishData) {
		_publishData = publishData;
	}

	public String getMessageText() {
		return _messageText;
	}

	public void setMessageText(String messageText) {
		_messageText = messageText;
	}

	public String getAcknowlegement() {
		return _acknowlegement;
	}

	public void setAcknowlegement(String acknowlegement) {
		_acknowlegement = acknowlegement;
	}

	private String _uuid;
	private long _publishQueueId;
	private long _groupId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _dossierId;
	private String _serverNo;
	private int _status;
	private int _retry;
	private int _publishType;
	private String _publishData;
	private String _messageText;
	private String _acknowlegement;
}