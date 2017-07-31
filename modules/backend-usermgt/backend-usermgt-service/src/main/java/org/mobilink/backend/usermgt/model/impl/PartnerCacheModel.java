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

import org.mobilink.backend.usermgt.model.Partner;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Partner in entity cache.
 *
 * @author Binhth
 * @see Partner
 * @generated
 */
@ProviderType
public class PartnerCacheModel implements CacheModel<Partner>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PartnerCacheModel)) {
			return false;
		}

		PartnerCacheModel partnerCacheModel = (PartnerCacheModel)obj;

		if (partnerId == partnerCacheModel.partnerId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, partnerId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", partnerId=");
		sb.append(partnerId);
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
		sb.append(", name=");
		sb.append(name);
		sb.append(", address=");
		sb.append(address);
		sb.append(", telNo=");
		sb.append(telNo);
		sb.append(", faxNo=");
		sb.append(faxNo);
		sb.append(", email=");
		sb.append(email);
		sb.append(", website=");
		sb.append(website);
		sb.append(", partnerClass=");
		sb.append(partnerClass);
		sb.append(", accountUserId=");
		sb.append(accountUserId);
		sb.append(", docFileId=");
		sb.append(docFileId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Partner toEntityModel() {
		PartnerImpl partnerImpl = new PartnerImpl();

		if (uuid == null) {
			partnerImpl.setUuid(StringPool.BLANK);
		}
		else {
			partnerImpl.setUuid(uuid);
		}

		partnerImpl.setPartnerId(partnerId);
		partnerImpl.setCompanyId(companyId);
		partnerImpl.setGroupId(groupId);
		partnerImpl.setUserId(userId);

		if (userName == null) {
			partnerImpl.setUserName(StringPool.BLANK);
		}
		else {
			partnerImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			partnerImpl.setCreateDate(null);
		}
		else {
			partnerImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			partnerImpl.setModifiedDate(null);
		}
		else {
			partnerImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			partnerImpl.setName(StringPool.BLANK);
		}
		else {
			partnerImpl.setName(name);
		}

		if (address == null) {
			partnerImpl.setAddress(StringPool.BLANK);
		}
		else {
			partnerImpl.setAddress(address);
		}

		if (telNo == null) {
			partnerImpl.setTelNo(StringPool.BLANK);
		}
		else {
			partnerImpl.setTelNo(telNo);
		}

		if (faxNo == null) {
			partnerImpl.setFaxNo(StringPool.BLANK);
		}
		else {
			partnerImpl.setFaxNo(faxNo);
		}

		if (email == null) {
			partnerImpl.setEmail(StringPool.BLANK);
		}
		else {
			partnerImpl.setEmail(email);
		}

		if (website == null) {
			partnerImpl.setWebsite(StringPool.BLANK);
		}
		else {
			partnerImpl.setWebsite(website);
		}

		partnerImpl.setPartnerClass(partnerClass);
		partnerImpl.setAccountUserId(accountUserId);

		if (docFileId == null) {
			partnerImpl.setDocFileId(StringPool.BLANK);
		}
		else {
			partnerImpl.setDocFileId(docFileId);
		}

		partnerImpl.resetOriginalValues();

		return partnerImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		partnerId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
		address = objectInput.readUTF();
		telNo = objectInput.readUTF();
		faxNo = objectInput.readUTF();
		email = objectInput.readUTF();
		website = objectInput.readUTF();

		partnerClass = objectInput.readInt();

		accountUserId = objectInput.readLong();
		docFileId = objectInput.readUTF();
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

		objectOutput.writeLong(partnerId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (address == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(address);
		}

		if (telNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(telNo);
		}

		if (faxNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(faxNo);
		}

		if (email == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(email);
		}

		if (website == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(website);
		}

		objectOutput.writeInt(partnerClass);

		objectOutput.writeLong(accountUserId);

		if (docFileId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(docFileId);
		}
	}

	public String uuid;
	public long partnerId;
	public long companyId;
	public long groupId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String address;
	public String telNo;
	public String faxNo;
	public String email;
	public String website;
	public int partnerClass;
	public long accountUserId;
	public String docFileId;
}