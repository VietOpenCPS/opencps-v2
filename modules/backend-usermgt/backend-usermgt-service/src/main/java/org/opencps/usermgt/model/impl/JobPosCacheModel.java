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

import org.opencps.usermgt.model.JobPos;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing JobPos in entity cache.
 *
 * @author khoavu
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
		StringBundler sb = new StringBundler(27);

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
		sb.append(", jobPosCode=");
		sb.append(jobPosCode);
		sb.append(", title=");
		sb.append(title);
		sb.append(", description=");
		sb.append(description);
		sb.append(", mappingRoleId=");
		sb.append(mappingRoleId);
		sb.append(", leader=");
		sb.append(leader);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public JobPos toEntityModel() {
		JobPosImpl jobPosImpl = new JobPosImpl();

		if (uuid == null) {
			jobPosImpl.setUuid("");
		}
		else {
			jobPosImpl.setUuid(uuid);
		}

		jobPosImpl.setJobPosId(jobPosId);
		jobPosImpl.setCompanyId(companyId);
		jobPosImpl.setGroupId(groupId);
		jobPosImpl.setUserId(userId);

		if (userName == null) {
			jobPosImpl.setUserName("");
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

		if (jobPosCode == null) {
			jobPosImpl.setJobPosCode("");
		}
		else {
			jobPosImpl.setJobPosCode(jobPosCode);
		}

		if (title == null) {
			jobPosImpl.setTitle("");
		}
		else {
			jobPosImpl.setTitle(title);
		}

		if (description == null) {
			jobPosImpl.setDescription("");
		}
		else {
			jobPosImpl.setDescription(description);
		}

		jobPosImpl.setMappingRoleId(mappingRoleId);
		jobPosImpl.setLeader(leader);

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
		jobPosCode = objectInput.readUTF();
		title = objectInput.readUTF();
		description = objectInput.readUTF();

		mappingRoleId = objectInput.readLong();

		leader = objectInput.readInt();
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

		objectOutput.writeLong(jobPosId);

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

		if (jobPosCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(jobPosCode);
		}

		if (title == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeLong(mappingRoleId);

		objectOutput.writeInt(leader);
	}

	public String uuid;
	public long jobPosId;
	public long companyId;
	public long groupId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String jobPosCode;
	public String title;
	public String description;
	public long mappingRoleId;
	public int leader;
}