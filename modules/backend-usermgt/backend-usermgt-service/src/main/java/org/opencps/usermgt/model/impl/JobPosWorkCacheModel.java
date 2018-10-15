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

import org.opencps.usermgt.model.JobPosWork;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing JobPosWork in entity cache.
 *
 * @author khoavu
 * @see JobPosWork
 * @generated
 */
@ProviderType
public class JobPosWorkCacheModel implements CacheModel<JobPosWork>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof JobPosWorkCacheModel)) {
			return false;
		}

		JobPosWorkCacheModel jobPosWorkCacheModel = (JobPosWorkCacheModel)obj;

		if (jobPosWorkId == jobPosWorkCacheModel.jobPosWorkId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, jobPosWorkId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", jobPosWorkId=");
		sb.append(jobPosWorkId);
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
		sb.append(", jobPostId=");
		sb.append(jobPostId);
		sb.append(", checklistCat=");
		sb.append(checklistCat);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public JobPosWork toEntityModel() {
		JobPosWorkImpl jobPosWorkImpl = new JobPosWorkImpl();

		if (uuid == null) {
			jobPosWorkImpl.setUuid("");
		}
		else {
			jobPosWorkImpl.setUuid(uuid);
		}

		jobPosWorkImpl.setJobPosWorkId(jobPosWorkId);
		jobPosWorkImpl.setCompanyId(companyId);
		jobPosWorkImpl.setGroupId(groupId);
		jobPosWorkImpl.setUserId(userId);

		if (userName == null) {
			jobPosWorkImpl.setUserName("");
		}
		else {
			jobPosWorkImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			jobPosWorkImpl.setCreateDate(null);
		}
		else {
			jobPosWorkImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			jobPosWorkImpl.setModifiedDate(null);
		}
		else {
			jobPosWorkImpl.setModifiedDate(new Date(modifiedDate));
		}

		jobPosWorkImpl.setJobPostId(jobPostId);

		if (checklistCat == null) {
			jobPosWorkImpl.setChecklistCat("");
		}
		else {
			jobPosWorkImpl.setChecklistCat(checklistCat);
		}

		jobPosWorkImpl.resetOriginalValues();

		return jobPosWorkImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		jobPosWorkId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		jobPostId = objectInput.readLong();
		checklistCat = objectInput.readUTF();
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

		objectOutput.writeLong(jobPosWorkId);

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

		objectOutput.writeLong(jobPostId);

		if (checklistCat == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(checklistCat);
		}
	}

	public String uuid;
	public long jobPosWorkId;
	public long companyId;
	public long groupId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long jobPostId;
	public String checklistCat;
}