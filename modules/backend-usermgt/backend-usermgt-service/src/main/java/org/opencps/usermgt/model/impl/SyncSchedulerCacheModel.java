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

import org.opencps.usermgt.model.SyncScheduler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing SyncScheduler in entity cache.
 *
 * @author khoavu
 * @see SyncScheduler
 * @generated
 */
@ProviderType
public class SyncSchedulerCacheModel implements CacheModel<SyncScheduler>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SyncSchedulerCacheModel)) {
			return false;
		}

		SyncSchedulerCacheModel syncSchedulerCacheModel = (SyncSchedulerCacheModel)obj;

		if (syncSchedulerId == syncSchedulerCacheModel.syncSchedulerId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, syncSchedulerId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", syncSchedulerId=");
		sb.append(syncSchedulerId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", className=");
		sb.append(className);
		sb.append(", typeCode=");
		sb.append(typeCode);
		sb.append(", syncDate=");
		sb.append(syncDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SyncScheduler toEntityModel() {
		SyncSchedulerImpl syncSchedulerImpl = new SyncSchedulerImpl();

		if (uuid == null) {
			syncSchedulerImpl.setUuid("");
		}
		else {
			syncSchedulerImpl.setUuid(uuid);
		}

		syncSchedulerImpl.setSyncSchedulerId(syncSchedulerId);

		if (createDate == Long.MIN_VALUE) {
			syncSchedulerImpl.setCreateDate(null);
		}
		else {
			syncSchedulerImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			syncSchedulerImpl.setModifiedDate(null);
		}
		else {
			syncSchedulerImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (className == null) {
			syncSchedulerImpl.setClassName("");
		}
		else {
			syncSchedulerImpl.setClassName(className);
		}

		if (typeCode == null) {
			syncSchedulerImpl.setTypeCode("");
		}
		else {
			syncSchedulerImpl.setTypeCode(typeCode);
		}

		if (syncDate == Long.MIN_VALUE) {
			syncSchedulerImpl.setSyncDate(null);
		}
		else {
			syncSchedulerImpl.setSyncDate(new Date(syncDate));
		}

		syncSchedulerImpl.resetOriginalValues();

		return syncSchedulerImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		syncSchedulerId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		className = objectInput.readUTF();
		typeCode = objectInput.readUTF();
		syncDate = objectInput.readLong();
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

		objectOutput.writeLong(syncSchedulerId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (className == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(className);
		}

		if (typeCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(typeCode);
		}

		objectOutput.writeLong(syncDate);
	}

	public String uuid;
	public long syncSchedulerId;
	public long createDate;
	public long modifiedDate;
	public String className;
	public String typeCode;
	public long syncDate;
}