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

package org.opencps.usermgt.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import org.opencps.usermgt.model.UserLogin;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing UserLogin in entity cache.
 *
 * @author khoavu
 * @see UserLogin
 * @generated
 */
@ProviderType
public class UserLoginCacheModel implements CacheModel<UserLogin>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof UserLoginCacheModel)) {
			return false;
		}

		UserLoginCacheModel userLoginCacheModel = (UserLoginCacheModel)obj;

		if (userLoginId == userLoginCacheModel.userLoginId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, userLoginId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", userLoginId=");
		sb.append(userLoginId);
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
		sb.append(", sessionId=");
		sb.append(sessionId);
		sb.append(", hits=");
		sb.append(hits);
		sb.append(", logout=");
		sb.append(logout);
		sb.append(", ipAddress=");
		sb.append(ipAddress);
		sb.append(", online=");
		sb.append(online);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public UserLogin toEntityModel() {
		UserLoginImpl userLoginImpl = new UserLoginImpl();

		if (uuid == null) {
			userLoginImpl.setUuid("");
		}
		else {
			userLoginImpl.setUuid(uuid);
		}

		userLoginImpl.setUserLoginId(userLoginId);
		userLoginImpl.setGroupId(groupId);
		userLoginImpl.setCompanyId(companyId);
		userLoginImpl.setUserId(userId);

		if (userName == null) {
			userLoginImpl.setUserName("");
		}
		else {
			userLoginImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			userLoginImpl.setCreateDate(null);
		}
		else {
			userLoginImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			userLoginImpl.setModifiedDate(null);
		}
		else {
			userLoginImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (sessionId == null) {
			userLoginImpl.setSessionId("");
		}
		else {
			userLoginImpl.setSessionId(sessionId);
		}

		userLoginImpl.setHits(hits);

		if (logout == Long.MIN_VALUE) {
			userLoginImpl.setLogout(null);
		}
		else {
			userLoginImpl.setLogout(new Date(logout));
		}

		if (ipAddress == null) {
			userLoginImpl.setIpAddress("");
		}
		else {
			userLoginImpl.setIpAddress(ipAddress);
		}

		userLoginImpl.setOnline(online);

		userLoginImpl.resetOriginalValues();

		return userLoginImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		userLoginId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		sessionId = objectInput.readUTF();

		hits = objectInput.readInt();
		logout = objectInput.readLong();
		ipAddress = objectInput.readUTF();

		online = objectInput.readBoolean();
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

		objectOutput.writeLong(userLoginId);

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

		if (sessionId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(sessionId);
		}

		objectOutput.writeInt(hits);
		objectOutput.writeLong(logout);

		if (ipAddress == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(ipAddress);
		}

		objectOutput.writeBoolean(online);
	}

	public String uuid;
	public long userLoginId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String sessionId;
	public int hits;
	public long logout;
	public String ipAddress;
	public boolean online;
}