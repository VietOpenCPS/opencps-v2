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

import org.opencps.communication.model.NotificationQueue;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing NotificationQueue in entity cache.
 *
 * @author khoavd
 * @see NotificationQueue
 * @generated
 */
@ProviderType
public class NotificationQueueCacheModel implements CacheModel<NotificationQueue>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof NotificationQueueCacheModel)) {
			return false;
		}

		NotificationQueueCacheModel notificationQueueCacheModel = (NotificationQueueCacheModel)obj;

		if (notificationQueueId == notificationQueueCacheModel.notificationQueueId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, notificationQueueId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(39);

		sb.append("{notificationQueueId=");
		sb.append(notificationQueueId);
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
		sb.append(", className=");
		sb.append(className);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", payload=");
		sb.append(payload);
		sb.append(", fromUsername=");
		sb.append(fromUsername);
		sb.append(", toUsername=");
		sb.append(toUsername);
		sb.append(", toUserId=");
		sb.append(toUserId);
		sb.append(", toEmail=");
		sb.append(toEmail);
		sb.append(", toTelNo=");
		sb.append(toTelNo);
		sb.append(", publicationDate=");
		sb.append(publicationDate);
		sb.append(", expireDate=");
		sb.append(expireDate);
		sb.append(", priority=");
		sb.append(priority);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public NotificationQueue toEntityModel() {
		NotificationQueueImpl notificationQueueImpl = new NotificationQueueImpl();

		notificationQueueImpl.setNotificationQueueId(notificationQueueId);
		notificationQueueImpl.setGroupId(groupId);
		notificationQueueImpl.setCompanyId(companyId);
		notificationQueueImpl.setUserId(userId);

		if (userName == null) {
			notificationQueueImpl.setUserName("");
		}
		else {
			notificationQueueImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			notificationQueueImpl.setCreateDate(null);
		}
		else {
			notificationQueueImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			notificationQueueImpl.setModifiedDate(null);
		}
		else {
			notificationQueueImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (notificationType == null) {
			notificationQueueImpl.setNotificationType("");
		}
		else {
			notificationQueueImpl.setNotificationType(notificationType);
		}

		if (className == null) {
			notificationQueueImpl.setClassName("");
		}
		else {
			notificationQueueImpl.setClassName(className);
		}

		if (classPK == null) {
			notificationQueueImpl.setClassPK("");
		}
		else {
			notificationQueueImpl.setClassPK(classPK);
		}

		if (payload == null) {
			notificationQueueImpl.setPayload("");
		}
		else {
			notificationQueueImpl.setPayload(payload);
		}

		if (fromUsername == null) {
			notificationQueueImpl.setFromUsername("");
		}
		else {
			notificationQueueImpl.setFromUsername(fromUsername);
		}

		if (toUsername == null) {
			notificationQueueImpl.setToUsername("");
		}
		else {
			notificationQueueImpl.setToUsername(toUsername);
		}

		notificationQueueImpl.setToUserId(toUserId);

		if (toEmail == null) {
			notificationQueueImpl.setToEmail("");
		}
		else {
			notificationQueueImpl.setToEmail(toEmail);
		}

		if (toTelNo == null) {
			notificationQueueImpl.setToTelNo("");
		}
		else {
			notificationQueueImpl.setToTelNo(toTelNo);
		}

		if (publicationDate == Long.MIN_VALUE) {
			notificationQueueImpl.setPublicationDate(null);
		}
		else {
			notificationQueueImpl.setPublicationDate(new Date(publicationDate));
		}

		if (expireDate == Long.MIN_VALUE) {
			notificationQueueImpl.setExpireDate(null);
		}
		else {
			notificationQueueImpl.setExpireDate(new Date(expireDate));
		}

		notificationQueueImpl.setPriority(priority);

		notificationQueueImpl.resetOriginalValues();

		return notificationQueueImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		notificationQueueId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		notificationType = objectInput.readUTF();
		className = objectInput.readUTF();
		classPK = objectInput.readUTF();
		payload = objectInput.readUTF();
		fromUsername = objectInput.readUTF();
		toUsername = objectInput.readUTF();

		toUserId = objectInput.readLong();
		toEmail = objectInput.readUTF();
		toTelNo = objectInput.readUTF();
		publicationDate = objectInput.readLong();
		expireDate = objectInput.readLong();

		priority = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(notificationQueueId);

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

		if (className == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(className);
		}

		if (classPK == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(classPK);
		}

		if (payload == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(payload);
		}

		if (fromUsername == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(fromUsername);
		}

		if (toUsername == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(toUsername);
		}

		objectOutput.writeLong(toUserId);

		if (toEmail == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(toEmail);
		}

		if (toTelNo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(toTelNo);
		}

		objectOutput.writeLong(publicationDate);
		objectOutput.writeLong(expireDate);

		objectOutput.writeInt(priority);
	}

	public long notificationQueueId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String notificationType;
	public String className;
	public String classPK;
	public String payload;
	public String fromUsername;
	public String toUsername;
	public long toUserId;
	public String toEmail;
	public String toTelNo;
	public long publicationDate;
	public long expireDate;
	public int priority;
}