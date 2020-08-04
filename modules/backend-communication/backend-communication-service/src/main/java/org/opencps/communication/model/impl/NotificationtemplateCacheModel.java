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

package org.opencps.communication.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import org.opencps.communication.model.Notificationtemplate;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Notificationtemplate in entity cache.
 *
 * @author khoavd
 * @see Notificationtemplate
 * @generated
 */
@ProviderType
public class NotificationtemplateCacheModel implements CacheModel<Notificationtemplate>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof NotificationtemplateCacheModel)) {
			return false;
		}

		NotificationtemplateCacheModel notificationtemplateCacheModel = (NotificationtemplateCacheModel)obj;

		if (notificationTemplateId == notificationtemplateCacheModel.notificationTemplateId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, notificationTemplateId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(43);

		sb.append("{notificationTemplateId=");
		sb.append(notificationTemplateId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", notificationType=");
		sb.append(notificationType);
		sb.append(", emailSubject=");
		sb.append(emailSubject);
		sb.append(", emailBody=");
		sb.append(emailBody);
		sb.append(", textMessage=");
		sb.append(textMessage);
		sb.append(", notifyMessage=");
		sb.append(notifyMessage);
		sb.append(", sendSMS=");
		sb.append(sendSMS);
		sb.append(", sendEmail=");
		sb.append(sendEmail);
		sb.append(", sendNotification=");
		sb.append(sendNotification);
		sb.append(", expireDuration=");
		sb.append(expireDuration);
		sb.append(", userUrlPattern=");
		sb.append(userUrlPattern);
		sb.append(", guestUrlPattern=");
		sb.append(guestUrlPattern);
		sb.append(", interval=");
		sb.append(interval);
		sb.append(", grouping=");
		sb.append(grouping);
		sb.append(", priority=");
		sb.append(priority);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Notificationtemplate toEntityModel() {
		NotificationtemplateImpl notificationtemplateImpl = new NotificationtemplateImpl();

		notificationtemplateImpl.setNotificationTemplateId(notificationTemplateId);
		notificationtemplateImpl.setGroupId(groupId);
		notificationtemplateImpl.setCompanyId(companyId);
		notificationtemplateImpl.setUserId(userId);

		if (userName == null) {
			notificationtemplateImpl.setUserName("");
		}
		else {
			notificationtemplateImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			notificationtemplateImpl.setCreateDate(null);
		}
		else {
			notificationtemplateImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			notificationtemplateImpl.setModifiedDate(null);
		}
		else {
			notificationtemplateImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (notificationType == null) {
			notificationtemplateImpl.setNotificationType("");
		}
		else {
			notificationtemplateImpl.setNotificationType(notificationType);
		}

		if (emailSubject == null) {
			notificationtemplateImpl.setEmailSubject("");
		}
		else {
			notificationtemplateImpl.setEmailSubject(emailSubject);
		}

		if (emailBody == null) {
			notificationtemplateImpl.setEmailBody("");
		}
		else {
			notificationtemplateImpl.setEmailBody(emailBody);
		}

		if (textMessage == null) {
			notificationtemplateImpl.setTextMessage("");
		}
		else {
			notificationtemplateImpl.setTextMessage(textMessage);
		}

		if (notifyMessage == null) {
			notificationtemplateImpl.setNotifyMessage("");
		}
		else {
			notificationtemplateImpl.setNotifyMessage(notifyMessage);
		}

		notificationtemplateImpl.setSendSMS(sendSMS);
		notificationtemplateImpl.setSendEmail(sendEmail);
		notificationtemplateImpl.setSendNotification(sendNotification);
		notificationtemplateImpl.setExpireDuration(expireDuration);

		if (userUrlPattern == null) {
			notificationtemplateImpl.setUserUrlPattern("");
		}
		else {
			notificationtemplateImpl.setUserUrlPattern(userUrlPattern);
		}

		if (guestUrlPattern == null) {
			notificationtemplateImpl.setGuestUrlPattern("");
		}
		else {
			notificationtemplateImpl.setGuestUrlPattern(guestUrlPattern);
		}

		if (interval == null) {
			notificationtemplateImpl.setInterval("");
		}
		else {
			notificationtemplateImpl.setInterval(interval);
		}

		notificationtemplateImpl.setGrouping(grouping);
		notificationtemplateImpl.setPriority(priority);

		notificationtemplateImpl.resetOriginalValues();

		return notificationtemplateImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		notificationTemplateId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		notificationType = objectInput.readUTF();
		emailSubject = objectInput.readUTF();
		emailBody = objectInput.readUTF();
		textMessage = objectInput.readUTF();
		notifyMessage = objectInput.readUTF();

		sendSMS = objectInput.readBoolean();

		sendEmail = objectInput.readBoolean();

		sendNotification = objectInput.readBoolean();

		expireDuration = objectInput.readInt();
		userUrlPattern = objectInput.readUTF();
		guestUrlPattern = objectInput.readUTF();
		interval = objectInput.readUTF();

		grouping = objectInput.readBoolean();

		priority = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(notificationTemplateId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (notificationType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(notificationType);
		}

		if (emailSubject == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(emailSubject);
		}

		if (emailBody == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(emailBody);
		}

		if (textMessage == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(textMessage);
		}

		if (notifyMessage == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(notifyMessage);
		}

		objectOutput.writeBoolean(sendSMS);

		objectOutput.writeBoolean(sendEmail);

		objectOutput.writeBoolean(sendNotification);

		objectOutput.writeInt(expireDuration);

		if (userUrlPattern == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userUrlPattern);
		}

		if (guestUrlPattern == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(guestUrlPattern);
		}

		if (interval == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(interval);
		}

		objectOutput.writeBoolean(grouping);

		objectOutput.writeInt(priority);
	}

	public long notificationTemplateId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String notificationType;
	public String emailSubject;
	public String emailBody;
	public String textMessage;
	public String notifyMessage;
	public boolean sendSMS;
	public boolean sendEmail;
	public boolean sendNotification;
	public int expireDuration;
	public String userUrlPattern;
	public String guestUrlPattern;
	public String interval;
	public boolean grouping;
	public int priority;
}