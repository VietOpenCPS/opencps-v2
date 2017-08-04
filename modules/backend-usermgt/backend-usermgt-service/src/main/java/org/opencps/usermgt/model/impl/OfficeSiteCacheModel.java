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

import org.opencps.usermgt.model.OfficeSite;

/**
 * The cache model class for representing OfficeSite in entity cache.
 *
 * @author Binhth
 * @see OfficeSite
 * @generated
 */
@ProviderType
public class OfficeSiteCacheModel implements CacheModel<OfficeSite>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OfficeSiteCacheModel)) {
			return false;
		}

		OfficeSiteCacheModel officeSiteCacheModel = (OfficeSiteCacheModel)obj;

		if (officeSiteId == officeSiteCacheModel.officeSiteId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, officeSiteId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(41);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", officeSiteId=");
		sb.append(officeSiteId);
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
		sb.append(", siteGroupId=");
		sb.append(siteGroupId);
		sb.append(", adminUserId=");
		sb.append(adminUserId);
		sb.append(", preferences=");
		sb.append(preferences);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public OfficeSite toEntityModel() {
		OfficeSiteImpl officeSiteImpl = new OfficeSiteImpl();

		if (uuid == null) {
			officeSiteImpl.setUuid(StringPool.BLANK);
		}
		else {
			officeSiteImpl.setUuid(uuid);
		}

		officeSiteImpl.setOfficeSiteId(officeSiteId);
		officeSiteImpl.setCompanyId(companyId);
		officeSiteImpl.setGroupId(groupId);
		officeSiteImpl.setUserId(userId);

		if (userName == null) {
			officeSiteImpl.setUserName(StringPool.BLANK);
		}
		else {
			officeSiteImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			officeSiteImpl.setCreateDate(null);
		}
		else {
			officeSiteImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			officeSiteImpl.setModifiedDate(null);
		}
		else {
			officeSiteImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			officeSiteImpl.setName(StringPool.BLANK);
		}
		else {
			officeSiteImpl.setName(name);
		}

		if (enName == null) {
			officeSiteImpl.setEnName(StringPool.BLANK);
		}
		else {
			officeSiteImpl.setEnName(enName);
		}

		if (govAgencyCode == null) {
			officeSiteImpl.setGovAgencyCode(StringPool.BLANK);
		}
		else {
			officeSiteImpl.setGovAgencyCode(govAgencyCode);
		}

		if (address == null) {
			officeSiteImpl.setAddress(StringPool.BLANK);
		}
		else {
			officeSiteImpl.setAddress(address);
		}

		if (telNo == null) {
			officeSiteImpl.setTelNo(StringPool.BLANK);
		}
		else {
			officeSiteImpl.setTelNo(telNo);
		}

		if (faxNo == null) {
			officeSiteImpl.setFaxNo(StringPool.BLANK);
		}
		else {
			officeSiteImpl.setFaxNo(faxNo);
		}

		if (email == null) {
			officeSiteImpl.setEmail(StringPool.BLANK);
		}
		else {
			officeSiteImpl.setEmail(email);
		}

		if (website == null) {
			officeSiteImpl.setWebsite(StringPool.BLANK);
		}
		else {
			officeSiteImpl.setWebsite(website);
		}

		officeSiteImpl.setLogoFileEntryId(logoFileEntryId);
		officeSiteImpl.setSiteGroupId(siteGroupId);
		officeSiteImpl.setAdminUserId(adminUserId);

		if (preferences == null) {
			officeSiteImpl.setPreferences(StringPool.BLANK);
		}
		else {
			officeSiteImpl.setPreferences(preferences);
		}

		officeSiteImpl.resetOriginalValues();

		return officeSiteImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		officeSiteId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
		enName = objectInput.readUTF();
		govAgencyCode = objectInput.readUTF();
		address = objectInput.readUTF();
		telNo = objectInput.readUTF();
		faxNo = objectInput.readUTF();
		email = objectInput.readUTF();
		website = objectInput.readUTF();

		logoFileEntryId = objectInput.readLong();

		siteGroupId = objectInput.readLong();

		adminUserId = objectInput.readLong();
		preferences = objectInput.readUTF();
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

		objectOutput.writeLong(officeSiteId);

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

		objectOutput.writeLong(siteGroupId);

		objectOutput.writeLong(adminUserId);

		if (preferences == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(preferences);
		}
	}

	public String uuid;
	public long officeSiteId;
	public long companyId;
	public long groupId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String enName;
	public String govAgencyCode;
	public String address;
	public String telNo;
	public String faxNo;
	public String email;
	public String website;
	public long logoFileEntryId;
	public long siteGroupId;
	public long adminUserId;
	public String preferences;
}