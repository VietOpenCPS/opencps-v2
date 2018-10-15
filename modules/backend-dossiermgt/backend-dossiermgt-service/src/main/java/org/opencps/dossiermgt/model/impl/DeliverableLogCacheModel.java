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

package org.opencps.dossiermgt.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import org.opencps.dossiermgt.model.DeliverableLog;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing DeliverableLog in entity cache.
 *
 * @author huymq
 * @see DeliverableLog
 * @generated
 */
@ProviderType
public class DeliverableLogCacheModel implements CacheModel<DeliverableLog>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DeliverableLogCacheModel)) {
			return false;
		}

		DeliverableLogCacheModel deliverableLogCacheModel = (DeliverableLogCacheModel)obj;

		if (deliverableLogId == deliverableLogCacheModel.deliverableLogId) {
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
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", groupId=");
		sb.append(groupId);
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
	public DeliverableLog toEntityModel() {
		DeliverableLogImpl deliverableLogImpl = new DeliverableLogImpl();

		if (uuid == null) {
			deliverableLogImpl.setUuid("");
		}
		else {
			deliverableLogImpl.setUuid(uuid);
		}

		deliverableLogImpl.setDeliverableLogId(deliverableLogId);
		deliverableLogImpl.setCompanyId(companyId);
		deliverableLogImpl.setGroupId(groupId);
		deliverableLogImpl.setUserId(userId);

		if (userName == null) {
			deliverableLogImpl.setUserName("");
		}
		else {
			deliverableLogImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			deliverableLogImpl.setCreateDate(null);
		}
		else {
			deliverableLogImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			deliverableLogImpl.setModifiedDate(null);
		}
		else {
			deliverableLogImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (deliverableId == null) {
			deliverableLogImpl.setDeliverableId("");
		}
		else {
			deliverableLogImpl.setDeliverableId(deliverableId);
		}

		if (dossierUid == null) {
			deliverableLogImpl.setDossierUid("");
		}
		else {
			deliverableLogImpl.setDossierUid(dossierUid);
		}

		if (author == null) {
			deliverableLogImpl.setAuthor("");
		}
		else {
			deliverableLogImpl.setAuthor(author);
		}

		if (content == null) {
			deliverableLogImpl.setContent("");
		}
		else {
			deliverableLogImpl.setContent(content);
		}

		deliverableLogImpl.setDeliverableAction(deliverableAction);

		if (actionDate == Long.MIN_VALUE) {
			deliverableLogImpl.setActionDate(null);
		}
		else {
			deliverableLogImpl.setActionDate(new Date(actionDate));
		}

		if (payload == null) {
			deliverableLogImpl.setPayload("");
		}
		else {
			deliverableLogImpl.setPayload(payload);
		}

		deliverableLogImpl.resetOriginalValues();

		return deliverableLogImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		deliverableLogId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		deliverableId = objectInput.readUTF();
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

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (deliverableId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(deliverableId);
		}

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
	public long companyId;
	public long groupId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String deliverableId;
	public String dossierUid;
	public String author;
	public String content;
	public int deliverableAction;
	public long actionDate;
	public String payload;
}