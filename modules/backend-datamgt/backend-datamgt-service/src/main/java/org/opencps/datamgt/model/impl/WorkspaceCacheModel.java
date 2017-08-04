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

import org.opencps.datamgt.model.Workspace;

/**
 * The cache model class for representing Workspace in entity cache.
 *
 * @author Binhth
 * @see Workspace
 * @generated
 */
@ProviderType
public class WorkspaceCacheModel implements CacheModel<Workspace>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WorkspaceCacheModel)) {
			return false;
		}

		WorkspaceCacheModel workspaceCacheModel = (WorkspaceCacheModel)obj;

		if (workspaceId == workspaceCacheModel.workspaceId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, workspaceId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", workspaceId=");
		sb.append(workspaceId);
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
		sb.append(", name=");
		sb.append(name);
		sb.append(", seqOrder=");
		sb.append(seqOrder);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Workspace toEntityModel() {
		WorkspaceImpl workspaceImpl = new WorkspaceImpl();

		if (uuid == null) {
			workspaceImpl.setUuid(StringPool.BLANK);
		}
		else {
			workspaceImpl.setUuid(uuid);
		}

		workspaceImpl.setWorkspaceId(workspaceId);
		workspaceImpl.setCompanyId(companyId);
		workspaceImpl.setGroupId(groupId);
		workspaceImpl.setUserId(userId);

		if (userName == null) {
			workspaceImpl.setUserName(StringPool.BLANK);
		}
		else {
			workspaceImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			workspaceImpl.setCreateDate(null);
		}
		else {
			workspaceImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			workspaceImpl.setModifiedDate(null);
		}
		else {
			workspaceImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			workspaceImpl.setName(StringPool.BLANK);
		}
		else {
			workspaceImpl.setName(name);
		}

		workspaceImpl.setSeqOrder(seqOrder);

		workspaceImpl.resetOriginalValues();

		return workspaceImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		workspaceId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();

		seqOrder = objectInput.readInt();
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

		objectOutput.writeLong(workspaceId);

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

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		objectOutput.writeInt(seqOrder);
	}

	public String uuid;
	public long workspaceId;
	public long companyId;
	public long groupId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public int seqOrder;
}