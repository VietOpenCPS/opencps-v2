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
 * This class is a wrapper for {@link ApplicantData}.
 * </p>
 *
 * @author khoavu
 * @see ApplicantData
 * @generated
 */
@ProviderType
public class ApplicantDataWrapper implements ApplicantData,
	ModelWrapper<ApplicantData> {
	public ApplicantDataWrapper(ApplicantData applicantData) {
		_applicantData = applicantData;
	}

	@Override
	public Class<?> getModelClass() {
		return ApplicantData.class;
	}

	@Override
	public String getModelClassName() {
		return ApplicantData.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("applicantDataId", getApplicantDataId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("fileTemplateNo", getFileTemplateNo());
		attributes.put("fileNo", getFileNo());
		attributes.put("fileName", getFileName());
		attributes.put("fileEntryId", getFileEntryId());
		attributes.put("metadata", getMetadata());
		attributes.put("status", getStatus());
		attributes.put("applicantIdNo", getApplicantIdNo());
		attributes.put("applicantDataType", getApplicantDataType());
		attributes.put("dossierNo", getDossierNo());
		attributes.put("log", getLog());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long applicantDataId = (Long)attributes.get("applicantDataId");

		if (applicantDataId != null) {
			setApplicantDataId(applicantDataId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
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

		String fileTemplateNo = (String)attributes.get("fileTemplateNo");

		if (fileTemplateNo != null) {
			setFileTemplateNo(fileTemplateNo);
		}

		String fileNo = (String)attributes.get("fileNo");

		if (fileNo != null) {
			setFileNo(fileNo);
		}

		String fileName = (String)attributes.get("fileName");

		if (fileName != null) {
			setFileName(fileName);
		}

		Long fileEntryId = (Long)attributes.get("fileEntryId");

		if (fileEntryId != null) {
			setFileEntryId(fileEntryId);
		}

		String metadata = (String)attributes.get("metadata");

		if (metadata != null) {
			setMetadata(metadata);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		String applicantIdNo = (String)attributes.get("applicantIdNo");

		if (applicantIdNo != null) {
			setApplicantIdNo(applicantIdNo);
		}

		Integer applicantDataType = (Integer)attributes.get("applicantDataType");

		if (applicantDataType != null) {
			setApplicantDataType(applicantDataType);
		}

		String dossierNo = (String)attributes.get("dossierNo");

		if (dossierNo != null) {
			setDossierNo(dossierNo);
		}

		String log = (String)attributes.get("log");

		if (log != null) {
			setLog(log);
		}
	}

	@Override
	public Object clone() {
		return new ApplicantDataWrapper((ApplicantData)_applicantData.clone());
	}

	@Override
	public int compareTo(ApplicantData applicantData) {
		return _applicantData.compareTo(applicantData);
	}

	/**
	* Returns the applicant data ID of this applicant data.
	*
	* @return the applicant data ID of this applicant data
	*/
	@Override
	public long getApplicantDataId() {
		return _applicantData.getApplicantDataId();
	}

	/**
	* Returns the applicant data type of this applicant data.
	*
	* @return the applicant data type of this applicant data
	*/
	@Override
	public int getApplicantDataType() {
		return _applicantData.getApplicantDataType();
	}

	/**
	* Returns the applicant ID no of this applicant data.
	*
	* @return the applicant ID no of this applicant data
	*/
	@Override
	public String getApplicantIdNo() {
		return _applicantData.getApplicantIdNo();
	}

	/**
	* Returns the company ID of this applicant data.
	*
	* @return the company ID of this applicant data
	*/
	@Override
	public long getCompanyId() {
		return _applicantData.getCompanyId();
	}

	/**
	* Returns the create date of this applicant data.
	*
	* @return the create date of this applicant data
	*/
	@Override
	public Date getCreateDate() {
		return _applicantData.getCreateDate();
	}

	/**
	* Returns the dossier no of this applicant data.
	*
	* @return the dossier no of this applicant data
	*/
	@Override
	public String getDossierNo() {
		return _applicantData.getDossierNo();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _applicantData.getExpandoBridge();
	}

	/**
	* Returns the file entry ID of this applicant data.
	*
	* @return the file entry ID of this applicant data
	*/
	@Override
	public long getFileEntryId() {
		return _applicantData.getFileEntryId();
	}

	/**
	* Returns the file name of this applicant data.
	*
	* @return the file name of this applicant data
	*/
	@Override
	public String getFileName() {
		return _applicantData.getFileName();
	}

	/**
	* Returns the file no of this applicant data.
	*
	* @return the file no of this applicant data
	*/
	@Override
	public String getFileNo() {
		return _applicantData.getFileNo();
	}

	/**
	* Returns the file template no of this applicant data.
	*
	* @return the file template no of this applicant data
	*/
	@Override
	public String getFileTemplateNo() {
		return _applicantData.getFileTemplateNo();
	}

	/**
	* Returns the group ID of this applicant data.
	*
	* @return the group ID of this applicant data
	*/
	@Override
	public long getGroupId() {
		return _applicantData.getGroupId();
	}

	/**
	* Returns the log of this applicant data.
	*
	* @return the log of this applicant data
	*/
	@Override
	public String getLog() {
		return _applicantData.getLog();
	}

	/**
	* Returns the metadata of this applicant data.
	*
	* @return the metadata of this applicant data
	*/
	@Override
	public String getMetadata() {
		return _applicantData.getMetadata();
	}

	/**
	* Returns the modified date of this applicant data.
	*
	* @return the modified date of this applicant data
	*/
	@Override
	public Date getModifiedDate() {
		return _applicantData.getModifiedDate();
	}

	/**
	* Returns the primary key of this applicant data.
	*
	* @return the primary key of this applicant data
	*/
	@Override
	public long getPrimaryKey() {
		return _applicantData.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _applicantData.getPrimaryKeyObj();
	}

	/**
	* Returns the status of this applicant data.
	*
	* @return the status of this applicant data
	*/
	@Override
	public int getStatus() {
		return _applicantData.getStatus();
	}

	/**
	* Returns the user ID of this applicant data.
	*
	* @return the user ID of this applicant data
	*/
	@Override
	public long getUserId() {
		return _applicantData.getUserId();
	}

	/**
	* Returns the user name of this applicant data.
	*
	* @return the user name of this applicant data
	*/
	@Override
	public String getUserName() {
		return _applicantData.getUserName();
	}

	/**
	* Returns the user uuid of this applicant data.
	*
	* @return the user uuid of this applicant data
	*/
	@Override
	public String getUserUuid() {
		return _applicantData.getUserUuid();
	}

	/**
	* Returns the uuid of this applicant data.
	*
	* @return the uuid of this applicant data
	*/
	@Override
	public String getUuid() {
		return _applicantData.getUuid();
	}

	@Override
	public int hashCode() {
		return _applicantData.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _applicantData.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _applicantData.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _applicantData.isNew();
	}

	@Override
	public void persist() {
		_applicantData.persist();
	}

	/**
	* Sets the applicant data ID of this applicant data.
	*
	* @param applicantDataId the applicant data ID of this applicant data
	*/
	@Override
	public void setApplicantDataId(long applicantDataId) {
		_applicantData.setApplicantDataId(applicantDataId);
	}

	/**
	* Sets the applicant data type of this applicant data.
	*
	* @param applicantDataType the applicant data type of this applicant data
	*/
	@Override
	public void setApplicantDataType(int applicantDataType) {
		_applicantData.setApplicantDataType(applicantDataType);
	}

	/**
	* Sets the applicant ID no of this applicant data.
	*
	* @param applicantIdNo the applicant ID no of this applicant data
	*/
	@Override
	public void setApplicantIdNo(String applicantIdNo) {
		_applicantData.setApplicantIdNo(applicantIdNo);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_applicantData.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this applicant data.
	*
	* @param companyId the company ID of this applicant data
	*/
	@Override
	public void setCompanyId(long companyId) {
		_applicantData.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this applicant data.
	*
	* @param createDate the create date of this applicant data
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_applicantData.setCreateDate(createDate);
	}

	/**
	* Sets the dossier no of this applicant data.
	*
	* @param dossierNo the dossier no of this applicant data
	*/
	@Override
	public void setDossierNo(String dossierNo) {
		_applicantData.setDossierNo(dossierNo);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_applicantData.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_applicantData.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_applicantData.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the file entry ID of this applicant data.
	*
	* @param fileEntryId the file entry ID of this applicant data
	*/
	@Override
	public void setFileEntryId(long fileEntryId) {
		_applicantData.setFileEntryId(fileEntryId);
	}

	/**
	* Sets the file name of this applicant data.
	*
	* @param fileName the file name of this applicant data
	*/
	@Override
	public void setFileName(String fileName) {
		_applicantData.setFileName(fileName);
	}

	/**
	* Sets the file no of this applicant data.
	*
	* @param fileNo the file no of this applicant data
	*/
	@Override
	public void setFileNo(String fileNo) {
		_applicantData.setFileNo(fileNo);
	}

	/**
	* Sets the file template no of this applicant data.
	*
	* @param fileTemplateNo the file template no of this applicant data
	*/
	@Override
	public void setFileTemplateNo(String fileTemplateNo) {
		_applicantData.setFileTemplateNo(fileTemplateNo);
	}

	/**
	* Sets the group ID of this applicant data.
	*
	* @param groupId the group ID of this applicant data
	*/
	@Override
	public void setGroupId(long groupId) {
		_applicantData.setGroupId(groupId);
	}

	/**
	* Sets the log of this applicant data.
	*
	* @param log the log of this applicant data
	*/
	@Override
	public void setLog(String log) {
		_applicantData.setLog(log);
	}

	/**
	* Sets the metadata of this applicant data.
	*
	* @param metadata the metadata of this applicant data
	*/
	@Override
	public void setMetadata(String metadata) {
		_applicantData.setMetadata(metadata);
	}

	/**
	* Sets the modified date of this applicant data.
	*
	* @param modifiedDate the modified date of this applicant data
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_applicantData.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_applicantData.setNew(n);
	}

	/**
	* Sets the primary key of this applicant data.
	*
	* @param primaryKey the primary key of this applicant data
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_applicantData.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_applicantData.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the status of this applicant data.
	*
	* @param status the status of this applicant data
	*/
	@Override
	public void setStatus(int status) {
		_applicantData.setStatus(status);
	}

	/**
	* Sets the user ID of this applicant data.
	*
	* @param userId the user ID of this applicant data
	*/
	@Override
	public void setUserId(long userId) {
		_applicantData.setUserId(userId);
	}

	/**
	* Sets the user name of this applicant data.
	*
	* @param userName the user name of this applicant data
	*/
	@Override
	public void setUserName(String userName) {
		_applicantData.setUserName(userName);
	}

	/**
	* Sets the user uuid of this applicant data.
	*
	* @param userUuid the user uuid of this applicant data
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_applicantData.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this applicant data.
	*
	* @param uuid the uuid of this applicant data
	*/
	@Override
	public void setUuid(String uuid) {
		_applicantData.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ApplicantData> toCacheModel() {
		return _applicantData.toCacheModel();
	}

	@Override
	public ApplicantData toEscapedModel() {
		return new ApplicantDataWrapper(_applicantData.toEscapedModel());
	}

	@Override
	public String toString() {
		return _applicantData.toString();
	}

	@Override
	public ApplicantData toUnescapedModel() {
		return new ApplicantDataWrapper(_applicantData.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _applicantData.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ApplicantDataWrapper)) {
			return false;
		}

		ApplicantDataWrapper applicantDataWrapper = (ApplicantDataWrapper)obj;

		if (Objects.equals(_applicantData, applicantDataWrapper._applicantData)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _applicantData.getStagedModelType();
	}

	@Override
	public ApplicantData getWrappedModel() {
		return _applicantData;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _applicantData.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _applicantData.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_applicantData.resetOriginalValues();
	}

	private final ApplicantData _applicantData;
}