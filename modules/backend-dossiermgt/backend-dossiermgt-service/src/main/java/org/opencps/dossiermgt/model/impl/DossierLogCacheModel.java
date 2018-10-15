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

import org.opencps.dossiermgt.model.DossierLog;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing DossierLog in entity cache.
 *
 * @author huymq
 * @see DossierLog
 * @generated
 */
@ProviderType
public class DossierLogCacheModel implements CacheModel<DossierLog>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DossierLogCacheModel)) {
			return false;
		}

		DossierLogCacheModel dossierLogCacheModel = (DossierLogCacheModel)obj;

		if (dossierLogId == dossierLogCacheModel.dossierLogId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, dossierLogId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", dossierLogId=");
		sb.append(dossierLogId);
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
		sb.append(", dossierId=");
		sb.append(dossierId);
		sb.append(", author=");
		sb.append(author);
		sb.append(", content=");
		sb.append(content);
		sb.append(", notificationType=");
		sb.append(notificationType);
		sb.append(", payload=");
		sb.append(payload);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DossierLog toEntityModel() {
		DossierLogImpl dossierLogImpl = new DossierLogImpl();

		if (uuid == null) {
			dossierLogImpl.setUuid("");
		}
		else {
			dossierLogImpl.setUuid(uuid);
		}

		dossierLogImpl.setDossierLogId(dossierLogId);
		dossierLogImpl.setGroupId(groupId);
		dossierLogImpl.setCompanyId(companyId);
		dossierLogImpl.setUserId(userId);

		if (userName == null) {
			dossierLogImpl.setUserName("");
		}
		else {
			dossierLogImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			dossierLogImpl.setCreateDate(null);
		}
		else {
			dossierLogImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			dossierLogImpl.setModifiedDate(null);
		}
		else {
			dossierLogImpl.setModifiedDate(new Date(modifiedDate));
		}

		dossierLogImpl.setDossierId(dossierId);

		if (author == null) {
			dossierLogImpl.setAuthor("");
		}
		else {
			dossierLogImpl.setAuthor(author);
		}

		if (content == null) {
			dossierLogImpl.setContent("");
		}
		else {
			dossierLogImpl.setContent(content);
		}

		if (notificationType == null) {
			dossierLogImpl.setNotificationType("");
		}
		else {
			dossierLogImpl.setNotificationType(notificationType);
		}

		if (payload == null) {
			dossierLogImpl.setPayload("");
		}
		else {
			dossierLogImpl.setPayload(payload);
		}

		dossierLogImpl.resetOriginalValues();

		return dossierLogImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		dossierLogId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		dossierId = objectInput.readLong();
		author = objectInput.readUTF();
		content = objectInput.readUTF();
		notificationType = objectInput.readUTF();
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

		objectOutput.writeLong(dossierLogId);

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

		objectOutput.writeLong(dossierId);

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

		if (notificationType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(notificationType);
		}

		if (payload == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(payload);
		}
	}

	public String uuid;
	public long dossierLogId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long dossierId;
	public String author;
	public String content;
	public String notificationType;
	public String payload;
}