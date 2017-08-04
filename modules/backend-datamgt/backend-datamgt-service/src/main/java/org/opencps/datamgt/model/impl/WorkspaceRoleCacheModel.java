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

package org.opencps.datamgt.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

import org.opencps.datamgt.model.WorkspaceRole;

/**
 * The cache model class for representing WorkspaceRole in entity cache.
 *
 * @author Binhth
 * @see WorkspaceRole
 * @generated
 */
@ProviderType
public class WorkspaceRoleCacheModel implements CacheModel<WorkspaceRole>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WorkspaceRoleCacheModel)) {
			return false;
		}

		WorkspaceRoleCacheModel workspaceRoleCacheModel = (WorkspaceRoleCacheModel)obj;

		if (workspaceRoleId == workspaceRoleCacheModel.workspaceRoleId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, workspaceRoleId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", workspaceRoleId=");
		sb.append(workspaceRoleId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", workspaceId=");
		sb.append(workspaceId);
		sb.append(", roleId=");
		sb.append(roleId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public WorkspaceRole toEntityModel() {
		WorkspaceRoleImpl workspaceRoleImpl = new WorkspaceRoleImpl();

		if (uuid == null) {
			workspaceRoleImpl.setUuid(StringPool.BLANK);
		}
		else {
			workspaceRoleImpl.setUuid(uuid);
		}

		workspaceRoleImpl.setWorkspaceRoleId(workspaceRoleId);
		workspaceRoleImpl.setCompanyId(companyId);
		workspaceRoleImpl.setGroupId(groupId);
		workspaceRoleImpl.setUserId(userId);

		if (userName == null) {
			workspaceRoleImpl.setUserName(StringPool.BLANK);
		}
		else {
			workspaceRoleImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			workspaceRoleImpl.setCreateDate(null);
		}
		else {
			workspaceRoleImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			workspaceRoleImpl.setModifiedDate(null);
		}
		else {
			workspaceRoleImpl.setModifiedDate(new Date(modifiedDate));
		}

		workspaceRoleImpl.setWorkspaceId(workspaceId);
		workspaceRoleImpl.setRoleId(roleId);

		workspaceRoleImpl.resetOriginalValues();

		return workspaceRoleImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		workspaceRoleId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		workspaceId = objectInput.readLong();

		roleId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(workspaceRoleId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(workspaceId);

		objectOutput.writeLong(roleId);
	}

	public String uuid;
	public long workspaceRoleId;
	public long companyId;
	public long groupId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long workspaceId;
	public long roleId;
}