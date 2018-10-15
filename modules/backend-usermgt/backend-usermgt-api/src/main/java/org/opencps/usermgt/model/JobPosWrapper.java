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
 * This class is a wrapper for {@link JobPos}.
 * </p>
 *
 * @author khoavu
 * @see JobPos
 * @generated
 */
@ProviderType
public class JobPosWrapper implements JobPos, ModelWrapper<JobPos> {
	public JobPosWrapper(JobPos jobPos) {
		_jobPos = jobPos;
	}

	@Override
	public Class<?> getModelClass() {
		return JobPos.class;
	}

	@Override
	public String getModelClassName() {
		return JobPos.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("jobPosId", getJobPosId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("jobPosCode", getJobPosCode());
		attributes.put("title", getTitle());
		attributes.put("description", getDescription());
		attributes.put("mappingRoleId", getMappingRoleId());
		attributes.put("leader", getLeader());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long jobPosId = (Long)attributes.get("jobPosId");

		if (jobPosId != null) {
			setJobPosId(jobPosId);
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

		String jobPosCode = (String)attributes.get("jobPosCode");

		if (jobPosCode != null) {
			setJobPosCode(jobPosCode);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Long mappingRoleId = (Long)attributes.get("mappingRoleId");

		if (mappingRoleId != null) {
			setMappingRoleId(mappingRoleId);
		}

		Integer leader = (Integer)attributes.get("leader");

		if (leader != null) {
			setLeader(leader);
		}
	}

	@Override
	public Object clone() {
		return new JobPosWrapper((JobPos)_jobPos.clone());
	}

	@Override
	public int compareTo(JobPos jobPos) {
		return _jobPos.compareTo(jobPos);
	}

	/**
	* Returns the company ID of this job pos.
	*
	* @return the company ID of this job pos
	*/
	@Override
	public long getCompanyId() {
		return _jobPos.getCompanyId();
	}

	/**
	* Returns the create date of this job pos.
	*
	* @return the create date of this job pos
	*/
	@Override
	public Date getCreateDate() {
		return _jobPos.getCreateDate();
	}

	/**
	* Returns the description of this job pos.
	*
	* @return the description of this job pos
	*/
	@Override
	public String getDescription() {
		return _jobPos.getDescription();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _jobPos.getExpandoBridge();
	}

	/**
	* Returns the group ID of this job pos.
	*
	* @return the group ID of this job pos
	*/
	@Override
	public long getGroupId() {
		return _jobPos.getGroupId();
	}

	/**
	* Returns the job pos code of this job pos.
	*
	* @return the job pos code of this job pos
	*/
	@Override
	public String getJobPosCode() {
		return _jobPos.getJobPosCode();
	}

	/**
	* Returns the job pos ID of this job pos.
	*
	* @return the job pos ID of this job pos
	*/
	@Override
	public long getJobPosId() {
		return _jobPos.getJobPosId();
	}

	/**
	* Returns the leader of this job pos.
	*
	* @return the leader of this job pos
	*/
	@Override
	public int getLeader() {
		return _jobPos.getLeader();
	}

	/**
	* Returns the mapping role ID of this job pos.
	*
	* @return the mapping role ID of this job pos
	*/
	@Override
	public long getMappingRoleId() {
		return _jobPos.getMappingRoleId();
	}

	/**
	* Returns the modified date of this job pos.
	*
	* @return the modified date of this job pos
	*/
	@Override
	public Date getModifiedDate() {
		return _jobPos.getModifiedDate();
	}

	/**
	* Returns the primary key of this job pos.
	*
	* @return the primary key of this job pos
	*/
	@Override
	public long getPrimaryKey() {
		return _jobPos.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _jobPos.getPrimaryKeyObj();
	}

	/**
	* Returns the title of this job pos.
	*
	* @return the title of this job pos
	*/
	@Override
	public String getTitle() {
		return _jobPos.getTitle();
	}

	/**
	* Returns the user ID of this job pos.
	*
	* @return the user ID of this job pos
	*/
	@Override
	public long getUserId() {
		return _jobPos.getUserId();
	}

	/**
	* Returns the user name of this job pos.
	*
	* @return the user name of this job pos
	*/
	@Override
	public String getUserName() {
		return _jobPos.getUserName();
	}

	/**
	* Returns the user uuid of this job pos.
	*
	* @return the user uuid of this job pos
	*/
	@Override
	public String getUserUuid() {
		return _jobPos.getUserUuid();
	}

	/**
	* Returns the uuid of this job pos.
	*
	* @return the uuid of this job pos
	*/
	@Override
	public String getUuid() {
		return _jobPos.getUuid();
	}

	@Override
	public int hashCode() {
		return _jobPos.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _jobPos.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _jobPos.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _jobPos.isNew();
	}

	@Override
	public void persist() {
		_jobPos.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_jobPos.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this job pos.
	*
	* @param companyId the company ID of this job pos
	*/
	@Override
	public void setCompanyId(long companyId) {
		_jobPos.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this job pos.
	*
	* @param createDate the create date of this job pos
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_jobPos.setCreateDate(createDate);
	}

	/**
	* Sets the description of this job pos.
	*
	* @param description the description of this job pos
	*/
	@Override
	public void setDescription(String description) {
		_jobPos.setDescription(description);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_jobPos.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_jobPos.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_jobPos.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this job pos.
	*
	* @param groupId the group ID of this job pos
	*/
	@Override
	public void setGroupId(long groupId) {
		_jobPos.setGroupId(groupId);
	}

	/**
	* Sets the job pos code of this job pos.
	*
	* @param jobPosCode the job pos code of this job pos
	*/
	@Override
	public void setJobPosCode(String jobPosCode) {
		_jobPos.setJobPosCode(jobPosCode);
	}

	/**
	* Sets the job pos ID of this job pos.
	*
	* @param jobPosId the job pos ID of this job pos
	*/
	@Override
	public void setJobPosId(long jobPosId) {
		_jobPos.setJobPosId(jobPosId);
	}

	/**
	* Sets the leader of this job pos.
	*
	* @param leader the leader of this job pos
	*/
	@Override
	public void setLeader(int leader) {
		_jobPos.setLeader(leader);
	}

	/**
	* Sets the mapping role ID of this job pos.
	*
	* @param mappingRoleId the mapping role ID of this job pos
	*/
	@Override
	public void setMappingRoleId(long mappingRoleId) {
		_jobPos.setMappingRoleId(mappingRoleId);
	}

	/**
	* Sets the modified date of this job pos.
	*
	* @param modifiedDate the modified date of this job pos
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_jobPos.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_jobPos.setNew(n);
	}

	/**
	* Sets the primary key of this job pos.
	*
	* @param primaryKey the primary key of this job pos
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_jobPos.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_jobPos.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the title of this job pos.
	*
	* @param title the title of this job pos
	*/
	@Override
	public void setTitle(String title) {
		_jobPos.setTitle(title);
	}

	/**
	* Sets the user ID of this job pos.
	*
	* @param userId the user ID of this job pos
	*/
	@Override
	public void setUserId(long userId) {
		_jobPos.setUserId(userId);
	}

	/**
	* Sets the user name of this job pos.
	*
	* @param userName the user name of this job pos
	*/
	@Override
	public void setUserName(String userName) {
		_jobPos.setUserName(userName);
	}

	/**
	* Sets the user uuid of this job pos.
	*
	* @param userUuid the user uuid of this job pos
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_jobPos.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this job pos.
	*
	* @param uuid the uuid of this job pos
	*/
	@Override
	public void setUuid(String uuid) {
		_jobPos.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<JobPos> toCacheModel() {
		return _jobPos.toCacheModel();
	}

	@Override
	public JobPos toEscapedModel() {
		return new JobPosWrapper(_jobPos.toEscapedModel());
	}

	@Override
	public String toString() {
		return _jobPos.toString();
	}

	@Override
	public JobPos toUnescapedModel() {
		return new JobPosWrapper(_jobPos.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _jobPos.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof JobPosWrapper)) {
			return false;
		}

		JobPosWrapper jobPosWrapper = (JobPosWrapper)obj;

		if (Objects.equals(_jobPos, jobPosWrapper._jobPos)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _jobPos.getStagedModelType();
	}

	@Override
	public JobPos getWrappedModel() {
		return _jobPos;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _jobPos.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _jobPos.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_jobPos.resetOriginalValues();
	}

	private final JobPos _jobPos;
}