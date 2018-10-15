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

import org.opencps.usermgt.model.Employee;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Employee in entity cache.
 *
 * @author khoavu
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
		StringBundler sb = new StringBundler(53);

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
		sb.append(", employeeNo=");
		sb.append(employeeNo);
		sb.append(", fullName=");
		sb.append(fullName);
		sb.append(", title=");
		sb.append(title);
		sb.append(", gender=");
		sb.append(gender);
		sb.append(", birthdate=");
		sb.append(birthdate);
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
		sb.append(", recruitDate=");
		sb.append(recruitDate);
		sb.append(", leaveDate=");
		sb.append(leaveDate);
		sb.append(", fileCertId=");
		sb.append(fileCertId);
		sb.append(", fileSignId=");
		sb.append(fileSignId);
		sb.append(", fileCertPath=");
		sb.append(fileCertPath);
		sb.append(", fileSignPath=");
		sb.append(fileSignPath);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Employee toEntityModel() {
		EmployeeImpl employeeImpl = new EmployeeImpl();

		if (uuid == null) {
			employeeImpl.setUuid("");
		}
		else {
			employeeImpl.setUuid(uuid);
		}

		employeeImpl.setEmployeeId(employeeId);
		employeeImpl.setCompanyId(companyId);
		employeeImpl.setGroupId(groupId);
		employeeImpl.setUserId(userId);

		if (userName == null) {
			employeeImpl.setUserName("");
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

		if (employeeNo == null) {
			employeeImpl.setEmployeeNo("");
		}
		else {
			employeeImpl.setEmployeeNo(employeeNo);
		}

		if (fullName == null) {
			employeeImpl.setFullName("");
		}
		else {
			employeeImpl.setFullName(fullName);
		}

		if (title == null) {
			employeeImpl.setTitle("");
		}
		else {
			employeeImpl.setTitle(title);
		}

		employeeImpl.setGender(gender);

		if (birthdate == Long.MIN_VALUE) {
			employeeImpl.setBirthdate(null);
		}
		else {
			employeeImpl.setBirthdate(new Date(birthdate));
		}

		if (telNo == null) {
			employeeImpl.setTelNo("");
		}
		else {
			employeeImpl.setTelNo(telNo);
		}

		if (mobile == null) {
			employeeImpl.setMobile("");
		}
		else {
			employeeImpl.setMobile(mobile);
		}

		if (email == null) {
			employeeImpl.setEmail("");
		}
		else {
			employeeImpl.setEmail(email);
		}

		employeeImpl.setWorkingStatus(workingStatus);
		employeeImpl.setMappingUserId(mappingUserId);
		employeeImpl.setMainJobPostId(mainJobPostId);
		employeeImpl.setPhotoFileEntryId(photoFileEntryId);

		if (recruitDate == Long.MIN_VALUE) {
			employeeImpl.setRecruitDate(null);
		}
		else {
			employeeImpl.setRecruitDate(new Date(recruitDate));
		}

		if (leaveDate == Long.MIN_VALUE) {
			employeeImpl.setLeaveDate(null);
		}
		else {
			employeeImpl.setLeaveDate(new Date(leaveDate));
		}

		employeeImpl.setFileCertId(fileCertId);
		employeeImpl.setFileSignId(fileSignId);

		if (fileCertPath == null) {
			employeeImpl.setFileCertPath("");
		}
		else {
			employeeImpl.setFileCertPath(fileCertPath);
		}

		if (fileSignPath == null) {
			employeeImpl.setFileSignPath("");
		}
		else {
			employeeImpl.setFileSignPath(fileSignPath);
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
		employeeNo = objectInput.readUTF();
		fullName = objectInput.readUTF();
		title = objectInput.readUTF();

		gender = objectInput.readInt();
		birthdate = objectInput.readLong();
		telNo = objectInput.readUTF();
		mobile = objectInput.readUTF();
		email = objectInput.readUTF();

		workingStatus = objectInput.readInt();

		mappingUserId = objectInput.readLong();

		mainJobPostId = objectInput.readLong();

		photoFileEntryId = objectInput.readLong();
		recruitDate = objectInput.readLong();
		leaveDate = objectInput.readLong();

		fileCertId = objectInput.readLong();

		fileSignId = objectInput.readLong();
		fileCertPath = objectInput.readUTF();
		fileSignPath = objectInput.readUTF();
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

		objectOutput.writeLong(employeeId);

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

		if (employeeNo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(employeeNo);
		}

		if (fullName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(fullName);
		}

		if (title == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(title);
		}

		objectOutput.writeInt(gender);
		objectOutput.writeLong(birthdate);

		if (telNo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(telNo);
		}

		if (mobile == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(mobile);
		}

		if (email == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(email);
		}

		objectOutput.writeInt(workingStatus);

		objectOutput.writeLong(mappingUserId);

		objectOutput.writeLong(mainJobPostId);

		objectOutput.writeLong(photoFileEntryId);
		objectOutput.writeLong(recruitDate);
		objectOutput.writeLong(leaveDate);

		objectOutput.writeLong(fileCertId);

		objectOutput.writeLong(fileSignId);

		if (fileCertPath == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(fileCertPath);
		}

		if (fileSignPath == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(fileSignPath);
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
	public String employeeNo;
	public String fullName;
	public String title;
	public int gender;
	public long birthdate;
	public String telNo;
	public String mobile;
	public String email;
	public int workingStatus;
	public long mappingUserId;
	public long mainJobPostId;
	public long photoFileEntryId;
	public long recruitDate;
	public long leaveDate;
	public long fileCertId;
	public long fileSignId;
	public String fileCertPath;
	public String fileSignPath;
}