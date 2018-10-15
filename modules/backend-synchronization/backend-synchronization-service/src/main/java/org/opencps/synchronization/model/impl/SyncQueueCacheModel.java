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

import org.opencps.synchronization.model.SyncQueue;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing SyncQueue in entity cache.
 *
 * @author trungdk
 * @see SyncQueue
 * @generated
 */
@ProviderType
public class SyncQueueCacheModel implements CacheModel<SyncQueue>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SyncQueueCacheModel)) {
			return false;
		}

		SyncQueueCacheModel syncQueueCacheModel = (SyncQueueCacheModel)obj;

		if (syncQueueId == syncQueueCacheModel.syncQueueId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, syncQueueId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", syncQueueId=");
		sb.append(syncQueueId);
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
		sb.append(", className=");
		sb.append(className);
		sb.append(", jsonObject=");
		sb.append(jsonObject);
		sb.append(", status=");
		sb.append(status);
		sb.append(", retryCount=");
		sb.append(retryCount);
		sb.append(", priority=");
		sb.append(priority);
		sb.append(", method=");
		sb.append(method);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SyncQueue toEntityModel() {
		SyncQueueImpl syncQueueImpl = new SyncQueueImpl();

		if (uuid == null) {
			syncQueueImpl.setUuid("");
		}
		else {
			syncQueueImpl.setUuid(uuid);
		}

		syncQueueImpl.setSyncQueueId(syncQueueId);
		syncQueueImpl.setGroupId(groupId);
		syncQueueImpl.setCompanyId(companyId);
		syncQueueImpl.setUserId(userId);

		if (userName == null) {
			syncQueueImpl.setUserName("");
		}
		else {
			syncQueueImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			syncQueueImpl.setCreateDate(null);
		}
		else {
			syncQueueImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			syncQueueImpl.setModifiedDate(null);
		}
		else {
			syncQueueImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (serverNo == null) {
			syncQueueImpl.setServerNo("");
		}
		else {
			syncQueueImpl.setServerNo(serverNo);
		}

		if (className == null) {
			syncQueueImpl.setClassName("");
		}
		else {
			syncQueueImpl.setClassName(className);
		}

		if (jsonObject == null) {
			syncQueueImpl.setJsonObject("");
		}
		else {
			syncQueueImpl.setJsonObject(jsonObject);
		}

		syncQueueImpl.setStatus(status);
		syncQueueImpl.setRetryCount(retryCount);
		syncQueueImpl.setPriority(priority);

		if (method == null) {
			syncQueueImpl.setMethod("");
		}
		else {
			syncQueueImpl.setMethod(method);
		}

		syncQueueImpl.resetOriginalValues();

		return syncQueueImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		syncQueueId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		serverNo = objectInput.readUTF();
		className = objectInput.readUTF();
		jsonObject = objectInput.readUTF();

		status = objectInput.readInt();

		retryCount = objectInput.readInt();

		priority = objectInput.readInt();
		method = objectInput.readUTF();
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

		objectOutput.writeLong(syncQueueId);

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

		if (className == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(className);
		}

		if (jsonObject == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(jsonObject);
		}

		objectOutput.writeInt(status);

		objectOutput.writeInt(retryCount);

		objectOutput.writeInt(priority);

		if (method == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(method);
		}
	}

	public String uuid;
	public long syncQueueId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String serverNo;
	public String className;
	public String jsonObject;
	public int status;
	public int retryCount;
	public int priority;
	public String method;
}