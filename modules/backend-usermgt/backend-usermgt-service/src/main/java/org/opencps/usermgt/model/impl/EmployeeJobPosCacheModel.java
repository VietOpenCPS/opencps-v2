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
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

import org.opencps.usermgt.model.EmployeeJobPos;

/**
 * The cache model class for representing EmployeeJobPos in entity cache.
 *
 * @author Binhth
 * @see EmployeeJobPos
 * @generated
 */
@ProviderType
public class EmployeeJobPosCacheModel implements CacheModel<EmployeeJobPos>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EmployeeJobPosCacheModel)) {
			return false;
		}

		EmployeeJobPosCacheModel employeeJobPosCacheModel = (EmployeeJobPosCacheModel)obj;

		if (employeeJobPosId == employeeJobPosCacheModel.employeeJobPosId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, employeeJobPosId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", employeeJobPosId=");
		sb.append(employeeJobPosId);
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
		sb.append(", jobPostId=");
		sb.append(jobPostId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public EmployeeJobPos toEntityModel() {
		EmployeeJobPosImpl employeeJobPosImpl = new EmployeeJobPosImpl();

		if (uuid == null) {
			employeeJobPosImpl.setUuid(StringPool.BLANK);
		}
		else {
			employeeJobPosImpl.setUuid(uuid);
		}

		employeeJobPosImpl.setEmployeeJobPosId(employeeJobPosId);
		employeeJobPosImpl.setCompanyId(companyId);
		employeeJobPosImpl.setGroupId(groupId);
		employeeJobPosImpl.setUserId(userId);

		if (userName == null) {
			employeeJobPosImpl.setUserName(StringPool.BLANK);
		}
		else {
			employeeJobPosImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			employeeJobPosImpl.setCreateDate(null);
		}
		else {
			employeeJobPosImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			employeeJobPosImpl.setModifiedDate(null);
		}
		else {
			employeeJobPosImpl.setModifiedDate(new Date(modifiedDate));
		}

		employeeJobPosImpl.setEmployeeId(employeeId);
		employeeJobPosImpl.setJobPostId(jobPostId);

		employeeJobPosImpl.resetOriginalValues();

		return employeeJobPosImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		employeeJobPosId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		employeeId = objectInput.readLong();

		jobPostId = objectInput.readLong();
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

		objectOutput.writeLong(employeeJobPosId);

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

		objectOutput.writeLong(jobPostId);
	}

	public String uuid;
	public long employeeJobPosId;
	public long companyId;
	public long groupId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long employeeId;
	public long jobPostId;
}