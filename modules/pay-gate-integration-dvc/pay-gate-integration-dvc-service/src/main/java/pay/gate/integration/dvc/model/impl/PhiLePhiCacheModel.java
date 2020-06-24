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

import pay.gate.integration.dvc.model.PhiLePhi;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing PhiLePhi in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PhiLePhi
 * @generated
 */
@ProviderType
public class PhiLePhiCacheModel implements CacheModel<PhiLePhi>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PhiLePhiCacheModel)) {
			return false;
		}

		PhiLePhiCacheModel phiLePhiCacheModel = (PhiLePhiCacheModel)obj;

		if (phiLePhiId == phiLePhiCacheModel.phiLePhiId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, phiLePhiId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", phiLePhiId=");
		sb.append(phiLePhiId);
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
		sb.append(", loaiPLP=");
		sb.append(loaiPLP);
		sb.append(", maPLP=");
		sb.append(maPLP);
		sb.append(", tenPLP=");
		sb.append(tenPLP);
		sb.append(", soTien=");
		sb.append(soTien);
		sb.append(", serviceConfigMappingId=");
		sb.append(serviceConfigMappingId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public PhiLePhi toEntityModel() {
		PhiLePhiImpl phiLePhiImpl = new PhiLePhiImpl();

		if (uuid == null) {
			phiLePhiImpl.setUuid("");
		}
		else {
			phiLePhiImpl.setUuid(uuid);
		}

		phiLePhiImpl.setPhiLePhiId(phiLePhiId);
		phiLePhiImpl.setGroupId(groupId);
		phiLePhiImpl.setCompanyId(companyId);
		phiLePhiImpl.setUserId(userId);

		if (userName == null) {
			phiLePhiImpl.setUserName("");
		}
		else {
			phiLePhiImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			phiLePhiImpl.setCreateDate(null);
		}
		else {
			phiLePhiImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			phiLePhiImpl.setModifiedDate(null);
		}
		else {
			phiLePhiImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (loaiPLP == null) {
			phiLePhiImpl.setLoaiPLP("");
		}
		else {
			phiLePhiImpl.setLoaiPLP(loaiPLP);
		}

		if (maPLP == null) {
			phiLePhiImpl.setMaPLP("");
		}
		else {
			phiLePhiImpl.setMaPLP(maPLP);
		}

		if (tenPLP == null) {
			phiLePhiImpl.setTenPLP("");
		}
		else {
			phiLePhiImpl.setTenPLP(tenPLP);
		}

		phiLePhiImpl.setSoTien(soTien);
		phiLePhiImpl.setServiceConfigMappingId(serviceConfigMappingId);

		phiLePhiImpl.resetOriginalValues();

		return phiLePhiImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		phiLePhiId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		loaiPLP = objectInput.readUTF();
		maPLP = objectInput.readUTF();
		tenPLP = objectInput.readUTF();

		soTien = objectInput.readLong();

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

		objectOutput.writeLong(phiLePhiId);

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

		if (loaiPLP == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(loaiPLP);
		}

		if (maPLP == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(maPLP);
		}

		if (tenPLP == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(tenPLP);
		}

		objectOutput.writeLong(soTien);

		objectOutput.writeLong(serviceConfigMappingId);
	}

	public String uuid;
	public long phiLePhiId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String loaiPLP;
	public String maPLP;
	public String tenPLP;
	public long soTien;
	public long serviceConfigMappingId;
}