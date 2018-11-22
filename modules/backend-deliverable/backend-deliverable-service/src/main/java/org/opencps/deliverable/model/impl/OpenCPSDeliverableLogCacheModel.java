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

package org.opencps.deliverable.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import org.opencps.deliverable.model.OpenCPSDeliverableLog;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing OpenCPSDeliverableLog in entity cache.
 *
 * @author binhth
 * @see OpenCPSDeliverableLog
 * @generated
 */
@ProviderType
public class OpenCPSDeliverableLogCacheModel implements CacheModel<OpenCPSDeliverableLog>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OpenCPSDeliverableLogCacheModel)) {
			return false;
		}

		OpenCPSDeliverableLogCacheModel openCPSDeliverableLogCacheModel = (OpenCPSDeliverableLogCacheModel)obj;

		if (deliverableLogId == openCPSDeliverableLogCacheModel.deliverableLogId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, deliverableLogId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", deliverableLogId=");
		sb.append(deliverableLogId);
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
		sb.append(", deliverableId=");
		sb.append(deliverableId);
		sb.append(", dossierUid=");
		sb.append(dossierUid);
		sb.append(", author=");
		sb.append(author);
		sb.append(", content=");
		sb.append(content);
		sb.append(", deliverableAction=");
		sb.append(deliverableAction);
		sb.append(", actionDate=");
		sb.append(actionDate);
		sb.append(", payload=");
		sb.append(payload);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public OpenCPSDeliverableLog toEntityModel() {
		OpenCPSDeliverableLogImpl openCPSDeliverableLogImpl = new OpenCPSDeliverableLogImpl();

		if (uuid == null) {
			openCPSDeliverableLogImpl.setUuid("");
		}
		else {
			openCPSDeliverableLogImpl.setUuid(uuid);
		}

		openCPSDeliverableLogImpl.setDeliverableLogId(deliverableLogId);
		openCPSDeliverableLogImpl.setGroupId(groupId);
		openCPSDeliverableLogImpl.setCompanyId(companyId);
		openCPSDeliverableLogImpl.setUserId(userId);

		if (userName == null) {
			openCPSDeliverableLogImpl.setUserName("");
		}
		else {
			openCPSDeliverableLogImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			openCPSDeliverableLogImpl.setCreateDate(null);
		}
		else {
			openCPSDeliverableLogImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			openCPSDeliverableLogImpl.setModifiedDate(null);
		}
		else {
			openCPSDeliverableLogImpl.setModifiedDate(new Date(modifiedDate));
		}

		openCPSDeliverableLogImpl.setDeliverableId(deliverableId);

		if (dossierUid == null) {
			openCPSDeliverableLogImpl.setDossierUid("");
		}
		else {
			openCPSDeliverableLogImpl.setDossierUid(dossierUid);
		}

		if (author == null) {
			openCPSDeliverableLogImpl.setAuthor("");
		}
		else {
			openCPSDeliverableLogImpl.setAuthor(author);
		}

		if (content == null) {
			openCPSDeliverableLogImpl.setContent("");
		}
		else {
			openCPSDeliverableLogImpl.setContent(content);
		}

		openCPSDeliverableLogImpl.setDeliverableAction(deliverableAction);

		if (actionDate == Long.MIN_VALUE) {
			openCPSDeliverableLogImpl.setActionDate(null);
		}
		else {
			openCPSDeliverableLogImpl.setActionDate(new Date(actionDate));
		}

		if (payload == null) {
			openCPSDeliverableLogImpl.setPayload("");
		}
		else {
			openCPSDeliverableLogImpl.setPayload(payload);
		}

		openCPSDeliverableLogImpl.resetOriginalValues();

		return openCPSDeliverableLogImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		deliverableLogId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		deliverableId = objectInput.readLong();
		dossierUid = objectInput.readUTF();
		author = objectInput.readUTF();
		content = objectInput.readUTF();

		deliverableAction = objectInput.readInt();
		actionDate = objectInput.readLong();
		payload = objectInput.readUTF();
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

		objectOutput.writeLong(deliverableLogId);

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

		objectOutput.writeLong(deliverableId);

		if (dossierUid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(dossierUid);
		}

		if (author == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(author);
		}

		if (content == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(content);
		}

		objectOutput.writeInt(deliverableAction);
		objectOutput.writeLong(actionDate);

		if (payload == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(payload);
		}
	}

	public String uuid;
	public long deliverableLogId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long deliverableId;
	public String dossierUid;
	public String author;
	public String content;
	public int deliverableAction;
	public long actionDate;
	public String payload;
}