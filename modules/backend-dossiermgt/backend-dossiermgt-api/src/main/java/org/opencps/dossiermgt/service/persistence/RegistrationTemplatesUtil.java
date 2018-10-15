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

package org.opencps.dossiermgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.dossiermgt.model.RegistrationTemplates;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the registration templates service. This utility wraps {@link org.opencps.dossiermgt.service.persistence.impl.RegistrationTemplatesPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see RegistrationTemplatesPersistence
 * @see org.opencps.dossiermgt.service.persistence.impl.RegistrationTemplatesPersistenceImpl
 * @generated
 */
@ProviderType
public class RegistrationTemplatesUtil {
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
	public static void clearCache(RegistrationTemplates registrationTemplates) {
		getPersistence().clearCache(registrationTemplates);
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
	public static List<RegistrationTemplates> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<RegistrationTemplates> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<RegistrationTemplates> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<RegistrationTemplates> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static RegistrationTemplates update(
		RegistrationTemplates registrationTemplates) {
		return getPersistence().update(registrationTemplates);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static RegistrationTemplates update(
		RegistrationTemplates registrationTemplates,
		ServiceContext serviceContext) {
		return getPersistence().update(registrationTemplates, serviceContext);
	}

	/**
	* Returns all the registration templateses where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching registration templateses
	*/
	public static List<RegistrationTemplates> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the registration templateses where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationTemplatesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of registration templateses
	* @param end the upper bound of the range of registration templateses (not inclusive)
	* @return the range of matching registration templateses
	*/
	public static List<RegistrationTemplates> findByUuid(String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the registration templateses where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationTemplatesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of registration templateses
	* @param end the upper bound of the range of registration templateses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching registration templateses
	*/
	public static List<RegistrationTemplates> findByUuid(String uuid,
		int start, int end,
		OrderByComparator<RegistrationTemplates> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the registration templateses where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationTemplatesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of registration templateses
	* @param end the upper bound of the range of registration templateses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching registration templateses
	*/
	public static List<RegistrationTemplates> findByUuid(String uuid,
		int start, int end,
		OrderByComparator<RegistrationTemplates> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first registration templates in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration templates
	* @throws NoSuchRegistrationTemplatesException if a matching registration templates could not be found
	*/
	public static RegistrationTemplates findByUuid_First(String uuid,
		OrderByComparator<RegistrationTemplates> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationTemplatesException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first registration templates in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration templates, or <code>null</code> if a matching registration templates could not be found
	*/
	public static RegistrationTemplates fetchByUuid_First(String uuid,
		OrderByComparator<RegistrationTemplates> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last registration templates in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration templates
	* @throws NoSuchRegistrationTemplatesException if a matching registration templates could not be found
	*/
	public static RegistrationTemplates findByUuid_Last(String uuid,
		OrderByComparator<RegistrationTemplates> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationTemplatesException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last registration templates in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration templates, or <code>null</code> if a matching registration templates could not be found
	*/
	public static RegistrationTemplates fetchByUuid_Last(String uuid,
		OrderByComparator<RegistrationTemplates> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the registration templateses before and after the current registration templates in the ordered set where uuid = &#63;.
	*
	* @param registrationTemplateId the primary key of the current registration templates
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next registration templates
	* @throws NoSuchRegistrationTemplatesException if a registration templates with the primary key could not be found
	*/
	public static RegistrationTemplates[] findByUuid_PrevAndNext(
		long registrationTemplateId, String uuid,
		OrderByComparator<RegistrationTemplates> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationTemplatesException {
		return getPersistence()
				   .findByUuid_PrevAndNext(registrationTemplateId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the registration templateses where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of registration templateses where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching registration templateses
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the registration templates where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchRegistrationTemplatesException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching registration templates
	* @throws NoSuchRegistrationTemplatesException if a matching registration templates could not be found
	*/
	public static RegistrationTemplates findByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationTemplatesException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the registration templates where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching registration templates, or <code>null</code> if a matching registration templates could not be found
	*/
	public static RegistrationTemplates fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the registration templates where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching registration templates, or <code>null</code> if a matching registration templates could not be found
	*/
	public static RegistrationTemplates fetchByUUID_G(String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the registration templates where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the registration templates that was removed
	*/
	public static RegistrationTemplates removeByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationTemplatesException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of registration templateses where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching registration templateses
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns the registration templates where groupId = &#63; and registrationTemplateId = &#63; or throws a {@link NoSuchRegistrationTemplatesException} if it could not be found.
	*
	* @param groupId the group ID
	* @param registrationTemplateId the registration template ID
	* @return the matching registration templates
	* @throws NoSuchRegistrationTemplatesException if a matching registration templates could not be found
	*/
	public static RegistrationTemplates findByG_REGID(long groupId,
		long registrationTemplateId)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationTemplatesException {
		return getPersistence().findByG_REGID(groupId, registrationTemplateId);
	}

	/**
	* Returns the registration templates where groupId = &#63; and registrationTemplateId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param registrationTemplateId the registration template ID
	* @return the matching registration templates, or <code>null</code> if a matching registration templates could not be found
	*/
	public static RegistrationTemplates fetchByG_REGID(long groupId,
		long registrationTemplateId) {
		return getPersistence().fetchByG_REGID(groupId, registrationTemplateId);
	}

	/**
	* Returns the registration templates where groupId = &#63; and registrationTemplateId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param registrationTemplateId the registration template ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching registration templates, or <code>null</code> if a matching registration templates could not be found
	*/
	public static RegistrationTemplates fetchByG_REGID(long groupId,
		long registrationTemplateId, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByG_REGID(groupId, registrationTemplateId,
			retrieveFromCache);
	}

	/**
	* Removes the registration templates where groupId = &#63; and registrationTemplateId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param registrationTemplateId the registration template ID
	* @return the registration templates that was removed
	*/
	public static RegistrationTemplates removeByG_REGID(long groupId,
		long registrationTemplateId)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationTemplatesException {
		return getPersistence().removeByG_REGID(groupId, registrationTemplateId);
	}

	/**
	* Returns the number of registration templateses where groupId = &#63; and registrationTemplateId = &#63;.
	*
	* @param groupId the group ID
	* @param registrationTemplateId the registration template ID
	* @return the number of matching registration templateses
	*/
	public static int countByG_REGID(long groupId, long registrationTemplateId) {
		return getPersistence().countByG_REGID(groupId, registrationTemplateId);
	}

	/**
	* Returns all the registration templateses where groupId = &#63; and formNo = &#63;.
	*
	* @param groupId the group ID
	* @param formNo the form no
	* @return the matching registration templateses
	*/
	public static List<RegistrationTemplates> findByFNO(long groupId,
		String formNo) {
		return getPersistence().findByFNO(groupId, formNo);
	}

	/**
	* Returns a range of all the registration templateses where groupId = &#63; and formNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationTemplatesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param formNo the form no
	* @param start the lower bound of the range of registration templateses
	* @param end the upper bound of the range of registration templateses (not inclusive)
	* @return the range of matching registration templateses
	*/
	public static List<RegistrationTemplates> findByFNO(long groupId,
		String formNo, int start, int end) {
		return getPersistence().findByFNO(groupId, formNo, start, end);
	}

	/**
	* Returns an ordered range of all the registration templateses where groupId = &#63; and formNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationTemplatesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param formNo the form no
	* @param start the lower bound of the range of registration templateses
	* @param end the upper bound of the range of registration templateses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching registration templateses
	*/
	public static List<RegistrationTemplates> findByFNO(long groupId,
		String formNo, int start, int end,
		OrderByComparator<RegistrationTemplates> orderByComparator) {
		return getPersistence()
				   .findByFNO(groupId, formNo, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the registration templateses where groupId = &#63; and formNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationTemplatesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param formNo the form no
	* @param start the lower bound of the range of registration templateses
	* @param end the upper bound of the range of registration templateses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching registration templateses
	*/
	public static List<RegistrationTemplates> findByFNO(long groupId,
		String formNo, int start, int end,
		OrderByComparator<RegistrationTemplates> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByFNO(groupId, formNo, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first registration templates in the ordered set where groupId = &#63; and formNo = &#63;.
	*
	* @param groupId the group ID
	* @param formNo the form no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration templates
	* @throws NoSuchRegistrationTemplatesException if a matching registration templates could not be found
	*/
	public static RegistrationTemplates findByFNO_First(long groupId,
		String formNo,
		OrderByComparator<RegistrationTemplates> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationTemplatesException {
		return getPersistence()
				   .findByFNO_First(groupId, formNo, orderByComparator);
	}

	/**
	* Returns the first registration templates in the ordered set where groupId = &#63; and formNo = &#63;.
	*
	* @param groupId the group ID
	* @param formNo the form no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration templates, or <code>null</code> if a matching registration templates could not be found
	*/
	public static RegistrationTemplates fetchByFNO_First(long groupId,
		String formNo,
		OrderByComparator<RegistrationTemplates> orderByComparator) {
		return getPersistence()
				   .fetchByFNO_First(groupId, formNo, orderByComparator);
	}

	/**
	* Returns the last registration templates in the ordered set where groupId = &#63; and formNo = &#63;.
	*
	* @param groupId the group ID
	* @param formNo the form no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration templates
	* @throws NoSuchRegistrationTemplatesException if a matching registration templates could not be found
	*/
	public static RegistrationTemplates findByFNO_Last(long groupId,
		String formNo,
		OrderByComparator<RegistrationTemplates> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationTemplatesException {
		return getPersistence()
				   .findByFNO_Last(groupId, formNo, orderByComparator);
	}

	/**
	* Returns the last registration templates in the ordered set where groupId = &#63; and formNo = &#63;.
	*
	* @param groupId the group ID
	* @param formNo the form no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration templates, or <code>null</code> if a matching registration templates could not be found
	*/
	public static RegistrationTemplates fetchByFNO_Last(long groupId,
		String formNo,
		OrderByComparator<RegistrationTemplates> orderByComparator) {
		return getPersistence()
				   .fetchByFNO_Last(groupId, formNo, orderByComparator);
	}

	/**
	* Returns the registration templateses before and after the current registration templates in the ordered set where groupId = &#63; and formNo = &#63;.
	*
	* @param registrationTemplateId the primary key of the current registration templates
	* @param groupId the group ID
	* @param formNo the form no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next registration templates
	* @throws NoSuchRegistrationTemplatesException if a registration templates with the primary key could not be found
	*/
	public static RegistrationTemplates[] findByFNO_PrevAndNext(
		long registrationTemplateId, long groupId, String formNo,
		OrderByComparator<RegistrationTemplates> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationTemplatesException {
		return getPersistence()
				   .findByFNO_PrevAndNext(registrationTemplateId, groupId,
			formNo, orderByComparator);
	}

	/**
	* Removes all the registration templateses where groupId = &#63; and formNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param formNo the form no
	*/
	public static void removeByFNO(long groupId, String formNo) {
		getPersistence().removeByFNO(groupId, formNo);
	}

	/**
	* Returns the number of registration templateses where groupId = &#63; and formNo = &#63;.
	*
	* @param groupId the group ID
	* @param formNo the form no
	* @return the number of matching registration templateses
	*/
	public static int countByFNO(long groupId, String formNo) {
		return getPersistence().countByFNO(groupId, formNo);
	}

	/**
	* Returns all the registration templateses where groupId = &#63; and govAgencyCode = &#63;.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @return the matching registration templateses
	*/
	public static List<RegistrationTemplates> findByGOVCODE(long groupId,
		String govAgencyCode) {
		return getPersistence().findByGOVCODE(groupId, govAgencyCode);
	}

	/**
	* Returns a range of all the registration templateses where groupId = &#63; and govAgencyCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationTemplatesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param start the lower bound of the range of registration templateses
	* @param end the upper bound of the range of registration templateses (not inclusive)
	* @return the range of matching registration templateses
	*/
	public static List<RegistrationTemplates> findByGOVCODE(long groupId,
		String govAgencyCode, int start, int end) {
		return getPersistence().findByGOVCODE(groupId, govAgencyCode, start, end);
	}

	/**
	* Returns an ordered range of all the registration templateses where groupId = &#63; and govAgencyCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationTemplatesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param start the lower bound of the range of registration templateses
	* @param end the upper bound of the range of registration templateses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching registration templateses
	*/
	public static List<RegistrationTemplates> findByGOVCODE(long groupId,
		String govAgencyCode, int start, int end,
		OrderByComparator<RegistrationTemplates> orderByComparator) {
		return getPersistence()
				   .findByGOVCODE(groupId, govAgencyCode, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the registration templateses where groupId = &#63; and govAgencyCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationTemplatesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param start the lower bound of the range of registration templateses
	* @param end the upper bound of the range of registration templateses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching registration templateses
	*/
	public static List<RegistrationTemplates> findByGOVCODE(long groupId,
		String govAgencyCode, int start, int end,
		OrderByComparator<RegistrationTemplates> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGOVCODE(groupId, govAgencyCode, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first registration templates in the ordered set where groupId = &#63; and govAgencyCode = &#63;.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration templates
	* @throws NoSuchRegistrationTemplatesException if a matching registration templates could not be found
	*/
	public static RegistrationTemplates findByGOVCODE_First(long groupId,
		String govAgencyCode,
		OrderByComparator<RegistrationTemplates> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationTemplatesException {
		return getPersistence()
				   .findByGOVCODE_First(groupId, govAgencyCode,
			orderByComparator);
	}

	/**
	* Returns the first registration templates in the ordered set where groupId = &#63; and govAgencyCode = &#63;.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration templates, or <code>null</code> if a matching registration templates could not be found
	*/
	public static RegistrationTemplates fetchByGOVCODE_First(long groupId,
		String govAgencyCode,
		OrderByComparator<RegistrationTemplates> orderByComparator) {
		return getPersistence()
				   .fetchByGOVCODE_First(groupId, govAgencyCode,
			orderByComparator);
	}

	/**
	* Returns the last registration templates in the ordered set where groupId = &#63; and govAgencyCode = &#63;.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration templates
	* @throws NoSuchRegistrationTemplatesException if a matching registration templates could not be found
	*/
	public static RegistrationTemplates findByGOVCODE_Last(long groupId,
		String govAgencyCode,
		OrderByComparator<RegistrationTemplates> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationTemplatesException {
		return getPersistence()
				   .findByGOVCODE_Last(groupId, govAgencyCode, orderByComparator);
	}

	/**
	* Returns the last registration templates in the ordered set where groupId = &#63; and govAgencyCode = &#63;.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration templates, or <code>null</code> if a matching registration templates could not be found
	*/
	public static RegistrationTemplates fetchByGOVCODE_Last(long groupId,
		String govAgencyCode,
		OrderByComparator<RegistrationTemplates> orderByComparator) {
		return getPersistence()
				   .fetchByGOVCODE_Last(groupId, govAgencyCode,
			orderByComparator);
	}

	/**
	* Returns the registration templateses before and after the current registration templates in the ordered set where groupId = &#63; and govAgencyCode = &#63;.
	*
	* @param registrationTemplateId the primary key of the current registration templates
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next registration templates
	* @throws NoSuchRegistrationTemplatesException if a registration templates with the primary key could not be found
	*/
	public static RegistrationTemplates[] findByGOVCODE_PrevAndNext(
		long registrationTemplateId, long groupId, String govAgencyCode,
		OrderByComparator<RegistrationTemplates> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationTemplatesException {
		return getPersistence()
				   .findByGOVCODE_PrevAndNext(registrationTemplateId, groupId,
			govAgencyCode, orderByComparator);
	}

	/**
	* Removes all the registration templateses where groupId = &#63; and govAgencyCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	*/
	public static void removeByGOVCODE(long groupId, String govAgencyCode) {
		getPersistence().removeByGOVCODE(groupId, govAgencyCode);
	}

	/**
	* Returns the number of registration templateses where groupId = &#63; and govAgencyCode = &#63;.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @return the number of matching registration templateses
	*/
	public static int countByGOVCODE(long groupId, String govAgencyCode) {
		return getPersistence().countByGOVCODE(groupId, govAgencyCode);
	}

	/**
	* Returns the registration templates where groupId = &#63; and formNo = &#63; and govAgencyCode = &#63; or throws a {@link NoSuchRegistrationTemplatesException} if it could not be found.
	*
	* @param groupId the group ID
	* @param formNo the form no
	* @param govAgencyCode the gov agency code
	* @return the matching registration templates
	* @throws NoSuchRegistrationTemplatesException if a matching registration templates could not be found
	*/
	public static RegistrationTemplates findByGOVCODE_FORMNO(long groupId,
		String formNo, String govAgencyCode)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationTemplatesException {
		return getPersistence()
				   .findByGOVCODE_FORMNO(groupId, formNo, govAgencyCode);
	}

	/**
	* Returns the registration templates where groupId = &#63; and formNo = &#63; and govAgencyCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param formNo the form no
	* @param govAgencyCode the gov agency code
	* @return the matching registration templates, or <code>null</code> if a matching registration templates could not be found
	*/
	public static RegistrationTemplates fetchByGOVCODE_FORMNO(long groupId,
		String formNo, String govAgencyCode) {
		return getPersistence()
				   .fetchByGOVCODE_FORMNO(groupId, formNo, govAgencyCode);
	}

	/**
	* Returns the registration templates where groupId = &#63; and formNo = &#63; and govAgencyCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param formNo the form no
	* @param govAgencyCode the gov agency code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching registration templates, or <code>null</code> if a matching registration templates could not be found
	*/
	public static RegistrationTemplates fetchByGOVCODE_FORMNO(long groupId,
		String formNo, String govAgencyCode, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByGOVCODE_FORMNO(groupId, formNo, govAgencyCode,
			retrieveFromCache);
	}

	/**
	* Removes the registration templates where groupId = &#63; and formNo = &#63; and govAgencyCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param formNo the form no
	* @param govAgencyCode the gov agency code
	* @return the registration templates that was removed
	*/
	public static RegistrationTemplates removeByGOVCODE_FORMNO(long groupId,
		String formNo, String govAgencyCode)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationTemplatesException {
		return getPersistence()
				   .removeByGOVCODE_FORMNO(groupId, formNo, govAgencyCode);
	}

	/**
	* Returns the number of registration templateses where groupId = &#63; and formNo = &#63; and govAgencyCode = &#63;.
	*
	* @param groupId the group ID
	* @param formNo the form no
	* @param govAgencyCode the gov agency code
	* @return the number of matching registration templateses
	*/
	public static int countByGOVCODE_FORMNO(long groupId, String formNo,
		String govAgencyCode) {
		return getPersistence()
				   .countByGOVCODE_FORMNO(groupId, formNo, govAgencyCode);
	}

	/**
	* Returns all the registration templateses where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching registration templateses
	*/
	public static List<RegistrationTemplates> findByGROUPID(long groupId) {
		return getPersistence().findByGROUPID(groupId);
	}

	/**
	* Returns a range of all the registration templateses where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationTemplatesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of registration templateses
	* @param end the upper bound of the range of registration templateses (not inclusive)
	* @return the range of matching registration templateses
	*/
	public static List<RegistrationTemplates> findByGROUPID(long groupId,
		int start, int end) {
		return getPersistence().findByGROUPID(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the registration templateses where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationTemplatesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of registration templateses
	* @param end the upper bound of the range of registration templateses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching registration templateses
	*/
	public static List<RegistrationTemplates> findByGROUPID(long groupId,
		int start, int end,
		OrderByComparator<RegistrationTemplates> orderByComparator) {
		return getPersistence()
				   .findByGROUPID(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the registration templateses where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationTemplatesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of registration templateses
	* @param end the upper bound of the range of registration templateses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching registration templateses
	*/
	public static List<RegistrationTemplates> findByGROUPID(long groupId,
		int start, int end,
		OrderByComparator<RegistrationTemplates> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGROUPID(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first registration templates in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration templates
	* @throws NoSuchRegistrationTemplatesException if a matching registration templates could not be found
	*/
	public static RegistrationTemplates findByGROUPID_First(long groupId,
		OrderByComparator<RegistrationTemplates> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationTemplatesException {
		return getPersistence().findByGROUPID_First(groupId, orderByComparator);
	}

	/**
	* Returns the first registration templates in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration templates, or <code>null</code> if a matching registration templates could not be found
	*/
	public static RegistrationTemplates fetchByGROUPID_First(long groupId,
		OrderByComparator<RegistrationTemplates> orderByComparator) {
		return getPersistence().fetchByGROUPID_First(groupId, orderByComparator);
	}

	/**
	* Returns the last registration templates in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration templates
	* @throws NoSuchRegistrationTemplatesException if a matching registration templates could not be found
	*/
	public static RegistrationTemplates findByGROUPID_Last(long groupId,
		OrderByComparator<RegistrationTemplates> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationTemplatesException {
		return getPersistence().findByGROUPID_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last registration templates in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration templates, or <code>null</code> if a matching registration templates could not be found
	*/
	public static RegistrationTemplates fetchByGROUPID_Last(long groupId,
		OrderByComparator<RegistrationTemplates> orderByComparator) {
		return getPersistence().fetchByGROUPID_Last(groupId, orderByComparator);
	}

	/**
	* Returns the registration templateses before and after the current registration templates in the ordered set where groupId = &#63;.
	*
	* @param registrationTemplateId the primary key of the current registration templates
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next registration templates
	* @throws NoSuchRegistrationTemplatesException if a registration templates with the primary key could not be found
	*/
	public static RegistrationTemplates[] findByGROUPID_PrevAndNext(
		long registrationTemplateId, long groupId,
		OrderByComparator<RegistrationTemplates> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationTemplatesException {
		return getPersistence()
				   .findByGROUPID_PrevAndNext(registrationTemplateId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the registration templateses where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGROUPID(long groupId) {
		getPersistence().removeByGROUPID(groupId);
	}

	/**
	* Returns the number of registration templateses where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching registration templateses
	*/
	public static int countByGROUPID(long groupId) {
		return getPersistence().countByGROUPID(groupId);
	}

	/**
	* Caches the registration templates in the entity cache if it is enabled.
	*
	* @param registrationTemplates the registration templates
	*/
	public static void cacheResult(RegistrationTemplates registrationTemplates) {
		getPersistence().cacheResult(registrationTemplates);
	}

	/**
	* Caches the registration templateses in the entity cache if it is enabled.
	*
	* @param registrationTemplateses the registration templateses
	*/
	public static void cacheResult(
		List<RegistrationTemplates> registrationTemplateses) {
		getPersistence().cacheResult(registrationTemplateses);
	}

	/**
	* Creates a new registration templates with the primary key. Does not add the registration templates to the database.
	*
	* @param registrationTemplateId the primary key for the new registration templates
	* @return the new registration templates
	*/
	public static RegistrationTemplates create(long registrationTemplateId) {
		return getPersistence().create(registrationTemplateId);
	}

	/**
	* Removes the registration templates with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param registrationTemplateId the primary key of the registration templates
	* @return the registration templates that was removed
	* @throws NoSuchRegistrationTemplatesException if a registration templates with the primary key could not be found
	*/
	public static RegistrationTemplates remove(long registrationTemplateId)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationTemplatesException {
		return getPersistence().remove(registrationTemplateId);
	}

	public static RegistrationTemplates updateImpl(
		RegistrationTemplates registrationTemplates) {
		return getPersistence().updateImpl(registrationTemplates);
	}

	/**
	* Returns the registration templates with the primary key or throws a {@link NoSuchRegistrationTemplatesException} if it could not be found.
	*
	* @param registrationTemplateId the primary key of the registration templates
	* @return the registration templates
	* @throws NoSuchRegistrationTemplatesException if a registration templates with the primary key could not be found
	*/
	public static RegistrationTemplates findByPrimaryKey(
		long registrationTemplateId)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationTemplatesException {
		return getPersistence().findByPrimaryKey(registrationTemplateId);
	}

	/**
	* Returns the registration templates with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param registrationTemplateId the primary key of the registration templates
	* @return the registration templates, or <code>null</code> if a registration templates with the primary key could not be found
	*/
	public static RegistrationTemplates fetchByPrimaryKey(
		long registrationTemplateId) {
		return getPersistence().fetchByPrimaryKey(registrationTemplateId);
	}

	public static java.util.Map<java.io.Serializable, RegistrationTemplates> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the registration templateses.
	*
	* @return the registration templateses
	*/
	public static List<RegistrationTemplates> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the registration templateses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationTemplatesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of registration templateses
	* @param end the upper bound of the range of registration templateses (not inclusive)
	* @return the range of registration templateses
	*/
	public static List<RegistrationTemplates> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the registration templateses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationTemplatesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of registration templateses
	* @param end the upper bound of the range of registration templateses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of registration templateses
	*/
	public static List<RegistrationTemplates> findAll(int start, int end,
		OrderByComparator<RegistrationTemplates> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the registration templateses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationTemplatesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of registration templateses
	* @param end the upper bound of the range of registration templateses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of registration templateses
	*/
	public static List<RegistrationTemplates> findAll(int start, int end,
		OrderByComparator<RegistrationTemplates> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the registration templateses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of registration templateses.
	*
	* @return the number of registration templateses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static RegistrationTemplatesPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<RegistrationTemplatesPersistence, RegistrationTemplatesPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(RegistrationTemplatesPersistence.class);

		ServiceTracker<RegistrationTemplatesPersistence, RegistrationTemplatesPersistence> serviceTracker =
			new ServiceTracker<RegistrationTemplatesPersistence, RegistrationTemplatesPersistence>(bundle.getBundleContext(),
				RegistrationTemplatesPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}