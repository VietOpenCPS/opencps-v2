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
import com.liferay.portal.kernel.util.StringPool;

import org.opencps.synchronization.model.PushDictGroup;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing PushDictGroup in entity cache.
 *
 * @author trungdk
 * @see PushDictGroup
 * @generated
 */
@ProviderType
public class PushDictGroupCacheModel implements CacheModel<PushDictGroup>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PushDictGroupCacheModel)) {
			return false;
		}

		PushDictGroupCacheModel pushDictGroupCacheModel = (PushDictGroupCacheModel)obj;

		if (pushDictGroupId == pushDictGroupCacheModel.pushDictGroupId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, pushDictGroupId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(33);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", pushDictGroupId=");
		sb.append(pushDictGroupId);
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
		sb.append(", groupCode=");
		sb.append(groupCode);
		sb.append(", groupName=");
		sb.append(groupName);
		sb.append(", groupNameEN=");
		sb.append(groupNameEN);
		sb.append(", groupDescription=");
		sb.append(groupDescription);
		sb.append(", itemCode=");
		sb.append(itemCode);
		sb.append(", method=");
		sb.append(method);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public PushDictGroup toEntityModel() {
		PushDictGroupImpl pushDictGroupImpl = new PushDictGroupImpl();

		if (uuid == null) {
			pushDictGroupImpl.setUuid(StringPool.BLANK);
		}
		else {
			pushDictGroupImpl.setUuid(uuid);
		}

		pushDictGroupImpl.setPushDictGroupId(pushDictGroupId);
		pushDictGroupImpl.setGroupId(groupId);
		pushDictGroupImpl.setCompanyId(companyId);
		pushDictGroupImpl.setUserId(userId);

		if (userName == null) {
			pushDictGroupImpl.setUserName(StringPool.BLANK);
		}
		else {
			pushDictGroupImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			pushDictGroupImpl.setCreateDate(null);
		}
		else {
			pushDictGroupImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			pushDictGroupImpl.setModifiedDate(null);
		}
		else {
			pushDictGroupImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (serverNo == null) {
			pushDictGroupImpl.setServerNo(StringPool.BLANK);
		}
		else {
			pushDictGroupImpl.setServerNo(serverNo);
		}

		if (collectionCode == null) {
			pushDictGroupImpl.setCollectionCode(StringPool.BLANK);
		}
		else {
			pushDictGroupImpl.setCollectionCode(collectionCode);
		}

		if (groupCode == null) {
			pushDictGroupImpl.setGroupCode(StringPool.BLANK);
		}
		else {
			pushDictGroupImpl.setGroupCode(groupCode);
		}

		if (groupName == null) {
			pushDictGroupImpl.setGroupName(StringPool.BLANK);
		}
		else {
			pushDictGroupImpl.setGroupName(groupName);
		}

		if (groupNameEN == null) {
			pushDictGroupImpl.setGroupNameEN(StringPool.BLANK);
		}
		else {
			pushDictGroupImpl.setGroupNameEN(groupNameEN);
		}

		if (groupDescription == null) {
			pushDictGroupImpl.setGroupDescription(StringPool.BLANK);
		}
		else {
			pushDictGroupImpl.setGroupDescription(groupDescription);
		}

		if (itemCode == null) {
			pushDictGroupImpl.setItemCode(StringPool.BLANK);
		}
		else {
			pushDictGroupImpl.setItemCode(itemCode);
		}

		if (method == null) {
			pushDictGroupImpl.setMethod(StringPool.BLANK);
		}
		else {
			pushDictGroupImpl.setMethod(method);
		}

		pushDictGroupImpl.resetOriginalValues();

		return pushDictGroupImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		pushDictGroupId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		serverNo = objectInput.readUTF();
		collectionCode = objectInput.readUTF();
		groupCode = objectInput.readUTF();
		groupName = objectInput.readUTF();
		groupNameEN = objectInput.readUTF();
		groupDescription = objectInput.readUTF();
		itemCode = objectInput.readUTF();
		method = objectInput.readUTF();
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

		objectOutput.writeLong(pushDictGroupId);

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

		if (serverNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(serverNo);
		}

		if (collectionCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(collectionCode);
		}

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

		if (itemCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemCode);
		}

		if (method == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(method);
		}
	}

	public String uuid;
	public long pushDictGroupId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String serverNo;
	public String collectionCode;
	public String groupCode;
	public String groupName;
	public String groupNameEN;
	public String groupDescription;
	public String itemCode;
	public String method;
}