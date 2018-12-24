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

package org.opencps.communication.service.impl;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;

import org.opencps.communication.exception.NoSuchNotificationQueueException;
import org.opencps.communication.model.NotificationQueue;
import org.opencps.communication.service.base.NotificationQueueLocalServiceBaseImpl;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the notification queue local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.communication.service.NotificationQueueLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Binhth
 * @see NotificationQueueLocalServiceBaseImpl
 * @see org.opencps.communication.service.NotificationQueueLocalServiceUtil
 */
@ProviderType
public class NotificationQueueLocalServiceImpl extends NotificationQueueLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.communication.service. NotificationQueueLocalServiceUtil} to
	 * access the notification queue local service.
	 */
	public NotificationQueue addNotificationQueue(long userId, long groupId, String notificationType, String className,
			String classPK, String payload, String fromUsername, String toUsername, long toUserId, String toEmail,
			String toTelNo, Date publicationDate, Date expireDate, ServiceContext serviceContext)
			throws NoSuchUserException {

		Date now = new Date();

		User user = userPersistence.findByPrimaryKey(userId);

		long notificationQueueId = counterLocalService.increment(NotificationQueue.class.getName());

		NotificationQueue notificationQueue = notificationQueuePersistence.create(notificationQueueId);

		// Group instance
		notificationQueue.setGroupId(groupId);

		// Audit fields
		notificationQueue.setCompanyId(user.getCompanyId());
		notificationQueue.setUserId(user.getUserId());
		notificationQueue.setUserName(user.getFullName());
		notificationQueue.setCreateDate(serviceContext.getCreateDate(now));
		notificationQueue.setModifiedDate(serviceContext.getCreateDate(now));

		notificationQueue.setNotificationType(notificationType);
		notificationQueue.setClassName(className);
		notificationQueue.setClassPK(classPK);
		notificationQueue.setPayload(payload);
		notificationQueue.setFromUsername(fromUsername);
		notificationQueue.setToUsername(toUsername);

		notificationQueue.setToUserId(toUserId);
		notificationQueue.setToEmail(toEmail);
		notificationQueue.setToTelNo(toTelNo);
		notificationQueue.setPublicationDate(publicationDate);
		notificationQueue.setExpireDate(expireDate);

		notificationQueue.setExpandoBridgeAttributes(serviceContext);

		return notificationQueuePersistence.update(notificationQueue);
	}

	/**
	 * @param dictCollectionId
	 * @param serviceContext
	 * @return
	 * @throws NoSuchNotificationQueueException
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public NotificationQueue deleteNotificationQueue(long notificationQueueId, ServiceContext serviceContext)
			throws NoSuchNotificationQueueException {

		NotificationQueue NotificationQueue = notificationQueuePersistence.remove(notificationQueueId);

		return NotificationQueue;

	}

	@Indexable(type = IndexableType.DELETE)
	public void deleteExpiredNotificationQueue(Date date) {

		notificationQueuePersistence.removeByF_GreaterThan_ExpireDate(date);
	}

	@Override
	public List<NotificationQueue> findByF_notificationType_LessThanExpireDate(String notificationType, Date date) {

		return notificationQueuePersistence.findByF_notificationType_LessThanExpireDate(notificationType, date);
	}

	public void deleteByGroup(long groupId) {
		notificationQueuePersistence.removeByG(groupId);
	}

	// super_admin Generators
	@Indexable(type = IndexableType.DELETE)
	public NotificationQueue adminProcessDelete(Long id) {

		NotificationQueue object = notificationQueuePersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		} else {
			notificationQueuePersistence.remove(object);
		}

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
	public NotificationQueue adminProcessData(JSONObject objectData) {

		NotificationQueue object = null;

		if (objectData.getLong("notificationQueueId") > 0) {

			object = notificationQueuePersistence.fetchByPrimaryKey(objectData.getLong("notificationQueueId"));

			object.setModifiedDate(new Date());

		} else {

			long id = CounterLocalServiceUtil.increment(NotificationQueue.class.getName());

			object = notificationQueuePersistence.create(id);

			object.setGroupId(objectData.getLong("groupId"));
			object.setCompanyId(objectData.getLong("companyId"));
			object.setCreateDate(new Date());

		}

		object.setUserId(objectData.getLong("userId"));

		object.setNotificationType(objectData.getString("notificationType"));
		object.setClassName(objectData.getString("className"));
		object.setClassPK(objectData.getString("classPK"));
		object.setPayload(objectData.getString("payload"));
		object.setFromUsername(objectData.getString("fromUsername"));
		object.setToUsername(objectData.getString("toUsername"));
		object.setToUserId(objectData.getLong("toUserId"));
		object.setToEmail(objectData.getString("toEmail"));
		object.setToTelNo(objectData.getString("toTelNo"));
		object.setPublicationDate(new Date(objectData.getLong("publicationDate")));
		object.setExpireDate(new Date(objectData.getLong("expireDate")));

		notificationQueuePersistence.update(object);

		return object;
	}

	public List<NotificationQueue> findByF_LessThan_ExpireDate(Date date) {
		return notificationQueuePersistence.findByF_LessThan_ExpireDate(date);
	}

}