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
 * This class is a wrapper for {@link JobPosWork}.
 * </p>
 *
 * @author khoavu
 * @see JobPosWork
 * @generated
 */
@ProviderType
public class JobPosWorkWrapper implements JobPosWork, ModelWrapper<JobPosWork> {
	public JobPosWorkWrapper(JobPosWork jobPosWork) {
		_jobPosWork = jobPosWork;
	}

	@Override
	public Class<?> getModelClass() {
		return JobPosWork.class;
	}

	@Override
	public String getModelClassName() {
		return JobPosWork.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("jobPosWorkId", getJobPosWorkId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("jobPostId", getJobPostId());
		attributes.put("checklistCat", getChecklistCat());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long jobPosWorkId = (Long)attributes.get("jobPosWorkId");

		if (jobPosWorkId != null) {
			setJobPosWorkId(jobPosWorkId);
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

		Long jobPostId = (Long)attributes.get("jobPostId");

		if (jobPostId != null) {
			setJobPostId(jobPostId);
		}

		String checklistCat = (String)attributes.get("checklistCat");

		if (checklistCat != null) {
			setChecklistCat(checklistCat);
		}
	}

	@Override
	public Object clone() {
		return new JobPosWorkWrapper((JobPosWork)_jobPosWork.clone());
	}

	@Override
	public int compareTo(JobPosWork jobPosWork) {
		return _jobPosWork.compareTo(jobPosWork);
	}

	/**
	* Returns the checklist cat of this job pos work.
	*
	* @return the checklist cat of this job pos work
	*/
	@Override
	public String getChecklistCat() {
		return _jobPosWork.getChecklistCat();
	}

	/**
	* Returns the company ID of this job pos work.
	*
	* @return the company ID of this job pos work
	*/
	@Override
	public long getCompanyId() {
		return _jobPosWork.getCompanyId();
	}

	/**
	* Returns the create date of this job pos work.
	*
	* @return the create date of this job pos work
	*/
	@Override
	public Date getCreateDate() {
		return _jobPosWork.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _jobPosWork.getExpandoBridge();
	}

	/**
	* Returns the group ID of this job pos work.
	*
	* @return the group ID of this job pos work
	*/
	@Override
	public long getGroupId() {
		return _jobPosWork.getGroupId();
	}

	/**
	* Returns the job post ID of this job pos work.
	*
	* @return the job post ID of this job pos work
	*/
	@Override
	public long getJobPostId() {
		return _jobPosWork.getJobPostId();
	}

	/**
	* Returns the job pos work ID of this job pos work.
	*
	* @return the job pos work ID of this job pos work
	*/
	@Override
	public long getJobPosWorkId() {
		return _jobPosWork.getJobPosWorkId();
	}

	/**
	* Returns the modified date of this job pos work.
	*
	* @return the modified date of this job pos work
	*/
	@Override
	public Date getModifiedDate() {
		return _jobPosWork.getModifiedDate();
	}

	/**
	* Returns the primary key of this job pos work.
	*
	* @return the primary key of this job pos work
	*/
	@Override
	public long getPrimaryKey() {
		return _jobPosWork.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _jobPosWork.getPrimaryKeyObj();
	}

	/**
	* Returns the user ID of this job pos work.
	*
	* @return the user ID of this job pos work
	*/
	@Override
	public long getUserId() {
		return _jobPosWork.getUserId();
	}

	/**
	* Returns the user name of this job pos work.
	*
	* @return the user name of this job pos work
	*/
	@Override
	public String getUserName() {
		return _jobPosWork.getUserName();
	}

	/**
	* Returns the user uuid of this job pos work.
	*
	* @return the user uuid of this job pos work
	*/
	@Override
	public String getUserUuid() {
		return _jobPosWork.getUserUuid();
	}

	/**
	* Returns the uuid of this job pos work.
	*
	* @return the uuid of this job pos work
	*/
	@Override
	public String getUuid() {
		return _jobPosWork.getUuid();
	}

	@Override
	public int hashCode() {
		return _jobPosWork.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _jobPosWork.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _jobPosWork.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _jobPosWork.isNew();
	}

	@Override
	public void persist() {
		_jobPosWork.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_jobPosWork.setCachedModel(cachedModel);
	}

	/**
	* Sets the checklist cat of this job pos work.
	*
	* @param checklistCat the checklist cat of this job pos work
	*/
	@Override
	public void setChecklistCat(String checklistCat) {
		_jobPosWork.setChecklistCat(checklistCat);
	}

	/**
	* Sets the company ID of this job pos work.
	*
	* @param companyId the company ID of this job pos work
	*/
	@Override
	public void setCompanyId(long companyId) {
		_jobPosWork.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this job pos work.
	*
	* @param createDate the create date of this job pos work
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_jobPosWork.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_jobPosWork.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_jobPosWork.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_jobPosWork.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this job pos work.
	*
	* @param groupId the group ID of this job pos work
	*/
	@Override
	public void setGroupId(long groupId) {
		_jobPosWork.setGroupId(groupId);
	}

	/**
	* Sets the job post ID of this job pos work.
	*
	* @param jobPostId the job post ID of this job pos work
	*/
	@Override
	public void setJobPostId(long jobPostId) {
		_jobPosWork.setJobPostId(jobPostId);
	}

	/**
	* Sets the job pos work ID of this job pos work.
	*
	* @param jobPosWorkId the job pos work ID of this job pos work
	*/
	@Override
	public void setJobPosWorkId(long jobPosWorkId) {
		_jobPosWork.setJobPosWorkId(jobPosWorkId);
	}

	/**
	* Sets the modified date of this job pos work.
	*
	* @param modifiedDate the modified date of this job pos work
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_jobPosWork.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_jobPosWork.setNew(n);
	}

	/**
	* Sets the primary key of this job pos work.
	*
	* @param primaryKey the primary key of this job pos work
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_jobPosWork.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_jobPosWork.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this job pos work.
	*
	* @param userId the user ID of this job pos work
	*/
	@Override
	public void setUserId(long userId) {
		_jobPosWork.setUserId(userId);
	}

	/**
	* Sets the user name of this job pos work.
	*
	* @param userName the user name of this job pos work
	*/
	@Override
	public void setUserName(String userName) {
		_jobPosWork.setUserName(userName);
	}

	/**
	* Sets the user uuid of this job pos work.
	*
	* @param userUuid the user uuid of this job pos work
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_jobPosWork.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this job pos work.
	*
	* @param uuid the uuid of this job pos work
	*/
	@Override
	public void setUuid(String uuid) {
		_jobPosWork.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<JobPosWork> toCacheModel() {
		return _jobPosWork.toCacheModel();
	}

	@Override
	public JobPosWork toEscapedModel() {
		return new JobPosWorkWrapper(_jobPosWork.toEscapedModel());
	}

	@Override
	public String toString() {
		return _jobPosWork.toString();
	}

	@Override
	public JobPosWork toUnescapedModel() {
		return new JobPosWorkWrapper(_jobPosWork.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _jobPosWork.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof JobPosWorkWrapper)) {
			return false;
		}

		JobPosWorkWrapper jobPosWorkWrapper = (JobPosWorkWrapper)obj;

		if (Objects.equals(_jobPosWork, jobPosWorkWrapper._jobPosWork)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _jobPosWork.getStagedModelType();
	}

	@Override
	public JobPosWork getWrappedModel() {
		return _jobPosWork;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _jobPosWork.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _jobPosWork.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_jobPosWork.resetOriginalValues();
	}

	private final JobPosWork _jobPosWork;
}