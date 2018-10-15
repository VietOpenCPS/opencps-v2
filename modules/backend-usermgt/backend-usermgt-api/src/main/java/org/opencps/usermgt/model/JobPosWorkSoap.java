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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author khoavu
 * @generated
 */
@ProviderType
public class JobPosWorkSoap implements Serializable {
	public static JobPosWorkSoap toSoapModel(JobPosWork model) {
		JobPosWorkSoap soapModel = new JobPosWorkSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setJobPosWorkId(model.getJobPosWorkId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setJobPostId(model.getJobPostId());
		soapModel.setChecklistCat(model.getChecklistCat());

		return soapModel;
	}

	public static JobPosWorkSoap[] toSoapModels(JobPosWork[] models) {
		JobPosWorkSoap[] soapModels = new JobPosWorkSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static JobPosWorkSoap[][] toSoapModels(JobPosWork[][] models) {
		JobPosWorkSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new JobPosWorkSoap[models.length][models[0].length];
		}
		else {
			soapModels = new JobPosWorkSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static JobPosWorkSoap[] toSoapModels(List<JobPosWork> models) {
		List<JobPosWorkSoap> soapModels = new ArrayList<JobPosWorkSoap>(models.size());

		for (JobPosWork model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new JobPosWorkSoap[soapModels.size()]);
	}

	public JobPosWorkSoap() {
	}

	public long getPrimaryKey() {
		return _jobPosWorkId;
	}

	public void setPrimaryKey(long pk) {
		setJobPosWorkId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getJobPosWorkId() {
		return _jobPosWorkId;
	}

	public void setJobPosWorkId(long jobPosWorkId) {
		_jobPosWorkId = jobPosWorkId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getJobPostId() {
		return _jobPostId;
	}

	public void setJobPostId(long jobPostId) {
		_jobPostId = jobPostId;
	}

	public String getChecklistCat() {
		return _checklistCat;
	}

	public void setChecklistCat(String checklistCat) {
		_checklistCat = checklistCat;
	}

	private String _uuid;
	private long _jobPosWorkId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _jobPostId;
	private String _checklistCat;
}