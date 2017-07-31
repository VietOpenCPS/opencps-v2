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

package org.mobilink.backend.usermgt.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import org.mobilink.backend.usermgt.model.JoinSiteStatus;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing JoinSiteStatus in entity cache.
 *
 * @author Binhth
 * @see JoinSiteStatus
 * @generated
 */
@ProviderType
public class JoinSiteStatusCacheModel implements CacheModel<JoinSiteStatus>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof JoinSiteStatusCacheModel)) {
			return false;
		}

		JoinSiteStatusCacheModel joinSiteStatusCacheModel = (JoinSiteStatusCacheModel)obj;

		if (JoinSiteStatusId == joinSiteStatusCacheModel.JoinSiteStatusId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, JoinSiteStatusId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", JoinSiteStatusId=");
		sb.append(JoinSiteStatusId);
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
		sb.append(", employeeId=");
		sb.append(employeeId);
		sb.append(", joinSiteGroupId=");
		sb.append(joinSiteGroupId);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public JoinSiteStatus toEntityModel() {
		JoinSiteStatusImpl joinSiteStatusImpl = new JoinSiteStatusImpl();

		if (uuid == null) {
			joinSiteStatusImpl.setUuid(StringPool.BLANK);
		}
		else {
			joinSiteStatusImpl.setUuid(uuid);
		}

		joinSiteStatusImpl.setJoinSiteStatusId(JoinSiteStatusId);
		joinSiteStatusImpl.setCompanyId(companyId);
		joinSiteStatusImpl.setGroupId(groupId);
		joinSiteStatusImpl.setUserId(userId);

		if (userName == null) {
			joinSiteStatusImpl.setUserName(StringPool.BLANK);
		}
		else {
			joinSiteStatusImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			joinSiteStatusImpl.setCreateDate(null);
		}
		else {
			joinSiteStatusImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			joinSiteStatusImpl.setModifiedDate(null);
		}
		else {
			joinSiteStatusImpl.setModifiedDate(new Date(modifiedDate));
		}

		joinSiteStatusImpl.setEmployeeId(employeeId);
		joinSiteStatusImpl.setJoinSiteGroupId(joinSiteGroupId);
		joinSiteStatusImpl.setStatus(status);

		joinSiteStatusImpl.resetOriginalValues();

		return joinSiteStatusImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		JoinSiteStatusId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		employeeId = objectInput.readLong();

		joinSiteGroupId = objectInput.readLong();

		status = objectInput.readInt();
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

		objectOutput.writeLong(JoinSiteStatusId);

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

		objectOutput.writeLong(employeeId);

		objectOutput.writeLong(joinSiteGroupId);

		objectOutput.writeInt(status);
	}

	public String uuid;
	public long JoinSiteStatusId;
	public long companyId;
	public long groupId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long employeeId;
	public long joinSiteGroupId;
	public int status;
}