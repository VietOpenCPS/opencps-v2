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

import org.opencps.datamgt.model.WorkTime;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WorkTime in entity cache.
 *
 * @author khoavu
 * @see WorkTime
 * @generated
 */
@ProviderType
public class WorkTimeCacheModel implements CacheModel<WorkTime>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WorkTimeCacheModel)) {
			return false;
		}

		WorkTimeCacheModel workTimeCacheModel = (WorkTimeCacheModel)obj;

		if (workTimeId == workTimeCacheModel.workTimeId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, workTimeId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", workTimeId=");
		sb.append(workTimeId);
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
		sb.append(", day=");
		sb.append(day);
		sb.append(", hours=");
		sb.append(hours);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public WorkTime toEntityModel() {
		WorkTimeImpl workTimeImpl = new WorkTimeImpl();

		if (uuid == null) {
			workTimeImpl.setUuid("");
		}
		else {
			workTimeImpl.setUuid(uuid);
		}

		workTimeImpl.setWorkTimeId(workTimeId);
		workTimeImpl.setCompanyId(companyId);
		workTimeImpl.setGroupId(groupId);
		workTimeImpl.setUserId(userId);

		if (userName == null) {
			workTimeImpl.setUserName("");
		}
		else {
			workTimeImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			workTimeImpl.setCreateDate(null);
		}
		else {
			workTimeImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			workTimeImpl.setModifiedDate(null);
		}
		else {
			workTimeImpl.setModifiedDate(new Date(modifiedDate));
		}

		workTimeImpl.setDay(day);

		if (hours == null) {
			workTimeImpl.setHours("");
		}
		else {
			workTimeImpl.setHours(hours);
		}

		workTimeImpl.resetOriginalValues();

		return workTimeImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		workTimeId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		day = objectInput.readInt();
		hours = objectInput.readUTF();
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

		objectOutput.writeLong(workTimeId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeInt(day);

		if (hours == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(hours);
		}
	}

	public String uuid;
	public long workTimeId;
	public long companyId;
	public long groupId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public int day;
	public String hours;
}