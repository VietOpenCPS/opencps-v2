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

import org.opencps.synchronization.model.PushCollection;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing PushCollection in entity cache.
 *
 * @author trungdk
 * @see PushCollection
 * @generated
 */
@ProviderType
public class PushCollectionCacheModel implements CacheModel<PushCollection>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PushCollectionCacheModel)) {
			return false;
		}

		PushCollectionCacheModel pushCollectionCacheModel = (PushCollectionCacheModel)obj;

		if (pushCollectionId == pushCollectionCacheModel.pushCollectionId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, pushCollectionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", pushCollectionId=");
		sb.append(pushCollectionId);
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
		sb.append(", collectionName=");
		sb.append(collectionName);
		sb.append(", collectionNameEN=");
		sb.append(collectionNameEN);
		sb.append(", description=");
		sb.append(description);
		sb.append(", dataForm=");
		sb.append(dataForm);
		sb.append(", method=");
		sb.append(method);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public PushCollection toEntityModel() {
		PushCollectionImpl pushCollectionImpl = new PushCollectionImpl();

		if (uuid == null) {
			pushCollectionImpl.setUuid(StringPool.BLANK);
		}
		else {
			pushCollectionImpl.setUuid(uuid);
		}

		pushCollectionImpl.setPushCollectionId(pushCollectionId);
		pushCollectionImpl.setGroupId(groupId);
		pushCollectionImpl.setCompanyId(companyId);
		pushCollectionImpl.setUserId(userId);

		if (userName == null) {
			pushCollectionImpl.setUserName(StringPool.BLANK);
		}
		else {
			pushCollectionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			pushCollectionImpl.setCreateDate(null);
		}
		else {
			pushCollectionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			pushCollectionImpl.setModifiedDate(null);
		}
		else {
			pushCollectionImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (serverNo == null) {
			pushCollectionImpl.setServerNo(StringPool.BLANK);
		}
		else {
			pushCollectionImpl.setServerNo(serverNo);
		}

		if (collectionCode == null) {
			pushCollectionImpl.setCollectionCode(StringPool.BLANK);
		}
		else {
			pushCollectionImpl.setCollectionCode(collectionCode);
		}

		if (collectionName == null) {
			pushCollectionImpl.setCollectionName(StringPool.BLANK);
		}
		else {
			pushCollectionImpl.setCollectionName(collectionName);
		}

		if (collectionNameEN == null) {
			pushCollectionImpl.setCollectionNameEN(StringPool.BLANK);
		}
		else {
			pushCollectionImpl.setCollectionNameEN(collectionNameEN);
		}

		if (description == null) {
			pushCollectionImpl.setDescription(StringPool.BLANK);
		}
		else {
			pushCollectionImpl.setDescription(description);
		}

		if (dataForm == null) {
			pushCollectionImpl.setDataForm(StringPool.BLANK);
		}
		else {
			pushCollectionImpl.setDataForm(dataForm);
		}

		if (method == null) {
			pushCollectionImpl.setMethod(StringPool.BLANK);
		}
		else {
			pushCollectionImpl.setMethod(method);
		}

		pushCollectionImpl.resetOriginalValues();

		return pushCollectionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		pushCollectionId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		serverNo = objectInput.readUTF();
		collectionCode = objectInput.readUTF();
		collectionName = objectInput.readUTF();
		collectionNameEN = objectInput.readUTF();
		description = objectInput.readUTF();
		dataForm = objectInput.readUTF();
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

		objectOutput.writeLong(pushCollectionId);

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

		if (collectionName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(collectionName);
		}

		if (collectionNameEN == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(collectionNameEN);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (dataForm == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(dataForm);
		}

		if (method == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(method);
		}
	}

	public String uuid;
	public long pushCollectionId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String serverNo;
	public String collectionCode;
	public String collectionName;
	public String collectionNameEN;
	public String description;
	public String dataForm;
	public String method;
}