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

import org.opencps.statistic.model.OpencpsDossierStatistic;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the opencps dossier statistic service. This utility wraps {@link org.opencps.statistic.service.persistence.impl.OpencpsDossierStatisticPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see OpencpsDossierStatisticPersistence
 * @see org.opencps.statistic.service.persistence.impl.OpencpsDossierStatisticPersistenceImpl
 * @generated
 */
@ProviderType
public class OpencpsDossierStatisticUtil {
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
		OpencpsDossierStatistic opencpsDossierStatistic) {
		getPersistence().clearCache(opencpsDossierStatistic);
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
	public static List<OpencpsDossierStatistic> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<OpencpsDossierStatistic> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<OpencpsDossierStatistic> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static OpencpsDossierStatistic update(
		OpencpsDossierStatistic opencpsDossierStatistic) {
		return getPersistence().update(opencpsDossierStatistic);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static OpencpsDossierStatistic update(
		OpencpsDossierStatistic opencpsDossierStatistic,
		ServiceContext serviceContext) {
		return getPersistence().update(opencpsDossierStatistic, serviceContext);
	}

	/**
	* Returns all the opencps dossier statistics where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the opencps dossier statistics where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @return the range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByUuid(String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByUuid(String uuid,
		int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByUuid(String uuid,
		int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first opencps dossier statistic in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic findByUuid_First(String uuid,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first opencps dossier statistic in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByUuid_First(String uuid,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic findByUuid_Last(String uuid,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByUuid_Last(String uuid,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the opencps dossier statistics before and after the current opencps dossier statistic in the ordered set where uuid = &#63;.
	*
	* @param dossierStatisticId the primary key of the current opencps dossier statistic
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a opencps dossier statistic with the primary key could not be found
	*/
	public static OpencpsDossierStatistic[] findByUuid_PrevAndNext(
		long dossierStatisticId, String uuid,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByUuid_PrevAndNext(dossierStatisticId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the opencps dossier statistics where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of opencps dossier statistics where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching opencps dossier statistics
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the opencps dossier statistic where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchOpencpsDossierStatisticException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic findByUUID_G(String uuid, long groupId)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the opencps dossier statistic where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByUUID_G(String uuid,
		long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the opencps dossier statistic where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByUUID_G(String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the opencps dossier statistic where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the opencps dossier statistic that was removed
	*/
	public static OpencpsDossierStatistic removeByUUID_G(String uuid,
		long groupId)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of opencps dossier statistics where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching opencps dossier statistics
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the opencps dossier statistics where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByUuid_C(String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the opencps dossier statistics where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @return the range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first opencps dossier statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic findByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first opencps dossier statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic findByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the opencps dossier statistics before and after the current opencps dossier statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param dossierStatisticId the primary key of the current opencps dossier statistic
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a opencps dossier statistic with the primary key could not be found
	*/
	public static OpencpsDossierStatistic[] findByUuid_C_PrevAndNext(
		long dossierStatisticId, String uuid, long companyId,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(dossierStatisticId, uuid,
			companyId, orderByComparator);
	}

	/**
	* Removes all the opencps dossier statistics where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of opencps dossier statistics where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching opencps dossier statistics
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the opencps dossier statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @return the matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByG_UID_Y(long groupId,
		long userId, int year) {
		return getPersistence().findByG_UID_Y(groupId, userId, year);
	}

	/**
	* Returns a range of all the opencps dossier statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @return the range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByG_UID_Y(long groupId,
		long userId, int year, int start, int end) {
		return getPersistence().findByG_UID_Y(groupId, userId, year, start, end);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByG_UID_Y(long groupId,
		long userId, int year, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .findByG_UID_Y(groupId, userId, year, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByG_UID_Y(long groupId,
		long userId, int year, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_UID_Y(groupId, userId, year, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first opencps dossier statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic findByG_UID_Y_First(long groupId,
		long userId, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByG_UID_Y_First(groupId, userId, year, orderByComparator);
	}

	/**
	* Returns the first opencps dossier statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByG_UID_Y_First(long groupId,
		long userId, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByG_UID_Y_First(groupId, userId, year,
			orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic findByG_UID_Y_Last(long groupId,
		long userId, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByG_UID_Y_Last(groupId, userId, year, orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByG_UID_Y_Last(long groupId,
		long userId, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByG_UID_Y_Last(groupId, userId, year, orderByComparator);
	}

	/**
	* Returns the opencps dossier statistics before and after the current opencps dossier statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param dossierStatisticId the primary key of the current opencps dossier statistic
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a opencps dossier statistic with the primary key could not be found
	*/
	public static OpencpsDossierStatistic[] findByG_UID_Y_PrevAndNext(
		long dossierStatisticId, long groupId, long userId, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByG_UID_Y_PrevAndNext(dossierStatisticId, groupId,
			userId, year, orderByComparator);
	}

	/**
	* Removes all the opencps dossier statistics where groupId = &#63; and userId = &#63; and year = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	*/
	public static void removeByG_UID_Y(long groupId, long userId, int year) {
		getPersistence().removeByG_UID_Y(groupId, userId, year);
	}

	/**
	* Returns the number of opencps dossier statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @return the number of matching opencps dossier statistics
	*/
	public static int countByG_UID_Y(long groupId, long userId, int year) {
		return getPersistence().countByG_UID_Y(groupId, userId, year);
	}

	/**
	* Returns the opencps dossier statistic where groupId = &#63; and govAgencyCode = &#63; and month = &#63; and year = &#63; and domainCode = &#63; and reporting = &#63; or throws a {@link NoSuchOpencpsDossierStatisticException} if it could not be found.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param month the month
	* @param year the year
	* @param domainCode the domain code
	* @param reporting the reporting
	* @return the matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic findByM_Y_DM_G(long groupId,
		String govAgencyCode, int month, int year, String domainCode,
		boolean reporting)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByM_Y_DM_G(groupId, govAgencyCode, month, year,
			domainCode, reporting);
	}

	/**
	* Returns the opencps dossier statistic where groupId = &#63; and govAgencyCode = &#63; and month = &#63; and year = &#63; and domainCode = &#63; and reporting = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param month the month
	* @param year the year
	* @param domainCode the domain code
	* @param reporting the reporting
	* @return the matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByM_Y_DM_G(long groupId,
		String govAgencyCode, int month, int year, String domainCode,
		boolean reporting) {
		return getPersistence()
				   .fetchByM_Y_DM_G(groupId, govAgencyCode, month, year,
			domainCode, reporting);
	}

	/**
	* Returns the opencps dossier statistic where groupId = &#63; and govAgencyCode = &#63; and month = &#63; and year = &#63; and domainCode = &#63; and reporting = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param month the month
	* @param year the year
	* @param domainCode the domain code
	* @param reporting the reporting
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByM_Y_DM_G(long groupId,
		String govAgencyCode, int month, int year, String domainCode,
		boolean reporting, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByM_Y_DM_G(groupId, govAgencyCode, month, year,
			domainCode, reporting, retrieveFromCache);
	}

	/**
	* Removes the opencps dossier statistic where groupId = &#63; and govAgencyCode = &#63; and month = &#63; and year = &#63; and domainCode = &#63; and reporting = &#63; from the database.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param month the month
	* @param year the year
	* @param domainCode the domain code
	* @param reporting the reporting
	* @return the opencps dossier statistic that was removed
	*/
	public static OpencpsDossierStatistic removeByM_Y_DM_G(long groupId,
		String govAgencyCode, int month, int year, String domainCode,
		boolean reporting)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .removeByM_Y_DM_G(groupId, govAgencyCode, month, year,
			domainCode, reporting);
	}

	/**
	* Returns the number of opencps dossier statistics where groupId = &#63; and govAgencyCode = &#63; and month = &#63; and year = &#63; and domainCode = &#63; and reporting = &#63;.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param month the month
	* @param year the year
	* @param domainCode the domain code
	* @param reporting the reporting
	* @return the number of matching opencps dossier statistics
	*/
	public static int countByM_Y_DM_G(long groupId, String govAgencyCode,
		int month, int year, String domainCode, boolean reporting) {
		return getPersistence()
				   .countByM_Y_DM_G(groupId, govAgencyCode, month, year,
			domainCode, reporting);
	}

	/**
	* Returns the opencps dossier statistic where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; or throws a {@link NoSuchOpencpsDossierStatisticException} if it could not be found.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @return the matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic findByG_M_Y_G_D(long groupId,
		int month, int year, String govAgencyCode, String domainCode)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByG_M_Y_G_D(groupId, month, year, govAgencyCode,
			domainCode);
	}

	/**
	* Returns the opencps dossier statistic where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @return the matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByG_M_Y_G_D(long groupId,
		int month, int year, String govAgencyCode, String domainCode) {
		return getPersistence()
				   .fetchByG_M_Y_G_D(groupId, month, year, govAgencyCode,
			domainCode);
	}

	/**
	* Returns the opencps dossier statistic where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByG_M_Y_G_D(long groupId,
		int month, int year, String govAgencyCode, String domainCode,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByG_M_Y_G_D(groupId, month, year, govAgencyCode,
			domainCode, retrieveFromCache);
	}

	/**
	* Removes the opencps dossier statistic where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @return the opencps dossier statistic that was removed
	*/
	public static OpencpsDossierStatistic removeByG_M_Y_G_D(long groupId,
		int month, int year, String govAgencyCode, String domainCode)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .removeByG_M_Y_G_D(groupId, month, year, govAgencyCode,
			domainCode);
	}

	/**
	* Returns the number of opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @return the number of matching opencps dossier statistics
	*/
	public static int countByG_M_Y_G_D(long groupId, int month, int year,
		String govAgencyCode, String domainCode) {
		return getPersistence()
				   .countByG_M_Y_G_D(groupId, month, year, govAgencyCode,
			domainCode);
	}

	/**
	* Returns the opencps dossier statistic where groupId = &#63; and govAgencyCode = &#63; and month = &#63; and year = &#63; or throws a {@link NoSuchOpencpsDossierStatisticException} if it could not be found.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param month the month
	* @param year the year
	* @return the matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic findByM_Y_G(long groupId,
		String govAgencyCode, int month, int year)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence().findByM_Y_G(groupId, govAgencyCode, month, year);
	}

	/**
	* Returns the opencps dossier statistic where groupId = &#63; and govAgencyCode = &#63; and month = &#63; and year = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param month the month
	* @param year the year
	* @return the matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByM_Y_G(long groupId,
		String govAgencyCode, int month, int year) {
		return getPersistence().fetchByM_Y_G(groupId, govAgencyCode, month, year);
	}

	/**
	* Returns the opencps dossier statistic where groupId = &#63; and govAgencyCode = &#63; and month = &#63; and year = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param month the month
	* @param year the year
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByM_Y_G(long groupId,
		String govAgencyCode, int month, int year, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByM_Y_G(groupId, govAgencyCode, month, year,
			retrieveFromCache);
	}

	/**
	* Removes the opencps dossier statistic where groupId = &#63; and govAgencyCode = &#63; and month = &#63; and year = &#63; from the database.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param month the month
	* @param year the year
	* @return the opencps dossier statistic that was removed
	*/
	public static OpencpsDossierStatistic removeByM_Y_G(long groupId,
		String govAgencyCode, int month, int year)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .removeByM_Y_G(groupId, govAgencyCode, month, year);
	}

	/**
	* Returns the number of opencps dossier statistics where groupId = &#63; and govAgencyCode = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param month the month
	* @param year the year
	* @return the number of matching opencps dossier statistics
	*/
	public static int countByM_Y_G(long groupId, String govAgencyCode,
		int month, int year) {
		return getPersistence().countByM_Y_G(groupId, govAgencyCode, month, year);
	}

	/**
	* Returns all the opencps dossier statistics where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param domainCode the domain code
	* @param month the month
	* @param year the year
	* @return the matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByG_D_M_Y(long groupId,
		String domainCode, int month, int year) {
		return getPersistence().findByG_D_M_Y(groupId, domainCode, month, year);
	}

	/**
	* Returns a range of all the opencps dossier statistics where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param domainCode the domain code
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @return the range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByG_D_M_Y(long groupId,
		String domainCode, int month, int year, int start, int end) {
		return getPersistence()
				   .findByG_D_M_Y(groupId, domainCode, month, year, start, end);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param domainCode the domain code
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByG_D_M_Y(long groupId,
		String domainCode, int month, int year, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .findByG_D_M_Y(groupId, domainCode, month, year, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param domainCode the domain code
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByG_D_M_Y(long groupId,
		String domainCode, int month, int year, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_D_M_Y(groupId, domainCode, month, year, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first opencps dossier statistic in the ordered set where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param domainCode the domain code
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic findByG_D_M_Y_First(long groupId,
		String domainCode, int month, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByG_D_M_Y_First(groupId, domainCode, month, year,
			orderByComparator);
	}

	/**
	* Returns the first opencps dossier statistic in the ordered set where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param domainCode the domain code
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByG_D_M_Y_First(long groupId,
		String domainCode, int month, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByG_D_M_Y_First(groupId, domainCode, month, year,
			orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic in the ordered set where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param domainCode the domain code
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic findByG_D_M_Y_Last(long groupId,
		String domainCode, int month, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByG_D_M_Y_Last(groupId, domainCode, month, year,
			orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic in the ordered set where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param domainCode the domain code
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByG_D_M_Y_Last(long groupId,
		String domainCode, int month, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByG_D_M_Y_Last(groupId, domainCode, month, year,
			orderByComparator);
	}

	/**
	* Returns the opencps dossier statistics before and after the current opencps dossier statistic in the ordered set where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	*
	* @param dossierStatisticId the primary key of the current opencps dossier statistic
	* @param groupId the group ID
	* @param domainCode the domain code
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a opencps dossier statistic with the primary key could not be found
	*/
	public static OpencpsDossierStatistic[] findByG_D_M_Y_PrevAndNext(
		long dossierStatisticId, long groupId, String domainCode, int month,
		int year, OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByG_D_M_Y_PrevAndNext(dossierStatisticId, groupId,
			domainCode, month, year, orderByComparator);
	}

	/**
	* Removes all the opencps dossier statistics where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63; from the database.
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
	* Returns the number of opencps dossier statistics where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param domainCode the domain code
	* @param month the month
	* @param year the year
	* @return the number of matching opencps dossier statistics
	*/
	public static int countByG_D_M_Y(long groupId, String domainCode,
		int month, int year) {
		return getPersistence().countByG_D_M_Y(groupId, domainCode, month, year);
	}

	/**
	* Returns all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @return the matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByG_M_Y(long groupId,
		int month, int year) {
		return getPersistence().findByG_M_Y(groupId, month, year);
	}

	/**
	* Returns a range of all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @return the range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByG_M_Y(long groupId,
		int month, int year, int start, int end) {
		return getPersistence().findByG_M_Y(groupId, month, year, start, end);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByG_M_Y(long groupId,
		int month, int year, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .findByG_M_Y(groupId, month, year, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByG_M_Y(long groupId,
		int month, int year, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_M_Y(groupId, month, year, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic findByG_M_Y_First(long groupId,
		int month, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByG_M_Y_First(groupId, month, year, orderByComparator);
	}

	/**
	* Returns the first opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByG_M_Y_First(long groupId,
		int month, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByG_M_Y_First(groupId, month, year, orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic findByG_M_Y_Last(long groupId,
		int month, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByG_M_Y_Last(groupId, month, year, orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByG_M_Y_Last(long groupId,
		int month, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByG_M_Y_Last(groupId, month, year, orderByComparator);
	}

	/**
	* Returns the opencps dossier statistics before and after the current opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param dossierStatisticId the primary key of the current opencps dossier statistic
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a opencps dossier statistic with the primary key could not be found
	*/
	public static OpencpsDossierStatistic[] findByG_M_Y_PrevAndNext(
		long dossierStatisticId, long groupId, int month, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByG_M_Y_PrevAndNext(dossierStatisticId, groupId, month,
			year, orderByComparator);
	}

	/**
	* Removes all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; from the database.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	*/
	public static void removeByG_M_Y(long groupId, int month, int year) {
		getPersistence().removeByG_M_Y(groupId, month, year);
	}

	/**
	* Returns the number of opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @return the number of matching opencps dossier statistics
	*/
	public static int countByG_M_Y(long groupId, int month, int year) {
		return getPersistence().countByG_M_Y(groupId, month, year);
	}

	/**
	* Returns all the opencps dossier statistics where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @return the matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByCID_GID_Y(
		long companyId, long groupId, int month, int year) {
		return getPersistence().findByCID_GID_Y(companyId, groupId, month, year);
	}

	/**
	* Returns a range of all the opencps dossier statistics where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @return the range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByCID_GID_Y(
		long companyId, long groupId, int month, int year, int start, int end) {
		return getPersistence()
				   .findByCID_GID_Y(companyId, groupId, month, year, start, end);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByCID_GID_Y(
		long companyId, long groupId, int month, int year, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .findByCID_GID_Y(companyId, groupId, month, year, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByCID_GID_Y(
		long companyId, long groupId, int month, int year, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCID_GID_Y(companyId, groupId, month, year, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first opencps dossier statistic in the ordered set where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic findByCID_GID_Y_First(
		long companyId, long groupId, int month, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByCID_GID_Y_First(companyId, groupId, month, year,
			orderByComparator);
	}

	/**
	* Returns the first opencps dossier statistic in the ordered set where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByCID_GID_Y_First(
		long companyId, long groupId, int month, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByCID_GID_Y_First(companyId, groupId, month, year,
			orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic in the ordered set where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic findByCID_GID_Y_Last(long companyId,
		long groupId, int month, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByCID_GID_Y_Last(companyId, groupId, month, year,
			orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic in the ordered set where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByCID_GID_Y_Last(
		long companyId, long groupId, int month, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByCID_GID_Y_Last(companyId, groupId, month, year,
			orderByComparator);
	}

	/**
	* Returns the opencps dossier statistics before and after the current opencps dossier statistic in the ordered set where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param dossierStatisticId the primary key of the current opencps dossier statistic
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a opencps dossier statistic with the primary key could not be found
	*/
	public static OpencpsDossierStatistic[] findByCID_GID_Y_PrevAndNext(
		long dossierStatisticId, long companyId, long groupId, int month,
		int year, OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByCID_GID_Y_PrevAndNext(dossierStatisticId, companyId,
			groupId, month, year, orderByComparator);
	}

	/**
	* Removes all the opencps dossier statistics where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63; from the database.
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
	* Returns the number of opencps dossier statistics where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @return the number of matching opencps dossier statistics
	*/
	public static int countByCID_GID_Y(long companyId, long groupId, int month,
		int year) {
		return getPersistence().countByCID_GID_Y(companyId, groupId, month, year);
	}

	/**
	* Returns all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param reporting the reporting
	* @return the matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByGID_M_Y_RP(long groupId,
		int month, int year, boolean reporting) {
		return getPersistence().findByGID_M_Y_RP(groupId, month, year, reporting);
	}

	/**
	* Returns a range of all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param reporting the reporting
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @return the range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByGID_M_Y_RP(long groupId,
		int month, int year, boolean reporting, int start, int end) {
		return getPersistence()
				   .findByGID_M_Y_RP(groupId, month, year, reporting, start, end);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param reporting the reporting
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByGID_M_Y_RP(long groupId,
		int month, int year, boolean reporting, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .findByGID_M_Y_RP(groupId, month, year, reporting, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param reporting the reporting
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByGID_M_Y_RP(long groupId,
		int month, int year, boolean reporting, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGID_M_Y_RP(groupId, month, year, reporting, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param reporting the reporting
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic findByGID_M_Y_RP_First(long groupId,
		int month, int year, boolean reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByGID_M_Y_RP_First(groupId, month, year, reporting,
			orderByComparator);
	}

	/**
	* Returns the first opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param reporting the reporting
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByGID_M_Y_RP_First(
		long groupId, int month, int year, boolean reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByGID_M_Y_RP_First(groupId, month, year, reporting,
			orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param reporting the reporting
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic findByGID_M_Y_RP_Last(long groupId,
		int month, int year, boolean reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByGID_M_Y_RP_Last(groupId, month, year, reporting,
			orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param reporting the reporting
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByGID_M_Y_RP_Last(long groupId,
		int month, int year, boolean reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByGID_M_Y_RP_Last(groupId, month, year, reporting,
			orderByComparator);
	}

	/**
	* Returns the opencps dossier statistics before and after the current opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	*
	* @param dossierStatisticId the primary key of the current opencps dossier statistic
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param reporting the reporting
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a opencps dossier statistic with the primary key could not be found
	*/
	public static OpencpsDossierStatistic[] findByGID_M_Y_RP_PrevAndNext(
		long dossierStatisticId, long groupId, int month, int year,
		boolean reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByGID_M_Y_RP_PrevAndNext(dossierStatisticId, groupId,
			month, year, reporting, orderByComparator);
	}

	/**
	* Removes all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63; from the database.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param reporting the reporting
	*/
	public static void removeByGID_M_Y_RP(long groupId, int month, int year,
		boolean reporting) {
		getPersistence().removeByGID_M_Y_RP(groupId, month, year, reporting);
	}

	/**
	* Returns the number of opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param reporting the reporting
	* @return the number of matching opencps dossier statistics
	*/
	public static int countByGID_M_Y_RP(long groupId, int month, int year,
		boolean reporting) {
		return getPersistence()
				   .countByGID_M_Y_RP(groupId, month, year, reporting);
	}

	/**
	* Returns all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param reporting the reporting
	* @return the matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByGID_MS_Y_RP(
		long groupId, int month, int year, boolean reporting) {
		return getPersistence()
				   .findByGID_MS_Y_RP(groupId, month, year, reporting);
	}

	/**
	* Returns a range of all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param reporting the reporting
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @return the range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByGID_MS_Y_RP(
		long groupId, int month, int year, boolean reporting, int start, int end) {
		return getPersistence()
				   .findByGID_MS_Y_RP(groupId, month, year, reporting, start,
			end);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param reporting the reporting
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByGID_MS_Y_RP(
		long groupId, int month, int year, boolean reporting, int start,
		int end, OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .findByGID_MS_Y_RP(groupId, month, year, reporting, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param reporting the reporting
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByGID_MS_Y_RP(
		long groupId, int month, int year, boolean reporting, int start,
		int end, OrderByComparator<OpencpsDossierStatistic> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGID_MS_Y_RP(groupId, month, year, reporting, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param reporting the reporting
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic findByGID_MS_Y_RP_First(
		long groupId, int month, int year, boolean reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByGID_MS_Y_RP_First(groupId, month, year, reporting,
			orderByComparator);
	}

	/**
	* Returns the first opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param reporting the reporting
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByGID_MS_Y_RP_First(
		long groupId, int month, int year, boolean reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByGID_MS_Y_RP_First(groupId, month, year, reporting,
			orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param reporting the reporting
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic findByGID_MS_Y_RP_Last(long groupId,
		int month, int year, boolean reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByGID_MS_Y_RP_Last(groupId, month, year, reporting,
			orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param reporting the reporting
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByGID_MS_Y_RP_Last(
		long groupId, int month, int year, boolean reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByGID_MS_Y_RP_Last(groupId, month, year, reporting,
			orderByComparator);
	}

	/**
	* Returns the opencps dossier statistics before and after the current opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	*
	* @param dossierStatisticId the primary key of the current opencps dossier statistic
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param reporting the reporting
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a opencps dossier statistic with the primary key could not be found
	*/
	public static OpencpsDossierStatistic[] findByGID_MS_Y_RP_PrevAndNext(
		long dossierStatisticId, long groupId, int month, int year,
		boolean reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByGID_MS_Y_RP_PrevAndNext(dossierStatisticId, groupId,
			month, year, reporting, orderByComparator);
	}

	/**
	* Returns all the opencps dossier statistics where groupId = &#63; and month = any &#63; and year = &#63; and reporting = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param months the months
	* @param year the year
	* @param reporting the reporting
	* @return the matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByGID_MS_Y_RP(
		long groupId, int[] months, int year, boolean reporting) {
		return getPersistence()
				   .findByGID_MS_Y_RP(groupId, months, year, reporting);
	}

	/**
	* Returns a range of all the opencps dossier statistics where groupId = &#63; and month = any &#63; and year = &#63; and reporting = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param months the months
	* @param year the year
	* @param reporting the reporting
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @return the range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByGID_MS_Y_RP(
		long groupId, int[] months, int year, boolean reporting, int start,
		int end) {
		return getPersistence()
				   .findByGID_MS_Y_RP(groupId, months, year, reporting, start,
			end);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where groupId = &#63; and month = any &#63; and year = &#63; and reporting = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param months the months
	* @param year the year
	* @param reporting the reporting
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByGID_MS_Y_RP(
		long groupId, int[] months, int year, boolean reporting, int start,
		int end, OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .findByGID_MS_Y_RP(groupId, months, year, reporting, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param reporting the reporting
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByGID_MS_Y_RP(
		long groupId, int[] months, int year, boolean reporting, int start,
		int end, OrderByComparator<OpencpsDossierStatistic> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGID_MS_Y_RP(groupId, months, year, reporting, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63; from the database.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param reporting the reporting
	*/
	public static void removeByGID_MS_Y_RP(long groupId, int month, int year,
		boolean reporting) {
		getPersistence().removeByGID_MS_Y_RP(groupId, month, year, reporting);
	}

	/**
	* Returns the number of opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param reporting the reporting
	* @return the number of matching opencps dossier statistics
	*/
	public static int countByGID_MS_Y_RP(long groupId, int month, int year,
		boolean reporting) {
		return getPersistence()
				   .countByGID_MS_Y_RP(groupId, month, year, reporting);
	}

	/**
	* Returns the number of opencps dossier statistics where groupId = &#63; and month = any &#63; and year = &#63; and reporting = &#63;.
	*
	* @param groupId the group ID
	* @param months the months
	* @param year the year
	* @param reporting the reporting
	* @return the number of matching opencps dossier statistics
	*/
	public static int countByGID_MS_Y_RP(long groupId, int[] months, int year,
		boolean reporting) {
		return getPersistence()
				   .countByGID_MS_Y_RP(groupId, months, year, reporting);
	}

	/**
	* Returns all the opencps dossier statistics where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByG(long groupId) {
		return getPersistence().findByG(groupId);
	}

	/**
	* Returns a range of all the opencps dossier statistics where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @return the range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByG(long groupId,
		int start, int end) {
		return getPersistence().findByG(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByG(long groupId,
		int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence().findByG(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByG(long groupId,
		int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first opencps dossier statistic in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic findByG_First(long groupId,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence().findByG_First(groupId, orderByComparator);
	}

	/**
	* Returns the first opencps dossier statistic in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByG_First(long groupId,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence().fetchByG_First(groupId, orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic findByG_Last(long groupId,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence().findByG_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByG_Last(long groupId,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence().fetchByG_Last(groupId, orderByComparator);
	}

	/**
	* Returns the opencps dossier statistics before and after the current opencps dossier statistic in the ordered set where groupId = &#63;.
	*
	* @param dossierStatisticId the primary key of the current opencps dossier statistic
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a opencps dossier statistic with the primary key could not be found
	*/
	public static OpencpsDossierStatistic[] findByG_PrevAndNext(
		long dossierStatisticId, long groupId,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByG_PrevAndNext(dossierStatisticId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the opencps dossier statistics where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByG(long groupId) {
		getPersistence().removeByG(groupId);
	}

	/**
	* Returns the number of opencps dossier statistics where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching opencps dossier statistics
	*/
	public static int countByG(long groupId) {
		return getPersistence().countByG(groupId);
	}

	/**
	* Caches the opencps dossier statistic in the entity cache if it is enabled.
	*
	* @param opencpsDossierStatistic the opencps dossier statistic
	*/
	public static void cacheResult(
		OpencpsDossierStatistic opencpsDossierStatistic) {
		getPersistence().cacheResult(opencpsDossierStatistic);
	}

	/**
	* Caches the opencps dossier statistics in the entity cache if it is enabled.
	*
	* @param opencpsDossierStatistics the opencps dossier statistics
	*/
	public static void cacheResult(
		List<OpencpsDossierStatistic> opencpsDossierStatistics) {
		getPersistence().cacheResult(opencpsDossierStatistics);
	}

	/**
	* Creates a new opencps dossier statistic with the primary key. Does not add the opencps dossier statistic to the database.
	*
	* @param dossierStatisticId the primary key for the new opencps dossier statistic
	* @return the new opencps dossier statistic
	*/
	public static OpencpsDossierStatistic create(long dossierStatisticId) {
		return getPersistence().create(dossierStatisticId);
	}

	/**
	* Removes the opencps dossier statistic with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierStatisticId the primary key of the opencps dossier statistic
	* @return the opencps dossier statistic that was removed
	* @throws NoSuchOpencpsDossierStatisticException if a opencps dossier statistic with the primary key could not be found
	*/
	public static OpencpsDossierStatistic remove(long dossierStatisticId)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence().remove(dossierStatisticId);
	}

	public static OpencpsDossierStatistic updateImpl(
		OpencpsDossierStatistic opencpsDossierStatistic) {
		return getPersistence().updateImpl(opencpsDossierStatistic);
	}

	/**
	* Returns the opencps dossier statistic with the primary key or throws a {@link NoSuchOpencpsDossierStatisticException} if it could not be found.
	*
	* @param dossierStatisticId the primary key of the opencps dossier statistic
	* @return the opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a opencps dossier statistic with the primary key could not be found
	*/
	public static OpencpsDossierStatistic findByPrimaryKey(
		long dossierStatisticId)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence().findByPrimaryKey(dossierStatisticId);
	}

	/**
	* Returns the opencps dossier statistic with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param dossierStatisticId the primary key of the opencps dossier statistic
	* @return the opencps dossier statistic, or <code>null</code> if a opencps dossier statistic with the primary key could not be found
	*/
	public static OpencpsDossierStatistic fetchByPrimaryKey(
		long dossierStatisticId) {
		return getPersistence().fetchByPrimaryKey(dossierStatisticId);
	}

	public static java.util.Map<java.io.Serializable, OpencpsDossierStatistic> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the opencps dossier statistics.
	*
	* @return the opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the opencps dossier statistics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @return the range of opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findAll(int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findAll(int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the opencps dossier statistics from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of opencps dossier statistics.
	*
	* @return the number of opencps dossier statistics
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static OpencpsDossierStatisticPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<OpencpsDossierStatisticPersistence, OpencpsDossierStatisticPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(OpencpsDossierStatisticPersistence.class);

		ServiceTracker<OpencpsDossierStatisticPersistence, OpencpsDossierStatisticPersistence> serviceTracker =
			new ServiceTracker<OpencpsDossierStatisticPersistence, OpencpsDossierStatisticPersistence>(bundle.getBundleContext(),
				OpencpsDossierStatisticPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}