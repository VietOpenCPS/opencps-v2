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

package org.opencps.communication.constants;

import java.util.Date;

public class NotificationTemplateTerm {

	public static final String NOTIFICATIONTEMPLATE_ID = "notificationTemplateId";

	public static final String COMPANY_ID = "companyId";

	public static final String GROUP_ID = "groupId";

	public static final String USER_ID = "userId";

	public static final String USER_NAME = "userName";

	public static final String CREATE_DATE = "createDate";

	public static final String MODIFIED_DATE = "modifiedDate";

	public static final String NOTIFICATTION_TYPE = "notificationType";

	public static final String NOTIFICATION_EMAIL_SUBJECT = "emailSubject";

	public static final String NOTIFICATION_EMAIL_BODY = "emailBody";

	public static final String NOTIFICATION_TEXT_MESSAGE = "textMessage";

	public static final String NOTIFICATION_TEXT_SMS = "textSMS";
	
	public static final String NOTIFICATIONTEMPLATE_ID_SORTABLE = "notificationTemplateId_sortable";

	public static final String COMPANY_ID_SORTABLE = "companyId_sortable";

	public static final String GROUP_ID_SORTABLE = "groupId_sortable";

	public static final String USER_ID_SORTABLE = "userId_sortable";

	public static final String USER_NAME_SORTABLE = "userName_sortable";

	public static final String CREATE_DATE_SORTABLE = "createDate_sortable";

	public static final String MODIFIED_DATE_SORTABLE = "modifiedDate_sortable";

	public static final String NOTIFICATTION_TYPE_SORTABLE = "notificationType_sortable";

	public static final String NOTIFICATION_EMAIL_SUBJECT_SORTABLE = "emailSubject_sortable";

	public static final String NOTIFICATION_EMAIL_BODY_SORTABLE = "emailBody_sortable";

	public static final String NOTIFICATION_TEXT_MESSAGE_SORTABLE = "textMessage_sortable";
	
	public static final String NOTIFICATION_TEXT_SMS_SORTABLE = "textSMS_sortable";

	public NotificationTemplateTerm() {

	}

	private long notificationTemplateId;
	private long companyId;
	private long groupId;
	private long userId;
	private String userName;
	private Date createDate;
	private Date modifiedDate;

	private int notificationType;
	private String emailSubject;
	private String emailBody;
	private String textMessage;
	private String textSMS;

	public long getNotificationTemplateId() {
		return notificationTemplateId;
	}
	public void setNotificationTemplateId(long notificationTemplateId) {
		this.notificationTemplateId = notificationTemplateId;
	}
	public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	public long getGroupId() {
		return groupId;
	}
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public int getNotificationType() {
		return notificationType;
	}
	public void setNotificationType(int notificationType) {
		this.notificationType = notificationType;
	}
	public String getEmailSubject() {
		return emailSubject;
	}
	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}
	public String getEmailBody() {
		return emailBody;
	}
	public void setEmailBody(String emailBody) {
		this.emailBody = emailBody;
	}
	public String getTextMessage() {
		return textMessage;
	}
	public void setTextMessage(String textMessage) {
		this.textMessage = textMessage;
	}
	public String getTextSMS() {
		return textSMS;
	}
	public void setTextSMS(String textSMS) {
		this.textSMS = textSMS;
	}
}