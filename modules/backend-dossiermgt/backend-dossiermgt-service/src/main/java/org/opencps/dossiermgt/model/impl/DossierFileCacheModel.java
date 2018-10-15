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

import org.opencps.dossiermgt.model.DossierFile;

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
		StringBundler sb = new StringBundler(55);

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
		sb.append(", dossierPartType=");
		sb.append(dossierPartType);
		sb.append(", fileTemplateNo=");
		sb.append(fileTemplateNo);
		sb.append(", displayName=");
		sb.append(displayName);
		sb.append(", formData=");
		sb.append(formData);
		sb.append(", fileEntryId=");
		sb.append(fileEntryId);
		sb.append(", original=");
		sb.append(original);
		sb.append(", eForm=");
		sb.append(eForm);
		sb.append(", isNew=");
		sb.append(isNew);
		sb.append(", removed=");
		sb.append(removed);
		sb.append(", signCheck=");
		sb.append(signCheck);
		sb.append(", signInfo=");
		sb.append(signInfo);
		sb.append(", formScript=");
		sb.append(formScript);
		sb.append(", formReport=");
		sb.append(formReport);
		sb.append(", formSchema=");
		sb.append(formSchema);
		sb.append(", deliverableCode=");
		sb.append(deliverableCode);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DossierFile toEntityModel() {
		DossierFileImpl dossierFileImpl = new DossierFileImpl();

		if (uuid == null) {
			dossierFileImpl.setUuid("");
		}
		else {
			dossierFileImpl.setUuid(uuid);
		}

		dossierFileImpl.setDossierFileId(dossierFileId);
		dossierFileImpl.setGroupId(groupId);
		dossierFileImpl.setCompanyId(companyId);
		dossierFileImpl.setUserId(userId);

		if (userName == null) {
			dossierFileImpl.setUserName("");
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
			dossierFileImpl.setReferenceUid("");
		}
		else {
			dossierFileImpl.setReferenceUid(referenceUid);
		}

		if (dossierTemplateNo == null) {
			dossierFileImpl.setDossierTemplateNo("");
		}
		else {
			dossierFileImpl.setDossierTemplateNo(dossierTemplateNo);
		}

		if (dossierPartNo == null) {
			dossierFileImpl.setDossierPartNo("");
		}
		else {
			dossierFileImpl.setDossierPartNo(dossierPartNo);
		}

		dossierFileImpl.setDossierPartType(dossierPartType);

		if (fileTemplateNo == null) {
			dossierFileImpl.setFileTemplateNo("");
		}
		else {
			dossierFileImpl.setFileTemplateNo(fileTemplateNo);
		}

		if (displayName == null) {
			dossierFileImpl.setDisplayName("");
		}
		else {
			dossierFileImpl.setDisplayName(displayName);
		}

		if (formData == null) {
			dossierFileImpl.setFormData("");
		}
		else {
			dossierFileImpl.setFormData(formData);
		}

		dossierFileImpl.setFileEntryId(fileEntryId);
		dossierFileImpl.setOriginal(original);
		dossierFileImpl.setEForm(eForm);
		dossierFileImpl.setIsNew(isNew);
		dossierFileImpl.setRemoved(removed);
		dossierFileImpl.setSignCheck(signCheck);

		if (signInfo == null) {
			dossierFileImpl.setSignInfo("");
		}
		else {
			dossierFileImpl.setSignInfo(signInfo);
		}

		if (formScript == null) {
			dossierFileImpl.setFormScript("");
		}
		else {
			dossierFileImpl.setFormScript(formScript);
		}

		if (formReport == null) {
			dossierFileImpl.setFormReport("");
		}
		else {
			dossierFileImpl.setFormReport(formReport);
		}

		if (formSchema == null) {
			dossierFileImpl.setFormSchema("");
		}
		else {
			dossierFileImpl.setFormSchema(formSchema);
		}

		if (deliverableCode == null) {
			dossierFileImpl.setDeliverableCode("");
		}
		else {
			dossierFileImpl.setDeliverableCode(deliverableCode);
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

		dossierPartType = objectInput.readInt();
		fileTemplateNo = objectInput.readUTF();
		displayName = objectInput.readUTF();
		formData = objectInput.readUTF();

		fileEntryId = objectInput.readLong();

		original = objectInput.readBoolean();

		eForm = objectInput.readBoolean();

		isNew = objectInput.readBoolean();

		removed = objectInput.readBoolean();

		signCheck = objectInput.readInt();
		signInfo = objectInput.readUTF();
		formScript = objectInput.readUTF();
		formReport = objectInput.readUTF();
		formSchema = objectInput.readUTF();
		deliverableCode = objectInput.readUTF();
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

		objectOutput.writeLong(dossierFileId);

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

		objectOutput.writeLong(dossierId);

		if (referenceUid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(referenceUid);
		}

		if (dossierTemplateNo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(dossierTemplateNo);
		}

		if (dossierPartNo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(dossierPartNo);
		}

		objectOutput.writeInt(dossierPartType);

		if (fileTemplateNo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(fileTemplateNo);
		}

		if (displayName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(displayName);
		}

		if (formData == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(formData);
		}

		objectOutput.writeLong(fileEntryId);

		objectOutput.writeBoolean(original);

		objectOutput.writeBoolean(eForm);

		objectOutput.writeBoolean(isNew);

		objectOutput.writeBoolean(removed);

		objectOutput.writeInt(signCheck);

		if (signInfo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(signInfo);
		}

		if (formScript == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(formScript);
		}

		if (formReport == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(formReport);
		}

		if (formSchema == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(formSchema);
		}

		if (deliverableCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(deliverableCode);
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
	public int dossierPartType;
	public String fileTemplateNo;
	public String displayName;
	public String formData;
	public long fileEntryId;
	public boolean original;
	public boolean eForm;
	public boolean isNew;
	public boolean removed;
	public int signCheck;
	public String signInfo;
	public String formScript;
	public String formReport;
	public String formSchema;
	public String deliverableCode;
}