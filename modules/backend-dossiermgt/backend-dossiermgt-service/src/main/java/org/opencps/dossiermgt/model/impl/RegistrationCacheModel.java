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

import org.opencps.dossiermgt.model.Registration;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Registration in entity cache.
 *
 * @author huymq
 * @see Registration
 * @generated
 */
@ProviderType
public class RegistrationCacheModel implements CacheModel<Registration>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RegistrationCacheModel)) {
			return false;
		}

		RegistrationCacheModel registrationCacheModel = (RegistrationCacheModel)obj;

		if (registrationId == registrationCacheModel.registrationId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, registrationId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(55);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", registrationId=");
		sb.append(registrationId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", userId=");
		sb.append(userId);
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
		sb.append(", govAgencyCode=");
		sb.append(govAgencyCode);
		sb.append(", govAgencyName=");
		sb.append(govAgencyName);
		sb.append(", registrationState=");
		sb.append(registrationState);
		sb.append(", registrationClass=");
		sb.append(registrationClass);
		sb.append(", submitting=");
		sb.append(submitting);
		sb.append(", representativeEnterprise=");
		sb.append(representativeEnterprise);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Registration toEntityModel() {
		RegistrationImpl registrationImpl = new RegistrationImpl();

		if (uuid == null) {
			registrationImpl.setUuid("");
		}
		else {
			registrationImpl.setUuid(uuid);
		}

		registrationImpl.setRegistrationId(registrationId);
		registrationImpl.setCompanyId(companyId);
		registrationImpl.setGroupId(groupId);
		registrationImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			registrationImpl.setCreateDate(null);
		}
		else {
			registrationImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			registrationImpl.setModifiedDate(null);
		}
		else {
			registrationImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (applicantName == null) {
			registrationImpl.setApplicantName("");
		}
		else {
			registrationImpl.setApplicantName(applicantName);
		}

		if (applicantIdType == null) {
			registrationImpl.setApplicantIdType("");
		}
		else {
			registrationImpl.setApplicantIdType(applicantIdType);
		}

		if (applicantIdNo == null) {
			registrationImpl.setApplicantIdNo("");
		}
		else {
			registrationImpl.setApplicantIdNo(applicantIdNo);
		}

		if (applicantIdDate == Long.MIN_VALUE) {
			registrationImpl.setApplicantIdDate(null);
		}
		else {
			registrationImpl.setApplicantIdDate(new Date(applicantIdDate));
		}

		if (address == null) {
			registrationImpl.setAddress("");
		}
		else {
			registrationImpl.setAddress(address);
		}

		if (cityCode == null) {
			registrationImpl.setCityCode("");
		}
		else {
			registrationImpl.setCityCode(cityCode);
		}

		if (cityName == null) {
			registrationImpl.setCityName("");
		}
		else {
			registrationImpl.setCityName(cityName);
		}

		if (districtCode == null) {
			registrationImpl.setDistrictCode("");
		}
		else {
			registrationImpl.setDistrictCode(districtCode);
		}

		if (districtName == null) {
			registrationImpl.setDistrictName("");
		}
		else {
			registrationImpl.setDistrictName(districtName);
		}

		if (wardCode == null) {
			registrationImpl.setWardCode("");
		}
		else {
			registrationImpl.setWardCode(wardCode);
		}

		if (wardName == null) {
			registrationImpl.setWardName("");
		}
		else {
			registrationImpl.setWardName(wardName);
		}

		if (contactName == null) {
			registrationImpl.setContactName("");
		}
		else {
			registrationImpl.setContactName(contactName);
		}

		if (contactTelNo == null) {
			registrationImpl.setContactTelNo("");
		}
		else {
			registrationImpl.setContactTelNo(contactTelNo);
		}

		if (contactEmail == null) {
			registrationImpl.setContactEmail("");
		}
		else {
			registrationImpl.setContactEmail(contactEmail);
		}

		if (govAgencyCode == null) {
			registrationImpl.setGovAgencyCode("");
		}
		else {
			registrationImpl.setGovAgencyCode(govAgencyCode);
		}

		if (govAgencyName == null) {
			registrationImpl.setGovAgencyName("");
		}
		else {
			registrationImpl.setGovAgencyName(govAgencyName);
		}

		registrationImpl.setRegistrationState(registrationState);

		if (registrationClass == null) {
			registrationImpl.setRegistrationClass("");
		}
		else {
			registrationImpl.setRegistrationClass(registrationClass);
		}

		registrationImpl.setSubmitting(submitting);

		if (representativeEnterprise == null) {
			registrationImpl.setRepresentativeEnterprise("");
		}
		else {
			registrationImpl.setRepresentativeEnterprise(representativeEnterprise);
		}

		registrationImpl.resetOriginalValues();

		return registrationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		registrationId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
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
		govAgencyCode = objectInput.readUTF();
		govAgencyName = objectInput.readUTF();

		registrationState = objectInput.readInt();
		registrationClass = objectInput.readUTF();

		submitting = objectInput.readBoolean();
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

		objectOutput.writeLong(registrationId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(userId);
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

		if (govAgencyCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(govAgencyCode);
		}

		if (govAgencyName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(govAgencyName);
		}

		objectOutput.writeInt(registrationState);

		if (registrationClass == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(registrationClass);
		}

		objectOutput.writeBoolean(submitting);

		if (representativeEnterprise == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(representativeEnterprise);
		}
	}

	public String uuid;
	public long registrationId;
	public long companyId;
	public long groupId;
	public long userId;
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
	public String govAgencyCode;
	public String govAgencyName;
	public int registrationState;
	public String registrationClass;
	public boolean submitting;
	public String representativeEnterprise;
}