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

import org.opencps.usermgt.model.FileItem;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing FileItem in entity cache.
 *
 * @author khoavu
 * @see FileItem
 * @generated
 */
@ProviderType
public class FileItemCacheModel implements CacheModel<FileItem>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof FileItemCacheModel)) {
			return false;
		}

		FileItemCacheModel fileItemCacheModel = (FileItemCacheModel)obj;

		if (fileItemId == fileItemCacheModel.fileItemId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, fileItemId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", fileItemId=");
		sb.append(fileItemId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", fileTemplateNo=");
		sb.append(fileTemplateNo);
		sb.append(", name=");
		sb.append(name);
		sb.append(", status=");
		sb.append(status);
		sb.append(", size=");
		sb.append(size);
		sb.append(", fileType=");
		sb.append(fileType);
		sb.append(", log=");
		sb.append(log);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public FileItem toEntityModel() {
		FileItemImpl fileItemImpl = new FileItemImpl();

		if (uuid == null) {
			fileItemImpl.setUuid("");
		}
		else {
			fileItemImpl.setUuid(uuid);
		}

		fileItemImpl.setFileItemId(fileItemId);

		if (createDate == Long.MIN_VALUE) {
			fileItemImpl.setCreateDate(null);
		}
		else {
			fileItemImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			fileItemImpl.setModifiedDate(null);
		}
		else {
			fileItemImpl.setModifiedDate(new Date(modifiedDate));
		}

		fileItemImpl.setCompanyId(companyId);
		fileItemImpl.setGroupId(groupId);
		fileItemImpl.setUserId(userId);

		if (userName == null) {
			fileItemImpl.setUserName("");
		}
		else {
			fileItemImpl.setUserName(userName);
		}

		if (fileTemplateNo == null) {
			fileItemImpl.setFileTemplateNo("");
		}
		else {
			fileItemImpl.setFileTemplateNo(fileTemplateNo);
		}

		if (name == null) {
			fileItemImpl.setName("");
		}
		else {
			fileItemImpl.setName(name);
		}

		fileItemImpl.setStatus(status);
		fileItemImpl.setSize(size);

		if (fileType == null) {
			fileItemImpl.setFileType("");
		}
		else {
			fileItemImpl.setFileType(fileType);
		}

		if (log == null) {
			fileItemImpl.setLog("");
		}
		else {
			fileItemImpl.setLog(log);
		}

		fileItemImpl.resetOriginalValues();

		return fileItemImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		fileItemId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		fileTemplateNo = objectInput.readUTF();
		name = objectInput.readUTF();

		status = objectInput.readInt();

		size = objectInput.readInt();
		fileType = objectInput.readUTF();
		log = objectInput.readUTF();
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

		objectOutput.writeLong(fileItemId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		if (fileTemplateNo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(fileTemplateNo);
		}

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		objectOutput.writeInt(status);

		objectOutput.writeInt(size);

		if (fileType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(fileType);
		}

		if (log == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(log);
		}
	}

	public String uuid;
	public long fileItemId;
	public long createDate;
	public long modifiedDate;
	public long companyId;
	public long groupId;
	public long userId;
	public String userName;
	public String fileTemplateNo;
	public String name;
	public int status;
	public int size;
	public String fileType;
	public String log;
}