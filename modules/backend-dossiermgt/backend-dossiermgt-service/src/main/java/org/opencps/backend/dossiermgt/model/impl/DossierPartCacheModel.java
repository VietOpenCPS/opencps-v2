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

import org.opencps.backend.dossiermgt.model.DossierPart;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing DossierPart in entity cache.
 *
 * @author huymq
 * @see DossierPart
 * @generated
 */
@ProviderType
public class DossierPartCacheModel implements CacheModel<DossierPart>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DossierPartCacheModel)) {
			return false;
		}

		DossierPartCacheModel dossierPartCacheModel = (DossierPartCacheModel)obj;

		if (dossierPartId == dossierPartCacheModel.dossierPartId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, dossierPartId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(41);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", dossierPartId=");
		sb.append(dossierPartId);
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
		sb.append(", templateNo=");
		sb.append(templateNo);
		sb.append(", partNo=");
		sb.append(partNo);
		sb.append(", partName=");
		sb.append(partName);
		sb.append(", partTip=");
		sb.append(partTip);
		sb.append(", partType=");
		sb.append(partType);
		sb.append(", multiple=");
		sb.append(multiple);
		sb.append(", formScript=");
		sb.append(formScript);
		sb.append(", formReport=");
		sb.append(formReport);
		sb.append(", sampleData=");
		sb.append(sampleData);
		sb.append(", required=");
		sb.append(required);
		sb.append(", fileTemplateNo=");
		sb.append(fileTemplateNo);
		sb.append(", eSign=");
		sb.append(eSign);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DossierPart toEntityModel() {
		DossierPartImpl dossierPartImpl = new DossierPartImpl();

		if (uuid == null) {
			dossierPartImpl.setUuid(StringPool.BLANK);
		}
		else {
			dossierPartImpl.setUuid(uuid);
		}

		dossierPartImpl.setDossierPartId(dossierPartId);
		dossierPartImpl.setGroupId(groupId);
		dossierPartImpl.setCompanyId(companyId);
		dossierPartImpl.setUserId(userId);

		if (userName == null) {
			dossierPartImpl.setUserName(StringPool.BLANK);
		}
		else {
			dossierPartImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			dossierPartImpl.setCreateDate(null);
		}
		else {
			dossierPartImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			dossierPartImpl.setModifiedDate(null);
		}
		else {
			dossierPartImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (templateNo == null) {
			dossierPartImpl.setTemplateNo(StringPool.BLANK);
		}
		else {
			dossierPartImpl.setTemplateNo(templateNo);
		}

		if (partNo == null) {
			dossierPartImpl.setPartNo(StringPool.BLANK);
		}
		else {
			dossierPartImpl.setPartNo(partNo);
		}

		if (partName == null) {
			dossierPartImpl.setPartName(StringPool.BLANK);
		}
		else {
			dossierPartImpl.setPartName(partName);
		}

		if (partTip == null) {
			dossierPartImpl.setPartTip(StringPool.BLANK);
		}
		else {
			dossierPartImpl.setPartTip(partTip);
		}

		dossierPartImpl.setPartType(partType);
		dossierPartImpl.setMultiple(multiple);

		if (formScript == null) {
			dossierPartImpl.setFormScript(StringPool.BLANK);
		}
		else {
			dossierPartImpl.setFormScript(formScript);
		}

		if (formReport == null) {
			dossierPartImpl.setFormReport(StringPool.BLANK);
		}
		else {
			dossierPartImpl.setFormReport(formReport);
		}

		if (sampleData == null) {
			dossierPartImpl.setSampleData(StringPool.BLANK);
		}
		else {
			dossierPartImpl.setSampleData(sampleData);
		}

		dossierPartImpl.setRequired(required);

		if (fileTemplateNo == null) {
			dossierPartImpl.setFileTemplateNo(StringPool.BLANK);
		}
		else {
			dossierPartImpl.setFileTemplateNo(fileTemplateNo);
		}

		dossierPartImpl.setESign(eSign);

		dossierPartImpl.resetOriginalValues();

		return dossierPartImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		dossierPartId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		templateNo = objectInput.readUTF();
		partNo = objectInput.readUTF();
		partName = objectInput.readUTF();
		partTip = objectInput.readUTF();

		partType = objectInput.readInt();

		multiple = objectInput.readBoolean();
		formScript = objectInput.readUTF();
		formReport = objectInput.readUTF();
		sampleData = objectInput.readUTF();

		required = objectInput.readBoolean();
		fileTemplateNo = objectInput.readUTF();

		eSign = objectInput.readBoolean();
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

		objectOutput.writeLong(dossierPartId);

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

		if (templateNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(templateNo);
		}

		if (partNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(partNo);
		}

		if (partName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(partName);
		}

		if (partTip == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(partTip);
		}

		objectOutput.writeInt(partType);

		objectOutput.writeBoolean(multiple);

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

		if (sampleData == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(sampleData);
		}

		objectOutput.writeBoolean(required);

		if (fileTemplateNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(fileTemplateNo);
		}

		objectOutput.writeBoolean(eSign);
	}

	public String uuid;
	public long dossierPartId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String templateNo;
	public String partNo;
	public String partName;
	public String partTip;
	public int partType;
	public boolean multiple;
	public String formScript;
	public String formReport;
	public String sampleData;
	public boolean required;
	public String fileTemplateNo;
	public boolean eSign;
}