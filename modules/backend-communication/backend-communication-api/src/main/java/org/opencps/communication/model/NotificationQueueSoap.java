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

package org.opencps.communication.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author khoavd
 * @generated
 */
@ProviderType
public class NotificationQueueSoap implements Serializable {
	public static NotificationQueueSoap toSoapModel(NotificationQueue model) {
		NotificationQueueSoap soapModel = new NotificationQueueSoap();

		soapModel.setNotificationQueueId(model.getNotificationQueueId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setNotificationType(model.getNotificationType());
		soapModel.setClassName(model.getClassName());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setPayload(model.getPayload());
		soapModel.setFromUsername(model.getFromUsername());
		soapModel.setToUsername(model.getToUsername());
		soapModel.setToUserId(model.getToUserId());
		soapModel.setToEmail(model.getToEmail());
		soapModel.setToTelNo(model.getToTelNo());
		soapModel.setPublicationDate(model.getPublicationDate());
		soapModel.setExpireDate(model.getExpireDate());

		return soapModel;
	}

	public static NotificationQueueSoap[] toSoapModels(
		NotificationQueue[] models) {
		NotificationQueueSoap[] soapModels = new NotificationQueueSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static NotificationQueueSoap[][] toSoapModels(
		NotificationQueue[][] models) {
		NotificationQueueSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new NotificationQueueSoap[models.length][models[0].length];
		}
		else {
			soapModels = new NotificationQueueSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static NotificationQueueSoap[] toSoapModels(
		List<NotificationQueue> models) {
		List<NotificationQueueSoap> soapModels = new ArrayList<NotificationQueueSoap>(models.size());

		for (NotificationQueue model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new NotificationQueueSoap[soapModels.size()]);
	}

	public NotificationQueueSoap() {
	}

	public long getPrimaryKey() {
		return _notificationQueueId;
	}

	public void setPrimaryKey(long pk) {
		setNotificationQueueId(pk);
	}

	public long getNotificationQueueId() {
		return _notificationQueueId;
	}

	public void setNotificationQueueId(long notificationQueueId) {
		_notificationQueueId = notificationQueueId;
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

	public String getNotificationType() {
		return _notificationType;
	}

	public void setNotificationType(String notificationType) {
		_notificationType = notificationType;
	}

	public String getClassName() {
		return _className;
	}

	public void setClassName(String className) {
		_className = className;
	}

	public String getClassPK() {
		return _classPK;
	}

	public void setClassPK(String classPK) {
		_classPK = classPK;
	}

	public String getPayload() {
		return _payload;
	}

	public void setPayload(String payload) {
		_payload = payload;
	}

	public String getFromUsername() {
		return _fromUsername;
	}

	public void setFromUsername(String fromUsername) {
		_fromUsername = fromUsername;
	}

	public String getToUsername() {
		return _toUsername;
	}

	public void setToUsername(String toUsername) {
		_toUsername = toUsername;
	}

	public long getToUserId() {
		return _toUserId;
	}

	public void setToUserId(long toUserId) {
		_toUserId = toUserId;
	}

	public String getToEmail() {
		return _toEmail;
	}

	public void setToEmail(String toEmail) {
		_toEmail = toEmail;
	}

	public String getToTelNo() {
		return _toTelNo;
	}

	public void setToTelNo(String toTelNo) {
		_toTelNo = toTelNo;
	}

	public Date getPublicationDate() {
		return _publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		_publicationDate = publicationDate;
	}

	public Date getExpireDate() {
		return _expireDate;
	}

	public void setExpireDate(Date expireDate) {
		_expireDate = expireDate;
	}

	private long _notificationQueueId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _notificationType;
	private String _className;
	private String _classPK;
	private String _payload;
	private String _fromUsername;
	private String _toUsername;
	private long _toUserId;
	private String _toEmail;
	private String _toTelNo;
	private Date _publicationDate;
	private Date _expireDate;
}