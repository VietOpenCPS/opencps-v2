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
		int reporting)
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
		int reporting) {
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
		int reporting, boolean retrieveFromCache) {
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
		int reporting)
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
		int month, int year, String domainCode, int reporting) {
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
	* Returns the opencps dossier statistic where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and system = &#63; or throws a {@link NoSuchOpencpsDossierStatisticException} if it could not be found.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param system the system
	* @return the matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic findByG_M_Y_G_D_S(long groupId,
		int month, int year, String govAgencyCode, String domainCode,
		String system)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByG_M_Y_G_D_S(groupId, month, year, govAgencyCode,
			domainCode, system);
	}

	/**
	* Returns the opencps dossier statistic where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and system = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param system the system
	* @return the matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByG_M_Y_G_D_S(long groupId,
		int month, int year, String govAgencyCode, String domainCode,
		String system) {
		return getPersistence()
				   .fetchByG_M_Y_G_D_S(groupId, month, year, govAgencyCode,
			domainCode, system);
	}

	/**
	* Returns the opencps dossier statistic where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and system = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param system the system
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByG_M_Y_G_D_S(long groupId,
		int month, int year, String govAgencyCode, String domainCode,
		String system, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByG_M_Y_G_D_S(groupId, month, year, govAgencyCode,
			domainCode, system, retrieveFromCache);
	}

	/**
	* Removes the opencps dossier statistic where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and system = &#63; from the database.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param system the system
	* @return the opencps dossier statistic that was removed
	*/
	public static OpencpsDossierStatistic removeByG_M_Y_G_D_S(long groupId,
		int month, int year, String govAgencyCode, String domainCode,
		String system)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .removeByG_M_Y_G_D_S(groupId, month, year, govAgencyCode,
			domainCode, system);
	}

	/**
	* Returns the number of opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and system = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param system the system
	* @return the number of matching opencps dossier statistics
	*/
	public static int countByG_M_Y_G_D_S(long groupId, int month, int year,
		String govAgencyCode, String domainCode, String system) {
		return getPersistence()
				   .countByG_M_Y_G_D_S(groupId, month, year, govAgencyCode,
			domainCode, system);
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
	* Returns the opencps dossier statistic where groupId = &#63; and govAgencyCode = &#63; and month = &#63; and year = &#63; and system = &#63; or throws a {@link NoSuchOpencpsDossierStatisticException} if it could not be found.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param month the month
	* @param year the year
	* @param system the system
	* @return the matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic findByGID_M_Y_GAC_S(long groupId,
		String govAgencyCode, int month, int year, String system)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByGID_M_Y_GAC_S(groupId, govAgencyCode, month, year,
			system);
	}

	/**
	* Returns the opencps dossier statistic where groupId = &#63; and govAgencyCode = &#63; and month = &#63; and year = &#63; and system = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param month the month
	* @param year the year
	* @param system the system
	* @return the matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByGID_M_Y_GAC_S(long groupId,
		String govAgencyCode, int month, int year, String system) {
		return getPersistence()
				   .fetchByGID_M_Y_GAC_S(groupId, govAgencyCode, month, year,
			system);
	}

	/**
	* Returns the opencps dossier statistic where groupId = &#63; and govAgencyCode = &#63; and month = &#63; and year = &#63; and system = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param month the month
	* @param year the year
	* @param system the system
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByGID_M_Y_GAC_S(long groupId,
		String govAgencyCode, int month, int year, String system,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByGID_M_Y_GAC_S(groupId, govAgencyCode, month, year,
			system, retrieveFromCache);
	}

	/**
	* Removes the opencps dossier statistic where groupId = &#63; and govAgencyCode = &#63; and month = &#63; and year = &#63; and system = &#63; from the database.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param month the month
	* @param year the year
	* @param system the system
	* @return the opencps dossier statistic that was removed
	*/
	public static OpencpsDossierStatistic removeByGID_M_Y_GAC_S(long groupId,
		String govAgencyCode, int month, int year, String system)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .removeByGID_M_Y_GAC_S(groupId, govAgencyCode, month, year,
			system);
	}

	/**
	* Returns the number of opencps dossier statistics where groupId = &#63; and govAgencyCode = &#63; and month = &#63; and year = &#63; and system = &#63;.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param month the month
	* @param year the year
	* @param system the system
	* @return the number of matching opencps dossier statistics
	*/
	public static int countByGID_M_Y_GAC_S(long groupId, String govAgencyCode,
		int month, int year, String system) {
		return getPersistence()
				   .countByGID_M_Y_GAC_S(groupId, govAgencyCode, month, year,
			system);
	}

	/**
	* Returns the opencps dossier statistic where groupId = &#63; and month = &#63; and year = &#63; and system = &#63; and domainCode = &#63; or throws a {@link NoSuchOpencpsDossierStatisticException} if it could not be found.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param system the system
	* @param domainCode the domain code
	* @return the matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic findByGID_M_Y_S_DC(long groupId,
		int month, int year, String system, String domainCode)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByGID_M_Y_S_DC(groupId, month, year, system, domainCode);
	}

	/**
	* Returns the opencps dossier statistic where groupId = &#63; and month = &#63; and year = &#63; and system = &#63; and domainCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param system the system
	* @param domainCode the domain code
	* @return the matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByGID_M_Y_S_DC(long groupId,
		int month, int year, String system, String domainCode) {
		return getPersistence()
				   .fetchByGID_M_Y_S_DC(groupId, month, year, system, domainCode);
	}

	/**
	* Returns the opencps dossier statistic where groupId = &#63; and month = &#63; and year = &#63; and system = &#63; and domainCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param system the system
	* @param domainCode the domain code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByGID_M_Y_S_DC(long groupId,
		int month, int year, String system, String domainCode,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByGID_M_Y_S_DC(groupId, month, year, system,
			domainCode, retrieveFromCache);
	}

	/**
	* Removes the opencps dossier statistic where groupId = &#63; and month = &#63; and year = &#63; and system = &#63; and domainCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param system the system
	* @param domainCode the domain code
	* @return the opencps dossier statistic that was removed
	*/
	public static OpencpsDossierStatistic removeByGID_M_Y_S_DC(long groupId,
		int month, int year, String system, String domainCode)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .removeByGID_M_Y_S_DC(groupId, month, year, system,
			domainCode);
	}

	/**
	* Returns the number of opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and system = &#63; and domainCode = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param system the system
	* @param domainCode the domain code
	* @return the number of matching opencps dossier statistics
	*/
	public static int countByGID_M_Y_S_DC(long groupId, int month, int year,
		String system, String domainCode) {
		return getPersistence()
				   .countByGID_M_Y_S_DC(groupId, month, year, system, domainCode);
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
	public static OpencpsDossierStatistic findByGID_M_Y_GAC(long groupId,
		String govAgencyCode, int month, int year)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByGID_M_Y_GAC(groupId, govAgencyCode, month, year);
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
	public static OpencpsDossierStatistic fetchByGID_M_Y_GAC(long groupId,
		String govAgencyCode, int month, int year) {
		return getPersistence()
				   .fetchByGID_M_Y_GAC(groupId, govAgencyCode, month, year);
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
	public static OpencpsDossierStatistic fetchByGID_M_Y_GAC(long groupId,
		String govAgencyCode, int month, int year, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByGID_M_Y_GAC(groupId, govAgencyCode, month, year,
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
	public static OpencpsDossierStatistic removeByGID_M_Y_GAC(long groupId,
		String govAgencyCode, int month, int year)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .removeByGID_M_Y_GAC(groupId, govAgencyCode, month, year);
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
	public static int countByGID_M_Y_GAC(long groupId, String govAgencyCode,
		int month, int year) {
		return getPersistence()
				   .countByGID_M_Y_GAC(groupId, govAgencyCode, month, year);
	}

	/**
	* Returns the opencps dossier statistic where groupId = &#63; and month = &#63; and year = &#63; and domainCode = &#63; or throws a {@link NoSuchOpencpsDossierStatisticException} if it could not be found.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param domainCode the domain code
	* @return the matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic findByGID_M_Y_DC(long groupId,
		int month, int year, String domainCode)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByGID_M_Y_DC(groupId, month, year, domainCode);
	}

	/**
	* Returns the opencps dossier statistic where groupId = &#63; and month = &#63; and year = &#63; and domainCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param domainCode the domain code
	* @return the matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByGID_M_Y_DC(long groupId,
		int month, int year, String domainCode) {
		return getPersistence()
				   .fetchByGID_M_Y_DC(groupId, month, year, domainCode);
	}

	/**
	* Returns the opencps dossier statistic where groupId = &#63; and month = &#63; and year = &#63; and domainCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param domainCode the domain code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByGID_M_Y_DC(long groupId,
		int month, int year, String domainCode, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByGID_M_Y_DC(groupId, month, year, domainCode,
			retrieveFromCache);
	}

	/**
	* Removes the opencps dossier statistic where groupId = &#63; and month = &#63; and year = &#63; and domainCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param domainCode the domain code
	* @return the opencps dossier statistic that was removed
	*/
	public static OpencpsDossierStatistic removeByGID_M_Y_DC(long groupId,
		int month, int year, String domainCode)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .removeByGID_M_Y_DC(groupId, month, year, domainCode);
	}

	/**
	* Returns the number of opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and domainCode = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param domainCode the domain code
	* @return the number of matching opencps dossier statistics
	*/
	public static int countByGID_M_Y_DC(long groupId, int month, int year,
		String domainCode) {
		return getPersistence()
				   .countByGID_M_Y_DC(groupId, month, year, domainCode);
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
		int month, int year, int reporting) {
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
		int month, int year, int reporting, int start, int end) {
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
		int month, int year, int reporting, int start, int end,
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
		int month, int year, int reporting, int start, int end,
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
		int month, int year, int reporting,
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
		long groupId, int month, int year, int reporting,
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
		int month, int year, int reporting,
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
		int month, int year, int reporting,
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
		int reporting,
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
		int reporting) {
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
		int reporting) {
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
		long groupId, int month, int year, int reporting) {
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
		long groupId, int month, int year, int reporting, int start, int end) {
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
		long groupId, int month, int year, int reporting, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
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
		long groupId, int month, int year, int reporting, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
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
		long groupId, int month, int year, int reporting,
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
		long groupId, int month, int year, int reporting,
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
		int month, int year, int reporting,
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
		long groupId, int month, int year, int reporting,
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
		int reporting,
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
		long groupId, int[] months, int year, int reporting) {
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
		long groupId, int[] months, int year, int reporting, int start, int end) {
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
		long groupId, int[] months, int year, int reporting, int start,
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
		long groupId, int[] months, int year, int reporting, int start,
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
		int reporting) {
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
		int reporting) {
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
		int reporting) {
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
	* Returns all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @return the matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByG_M_Y_GC_DC(
		long groupId, int month, int year) {
		return getPersistence().findByG_M_Y_GC_DC(groupId, month, year);
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
	public static List<OpencpsDossierStatistic> findByG_M_Y_GC_DC(
		long groupId, int month, int year, int start, int end) {
		return getPersistence()
				   .findByG_M_Y_GC_DC(groupId, month, year, start, end);
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
	public static List<OpencpsDossierStatistic> findByG_M_Y_GC_DC(
		long groupId, int month, int year, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .findByG_M_Y_GC_DC(groupId, month, year, start, end,
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
	public static List<OpencpsDossierStatistic> findByG_M_Y_GC_DC(
		long groupId, int month, int year, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_M_Y_GC_DC(groupId, month, year, start, end,
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
	public static OpencpsDossierStatistic findByG_M_Y_GC_DC_First(
		long groupId, int month, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByG_M_Y_GC_DC_First(groupId, month, year,
			orderByComparator);
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
	public static OpencpsDossierStatistic fetchByG_M_Y_GC_DC_First(
		long groupId, int month, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByG_M_Y_GC_DC_First(groupId, month, year,
			orderByComparator);
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
	public static OpencpsDossierStatistic findByG_M_Y_GC_DC_Last(long groupId,
		int month, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByG_M_Y_GC_DC_Last(groupId, month, year,
			orderByComparator);
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
	public static OpencpsDossierStatistic fetchByG_M_Y_GC_DC_Last(
		long groupId, int month, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByG_M_Y_GC_DC_Last(groupId, month, year,
			orderByComparator);
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
	public static OpencpsDossierStatistic[] findByG_M_Y_GC_DC_PrevAndNext(
		long dossierStatisticId, long groupId, int month, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByG_M_Y_GC_DC_PrevAndNext(dossierStatisticId, groupId,
			month, year, orderByComparator);
	}

	/**
	* Removes all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; from the database.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	*/
	public static void removeByG_M_Y_GC_DC(long groupId, int month, int year) {
		getPersistence().removeByG_M_Y_GC_DC(groupId, month, year);
	}

	/**
	* Returns the number of opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @return the number of matching opencps dossier statistics
	*/
	public static int countByG_M_Y_GC_DC(long groupId, int month, int year) {
		return getPersistence().countByG_M_Y_GC_DC(groupId, month, year);
	}

	/**
	* Returns all the opencps dossier statistics where groupId = &#63; and month &ne; &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @return the matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByG_NM_Y(long groupId,
		int month, int year) {
		return getPersistence().findByG_NM_Y(groupId, month, year);
	}

	/**
	* Returns a range of all the opencps dossier statistics where groupId = &#63; and month &ne; &#63; and year = &#63;.
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
	public static List<OpencpsDossierStatistic> findByG_NM_Y(long groupId,
		int month, int year, int start, int end) {
		return getPersistence().findByG_NM_Y(groupId, month, year, start, end);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where groupId = &#63; and month &ne; &#63; and year = &#63;.
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
	public static List<OpencpsDossierStatistic> findByG_NM_Y(long groupId,
		int month, int year, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .findByG_NM_Y(groupId, month, year, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where groupId = &#63; and month &ne; &#63; and year = &#63;.
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
	public static List<OpencpsDossierStatistic> findByG_NM_Y(long groupId,
		int month, int year, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_NM_Y(groupId, month, year, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first opencps dossier statistic in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic findByG_NM_Y_First(long groupId,
		int month, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByG_NM_Y_First(groupId, month, year, orderByComparator);
	}

	/**
	* Returns the first opencps dossier statistic in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByG_NM_Y_First(long groupId,
		int month, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByG_NM_Y_First(groupId, month, year, orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic findByG_NM_Y_Last(long groupId,
		int month, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByG_NM_Y_Last(groupId, month, year, orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByG_NM_Y_Last(long groupId,
		int month, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByG_NM_Y_Last(groupId, month, year, orderByComparator);
	}

	/**
	* Returns the opencps dossier statistics before and after the current opencps dossier statistic in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63;.
	*
	* @param dossierStatisticId the primary key of the current opencps dossier statistic
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a opencps dossier statistic with the primary key could not be found
	*/
	public static OpencpsDossierStatistic[] findByG_NM_Y_PrevAndNext(
		long dossierStatisticId, long groupId, int month, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByG_NM_Y_PrevAndNext(dossierStatisticId, groupId,
			month, year, orderByComparator);
	}

	/**
	* Removes all the opencps dossier statistics where groupId = &#63; and month &ne; &#63; and year = &#63; from the database.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	*/
	public static void removeByG_NM_Y(long groupId, int month, int year) {
		getPersistence().removeByG_NM_Y(groupId, month, year);
	}

	/**
	* Returns the number of opencps dossier statistics where groupId = &#63; and month &ne; &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @return the number of matching opencps dossier statistics
	*/
	public static int countByG_NM_Y(long groupId, int month, int year) {
		return getPersistence().countByG_NM_Y(groupId, month, year);
	}

	/**
	* Returns all the opencps dossier statistics where reporting = &#63;.
	*
	* @param reporting the reporting
	* @return the matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByF_REPO(int reporting) {
		return getPersistence().findByF_REPO(reporting);
	}

	/**
	* Returns a range of all the opencps dossier statistics where reporting = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param reporting the reporting
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @return the range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByF_REPO(int reporting,
		int start, int end) {
		return getPersistence().findByF_REPO(reporting, start, end);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where reporting = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param reporting the reporting
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByF_REPO(int reporting,
		int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .findByF_REPO(reporting, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where reporting = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param reporting the reporting
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByF_REPO(int reporting,
		int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_REPO(reporting, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first opencps dossier statistic in the ordered set where reporting = &#63;.
	*
	* @param reporting the reporting
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic findByF_REPO_First(int reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence().findByF_REPO_First(reporting, orderByComparator);
	}

	/**
	* Returns the first opencps dossier statistic in the ordered set where reporting = &#63;.
	*
	* @param reporting the reporting
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByF_REPO_First(int reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence().fetchByF_REPO_First(reporting, orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic in the ordered set where reporting = &#63;.
	*
	* @param reporting the reporting
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic findByF_REPO_Last(int reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence().findByF_REPO_Last(reporting, orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic in the ordered set where reporting = &#63;.
	*
	* @param reporting the reporting
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByF_REPO_Last(int reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence().fetchByF_REPO_Last(reporting, orderByComparator);
	}

	/**
	* Returns the opencps dossier statistics before and after the current opencps dossier statistic in the ordered set where reporting = &#63;.
	*
	* @param dossierStatisticId the primary key of the current opencps dossier statistic
	* @param reporting the reporting
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a opencps dossier statistic with the primary key could not be found
	*/
	public static OpencpsDossierStatistic[] findByF_REPO_PrevAndNext(
		long dossierStatisticId, int reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByF_REPO_PrevAndNext(dossierStatisticId, reporting,
			orderByComparator);
	}

	/**
	* Returns all the opencps dossier statistics where reporting = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param reportings the reportings
	* @return the matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByF_REPO(int[] reportings) {
		return getPersistence().findByF_REPO(reportings);
	}

	/**
	* Returns a range of all the opencps dossier statistics where reporting = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param reportings the reportings
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @return the range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByF_REPO(int[] reportings,
		int start, int end) {
		return getPersistence().findByF_REPO(reportings, start, end);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where reporting = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param reportings the reportings
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByF_REPO(int[] reportings,
		int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .findByF_REPO(reportings, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where reporting = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param reporting the reporting
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByF_REPO(int[] reportings,
		int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_REPO(reportings, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Removes all the opencps dossier statistics where reporting = &#63; from the database.
	*
	* @param reporting the reporting
	*/
	public static void removeByF_REPO(int reporting) {
		getPersistence().removeByF_REPO(reporting);
	}

	/**
	* Returns the number of opencps dossier statistics where reporting = &#63;.
	*
	* @param reporting the reporting
	* @return the number of matching opencps dossier statistics
	*/
	public static int countByF_REPO(int reporting) {
		return getPersistence().countByF_REPO(reporting);
	}

	/**
	* Returns the number of opencps dossier statistics where reporting = any &#63;.
	*
	* @param reportings the reportings
	* @return the number of matching opencps dossier statistics
	*/
	public static int countByF_REPO(int[] reportings) {
		return getPersistence().countByF_REPO(reportings);
	}

	/**
	* Returns all the opencps dossier statistics where groupId &ne; &#63;.
	*
	* @param groupId the group ID
	* @return the matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByF_NOT_GID(long groupId) {
		return getPersistence().findByF_NOT_GID(groupId);
	}

	/**
	* Returns a range of all the opencps dossier statistics where groupId &ne; &#63;.
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
	public static List<OpencpsDossierStatistic> findByF_NOT_GID(long groupId,
		int start, int end) {
		return getPersistence().findByF_NOT_GID(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where groupId &ne; &#63;.
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
	public static List<OpencpsDossierStatistic> findByF_NOT_GID(long groupId,
		int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .findByF_NOT_GID(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where groupId &ne; &#63;.
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
	public static List<OpencpsDossierStatistic> findByF_NOT_GID(long groupId,
		int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_NOT_GID(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first opencps dossier statistic in the ordered set where groupId &ne; &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic findByF_NOT_GID_First(long groupId,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence().findByF_NOT_GID_First(groupId, orderByComparator);
	}

	/**
	* Returns the first opencps dossier statistic in the ordered set where groupId &ne; &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByF_NOT_GID_First(long groupId,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByF_NOT_GID_First(groupId, orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic in the ordered set where groupId &ne; &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic findByF_NOT_GID_Last(long groupId,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence().findByF_NOT_GID_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic in the ordered set where groupId &ne; &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByF_NOT_GID_Last(long groupId,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence().fetchByF_NOT_GID_Last(groupId, orderByComparator);
	}

	/**
	* Returns the opencps dossier statistics before and after the current opencps dossier statistic in the ordered set where groupId &ne; &#63;.
	*
	* @param dossierStatisticId the primary key of the current opencps dossier statistic
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a opencps dossier statistic with the primary key could not be found
	*/
	public static OpencpsDossierStatistic[] findByF_NOT_GID_PrevAndNext(
		long dossierStatisticId, long groupId,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByF_NOT_GID_PrevAndNext(dossierStatisticId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the opencps dossier statistics where groupId &ne; &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByF_NOT_GID(long groupId) {
		getPersistence().removeByF_NOT_GID(groupId);
	}

	/**
	* Returns the number of opencps dossier statistics where groupId &ne; &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching opencps dossier statistics
	*/
	public static int countByF_NOT_GID(long groupId) {
		return getPersistence().countByF_NOT_GID(groupId);
	}

	/**
	* Returns all the opencps dossier statistics where month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	*
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @return the matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByM_Y_GOV_DOM_GRO_SYS(
		int month, int year, String govAgencyCode, String domainCode,
		String groupAgencyCode, String system) {
		return getPersistence()
				   .findByM_Y_GOV_DOM_GRO_SYS(month, year, govAgencyCode,
			domainCode, groupAgencyCode, system);
	}

	/**
	* Returns a range of all the opencps dossier statistics where month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @return the range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByM_Y_GOV_DOM_GRO_SYS(
		int month, int year, String govAgencyCode, String domainCode,
		String groupAgencyCode, String system, int start, int end) {
		return getPersistence()
				   .findByM_Y_GOV_DOM_GRO_SYS(month, year, govAgencyCode,
			domainCode, groupAgencyCode, system, start, end);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByM_Y_GOV_DOM_GRO_SYS(
		int month, int year, String govAgencyCode, String domainCode,
		String groupAgencyCode, String system, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .findByM_Y_GOV_DOM_GRO_SYS(month, year, govAgencyCode,
			domainCode, groupAgencyCode, system, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByM_Y_GOV_DOM_GRO_SYS(
		int month, int year, String govAgencyCode, String domainCode,
		String groupAgencyCode, String system, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByM_Y_GOV_DOM_GRO_SYS(month, year, govAgencyCode,
			domainCode, groupAgencyCode, system, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first opencps dossier statistic in the ordered set where month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	*
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic findByM_Y_GOV_DOM_GRO_SYS_First(
		int month, int year, String govAgencyCode, String domainCode,
		String groupAgencyCode, String system,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByM_Y_GOV_DOM_GRO_SYS_First(month, year, govAgencyCode,
			domainCode, groupAgencyCode, system, orderByComparator);
	}

	/**
	* Returns the first opencps dossier statistic in the ordered set where month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	*
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByM_Y_GOV_DOM_GRO_SYS_First(
		int month, int year, String govAgencyCode, String domainCode,
		String groupAgencyCode, String system,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByM_Y_GOV_DOM_GRO_SYS_First(month, year,
			govAgencyCode, domainCode, groupAgencyCode, system,
			orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic in the ordered set where month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	*
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic findByM_Y_GOV_DOM_GRO_SYS_Last(
		int month, int year, String govAgencyCode, String domainCode,
		String groupAgencyCode, String system,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByM_Y_GOV_DOM_GRO_SYS_Last(month, year, govAgencyCode,
			domainCode, groupAgencyCode, system, orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic in the ordered set where month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	*
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByM_Y_GOV_DOM_GRO_SYS_Last(
		int month, int year, String govAgencyCode, String domainCode,
		String groupAgencyCode, String system,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByM_Y_GOV_DOM_GRO_SYS_Last(month, year, govAgencyCode,
			domainCode, groupAgencyCode, system, orderByComparator);
	}

	/**
	* Returns the opencps dossier statistics before and after the current opencps dossier statistic in the ordered set where month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	*
	* @param dossierStatisticId the primary key of the current opencps dossier statistic
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a opencps dossier statistic with the primary key could not be found
	*/
	public static OpencpsDossierStatistic[] findByM_Y_GOV_DOM_GRO_SYS_PrevAndNext(
		long dossierStatisticId, int month, int year, String govAgencyCode,
		String domainCode, String groupAgencyCode, String system,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByM_Y_GOV_DOM_GRO_SYS_PrevAndNext(dossierStatisticId,
			month, year, govAgencyCode, domainCode, groupAgencyCode, system,
			orderByComparator);
	}

	/**
	* Removes all the opencps dossier statistics where month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63; from the database.
	*
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	*/
	public static void removeByM_Y_GOV_DOM_GRO_SYS(int month, int year,
		String govAgencyCode, String domainCode, String groupAgencyCode,
		String system) {
		getPersistence()
			.removeByM_Y_GOV_DOM_GRO_SYS(month, year, govAgencyCode,
			domainCode, groupAgencyCode, system);
	}

	/**
	* Returns the number of opencps dossier statistics where month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	*
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @return the number of matching opencps dossier statistics
	*/
	public static int countByM_Y_GOV_DOM_GRO_SYS(int month, int year,
		String govAgencyCode, String domainCode, String groupAgencyCode,
		String system) {
		return getPersistence()
				   .countByM_Y_GOV_DOM_GRO_SYS(month, year, govAgencyCode,
			domainCode, groupAgencyCode, system);
	}

	/**
	* Returns all the opencps dossier statistics where groupId &ne; &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @return the matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByNOT_G_M_Y_GOV_DOM_GRO_SYS(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, String groupAgencyCode, String system) {
		return getPersistence()
				   .findByNOT_G_M_Y_GOV_DOM_GRO_SYS(groupId, month, year,
			govAgencyCode, domainCode, groupAgencyCode, system);
	}

	/**
	* Returns a range of all the opencps dossier statistics where groupId &ne; &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @return the range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByNOT_G_M_Y_GOV_DOM_GRO_SYS(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, String groupAgencyCode, String system, int start,
		int end) {
		return getPersistence()
				   .findByNOT_G_M_Y_GOV_DOM_GRO_SYS(groupId, month, year,
			govAgencyCode, domainCode, groupAgencyCode, system, start, end);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where groupId &ne; &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByNOT_G_M_Y_GOV_DOM_GRO_SYS(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, String groupAgencyCode, String system, int start,
		int end, OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .findByNOT_G_M_Y_GOV_DOM_GRO_SYS(groupId, month, year,
			govAgencyCode, domainCode, groupAgencyCode, system, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where groupId &ne; &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByNOT_G_M_Y_GOV_DOM_GRO_SYS(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, String groupAgencyCode, String system, int start,
		int end, OrderByComparator<OpencpsDossierStatistic> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByNOT_G_M_Y_GOV_DOM_GRO_SYS(groupId, month, year,
			govAgencyCode, domainCode, groupAgencyCode, system, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first opencps dossier statistic in the ordered set where groupId &ne; &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic findByNOT_G_M_Y_GOV_DOM_GRO_SYS_First(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, String groupAgencyCode, String system,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByNOT_G_M_Y_GOV_DOM_GRO_SYS_First(groupId, month, year,
			govAgencyCode, domainCode, groupAgencyCode, system,
			orderByComparator);
	}

	/**
	* Returns the first opencps dossier statistic in the ordered set where groupId &ne; &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByNOT_G_M_Y_GOV_DOM_GRO_SYS_First(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, String groupAgencyCode, String system,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByNOT_G_M_Y_GOV_DOM_GRO_SYS_First(groupId, month,
			year, govAgencyCode, domainCode, groupAgencyCode, system,
			orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic in the ordered set where groupId &ne; &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic findByNOT_G_M_Y_GOV_DOM_GRO_SYS_Last(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, String groupAgencyCode, String system,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByNOT_G_M_Y_GOV_DOM_GRO_SYS_Last(groupId, month, year,
			govAgencyCode, domainCode, groupAgencyCode, system,
			orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic in the ordered set where groupId &ne; &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByNOT_G_M_Y_GOV_DOM_GRO_SYS_Last(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, String groupAgencyCode, String system,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByNOT_G_M_Y_GOV_DOM_GRO_SYS_Last(groupId, month, year,
			govAgencyCode, domainCode, groupAgencyCode, system,
			orderByComparator);
	}

	/**
	* Returns the opencps dossier statistics before and after the current opencps dossier statistic in the ordered set where groupId &ne; &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	*
	* @param dossierStatisticId the primary key of the current opencps dossier statistic
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a opencps dossier statistic with the primary key could not be found
	*/
	public static OpencpsDossierStatistic[] findByNOT_G_M_Y_GOV_DOM_GRO_SYS_PrevAndNext(
		long dossierStatisticId, long groupId, int month, int year,
		String govAgencyCode, String domainCode, String groupAgencyCode,
		String system,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByNOT_G_M_Y_GOV_DOM_GRO_SYS_PrevAndNext(dossierStatisticId,
			groupId, month, year, govAgencyCode, domainCode, groupAgencyCode,
			system, orderByComparator);
	}

	/**
	* Removes all the opencps dossier statistics where groupId &ne; &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63; from the database.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	*/
	public static void removeByNOT_G_M_Y_GOV_DOM_GRO_SYS(long groupId,
		int month, int year, String govAgencyCode, String domainCode,
		String groupAgencyCode, String system) {
		getPersistence()
			.removeByNOT_G_M_Y_GOV_DOM_GRO_SYS(groupId, month, year,
			govAgencyCode, domainCode, groupAgencyCode, system);
	}

	/**
	* Returns the number of opencps dossier statistics where groupId &ne; &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @return the number of matching opencps dossier statistics
	*/
	public static int countByNOT_G_M_Y_GOV_DOM_GRO_SYS(long groupId, int month,
		int year, String govAgencyCode, String domainCode,
		String groupAgencyCode, String system) {
		return getPersistence()
				   .countByNOT_G_M_Y_GOV_DOM_GRO_SYS(groupId, month, year,
			govAgencyCode, domainCode, groupAgencyCode, system);
	}

	/**
	* Returns all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and reporting &ne; &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param reporting the reporting
	* @return the matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByGID_M_Y_NOT_RP(
		long groupId, int month, int year, int reporting) {
		return getPersistence()
				   .findByGID_M_Y_NOT_RP(groupId, month, year, reporting);
	}

	/**
	* Returns a range of all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and reporting &ne; &#63;.
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
	public static List<OpencpsDossierStatistic> findByGID_M_Y_NOT_RP(
		long groupId, int month, int year, int reporting, int start, int end) {
		return getPersistence()
				   .findByGID_M_Y_NOT_RP(groupId, month, year, reporting,
			start, end);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and reporting &ne; &#63;.
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
	public static List<OpencpsDossierStatistic> findByGID_M_Y_NOT_RP(
		long groupId, int month, int year, int reporting, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .findByGID_M_Y_NOT_RP(groupId, month, year, reporting,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and reporting &ne; &#63;.
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
	public static List<OpencpsDossierStatistic> findByGID_M_Y_NOT_RP(
		long groupId, int month, int year, int reporting, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGID_M_Y_NOT_RP(groupId, month, year, reporting,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and reporting &ne; &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param reporting the reporting
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic findByGID_M_Y_NOT_RP_First(
		long groupId, int month, int year, int reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByGID_M_Y_NOT_RP_First(groupId, month, year, reporting,
			orderByComparator);
	}

	/**
	* Returns the first opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and reporting &ne; &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param reporting the reporting
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByGID_M_Y_NOT_RP_First(
		long groupId, int month, int year, int reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByGID_M_Y_NOT_RP_First(groupId, month, year,
			reporting, orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and reporting &ne; &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param reporting the reporting
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic findByGID_M_Y_NOT_RP_Last(
		long groupId, int month, int year, int reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByGID_M_Y_NOT_RP_Last(groupId, month, year, reporting,
			orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and reporting &ne; &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param reporting the reporting
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByGID_M_Y_NOT_RP_Last(
		long groupId, int month, int year, int reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByGID_M_Y_NOT_RP_Last(groupId, month, year, reporting,
			orderByComparator);
	}

	/**
	* Returns the opencps dossier statistics before and after the current opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and reporting &ne; &#63;.
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
	public static OpencpsDossierStatistic[] findByGID_M_Y_NOT_RP_PrevAndNext(
		long dossierStatisticId, long groupId, int month, int year,
		int reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByGID_M_Y_NOT_RP_PrevAndNext(dossierStatisticId,
			groupId, month, year, reporting, orderByComparator);
	}

	/**
	* Removes all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and reporting &ne; &#63; from the database.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param reporting the reporting
	*/
	public static void removeByGID_M_Y_NOT_RP(long groupId, int month,
		int year, int reporting) {
		getPersistence().removeByGID_M_Y_NOT_RP(groupId, month, year, reporting);
	}

	/**
	* Returns the number of opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and reporting &ne; &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param reporting the reporting
	* @return the number of matching opencps dossier statistics
	*/
	public static int countByGID_M_Y_NOT_RP(long groupId, int month, int year,
		int reporting) {
		return getPersistence()
				   .countByGID_M_Y_NOT_RP(groupId, month, year, reporting);
	}

	/**
	* Returns all the opencps dossier statistics where groupId = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	*
	* @param groupId the group ID
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @return the matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByG_Y_GO_DO_GR_SY(
		long groupId, int year, String govAgencyCode, String domainCode,
		String groupAgencyCode, String system) {
		return getPersistence()
				   .findByG_Y_GO_DO_GR_SY(groupId, year, govAgencyCode,
			domainCode, groupAgencyCode, system);
	}

	/**
	* Returns a range of all the opencps dossier statistics where groupId = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @return the range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByG_Y_GO_DO_GR_SY(
		long groupId, int year, String govAgencyCode, String domainCode,
		String groupAgencyCode, String system, int start, int end) {
		return getPersistence()
				   .findByG_Y_GO_DO_GR_SY(groupId, year, govAgencyCode,
			domainCode, groupAgencyCode, system, start, end);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where groupId = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByG_Y_GO_DO_GR_SY(
		long groupId, int year, String govAgencyCode, String domainCode,
		String groupAgencyCode, String system, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .findByG_Y_GO_DO_GR_SY(groupId, year, govAgencyCode,
			domainCode, groupAgencyCode, system, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where groupId = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByG_Y_GO_DO_GR_SY(
		long groupId, int year, String govAgencyCode, String domainCode,
		String groupAgencyCode, String system, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_Y_GO_DO_GR_SY(groupId, year, govAgencyCode,
			domainCode, groupAgencyCode, system, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first opencps dossier statistic in the ordered set where groupId = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	*
	* @param groupId the group ID
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic findByG_Y_GO_DO_GR_SY_First(
		long groupId, int year, String govAgencyCode, String domainCode,
		String groupAgencyCode, String system,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByG_Y_GO_DO_GR_SY_First(groupId, year, govAgencyCode,
			domainCode, groupAgencyCode, system, orderByComparator);
	}

	/**
	* Returns the first opencps dossier statistic in the ordered set where groupId = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	*
	* @param groupId the group ID
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByG_Y_GO_DO_GR_SY_First(
		long groupId, int year, String govAgencyCode, String domainCode,
		String groupAgencyCode, String system,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByG_Y_GO_DO_GR_SY_First(groupId, year, govAgencyCode,
			domainCode, groupAgencyCode, system, orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic in the ordered set where groupId = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	*
	* @param groupId the group ID
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic findByG_Y_GO_DO_GR_SY_Last(
		long groupId, int year, String govAgencyCode, String domainCode,
		String groupAgencyCode, String system,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByG_Y_GO_DO_GR_SY_Last(groupId, year, govAgencyCode,
			domainCode, groupAgencyCode, system, orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic in the ordered set where groupId = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	*
	* @param groupId the group ID
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByG_Y_GO_DO_GR_SY_Last(
		long groupId, int year, String govAgencyCode, String domainCode,
		String groupAgencyCode, String system,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByG_Y_GO_DO_GR_SY_Last(groupId, year, govAgencyCode,
			domainCode, groupAgencyCode, system, orderByComparator);
	}

	/**
	* Returns the opencps dossier statistics before and after the current opencps dossier statistic in the ordered set where groupId = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	*
	* @param dossierStatisticId the primary key of the current opencps dossier statistic
	* @param groupId the group ID
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a opencps dossier statistic with the primary key could not be found
	*/
	public static OpencpsDossierStatistic[] findByG_Y_GO_DO_GR_SY_PrevAndNext(
		long dossierStatisticId, long groupId, int year, String govAgencyCode,
		String domainCode, String groupAgencyCode, String system,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByG_Y_GO_DO_GR_SY_PrevAndNext(dossierStatisticId,
			groupId, year, govAgencyCode, domainCode, groupAgencyCode, system,
			orderByComparator);
	}

	/**
	* Returns all the opencps dossier statistics where groupId = &#63; and year = &#63; and govAgencyCode = any &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param year the year
	* @param govAgencyCodes the gov agency codes
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @return the matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByG_Y_GO_DO_GR_SY(
		long groupId, int year, String[] govAgencyCodes, String domainCode,
		String groupAgencyCode, String system) {
		return getPersistence()
				   .findByG_Y_GO_DO_GR_SY(groupId, year, govAgencyCodes,
			domainCode, groupAgencyCode, system);
	}

	/**
	* Returns a range of all the opencps dossier statistics where groupId = &#63; and year = &#63; and govAgencyCode = any &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param year the year
	* @param govAgencyCodes the gov agency codes
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @return the range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByG_Y_GO_DO_GR_SY(
		long groupId, int year, String[] govAgencyCodes, String domainCode,
		String groupAgencyCode, String system, int start, int end) {
		return getPersistence()
				   .findByG_Y_GO_DO_GR_SY(groupId, year, govAgencyCodes,
			domainCode, groupAgencyCode, system, start, end);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where groupId = &#63; and year = &#63; and govAgencyCode = any &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param year the year
	* @param govAgencyCodes the gov agency codes
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByG_Y_GO_DO_GR_SY(
		long groupId, int year, String[] govAgencyCodes, String domainCode,
		String groupAgencyCode, String system, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .findByG_Y_GO_DO_GR_SY(groupId, year, govAgencyCodes,
			domainCode, groupAgencyCode, system, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where groupId = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByG_Y_GO_DO_GR_SY(
		long groupId, int year, String[] govAgencyCodes, String domainCode,
		String groupAgencyCode, String system, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_Y_GO_DO_GR_SY(groupId, year, govAgencyCodes,
			domainCode, groupAgencyCode, system, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Removes all the opencps dossier statistics where groupId = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63; from the database.
	*
	* @param groupId the group ID
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	*/
	public static void removeByG_Y_GO_DO_GR_SY(long groupId, int year,
		String govAgencyCode, String domainCode, String groupAgencyCode,
		String system) {
		getPersistence()
			.removeByG_Y_GO_DO_GR_SY(groupId, year, govAgencyCode, domainCode,
			groupAgencyCode, system);
	}

	/**
	* Returns the number of opencps dossier statistics where groupId = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	*
	* @param groupId the group ID
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @return the number of matching opencps dossier statistics
	*/
	public static int countByG_Y_GO_DO_GR_SY(long groupId, int year,
		String govAgencyCode, String domainCode, String groupAgencyCode,
		String system) {
		return getPersistence()
				   .countByG_Y_GO_DO_GR_SY(groupId, year, govAgencyCode,
			domainCode, groupAgencyCode, system);
	}

	/**
	* Returns the number of opencps dossier statistics where groupId = &#63; and year = &#63; and govAgencyCode = any &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	*
	* @param groupId the group ID
	* @param year the year
	* @param govAgencyCodes the gov agency codes
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @return the number of matching opencps dossier statistics
	*/
	public static int countByG_Y_GO_DO_GR_SY(long groupId, int year,
		String[] govAgencyCodes, String domainCode, String groupAgencyCode,
		String system) {
		return getPersistence()
				   .countByG_Y_GO_DO_GR_SY(groupId, year, govAgencyCodes,
			domainCode, groupAgencyCode, system);
	}

	/**
	* Returns all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system &ne; &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @return the matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByG_M_Y_GOV_DOM_GRO_NOT_SYS(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, String groupAgencyCode, String system) {
		return getPersistence()
				   .findByG_M_Y_GOV_DOM_GRO_NOT_SYS(groupId, month, year,
			govAgencyCode, domainCode, groupAgencyCode, system);
	}

	/**
	* Returns a range of all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @return the range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByG_M_Y_GOV_DOM_GRO_NOT_SYS(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, String groupAgencyCode, String system, int start,
		int end) {
		return getPersistence()
				   .findByG_M_Y_GOV_DOM_GRO_NOT_SYS(groupId, month, year,
			govAgencyCode, domainCode, groupAgencyCode, system, start, end);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByG_M_Y_GOV_DOM_GRO_NOT_SYS(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, String groupAgencyCode, String system, int start,
		int end, OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .findByG_M_Y_GOV_DOM_GRO_NOT_SYS(groupId, month, year,
			govAgencyCode, domainCode, groupAgencyCode, system, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByG_M_Y_GOV_DOM_GRO_NOT_SYS(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, String groupAgencyCode, String system, int start,
		int end, OrderByComparator<OpencpsDossierStatistic> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_M_Y_GOV_DOM_GRO_NOT_SYS(groupId, month, year,
			govAgencyCode, domainCode, groupAgencyCode, system, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system &ne; &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic findByG_M_Y_GOV_DOM_GRO_NOT_SYS_First(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, String groupAgencyCode, String system,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByG_M_Y_GOV_DOM_GRO_NOT_SYS_First(groupId, month, year,
			govAgencyCode, domainCode, groupAgencyCode, system,
			orderByComparator);
	}

	/**
	* Returns the first opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system &ne; &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByG_M_Y_GOV_DOM_GRO_NOT_SYS_First(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, String groupAgencyCode, String system,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByG_M_Y_GOV_DOM_GRO_NOT_SYS_First(groupId, month,
			year, govAgencyCode, domainCode, groupAgencyCode, system,
			orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system &ne; &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic findByG_M_Y_GOV_DOM_GRO_NOT_SYS_Last(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, String groupAgencyCode, String system,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByG_M_Y_GOV_DOM_GRO_NOT_SYS_Last(groupId, month, year,
			govAgencyCode, domainCode, groupAgencyCode, system,
			orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system &ne; &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByG_M_Y_GOV_DOM_GRO_NOT_SYS_Last(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, String groupAgencyCode, String system,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByG_M_Y_GOV_DOM_GRO_NOT_SYS_Last(groupId, month, year,
			govAgencyCode, domainCode, groupAgencyCode, system,
			orderByComparator);
	}

	/**
	* Returns the opencps dossier statistics before and after the current opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system &ne; &#63;.
	*
	* @param dossierStatisticId the primary key of the current opencps dossier statistic
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a opencps dossier statistic with the primary key could not be found
	*/
	public static OpencpsDossierStatistic[] findByG_M_Y_GOV_DOM_GRO_NOT_SYS_PrevAndNext(
		long dossierStatisticId, long groupId, int month, int year,
		String govAgencyCode, String domainCode, String groupAgencyCode,
		String system,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByG_M_Y_GOV_DOM_GRO_NOT_SYS_PrevAndNext(dossierStatisticId,
			groupId, month, year, govAgencyCode, domainCode, groupAgencyCode,
			system, orderByComparator);
	}

	/**
	* Removes all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system &ne; &#63; from the database.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	*/
	public static void removeByG_M_Y_GOV_DOM_GRO_NOT_SYS(long groupId,
		int month, int year, String govAgencyCode, String domainCode,
		String groupAgencyCode, String system) {
		getPersistence()
			.removeByG_M_Y_GOV_DOM_GRO_NOT_SYS(groupId, month, year,
			govAgencyCode, domainCode, groupAgencyCode, system);
	}

	/**
	* Returns the number of opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system &ne; &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @return the number of matching opencps dossier statistics
	*/
	public static int countByG_M_Y_GOV_DOM_GRO_NOT_SYS(long groupId, int month,
		int year, String govAgencyCode, String domainCode,
		String groupAgencyCode, String system) {
		return getPersistence()
				   .countByG_M_Y_GOV_DOM_GRO_NOT_SYS(groupId, month, year,
			govAgencyCode, domainCode, groupAgencyCode, system);
	}

	/**
	* Returns all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @return the matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByG_M_Y_GOV_DOM_GRO_SYS(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, String groupAgencyCode, String system) {
		return getPersistence()
				   .findByG_M_Y_GOV_DOM_GRO_SYS(groupId, month, year,
			govAgencyCode, domainCode, groupAgencyCode, system);
	}

	/**
	* Returns a range of all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @return the range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByG_M_Y_GOV_DOM_GRO_SYS(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, String groupAgencyCode, String system, int start,
		int end) {
		return getPersistence()
				   .findByG_M_Y_GOV_DOM_GRO_SYS(groupId, month, year,
			govAgencyCode, domainCode, groupAgencyCode, system, start, end);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByG_M_Y_GOV_DOM_GRO_SYS(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, String groupAgencyCode, String system, int start,
		int end, OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .findByG_M_Y_GOV_DOM_GRO_SYS(groupId, month, year,
			govAgencyCode, domainCode, groupAgencyCode, system, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByG_M_Y_GOV_DOM_GRO_SYS(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, String groupAgencyCode, String system, int start,
		int end, OrderByComparator<OpencpsDossierStatistic> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_M_Y_GOV_DOM_GRO_SYS(groupId, month, year,
			govAgencyCode, domainCode, groupAgencyCode, system, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic findByG_M_Y_GOV_DOM_GRO_SYS_First(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, String groupAgencyCode, String system,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByG_M_Y_GOV_DOM_GRO_SYS_First(groupId, month, year,
			govAgencyCode, domainCode, groupAgencyCode, system,
			orderByComparator);
	}

	/**
	* Returns the first opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByG_M_Y_GOV_DOM_GRO_SYS_First(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, String groupAgencyCode, String system,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByG_M_Y_GOV_DOM_GRO_SYS_First(groupId, month, year,
			govAgencyCode, domainCode, groupAgencyCode, system,
			orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic findByG_M_Y_GOV_DOM_GRO_SYS_Last(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, String groupAgencyCode, String system,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByG_M_Y_GOV_DOM_GRO_SYS_Last(groupId, month, year,
			govAgencyCode, domainCode, groupAgencyCode, system,
			orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByG_M_Y_GOV_DOM_GRO_SYS_Last(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, String groupAgencyCode, String system,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByG_M_Y_GOV_DOM_GRO_SYS_Last(groupId, month, year,
			govAgencyCode, domainCode, groupAgencyCode, system,
			orderByComparator);
	}

	/**
	* Returns the opencps dossier statistics before and after the current opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	*
	* @param dossierStatisticId the primary key of the current opencps dossier statistic
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a opencps dossier statistic with the primary key could not be found
	*/
	public static OpencpsDossierStatistic[] findByG_M_Y_GOV_DOM_GRO_SYS_PrevAndNext(
		long dossierStatisticId, long groupId, int month, int year,
		String govAgencyCode, String domainCode, String groupAgencyCode,
		String system,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByG_M_Y_GOV_DOM_GRO_SYS_PrevAndNext(dossierStatisticId,
			groupId, month, year, govAgencyCode, domainCode, groupAgencyCode,
			system, orderByComparator);
	}

	/**
	* Removes all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63; from the database.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	*/
	public static void removeByG_M_Y_GOV_DOM_GRO_SYS(long groupId, int month,
		int year, String govAgencyCode, String domainCode,
		String groupAgencyCode, String system) {
		getPersistence()
			.removeByG_M_Y_GOV_DOM_GRO_SYS(groupId, month, year, govAgencyCode,
			domainCode, groupAgencyCode, system);
	}

	/**
	* Returns the number of opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupAgencyCode the group agency code
	* @param system the system
	* @return the number of matching opencps dossier statistics
	*/
	public static int countByG_M_Y_GOV_DOM_GRO_SYS(long groupId, int month,
		int year, String govAgencyCode, String domainCode,
		String groupAgencyCode, String system) {
		return getPersistence()
				   .countByG_M_Y_GOV_DOM_GRO_SYS(groupId, month, year,
			govAgencyCode, domainCode, groupAgencyCode, system);
	}

	/**
	* Returns all the opencps dossier statistics where month = &#63; and year = &#63; and reporting = &#63;.
	*
	* @param month the month
	* @param year the year
	* @param reporting the reporting
	* @return the matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByF_M_Y_REPO(int month,
		int year, int reporting) {
		return getPersistence().findByF_M_Y_REPO(month, year, reporting);
	}

	/**
	* Returns a range of all the opencps dossier statistics where month = &#63; and year = &#63; and reporting = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param month the month
	* @param year the year
	* @param reporting the reporting
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @return the range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByF_M_Y_REPO(int month,
		int year, int reporting, int start, int end) {
		return getPersistence()
				   .findByF_M_Y_REPO(month, year, reporting, start, end);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where month = &#63; and year = &#63; and reporting = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param month the month
	* @param year the year
	* @param reporting the reporting
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByF_M_Y_REPO(int month,
		int year, int reporting, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .findByF_M_Y_REPO(month, year, reporting, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where month = &#63; and year = &#63; and reporting = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param month the month
	* @param year the year
	* @param reporting the reporting
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByF_M_Y_REPO(int month,
		int year, int reporting, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_M_Y_REPO(month, year, reporting, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first opencps dossier statistic in the ordered set where month = &#63; and year = &#63; and reporting = &#63;.
	*
	* @param month the month
	* @param year the year
	* @param reporting the reporting
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic findByF_M_Y_REPO_First(int month,
		int year, int reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByF_M_Y_REPO_First(month, year, reporting,
			orderByComparator);
	}

	/**
	* Returns the first opencps dossier statistic in the ordered set where month = &#63; and year = &#63; and reporting = &#63;.
	*
	* @param month the month
	* @param year the year
	* @param reporting the reporting
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByF_M_Y_REPO_First(int month,
		int year, int reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByF_M_Y_REPO_First(month, year, reporting,
			orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic in the ordered set where month = &#63; and year = &#63; and reporting = &#63;.
	*
	* @param month the month
	* @param year the year
	* @param reporting the reporting
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic findByF_M_Y_REPO_Last(int month,
		int year, int reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByF_M_Y_REPO_Last(month, year, reporting,
			orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic in the ordered set where month = &#63; and year = &#63; and reporting = &#63;.
	*
	* @param month the month
	* @param year the year
	* @param reporting the reporting
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByF_M_Y_REPO_Last(int month,
		int year, int reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByF_M_Y_REPO_Last(month, year, reporting,
			orderByComparator);
	}

	/**
	* Returns the opencps dossier statistics before and after the current opencps dossier statistic in the ordered set where month = &#63; and year = &#63; and reporting = &#63;.
	*
	* @param dossierStatisticId the primary key of the current opencps dossier statistic
	* @param month the month
	* @param year the year
	* @param reporting the reporting
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a opencps dossier statistic with the primary key could not be found
	*/
	public static OpencpsDossierStatistic[] findByF_M_Y_REPO_PrevAndNext(
		long dossierStatisticId, int month, int year, int reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByF_M_Y_REPO_PrevAndNext(dossierStatisticId, month,
			year, reporting, orderByComparator);
	}

	/**
	* Returns all the opencps dossier statistics where month = any &#63; and year = any &#63; and reporting = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param months the months
	* @param years the years
	* @param reportings the reportings
	* @return the matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByF_M_Y_REPO(int[] months,
		int[] years, int[] reportings) {
		return getPersistence().findByF_M_Y_REPO(months, years, reportings);
	}

	/**
	* Returns a range of all the opencps dossier statistics where month = any &#63; and year = any &#63; and reporting = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param months the months
	* @param years the years
	* @param reportings the reportings
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @return the range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByF_M_Y_REPO(int[] months,
		int[] years, int[] reportings, int start, int end) {
		return getPersistence()
				   .findByF_M_Y_REPO(months, years, reportings, start, end);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where month = any &#63; and year = any &#63; and reporting = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param months the months
	* @param years the years
	* @param reportings the reportings
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByF_M_Y_REPO(int[] months,
		int[] years, int[] reportings, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .findByF_M_Y_REPO(months, years, reportings, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where month = &#63; and year = &#63; and reporting = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param month the month
	* @param year the year
	* @param reporting the reporting
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByF_M_Y_REPO(int[] months,
		int[] years, int[] reportings, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_M_Y_REPO(months, years, reportings, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the opencps dossier statistics where month = &#63; and year = &#63; and reporting = &#63; from the database.
	*
	* @param month the month
	* @param year the year
	* @param reporting the reporting
	*/
	public static void removeByF_M_Y_REPO(int month, int year, int reporting) {
		getPersistence().removeByF_M_Y_REPO(month, year, reporting);
	}

	/**
	* Returns the number of opencps dossier statistics where month = &#63; and year = &#63; and reporting = &#63;.
	*
	* @param month the month
	* @param year the year
	* @param reporting the reporting
	* @return the number of matching opencps dossier statistics
	*/
	public static int countByF_M_Y_REPO(int month, int year, int reporting) {
		return getPersistence().countByF_M_Y_REPO(month, year, reporting);
	}

	/**
	* Returns the number of opencps dossier statistics where month = any &#63; and year = any &#63; and reporting = any &#63;.
	*
	* @param months the months
	* @param years the years
	* @param reportings the reportings
	* @return the number of matching opencps dossier statistics
	*/
	public static int countByF_M_Y_REPO(int[] months, int[] years,
		int[] reportings) {
		return getPersistence().countByF_M_Y_REPO(months, years, reportings);
	}

	/**
	* Returns all the opencps dossier statistics where year = &#63; and reporting = &#63;.
	*
	* @param year the year
	* @param reporting the reporting
	* @return the matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByF_Y_REPO(int year,
		int reporting) {
		return getPersistence().findByF_Y_REPO(year, reporting);
	}

	/**
	* Returns a range of all the opencps dossier statistics where year = &#63; and reporting = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param year the year
	* @param reporting the reporting
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @return the range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByF_Y_REPO(int year,
		int reporting, int start, int end) {
		return getPersistence().findByF_Y_REPO(year, reporting, start, end);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where year = &#63; and reporting = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param year the year
	* @param reporting the reporting
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByF_Y_REPO(int year,
		int reporting, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .findByF_Y_REPO(year, reporting, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where year = &#63; and reporting = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param year the year
	* @param reporting the reporting
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByF_Y_REPO(int year,
		int reporting, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_Y_REPO(year, reporting, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first opencps dossier statistic in the ordered set where year = &#63; and reporting = &#63;.
	*
	* @param year the year
	* @param reporting the reporting
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic findByF_Y_REPO_First(int year,
		int reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByF_Y_REPO_First(year, reporting, orderByComparator);
	}

	/**
	* Returns the first opencps dossier statistic in the ordered set where year = &#63; and reporting = &#63;.
	*
	* @param year the year
	* @param reporting the reporting
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByF_Y_REPO_First(int year,
		int reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByF_Y_REPO_First(year, reporting, orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic in the ordered set where year = &#63; and reporting = &#63;.
	*
	* @param year the year
	* @param reporting the reporting
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic findByF_Y_REPO_Last(int year,
		int reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByF_Y_REPO_Last(year, reporting, orderByComparator);
	}

	/**
	* Returns the last opencps dossier statistic in the ordered set where year = &#63; and reporting = &#63;.
	*
	* @param year the year
	* @param reporting the reporting
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public static OpencpsDossierStatistic fetchByF_Y_REPO_Last(int year,
		int reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByF_Y_REPO_Last(year, reporting, orderByComparator);
	}

	/**
	* Returns the opencps dossier statistics before and after the current opencps dossier statistic in the ordered set where year = &#63; and reporting = &#63;.
	*
	* @param dossierStatisticId the primary key of the current opencps dossier statistic
	* @param year the year
	* @param reporting the reporting
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a opencps dossier statistic with the primary key could not be found
	*/
	public static OpencpsDossierStatistic[] findByF_Y_REPO_PrevAndNext(
		long dossierStatisticId, int year, int reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException {
		return getPersistence()
				   .findByF_Y_REPO_PrevAndNext(dossierStatisticId, year,
			reporting, orderByComparator);
	}

	/**
	* Returns all the opencps dossier statistics where year = any &#63; and reporting = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param years the years
	* @param reportings the reportings
	* @return the matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByF_Y_REPO(int[] years,
		int[] reportings) {
		return getPersistence().findByF_Y_REPO(years, reportings);
	}

	/**
	* Returns a range of all the opencps dossier statistics where year = any &#63; and reporting = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param years the years
	* @param reportings the reportings
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @return the range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByF_Y_REPO(int[] years,
		int[] reportings, int start, int end) {
		return getPersistence().findByF_Y_REPO(years, reportings, start, end);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where year = any &#63; and reporting = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param years the years
	* @param reportings the reportings
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByF_Y_REPO(int[] years,
		int[] reportings, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return getPersistence()
				   .findByF_Y_REPO(years, reportings, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps dossier statistics where year = &#63; and reporting = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param year the year
	* @param reporting the reporting
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossier statistics
	*/
	public static List<OpencpsDossierStatistic> findByF_Y_REPO(int[] years,
		int[] reportings, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_Y_REPO(years, reportings, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the opencps dossier statistics where year = &#63; and reporting = &#63; from the database.
	*
	* @param year the year
	* @param reporting the reporting
	*/
	public static void removeByF_Y_REPO(int year, int reporting) {
		getPersistence().removeByF_Y_REPO(year, reporting);
	}

	/**
	* Returns the number of opencps dossier statistics where year = &#63; and reporting = &#63;.
	*
	* @param year the year
	* @param reporting the reporting
	* @return the number of matching opencps dossier statistics
	*/
	public static int countByF_Y_REPO(int year, int reporting) {
		return getPersistence().countByF_Y_REPO(year, reporting);
	}

	/**
	* Returns the number of opencps dossier statistics where year = any &#63; and reporting = any &#63;.
	*
	* @param years the years
	* @param reportings the reportings
	* @return the number of matching opencps dossier statistics
	*/
	public static int countByF_Y_REPO(int[] years, int[] reportings) {
		return getPersistence().countByF_Y_REPO(years, reportings);
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