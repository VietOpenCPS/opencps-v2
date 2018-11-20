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

import org.opencps.sms.model.SMSGatewayLog;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing SMSGatewayLog in entity cache.
 *
 * @author khoa
 * @see SMSGatewayLog
 * @generated
 */
@ProviderType
public class SMSGatewayLogCacheModel implements CacheModel<SMSGatewayLog>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SMSGatewayLogCacheModel)) {
			return false;
		}

		SMSGatewayLogCacheModel smsGatewayLogCacheModel = (SMSGatewayLogCacheModel)obj;

		if (smsId == smsGatewayLogCacheModel.smsId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, smsId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(39);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", smsId=");
		sb.append(smsId);
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
		sb.append(", src=");
		sb.append(src);
		sb.append(", smsReq=");
		sb.append(smsReq);
		sb.append(", smsReply=");
		sb.append(smsReply);
		sb.append(", dossierNo=");
		sb.append(dossierNo);
		sb.append(", applicationName=");
		sb.append(applicationName);
		sb.append(", reqDate=");
		sb.append(reqDate);
		sb.append(", replyDate=");
		sb.append(replyDate);
		sb.append(", status=");
		sb.append(status);
		sb.append(", smsType=");
		sb.append(smsType);
		sb.append(", lastReplyManualDate=");
		sb.append(lastReplyManualDate);
		sb.append(", lastReplyManualUserId=");
		sb.append(lastReplyManualUserId);
		sb.append(", lastReplyManualUserName=");
		sb.append(lastReplyManualUserName);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SMSGatewayLog toEntityModel() {
		SMSGatewayLogImpl smsGatewayLogImpl = new SMSGatewayLogImpl();

		if (uuid == null) {
			smsGatewayLogImpl.setUuid("");
		}
		else {
			smsGatewayLogImpl.setUuid(uuid);
		}

		smsGatewayLogImpl.setSmsId(smsId);
		smsGatewayLogImpl.setGroupId(groupId);
		smsGatewayLogImpl.setCompanyId(companyId);
		smsGatewayLogImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			smsGatewayLogImpl.setCreateDate(null);
		}
		else {
			smsGatewayLogImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			smsGatewayLogImpl.setModifiedDate(null);
		}
		else {
			smsGatewayLogImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (src == null) {
			smsGatewayLogImpl.setSrc("");
		}
		else {
			smsGatewayLogImpl.setSrc(src);
		}

		if (smsReq == null) {
			smsGatewayLogImpl.setSmsReq("");
		}
		else {
			smsGatewayLogImpl.setSmsReq(smsReq);
		}

		if (smsReply == null) {
			smsGatewayLogImpl.setSmsReply("");
		}
		else {
			smsGatewayLogImpl.setSmsReply(smsReply);
		}

		if (dossierNo == null) {
			smsGatewayLogImpl.setDossierNo("");
		}
		else {
			smsGatewayLogImpl.setDossierNo(dossierNo);
		}

		if (applicationName == null) {
			smsGatewayLogImpl.setApplicationName("");
		}
		else {
			smsGatewayLogImpl.setApplicationName(applicationName);
		}

		if (reqDate == Long.MIN_VALUE) {
			smsGatewayLogImpl.setReqDate(null);
		}
		else {
			smsGatewayLogImpl.setReqDate(new Date(reqDate));
		}

		if (replyDate == Long.MIN_VALUE) {
			smsGatewayLogImpl.setReplyDate(null);
		}
		else {
			smsGatewayLogImpl.setReplyDate(new Date(replyDate));
		}

		smsGatewayLogImpl.setStatus(status);
		smsGatewayLogImpl.setSmsType(smsType);

		if (lastReplyManualDate == Long.MIN_VALUE) {
			smsGatewayLogImpl.setLastReplyManualDate(null);
		}
		else {
			smsGatewayLogImpl.setLastReplyManualDate(new Date(
					lastReplyManualDate));
		}

		smsGatewayLogImpl.setLastReplyManualUserId(lastReplyManualUserId);

		if (lastReplyManualUserName == null) {
			smsGatewayLogImpl.setLastReplyManualUserName("");
		}
		else {
			smsGatewayLogImpl.setLastReplyManualUserName(lastReplyManualUserName);
		}

		smsGatewayLogImpl.resetOriginalValues();

		return smsGatewayLogImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		smsId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		src = objectInput.readUTF();
		smsReq = objectInput.readUTF();
		smsReply = objectInput.readUTF();
		dossierNo = objectInput.readUTF();
		applicationName = objectInput.readUTF();
		reqDate = objectInput.readLong();
		replyDate = objectInput.readLong();

		status = objectInput.readInt();

		smsType = objectInput.readInt();
		lastReplyManualDate = objectInput.readLong();

		lastReplyManualUserId = objectInput.readLong();
		lastReplyManualUserName = objectInput.readUTF();
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

		objectOutput.writeLong(smsId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (src == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(src);
		}

		if (smsReq == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(smsReq);
		}

		if (smsReply == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(smsReply);
		}

		if (dossierNo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(dossierNo);
		}

		if (applicationName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(applicationName);
		}

		objectOutput.writeLong(reqDate);
		objectOutput.writeLong(replyDate);

		objectOutput.writeInt(status);

		objectOutput.writeInt(smsType);
		objectOutput.writeLong(lastReplyManualDate);

		objectOutput.writeLong(lastReplyManualUserId);

		if (lastReplyManualUserName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(lastReplyManualUserName);
		}
	}

	public String uuid;
	public long smsId;
	public long groupId;
	public long companyId;
	public long userId;
	public long createDate;
	public long modifiedDate;
	public String src;
	public String smsReq;
	public String smsReply;
	public String dossierNo;
	public String applicationName;
	public long reqDate;
	public long replyDate;
	public int status;
	public int smsType;
	public long lastReplyManualDate;
	public long lastReplyManualUserId;
	public String lastReplyManualUserName;
}