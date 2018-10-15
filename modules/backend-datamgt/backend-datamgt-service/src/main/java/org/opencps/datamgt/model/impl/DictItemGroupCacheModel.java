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

import org.opencps.datamgt.model.DictItemGroup;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing DictItemGroup in entity cache.
 *
 * @author khoavu
 * @see DictItemGroup
 * @generated
 */
@ProviderType
public class DictItemGroupCacheModel implements CacheModel<DictItemGroup>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DictItemGroupCacheModel)) {
			return false;
		}

		DictItemGroupCacheModel dictItemGroupCacheModel = (DictItemGroupCacheModel)obj;

		if (dictItemGroupId == dictItemGroupCacheModel.dictItemGroupId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, dictItemGroupId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", dictItemGroupId=");
		sb.append(dictItemGroupId);
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
		sb.append(", dictGroupId=");
		sb.append(dictGroupId);
		sb.append(", dictItemId=");
		sb.append(dictItemId);
		sb.append(", dictGroupName=");
		sb.append(dictGroupName);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DictItemGroup toEntityModel() {
		DictItemGroupImpl dictItemGroupImpl = new DictItemGroupImpl();

		if (uuid == null) {
			dictItemGroupImpl.setUuid("");
		}
		else {
			dictItemGroupImpl.setUuid(uuid);
		}

		dictItemGroupImpl.setDictItemGroupId(dictItemGroupId);
		dictItemGroupImpl.setCompanyId(companyId);
		dictItemGroupImpl.setGroupId(groupId);
		dictItemGroupImpl.setUserId(userId);

		if (userName == null) {
			dictItemGroupImpl.setUserName("");
		}
		else {
			dictItemGroupImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			dictItemGroupImpl.setCreateDate(null);
		}
		else {
			dictItemGroupImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			dictItemGroupImpl.setModifiedDate(null);
		}
		else {
			dictItemGroupImpl.setModifiedDate(new Date(modifiedDate));
		}

		dictItemGroupImpl.setDictGroupId(dictGroupId);
		dictItemGroupImpl.setDictItemId(dictItemId);

		if (dictGroupName == null) {
			dictItemGroupImpl.setDictGroupName("");
		}
		else {
			dictItemGroupImpl.setDictGroupName(dictGroupName);
		}

		dictItemGroupImpl.resetOriginalValues();

		return dictItemGroupImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		dictItemGroupId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		dictGroupId = objectInput.readLong();

		dictItemId = objectInput.readLong();
		dictGroupName = objectInput.readUTF();
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

		objectOutput.writeLong(dictItemGroupId);

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

		objectOutput.writeLong(dictGroupId);

		objectOutput.writeLong(dictItemId);

		if (dictGroupName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(dictGroupName);
		}
	}

	public String uuid;
	public long dictItemGroupId;
	public long companyId;
	public long groupId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long dictGroupId;
	public long dictItemId;
	public String dictGroupName;
}