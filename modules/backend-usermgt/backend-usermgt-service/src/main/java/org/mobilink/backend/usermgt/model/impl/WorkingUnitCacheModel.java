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

package org.mobilink.backend.usermgt.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import org.mobilink.backend.usermgt.model.WorkingUnit;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WorkingUnit in entity cache.
 *
 * @author Binhth
 * @see WorkingUnit
 * @generated
 */
@ProviderType
public class WorkingUnitCacheModel implements CacheModel<WorkingUnit>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WorkingUnitCacheModel)) {
			return false;
		}

		WorkingUnitCacheModel workingUnitCacheModel = (WorkingUnitCacheModel)obj;

		if (workingUnitId == workingUnitCacheModel.workingUnitId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, workingUnitId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(41);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", workingUnitId=");
		sb.append(workingUnitId);
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
		sb.append(", name=");
		sb.append(name);
		sb.append(", enName=");
		sb.append(enName);
		sb.append(", govAgencyCode=");
		sb.append(govAgencyCode);
		sb.append(", parentWorkingUnitId=");
		sb.append(parentWorkingUnitId);
		sb.append(", address=");
		sb.append(address);
		sb.append(", telNo=");
		sb.append(telNo);
		sb.append(", faxNo=");
		sb.append(faxNo);
		sb.append(", email=");
		sb.append(email);
		sb.append(", website=");
		sb.append(website);
		sb.append(", logoFileEntryId=");
		sb.append(logoFileEntryId);
		sb.append(", sibling=");
		sb.append(sibling);
		sb.append(", treeIndex=");
		sb.append(treeIndex);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public WorkingUnit toEntityModel() {
		WorkingUnitImpl workingUnitImpl = new WorkingUnitImpl();

		if (uuid == null) {
			workingUnitImpl.setUuid(StringPool.BLANK);
		}
		else {
			workingUnitImpl.setUuid(uuid);
		}

		workingUnitImpl.setWorkingUnitId(workingUnitId);
		workingUnitImpl.setCompanyId(companyId);
		workingUnitImpl.setGroupId(groupId);
		workingUnitImpl.setUserId(userId);

		if (userName == null) {
			workingUnitImpl.setUserName(StringPool.BLANK);
		}
		else {
			workingUnitImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			workingUnitImpl.setCreateDate(null);
		}
		else {
			workingUnitImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			workingUnitImpl.setModifiedDate(null);
		}
		else {
			workingUnitImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			workingUnitImpl.setName(StringPool.BLANK);
		}
		else {
			workingUnitImpl.setName(name);
		}

		if (enName == null) {
			workingUnitImpl.setEnName(StringPool.BLANK);
		}
		else {
			workingUnitImpl.setEnName(enName);
		}

		if (govAgencyCode == null) {
			workingUnitImpl.setGovAgencyCode(StringPool.BLANK);
		}
		else {
			workingUnitImpl.setGovAgencyCode(govAgencyCode);
		}

		workingUnitImpl.setParentWorkingUnitId(parentWorkingUnitId);

		if (address == null) {
			workingUnitImpl.setAddress(StringPool.BLANK);
		}
		else {
			workingUnitImpl.setAddress(address);
		}

		if (telNo == null) {
			workingUnitImpl.setTelNo(StringPool.BLANK);
		}
		else {
			workingUnitImpl.setTelNo(telNo);
		}

		if (faxNo == null) {
			workingUnitImpl.setFaxNo(StringPool.BLANK);
		}
		else {
			workingUnitImpl.setFaxNo(faxNo);
		}

		if (email == null) {
			workingUnitImpl.setEmail(StringPool.BLANK);
		}
		else {
			workingUnitImpl.setEmail(email);
		}

		if (website == null) {
			workingUnitImpl.setWebsite(StringPool.BLANK);
		}
		else {
			workingUnitImpl.setWebsite(website);
		}

		workingUnitImpl.setLogoFileEntryId(logoFileEntryId);

		if (sibling == null) {
			workingUnitImpl.setSibling(StringPool.BLANK);
		}
		else {
			workingUnitImpl.setSibling(sibling);
		}

		if (treeIndex == null) {
			workingUnitImpl.setTreeIndex(StringPool.BLANK);
		}
		else {
			workingUnitImpl.setTreeIndex(treeIndex);
		}

		workingUnitImpl.resetOriginalValues();

		return workingUnitImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		workingUnitId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
		enName = objectInput.readUTF();
		govAgencyCode = objectInput.readUTF();

		parentWorkingUnitId = objectInput.readLong();
		address = objectInput.readUTF();
		telNo = objectInput.readUTF();
		faxNo = objectInput.readUTF();
		email = objectInput.readUTF();
		website = objectInput.readUTF();

		logoFileEntryId = objectInput.readLong();
		sibling = objectInput.readUTF();
		treeIndex = objectInput.readUTF();
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

		objectOutput.writeLong(workingUnitId);

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

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (enName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(enName);
		}

		if (govAgencyCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(govAgencyCode);
		}

		objectOutput.writeLong(parentWorkingUnitId);

		if (address == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(address);
		}

		if (telNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(telNo);
		}

		if (faxNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(faxNo);
		}

		if (email == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(email);
		}

		if (website == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(website);
		}

		objectOutput.writeLong(logoFileEntryId);

		if (sibling == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(sibling);
		}

		if (treeIndex == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(treeIndex);
		}
	}

	public String uuid;
	public long workingUnitId;
	public long companyId;
	public long groupId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String enName;
	public String govAgencyCode;
	public long parentWorkingUnitId;
	public String address;
	public String telNo;
	public String faxNo;
	public String email;
	public String website;
	public long logoFileEntryId;
	public String sibling;
	public String treeIndex;
}