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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link PublishQueue}.
 * </p>
 *
 * @author huymq
 * @see PublishQueue
 * @generated
 */
@ProviderType
public class PublishQueueWrapper implements PublishQueue,
	ModelWrapper<PublishQueue> {
	public PublishQueueWrapper(PublishQueue publishQueue) {
		_publishQueue = publishQueue;
	}

	@Override
	public Class<?> getModelClass() {
		return PublishQueue.class;
	}

	@Override
	public String getModelClassName() {
		return PublishQueue.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("publishQueueId", getPublishQueueId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("dossierId", getDossierId());
		attributes.put("serverNo", getServerNo());
		attributes.put("status", getStatus());
		attributes.put("retry", getRetry());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long publishQueueId = (Long)attributes.get("publishQueueId");

		if (publishQueueId != null) {
			setPublishQueueId(publishQueueId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
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

		String serverNo = (String)attributes.get("serverNo");

		if (serverNo != null) {
			setServerNo(serverNo);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Integer retry = (Integer)attributes.get("retry");

		if (retry != null) {
			setRetry(retry);
		}
	}

	@Override
	public Object clone() {
		return new PublishQueueWrapper((PublishQueue)_publishQueue.clone());
	}

	@Override
	public int compareTo(PublishQueue publishQueue) {
		return _publishQueue.compareTo(publishQueue);
	}

	/**
	* Returns the create date of this publish queue.
	*
	* @return the create date of this publish queue
	*/
	@Override
	public Date getCreateDate() {
		return _publishQueue.getCreateDate();
	}

	/**
	* Returns the dossier ID of this publish queue.
	*
	* @return the dossier ID of this publish queue
	*/
	@Override
	public long getDossierId() {
		return _publishQueue.getDossierId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _publishQueue.getExpandoBridge();
	}

	/**
	* Returns the group ID of this publish queue.
	*
	* @return the group ID of this publish queue
	*/
	@Override
	public long getGroupId() {
		return _publishQueue.getGroupId();
	}

	/**
	* Returns the modified date of this publish queue.
	*
	* @return the modified date of this publish queue
	*/
	@Override
	public Date getModifiedDate() {
		return _publishQueue.getModifiedDate();
	}

	/**
	* Returns the primary key of this publish queue.
	*
	* @return the primary key of this publish queue
	*/
	@Override
	public long getPrimaryKey() {
		return _publishQueue.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _publishQueue.getPrimaryKeyObj();
	}

	/**
	* Returns the publish queue ID of this publish queue.
	*
	* @return the publish queue ID of this publish queue
	*/
	@Override
	public long getPublishQueueId() {
		return _publishQueue.getPublishQueueId();
	}

	/**
	* Returns the retry of this publish queue.
	*
	* @return the retry of this publish queue
	*/
	@Override
	public int getRetry() {
		return _publishQueue.getRetry();
	}

	/**
	* Returns the server no of this publish queue.
	*
	* @return the server no of this publish queue
	*/
	@Override
	public String getServerNo() {
		return _publishQueue.getServerNo();
	}

	/**
	* Returns the status of this publish queue.
	*
	* @return the status of this publish queue
	*/
	@Override
	public int getStatus() {
		return _publishQueue.getStatus();
	}

	/**
	* Returns the user ID of this publish queue.
	*
	* @return the user ID of this publish queue
	*/
	@Override
	public long getUserId() {
		return _publishQueue.getUserId();
	}

	/**
	* Returns the user uuid of this publish queue.
	*
	* @return the user uuid of this publish queue
	*/
	@Override
	public String getUserUuid() {
		return _publishQueue.getUserUuid();
	}

	/**
	* Returns the uuid of this publish queue.
	*
	* @return the uuid of this publish queue
	*/
	@Override
	public String getUuid() {
		return _publishQueue.getUuid();
	}

	@Override
	public int hashCode() {
		return _publishQueue.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _publishQueue.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _publishQueue.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _publishQueue.isNew();
	}

	@Override
	public void persist() {
		_publishQueue.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_publishQueue.setCachedModel(cachedModel);
	}

	/**
	* Sets the create date of this publish queue.
	*
	* @param createDate the create date of this publish queue
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_publishQueue.setCreateDate(createDate);
	}

	/**
	* Sets the dossier ID of this publish queue.
	*
	* @param dossierId the dossier ID of this publish queue
	*/
	@Override
	public void setDossierId(long dossierId) {
		_publishQueue.setDossierId(dossierId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_publishQueue.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_publishQueue.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_publishQueue.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this publish queue.
	*
	* @param groupId the group ID of this publish queue
	*/
	@Override
	public void setGroupId(long groupId) {
		_publishQueue.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this publish queue.
	*
	* @param modifiedDate the modified date of this publish queue
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_publishQueue.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_publishQueue.setNew(n);
	}

	/**
	* Sets the primary key of this publish queue.
	*
	* @param primaryKey the primary key of this publish queue
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_publishQueue.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_publishQueue.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the publish queue ID of this publish queue.
	*
	* @param publishQueueId the publish queue ID of this publish queue
	*/
	@Override
	public void setPublishQueueId(long publishQueueId) {
		_publishQueue.setPublishQueueId(publishQueueId);
	}

	/**
	* Sets the retry of this publish queue.
	*
	* @param retry the retry of this publish queue
	*/
	@Override
	public void setRetry(int retry) {
		_publishQueue.setRetry(retry);
	}

	/**
	* Sets the server no of this publish queue.
	*
	* @param serverNo the server no of this publish queue
	*/
	@Override
	public void setServerNo(String serverNo) {
		_publishQueue.setServerNo(serverNo);
	}

	/**
	* Sets the status of this publish queue.
	*
	* @param status the status of this publish queue
	*/
	@Override
	public void setStatus(int status) {
		_publishQueue.setStatus(status);
	}

	/**
	* Sets the user ID of this publish queue.
	*
	* @param userId the user ID of this publish queue
	*/
	@Override
	public void setUserId(long userId) {
		_publishQueue.setUserId(userId);
	}

	/**
	* Sets the user uuid of this publish queue.
	*
	* @param userUuid the user uuid of this publish queue
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_publishQueue.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this publish queue.
	*
	* @param uuid the uuid of this publish queue
	*/
	@Override
	public void setUuid(String uuid) {
		_publishQueue.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<PublishQueue> toCacheModel() {
		return _publishQueue.toCacheModel();
	}

	@Override
	public PublishQueue toEscapedModel() {
		return new PublishQueueWrapper(_publishQueue.toEscapedModel());
	}

	@Override
	public String toString() {
		return _publishQueue.toString();
	}

	@Override
	public PublishQueue toUnescapedModel() {
		return new PublishQueueWrapper(_publishQueue.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _publishQueue.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PublishQueueWrapper)) {
			return false;
		}

		PublishQueueWrapper publishQueueWrapper = (PublishQueueWrapper)obj;

		if (Objects.equals(_publishQueue, publishQueueWrapper._publishQueue)) {
			return true;
		}

		return false;
	}

	@Override
	public PublishQueue getWrappedModel() {
		return _publishQueue;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _publishQueue.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _publishQueue.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_publishQueue.resetOriginalValues();
	}

	private final PublishQueue _publishQueue;
}