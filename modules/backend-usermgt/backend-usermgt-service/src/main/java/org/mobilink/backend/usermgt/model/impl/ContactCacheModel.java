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

package org.mobilink.backend.usermgt.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import org.mobilink.backend.usermgt.model.Contact;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Contact in entity cache.
 *
 * @author Binhth
 * @see Contact
 * @generated
 */
@ProviderType
public class ContactCacheModel implements CacheModel<Contact>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ContactCacheModel)) {
			return false;
		}

		ContactCacheModel contactCacheModel = (ContactCacheModel)obj;

		if (contactId == contactCacheModel.contactId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, contactId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", contactId=");
		sb.append(contactId);
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
		sb.append(", fullName=");
		sb.append(fullName);
		sb.append(", companyName=");
		sb.append(companyName);
		sb.append(", telNo=");
		sb.append(telNo);
		sb.append(", email=");
		sb.append(email);
		sb.append(", userMappingId=");
		sb.append(userMappingId);
		sb.append(", isOrg=");
		sb.append(isOrg);
		sb.append(", shared=");
		sb.append(shared);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Contact toEntityModel() {
		ContactImpl contactImpl = new ContactImpl();

		if (uuid == null) {
			contactImpl.setUuid(StringPool.BLANK);
		}
		else {
			contactImpl.setUuid(uuid);
		}

		contactImpl.setContactId(contactId);
		contactImpl.setGroupId(groupId);
		contactImpl.setCompanyId(companyId);
		contactImpl.setUserId(userId);

		if (userName == null) {
			contactImpl.setUserName(StringPool.BLANK);
		}
		else {
			contactImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			contactImpl.setCreateDate(null);
		}
		else {
			contactImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			contactImpl.setModifiedDate(null);
		}
		else {
			contactImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (fullName == null) {
			contactImpl.setFullName(StringPool.BLANK);
		}
		else {
			contactImpl.setFullName(fullName);
		}

		if (companyName == null) {
			contactImpl.setCompanyName(StringPool.BLANK);
		}
		else {
			contactImpl.setCompanyName(companyName);
		}

		if (telNo == null) {
			contactImpl.setTelNo(StringPool.BLANK);
		}
		else {
			contactImpl.setTelNo(telNo);
		}

		if (email == null) {
			contactImpl.setEmail(StringPool.BLANK);
		}
		else {
			contactImpl.setEmail(email);
		}

		contactImpl.setUserMappingId(userMappingId);
		contactImpl.setIsOrg(isOrg);
		contactImpl.setShared(shared);

		contactImpl.resetOriginalValues();

		return contactImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		contactId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		fullName = objectInput.readUTF();
		companyName = objectInput.readUTF();
		telNo = objectInput.readUTF();
		email = objectInput.readUTF();

		userMappingId = objectInput.readLong();

		isOrg = objectInput.readBoolean();

		shared = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(contactId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (fullName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(fullName);
		}

		if (companyName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyName);
		}

		if (telNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(telNo);
		}

		if (email == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(email);
		}

		objectOutput.writeLong(userMappingId);

		objectOutput.writeBoolean(isOrg);

		objectOutput.writeInt(shared);
	}

	public String uuid;
	public long contactId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String fullName;
	public String companyName;
	public String telNo;
	public String email;
	public long userMappingId;
	public boolean isOrg;
	public int shared;
}