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

package org.opencps.deliverable.model;

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
 * This class is a wrapper for {@link OpenCPSDeliverableLog}.
 * </p>
 *
 * @author binhth
 * @see OpenCPSDeliverableLog
 * @generated
 */
@ProviderType
public class OpenCPSDeliverableLogWrapper implements OpenCPSDeliverableLog,
	ModelWrapper<OpenCPSDeliverableLog> {
	public OpenCPSDeliverableLogWrapper(
		OpenCPSDeliverableLog openCPSDeliverableLog) {
		_openCPSDeliverableLog = openCPSDeliverableLog;
	}

	@Override
	public Class<?> getModelClass() {
		return OpenCPSDeliverableLog.class;
	}

	@Override
	public String getModelClassName() {
		return OpenCPSDeliverableLog.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("deliverableLogId", getDeliverableLogId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("deliverableId", getDeliverableId());
		attributes.put("dossierUid", getDossierUid());
		attributes.put("author", getAuthor());
		attributes.put("content", getContent());
		attributes.put("deliverableAction", getDeliverableAction());
		attributes.put("actionDate", getActionDate());
		attributes.put("payload", getPayload());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long deliverableLogId = (Long)attributes.get("deliverableLogId");

		if (deliverableLogId != null) {
			setDeliverableLogId(deliverableLogId);
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

		Long deliverableId = (Long)attributes.get("deliverableId");

		if (deliverableId != null) {
			setDeliverableId(deliverableId);
		}

		String dossierUid = (String)attributes.get("dossierUid");

		if (dossierUid != null) {
			setDossierUid(dossierUid);
		}

		String author = (String)attributes.get("author");

		if (author != null) {
			setAuthor(author);
		}

		String content = (String)attributes.get("content");

		if (content != null) {
			setContent(content);
		}

		Integer deliverableAction = (Integer)attributes.get("deliverableAction");

		if (deliverableAction != null) {
			setDeliverableAction(deliverableAction);
		}

		Date actionDate = (Date)attributes.get("actionDate");

		if (actionDate != null) {
			setActionDate(actionDate);
		}

		String payload = (String)attributes.get("payload");

		if (payload != null) {
			setPayload(payload);
		}
	}

	@Override
	public Object clone() {
		return new OpenCPSDeliverableLogWrapper((OpenCPSDeliverableLog)_openCPSDeliverableLog.clone());
	}

	@Override
	public int compareTo(OpenCPSDeliverableLog openCPSDeliverableLog) {
		return _openCPSDeliverableLog.compareTo(openCPSDeliverableLog);
	}

	/**
	* Returns the action date of this open cps deliverable log.
	*
	* @return the action date of this open cps deliverable log
	*/
	@Override
	public Date getActionDate() {
		return _openCPSDeliverableLog.getActionDate();
	}

	/**
	* Returns the author of this open cps deliverable log.
	*
	* @return the author of this open cps deliverable log
	*/
	@Override
	public String getAuthor() {
		return _openCPSDeliverableLog.getAuthor();
	}

	/**
	* Returns the company ID of this open cps deliverable log.
	*
	* @return the company ID of this open cps deliverable log
	*/
	@Override
	public long getCompanyId() {
		return _openCPSDeliverableLog.getCompanyId();
	}

	/**
	* Returns the content of this open cps deliverable log.
	*
	* @return the content of this open cps deliverable log
	*/
	@Override
	public String getContent() {
		return _openCPSDeliverableLog.getContent();
	}

	/**
	* Returns the create date of this open cps deliverable log.
	*
	* @return the create date of this open cps deliverable log
	*/
	@Override
	public Date getCreateDate() {
		return _openCPSDeliverableLog.getCreateDate();
	}

	/**
	* Returns the deliverable action of this open cps deliverable log.
	*
	* @return the deliverable action of this open cps deliverable log
	*/
	@Override
	public int getDeliverableAction() {
		return _openCPSDeliverableLog.getDeliverableAction();
	}

	/**
	* Returns the deliverable ID of this open cps deliverable log.
	*
	* @return the deliverable ID of this open cps deliverable log
	*/
	@Override
	public long getDeliverableId() {
		return _openCPSDeliverableLog.getDeliverableId();
	}

	/**
	* Returns the deliverable log ID of this open cps deliverable log.
	*
	* @return the deliverable log ID of this open cps deliverable log
	*/
	@Override
	public long getDeliverableLogId() {
		return _openCPSDeliverableLog.getDeliverableLogId();
	}

	/**
	* Returns the dossier uid of this open cps deliverable log.
	*
	* @return the dossier uid of this open cps deliverable log
	*/
	@Override
	public String getDossierUid() {
		return _openCPSDeliverableLog.getDossierUid();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _openCPSDeliverableLog.getExpandoBridge();
	}

	/**
	* Returns the group ID of this open cps deliverable log.
	*
	* @return the group ID of this open cps deliverable log
	*/
	@Override
	public long getGroupId() {
		return _openCPSDeliverableLog.getGroupId();
	}

	/**
	* Returns the modified date of this open cps deliverable log.
	*
	* @return the modified date of this open cps deliverable log
	*/
	@Override
	public Date getModifiedDate() {
		return _openCPSDeliverableLog.getModifiedDate();
	}

	/**
	* Returns the payload of this open cps deliverable log.
	*
	* @return the payload of this open cps deliverable log
	*/
	@Override
	public String getPayload() {
		return _openCPSDeliverableLog.getPayload();
	}

	/**
	* Returns the primary key of this open cps deliverable log.
	*
	* @return the primary key of this open cps deliverable log
	*/
	@Override
	public long getPrimaryKey() {
		return _openCPSDeliverableLog.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _openCPSDeliverableLog.getPrimaryKeyObj();
	}

	/**
	* Returns the user ID of this open cps deliverable log.
	*
	* @return the user ID of this open cps deliverable log
	*/
	@Override
	public long getUserId() {
		return _openCPSDeliverableLog.getUserId();
	}

	/**
	* Returns the user name of this open cps deliverable log.
	*
	* @return the user name of this open cps deliverable log
	*/
	@Override
	public String getUserName() {
		return _openCPSDeliverableLog.getUserName();
	}

	/**
	* Returns the user uuid of this open cps deliverable log.
	*
	* @return the user uuid of this open cps deliverable log
	*/
	@Override
	public String getUserUuid() {
		return _openCPSDeliverableLog.getUserUuid();
	}

	/**
	* Returns the uuid of this open cps deliverable log.
	*
	* @return the uuid of this open cps deliverable log
	*/
	@Override
	public String getUuid() {
		return _openCPSDeliverableLog.getUuid();
	}

	@Override
	public int hashCode() {
		return _openCPSDeliverableLog.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _openCPSDeliverableLog.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _openCPSDeliverableLog.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _openCPSDeliverableLog.isNew();
	}

	@Override
	public void persist() {
		_openCPSDeliverableLog.persist();
	}

	/**
	* Sets the action date of this open cps deliverable log.
	*
	* @param actionDate the action date of this open cps deliverable log
	*/
	@Override
	public void setActionDate(Date actionDate) {
		_openCPSDeliverableLog.setActionDate(actionDate);
	}

	/**
	* Sets the author of this open cps deliverable log.
	*
	* @param author the author of this open cps deliverable log
	*/
	@Override
	public void setAuthor(String author) {
		_openCPSDeliverableLog.setAuthor(author);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_openCPSDeliverableLog.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this open cps deliverable log.
	*
	* @param companyId the company ID of this open cps deliverable log
	*/
	@Override
	public void setCompanyId(long companyId) {
		_openCPSDeliverableLog.setCompanyId(companyId);
	}

	/**
	* Sets the content of this open cps deliverable log.
	*
	* @param content the content of this open cps deliverable log
	*/
	@Override
	public void setContent(String content) {
		_openCPSDeliverableLog.setContent(content);
	}

	/**
	* Sets the create date of this open cps deliverable log.
	*
	* @param createDate the create date of this open cps deliverable log
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_openCPSDeliverableLog.setCreateDate(createDate);
	}

	/**
	* Sets the deliverable action of this open cps deliverable log.
	*
	* @param deliverableAction the deliverable action of this open cps deliverable log
	*/
	@Override
	public void setDeliverableAction(int deliverableAction) {
		_openCPSDeliverableLog.setDeliverableAction(deliverableAction);
	}

	/**
	* Sets the deliverable ID of this open cps deliverable log.
	*
	* @param deliverableId the deliverable ID of this open cps deliverable log
	*/
	@Override
	public void setDeliverableId(long deliverableId) {
		_openCPSDeliverableLog.setDeliverableId(deliverableId);
	}

	/**
	* Sets the deliverable log ID of this open cps deliverable log.
	*
	* @param deliverableLogId the deliverable log ID of this open cps deliverable log
	*/
	@Override
	public void setDeliverableLogId(long deliverableLogId) {
		_openCPSDeliverableLog.setDeliverableLogId(deliverableLogId);
	}

	/**
	* Sets the dossier uid of this open cps deliverable log.
	*
	* @param dossierUid the dossier uid of this open cps deliverable log
	*/
	@Override
	public void setDossierUid(String dossierUid) {
		_openCPSDeliverableLog.setDossierUid(dossierUid);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_openCPSDeliverableLog.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_openCPSDeliverableLog.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_openCPSDeliverableLog.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this open cps deliverable log.
	*
	* @param groupId the group ID of this open cps deliverable log
	*/
	@Override
	public void setGroupId(long groupId) {
		_openCPSDeliverableLog.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this open cps deliverable log.
	*
	* @param modifiedDate the modified date of this open cps deliverable log
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_openCPSDeliverableLog.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_openCPSDeliverableLog.setNew(n);
	}

	/**
	* Sets the payload of this open cps deliverable log.
	*
	* @param payload the payload of this open cps deliverable log
	*/
	@Override
	public void setPayload(String payload) {
		_openCPSDeliverableLog.setPayload(payload);
	}

	/**
	* Sets the primary key of this open cps deliverable log.
	*
	* @param primaryKey the primary key of this open cps deliverable log
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_openCPSDeliverableLog.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_openCPSDeliverableLog.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this open cps deliverable log.
	*
	* @param userId the user ID of this open cps deliverable log
	*/
	@Override
	public void setUserId(long userId) {
		_openCPSDeliverableLog.setUserId(userId);
	}

	/**
	* Sets the user name of this open cps deliverable log.
	*
	* @param userName the user name of this open cps deliverable log
	*/
	@Override
	public void setUserName(String userName) {
		_openCPSDeliverableLog.setUserName(userName);
	}

	/**
	* Sets the user uuid of this open cps deliverable log.
	*
	* @param userUuid the user uuid of this open cps deliverable log
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_openCPSDeliverableLog.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this open cps deliverable log.
	*
	* @param uuid the uuid of this open cps deliverable log
	*/
	@Override
	public void setUuid(String uuid) {
		_openCPSDeliverableLog.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<OpenCPSDeliverableLog> toCacheModel() {
		return _openCPSDeliverableLog.toCacheModel();
	}

	@Override
	public OpenCPSDeliverableLog toEscapedModel() {
		return new OpenCPSDeliverableLogWrapper(_openCPSDeliverableLog.toEscapedModel());
	}

	@Override
	public String toString() {
		return _openCPSDeliverableLog.toString();
	}

	@Override
	public OpenCPSDeliverableLog toUnescapedModel() {
		return new OpenCPSDeliverableLogWrapper(_openCPSDeliverableLog.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _openCPSDeliverableLog.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OpenCPSDeliverableLogWrapper)) {
			return false;
		}

		OpenCPSDeliverableLogWrapper openCPSDeliverableLogWrapper = (OpenCPSDeliverableLogWrapper)obj;

		if (Objects.equals(_openCPSDeliverableLog,
					openCPSDeliverableLogWrapper._openCPSDeliverableLog)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _openCPSDeliverableLog.getStagedModelType();
	}

	@Override
	public OpenCPSDeliverableLog getWrappedModel() {
		return _openCPSDeliverableLog;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _openCPSDeliverableLog.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _openCPSDeliverableLog.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_openCPSDeliverableLog.resetOriginalValues();
	}

	private final OpenCPSDeliverableLog _openCPSDeliverableLog;
}