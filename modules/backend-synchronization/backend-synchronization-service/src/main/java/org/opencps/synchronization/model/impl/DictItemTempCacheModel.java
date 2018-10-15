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

import org.opencps.synchronization.model.DictItemTemp;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing DictItemTemp in entity cache.
 *
 * @author trungdk
 * @see DictItemTemp
 * @generated
 */
@ProviderType
public class DictItemTempCacheModel implements CacheModel<DictItemTemp>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DictItemTempCacheModel)) {
			return false;
		}

		DictItemTempCacheModel dictItemTempCacheModel = (DictItemTempCacheModel)obj;

		if (dictItemId == dictItemTempCacheModel.dictItemId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, dictItemId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(39);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", dictItemId=");
		sb.append(dictItemId);
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
		sb.append(", itemCode=");
		sb.append(itemCode);
		sb.append(", itemName=");
		sb.append(itemName);
		sb.append(", itemNameEN=");
		sb.append(itemNameEN);
		sb.append(", itemDescription=");
		sb.append(itemDescription);
		sb.append(", parentItemId=");
		sb.append(parentItemId);
		sb.append(", level=");
		sb.append(level);
		sb.append(", sibling=");
		sb.append(sibling);
		sb.append(", treeIndex=");
		sb.append(treeIndex);
		sb.append(", metaData=");
		sb.append(metaData);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DictItemTemp toEntityModel() {
		DictItemTempImpl dictItemTempImpl = new DictItemTempImpl();

		if (uuid == null) {
			dictItemTempImpl.setUuid("");
		}
		else {
			dictItemTempImpl.setUuid(uuid);
		}

		dictItemTempImpl.setDictItemId(dictItemId);
		dictItemTempImpl.setCompanyId(companyId);
		dictItemTempImpl.setGroupId(groupId);
		dictItemTempImpl.setUserId(userId);

		if (userName == null) {
			dictItemTempImpl.setUserName("");
		}
		else {
			dictItemTempImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			dictItemTempImpl.setCreateDate(null);
		}
		else {
			dictItemTempImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			dictItemTempImpl.setModifiedDate(null);
		}
		else {
			dictItemTempImpl.setModifiedDate(new Date(modifiedDate));
		}

		dictItemTempImpl.setDictCollectionId(dictCollectionId);

		if (itemCode == null) {
			dictItemTempImpl.setItemCode("");
		}
		else {
			dictItemTempImpl.setItemCode(itemCode);
		}

		if (itemName == null) {
			dictItemTempImpl.setItemName("");
		}
		else {
			dictItemTempImpl.setItemName(itemName);
		}

		if (itemNameEN == null) {
			dictItemTempImpl.setItemNameEN("");
		}
		else {
			dictItemTempImpl.setItemNameEN(itemNameEN);
		}

		if (itemDescription == null) {
			dictItemTempImpl.setItemDescription("");
		}
		else {
			dictItemTempImpl.setItemDescription(itemDescription);
		}

		dictItemTempImpl.setParentItemId(parentItemId);
		dictItemTempImpl.setLevel(level);

		if (sibling == null) {
			dictItemTempImpl.setSibling("");
		}
		else {
			dictItemTempImpl.setSibling(sibling);
		}

		if (treeIndex == null) {
			dictItemTempImpl.setTreeIndex("");
		}
		else {
			dictItemTempImpl.setTreeIndex(treeIndex);
		}

		if (metaData == null) {
			dictItemTempImpl.setMetaData("");
		}
		else {
			dictItemTempImpl.setMetaData(metaData);
		}

		dictItemTempImpl.setStatus(status);

		dictItemTempImpl.resetOriginalValues();

		return dictItemTempImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		dictItemId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		dictCollectionId = objectInput.readLong();
		itemCode = objectInput.readUTF();
		itemName = objectInput.readUTF();
		itemNameEN = objectInput.readUTF();
		itemDescription = objectInput.readUTF();

		parentItemId = objectInput.readLong();

		level = objectInput.readInt();
		sibling = objectInput.readUTF();
		treeIndex = objectInput.readUTF();
		metaData = objectInput.readUTF();

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

		objectOutput.writeLong(dictItemId);

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

		if (itemCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(itemCode);
		}

		if (itemName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(itemName);
		}

		if (itemNameEN == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(itemNameEN);
		}

		if (itemDescription == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(itemDescription);
		}

		objectOutput.writeLong(parentItemId);

		objectOutput.writeInt(level);

		if (sibling == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(sibling);
		}

		if (treeIndex == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(treeIndex);
		}

		if (metaData == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(metaData);
		}

		objectOutput.writeInt(status);
	}

	public String uuid;
	public long dictItemId;
	public long companyId;
	public long groupId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long dictCollectionId;
	public String itemCode;
	public String itemName;
	public String itemNameEN;
	public String itemDescription;
	public long parentItemId;
	public int level;
	public String sibling;
	public String treeIndex;
	public String metaData;
	public int status;
}