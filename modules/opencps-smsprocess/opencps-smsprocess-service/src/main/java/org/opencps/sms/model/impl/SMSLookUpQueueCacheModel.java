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

package org.opencps.sms.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import org.opencps.sms.model.SMSLookUpQueue;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing SMSLookUpQueue in entity cache.
 *
 * @author khoa
 * @see SMSLookUpQueue
 * @generated
 */
@ProviderType
public class SMSLookUpQueueCacheModel implements CacheModel<SMSLookUpQueue>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SMSLookUpQueueCacheModel)) {
			return false;
		}

		SMSLookUpQueueCacheModel smsLookUpQueueCacheModel = (SMSLookUpQueueCacheModel)obj;

		if (queueId == smsLookUpQueueCacheModel.queueId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, queueId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", queueId=");
		sb.append(queueId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", moid=");
		sb.append(moid);
		sb.append(", src=");
		sb.append(src);
		sb.append(", dest=");
		sb.append(dest);
		sb.append(", moseq=");
		sb.append(moseq);
		sb.append(", cmdcode=");
		sb.append(cmdcode);
		sb.append(", msgbody=");
		sb.append(msgbody);
		sb.append(", password=");
		sb.append(password);
		sb.append(", status=");
		sb.append(status);
		sb.append(", receivedDate=");
		sb.append(receivedDate);
		sb.append(", userName=");
		sb.append(userName);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SMSLookUpQueue toEntityModel() {
		SMSLookUpQueueImpl smsLookUpQueueImpl = new SMSLookUpQueueImpl();

		if (uuid == null) {
			smsLookUpQueueImpl.setUuid("");
		}
		else {
			smsLookUpQueueImpl.setUuid(uuid);
		}

		smsLookUpQueueImpl.setQueueId(queueId);
		smsLookUpQueueImpl.setGroupId(groupId);
		smsLookUpQueueImpl.setCompanyId(companyId);
		smsLookUpQueueImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			smsLookUpQueueImpl.setCreateDate(null);
		}
		else {
			smsLookUpQueueImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			smsLookUpQueueImpl.setModifiedDate(null);
		}
		else {
			smsLookUpQueueImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (moid == null) {
			smsLookUpQueueImpl.setMoid("");
		}
		else {
			smsLookUpQueueImpl.setMoid(moid);
		}

		if (src == null) {
			smsLookUpQueueImpl.setSrc("");
		}
		else {
			smsLookUpQueueImpl.setSrc(src);
		}

		if (dest == null) {
			smsLookUpQueueImpl.setDest("");
		}
		else {
			smsLookUpQueueImpl.setDest(dest);
		}

		if (moseq == null) {
			smsLookUpQueueImpl.setMoseq("");
		}
		else {
			smsLookUpQueueImpl.setMoseq(moseq);
		}

		if (cmdcode == null) {
			smsLookUpQueueImpl.setCmdcode("");
		}
		else {
			smsLookUpQueueImpl.setCmdcode(cmdcode);
		}

		if (msgbody == null) {
			smsLookUpQueueImpl.setMsgbody("");
		}
		else {
			smsLookUpQueueImpl.setMsgbody(msgbody);
		}

		if (password == null) {
			smsLookUpQueueImpl.setPassword("");
		}
		else {
			smsLookUpQueueImpl.setPassword(password);
		}

		smsLookUpQueueImpl.setStatus(status);

		if (receivedDate == Long.MIN_VALUE) {
			smsLookUpQueueImpl.setReceivedDate(null);
		}
		else {
			smsLookUpQueueImpl.setReceivedDate(new Date(receivedDate));
		}

		if (userName == null) {
			smsLookUpQueueImpl.setUserName("");
		}
		else {
			smsLookUpQueueImpl.setUserName(userName);
		}

		smsLookUpQueueImpl.resetOriginalValues();

		return smsLookUpQueueImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		queueId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		moid = objectInput.readUTF();
		src = objectInput.readUTF();
		dest = objectInput.readUTF();
		moseq = objectInput.readUTF();
		cmdcode = objectInput.readUTF();
		msgbody = objectInput.readUTF();
		password = objectInput.readUTF();

		status = objectInput.readInt();
		receivedDate = objectInput.readLong();
		userName = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(queueId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (moid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(moid);
		}

		if (src == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(src);
		}

		if (dest == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(dest);
		}

		if (moseq == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(moseq);
		}

		if (cmdcode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(cmdcode);
		}

		if (msgbody == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(msgbody);
		}

		if (password == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(password);
		}

		objectOutput.writeInt(status);
		objectOutput.writeLong(receivedDate);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}
	}

	public String uuid;
	public long queueId;
	public long groupId;
	public long companyId;
	public long userId;
	public long createDate;
	public long modifiedDate;
	public String moid;
	public String src;
	public String dest;
	public String moseq;
	public String cmdcode;
	public String msgbody;
	public String password;
	public int status;
	public long receivedDate;
	public String userName;
}