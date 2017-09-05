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

package org.opencps.backend.dossiermgt.model;

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
 * This class is a wrapper for {@link FileTemplate}.
 * </p>
 *
 * @author huymq
 * @see FileTemplate
 * @generated
 */
@ProviderType
public class FileTemplateWrapper implements FileTemplate,
	ModelWrapper<FileTemplate> {
	public FileTemplateWrapper(FileTemplate fileTemplate) {
		_fileTemplate = fileTemplate;
	}

	@Override
	public Class<?> getModelClass() {
		return FileTemplate.class;
	}

	@Override
	public String getModelClassName() {
		return FileTemplate.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("fileTemplateId", getFileTemplateId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("fileName", getFileName());
		attributes.put("fileNo", getFileNo());
		attributes.put("fileEntryId", getFileEntryId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long fileTemplateId = (Long)attributes.get("fileTemplateId");

		if (fileTemplateId != null) {
			setFileTemplateId(fileTemplateId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
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

		String fileName = (String)attributes.get("fileName");

		if (fileName != null) {
			setFileName(fileName);
		}

		String fileNo = (String)attributes.get("fileNo");

		if (fileNo != null) {
			setFileNo(fileNo);
		}

		Long fileEntryId = (Long)attributes.get("fileEntryId");

		if (fileEntryId != null) {
			setFileEntryId(fileEntryId);
		}
	}

	@Override
	public FileTemplate toEscapedModel() {
		return new FileTemplateWrapper(_fileTemplate.toEscapedModel());
	}

	@Override
	public FileTemplate toUnescapedModel() {
		return new FileTemplateWrapper(_fileTemplate.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _fileTemplate.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _fileTemplate.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _fileTemplate.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _fileTemplate.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<FileTemplate> toCacheModel() {
		return _fileTemplate.toCacheModel();
	}

	@Override
	public int compareTo(FileTemplate fileTemplate) {
		return _fileTemplate.compareTo(fileTemplate);
	}

	@Override
	public int hashCode() {
		return _fileTemplate.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _fileTemplate.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new FileTemplateWrapper((FileTemplate)_fileTemplate.clone());
	}

	/**
	* Returns the file name of this file template.
	*
	* @return the file name of this file template
	*/
	@Override
	public java.lang.String getFileName() {
		return _fileTemplate.getFileName();
	}

	/**
	* Returns the file no of this file template.
	*
	* @return the file no of this file template
	*/
	@Override
	public java.lang.String getFileNo() {
		return _fileTemplate.getFileNo();
	}

	/**
	* Returns the user name of this file template.
	*
	* @return the user name of this file template
	*/
	@Override
	public java.lang.String getUserName() {
		return _fileTemplate.getUserName();
	}

	/**
	* Returns the user uuid of this file template.
	*
	* @return the user uuid of this file template
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _fileTemplate.getUserUuid();
	}

	/**
	* Returns the uuid of this file template.
	*
	* @return the uuid of this file template
	*/
	@Override
	public java.lang.String getUuid() {
		return _fileTemplate.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _fileTemplate.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _fileTemplate.toXmlString();
	}

	/**
	* Returns the create date of this file template.
	*
	* @return the create date of this file template
	*/
	@Override
	public Date getCreateDate() {
		return _fileTemplate.getCreateDate();
	}

	/**
	* Returns the modified date of this file template.
	*
	* @return the modified date of this file template
	*/
	@Override
	public Date getModifiedDate() {
		return _fileTemplate.getModifiedDate();
	}

	/**
	* Returns the company ID of this file template.
	*
	* @return the company ID of this file template
	*/
	@Override
	public long getCompanyId() {
		return _fileTemplate.getCompanyId();
	}

	/**
	* Returns the file entry ID of this file template.
	*
	* @return the file entry ID of this file template
	*/
	@Override
	public long getFileEntryId() {
		return _fileTemplate.getFileEntryId();
	}

	/**
	* Returns the file template ID of this file template.
	*
	* @return the file template ID of this file template
	*/
	@Override
	public long getFileTemplateId() {
		return _fileTemplate.getFileTemplateId();
	}

	/**
	* Returns the group ID of this file template.
	*
	* @return the group ID of this file template
	*/
	@Override
	public long getGroupId() {
		return _fileTemplate.getGroupId();
	}

	/**
	* Returns the primary key of this file template.
	*
	* @return the primary key of this file template
	*/
	@Override
	public long getPrimaryKey() {
		return _fileTemplate.getPrimaryKey();
	}

	/**
	* Returns the user ID of this file template.
	*
	* @return the user ID of this file template
	*/
	@Override
	public long getUserId() {
		return _fileTemplate.getUserId();
	}

	@Override
	public void persist() {
		_fileTemplate.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_fileTemplate.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this file template.
	*
	* @param companyId the company ID of this file template
	*/
	@Override
	public void setCompanyId(long companyId) {
		_fileTemplate.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this file template.
	*
	* @param createDate the create date of this file template
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_fileTemplate.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_fileTemplate.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_fileTemplate.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_fileTemplate.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the file entry ID of this file template.
	*
	* @param fileEntryId the file entry ID of this file template
	*/
	@Override
	public void setFileEntryId(long fileEntryId) {
		_fileTemplate.setFileEntryId(fileEntryId);
	}

	/**
	* Sets the file name of this file template.
	*
	* @param fileName the file name of this file template
	*/
	@Override
	public void setFileName(java.lang.String fileName) {
		_fileTemplate.setFileName(fileName);
	}

	/**
	* Sets the file no of this file template.
	*
	* @param fileNo the file no of this file template
	*/
	@Override
	public void setFileNo(java.lang.String fileNo) {
		_fileTemplate.setFileNo(fileNo);
	}

	/**
	* Sets the file template ID of this file template.
	*
	* @param fileTemplateId the file template ID of this file template
	*/
	@Override
	public void setFileTemplateId(long fileTemplateId) {
		_fileTemplate.setFileTemplateId(fileTemplateId);
	}

	/**
	* Sets the group ID of this file template.
	*
	* @param groupId the group ID of this file template
	*/
	@Override
	public void setGroupId(long groupId) {
		_fileTemplate.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this file template.
	*
	* @param modifiedDate the modified date of this file template
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_fileTemplate.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_fileTemplate.setNew(n);
	}

	/**
	* Sets the primary key of this file template.
	*
	* @param primaryKey the primary key of this file template
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_fileTemplate.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_fileTemplate.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this file template.
	*
	* @param userId the user ID of this file template
	*/
	@Override
	public void setUserId(long userId) {
		_fileTemplate.setUserId(userId);
	}

	/**
	* Sets the user name of this file template.
	*
	* @param userName the user name of this file template
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_fileTemplate.setUserName(userName);
	}

	/**
	* Sets the user uuid of this file template.
	*
	* @param userUuid the user uuid of this file template
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_fileTemplate.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this file template.
	*
	* @param uuid the uuid of this file template
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_fileTemplate.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof FileTemplateWrapper)) {
			return false;
		}

		FileTemplateWrapper fileTemplateWrapper = (FileTemplateWrapper)obj;

		if (Objects.equals(_fileTemplate, fileTemplateWrapper._fileTemplate)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _fileTemplate.getStagedModelType();
	}

	@Override
	public FileTemplate getWrappedModel() {
		return _fileTemplate;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _fileTemplate.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _fileTemplate.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_fileTemplate.resetOriginalValues();
	}

	private final FileTemplate _fileTemplate;
}