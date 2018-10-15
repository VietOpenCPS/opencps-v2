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

import org.opencps.dossiermgt.model.UserInfoLog;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing UserInfoLog in entity cache.
 *
 * @author huymq
 * @see UserInfoLog
 * @generated
 */
@ProviderType
public class UserInfoLogCacheModel implements CacheModel<UserInfoLog>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof UserInfoLogCacheModel)) {
			return false;
		}

		UserInfoLogCacheModel userInfoLogCacheModel = (UserInfoLogCacheModel)obj;

		if (userLogId == userInfoLogCacheModel.userLogId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, userLogId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", userLogId=");
		sb.append(userLogId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", payload=");
		sb.append(payload);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public UserInfoLog toEntityModel() {
		UserInfoLogImpl userInfoLogImpl = new UserInfoLogImpl();

		if (uuid == null) {
			userInfoLogImpl.setUuid("");
		}
		else {
			userInfoLogImpl.setUuid(uuid);
		}

		userInfoLogImpl.setUserLogId(userLogId);
		userInfoLogImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			userInfoLogImpl.setCreateDate(null);
		}
		else {
			userInfoLogImpl.setCreateDate(new Date(createDate));
		}

		if (payload == null) {
			userInfoLogImpl.setPayload("");
		}
		else {
			userInfoLogImpl.setPayload(payload);
		}

		userInfoLogImpl.resetOriginalValues();

		return userInfoLogImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		userLogId = objectInput.readLong();

		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		payload = objectInput.readUTF();
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

		objectOutput.writeLong(userLogId);

		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);

		if (payload == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(payload);
		}
	}

	public String uuid;
	public long userLogId;
	public long userId;
	public long createDate;
	public String payload;
}