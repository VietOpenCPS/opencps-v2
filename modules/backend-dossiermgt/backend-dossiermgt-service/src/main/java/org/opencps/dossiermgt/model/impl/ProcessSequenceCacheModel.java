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

package org.opencps.dossiermgt.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import org.opencps.dossiermgt.model.ProcessSequence;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ProcessSequence in entity cache.
 *
 * @author huymq
 * @see ProcessSequence
 * @generated
 */
@ProviderType
public class ProcessSequenceCacheModel implements CacheModel<ProcessSequence>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProcessSequenceCacheModel)) {
			return false;
		}

		ProcessSequenceCacheModel processSequenceCacheModel = (ProcessSequenceCacheModel)obj;

		if (processSequenceId == processSequenceCacheModel.processSequenceId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, processSequenceId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", processSequenceId=");
		sb.append(processSequenceId);
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
		sb.append(", serviceProcessId=");
		sb.append(serviceProcessId);
		sb.append(", sequenceNo=");
		sb.append(sequenceNo);
		sb.append(", sequenceName=");
		sb.append(sequenceName);
		sb.append(", sequenceRole=");
		sb.append(sequenceRole);
		sb.append(", durationCount=");
		sb.append(durationCount);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ProcessSequence toEntityModel() {
		ProcessSequenceImpl processSequenceImpl = new ProcessSequenceImpl();

		if (uuid == null) {
			processSequenceImpl.setUuid("");
		}
		else {
			processSequenceImpl.setUuid(uuid);
		}

		processSequenceImpl.setProcessSequenceId(processSequenceId);
		processSequenceImpl.setCompanyId(companyId);
		processSequenceImpl.setGroupId(groupId);
		processSequenceImpl.setUserId(userId);

		if (userName == null) {
			processSequenceImpl.setUserName("");
		}
		else {
			processSequenceImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			processSequenceImpl.setCreateDate(null);
		}
		else {
			processSequenceImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			processSequenceImpl.setModifiedDate(null);
		}
		else {
			processSequenceImpl.setModifiedDate(new Date(modifiedDate));
		}

		processSequenceImpl.setServiceProcessId(serviceProcessId);

		if (sequenceNo == null) {
			processSequenceImpl.setSequenceNo("");
		}
		else {
			processSequenceImpl.setSequenceNo(sequenceNo);
		}

		if (sequenceName == null) {
			processSequenceImpl.setSequenceName("");
		}
		else {
			processSequenceImpl.setSequenceName(sequenceName);
		}

		if (sequenceRole == null) {
			processSequenceImpl.setSequenceRole("");
		}
		else {
			processSequenceImpl.setSequenceRole(sequenceRole);
		}

		processSequenceImpl.setDurationCount(durationCount);

		processSequenceImpl.resetOriginalValues();

		return processSequenceImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		processSequenceId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		serviceProcessId = objectInput.readLong();
		sequenceNo = objectInput.readUTF();
		sequenceName = objectInput.readUTF();
		sequenceRole = objectInput.readUTF();

		durationCount = objectInput.readDouble();
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

		objectOutput.writeLong(processSequenceId);

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

		objectOutput.writeLong(serviceProcessId);

		if (sequenceNo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(sequenceNo);
		}

		if (sequenceName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(sequenceName);
		}

		if (sequenceRole == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(sequenceRole);
		}

		objectOutput.writeDouble(durationCount);
	}

	public String uuid;
	public long processSequenceId;
	public long companyId;
	public long groupId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long serviceProcessId;
	public String sequenceNo;
	public String sequenceName;
	public String sequenceRole;
	public double durationCount;
}