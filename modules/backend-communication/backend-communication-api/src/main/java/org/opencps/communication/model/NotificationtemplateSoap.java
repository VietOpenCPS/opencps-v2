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
public class NotificationtemplateSoap implements Serializable {
	public static NotificationtemplateSoap toSoapModel(
		Notificationtemplate model) {
		NotificationtemplateSoap soapModel = new NotificationtemplateSoap();

		soapModel.setNotificationTemplateId(model.getNotificationTemplateId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setNotificationType(model.getNotificationType());
		soapModel.setEmailSubject(model.getEmailSubject());
		soapModel.setEmailBody(model.getEmailBody());
		soapModel.setTextMessage(model.getTextMessage());
		soapModel.setNotifyMessage(model.getNotifyMessage());
		soapModel.setSendSMS(model.isSendSMS());
		soapModel.setSendEmail(model.isSendEmail());
		soapModel.setSendNotification(model.isSendNotification());
		soapModel.setExpireDuration(model.getExpireDuration());
		soapModel.setUserUrlPattern(model.getUserUrlPattern());
		soapModel.setGuestUrlPattern(model.getGuestUrlPattern());
		soapModel.setInterval(model.getInterval());
		soapModel.setGrouping(model.isGrouping());
		soapModel.setPriority(model.getPriority());

		return soapModel;
	}

	public static NotificationtemplateSoap[] toSoapModels(
		Notificationtemplate[] models) {
		NotificationtemplateSoap[] soapModels = new NotificationtemplateSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static NotificationtemplateSoap[][] toSoapModels(
		Notificationtemplate[][] models) {
		NotificationtemplateSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new NotificationtemplateSoap[models.length][models[0].length];
		}
		else {
			soapModels = new NotificationtemplateSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static NotificationtemplateSoap[] toSoapModels(
		List<Notificationtemplate> models) {
		List<NotificationtemplateSoap> soapModels = new ArrayList<NotificationtemplateSoap>(models.size());

		for (Notificationtemplate model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new NotificationtemplateSoap[soapModels.size()]);
	}

	public NotificationtemplateSoap() {
	}

	public long getPrimaryKey() {
		return _notificationTemplateId;
	}

	public void setPrimaryKey(long pk) {
		setNotificationTemplateId(pk);
	}

	public long getNotificationTemplateId() {
		return _notificationTemplateId;
	}

	public void setNotificationTemplateId(long notificationTemplateId) {
		_notificationTemplateId = notificationTemplateId;
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

	public String getEmailSubject() {
		return _emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		_emailSubject = emailSubject;
	}

	public String getEmailBody() {
		return _emailBody;
	}

	public void setEmailBody(String emailBody) {
		_emailBody = emailBody;
	}

	public String getTextMessage() {
		return _textMessage;
	}

	public void setTextMessage(String textMessage) {
		_textMessage = textMessage;
	}

	public String getNotifyMessage() {
		return _notifyMessage;
	}

	public void setNotifyMessage(String notifyMessage) {
		_notifyMessage = notifyMessage;
	}

	public boolean getSendSMS() {
		return _sendSMS;
	}

	public boolean isSendSMS() {
		return _sendSMS;
	}

	public void setSendSMS(boolean sendSMS) {
		_sendSMS = sendSMS;
	}

	public boolean getSendEmail() {
		return _sendEmail;
	}

	public boolean isSendEmail() {
		return _sendEmail;
	}

	public void setSendEmail(boolean sendEmail) {
		_sendEmail = sendEmail;
	}

	public boolean getSendNotification() {
		return _sendNotification;
	}

	public boolean isSendNotification() {
		return _sendNotification;
	}

	public void setSendNotification(boolean sendNotification) {
		_sendNotification = sendNotification;
	}

	public int getExpireDuration() {
		return _expireDuration;
	}

	public void setExpireDuration(int expireDuration) {
		_expireDuration = expireDuration;
	}

	public String getUserUrlPattern() {
		return _userUrlPattern;
	}

	public void setUserUrlPattern(String userUrlPattern) {
		_userUrlPattern = userUrlPattern;
	}

	public String getGuestUrlPattern() {
		return _guestUrlPattern;
	}

	public void setGuestUrlPattern(String guestUrlPattern) {
		_guestUrlPattern = guestUrlPattern;
	}

	public String getInterval() {
		return _interval;
	}

	public void setInterval(String interval) {
		_interval = interval;
	}

	public boolean getGrouping() {
		return _grouping;
	}

	public boolean isGrouping() {
		return _grouping;
	}

	public void setGrouping(boolean grouping) {
		_grouping = grouping;
	}

	public int getPriority() {
		return _priority;
	}

	public void setPriority(int priority) {
		_priority = priority;
	}

	private long _notificationTemplateId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _notificationType;
	private String _emailSubject;
	private String _emailBody;
	private String _textMessage;
	private String _notifyMessage;
	private boolean _sendSMS;
	private boolean _sendEmail;
	private boolean _sendNotification;
	private int _expireDuration;
	private String _userUrlPattern;
	private String _guestUrlPattern;
	private String _interval;
	private boolean _grouping;
	private int _priority;
}