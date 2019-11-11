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

package org.opencps.dossiermgt.model;

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
 * This class is a wrapper for {@link ProcessSequence}.
 * </p>
 *
 * @author huymq
 * @see ProcessSequence
 * @generated
 */
@ProviderType
public class ProcessSequenceWrapper implements ProcessSequence,
	ModelWrapper<ProcessSequence> {
	public ProcessSequenceWrapper(ProcessSequence processSequence) {
		_processSequence = processSequence;
	}

	@Override
	public Class<?> getModelClass() {
		return ProcessSequence.class;
	}

	@Override
	public String getModelClassName() {
		return ProcessSequence.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("processSequenceId", getProcessSequenceId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("serviceProcessId", getServiceProcessId());
		attributes.put("sequenceNo", getSequenceNo());
		attributes.put("sequenceName", getSequenceName());
		attributes.put("sequenceRole", getSequenceRole());
		attributes.put("durationCount", getDurationCount());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long processSequenceId = (Long)attributes.get("processSequenceId");

		if (processSequenceId != null) {
			setProcessSequenceId(processSequenceId);
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

		Long serviceProcessId = (Long)attributes.get("serviceProcessId");

		if (serviceProcessId != null) {
			setServiceProcessId(serviceProcessId);
		}

		String sequenceNo = (String)attributes.get("sequenceNo");

		if (sequenceNo != null) {
			setSequenceNo(sequenceNo);
		}

		String sequenceName = (String)attributes.get("sequenceName");

		if (sequenceName != null) {
			setSequenceName(sequenceName);
		}

		String sequenceRole = (String)attributes.get("sequenceRole");

		if (sequenceRole != null) {
			setSequenceRole(sequenceRole);
		}

		Double durationCount = (Double)attributes.get("durationCount");

		if (durationCount != null) {
			setDurationCount(durationCount);
		}
	}

	@Override
	public Object clone() {
		return new ProcessSequenceWrapper((ProcessSequence)_processSequence.clone());
	}

	@Override
	public int compareTo(ProcessSequence processSequence) {
		return _processSequence.compareTo(processSequence);
	}

	/**
	* Returns the company ID of this process sequence.
	*
	* @return the company ID of this process sequence
	*/
	@Override
	public long getCompanyId() {
		return _processSequence.getCompanyId();
	}

	/**
	* Returns the create date of this process sequence.
	*
	* @return the create date of this process sequence
	*/
	@Override
	public Date getCreateDate() {
		return _processSequence.getCreateDate();
	}

	/**
	* Returns the duration count of this process sequence.
	*
	* @return the duration count of this process sequence
	*/
	@Override
	public double getDurationCount() {
		return _processSequence.getDurationCount();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _processSequence.getExpandoBridge();
	}

	/**
	* Returns the group ID of this process sequence.
	*
	* @return the group ID of this process sequence
	*/
	@Override
	public long getGroupId() {
		return _processSequence.getGroupId();
	}

	/**
	* Returns the modified date of this process sequence.
	*
	* @return the modified date of this process sequence
	*/
	@Override
	public Date getModifiedDate() {
		return _processSequence.getModifiedDate();
	}

	/**
	* Returns the primary key of this process sequence.
	*
	* @return the primary key of this process sequence
	*/
	@Override
	public long getPrimaryKey() {
		return _processSequence.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _processSequence.getPrimaryKeyObj();
	}

	/**
	* Returns the process sequence ID of this process sequence.
	*
	* @return the process sequence ID of this process sequence
	*/
	@Override
	public long getProcessSequenceId() {
		return _processSequence.getProcessSequenceId();
	}

	/**
	* Returns the sequence name of this process sequence.
	*
	* @return the sequence name of this process sequence
	*/
	@Override
	public String getSequenceName() {
		return _processSequence.getSequenceName();
	}

	/**
	* Returns the sequence no of this process sequence.
	*
	* @return the sequence no of this process sequence
	*/
	@Override
	public String getSequenceNo() {
		return _processSequence.getSequenceNo();
	}

	/**
	* Returns the sequence role of this process sequence.
	*
	* @return the sequence role of this process sequence
	*/
	@Override
	public String getSequenceRole() {
		return _processSequence.getSequenceRole();
	}

	/**
	* Returns the service process ID of this process sequence.
	*
	* @return the service process ID of this process sequence
	*/
	@Override
	public long getServiceProcessId() {
		return _processSequence.getServiceProcessId();
	}

	/**
	* Returns the user ID of this process sequence.
	*
	* @return the user ID of this process sequence
	*/
	@Override
	public long getUserId() {
		return _processSequence.getUserId();
	}

	/**
	* Returns the user name of this process sequence.
	*
	* @return the user name of this process sequence
	*/
	@Override
	public String getUserName() {
		return _processSequence.getUserName();
	}

	/**
	* Returns the user uuid of this process sequence.
	*
	* @return the user uuid of this process sequence
	*/
	@Override
	public String getUserUuid() {
		return _processSequence.getUserUuid();
	}

	/**
	* Returns the uuid of this process sequence.
	*
	* @return the uuid of this process sequence
	*/
	@Override
	public String getUuid() {
		return _processSequence.getUuid();
	}

	@Override
	public int hashCode() {
		return _processSequence.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _processSequence.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _processSequence.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _processSequence.isNew();
	}

	@Override
	public void persist() {
		_processSequence.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_processSequence.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this process sequence.
	*
	* @param companyId the company ID of this process sequence
	*/
	@Override
	public void setCompanyId(long companyId) {
		_processSequence.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this process sequence.
	*
	* @param createDate the create date of this process sequence
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_processSequence.setCreateDate(createDate);
	}

	/**
	* Sets the duration count of this process sequence.
	*
	* @param durationCount the duration count of this process sequence
	*/
	@Override
	public void setDurationCount(double durationCount) {
		_processSequence.setDurationCount(durationCount);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_processSequence.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_processSequence.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_processSequence.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this process sequence.
	*
	* @param groupId the group ID of this process sequence
	*/
	@Override
	public void setGroupId(long groupId) {
		_processSequence.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this process sequence.
	*
	* @param modifiedDate the modified date of this process sequence
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_processSequence.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_processSequence.setNew(n);
	}

	/**
	* Sets the primary key of this process sequence.
	*
	* @param primaryKey the primary key of this process sequence
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_processSequence.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_processSequence.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the process sequence ID of this process sequence.
	*
	* @param processSequenceId the process sequence ID of this process sequence
	*/
	@Override
	public void setProcessSequenceId(long processSequenceId) {
		_processSequence.setProcessSequenceId(processSequenceId);
	}

	/**
	* Sets the sequence name of this process sequence.
	*
	* @param sequenceName the sequence name of this process sequence
	*/
	@Override
	public void setSequenceName(String sequenceName) {
		_processSequence.setSequenceName(sequenceName);
	}

	/**
	* Sets the sequence no of this process sequence.
	*
	* @param sequenceNo the sequence no of this process sequence
	*/
	@Override
	public void setSequenceNo(String sequenceNo) {
		_processSequence.setSequenceNo(sequenceNo);
	}

	/**
	* Sets the sequence role of this process sequence.
	*
	* @param sequenceRole the sequence role of this process sequence
	*/
	@Override
	public void setSequenceRole(String sequenceRole) {
		_processSequence.setSequenceRole(sequenceRole);
	}

	/**
	* Sets the service process ID of this process sequence.
	*
	* @param serviceProcessId the service process ID of this process sequence
	*/
	@Override
	public void setServiceProcessId(long serviceProcessId) {
		_processSequence.setServiceProcessId(serviceProcessId);
	}

	/**
	* Sets the user ID of this process sequence.
	*
	* @param userId the user ID of this process sequence
	*/
	@Override
	public void setUserId(long userId) {
		_processSequence.setUserId(userId);
	}

	/**
	* Sets the user name of this process sequence.
	*
	* @param userName the user name of this process sequence
	*/
	@Override
	public void setUserName(String userName) {
		_processSequence.setUserName(userName);
	}

	/**
	* Sets the user uuid of this process sequence.
	*
	* @param userUuid the user uuid of this process sequence
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_processSequence.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this process sequence.
	*
	* @param uuid the uuid of this process sequence
	*/
	@Override
	public void setUuid(String uuid) {
		_processSequence.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ProcessSequence> toCacheModel() {
		return _processSequence.toCacheModel();
	}

	@Override
	public ProcessSequence toEscapedModel() {
		return new ProcessSequenceWrapper(_processSequence.toEscapedModel());
	}

	@Override
	public String toString() {
		return _processSequence.toString();
	}

	@Override
	public ProcessSequence toUnescapedModel() {
		return new ProcessSequenceWrapper(_processSequence.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _processSequence.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProcessSequenceWrapper)) {
			return false;
		}

		ProcessSequenceWrapper processSequenceWrapper = (ProcessSequenceWrapper)obj;

		if (Objects.equals(_processSequence,
					processSequenceWrapper._processSequence)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _processSequence.getStagedModelType();
	}

	@Override
	public ProcessSequence getWrappedModel() {
		return _processSequence;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _processSequence.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _processSequence.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_processSequence.resetOriginalValues();
	}

	private final ProcessSequence _processSequence;
}