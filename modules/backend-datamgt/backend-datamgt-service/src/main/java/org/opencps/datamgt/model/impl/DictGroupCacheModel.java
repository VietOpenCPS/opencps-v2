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
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

import org.opencps.datamgt.model.DictGroup;

/**
 * The cache model class for representing DictGroup in entity cache.
 *
 * @author Binhth
 * @see DictGroup
 * @generated
 */
@ProviderType
public class DictGroupCacheModel implements CacheModel<DictGroup>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DictGroupCacheModel)) {
			return false;
		}

		DictGroupCacheModel dictGroupCacheModel = (DictGroupCacheModel)obj;

		if (dictGroupId == dictGroupCacheModel.dictGroupId) {
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
		StringBundler sb = new StringBundler(27);

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
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DictGroup toEntityModel() {
		DictGroupImpl dictGroupImpl = new DictGroupImpl();

		if (uuid == null) {
			dictGroupImpl.setUuid(StringPool.BLANK);
		}
		else {
			dictGroupImpl.setUuid(uuid);
		}

		dictGroupImpl.setDictGroupId(dictGroupId);
		dictGroupImpl.setCompanyId(companyId);
		dictGroupImpl.setGroupId(groupId);
		dictGroupImpl.setUserId(userId);

		if (userName == null) {
			dictGroupImpl.setUserName(StringPool.BLANK);
		}
		else {
			dictGroupImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			dictGroupImpl.setCreateDate(null);
		}
		else {
			dictGroupImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			dictGroupImpl.setModifiedDate(null);
		}
		else {
			dictGroupImpl.setModifiedDate(new Date(modifiedDate));
		}

		dictGroupImpl.setDictCollectionId(dictCollectionId);

		if (groupCode == null) {
			dictGroupImpl.setGroupCode(StringPool.BLANK);
		}
		else {
			dictGroupImpl.setGroupCode(groupCode);
		}

		if (groupName == null) {
			dictGroupImpl.setGroupName(StringPool.BLANK);
		}
		else {
			dictGroupImpl.setGroupName(groupName);
		}

		if (groupNameEN == null) {
			dictGroupImpl.setGroupNameEN(StringPool.BLANK);
		}
		else {
			dictGroupImpl.setGroupNameEN(groupNameEN);
		}

		if (groupDescription == null) {
			dictGroupImpl.setGroupDescription(StringPool.BLANK);
		}
		else {
			dictGroupImpl.setGroupDescription(groupDescription);
		}

		dictGroupImpl.resetOriginalValues();

		return dictGroupImpl;
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

		objectOutput.writeLong(dictGroupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(dictCollectionId);

		if (groupCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(groupCode);
		}

		if (groupName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(groupName);
		}

		if (groupNameEN == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(groupNameEN);
		}

		if (groupDescription == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(groupDescription);
		}
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
}