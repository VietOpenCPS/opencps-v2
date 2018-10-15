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

import org.opencps.dossiermgt.model.DeliverableType;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing DeliverableType in entity cache.
 *
 * @author huymq
 * @see DeliverableType
 * @generated
 */
@ProviderType
public class DeliverableTypeCacheModel implements CacheModel<DeliverableType>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DeliverableTypeCacheModel)) {
			return false;
		}

		DeliverableTypeCacheModel deliverableTypeCacheModel = (DeliverableTypeCacheModel)obj;

		if (deliverableTypeId == deliverableTypeCacheModel.deliverableTypeId) {
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
		StringBundler sb = new StringBundler(35);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", deliverableTypeId=");
		sb.append(deliverableTypeId);
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
		sb.append(", typeCode=");
		sb.append(typeCode);
		sb.append(", typeName=");
		sb.append(typeName);
		sb.append(", formScript=");
		sb.append(formScript);
		sb.append(", formReport=");
		sb.append(formReport);
		sb.append(", codePattern=");
		sb.append(codePattern);
		sb.append(", counter=");
		sb.append(counter);
		sb.append(", mappingData=");
		sb.append(mappingData);
		sb.append(", docSync=");
		sb.append(docSync);
		sb.append(", govAgencies=");
		sb.append(govAgencies);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DeliverableType toEntityModel() {
		DeliverableTypeImpl deliverableTypeImpl = new DeliverableTypeImpl();

		if (uuid == null) {
			deliverableTypeImpl.setUuid("");
		}
		else {
			deliverableTypeImpl.setUuid(uuid);
		}

		deliverableTypeImpl.setDeliverableTypeId(deliverableTypeId);
		deliverableTypeImpl.setCompanyId(companyId);
		deliverableTypeImpl.setGroupId(groupId);
		deliverableTypeImpl.setUserId(userId);

		if (userName == null) {
			deliverableTypeImpl.setUserName("");
		}
		else {
			deliverableTypeImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			deliverableTypeImpl.setCreateDate(null);
		}
		else {
			deliverableTypeImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			deliverableTypeImpl.setModifiedDate(null);
		}
		else {
			deliverableTypeImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (typeCode == null) {
			deliverableTypeImpl.setTypeCode("");
		}
		else {
			deliverableTypeImpl.setTypeCode(typeCode);
		}

		if (typeName == null) {
			deliverableTypeImpl.setTypeName("");
		}
		else {
			deliverableTypeImpl.setTypeName(typeName);
		}

		if (formScript == null) {
			deliverableTypeImpl.setFormScript("");
		}
		else {
			deliverableTypeImpl.setFormScript(formScript);
		}

		if (formReport == null) {
			deliverableTypeImpl.setFormReport("");
		}
		else {
			deliverableTypeImpl.setFormReport(formReport);
		}

		if (codePattern == null) {
			deliverableTypeImpl.setCodePattern("");
		}
		else {
			deliverableTypeImpl.setCodePattern(codePattern);
		}

		if (counter == null) {
			deliverableTypeImpl.setCounter("");
		}
		else {
			deliverableTypeImpl.setCounter(counter);
		}

		if (mappingData == null) {
			deliverableTypeImpl.setMappingData("");
		}
		else {
			deliverableTypeImpl.setMappingData(mappingData);
		}

		deliverableTypeImpl.setDocSync(docSync);

		if (govAgencies == null) {
			deliverableTypeImpl.setGovAgencies("");
		}
		else {
			deliverableTypeImpl.setGovAgencies(govAgencies);
		}

		deliverableTypeImpl.resetOriginalValues();

		return deliverableTypeImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		deliverableTypeId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		typeCode = objectInput.readUTF();
		typeName = objectInput.readUTF();
		formScript = objectInput.readUTF();
		formReport = objectInput.readUTF();
		codePattern = objectInput.readUTF();
		counter = objectInput.readUTF();
		mappingData = objectInput.readUTF();

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

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(groupId);

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

		if (codePattern == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(codePattern);
		}

		if (counter == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(counter);
		}

		if (mappingData == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(mappingData);
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
	public long companyId;
	public long groupId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String typeCode;
	public String typeName;
	public String formScript;
	public String formReport;
	public String codePattern;
	public String counter;
	public String mappingData;
	public int docSync;
	public String govAgencies;
}