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
 * This class is a wrapper for {@link DossierLog}.
 * </p>
 *
 * @author huymq
 * @see DossierLog
 * @generated
 */
@ProviderType
public class DossierLogWrapper implements DossierLog, ModelWrapper<DossierLog> {
	public DossierLogWrapper(DossierLog dossierLog) {
		_dossierLog = dossierLog;
	}

	@Override
	public Class<?> getModelClass() {
		return DossierLog.class;
	}

	@Override
	public String getModelClassName() {
		return DossierLog.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("dossierLogId", getDossierLogId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("dossierId", getDossierId());
		attributes.put("author", getAuthor());
		attributes.put("content", getContent());
		attributes.put("notificationType", getNotificationType());
		attributes.put("toUserIds", getToUserIds());
		attributes.put("deliveredDate", getDeliveredDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long dossierLogId = (Long)attributes.get("dossierLogId");

		if (dossierLogId != null) {
			setDossierLogId(dossierLogId);
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

		Long dossierId = (Long)attributes.get("dossierId");

		if (dossierId != null) {
			setDossierId(dossierId);
		}

		String author = (String)attributes.get("author");

		if (author != null) {
			setAuthor(author);
		}

		String content = (String)attributes.get("content");

		if (content != null) {
			setContent(content);
		}

		Integer notificationType = (Integer)attributes.get("notificationType");

		if (notificationType != null) {
			setNotificationType(notificationType);
		}

		String toUserIds = (String)attributes.get("toUserIds");

		if (toUserIds != null) {
			setToUserIds(toUserIds);
		}

		Date deliveredDate = (Date)attributes.get("deliveredDate");

		if (deliveredDate != null) {
			setDeliveredDate(deliveredDate);
		}
	}

	@Override
	public DossierLog toEscapedModel() {
		return new DossierLogWrapper(_dossierLog.toEscapedModel());
	}

	@Override
	public DossierLog toUnescapedModel() {
		return new DossierLogWrapper(_dossierLog.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _dossierLog.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _dossierLog.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _dossierLog.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _dossierLog.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<DossierLog> toCacheModel() {
		return _dossierLog.toCacheModel();
	}

	@Override
	public int compareTo(DossierLog dossierLog) {
		return _dossierLog.compareTo(dossierLog);
	}

	/**
	* Returns the notification type of this dossier log.
	*
	* @return the notification type of this dossier log
	*/
	@Override
	public int getNotificationType() {
		return _dossierLog.getNotificationType();
	}

	@Override
	public int hashCode() {
		return _dossierLog.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _dossierLog.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new DossierLogWrapper((DossierLog)_dossierLog.clone());
	}

	/**
	* Returns the author of this dossier log.
	*
	* @return the author of this dossier log
	*/
	@Override
	public java.lang.String getAuthor() {
		return _dossierLog.getAuthor();
	}

	/**
	* Returns the content of this dossier log.
	*
	* @return the content of this dossier log
	*/
	@Override
	public java.lang.String getContent() {
		return _dossierLog.getContent();
	}

	/**
	* Returns the to user IDs of this dossier log.
	*
	* @return the to user IDs of this dossier log
	*/
	@Override
	public java.lang.String getToUserIds() {
		return _dossierLog.getToUserIds();
	}

	/**
	* Returns the user name of this dossier log.
	*
	* @return the user name of this dossier log
	*/
	@Override
	public java.lang.String getUserName() {
		return _dossierLog.getUserName();
	}

	/**
	* Returns the user uuid of this dossier log.
	*
	* @return the user uuid of this dossier log
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _dossierLog.getUserUuid();
	}

	/**
	* Returns the uuid of this dossier log.
	*
	* @return the uuid of this dossier log
	*/
	@Override
	public java.lang.String getUuid() {
		return _dossierLog.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _dossierLog.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _dossierLog.toXmlString();
	}

	/**
	* Returns the create date of this dossier log.
	*
	* @return the create date of this dossier log
	*/
	@Override
	public Date getCreateDate() {
		return _dossierLog.getCreateDate();
	}

	/**
	* Returns the delivered date of this dossier log.
	*
	* @return the delivered date of this dossier log
	*/
	@Override
	public Date getDeliveredDate() {
		return _dossierLog.getDeliveredDate();
	}

	/**
	* Returns the modified date of this dossier log.
	*
	* @return the modified date of this dossier log
	*/
	@Override
	public Date getModifiedDate() {
		return _dossierLog.getModifiedDate();
	}

	/**
	* Returns the company ID of this dossier log.
	*
	* @return the company ID of this dossier log
	*/
	@Override
	public long getCompanyId() {
		return _dossierLog.getCompanyId();
	}

	/**
	* Returns the dossier ID of this dossier log.
	*
	* @return the dossier ID of this dossier log
	*/
	@Override
	public long getDossierId() {
		return _dossierLog.getDossierId();
	}

	/**
	* Returns the dossier log ID of this dossier log.
	*
	* @return the dossier log ID of this dossier log
	*/
	@Override
	public long getDossierLogId() {
		return _dossierLog.getDossierLogId();
	}

	/**
	* Returns the group ID of this dossier log.
	*
	* @return the group ID of this dossier log
	*/
	@Override
	public long getGroupId() {
		return _dossierLog.getGroupId();
	}

	/**
	* Returns the primary key of this dossier log.
	*
	* @return the primary key of this dossier log
	*/
	@Override
	public long getPrimaryKey() {
		return _dossierLog.getPrimaryKey();
	}

	/**
	* Returns the user ID of this dossier log.
	*
	* @return the user ID of this dossier log
	*/
	@Override
	public long getUserId() {
		return _dossierLog.getUserId();
	}

	@Override
	public void persist() {
		_dossierLog.persist();
	}

	/**
	* Sets the author of this dossier log.
	*
	* @param author the author of this dossier log
	*/
	@Override
	public void setAuthor(java.lang.String author) {
		_dossierLog.setAuthor(author);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_dossierLog.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this dossier log.
	*
	* @param companyId the company ID of this dossier log
	*/
	@Override
	public void setCompanyId(long companyId) {
		_dossierLog.setCompanyId(companyId);
	}

	/**
	* Sets the content of this dossier log.
	*
	* @param content the content of this dossier log
	*/
	@Override
	public void setContent(java.lang.String content) {
		_dossierLog.setContent(content);
	}

	/**
	* Sets the create date of this dossier log.
	*
	* @param createDate the create date of this dossier log
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_dossierLog.setCreateDate(createDate);
	}

	/**
	* Sets the delivered date of this dossier log.
	*
	* @param deliveredDate the delivered date of this dossier log
	*/
	@Override
	public void setDeliveredDate(Date deliveredDate) {
		_dossierLog.setDeliveredDate(deliveredDate);
	}

	/**
	* Sets the dossier ID of this dossier log.
	*
	* @param dossierId the dossier ID of this dossier log
	*/
	@Override
	public void setDossierId(long dossierId) {
		_dossierLog.setDossierId(dossierId);
	}

	/**
	* Sets the dossier log ID of this dossier log.
	*
	* @param dossierLogId the dossier log ID of this dossier log
	*/
	@Override
	public void setDossierLogId(long dossierLogId) {
		_dossierLog.setDossierLogId(dossierLogId);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_dossierLog.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_dossierLog.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_dossierLog.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this dossier log.
	*
	* @param groupId the group ID of this dossier log
	*/
	@Override
	public void setGroupId(long groupId) {
		_dossierLog.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this dossier log.
	*
	* @param modifiedDate the modified date of this dossier log
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_dossierLog.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_dossierLog.setNew(n);
	}

	/**
	* Sets the notification type of this dossier log.
	*
	* @param notificationType the notification type of this dossier log
	*/
	@Override
	public void setNotificationType(int notificationType) {
		_dossierLog.setNotificationType(notificationType);
	}

	/**
	* Sets the primary key of this dossier log.
	*
	* @param primaryKey the primary key of this dossier log
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_dossierLog.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_dossierLog.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the to user IDs of this dossier log.
	*
	* @param toUserIds the to user IDs of this dossier log
	*/
	@Override
	public void setToUserIds(java.lang.String toUserIds) {
		_dossierLog.setToUserIds(toUserIds);
	}

	/**
	* Sets the user ID of this dossier log.
	*
	* @param userId the user ID of this dossier log
	*/
	@Override
	public void setUserId(long userId) {
		_dossierLog.setUserId(userId);
	}

	/**
	* Sets the user name of this dossier log.
	*
	* @param userName the user name of this dossier log
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_dossierLog.setUserName(userName);
	}

	/**
	* Sets the user uuid of this dossier log.
	*
	* @param userUuid the user uuid of this dossier log
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_dossierLog.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this dossier log.
	*
	* @param uuid the uuid of this dossier log
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_dossierLog.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DossierLogWrapper)) {
			return false;
		}

		DossierLogWrapper dossierLogWrapper = (DossierLogWrapper)obj;

		if (Objects.equals(_dossierLog, dossierLogWrapper._dossierLog)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _dossierLog.getStagedModelType();
	}

	@Override
	public DossierLog getWrappedModel() {
		return _dossierLog;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _dossierLog.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _dossierLog.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_dossierLog.resetOriginalValues();
	}

	private final DossierLog _dossierLog;
}