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

package org.opencps.communication.model;

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
 * This class is a wrapper for {@link NotificationQueue}.
 * </p>
 *
 * @author khoavd
 * @see NotificationQueue
 * @generated
 */
@ProviderType
public class NotificationQueueWrapper implements NotificationQueue,
	ModelWrapper<NotificationQueue> {
	public NotificationQueueWrapper(NotificationQueue notificationQueue) {
		_notificationQueue = notificationQueue;
	}

	@Override
	public Class<?> getModelClass() {
		return NotificationQueue.class;
	}

	@Override
	public String getModelClassName() {
		return NotificationQueue.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("notificationQueueId", getNotificationQueueId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("notificationType", getNotificationType());
		attributes.put("className", getClassName());
		attributes.put("classPK", getClassPK());
		attributes.put("payload", getPayload());
		attributes.put("fromUsername", getFromUsername());
		attributes.put("toUsername", getToUsername());
		attributes.put("toUserId", getToUserId());
		attributes.put("toEmail", getToEmail());
		attributes.put("toTelNo", getToTelNo());
		attributes.put("publicationDate", getPublicationDate());
		attributes.put("expireDate", getExpireDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long notificationQueueId = (Long)attributes.get("notificationQueueId");

		if (notificationQueueId != null) {
			setNotificationQueueId(notificationQueueId);
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

		String notificationType = (String)attributes.get("notificationType");

		if (notificationType != null) {
			setNotificationType(notificationType);
		}

		String className = (String)attributes.get("className");

		if (className != null) {
			setClassName(className);
		}

		String classPK = (String)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		String payload = (String)attributes.get("payload");

		if (payload != null) {
			setPayload(payload);
		}

		String fromUsername = (String)attributes.get("fromUsername");

		if (fromUsername != null) {
			setFromUsername(fromUsername);
		}

		String toUsername = (String)attributes.get("toUsername");

		if (toUsername != null) {
			setToUsername(toUsername);
		}

		Long toUserId = (Long)attributes.get("toUserId");

		if (toUserId != null) {
			setToUserId(toUserId);
		}

		String toEmail = (String)attributes.get("toEmail");

		if (toEmail != null) {
			setToEmail(toEmail);
		}

		String toTelNo = (String)attributes.get("toTelNo");

		if (toTelNo != null) {
			setToTelNo(toTelNo);
		}

		Date publicationDate = (Date)attributes.get("publicationDate");

		if (publicationDate != null) {
			setPublicationDate(publicationDate);
		}

		Date expireDate = (Date)attributes.get("expireDate");

		if (expireDate != null) {
			setExpireDate(expireDate);
		}
	}

	@Override
	public Object clone() {
		return new NotificationQueueWrapper((NotificationQueue)_notificationQueue.clone());
	}

	@Override
	public int compareTo(NotificationQueue notificationQueue) {
		return _notificationQueue.compareTo(notificationQueue);
	}

	/**
	* Returns the class name of this notification queue.
	*
	* @return the class name of this notification queue
	*/
	@Override
	public String getClassName() {
		return _notificationQueue.getClassName();
	}

	/**
	* Returns the class pk of this notification queue.
	*
	* @return the class pk of this notification queue
	*/
	@Override
	public String getClassPK() {
		return _notificationQueue.getClassPK();
	}

	/**
	* Returns the company ID of this notification queue.
	*
	* @return the company ID of this notification queue
	*/
	@Override
	public long getCompanyId() {
		return _notificationQueue.getCompanyId();
	}

	/**
	* Returns the create date of this notification queue.
	*
	* @return the create date of this notification queue
	*/
	@Override
	public Date getCreateDate() {
		return _notificationQueue.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _notificationQueue.getExpandoBridge();
	}

	/**
	* Returns the expire date of this notification queue.
	*
	* @return the expire date of this notification queue
	*/
	@Override
	public Date getExpireDate() {
		return _notificationQueue.getExpireDate();
	}

	/**
	* Returns the from username of this notification queue.
	*
	* @return the from username of this notification queue
	*/
	@Override
	public String getFromUsername() {
		return _notificationQueue.getFromUsername();
	}

	/**
	* Returns the group ID of this notification queue.
	*
	* @return the group ID of this notification queue
	*/
	@Override
	public long getGroupId() {
		return _notificationQueue.getGroupId();
	}

	/**
	* Returns the modified date of this notification queue.
	*
	* @return the modified date of this notification queue
	*/
	@Override
	public Date getModifiedDate() {
		return _notificationQueue.getModifiedDate();
	}

	/**
	* Returns the notification queue ID of this notification queue.
	*
	* @return the notification queue ID of this notification queue
	*/
	@Override
	public long getNotificationQueueId() {
		return _notificationQueue.getNotificationQueueId();
	}

	/**
	* Returns the notification type of this notification queue.
	*
	* @return the notification type of this notification queue
	*/
	@Override
	public String getNotificationType() {
		return _notificationQueue.getNotificationType();
	}

	/**
	* Returns the payload of this notification queue.
	*
	* @return the payload of this notification queue
	*/
	@Override
	public String getPayload() {
		return _notificationQueue.getPayload();
	}

	/**
	* Returns the primary key of this notification queue.
	*
	* @return the primary key of this notification queue
	*/
	@Override
	public long getPrimaryKey() {
		return _notificationQueue.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _notificationQueue.getPrimaryKeyObj();
	}

	/**
	* Returns the publication date of this notification queue.
	*
	* @return the publication date of this notification queue
	*/
	@Override
	public Date getPublicationDate() {
		return _notificationQueue.getPublicationDate();
	}

	/**
	* Returns the to email of this notification queue.
	*
	* @return the to email of this notification queue
	*/
	@Override
	public String getToEmail() {
		return _notificationQueue.getToEmail();
	}

	/**
	* Returns the to tel no of this notification queue.
	*
	* @return the to tel no of this notification queue
	*/
	@Override
	public String getToTelNo() {
		return _notificationQueue.getToTelNo();
	}

	/**
	* Returns the to user ID of this notification queue.
	*
	* @return the to user ID of this notification queue
	*/
	@Override
	public long getToUserId() {
		return _notificationQueue.getToUserId();
	}

	/**
	* Returns the to username of this notification queue.
	*
	* @return the to username of this notification queue
	*/
	@Override
	public String getToUsername() {
		return _notificationQueue.getToUsername();
	}

	/**
	* Returns the to user uuid of this notification queue.
	*
	* @return the to user uuid of this notification queue
	*/
	@Override
	public String getToUserUuid() {
		return _notificationQueue.getToUserUuid();
	}

	/**
	* Returns the user ID of this notification queue.
	*
	* @return the user ID of this notification queue
	*/
	@Override
	public long getUserId() {
		return _notificationQueue.getUserId();
	}

	/**
	* Returns the user name of this notification queue.
	*
	* @return the user name of this notification queue
	*/
	@Override
	public String getUserName() {
		return _notificationQueue.getUserName();
	}

	/**
	* Returns the user uuid of this notification queue.
	*
	* @return the user uuid of this notification queue
	*/
	@Override
	public String getUserUuid() {
		return _notificationQueue.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _notificationQueue.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _notificationQueue.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _notificationQueue.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _notificationQueue.isNew();
	}

	@Override
	public void persist() {
		_notificationQueue.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_notificationQueue.setCachedModel(cachedModel);
	}

	/**
	* Sets the class name of this notification queue.
	*
	* @param className the class name of this notification queue
	*/
	@Override
	public void setClassName(String className) {
		_notificationQueue.setClassName(className);
	}

	/**
	* Sets the class pk of this notification queue.
	*
	* @param classPK the class pk of this notification queue
	*/
	@Override
	public void setClassPK(String classPK) {
		_notificationQueue.setClassPK(classPK);
	}

	/**
	* Sets the company ID of this notification queue.
	*
	* @param companyId the company ID of this notification queue
	*/
	@Override
	public void setCompanyId(long companyId) {
		_notificationQueue.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this notification queue.
	*
	* @param createDate the create date of this notification queue
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_notificationQueue.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_notificationQueue.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_notificationQueue.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_notificationQueue.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the expire date of this notification queue.
	*
	* @param expireDate the expire date of this notification queue
	*/
	@Override
	public void setExpireDate(Date expireDate) {
		_notificationQueue.setExpireDate(expireDate);
	}

	/**
	* Sets the from username of this notification queue.
	*
	* @param fromUsername the from username of this notification queue
	*/
	@Override
	public void setFromUsername(String fromUsername) {
		_notificationQueue.setFromUsername(fromUsername);
	}

	/**
	* Sets the group ID of this notification queue.
	*
	* @param groupId the group ID of this notification queue
	*/
	@Override
	public void setGroupId(long groupId) {
		_notificationQueue.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this notification queue.
	*
	* @param modifiedDate the modified date of this notification queue
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_notificationQueue.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_notificationQueue.setNew(n);
	}

	/**
	* Sets the notification queue ID of this notification queue.
	*
	* @param notificationQueueId the notification queue ID of this notification queue
	*/
	@Override
	public void setNotificationQueueId(long notificationQueueId) {
		_notificationQueue.setNotificationQueueId(notificationQueueId);
	}

	/**
	* Sets the notification type of this notification queue.
	*
	* @param notificationType the notification type of this notification queue
	*/
	@Override
	public void setNotificationType(String notificationType) {
		_notificationQueue.setNotificationType(notificationType);
	}

	/**
	* Sets the payload of this notification queue.
	*
	* @param payload the payload of this notification queue
	*/
	@Override
	public void setPayload(String payload) {
		_notificationQueue.setPayload(payload);
	}

	/**
	* Sets the primary key of this notification queue.
	*
	* @param primaryKey the primary key of this notification queue
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_notificationQueue.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_notificationQueue.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the publication date of this notification queue.
	*
	* @param publicationDate the publication date of this notification queue
	*/
	@Override
	public void setPublicationDate(Date publicationDate) {
		_notificationQueue.setPublicationDate(publicationDate);
	}

	/**
	* Sets the to email of this notification queue.
	*
	* @param toEmail the to email of this notification queue
	*/
	@Override
	public void setToEmail(String toEmail) {
		_notificationQueue.setToEmail(toEmail);
	}

	/**
	* Sets the to tel no of this notification queue.
	*
	* @param toTelNo the to tel no of this notification queue
	*/
	@Override
	public void setToTelNo(String toTelNo) {
		_notificationQueue.setToTelNo(toTelNo);
	}

	/**
	* Sets the to user ID of this notification queue.
	*
	* @param toUserId the to user ID of this notification queue
	*/
	@Override
	public void setToUserId(long toUserId) {
		_notificationQueue.setToUserId(toUserId);
	}

	/**
	* Sets the to username of this notification queue.
	*
	* @param toUsername the to username of this notification queue
	*/
	@Override
	public void setToUsername(String toUsername) {
		_notificationQueue.setToUsername(toUsername);
	}

	/**
	* Sets the to user uuid of this notification queue.
	*
	* @param toUserUuid the to user uuid of this notification queue
	*/
	@Override
	public void setToUserUuid(String toUserUuid) {
		_notificationQueue.setToUserUuid(toUserUuid);
	}

	/**
	* Sets the user ID of this notification queue.
	*
	* @param userId the user ID of this notification queue
	*/
	@Override
	public void setUserId(long userId) {
		_notificationQueue.setUserId(userId);
	}

	/**
	* Sets the user name of this notification queue.
	*
	* @param userName the user name of this notification queue
	*/
	@Override
	public void setUserName(String userName) {
		_notificationQueue.setUserName(userName);
	}

	/**
	* Sets the user uuid of this notification queue.
	*
	* @param userUuid the user uuid of this notification queue
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_notificationQueue.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<NotificationQueue> toCacheModel() {
		return _notificationQueue.toCacheModel();
	}

	@Override
	public NotificationQueue toEscapedModel() {
		return new NotificationQueueWrapper(_notificationQueue.toEscapedModel());
	}

	@Override
	public String toString() {
		return _notificationQueue.toString();
	}

	@Override
	public NotificationQueue toUnescapedModel() {
		return new NotificationQueueWrapper(_notificationQueue.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _notificationQueue.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof NotificationQueueWrapper)) {
			return false;
		}

		NotificationQueueWrapper notificationQueueWrapper = (NotificationQueueWrapper)obj;

		if (Objects.equals(_notificationQueue,
					notificationQueueWrapper._notificationQueue)) {
			return true;
		}

		return false;
	}

	@Override
	public NotificationQueue getWrappedModel() {
		return _notificationQueue;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _notificationQueue.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _notificationQueue.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_notificationQueue.resetOriginalValues();
	}

	private final NotificationQueue _notificationQueue;
}