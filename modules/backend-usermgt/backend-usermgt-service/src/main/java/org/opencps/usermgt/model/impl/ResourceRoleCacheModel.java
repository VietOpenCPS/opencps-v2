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

import org.opencps.usermgt.model.ResourceRole;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ResourceRole in entity cache.
 *
 * @author khoavu
 * @see ResourceRole
 * @generated
 */
@ProviderType
public class ResourceRoleCacheModel implements CacheModel<ResourceRole>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ResourceRoleCacheModel)) {
			return false;
		}

		ResourceRoleCacheModel resourceRoleCacheModel = (ResourceRoleCacheModel)obj;

		if (resourceRoleId == resourceRoleCacheModel.resourceRoleId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, resourceRoleId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", resourceRoleId=");
		sb.append(resourceRoleId);
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
		sb.append(", roleId=");
		sb.append(roleId);
		sb.append(", readonly=");
		sb.append(readonly);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ResourceRole toEntityModel() {
		ResourceRoleImpl resourceRoleImpl = new ResourceRoleImpl();

		if (uuid == null) {
			resourceRoleImpl.setUuid("");
		}
		else {
			resourceRoleImpl.setUuid(uuid);
		}

		resourceRoleImpl.setResourceRoleId(resourceRoleId);
		resourceRoleImpl.setGroupId(groupId);
		resourceRoleImpl.setCompanyId(companyId);
		resourceRoleImpl.setUserId(userId);

		if (userName == null) {
			resourceRoleImpl.setUserName("");
		}
		else {
			resourceRoleImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			resourceRoleImpl.setCreateDate(null);
		}
		else {
			resourceRoleImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			resourceRoleImpl.setModifiedDate(null);
		}
		else {
			resourceRoleImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (className == null) {
			resourceRoleImpl.setClassName("");
		}
		else {
			resourceRoleImpl.setClassName(className);
		}

		if (classPK == null) {
			resourceRoleImpl.setClassPK("");
		}
		else {
			resourceRoleImpl.setClassPK(classPK);
		}

		resourceRoleImpl.setRoleId(roleId);
		resourceRoleImpl.setReadonly(readonly);

		resourceRoleImpl.resetOriginalValues();

		return resourceRoleImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		resourceRoleId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		className = objectInput.readUTF();
		classPK = objectInput.readUTF();

		roleId = objectInput.readLong();

		readonly = objectInput.readInt();
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

		objectOutput.writeLong(resourceRoleId);

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

		objectOutput.writeLong(roleId);

		objectOutput.writeInt(readonly);
	}

	public String uuid;
	public long resourceRoleId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String className;
	public String classPK;
	public long roleId;
	public int readonly;
}