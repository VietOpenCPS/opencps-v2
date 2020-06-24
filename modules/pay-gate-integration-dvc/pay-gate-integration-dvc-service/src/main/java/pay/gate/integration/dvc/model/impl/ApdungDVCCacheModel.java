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

import pay.gate.integration.dvc.model.ApdungDVC;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ApdungDVC in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ApdungDVC
 * @generated
 */
@ProviderType
public class ApdungDVCCacheModel implements CacheModel<ApdungDVC>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ApdungDVCCacheModel)) {
			return false;
		}

		ApdungDVCCacheModel apdungDVCCacheModel = (ApdungDVCCacheModel)obj;

		if (apdungDVCId == apdungDVCCacheModel.apdungDVCId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, apdungDVCId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", apdungDVCId=");
		sb.append(apdungDVCId);
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
		sb.append(", maTTHC=");
		sb.append(maTTHC);
		sb.append(", maCQTH=");
		sb.append(maCQTH);
		sb.append(", mucdo=");
		sb.append(mucdo);
		sb.append(", serviceConfigMappingId=");
		sb.append(serviceConfigMappingId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ApdungDVC toEntityModel() {
		ApdungDVCImpl apdungDVCImpl = new ApdungDVCImpl();

		if (uuid == null) {
			apdungDVCImpl.setUuid("");
		}
		else {
			apdungDVCImpl.setUuid(uuid);
		}

		apdungDVCImpl.setApdungDVCId(apdungDVCId);
		apdungDVCImpl.setGroupId(groupId);
		apdungDVCImpl.setCompanyId(companyId);
		apdungDVCImpl.setUserId(userId);

		if (userName == null) {
			apdungDVCImpl.setUserName("");
		}
		else {
			apdungDVCImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			apdungDVCImpl.setCreateDate(null);
		}
		else {
			apdungDVCImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			apdungDVCImpl.setModifiedDate(null);
		}
		else {
			apdungDVCImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (maTTHC == null) {
			apdungDVCImpl.setMaTTHC("");
		}
		else {
			apdungDVCImpl.setMaTTHC(maTTHC);
		}

		if (maCQTH == null) {
			apdungDVCImpl.setMaCQTH("");
		}
		else {
			apdungDVCImpl.setMaCQTH(maCQTH);
		}

		apdungDVCImpl.setMucdo(mucdo);
		apdungDVCImpl.setServiceConfigMappingId(serviceConfigMappingId);

		apdungDVCImpl.resetOriginalValues();

		return apdungDVCImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		apdungDVCId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		maTTHC = objectInput.readUTF();
		maCQTH = objectInput.readUTF();

		mucdo = objectInput.readInt();

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

		objectOutput.writeLong(apdungDVCId);

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

		if (maTTHC == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(maTTHC);
		}

		if (maCQTH == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(maCQTH);
		}

		objectOutput.writeInt(mucdo);

		objectOutput.writeLong(serviceConfigMappingId);
	}

	public String uuid;
	public long apdungDVCId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String maTTHC;
	public String maCQTH;
	public int mucdo;
	public long serviceConfigMappingId;
}