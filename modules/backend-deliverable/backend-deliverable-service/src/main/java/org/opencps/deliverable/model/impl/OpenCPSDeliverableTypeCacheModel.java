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

package org.opencps.deliverable.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import org.opencps.deliverable.model.OpenCPSDeliverableType;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing OpenCPSDeliverableType in entity cache.
 *
 * @author binhth
 * @see OpenCPSDeliverableType
 * @generated
 */
@ProviderType
public class OpenCPSDeliverableTypeCacheModel implements CacheModel<OpenCPSDeliverableType>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OpenCPSDeliverableTypeCacheModel)) {
			return false;
		}

		OpenCPSDeliverableTypeCacheModel openCPSDeliverableTypeCacheModel = (OpenCPSDeliverableTypeCacheModel)obj;

		if (deliverableTypeId == openCPSDeliverableTypeCacheModel.deliverableTypeId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, deliverableTypeId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(39);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", deliverableTypeId=");
		sb.append(deliverableTypeId);
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
		sb.append(", typeCode=");
		sb.append(typeCode);
		sb.append(", typeName=");
		sb.append(typeName);
		sb.append(", formScriptFileId=");
		sb.append(formScriptFileId);
		sb.append(", formReportFileId=");
		sb.append(formReportFileId);
		sb.append(", codePattern=");
		sb.append(codePattern);
		sb.append(", counter=");
		sb.append(counter);
		sb.append(", mappingData=");
		sb.append(mappingData);
		sb.append(", dataConfig=");
		sb.append(dataConfig);
		sb.append(", tableConfig=");
		sb.append(tableConfig);
		sb.append(", docSync=");
		sb.append(docSync);
		sb.append(", govAgencies=");
		sb.append(govAgencies);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public OpenCPSDeliverableType toEntityModel() {
		OpenCPSDeliverableTypeImpl openCPSDeliverableTypeImpl = new OpenCPSDeliverableTypeImpl();

		if (uuid == null) {
			openCPSDeliverableTypeImpl.setUuid("");
		}
		else {
			openCPSDeliverableTypeImpl.setUuid(uuid);
		}

		openCPSDeliverableTypeImpl.setDeliverableTypeId(deliverableTypeId);
		openCPSDeliverableTypeImpl.setGroupId(groupId);
		openCPSDeliverableTypeImpl.setCompanyId(companyId);
		openCPSDeliverableTypeImpl.setUserId(userId);

		if (userName == null) {
			openCPSDeliverableTypeImpl.setUserName("");
		}
		else {
			openCPSDeliverableTypeImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			openCPSDeliverableTypeImpl.setCreateDate(null);
		}
		else {
			openCPSDeliverableTypeImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			openCPSDeliverableTypeImpl.setModifiedDate(null);
		}
		else {
			openCPSDeliverableTypeImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (typeCode == null) {
			openCPSDeliverableTypeImpl.setTypeCode("");
		}
		else {
			openCPSDeliverableTypeImpl.setTypeCode(typeCode);
		}

		if (typeName == null) {
			openCPSDeliverableTypeImpl.setTypeName("");
		}
		else {
			openCPSDeliverableTypeImpl.setTypeName(typeName);
		}

		openCPSDeliverableTypeImpl.setFormScriptFileId(formScriptFileId);
		openCPSDeliverableTypeImpl.setFormReportFileId(formReportFileId);

		if (codePattern == null) {
			openCPSDeliverableTypeImpl.setCodePattern("");
		}
		else {
			openCPSDeliverableTypeImpl.setCodePattern(codePattern);
		}

		openCPSDeliverableTypeImpl.setCounter(counter);

		if (mappingData == null) {
			openCPSDeliverableTypeImpl.setMappingData("");
		}
		else {
			openCPSDeliverableTypeImpl.setMappingData(mappingData);
		}

		if (dataConfig == null) {
			openCPSDeliverableTypeImpl.setDataConfig("");
		}
		else {
			openCPSDeliverableTypeImpl.setDataConfig(dataConfig);
		}

		if (tableConfig == null) {
			openCPSDeliverableTypeImpl.setTableConfig("");
		}
		else {
			openCPSDeliverableTypeImpl.setTableConfig(tableConfig);
		}

		openCPSDeliverableTypeImpl.setDocSync(docSync);

		if (govAgencies == null) {
			openCPSDeliverableTypeImpl.setGovAgencies("");
		}
		else {
			openCPSDeliverableTypeImpl.setGovAgencies(govAgencies);
		}

		openCPSDeliverableTypeImpl.resetOriginalValues();

		return openCPSDeliverableTypeImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		deliverableTypeId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		typeCode = objectInput.readUTF();
		typeName = objectInput.readUTF();

		formScriptFileId = objectInput.readLong();

		formReportFileId = objectInput.readLong();
		codePattern = objectInput.readUTF();

		counter = objectInput.readLong();
		mappingData = objectInput.readUTF();
		dataConfig = objectInput.readUTF();
		tableConfig = objectInput.readUTF();

		docSync = objectInput.readInt();
		govAgencies = objectInput.readUTF();
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

		objectOutput.writeLong(deliverableTypeId);

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

		if (typeCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(typeCode);
		}

		if (typeName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(typeName);
		}

		objectOutput.writeLong(formScriptFileId);

		objectOutput.writeLong(formReportFileId);

		if (codePattern == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(codePattern);
		}

		objectOutput.writeLong(counter);

		if (mappingData == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(mappingData);
		}

		if (dataConfig == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(dataConfig);
		}

		if (tableConfig == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(tableConfig);
		}

		objectOutput.writeInt(docSync);

		if (govAgencies == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(govAgencies);
		}
	}

	public String uuid;
	public long deliverableTypeId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String typeCode;
	public String typeName;
	public long formScriptFileId;
	public long formReportFileId;
	public String codePattern;
	public long counter;
	public String mappingData;
	public String dataConfig;
	public String tableConfig;
	public int docSync;
	public String govAgencies;
}