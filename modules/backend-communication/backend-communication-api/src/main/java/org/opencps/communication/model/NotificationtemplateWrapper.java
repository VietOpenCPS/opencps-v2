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
 * This class is a wrapper for {@link Notificationtemplate}.
 * </p>
 *
 * @author khoavd
 * @see Notificationtemplate
 * @generated
 */
@ProviderType
public class NotificationtemplateWrapper implements Notificationtemplate,
	ModelWrapper<Notificationtemplate> {
	public NotificationtemplateWrapper(
		Notificationtemplate notificationtemplate) {
		_notificationtemplate = notificationtemplate;
	}

	@Override
	public Class<?> getModelClass() {
		return Notificationtemplate.class;
	}

	@Override
	public String getModelClassName() {
		return Notificationtemplate.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("notificationTemplateId", getNotificationTemplateId());
		attributes.put(Field.GROUP_ID, getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("notificationType", getNotificationType());
		attributes.put("emailSubject", getEmailSubject());
		attributes.put("emailBody", getEmailBody());
		attributes.put("textMessage", getTextMessage());
		attributes.put("notifyMessage", getNotifyMessage());
		attributes.put("sendSMS", isSendSMS());
		attributes.put("sendEmail", isSendEmail());
		attributes.put("sendNotification", isSendNotification());
		attributes.put("expireDuration", getExpireDuration());
		attributes.put("userUrlPattern", getUserUrlPattern());
		attributes.put("guestUrlPattern", getGuestUrlPattern());
		attributes.put("interval", getInterval());
		attributes.put("grouping", isGrouping());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long notificationTemplateId = (Long)attributes.get(
				"notificationTemplateId");

		if (notificationTemplateId != null) {
			setNotificationTemplateId(notificationTemplateId);
		}

		Long groupId = (Long)attributes.get(Field.GROUP_ID);

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

		String emailSubject = (String)attributes.get("emailSubject");

		if (emailSubject != null) {
			setEmailSubject(emailSubject);
		}

		String emailBody = (String)attributes.get("emailBody");

		if (emailBody != null) {
			setEmailBody(emailBody);
		}

		String textMessage = (String)attributes.get("textMessage");

		if (textMessage != null) {
			setTextMessage(textMessage);
		}

		String notifyMessage = (String)attributes.get("notifyMessage");

		if (notifyMessage != null) {
			setNotifyMessage(notifyMessage);
		}

		Boolean sendSMS = (Boolean)attributes.get("sendSMS");

		if (sendSMS != null) {
			setSendSMS(sendSMS);
		}

		Boolean sendEmail = (Boolean)attributes.get("sendEmail");

		if (sendEmail != null) {
			setSendEmail(sendEmail);
		}

		Boolean sendNotification = (Boolean)attributes.get("sendNotification");

		if (sendNotification != null) {
			setSendNotification(sendNotification);
		}

		Integer expireDuration = (Integer)attributes.get("expireDuration");

		if (expireDuration != null) {
			setExpireDuration(expireDuration);
		}

		String userUrlPattern = (String)attributes.get("userUrlPattern");

		if (userUrlPattern != null) {
			setUserUrlPattern(userUrlPattern);
		}

		String guestUrlPattern = (String)attributes.get("guestUrlPattern");

		if (guestUrlPattern != null) {
			setGuestUrlPattern(guestUrlPattern);
		}

		String interval = (String)attributes.get("interval");

		if (interval != null) {
			setInterval(interval);
		}

		Boolean grouping = (Boolean)attributes.get("grouping");

		if (grouping != null) {
			setGrouping(grouping);
		}
	}

	@Override
	public Object clone() {
		return new NotificationtemplateWrapper((Notificationtemplate)_notificationtemplate.clone());
	}

	@Override
	public int compareTo(Notificationtemplate notificationtemplate) {
		return _notificationtemplate.compareTo(notificationtemplate);
	}

	/**
	* Returns the company ID of this notificationtemplate.
	*
	* @return the company ID of this notificationtemplate
	*/
	@Override
	public long getCompanyId() {
		return _notificationtemplate.getCompanyId();
	}

	/**
	* Returns the create date of this notificationtemplate.
	*
	* @return the create date of this notificationtemplate
	*/
	@Override
	public Date getCreateDate() {
		return _notificationtemplate.getCreateDate();
	}

	/**
	* Returns the email body of this notificationtemplate.
	*
	* @return the email body of this notificationtemplate
	*/
	@Override
	public String getEmailBody() {
		return _notificationtemplate.getEmailBody();
	}

	/**
	* Returns the email subject of this notificationtemplate.
	*
	* @return the email subject of this notificationtemplate
	*/
	@Override
	public String getEmailSubject() {
		return _notificationtemplate.getEmailSubject();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _notificationtemplate.getExpandoBridge();
	}

	/**
	* Returns the expire duration of this notificationtemplate.
	*
	* @return the expire duration of this notificationtemplate
	*/
	@Override
	public int getExpireDuration() {
		return _notificationtemplate.getExpireDuration();
	}

	/**
	* Returns the group ID of this notificationtemplate.
	*
	* @return the group ID of this notificationtemplate
	*/
	@Override
	public long getGroupId() {
		return _notificationtemplate.getGroupId();
	}

	/**
	* Returns the grouping of this notificationtemplate.
	*
	* @return the grouping of this notificationtemplate
	*/
	@Override
	public boolean getGrouping() {
		return _notificationtemplate.getGrouping();
	}

	/**
	* Returns the guest url pattern of this notificationtemplate.
	*
	* @return the guest url pattern of this notificationtemplate
	*/
	@Override
	public String getGuestUrlPattern() {
		return _notificationtemplate.getGuestUrlPattern();
	}

	/**
	* Returns the interval of this notificationtemplate.
	*
	* @return the interval of this notificationtemplate
	*/
	@Override
	public String getInterval() {
		return _notificationtemplate.getInterval();
	}

	/**
	* Returns the modified date of this notificationtemplate.
	*
	* @return the modified date of this notificationtemplate
	*/
	@Override
	public Date getModifiedDate() {
		return _notificationtemplate.getModifiedDate();
	}

	/**
	* Returns the notification template ID of this notificationtemplate.
	*
	* @return the notification template ID of this notificationtemplate
	*/
	@Override
	public long getNotificationTemplateId() {
		return _notificationtemplate.getNotificationTemplateId();
	}

	/**
	* Returns the notification type of this notificationtemplate.
	*
	* @return the notification type of this notificationtemplate
	*/
	@Override
	public String getNotificationType() {
		return _notificationtemplate.getNotificationType();
	}

	/**
	* Returns the notify message of this notificationtemplate.
	*
	* @return the notify message of this notificationtemplate
	*/
	@Override
	public String getNotifyMessage() {
		return _notificationtemplate.getNotifyMessage();
	}

	/**
	* Returns the primary key of this notificationtemplate.
	*
	* @return the primary key of this notificationtemplate
	*/
	@Override
	public long getPrimaryKey() {
		return _notificationtemplate.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _notificationtemplate.getPrimaryKeyObj();
	}

	/**
	* Returns the send email of this notificationtemplate.
	*
	* @return the send email of this notificationtemplate
	*/
	@Override
	public boolean getSendEmail() {
		return _notificationtemplate.getSendEmail();
	}

	/**
	* Returns the send notification of this notificationtemplate.
	*
	* @return the send notification of this notificationtemplate
	*/
	@Override
	public boolean getSendNotification() {
		return _notificationtemplate.getSendNotification();
	}

	/**
	* Returns the send sms of this notificationtemplate.
	*
	* @return the send sms of this notificationtemplate
	*/
	@Override
	public boolean getSendSMS() {
		return _notificationtemplate.getSendSMS();
	}

	/**
	* Returns the text message of this notificationtemplate.
	*
	* @return the text message of this notificationtemplate
	*/
	@Override
	public String getTextMessage() {
		return _notificationtemplate.getTextMessage();
	}

	/**
	* Returns the user ID of this notificationtemplate.
	*
	* @return the user ID of this notificationtemplate
	*/
	@Override
	public long getUserId() {
		return _notificationtemplate.getUserId();
	}

	/**
	* Returns the user name of this notificationtemplate.
	*
	* @return the user name of this notificationtemplate
	*/
	@Override
	public String getUserName() {
		return _notificationtemplate.getUserName();
	}

	/**
	* Returns the user url pattern of this notificationtemplate.
	*
	* @return the user url pattern of this notificationtemplate
	*/
	@Override
	public String getUserUrlPattern() {
		return _notificationtemplate.getUserUrlPattern();
	}

	/**
	* Returns the user uuid of this notificationtemplate.
	*
	* @return the user uuid of this notificationtemplate
	*/
	@Override
	public String getUserUuid() {
		return _notificationtemplate.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _notificationtemplate.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _notificationtemplate.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _notificationtemplate.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this notificationtemplate is grouping.
	*
	* @return <code>true</code> if this notificationtemplate is grouping; <code>false</code> otherwise
	*/
	@Override
	public boolean isGrouping() {
		return _notificationtemplate.isGrouping();
	}

	@Override
	public boolean isNew() {
		return _notificationtemplate.isNew();
	}

	/**
	* Returns <code>true</code> if this notificationtemplate is send email.
	*
	* @return <code>true</code> if this notificationtemplate is send email; <code>false</code> otherwise
	*/
	@Override
	public boolean isSendEmail() {
		return _notificationtemplate.isSendEmail();
	}

	/**
	* Returns <code>true</code> if this notificationtemplate is send notification.
	*
	* @return <code>true</code> if this notificationtemplate is send notification; <code>false</code> otherwise
	*/
	@Override
	public boolean isSendNotification() {
		return _notificationtemplate.isSendNotification();
	}

	/**
	* Returns <code>true</code> if this notificationtemplate is send sms.
	*
	* @return <code>true</code> if this notificationtemplate is send sms; <code>false</code> otherwise
	*/
	@Override
	public boolean isSendSMS() {
		return _notificationtemplate.isSendSMS();
	}

	@Override
	public void persist() {
		_notificationtemplate.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_notificationtemplate.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this notificationtemplate.
	*
	* @param companyId the company ID of this notificationtemplate
	*/
	@Override
	public void setCompanyId(long companyId) {
		_notificationtemplate.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this notificationtemplate.
	*
	* @param createDate the create date of this notificationtemplate
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_notificationtemplate.setCreateDate(createDate);
	}

	/**
	* Sets the email body of this notificationtemplate.
	*
	* @param emailBody the email body of this notificationtemplate
	*/
	@Override
	public void setEmailBody(String emailBody) {
		_notificationtemplate.setEmailBody(emailBody);
	}

	/**
	* Sets the email subject of this notificationtemplate.
	*
	* @param emailSubject the email subject of this notificationtemplate
	*/
	@Override
	public void setEmailSubject(String emailSubject) {
		_notificationtemplate.setEmailSubject(emailSubject);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_notificationtemplate.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_notificationtemplate.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_notificationtemplate.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the expire duration of this notificationtemplate.
	*
	* @param expireDuration the expire duration of this notificationtemplate
	*/
	@Override
	public void setExpireDuration(int expireDuration) {
		_notificationtemplate.setExpireDuration(expireDuration);
	}

	/**
	* Sets the group ID of this notificationtemplate.
	*
	* @param groupId the group ID of this notificationtemplate
	*/
	@Override
	public void setGroupId(long groupId) {
		_notificationtemplate.setGroupId(groupId);
	}

	/**
	* Sets whether this notificationtemplate is grouping.
	*
	* @param grouping the grouping of this notificationtemplate
	*/
	@Override
	public void setGrouping(boolean grouping) {
		_notificationtemplate.setGrouping(grouping);
	}

	/**
	* Sets the guest url pattern of this notificationtemplate.
	*
	* @param guestUrlPattern the guest url pattern of this notificationtemplate
	*/
	@Override
	public void setGuestUrlPattern(String guestUrlPattern) {
		_notificationtemplate.setGuestUrlPattern(guestUrlPattern);
	}

	/**
	* Sets the interval of this notificationtemplate.
	*
	* @param interval the interval of this notificationtemplate
	*/
	@Override
	public void setInterval(String interval) {
		_notificationtemplate.setInterval(interval);
	}

	/**
	* Sets the modified date of this notificationtemplate.
	*
	* @param modifiedDate the modified date of this notificationtemplate
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_notificationtemplate.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_notificationtemplate.setNew(n);
	}

	/**
	* Sets the notification template ID of this notificationtemplate.
	*
	* @param notificationTemplateId the notification template ID of this notificationtemplate
	*/
	@Override
	public void setNotificationTemplateId(long notificationTemplateId) {
		_notificationtemplate.setNotificationTemplateId(notificationTemplateId);
	}

	/**
	* Sets the notification type of this notificationtemplate.
	*
	* @param notificationType the notification type of this notificationtemplate
	*/
	@Override
	public void setNotificationType(String notificationType) {
		_notificationtemplate.setNotificationType(notificationType);
	}

	/**
	* Sets the notify message of this notificationtemplate.
	*
	* @param notifyMessage the notify message of this notificationtemplate
	*/
	@Override
	public void setNotifyMessage(String notifyMessage) {
		_notificationtemplate.setNotifyMessage(notifyMessage);
	}

	/**
	* Sets the primary key of this notificationtemplate.
	*
	* @param primaryKey the primary key of this notificationtemplate
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_notificationtemplate.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_notificationtemplate.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this notificationtemplate is send email.
	*
	* @param sendEmail the send email of this notificationtemplate
	*/
	@Override
	public void setSendEmail(boolean sendEmail) {
		_notificationtemplate.setSendEmail(sendEmail);
	}

	/**
	* Sets whether this notificationtemplate is send notification.
	*
	* @param sendNotification the send notification of this notificationtemplate
	*/
	@Override
	public void setSendNotification(boolean sendNotification) {
		_notificationtemplate.setSendNotification(sendNotification);
	}

	/**
	* Sets whether this notificationtemplate is send sms.
	*
	* @param sendSMS the send sms of this notificationtemplate
	*/
	@Override
	public void setSendSMS(boolean sendSMS) {
		_notificationtemplate.setSendSMS(sendSMS);
	}

	/**
	* Sets the text message of this notificationtemplate.
	*
	* @param textMessage the text message of this notificationtemplate
	*/
	@Override
	public void setTextMessage(String textMessage) {
		_notificationtemplate.setTextMessage(textMessage);
	}

	/**
	* Sets the user ID of this notificationtemplate.
	*
	* @param userId the user ID of this notificationtemplate
	*/
	@Override
	public void setUserId(long userId) {
		_notificationtemplate.setUserId(userId);
	}

	/**
	* Sets the user name of this notificationtemplate.
	*
	* @param userName the user name of this notificationtemplate
	*/
	@Override
	public void setUserName(String userName) {
		_notificationtemplate.setUserName(userName);
	}

	/**
	* Sets the user url pattern of this notificationtemplate.
	*
	* @param userUrlPattern the user url pattern of this notificationtemplate
	*/
	@Override
	public void setUserUrlPattern(String userUrlPattern) {
		_notificationtemplate.setUserUrlPattern(userUrlPattern);
	}

	/**
	* Sets the user uuid of this notificationtemplate.
	*
	* @param userUuid the user uuid of this notificationtemplate
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_notificationtemplate.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Notificationtemplate> toCacheModel() {
		return _notificationtemplate.toCacheModel();
	}

	@Override
	public Notificationtemplate toEscapedModel() {
		return new NotificationtemplateWrapper(_notificationtemplate.toEscapedModel());
	}

	@Override
	public String toString() {
		return _notificationtemplate.toString();
	}

	@Override
	public Notificationtemplate toUnescapedModel() {
		return new NotificationtemplateWrapper(_notificationtemplate.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _notificationtemplate.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof NotificationtemplateWrapper)) {
			return false;
		}

		NotificationtemplateWrapper notificationtemplateWrapper = (NotificationtemplateWrapper)obj;

		if (Objects.equals(_notificationtemplate,
					notificationtemplateWrapper._notificationtemplate)) {
			return true;
		}

		return false;
	}

	@Override
	public Notificationtemplate getWrappedModel() {
		return _notificationtemplate;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _notificationtemplate.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _notificationtemplate.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_notificationtemplate.resetOriginalValues();
	}

	private final Notificationtemplate _notificationtemplate;
}