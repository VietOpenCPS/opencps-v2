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

import org.opencps.statistic.model.OpencpsPersonStatistic;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the opencps person statistic service. This utility wraps {@link org.opencps.statistic.service.persistence.impl.OpencpsPersonStatisticPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see OpencpsPersonStatisticPersistence
 * @see org.opencps.statistic.service.persistence.impl.OpencpsPersonStatisticPersistenceImpl
 * @generated
 */
@ProviderType
public class OpencpsPersonStatisticUtil {
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
	public static void clearCache(OpencpsPersonStatistic opencpsPersonStatistic) {
		getPersistence().clearCache(opencpsPersonStatistic);
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
	public static List<OpencpsPersonStatistic> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<OpencpsPersonStatistic> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<OpencpsPersonStatistic> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static OpencpsPersonStatistic update(
		OpencpsPersonStatistic opencpsPersonStatistic) {
		return getPersistence().update(opencpsPersonStatistic);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static OpencpsPersonStatistic update(
		OpencpsPersonStatistic opencpsPersonStatistic,
		ServiceContext serviceContext) {
		return getPersistence().update(opencpsPersonStatistic, serviceContext);
	}

	/**
	* Returns all the opencps person statistics where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching opencps person statistics
	*/
	public static List<OpencpsPersonStatistic> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the opencps person statistics where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of opencps person statistics
	* @param end the upper bound of the range of opencps person statistics (not inclusive)
	* @return the range of matching opencps person statistics
	*/
	public static List<OpencpsPersonStatistic> findByUuid(String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the opencps person statistics where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of opencps person statistics
	* @param end the upper bound of the range of opencps person statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps person statistics
	*/
	public static List<OpencpsPersonStatistic> findByUuid(String uuid,
		int start, int end,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps person statistics where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of opencps person statistics
	* @param end the upper bound of the range of opencps person statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps person statistics
	*/
	public static List<OpencpsPersonStatistic> findByUuid(String uuid,
		int start, int end,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first opencps person statistic in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps person statistic
	* @throws NoSuchOpencpsPersonStatisticException if a matching opencps person statistic could not be found
	*/
	public static OpencpsPersonStatistic findByUuid_First(String uuid,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsPersonStatisticException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first opencps person statistic in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	*/
	public static OpencpsPersonStatistic fetchByUuid_First(String uuid,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last opencps person statistic in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps person statistic
	* @throws NoSuchOpencpsPersonStatisticException if a matching opencps person statistic could not be found
	*/
	public static OpencpsPersonStatistic findByUuid_Last(String uuid,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsPersonStatisticException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last opencps person statistic in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	*/
	public static OpencpsPersonStatistic fetchByUuid_Last(String uuid,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the opencps person statistics before and after the current opencps person statistic in the ordered set where uuid = &#63;.
	*
	* @param personStatisticId the primary key of the current opencps person statistic
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps person statistic
	* @throws NoSuchOpencpsPersonStatisticException if a opencps person statistic with the primary key could not be found
	*/
	public static OpencpsPersonStatistic[] findByUuid_PrevAndNext(
		long personStatisticId, String uuid,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsPersonStatisticException {
		return getPersistence()
				   .findByUuid_PrevAndNext(personStatisticId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the opencps person statistics where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of opencps person statistics where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching opencps person statistics
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the opencps person statistic where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchOpencpsPersonStatisticException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching opencps person statistic
	* @throws NoSuchOpencpsPersonStatisticException if a matching opencps person statistic could not be found
	*/
	public static OpencpsPersonStatistic findByUUID_G(String uuid, long groupId)
		throws org.opencps.statistic.exception.NoSuchOpencpsPersonStatisticException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the opencps person statistic where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	*/
	public static OpencpsPersonStatistic fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the opencps person statistic where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	*/
	public static OpencpsPersonStatistic fetchByUUID_G(String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the opencps person statistic where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the opencps person statistic that was removed
	*/
	public static OpencpsPersonStatistic removeByUUID_G(String uuid,
		long groupId)
		throws org.opencps.statistic.exception.NoSuchOpencpsPersonStatisticException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of opencps person statistics where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching opencps person statistics
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the opencps person statistics where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching opencps person statistics
	*/
	public static List<OpencpsPersonStatistic> findByUuid_C(String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the opencps person statistics where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of opencps person statistics
	* @param end the upper bound of the range of opencps person statistics (not inclusive)
	* @return the range of matching opencps person statistics
	*/
	public static List<OpencpsPersonStatistic> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the opencps person statistics where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of opencps person statistics
	* @param end the upper bound of the range of opencps person statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps person statistics
	*/
	public static List<OpencpsPersonStatistic> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps person statistics where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of opencps person statistics
	* @param end the upper bound of the range of opencps person statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps person statistics
	*/
	public static List<OpencpsPersonStatistic> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first opencps person statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps person statistic
	* @throws NoSuchOpencpsPersonStatisticException if a matching opencps person statistic could not be found
	*/
	public static OpencpsPersonStatistic findByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsPersonStatisticException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first opencps person statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	*/
	public static OpencpsPersonStatistic fetchByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last opencps person statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps person statistic
	* @throws NoSuchOpencpsPersonStatisticException if a matching opencps person statistic could not be found
	*/
	public static OpencpsPersonStatistic findByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsPersonStatisticException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last opencps person statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	*/
	public static OpencpsPersonStatistic fetchByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the opencps person statistics before and after the current opencps person statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param personStatisticId the primary key of the current opencps person statistic
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps person statistic
	* @throws NoSuchOpencpsPersonStatisticException if a opencps person statistic with the primary key could not be found
	*/
	public static OpencpsPersonStatistic[] findByUuid_C_PrevAndNext(
		long personStatisticId, String uuid, long companyId,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsPersonStatisticException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(personStatisticId, uuid,
			companyId, orderByComparator);
	}

	/**
	* Removes all the opencps person statistics where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of opencps person statistics where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching opencps person statistics
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the opencps person statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @return the matching opencps person statistics
	*/
	public static List<OpencpsPersonStatistic> findByG_UID_Y(long groupId,
		long userId, int year) {
		return getPersistence().findByG_UID_Y(groupId, userId, year);
	}

	/**
	* Returns a range of all the opencps person statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param start the lower bound of the range of opencps person statistics
	* @param end the upper bound of the range of opencps person statistics (not inclusive)
	* @return the range of matching opencps person statistics
	*/
	public static List<OpencpsPersonStatistic> findByG_UID_Y(long groupId,
		long userId, int year, int start, int end) {
		return getPersistence().findByG_UID_Y(groupId, userId, year, start, end);
	}

	/**
	* Returns an ordered range of all the opencps person statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param start the lower bound of the range of opencps person statistics
	* @param end the upper bound of the range of opencps person statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps person statistics
	*/
	public static List<OpencpsPersonStatistic> findByG_UID_Y(long groupId,
		long userId, int year, int start, int end,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator) {
		return getPersistence()
				   .findByG_UID_Y(groupId, userId, year, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps person statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param start the lower bound of the range of opencps person statistics
	* @param end the upper bound of the range of opencps person statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps person statistics
	*/
	public static List<OpencpsPersonStatistic> findByG_UID_Y(long groupId,
		long userId, int year, int start, int end,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_UID_Y(groupId, userId, year, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first opencps person statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps person statistic
	* @throws NoSuchOpencpsPersonStatisticException if a matching opencps person statistic could not be found
	*/
	public static OpencpsPersonStatistic findByG_UID_Y_First(long groupId,
		long userId, int year,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsPersonStatisticException {
		return getPersistence()
				   .findByG_UID_Y_First(groupId, userId, year, orderByComparator);
	}

	/**
	* Returns the first opencps person statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	*/
	public static OpencpsPersonStatistic fetchByG_UID_Y_First(long groupId,
		long userId, int year,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByG_UID_Y_First(groupId, userId, year,
			orderByComparator);
	}

	/**
	* Returns the last opencps person statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps person statistic
	* @throws NoSuchOpencpsPersonStatisticException if a matching opencps person statistic could not be found
	*/
	public static OpencpsPersonStatistic findByG_UID_Y_Last(long groupId,
		long userId, int year,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsPersonStatisticException {
		return getPersistence()
				   .findByG_UID_Y_Last(groupId, userId, year, orderByComparator);
	}

	/**
	* Returns the last opencps person statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	*/
	public static OpencpsPersonStatistic fetchByG_UID_Y_Last(long groupId,
		long userId, int year,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByG_UID_Y_Last(groupId, userId, year, orderByComparator);
	}

	/**
	* Returns the opencps person statistics before and after the current opencps person statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param personStatisticId the primary key of the current opencps person statistic
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps person statistic
	* @throws NoSuchOpencpsPersonStatisticException if a opencps person statistic with the primary key could not be found
	*/
	public static OpencpsPersonStatistic[] findByG_UID_Y_PrevAndNext(
		long personStatisticId, long groupId, long userId, int year,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsPersonStatisticException {
		return getPersistence()
				   .findByG_UID_Y_PrevAndNext(personStatisticId, groupId,
			userId, year, orderByComparator);
	}

	/**
	* Removes all the opencps person statistics where groupId = &#63; and userId = &#63; and year = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	*/
	public static void removeByG_UID_Y(long groupId, long userId, int year) {
		getPersistence().removeByG_UID_Y(groupId, userId, year);
	}

	/**
	* Returns the number of opencps person statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @return the number of matching opencps person statistics
	*/
	public static int countByG_UID_Y(long groupId, long userId, int year) {
		return getPersistence().countByG_UID_Y(groupId, userId, year);
	}

	/**
	* Returns the opencps person statistic where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and employeeId = &#63; or throws a {@link NoSuchOpencpsPersonStatisticException} if it could not be found.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param employeeId the employee ID
	* @return the matching opencps person statistic
	* @throws NoSuchOpencpsPersonStatisticException if a matching opencps person statistic could not be found
	*/
	public static OpencpsPersonStatistic findByM_Y_GOV_EMP(long groupId,
		int month, int year, String govAgencyCode, long employeeId)
		throws org.opencps.statistic.exception.NoSuchOpencpsPersonStatisticException {
		return getPersistence()
				   .findByM_Y_GOV_EMP(groupId, month, year, govAgencyCode,
			employeeId);
	}

	/**
	* Returns the opencps person statistic where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and employeeId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param employeeId the employee ID
	* @return the matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	*/
	public static OpencpsPersonStatistic fetchByM_Y_GOV_EMP(long groupId,
		int month, int year, String govAgencyCode, long employeeId) {
		return getPersistence()
				   .fetchByM_Y_GOV_EMP(groupId, month, year, govAgencyCode,
			employeeId);
	}

	/**
	* Returns the opencps person statistic where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and employeeId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param employeeId the employee ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	*/
	public static OpencpsPersonStatistic fetchByM_Y_GOV_EMP(long groupId,
		int month, int year, String govAgencyCode, long employeeId,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByM_Y_GOV_EMP(groupId, month, year, govAgencyCode,
			employeeId, retrieveFromCache);
	}

	/**
	* Removes the opencps person statistic where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and employeeId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param employeeId the employee ID
	* @return the opencps person statistic that was removed
	*/
	public static OpencpsPersonStatistic removeByM_Y_GOV_EMP(long groupId,
		int month, int year, String govAgencyCode, long employeeId)
		throws org.opencps.statistic.exception.NoSuchOpencpsPersonStatisticException {
		return getPersistence()
				   .removeByM_Y_GOV_EMP(groupId, month, year, govAgencyCode,
			employeeId);
	}

	/**
	* Returns the number of opencps person statistics where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and employeeId = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param employeeId the employee ID
	* @return the number of matching opencps person statistics
	*/
	public static int countByM_Y_GOV_EMP(long groupId, int month, int year,
		String govAgencyCode, long employeeId) {
		return getPersistence()
				   .countByM_Y_GOV_EMP(groupId, month, year, govAgencyCode,
			employeeId);
	}

	/**
	* Returns all the opencps person statistics where groupId = &#63; and employeeId = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param employeeId the employee ID
	* @param month the month
	* @param year the year
	* @return the matching opencps person statistics
	*/
	public static List<OpencpsPersonStatistic> findByG_D_M_Y(long groupId,
		long employeeId, int month, int year) {
		return getPersistence().findByG_D_M_Y(groupId, employeeId, month, year);
	}

	/**
	* Returns a range of all the opencps person statistics where groupId = &#63; and employeeId = &#63; and month = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param employeeId the employee ID
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps person statistics
	* @param end the upper bound of the range of opencps person statistics (not inclusive)
	* @return the range of matching opencps person statistics
	*/
	public static List<OpencpsPersonStatistic> findByG_D_M_Y(long groupId,
		long employeeId, int month, int year, int start, int end) {
		return getPersistence()
				   .findByG_D_M_Y(groupId, employeeId, month, year, start, end);
	}

	/**
	* Returns an ordered range of all the opencps person statistics where groupId = &#63; and employeeId = &#63; and month = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param employeeId the employee ID
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps person statistics
	* @param end the upper bound of the range of opencps person statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps person statistics
	*/
	public static List<OpencpsPersonStatistic> findByG_D_M_Y(long groupId,
		long employeeId, int month, int year, int start, int end,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator) {
		return getPersistence()
				   .findByG_D_M_Y(groupId, employeeId, month, year, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps person statistics where groupId = &#63; and employeeId = &#63; and month = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param employeeId the employee ID
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps person statistics
	* @param end the upper bound of the range of opencps person statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps person statistics
	*/
	public static List<OpencpsPersonStatistic> findByG_D_M_Y(long groupId,
		long employeeId, int month, int year, int start, int end,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_D_M_Y(groupId, employeeId, month, year, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first opencps person statistic in the ordered set where groupId = &#63; and employeeId = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param employeeId the employee ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps person statistic
	* @throws NoSuchOpencpsPersonStatisticException if a matching opencps person statistic could not be found
	*/
	public static OpencpsPersonStatistic findByG_D_M_Y_First(long groupId,
		long employeeId, int month, int year,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsPersonStatisticException {
		return getPersistence()
				   .findByG_D_M_Y_First(groupId, employeeId, month, year,
			orderByComparator);
	}

	/**
	* Returns the first opencps person statistic in the ordered set where groupId = &#63; and employeeId = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param employeeId the employee ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	*/
	public static OpencpsPersonStatistic fetchByG_D_M_Y_First(long groupId,
		long employeeId, int month, int year,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByG_D_M_Y_First(groupId, employeeId, month, year,
			orderByComparator);
	}

	/**
	* Returns the last opencps person statistic in the ordered set where groupId = &#63; and employeeId = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param employeeId the employee ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps person statistic
	* @throws NoSuchOpencpsPersonStatisticException if a matching opencps person statistic could not be found
	*/
	public static OpencpsPersonStatistic findByG_D_M_Y_Last(long groupId,
		long employeeId, int month, int year,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsPersonStatisticException {
		return getPersistence()
				   .findByG_D_M_Y_Last(groupId, employeeId, month, year,
			orderByComparator);
	}

	/**
	* Returns the last opencps person statistic in the ordered set where groupId = &#63; and employeeId = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param employeeId the employee ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	*/
	public static OpencpsPersonStatistic fetchByG_D_M_Y_Last(long groupId,
		long employeeId, int month, int year,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByG_D_M_Y_Last(groupId, employeeId, month, year,
			orderByComparator);
	}

	/**
	* Returns the opencps person statistics before and after the current opencps person statistic in the ordered set where groupId = &#63; and employeeId = &#63; and month = &#63; and year = &#63;.
	*
	* @param personStatisticId the primary key of the current opencps person statistic
	* @param groupId the group ID
	* @param employeeId the employee ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps person statistic
	* @throws NoSuchOpencpsPersonStatisticException if a opencps person statistic with the primary key could not be found
	*/
	public static OpencpsPersonStatistic[] findByG_D_M_Y_PrevAndNext(
		long personStatisticId, long groupId, long employeeId, int month,
		int year, OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsPersonStatisticException {
		return getPersistence()
				   .findByG_D_M_Y_PrevAndNext(personStatisticId, groupId,
			employeeId, month, year, orderByComparator);
	}

	/**
	* Removes all the opencps person statistics where groupId = &#63; and employeeId = &#63; and month = &#63; and year = &#63; from the database.
	*
	* @param groupId the group ID
	* @param employeeId the employee ID
	* @param month the month
	* @param year the year
	*/
	public static void removeByG_D_M_Y(long groupId, long employeeId,
		int month, int year) {
		getPersistence().removeByG_D_M_Y(groupId, employeeId, month, year);
	}

	/**
	* Returns the number of opencps person statistics where groupId = &#63; and employeeId = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param employeeId the employee ID
	* @param month the month
	* @param year the year
	* @return the number of matching opencps person statistics
	*/
	public static int countByG_D_M_Y(long groupId, long employeeId, int month,
		int year) {
		return getPersistence().countByG_D_M_Y(groupId, employeeId, month, year);
	}

	/**
	* Returns all the opencps person statistics where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @return the matching opencps person statistics
	*/
	public static List<OpencpsPersonStatistic> findByG_M_Y(long groupId,
		int month, int year) {
		return getPersistence().findByG_M_Y(groupId, month, year);
	}

	/**
	* Returns a range of all the opencps person statistics where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps person statistics
	* @param end the upper bound of the range of opencps person statistics (not inclusive)
	* @return the range of matching opencps person statistics
	*/
	public static List<OpencpsPersonStatistic> findByG_M_Y(long groupId,
		int month, int year, int start, int end) {
		return getPersistence().findByG_M_Y(groupId, month, year, start, end);
	}

	/**
	* Returns an ordered range of all the opencps person statistics where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps person statistics
	* @param end the upper bound of the range of opencps person statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps person statistics
	*/
	public static List<OpencpsPersonStatistic> findByG_M_Y(long groupId,
		int month, int year, int start, int end,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator) {
		return getPersistence()
				   .findByG_M_Y(groupId, month, year, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps person statistics where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps person statistics
	* @param end the upper bound of the range of opencps person statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps person statistics
	*/
	public static List<OpencpsPersonStatistic> findByG_M_Y(long groupId,
		int month, int year, int start, int end,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_M_Y(groupId, month, year, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first opencps person statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps person statistic
	* @throws NoSuchOpencpsPersonStatisticException if a matching opencps person statistic could not be found
	*/
	public static OpencpsPersonStatistic findByG_M_Y_First(long groupId,
		int month, int year,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsPersonStatisticException {
		return getPersistence()
				   .findByG_M_Y_First(groupId, month, year, orderByComparator);
	}

	/**
	* Returns the first opencps person statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	*/
	public static OpencpsPersonStatistic fetchByG_M_Y_First(long groupId,
		int month, int year,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByG_M_Y_First(groupId, month, year, orderByComparator);
	}

	/**
	* Returns the last opencps person statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps person statistic
	* @throws NoSuchOpencpsPersonStatisticException if a matching opencps person statistic could not be found
	*/
	public static OpencpsPersonStatistic findByG_M_Y_Last(long groupId,
		int month, int year,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsPersonStatisticException {
		return getPersistence()
				   .findByG_M_Y_Last(groupId, month, year, orderByComparator);
	}

	/**
	* Returns the last opencps person statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	*/
	public static OpencpsPersonStatistic fetchByG_M_Y_Last(long groupId,
		int month, int year,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByG_M_Y_Last(groupId, month, year, orderByComparator);
	}

	/**
	* Returns the opencps person statistics before and after the current opencps person statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param personStatisticId the primary key of the current opencps person statistic
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps person statistic
	* @throws NoSuchOpencpsPersonStatisticException if a opencps person statistic with the primary key could not be found
	*/
	public static OpencpsPersonStatistic[] findByG_M_Y_PrevAndNext(
		long personStatisticId, long groupId, int month, int year,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsPersonStatisticException {
		return getPersistence()
				   .findByG_M_Y_PrevAndNext(personStatisticId, groupId, month,
			year, orderByComparator);
	}

	/**
	* Removes all the opencps person statistics where groupId = &#63; and month = &#63; and year = &#63; from the database.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	*/
	public static void removeByG_M_Y(long groupId, int month, int year) {
		getPersistence().removeByG_M_Y(groupId, month, year);
	}

	/**
	* Returns the number of opencps person statistics where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @return the number of matching opencps person statistics
	*/
	public static int countByG_M_Y(long groupId, int month, int year) {
		return getPersistence().countByG_M_Y(groupId, month, year);
	}

	/**
	* Returns all the opencps person statistics where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @return the matching opencps person statistics
	*/
	public static List<OpencpsPersonStatistic> findByCID_GID_Y(long companyId,
		long groupId, int month, int year) {
		return getPersistence().findByCID_GID_Y(companyId, groupId, month, year);
	}

	/**
	* Returns a range of all the opencps person statistics where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps person statistics
	* @param end the upper bound of the range of opencps person statistics (not inclusive)
	* @return the range of matching opencps person statistics
	*/
	public static List<OpencpsPersonStatistic> findByCID_GID_Y(long companyId,
		long groupId, int month, int year, int start, int end) {
		return getPersistence()
				   .findByCID_GID_Y(companyId, groupId, month, year, start, end);
	}

	/**
	* Returns an ordered range of all the opencps person statistics where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps person statistics
	* @param end the upper bound of the range of opencps person statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps person statistics
	*/
	public static List<OpencpsPersonStatistic> findByCID_GID_Y(long companyId,
		long groupId, int month, int year, int start, int end,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator) {
		return getPersistence()
				   .findByCID_GID_Y(companyId, groupId, month, year, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps person statistics where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps person statistics
	* @param end the upper bound of the range of opencps person statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps person statistics
	*/
	public static List<OpencpsPersonStatistic> findByCID_GID_Y(long companyId,
		long groupId, int month, int year, int start, int end,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCID_GID_Y(companyId, groupId, month, year, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first opencps person statistic in the ordered set where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps person statistic
	* @throws NoSuchOpencpsPersonStatisticException if a matching opencps person statistic could not be found
	*/
	public static OpencpsPersonStatistic findByCID_GID_Y_First(long companyId,
		long groupId, int month, int year,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsPersonStatisticException {
		return getPersistence()
				   .findByCID_GID_Y_First(companyId, groupId, month, year,
			orderByComparator);
	}

	/**
	* Returns the first opencps person statistic in the ordered set where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	*/
	public static OpencpsPersonStatistic fetchByCID_GID_Y_First(
		long companyId, long groupId, int month, int year,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByCID_GID_Y_First(companyId, groupId, month, year,
			orderByComparator);
	}

	/**
	* Returns the last opencps person statistic in the ordered set where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps person statistic
	* @throws NoSuchOpencpsPersonStatisticException if a matching opencps person statistic could not be found
	*/
	public static OpencpsPersonStatistic findByCID_GID_Y_Last(long companyId,
		long groupId, int month, int year,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsPersonStatisticException {
		return getPersistence()
				   .findByCID_GID_Y_Last(companyId, groupId, month, year,
			orderByComparator);
	}

	/**
	* Returns the last opencps person statistic in the ordered set where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	*/
	public static OpencpsPersonStatistic fetchByCID_GID_Y_Last(long companyId,
		long groupId, int month, int year,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByCID_GID_Y_Last(companyId, groupId, month, year,
			orderByComparator);
	}

	/**
	* Returns the opencps person statistics before and after the current opencps person statistic in the ordered set where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param personStatisticId the primary key of the current opencps person statistic
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps person statistic
	* @throws NoSuchOpencpsPersonStatisticException if a opencps person statistic with the primary key could not be found
	*/
	public static OpencpsPersonStatistic[] findByCID_GID_Y_PrevAndNext(
		long personStatisticId, long companyId, long groupId, int month,
		int year, OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsPersonStatisticException {
		return getPersistence()
				   .findByCID_GID_Y_PrevAndNext(personStatisticId, companyId,
			groupId, month, year, orderByComparator);
	}

	/**
	* Removes all the opencps person statistics where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63; from the database.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	*/
	public static void removeByCID_GID_Y(long companyId, long groupId,
		int month, int year) {
		getPersistence().removeByCID_GID_Y(companyId, groupId, month, year);
	}

	/**
	* Returns the number of opencps person statistics where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @return the number of matching opencps person statistics
	*/
	public static int countByCID_GID_Y(long companyId, long groupId, int month,
		int year) {
		return getPersistence().countByCID_GID_Y(companyId, groupId, month, year);
	}

	/**
	* Caches the opencps person statistic in the entity cache if it is enabled.
	*
	* @param opencpsPersonStatistic the opencps person statistic
	*/
	public static void cacheResult(
		OpencpsPersonStatistic opencpsPersonStatistic) {
		getPersistence().cacheResult(opencpsPersonStatistic);
	}

	/**
	* Caches the opencps person statistics in the entity cache if it is enabled.
	*
	* @param opencpsPersonStatistics the opencps person statistics
	*/
	public static void cacheResult(
		List<OpencpsPersonStatistic> opencpsPersonStatistics) {
		getPersistence().cacheResult(opencpsPersonStatistics);
	}

	/**
	* Creates a new opencps person statistic with the primary key. Does not add the opencps person statistic to the database.
	*
	* @param personStatisticId the primary key for the new opencps person statistic
	* @return the new opencps person statistic
	*/
	public static OpencpsPersonStatistic create(long personStatisticId) {
		return getPersistence().create(personStatisticId);
	}

	/**
	* Removes the opencps person statistic with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param personStatisticId the primary key of the opencps person statistic
	* @return the opencps person statistic that was removed
	* @throws NoSuchOpencpsPersonStatisticException if a opencps person statistic with the primary key could not be found
	*/
	public static OpencpsPersonStatistic remove(long personStatisticId)
		throws org.opencps.statistic.exception.NoSuchOpencpsPersonStatisticException {
		return getPersistence().remove(personStatisticId);
	}

	public static OpencpsPersonStatistic updateImpl(
		OpencpsPersonStatistic opencpsPersonStatistic) {
		return getPersistence().updateImpl(opencpsPersonStatistic);
	}

	/**
	* Returns the opencps person statistic with the primary key or throws a {@link NoSuchOpencpsPersonStatisticException} if it could not be found.
	*
	* @param personStatisticId the primary key of the opencps person statistic
	* @return the opencps person statistic
	* @throws NoSuchOpencpsPersonStatisticException if a opencps person statistic with the primary key could not be found
	*/
	public static OpencpsPersonStatistic findByPrimaryKey(
		long personStatisticId)
		throws org.opencps.statistic.exception.NoSuchOpencpsPersonStatisticException {
		return getPersistence().findByPrimaryKey(personStatisticId);
	}

	/**
	* Returns the opencps person statistic with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param personStatisticId the primary key of the opencps person statistic
	* @return the opencps person statistic, or <code>null</code> if a opencps person statistic with the primary key could not be found
	*/
	public static OpencpsPersonStatistic fetchByPrimaryKey(
		long personStatisticId) {
		return getPersistence().fetchByPrimaryKey(personStatisticId);
	}

	public static java.util.Map<java.io.Serializable, OpencpsPersonStatistic> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the opencps person statistics.
	*
	* @return the opencps person statistics
	*/
	public static List<OpencpsPersonStatistic> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the opencps person statistics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of opencps person statistics
	* @param end the upper bound of the range of opencps person statistics (not inclusive)
	* @return the range of opencps person statistics
	*/
	public static List<OpencpsPersonStatistic> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the opencps person statistics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of opencps person statistics
	* @param end the upper bound of the range of opencps person statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of opencps person statistics
	*/
	public static List<OpencpsPersonStatistic> findAll(int start, int end,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps person statistics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of opencps person statistics
	* @param end the upper bound of the range of opencps person statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of opencps person statistics
	*/
	public static List<OpencpsPersonStatistic> findAll(int start, int end,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the opencps person statistics from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of opencps person statistics.
	*
	* @return the number of opencps person statistics
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static OpencpsPersonStatisticPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<OpencpsPersonStatisticPersistence, OpencpsPersonStatisticPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(OpencpsPersonStatisticPersistence.class);

		ServiceTracker<OpencpsPersonStatisticPersistence, OpencpsPersonStatisticPersistence> serviceTracker =
			new ServiceTracker<OpencpsPersonStatisticPersistence, OpencpsPersonStatisticPersistence>(bundle.getBundleContext(),
				OpencpsPersonStatisticPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}