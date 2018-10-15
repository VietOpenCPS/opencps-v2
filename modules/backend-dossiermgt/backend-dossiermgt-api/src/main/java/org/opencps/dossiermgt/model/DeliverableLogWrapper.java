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

package org.opencps.dossiermgt.model;

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
 * This class is a wrapper for {@link DeliverableLog}.
 * </p>
 *
 * @author huymq
 * @see DeliverableLog
 * @generated
 */
@ProviderType
public class DeliverableLogWrapper implements DeliverableLog,
	ModelWrapper<DeliverableLog> {
	public DeliverableLogWrapper(DeliverableLog deliverableLog) {
		_deliverableLog = deliverableLog;
	}

	@Override
	public Class<?> getModelClass() {
		return DeliverableLog.class;
	}

	@Override
	public String getModelClassName() {
		return DeliverableLog.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("deliverableLogId", getDeliverableLogId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
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

		String deliverableId = (String)attributes.get("deliverableId");

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
		return new DeliverableLogWrapper((DeliverableLog)_deliverableLog.clone());
	}

	@Override
	public int compareTo(DeliverableLog deliverableLog) {
		return _deliverableLog.compareTo(deliverableLog);
	}

	/**
	* Returns the action date of this deliverable log.
	*
	* @return the action date of this deliverable log
	*/
	@Override
	public Date getActionDate() {
		return _deliverableLog.getActionDate();
	}

	/**
	* Returns the author of this deliverable log.
	*
	* @return the author of this deliverable log
	*/
	@Override
	public String getAuthor() {
		return _deliverableLog.getAuthor();
	}

	/**
	* Returns the company ID of this deliverable log.
	*
	* @return the company ID of this deliverable log
	*/
	@Override
	public long getCompanyId() {
		return _deliverableLog.getCompanyId();
	}

	/**
	* Returns the content of this deliverable log.
	*
	* @return the content of this deliverable log
	*/
	@Override
	public String getContent() {
		return _deliverableLog.getContent();
	}

	/**
	* Returns the create date of this deliverable log.
	*
	* @return the create date of this deliverable log
	*/
	@Override
	public Date getCreateDate() {
		return _deliverableLog.getCreateDate();
	}

	/**
	* Returns the deliverable action of this deliverable log.
	*
	* @return the deliverable action of this deliverable log
	*/
	@Override
	public int getDeliverableAction() {
		return _deliverableLog.getDeliverableAction();
	}

	/**
	* Returns the deliverable ID of this deliverable log.
	*
	* @return the deliverable ID of this deliverable log
	*/
	@Override
	public String getDeliverableId() {
		return _deliverableLog.getDeliverableId();
	}

	/**
	* Returns the deliverable log ID of this deliverable log.
	*
	* @return the deliverable log ID of this deliverable log
	*/
	@Override
	public long getDeliverableLogId() {
		return _deliverableLog.getDeliverableLogId();
	}

	/**
	* Returns the dossier uid of this deliverable log.
	*
	* @return the dossier uid of this deliverable log
	*/
	@Override
	public String getDossierUid() {
		return _deliverableLog.getDossierUid();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _deliverableLog.getExpandoBridge();
	}

	/**
	* Returns the group ID of this deliverable log.
	*
	* @return the group ID of this deliverable log
	*/
	@Override
	public long getGroupId() {
		return _deliverableLog.getGroupId();
	}

	/**
	* Returns the modified date of this deliverable log.
	*
	* @return the modified date of this deliverable log
	*/
	@Override
	public Date getModifiedDate() {
		return _deliverableLog.getModifiedDate();
	}

	/**
	* Returns the payload of this deliverable log.
	*
	* @return the payload of this deliverable log
	*/
	@Override
	public String getPayload() {
		return _deliverableLog.getPayload();
	}

	/**
	* Returns the primary key of this deliverable log.
	*
	* @return the primary key of this deliverable log
	*/
	@Override
	public long getPrimaryKey() {
		return _deliverableLog.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _deliverableLog.getPrimaryKeyObj();
	}

	/**
	* Returns the user ID of this deliverable log.
	*
	* @return the user ID of this deliverable log
	*/
	@Override
	public long getUserId() {
		return _deliverableLog.getUserId();
	}

	/**
	* Returns the user name of this deliverable log.
	*
	* @return the user name of this deliverable log
	*/
	@Override
	public String getUserName() {
		return _deliverableLog.getUserName();
	}

	/**
	* Returns the user uuid of this deliverable log.
	*
	* @return the user uuid of this deliverable log
	*/
	@Override
	public String getUserUuid() {
		return _deliverableLog.getUserUuid();
	}

	/**
	* Returns the uuid of this deliverable log.
	*
	* @return the uuid of this deliverable log
	*/
	@Override
	public String getUuid() {
		return _deliverableLog.getUuid();
	}

	@Override
	public int hashCode() {
		return _deliverableLog.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _deliverableLog.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _deliverableLog.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _deliverableLog.isNew();
	}

	@Override
	public void persist() {
		_deliverableLog.persist();
	}

	/**
	* Sets the action date of this deliverable log.
	*
	* @param actionDate the action date of this deliverable log
	*/
	@Override
	public void setActionDate(Date actionDate) {
		_deliverableLog.setActionDate(actionDate);
	}

	/**
	* Sets the author of this deliverable log.
	*
	* @param author the author of this deliverable log
	*/
	@Override
	public void setAuthor(String author) {
		_deliverableLog.setAuthor(author);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_deliverableLog.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this deliverable log.
	*
	* @param companyId the company ID of this deliverable log
	*/
	@Override
	public void setCompanyId(long companyId) {
		_deliverableLog.setCompanyId(companyId);
	}

	/**
	* Sets the content of this deliverable log.
	*
	* @param content the content of this deliverable log
	*/
	@Override
	public void setContent(String content) {
		_deliverableLog.setContent(content);
	}

	/**
	* Sets the create date of this deliverable log.
	*
	* @param createDate the create date of this deliverable log
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_deliverableLog.setCreateDate(createDate);
	}

	/**
	* Sets the deliverable action of this deliverable log.
	*
	* @param deliverableAction the deliverable action of this deliverable log
	*/
	@Override
	public void setDeliverableAction(int deliverableAction) {
		_deliverableLog.setDeliverableAction(deliverableAction);
	}

	/**
	* Sets the deliverable ID of this deliverable log.
	*
	* @param deliverableId the deliverable ID of this deliverable log
	*/
	@Override
	public void setDeliverableId(String deliverableId) {
		_deliverableLog.setDeliverableId(deliverableId);
	}

	/**
	* Sets the deliverable log ID of this deliverable log.
	*
	* @param deliverableLogId the deliverable log ID of this deliverable log
	*/
	@Override
	public void setDeliverableLogId(long deliverableLogId) {
		_deliverableLog.setDeliverableLogId(deliverableLogId);
	}

	/**
	* Sets the dossier uid of this deliverable log.
	*
	* @param dossierUid the dossier uid of this deliverable log
	*/
	@Override
	public void setDossierUid(String dossierUid) {
		_deliverableLog.setDossierUid(dossierUid);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_deliverableLog.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_deliverableLog.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_deliverableLog.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this deliverable log.
	*
	* @param groupId the group ID of this deliverable log
	*/
	@Override
	public void setGroupId(long groupId) {
		_deliverableLog.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this deliverable log.
	*
	* @param modifiedDate the modified date of this deliverable log
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_deliverableLog.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_deliverableLog.setNew(n);
	}

	/**
	* Sets the payload of this deliverable log.
	*
	* @param payload the payload of this deliverable log
	*/
	@Override
	public void setPayload(String payload) {
		_deliverableLog.setPayload(payload);
	}

	/**
	* Sets the primary key of this deliverable log.
	*
	* @param primaryKey the primary key of this deliverable log
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_deliverableLog.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_deliverableLog.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this deliverable log.
	*
	* @param userId the user ID of this deliverable log
	*/
	@Override
	public void setUserId(long userId) {
		_deliverableLog.setUserId(userId);
	}

	/**
	* Sets the user name of this deliverable log.
	*
	* @param userName the user name of this deliverable log
	*/
	@Override
	public void setUserName(String userName) {
		_deliverableLog.setUserName(userName);
	}

	/**
	* Sets the user uuid of this deliverable log.
	*
	* @param userUuid the user uuid of this deliverable log
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_deliverableLog.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this deliverable log.
	*
	* @param uuid the uuid of this deliverable log
	*/
	@Override
	public void setUuid(String uuid) {
		_deliverableLog.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<DeliverableLog> toCacheModel() {
		return _deliverableLog.toCacheModel();
	}

	@Override
	public DeliverableLog toEscapedModel() {
		return new DeliverableLogWrapper(_deliverableLog.toEscapedModel());
	}

	@Override
	public String toString() {
		return _deliverableLog.toString();
	}

	@Override
	public DeliverableLog toUnescapedModel() {
		return new DeliverableLogWrapper(_deliverableLog.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _deliverableLog.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DeliverableLogWrapper)) {
			return false;
		}

		DeliverableLogWrapper deliverableLogWrapper = (DeliverableLogWrapper)obj;

		if (Objects.equals(_deliverableLog,
					deliverableLogWrapper._deliverableLog)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _deliverableLog.getStagedModelType();
	}

	@Override
	public DeliverableLog getWrappedModel() {
		return _deliverableLog;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _deliverableLog.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _deliverableLog.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_deliverableLog.resetOriginalValues();
	}

	private final DeliverableLog _deliverableLog;
}