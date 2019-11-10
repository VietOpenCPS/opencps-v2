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

package org.opencps.datamgt.model;

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
 * This class is a wrapper for {@link WorkTime}.
 * </p>
 *
 * @author khoavu
 * @see WorkTime
 * @generated
 */
@ProviderType
public class WorkTimeWrapper implements WorkTime, ModelWrapper<WorkTime> {
	public WorkTimeWrapper(WorkTime workTime) {
		_workTime = workTime;
	}

	@Override
	public Class<?> getModelClass() {
		return WorkTime.class;
	}

	@Override
	public String getModelClassName() {
		return WorkTime.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("workTimeId", getWorkTimeId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("day", getDay());
		attributes.put("hours", getHours());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long workTimeId = (Long)attributes.get("workTimeId");

		if (workTimeId != null) {
			setWorkTimeId(workTimeId);
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

		Integer day = (Integer)attributes.get("day");

		if (day != null) {
			setDay(day);
		}

		String hours = (String)attributes.get("hours");

		if (hours != null) {
			setHours(hours);
		}
	}

	@Override
	public Object clone() {
		return new WorkTimeWrapper((WorkTime)_workTime.clone());
	}

	@Override
	public int compareTo(WorkTime workTime) {
		return _workTime.compareTo(workTime);
	}

	/**
	* Returns the company ID of this work time.
	*
	* @return the company ID of this work time
	*/
	@Override
	public long getCompanyId() {
		return _workTime.getCompanyId();
	}

	/**
	* Returns the create date of this work time.
	*
	* @return the create date of this work time
	*/
	@Override
	public Date getCreateDate() {
		return _workTime.getCreateDate();
	}

	/**
	* Returns the day of this work time.
	*
	* @return the day of this work time
	*/
	@Override
	public int getDay() {
		return _workTime.getDay();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _workTime.getExpandoBridge();
	}

	/**
	* Returns the group ID of this work time.
	*
	* @return the group ID of this work time
	*/
	@Override
	public long getGroupId() {
		return _workTime.getGroupId();
	}

	/**
	* Returns the hours of this work time.
	*
	* @return the hours of this work time
	*/
	@Override
	public String getHours() {
		return _workTime.getHours();
	}

	/**
	* Returns the modified date of this work time.
	*
	* @return the modified date of this work time
	*/
	@Override
	public Date getModifiedDate() {
		return _workTime.getModifiedDate();
	}

	/**
	* Returns the primary key of this work time.
	*
	* @return the primary key of this work time
	*/
	@Override
	public long getPrimaryKey() {
		return _workTime.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _workTime.getPrimaryKeyObj();
	}

	/**
	* Returns the user ID of this work time.
	*
	* @return the user ID of this work time
	*/
	@Override
	public long getUserId() {
		return _workTime.getUserId();
	}

	/**
	* Returns the user name of this work time.
	*
	* @return the user name of this work time
	*/
	@Override
	public String getUserName() {
		return _workTime.getUserName();
	}

	/**
	* Returns the user uuid of this work time.
	*
	* @return the user uuid of this work time
	*/
	@Override
	public String getUserUuid() {
		return _workTime.getUserUuid();
	}

	/**
	* Returns the uuid of this work time.
	*
	* @return the uuid of this work time
	*/
	@Override
	public String getUuid() {
		return _workTime.getUuid();
	}

	/**
	* Returns the work time ID of this work time.
	*
	* @return the work time ID of this work time
	*/
	@Override
	public long getWorkTimeId() {
		return _workTime.getWorkTimeId();
	}

	@Override
	public int hashCode() {
		return _workTime.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _workTime.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _workTime.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _workTime.isNew();
	}

	@Override
	public void persist() {
		_workTime.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_workTime.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this work time.
	*
	* @param companyId the company ID of this work time
	*/
	@Override
	public void setCompanyId(long companyId) {
		_workTime.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this work time.
	*
	* @param createDate the create date of this work time
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_workTime.setCreateDate(createDate);
	}

	/**
	* Sets the day of this work time.
	*
	* @param day the day of this work time
	*/
	@Override
	public void setDay(int day) {
		_workTime.setDay(day);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_workTime.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_workTime.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_workTime.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this work time.
	*
	* @param groupId the group ID of this work time
	*/
	@Override
	public void setGroupId(long groupId) {
		_workTime.setGroupId(groupId);
	}

	/**
	* Sets the hours of this work time.
	*
	* @param hours the hours of this work time
	*/
	@Override
	public void setHours(String hours) {
		_workTime.setHours(hours);
	}

	/**
	* Sets the modified date of this work time.
	*
	* @param modifiedDate the modified date of this work time
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_workTime.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_workTime.setNew(n);
	}

	/**
	* Sets the primary key of this work time.
	*
	* @param primaryKey the primary key of this work time
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_workTime.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_workTime.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this work time.
	*
	* @param userId the user ID of this work time
	*/
	@Override
	public void setUserId(long userId) {
		_workTime.setUserId(userId);
	}

	/**
	* Sets the user name of this work time.
	*
	* @param userName the user name of this work time
	*/
	@Override
	public void setUserName(String userName) {
		_workTime.setUserName(userName);
	}

	/**
	* Sets the user uuid of this work time.
	*
	* @param userUuid the user uuid of this work time
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_workTime.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this work time.
	*
	* @param uuid the uuid of this work time
	*/
	@Override
	public void setUuid(String uuid) {
		_workTime.setUuid(uuid);
	}

	/**
	* Sets the work time ID of this work time.
	*
	* @param workTimeId the work time ID of this work time
	*/
	@Override
	public void setWorkTimeId(long workTimeId) {
		_workTime.setWorkTimeId(workTimeId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<WorkTime> toCacheModel() {
		return _workTime.toCacheModel();
	}

	@Override
	public WorkTime toEscapedModel() {
		return new WorkTimeWrapper(_workTime.toEscapedModel());
	}

	@Override
	public String toString() {
		return _workTime.toString();
	}

	@Override
	public WorkTime toUnescapedModel() {
		return new WorkTimeWrapper(_workTime.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _workTime.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WorkTimeWrapper)) {
			return false;
		}

		WorkTimeWrapper workTimeWrapper = (WorkTimeWrapper)obj;

		if (Objects.equals(_workTime, workTimeWrapper._workTime)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _workTime.getStagedModelType();
	}

	@Override
	public WorkTime getWrappedModel() {
		return _workTime;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _workTime.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _workTime.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_workTime.resetOriginalValues();
	}

	private final WorkTime _workTime;
}