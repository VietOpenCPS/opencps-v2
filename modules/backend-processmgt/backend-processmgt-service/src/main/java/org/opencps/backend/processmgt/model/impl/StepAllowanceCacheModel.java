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

package org.opencps.backend.processmgt.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import org.opencps.backend.processmgt.model.StepAllowance;
import org.opencps.backend.processmgt.service.persistence.StepAllowancePK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing StepAllowance in entity cache.
 *
 * @author khoavu
 * @see StepAllowance
 * @generated
 */
@ProviderType
public class StepAllowanceCacheModel implements CacheModel<StepAllowance>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StepAllowanceCacheModel)) {
			return false;
		}

		StepAllowanceCacheModel stepAllowanceCacheModel = (StepAllowanceCacheModel)obj;

		if (stepAllowancePK.equals(stepAllowanceCacheModel.stepAllowancePK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, stepAllowancePK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", processStepId=");
		sb.append(processStepId);
		sb.append(", roleId=");
		sb.append(roleId);
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
		sb.append(", moderator=");
		sb.append(moderator);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public StepAllowance toEntityModel() {
		StepAllowanceImpl stepAllowanceImpl = new StepAllowanceImpl();

		if (uuid == null) {
			stepAllowanceImpl.setUuid(StringPool.BLANK);
		}
		else {
			stepAllowanceImpl.setUuid(uuid);
		}

		stepAllowanceImpl.setProcessStepId(processStepId);
		stepAllowanceImpl.setRoleId(roleId);
		stepAllowanceImpl.setCompanyId(companyId);
		stepAllowanceImpl.setGroupId(groupId);
		stepAllowanceImpl.setUserId(userId);

		if (userName == null) {
			stepAllowanceImpl.setUserName(StringPool.BLANK);
		}
		else {
			stepAllowanceImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			stepAllowanceImpl.setCreateDate(null);
		}
		else {
			stepAllowanceImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			stepAllowanceImpl.setModifiedDate(null);
		}
		else {
			stepAllowanceImpl.setModifiedDate(new Date(modifiedDate));
		}

		stepAllowanceImpl.setModerator(moderator);

		stepAllowanceImpl.resetOriginalValues();

		return stepAllowanceImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		processStepId = objectInput.readLong();

		roleId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		moderator = objectInput.readInt();

		stepAllowancePK = new StepAllowancePK(processStepId, roleId);
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

		objectOutput.writeLong(processStepId);

		objectOutput.writeLong(roleId);

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

		objectOutput.writeInt(moderator);
	}

	public String uuid;
	public long processStepId;
	public long roleId;
	public long companyId;
	public long groupId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public int moderator;
	public transient StepAllowancePK stepAllowancePK;
}