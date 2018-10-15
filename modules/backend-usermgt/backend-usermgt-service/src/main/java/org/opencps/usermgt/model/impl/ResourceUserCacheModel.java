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

import org.opencps.usermgt.model.ResourceUser;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ResourceUser in entity cache.
 *
 * @author khoavu
 * @see ResourceUser
 * @generated
 */
@ProviderType
public class ResourceUserCacheModel implements CacheModel<ResourceUser>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ResourceUserCacheModel)) {
			return false;
		}

		ResourceUserCacheModel resourceUserCacheModel = (ResourceUserCacheModel)obj;

		if (resourceUserId == resourceUserCacheModel.resourceUserId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, resourceUserId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", resourceUserId=");
		sb.append(resourceUserId);
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
		sb.append(", className=");
		sb.append(className);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", toUserId=");
		sb.append(toUserId);
		sb.append(", fullname=");
		sb.append(fullname);
		sb.append(", email=");
		sb.append(email);
		sb.append(", readonly=");
		sb.append(readonly);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ResourceUser toEntityModel() {
		ResourceUserImpl resourceUserImpl = new ResourceUserImpl();

		if (uuid == null) {
			resourceUserImpl.setUuid("");
		}
		else {
			resourceUserImpl.setUuid(uuid);
		}

		resourceUserImpl.setResourceUserId(resourceUserId);
		resourceUserImpl.setGroupId(groupId);
		resourceUserImpl.setCompanyId(companyId);
		resourceUserImpl.setUserId(userId);

		if (userName == null) {
			resourceUserImpl.setUserName("");
		}
		else {
			resourceUserImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			resourceUserImpl.setCreateDate(null);
		}
		else {
			resourceUserImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			resourceUserImpl.setModifiedDate(null);
		}
		else {
			resourceUserImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (className == null) {
			resourceUserImpl.setClassName("");
		}
		else {
			resourceUserImpl.setClassName(className);
		}

		if (classPK == null) {
			resourceUserImpl.setClassPK("");
		}
		else {
			resourceUserImpl.setClassPK(classPK);
		}

		resourceUserImpl.setToUserId(toUserId);

		if (fullname == null) {
			resourceUserImpl.setFullname("");
		}
		else {
			resourceUserImpl.setFullname(fullname);
		}

		if (email == null) {
			resourceUserImpl.setEmail("");
		}
		else {
			resourceUserImpl.setEmail(email);
		}

		resourceUserImpl.setReadonly(readonly);

		resourceUserImpl.resetOriginalValues();

		return resourceUserImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		resourceUserId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		className = objectInput.readUTF();
		classPK = objectInput.readUTF();

		toUserId = objectInput.readLong();
		fullname = objectInput.readUTF();
		email = objectInput.readUTF();

		readonly = objectInput.readBoolean();
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

		objectOutput.writeLong(resourceUserId);

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

		if (className == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(className);
		}

		if (classPK == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(classPK);
		}

		objectOutput.writeLong(toUserId);

		if (fullname == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(fullname);
		}

		if (email == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(email);
		}

		objectOutput.writeBoolean(readonly);
	}

	public String uuid;
	public long resourceUserId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String className;
	public String classPK;
	public long toUserId;
	public String fullname;
	public String email;
	public boolean readonly;
}