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

package org.opencps.synchronization.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import org.opencps.synchronization.model.DictCollectionTemp;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing DictCollectionTemp in entity cache.
 *
 * @author trungdk
 * @see DictCollectionTemp
 * @generated
 */
@ProviderType
public class DictCollectionTempCacheModel implements CacheModel<DictCollectionTemp>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DictCollectionTempCacheModel)) {
			return false;
		}

		DictCollectionTempCacheModel dictCollectionTempCacheModel = (DictCollectionTempCacheModel)obj;

		if (dictCollectionId == dictCollectionTempCacheModel.dictCollectionId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, dictCollectionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", dictCollectionId=");
		sb.append(dictCollectionId);
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
		sb.append(", collectionCode=");
		sb.append(collectionCode);
		sb.append(", collectionName=");
		sb.append(collectionName);
		sb.append(", collectionNameEN=");
		sb.append(collectionNameEN);
		sb.append(", description=");
		sb.append(description);
		sb.append(", dataForm=");
		sb.append(dataForm);
		sb.append(", status=");
		sb.append(status);
		sb.append(", mustSync=");
		sb.append(mustSync);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DictCollectionTemp toEntityModel() {
		DictCollectionTempImpl dictCollectionTempImpl = new DictCollectionTempImpl();

		if (uuid == null) {
			dictCollectionTempImpl.setUuid("");
		}
		else {
			dictCollectionTempImpl.setUuid(uuid);
		}

		dictCollectionTempImpl.setDictCollectionId(dictCollectionId);
		dictCollectionTempImpl.setCompanyId(companyId);
		dictCollectionTempImpl.setGroupId(groupId);
		dictCollectionTempImpl.setUserId(userId);

		if (userName == null) {
			dictCollectionTempImpl.setUserName("");
		}
		else {
			dictCollectionTempImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			dictCollectionTempImpl.setCreateDate(null);
		}
		else {
			dictCollectionTempImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			dictCollectionTempImpl.setModifiedDate(null);
		}
		else {
			dictCollectionTempImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (collectionCode == null) {
			dictCollectionTempImpl.setCollectionCode("");
		}
		else {
			dictCollectionTempImpl.setCollectionCode(collectionCode);
		}

		if (collectionName == null) {
			dictCollectionTempImpl.setCollectionName("");
		}
		else {
			dictCollectionTempImpl.setCollectionName(collectionName);
		}

		if (collectionNameEN == null) {
			dictCollectionTempImpl.setCollectionNameEN("");
		}
		else {
			dictCollectionTempImpl.setCollectionNameEN(collectionNameEN);
		}

		if (description == null) {
			dictCollectionTempImpl.setDescription("");
		}
		else {
			dictCollectionTempImpl.setDescription(description);
		}

		if (dataForm == null) {
			dictCollectionTempImpl.setDataForm("");
		}
		else {
			dictCollectionTempImpl.setDataForm(dataForm);
		}

		dictCollectionTempImpl.setStatus(status);
		dictCollectionTempImpl.setMustSync(mustSync);

		dictCollectionTempImpl.resetOriginalValues();

		return dictCollectionTempImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		dictCollectionId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		collectionCode = objectInput.readUTF();
		collectionName = objectInput.readUTF();
		collectionNameEN = objectInput.readUTF();
		description = objectInput.readUTF();
		dataForm = objectInput.readUTF();

		status = objectInput.readInt();

		mustSync = objectInput.readInt();
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

		objectOutput.writeLong(dictCollectionId);

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

		if (collectionCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(collectionCode);
		}

		if (collectionName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(collectionName);
		}

		if (collectionNameEN == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(collectionNameEN);
		}

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (dataForm == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(dataForm);
		}

		objectOutput.writeInt(status);

		objectOutput.writeInt(mustSync);
	}

	public String uuid;
	public long dictCollectionId;
	public long companyId;
	public long groupId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String collectionCode;
	public String collectionName;
	public String collectionNameEN;
	public String description;
	public String dataForm;
	public int status;
	public int mustSync;
}