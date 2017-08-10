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

package org.opencps.backend.dossiermgt.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import org.opencps.backend.dossiermgt.model.FileTemplate;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing FileTemplate in entity cache.
 *
 * @author huymq
 * @see FileTemplate
 * @generated
 */
@ProviderType
public class FileTemplateCacheModel implements CacheModel<FileTemplate>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof FileTemplateCacheModel)) {
			return false;
		}

		FileTemplateCacheModel fileTemplateCacheModel = (FileTemplateCacheModel)obj;

		if (fileTemplateId == fileTemplateCacheModel.fileTemplateId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, fileTemplateId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", fileTemplateId=");
		sb.append(fileTemplateId);
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
		sb.append(", fileName=");
		sb.append(fileName);
		sb.append(", fileNo=");
		sb.append(fileNo);
		sb.append(", fileEntryId=");
		sb.append(fileEntryId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public FileTemplate toEntityModel() {
		FileTemplateImpl fileTemplateImpl = new FileTemplateImpl();

		if (uuid == null) {
			fileTemplateImpl.setUuid(StringPool.BLANK);
		}
		else {
			fileTemplateImpl.setUuid(uuid);
		}

		fileTemplateImpl.setFileTemplateId(fileTemplateId);
		fileTemplateImpl.setGroupId(groupId);
		fileTemplateImpl.setCompanyId(companyId);
		fileTemplateImpl.setUserId(userId);

		if (userName == null) {
			fileTemplateImpl.setUserName(StringPool.BLANK);
		}
		else {
			fileTemplateImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			fileTemplateImpl.setCreateDate(null);
		}
		else {
			fileTemplateImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			fileTemplateImpl.setModifiedDate(null);
		}
		else {
			fileTemplateImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (fileName == null) {
			fileTemplateImpl.setFileName(StringPool.BLANK);
		}
		else {
			fileTemplateImpl.setFileName(fileName);
		}

		if (fileNo == null) {
			fileTemplateImpl.setFileNo(StringPool.BLANK);
		}
		else {
			fileTemplateImpl.setFileNo(fileNo);
		}

		fileTemplateImpl.setFileEntryId(fileEntryId);

		fileTemplateImpl.resetOriginalValues();

		return fileTemplateImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		fileTemplateId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		fileName = objectInput.readUTF();
		fileNo = objectInput.readUTF();

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

		objectOutput.writeLong(fileTemplateId);

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

		if (fileName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(fileName);
		}

		if (fileNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(fileNo);
		}

		objectOutput.writeLong(fileEntryId);
	}

	public String uuid;
	public long fileTemplateId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String fileName;
	public String fileNo;
	public long fileEntryId;
}