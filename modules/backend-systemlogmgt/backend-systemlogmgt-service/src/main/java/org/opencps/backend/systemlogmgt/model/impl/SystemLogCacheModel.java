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

package org.opencps.backend.systemlogmgt.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.petra.string.StringBundler;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;

import org.opencps.backend.systemlogmgt.model.SystemLog;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing SystemLog in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SystemLog
 * @generated
 */
@ProviderType
public class SystemLogCacheModel implements CacheModel<SystemLog>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SystemLogCacheModel)) {
			return false;
		}

		SystemLogCacheModel systemLogCacheModel = (SystemLogCacheModel)obj;

		if (logId == systemLogCacheModel.logId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, logId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", logId=");
		sb.append(logId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", moduleName=");
		sb.append(moduleName);
		sb.append(", preLine=");
		sb.append(preLine);
		sb.append(", preMethod=");
		sb.append(preMethod);
		sb.append(", line=");
		sb.append(line);
		sb.append(", method=");
		sb.append(method);
		sb.append(", message=");
		sb.append(message);
		sb.append(", type=");
		sb.append(type);
		sb.append(", threadId=");
		sb.append(threadId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SystemLog toEntityModel() {
		SystemLogImpl systemLogImpl = new SystemLogImpl();

		if (uuid == null) {
			systemLogImpl.setUuid("");
		}
		else {
			systemLogImpl.setUuid(uuid);
		}

		systemLogImpl.setLogId(logId);
		systemLogImpl.setGroupId(groupId);

		if (createDate == Long.MIN_VALUE) {
			systemLogImpl.setCreateDate(null);
		}
		else {
			systemLogImpl.setCreateDate(new Date(createDate));
		}

		if (moduleName == null) {
			systemLogImpl.setModuleName("");
		}
		else {
			systemLogImpl.setModuleName(moduleName);
		}

		systemLogImpl.setPreLine(preLine);

		if (preMethod == null) {
			systemLogImpl.setPreMethod("");
		}
		else {
			systemLogImpl.setPreMethod(preMethod);
		}

		systemLogImpl.setLine(line);

		if (method == null) {
			systemLogImpl.setMethod("");
		}
		else {
			systemLogImpl.setMethod(method);
		}

		if (message == null) {
			systemLogImpl.setMessage("");
		}
		else {
			systemLogImpl.setMessage(message);
		}

		if (type == null) {
			systemLogImpl.setType("");
		}
		else {
			systemLogImpl.setType(type);
		}

		if (threadId == null) {
			systemLogImpl.setThreadId("");
		}
		else {
			systemLogImpl.setThreadId(threadId);
		}

		systemLogImpl.resetOriginalValues();

		return systemLogImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		logId = objectInput.readLong();

		groupId = objectInput.readLong();
		createDate = objectInput.readLong();
		moduleName = objectInput.readUTF();

		preLine = objectInput.readInt();
		preMethod = objectInput.readUTF();

		line = objectInput.readInt();
		method = objectInput.readUTF();
		message = objectInput.readUTF();
		type = objectInput.readUTF();
		threadId = objectInput.readUTF();
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

		objectOutput.writeLong(logId);

		objectOutput.writeLong(groupId);
		objectOutput.writeLong(createDate);

		if (moduleName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(moduleName);
		}

		objectOutput.writeInt(preLine);

		if (preMethod == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(preMethod);
		}

		objectOutput.writeInt(line);

		if (method == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(method);
		}

		if (message == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(message);
		}

		if (type == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(type);
		}

		if (threadId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(threadId);
		}
	}

	public String uuid;
	public long logId;
	public long groupId;
	public long createDate;
	public String moduleName;
	public int preLine;
	public String preMethod;
	public int line;
	public String method;
	public String message;
	public String type;
	public String threadId;
}