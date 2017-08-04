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

package org.opencps.usermgt.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

import org.opencps.usermgt.model.PartnerFile;

/**
 * The cache model class for representing PartnerFile in entity cache.
 *
 * @author Binhth
 * @see PartnerFile
 * @generated
 */
@ProviderType
public class PartnerFileCacheModel implements CacheModel<PartnerFile>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PartnerFileCacheModel)) {
			return false;
		}

		PartnerFileCacheModel partnerFileCacheModel = (PartnerFileCacheModel)obj;

		if (partnerFileId == partnerFileCacheModel.partnerFileId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, partnerFileId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", partnerFileId=");
		sb.append(partnerFileId);
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
		sb.append(", partnerId=");
		sb.append(partnerId);
		sb.append(", fileEntryId=");
		sb.append(fileEntryId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public PartnerFile toEntityModel() {
		PartnerFileImpl partnerFileImpl = new PartnerFileImpl();

		if (uuid == null) {
			partnerFileImpl.setUuid(StringPool.BLANK);
		}
		else {
			partnerFileImpl.setUuid(uuid);
		}

		partnerFileImpl.setPartnerFileId(partnerFileId);
		partnerFileImpl.setCompanyId(companyId);
		partnerFileImpl.setGroupId(groupId);
		partnerFileImpl.setUserId(userId);

		if (userName == null) {
			partnerFileImpl.setUserName(StringPool.BLANK);
		}
		else {
			partnerFileImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			partnerFileImpl.setCreateDate(null);
		}
		else {
			partnerFileImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			partnerFileImpl.setModifiedDate(null);
		}
		else {
			partnerFileImpl.setModifiedDate(new Date(modifiedDate));
		}

		partnerFileImpl.setPartnerId(partnerId);
		partnerFileImpl.setFileEntryId(fileEntryId);

		partnerFileImpl.resetOriginalValues();

		return partnerFileImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		partnerFileId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		partnerId = objectInput.readLong();

		fileEntryId = objectInput.readLong();
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

		objectOutput.writeLong(partnerFileId);

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

		objectOutput.writeLong(partnerId);

		objectOutput.writeLong(fileEntryId);
	}

	public String uuid;
	public long partnerFileId;
	public long companyId;
	public long groupId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long partnerId;
	public long fileEntryId;
}