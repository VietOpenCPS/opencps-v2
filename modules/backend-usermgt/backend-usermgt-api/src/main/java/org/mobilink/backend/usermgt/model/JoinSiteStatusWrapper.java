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
 * This class is a wrapper for {@link JoinSiteStatus}.
 * </p>
 *
 * @author Binhth
 * @see JoinSiteStatus
 * @generated
 */
@ProviderType
public class JoinSiteStatusWrapper implements JoinSiteStatus,
	ModelWrapper<JoinSiteStatus> {
	public JoinSiteStatusWrapper(JoinSiteStatus joinSiteStatus) {
		_joinSiteStatus = joinSiteStatus;
	}

	@Override
	public Class<?> getModelClass() {
		return JoinSiteStatus.class;
	}

	@Override
	public String getModelClassName() {
		return JoinSiteStatus.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("JoinSiteStatusId", getJoinSiteStatusId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("employeeId", getEmployeeId());
		attributes.put("joinSiteGroupId", getJoinSiteGroupId());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long JoinSiteStatusId = (Long)attributes.get("JoinSiteStatusId");

		if (JoinSiteStatusId != null) {
			setJoinSiteStatusId(JoinSiteStatusId);
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

		Long joinSiteGroupId = (Long)attributes.get("joinSiteGroupId");

		if (joinSiteGroupId != null) {
			setJoinSiteGroupId(joinSiteGroupId);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public JoinSiteStatus toEscapedModel() {
		return new JoinSiteStatusWrapper(_joinSiteStatus.toEscapedModel());
	}

	@Override
	public JoinSiteStatus toUnescapedModel() {
		return new JoinSiteStatusWrapper(_joinSiteStatus.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _joinSiteStatus.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _joinSiteStatus.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _joinSiteStatus.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _joinSiteStatus.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<JoinSiteStatus> toCacheModel() {
		return _joinSiteStatus.toCacheModel();
	}

	@Override
	public int compareTo(JoinSiteStatus joinSiteStatus) {
		return _joinSiteStatus.compareTo(joinSiteStatus);
	}

	/**
	* Returns the status of this join site status.
	*
	* @return the status of this join site status
	*/
	@Override
	public int getStatus() {
		return _joinSiteStatus.getStatus();
	}

	@Override
	public int hashCode() {
		return _joinSiteStatus.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _joinSiteStatus.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new JoinSiteStatusWrapper((JoinSiteStatus)_joinSiteStatus.clone());
	}

	/**
	* Returns the user name of this join site status.
	*
	* @return the user name of this join site status
	*/
	@Override
	public java.lang.String getUserName() {
		return _joinSiteStatus.getUserName();
	}

	/**
	* Returns the user uuid of this join site status.
	*
	* @return the user uuid of this join site status
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _joinSiteStatus.getUserUuid();
	}

	/**
	* Returns the uuid of this join site status.
	*
	* @return the uuid of this join site status
	*/
	@Override
	public java.lang.String getUuid() {
		return _joinSiteStatus.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _joinSiteStatus.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _joinSiteStatus.toXmlString();
	}

	/**
	* Returns the create date of this join site status.
	*
	* @return the create date of this join site status
	*/
	@Override
	public Date getCreateDate() {
		return _joinSiteStatus.getCreateDate();
	}

	/**
	* Returns the modified date of this join site status.
	*
	* @return the modified date of this join site status
	*/
	@Override
	public Date getModifiedDate() {
		return _joinSiteStatus.getModifiedDate();
	}

	/**
	* Returns the company ID of this join site status.
	*
	* @return the company ID of this join site status
	*/
	@Override
	public long getCompanyId() {
		return _joinSiteStatus.getCompanyId();
	}

	/**
	* Returns the employee ID of this join site status.
	*
	* @return the employee ID of this join site status
	*/
	@Override
	public long getEmployeeId() {
		return _joinSiteStatus.getEmployeeId();
	}

	/**
	* Returns the group ID of this join site status.
	*
	* @return the group ID of this join site status
	*/
	@Override
	public long getGroupId() {
		return _joinSiteStatus.getGroupId();
	}

	/**
	* Returns the join site group ID of this join site status.
	*
	* @return the join site group ID of this join site status
	*/
	@Override
	public long getJoinSiteGroupId() {
		return _joinSiteStatus.getJoinSiteGroupId();
	}

	/**
	* Returns the join site status ID of this join site status.
	*
	* @return the join site status ID of this join site status
	*/
	@Override
	public long getJoinSiteStatusId() {
		return _joinSiteStatus.getJoinSiteStatusId();
	}

	/**
	* Returns the primary key of this join site status.
	*
	* @return the primary key of this join site status
	*/
	@Override
	public long getPrimaryKey() {
		return _joinSiteStatus.getPrimaryKey();
	}

	/**
	* Returns the user ID of this join site status.
	*
	* @return the user ID of this join site status
	*/
	@Override
	public long getUserId() {
		return _joinSiteStatus.getUserId();
	}

	@Override
	public void persist() {
		_joinSiteStatus.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_joinSiteStatus.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this join site status.
	*
	* @param companyId the company ID of this join site status
	*/
	@Override
	public void setCompanyId(long companyId) {
		_joinSiteStatus.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this join site status.
	*
	* @param createDate the create date of this join site status
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_joinSiteStatus.setCreateDate(createDate);
	}

	/**
	* Sets the employee ID of this join site status.
	*
	* @param employeeId the employee ID of this join site status
	*/
	@Override
	public void setEmployeeId(long employeeId) {
		_joinSiteStatus.setEmployeeId(employeeId);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_joinSiteStatus.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_joinSiteStatus.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_joinSiteStatus.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this join site status.
	*
	* @param groupId the group ID of this join site status
	*/
	@Override
	public void setGroupId(long groupId) {
		_joinSiteStatus.setGroupId(groupId);
	}

	/**
	* Sets the join site group ID of this join site status.
	*
	* @param joinSiteGroupId the join site group ID of this join site status
	*/
	@Override
	public void setJoinSiteGroupId(long joinSiteGroupId) {
		_joinSiteStatus.setJoinSiteGroupId(joinSiteGroupId);
	}

	/**
	* Sets the join site status ID of this join site status.
	*
	* @param JoinSiteStatusId the join site status ID of this join site status
	*/
	@Override
	public void setJoinSiteStatusId(long JoinSiteStatusId) {
		_joinSiteStatus.setJoinSiteStatusId(JoinSiteStatusId);
	}

	/**
	* Sets the modified date of this join site status.
	*
	* @param modifiedDate the modified date of this join site status
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_joinSiteStatus.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_joinSiteStatus.setNew(n);
	}

	/**
	* Sets the primary key of this join site status.
	*
	* @param primaryKey the primary key of this join site status
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_joinSiteStatus.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_joinSiteStatus.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the status of this join site status.
	*
	* @param status the status of this join site status
	*/
	@Override
	public void setStatus(int status) {
		_joinSiteStatus.setStatus(status);
	}

	/**
	* Sets the user ID of this join site status.
	*
	* @param userId the user ID of this join site status
	*/
	@Override
	public void setUserId(long userId) {
		_joinSiteStatus.setUserId(userId);
	}

	/**
	* Sets the user name of this join site status.
	*
	* @param userName the user name of this join site status
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_joinSiteStatus.setUserName(userName);
	}

	/**
	* Sets the user uuid of this join site status.
	*
	* @param userUuid the user uuid of this join site status
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_joinSiteStatus.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this join site status.
	*
	* @param uuid the uuid of this join site status
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_joinSiteStatus.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof JoinSiteStatusWrapper)) {
			return false;
		}

		JoinSiteStatusWrapper joinSiteStatusWrapper = (JoinSiteStatusWrapper)obj;

		if (Objects.equals(_joinSiteStatus,
					joinSiteStatusWrapper._joinSiteStatus)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _joinSiteStatus.getStagedModelType();
	}

	@Override
	public JoinSiteStatus getWrappedModel() {
		return _joinSiteStatus;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _joinSiteStatus.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _joinSiteStatus.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_joinSiteStatus.resetOriginalValues();
	}

	private final JoinSiteStatus _joinSiteStatus;
}