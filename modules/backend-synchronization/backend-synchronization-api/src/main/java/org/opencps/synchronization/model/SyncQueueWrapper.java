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

package org.opencps.synchronization.model;

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
 * This class is a wrapper for {@link SyncQueue}.
 * </p>
 *
 * @author trungdk
 * @see SyncQueue
 * @generated
 */
@ProviderType
public class SyncQueueWrapper implements SyncQueue, ModelWrapper<SyncQueue> {
	public SyncQueueWrapper(SyncQueue syncQueue) {
		_syncQueue = syncQueue;
	}

	@Override
	public Class<?> getModelClass() {
		return SyncQueue.class;
	}

	@Override
	public String getModelClassName() {
		return SyncQueue.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("syncQueueId", getSyncQueueId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("serverNo", getServerNo());
		attributes.put("className", getClassName());
		attributes.put("jsonObject", getJsonObject());
		attributes.put("status", getStatus());
		attributes.put("retryCount", getRetryCount());
		attributes.put("priority", getPriority());
		attributes.put("method", getMethod());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long syncQueueId = (Long)attributes.get("syncQueueId");

		if (syncQueueId != null) {
			setSyncQueueId(syncQueueId);
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

		String serverNo = (String)attributes.get("serverNo");

		if (serverNo != null) {
			setServerNo(serverNo);
		}

		String className = (String)attributes.get("className");

		if (className != null) {
			setClassName(className);
		}

		String jsonObject = (String)attributes.get("jsonObject");

		if (jsonObject != null) {
			setJsonObject(jsonObject);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Integer retryCount = (Integer)attributes.get("retryCount");

		if (retryCount != null) {
			setRetryCount(retryCount);
		}

		Integer priority = (Integer)attributes.get("priority");

		if (priority != null) {
			setPriority(priority);
		}

		String method = (String)attributes.get("method");

		if (method != null) {
			setMethod(method);
		}
	}

	@Override
	public Object clone() {
		return new SyncQueueWrapper((SyncQueue)_syncQueue.clone());
	}

	@Override
	public int compareTo(SyncQueue syncQueue) {
		return _syncQueue.compareTo(syncQueue);
	}

	/**
	* Returns the class name of this sync queue.
	*
	* @return the class name of this sync queue
	*/
	@Override
	public String getClassName() {
		return _syncQueue.getClassName();
	}

	/**
	* Returns the company ID of this sync queue.
	*
	* @return the company ID of this sync queue
	*/
	@Override
	public long getCompanyId() {
		return _syncQueue.getCompanyId();
	}

	/**
	* Returns the create date of this sync queue.
	*
	* @return the create date of this sync queue
	*/
	@Override
	public Date getCreateDate() {
		return _syncQueue.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _syncQueue.getExpandoBridge();
	}

	/**
	* Returns the group ID of this sync queue.
	*
	* @return the group ID of this sync queue
	*/
	@Override
	public long getGroupId() {
		return _syncQueue.getGroupId();
	}

	/**
	* Returns the json object of this sync queue.
	*
	* @return the json object of this sync queue
	*/
	@Override
	public String getJsonObject() {
		return _syncQueue.getJsonObject();
	}

	/**
	* Returns the method of this sync queue.
	*
	* @return the method of this sync queue
	*/
	@Override
	public String getMethod() {
		return _syncQueue.getMethod();
	}

	/**
	* Returns the modified date of this sync queue.
	*
	* @return the modified date of this sync queue
	*/
	@Override
	public Date getModifiedDate() {
		return _syncQueue.getModifiedDate();
	}

	/**
	* Returns the primary key of this sync queue.
	*
	* @return the primary key of this sync queue
	*/
	@Override
	public long getPrimaryKey() {
		return _syncQueue.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _syncQueue.getPrimaryKeyObj();
	}

	/**
	* Returns the priority of this sync queue.
	*
	* @return the priority of this sync queue
	*/
	@Override
	public int getPriority() {
		return _syncQueue.getPriority();
	}

	/**
	* Returns the retry count of this sync queue.
	*
	* @return the retry count of this sync queue
	*/
	@Override
	public int getRetryCount() {
		return _syncQueue.getRetryCount();
	}

	/**
	* Returns the server no of this sync queue.
	*
	* @return the server no of this sync queue
	*/
	@Override
	public String getServerNo() {
		return _syncQueue.getServerNo();
	}

	/**
	* Returns the status of this sync queue.
	*
	* @return the status of this sync queue
	*/
	@Override
	public int getStatus() {
		return _syncQueue.getStatus();
	}

	/**
	* Returns the sync queue ID of this sync queue.
	*
	* @return the sync queue ID of this sync queue
	*/
	@Override
	public long getSyncQueueId() {
		return _syncQueue.getSyncQueueId();
	}

	/**
	* Returns the user ID of this sync queue.
	*
	* @return the user ID of this sync queue
	*/
	@Override
	public long getUserId() {
		return _syncQueue.getUserId();
	}

	/**
	* Returns the user name of this sync queue.
	*
	* @return the user name of this sync queue
	*/
	@Override
	public String getUserName() {
		return _syncQueue.getUserName();
	}

	/**
	* Returns the user uuid of this sync queue.
	*
	* @return the user uuid of this sync queue
	*/
	@Override
	public String getUserUuid() {
		return _syncQueue.getUserUuid();
	}

	/**
	* Returns the uuid of this sync queue.
	*
	* @return the uuid of this sync queue
	*/
	@Override
	public String getUuid() {
		return _syncQueue.getUuid();
	}

	@Override
	public int hashCode() {
		return _syncQueue.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _syncQueue.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _syncQueue.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _syncQueue.isNew();
	}

	@Override
	public void persist() {
		_syncQueue.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_syncQueue.setCachedModel(cachedModel);
	}

	/**
	* Sets the class name of this sync queue.
	*
	* @param className the class name of this sync queue
	*/
	@Override
	public void setClassName(String className) {
		_syncQueue.setClassName(className);
	}

	/**
	* Sets the company ID of this sync queue.
	*
	* @param companyId the company ID of this sync queue
	*/
	@Override
	public void setCompanyId(long companyId) {
		_syncQueue.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this sync queue.
	*
	* @param createDate the create date of this sync queue
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_syncQueue.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_syncQueue.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_syncQueue.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_syncQueue.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this sync queue.
	*
	* @param groupId the group ID of this sync queue
	*/
	@Override
	public void setGroupId(long groupId) {
		_syncQueue.setGroupId(groupId);
	}

	/**
	* Sets the json object of this sync queue.
	*
	* @param jsonObject the json object of this sync queue
	*/
	@Override
	public void setJsonObject(String jsonObject) {
		_syncQueue.setJsonObject(jsonObject);
	}

	/**
	* Sets the method of this sync queue.
	*
	* @param method the method of this sync queue
	*/
	@Override
	public void setMethod(String method) {
		_syncQueue.setMethod(method);
	}

	/**
	* Sets the modified date of this sync queue.
	*
	* @param modifiedDate the modified date of this sync queue
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_syncQueue.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_syncQueue.setNew(n);
	}

	/**
	* Sets the primary key of this sync queue.
	*
	* @param primaryKey the primary key of this sync queue
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_syncQueue.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_syncQueue.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the priority of this sync queue.
	*
	* @param priority the priority of this sync queue
	*/
	@Override
	public void setPriority(int priority) {
		_syncQueue.setPriority(priority);
	}

	/**
	* Sets the retry count of this sync queue.
	*
	* @param retryCount the retry count of this sync queue
	*/
	@Override
	public void setRetryCount(int retryCount) {
		_syncQueue.setRetryCount(retryCount);
	}

	/**
	* Sets the server no of this sync queue.
	*
	* @param serverNo the server no of this sync queue
	*/
	@Override
	public void setServerNo(String serverNo) {
		_syncQueue.setServerNo(serverNo);
	}

	/**
	* Sets the status of this sync queue.
	*
	* @param status the status of this sync queue
	*/
	@Override
	public void setStatus(int status) {
		_syncQueue.setStatus(status);
	}

	/**
	* Sets the sync queue ID of this sync queue.
	*
	* @param syncQueueId the sync queue ID of this sync queue
	*/
	@Override
	public void setSyncQueueId(long syncQueueId) {
		_syncQueue.setSyncQueueId(syncQueueId);
	}

	/**
	* Sets the user ID of this sync queue.
	*
	* @param userId the user ID of this sync queue
	*/
	@Override
	public void setUserId(long userId) {
		_syncQueue.setUserId(userId);
	}

	/**
	* Sets the user name of this sync queue.
	*
	* @param userName the user name of this sync queue
	*/
	@Override
	public void setUserName(String userName) {
		_syncQueue.setUserName(userName);
	}

	/**
	* Sets the user uuid of this sync queue.
	*
	* @param userUuid the user uuid of this sync queue
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_syncQueue.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this sync queue.
	*
	* @param uuid the uuid of this sync queue
	*/
	@Override
	public void setUuid(String uuid) {
		_syncQueue.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<SyncQueue> toCacheModel() {
		return _syncQueue.toCacheModel();
	}

	@Override
	public SyncQueue toEscapedModel() {
		return new SyncQueueWrapper(_syncQueue.toEscapedModel());
	}

	@Override
	public String toString() {
		return _syncQueue.toString();
	}

	@Override
	public SyncQueue toUnescapedModel() {
		return new SyncQueueWrapper(_syncQueue.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _syncQueue.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SyncQueueWrapper)) {
			return false;
		}

		SyncQueueWrapper syncQueueWrapper = (SyncQueueWrapper)obj;

		if (Objects.equals(_syncQueue, syncQueueWrapper._syncQueue)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _syncQueue.getStagedModelType();
	}

	@Override
	public SyncQueue getWrappedModel() {
		return _syncQueue;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _syncQueue.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _syncQueue.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_syncQueue.resetOriginalValues();
	}

	private final SyncQueue _syncQueue;
}