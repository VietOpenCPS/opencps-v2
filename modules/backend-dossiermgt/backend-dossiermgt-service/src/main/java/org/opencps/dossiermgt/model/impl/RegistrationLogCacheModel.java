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

import org.opencps.dossiermgt.model.RegistrationLog;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing RegistrationLog in entity cache.
 *
 * @author huymq
 * @see RegistrationLog
 * @generated
 */
@ProviderType
public class RegistrationLogCacheModel implements CacheModel<RegistrationLog>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RegistrationLogCacheModel)) {
			return false;
		}

		RegistrationLogCacheModel registrationLogCacheModel = (RegistrationLogCacheModel)obj;

		if (registrationLogId == registrationLogCacheModel.registrationLogId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, registrationLogId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", registrationLogId=");
		sb.append(registrationLogId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", registrationId=");
		sb.append(registrationId);
		sb.append(", author=");
		sb.append(author);
		sb.append(", content=");
		sb.append(content);
		sb.append(", payload=");
		sb.append(payload);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public RegistrationLog toEntityModel() {
		RegistrationLogImpl registrationLogImpl = new RegistrationLogImpl();

		if (uuid == null) {
			registrationLogImpl.setUuid("");
		}
		else {
			registrationLogImpl.setUuid(uuid);
		}

		registrationLogImpl.setRegistrationLogId(registrationLogId);
		registrationLogImpl.setCompanyId(companyId);
		registrationLogImpl.setGroupId(groupId);
		registrationLogImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			registrationLogImpl.setCreateDate(null);
		}
		else {
			registrationLogImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			registrationLogImpl.setModifiedDate(null);
		}
		else {
			registrationLogImpl.setModifiedDate(new Date(modifiedDate));
		}

		registrationLogImpl.setRegistrationId(registrationId);

		if (author == null) {
			registrationLogImpl.setAuthor("");
		}
		else {
			registrationLogImpl.setAuthor(author);
		}

		if (content == null) {
			registrationLogImpl.setContent("");
		}
		else {
			registrationLogImpl.setContent(content);
		}

		if (payload == null) {
			registrationLogImpl.setPayload("");
		}
		else {
			registrationLogImpl.setPayload(payload);
		}

		registrationLogImpl.resetOriginalValues();

		return registrationLogImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		registrationLogId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		registrationId = objectInput.readLong();
		author = objectInput.readUTF();
		content = objectInput.readUTF();
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

		objectOutput.writeLong(registrationLogId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(registrationId);

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

		if (payload == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(payload);
		}
	}

	public String uuid;
	public long registrationLogId;
	public long companyId;
	public long groupId;
	public long userId;
	public long createDate;
	public long modifiedDate;
	public long registrationId;
	public String author;
	public String content;
	public String payload;
}