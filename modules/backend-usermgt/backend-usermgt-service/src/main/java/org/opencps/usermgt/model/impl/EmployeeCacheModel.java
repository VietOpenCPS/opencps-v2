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

import org.opencps.usermgt.model.Employee;

/**
 * The cache model class for representing Employee in entity cache.
 *
 * @author Binhth
 * @see Employee
 * @generated
 */
@ProviderType
public class EmployeeCacheModel implements CacheModel<Employee>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EmployeeCacheModel)) {
			return false;
		}

		EmployeeCacheModel employeeCacheModel = (EmployeeCacheModel)obj;

		if (employeeId == employeeCacheModel.employeeId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, employeeId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(43);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", employeeId=");
		sb.append(employeeId);
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
		sb.append(", fullName=");
		sb.append(fullName);
		sb.append(", employeeNo=");
		sb.append(employeeNo);
		sb.append(", gender=");
		sb.append(gender);
		sb.append(", birthDate=");
		sb.append(birthDate);
		sb.append(", telNo=");
		sb.append(telNo);
		sb.append(", mobile=");
		sb.append(mobile);
		sb.append(", email=");
		sb.append(email);
		sb.append(", workingStatus=");
		sb.append(workingStatus);
		sb.append(", mappingUserId=");
		sb.append(mappingUserId);
		sb.append(", mainJobPostId=");
		sb.append(mainJobPostId);
		sb.append(", photoFileEntryId=");
		sb.append(photoFileEntryId);
		sb.append(", fileDocId=");
		sb.append(fileDocId);
		sb.append(", preferences=");
		sb.append(preferences);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Employee toEntityModel() {
		EmployeeImpl employeeImpl = new EmployeeImpl();

		if (uuid == null) {
			employeeImpl.setUuid(StringPool.BLANK);
		}
		else {
			employeeImpl.setUuid(uuid);
		}

		employeeImpl.setEmployeeId(employeeId);
		employeeImpl.setCompanyId(companyId);
		employeeImpl.setGroupId(groupId);
		employeeImpl.setUserId(userId);

		if (userName == null) {
			employeeImpl.setUserName(StringPool.BLANK);
		}
		else {
			employeeImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			employeeImpl.setCreateDate(null);
		}
		else {
			employeeImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			employeeImpl.setModifiedDate(null);
		}
		else {
			employeeImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (fullName == null) {
			employeeImpl.setFullName(StringPool.BLANK);
		}
		else {
			employeeImpl.setFullName(fullName);
		}

		if (employeeNo == null) {
			employeeImpl.setEmployeeNo(StringPool.BLANK);
		}
		else {
			employeeImpl.setEmployeeNo(employeeNo);
		}

		employeeImpl.setGender(gender);

		if (birthDate == Long.MIN_VALUE) {
			employeeImpl.setBirthDate(null);
		}
		else {
			employeeImpl.setBirthDate(new Date(birthDate));
		}

		if (telNo == null) {
			employeeImpl.setTelNo(StringPool.BLANK);
		}
		else {
			employeeImpl.setTelNo(telNo);
		}

		if (mobile == null) {
			employeeImpl.setMobile(StringPool.BLANK);
		}
		else {
			employeeImpl.setMobile(mobile);
		}

		if (email == null) {
			employeeImpl.setEmail(StringPool.BLANK);
		}
		else {
			employeeImpl.setEmail(email);
		}

		employeeImpl.setWorkingStatus(workingStatus);
		employeeImpl.setMappingUserId(mappingUserId);
		employeeImpl.setMainJobPostId(mainJobPostId);
		employeeImpl.setPhotoFileEntryId(photoFileEntryId);
		employeeImpl.setFileDocId(fileDocId);

		if (preferences == null) {
			employeeImpl.setPreferences(StringPool.BLANK);
		}
		else {
			employeeImpl.setPreferences(preferences);
		}

		employeeImpl.resetOriginalValues();

		return employeeImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		employeeId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		fullName = objectInput.readUTF();
		employeeNo = objectInput.readUTF();

		gender = objectInput.readInt();
		birthDate = objectInput.readLong();
		telNo = objectInput.readUTF();
		mobile = objectInput.readUTF();
		email = objectInput.readUTF();

		workingStatus = objectInput.readInt();

		mappingUserId = objectInput.readLong();

		mainJobPostId = objectInput.readLong();

		photoFileEntryId = objectInput.readLong();

		fileDocId = objectInput.readLong();
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

		objectOutput.writeLong(employeeId);

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

		if (fullName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(fullName);
		}

		if (employeeNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(employeeNo);
		}

		objectOutput.writeInt(gender);
		objectOutput.writeLong(birthDate);

		if (telNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(telNo);
		}

		if (mobile == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(mobile);
		}

		if (email == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(email);
		}

		objectOutput.writeInt(workingStatus);

		objectOutput.writeLong(mappingUserId);

		objectOutput.writeLong(mainJobPostId);

		objectOutput.writeLong(photoFileEntryId);

		objectOutput.writeLong(fileDocId);

		if (preferences == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(preferences);
		}
	}

	public String uuid;
	public long employeeId;
	public long companyId;
	public long groupId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String fullName;
	public String employeeNo;
	public int gender;
	public long birthDate;
	public String telNo;
	public String mobile;
	public String email;
	public int workingStatus;
	public long mappingUserId;
	public long mainJobPostId;
	public long photoFileEntryId;
	public long fileDocId;
	public String preferences;
}