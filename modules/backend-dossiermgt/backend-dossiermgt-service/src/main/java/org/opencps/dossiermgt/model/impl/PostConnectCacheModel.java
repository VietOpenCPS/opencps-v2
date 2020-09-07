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

import org.opencps.dossiermgt.model.PostConnect;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing PostConnect in entity cache.
 *
 * @author huymq
 * @see PostConnect
 * @generated
 */
@ProviderType
public class PostConnectCacheModel implements CacheModel<PostConnect>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PostConnectCacheModel)) {
			return false;
		}

		PostConnectCacheModel postConnectCacheModel = (PostConnectCacheModel)obj;

		if (postConnectId == postConnectCacheModel.postConnectId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, postConnectId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(33);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", postConnectId=");
		sb.append(postConnectId);
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
		sb.append(", dossierId=");
		sb.append(dossierId);
		sb.append(", postService=");
		sb.append(postService);
		sb.append(", postType=");
		sb.append(postType);
		sb.append(", orderNumber=");
		sb.append(orderNumber);
		sb.append(", postStatus=");
		sb.append(postStatus);
		sb.append(", metadata=");
		sb.append(metadata);
		sb.append(", syncState=");
		sb.append(syncState);
		sb.append(", retry=");
		sb.append(retry);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public PostConnect toEntityModel() {
		PostConnectImpl postConnectImpl = new PostConnectImpl();

		if (uuid == null) {
			postConnectImpl.setUuid("");
		}
		else {
			postConnectImpl.setUuid(uuid);
		}

		postConnectImpl.setPostConnectId(postConnectId);
		postConnectImpl.setGroupId(groupId);
		postConnectImpl.setCompanyId(companyId);
		postConnectImpl.setUserId(userId);

		if (userName == null) {
			postConnectImpl.setUserName("");
		}
		else {
			postConnectImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			postConnectImpl.setCreateDate(null);
		}
		else {
			postConnectImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			postConnectImpl.setModifiedDate(null);
		}
		else {
			postConnectImpl.setModifiedDate(new Date(modifiedDate));
		}

		postConnectImpl.setDossierId(dossierId);
		postConnectImpl.setPostService(postService);
		postConnectImpl.setPostType(postType);

		if (orderNumber == null) {
			postConnectImpl.setOrderNumber("");
		}
		else {
			postConnectImpl.setOrderNumber(orderNumber);
		}

		postConnectImpl.setPostStatus(postStatus);

		if (metadata == null) {
			postConnectImpl.setMetadata("");
		}
		else {
			postConnectImpl.setMetadata(metadata);
		}

		postConnectImpl.setSyncState(syncState);
		postConnectImpl.setRetry(retry);

		postConnectImpl.resetOriginalValues();

		return postConnectImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		postConnectId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		dossierId = objectInput.readLong();

		postService = objectInput.readInt();

		postType = objectInput.readInt();
		orderNumber = objectInput.readUTF();

		postStatus = objectInput.readInt();
		metadata = objectInput.readUTF();

		syncState = objectInput.readInt();

		retry = objectInput.readInt();
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

		objectOutput.writeLong(postConnectId);

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

		objectOutput.writeLong(dossierId);

		objectOutput.writeInt(postService);

		objectOutput.writeInt(postType);

		if (orderNumber == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(orderNumber);
		}

		objectOutput.writeInt(postStatus);

		if (metadata == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(metadata);
		}

		objectOutput.writeInt(syncState);

		objectOutput.writeInt(retry);
	}

	public String uuid;
	public long postConnectId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long dossierId;
	public int postService;
	public int postType;
	public String orderNumber;
	public int postStatus;
	public String metadata;
	public int syncState;
	public int retry;
}