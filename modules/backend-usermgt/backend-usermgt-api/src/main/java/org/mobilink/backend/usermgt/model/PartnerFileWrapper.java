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
 * This class is a wrapper for {@link PartnerFile}.
 * </p>
 *
 * @author Binhth
 * @see PartnerFile
 * @generated
 */
@ProviderType
public class PartnerFileWrapper implements PartnerFile,
	ModelWrapper<PartnerFile> {
	public PartnerFileWrapper(PartnerFile partnerFile) {
		_partnerFile = partnerFile;
	}

	@Override
	public Class<?> getModelClass() {
		return PartnerFile.class;
	}

	@Override
	public String getModelClassName() {
		return PartnerFile.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("partnerFileId", getPartnerFileId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("partnerId", getPartnerId());
		attributes.put("fileEntryId", getFileEntryId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long partnerFileId = (Long)attributes.get("partnerFileId");

		if (partnerFileId != null) {
			setPartnerFileId(partnerFileId);
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

		Long partnerId = (Long)attributes.get("partnerId");

		if (partnerId != null) {
			setPartnerId(partnerId);
		}

		Long fileEntryId = (Long)attributes.get("fileEntryId");

		if (fileEntryId != null) {
			setFileEntryId(fileEntryId);
		}
	}

	@Override
	public PartnerFile toEscapedModel() {
		return new PartnerFileWrapper(_partnerFile.toEscapedModel());
	}

	@Override
	public PartnerFile toUnescapedModel() {
		return new PartnerFileWrapper(_partnerFile.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _partnerFile.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _partnerFile.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _partnerFile.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _partnerFile.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<PartnerFile> toCacheModel() {
		return _partnerFile.toCacheModel();
	}

	@Override
	public int compareTo(PartnerFile partnerFile) {
		return _partnerFile.compareTo(partnerFile);
	}

	@Override
	public int hashCode() {
		return _partnerFile.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _partnerFile.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new PartnerFileWrapper((PartnerFile)_partnerFile.clone());
	}

	/**
	* Returns the user name of this partner file.
	*
	* @return the user name of this partner file
	*/
	@Override
	public java.lang.String getUserName() {
		return _partnerFile.getUserName();
	}

	/**
	* Returns the user uuid of this partner file.
	*
	* @return the user uuid of this partner file
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _partnerFile.getUserUuid();
	}

	/**
	* Returns the uuid of this partner file.
	*
	* @return the uuid of this partner file
	*/
	@Override
	public java.lang.String getUuid() {
		return _partnerFile.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _partnerFile.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _partnerFile.toXmlString();
	}

	/**
	* Returns the create date of this partner file.
	*
	* @return the create date of this partner file
	*/
	@Override
	public Date getCreateDate() {
		return _partnerFile.getCreateDate();
	}

	/**
	* Returns the modified date of this partner file.
	*
	* @return the modified date of this partner file
	*/
	@Override
	public Date getModifiedDate() {
		return _partnerFile.getModifiedDate();
	}

	/**
	* Returns the company ID of this partner file.
	*
	* @return the company ID of this partner file
	*/
	@Override
	public long getCompanyId() {
		return _partnerFile.getCompanyId();
	}

	/**
	* Returns the file entry ID of this partner file.
	*
	* @return the file entry ID of this partner file
	*/
	@Override
	public long getFileEntryId() {
		return _partnerFile.getFileEntryId();
	}

	/**
	* Returns the group ID of this partner file.
	*
	* @return the group ID of this partner file
	*/
	@Override
	public long getGroupId() {
		return _partnerFile.getGroupId();
	}

	/**
	* Returns the partner file ID of this partner file.
	*
	* @return the partner file ID of this partner file
	*/
	@Override
	public long getPartnerFileId() {
		return _partnerFile.getPartnerFileId();
	}

	/**
	* Returns the partner ID of this partner file.
	*
	* @return the partner ID of this partner file
	*/
	@Override
	public long getPartnerId() {
		return _partnerFile.getPartnerId();
	}

	/**
	* Returns the primary key of this partner file.
	*
	* @return the primary key of this partner file
	*/
	@Override
	public long getPrimaryKey() {
		return _partnerFile.getPrimaryKey();
	}

	/**
	* Returns the user ID of this partner file.
	*
	* @return the user ID of this partner file
	*/
	@Override
	public long getUserId() {
		return _partnerFile.getUserId();
	}

	@Override
	public void persist() {
		_partnerFile.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_partnerFile.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this partner file.
	*
	* @param companyId the company ID of this partner file
	*/
	@Override
	public void setCompanyId(long companyId) {
		_partnerFile.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this partner file.
	*
	* @param createDate the create date of this partner file
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_partnerFile.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_partnerFile.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_partnerFile.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_partnerFile.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the file entry ID of this partner file.
	*
	* @param fileEntryId the file entry ID of this partner file
	*/
	@Override
	public void setFileEntryId(long fileEntryId) {
		_partnerFile.setFileEntryId(fileEntryId);
	}

	/**
	* Sets the group ID of this partner file.
	*
	* @param groupId the group ID of this partner file
	*/
	@Override
	public void setGroupId(long groupId) {
		_partnerFile.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this partner file.
	*
	* @param modifiedDate the modified date of this partner file
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_partnerFile.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_partnerFile.setNew(n);
	}

	/**
	* Sets the partner file ID of this partner file.
	*
	* @param partnerFileId the partner file ID of this partner file
	*/
	@Override
	public void setPartnerFileId(long partnerFileId) {
		_partnerFile.setPartnerFileId(partnerFileId);
	}

	/**
	* Sets the partner ID of this partner file.
	*
	* @param partnerId the partner ID of this partner file
	*/
	@Override
	public void setPartnerId(long partnerId) {
		_partnerFile.setPartnerId(partnerId);
	}

	/**
	* Sets the primary key of this partner file.
	*
	* @param primaryKey the primary key of this partner file
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_partnerFile.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_partnerFile.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this partner file.
	*
	* @param userId the user ID of this partner file
	*/
	@Override
	public void setUserId(long userId) {
		_partnerFile.setUserId(userId);
	}

	/**
	* Sets the user name of this partner file.
	*
	* @param userName the user name of this partner file
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_partnerFile.setUserName(userName);
	}

	/**
	* Sets the user uuid of this partner file.
	*
	* @param userUuid the user uuid of this partner file
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_partnerFile.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this partner file.
	*
	* @param uuid the uuid of this partner file
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_partnerFile.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PartnerFileWrapper)) {
			return false;
		}

		PartnerFileWrapper partnerFileWrapper = (PartnerFileWrapper)obj;

		if (Objects.equals(_partnerFile, partnerFileWrapper._partnerFile)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _partnerFile.getStagedModelType();
	}

	@Override
	public PartnerFile getWrappedModel() {
		return _partnerFile;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _partnerFile.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _partnerFile.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_partnerFile.resetOriginalValues();
	}

	private final PartnerFile _partnerFile;
}