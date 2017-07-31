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

package org.mobilink.backend.usermgt.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Binhth
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
		soapModel.setFullName(model.getFullName());
		soapModel.setEmployeeNo(model.getEmployeeNo());
		soapModel.setGender(model.getGender());
		soapModel.setBirthDate(model.getBirthDate());
		soapModel.setTelNo(model.getTelNo());
		soapModel.setMobile(model.getMobile());
		soapModel.setEmail(model.getEmail());
		soapModel.setWorkingStatus(model.getWorkingStatus());
		soapModel.setMappingUserId(model.getMappingUserId());
		soapModel.setMainJobPostId(model.getMainJobPostId());
		soapModel.setPhotoFileEntryId(model.getPhotoFileEntryId());
		soapModel.setFileDocId(model.getFileDocId());
		soapModel.setPreferences(model.getPreferences());

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

	public String getFullName() {
		return _fullName;
	}

	public void setFullName(String fullName) {
		_fullName = fullName;
	}

	public String getEmployeeNo() {
		return _employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		_employeeNo = employeeNo;
	}

	public int getGender() {
		return _gender;
	}

	public void setGender(int gender) {
		_gender = gender;
	}

	public Date getBirthDate() {
		return _birthDate;
	}

	public void setBirthDate(Date birthDate) {
		_birthDate = birthDate;
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

	public long getFileDocId() {
		return _fileDocId;
	}

	public void setFileDocId(long fileDocId) {
		_fileDocId = fileDocId;
	}

	public String getPreferences() {
		return _preferences;
	}

	public void setPreferences(String preferences) {
		_preferences = preferences;
	}

	private String _uuid;
	private long _employeeId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _fullName;
	private String _employeeNo;
	private int _gender;
	private Date _birthDate;
	private String _telNo;
	private String _mobile;
	private String _email;
	private int _workingStatus;
	private long _mappingUserId;
	private long _mainJobPostId;
	private long _photoFileEntryId;
	private long _fileDocId;
	private String _preferences;
}