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

package org.opencps.adminconfig.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import org.opencps.adminconfig.model.ApiRole;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ApiRole in entity cache.
 *
 * @author binhth
 * @see ApiRole
 * @generated
 */
@ProviderType
public class ApiRoleCacheModel implements CacheModel<ApiRole>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ApiRoleCacheModel)) {
			return false;
		}

		ApiRoleCacheModel apiRoleCacheModel = (ApiRoleCacheModel)obj;

		if (apiRoleId == apiRoleCacheModel.apiRoleId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, apiRoleId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{apiRoleId=");
		sb.append(apiRoleId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", apiCode=");
		sb.append(apiCode);
		sb.append(", roleId=");
		sb.append(roleId);
		sb.append(", roleCode=");
		sb.append(roleCode);
		sb.append(", apiRoleStatus=");
		sb.append(apiRoleStatus);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ApiRole toEntityModel() {
		ApiRoleImpl apiRoleImpl = new ApiRoleImpl();

		apiRoleImpl.setApiRoleId(apiRoleId);
		apiRoleImpl.setGroupId(groupId);

		if (createDate == Long.MIN_VALUE) {
			apiRoleImpl.setCreateDate(null);
		}
		else {
			apiRoleImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			apiRoleImpl.setModifiedDate(null);
		}
		else {
			apiRoleImpl.setModifiedDate(new Date(modifiedDate));
		}

		apiRoleImpl.setUserId(userId);

		if (apiCode == null) {
			apiRoleImpl.setApiCode("");
		}
		else {
			apiRoleImpl.setApiCode(apiCode);
		}

		apiRoleImpl.setRoleId(roleId);

		if (roleCode == null) {
			apiRoleImpl.setRoleCode("");
		}
		else {
			apiRoleImpl.setRoleCode(roleCode);
		}

		apiRoleImpl.setApiRoleStatus(apiRoleStatus);

		apiRoleImpl.resetOriginalValues();

		return apiRoleImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		apiRoleId = objectInput.readLong();

		groupId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		userId = objectInput.readLong();
		apiCode = objectInput.readUTF();

		roleId = objectInput.readInt();
		roleCode = objectInput.readUTF();

		apiRoleStatus = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(apiRoleId);

		objectOutput.writeLong(groupId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(userId);

		if (apiCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(apiCode);
		}

		objectOutput.writeInt(roleId);

		if (roleCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(roleCode);
		}

		objectOutput.writeInt(apiRoleStatus);
	}

	public long apiRoleId;
	public long groupId;
	public long createDate;
	public long modifiedDate;
	public long userId;
	public String apiCode;
	public int roleId;
	public String roleCode;
	public int apiRoleStatus;
}