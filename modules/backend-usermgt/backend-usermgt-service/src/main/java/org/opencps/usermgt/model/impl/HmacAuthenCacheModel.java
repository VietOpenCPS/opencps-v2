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

import org.opencps.usermgt.model.HmacAuthen;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing HmacAuthen in entity cache.
 *
 * @author khoavu
 * @see HmacAuthen
 * @generated
 */
@ProviderType
public class HmacAuthenCacheModel implements CacheModel<HmacAuthen>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof HmacAuthenCacheModel)) {
			return false;
		}

		HmacAuthenCacheModel hmacAuthenCacheModel = (HmacAuthenCacheModel)obj;

		if (hmacAuthId == hmacAuthenCacheModel.hmacAuthId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, hmacAuthId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{hmacAuthId=");
		sb.append(hmacAuthId);
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
		sb.append(", secret=");
		sb.append(secret);
		sb.append(", permanent=");
		sb.append(permanent);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public HmacAuthen toEntityModel() {
		HmacAuthenImpl hmacAuthenImpl = new HmacAuthenImpl();

		hmacAuthenImpl.setHmacAuthId(hmacAuthId);
		hmacAuthenImpl.setGroupId(groupId);
		hmacAuthenImpl.setCompanyId(companyId);
		hmacAuthenImpl.setUserId(userId);

		if (userName == null) {
			hmacAuthenImpl.setUserName("");
		}
		else {
			hmacAuthenImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			hmacAuthenImpl.setCreateDate(null);
		}
		else {
			hmacAuthenImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			hmacAuthenImpl.setModifiedDate(null);
		}
		else {
			hmacAuthenImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (secret == null) {
			hmacAuthenImpl.setSecret("");
		}
		else {
			hmacAuthenImpl.setSecret(secret);
		}

		hmacAuthenImpl.setPermanent(permanent);

		hmacAuthenImpl.resetOriginalValues();

		return hmacAuthenImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		hmacAuthId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		secret = objectInput.readUTF();

		permanent = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(hmacAuthId);

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

		if (secret == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(secret);
		}

		objectOutput.writeBoolean(permanent);
	}

	public long hmacAuthId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String secret;
	public boolean permanent;
}