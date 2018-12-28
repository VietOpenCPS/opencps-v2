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

import org.opencps.statistic.model.OpencpsVotingStatistic;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the opencps voting statistic service. This utility wraps {@link org.opencps.statistic.service.persistence.impl.OpencpsVotingStatisticPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see OpencpsVotingStatisticPersistence
 * @see org.opencps.statistic.service.persistence.impl.OpencpsVotingStatisticPersistenceImpl
 * @generated
 */
@ProviderType
public class OpencpsVotingStatisticUtil {
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
	public static void clearCache(OpencpsVotingStatistic opencpsVotingStatistic) {
		getPersistence().clearCache(opencpsVotingStatistic);
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
	public static List<OpencpsVotingStatistic> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<OpencpsVotingStatistic> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<OpencpsVotingStatistic> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static OpencpsVotingStatistic update(
		OpencpsVotingStatistic opencpsVotingStatistic) {
		return getPersistence().update(opencpsVotingStatistic);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static OpencpsVotingStatistic update(
		OpencpsVotingStatistic opencpsVotingStatistic,
		ServiceContext serviceContext) {
		return getPersistence().update(opencpsVotingStatistic, serviceContext);
	}

	/**
	* Returns all the opencps voting statistics where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching opencps voting statistics
	*/
	public static List<OpencpsVotingStatistic> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the opencps voting statistics where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of opencps voting statistics
	* @param end the upper bound of the range of opencps voting statistics (not inclusive)
	* @return the range of matching opencps voting statistics
	*/
	public static List<OpencpsVotingStatistic> findByUuid(String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the opencps voting statistics where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of opencps voting statistics
	* @param end the upper bound of the range of opencps voting statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps voting statistics
	*/
	public static List<OpencpsVotingStatistic> findByUuid(String uuid,
		int start, int end,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps voting statistics where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of opencps voting statistics
	* @param end the upper bound of the range of opencps voting statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps voting statistics
	*/
	public static List<OpencpsVotingStatistic> findByUuid(String uuid,
		int start, int end,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first opencps voting statistic in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps voting statistic
	* @throws NoSuchOpencpsVotingStatisticException if a matching opencps voting statistic could not be found
	*/
	public static OpencpsVotingStatistic findByUuid_First(String uuid,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsVotingStatisticException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first opencps voting statistic in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps voting statistic, or <code>null</code> if a matching opencps voting statistic could not be found
	*/
	public static OpencpsVotingStatistic fetchByUuid_First(String uuid,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last opencps voting statistic in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps voting statistic
	* @throws NoSuchOpencpsVotingStatisticException if a matching opencps voting statistic could not be found
	*/
	public static OpencpsVotingStatistic findByUuid_Last(String uuid,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsVotingStatisticException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last opencps voting statistic in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps voting statistic, or <code>null</code> if a matching opencps voting statistic could not be found
	*/
	public static OpencpsVotingStatistic fetchByUuid_Last(String uuid,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the opencps voting statistics before and after the current opencps voting statistic in the ordered set where uuid = &#63;.
	*
	* @param votingStatisticId the primary key of the current opencps voting statistic
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps voting statistic
	* @throws NoSuchOpencpsVotingStatisticException if a opencps voting statistic with the primary key could not be found
	*/
	public static OpencpsVotingStatistic[] findByUuid_PrevAndNext(
		long votingStatisticId, String uuid,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsVotingStatisticException {
		return getPersistence()
				   .findByUuid_PrevAndNext(votingStatisticId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the opencps voting statistics where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of opencps voting statistics where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching opencps voting statistics
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the opencps voting statistic where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchOpencpsVotingStatisticException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching opencps voting statistic
	* @throws NoSuchOpencpsVotingStatisticException if a matching opencps voting statistic could not be found
	*/
	public static OpencpsVotingStatistic findByUUID_G(String uuid, long groupId)
		throws org.opencps.statistic.exception.NoSuchOpencpsVotingStatisticException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the opencps voting statistic where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching opencps voting statistic, or <code>null</code> if a matching opencps voting statistic could not be found
	*/
	public static OpencpsVotingStatistic fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the opencps voting statistic where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching opencps voting statistic, or <code>null</code> if a matching opencps voting statistic could not be found
	*/
	public static OpencpsVotingStatistic fetchByUUID_G(String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the opencps voting statistic where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the opencps voting statistic that was removed
	*/
	public static OpencpsVotingStatistic removeByUUID_G(String uuid,
		long groupId)
		throws org.opencps.statistic.exception.NoSuchOpencpsVotingStatisticException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of opencps voting statistics where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching opencps voting statistics
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the opencps voting statistics where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching opencps voting statistics
	*/
	public static List<OpencpsVotingStatistic> findByUuid_C(String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the opencps voting statistics where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of opencps voting statistics
	* @param end the upper bound of the range of opencps voting statistics (not inclusive)
	* @return the range of matching opencps voting statistics
	*/
	public static List<OpencpsVotingStatistic> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the opencps voting statistics where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of opencps voting statistics
	* @param end the upper bound of the range of opencps voting statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps voting statistics
	*/
	public static List<OpencpsVotingStatistic> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps voting statistics where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of opencps voting statistics
	* @param end the upper bound of the range of opencps voting statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps voting statistics
	*/
	public static List<OpencpsVotingStatistic> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first opencps voting statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps voting statistic
	* @throws NoSuchOpencpsVotingStatisticException if a matching opencps voting statistic could not be found
	*/
	public static OpencpsVotingStatistic findByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsVotingStatisticException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first opencps voting statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps voting statistic, or <code>null</code> if a matching opencps voting statistic could not be found
	*/
	public static OpencpsVotingStatistic fetchByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last opencps voting statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps voting statistic
	* @throws NoSuchOpencpsVotingStatisticException if a matching opencps voting statistic could not be found
	*/
	public static OpencpsVotingStatistic findByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsVotingStatisticException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last opencps voting statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps voting statistic, or <code>null</code> if a matching opencps voting statistic could not be found
	*/
	public static OpencpsVotingStatistic fetchByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the opencps voting statistics before and after the current opencps voting statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param votingStatisticId the primary key of the current opencps voting statistic
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps voting statistic
	* @throws NoSuchOpencpsVotingStatisticException if a opencps voting statistic with the primary key could not be found
	*/
	public static OpencpsVotingStatistic[] findByUuid_C_PrevAndNext(
		long votingStatisticId, String uuid, long companyId,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsVotingStatisticException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(votingStatisticId, uuid,
			companyId, orderByComparator);
	}

	/**
	* Removes all the opencps voting statistics where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of opencps voting statistics where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching opencps voting statistics
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the opencps voting statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @return the matching opencps voting statistics
	*/
	public static List<OpencpsVotingStatistic> findByG_UID_Y(long groupId,
		long userId, int year) {
		return getPersistence().findByG_UID_Y(groupId, userId, year);
	}

	/**
	* Returns a range of all the opencps voting statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param start the lower bound of the range of opencps voting statistics
	* @param end the upper bound of the range of opencps voting statistics (not inclusive)
	* @return the range of matching opencps voting statistics
	*/
	public static List<OpencpsVotingStatistic> findByG_UID_Y(long groupId,
		long userId, int year, int start, int end) {
		return getPersistence().findByG_UID_Y(groupId, userId, year, start, end);
	}

	/**
	* Returns an ordered range of all the opencps voting statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param start the lower bound of the range of opencps voting statistics
	* @param end the upper bound of the range of opencps voting statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps voting statistics
	*/
	public static List<OpencpsVotingStatistic> findByG_UID_Y(long groupId,
		long userId, int year, int start, int end,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator) {
		return getPersistence()
				   .findByG_UID_Y(groupId, userId, year, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps voting statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param start the lower bound of the range of opencps voting statistics
	* @param end the upper bound of the range of opencps voting statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps voting statistics
	*/
	public static List<OpencpsVotingStatistic> findByG_UID_Y(long groupId,
		long userId, int year, int start, int end,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_UID_Y(groupId, userId, year, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first opencps voting statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps voting statistic
	* @throws NoSuchOpencpsVotingStatisticException if a matching opencps voting statistic could not be found
	*/
	public static OpencpsVotingStatistic findByG_UID_Y_First(long groupId,
		long userId, int year,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsVotingStatisticException {
		return getPersistence()
				   .findByG_UID_Y_First(groupId, userId, year, orderByComparator);
	}

	/**
	* Returns the first opencps voting statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps voting statistic, or <code>null</code> if a matching opencps voting statistic could not be found
	*/
	public static OpencpsVotingStatistic fetchByG_UID_Y_First(long groupId,
		long userId, int year,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByG_UID_Y_First(groupId, userId, year,
			orderByComparator);
	}

	/**
	* Returns the last opencps voting statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps voting statistic
	* @throws NoSuchOpencpsVotingStatisticException if a matching opencps voting statistic could not be found
	*/
	public static OpencpsVotingStatistic findByG_UID_Y_Last(long groupId,
		long userId, int year,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsVotingStatisticException {
		return getPersistence()
				   .findByG_UID_Y_Last(groupId, userId, year, orderByComparator);
	}

	/**
	* Returns the last opencps voting statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps voting statistic, or <code>null</code> if a matching opencps voting statistic could not be found
	*/
	public static OpencpsVotingStatistic fetchByG_UID_Y_Last(long groupId,
		long userId, int year,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByG_UID_Y_Last(groupId, userId, year, orderByComparator);
	}

	/**
	* Returns the opencps voting statistics before and after the current opencps voting statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param votingStatisticId the primary key of the current opencps voting statistic
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps voting statistic
	* @throws NoSuchOpencpsVotingStatisticException if a opencps voting statistic with the primary key could not be found
	*/
	public static OpencpsVotingStatistic[] findByG_UID_Y_PrevAndNext(
		long votingStatisticId, long groupId, long userId, int year,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsVotingStatisticException {
		return getPersistence()
				   .findByG_UID_Y_PrevAndNext(votingStatisticId, groupId,
			userId, year, orderByComparator);
	}

	/**
	* Removes all the opencps voting statistics where groupId = &#63; and userId = &#63; and year = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	*/
	public static void removeByG_UID_Y(long groupId, long userId, int year) {
		getPersistence().removeByG_UID_Y(groupId, userId, year);
	}

	/**
	* Returns the number of opencps voting statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @return the number of matching opencps voting statistics
	*/
	public static int countByG_UID_Y(long groupId, long userId, int year) {
		return getPersistence().countByG_UID_Y(groupId, userId, year);
	}

	/**
	* Returns the opencps voting statistic where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; or throws a {@link NoSuchOpencpsVotingStatisticException} if it could not be found.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @return the matching opencps voting statistic
	* @throws NoSuchOpencpsVotingStatisticException if a matching opencps voting statistic could not be found
	*/
	public static OpencpsVotingStatistic findByM_Y_DM_G(long groupId,
		int month, int year, String govAgencyCode, String domainCode)
		throws org.opencps.statistic.exception.NoSuchOpencpsVotingStatisticException {
		return getPersistence()
				   .findByM_Y_DM_G(groupId, month, year, govAgencyCode,
			domainCode);
	}

	/**
	* Returns the opencps voting statistic where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @return the matching opencps voting statistic, or <code>null</code> if a matching opencps voting statistic could not be found
	*/
	public static OpencpsVotingStatistic fetchByM_Y_DM_G(long groupId,
		int month, int year, String govAgencyCode, String domainCode) {
		return getPersistence()
				   .fetchByM_Y_DM_G(groupId, month, year, govAgencyCode,
			domainCode);
	}

	/**
	* Returns the opencps voting statistic where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching opencps voting statistic, or <code>null</code> if a matching opencps voting statistic could not be found
	*/
	public static OpencpsVotingStatistic fetchByM_Y_DM_G(long groupId,
		int month, int year, String govAgencyCode, String domainCode,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByM_Y_DM_G(groupId, month, year, govAgencyCode,
			domainCode, retrieveFromCache);
	}

	/**
	* Removes the opencps voting statistic where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @return the opencps voting statistic that was removed
	*/
	public static OpencpsVotingStatistic removeByM_Y_DM_G(long groupId,
		int month, int year, String govAgencyCode, String domainCode)
		throws org.opencps.statistic.exception.NoSuchOpencpsVotingStatisticException {
		return getPersistence()
				   .removeByM_Y_DM_G(groupId, month, year, govAgencyCode,
			domainCode);
	}

	/**
	* Returns the number of opencps voting statistics where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @return the number of matching opencps voting statistics
	*/
	public static int countByM_Y_DM_G(long groupId, int month, int year,
		String govAgencyCode, String domainCode) {
		return getPersistence()
				   .countByM_Y_DM_G(groupId, month, year, govAgencyCode,
			domainCode);
	}

	/**
	* Returns all the opencps voting statistics where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param domainCode the domain code
	* @param month the month
	* @param year the year
	* @return the matching opencps voting statistics
	*/
	public static List<OpencpsVotingStatistic> findByG_D_M_Y(long groupId,
		String domainCode, int month, int year) {
		return getPersistence().findByG_D_M_Y(groupId, domainCode, month, year);
	}

	/**
	* Returns a range of all the opencps voting statistics where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param domainCode the domain code
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps voting statistics
	* @param end the upper bound of the range of opencps voting statistics (not inclusive)
	* @return the range of matching opencps voting statistics
	*/
	public static List<OpencpsVotingStatistic> findByG_D_M_Y(long groupId,
		String domainCode, int month, int year, int start, int end) {
		return getPersistence()
				   .findByG_D_M_Y(groupId, domainCode, month, year, start, end);
	}

	/**
	* Returns an ordered range of all the opencps voting statistics where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param domainCode the domain code
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps voting statistics
	* @param end the upper bound of the range of opencps voting statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps voting statistics
	*/
	public static List<OpencpsVotingStatistic> findByG_D_M_Y(long groupId,
		String domainCode, int month, int year, int start, int end,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator) {
		return getPersistence()
				   .findByG_D_M_Y(groupId, domainCode, month, year, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps voting statistics where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param domainCode the domain code
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps voting statistics
	* @param end the upper bound of the range of opencps voting statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps voting statistics
	*/
	public static List<OpencpsVotingStatistic> findByG_D_M_Y(long groupId,
		String domainCode, int month, int year, int start, int end,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_D_M_Y(groupId, domainCode, month, year, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first opencps voting statistic in the ordered set where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param domainCode the domain code
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps voting statistic
	* @throws NoSuchOpencpsVotingStatisticException if a matching opencps voting statistic could not be found
	*/
	public static OpencpsVotingStatistic findByG_D_M_Y_First(long groupId,
		String domainCode, int month, int year,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsVotingStatisticException {
		return getPersistence()
				   .findByG_D_M_Y_First(groupId, domainCode, month, year,
			orderByComparator);
	}

	/**
	* Returns the first opencps voting statistic in the ordered set where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param domainCode the domain code
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps voting statistic, or <code>null</code> if a matching opencps voting statistic could not be found
	*/
	public static OpencpsVotingStatistic fetchByG_D_M_Y_First(long groupId,
		String domainCode, int month, int year,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByG_D_M_Y_First(groupId, domainCode, month, year,
			orderByComparator);
	}

	/**
	* Returns the last opencps voting statistic in the ordered set where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param domainCode the domain code
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps voting statistic
	* @throws NoSuchOpencpsVotingStatisticException if a matching opencps voting statistic could not be found
	*/
	public static OpencpsVotingStatistic findByG_D_M_Y_Last(long groupId,
		String domainCode, int month, int year,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsVotingStatisticException {
		return getPersistence()
				   .findByG_D_M_Y_Last(groupId, domainCode, month, year,
			orderByComparator);
	}

	/**
	* Returns the last opencps voting statistic in the ordered set where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param domainCode the domain code
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps voting statistic, or <code>null</code> if a matching opencps voting statistic could not be found
	*/
	public static OpencpsVotingStatistic fetchByG_D_M_Y_Last(long groupId,
		String domainCode, int month, int year,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByG_D_M_Y_Last(groupId, domainCode, month, year,
			orderByComparator);
	}

	/**
	* Returns the opencps voting statistics before and after the current opencps voting statistic in the ordered set where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	*
	* @param votingStatisticId the primary key of the current opencps voting statistic
	* @param groupId the group ID
	* @param domainCode the domain code
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps voting statistic
	* @throws NoSuchOpencpsVotingStatisticException if a opencps voting statistic with the primary key could not be found
	*/
	public static OpencpsVotingStatistic[] findByG_D_M_Y_PrevAndNext(
		long votingStatisticId, long groupId, String domainCode, int month,
		int year, OrderByComparator<OpencpsVotingStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsVotingStatisticException {
		return getPersistence()
				   .findByG_D_M_Y_PrevAndNext(votingStatisticId, groupId,
			domainCode, month, year, orderByComparator);
	}

	/**
	* Removes all the opencps voting statistics where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63; from the database.
	*
	* @param groupId the group ID
	* @param domainCode the domain code
	* @param month the month
	* @param year the year
	*/
	public static void removeByG_D_M_Y(long groupId, String domainCode,
		int month, int year) {
		getPersistence().removeByG_D_M_Y(groupId, domainCode, month, year);
	}

	/**
	* Returns the number of opencps voting statistics where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param domainCode the domain code
	* @param month the month
	* @param year the year
	* @return the number of matching opencps voting statistics
	*/
	public static int countByG_D_M_Y(long groupId, String domainCode,
		int month, int year) {
		return getPersistence().countByG_D_M_Y(groupId, domainCode, month, year);
	}

	/**
	* Returns all the opencps voting statistics where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @return the matching opencps voting statistics
	*/
	public static List<OpencpsVotingStatistic> findByG_M_Y(long groupId,
		int month, int year) {
		return getPersistence().findByG_M_Y(groupId, month, year);
	}

	/**
	* Returns a range of all the opencps voting statistics where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps voting statistics
	* @param end the upper bound of the range of opencps voting statistics (not inclusive)
	* @return the range of matching opencps voting statistics
	*/
	public static List<OpencpsVotingStatistic> findByG_M_Y(long groupId,
		int month, int year, int start, int end) {
		return getPersistence().findByG_M_Y(groupId, month, year, start, end);
	}

	/**
	* Returns an ordered range of all the opencps voting statistics where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps voting statistics
	* @param end the upper bound of the range of opencps voting statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps voting statistics
	*/
	public static List<OpencpsVotingStatistic> findByG_M_Y(long groupId,
		int month, int year, int start, int end,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator) {
		return getPersistence()
				   .findByG_M_Y(groupId, month, year, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps voting statistics where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps voting statistics
	* @param end the upper bound of the range of opencps voting statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps voting statistics
	*/
	public static List<OpencpsVotingStatistic> findByG_M_Y(long groupId,
		int month, int year, int start, int end,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_M_Y(groupId, month, year, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first opencps voting statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps voting statistic
	* @throws NoSuchOpencpsVotingStatisticException if a matching opencps voting statistic could not be found
	*/
	public static OpencpsVotingStatistic findByG_M_Y_First(long groupId,
		int month, int year,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsVotingStatisticException {
		return getPersistence()
				   .findByG_M_Y_First(groupId, month, year, orderByComparator);
	}

	/**
	* Returns the first opencps voting statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps voting statistic, or <code>null</code> if a matching opencps voting statistic could not be found
	*/
	public static OpencpsVotingStatistic fetchByG_M_Y_First(long groupId,
		int month, int year,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByG_M_Y_First(groupId, month, year, orderByComparator);
	}

	/**
	* Returns the last opencps voting statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps voting statistic
	* @throws NoSuchOpencpsVotingStatisticException if a matching opencps voting statistic could not be found
	*/
	public static OpencpsVotingStatistic findByG_M_Y_Last(long groupId,
		int month, int year,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsVotingStatisticException {
		return getPersistence()
				   .findByG_M_Y_Last(groupId, month, year, orderByComparator);
	}

	/**
	* Returns the last opencps voting statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps voting statistic, or <code>null</code> if a matching opencps voting statistic could not be found
	*/
	public static OpencpsVotingStatistic fetchByG_M_Y_Last(long groupId,
		int month, int year,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByG_M_Y_Last(groupId, month, year, orderByComparator);
	}

	/**
	* Returns the opencps voting statistics before and after the current opencps voting statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param votingStatisticId the primary key of the current opencps voting statistic
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps voting statistic
	* @throws NoSuchOpencpsVotingStatisticException if a opencps voting statistic with the primary key could not be found
	*/
	public static OpencpsVotingStatistic[] findByG_M_Y_PrevAndNext(
		long votingStatisticId, long groupId, int month, int year,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsVotingStatisticException {
		return getPersistence()
				   .findByG_M_Y_PrevAndNext(votingStatisticId, groupId, month,
			year, orderByComparator);
	}

	/**
	* Removes all the opencps voting statistics where groupId = &#63; and month = &#63; and year = &#63; from the database.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	*/
	public static void removeByG_M_Y(long groupId, int month, int year) {
		getPersistence().removeByG_M_Y(groupId, month, year);
	}

	/**
	* Returns the number of opencps voting statistics where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @return the number of matching opencps voting statistics
	*/
	public static int countByG_M_Y(long groupId, int month, int year) {
		return getPersistence().countByG_M_Y(groupId, month, year);
	}

	/**
	* Returns all the opencps voting statistics where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @return the matching opencps voting statistics
	*/
	public static List<OpencpsVotingStatistic> findByCID_GID_Y(long companyId,
		long groupId, int month, int year) {
		return getPersistence().findByCID_GID_Y(companyId, groupId, month, year);
	}

	/**
	* Returns a range of all the opencps voting statistics where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps voting statistics
	* @param end the upper bound of the range of opencps voting statistics (not inclusive)
	* @return the range of matching opencps voting statistics
	*/
	public static List<OpencpsVotingStatistic> findByCID_GID_Y(long companyId,
		long groupId, int month, int year, int start, int end) {
		return getPersistence()
				   .findByCID_GID_Y(companyId, groupId, month, year, start, end);
	}

	/**
	* Returns an ordered range of all the opencps voting statistics where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps voting statistics
	* @param end the upper bound of the range of opencps voting statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps voting statistics
	*/
	public static List<OpencpsVotingStatistic> findByCID_GID_Y(long companyId,
		long groupId, int month, int year, int start, int end,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator) {
		return getPersistence()
				   .findByCID_GID_Y(companyId, groupId, month, year, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps voting statistics where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps voting statistics
	* @param end the upper bound of the range of opencps voting statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps voting statistics
	*/
	public static List<OpencpsVotingStatistic> findByCID_GID_Y(long companyId,
		long groupId, int month, int year, int start, int end,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCID_GID_Y(companyId, groupId, month, year, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first opencps voting statistic in the ordered set where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps voting statistic
	* @throws NoSuchOpencpsVotingStatisticException if a matching opencps voting statistic could not be found
	*/
	public static OpencpsVotingStatistic findByCID_GID_Y_First(long companyId,
		long groupId, int month, int year,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsVotingStatisticException {
		return getPersistence()
				   .findByCID_GID_Y_First(companyId, groupId, month, year,
			orderByComparator);
	}

	/**
	* Returns the first opencps voting statistic in the ordered set where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps voting statistic, or <code>null</code> if a matching opencps voting statistic could not be found
	*/
	public static OpencpsVotingStatistic fetchByCID_GID_Y_First(
		long companyId, long groupId, int month, int year,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByCID_GID_Y_First(companyId, groupId, month, year,
			orderByComparator);
	}

	/**
	* Returns the last opencps voting statistic in the ordered set where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps voting statistic
	* @throws NoSuchOpencpsVotingStatisticException if a matching opencps voting statistic could not be found
	*/
	public static OpencpsVotingStatistic findByCID_GID_Y_Last(long companyId,
		long groupId, int month, int year,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsVotingStatisticException {
		return getPersistence()
				   .findByCID_GID_Y_Last(companyId, groupId, month, year,
			orderByComparator);
	}

	/**
	* Returns the last opencps voting statistic in the ordered set where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps voting statistic, or <code>null</code> if a matching opencps voting statistic could not be found
	*/
	public static OpencpsVotingStatistic fetchByCID_GID_Y_Last(long companyId,
		long groupId, int month, int year,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByCID_GID_Y_Last(companyId, groupId, month, year,
			orderByComparator);
	}

	/**
	* Returns the opencps voting statistics before and after the current opencps voting statistic in the ordered set where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param votingStatisticId the primary key of the current opencps voting statistic
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps voting statistic
	* @throws NoSuchOpencpsVotingStatisticException if a opencps voting statistic with the primary key could not be found
	*/
	public static OpencpsVotingStatistic[] findByCID_GID_Y_PrevAndNext(
		long votingStatisticId, long companyId, long groupId, int month,
		int year, OrderByComparator<OpencpsVotingStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsVotingStatisticException {
		return getPersistence()
				   .findByCID_GID_Y_PrevAndNext(votingStatisticId, companyId,
			groupId, month, year, orderByComparator);
	}

	/**
	* Removes all the opencps voting statistics where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63; from the database.
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
	* Returns the number of opencps voting statistics where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @return the number of matching opencps voting statistics
	*/
	public static int countByCID_GID_Y(long companyId, long groupId, int month,
		int year) {
		return getPersistence().countByCID_GID_Y(companyId, groupId, month, year);
	}

	/**
	* Caches the opencps voting statistic in the entity cache if it is enabled.
	*
	* @param opencpsVotingStatistic the opencps voting statistic
	*/
	public static void cacheResult(
		OpencpsVotingStatistic opencpsVotingStatistic) {
		getPersistence().cacheResult(opencpsVotingStatistic);
	}

	/**
	* Caches the opencps voting statistics in the entity cache if it is enabled.
	*
	* @param opencpsVotingStatistics the opencps voting statistics
	*/
	public static void cacheResult(
		List<OpencpsVotingStatistic> opencpsVotingStatistics) {
		getPersistence().cacheResult(opencpsVotingStatistics);
	}

	/**
	* Creates a new opencps voting statistic with the primary key. Does not add the opencps voting statistic to the database.
	*
	* @param votingStatisticId the primary key for the new opencps voting statistic
	* @return the new opencps voting statistic
	*/
	public static OpencpsVotingStatistic create(long votingStatisticId) {
		return getPersistence().create(votingStatisticId);
	}

	/**
	* Removes the opencps voting statistic with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param votingStatisticId the primary key of the opencps voting statistic
	* @return the opencps voting statistic that was removed
	* @throws NoSuchOpencpsVotingStatisticException if a opencps voting statistic with the primary key could not be found
	*/
	public static OpencpsVotingStatistic remove(long votingStatisticId)
		throws org.opencps.statistic.exception.NoSuchOpencpsVotingStatisticException {
		return getPersistence().remove(votingStatisticId);
	}

	public static OpencpsVotingStatistic updateImpl(
		OpencpsVotingStatistic opencpsVotingStatistic) {
		return getPersistence().updateImpl(opencpsVotingStatistic);
	}

	/**
	* Returns the opencps voting statistic with the primary key or throws a {@link NoSuchOpencpsVotingStatisticException} if it could not be found.
	*
	* @param votingStatisticId the primary key of the opencps voting statistic
	* @return the opencps voting statistic
	* @throws NoSuchOpencpsVotingStatisticException if a opencps voting statistic with the primary key could not be found
	*/
	public static OpencpsVotingStatistic findByPrimaryKey(
		long votingStatisticId)
		throws org.opencps.statistic.exception.NoSuchOpencpsVotingStatisticException {
		return getPersistence().findByPrimaryKey(votingStatisticId);
	}

	/**
	* Returns the opencps voting statistic with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param votingStatisticId the primary key of the opencps voting statistic
	* @return the opencps voting statistic, or <code>null</code> if a opencps voting statistic with the primary key could not be found
	*/
	public static OpencpsVotingStatistic fetchByPrimaryKey(
		long votingStatisticId) {
		return getPersistence().fetchByPrimaryKey(votingStatisticId);
	}

	public static java.util.Map<java.io.Serializable, OpencpsVotingStatistic> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the opencps voting statistics.
	*
	* @return the opencps voting statistics
	*/
	public static List<OpencpsVotingStatistic> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the opencps voting statistics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of opencps voting statistics
	* @param end the upper bound of the range of opencps voting statistics (not inclusive)
	* @return the range of opencps voting statistics
	*/
	public static List<OpencpsVotingStatistic> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the opencps voting statistics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of opencps voting statistics
	* @param end the upper bound of the range of opencps voting statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of opencps voting statistics
	*/
	public static List<OpencpsVotingStatistic> findAll(int start, int end,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps voting statistics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of opencps voting statistics
	* @param end the upper bound of the range of opencps voting statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of opencps voting statistics
	*/
	public static List<OpencpsVotingStatistic> findAll(int start, int end,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the opencps voting statistics from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of opencps voting statistics.
	*
	* @return the number of opencps voting statistics
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static OpencpsVotingStatisticPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<OpencpsVotingStatisticPersistence, OpencpsVotingStatisticPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(OpencpsVotingStatisticPersistence.class);

		ServiceTracker<OpencpsVotingStatisticPersistence, OpencpsVotingStatisticPersistence> serviceTracker =
			new ServiceTracker<OpencpsVotingStatisticPersistence, OpencpsVotingStatisticPersistence>(bundle.getBundleContext(),
				OpencpsVotingStatisticPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}