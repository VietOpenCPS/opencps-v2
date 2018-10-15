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

package org.opencps.usermgt.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author khoavu
 * @generated
 */
@ProviderType
public class EmployeeSoap implements Serializable {
	public static EmployeeSoap toSoapModel(Employee model) {
		EmployeeSoap soapModel = new EmployeeSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setEmployeeId(model.getEmployeeId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setEmployeeNo(model.getEmployeeNo());
		soapModel.setFullName(model.getFullName());
		soapModel.setTitle(model.getTitle());
		soapModel.setGender(model.getGender());
		soapModel.setBirthdate(model.getBirthdate());
		soapModel.setTelNo(model.getTelNo());
		soapModel.setMobile(model.getMobile());
		soapModel.setEmail(model.getEmail());
		soapModel.setWorkingStatus(model.getWorkingStatus());
		soapModel.setMappingUserId(model.getMappingUserId());
		soapModel.setMainJobPostId(model.getMainJobPostId());
		soapModel.setPhotoFileEntryId(model.getPhotoFileEntryId());
		soapModel.setRecruitDate(model.getRecruitDate());
		soapModel.setLeaveDate(model.getLeaveDate());
		soapModel.setFileCertId(model.getFileCertId());
		soapModel.setFileSignId(model.getFileSignId());
		soapModel.setFileCertPath(model.getFileCertPath());
		soapModel.setFileSignPath(model.getFileSignPath());

		return soapModel;
	}

	public static EmployeeSoap[] toSoapModels(Employee[] models) {
		EmployeeSoap[] soapModels = new EmployeeSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static EmployeeSoap[][] toSoapModels(Employee[][] models) {
		EmployeeSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new EmployeeSoap[models.length][models[0].length];
		}
		else {
			soapModels = new EmployeeSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static EmployeeSoap[] toSoapModels(List<Employee> models) {
		List<EmployeeSoap> soapModels = new ArrayList<EmployeeSoap>(models.size());

		for (Employee model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new EmployeeSoap[soapModels.size()]);
	}

	public EmployeeSoap() {
	}

	public long getPrimaryKey() {
		return _employeeId;
	}

	public void setPrimaryKey(long pk) {
		setEmployeeId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getEmployeeId() {
		return _employeeId;
	}

	public void setEmployeeId(long employeeId) {
		_employeeId = employeeId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getEmployeeNo() {
		return _employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		_employeeNo = employeeNo;
	}

	public String getFullName() {
		return _fullName;
	}

	public void setFullName(String fullName) {
		_fullName = fullName;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public int getGender() {
		return _gender;
	}

	public void setGender(int gender) {
		_gender = gender;
	}

	public Date getBirthdate() {
		return _birthdate;
	}

	public void setBirthdate(Date birthdate) {
		_birthdate = birthdate;
	}

	public String getTelNo() {
		return _telNo;
	}

	public void setTelNo(String telNo) {
		_telNo = telNo;
	}

	public String getMobile() {
		return _mobile;
	}

	public void setMobile(String mobile) {
		_mobile = mobile;
	}

	public String getEmail() {
		return _email;
	}

	public void setEmail(String email) {
		_email = email;
	}

	public int getWorkingStatus() {
		return _workingStatus;
	}

	public void setWorkingStatus(int workingStatus) {
		_workingStatus = workingStatus;
	}

	public long getMappingUserId() {
		return _mappingUserId;
	}

	public void setMappingUserId(long mappingUserId) {
		_mappingUserId = mappingUserId;
	}

	public long getMainJobPostId() {
		return _mainJobPostId;
	}

	public void setMainJobPostId(long mainJobPostId) {
		_mainJobPostId = mainJobPostId;
	}

	public long getPhotoFileEntryId() {
		return _photoFileEntryId;
	}

	public void setPhotoFileEntryId(long photoFileEntryId) {
		_photoFileEntryId = photoFileEntryId;
	}

	public Date getRecruitDate() {
		return _recruitDate;
	}

	public void setRecruitDate(Date recruitDate) {
		_recruitDate = recruitDate;
	}

	public Date getLeaveDate() {
		return _leaveDate;
	}

	public void setLeaveDate(Date leaveDate) {
		_leaveDate = leaveDate;
	}

	public long getFileCertId() {
		return _fileCertId;
	}

	public void setFileCertId(long fileCertId) {
		_fileCertId = fileCertId;
	}

	public long getFileSignId() {
		return _fileSignId;
	}

	public void setFileSignId(long fileSignId) {
		_fileSignId = fileSignId;
	}

	public String getFileCertPath() {
		return _fileCertPath;
	}

	public void setFileCertPath(String fileCertPath) {
		_fileCertPath = fileCertPath;
	}

	public String getFileSignPath() {
		return _fileSignPath;
	}

	public void setFileSignPath(String fileSignPath) {
		_fileSignPath = fileSignPath;
	}

	private String _uuid;
	private long _employeeId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _employeeNo;
	private String _fullName;
	private String _title;
	private int _gender;
	private Date _birthdate;
	private String _telNo;
	private String _mobile;
	private String _email;
	private int _workingStatus;
	private long _mappingUserId;
	private long _mainJobPostId;
	private long _photoFileEntryId;
	private Date _recruitDate;
	private Date _leaveDate;
	private long _fileCertId;
	private long _fileSignId;
	private String _fileCertPath;
	private String _fileSignPath;
}