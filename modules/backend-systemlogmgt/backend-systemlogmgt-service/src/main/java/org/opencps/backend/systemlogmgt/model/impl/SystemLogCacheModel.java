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

import com.liferay.portal.kernel.util.StringBundler;

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
		StringBundler sb = new StringBundler(17);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", logId=");
		sb.append(logId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", className=");
		sb.append(className);
		sb.append(", message=");
		sb.append(message);
		sb.append(", type=");
		sb.append(type);
		sb.append(", line=");
		sb.append(line);
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

		if (className == null) {
			systemLogImpl.setClassName("");
		}
		else {
			systemLogImpl.setClassName(className);
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

		systemLogImpl.setLine(line);

		systemLogImpl.resetOriginalValues();

		return systemLogImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		logId = objectInput.readLong();

		groupId = objectInput.readLong();
		createDate = objectInput.readLong();
		className = objectInput.readUTF();
		message = objectInput.readUTF();
		type = objectInput.readUTF();

		line = objectInput.readInt();
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

		if (className == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(className);
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

		objectOutput.writeInt(line);
	}

	public String uuid;
	public long logId;
	public long groupId;
	public long createDate;
	public String className;
	public String message;
	public String type;
	public int line;
}