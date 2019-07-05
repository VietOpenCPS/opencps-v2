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

package org.opencps.communication.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import org.opencps.communication.model.ZaloMap;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ZaloMap in entity cache.
 *
 * @author khoavd
 * @see ZaloMap
 * @generated
 */
@ProviderType
public class ZaloMapCacheModel implements CacheModel<ZaloMap>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ZaloMapCacheModel)) {
			return false;
		}

		ZaloMapCacheModel zaloMapCacheModel = (ZaloMapCacheModel)obj;

		if (zaloMapId == zaloMapCacheModel.zaloMapId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, zaloMapId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{zaloMapId=");
		sb.append(zaloMapId);
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
		sb.append(", uId=");
		sb.append(uId);
		sb.append(", telNo=");
		sb.append(telNo);
		sb.append(", zaloOAId=");
		sb.append(zaloOAId);
		sb.append(", isFollowed=");
		sb.append(isFollowed);
		sb.append(", payload=");
		sb.append(payload);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ZaloMap toEntityModel() {
		ZaloMapImpl zaloMapImpl = new ZaloMapImpl();

		zaloMapImpl.setZaloMapId(zaloMapId);
		zaloMapImpl.setGroupId(groupId);
		zaloMapImpl.setCompanyId(companyId);
		zaloMapImpl.setUserId(userId);

		if (userName == null) {
			zaloMapImpl.setUserName("");
		}
		else {
			zaloMapImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			zaloMapImpl.setCreateDate(null);
		}
		else {
			zaloMapImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			zaloMapImpl.setModifiedDate(null);
		}
		else {
			zaloMapImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (uId == null) {
			zaloMapImpl.setUId("");
		}
		else {
			zaloMapImpl.setUId(uId);
		}

		if (telNo == null) {
			zaloMapImpl.setTelNo("");
		}
		else {
			zaloMapImpl.setTelNo(telNo);
		}

		if (zaloOAId == null) {
			zaloMapImpl.setZaloOAId("");
		}
		else {
			zaloMapImpl.setZaloOAId(zaloOAId);
		}

		zaloMapImpl.setIsFollowed(isFollowed);

		if (payload == null) {
			zaloMapImpl.setPayload("");
		}
		else {
			zaloMapImpl.setPayload(payload);
		}

		zaloMapImpl.resetOriginalValues();

		return zaloMapImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		zaloMapId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		uId = objectInput.readUTF();
		telNo = objectInput.readUTF();
		zaloOAId = objectInput.readUTF();

		isFollowed = objectInput.readInt();
		payload = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(zaloMapId);

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

		if (uId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uId);
		}

		if (telNo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(telNo);
		}

		if (zaloOAId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(zaloOAId);
		}

		objectOutput.writeInt(isFollowed);

		if (payload == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(payload);
		}
	}

	public long zaloMapId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String uId;
	public String telNo;
	public String zaloOAId;
	public int isFollowed;
	public String payload;
}