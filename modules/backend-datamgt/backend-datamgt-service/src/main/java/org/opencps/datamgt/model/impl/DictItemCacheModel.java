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

import org.opencps.datamgt.model.DictItem;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing DictItem in entity cache.
 *
 * @author khoavu
 * @see DictItem
 * @generated
 */
@ProviderType
public class DictItemCacheModel implements CacheModel<DictItem>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DictItemCacheModel)) {
			return false;
		}

		DictItemCacheModel dictItemCacheModel = (DictItemCacheModel)obj;

		if (dictItemId == dictItemCacheModel.dictItemId) {
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
		StringBundler sb = new StringBundler(37);

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
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DictItem toEntityModel() {
		DictItemImpl dictItemImpl = new DictItemImpl();

		if (uuid == null) {
			dictItemImpl.setUuid("");
		}
		else {
			dictItemImpl.setUuid(uuid);
		}

		dictItemImpl.setDictItemId(dictItemId);
		dictItemImpl.setCompanyId(companyId);
		dictItemImpl.setGroupId(groupId);
		dictItemImpl.setUserId(userId);

		if (userName == null) {
			dictItemImpl.setUserName("");
		}
		else {
			dictItemImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			dictItemImpl.setCreateDate(null);
		}
		else {
			dictItemImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			dictItemImpl.setModifiedDate(null);
		}
		else {
			dictItemImpl.setModifiedDate(new Date(modifiedDate));
		}

		dictItemImpl.setDictCollectionId(dictCollectionId);

		if (itemCode == null) {
			dictItemImpl.setItemCode("");
		}
		else {
			dictItemImpl.setItemCode(itemCode);
		}

		if (itemName == null) {
			dictItemImpl.setItemName("");
		}
		else {
			dictItemImpl.setItemName(itemName);
		}

		if (itemNameEN == null) {
			dictItemImpl.setItemNameEN("");
		}
		else {
			dictItemImpl.setItemNameEN(itemNameEN);
		}

		if (itemDescription == null) {
			dictItemImpl.setItemDescription("");
		}
		else {
			dictItemImpl.setItemDescription(itemDescription);
		}

		dictItemImpl.setParentItemId(parentItemId);
		dictItemImpl.setLevel(level);

		if (sibling == null) {
			dictItemImpl.setSibling("");
		}
		else {
			dictItemImpl.setSibling(sibling);
		}

		if (treeIndex == null) {
			dictItemImpl.setTreeIndex("");
		}
		else {
			dictItemImpl.setTreeIndex(treeIndex);
		}

		if (metaData == null) {
			dictItemImpl.setMetaData("");
		}
		else {
			dictItemImpl.setMetaData(metaData);
		}

		dictItemImpl.resetOriginalValues();

		return dictItemImpl;
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
}