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

package org.opencps.datamgt.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import org.opencps.datamgt.model.FileAttach;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing FileAttach in entity cache.
 *
 * @author khoavu
 * @see FileAttach
 * @generated
 */
@ProviderType
public class FileAttachCacheModel implements CacheModel<FileAttach>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof FileAttachCacheModel)) {
			return false;
		}

		FileAttachCacheModel fileAttachCacheModel = (FileAttachCacheModel)obj;

		if (fileAttachId == fileAttachCacheModel.fileAttachId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, fileAttachId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(33);

		sb.append("{fileAttachId=");
		sb.append(fileAttachId);
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
		sb.append(", className=");
		sb.append(className);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", fullName=");
		sb.append(fullName);
		sb.append(", email=");
		sb.append(email);
		sb.append(", fileEntryId=");
		sb.append(fileEntryId);
		sb.append(", source=");
		sb.append(source);
		sb.append(", sourceUrl=");
		sb.append(sourceUrl);
		sb.append(", docFileId=");
		sb.append(docFileId);
		sb.append(", fileName=");
		sb.append(fileName);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public FileAttach toEntityModel() {
		FileAttachImpl fileAttachImpl = new FileAttachImpl();

		fileAttachImpl.setFileAttachId(fileAttachId);
		fileAttachImpl.setGroupId(groupId);
		fileAttachImpl.setCompanyId(companyId);
		fileAttachImpl.setUserId(userId);

		if (userName == null) {
			fileAttachImpl.setUserName("");
		}
		else {
			fileAttachImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			fileAttachImpl.setCreateDate(null);
		}
		else {
			fileAttachImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			fileAttachImpl.setModifiedDate(null);
		}
		else {
			fileAttachImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (className == null) {
			fileAttachImpl.setClassName("");
		}
		else {
			fileAttachImpl.setClassName(className);
		}

		if (classPK == null) {
			fileAttachImpl.setClassPK("");
		}
		else {
			fileAttachImpl.setClassPK(classPK);
		}

		if (fullName == null) {
			fileAttachImpl.setFullName("");
		}
		else {
			fileAttachImpl.setFullName(fullName);
		}

		if (email == null) {
			fileAttachImpl.setEmail("");
		}
		else {
			fileAttachImpl.setEmail(email);
		}

		fileAttachImpl.setFileEntryId(fileEntryId);

		if (source == null) {
			fileAttachImpl.setSource("");
		}
		else {
			fileAttachImpl.setSource(source);
		}

		if (sourceUrl == null) {
			fileAttachImpl.setSourceUrl("");
		}
		else {
			fileAttachImpl.setSourceUrl(sourceUrl);
		}

		fileAttachImpl.setDocFileId(docFileId);

		if (fileName == null) {
			fileAttachImpl.setFileName("");
		}
		else {
			fileAttachImpl.setFileName(fileName);
		}

		fileAttachImpl.resetOriginalValues();

		return fileAttachImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		fileAttachId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		className = objectInput.readUTF();
		classPK = objectInput.readUTF();
		fullName = objectInput.readUTF();
		email = objectInput.readUTF();

		fileEntryId = objectInput.readLong();
		source = objectInput.readUTF();
		sourceUrl = objectInput.readUTF();

		docFileId = objectInput.readLong();
		fileName = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(fileAttachId);

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

		if (className == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(className);
		}

		if (classPK == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(classPK);
		}

		if (fullName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(fullName);
		}

		if (email == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(email);
		}

		objectOutput.writeLong(fileEntryId);

		if (source == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(source);
		}

		if (sourceUrl == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(sourceUrl);
		}

		objectOutput.writeLong(docFileId);

		if (fileName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(fileName);
		}
	}

	public long fileAttachId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String className;
	public String classPK;
	public String fullName;
	public String email;
	public long fileEntryId;
	public String source;
	public String sourceUrl;
	public long docFileId;
	public String fileName;
}