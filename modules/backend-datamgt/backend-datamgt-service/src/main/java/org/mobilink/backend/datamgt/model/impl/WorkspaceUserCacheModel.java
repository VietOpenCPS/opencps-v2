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

package org.mobilink.backend.datamgt.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import org.mobilink.backend.datamgt.model.WorkspaceUser;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WorkspaceUser in entity cache.
 *
 * @author Binhth
 * @see WorkspaceUser
 * @generated
 */
@ProviderType
public class WorkspaceUserCacheModel implements CacheModel<WorkspaceUser>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WorkspaceUserCacheModel)) {
			return false;
		}

		WorkspaceUserCacheModel workspaceUserCacheModel = (WorkspaceUserCacheModel)obj;

		if (workspaceUserId == workspaceUserCacheModel.workspaceUserId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, workspaceUserId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", workspaceUserId=");
		sb.append(workspaceUserId);
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
		sb.append(", assignUserId=");
		sb.append(assignUserId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public WorkspaceUser toEntityModel() {
		WorkspaceUserImpl workspaceUserImpl = new WorkspaceUserImpl();

		if (uuid == null) {
			workspaceUserImpl.setUuid(StringPool.BLANK);
		}
		else {
			workspaceUserImpl.setUuid(uuid);
		}

		workspaceUserImpl.setWorkspaceUserId(workspaceUserId);
		workspaceUserImpl.setCompanyId(companyId);
		workspaceUserImpl.setGroupId(groupId);
		workspaceUserImpl.setUserId(userId);

		if (userName == null) {
			workspaceUserImpl.setUserName(StringPool.BLANK);
		}
		else {
			workspaceUserImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			workspaceUserImpl.setCreateDate(null);
		}
		else {
			workspaceUserImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			workspaceUserImpl.setModifiedDate(null);
		}
		else {
			workspaceUserImpl.setModifiedDate(new Date(modifiedDate));
		}

		workspaceUserImpl.setWorkspaceId(workspaceId);
		workspaceUserImpl.setAssignUserId(assignUserId);

		workspaceUserImpl.resetOriginalValues();

		return workspaceUserImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		workspaceUserId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		workspaceId = objectInput.readLong();

		assignUserId = objectInput.readLong();
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

		objectOutput.writeLong(workspaceUserId);

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

		objectOutput.writeLong(assignUserId);
	}

	public String uuid;
	public long workspaceUserId;
	public long companyId;
	public long groupId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long workspaceId;
	public long assignUserId;
}