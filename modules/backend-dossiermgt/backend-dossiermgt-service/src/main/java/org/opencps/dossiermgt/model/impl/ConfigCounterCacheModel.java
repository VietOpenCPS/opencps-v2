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

import org.opencps.dossiermgt.model.ConfigCounter;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ConfigCounter in entity cache.
 *
 * @author huymq
 * @see ConfigCounter
 * @generated
 */
@ProviderType
public class ConfigCounterCacheModel implements CacheModel<ConfigCounter>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ConfigCounterCacheModel)) {
			return false;
		}

		ConfigCounterCacheModel configCounterCacheModel = (ConfigCounterCacheModel)obj;

		if (configCounterId == configCounterCacheModel.configCounterId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, configCounterId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", configCounterId=");
		sb.append(configCounterId);
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
		sb.append(", counterCode=");
		sb.append(counterCode);
		sb.append(", patternCode=");
		sb.append(patternCode);
		sb.append(", startCounter=");
		sb.append(startCounter);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ConfigCounter toEntityModel() {
		ConfigCounterImpl configCounterImpl = new ConfigCounterImpl();

		if (uuid == null) {
			configCounterImpl.setUuid("");
		}
		else {
			configCounterImpl.setUuid(uuid);
		}

		configCounterImpl.setConfigCounterId(configCounterId);
		configCounterImpl.setCompanyId(companyId);
		configCounterImpl.setGroupId(groupId);
		configCounterImpl.setUserId(userId);

		if (userName == null) {
			configCounterImpl.setUserName("");
		}
		else {
			configCounterImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			configCounterImpl.setCreateDate(null);
		}
		else {
			configCounterImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			configCounterImpl.setModifiedDate(null);
		}
		else {
			configCounterImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (counterCode == null) {
			configCounterImpl.setCounterCode("");
		}
		else {
			configCounterImpl.setCounterCode(counterCode);
		}

		if (patternCode == null) {
			configCounterImpl.setPatternCode("");
		}
		else {
			configCounterImpl.setPatternCode(patternCode);
		}

		configCounterImpl.setStartCounter(startCounter);

		configCounterImpl.resetOriginalValues();

		return configCounterImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		configCounterId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		counterCode = objectInput.readUTF();
		patternCode = objectInput.readUTF();

		startCounter = objectInput.readInt();
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

		objectOutput.writeLong(configCounterId);

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

		if (counterCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(counterCode);
		}

		if (patternCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(patternCode);
		}

		objectOutput.writeInt(startCounter);
	}

	public String uuid;
	public long configCounterId;
	public long companyId;
	public long groupId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String counterCode;
	public String patternCode;
	public int startCounter;
}