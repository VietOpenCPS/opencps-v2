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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link FileAttach}.
 * </p>
 *
 * @author khoavu
 * @see FileAttach
 * @generated
 */
@ProviderType
public class FileAttachWrapper implements FileAttach, ModelWrapper<FileAttach> {
	public FileAttachWrapper(FileAttach fileAttach) {
		_fileAttach = fileAttach;
	}

	@Override
	public Class<?> getModelClass() {
		return FileAttach.class;
	}

	@Override
	public String getModelClassName() {
		return FileAttach.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("fileAttachId", getFileAttachId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("className", getClassName());
		attributes.put("classPK", getClassPK());
		attributes.put("fullName", getFullName());
		attributes.put("email", getEmail());
		attributes.put("fileEntryId", getFileEntryId());
		attributes.put("source", getSource());
		attributes.put("sourceUrl", getSourceUrl());
		attributes.put("docFileId", getDocFileId());
		attributes.put("fileName", getFileName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long fileAttachId = (Long)attributes.get("fileAttachId");

		if (fileAttachId != null) {
			setFileAttachId(fileAttachId);
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

		String className = (String)attributes.get("className");

		if (className != null) {
			setClassName(className);
		}

		String classPK = (String)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		String fullName = (String)attributes.get("fullName");

		if (fullName != null) {
			setFullName(fullName);
		}

		String email = (String)attributes.get("email");

		if (email != null) {
			setEmail(email);
		}

		Long fileEntryId = (Long)attributes.get("fileEntryId");

		if (fileEntryId != null) {
			setFileEntryId(fileEntryId);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		String sourceUrl = (String)attributes.get("sourceUrl");

		if (sourceUrl != null) {
			setSourceUrl(sourceUrl);
		}

		Long docFileId = (Long)attributes.get("docFileId");

		if (docFileId != null) {
			setDocFileId(docFileId);
		}

		String fileName = (String)attributes.get("fileName");

		if (fileName != null) {
			setFileName(fileName);
		}
	}

	@Override
	public Object clone() {
		return new FileAttachWrapper((FileAttach)_fileAttach.clone());
	}

	@Override
	public int compareTo(FileAttach fileAttach) {
		return _fileAttach.compareTo(fileAttach);
	}

	/**
	* Returns the class name of this file attach.
	*
	* @return the class name of this file attach
	*/
	@Override
	public String getClassName() {
		return _fileAttach.getClassName();
	}

	/**
	* Returns the class pk of this file attach.
	*
	* @return the class pk of this file attach
	*/
	@Override
	public String getClassPK() {
		return _fileAttach.getClassPK();
	}

	/**
	* Returns the company ID of this file attach.
	*
	* @return the company ID of this file attach
	*/
	@Override
	public long getCompanyId() {
		return _fileAttach.getCompanyId();
	}

	/**
	* Returns the create date of this file attach.
	*
	* @return the create date of this file attach
	*/
	@Override
	public Date getCreateDate() {
		return _fileAttach.getCreateDate();
	}

	/**
	* Returns the doc file ID of this file attach.
	*
	* @return the doc file ID of this file attach
	*/
	@Override
	public long getDocFileId() {
		return _fileAttach.getDocFileId();
	}

	/**
	* Returns the email of this file attach.
	*
	* @return the email of this file attach
	*/
	@Override
	public String getEmail() {
		return _fileAttach.getEmail();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _fileAttach.getExpandoBridge();
	}

	/**
	* Returns the file attach ID of this file attach.
	*
	* @return the file attach ID of this file attach
	*/
	@Override
	public long getFileAttachId() {
		return _fileAttach.getFileAttachId();
	}

	/**
	* Returns the file entry ID of this file attach.
	*
	* @return the file entry ID of this file attach
	*/
	@Override
	public long getFileEntryId() {
		return _fileAttach.getFileEntryId();
	}

	/**
	* Returns the file name of this file attach.
	*
	* @return the file name of this file attach
	*/
	@Override
	public String getFileName() {
		return _fileAttach.getFileName();
	}

	/**
	* Returns the full name of this file attach.
	*
	* @return the full name of this file attach
	*/
	@Override
	public String getFullName() {
		return _fileAttach.getFullName();
	}

	/**
	* Returns the group ID of this file attach.
	*
	* @return the group ID of this file attach
	*/
	@Override
	public long getGroupId() {
		return _fileAttach.getGroupId();
	}

	/**
	* Returns the modified date of this file attach.
	*
	* @return the modified date of this file attach
	*/
	@Override
	public Date getModifiedDate() {
		return _fileAttach.getModifiedDate();
	}

	/**
	* Returns the primary key of this file attach.
	*
	* @return the primary key of this file attach
	*/
	@Override
	public long getPrimaryKey() {
		return _fileAttach.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _fileAttach.getPrimaryKeyObj();
	}

	/**
	* Returns the source of this file attach.
	*
	* @return the source of this file attach
	*/
	@Override
	public String getSource() {
		return _fileAttach.getSource();
	}

	/**
	* Returns the source url of this file attach.
	*
	* @return the source url of this file attach
	*/
	@Override
	public String getSourceUrl() {
		return _fileAttach.getSourceUrl();
	}

	/**
	* Returns the user ID of this file attach.
	*
	* @return the user ID of this file attach
	*/
	@Override
	public long getUserId() {
		return _fileAttach.getUserId();
	}

	/**
	* Returns the user name of this file attach.
	*
	* @return the user name of this file attach
	*/
	@Override
	public String getUserName() {
		return _fileAttach.getUserName();
	}

	/**
	* Returns the user uuid of this file attach.
	*
	* @return the user uuid of this file attach
	*/
	@Override
	public String getUserUuid() {
		return _fileAttach.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _fileAttach.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _fileAttach.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _fileAttach.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _fileAttach.isNew();
	}

	@Override
	public void persist() {
		_fileAttach.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_fileAttach.setCachedModel(cachedModel);
	}

	/**
	* Sets the class name of this file attach.
	*
	* @param className the class name of this file attach
	*/
	@Override
	public void setClassName(String className) {
		_fileAttach.setClassName(className);
	}

	/**
	* Sets the class pk of this file attach.
	*
	* @param classPK the class pk of this file attach
	*/
	@Override
	public void setClassPK(String classPK) {
		_fileAttach.setClassPK(classPK);
	}

	/**
	* Sets the company ID of this file attach.
	*
	* @param companyId the company ID of this file attach
	*/
	@Override
	public void setCompanyId(long companyId) {
		_fileAttach.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this file attach.
	*
	* @param createDate the create date of this file attach
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_fileAttach.setCreateDate(createDate);
	}

	/**
	* Sets the doc file ID of this file attach.
	*
	* @param docFileId the doc file ID of this file attach
	*/
	@Override
	public void setDocFileId(long docFileId) {
		_fileAttach.setDocFileId(docFileId);
	}

	/**
	* Sets the email of this file attach.
	*
	* @param email the email of this file attach
	*/
	@Override
	public void setEmail(String email) {
		_fileAttach.setEmail(email);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_fileAttach.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_fileAttach.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_fileAttach.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the file attach ID of this file attach.
	*
	* @param fileAttachId the file attach ID of this file attach
	*/
	@Override
	public void setFileAttachId(long fileAttachId) {
		_fileAttach.setFileAttachId(fileAttachId);
	}

	/**
	* Sets the file entry ID of this file attach.
	*
	* @param fileEntryId the file entry ID of this file attach
	*/
	@Override
	public void setFileEntryId(long fileEntryId) {
		_fileAttach.setFileEntryId(fileEntryId);
	}

	/**
	* Sets the file name of this file attach.
	*
	* @param fileName the file name of this file attach
	*/
	@Override
	public void setFileName(String fileName) {
		_fileAttach.setFileName(fileName);
	}

	/**
	* Sets the full name of this file attach.
	*
	* @param fullName the full name of this file attach
	*/
	@Override
	public void setFullName(String fullName) {
		_fileAttach.setFullName(fullName);
	}

	/**
	* Sets the group ID of this file attach.
	*
	* @param groupId the group ID of this file attach
	*/
	@Override
	public void setGroupId(long groupId) {
		_fileAttach.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this file attach.
	*
	* @param modifiedDate the modified date of this file attach
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_fileAttach.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_fileAttach.setNew(n);
	}

	/**
	* Sets the primary key of this file attach.
	*
	* @param primaryKey the primary key of this file attach
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_fileAttach.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_fileAttach.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the source of this file attach.
	*
	* @param source the source of this file attach
	*/
	@Override
	public void setSource(String source) {
		_fileAttach.setSource(source);
	}

	/**
	* Sets the source url of this file attach.
	*
	* @param sourceUrl the source url of this file attach
	*/
	@Override
	public void setSourceUrl(String sourceUrl) {
		_fileAttach.setSourceUrl(sourceUrl);
	}

	/**
	* Sets the user ID of this file attach.
	*
	* @param userId the user ID of this file attach
	*/
	@Override
	public void setUserId(long userId) {
		_fileAttach.setUserId(userId);
	}

	/**
	* Sets the user name of this file attach.
	*
	* @param userName the user name of this file attach
	*/
	@Override
	public void setUserName(String userName) {
		_fileAttach.setUserName(userName);
	}

	/**
	* Sets the user uuid of this file attach.
	*
	* @param userUuid the user uuid of this file attach
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_fileAttach.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<FileAttach> toCacheModel() {
		return _fileAttach.toCacheModel();
	}

	@Override
	public FileAttach toEscapedModel() {
		return new FileAttachWrapper(_fileAttach.toEscapedModel());
	}

	@Override
	public String toString() {
		return _fileAttach.toString();
	}

	@Override
	public FileAttach toUnescapedModel() {
		return new FileAttachWrapper(_fileAttach.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _fileAttach.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof FileAttachWrapper)) {
			return false;
		}

		FileAttachWrapper fileAttachWrapper = (FileAttachWrapper)obj;

		if (Objects.equals(_fileAttach, fileAttachWrapper._fileAttach)) {
			return true;
		}

		return false;
	}

	@Override
	public FileAttach getWrappedModel() {
		return _fileAttach;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _fileAttach.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _fileAttach.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_fileAttach.resetOriginalValues();
	}

	private final FileAttach _fileAttach;
}