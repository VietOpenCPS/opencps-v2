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

package org.opencps.communication.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.communication.exception.NoSuchNotificationtemplateException;
import org.opencps.communication.model.Notificationtemplate;

/**
 * The persistence interface for the notificationtemplate service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavd
 * @see org.opencps.communication.service.persistence.impl.NotificationtemplatePersistenceImpl
 * @see NotificationtemplateUtil
 * @generated
 */
@ProviderType
public interface NotificationtemplatePersistence extends BasePersistence<Notificationtemplate> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link NotificationtemplateUtil} to access the notificationtemplate persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the notificationtemplate where groupId = &#63; and notificationType = &#63; or throws a {@link NoSuchNotificationtemplateException} if it could not be found.
	*
	* @param groupId the group ID
	* @param notificationType the notification type
	* @return the matching notificationtemplate
	* @throws NoSuchNotificationtemplateException if a matching notificationtemplate could not be found
	*/
	public Notificationtemplate findByF_NotificationtemplateByType(
		long groupId, String notificationType)
		throws NoSuchNotificationtemplateException;

	/**
	* Returns the notificationtemplate where groupId = &#63; and notificationType = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param notificationType the notification type
	* @return the matching notificationtemplate, or <code>null</code> if a matching notificationtemplate could not be found
	*/
	public Notificationtemplate fetchByF_NotificationtemplateByType(
		long groupId, String notificationType);

	/**
	* Returns the notificationtemplate where groupId = &#63; and notificationType = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param notificationType the notification type
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching notificationtemplate, or <code>null</code> if a matching notificationtemplate could not be found
	*/
	public Notificationtemplate fetchByF_NotificationtemplateByType(
		long groupId, String notificationType, boolean retrieveFromCache);

	/**
	* Removes the notificationtemplate where groupId = &#63; and notificationType = &#63; from the database.
	*
	* @param groupId the group ID
	* @param notificationType the notification type
	* @return the notificationtemplate that was removed
	*/
	public Notificationtemplate removeByF_NotificationtemplateByType(
		long groupId, String notificationType)
		throws NoSuchNotificationtemplateException;

	/**
	* Returns the number of notificationtemplates where groupId = &#63; and notificationType = &#63;.
	*
	* @param groupId the group ID
	* @param notificationType the notification type
	* @return the number of matching notificationtemplates
	*/
	public int countByF_NotificationtemplateByType(long groupId,
		String notificationType);

	/**
	* Returns all the notificationtemplates where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching notificationtemplates
	*/
	public java.util.List<Notificationtemplate> findByF_NotificationtemplateByGroup(
		long groupId);

	/**
	* Returns a range of all the notificationtemplates where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationtemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of notificationtemplates
	* @param end the upper bound of the range of notificationtemplates (not inclusive)
	* @return the range of matching notificationtemplates
	*/
	public java.util.List<Notificationtemplate> findByF_NotificationtemplateByGroup(
		long groupId, int start, int end);

	/**
	* Returns an ordered range of all the notificationtemplates where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationtemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of notificationtemplates
	* @param end the upper bound of the range of notificationtemplates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching notificationtemplates
	*/
	public java.util.List<Notificationtemplate> findByF_NotificationtemplateByGroup(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Notificationtemplate> orderByComparator);

	/**
	* Returns an ordered range of all the notificationtemplates where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationtemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of notificationtemplates
	* @param end the upper bound of the range of notificationtemplates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching notificationtemplates
	*/
	public java.util.List<Notificationtemplate> findByF_NotificationtemplateByGroup(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Notificationtemplate> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first notificationtemplate in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching notificationtemplate
	* @throws NoSuchNotificationtemplateException if a matching notificationtemplate could not be found
	*/
	public Notificationtemplate findByF_NotificationtemplateByGroup_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Notificationtemplate> orderByComparator)
		throws NoSuchNotificationtemplateException;

	/**
	* Returns the first notificationtemplate in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching notificationtemplate, or <code>null</code> if a matching notificationtemplate could not be found
	*/
	public Notificationtemplate fetchByF_NotificationtemplateByGroup_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Notificationtemplate> orderByComparator);

	/**
	* Returns the last notificationtemplate in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching notificationtemplate
	* @throws NoSuchNotificationtemplateException if a matching notificationtemplate could not be found
	*/
	public Notificationtemplate findByF_NotificationtemplateByGroup_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Notificationtemplate> orderByComparator)
		throws NoSuchNotificationtemplateException;

	/**
	* Returns the last notificationtemplate in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching notificationtemplate, or <code>null</code> if a matching notificationtemplate could not be found
	*/
	public Notificationtemplate fetchByF_NotificationtemplateByGroup_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Notificationtemplate> orderByComparator);

	/**
	* Returns the notificationtemplates before and after the current notificationtemplate in the ordered set where groupId = &#63;.
	*
	* @param notificationTemplateId the primary key of the current notificationtemplate
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next notificationtemplate
	* @throws NoSuchNotificationtemplateException if a notificationtemplate with the primary key could not be found
	*/
	public Notificationtemplate[] findByF_NotificationtemplateByGroup_PrevAndNext(
		long notificationTemplateId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Notificationtemplate> orderByComparator)
		throws NoSuchNotificationtemplateException;

	/**
	* Removes all the notificationtemplates where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByF_NotificationtemplateByGroup(long groupId);

	/**
	* Returns the number of notificationtemplates where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching notificationtemplates
	*/
	public int countByF_NotificationtemplateByGroup(long groupId);

	/**
	* Returns all the notificationtemplates where emailSubject = &#63;.
	*
	* @param emailSubject the email subject
	* @return the matching notificationtemplates
	*/
	public java.util.List<Notificationtemplate> findByF_NotificationtemplateEmailSubject(
		String emailSubject);

	/**
	* Returns a range of all the notificationtemplates where emailSubject = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationtemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param emailSubject the email subject
	* @param start the lower bound of the range of notificationtemplates
	* @param end the upper bound of the range of notificationtemplates (not inclusive)
	* @return the range of matching notificationtemplates
	*/
	public java.util.List<Notificationtemplate> findByF_NotificationtemplateEmailSubject(
		String emailSubject, int start, int end);

	/**
	* Returns an ordered range of all the notificationtemplates where emailSubject = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationtemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param emailSubject the email subject
	* @param start the lower bound of the range of notificationtemplates
	* @param end the upper bound of the range of notificationtemplates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching notificationtemplates
	*/
	public java.util.List<Notificationtemplate> findByF_NotificationtemplateEmailSubject(
		String emailSubject, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Notificationtemplate> orderByComparator);

	/**
	* Returns an ordered range of all the notificationtemplates where emailSubject = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationtemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param emailSubject the email subject
	* @param start the lower bound of the range of notificationtemplates
	* @param end the upper bound of the range of notificationtemplates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching notificationtemplates
	*/
	public java.util.List<Notificationtemplate> findByF_NotificationtemplateEmailSubject(
		String emailSubject, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Notificationtemplate> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first notificationtemplate in the ordered set where emailSubject = &#63;.
	*
	* @param emailSubject the email subject
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching notificationtemplate
	* @throws NoSuchNotificationtemplateException if a matching notificationtemplate could not be found
	*/
	public Notificationtemplate findByF_NotificationtemplateEmailSubject_First(
		String emailSubject,
		com.liferay.portal.kernel.util.OrderByComparator<Notificationtemplate> orderByComparator)
		throws NoSuchNotificationtemplateException;

	/**
	* Returns the first notificationtemplate in the ordered set where emailSubject = &#63;.
	*
	* @param emailSubject the email subject
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching notificationtemplate, or <code>null</code> if a matching notificationtemplate could not be found
	*/
	public Notificationtemplate fetchByF_NotificationtemplateEmailSubject_First(
		String emailSubject,
		com.liferay.portal.kernel.util.OrderByComparator<Notificationtemplate> orderByComparator);

	/**
	* Returns the last notificationtemplate in the ordered set where emailSubject = &#63;.
	*
	* @param emailSubject the email subject
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching notificationtemplate
	* @throws NoSuchNotificationtemplateException if a matching notificationtemplate could not be found
	*/
	public Notificationtemplate findByF_NotificationtemplateEmailSubject_Last(
		String emailSubject,
		com.liferay.portal.kernel.util.OrderByComparator<Notificationtemplate> orderByComparator)
		throws NoSuchNotificationtemplateException;

	/**
	* Returns the last notificationtemplate in the ordered set where emailSubject = &#63;.
	*
	* @param emailSubject the email subject
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching notificationtemplate, or <code>null</code> if a matching notificationtemplate could not be found
	*/
	public Notificationtemplate fetchByF_NotificationtemplateEmailSubject_Last(
		String emailSubject,
		com.liferay.portal.kernel.util.OrderByComparator<Notificationtemplate> orderByComparator);

	/**
	* Returns the notificationtemplates before and after the current notificationtemplate in the ordered set where emailSubject = &#63;.
	*
	* @param notificationTemplateId the primary key of the current notificationtemplate
	* @param emailSubject the email subject
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next notificationtemplate
	* @throws NoSuchNotificationtemplateException if a notificationtemplate with the primary key could not be found
	*/
	public Notificationtemplate[] findByF_NotificationtemplateEmailSubject_PrevAndNext(
		long notificationTemplateId, String emailSubject,
		com.liferay.portal.kernel.util.OrderByComparator<Notificationtemplate> orderByComparator)
		throws NoSuchNotificationtemplateException;

	/**
	* Removes all the notificationtemplates where emailSubject = &#63; from the database.
	*
	* @param emailSubject the email subject
	*/
	public void removeByF_NotificationtemplateEmailSubject(String emailSubject);

	/**
	* Returns the number of notificationtemplates where emailSubject = &#63;.
	*
	* @param emailSubject the email subject
	* @return the number of matching notificationtemplates
	*/
	public int countByF_NotificationtemplateEmailSubject(String emailSubject);

	/**
	* Returns all the notificationtemplates where expireDuration = &#63;.
	*
	* @param expireDuration the expire duration
	* @return the matching notificationtemplates
	*/
	public java.util.List<Notificationtemplate> findByF_expireDuration(
		int expireDuration);

	/**
	* Returns a range of all the notificationtemplates where expireDuration = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationtemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param expireDuration the expire duration
	* @param start the lower bound of the range of notificationtemplates
	* @param end the upper bound of the range of notificationtemplates (not inclusive)
	* @return the range of matching notificationtemplates
	*/
	public java.util.List<Notificationtemplate> findByF_expireDuration(
		int expireDuration, int start, int end);

	/**
	* Returns an ordered range of all the notificationtemplates where expireDuration = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationtemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param expireDuration the expire duration
	* @param start the lower bound of the range of notificationtemplates
	* @param end the upper bound of the range of notificationtemplates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching notificationtemplates
	*/
	public java.util.List<Notificationtemplate> findByF_expireDuration(
		int expireDuration, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Notificationtemplate> orderByComparator);

	/**
	* Returns an ordered range of all the notificationtemplates where expireDuration = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationtemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param expireDuration the expire duration
	* @param start the lower bound of the range of notificationtemplates
	* @param end the upper bound of the range of notificationtemplates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching notificationtemplates
	*/
	public java.util.List<Notificationtemplate> findByF_expireDuration(
		int expireDuration, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Notificationtemplate> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first notificationtemplate in the ordered set where expireDuration = &#63;.
	*
	* @param expireDuration the expire duration
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching notificationtemplate
	* @throws NoSuchNotificationtemplateException if a matching notificationtemplate could not be found
	*/
	public Notificationtemplate findByF_expireDuration_First(
		int expireDuration,
		com.liferay.portal.kernel.util.OrderByComparator<Notificationtemplate> orderByComparator)
		throws NoSuchNotificationtemplateException;

	/**
	* Returns the first notificationtemplate in the ordered set where expireDuration = &#63;.
	*
	* @param expireDuration the expire duration
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching notificationtemplate, or <code>null</code> if a matching notificationtemplate could not be found
	*/
	public Notificationtemplate fetchByF_expireDuration_First(
		int expireDuration,
		com.liferay.portal.kernel.util.OrderByComparator<Notificationtemplate> orderByComparator);

	/**
	* Returns the last notificationtemplate in the ordered set where expireDuration = &#63;.
	*
	* @param expireDuration the expire duration
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching notificationtemplate
	* @throws NoSuchNotificationtemplateException if a matching notificationtemplate could not be found
	*/
	public Notificationtemplate findByF_expireDuration_Last(
		int expireDuration,
		com.liferay.portal.kernel.util.OrderByComparator<Notificationtemplate> orderByComparator)
		throws NoSuchNotificationtemplateException;

	/**
	* Returns the last notificationtemplate in the ordered set where expireDuration = &#63;.
	*
	* @param expireDuration the expire duration
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching notificationtemplate, or <code>null</code> if a matching notificationtemplate could not be found
	*/
	public Notificationtemplate fetchByF_expireDuration_Last(
		int expireDuration,
		com.liferay.portal.kernel.util.OrderByComparator<Notificationtemplate> orderByComparator);

	/**
	* Returns the notificationtemplates before and after the current notificationtemplate in the ordered set where expireDuration = &#63;.
	*
	* @param notificationTemplateId the primary key of the current notificationtemplate
	* @param expireDuration the expire duration
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next notificationtemplate
	* @throws NoSuchNotificationtemplateException if a notificationtemplate with the primary key could not be found
	*/
	public Notificationtemplate[] findByF_expireDuration_PrevAndNext(
		long notificationTemplateId, int expireDuration,
		com.liferay.portal.kernel.util.OrderByComparator<Notificationtemplate> orderByComparator)
		throws NoSuchNotificationtemplateException;

	/**
	* Removes all the notificationtemplates where expireDuration = &#63; from the database.
	*
	* @param expireDuration the expire duration
	*/
	public void removeByF_expireDuration(int expireDuration);

	/**
	* Returns the number of notificationtemplates where expireDuration = &#63;.
	*
	* @param expireDuration the expire duration
	* @return the number of matching notificationtemplates
	*/
	public int countByF_expireDuration(int expireDuration);

	/**
	* Returns all the notificationtemplates where interval = &#63;.
	*
	* @param interval the interval
	* @return the matching notificationtemplates
	*/
	public java.util.List<Notificationtemplate> findByF_interval(
		String interval);

	/**
	* Returns a range of all the notificationtemplates where interval = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationtemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param interval the interval
	* @param start the lower bound of the range of notificationtemplates
	* @param end the upper bound of the range of notificationtemplates (not inclusive)
	* @return the range of matching notificationtemplates
	*/
	public java.util.List<Notificationtemplate> findByF_interval(
		String interval, int start, int end);

	/**
	* Returns an ordered range of all the notificationtemplates where interval = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationtemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param interval the interval
	* @param start the lower bound of the range of notificationtemplates
	* @param end the upper bound of the range of notificationtemplates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching notificationtemplates
	*/
	public java.util.List<Notificationtemplate> findByF_interval(
		String interval, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Notificationtemplate> orderByComparator);

	/**
	* Returns an ordered range of all the notificationtemplates where interval = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationtemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param interval the interval
	* @param start the lower bound of the range of notificationtemplates
	* @param end the upper bound of the range of notificationtemplates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching notificationtemplates
	*/
	public java.util.List<Notificationtemplate> findByF_interval(
		String interval, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Notificationtemplate> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first notificationtemplate in the ordered set where interval = &#63;.
	*
	* @param interval the interval
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching notificationtemplate
	* @throws NoSuchNotificationtemplateException if a matching notificationtemplate could not be found
	*/
	public Notificationtemplate findByF_interval_First(String interval,
		com.liferay.portal.kernel.util.OrderByComparator<Notificationtemplate> orderByComparator)
		throws NoSuchNotificationtemplateException;

	/**
	* Returns the first notificationtemplate in the ordered set where interval = &#63;.
	*
	* @param interval the interval
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching notificationtemplate, or <code>null</code> if a matching notificationtemplate could not be found
	*/
	public Notificationtemplate fetchByF_interval_First(String interval,
		com.liferay.portal.kernel.util.OrderByComparator<Notificationtemplate> orderByComparator);

	/**
	* Returns the last notificationtemplate in the ordered set where interval = &#63;.
	*
	* @param interval the interval
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching notificationtemplate
	* @throws NoSuchNotificationtemplateException if a matching notificationtemplate could not be found
	*/
	public Notificationtemplate findByF_interval_Last(String interval,
		com.liferay.portal.kernel.util.OrderByComparator<Notificationtemplate> orderByComparator)
		throws NoSuchNotificationtemplateException;

	/**
	* Returns the last notificationtemplate in the ordered set where interval = &#63;.
	*
	* @param interval the interval
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching notificationtemplate, or <code>null</code> if a matching notificationtemplate could not be found
	*/
	public Notificationtemplate fetchByF_interval_Last(String interval,
		com.liferay.portal.kernel.util.OrderByComparator<Notificationtemplate> orderByComparator);

	/**
	* Returns the notificationtemplates before and after the current notificationtemplate in the ordered set where interval = &#63;.
	*
	* @param notificationTemplateId the primary key of the current notificationtemplate
	* @param interval the interval
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next notificationtemplate
	* @throws NoSuchNotificationtemplateException if a notificationtemplate with the primary key could not be found
	*/
	public Notificationtemplate[] findByF_interval_PrevAndNext(
		long notificationTemplateId, String interval,
		com.liferay.portal.kernel.util.OrderByComparator<Notificationtemplate> orderByComparator)
		throws NoSuchNotificationtemplateException;

	/**
	* Removes all the notificationtemplates where interval = &#63; from the database.
	*
	* @param interval the interval
	*/
	public void removeByF_interval(String interval);

	/**
	* Returns the number of notificationtemplates where interval = &#63;.
	*
	* @param interval the interval
	* @return the number of matching notificationtemplates
	*/
	public int countByF_interval(String interval);

	/**
	* Returns the notificationtemplate where groupId = &#63; and notificationType = &#63; and interval = &#63; or throws a {@link NoSuchNotificationtemplateException} if it could not be found.
	*
	* @param groupId the group ID
	* @param notificationType the notification type
	* @param interval the interval
	* @return the matching notificationtemplate
	* @throws NoSuchNotificationtemplateException if a matching notificationtemplate could not be found
	*/
	public Notificationtemplate findByF_GID_TYPE_INTER(long groupId,
		String notificationType, String interval)
		throws NoSuchNotificationtemplateException;

	/**
	* Returns the notificationtemplate where groupId = &#63; and notificationType = &#63; and interval = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param notificationType the notification type
	* @param interval the interval
	* @return the matching notificationtemplate, or <code>null</code> if a matching notificationtemplate could not be found
	*/
	public Notificationtemplate fetchByF_GID_TYPE_INTER(long groupId,
		String notificationType, String interval);

	/**
	* Returns the notificationtemplate where groupId = &#63; and notificationType = &#63; and interval = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param notificationType the notification type
	* @param interval the interval
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching notificationtemplate, or <code>null</code> if a matching notificationtemplate could not be found
	*/
	public Notificationtemplate fetchByF_GID_TYPE_INTER(long groupId,
		String notificationType, String interval, boolean retrieveFromCache);

	/**
	* Removes the notificationtemplate where groupId = &#63; and notificationType = &#63; and interval = &#63; from the database.
	*
	* @param groupId the group ID
	* @param notificationType the notification type
	* @param interval the interval
	* @return the notificationtemplate that was removed
	*/
	public Notificationtemplate removeByF_GID_TYPE_INTER(long groupId,
		String notificationType, String interval)
		throws NoSuchNotificationtemplateException;

	/**
	* Returns the number of notificationtemplates where groupId = &#63; and notificationType = &#63; and interval = &#63;.
	*
	* @param groupId the group ID
	* @param notificationType the notification type
	* @param interval the interval
	* @return the number of matching notificationtemplates
	*/
	public int countByF_GID_TYPE_INTER(long groupId, String notificationType,
		String interval);

	/**
	* Caches the notificationtemplate in the entity cache if it is enabled.
	*
	* @param notificationtemplate the notificationtemplate
	*/
	public void cacheResult(Notificationtemplate notificationtemplate);

	/**
	* Caches the notificationtemplates in the entity cache if it is enabled.
	*
	* @param notificationtemplates the notificationtemplates
	*/
	public void cacheResult(
		java.util.List<Notificationtemplate> notificationtemplates);

	/**
	* Creates a new notificationtemplate with the primary key. Does not add the notificationtemplate to the database.
	*
	* @param notificationTemplateId the primary key for the new notificationtemplate
	* @return the new notificationtemplate
	*/
	public Notificationtemplate create(long notificationTemplateId);

	/**
	* Removes the notificationtemplate with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param notificationTemplateId the primary key of the notificationtemplate
	* @return the notificationtemplate that was removed
	* @throws NoSuchNotificationtemplateException if a notificationtemplate with the primary key could not be found
	*/
	public Notificationtemplate remove(long notificationTemplateId)
		throws NoSuchNotificationtemplateException;

	public Notificationtemplate updateImpl(
		Notificationtemplate notificationtemplate);

	/**
	* Returns the notificationtemplate with the primary key or throws a {@link NoSuchNotificationtemplateException} if it could not be found.
	*
	* @param notificationTemplateId the primary key of the notificationtemplate
	* @return the notificationtemplate
	* @throws NoSuchNotificationtemplateException if a notificationtemplate with the primary key could not be found
	*/
	public Notificationtemplate findByPrimaryKey(long notificationTemplateId)
		throws NoSuchNotificationtemplateException;

	/**
	* Returns the notificationtemplate with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param notificationTemplateId the primary key of the notificationtemplate
	* @return the notificationtemplate, or <code>null</code> if a notificationtemplate with the primary key could not be found
	*/
	public Notificationtemplate fetchByPrimaryKey(long notificationTemplateId);

	@Override
	public java.util.Map<java.io.Serializable, Notificationtemplate> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the notificationtemplates.
	*
	* @return the notificationtemplates
	*/
	public java.util.List<Notificationtemplate> findAll();

	/**
	* Returns a range of all the notificationtemplates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationtemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of notificationtemplates
	* @param end the upper bound of the range of notificationtemplates (not inclusive)
	* @return the range of notificationtemplates
	*/
	public java.util.List<Notificationtemplate> findAll(int start, int end);

	/**
	* Returns an ordered range of all the notificationtemplates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationtemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of notificationtemplates
	* @param end the upper bound of the range of notificationtemplates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of notificationtemplates
	*/
	public java.util.List<Notificationtemplate> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Notificationtemplate> orderByComparator);

	/**
	* Returns an ordered range of all the notificationtemplates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationtemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of notificationtemplates
	* @param end the upper bound of the range of notificationtemplates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of notificationtemplates
	*/
	public java.util.List<Notificationtemplate> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Notificationtemplate> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the notificationtemplates from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of notificationtemplates.
	*
	* @return the number of notificationtemplates
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}