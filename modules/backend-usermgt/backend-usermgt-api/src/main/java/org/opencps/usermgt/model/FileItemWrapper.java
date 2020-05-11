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
 * This class is a wrapper for {@link FileItem}.
 * </p>
 *
 * @author khoavu
 * @see FileItem
 * @generated
 */
@ProviderType
public class FileItemWrapper implements FileItem, ModelWrapper<FileItem> {
	public FileItemWrapper(FileItem fileItem) {
		_fileItem = fileItem;
	}

	@Override
	public Class<?> getModelClass() {
		return FileItem.class;
	}

	@Override
	public String getModelClassName() {
		return FileItem.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("fileItemId", getFileItemId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("fileTemplateNo", getFileTemplateNo());
		attributes.put("name", getName());
		attributes.put("status", getStatus());
		attributes.put("size", getSize());
		attributes.put("fileType", getFileType());
		attributes.put("log", getLog());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long fileItemId = (Long)attributes.get("fileItemId");

		if (fileItemId != null) {
			setFileItemId(fileItemId);
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

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Integer size = (Integer)attributes.get("size");

		if (size != null) {
			setSize(size);
		}

		String fileType = (String)attributes.get("fileType");

		if (fileType != null) {
			setFileType(fileType);
		}

		String log = (String)attributes.get("log");

		if (log != null) {
			setLog(log);
		}
	}

	@Override
	public Object clone() {
		return new FileItemWrapper((FileItem)_fileItem.clone());
	}

	@Override
	public int compareTo(FileItem fileItem) {
		return _fileItem.compareTo(fileItem);
	}

	/**
	* Returns the company ID of this file item.
	*
	* @return the company ID of this file item
	*/
	@Override
	public long getCompanyId() {
		return _fileItem.getCompanyId();
	}

	/**
	* Returns the create date of this file item.
	*
	* @return the create date of this file item
	*/
	@Override
	public Date getCreateDate() {
		return _fileItem.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _fileItem.getExpandoBridge();
	}

	/**
	* Returns the file item ID of this file item.
	*
	* @return the file item ID of this file item
	*/
	@Override
	public long getFileItemId() {
		return _fileItem.getFileItemId();
	}

	/**
	* Returns the file template no of this file item.
	*
	* @return the file template no of this file item
	*/
	@Override
	public String getFileTemplateNo() {
		return _fileItem.getFileTemplateNo();
	}

	/**
	* Returns the file type of this file item.
	*
	* @return the file type of this file item
	*/
	@Override
	public String getFileType() {
		return _fileItem.getFileType();
	}

	/**
	* Returns the group ID of this file item.
	*
	* @return the group ID of this file item
	*/
	@Override
	public long getGroupId() {
		return _fileItem.getGroupId();
	}

	/**
	* Returns the log of this file item.
	*
	* @return the log of this file item
	*/
	@Override
	public String getLog() {
		return _fileItem.getLog();
	}

	/**
	* Returns the modified date of this file item.
	*
	* @return the modified date of this file item
	*/
	@Override
	public Date getModifiedDate() {
		return _fileItem.getModifiedDate();
	}

	/**
	* Returns the name of this file item.
	*
	* @return the name of this file item
	*/
	@Override
	public String getName() {
		return _fileItem.getName();
	}

	/**
	* Returns the primary key of this file item.
	*
	* @return the primary key of this file item
	*/
	@Override
	public long getPrimaryKey() {
		return _fileItem.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _fileItem.getPrimaryKeyObj();
	}

	/**
	* Returns the size of this file item.
	*
	* @return the size of this file item
	*/
	@Override
	public int getSize() {
		return _fileItem.getSize();
	}

	/**
	* Returns the status of this file item.
	*
	* @return the status of this file item
	*/
	@Override
	public int getStatus() {
		return _fileItem.getStatus();
	}

	/**
	* Returns the user ID of this file item.
	*
	* @return the user ID of this file item
	*/
	@Override
	public long getUserId() {
		return _fileItem.getUserId();
	}

	/**
	* Returns the user name of this file item.
	*
	* @return the user name of this file item
	*/
	@Override
	public String getUserName() {
		return _fileItem.getUserName();
	}

	/**
	* Returns the user uuid of this file item.
	*
	* @return the user uuid of this file item
	*/
	@Override
	public String getUserUuid() {
		return _fileItem.getUserUuid();
	}

	/**
	* Returns the uuid of this file item.
	*
	* @return the uuid of this file item
	*/
	@Override
	public String getUuid() {
		return _fileItem.getUuid();
	}

	@Override
	public int hashCode() {
		return _fileItem.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _fileItem.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _fileItem.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _fileItem.isNew();
	}

	@Override
	public void persist() {
		_fileItem.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_fileItem.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this file item.
	*
	* @param companyId the company ID of this file item
	*/
	@Override
	public void setCompanyId(long companyId) {
		_fileItem.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this file item.
	*
	* @param createDate the create date of this file item
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_fileItem.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_fileItem.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_fileItem.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_fileItem.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the file item ID of this file item.
	*
	* @param fileItemId the file item ID of this file item
	*/
	@Override
	public void setFileItemId(long fileItemId) {
		_fileItem.setFileItemId(fileItemId);
	}

	/**
	* Sets the file template no of this file item.
	*
	* @param fileTemplateNo the file template no of this file item
	*/
	@Override
	public void setFileTemplateNo(String fileTemplateNo) {
		_fileItem.setFileTemplateNo(fileTemplateNo);
	}

	/**
	* Sets the file type of this file item.
	*
	* @param fileType the file type of this file item
	*/
	@Override
	public void setFileType(String fileType) {
		_fileItem.setFileType(fileType);
	}

	/**
	* Sets the group ID of this file item.
	*
	* @param groupId the group ID of this file item
	*/
	@Override
	public void setGroupId(long groupId) {
		_fileItem.setGroupId(groupId);
	}

	/**
	* Sets the log of this file item.
	*
	* @param log the log of this file item
	*/
	@Override
	public void setLog(String log) {
		_fileItem.setLog(log);
	}

	/**
	* Sets the modified date of this file item.
	*
	* @param modifiedDate the modified date of this file item
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_fileItem.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this file item.
	*
	* @param name the name of this file item
	*/
	@Override
	public void setName(String name) {
		_fileItem.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_fileItem.setNew(n);
	}

	/**
	* Sets the primary key of this file item.
	*
	* @param primaryKey the primary key of this file item
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_fileItem.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_fileItem.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the size of this file item.
	*
	* @param size the size of this file item
	*/
	@Override
	public void setSize(int size) {
		_fileItem.setSize(size);
	}

	/**
	* Sets the status of this file item.
	*
	* @param status the status of this file item
	*/
	@Override
	public void setStatus(int status) {
		_fileItem.setStatus(status);
	}

	/**
	* Sets the user ID of this file item.
	*
	* @param userId the user ID of this file item
	*/
	@Override
	public void setUserId(long userId) {
		_fileItem.setUserId(userId);
	}

	/**
	* Sets the user name of this file item.
	*
	* @param userName the user name of this file item
	*/
	@Override
	public void setUserName(String userName) {
		_fileItem.setUserName(userName);
	}

	/**
	* Sets the user uuid of this file item.
	*
	* @param userUuid the user uuid of this file item
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_fileItem.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this file item.
	*
	* @param uuid the uuid of this file item
	*/
	@Override
	public void setUuid(String uuid) {
		_fileItem.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<FileItem> toCacheModel() {
		return _fileItem.toCacheModel();
	}

	@Override
	public FileItem toEscapedModel() {
		return new FileItemWrapper(_fileItem.toEscapedModel());
	}

	@Override
	public String toString() {
		return _fileItem.toString();
	}

	@Override
	public FileItem toUnescapedModel() {
		return new FileItemWrapper(_fileItem.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _fileItem.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof FileItemWrapper)) {
			return false;
		}

		FileItemWrapper fileItemWrapper = (FileItemWrapper)obj;

		if (Objects.equals(_fileItem, fileItemWrapper._fileItem)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _fileItem.getStagedModelType();
	}

	@Override
	public FileItem getWrappedModel() {
		return _fileItem;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _fileItem.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _fileItem.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_fileItem.resetOriginalValues();
	}

	private final FileItem _fileItem;
}