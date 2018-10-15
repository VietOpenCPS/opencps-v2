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

import org.opencps.synchronization.model.DictGroupTemp;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing DictGroupTemp in entity cache.
 *
 * @author trungdk
 * @see DictGroupTemp
 * @generated
 */
@ProviderType
public class DictGroupTempCacheModel implements CacheModel<DictGroupTemp>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DictGroupTempCacheModel)) {
			return false;
		}

		DictGroupTempCacheModel dictGroupTempCacheModel = (DictGroupTempCacheModel)obj;

		if (dictGroupId == dictGroupTempCacheModel.dictGroupId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, dictGroupId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", dictGroupId=");
		sb.append(dictGroupId);
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
		sb.append(", dictCollectionId=");
		sb.append(dictCollectionId);
		sb.append(", groupCode=");
		sb.append(groupCode);
		sb.append(", groupName=");
		sb.append(groupName);
		sb.append(", groupNameEN=");
		sb.append(groupNameEN);
		sb.append(", groupDescription=");
		sb.append(groupDescription);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DictGroupTemp toEntityModel() {
		DictGroupTempImpl dictGroupTempImpl = new DictGroupTempImpl();

		if (uuid == null) {
			dictGroupTempImpl.setUuid("");
		}
		else {
			dictGroupTempImpl.setUuid(uuid);
		}

		dictGroupTempImpl.setDictGroupId(dictGroupId);
		dictGroupTempImpl.setCompanyId(companyId);
		dictGroupTempImpl.setGroupId(groupId);
		dictGroupTempImpl.setUserId(userId);

		if (userName == null) {
			dictGroupTempImpl.setUserName("");
		}
		else {
			dictGroupTempImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			dictGroupTempImpl.setCreateDate(null);
		}
		else {
			dictGroupTempImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			dictGroupTempImpl.setModifiedDate(null);
		}
		else {
			dictGroupTempImpl.setModifiedDate(new Date(modifiedDate));
		}

		dictGroupTempImpl.setDictCollectionId(dictCollectionId);

		if (groupCode == null) {
			dictGroupTempImpl.setGroupCode("");
		}
		else {
			dictGroupTempImpl.setGroupCode(groupCode);
		}

		if (groupName == null) {
			dictGroupTempImpl.setGroupName("");
		}
		else {
			dictGroupTempImpl.setGroupName(groupName);
		}

		if (groupNameEN == null) {
			dictGroupTempImpl.setGroupNameEN("");
		}
		else {
			dictGroupTempImpl.setGroupNameEN(groupNameEN);
		}

		if (groupDescription == null) {
			dictGroupTempImpl.setGroupDescription("");
		}
		else {
			dictGroupTempImpl.setGroupDescription(groupDescription);
		}

		dictGroupTempImpl.setStatus(status);

		dictGroupTempImpl.resetOriginalValues();

		return dictGroupTempImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		dictGroupId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		dictCollectionId = objectInput.readLong();
		groupCode = objectInput.readUTF();
		groupName = objectInput.readUTF();
		groupNameEN = objectInput.readUTF();
		groupDescription = objectInput.readUTF();

		status = objectInput.readInt();
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

		objectOutput.writeLong(dictGroupId);

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

		objectOutput.writeLong(dictCollectionId);

		if (groupCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(groupCode);
		}

		if (groupName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(groupName);
		}

		if (groupNameEN == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(groupNameEN);
		}

		if (groupDescription == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(groupDescription);
		}

		objectOutput.writeInt(status);
	}

	public String uuid;
	public long dictGroupId;
	public long companyId;
	public long groupId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long dictCollectionId;
	public String groupCode;
	public String groupName;
	public String groupNameEN;
	public String groupDescription;
	public int status;
}