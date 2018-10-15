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

import org.opencps.usermgt.model.Applicant;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Applicant in entity cache.
 *
 * @author khoavu
 * @see Applicant
 * @generated
 */
@ProviderType
public class ApplicantCacheModel implements CacheModel<Applicant>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ApplicantCacheModel)) {
			return false;
		}

		ApplicantCacheModel applicantCacheModel = (ApplicantCacheModel)obj;

		if (applicantId == applicantCacheModel.applicantId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, applicantId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(57);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", applicantId=");
		sb.append(applicantId);
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
		sb.append(", applicantName=");
		sb.append(applicantName);
		sb.append(", applicantIdType=");
		sb.append(applicantIdType);
		sb.append(", applicantIdNo=");
		sb.append(applicantIdNo);
		sb.append(", applicantIdDate=");
		sb.append(applicantIdDate);
		sb.append(", address=");
		sb.append(address);
		sb.append(", cityCode=");
		sb.append(cityCode);
		sb.append(", cityName=");
		sb.append(cityName);
		sb.append(", districtCode=");
		sb.append(districtCode);
		sb.append(", districtName=");
		sb.append(districtName);
		sb.append(", wardCode=");
		sb.append(wardCode);
		sb.append(", wardName=");
		sb.append(wardName);
		sb.append(", contactName=");
		sb.append(contactName);
		sb.append(", contactTelNo=");
		sb.append(contactTelNo);
		sb.append(", contactEmail=");
		sb.append(contactEmail);
		sb.append(", mappingUserId=");
		sb.append(mappingUserId);
		sb.append(", activationCode=");
		sb.append(activationCode);
		sb.append(", lock_=");
		sb.append(lock_);
		sb.append(", profile=");
		sb.append(profile);
		sb.append(", tmpPass=");
		sb.append(tmpPass);
		sb.append(", representativeEnterprise=");
		sb.append(representativeEnterprise);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Applicant toEntityModel() {
		ApplicantImpl applicantImpl = new ApplicantImpl();

		if (uuid == null) {
			applicantImpl.setUuid("");
		}
		else {
			applicantImpl.setUuid(uuid);
		}

		applicantImpl.setApplicantId(applicantId);
		applicantImpl.setGroupId(groupId);
		applicantImpl.setCompanyId(companyId);
		applicantImpl.setUserId(userId);

		if (userName == null) {
			applicantImpl.setUserName("");
		}
		else {
			applicantImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			applicantImpl.setCreateDate(null);
		}
		else {
			applicantImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			applicantImpl.setModifiedDate(null);
		}
		else {
			applicantImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (applicantName == null) {
			applicantImpl.setApplicantName("");
		}
		else {
			applicantImpl.setApplicantName(applicantName);
		}

		if (applicantIdType == null) {
			applicantImpl.setApplicantIdType("");
		}
		else {
			applicantImpl.setApplicantIdType(applicantIdType);
		}

		if (applicantIdNo == null) {
			applicantImpl.setApplicantIdNo("");
		}
		else {
			applicantImpl.setApplicantIdNo(applicantIdNo);
		}

		if (applicantIdDate == Long.MIN_VALUE) {
			applicantImpl.setApplicantIdDate(null);
		}
		else {
			applicantImpl.setApplicantIdDate(new Date(applicantIdDate));
		}

		if (address == null) {
			applicantImpl.setAddress("");
		}
		else {
			applicantImpl.setAddress(address);
		}

		if (cityCode == null) {
			applicantImpl.setCityCode("");
		}
		else {
			applicantImpl.setCityCode(cityCode);
		}

		if (cityName == null) {
			applicantImpl.setCityName("");
		}
		else {
			applicantImpl.setCityName(cityName);
		}

		if (districtCode == null) {
			applicantImpl.setDistrictCode("");
		}
		else {
			applicantImpl.setDistrictCode(districtCode);
		}

		if (districtName == null) {
			applicantImpl.setDistrictName("");
		}
		else {
			applicantImpl.setDistrictName(districtName);
		}

		if (wardCode == null) {
			applicantImpl.setWardCode("");
		}
		else {
			applicantImpl.setWardCode(wardCode);
		}

		if (wardName == null) {
			applicantImpl.setWardName("");
		}
		else {
			applicantImpl.setWardName(wardName);
		}

		if (contactName == null) {
			applicantImpl.setContactName("");
		}
		else {
			applicantImpl.setContactName(contactName);
		}

		if (contactTelNo == null) {
			applicantImpl.setContactTelNo("");
		}
		else {
			applicantImpl.setContactTelNo(contactTelNo);
		}

		if (contactEmail == null) {
			applicantImpl.setContactEmail("");
		}
		else {
			applicantImpl.setContactEmail(contactEmail);
		}

		applicantImpl.setMappingUserId(mappingUserId);

		if (activationCode == null) {
			applicantImpl.setActivationCode("");
		}
		else {
			applicantImpl.setActivationCode(activationCode);
		}

		applicantImpl.setLock_(lock_);

		if (profile == null) {
			applicantImpl.setProfile("");
		}
		else {
			applicantImpl.setProfile(profile);
		}

		if (tmpPass == null) {
			applicantImpl.setTmpPass("");
		}
		else {
			applicantImpl.setTmpPass(tmpPass);
		}

		if (representativeEnterprise == null) {
			applicantImpl.setRepresentativeEnterprise("");
		}
		else {
			applicantImpl.setRepresentativeEnterprise(representativeEnterprise);
		}

		applicantImpl.resetOriginalValues();

		return applicantImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		applicantId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		applicantName = objectInput.readUTF();
		applicantIdType = objectInput.readUTF();
		applicantIdNo = objectInput.readUTF();
		applicantIdDate = objectInput.readLong();
		address = objectInput.readUTF();
		cityCode = objectInput.readUTF();
		cityName = objectInput.readUTF();
		districtCode = objectInput.readUTF();
		districtName = objectInput.readUTF();
		wardCode = objectInput.readUTF();
		wardName = objectInput.readUTF();
		contactName = objectInput.readUTF();
		contactTelNo = objectInput.readUTF();
		contactEmail = objectInput.readUTF();

		mappingUserId = objectInput.readLong();
		activationCode = objectInput.readUTF();

		lock_ = objectInput.readBoolean();
		profile = objectInput.readUTF();
		tmpPass = objectInput.readUTF();
		representativeEnterprise = objectInput.readUTF();
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

		objectOutput.writeLong(applicantId);

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

		if (applicantName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(applicantName);
		}

		if (applicantIdType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(applicantIdType);
		}

		if (applicantIdNo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(applicantIdNo);
		}

		objectOutput.writeLong(applicantIdDate);

		if (address == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(address);
		}

		if (cityCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(cityCode);
		}

		if (cityName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(cityName);
		}

		if (districtCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(districtCode);
		}

		if (districtName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(districtName);
		}

		if (wardCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(wardCode);
		}

		if (wardName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(wardName);
		}

		if (contactName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(contactName);
		}

		if (contactTelNo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(contactTelNo);
		}

		if (contactEmail == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(contactEmail);
		}

		objectOutput.writeLong(mappingUserId);

		if (activationCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(activationCode);
		}

		objectOutput.writeBoolean(lock_);

		if (profile == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(profile);
		}

		if (tmpPass == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(tmpPass);
		}

		if (representativeEnterprise == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(representativeEnterprise);
		}
	}

	public String uuid;
	public long applicantId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String applicantName;
	public String applicantIdType;
	public String applicantIdNo;
	public long applicantIdDate;
	public String address;
	public String cityCode;
	public String cityName;
	public String districtCode;
	public String districtName;
	public String wardCode;
	public String wardName;
	public String contactName;
	public String contactTelNo;
	public String contactEmail;
	public long mappingUserId;
	public String activationCode;
	public boolean lock_;
	public String profile;
	public String tmpPass;
	public String representativeEnterprise;
}