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

package pay.gate.integration.dvc.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.petra.string.StringBundler;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;

import pay.gate.integration.dvc.model.ServiceConfigMapping;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ServiceConfigMapping in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ServiceConfigMapping
 * @generated
 */
@ProviderType
public class ServiceConfigMappingCacheModel implements CacheModel<ServiceConfigMapping>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ServiceConfigMappingCacheModel)) {
			return false;
		}

		ServiceConfigMappingCacheModel serviceConfigMappingCacheModel = (ServiceConfigMappingCacheModel)obj;

		if (serviceConfigMappingId == serviceConfigMappingCacheModel.serviceConfigMappingId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, serviceConfigMappingId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", serviceConfigMappingId=");
		sb.append(serviceConfigMappingId);
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
		sb.append(", maDVC=");
		sb.append(maDVC);
		sb.append(", tenDVC=");
		sb.append(tenDVC);
		sb.append(", maTTHC=");
		sb.append(maTTHC);
		sb.append(", tenTTHC=");
		sb.append(tenTTHC);
		sb.append(", tenCQBH=");
		sb.append(tenCQBH);
		sb.append(", tenLinhVuc=");
		sb.append(tenLinhVuc);
		sb.append(", apdungDVC=");
		sb.append(apdungDVC);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ServiceConfigMapping toEntityModel() {
		ServiceConfigMappingImpl serviceConfigMappingImpl = new ServiceConfigMappingImpl();

		if (uuid == null) {
			serviceConfigMappingImpl.setUuid("");
		}
		else {
			serviceConfigMappingImpl.setUuid(uuid);
		}

		serviceConfigMappingImpl.setServiceConfigMappingId(serviceConfigMappingId);
		serviceConfigMappingImpl.setGroupId(groupId);
		serviceConfigMappingImpl.setCompanyId(companyId);
		serviceConfigMappingImpl.setUserId(userId);

		if (userName == null) {
			serviceConfigMappingImpl.setUserName("");
		}
		else {
			serviceConfigMappingImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			serviceConfigMappingImpl.setCreateDate(null);
		}
		else {
			serviceConfigMappingImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			serviceConfigMappingImpl.setModifiedDate(null);
		}
		else {
			serviceConfigMappingImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (maDVC == null) {
			serviceConfigMappingImpl.setMaDVC("");
		}
		else {
			serviceConfigMappingImpl.setMaDVC(maDVC);
		}

		if (tenDVC == null) {
			serviceConfigMappingImpl.setTenDVC("");
		}
		else {
			serviceConfigMappingImpl.setTenDVC(tenDVC);
		}

		if (maTTHC == null) {
			serviceConfigMappingImpl.setMaTTHC("");
		}
		else {
			serviceConfigMappingImpl.setMaTTHC(maTTHC);
		}

		if (tenTTHC == null) {
			serviceConfigMappingImpl.setTenTTHC("");
		}
		else {
			serviceConfigMappingImpl.setTenTTHC(tenTTHC);
		}

		if (tenCQBH == null) {
			serviceConfigMappingImpl.setTenCQBH("");
		}
		else {
			serviceConfigMappingImpl.setTenCQBH(tenCQBH);
		}

		if (tenLinhVuc == null) {
			serviceConfigMappingImpl.setTenLinhVuc("");
		}
		else {
			serviceConfigMappingImpl.setTenLinhVuc(tenLinhVuc);
		}

		if (apdungDVC == null) {
			serviceConfigMappingImpl.setApdungDVC("");
		}
		else {
			serviceConfigMappingImpl.setApdungDVC(apdungDVC);
		}

		serviceConfigMappingImpl.resetOriginalValues();

		return serviceConfigMappingImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		serviceConfigMappingId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		maDVC = objectInput.readUTF();
		tenDVC = objectInput.readUTF();
		maTTHC = objectInput.readUTF();
		tenTTHC = objectInput.readUTF();
		tenCQBH = objectInput.readUTF();
		tenLinhVuc = objectInput.readUTF();
		apdungDVC = objectInput.readUTF();
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

		objectOutput.writeLong(serviceConfigMappingId);

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

		if (maDVC == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(maDVC);
		}

		if (tenDVC == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(tenDVC);
		}

		if (maTTHC == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(maTTHC);
		}

		if (tenTTHC == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(tenTTHC);
		}

		if (tenCQBH == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(tenCQBH);
		}

		if (tenLinhVuc == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(tenLinhVuc);
		}

		if (apdungDVC == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(apdungDVC);
		}
	}

	public String uuid;
	public long serviceConfigMappingId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String maDVC;
	public String tenDVC;
	public String maTTHC;
	public String tenTTHC;
	public String tenCQBH;
	public String tenLinhVuc;
	public String apdungDVC;
}