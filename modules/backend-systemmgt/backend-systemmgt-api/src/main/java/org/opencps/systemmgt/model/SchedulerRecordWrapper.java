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

package org.opencps.systemmgt.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link SchedulerRecord}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SchedulerRecord
 * @generated
 */
@ProviderType
public class SchedulerRecordWrapper implements SchedulerRecord,
	ModelWrapper<SchedulerRecord> {
	public SchedulerRecordWrapper(SchedulerRecord schedulerRecord) {
		_schedulerRecord = schedulerRecord;
	}

	@Override
	public Class<?> getModelClass() {
		return SchedulerRecord.class;
	}

	@Override
	public String getModelClassName() {
		return SchedulerRecord.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("schedulerId", getSchedulerId());
		attributes.put("schedulerType", getSchedulerType());
		attributes.put("onTime", getOnTime());
		attributes.put("nextTime", getNextTime());
		attributes.put("expiredTime", getExpiredTime());
		attributes.put("minDuration", getMinDuration());
		attributes.put("maxDuration", getMaxDuration());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long schedulerId = (Long)attributes.get("schedulerId");

		if (schedulerId != null) {
			setSchedulerId(schedulerId);
		}

		String schedulerType = (String)attributes.get("schedulerType");

		if (schedulerType != null) {
			setSchedulerType(schedulerType);
		}

		Date onTime = (Date)attributes.get("onTime");

		if (onTime != null) {
			setOnTime(onTime);
		}

		Date nextTime = (Date)attributes.get("nextTime");

		if (nextTime != null) {
			setNextTime(nextTime);
		}

		Date expiredTime = (Date)attributes.get("expiredTime");

		if (expiredTime != null) {
			setExpiredTime(expiredTime);
		}

		Long minDuration = (Long)attributes.get("minDuration");

		if (minDuration != null) {
			setMinDuration(minDuration);
		}

		Long maxDuration = (Long)attributes.get("maxDuration");

		if (maxDuration != null) {
			setMaxDuration(maxDuration);
		}
	}

	@Override
	public Object clone() {
		return new SchedulerRecordWrapper((SchedulerRecord)_schedulerRecord.clone());
	}

	@Override
	public int compareTo(SchedulerRecord schedulerRecord) {
		return _schedulerRecord.compareTo(schedulerRecord);
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _schedulerRecord.getExpandoBridge();
	}

	/**
	* Returns the expired time of this scheduler record.
	*
	* @return the expired time of this scheduler record
	*/
	@Override
	public Date getExpiredTime() {
		return _schedulerRecord.getExpiredTime();
	}

	/**
	* Returns the max duration of this scheduler record.
	*
	* @return the max duration of this scheduler record
	*/
	@Override
	public long getMaxDuration() {
		return _schedulerRecord.getMaxDuration();
	}

	/**
	* Returns the min duration of this scheduler record.
	*
	* @return the min duration of this scheduler record
	*/
	@Override
	public long getMinDuration() {
		return _schedulerRecord.getMinDuration();
	}

	/**
	* Returns the next time of this scheduler record.
	*
	* @return the next time of this scheduler record
	*/
	@Override
	public Date getNextTime() {
		return _schedulerRecord.getNextTime();
	}

	/**
	* Returns the on time of this scheduler record.
	*
	* @return the on time of this scheduler record
	*/
	@Override
	public Date getOnTime() {
		return _schedulerRecord.getOnTime();
	}

	/**
	* Returns the primary key of this scheduler record.
	*
	* @return the primary key of this scheduler record
	*/
	@Override
	public long getPrimaryKey() {
		return _schedulerRecord.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _schedulerRecord.getPrimaryKeyObj();
	}

	/**
	* Returns the scheduler ID of this scheduler record.
	*
	* @return the scheduler ID of this scheduler record
	*/
	@Override
	public long getSchedulerId() {
		return _schedulerRecord.getSchedulerId();
	}

	/**
	* Returns the scheduler type of this scheduler record.
	*
	* @return the scheduler type of this scheduler record
	*/
	@Override
	public String getSchedulerType() {
		return _schedulerRecord.getSchedulerType();
	}

	@Override
	public int hashCode() {
		return _schedulerRecord.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _schedulerRecord.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _schedulerRecord.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _schedulerRecord.isNew();
	}

	@Override
	public void persist() {
		_schedulerRecord.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_schedulerRecord.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_schedulerRecord.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_schedulerRecord.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_schedulerRecord.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the expired time of this scheduler record.
	*
	* @param expiredTime the expired time of this scheduler record
	*/
	@Override
	public void setExpiredTime(Date expiredTime) {
		_schedulerRecord.setExpiredTime(expiredTime);
	}

	/**
	* Sets the max duration of this scheduler record.
	*
	* @param maxDuration the max duration of this scheduler record
	*/
	@Override
	public void setMaxDuration(long maxDuration) {
		_schedulerRecord.setMaxDuration(maxDuration);
	}

	/**
	* Sets the min duration of this scheduler record.
	*
	* @param minDuration the min duration of this scheduler record
	*/
	@Override
	public void setMinDuration(long minDuration) {
		_schedulerRecord.setMinDuration(minDuration);
	}

	@Override
	public void setNew(boolean n) {
		_schedulerRecord.setNew(n);
	}

	/**
	* Sets the next time of this scheduler record.
	*
	* @param nextTime the next time of this scheduler record
	*/
	@Override
	public void setNextTime(Date nextTime) {
		_schedulerRecord.setNextTime(nextTime);
	}

	/**
	* Sets the on time of this scheduler record.
	*
	* @param onTime the on time of this scheduler record
	*/
	@Override
	public void setOnTime(Date onTime) {
		_schedulerRecord.setOnTime(onTime);
	}

	/**
	* Sets the primary key of this scheduler record.
	*
	* @param primaryKey the primary key of this scheduler record
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_schedulerRecord.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_schedulerRecord.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the scheduler ID of this scheduler record.
	*
	* @param schedulerId the scheduler ID of this scheduler record
	*/
	@Override
	public void setSchedulerId(long schedulerId) {
		_schedulerRecord.setSchedulerId(schedulerId);
	}

	/**
	* Sets the scheduler type of this scheduler record.
	*
	* @param schedulerType the scheduler type of this scheduler record
	*/
	@Override
	public void setSchedulerType(String schedulerType) {
		_schedulerRecord.setSchedulerType(schedulerType);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<SchedulerRecord> toCacheModel() {
		return _schedulerRecord.toCacheModel();
	}

	@Override
	public SchedulerRecord toEscapedModel() {
		return new SchedulerRecordWrapper(_schedulerRecord.toEscapedModel());
	}

	@Override
	public String toString() {
		return _schedulerRecord.toString();
	}

	@Override
	public SchedulerRecord toUnescapedModel() {
		return new SchedulerRecordWrapper(_schedulerRecord.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _schedulerRecord.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SchedulerRecordWrapper)) {
			return false;
		}

		SchedulerRecordWrapper schedulerRecordWrapper = (SchedulerRecordWrapper)obj;

		if (Objects.equals(_schedulerRecord,
					schedulerRecordWrapper._schedulerRecord)) {
			return true;
		}

		return false;
	}

	@Override
	public SchedulerRecord getWrappedModel() {
		return _schedulerRecord;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _schedulerRecord.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _schedulerRecord.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_schedulerRecord.resetOriginalValues();
	}

	private final SchedulerRecord _schedulerRecord;
}