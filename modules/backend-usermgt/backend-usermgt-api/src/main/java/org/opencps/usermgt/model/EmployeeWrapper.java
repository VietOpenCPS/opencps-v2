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

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link Employee}.
 * </p>
 *
 * @author khoavu
 * @see Employee
 * @generated
 */
@ProviderType
public class EmployeeWrapper implements Employee, ModelWrapper<Employee> {
	public EmployeeWrapper(Employee employee) {
		_employee = employee;
	}

	@Override
	public Class<?> getModelClass() {
		return Employee.class;
	}

	@Override
	public String getModelClassName() {
		return Employee.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("employeeId", getEmployeeId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("employeeNo", getEmployeeNo());
		attributes.put("fullName", getFullName());
		attributes.put("title", getTitle());
		attributes.put("gender", getGender());
		attributes.put("birthdate", getBirthdate());
		attributes.put("telNo", getTelNo());
		attributes.put("mobile", getMobile());
		attributes.put("email", getEmail());
		attributes.put("workingStatus", getWorkingStatus());
		attributes.put("mappingUserId", getMappingUserId());
		attributes.put("mainJobPostId", getMainJobPostId());
		attributes.put("photoFileEntryId", getPhotoFileEntryId());
		attributes.put("recruitDate", getRecruitDate());
		attributes.put("leaveDate", getLeaveDate());
		attributes.put("fileCertId", getFileCertId());
		attributes.put("fileSignId", getFileSignId());
		attributes.put("fileCertPath", getFileCertPath());
		attributes.put("fileSignPath", getFileSignPath());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long employeeId = (Long)attributes.get("employeeId");

		if (employeeId != null) {
			setEmployeeId(employeeId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String employeeNo = (String)attributes.get("employeeNo");

		if (employeeNo != null) {
			setEmployeeNo(employeeNo);
		}

		String fullName = (String)attributes.get("fullName");

		if (fullName != null) {
			setFullName(fullName);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		Integer gender = (Integer)attributes.get("gender");

		if (gender != null) {
			setGender(gender);
		}

		Date birthdate = (Date)attributes.get("birthdate");

		if (birthdate != null) {
			setBirthdate(birthdate);
		}

		String telNo = (String)attributes.get("telNo");

		if (telNo != null) {
			setTelNo(telNo);
		}

		String mobile = (String)attributes.get("mobile");

		if (mobile != null) {
			setMobile(mobile);
		}

		String email = (String)attributes.get("email");

		if (email != null) {
			setEmail(email);
		}

		Integer workingStatus = (Integer)attributes.get("workingStatus");

		if (workingStatus != null) {
			setWorkingStatus(workingStatus);
		}

		Long mappingUserId = (Long)attributes.get("mappingUserId");

		if (mappingUserId != null) {
			setMappingUserId(mappingUserId);
		}

		Long mainJobPostId = (Long)attributes.get("mainJobPostId");

		if (mainJobPostId != null) {
			setMainJobPostId(mainJobPostId);
		}

		Long photoFileEntryId = (Long)attributes.get("photoFileEntryId");

		if (photoFileEntryId != null) {
			setPhotoFileEntryId(photoFileEntryId);
		}

		Date recruitDate = (Date)attributes.get("recruitDate");

		if (recruitDate != null) {
			setRecruitDate(recruitDate);
		}

		Date leaveDate = (Date)attributes.get("leaveDate");

		if (leaveDate != null) {
			setLeaveDate(leaveDate);
		}

		Long fileCertId = (Long)attributes.get("fileCertId");

		if (fileCertId != null) {
			setFileCertId(fileCertId);
		}

		Long fileSignId = (Long)attributes.get("fileSignId");

		if (fileSignId != null) {
			setFileSignId(fileSignId);
		}

		String fileCertPath = (String)attributes.get("fileCertPath");

		if (fileCertPath != null) {
			setFileCertPath(fileCertPath);
		}

		String fileSignPath = (String)attributes.get("fileSignPath");

		if (fileSignPath != null) {
			setFileSignPath(fileSignPath);
		}
	}

	@Override
	public Object clone() {
		return new EmployeeWrapper((Employee)_employee.clone());
	}

	@Override
	public int compareTo(Employee employee) {
		return _employee.compareTo(employee);
	}

	/**
	* Returns the birthdate of this employee.
	*
	* @return the birthdate of this employee
	*/
	@Override
	public Date getBirthdate() {
		return _employee.getBirthdate();
	}

	/**
	* Returns the company ID of this employee.
	*
	* @return the company ID of this employee
	*/
	@Override
	public long getCompanyId() {
		return _employee.getCompanyId();
	}

	/**
	* Returns the create date of this employee.
	*
	* @return the create date of this employee
	*/
	@Override
	public Date getCreateDate() {
		return _employee.getCreateDate();
	}

	/**
	* Returns the email of this employee.
	*
	* @return the email of this employee
	*/
	@Override
	public String getEmail() {
		return _employee.getEmail();
	}

	/**
	* Returns the employee ID of this employee.
	*
	* @return the employee ID of this employee
	*/
	@Override
	public long getEmployeeId() {
		return _employee.getEmployeeId();
	}

	/**
	* Returns the employee no of this employee.
	*
	* @return the employee no of this employee
	*/
	@Override
	public String getEmployeeNo() {
		return _employee.getEmployeeNo();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _employee.getExpandoBridge();
	}

	/**
	* Returns the file cert ID of this employee.
	*
	* @return the file cert ID of this employee
	*/
	@Override
	public long getFileCertId() {
		return _employee.getFileCertId();
	}

	/**
	* Returns the file cert path of this employee.
	*
	* @return the file cert path of this employee
	*/
	@Override
	public String getFileCertPath() {
		return _employee.getFileCertPath();
	}

	/**
	* Returns the file sign ID of this employee.
	*
	* @return the file sign ID of this employee
	*/
	@Override
	public long getFileSignId() {
		return _employee.getFileSignId();
	}

	/**
	* Returns the file sign path of this employee.
	*
	* @return the file sign path of this employee
	*/
	@Override
	public String getFileSignPath() {
		return _employee.getFileSignPath();
	}

	/**
	* Returns the full name of this employee.
	*
	* @return the full name of this employee
	*/
	@Override
	public String getFullName() {
		return _employee.getFullName();
	}

	/**
	* Returns the gender of this employee.
	*
	* @return the gender of this employee
	*/
	@Override
	public int getGender() {
		return _employee.getGender();
	}

	/**
	* Returns the group ID of this employee.
	*
	* @return the group ID of this employee
	*/
	@Override
	public long getGroupId() {
		return _employee.getGroupId();
	}

	/**
	* Returns the leave date of this employee.
	*
	* @return the leave date of this employee
	*/
	@Override
	public Date getLeaveDate() {
		return _employee.getLeaveDate();
	}

	/**
	* Returns the main job post ID of this employee.
	*
	* @return the main job post ID of this employee
	*/
	@Override
	public long getMainJobPostId() {
		return _employee.getMainJobPostId();
	}

	/**
	* Returns the mapping user ID of this employee.
	*
	* @return the mapping user ID of this employee
	*/
	@Override
	public long getMappingUserId() {
		return _employee.getMappingUserId();
	}

	/**
	* Returns the mapping user uuid of this employee.
	*
	* @return the mapping user uuid of this employee
	*/
	@Override
	public String getMappingUserUuid() {
		return _employee.getMappingUserUuid();
	}

	/**
	* Returns the mobile of this employee.
	*
	* @return the mobile of this employee
	*/
	@Override
	public String getMobile() {
		return _employee.getMobile();
	}

	/**
	* Returns the modified date of this employee.
	*
	* @return the modified date of this employee
	*/
	@Override
	public Date getModifiedDate() {
		return _employee.getModifiedDate();
	}

	/**
	* Returns the photo file entry ID of this employee.
	*
	* @return the photo file entry ID of this employee
	*/
	@Override
	public long getPhotoFileEntryId() {
		return _employee.getPhotoFileEntryId();
	}

	/**
	* Returns the primary key of this employee.
	*
	* @return the primary key of this employee
	*/
	@Override
	public long getPrimaryKey() {
		return _employee.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _employee.getPrimaryKeyObj();
	}

	/**
	* Returns the recruit date of this employee.
	*
	* @return the recruit date of this employee
	*/
	@Override
	public Date getRecruitDate() {
		return _employee.getRecruitDate();
	}

	/**
	* Returns the tel no of this employee.
	*
	* @return the tel no of this employee
	*/
	@Override
	public String getTelNo() {
		return _employee.getTelNo();
	}

	/**
	* Returns the title of this employee.
	*
	* @return the title of this employee
	*/
	@Override
	public String getTitle() {
		return _employee.getTitle();
	}

	/**
	* Returns the user ID of this employee.
	*
	* @return the user ID of this employee
	*/
	@Override
	public long getUserId() {
		return _employee.getUserId();
	}

	/**
	* Returns the user name of this employee.
	*
	* @return the user name of this employee
	*/
	@Override
	public String getUserName() {
		return _employee.getUserName();
	}

	/**
	* Returns the user uuid of this employee.
	*
	* @return the user uuid of this employee
	*/
	@Override
	public String getUserUuid() {
		return _employee.getUserUuid();
	}

	/**
	* Returns the uuid of this employee.
	*
	* @return the uuid of this employee
	*/
	@Override
	public String getUuid() {
		return _employee.getUuid();
	}

	/**
	* Returns the working status of this employee.
	*
	* @return the working status of this employee
	*/
	@Override
	public int getWorkingStatus() {
		return _employee.getWorkingStatus();
	}

	@Override
	public int hashCode() {
		return _employee.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _employee.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _employee.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _employee.isNew();
	}

	@Override
	public void persist() {
		_employee.persist();
	}

	/**
	* Sets the birthdate of this employee.
	*
	* @param birthdate the birthdate of this employee
	*/
	@Override
	public void setBirthdate(Date birthdate) {
		_employee.setBirthdate(birthdate);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_employee.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this employee.
	*
	* @param companyId the company ID of this employee
	*/
	@Override
	public void setCompanyId(long companyId) {
		_employee.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this employee.
	*
	* @param createDate the create date of this employee
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_employee.setCreateDate(createDate);
	}

	/**
	* Sets the email of this employee.
	*
	* @param email the email of this employee
	*/
	@Override
	public void setEmail(String email) {
		_employee.setEmail(email);
	}

	/**
	* Sets the employee ID of this employee.
	*
	* @param employeeId the employee ID of this employee
	*/
	@Override
	public void setEmployeeId(long employeeId) {
		_employee.setEmployeeId(employeeId);
	}

	/**
	* Sets the employee no of this employee.
	*
	* @param employeeNo the employee no of this employee
	*/
	@Override
	public void setEmployeeNo(String employeeNo) {
		_employee.setEmployeeNo(employeeNo);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_employee.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_employee.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_employee.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the file cert ID of this employee.
	*
	* @param fileCertId the file cert ID of this employee
	*/
	@Override
	public void setFileCertId(long fileCertId) {
		_employee.setFileCertId(fileCertId);
	}

	/**
	* Sets the file cert path of this employee.
	*
	* @param fileCertPath the file cert path of this employee
	*/
	@Override
	public void setFileCertPath(String fileCertPath) {
		_employee.setFileCertPath(fileCertPath);
	}

	/**
	* Sets the file sign ID of this employee.
	*
	* @param fileSignId the file sign ID of this employee
	*/
	@Override
	public void setFileSignId(long fileSignId) {
		_employee.setFileSignId(fileSignId);
	}

	/**
	* Sets the file sign path of this employee.
	*
	* @param fileSignPath the file sign path of this employee
	*/
	@Override
	public void setFileSignPath(String fileSignPath) {
		_employee.setFileSignPath(fileSignPath);
	}

	/**
	* Sets the full name of this employee.
	*
	* @param fullName the full name of this employee
	*/
	@Override
	public void setFullName(String fullName) {
		_employee.setFullName(fullName);
	}

	/**
	* Sets the gender of this employee.
	*
	* @param gender the gender of this employee
	*/
	@Override
	public void setGender(int gender) {
		_employee.setGender(gender);
	}

	/**
	* Sets the group ID of this employee.
	*
	* @param groupId the group ID of this employee
	*/
	@Override
	public void setGroupId(long groupId) {
		_employee.setGroupId(groupId);
	}

	/**
	* Sets the leave date of this employee.
	*
	* @param leaveDate the leave date of this employee
	*/
	@Override
	public void setLeaveDate(Date leaveDate) {
		_employee.setLeaveDate(leaveDate);
	}

	/**
	* Sets the main job post ID of this employee.
	*
	* @param mainJobPostId the main job post ID of this employee
	*/
	@Override
	public void setMainJobPostId(long mainJobPostId) {
		_employee.setMainJobPostId(mainJobPostId);
	}

	/**
	* Sets the mapping user ID of this employee.
	*
	* @param mappingUserId the mapping user ID of this employee
	*/
	@Override
	public void setMappingUserId(long mappingUserId) {
		_employee.setMappingUserId(mappingUserId);
	}

	/**
	* Sets the mapping user uuid of this employee.
	*
	* @param mappingUserUuid the mapping user uuid of this employee
	*/
	@Override
	public void setMappingUserUuid(String mappingUserUuid) {
		_employee.setMappingUserUuid(mappingUserUuid);
	}

	/**
	* Sets the mobile of this employee.
	*
	* @param mobile the mobile of this employee
	*/
	@Override
	public void setMobile(String mobile) {
		_employee.setMobile(mobile);
	}

	/**
	* Sets the modified date of this employee.
	*
	* @param modifiedDate the modified date of this employee
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_employee.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_employee.setNew(n);
	}

	/**
	* Sets the photo file entry ID of this employee.
	*
	* @param photoFileEntryId the photo file entry ID of this employee
	*/
	@Override
	public void setPhotoFileEntryId(long photoFileEntryId) {
		_employee.setPhotoFileEntryId(photoFileEntryId);
	}

	/**
	* Sets the primary key of this employee.
	*
	* @param primaryKey the primary key of this employee
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_employee.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_employee.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the recruit date of this employee.
	*
	* @param recruitDate the recruit date of this employee
	*/
	@Override
	public void setRecruitDate(Date recruitDate) {
		_employee.setRecruitDate(recruitDate);
	}

	/**
	* Sets the tel no of this employee.
	*
	* @param telNo the tel no of this employee
	*/
	@Override
	public void setTelNo(String telNo) {
		_employee.setTelNo(telNo);
	}

	/**
	* Sets the title of this employee.
	*
	* @param title the title of this employee
	*/
	@Override
	public void setTitle(String title) {
		_employee.setTitle(title);
	}

	/**
	* Sets the user ID of this employee.
	*
	* @param userId the user ID of this employee
	*/
	@Override
	public void setUserId(long userId) {
		_employee.setUserId(userId);
	}

	/**
	* Sets the user name of this employee.
	*
	* @param userName the user name of this employee
	*/
	@Override
	public void setUserName(String userName) {
		_employee.setUserName(userName);
	}

	/**
	* Sets the user uuid of this employee.
	*
	* @param userUuid the user uuid of this employee
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_employee.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this employee.
	*
	* @param uuid the uuid of this employee
	*/
	@Override
	public void setUuid(String uuid) {
		_employee.setUuid(uuid);
	}

	/**
	* Sets the working status of this employee.
	*
	* @param workingStatus the working status of this employee
	*/
	@Override
	public void setWorkingStatus(int workingStatus) {
		_employee.setWorkingStatus(workingStatus);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Employee> toCacheModel() {
		return _employee.toCacheModel();
	}

	@Override
	public Employee toEscapedModel() {
		return new EmployeeWrapper(_employee.toEscapedModel());
	}

	@Override
	public String toString() {
		return _employee.toString();
	}

	@Override
	public Employee toUnescapedModel() {
		return new EmployeeWrapper(_employee.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _employee.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EmployeeWrapper)) {
			return false;
		}

		EmployeeWrapper employeeWrapper = (EmployeeWrapper)obj;

		if (Objects.equals(_employee, employeeWrapper._employee)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _employee.getStagedModelType();
	}

	@Override
	public Employee getWrappedModel() {
		return _employee;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _employee.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _employee.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_employee.resetOriginalValues();
	}

	private final Employee _employee;
}