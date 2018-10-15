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
 * This class is a wrapper for {@link Holiday}.
 * </p>
 *
 * @author khoavu
 * @see Holiday
 * @generated
 */
@ProviderType
public class HolidayWrapper implements Holiday, ModelWrapper<Holiday> {
	public HolidayWrapper(Holiday holiday) {
		_holiday = holiday;
	}

	@Override
	public Class<?> getModelClass() {
		return Holiday.class;
	}

	@Override
	public String getModelClassName() {
		return Holiday.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("holidayId", getHolidayId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("holidayDate", getHolidayDate());
		attributes.put("description", getDescription());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long holidayId = (Long)attributes.get("holidayId");

		if (holidayId != null) {
			setHolidayId(holidayId);
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

		Date holidayDate = (Date)attributes.get("holidayDate");

		if (holidayDate != null) {
			setHolidayDate(holidayDate);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}
	}

	@Override
	public Object clone() {
		return new HolidayWrapper((Holiday)_holiday.clone());
	}

	@Override
	public int compareTo(Holiday holiday) {
		return _holiday.compareTo(holiday);
	}

	/**
	* Returns the company ID of this holiday.
	*
	* @return the company ID of this holiday
	*/
	@Override
	public long getCompanyId() {
		return _holiday.getCompanyId();
	}

	/**
	* Returns the create date of this holiday.
	*
	* @return the create date of this holiday
	*/
	@Override
	public Date getCreateDate() {
		return _holiday.getCreateDate();
	}

	/**
	* Returns the description of this holiday.
	*
	* @return the description of this holiday
	*/
	@Override
	public String getDescription() {
		return _holiday.getDescription();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _holiday.getExpandoBridge();
	}

	/**
	* Returns the group ID of this holiday.
	*
	* @return the group ID of this holiday
	*/
	@Override
	public long getGroupId() {
		return _holiday.getGroupId();
	}

	/**
	* Returns the holiday date of this holiday.
	*
	* @return the holiday date of this holiday
	*/
	@Override
	public Date getHolidayDate() {
		return _holiday.getHolidayDate();
	}

	/**
	* Returns the holiday ID of this holiday.
	*
	* @return the holiday ID of this holiday
	*/
	@Override
	public long getHolidayId() {
		return _holiday.getHolidayId();
	}

	/**
	* Returns the modified date of this holiday.
	*
	* @return the modified date of this holiday
	*/
	@Override
	public Date getModifiedDate() {
		return _holiday.getModifiedDate();
	}

	/**
	* Returns the primary key of this holiday.
	*
	* @return the primary key of this holiday
	*/
	@Override
	public long getPrimaryKey() {
		return _holiday.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _holiday.getPrimaryKeyObj();
	}

	/**
	* Returns the user ID of this holiday.
	*
	* @return the user ID of this holiday
	*/
	@Override
	public long getUserId() {
		return _holiday.getUserId();
	}

	/**
	* Returns the user name of this holiday.
	*
	* @return the user name of this holiday
	*/
	@Override
	public String getUserName() {
		return _holiday.getUserName();
	}

	/**
	* Returns the user uuid of this holiday.
	*
	* @return the user uuid of this holiday
	*/
	@Override
	public String getUserUuid() {
		return _holiday.getUserUuid();
	}

	/**
	* Returns the uuid of this holiday.
	*
	* @return the uuid of this holiday
	*/
	@Override
	public String getUuid() {
		return _holiday.getUuid();
	}

	@Override
	public int hashCode() {
		return _holiday.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _holiday.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _holiday.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _holiday.isNew();
	}

	@Override
	public void persist() {
		_holiday.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_holiday.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this holiday.
	*
	* @param companyId the company ID of this holiday
	*/
	@Override
	public void setCompanyId(long companyId) {
		_holiday.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this holiday.
	*
	* @param createDate the create date of this holiday
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_holiday.setCreateDate(createDate);
	}

	/**
	* Sets the description of this holiday.
	*
	* @param description the description of this holiday
	*/
	@Override
	public void setDescription(String description) {
		_holiday.setDescription(description);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_holiday.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_holiday.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_holiday.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this holiday.
	*
	* @param groupId the group ID of this holiday
	*/
	@Override
	public void setGroupId(long groupId) {
		_holiday.setGroupId(groupId);
	}

	/**
	* Sets the holiday date of this holiday.
	*
	* @param holidayDate the holiday date of this holiday
	*/
	@Override
	public void setHolidayDate(Date holidayDate) {
		_holiday.setHolidayDate(holidayDate);
	}

	/**
	* Sets the holiday ID of this holiday.
	*
	* @param holidayId the holiday ID of this holiday
	*/
	@Override
	public void setHolidayId(long holidayId) {
		_holiday.setHolidayId(holidayId);
	}

	/**
	* Sets the modified date of this holiday.
	*
	* @param modifiedDate the modified date of this holiday
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_holiday.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_holiday.setNew(n);
	}

	/**
	* Sets the primary key of this holiday.
	*
	* @param primaryKey the primary key of this holiday
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_holiday.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_holiday.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this holiday.
	*
	* @param userId the user ID of this holiday
	*/
	@Override
	public void setUserId(long userId) {
		_holiday.setUserId(userId);
	}

	/**
	* Sets the user name of this holiday.
	*
	* @param userName the user name of this holiday
	*/
	@Override
	public void setUserName(String userName) {
		_holiday.setUserName(userName);
	}

	/**
	* Sets the user uuid of this holiday.
	*
	* @param userUuid the user uuid of this holiday
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_holiday.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this holiday.
	*
	* @param uuid the uuid of this holiday
	*/
	@Override
	public void setUuid(String uuid) {
		_holiday.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Holiday> toCacheModel() {
		return _holiday.toCacheModel();
	}

	@Override
	public Holiday toEscapedModel() {
		return new HolidayWrapper(_holiday.toEscapedModel());
	}

	@Override
	public String toString() {
		return _holiday.toString();
	}

	@Override
	public Holiday toUnescapedModel() {
		return new HolidayWrapper(_holiday.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _holiday.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof HolidayWrapper)) {
			return false;
		}

		HolidayWrapper holidayWrapper = (HolidayWrapper)obj;

		if (Objects.equals(_holiday, holidayWrapper._holiday)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _holiday.getStagedModelType();
	}

	@Override
	public Holiday getWrappedModel() {
		return _holiday;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _holiday.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _holiday.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_holiday.resetOriginalValues();
	}

	private final Holiday _holiday;
}