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

package opencps.dvcqg.extend.sync.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.petra.string.StringBundler;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;

import opencps.dvcqg.extend.sync.model.ApplicableInfo;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ApplicableInfo in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ApplicableInfo
 * @generated
 */
@ProviderType
public class ApplicableInfoCacheModel implements CacheModel<ApplicableInfo>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ApplicableInfoCacheModel)) {
			return false;
		}

		ApplicableInfoCacheModel applicableInfoCacheModel = (ApplicableInfoCacheModel)obj;

		if (applicableInfoId == applicableInfoCacheModel.applicableInfoId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, applicableInfoId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", applicableInfoId=");
		sb.append(applicableInfoId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", serviceCode=");
		sb.append(serviceCode);
		sb.append(", govAgencyCode=");
		sb.append(govAgencyCode);
		sb.append(", serviceLevel=");
		sb.append(serviceLevel);
		sb.append(", serviceConfigMappingId=");
		sb.append(serviceConfigMappingId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ApplicableInfo toEntityModel() {
		ApplicableInfoImpl applicableInfoImpl = new ApplicableInfoImpl();

		if (uuid == null) {
			applicableInfoImpl.setUuid("");
		}
		else {
			applicableInfoImpl.setUuid(uuid);
		}

		applicableInfoImpl.setApplicableInfoId(applicableInfoId);
		applicableInfoImpl.setGroupId(groupId);
		applicableInfoImpl.setCompanyId(companyId);
		applicableInfoImpl.setUserId(userId);

		if (userName == null) {
			applicableInfoImpl.setUserName("");
		}
		else {
			applicableInfoImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			applicableInfoImpl.setCreateDate(null);
		}
		else {
			applicableInfoImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			applicableInfoImpl.setModifiedDate(null);
		}
		else {
			applicableInfoImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (serviceCode == null) {
			applicableInfoImpl.setServiceCode("");
		}
		else {
			applicableInfoImpl.setServiceCode(serviceCode);
		}

		if (govAgencyCode == null) {
			applicableInfoImpl.setGovAgencyCode("");
		}
		else {
			applicableInfoImpl.setGovAgencyCode(govAgencyCode);
		}

		applicableInfoImpl.setServiceLevel(serviceLevel);
		applicableInfoImpl.setServiceConfigMappingId(serviceConfigMappingId);

		applicableInfoImpl.resetOriginalValues();

		return applicableInfoImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		applicableInfoId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		serviceCode = objectInput.readUTF();
		govAgencyCode = objectInput.readUTF();

		serviceLevel = objectInput.readInt();

		serviceConfigMappingId = objectInput.readLong();
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

		objectOutput.writeLong(applicableInfoId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (serviceCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(serviceCode);
		}

		if (govAgencyCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(govAgencyCode);
		}

		objectOutput.writeInt(serviceLevel);

		objectOutput.writeLong(serviceConfigMappingId);
	}

	public String uuid;
	public long applicableInfoId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String serviceCode;
	public String govAgencyCode;
	public int serviceLevel;
	public long serviceConfigMappingId;
}