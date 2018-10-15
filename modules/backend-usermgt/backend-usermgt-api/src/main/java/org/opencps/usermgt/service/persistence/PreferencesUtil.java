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

package org.opencps.usermgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.usermgt.model.Preferences;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the preferences service. This utility wraps {@link org.opencps.usermgt.service.persistence.impl.PreferencesPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see PreferencesPersistence
 * @see org.opencps.usermgt.service.persistence.impl.PreferencesPersistenceImpl
 * @generated
 */
@ProviderType
public class PreferencesUtil {
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
	public static void clearCache(Preferences preferences) {
		getPersistence().clearCache(preferences);
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
	public static List<Preferences> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Preferences> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Preferences> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Preferences> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Preferences update(Preferences preferences) {
		return getPersistence().update(preferences);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Preferences update(Preferences preferences,
		ServiceContext serviceContext) {
		return getPersistence().update(preferences, serviceContext);
	}

	/**
	* Returns all the preferenceses where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching preferenceses
	*/
	public static List<Preferences> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the preferenceses where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PreferencesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of preferenceses
	* @param end the upper bound of the range of preferenceses (not inclusive)
	* @return the range of matching preferenceses
	*/
	public static List<Preferences> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the preferenceses where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PreferencesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of preferenceses
	* @param end the upper bound of the range of preferenceses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching preferenceses
	*/
	public static List<Preferences> findByUuid(String uuid, int start, int end,
		OrderByComparator<Preferences> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the preferenceses where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PreferencesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of preferenceses
	* @param end the upper bound of the range of preferenceses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching preferenceses
	*/
	public static List<Preferences> findByUuid(String uuid, int start, int end,
		OrderByComparator<Preferences> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first preferences in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching preferences
	* @throws NoSuchPreferencesException if a matching preferences could not be found
	*/
	public static Preferences findByUuid_First(String uuid,
		OrderByComparator<Preferences> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchPreferencesException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first preferences in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching preferences, or <code>null</code> if a matching preferences could not be found
	*/
	public static Preferences fetchByUuid_First(String uuid,
		OrderByComparator<Preferences> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last preferences in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching preferences
	* @throws NoSuchPreferencesException if a matching preferences could not be found
	*/
	public static Preferences findByUuid_Last(String uuid,
		OrderByComparator<Preferences> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchPreferencesException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last preferences in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching preferences, or <code>null</code> if a matching preferences could not be found
	*/
	public static Preferences fetchByUuid_Last(String uuid,
		OrderByComparator<Preferences> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the preferenceses before and after the current preferences in the ordered set where uuid = &#63;.
	*
	* @param preferencesId the primary key of the current preferences
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next preferences
	* @throws NoSuchPreferencesException if a preferences with the primary key could not be found
	*/
	public static Preferences[] findByUuid_PrevAndNext(long preferencesId,
		String uuid, OrderByComparator<Preferences> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchPreferencesException {
		return getPersistence()
				   .findByUuid_PrevAndNext(preferencesId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the preferenceses where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of preferenceses where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching preferenceses
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the preferences where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPreferencesException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching preferences
	* @throws NoSuchPreferencesException if a matching preferences could not be found
	*/
	public static Preferences findByUUID_G(String uuid, long groupId)
		throws org.opencps.usermgt.exception.NoSuchPreferencesException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the preferences where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching preferences, or <code>null</code> if a matching preferences could not be found
	*/
	public static Preferences fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the preferences where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching preferences, or <code>null</code> if a matching preferences could not be found
	*/
	public static Preferences fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the preferences where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the preferences that was removed
	*/
	public static Preferences removeByUUID_G(String uuid, long groupId)
		throws org.opencps.usermgt.exception.NoSuchPreferencesException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of preferenceses where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching preferenceses
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the preferenceses where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching preferenceses
	*/
	public static List<Preferences> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the preferenceses where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PreferencesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of preferenceses
	* @param end the upper bound of the range of preferenceses (not inclusive)
	* @return the range of matching preferenceses
	*/
	public static List<Preferences> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the preferenceses where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PreferencesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of preferenceses
	* @param end the upper bound of the range of preferenceses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching preferenceses
	*/
	public static List<Preferences> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<Preferences> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the preferenceses where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PreferencesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of preferenceses
	* @param end the upper bound of the range of preferenceses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching preferenceses
	*/
	public static List<Preferences> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<Preferences> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first preferences in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching preferences
	* @throws NoSuchPreferencesException if a matching preferences could not be found
	*/
	public static Preferences findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Preferences> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchPreferencesException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first preferences in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching preferences, or <code>null</code> if a matching preferences could not be found
	*/
	public static Preferences fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Preferences> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last preferences in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching preferences
	* @throws NoSuchPreferencesException if a matching preferences could not be found
	*/
	public static Preferences findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Preferences> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchPreferencesException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last preferences in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching preferences, or <code>null</code> if a matching preferences could not be found
	*/
	public static Preferences fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Preferences> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the preferenceses before and after the current preferences in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param preferencesId the primary key of the current preferences
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next preferences
	* @throws NoSuchPreferencesException if a preferences with the primary key could not be found
	*/
	public static Preferences[] findByUuid_C_PrevAndNext(long preferencesId,
		String uuid, long companyId,
		OrderByComparator<Preferences> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchPreferencesException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(preferencesId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the preferenceses where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of preferenceses where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching preferenceses
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns the preferences where groupId = &#63; and userId = &#63; or throws a {@link NoSuchPreferencesException} if it could not be found.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the matching preferences
	* @throws NoSuchPreferencesException if a matching preferences could not be found
	*/
	public static Preferences findByF_userId(long groupId, long userId)
		throws org.opencps.usermgt.exception.NoSuchPreferencesException {
		return getPersistence().findByF_userId(groupId, userId);
	}

	/**
	* Returns the preferences where groupId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the matching preferences, or <code>null</code> if a matching preferences could not be found
	*/
	public static Preferences fetchByF_userId(long groupId, long userId) {
		return getPersistence().fetchByF_userId(groupId, userId);
	}

	/**
	* Returns the preferences where groupId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching preferences, or <code>null</code> if a matching preferences could not be found
	*/
	public static Preferences fetchByF_userId(long groupId, long userId,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_userId(groupId, userId, retrieveFromCache);
	}

	/**
	* Removes the preferences where groupId = &#63; and userId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the preferences that was removed
	*/
	public static Preferences removeByF_userId(long groupId, long userId)
		throws org.opencps.usermgt.exception.NoSuchPreferencesException {
		return getPersistence().removeByF_userId(groupId, userId);
	}

	/**
	* Returns the number of preferenceses where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the number of matching preferenceses
	*/
	public static int countByF_userId(long groupId, long userId) {
		return getPersistence().countByF_userId(groupId, userId);
	}

	/**
	* Caches the preferences in the entity cache if it is enabled.
	*
	* @param preferences the preferences
	*/
	public static void cacheResult(Preferences preferences) {
		getPersistence().cacheResult(preferences);
	}

	/**
	* Caches the preferenceses in the entity cache if it is enabled.
	*
	* @param preferenceses the preferenceses
	*/
	public static void cacheResult(List<Preferences> preferenceses) {
		getPersistence().cacheResult(preferenceses);
	}

	/**
	* Creates a new preferences with the primary key. Does not add the preferences to the database.
	*
	* @param preferencesId the primary key for the new preferences
	* @return the new preferences
	*/
	public static Preferences create(long preferencesId) {
		return getPersistence().create(preferencesId);
	}

	/**
	* Removes the preferences with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param preferencesId the primary key of the preferences
	* @return the preferences that was removed
	* @throws NoSuchPreferencesException if a preferences with the primary key could not be found
	*/
	public static Preferences remove(long preferencesId)
		throws org.opencps.usermgt.exception.NoSuchPreferencesException {
		return getPersistence().remove(preferencesId);
	}

	public static Preferences updateImpl(Preferences preferences) {
		return getPersistence().updateImpl(preferences);
	}

	/**
	* Returns the preferences with the primary key or throws a {@link NoSuchPreferencesException} if it could not be found.
	*
	* @param preferencesId the primary key of the preferences
	* @return the preferences
	* @throws NoSuchPreferencesException if a preferences with the primary key could not be found
	*/
	public static Preferences findByPrimaryKey(long preferencesId)
		throws org.opencps.usermgt.exception.NoSuchPreferencesException {
		return getPersistence().findByPrimaryKey(preferencesId);
	}

	/**
	* Returns the preferences with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param preferencesId the primary key of the preferences
	* @return the preferences, or <code>null</code> if a preferences with the primary key could not be found
	*/
	public static Preferences fetchByPrimaryKey(long preferencesId) {
		return getPersistence().fetchByPrimaryKey(preferencesId);
	}

	public static java.util.Map<java.io.Serializable, Preferences> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the preferenceses.
	*
	* @return the preferenceses
	*/
	public static List<Preferences> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the preferenceses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PreferencesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of preferenceses
	* @param end the upper bound of the range of preferenceses (not inclusive)
	* @return the range of preferenceses
	*/
	public static List<Preferences> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the preferenceses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PreferencesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of preferenceses
	* @param end the upper bound of the range of preferenceses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of preferenceses
	*/
	public static List<Preferences> findAll(int start, int end,
		OrderByComparator<Preferences> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the preferenceses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PreferencesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of preferenceses
	* @param end the upper bound of the range of preferenceses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of preferenceses
	*/
	public static List<Preferences> findAll(int start, int end,
		OrderByComparator<Preferences> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the preferenceses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of preferenceses.
	*
	* @return the number of preferenceses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static PreferencesPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<PreferencesPersistence, PreferencesPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(PreferencesPersistence.class);

		ServiceTracker<PreferencesPersistence, PreferencesPersistence> serviceTracker =
			new ServiceTracker<PreferencesPersistence, PreferencesPersistence>(bundle.getBundleContext(),
				PreferencesPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}