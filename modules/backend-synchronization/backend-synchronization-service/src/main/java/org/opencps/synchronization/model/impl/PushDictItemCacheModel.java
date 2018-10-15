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

import org.opencps.synchronization.model.PushDictItem;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing PushDictItem in entity cache.
 *
 * @author trungdk
 * @see PushDictItem
 * @generated
 */
@ProviderType
public class PushDictItemCacheModel implements CacheModel<PushDictItem>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PushDictItemCacheModel)) {
			return false;
		}

		PushDictItemCacheModel pushDictItemCacheModel = (PushDictItemCacheModel)obj;

		if (pushDictItemId == pushDictItemCacheModel.pushDictItemId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, pushDictItemId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(37);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", pushDictItemId=");
		sb.append(pushDictItemId);
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
		sb.append(", serverNo=");
		sb.append(serverNo);
		sb.append(", collectionCode=");
		sb.append(collectionCode);
		sb.append(", itemCode=");
		sb.append(itemCode);
		sb.append(", itemName=");
		sb.append(itemName);
		sb.append(", itemNameEN=");
		sb.append(itemNameEN);
		sb.append(", itemDescription=");
		sb.append(itemDescription);
		sb.append(", parentItemCode=");
		sb.append(parentItemCode);
		sb.append(", sibling=");
		sb.append(sibling);
		sb.append(", method=");
		sb.append(method);
		sb.append(", metaData=");
		sb.append(metaData);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public PushDictItem toEntityModel() {
		PushDictItemImpl pushDictItemImpl = new PushDictItemImpl();

		if (uuid == null) {
			pushDictItemImpl.setUuid("");
		}
		else {
			pushDictItemImpl.setUuid(uuid);
		}

		pushDictItemImpl.setPushDictItemId(pushDictItemId);
		pushDictItemImpl.setGroupId(groupId);
		pushDictItemImpl.setCompanyId(companyId);
		pushDictItemImpl.setUserId(userId);

		if (userName == null) {
			pushDictItemImpl.setUserName("");
		}
		else {
			pushDictItemImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			pushDictItemImpl.setCreateDate(null);
		}
		else {
			pushDictItemImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			pushDictItemImpl.setModifiedDate(null);
		}
		else {
			pushDictItemImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (serverNo == null) {
			pushDictItemImpl.setServerNo("");
		}
		else {
			pushDictItemImpl.setServerNo(serverNo);
		}

		if (collectionCode == null) {
			pushDictItemImpl.setCollectionCode("");
		}
		else {
			pushDictItemImpl.setCollectionCode(collectionCode);
		}

		if (itemCode == null) {
			pushDictItemImpl.setItemCode("");
		}
		else {
			pushDictItemImpl.setItemCode(itemCode);
		}

		if (itemName == null) {
			pushDictItemImpl.setItemName("");
		}
		else {
			pushDictItemImpl.setItemName(itemName);
		}

		if (itemNameEN == null) {
			pushDictItemImpl.setItemNameEN("");
		}
		else {
			pushDictItemImpl.setItemNameEN(itemNameEN);
		}

		if (itemDescription == null) {
			pushDictItemImpl.setItemDescription("");
		}
		else {
			pushDictItemImpl.setItemDescription(itemDescription);
		}

		if (parentItemCode == null) {
			pushDictItemImpl.setParentItemCode("");
		}
		else {
			pushDictItemImpl.setParentItemCode(parentItemCode);
		}

		if (sibling == null) {
			pushDictItemImpl.setSibling("");
		}
		else {
			pushDictItemImpl.setSibling(sibling);
		}

		if (method == null) {
			pushDictItemImpl.setMethod("");
		}
		else {
			pushDictItemImpl.setMethod(method);
		}

		if (metaData == null) {
			pushDictItemImpl.setMetaData("");
		}
		else {
			pushDictItemImpl.setMetaData(metaData);
		}

		pushDictItemImpl.resetOriginalValues();

		return pushDictItemImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		pushDictItemId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		serverNo = objectInput.readUTF();
		collectionCode = objectInput.readUTF();
		itemCode = objectInput.readUTF();
		itemName = objectInput.readUTF();
		itemNameEN = objectInput.readUTF();
		itemDescription = objectInput.readUTF();
		parentItemCode = objectInput.readUTF();
		sibling = objectInput.readUTF();
		method = objectInput.readUTF();
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

		objectOutput.writeLong(pushDictItemId);

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

		if (serverNo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(serverNo);
		}

		if (collectionCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(collectionCode);
		}

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

		if (parentItemCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(parentItemCode);
		}

		if (sibling == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(sibling);
		}

		if (method == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(method);
		}

		if (metaData == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(metaData);
		}
	}

	public String uuid;
	public long pushDictItemId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String serverNo;
	public String collectionCode;
	public String itemCode;
	public String itemName;
	public String itemNameEN;
	public String itemDescription;
	public String parentItemCode;
	public String sibling;
	public String method;
	public String metaData;
}