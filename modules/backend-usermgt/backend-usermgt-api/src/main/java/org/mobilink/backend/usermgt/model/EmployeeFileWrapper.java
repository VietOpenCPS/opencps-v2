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
 * This class is a wrapper for {@link EmployeeFile}.
 * </p>
 *
 * @author Binhth
 * @see EmployeeFile
 * @generated
 */
@ProviderType
public class EmployeeFileWrapper implements EmployeeFile,
	ModelWrapper<EmployeeFile> {
	public EmployeeFileWrapper(EmployeeFile employeeFile) {
		_employeeFile = employeeFile;
	}

	@Override
	public Class<?> getModelClass() {
		return EmployeeFile.class;
	}

	@Override
	public String getModelClassName() {
		return EmployeeFile.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("employeeFileId", getEmployeeFileId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("employeeId", getEmployeeId());
		attributes.put("fileEntryId", getFileEntryId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long employeeFileId = (Long)attributes.get("employeeFileId");

		if (employeeFileId != null) {
			setEmployeeFileId(employeeFileId);
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

		Long employeeId = (Long)attributes.get("employeeId");

		if (employeeId != null) {
			setEmployeeId(employeeId);
		}

		Long fileEntryId = (Long)attributes.get("fileEntryId");

		if (fileEntryId != null) {
			setFileEntryId(fileEntryId);
		}
	}

	@Override
	public EmployeeFile toEscapedModel() {
		return new EmployeeFileWrapper(_employeeFile.toEscapedModel());
	}

	@Override
	public EmployeeFile toUnescapedModel() {
		return new EmployeeFileWrapper(_employeeFile.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _employeeFile.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _employeeFile.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _employeeFile.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _employeeFile.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<EmployeeFile> toCacheModel() {
		return _employeeFile.toCacheModel();
	}

	@Override
	public int compareTo(EmployeeFile employeeFile) {
		return _employeeFile.compareTo(employeeFile);
	}

	@Override
	public int hashCode() {
		return _employeeFile.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _employeeFile.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new EmployeeFileWrapper((EmployeeFile)_employeeFile.clone());
	}

	/**
	* Returns the user name of this employee file.
	*
	* @return the user name of this employee file
	*/
	@Override
	public java.lang.String getUserName() {
		return _employeeFile.getUserName();
	}

	/**
	* Returns the user uuid of this employee file.
	*
	* @return the user uuid of this employee file
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _employeeFile.getUserUuid();
	}

	/**
	* Returns the uuid of this employee file.
	*
	* @return the uuid of this employee file
	*/
	@Override
	public java.lang.String getUuid() {
		return _employeeFile.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _employeeFile.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _employeeFile.toXmlString();
	}

	/**
	* Returns the create date of this employee file.
	*
	* @return the create date of this employee file
	*/
	@Override
	public Date getCreateDate() {
		return _employeeFile.getCreateDate();
	}

	/**
	* Returns the modified date of this employee file.
	*
	* @return the modified date of this employee file
	*/
	@Override
	public Date getModifiedDate() {
		return _employeeFile.getModifiedDate();
	}

	/**
	* Returns the company ID of this employee file.
	*
	* @return the company ID of this employee file
	*/
	@Override
	public long getCompanyId() {
		return _employeeFile.getCompanyId();
	}

	/**
	* Returns the employee file ID of this employee file.
	*
	* @return the employee file ID of this employee file
	*/
	@Override
	public long getEmployeeFileId() {
		return _employeeFile.getEmployeeFileId();
	}

	/**
	* Returns the employee ID of this employee file.
	*
	* @return the employee ID of this employee file
	*/
	@Override
	public long getEmployeeId() {
		return _employeeFile.getEmployeeId();
	}

	/**
	* Returns the file entry ID of this employee file.
	*
	* @return the file entry ID of this employee file
	*/
	@Override
	public long getFileEntryId() {
		return _employeeFile.getFileEntryId();
	}

	/**
	* Returns the group ID of this employee file.
	*
	* @return the group ID of this employee file
	*/
	@Override
	public long getGroupId() {
		return _employeeFile.getGroupId();
	}

	/**
	* Returns the primary key of this employee file.
	*
	* @return the primary key of this employee file
	*/
	@Override
	public long getPrimaryKey() {
		return _employeeFile.getPrimaryKey();
	}

	/**
	* Returns the user ID of this employee file.
	*
	* @return the user ID of this employee file
	*/
	@Override
	public long getUserId() {
		return _employeeFile.getUserId();
	}

	@Override
	public void persist() {
		_employeeFile.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_employeeFile.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this employee file.
	*
	* @param companyId the company ID of this employee file
	*/
	@Override
	public void setCompanyId(long companyId) {
		_employeeFile.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this employee file.
	*
	* @param createDate the create date of this employee file
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_employeeFile.setCreateDate(createDate);
	}

	/**
	* Sets the employee file ID of this employee file.
	*
	* @param employeeFileId the employee file ID of this employee file
	*/
	@Override
	public void setEmployeeFileId(long employeeFileId) {
		_employeeFile.setEmployeeFileId(employeeFileId);
	}

	/**
	* Sets the employee ID of this employee file.
	*
	* @param employeeId the employee ID of this employee file
	*/
	@Override
	public void setEmployeeId(long employeeId) {
		_employeeFile.setEmployeeId(employeeId);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_employeeFile.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_employeeFile.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_employeeFile.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the file entry ID of this employee file.
	*
	* @param fileEntryId the file entry ID of this employee file
	*/
	@Override
	public void setFileEntryId(long fileEntryId) {
		_employeeFile.setFileEntryId(fileEntryId);
	}

	/**
	* Sets the group ID of this employee file.
	*
	* @param groupId the group ID of this employee file
	*/
	@Override
	public void setGroupId(long groupId) {
		_employeeFile.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this employee file.
	*
	* @param modifiedDate the modified date of this employee file
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_employeeFile.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_employeeFile.setNew(n);
	}

	/**
	* Sets the primary key of this employee file.
	*
	* @param primaryKey the primary key of this employee file
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_employeeFile.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_employeeFile.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this employee file.
	*
	* @param userId the user ID of this employee file
	*/
	@Override
	public void setUserId(long userId) {
		_employeeFile.setUserId(userId);
	}

	/**
	* Sets the user name of this employee file.
	*
	* @param userName the user name of this employee file
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_employeeFile.setUserName(userName);
	}

	/**
	* Sets the user uuid of this employee file.
	*
	* @param userUuid the user uuid of this employee file
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_employeeFile.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this employee file.
	*
	* @param uuid the uuid of this employee file
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_employeeFile.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EmployeeFileWrapper)) {
			return false;
		}

		EmployeeFileWrapper employeeFileWrapper = (EmployeeFileWrapper)obj;

		if (Objects.equals(_employeeFile, employeeFileWrapper._employeeFile)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _employeeFile.getStagedModelType();
	}

	@Override
	public EmployeeFile getWrappedModel() {
		return _employeeFile;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _employeeFile.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _employeeFile.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_employeeFile.resetOriginalValues();
	}

	private final EmployeeFile _employeeFile;
}