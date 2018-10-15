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

import org.opencps.dossiermgt.model.DossierStatistic;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the dossier statistic service. This utility wraps {@link org.opencps.dossiermgt.service.persistence.impl.DossierStatisticPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see DossierStatisticPersistence
 * @see org.opencps.dossiermgt.service.persistence.impl.DossierStatisticPersistenceImpl
 * @generated
 */
@ProviderType
public class DossierStatisticUtil {
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
	public static void clearCache(DossierStatistic dossierStatistic) {
		getPersistence().clearCache(dossierStatistic);
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
	public static List<DossierStatistic> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<DossierStatistic> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<DossierStatistic> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<DossierStatistic> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static DossierStatistic update(DossierStatistic dossierStatistic) {
		return getPersistence().update(dossierStatistic);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static DossierStatistic update(DossierStatistic dossierStatistic,
		ServiceContext serviceContext) {
		return getPersistence().update(dossierStatistic, serviceContext);
	}

	/**
	* Returns all the dossier statistics where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching dossier statistics
	*/
	public static List<DossierStatistic> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the dossier statistics where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier statistics
	* @param end the upper bound of the range of dossier statistics (not inclusive)
	* @return the range of matching dossier statistics
	*/
	public static List<DossierStatistic> findByUuid(String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the dossier statistics where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier statistics
	* @param end the upper bound of the range of dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier statistics
	*/
	public static List<DossierStatistic> findByUuid(String uuid, int start,
		int end, OrderByComparator<DossierStatistic> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier statistics where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier statistics
	* @param end the upper bound of the range of dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier statistics
	*/
	public static List<DossierStatistic> findByUuid(String uuid, int start,
		int end, OrderByComparator<DossierStatistic> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first dossier statistic in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier statistic
	* @throws NoSuchDossierStatisticException if a matching dossier statistic could not be found
	*/
	public static DossierStatistic findByUuid_First(String uuid,
		OrderByComparator<DossierStatistic> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierStatisticException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first dossier statistic in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier statistic, or <code>null</code> if a matching dossier statistic could not be found
	*/
	public static DossierStatistic fetchByUuid_First(String uuid,
		OrderByComparator<DossierStatistic> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last dossier statistic in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier statistic
	* @throws NoSuchDossierStatisticException if a matching dossier statistic could not be found
	*/
	public static DossierStatistic findByUuid_Last(String uuid,
		OrderByComparator<DossierStatistic> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierStatisticException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last dossier statistic in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier statistic, or <code>null</code> if a matching dossier statistic could not be found
	*/
	public static DossierStatistic fetchByUuid_Last(String uuid,
		OrderByComparator<DossierStatistic> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the dossier statistics before and after the current dossier statistic in the ordered set where uuid = &#63;.
	*
	* @param dossierStatisticId the primary key of the current dossier statistic
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier statistic
	* @throws NoSuchDossierStatisticException if a dossier statistic with the primary key could not be found
	*/
	public static DossierStatistic[] findByUuid_PrevAndNext(
		long dossierStatisticId, String uuid,
		OrderByComparator<DossierStatistic> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierStatisticException {
		return getPersistence()
				   .findByUuid_PrevAndNext(dossierStatisticId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the dossier statistics where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of dossier statistics where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching dossier statistics
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the dossier statistic where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDossierStatisticException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dossier statistic
	* @throws NoSuchDossierStatisticException if a matching dossier statistic could not be found
	*/
	public static DossierStatistic findByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierStatisticException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the dossier statistic where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dossier statistic, or <code>null</code> if a matching dossier statistic could not be found
	*/
	public static DossierStatistic fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the dossier statistic where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier statistic, or <code>null</code> if a matching dossier statistic could not be found
	*/
	public static DossierStatistic fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the dossier statistic where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the dossier statistic that was removed
	*/
	public static DossierStatistic removeByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierStatisticException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of dossier statistics where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching dossier statistics
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the dossier statistics where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching dossier statistics
	*/
	public static List<DossierStatistic> findByUuid_C(String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the dossier statistics where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossier statistics
	* @param end the upper bound of the range of dossier statistics (not inclusive)
	* @return the range of matching dossier statistics
	*/
	public static List<DossierStatistic> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the dossier statistics where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossier statistics
	* @param end the upper bound of the range of dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier statistics
	*/
	public static List<DossierStatistic> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<DossierStatistic> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier statistics where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossier statistics
	* @param end the upper bound of the range of dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier statistics
	*/
	public static List<DossierStatistic> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<DossierStatistic> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier statistic
	* @throws NoSuchDossierStatisticException if a matching dossier statistic could not be found
	*/
	public static DossierStatistic findByUuid_C_First(String uuid,
		long companyId, OrderByComparator<DossierStatistic> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierStatisticException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first dossier statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier statistic, or <code>null</code> if a matching dossier statistic could not be found
	*/
	public static DossierStatistic fetchByUuid_C_First(String uuid,
		long companyId, OrderByComparator<DossierStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last dossier statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier statistic
	* @throws NoSuchDossierStatisticException if a matching dossier statistic could not be found
	*/
	public static DossierStatistic findByUuid_C_Last(String uuid,
		long companyId, OrderByComparator<DossierStatistic> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierStatisticException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last dossier statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier statistic, or <code>null</code> if a matching dossier statistic could not be found
	*/
	public static DossierStatistic fetchByUuid_C_Last(String uuid,
		long companyId, OrderByComparator<DossierStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the dossier statistics before and after the current dossier statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param dossierStatisticId the primary key of the current dossier statistic
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier statistic
	* @throws NoSuchDossierStatisticException if a dossier statistic with the primary key could not be found
	*/
	public static DossierStatistic[] findByUuid_C_PrevAndNext(
		long dossierStatisticId, String uuid, long companyId,
		OrderByComparator<DossierStatistic> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierStatisticException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(dossierStatisticId, uuid,
			companyId, orderByComparator);
	}

	/**
	* Removes all the dossier statistics where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of dossier statistics where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching dossier statistics
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the dossier statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @return the matching dossier statistics
	*/
	public static List<DossierStatistic> findByG_UID_Y(long groupId,
		long userId, int year) {
		return getPersistence().findByG_UID_Y(groupId, userId, year);
	}

	/**
	* Returns a range of all the dossier statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param start the lower bound of the range of dossier statistics
	* @param end the upper bound of the range of dossier statistics (not inclusive)
	* @return the range of matching dossier statistics
	*/
	public static List<DossierStatistic> findByG_UID_Y(long groupId,
		long userId, int year, int start, int end) {
		return getPersistence().findByG_UID_Y(groupId, userId, year, start, end);
	}

	/**
	* Returns an ordered range of all the dossier statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param start the lower bound of the range of dossier statistics
	* @param end the upper bound of the range of dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier statistics
	*/
	public static List<DossierStatistic> findByG_UID_Y(long groupId,
		long userId, int year, int start, int end,
		OrderByComparator<DossierStatistic> orderByComparator) {
		return getPersistence()
				   .findByG_UID_Y(groupId, userId, year, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param start the lower bound of the range of dossier statistics
	* @param end the upper bound of the range of dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier statistics
	*/
	public static List<DossierStatistic> findByG_UID_Y(long groupId,
		long userId, int year, int start, int end,
		OrderByComparator<DossierStatistic> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_UID_Y(groupId, userId, year, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier statistic
	* @throws NoSuchDossierStatisticException if a matching dossier statistic could not be found
	*/
	public static DossierStatistic findByG_UID_Y_First(long groupId,
		long userId, int year,
		OrderByComparator<DossierStatistic> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierStatisticException {
		return getPersistence()
				   .findByG_UID_Y_First(groupId, userId, year, orderByComparator);
	}

	/**
	* Returns the first dossier statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier statistic, or <code>null</code> if a matching dossier statistic could not be found
	*/
	public static DossierStatistic fetchByG_UID_Y_First(long groupId,
		long userId, int year,
		OrderByComparator<DossierStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByG_UID_Y_First(groupId, userId, year,
			orderByComparator);
	}

	/**
	* Returns the last dossier statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier statistic
	* @throws NoSuchDossierStatisticException if a matching dossier statistic could not be found
	*/
	public static DossierStatistic findByG_UID_Y_Last(long groupId,
		long userId, int year,
		OrderByComparator<DossierStatistic> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierStatisticException {
		return getPersistence()
				   .findByG_UID_Y_Last(groupId, userId, year, orderByComparator);
	}

	/**
	* Returns the last dossier statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier statistic, or <code>null</code> if a matching dossier statistic could not be found
	*/
	public static DossierStatistic fetchByG_UID_Y_Last(long groupId,
		long userId, int year,
		OrderByComparator<DossierStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByG_UID_Y_Last(groupId, userId, year, orderByComparator);
	}

	/**
	* Returns the dossier statistics before and after the current dossier statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param dossierStatisticId the primary key of the current dossier statistic
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier statistic
	* @throws NoSuchDossierStatisticException if a dossier statistic with the primary key could not be found
	*/
	public static DossierStatistic[] findByG_UID_Y_PrevAndNext(
		long dossierStatisticId, long groupId, long userId, int year,
		OrderByComparator<DossierStatistic> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierStatisticException {
		return getPersistence()
				   .findByG_UID_Y_PrevAndNext(dossierStatisticId, groupId,
			userId, year, orderByComparator);
	}

	/**
	* Removes all the dossier statistics where groupId = &#63; and userId = &#63; and year = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	*/
	public static void removeByG_UID_Y(long groupId, long userId, int year) {
		getPersistence().removeByG_UID_Y(groupId, userId, year);
	}

	/**
	* Returns the number of dossier statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @return the number of matching dossier statistics
	*/
	public static int countByG_UID_Y(long groupId, long userId, int year) {
		return getPersistence().countByG_UID_Y(groupId, userId, year);
	}

	/**
	* Caches the dossier statistic in the entity cache if it is enabled.
	*
	* @param dossierStatistic the dossier statistic
	*/
	public static void cacheResult(DossierStatistic dossierStatistic) {
		getPersistence().cacheResult(dossierStatistic);
	}

	/**
	* Caches the dossier statistics in the entity cache if it is enabled.
	*
	* @param dossierStatistics the dossier statistics
	*/
	public static void cacheResult(List<DossierStatistic> dossierStatistics) {
		getPersistence().cacheResult(dossierStatistics);
	}

	/**
	* Creates a new dossier statistic with the primary key. Does not add the dossier statistic to the database.
	*
	* @param dossierStatisticId the primary key for the new dossier statistic
	* @return the new dossier statistic
	*/
	public static DossierStatistic create(long dossierStatisticId) {
		return getPersistence().create(dossierStatisticId);
	}

	/**
	* Removes the dossier statistic with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierStatisticId the primary key of the dossier statistic
	* @return the dossier statistic that was removed
	* @throws NoSuchDossierStatisticException if a dossier statistic with the primary key could not be found
	*/
	public static DossierStatistic remove(long dossierStatisticId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierStatisticException {
		return getPersistence().remove(dossierStatisticId);
	}

	public static DossierStatistic updateImpl(DossierStatistic dossierStatistic) {
		return getPersistence().updateImpl(dossierStatistic);
	}

	/**
	* Returns the dossier statistic with the primary key or throws a {@link NoSuchDossierStatisticException} if it could not be found.
	*
	* @param dossierStatisticId the primary key of the dossier statistic
	* @return the dossier statistic
	* @throws NoSuchDossierStatisticException if a dossier statistic with the primary key could not be found
	*/
	public static DossierStatistic findByPrimaryKey(long dossierStatisticId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierStatisticException {
		return getPersistence().findByPrimaryKey(dossierStatisticId);
	}

	/**
	* Returns the dossier statistic with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param dossierStatisticId the primary key of the dossier statistic
	* @return the dossier statistic, or <code>null</code> if a dossier statistic with the primary key could not be found
	*/
	public static DossierStatistic fetchByPrimaryKey(long dossierStatisticId) {
		return getPersistence().fetchByPrimaryKey(dossierStatisticId);
	}

	public static java.util.Map<java.io.Serializable, DossierStatistic> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the dossier statistics.
	*
	* @return the dossier statistics
	*/
	public static List<DossierStatistic> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the dossier statistics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier statistics
	* @param end the upper bound of the range of dossier statistics (not inclusive)
	* @return the range of dossier statistics
	*/
	public static List<DossierStatistic> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the dossier statistics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier statistics
	* @param end the upper bound of the range of dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of dossier statistics
	*/
	public static List<DossierStatistic> findAll(int start, int end,
		OrderByComparator<DossierStatistic> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier statistics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier statistics
	* @param end the upper bound of the range of dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of dossier statistics
	*/
	public static List<DossierStatistic> findAll(int start, int end,
		OrderByComparator<DossierStatistic> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the dossier statistics from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of dossier statistics.
	*
	* @return the number of dossier statistics
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static DossierStatisticPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DossierStatisticPersistence, DossierStatisticPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(DossierStatisticPersistence.class);

		ServiceTracker<DossierStatisticPersistence, DossierStatisticPersistence> serviceTracker =
			new ServiceTracker<DossierStatisticPersistence, DossierStatisticPersistence>(bundle.getBundleContext(),
				DossierStatisticPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}