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

import org.opencps.backend.dossiermgt.model.DossierFile;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing DossierFile in entity cache.
 *
 * @author huymq
 * @see DossierFile
 * @generated
 */
@ProviderType
public class DossierFileCacheModel implements CacheModel<DossierFile>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DossierFileCacheModel)) {
			return false;
		}

		DossierFileCacheModel dossierFileCacheModel = (DossierFileCacheModel)obj;

		if (dossierFileId == dossierFileCacheModel.dossierFileId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, dossierFileId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(51);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", dossierFileId=");
		sb.append(dossierFileId);
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
		sb.append(", referenceUid=");
		sb.append(referenceUid);
		sb.append(", dossierTemplateNo=");
		sb.append(dossierTemplateNo);
		sb.append(", dossierPartNo=");
		sb.append(dossierPartNo);
		sb.append(", fileTemplateNo=");
		sb.append(fileTemplateNo);
		sb.append(", displayName=");
		sb.append(displayName);
		sb.append(", formData=");
		sb.append(formData);
		sb.append(", fileEntryId=");
		sb.append(fileEntryId);
		sb.append(", dossierFileNo=");
		sb.append(dossierFileNo);
		sb.append(", dossierFileDate=");
		sb.append(dossierFileDate);
		sb.append(", original=");
		sb.append(original);
		sb.append(", isNew=");
		sb.append(isNew);
		sb.append(", signed=");
		sb.append(signed);
		sb.append(", signCheck=");
		sb.append(signCheck);
		sb.append(", signInfo=");
		sb.append(signInfo);
		sb.append(", formScript=");
		sb.append(formScript);
		sb.append(", formReport=");
		sb.append(formReport);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DossierFile toEntityModel() {
		DossierFileImpl dossierFileImpl = new DossierFileImpl();

		if (uuid == null) {
			dossierFileImpl.setUuid(StringPool.BLANK);
		}
		else {
			dossierFileImpl.setUuid(uuid);
		}

		dossierFileImpl.setDossierFileId(dossierFileId);
		dossierFileImpl.setGroupId(groupId);
		dossierFileImpl.setCompanyId(companyId);
		dossierFileImpl.setUserId(userId);

		if (userName == null) {
			dossierFileImpl.setUserName(StringPool.BLANK);
		}
		else {
			dossierFileImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			dossierFileImpl.setCreateDate(null);
		}
		else {
			dossierFileImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			dossierFileImpl.setModifiedDate(null);
		}
		else {
			dossierFileImpl.setModifiedDate(new Date(modifiedDate));
		}

		dossierFileImpl.setDossierId(dossierId);

		if (referenceUid == null) {
			dossierFileImpl.setReferenceUid(StringPool.BLANK);
		}
		else {
			dossierFileImpl.setReferenceUid(referenceUid);
		}

		if (dossierTemplateNo == null) {
			dossierFileImpl.setDossierTemplateNo(StringPool.BLANK);
		}
		else {
			dossierFileImpl.setDossierTemplateNo(dossierTemplateNo);
		}

		if (dossierPartNo == null) {
			dossierFileImpl.setDossierPartNo(StringPool.BLANK);
		}
		else {
			dossierFileImpl.setDossierPartNo(dossierPartNo);
		}

		if (fileTemplateNo == null) {
			dossierFileImpl.setFileTemplateNo(StringPool.BLANK);
		}
		else {
			dossierFileImpl.setFileTemplateNo(fileTemplateNo);
		}

		if (displayName == null) {
			dossierFileImpl.setDisplayName(StringPool.BLANK);
		}
		else {
			dossierFileImpl.setDisplayName(displayName);
		}

		if (formData == null) {
			dossierFileImpl.setFormData(StringPool.BLANK);
		}
		else {
			dossierFileImpl.setFormData(formData);
		}

		dossierFileImpl.setFileEntryId(fileEntryId);

		if (dossierFileNo == Long.MIN_VALUE) {
			dossierFileImpl.setDossierFileNo(null);
		}
		else {
			dossierFileImpl.setDossierFileNo(new Date(dossierFileNo));
		}

		if (dossierFileDate == null) {
			dossierFileImpl.setDossierFileDate(StringPool.BLANK);
		}
		else {
			dossierFileImpl.setDossierFileDate(dossierFileDate);
		}

		dossierFileImpl.setOriginal(original);
		dossierFileImpl.setIsNew(isNew);
		dossierFileImpl.setSigned(signed);
		dossierFileImpl.setSignCheck(signCheck);

		if (signInfo == null) {
			dossierFileImpl.setSignInfo(StringPool.BLANK);
		}
		else {
			dossierFileImpl.setSignInfo(signInfo);
		}

		if (formScript == null) {
			dossierFileImpl.setFormScript(StringPool.BLANK);
		}
		else {
			dossierFileImpl.setFormScript(formScript);
		}

		if (formReport == null) {
			dossierFileImpl.setFormReport(StringPool.BLANK);
		}
		else {
			dossierFileImpl.setFormReport(formReport);
		}

		dossierFileImpl.resetOriginalValues();

		return dossierFileImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		dossierFileId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		dossierId = objectInput.readLong();
		referenceUid = objectInput.readUTF();
		dossierTemplateNo = objectInput.readUTF();
		dossierPartNo = objectInput.readUTF();
		fileTemplateNo = objectInput.readUTF();
		displayName = objectInput.readUTF();
		formData = objectInput.readUTF();

		fileEntryId = objectInput.readLong();
		dossierFileNo = objectInput.readLong();
		dossierFileDate = objectInput.readUTF();

		original = objectInput.readBoolean();

		isNew = objectInput.readBoolean();

		signed = objectInput.readBoolean();

		signCheck = objectInput.readInt();
		signInfo = objectInput.readUTF();
		formScript = objectInput.readUTF();
		formReport = objectInput.readUTF();
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

		objectOutput.writeLong(dossierFileId);

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

		objectOutput.writeLong(dossierId);

		if (referenceUid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(referenceUid);
		}

		if (dossierTemplateNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(dossierTemplateNo);
		}

		if (dossierPartNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(dossierPartNo);
		}

		if (fileTemplateNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(fileTemplateNo);
		}

		if (displayName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(displayName);
		}

		if (formData == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(formData);
		}

		objectOutput.writeLong(fileEntryId);
		objectOutput.writeLong(dossierFileNo);

		if (dossierFileDate == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(dossierFileDate);
		}

		objectOutput.writeBoolean(original);

		objectOutput.writeBoolean(isNew);

		objectOutput.writeBoolean(signed);

		objectOutput.writeInt(signCheck);

		if (signInfo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(signInfo);
		}

		if (formScript == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(formScript);
		}

		if (formReport == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(formReport);
		}
	}

	public String uuid;
	public long dossierFileId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long dossierId;
	public String referenceUid;
	public String dossierTemplateNo;
	public String dossierPartNo;
	public String fileTemplateNo;
	public String displayName;
	public String formData;
	public long fileEntryId;
	public long dossierFileNo;
	public String dossierFileDate;
	public boolean original;
	public boolean isNew;
	public boolean signed;
	public int signCheck;
	public String signInfo;
	public String formScript;
	public String formReport;
}