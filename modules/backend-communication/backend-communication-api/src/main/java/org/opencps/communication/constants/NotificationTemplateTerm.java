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
	
	public static final String SHARING = "SHARING";
	public static final String ACTIVITY_01 = "ACTIVITY_01";
	public static final String ACTIVITY_02 = "ACTIVITY_02";
	public static final String ACTIVITY_03 = "ACTIVITY_03";
	public static final String ACTIVITY_04 = "ACTIVITY_04";
	public static final String ACTIVITY_05 = "ACTIVITY_05";
	public static final String FILEATTACH = "FILEATTACH";
	public static final String ALBUMFILE = "ALBUMFILE";
	public static final String COMMENT = "COMMENT";
	public static final String VOTING_01 = "VOTING-01";
	public static final String VOTING_02 = "VOTING-02";
	public static final String APPROVAL_01 = "APPROVAL-01";
	public static final String APPROVAL_02 = "APPROVAL-02";
	public static final String DOCUMENT_01 = "DOCUMENT-01";
	public static final String DOCUMENT_02 = "DOCUMENT-02";
	public static final String DOCUMENT_03 = "DOCUMENT-03";
	public static final String ARCHIVE_01 = "ARCHIVE-01";
	public static final String ARCHIVE_02 = "ARCHIVE-02";
	public static final String CHECKLIST_01 = "CHECKLIST-01";
	public static final String CHECKLIST_02 = "CHECKLIST-02";

	public static final String MINUTELY = "minutely";
	public static final String FIVE_MINUTES = "5-mins";
	public static final String FIFTEEN_MINUTES = "15-mins";
	public static final String THIRTY_MINUTES = "30-mins";
	public static final String HOURLY = "hourly";
	public static final String DAILY = "daily";

	public static final String NOTIFICATIONTEMPLATE_ID = "notificationTemplateId";

	public static final String COMPANY_ID = "companyId";

	public static final String GROUP_ID = "groupId";

	public static final String USER_ID = "userId";

	public static final String USER_NAME = "userName";

	public static final String CREATE_DATE = "createDate";

	public static final String MODIFIED_DATE = "modifiedDate";

	public static final String SEND_EMAIL = "sendEmail";

	public static final String NOTIFICATTION_TYPE = "notificationType";

	public static final String NOTIFICATION_EMAIL_SUBJECT = "emailSubject";

	public static final String NOTIFICATION_EMAIL_BODY = "emailBody";

	public static final String NOTIFICATION_TEXT_MESSAGE = "textMessage";

	public static final String NOTIFICATION_SEND_SMS = "sendSMS";

	public static final String EXPIRE_DURATION = "expireDuration";
	
	public static final String USER_URL_PARTTERN = "userUrlPattern";
	
	public static final String GUEST_URL_PARTTERN = "guestUrlPattern";
	
	public static final String INTERVAL = "interval";
	
	public static final String GROUPING = "grouping";

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

	public static final String NOTIFICATION_SEND_SMS_SORTABLE = "sendSMS_sortable";

	public NotificationTemplateTerm() {

	}

	private long notificationTemplateId;
	private long companyId;
	private long groupId;
	private long userId;
	private String userName;
	private Date createDate;
	private Date modifiedDate;

	private String notificationType;
	private String emailSubject;
	private String emailBody;
	private String textMessage;
	private boolean sendSMS;
	private boolean sendEmail;

	private Date expireDuration;
	private String userUrlPattern;
	private String guestUrlPattern;
	private String interval;
	private boolean grouping;

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

	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(String notificationType) {
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

	public boolean isSendSMS() {
		return sendSMS;
	}

	public void setSendSMS(boolean sendSMS) {
		this.sendSMS = sendSMS;
	}

	public boolean isSendEmail() {
		return sendEmail;
	}

	public void setSendEmail(boolean sendEmail) {
		this.sendEmail = sendEmail;
	}

	public Date getExpireDuration() {
		return expireDuration;
	}

	public void setExpireDuration(Date expireDuration) {
		this.expireDuration = expireDuration;
	}

	public String getUserUrlPattern() {
		return userUrlPattern;
	}

	public void setUserUrlPattern(String userUrlPattern) {
		this.userUrlPattern = userUrlPattern;
	}

	public String getGuestUrlPattern() {
		return guestUrlPattern;
	}

	public void setGuestUrlPattern(String guestUrlPattern) {
		this.guestUrlPattern = guestUrlPattern;
	}

	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

	public boolean isGrouping() {
		return grouping;
	}

	public void setGrouping(boolean grouping) {
		this.grouping = grouping;
	}

}