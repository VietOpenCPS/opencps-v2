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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.communication.model.Notificationtemplate;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the notificationtemplate service. This utility wraps {@link org.opencps.communication.service.persistence.impl.NotificationtemplatePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavd
 * @see NotificationtemplatePersistence
 * @see org.opencps.communication.service.persistence.impl.NotificationtemplatePersistenceImpl
 * @generated
 */
@ProviderType
public class NotificationtemplateUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(Notificationtemplate notificationtemplate) {
		getPersistence().clearCache(notificationtemplate);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Notificationtemplate> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Notificationtemplate> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Notificationtemplate> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Notificationtemplate> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Notificationtemplate update(
		Notificationtemplate notificationtemplate) {
		return getPersistence().update(notificationtemplate);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Notificationtemplate update(
		Notificationtemplate notificationtemplate, ServiceContext serviceContext) {
		return getPersistence().update(notificationtemplate, serviceContext);
	}

	/**
	* Returns the notificationtemplate where groupId = &#63; and notificationType = &#63; or throws a {@link NoSuchNotificationtemplateException} if it could not be found.
	*
	* @param groupId the group ID
	* @param notificationType the notification type
	* @return the matching notificationtemplate
	* @throws NoSuchNotificationtemplateException if a matching notificationtemplate could not be found
	*/
	public static Notificationtemplate findByF_NotificationtemplateByType(
		long groupId, String notificationType)
		throws org.opencps.communication.exception.NoSuchNotificationtemplateException {
		return getPersistence()
				   .findByF_NotificationtemplateByType(groupId, notificationType);
	}

	/**
	* Returns the notificationtemplate where groupId = &#63; and notificationType = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param notificationType the notification type
	* @return the matching notificationtemplate, or <code>null</code> if a matching notificationtemplate could not be found
	*/
	public static Notificationtemplate fetchByF_NotificationtemplateByType(
		long groupId, String notificationType) {
		return getPersistence()
				   .fetchByF_NotificationtemplateByType(groupId,
			notificationType);
	}

	/**
	* Returns the notificationtemplate where groupId = &#63; and notificationType = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param notificationType the notification type
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching notificationtemplate, or <code>null</code> if a matching notificationtemplate could not be found
	*/
	public static Notificationtemplate fetchByF_NotificationtemplateByType(
		long groupId, String notificationType, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_NotificationtemplateByType(groupId,
			notificationType, retrieveFromCache);
	}

	/**
	* Removes the notificationtemplate where groupId = &#63; and notificationType = &#63; from the database.
	*
	* @param groupId the group ID
	* @param notificationType the notification type
	* @return the notificationtemplate that was removed
	*/
	public static Notificationtemplate removeByF_NotificationtemplateByType(
		long groupId, String notificationType)
		throws org.opencps.communication.exception.NoSuchNotificationtemplateException {
		return getPersistence()
				   .removeByF_NotificationtemplateByType(groupId,
			notificationType);
	}

	/**
	* Returns the number of notificationtemplates where groupId = &#63; and notificationType = &#63;.
	*
	* @param groupId the group ID
	* @param notificationType the notification type
	* @return the number of matching notificationtemplates
	*/
	public static int countByF_NotificationtemplateByType(long groupId,
		String notificationType) {
		return getPersistence()
				   .countByF_NotificationtemplateByType(groupId,
			notificationType);
	}

	/**
	* Returns all the notificationtemplates where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching notificationtemplates
	*/
	public static List<Notificationtemplate> findByF_NotificationtemplateByGroup(
		long groupId) {
		return getPersistence().findByF_NotificationtemplateByGroup(groupId);
	}

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
	public static List<Notificationtemplate> findByF_NotificationtemplateByGroup(
		long groupId, int start, int end) {
		return getPersistence()
				   .findByF_NotificationtemplateByGroup(groupId, start, end);
	}

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
	public static List<Notificationtemplate> findByF_NotificationtemplateByGroup(
		long groupId, int start, int end,
		OrderByComparator<Notificationtemplate> orderByComparator) {
		return getPersistence()
				   .findByF_NotificationtemplateByGroup(groupId, start, end,
			orderByComparator);
	}

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
	public static List<Notificationtemplate> findByF_NotificationtemplateByGroup(
		long groupId, int start, int end,
		OrderByComparator<Notificationtemplate> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_NotificationtemplateByGroup(groupId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first notificationtemplate in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching notificationtemplate
	* @throws NoSuchNotificationtemplateException if a matching notificationtemplate could not be found
	*/
	public static Notificationtemplate findByF_NotificationtemplateByGroup_First(
		long groupId, OrderByComparator<Notificationtemplate> orderByComparator)
		throws org.opencps.communication.exception.NoSuchNotificationtemplateException {
		return getPersistence()
				   .findByF_NotificationtemplateByGroup_First(groupId,
			orderByComparator);
	}

	/**
	* Returns the first notificationtemplate in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching notificationtemplate, or <code>null</code> if a matching notificationtemplate could not be found
	*/
	public static Notificationtemplate fetchByF_NotificationtemplateByGroup_First(
		long groupId, OrderByComparator<Notificationtemplate> orderByComparator) {
		return getPersistence()
				   .fetchByF_NotificationtemplateByGroup_First(groupId,
			orderByComparator);
	}

	/**
	* Returns the last notificationtemplate in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching notificationtemplate
	* @throws NoSuchNotificationtemplateException if a matching notificationtemplate could not be found
	*/
	public static Notificationtemplate findByF_NotificationtemplateByGroup_Last(
		long groupId, OrderByComparator<Notificationtemplate> orderByComparator)
		throws org.opencps.communication.exception.NoSuchNotificationtemplateException {
		return getPersistence()
				   .findByF_NotificationtemplateByGroup_Last(groupId,
			orderByComparator);
	}

	/**
	* Returns the last notificationtemplate in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching notificationtemplate, or <code>null</code> if a matching notificationtemplate could not be found
	*/
	public static Notificationtemplate fetchByF_NotificationtemplateByGroup_Last(
		long groupId, OrderByComparator<Notificationtemplate> orderByComparator) {
		return getPersistence()
				   .fetchByF_NotificationtemplateByGroup_Last(groupId,
			orderByComparator);
	}

	/**
	* Returns the notificationtemplates before and after the current notificationtemplate in the ordered set where groupId = &#63;.
	*
	* @param notificationTemplateId the primary key of the current notificationtemplate
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next notificationtemplate
	* @throws NoSuchNotificationtemplateException if a notificationtemplate with the primary key could not be found
	*/
	public static Notificationtemplate[] findByF_NotificationtemplateByGroup_PrevAndNext(
		long notificationTemplateId, long groupId,
		OrderByComparator<Notificationtemplate> orderByComparator)
		throws org.opencps.communication.exception.NoSuchNotificationtemplateException {
		return getPersistence()
				   .findByF_NotificationtemplateByGroup_PrevAndNext(notificationTemplateId,
			groupId, orderByComparator);
	}

	/**
	* Removes all the notificationtemplates where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByF_NotificationtemplateByGroup(long groupId) {
		getPersistence().removeByF_NotificationtemplateByGroup(groupId);
	}

	/**
	* Returns the number of notificationtemplates where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching notificationtemplates
	*/
	public static int countByF_NotificationtemplateByGroup(long groupId) {
		return getPersistence().countByF_NotificationtemplateByGroup(groupId);
	}

	/**
	* Returns all the notificationtemplates where emailSubject = &#63;.
	*
	* @param emailSubject the email subject
	* @return the matching notificationtemplates
	*/
	public static List<Notificationtemplate> findByF_NotificationtemplateEmailSubject(
		String emailSubject) {
		return getPersistence()
				   .findByF_NotificationtemplateEmailSubject(emailSubject);
	}

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
	public static List<Notificationtemplate> findByF_NotificationtemplateEmailSubject(
		String emailSubject, int start, int end) {
		return getPersistence()
				   .findByF_NotificationtemplateEmailSubject(emailSubject,
			start, end);
	}

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
	public static List<Notificationtemplate> findByF_NotificationtemplateEmailSubject(
		String emailSubject, int start, int end,
		OrderByComparator<Notificationtemplate> orderByComparator) {
		return getPersistence()
				   .findByF_NotificationtemplateEmailSubject(emailSubject,
			start, end, orderByComparator);
	}

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
	public static List<Notificationtemplate> findByF_NotificationtemplateEmailSubject(
		String emailSubject, int start, int end,
		OrderByComparator<Notificationtemplate> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_NotificationtemplateEmailSubject(emailSubject,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first notificationtemplate in the ordered set where emailSubject = &#63;.
	*
	* @param emailSubject the email subject
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching notificationtemplate
	* @throws NoSuchNotificationtemplateException if a matching notificationtemplate could not be found
	*/
	public static Notificationtemplate findByF_NotificationtemplateEmailSubject_First(
		String emailSubject,
		OrderByComparator<Notificationtemplate> orderByComparator)
		throws org.opencps.communication.exception.NoSuchNotificationtemplateException {
		return getPersistence()
				   .findByF_NotificationtemplateEmailSubject_First(emailSubject,
			orderByComparator);
	}

	/**
	* Returns the first notificationtemplate in the ordered set where emailSubject = &#63;.
	*
	* @param emailSubject the email subject
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching notificationtemplate, or <code>null</code> if a matching notificationtemplate could not be found
	*/
	public static Notificationtemplate fetchByF_NotificationtemplateEmailSubject_First(
		String emailSubject,
		OrderByComparator<Notificationtemplate> orderByComparator) {
		return getPersistence()
				   .fetchByF_NotificationtemplateEmailSubject_First(emailSubject,
			orderByComparator);
	}

	/**
	* Returns the last notificationtemplate in the ordered set where emailSubject = &#63;.
	*
	* @param emailSubject the email subject
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching notificationtemplate
	* @throws NoSuchNotificationtemplateException if a matching notificationtemplate could not be found
	*/
	public static Notificationtemplate findByF_NotificationtemplateEmailSubject_Last(
		String emailSubject,
		OrderByComparator<Notificationtemplate> orderByComparator)
		throws org.opencps.communication.exception.NoSuchNotificationtemplateException {
		return getPersistence()
				   .findByF_NotificationtemplateEmailSubject_Last(emailSubject,
			orderByComparator);
	}

	/**
	* Returns the last notificationtemplate in the ordered set where emailSubject = &#63;.
	*
	* @param emailSubject the email subject
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching notificationtemplate, or <code>null</code> if a matching notificationtemplate could not be found
	*/
	public static Notificationtemplate fetchByF_NotificationtemplateEmailSubject_Last(
		String emailSubject,
		OrderByComparator<Notificationtemplate> orderByComparator) {
		return getPersistence()
				   .fetchByF_NotificationtemplateEmailSubject_Last(emailSubject,
			orderByComparator);
	}

	/**
	* Returns the notificationtemplates before and after the current notificationtemplate in the ordered set where emailSubject = &#63;.
	*
	* @param notificationTemplateId the primary key of the current notificationtemplate
	* @param emailSubject the email subject
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next notificationtemplate
	* @throws NoSuchNotificationtemplateException if a notificationtemplate with the primary key could not be found
	*/
	public static Notificationtemplate[] findByF_NotificationtemplateEmailSubject_PrevAndNext(
		long notificationTemplateId, String emailSubject,
		OrderByComparator<Notificationtemplate> orderByComparator)
		throws org.opencps.communication.exception.NoSuchNotificationtemplateException {
		return getPersistence()
				   .findByF_NotificationtemplateEmailSubject_PrevAndNext(notificationTemplateId,
			emailSubject, orderByComparator);
	}

	/**
	* Removes all the notificationtemplates where emailSubject = &#63; from the database.
	*
	* @param emailSubject the email subject
	*/
	public static void removeByF_NotificationtemplateEmailSubject(
		String emailSubject) {
		getPersistence().removeByF_NotificationtemplateEmailSubject(emailSubject);
	}

	/**
	* Returns the number of notificationtemplates where emailSubject = &#63;.
	*
	* @param emailSubject the email subject
	* @return the number of matching notificationtemplates
	*/
	public static int countByF_NotificationtemplateEmailSubject(
		String emailSubject) {
		return getPersistence()
				   .countByF_NotificationtemplateEmailSubject(emailSubject);
	}

	/**
	* Returns all the notificationtemplates where expireDuration = &#63;.
	*
	* @param expireDuration the expire duration
	* @return the matching notificationtemplates
	*/
	public static List<Notificationtemplate> findByF_expireDuration(
		int expireDuration) {
		return getPersistence().findByF_expireDuration(expireDuration);
	}

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
	public static List<Notificationtemplate> findByF_expireDuration(
		int expireDuration, int start, int end) {
		return getPersistence()
				   .findByF_expireDuration(expireDuration, start, end);
	}

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
	public static List<Notificationtemplate> findByF_expireDuration(
		int expireDuration, int start, int end,
		OrderByComparator<Notificationtemplate> orderByComparator) {
		return getPersistence()
				   .findByF_expireDuration(expireDuration, start, end,
			orderByComparator);
	}

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
	public static List<Notificationtemplate> findByF_expireDuration(
		int expireDuration, int start, int end,
		OrderByComparator<Notificationtemplate> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_expireDuration(expireDuration, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first notificationtemplate in the ordered set where expireDuration = &#63;.
	*
	* @param expireDuration the expire duration
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching notificationtemplate
	* @throws NoSuchNotificationtemplateException if a matching notificationtemplate could not be found
	*/
	public static Notificationtemplate findByF_expireDuration_First(
		int expireDuration,
		OrderByComparator<Notificationtemplate> orderByComparator)
		throws org.opencps.communication.exception.NoSuchNotificationtemplateException {
		return getPersistence()
				   .findByF_expireDuration_First(expireDuration,
			orderByComparator);
	}

	/**
	* Returns the first notificationtemplate in the ordered set where expireDuration = &#63;.
	*
	* @param expireDuration the expire duration
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching notificationtemplate, or <code>null</code> if a matching notificationtemplate could not be found
	*/
	public static Notificationtemplate fetchByF_expireDuration_First(
		int expireDuration,
		OrderByComparator<Notificationtemplate> orderByComparator) {
		return getPersistence()
				   .fetchByF_expireDuration_First(expireDuration,
			orderByComparator);
	}

	/**
	* Returns the last notificationtemplate in the ordered set where expireDuration = &#63;.
	*
	* @param expireDuration the expire duration
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching notificationtemplate
	* @throws NoSuchNotificationtemplateException if a matching notificationtemplate could not be found
	*/
	public static Notificationtemplate findByF_expireDuration_Last(
		int expireDuration,
		OrderByComparator<Notificationtemplate> orderByComparator)
		throws org.opencps.communication.exception.NoSuchNotificationtemplateException {
		return getPersistence()
				   .findByF_expireDuration_Last(expireDuration,
			orderByComparator);
	}

	/**
	* Returns the last notificationtemplate in the ordered set where expireDuration = &#63;.
	*
	* @param expireDuration the expire duration
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching notificationtemplate, or <code>null</code> if a matching notificationtemplate could not be found
	*/
	public static Notificationtemplate fetchByF_expireDuration_Last(
		int expireDuration,
		OrderByComparator<Notificationtemplate> orderByComparator) {
		return getPersistence()
				   .fetchByF_expireDuration_Last(expireDuration,
			orderByComparator);
	}

	/**
	* Returns the notificationtemplates before and after the current notificationtemplate in the ordered set where expireDuration = &#63;.
	*
	* @param notificationTemplateId the primary key of the current notificationtemplate
	* @param expireDuration the expire duration
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next notificationtemplate
	* @throws NoSuchNotificationtemplateException if a notificationtemplate with the primary key could not be found
	*/
	public static Notificationtemplate[] findByF_expireDuration_PrevAndNext(
		long notificationTemplateId, int expireDuration,
		OrderByComparator<Notificationtemplate> orderByComparator)
		throws org.opencps.communication.exception.NoSuchNotificationtemplateException {
		return getPersistence()
				   .findByF_expireDuration_PrevAndNext(notificationTemplateId,
			expireDuration, orderByComparator);
	}

	/**
	* Removes all the notificationtemplates where expireDuration = &#63; from the database.
	*
	* @param expireDuration the expire duration
	*/
	public static void removeByF_expireDuration(int expireDuration) {
		getPersistence().removeByF_expireDuration(expireDuration);
	}

	/**
	* Returns the number of notificationtemplates where expireDuration = &#63;.
	*
	* @param expireDuration the expire duration
	* @return the number of matching notificationtemplates
	*/
	public static int countByF_expireDuration(int expireDuration) {
		return getPersistence().countByF_expireDuration(expireDuration);
	}

	/**
	* Returns all the notificationtemplates where interval = &#63;.
	*
	* @param interval the interval
	* @return the matching notificationtemplates
	*/
	public static List<Notificationtemplate> findByF_interval(String interval) {
		return getPersistence().findByF_interval(interval);
	}

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
	public static List<Notificationtemplate> findByF_interval(String interval,
		int start, int end) {
		return getPersistence().findByF_interval(interval, start, end);
	}

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
	public static List<Notificationtemplate> findByF_interval(String interval,
		int start, int end,
		OrderByComparator<Notificationtemplate> orderByComparator) {
		return getPersistence()
				   .findByF_interval(interval, start, end, orderByComparator);
	}

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
	public static List<Notificationtemplate> findByF_interval(String interval,
		int start, int end,
		OrderByComparator<Notificationtemplate> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_interval(interval, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first notificationtemplate in the ordered set where interval = &#63;.
	*
	* @param interval the interval
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching notificationtemplate
	* @throws NoSuchNotificationtemplateException if a matching notificationtemplate could not be found
	*/
	public static Notificationtemplate findByF_interval_First(String interval,
		OrderByComparator<Notificationtemplate> orderByComparator)
		throws org.opencps.communication.exception.NoSuchNotificationtemplateException {
		return getPersistence()
				   .findByF_interval_First(interval, orderByComparator);
	}

	/**
	* Returns the first notificationtemplate in the ordered set where interval = &#63;.
	*
	* @param interval the interval
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching notificationtemplate, or <code>null</code> if a matching notificationtemplate could not be found
	*/
	public static Notificationtemplate fetchByF_interval_First(
		String interval,
		OrderByComparator<Notificationtemplate> orderByComparator) {
		return getPersistence()
				   .fetchByF_interval_First(interval, orderByComparator);
	}

	/**
	* Returns the last notificationtemplate in the ordered set where interval = &#63;.
	*
	* @param interval the interval
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching notificationtemplate
	* @throws NoSuchNotificationtemplateException if a matching notificationtemplate could not be found
	*/
	public static Notificationtemplate findByF_interval_Last(String interval,
		OrderByComparator<Notificationtemplate> orderByComparator)
		throws org.opencps.communication.exception.NoSuchNotificationtemplateException {
		return getPersistence()
				   .findByF_interval_Last(interval, orderByComparator);
	}

	/**
	* Returns the last notificationtemplate in the ordered set where interval = &#63;.
	*
	* @param interval the interval
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching notificationtemplate, or <code>null</code> if a matching notificationtemplate could not be found
	*/
	public static Notificationtemplate fetchByF_interval_Last(String interval,
		OrderByComparator<Notificationtemplate> orderByComparator) {
		return getPersistence()
				   .fetchByF_interval_Last(interval, orderByComparator);
	}

	/**
	* Returns the notificationtemplates before and after the current notificationtemplate in the ordered set where interval = &#63;.
	*
	* @param notificationTemplateId the primary key of the current notificationtemplate
	* @param interval the interval
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next notificationtemplate
	* @throws NoSuchNotificationtemplateException if a notificationtemplate with the primary key could not be found
	*/
	public static Notificationtemplate[] findByF_interval_PrevAndNext(
		long notificationTemplateId, String interval,
		OrderByComparator<Notificationtemplate> orderByComparator)
		throws org.opencps.communication.exception.NoSuchNotificationtemplateException {
		return getPersistence()
				   .findByF_interval_PrevAndNext(notificationTemplateId,
			interval, orderByComparator);
	}

	/**
	* Removes all the notificationtemplates where interval = &#63; from the database.
	*
	* @param interval the interval
	*/
	public static void removeByF_interval(String interval) {
		getPersistence().removeByF_interval(interval);
	}

	/**
	* Returns the number of notificationtemplates where interval = &#63;.
	*
	* @param interval the interval
	* @return the number of matching notificationtemplates
	*/
	public static int countByF_interval(String interval) {
		return getPersistence().countByF_interval(interval);
	}

	/**
	* Returns the notificationtemplate where groupId = &#63; and notificationType = &#63; and interval = &#63; or throws a {@link NoSuchNotificationtemplateException} if it could not be found.
	*
	* @param groupId the group ID
	* @param notificationType the notification type
	* @param interval the interval
	* @return the matching notificationtemplate
	* @throws NoSuchNotificationtemplateException if a matching notificationtemplate could not be found
	*/
	public static Notificationtemplate findByF_GID_TYPE_INTER(long groupId,
		String notificationType, String interval)
		throws org.opencps.communication.exception.NoSuchNotificationtemplateException {
		return getPersistence()
				   .findByF_GID_TYPE_INTER(groupId, notificationType, interval);
	}

	/**
	* Returns the notificationtemplate where groupId = &#63; and notificationType = &#63; and interval = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param notificationType the notification type
	* @param interval the interval
	* @return the matching notificationtemplate, or <code>null</code> if a matching notificationtemplate could not be found
	*/
	public static Notificationtemplate fetchByF_GID_TYPE_INTER(long groupId,
		String notificationType, String interval) {
		return getPersistence()
				   .fetchByF_GID_TYPE_INTER(groupId, notificationType, interval);
	}

	/**
	* Returns the notificationtemplate where groupId = &#63; and notificationType = &#63; and interval = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param notificationType the notification type
	* @param interval the interval
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching notificationtemplate, or <code>null</code> if a matching notificationtemplate could not be found
	*/
	public static Notificationtemplate fetchByF_GID_TYPE_INTER(long groupId,
		String notificationType, String interval, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_GID_TYPE_INTER(groupId, notificationType,
			interval, retrieveFromCache);
	}

	/**
	* Removes the notificationtemplate where groupId = &#63; and notificationType = &#63; and interval = &#63; from the database.
	*
	* @param groupId the group ID
	* @param notificationType the notification type
	* @param interval the interval
	* @return the notificationtemplate that was removed
	*/
	public static Notificationtemplate removeByF_GID_TYPE_INTER(long groupId,
		String notificationType, String interval)
		throws org.opencps.communication.exception.NoSuchNotificationtemplateException {
		return getPersistence()
				   .removeByF_GID_TYPE_INTER(groupId, notificationType, interval);
	}

	/**
	* Returns the number of notificationtemplates where groupId = &#63; and notificationType = &#63; and interval = &#63;.
	*
	* @param groupId the group ID
	* @param notificationType the notification type
	* @param interval the interval
	* @return the number of matching notificationtemplates
	*/
	public static int countByF_GID_TYPE_INTER(long groupId,
		String notificationType, String interval) {
		return getPersistence()
				   .countByF_GID_TYPE_INTER(groupId, notificationType, interval);
	}

	/**
	* Caches the notificationtemplate in the entity cache if it is enabled.
	*
	* @param notificationtemplate the notificationtemplate
	*/
	public static void cacheResult(Notificationtemplate notificationtemplate) {
		getPersistence().cacheResult(notificationtemplate);
	}

	/**
	* Caches the notificationtemplates in the entity cache if it is enabled.
	*
	* @param notificationtemplates the notificationtemplates
	*/
	public static void cacheResult(
		List<Notificationtemplate> notificationtemplates) {
		getPersistence().cacheResult(notificationtemplates);
	}

	/**
	* Creates a new notificationtemplate with the primary key. Does not add the notificationtemplate to the database.
	*
	* @param notificationTemplateId the primary key for the new notificationtemplate
	* @return the new notificationtemplate
	*/
	public static Notificationtemplate create(long notificationTemplateId) {
		return getPersistence().create(notificationTemplateId);
	}

	/**
	* Removes the notificationtemplate with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param notificationTemplateId the primary key of the notificationtemplate
	* @return the notificationtemplate that was removed
	* @throws NoSuchNotificationtemplateException if a notificationtemplate with the primary key could not be found
	*/
	public static Notificationtemplate remove(long notificationTemplateId)
		throws org.opencps.communication.exception.NoSuchNotificationtemplateException {
		return getPersistence().remove(notificationTemplateId);
	}

	public static Notificationtemplate updateImpl(
		Notificationtemplate notificationtemplate) {
		return getPersistence().updateImpl(notificationtemplate);
	}

	/**
	* Returns the notificationtemplate with the primary key or throws a {@link NoSuchNotificationtemplateException} if it could not be found.
	*
	* @param notificationTemplateId the primary key of the notificationtemplate
	* @return the notificationtemplate
	* @throws NoSuchNotificationtemplateException if a notificationtemplate with the primary key could not be found
	*/
	public static Notificationtemplate findByPrimaryKey(
		long notificationTemplateId)
		throws org.opencps.communication.exception.NoSuchNotificationtemplateException {
		return getPersistence().findByPrimaryKey(notificationTemplateId);
	}

	/**
	* Returns the notificationtemplate with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param notificationTemplateId the primary key of the notificationtemplate
	* @return the notificationtemplate, or <code>null</code> if a notificationtemplate with the primary key could not be found
	*/
	public static Notificationtemplate fetchByPrimaryKey(
		long notificationTemplateId) {
		return getPersistence().fetchByPrimaryKey(notificationTemplateId);
	}

	public static java.util.Map<java.io.Serializable, Notificationtemplate> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the notificationtemplates.
	*
	* @return the notificationtemplates
	*/
	public static List<Notificationtemplate> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<Notificationtemplate> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<Notificationtemplate> findAll(int start, int end,
		OrderByComparator<Notificationtemplate> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<Notificationtemplate> findAll(int start, int end,
		OrderByComparator<Notificationtemplate> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the notificationtemplates from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of notificationtemplates.
	*
	* @return the number of notificationtemplates
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static NotificationtemplatePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<NotificationtemplatePersistence, NotificationtemplatePersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(NotificationtemplatePersistence.class);

		ServiceTracker<NotificationtemplatePersistence, NotificationtemplatePersistence> serviceTracker =
			new ServiceTracker<NotificationtemplatePersistence, NotificationtemplatePersistence>(bundle.getBundleContext(),
				NotificationtemplatePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}