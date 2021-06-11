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

package org.opencps.statistic.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.statistic.model.OpencpsDossierStatisticMgt;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the opencps dossier statistic mgt service. This utility wraps {@link org.opencps.statistic.service.persistence.impl.OpencpsDossierStatisticMgtPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see OpencpsDossierStatisticMgtPersistence
 * @see org.opencps.statistic.service.persistence.impl.OpencpsDossierStatisticMgtPersistenceImpl
 * @generated
 */
@ProviderType
public class OpencpsDossierStatisticMgtUtil {
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
	public static void clearCache(
		OpencpsDossierStatisticMgt opencpsDossierStatisticMgt) {
		getPersistence().clearCache(opencpsDossierStatisticMgt);
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
	public static List<OpencpsDossierStatisticMgt> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<OpencpsDossierStatisticMgt> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<OpencpsDossierStatisticMgt> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static OpencpsDossierStatisticMgt update(
		OpencpsDossierStatisticMgt opencpsDossierStatisticMgt) {
		return getPersistence().update(opencpsDossierStatisticMgt);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static OpencpsDossierStatisticMgt update(
		OpencpsDossierStatisticMgt opencpsDossierStatisticMgt,
		ServiceContext serviceContext) {
		return getPersistence()
				   .update(opencpsDossierStatisticMgt, serviceContext);
	}

	/**
	* Returns all the opencps dossier statistic mgts where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching opencps dossier statistic mgts
	*/
	public static List<OpencpsDossierStatisticMgt> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the opencps dossier statistic mgts where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @return the range of matching opencps dossier statistic mgts
	*/
	public static List<OpencpsDossierStatisticMgt> findByUuid(String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistic mgts where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossier statistic mgts
	*/
	public static List<OpencpsDossierStatisticMgt> findByUuid(String uuid,
		int start, int end,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistic mgts where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossier statistic mgts
	*/
	public static List<OpencpsDossierStatisticMgt> findByUuid(String uuid,
		int start, int end,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first opencps dossier statistic mgt in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	*/
	public static OpencpsDossierStatisticMgt findByUuid_First(String uuid,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticMgtException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first opencps dossier statistic mgt in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	*/
	public static OpencpsDossierStatisticMgt fetchByUuid_First(String uuid,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic mgt in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	*/
	public static OpencpsDossierStatisticMgt findByUuid_Last(String uuid,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticMgtException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic mgt in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	*/
	public static OpencpsDossierStatisticMgt fetchByUuid_Last(String uuid,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the opencps dossier statistic mgts before and after the current opencps dossier statistic mgt in the ordered set where uuid = &#63;.
	*
	* @param dossierStatisticMgtId the primary key of the current opencps dossier statistic mgt
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a opencps dossier statistic mgt with the primary key could not be found
	*/
	public static OpencpsDossierStatisticMgt[] findByUuid_PrevAndNext(
		long dossierStatisticMgtId, String uuid,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticMgtException {
		return getPersistence()
				   .findByUuid_PrevAndNext(dossierStatisticMgtId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the opencps dossier statistic mgts where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of opencps dossier statistic mgts where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching opencps dossier statistic mgts
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the opencps dossier statistic mgt where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchOpencpsDossierStatisticMgtException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	*/
	public static OpencpsDossierStatisticMgt findByUUID_G(String uuid,
		long groupId)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticMgtException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the opencps dossier statistic mgt where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	*/
	public static OpencpsDossierStatisticMgt fetchByUUID_G(String uuid,
		long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the opencps dossier statistic mgt where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	*/
	public static OpencpsDossierStatisticMgt fetchByUUID_G(String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the opencps dossier statistic mgt where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the opencps dossier statistic mgt that was removed
	*/
	public static OpencpsDossierStatisticMgt removeByUUID_G(String uuid,
		long groupId)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticMgtException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of opencps dossier statistic mgts where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching opencps dossier statistic mgts
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the opencps dossier statistic mgts where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @return the matching opencps dossier statistic mgts
	*/
	public static List<OpencpsDossierStatisticMgt> findByG_UID_Y(long groupId,
		long userId, int year) {
		return getPersistence().findByG_UID_Y(groupId, userId, year);
	}

	/**
	* Returns a range of all the opencps dossier statistic mgts where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @return the range of matching opencps dossier statistic mgts
	*/
	public static List<OpencpsDossierStatisticMgt> findByG_UID_Y(long groupId,
		long userId, int year, int start, int end) {
		return getPersistence().findByG_UID_Y(groupId, userId, year, start, end);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossier statistic mgts
	*/
	public static List<OpencpsDossierStatisticMgt> findByG_UID_Y(long groupId,
		long userId, int year, int start, int end,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		return getPersistence()
				   .findByG_UID_Y(groupId, userId, year, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossier statistic mgts
	*/
	public static List<OpencpsDossierStatisticMgt> findByG_UID_Y(long groupId,
		long userId, int year, int start, int end,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_UID_Y(groupId, userId, year, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first opencps dossier statistic mgt in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	*/
	public static OpencpsDossierStatisticMgt findByG_UID_Y_First(long groupId,
		long userId, int year,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticMgtException {
		return getPersistence()
				   .findByG_UID_Y_First(groupId, userId, year, orderByComparator);
	}

	/**
	* Returns the first opencps dossier statistic mgt in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	*/
	public static OpencpsDossierStatisticMgt fetchByG_UID_Y_First(
		long groupId, long userId, int year,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		return getPersistence()
				   .fetchByG_UID_Y_First(groupId, userId, year,
			orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic mgt in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	*/
	public static OpencpsDossierStatisticMgt findByG_UID_Y_Last(long groupId,
		long userId, int year,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticMgtException {
		return getPersistence()
				   .findByG_UID_Y_Last(groupId, userId, year, orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic mgt in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	*/
	public static OpencpsDossierStatisticMgt fetchByG_UID_Y_Last(long groupId,
		long userId, int year,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		return getPersistence()
				   .fetchByG_UID_Y_Last(groupId, userId, year, orderByComparator);
	}

	/**
	* Returns the opencps dossier statistic mgts before and after the current opencps dossier statistic mgt in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param dossierStatisticMgtId the primary key of the current opencps dossier statistic mgt
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a opencps dossier statistic mgt with the primary key could not be found
	*/
	public static OpencpsDossierStatisticMgt[] findByG_UID_Y_PrevAndNext(
		long dossierStatisticMgtId, long groupId, long userId, int year,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticMgtException {
		return getPersistence()
				   .findByG_UID_Y_PrevAndNext(dossierStatisticMgtId, groupId,
			userId, year, orderByComparator);
	}

	/**
	* Removes all the opencps dossier statistic mgts where groupId = &#63; and userId = &#63; and year = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	*/
	public static void removeByG_UID_Y(long groupId, long userId, int year) {
		getPersistence().removeByG_UID_Y(groupId, userId, year);
	}

	/**
	* Returns the number of opencps dossier statistic mgts where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @return the number of matching opencps dossier statistic mgts
	*/
	public static int countByG_UID_Y(long groupId, long userId, int year) {
		return getPersistence().countByG_UID_Y(groupId, userId, year);
	}

	/**
	* Returns the opencps dossier statistic mgt where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; or throws a {@link NoSuchOpencpsDossierStatisticMgtException} if it could not be found.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @return the matching opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	*/
	public static OpencpsDossierStatisticMgt findByG_M_Y_G_D(long groupId,
		int month, int year, String govAgencyCode, String domainCode)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticMgtException {
		return getPersistence()
				   .findByG_M_Y_G_D(groupId, month, year, govAgencyCode,
			domainCode);
	}

	/**
	* Returns the opencps dossier statistic mgt where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @return the matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	*/
	public static OpencpsDossierStatisticMgt fetchByG_M_Y_G_D(long groupId,
		int month, int year, String govAgencyCode, String domainCode) {
		return getPersistence()
				   .fetchByG_M_Y_G_D(groupId, month, year, govAgencyCode,
			domainCode);
	}

	/**
	* Returns the opencps dossier statistic mgt where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	*/
	public static OpencpsDossierStatisticMgt fetchByG_M_Y_G_D(long groupId,
		int month, int year, String govAgencyCode, String domainCode,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByG_M_Y_G_D(groupId, month, year, govAgencyCode,
			domainCode, retrieveFromCache);
	}

	/**
	* Removes the opencps dossier statistic mgt where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @return the opencps dossier statistic mgt that was removed
	*/
	public static OpencpsDossierStatisticMgt removeByG_M_Y_G_D(long groupId,
		int month, int year, String govAgencyCode, String domainCode)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticMgtException {
		return getPersistence()
				   .removeByG_M_Y_G_D(groupId, month, year, govAgencyCode,
			domainCode);
	}

	/**
	* Returns the number of opencps dossier statistic mgts where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @return the number of matching opencps dossier statistic mgts
	*/
	public static int countByG_M_Y_G_D(long groupId, int month, int year,
		String govAgencyCode, String domainCode) {
		return getPersistence()
				   .countByG_M_Y_G_D(groupId, month, year, govAgencyCode,
			domainCode);
	}

	/**
	* Returns all the opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @return the matching opencps dossier statistic mgts
	*/
	public static List<OpencpsDossierStatisticMgt> findByG_NM_Y(long groupId,
		int month, int year) {
		return getPersistence().findByG_NM_Y(groupId, month, year);
	}

	/**
	* Returns a range of all the opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @return the range of matching opencps dossier statistic mgts
	*/
	public static List<OpencpsDossierStatisticMgt> findByG_NM_Y(long groupId,
		int month, int year, int start, int end) {
		return getPersistence().findByG_NM_Y(groupId, month, year, start, end);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossier statistic mgts
	*/
	public static List<OpencpsDossierStatisticMgt> findByG_NM_Y(long groupId,
		int month, int year, int start, int end,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		return getPersistence()
				   .findByG_NM_Y(groupId, month, year, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossier statistic mgts
	*/
	public static List<OpencpsDossierStatisticMgt> findByG_NM_Y(long groupId,
		int month, int year, int start, int end,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_NM_Y(groupId, month, year, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first opencps dossier statistic mgt in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	*/
	public static OpencpsDossierStatisticMgt findByG_NM_Y_First(long groupId,
		int month, int year,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticMgtException {
		return getPersistence()
				   .findByG_NM_Y_First(groupId, month, year, orderByComparator);
	}

	/**
	* Returns the first opencps dossier statistic mgt in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	*/
	public static OpencpsDossierStatisticMgt fetchByG_NM_Y_First(long groupId,
		int month, int year,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		return getPersistence()
				   .fetchByG_NM_Y_First(groupId, month, year, orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic mgt in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	*/
	public static OpencpsDossierStatisticMgt findByG_NM_Y_Last(long groupId,
		int month, int year,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticMgtException {
		return getPersistence()
				   .findByG_NM_Y_Last(groupId, month, year, orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic mgt in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	*/
	public static OpencpsDossierStatisticMgt fetchByG_NM_Y_Last(long groupId,
		int month, int year,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		return getPersistence()
				   .fetchByG_NM_Y_Last(groupId, month, year, orderByComparator);
	}

	/**
	* Returns the opencps dossier statistic mgts before and after the current opencps dossier statistic mgt in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63;.
	*
	* @param dossierStatisticMgtId the primary key of the current opencps dossier statistic mgt
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a opencps dossier statistic mgt with the primary key could not be found
	*/
	public static OpencpsDossierStatisticMgt[] findByG_NM_Y_PrevAndNext(
		long dossierStatisticMgtId, long groupId, int month, int year,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticMgtException {
		return getPersistence()
				   .findByG_NM_Y_PrevAndNext(dossierStatisticMgtId, groupId,
			month, year, orderByComparator);
	}

	/**
	* Removes all the opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63; from the database.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	*/
	public static void removeByG_NM_Y(long groupId, int month, int year) {
		getPersistence().removeByG_NM_Y(groupId, month, year);
	}

	/**
	* Returns the number of opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @return the number of matching opencps dossier statistic mgts
	*/
	public static int countByG_NM_Y(long groupId, int month, int year) {
		return getPersistence().countByG_NM_Y(groupId, month, year);
	}

	/**
	* Returns all the opencps dossier statistic mgts where groupId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param year the year
	* @return the matching opencps dossier statistic mgts
	*/
	public static List<OpencpsDossierStatisticMgt> findByG_Y_REPO(
		long groupId, int year) {
		return getPersistence().findByG_Y_REPO(groupId, year);
	}

	/**
	* Returns a range of all the opencps dossier statistic mgts where groupId = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param year the year
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @return the range of matching opencps dossier statistic mgts
	*/
	public static List<OpencpsDossierStatisticMgt> findByG_Y_REPO(
		long groupId, int year, int start, int end) {
		return getPersistence().findByG_Y_REPO(groupId, year, start, end);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param year the year
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossier statistic mgts
	*/
	public static List<OpencpsDossierStatisticMgt> findByG_Y_REPO(
		long groupId, int year, int start, int end,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		return getPersistence()
				   .findByG_Y_REPO(groupId, year, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param year the year
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossier statistic mgts
	*/
	public static List<OpencpsDossierStatisticMgt> findByG_Y_REPO(
		long groupId, int year, int start, int end,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_Y_REPO(groupId, year, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first opencps dossier statistic mgt in the ordered set where groupId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	*/
	public static OpencpsDossierStatisticMgt findByG_Y_REPO_First(
		long groupId, int year,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticMgtException {
		return getPersistence()
				   .findByG_Y_REPO_First(groupId, year, orderByComparator);
	}

	/**
	* Returns the first opencps dossier statistic mgt in the ordered set where groupId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	*/
	public static OpencpsDossierStatisticMgt fetchByG_Y_REPO_First(
		long groupId, int year,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		return getPersistence()
				   .fetchByG_Y_REPO_First(groupId, year, orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic mgt in the ordered set where groupId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	*/
	public static OpencpsDossierStatisticMgt findByG_Y_REPO_Last(long groupId,
		int year,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticMgtException {
		return getPersistence()
				   .findByG_Y_REPO_Last(groupId, year, orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic mgt in the ordered set where groupId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	*/
	public static OpencpsDossierStatisticMgt fetchByG_Y_REPO_Last(
		long groupId, int year,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		return getPersistence()
				   .fetchByG_Y_REPO_Last(groupId, year, orderByComparator);
	}

	/**
	* Returns the opencps dossier statistic mgts before and after the current opencps dossier statistic mgt in the ordered set where groupId = &#63; and year = &#63;.
	*
	* @param dossierStatisticMgtId the primary key of the current opencps dossier statistic mgt
	* @param groupId the group ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a opencps dossier statistic mgt with the primary key could not be found
	*/
	public static OpencpsDossierStatisticMgt[] findByG_Y_REPO_PrevAndNext(
		long dossierStatisticMgtId, long groupId, int year,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticMgtException {
		return getPersistence()
				   .findByG_Y_REPO_PrevAndNext(dossierStatisticMgtId, groupId,
			year, orderByComparator);
	}

	/**
	* Returns all the opencps dossier statistic mgts where groupId = &#63; and year = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param years the years
	* @return the matching opencps dossier statistic mgts
	*/
	public static List<OpencpsDossierStatisticMgt> findByG_Y_REPO(
		long groupId, int[] years) {
		return getPersistence().findByG_Y_REPO(groupId, years);
	}

	/**
	* Returns a range of all the opencps dossier statistic mgts where groupId = &#63; and year = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param years the years
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @return the range of matching opencps dossier statistic mgts
	*/
	public static List<OpencpsDossierStatisticMgt> findByG_Y_REPO(
		long groupId, int[] years, int start, int end) {
		return getPersistence().findByG_Y_REPO(groupId, years, start, end);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and year = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param years the years
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossier statistic mgts
	*/
	public static List<OpencpsDossierStatisticMgt> findByG_Y_REPO(
		long groupId, int[] years, int start, int end,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		return getPersistence()
				   .findByG_Y_REPO(groupId, years, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and year = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param year the year
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossier statistic mgts
	*/
	public static List<OpencpsDossierStatisticMgt> findByG_Y_REPO(
		long groupId, int[] years, int start, int end,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_Y_REPO(groupId, years, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the opencps dossier statistic mgts where groupId = &#63; and year = &#63; from the database.
	*
	* @param groupId the group ID
	* @param year the year
	*/
	public static void removeByG_Y_REPO(long groupId, int year) {
		getPersistence().removeByG_Y_REPO(groupId, year);
	}

	/**
	* Returns the number of opencps dossier statistic mgts where groupId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param year the year
	* @return the number of matching opencps dossier statistic mgts
	*/
	public static int countByG_Y_REPO(long groupId, int year) {
		return getPersistence().countByG_Y_REPO(groupId, year);
	}

	/**
	* Returns the number of opencps dossier statistic mgts where groupId = &#63; and year = any &#63;.
	*
	* @param groupId the group ID
	* @param years the years
	* @return the number of matching opencps dossier statistic mgts
	*/
	public static int countByG_Y_REPO(long groupId, int[] years) {
		return getPersistence().countByG_Y_REPO(groupId, years);
	}

	/**
	* Returns all the opencps dossier statistic mgts where groupId = &#63; and userId = &#63; and year = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param groupBy the group by
	* @return the matching opencps dossier statistic mgts
	*/
	public static List<OpencpsDossierStatisticMgt> findByG_UID_Y_GB(
		long groupId, long userId, int year, int groupBy) {
		return getPersistence().findByG_UID_Y_GB(groupId, userId, year, groupBy);
	}

	/**
	* Returns a range of all the opencps dossier statistic mgts where groupId = &#63; and userId = &#63; and year = &#63; and groupBy = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param groupBy the group by
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @return the range of matching opencps dossier statistic mgts
	*/
	public static List<OpencpsDossierStatisticMgt> findByG_UID_Y_GB(
		long groupId, long userId, int year, int groupBy, int start, int end) {
		return getPersistence()
				   .findByG_UID_Y_GB(groupId, userId, year, groupBy, start, end);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and userId = &#63; and year = &#63; and groupBy = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param groupBy the group by
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossier statistic mgts
	*/
	public static List<OpencpsDossierStatisticMgt> findByG_UID_Y_GB(
		long groupId, long userId, int year, int groupBy, int start, int end,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		return getPersistence()
				   .findByG_UID_Y_GB(groupId, userId, year, groupBy, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and userId = &#63; and year = &#63; and groupBy = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param groupBy the group by
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossier statistic mgts
	*/
	public static List<OpencpsDossierStatisticMgt> findByG_UID_Y_GB(
		long groupId, long userId, int year, int groupBy, int start, int end,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_UID_Y_GB(groupId, userId, year, groupBy, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first opencps dossier statistic mgt in the ordered set where groupId = &#63; and userId = &#63; and year = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param groupBy the group by
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	*/
	public static OpencpsDossierStatisticMgt findByG_UID_Y_GB_First(
		long groupId, long userId, int year, int groupBy,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticMgtException {
		return getPersistence()
				   .findByG_UID_Y_GB_First(groupId, userId, year, groupBy,
			orderByComparator);
	}

	/**
	* Returns the first opencps dossier statistic mgt in the ordered set where groupId = &#63; and userId = &#63; and year = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param groupBy the group by
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	*/
	public static OpencpsDossierStatisticMgt fetchByG_UID_Y_GB_First(
		long groupId, long userId, int year, int groupBy,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		return getPersistence()
				   .fetchByG_UID_Y_GB_First(groupId, userId, year, groupBy,
			orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic mgt in the ordered set where groupId = &#63; and userId = &#63; and year = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param groupBy the group by
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	*/
	public static OpencpsDossierStatisticMgt findByG_UID_Y_GB_Last(
		long groupId, long userId, int year, int groupBy,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticMgtException {
		return getPersistence()
				   .findByG_UID_Y_GB_Last(groupId, userId, year, groupBy,
			orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic mgt in the ordered set where groupId = &#63; and userId = &#63; and year = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param groupBy the group by
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	*/
	public static OpencpsDossierStatisticMgt fetchByG_UID_Y_GB_Last(
		long groupId, long userId, int year, int groupBy,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		return getPersistence()
				   .fetchByG_UID_Y_GB_Last(groupId, userId, year, groupBy,
			orderByComparator);
	}

	/**
	* Returns the opencps dossier statistic mgts before and after the current opencps dossier statistic mgt in the ordered set where groupId = &#63; and userId = &#63; and year = &#63; and groupBy = &#63;.
	*
	* @param dossierStatisticMgtId the primary key of the current opencps dossier statistic mgt
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param groupBy the group by
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a opencps dossier statistic mgt with the primary key could not be found
	*/
	public static OpencpsDossierStatisticMgt[] findByG_UID_Y_GB_PrevAndNext(
		long dossierStatisticMgtId, long groupId, long userId, int year,
		int groupBy,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticMgtException {
		return getPersistence()
				   .findByG_UID_Y_GB_PrevAndNext(dossierStatisticMgtId,
			groupId, userId, year, groupBy, orderByComparator);
	}

	/**
	* Removes all the opencps dossier statistic mgts where groupId = &#63; and userId = &#63; and year = &#63; and groupBy = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param groupBy the group by
	*/
	public static void removeByG_UID_Y_GB(long groupId, long userId, int year,
		int groupBy) {
		getPersistence().removeByG_UID_Y_GB(groupId, userId, year, groupBy);
	}

	/**
	* Returns the number of opencps dossier statistic mgts where groupId = &#63; and userId = &#63; and year = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param groupBy the group by
	* @return the number of matching opencps dossier statistic mgts
	*/
	public static int countByG_UID_Y_GB(long groupId, long userId, int year,
		int groupBy) {
		return getPersistence().countByG_UID_Y_GB(groupId, userId, year, groupBy);
	}

	/**
	* Returns the opencps dossier statistic mgt where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupBy = &#63; or throws a {@link NoSuchOpencpsDossierStatisticMgtException} if it could not be found.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupBy the group by
	* @return the matching opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	*/
	public static OpencpsDossierStatisticMgt findByG_M_Y_G_D_GB(long groupId,
		int month, int year, String govAgencyCode, String domainCode,
		int groupBy)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticMgtException {
		return getPersistence()
				   .findByG_M_Y_G_D_GB(groupId, month, year, govAgencyCode,
			domainCode, groupBy);
	}

	/**
	* Returns the opencps dossier statistic mgt where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupBy = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupBy the group by
	* @return the matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	*/
	public static OpencpsDossierStatisticMgt fetchByG_M_Y_G_D_GB(long groupId,
		int month, int year, String govAgencyCode, String domainCode,
		int groupBy) {
		return getPersistence()
				   .fetchByG_M_Y_G_D_GB(groupId, month, year, govAgencyCode,
			domainCode, groupBy);
	}

	/**
	* Returns the opencps dossier statistic mgt where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupBy = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupBy the group by
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	*/
	public static OpencpsDossierStatisticMgt fetchByG_M_Y_G_D_GB(long groupId,
		int month, int year, String govAgencyCode, String domainCode,
		int groupBy, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByG_M_Y_G_D_GB(groupId, month, year, govAgencyCode,
			domainCode, groupBy, retrieveFromCache);
	}

	/**
	* Removes the opencps dossier statistic mgt where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupBy = &#63; from the database.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupBy the group by
	* @return the opencps dossier statistic mgt that was removed
	*/
	public static OpencpsDossierStatisticMgt removeByG_M_Y_G_D_GB(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, int groupBy)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticMgtException {
		return getPersistence()
				   .removeByG_M_Y_G_D_GB(groupId, month, year, govAgencyCode,
			domainCode, groupBy);
	}

	/**
	* Returns the number of opencps dossier statistic mgts where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupBy the group by
	* @return the number of matching opencps dossier statistic mgts
	*/
	public static int countByG_M_Y_G_D_GB(long groupId, int month, int year,
		String govAgencyCode, String domainCode, int groupBy) {
		return getPersistence()
				   .countByG_M_Y_G_D_GB(groupId, month, year, govAgencyCode,
			domainCode, groupBy);
	}

	/**
	* Returns all the opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param groupBy the group by
	* @return the matching opencps dossier statistic mgts
	*/
	public static List<OpencpsDossierStatisticMgt> findByG_NM_Y_GB(
		long groupId, int month, int year, int groupBy) {
		return getPersistence().findByG_NM_Y_GB(groupId, month, year, groupBy);
	}

	/**
	* Returns a range of all the opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63; and groupBy = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param groupBy the group by
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @return the range of matching opencps dossier statistic mgts
	*/
	public static List<OpencpsDossierStatisticMgt> findByG_NM_Y_GB(
		long groupId, int month, int year, int groupBy, int start, int end) {
		return getPersistence()
				   .findByG_NM_Y_GB(groupId, month, year, groupBy, start, end);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63; and groupBy = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param groupBy the group by
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossier statistic mgts
	*/
	public static List<OpencpsDossierStatisticMgt> findByG_NM_Y_GB(
		long groupId, int month, int year, int groupBy, int start, int end,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		return getPersistence()
				   .findByG_NM_Y_GB(groupId, month, year, groupBy, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63; and groupBy = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param groupBy the group by
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossier statistic mgts
	*/
	public static List<OpencpsDossierStatisticMgt> findByG_NM_Y_GB(
		long groupId, int month, int year, int groupBy, int start, int end,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_NM_Y_GB(groupId, month, year, groupBy, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first opencps dossier statistic mgt in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param groupBy the group by
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	*/
	public static OpencpsDossierStatisticMgt findByG_NM_Y_GB_First(
		long groupId, int month, int year, int groupBy,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticMgtException {
		return getPersistence()
				   .findByG_NM_Y_GB_First(groupId, month, year, groupBy,
			orderByComparator);
	}

	/**
	* Returns the first opencps dossier statistic mgt in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param groupBy the group by
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	*/
	public static OpencpsDossierStatisticMgt fetchByG_NM_Y_GB_First(
		long groupId, int month, int year, int groupBy,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		return getPersistence()
				   .fetchByG_NM_Y_GB_First(groupId, month, year, groupBy,
			orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic mgt in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param groupBy the group by
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	*/
	public static OpencpsDossierStatisticMgt findByG_NM_Y_GB_Last(
		long groupId, int month, int year, int groupBy,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticMgtException {
		return getPersistence()
				   .findByG_NM_Y_GB_Last(groupId, month, year, groupBy,
			orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic mgt in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param groupBy the group by
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	*/
	public static OpencpsDossierStatisticMgt fetchByG_NM_Y_GB_Last(
		long groupId, int month, int year, int groupBy,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		return getPersistence()
				   .fetchByG_NM_Y_GB_Last(groupId, month, year, groupBy,
			orderByComparator);
	}

	/**
	* Returns the opencps dossier statistic mgts before and after the current opencps dossier statistic mgt in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63; and groupBy = &#63;.
	*
	* @param dossierStatisticMgtId the primary key of the current opencps dossier statistic mgt
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param groupBy the group by
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a opencps dossier statistic mgt with the primary key could not be found
	*/
	public static OpencpsDossierStatisticMgt[] findByG_NM_Y_GB_PrevAndNext(
		long dossierStatisticMgtId, long groupId, int month, int year,
		int groupBy,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticMgtException {
		return getPersistence()
				   .findByG_NM_Y_GB_PrevAndNext(dossierStatisticMgtId, groupId,
			month, year, groupBy, orderByComparator);
	}

	/**
	* Removes all the opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63; and groupBy = &#63; from the database.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param groupBy the group by
	*/
	public static void removeByG_NM_Y_GB(long groupId, int month, int year,
		int groupBy) {
		getPersistence().removeByG_NM_Y_GB(groupId, month, year, groupBy);
	}

	/**
	* Returns the number of opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param groupBy the group by
	* @return the number of matching opencps dossier statistic mgts
	*/
	public static int countByG_NM_Y_GB(long groupId, int month, int year,
		int groupBy) {
		return getPersistence().countByG_NM_Y_GB(groupId, month, year, groupBy);
	}

	/**
	* Returns all the opencps dossier statistic mgts where groupId = &#63; and year = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param year the year
	* @param groupBy the group by
	* @return the matching opencps dossier statistic mgts
	*/
	public static List<OpencpsDossierStatisticMgt> findByG_Y_REPO_GB(
		long groupId, int year, int groupBy) {
		return getPersistence().findByG_Y_REPO_GB(groupId, year, groupBy);
	}

	/**
	* Returns a range of all the opencps dossier statistic mgts where groupId = &#63; and year = &#63; and groupBy = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param year the year
	* @param groupBy the group by
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @return the range of matching opencps dossier statistic mgts
	*/
	public static List<OpencpsDossierStatisticMgt> findByG_Y_REPO_GB(
		long groupId, int year, int groupBy, int start, int end) {
		return getPersistence()
				   .findByG_Y_REPO_GB(groupId, year, groupBy, start, end);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and year = &#63; and groupBy = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param year the year
	* @param groupBy the group by
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossier statistic mgts
	*/
	public static List<OpencpsDossierStatisticMgt> findByG_Y_REPO_GB(
		long groupId, int year, int groupBy, int start, int end,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		return getPersistence()
				   .findByG_Y_REPO_GB(groupId, year, groupBy, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and year = &#63; and groupBy = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param year the year
	* @param groupBy the group by
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossier statistic mgts
	*/
	public static List<OpencpsDossierStatisticMgt> findByG_Y_REPO_GB(
		long groupId, int year, int groupBy, int start, int end,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_Y_REPO_GB(groupId, year, groupBy, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first opencps dossier statistic mgt in the ordered set where groupId = &#63; and year = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param year the year
	* @param groupBy the group by
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	*/
	public static OpencpsDossierStatisticMgt findByG_Y_REPO_GB_First(
		long groupId, int year, int groupBy,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticMgtException {
		return getPersistence()
				   .findByG_Y_REPO_GB_First(groupId, year, groupBy,
			orderByComparator);
	}

	/**
	* Returns the first opencps dossier statistic mgt in the ordered set where groupId = &#63; and year = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param year the year
	* @param groupBy the group by
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	*/
	public static OpencpsDossierStatisticMgt fetchByG_Y_REPO_GB_First(
		long groupId, int year, int groupBy,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		return getPersistence()
				   .fetchByG_Y_REPO_GB_First(groupId, year, groupBy,
			orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic mgt in the ordered set where groupId = &#63; and year = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param year the year
	* @param groupBy the group by
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	*/
	public static OpencpsDossierStatisticMgt findByG_Y_REPO_GB_Last(
		long groupId, int year, int groupBy,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticMgtException {
		return getPersistence()
				   .findByG_Y_REPO_GB_Last(groupId, year, groupBy,
			orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic mgt in the ordered set where groupId = &#63; and year = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param year the year
	* @param groupBy the group by
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	*/
	public static OpencpsDossierStatisticMgt fetchByG_Y_REPO_GB_Last(
		long groupId, int year, int groupBy,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		return getPersistence()
				   .fetchByG_Y_REPO_GB_Last(groupId, year, groupBy,
			orderByComparator);
	}

	/**
	* Returns the opencps dossier statistic mgts before and after the current opencps dossier statistic mgt in the ordered set where groupId = &#63; and year = &#63; and groupBy = &#63;.
	*
	* @param dossierStatisticMgtId the primary key of the current opencps dossier statistic mgt
	* @param groupId the group ID
	* @param year the year
	* @param groupBy the group by
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a opencps dossier statistic mgt with the primary key could not be found
	*/
	public static OpencpsDossierStatisticMgt[] findByG_Y_REPO_GB_PrevAndNext(
		long dossierStatisticMgtId, long groupId, int year, int groupBy,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticMgtException {
		return getPersistence()
				   .findByG_Y_REPO_GB_PrevAndNext(dossierStatisticMgtId,
			groupId, year, groupBy, orderByComparator);
	}

	/**
	* Returns all the opencps dossier statistic mgts where groupId = &#63; and year = any &#63; and groupBy = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param years the years
	* @param groupBy the group by
	* @return the matching opencps dossier statistic mgts
	*/
	public static List<OpencpsDossierStatisticMgt> findByG_Y_REPO_GB(
		long groupId, int[] years, int groupBy) {
		return getPersistence().findByG_Y_REPO_GB(groupId, years, groupBy);
	}

	/**
	* Returns a range of all the opencps dossier statistic mgts where groupId = &#63; and year = any &#63; and groupBy = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param years the years
	* @param groupBy the group by
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @return the range of matching opencps dossier statistic mgts
	*/
	public static List<OpencpsDossierStatisticMgt> findByG_Y_REPO_GB(
		long groupId, int[] years, int groupBy, int start, int end) {
		return getPersistence()
				   .findByG_Y_REPO_GB(groupId, years, groupBy, start, end);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and year = any &#63; and groupBy = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param years the years
	* @param groupBy the group by
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossier statistic mgts
	*/
	public static List<OpencpsDossierStatisticMgt> findByG_Y_REPO_GB(
		long groupId, int[] years, int groupBy, int start, int end,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		return getPersistence()
				   .findByG_Y_REPO_GB(groupId, years, groupBy, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and year = &#63; and groupBy = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param year the year
	* @param groupBy the group by
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossier statistic mgts
	*/
	public static List<OpencpsDossierStatisticMgt> findByG_Y_REPO_GB(
		long groupId, int[] years, int groupBy, int start, int end,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_Y_REPO_GB(groupId, years, groupBy, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the opencps dossier statistic mgts where groupId = &#63; and year = &#63; and groupBy = &#63; from the database.
	*
	* @param groupId the group ID
	* @param year the year
	* @param groupBy the group by
	*/
	public static void removeByG_Y_REPO_GB(long groupId, int year, int groupBy) {
		getPersistence().removeByG_Y_REPO_GB(groupId, year, groupBy);
	}

	/**
	* Returns the number of opencps dossier statistic mgts where groupId = &#63; and year = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param year the year
	* @param groupBy the group by
	* @return the number of matching opencps dossier statistic mgts
	*/
	public static int countByG_Y_REPO_GB(long groupId, int year, int groupBy) {
		return getPersistence().countByG_Y_REPO_GB(groupId, year, groupBy);
	}

	/**
	* Returns the number of opencps dossier statistic mgts where groupId = &#63; and year = any &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param years the years
	* @param groupBy the group by
	* @return the number of matching opencps dossier statistic mgts
	*/
	public static int countByG_Y_REPO_GB(long groupId, int[] years, int groupBy) {
		return getPersistence().countByG_Y_REPO_GB(groupId, years, groupBy);
	}

	/**
	* Returns all the opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupBy the group by
	* @return the matching opencps dossier statistic mgts
	*/
	public static List<OpencpsDossierStatisticMgt> findByG_NM_Y_G_D_GB(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, int groupBy) {
		return getPersistence()
				   .findByG_NM_Y_G_D_GB(groupId, month, year, govAgencyCode,
			domainCode, groupBy);
	}

	/**
	* Returns a range of all the opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupBy = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupBy the group by
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @return the range of matching opencps dossier statistic mgts
	*/
	public static List<OpencpsDossierStatisticMgt> findByG_NM_Y_G_D_GB(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, int groupBy, int start, int end) {
		return getPersistence()
				   .findByG_NM_Y_G_D_GB(groupId, month, year, govAgencyCode,
			domainCode, groupBy, start, end);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupBy = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupBy the group by
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossier statistic mgts
	*/
	public static List<OpencpsDossierStatisticMgt> findByG_NM_Y_G_D_GB(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, int groupBy, int start, int end,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		return getPersistence()
				   .findByG_NM_Y_G_D_GB(groupId, month, year, govAgencyCode,
			domainCode, groupBy, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupBy = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupBy the group by
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossier statistic mgts
	*/
	public static List<OpencpsDossierStatisticMgt> findByG_NM_Y_G_D_GB(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, int groupBy, int start, int end,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_NM_Y_G_D_GB(groupId, month, year, govAgencyCode,
			domainCode, groupBy, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first opencps dossier statistic mgt in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupBy the group by
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	*/
	public static OpencpsDossierStatisticMgt findByG_NM_Y_G_D_GB_First(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, int groupBy,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticMgtException {
		return getPersistence()
				   .findByG_NM_Y_G_D_GB_First(groupId, month, year,
			govAgencyCode, domainCode, groupBy, orderByComparator);
	}

	/**
	* Returns the first opencps dossier statistic mgt in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupBy the group by
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	*/
	public static OpencpsDossierStatisticMgt fetchByG_NM_Y_G_D_GB_First(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, int groupBy,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		return getPersistence()
				   .fetchByG_NM_Y_G_D_GB_First(groupId, month, year,
			govAgencyCode, domainCode, groupBy, orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic mgt in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupBy the group by
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	*/
	public static OpencpsDossierStatisticMgt findByG_NM_Y_G_D_GB_Last(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, int groupBy,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticMgtException {
		return getPersistence()
				   .findByG_NM_Y_G_D_GB_Last(groupId, month, year,
			govAgencyCode, domainCode, groupBy, orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic mgt in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupBy the group by
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	*/
	public static OpencpsDossierStatisticMgt fetchByG_NM_Y_G_D_GB_Last(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, int groupBy,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		return getPersistence()
				   .fetchByG_NM_Y_G_D_GB_Last(groupId, month, year,
			govAgencyCode, domainCode, groupBy, orderByComparator);
	}

	/**
	* Returns the opencps dossier statistic mgts before and after the current opencps dossier statistic mgt in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupBy = &#63;.
	*
	* @param dossierStatisticMgtId the primary key of the current opencps dossier statistic mgt
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupBy the group by
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a opencps dossier statistic mgt with the primary key could not be found
	*/
	public static OpencpsDossierStatisticMgt[] findByG_NM_Y_G_D_GB_PrevAndNext(
		long dossierStatisticMgtId, long groupId, int month, int year,
		String govAgencyCode, String domainCode, int groupBy,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticMgtException {
		return getPersistence()
				   .findByG_NM_Y_G_D_GB_PrevAndNext(dossierStatisticMgtId,
			groupId, month, year, govAgencyCode, domainCode, groupBy,
			orderByComparator);
	}

	/**
	* Removes all the opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupBy = &#63; from the database.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupBy the group by
	*/
	public static void removeByG_NM_Y_G_D_GB(long groupId, int month, int year,
		String govAgencyCode, String domainCode, int groupBy) {
		getPersistence()
			.removeByG_NM_Y_G_D_GB(groupId, month, year, govAgencyCode,
			domainCode, groupBy);
	}

	/**
	* Returns the number of opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupBy the group by
	* @return the number of matching opencps dossier statistic mgts
	*/
	public static int countByG_NM_Y_G_D_GB(long groupId, int month, int year,
		String govAgencyCode, String domainCode, int groupBy) {
		return getPersistence()
				   .countByG_NM_Y_G_D_GB(groupId, month, year, govAgencyCode,
			domainCode, groupBy);
	}

	/**
	* Caches the opencps dossier statistic mgt in the entity cache if it is enabled.
	*
	* @param opencpsDossierStatisticMgt the opencps dossier statistic mgt
	*/
	public static void cacheResult(
		OpencpsDossierStatisticMgt opencpsDossierStatisticMgt) {
		getPersistence().cacheResult(opencpsDossierStatisticMgt);
	}

	/**
	* Caches the opencps dossier statistic mgts in the entity cache if it is enabled.
	*
	* @param opencpsDossierStatisticMgts the opencps dossier statistic mgts
	*/
	public static void cacheResult(
		List<OpencpsDossierStatisticMgt> opencpsDossierStatisticMgts) {
		getPersistence().cacheResult(opencpsDossierStatisticMgts);
	}

	/**
	* Creates a new opencps dossier statistic mgt with the primary key. Does not add the opencps dossier statistic mgt to the database.
	*
	* @param dossierStatisticMgtId the primary key for the new opencps dossier statistic mgt
	* @return the new opencps dossier statistic mgt
	*/
	public static OpencpsDossierStatisticMgt create(long dossierStatisticMgtId) {
		return getPersistence().create(dossierStatisticMgtId);
	}

	/**
	* Removes the opencps dossier statistic mgt with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierStatisticMgtId the primary key of the opencps dossier statistic mgt
	* @return the opencps dossier statistic mgt that was removed
	* @throws NoSuchOpencpsDossierStatisticMgtException if a opencps dossier statistic mgt with the primary key could not be found
	*/
	public static OpencpsDossierStatisticMgt remove(long dossierStatisticMgtId)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticMgtException {
		return getPersistence().remove(dossierStatisticMgtId);
	}

	public static OpencpsDossierStatisticMgt updateImpl(
		OpencpsDossierStatisticMgt opencpsDossierStatisticMgt) {
		return getPersistence().updateImpl(opencpsDossierStatisticMgt);
	}

	/**
	* Returns the opencps dossier statistic mgt with the primary key or throws a {@link NoSuchOpencpsDossierStatisticMgtException} if it could not be found.
	*
	* @param dossierStatisticMgtId the primary key of the opencps dossier statistic mgt
	* @return the opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a opencps dossier statistic mgt with the primary key could not be found
	*/
	public static OpencpsDossierStatisticMgt findByPrimaryKey(
		long dossierStatisticMgtId)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticMgtException {
		return getPersistence().findByPrimaryKey(dossierStatisticMgtId);
	}

	/**
	* Returns the opencps dossier statistic mgt with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param dossierStatisticMgtId the primary key of the opencps dossier statistic mgt
	* @return the opencps dossier statistic mgt, or <code>null</code> if a opencps dossier statistic mgt with the primary key could not be found
	*/
	public static OpencpsDossierStatisticMgt fetchByPrimaryKey(
		long dossierStatisticMgtId) {
		return getPersistence().fetchByPrimaryKey(dossierStatisticMgtId);
	}

	public static java.util.Map<java.io.Serializable, OpencpsDossierStatisticMgt> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the opencps dossier statistic mgts.
	*
	* @return the opencps dossier statistic mgts
	*/
	public static List<OpencpsDossierStatisticMgt> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the opencps dossier statistic mgts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @return the range of opencps dossier statistic mgts
	*/
	public static List<OpencpsDossierStatisticMgt> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistic mgts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of opencps dossier statistic mgts
	*/
	public static List<OpencpsDossierStatisticMgt> findAll(int start, int end,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistic mgts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of opencps dossier statistic mgts
	*/
	public static List<OpencpsDossierStatisticMgt> findAll(int start, int end,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the opencps dossier statistic mgts from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of opencps dossier statistic mgts.
	*
	* @return the number of opencps dossier statistic mgts
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static OpencpsDossierStatisticMgtPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<OpencpsDossierStatisticMgtPersistence, OpencpsDossierStatisticMgtPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(OpencpsDossierStatisticMgtPersistence.class);

		ServiceTracker<OpencpsDossierStatisticMgtPersistence, OpencpsDossierStatisticMgtPersistence> serviceTracker =
			new ServiceTracker<OpencpsDossierStatisticMgtPersistence, OpencpsDossierStatisticMgtPersistence>(bundle.getBundleContext(),
				OpencpsDossierStatisticMgtPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}