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

import org.opencps.dossiermgt.model.AccessToken;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing AccessToken in entity cache.
 *
 * @author huymq
 * @see AccessToken
 * @generated
 */
@ProviderType
public class AccessTokenCacheModel implements CacheModel<AccessToken>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AccessTokenCacheModel)) {
			return false;
		}

		AccessTokenCacheModel accessTokenCacheModel = (AccessTokenCacheModel)obj;

		if (accessTokenId == accessTokenCacheModel.accessTokenId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, accessTokenId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{accessTokenId=");
		sb.append(accessTokenId);
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
		sb.append(", token=");
		sb.append(token);
		sb.append(", expireDate=");
		sb.append(expireDate);
		sb.append(", className=");
		sb.append(className);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public AccessToken toEntityModel() {
		AccessTokenImpl accessTokenImpl = new AccessTokenImpl();

		accessTokenImpl.setAccessTokenId(accessTokenId);
		accessTokenImpl.setGroupId(groupId);
		accessTokenImpl.setCompanyId(companyId);
		accessTokenImpl.setUserId(userId);

		if (userName == null) {
			accessTokenImpl.setUserName("");
		}
		else {
			accessTokenImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			accessTokenImpl.setCreateDate(null);
		}
		else {
			accessTokenImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			accessTokenImpl.setModifiedDate(null);
		}
		else {
			accessTokenImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (token == null) {
			accessTokenImpl.setToken("");
		}
		else {
			accessTokenImpl.setToken(token);
		}

		if (expireDate == Long.MIN_VALUE) {
			accessTokenImpl.setExpireDate(null);
		}
		else {
			accessTokenImpl.setExpireDate(new Date(expireDate));
		}

		if (className == null) {
			accessTokenImpl.setClassName("");
		}
		else {
			accessTokenImpl.setClassName(className);
		}

		accessTokenImpl.resetOriginalValues();

		return accessTokenImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		accessTokenId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		token = objectInput.readUTF();
		expireDate = objectInput.readLong();
		className = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(accessTokenId);

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

		if (token == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(token);
		}

		objectOutput.writeLong(expireDate);

		if (className == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(className);
		}
	}

	public long accessTokenId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String token;
	public long expireDate;
	public String className;
}