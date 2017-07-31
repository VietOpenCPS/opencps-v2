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

import org.mobilink.backend.usermgt.model.JobPos;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing JobPos in entity cache.
 *
 * @author Binhth
 * @see JobPos
 * @generated
 */
@ProviderType
public class JobPosCacheModel implements CacheModel<JobPos>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof JobPosCacheModel)) {
			return false;
		}

		JobPosCacheModel jobPosCacheModel = (JobPosCacheModel)obj;

		if (jobPosId == jobPosCacheModel.jobPosId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, jobPosId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", jobPosId=");
		sb.append(jobPosId);
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
		sb.append(", workingUnitId=");
		sb.append(workingUnitId);
		sb.append(", title=");
		sb.append(title);
		sb.append(", description=");
		sb.append(description);
		sb.append(", directWorkingUnitId=");
		sb.append(directWorkingUnitId);
		sb.append(", leader=");
		sb.append(leader);
		sb.append(", mappingRoleId=");
		sb.append(mappingRoleId);
		sb.append(", hiddenJobPos=");
		sb.append(hiddenJobPos);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public JobPos toEntityModel() {
		JobPosImpl jobPosImpl = new JobPosImpl();

		if (uuid == null) {
			jobPosImpl.setUuid(StringPool.BLANK);
		}
		else {
			jobPosImpl.setUuid(uuid);
		}

		jobPosImpl.setJobPosId(jobPosId);
		jobPosImpl.setCompanyId(companyId);
		jobPosImpl.setGroupId(groupId);
		jobPosImpl.setUserId(userId);

		if (userName == null) {
			jobPosImpl.setUserName(StringPool.BLANK);
		}
		else {
			jobPosImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			jobPosImpl.setCreateDate(null);
		}
		else {
			jobPosImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			jobPosImpl.setModifiedDate(null);
		}
		else {
			jobPosImpl.setModifiedDate(new Date(modifiedDate));
		}

		jobPosImpl.setWorkingUnitId(workingUnitId);

		if (title == null) {
			jobPosImpl.setTitle(StringPool.BLANK);
		}
		else {
			jobPosImpl.setTitle(title);
		}

		if (description == null) {
			jobPosImpl.setDescription(StringPool.BLANK);
		}
		else {
			jobPosImpl.setDescription(description);
		}

		jobPosImpl.setDirectWorkingUnitId(directWorkingUnitId);
		jobPosImpl.setLeader(leader);
		jobPosImpl.setMappingRoleId(mappingRoleId);
		jobPosImpl.setHiddenJobPos(hiddenJobPos);

		jobPosImpl.resetOriginalValues();

		return jobPosImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		jobPosId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		workingUnitId = objectInput.readLong();
		title = objectInput.readUTF();
		description = objectInput.readUTF();

		directWorkingUnitId = objectInput.readLong();

		leader = objectInput.readInt();

		mappingRoleId = objectInput.readLong();

		hiddenJobPos = objectInput.readBoolean();
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

		objectOutput.writeLong(jobPosId);

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

		objectOutput.writeLong(workingUnitId);

		if (title == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeLong(directWorkingUnitId);

		objectOutput.writeInt(leader);

		objectOutput.writeLong(mappingRoleId);

		objectOutput.writeBoolean(hiddenJobPos);
	}

	public String uuid;
	public long jobPosId;
	public long companyId;
	public long groupId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long workingUnitId;
	public String title;
	public String description;
	public long directWorkingUnitId;
	public int leader;
	public long mappingRoleId;
	public boolean hiddenJobPos;
}