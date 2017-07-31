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

import org.mobilink.backend.usermgt.model.EmployeeFile;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing EmployeeFile in entity cache.
 *
 * @author Binhth
 * @see EmployeeFile
 * @generated
 */
@ProviderType
public class EmployeeFileCacheModel implements CacheModel<EmployeeFile>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EmployeeFileCacheModel)) {
			return false;
		}

		EmployeeFileCacheModel employeeFileCacheModel = (EmployeeFileCacheModel)obj;

		if (employeeFileId == employeeFileCacheModel.employeeFileId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, employeeFileId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", employeeFileId=");
		sb.append(employeeFileId);
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
		sb.append(", employeeId=");
		sb.append(employeeId);
		sb.append(", fileEntryId=");
		sb.append(fileEntryId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public EmployeeFile toEntityModel() {
		EmployeeFileImpl employeeFileImpl = new EmployeeFileImpl();

		if (uuid == null) {
			employeeFileImpl.setUuid(StringPool.BLANK);
		}
		else {
			employeeFileImpl.setUuid(uuid);
		}

		employeeFileImpl.setEmployeeFileId(employeeFileId);
		employeeFileImpl.setCompanyId(companyId);
		employeeFileImpl.setGroupId(groupId);
		employeeFileImpl.setUserId(userId);

		if (userName == null) {
			employeeFileImpl.setUserName(StringPool.BLANK);
		}
		else {
			employeeFileImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			employeeFileImpl.setCreateDate(null);
		}
		else {
			employeeFileImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			employeeFileImpl.setModifiedDate(null);
		}
		else {
			employeeFileImpl.setModifiedDate(new Date(modifiedDate));
		}

		employeeFileImpl.setEmployeeId(employeeId);
		employeeFileImpl.setFileEntryId(fileEntryId);

		employeeFileImpl.resetOriginalValues();

		return employeeFileImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		employeeFileId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		employeeId = objectInput.readLong();

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

		objectOutput.writeLong(employeeFileId);

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

		objectOutput.writeLong(employeeId);

		objectOutput.writeLong(fileEntryId);
	}

	public String uuid;
	public long employeeFileId;
	public long companyId;
	public long groupId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long employeeId;
	public long fileEntryId;
}