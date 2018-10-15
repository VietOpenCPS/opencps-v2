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
 * This class is a wrapper for {@link EmployeeJobPos}.
 * </p>
 *
 * @author khoavu
 * @see EmployeeJobPos
 * @generated
 */
@ProviderType
public class EmployeeJobPosWrapper implements EmployeeJobPos,
	ModelWrapper<EmployeeJobPos> {
	public EmployeeJobPosWrapper(EmployeeJobPos employeeJobPos) {
		_employeeJobPos = employeeJobPos;
	}

	@Override
	public Class<?> getModelClass() {
		return EmployeeJobPos.class;
	}

	@Override
	public String getModelClassName() {
		return EmployeeJobPos.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("employeeJobPosId", getEmployeeJobPosId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("employeeId", getEmployeeId());
		attributes.put("jobPostId", getJobPostId());
		attributes.put("workingUnitId", getWorkingUnitId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long employeeJobPosId = (Long)attributes.get("employeeJobPosId");

		if (employeeJobPosId != null) {
			setEmployeeJobPosId(employeeJobPosId);
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

		Long jobPostId = (Long)attributes.get("jobPostId");

		if (jobPostId != null) {
			setJobPostId(jobPostId);
		}

		Long workingUnitId = (Long)attributes.get("workingUnitId");

		if (workingUnitId != null) {
			setWorkingUnitId(workingUnitId);
		}
	}

	@Override
	public Object clone() {
		return new EmployeeJobPosWrapper((EmployeeJobPos)_employeeJobPos.clone());
	}

	@Override
	public int compareTo(EmployeeJobPos employeeJobPos) {
		return _employeeJobPos.compareTo(employeeJobPos);
	}

	/**
	* Returns the company ID of this employee job pos.
	*
	* @return the company ID of this employee job pos
	*/
	@Override
	public long getCompanyId() {
		return _employeeJobPos.getCompanyId();
	}

	/**
	* Returns the create date of this employee job pos.
	*
	* @return the create date of this employee job pos
	*/
	@Override
	public Date getCreateDate() {
		return _employeeJobPos.getCreateDate();
	}

	/**
	* Returns the employee ID of this employee job pos.
	*
	* @return the employee ID of this employee job pos
	*/
	@Override
	public long getEmployeeId() {
		return _employeeJobPos.getEmployeeId();
	}

	/**
	* Returns the employee job pos ID of this employee job pos.
	*
	* @return the employee job pos ID of this employee job pos
	*/
	@Override
	public long getEmployeeJobPosId() {
		return _employeeJobPos.getEmployeeJobPosId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _employeeJobPos.getExpandoBridge();
	}

	/**
	* Returns the group ID of this employee job pos.
	*
	* @return the group ID of this employee job pos
	*/
	@Override
	public long getGroupId() {
		return _employeeJobPos.getGroupId();
	}

	/**
	* Returns the job post ID of this employee job pos.
	*
	* @return the job post ID of this employee job pos
	*/
	@Override
	public long getJobPostId() {
		return _employeeJobPos.getJobPostId();
	}

	/**
	* Returns the modified date of this employee job pos.
	*
	* @return the modified date of this employee job pos
	*/
	@Override
	public Date getModifiedDate() {
		return _employeeJobPos.getModifiedDate();
	}

	/**
	* Returns the primary key of this employee job pos.
	*
	* @return the primary key of this employee job pos
	*/
	@Override
	public long getPrimaryKey() {
		return _employeeJobPos.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _employeeJobPos.getPrimaryKeyObj();
	}

	/**
	* Returns the user ID of this employee job pos.
	*
	* @return the user ID of this employee job pos
	*/
	@Override
	public long getUserId() {
		return _employeeJobPos.getUserId();
	}

	/**
	* Returns the user name of this employee job pos.
	*
	* @return the user name of this employee job pos
	*/
	@Override
	public String getUserName() {
		return _employeeJobPos.getUserName();
	}

	/**
	* Returns the user uuid of this employee job pos.
	*
	* @return the user uuid of this employee job pos
	*/
	@Override
	public String getUserUuid() {
		return _employeeJobPos.getUserUuid();
	}

	/**
	* Returns the uuid of this employee job pos.
	*
	* @return the uuid of this employee job pos
	*/
	@Override
	public String getUuid() {
		return _employeeJobPos.getUuid();
	}

	/**
	* Returns the working unit ID of this employee job pos.
	*
	* @return the working unit ID of this employee job pos
	*/
	@Override
	public long getWorkingUnitId() {
		return _employeeJobPos.getWorkingUnitId();
	}

	@Override
	public int hashCode() {
		return _employeeJobPos.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _employeeJobPos.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _employeeJobPos.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _employeeJobPos.isNew();
	}

	@Override
	public void persist() {
		_employeeJobPos.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_employeeJobPos.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this employee job pos.
	*
	* @param companyId the company ID of this employee job pos
	*/
	@Override
	public void setCompanyId(long companyId) {
		_employeeJobPos.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this employee job pos.
	*
	* @param createDate the create date of this employee job pos
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_employeeJobPos.setCreateDate(createDate);
	}

	/**
	* Sets the employee ID of this employee job pos.
	*
	* @param employeeId the employee ID of this employee job pos
	*/
	@Override
	public void setEmployeeId(long employeeId) {
		_employeeJobPos.setEmployeeId(employeeId);
	}

	/**
	* Sets the employee job pos ID of this employee job pos.
	*
	* @param employeeJobPosId the employee job pos ID of this employee job pos
	*/
	@Override
	public void setEmployeeJobPosId(long employeeJobPosId) {
		_employeeJobPos.setEmployeeJobPosId(employeeJobPosId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_employeeJobPos.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_employeeJobPos.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_employeeJobPos.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this employee job pos.
	*
	* @param groupId the group ID of this employee job pos
	*/
	@Override
	public void setGroupId(long groupId) {
		_employeeJobPos.setGroupId(groupId);
	}

	/**
	* Sets the job post ID of this employee job pos.
	*
	* @param jobPostId the job post ID of this employee job pos
	*/
	@Override
	public void setJobPostId(long jobPostId) {
		_employeeJobPos.setJobPostId(jobPostId);
	}

	/**
	* Sets the modified date of this employee job pos.
	*
	* @param modifiedDate the modified date of this employee job pos
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_employeeJobPos.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_employeeJobPos.setNew(n);
	}

	/**
	* Sets the primary key of this employee job pos.
	*
	* @param primaryKey the primary key of this employee job pos
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_employeeJobPos.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_employeeJobPos.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this employee job pos.
	*
	* @param userId the user ID of this employee job pos
	*/
	@Override
	public void setUserId(long userId) {
		_employeeJobPos.setUserId(userId);
	}

	/**
	* Sets the user name of this employee job pos.
	*
	* @param userName the user name of this employee job pos
	*/
	@Override
	public void setUserName(String userName) {
		_employeeJobPos.setUserName(userName);
	}

	/**
	* Sets the user uuid of this employee job pos.
	*
	* @param userUuid the user uuid of this employee job pos
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_employeeJobPos.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this employee job pos.
	*
	* @param uuid the uuid of this employee job pos
	*/
	@Override
	public void setUuid(String uuid) {
		_employeeJobPos.setUuid(uuid);
	}

	/**
	* Sets the working unit ID of this employee job pos.
	*
	* @param workingUnitId the working unit ID of this employee job pos
	*/
	@Override
	public void setWorkingUnitId(long workingUnitId) {
		_employeeJobPos.setWorkingUnitId(workingUnitId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<EmployeeJobPos> toCacheModel() {
		return _employeeJobPos.toCacheModel();
	}

	@Override
	public EmployeeJobPos toEscapedModel() {
		return new EmployeeJobPosWrapper(_employeeJobPos.toEscapedModel());
	}

	@Override
	public String toString() {
		return _employeeJobPos.toString();
	}

	@Override
	public EmployeeJobPos toUnescapedModel() {
		return new EmployeeJobPosWrapper(_employeeJobPos.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _employeeJobPos.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EmployeeJobPosWrapper)) {
			return false;
		}

		EmployeeJobPosWrapper employeeJobPosWrapper = (EmployeeJobPosWrapper)obj;

		if (Objects.equals(_employeeJobPos,
					employeeJobPosWrapper._employeeJobPos)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _employeeJobPos.getStagedModelType();
	}

	@Override
	public EmployeeJobPos getWrappedModel() {
		return _employeeJobPos;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _employeeJobPos.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _employeeJobPos.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_employeeJobPos.resetOriginalValues();
	}

	private final EmployeeJobPos _employeeJobPos;
}