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

import org.opencps.usermgt.model.ApplicantData;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ApplicantData in entity cache.
 *
 * @author khoavu
 * @see ApplicantData
 * @generated
 */
@ProviderType
public class ApplicantDataCacheModel implements CacheModel<ApplicantData>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ApplicantDataCacheModel)) {
			return false;
		}

		ApplicantDataCacheModel applicantDataCacheModel = (ApplicantDataCacheModel)obj;

		if (applicantDataId == applicantDataCacheModel.applicantDataId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, applicantDataId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(33);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", applicantDataId=");
		sb.append(applicantDataId);
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
		sb.append(", fileNo=");
		sb.append(fileNo);
		sb.append(", fileName=");
		sb.append(fileName);
		sb.append(", fileEntryId=");
		sb.append(fileEntryId);
		sb.append(", metadata=");
		sb.append(metadata);
		sb.append(", status=");
		sb.append(status);
		sb.append(", applicantIdNo=");
		sb.append(applicantIdNo);
		sb.append(", applicantDataType=");
		sb.append(applicantDataType);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ApplicantData toEntityModel() {
		ApplicantDataImpl applicantDataImpl = new ApplicantDataImpl();

		if (uuid == null) {
			applicantDataImpl.setUuid("");
		}
		else {
			applicantDataImpl.setUuid(uuid);
		}

		applicantDataImpl.setApplicantDataId(applicantDataId);

		if (createDate == Long.MIN_VALUE) {
			applicantDataImpl.setCreateDate(null);
		}
		else {
			applicantDataImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			applicantDataImpl.setModifiedDate(null);
		}
		else {
			applicantDataImpl.setModifiedDate(new Date(modifiedDate));
		}

		applicantDataImpl.setCompanyId(companyId);
		applicantDataImpl.setGroupId(groupId);
		applicantDataImpl.setUserId(userId);

		if (userName == null) {
			applicantDataImpl.setUserName("");
		}
		else {
			applicantDataImpl.setUserName(userName);
		}

		if (fileTemplateNo == null) {
			applicantDataImpl.setFileTemplateNo("");
		}
		else {
			applicantDataImpl.setFileTemplateNo(fileTemplateNo);
		}

		if (fileNo == null) {
			applicantDataImpl.setFileNo("");
		}
		else {
			applicantDataImpl.setFileNo(fileNo);
		}

		if (fileName == null) {
			applicantDataImpl.setFileName("");
		}
		else {
			applicantDataImpl.setFileName(fileName);
		}

		applicantDataImpl.setFileEntryId(fileEntryId);

		if (metadata == null) {
			applicantDataImpl.setMetadata("");
		}
		else {
			applicantDataImpl.setMetadata(metadata);
		}

		applicantDataImpl.setStatus(status);

		if (applicantIdNo == null) {
			applicantDataImpl.setApplicantIdNo("");
		}
		else {
			applicantDataImpl.setApplicantIdNo(applicantIdNo);
		}

		applicantDataImpl.setApplicantDataType(applicantDataType);

		applicantDataImpl.resetOriginalValues();

		return applicantDataImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		applicantDataId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		fileTemplateNo = objectInput.readUTF();
		fileNo = objectInput.readUTF();
		fileName = objectInput.readUTF();

		fileEntryId = objectInput.readLong();
		metadata = objectInput.readUTF();

		status = objectInput.readInt();
		applicantIdNo = objectInput.readUTF();

		applicantDataType = objectInput.readInt();
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

		objectOutput.writeLong(applicantDataId);
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

		if (fileNo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(fileNo);
		}

		if (fileName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(fileName);
		}

		objectOutput.writeLong(fileEntryId);

		if (metadata == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(metadata);
		}

		objectOutput.writeInt(status);

		if (applicantIdNo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(applicantIdNo);
		}

		objectOutput.writeInt(applicantDataType);
	}

	public String uuid;
	public long applicantDataId;
	public long createDate;
	public long modifiedDate;
	public long companyId;
	public long groupId;
	public long userId;
	public String userName;
	public String fileTemplateNo;
	public String fileNo;
	public String fileName;
	public long fileEntryId;
	public String metadata;
	public int status;
	public String applicantIdNo;
	public int applicantDataType;
}