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

package org.opencps.adminconfig.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.adminconfig.model.SyncTracking;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.Date;
import java.util.List;

/**
 * The persistence utility for the sync tracking service. This utility wraps {@link org.opencps.adminconfig.service.persistence.impl.SyncTrackingPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author binhth
 * @see SyncTrackingPersistence
 * @see org.opencps.adminconfig.service.persistence.impl.SyncTrackingPersistenceImpl
 * @generated
 */
@ProviderType
public class SyncTrackingUtil {
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
	public static void clearCache(SyncTracking syncTracking) {
		getPersistence().clearCache(syncTracking);
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
	public static List<SyncTracking> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SyncTracking> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SyncTracking> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SyncTracking> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static SyncTracking update(SyncTracking syncTracking) {
		return getPersistence().update(syncTracking);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static SyncTracking update(SyncTracking syncTracking,
		ServiceContext serviceContext) {
		return getPersistence().update(syncTracking, serviceContext);
	}

	/**
	* Returns all the sync trackings where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching sync trackings
	*/
	public static List<SyncTracking> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the sync trackings where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @return the range of matching sync trackings
	*/
	public static List<SyncTracking> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the sync trackings where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sync trackings
	*/
	public static List<SyncTracking> findByUuid(String uuid, int start,
		int end, OrderByComparator<SyncTracking> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the sync trackings where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sync trackings
	*/
	public static List<SyncTracking> findByUuid(String uuid, int start,
		int end, OrderByComparator<SyncTracking> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first sync tracking in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public static SyncTracking findByUuid_First(String uuid,
		OrderByComparator<SyncTracking> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first sync tracking in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public static SyncTracking fetchByUuid_First(String uuid,
		OrderByComparator<SyncTracking> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last sync tracking in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public static SyncTracking findByUuid_Last(String uuid,
		OrderByComparator<SyncTracking> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last sync tracking in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public static SyncTracking fetchByUuid_Last(String uuid,
		OrderByComparator<SyncTracking> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the sync trackings before and after the current sync tracking in the ordered set where uuid = &#63;.
	*
	* @param trackingId the primary key of the current sync tracking
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync tracking
	* @throws NoSuchSyncTrackingException if a sync tracking with the primary key could not be found
	*/
	public static SyncTracking[] findByUuid_PrevAndNext(long trackingId,
		String uuid, OrderByComparator<SyncTracking> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence()
				   .findByUuid_PrevAndNext(trackingId, uuid, orderByComparator);
	}

	/**
	* Removes all the sync trackings where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of sync trackings where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching sync trackings
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the sync tracking where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchSyncTrackingException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public static SyncTracking findByUUID_G(String uuid, long groupId)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the sync tracking where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public static SyncTracking fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the sync tracking where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public static SyncTracking fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the sync tracking where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the sync tracking that was removed
	*/
	public static SyncTracking removeByUUID_G(String uuid, long groupId)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of sync trackings where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching sync trackings
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the sync trackings where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching sync trackings
	*/
	public static List<SyncTracking> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the sync trackings where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @return the range of matching sync trackings
	*/
	public static List<SyncTracking> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the sync trackings where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sync trackings
	*/
	public static List<SyncTracking> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<SyncTracking> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the sync trackings where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sync trackings
	*/
	public static List<SyncTracking> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<SyncTracking> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first sync tracking in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public static SyncTracking findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<SyncTracking> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first sync tracking in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public static SyncTracking fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<SyncTracking> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last sync tracking in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public static SyncTracking findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<SyncTracking> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last sync tracking in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public static SyncTracking fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<SyncTracking> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the sync trackings before and after the current sync tracking in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param trackingId the primary key of the current sync tracking
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync tracking
	* @throws NoSuchSyncTrackingException if a sync tracking with the primary key could not be found
	*/
	public static SyncTracking[] findByUuid_C_PrevAndNext(long trackingId,
		String uuid, long companyId,
		OrderByComparator<SyncTracking> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(trackingId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the sync trackings where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of sync trackings where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching sync trackings
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns the sync tracking where referenceUid = &#63; or throws a {@link NoSuchSyncTrackingException} if it could not be found.
	*
	* @param referenceUid the reference uid
	* @return the matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public static SyncTracking findByF_ReferenceUid(String referenceUid)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence().findByF_ReferenceUid(referenceUid);
	}

	/**
	* Returns the sync tracking where referenceUid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param referenceUid the reference uid
	* @return the matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public static SyncTracking fetchByF_ReferenceUid(String referenceUid) {
		return getPersistence().fetchByF_ReferenceUid(referenceUid);
	}

	/**
	* Returns the sync tracking where referenceUid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param referenceUid the reference uid
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public static SyncTracking fetchByF_ReferenceUid(String referenceUid,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_ReferenceUid(referenceUid, retrieveFromCache);
	}

	/**
	* Removes the sync tracking where referenceUid = &#63; from the database.
	*
	* @param referenceUid the reference uid
	* @return the sync tracking that was removed
	*/
	public static SyncTracking removeByF_ReferenceUid(String referenceUid)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence().removeByF_ReferenceUid(referenceUid);
	}

	/**
	* Returns the number of sync trackings where referenceUid = &#63;.
	*
	* @param referenceUid the reference uid
	* @return the number of matching sync trackings
	*/
	public static int countByF_ReferenceUid(String referenceUid) {
		return getPersistence().countByF_ReferenceUid(referenceUid);
	}

	/**
	* Returns the sync tracking where dossierNo = &#63; or throws a {@link NoSuchSyncTrackingException} if it could not be found.
	*
	* @param dossierNo the dossier no
	* @return the matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public static SyncTracking findByF_DossierNo(String dossierNo)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence().findByF_DossierNo(dossierNo);
	}

	/**
	* Returns the sync tracking where dossierNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param dossierNo the dossier no
	* @return the matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public static SyncTracking fetchByF_DossierNo(String dossierNo) {
		return getPersistence().fetchByF_DossierNo(dossierNo);
	}

	/**
	* Returns the sync tracking where dossierNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param dossierNo the dossier no
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public static SyncTracking fetchByF_DossierNo(String dossierNo,
		boolean retrieveFromCache) {
		return getPersistence().fetchByF_DossierNo(dossierNo, retrieveFromCache);
	}

	/**
	* Removes the sync tracking where dossierNo = &#63; from the database.
	*
	* @param dossierNo the dossier no
	* @return the sync tracking that was removed
	*/
	public static SyncTracking removeByF_DossierNo(String dossierNo)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence().removeByF_DossierNo(dossierNo);
	}

	/**
	* Returns the number of sync trackings where dossierNo = &#63;.
	*
	* @param dossierNo the dossier no
	* @return the number of matching sync trackings
	*/
	public static int countByF_DossierNo(String dossierNo) {
		return getPersistence().countByF_DossierNo(dossierNo);
	}

	/**
	* Returns all the sync trackings where protocol = &#63;.
	*
	* @param protocol the protocol
	* @return the matching sync trackings
	*/
	public static List<SyncTracking> findByF_Protocol(String protocol) {
		return getPersistence().findByF_Protocol(protocol);
	}

	/**
	* Returns a range of all the sync trackings where protocol = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param protocol the protocol
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @return the range of matching sync trackings
	*/
	public static List<SyncTracking> findByF_Protocol(String protocol,
		int start, int end) {
		return getPersistence().findByF_Protocol(protocol, start, end);
	}

	/**
	* Returns an ordered range of all the sync trackings where protocol = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param protocol the protocol
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sync trackings
	*/
	public static List<SyncTracking> findByF_Protocol(String protocol,
		int start, int end, OrderByComparator<SyncTracking> orderByComparator) {
		return getPersistence()
				   .findByF_Protocol(protocol, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the sync trackings where protocol = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param protocol the protocol
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sync trackings
	*/
	public static List<SyncTracking> findByF_Protocol(String protocol,
		int start, int end, OrderByComparator<SyncTracking> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_Protocol(protocol, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first sync tracking in the ordered set where protocol = &#63;.
	*
	* @param protocol the protocol
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public static SyncTracking findByF_Protocol_First(String protocol,
		OrderByComparator<SyncTracking> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence()
				   .findByF_Protocol_First(protocol, orderByComparator);
	}

	/**
	* Returns the first sync tracking in the ordered set where protocol = &#63;.
	*
	* @param protocol the protocol
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public static SyncTracking fetchByF_Protocol_First(String protocol,
		OrderByComparator<SyncTracking> orderByComparator) {
		return getPersistence()
				   .fetchByF_Protocol_First(protocol, orderByComparator);
	}

	/**
	* Returns the last sync tracking in the ordered set where protocol = &#63;.
	*
	* @param protocol the protocol
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public static SyncTracking findByF_Protocol_Last(String protocol,
		OrderByComparator<SyncTracking> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence()
				   .findByF_Protocol_Last(protocol, orderByComparator);
	}

	/**
	* Returns the last sync tracking in the ordered set where protocol = &#63;.
	*
	* @param protocol the protocol
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public static SyncTracking fetchByF_Protocol_Last(String protocol,
		OrderByComparator<SyncTracking> orderByComparator) {
		return getPersistence()
				   .fetchByF_Protocol_Last(protocol, orderByComparator);
	}

	/**
	* Returns the sync trackings before and after the current sync tracking in the ordered set where protocol = &#63;.
	*
	* @param trackingId the primary key of the current sync tracking
	* @param protocol the protocol
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync tracking
	* @throws NoSuchSyncTrackingException if a sync tracking with the primary key could not be found
	*/
	public static SyncTracking[] findByF_Protocol_PrevAndNext(long trackingId,
		String protocol, OrderByComparator<SyncTracking> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence()
				   .findByF_Protocol_PrevAndNext(trackingId, protocol,
			orderByComparator);
	}

	/**
	* Removes all the sync trackings where protocol = &#63; from the database.
	*
	* @param protocol the protocol
	*/
	public static void removeByF_Protocol(String protocol) {
		getPersistence().removeByF_Protocol(protocol);
	}

	/**
	* Returns the number of sync trackings where protocol = &#63;.
	*
	* @param protocol the protocol
	* @return the number of matching sync trackings
	*/
	public static int countByF_Protocol(String protocol) {
		return getPersistence().countByF_Protocol(protocol);
	}

	/**
	* Returns all the sync trackings where protocol = &#63; and dossierNo = &#63;.
	*
	* @param protocol the protocol
	* @param dossierNo the dossier no
	* @return the matching sync trackings
	*/
	public static List<SyncTracking> findByF_Protocol_DossierNo(
		String protocol, String dossierNo) {
		return getPersistence().findByF_Protocol_DossierNo(protocol, dossierNo);
	}

	/**
	* Returns a range of all the sync trackings where protocol = &#63; and dossierNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param protocol the protocol
	* @param dossierNo the dossier no
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @return the range of matching sync trackings
	*/
	public static List<SyncTracking> findByF_Protocol_DossierNo(
		String protocol, String dossierNo, int start, int end) {
		return getPersistence()
				   .findByF_Protocol_DossierNo(protocol, dossierNo, start, end);
	}

	/**
	* Returns an ordered range of all the sync trackings where protocol = &#63; and dossierNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param protocol the protocol
	* @param dossierNo the dossier no
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sync trackings
	*/
	public static List<SyncTracking> findByF_Protocol_DossierNo(
		String protocol, String dossierNo, int start, int end,
		OrderByComparator<SyncTracking> orderByComparator) {
		return getPersistence()
				   .findByF_Protocol_DossierNo(protocol, dossierNo, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the sync trackings where protocol = &#63; and dossierNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param protocol the protocol
	* @param dossierNo the dossier no
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sync trackings
	*/
	public static List<SyncTracking> findByF_Protocol_DossierNo(
		String protocol, String dossierNo, int start, int end,
		OrderByComparator<SyncTracking> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_Protocol_DossierNo(protocol, dossierNo, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first sync tracking in the ordered set where protocol = &#63; and dossierNo = &#63;.
	*
	* @param protocol the protocol
	* @param dossierNo the dossier no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public static SyncTracking findByF_Protocol_DossierNo_First(
		String protocol, String dossierNo,
		OrderByComparator<SyncTracking> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence()
				   .findByF_Protocol_DossierNo_First(protocol, dossierNo,
			orderByComparator);
	}

	/**
	* Returns the first sync tracking in the ordered set where protocol = &#63; and dossierNo = &#63;.
	*
	* @param protocol the protocol
	* @param dossierNo the dossier no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public static SyncTracking fetchByF_Protocol_DossierNo_First(
		String protocol, String dossierNo,
		OrderByComparator<SyncTracking> orderByComparator) {
		return getPersistence()
				   .fetchByF_Protocol_DossierNo_First(protocol, dossierNo,
			orderByComparator);
	}

	/**
	* Returns the last sync tracking in the ordered set where protocol = &#63; and dossierNo = &#63;.
	*
	* @param protocol the protocol
	* @param dossierNo the dossier no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public static SyncTracking findByF_Protocol_DossierNo_Last(
		String protocol, String dossierNo,
		OrderByComparator<SyncTracking> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence()
				   .findByF_Protocol_DossierNo_Last(protocol, dossierNo,
			orderByComparator);
	}

	/**
	* Returns the last sync tracking in the ordered set where protocol = &#63; and dossierNo = &#63;.
	*
	* @param protocol the protocol
	* @param dossierNo the dossier no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public static SyncTracking fetchByF_Protocol_DossierNo_Last(
		String protocol, String dossierNo,
		OrderByComparator<SyncTracking> orderByComparator) {
		return getPersistence()
				   .fetchByF_Protocol_DossierNo_Last(protocol, dossierNo,
			orderByComparator);
	}

	/**
	* Returns the sync trackings before and after the current sync tracking in the ordered set where protocol = &#63; and dossierNo = &#63;.
	*
	* @param trackingId the primary key of the current sync tracking
	* @param protocol the protocol
	* @param dossierNo the dossier no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync tracking
	* @throws NoSuchSyncTrackingException if a sync tracking with the primary key could not be found
	*/
	public static SyncTracking[] findByF_Protocol_DossierNo_PrevAndNext(
		long trackingId, String protocol, String dossierNo,
		OrderByComparator<SyncTracking> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence()
				   .findByF_Protocol_DossierNo_PrevAndNext(trackingId,
			protocol, dossierNo, orderByComparator);
	}

	/**
	* Removes all the sync trackings where protocol = &#63; and dossierNo = &#63; from the database.
	*
	* @param protocol the protocol
	* @param dossierNo the dossier no
	*/
	public static void removeByF_Protocol_DossierNo(String protocol,
		String dossierNo) {
		getPersistence().removeByF_Protocol_DossierNo(protocol, dossierNo);
	}

	/**
	* Returns the number of sync trackings where protocol = &#63; and dossierNo = &#63;.
	*
	* @param protocol the protocol
	* @param dossierNo the dossier no
	* @return the number of matching sync trackings
	*/
	public static int countByF_Protocol_DossierNo(String protocol,
		String dossierNo) {
		return getPersistence().countByF_Protocol_DossierNo(protocol, dossierNo);
	}

	/**
	* Returns all the sync trackings where protocol = &#63; and serviceCode = &#63;.
	*
	* @param protocol the protocol
	* @param serviceCode the service code
	* @return the matching sync trackings
	*/
	public static List<SyncTracking> findByF_Protocol_ServiceCode(
		String protocol, String serviceCode) {
		return getPersistence()
				   .findByF_Protocol_ServiceCode(protocol, serviceCode);
	}

	/**
	* Returns a range of all the sync trackings where protocol = &#63; and serviceCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param protocol the protocol
	* @param serviceCode the service code
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @return the range of matching sync trackings
	*/
	public static List<SyncTracking> findByF_Protocol_ServiceCode(
		String protocol, String serviceCode, int start, int end) {
		return getPersistence()
				   .findByF_Protocol_ServiceCode(protocol, serviceCode, start,
			end);
	}

	/**
	* Returns an ordered range of all the sync trackings where protocol = &#63; and serviceCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param protocol the protocol
	* @param serviceCode the service code
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sync trackings
	*/
	public static List<SyncTracking> findByF_Protocol_ServiceCode(
		String protocol, String serviceCode, int start, int end,
		OrderByComparator<SyncTracking> orderByComparator) {
		return getPersistence()
				   .findByF_Protocol_ServiceCode(protocol, serviceCode, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the sync trackings where protocol = &#63; and serviceCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param protocol the protocol
	* @param serviceCode the service code
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sync trackings
	*/
	public static List<SyncTracking> findByF_Protocol_ServiceCode(
		String protocol, String serviceCode, int start, int end,
		OrderByComparator<SyncTracking> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_Protocol_ServiceCode(protocol, serviceCode, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first sync tracking in the ordered set where protocol = &#63; and serviceCode = &#63;.
	*
	* @param protocol the protocol
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public static SyncTracking findByF_Protocol_ServiceCode_First(
		String protocol, String serviceCode,
		OrderByComparator<SyncTracking> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence()
				   .findByF_Protocol_ServiceCode_First(protocol, serviceCode,
			orderByComparator);
	}

	/**
	* Returns the first sync tracking in the ordered set where protocol = &#63; and serviceCode = &#63;.
	*
	* @param protocol the protocol
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public static SyncTracking fetchByF_Protocol_ServiceCode_First(
		String protocol, String serviceCode,
		OrderByComparator<SyncTracking> orderByComparator) {
		return getPersistence()
				   .fetchByF_Protocol_ServiceCode_First(protocol, serviceCode,
			orderByComparator);
	}

	/**
	* Returns the last sync tracking in the ordered set where protocol = &#63; and serviceCode = &#63;.
	*
	* @param protocol the protocol
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public static SyncTracking findByF_Protocol_ServiceCode_Last(
		String protocol, String serviceCode,
		OrderByComparator<SyncTracking> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence()
				   .findByF_Protocol_ServiceCode_Last(protocol, serviceCode,
			orderByComparator);
	}

	/**
	* Returns the last sync tracking in the ordered set where protocol = &#63; and serviceCode = &#63;.
	*
	* @param protocol the protocol
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public static SyncTracking fetchByF_Protocol_ServiceCode_Last(
		String protocol, String serviceCode,
		OrderByComparator<SyncTracking> orderByComparator) {
		return getPersistence()
				   .fetchByF_Protocol_ServiceCode_Last(protocol, serviceCode,
			orderByComparator);
	}

	/**
	* Returns the sync trackings before and after the current sync tracking in the ordered set where protocol = &#63; and serviceCode = &#63;.
	*
	* @param trackingId the primary key of the current sync tracking
	* @param protocol the protocol
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync tracking
	* @throws NoSuchSyncTrackingException if a sync tracking with the primary key could not be found
	*/
	public static SyncTracking[] findByF_Protocol_ServiceCode_PrevAndNext(
		long trackingId, String protocol, String serviceCode,
		OrderByComparator<SyncTracking> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence()
				   .findByF_Protocol_ServiceCode_PrevAndNext(trackingId,
			protocol, serviceCode, orderByComparator);
	}

	/**
	* Removes all the sync trackings where protocol = &#63; and serviceCode = &#63; from the database.
	*
	* @param protocol the protocol
	* @param serviceCode the service code
	*/
	public static void removeByF_Protocol_ServiceCode(String protocol,
		String serviceCode) {
		getPersistence().removeByF_Protocol_ServiceCode(protocol, serviceCode);
	}

	/**
	* Returns the number of sync trackings where protocol = &#63; and serviceCode = &#63;.
	*
	* @param protocol the protocol
	* @param serviceCode the service code
	* @return the number of matching sync trackings
	*/
	public static int countByF_Protocol_ServiceCode(String protocol,
		String serviceCode) {
		return getPersistence()
				   .countByF_Protocol_ServiceCode(protocol, serviceCode);
	}

	/**
	* Returns all the sync trackings where createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @return the matching sync trackings
	*/
	public static List<SyncTracking> findByF_CREATED_BETWEEN(Date createDate,
		Date modifiedDate) {
		return getPersistence().findByF_CREATED_BETWEEN(createDate, modifiedDate);
	}

	/**
	* Returns a range of all the sync trackings where createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @return the range of matching sync trackings
	*/
	public static List<SyncTracking> findByF_CREATED_BETWEEN(Date createDate,
		Date modifiedDate, int start, int end) {
		return getPersistence()
				   .findByF_CREATED_BETWEEN(createDate, modifiedDate, start, end);
	}

	/**
	* Returns an ordered range of all the sync trackings where createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sync trackings
	*/
	public static List<SyncTracking> findByF_CREATED_BETWEEN(Date createDate,
		Date modifiedDate, int start, int end,
		OrderByComparator<SyncTracking> orderByComparator) {
		return getPersistence()
				   .findByF_CREATED_BETWEEN(createDate, modifiedDate, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the sync trackings where createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sync trackings
	*/
	public static List<SyncTracking> findByF_CREATED_BETWEEN(Date createDate,
		Date modifiedDate, int start, int end,
		OrderByComparator<SyncTracking> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_CREATED_BETWEEN(createDate, modifiedDate, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first sync tracking in the ordered set where createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public static SyncTracking findByF_CREATED_BETWEEN_First(Date createDate,
		Date modifiedDate, OrderByComparator<SyncTracking> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence()
				   .findByF_CREATED_BETWEEN_First(createDate, modifiedDate,
			orderByComparator);
	}

	/**
	* Returns the first sync tracking in the ordered set where createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public static SyncTracking fetchByF_CREATED_BETWEEN_First(Date createDate,
		Date modifiedDate, OrderByComparator<SyncTracking> orderByComparator) {
		return getPersistence()
				   .fetchByF_CREATED_BETWEEN_First(createDate, modifiedDate,
			orderByComparator);
	}

	/**
	* Returns the last sync tracking in the ordered set where createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public static SyncTracking findByF_CREATED_BETWEEN_Last(Date createDate,
		Date modifiedDate, OrderByComparator<SyncTracking> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence()
				   .findByF_CREATED_BETWEEN_Last(createDate, modifiedDate,
			orderByComparator);
	}

	/**
	* Returns the last sync tracking in the ordered set where createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public static SyncTracking fetchByF_CREATED_BETWEEN_Last(Date createDate,
		Date modifiedDate, OrderByComparator<SyncTracking> orderByComparator) {
		return getPersistence()
				   .fetchByF_CREATED_BETWEEN_Last(createDate, modifiedDate,
			orderByComparator);
	}

	/**
	* Returns the sync trackings before and after the current sync tracking in the ordered set where createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param trackingId the primary key of the current sync tracking
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync tracking
	* @throws NoSuchSyncTrackingException if a sync tracking with the primary key could not be found
	*/
	public static SyncTracking[] findByF_CREATED_BETWEEN_PrevAndNext(
		long trackingId, Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence()
				   .findByF_CREATED_BETWEEN_PrevAndNext(trackingId, createDate,
			modifiedDate, orderByComparator);
	}

	/**
	* Removes all the sync trackings where createDate &ge; &#63; and modifiedDate &le; &#63; from the database.
	*
	* @param createDate the create date
	* @param modifiedDate the modified date
	*/
	public static void removeByF_CREATED_BETWEEN(Date createDate,
		Date modifiedDate) {
		getPersistence().removeByF_CREATED_BETWEEN(createDate, modifiedDate);
	}

	/**
	* Returns the number of sync trackings where createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @return the number of matching sync trackings
	*/
	public static int countByF_CREATED_BETWEEN(Date createDate,
		Date modifiedDate) {
		return getPersistence()
				   .countByF_CREATED_BETWEEN(createDate, modifiedDate);
	}

	/**
	* Returns all the sync trackings where dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @return the matching sync trackings
	*/
	public static List<SyncTracking> findByF_DOSSIERNO_CREATED_BETWEEN(
		String dossierNo, Date createDate, Date modifiedDate) {
		return getPersistence()
				   .findByF_DOSSIERNO_CREATED_BETWEEN(dossierNo, createDate,
			modifiedDate);
	}

	/**
	* Returns a range of all the sync trackings where dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @return the range of matching sync trackings
	*/
	public static List<SyncTracking> findByF_DOSSIERNO_CREATED_BETWEEN(
		String dossierNo, Date createDate, Date modifiedDate, int start, int end) {
		return getPersistence()
				   .findByF_DOSSIERNO_CREATED_BETWEEN(dossierNo, createDate,
			modifiedDate, start, end);
	}

	/**
	* Returns an ordered range of all the sync trackings where dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sync trackings
	*/
	public static List<SyncTracking> findByF_DOSSIERNO_CREATED_BETWEEN(
		String dossierNo, Date createDate, Date modifiedDate, int start,
		int end, OrderByComparator<SyncTracking> orderByComparator) {
		return getPersistence()
				   .findByF_DOSSIERNO_CREATED_BETWEEN(dossierNo, createDate,
			modifiedDate, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the sync trackings where dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sync trackings
	*/
	public static List<SyncTracking> findByF_DOSSIERNO_CREATED_BETWEEN(
		String dossierNo, Date createDate, Date modifiedDate, int start,
		int end, OrderByComparator<SyncTracking> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_DOSSIERNO_CREATED_BETWEEN(dossierNo, createDate,
			modifiedDate, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first sync tracking in the ordered set where dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public static SyncTracking findByF_DOSSIERNO_CREATED_BETWEEN_First(
		String dossierNo, Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence()
				   .findByF_DOSSIERNO_CREATED_BETWEEN_First(dossierNo,
			createDate, modifiedDate, orderByComparator);
	}

	/**
	* Returns the first sync tracking in the ordered set where dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public static SyncTracking fetchByF_DOSSIERNO_CREATED_BETWEEN_First(
		String dossierNo, Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator) {
		return getPersistence()
				   .fetchByF_DOSSIERNO_CREATED_BETWEEN_First(dossierNo,
			createDate, modifiedDate, orderByComparator);
	}

	/**
	* Returns the last sync tracking in the ordered set where dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public static SyncTracking findByF_DOSSIERNO_CREATED_BETWEEN_Last(
		String dossierNo, Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence()
				   .findByF_DOSSIERNO_CREATED_BETWEEN_Last(dossierNo,
			createDate, modifiedDate, orderByComparator);
	}

	/**
	* Returns the last sync tracking in the ordered set where dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public static SyncTracking fetchByF_DOSSIERNO_CREATED_BETWEEN_Last(
		String dossierNo, Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator) {
		return getPersistence()
				   .fetchByF_DOSSIERNO_CREATED_BETWEEN_Last(dossierNo,
			createDate, modifiedDate, orderByComparator);
	}

	/**
	* Returns the sync trackings before and after the current sync tracking in the ordered set where dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param trackingId the primary key of the current sync tracking
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync tracking
	* @throws NoSuchSyncTrackingException if a sync tracking with the primary key could not be found
	*/
	public static SyncTracking[] findByF_DOSSIERNO_CREATED_BETWEEN_PrevAndNext(
		long trackingId, String dossierNo, Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence()
				   .findByF_DOSSIERNO_CREATED_BETWEEN_PrevAndNext(trackingId,
			dossierNo, createDate, modifiedDate, orderByComparator);
	}

	/**
	* Removes all the sync trackings where dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63; from the database.
	*
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	*/
	public static void removeByF_DOSSIERNO_CREATED_BETWEEN(String dossierNo,
		Date createDate, Date modifiedDate) {
		getPersistence()
			.removeByF_DOSSIERNO_CREATED_BETWEEN(dossierNo, createDate,
			modifiedDate);
	}

	/**
	* Returns the number of sync trackings where dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @return the number of matching sync trackings
	*/
	public static int countByF_DOSSIERNO_CREATED_BETWEEN(String dossierNo,
		Date createDate, Date modifiedDate) {
		return getPersistence()
				   .countByF_DOSSIERNO_CREATED_BETWEEN(dossierNo, createDate,
			modifiedDate);
	}

	/**
	* Returns all the sync trackings where referenceUid = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param referenceUid the reference uid
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @return the matching sync trackings
	*/
	public static List<SyncTracking> findByF_REFERENCE_UID_CREATED_BETWEEN(
		String referenceUid, Date createDate, Date modifiedDate) {
		return getPersistence()
				   .findByF_REFERENCE_UID_CREATED_BETWEEN(referenceUid,
			createDate, modifiedDate);
	}

	/**
	* Returns a range of all the sync trackings where referenceUid = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param referenceUid the reference uid
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @return the range of matching sync trackings
	*/
	public static List<SyncTracking> findByF_REFERENCE_UID_CREATED_BETWEEN(
		String referenceUid, Date createDate, Date modifiedDate, int start,
		int end) {
		return getPersistence()
				   .findByF_REFERENCE_UID_CREATED_BETWEEN(referenceUid,
			createDate, modifiedDate, start, end);
	}

	/**
	* Returns an ordered range of all the sync trackings where referenceUid = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param referenceUid the reference uid
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sync trackings
	*/
	public static List<SyncTracking> findByF_REFERENCE_UID_CREATED_BETWEEN(
		String referenceUid, Date createDate, Date modifiedDate, int start,
		int end, OrderByComparator<SyncTracking> orderByComparator) {
		return getPersistence()
				   .findByF_REFERENCE_UID_CREATED_BETWEEN(referenceUid,
			createDate, modifiedDate, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the sync trackings where referenceUid = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param referenceUid the reference uid
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sync trackings
	*/
	public static List<SyncTracking> findByF_REFERENCE_UID_CREATED_BETWEEN(
		String referenceUid, Date createDate, Date modifiedDate, int start,
		int end, OrderByComparator<SyncTracking> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_REFERENCE_UID_CREATED_BETWEEN(referenceUid,
			createDate, modifiedDate, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first sync tracking in the ordered set where referenceUid = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param referenceUid the reference uid
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public static SyncTracking findByF_REFERENCE_UID_CREATED_BETWEEN_First(
		String referenceUid, Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence()
				   .findByF_REFERENCE_UID_CREATED_BETWEEN_First(referenceUid,
			createDate, modifiedDate, orderByComparator);
	}

	/**
	* Returns the first sync tracking in the ordered set where referenceUid = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param referenceUid the reference uid
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public static SyncTracking fetchByF_REFERENCE_UID_CREATED_BETWEEN_First(
		String referenceUid, Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator) {
		return getPersistence()
				   .fetchByF_REFERENCE_UID_CREATED_BETWEEN_First(referenceUid,
			createDate, modifiedDate, orderByComparator);
	}

	/**
	* Returns the last sync tracking in the ordered set where referenceUid = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param referenceUid the reference uid
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public static SyncTracking findByF_REFERENCE_UID_CREATED_BETWEEN_Last(
		String referenceUid, Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence()
				   .findByF_REFERENCE_UID_CREATED_BETWEEN_Last(referenceUid,
			createDate, modifiedDate, orderByComparator);
	}

	/**
	* Returns the last sync tracking in the ordered set where referenceUid = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param referenceUid the reference uid
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public static SyncTracking fetchByF_REFERENCE_UID_CREATED_BETWEEN_Last(
		String referenceUid, Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator) {
		return getPersistence()
				   .fetchByF_REFERENCE_UID_CREATED_BETWEEN_Last(referenceUid,
			createDate, modifiedDate, orderByComparator);
	}

	/**
	* Returns the sync trackings before and after the current sync tracking in the ordered set where referenceUid = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param trackingId the primary key of the current sync tracking
	* @param referenceUid the reference uid
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync tracking
	* @throws NoSuchSyncTrackingException if a sync tracking with the primary key could not be found
	*/
	public static SyncTracking[] findByF_REFERENCE_UID_CREATED_BETWEEN_PrevAndNext(
		long trackingId, String referenceUid, Date createDate,
		Date modifiedDate, OrderByComparator<SyncTracking> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence()
				   .findByF_REFERENCE_UID_CREATED_BETWEEN_PrevAndNext(trackingId,
			referenceUid, createDate, modifiedDate, orderByComparator);
	}

	/**
	* Removes all the sync trackings where referenceUid = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63; from the database.
	*
	* @param referenceUid the reference uid
	* @param createDate the create date
	* @param modifiedDate the modified date
	*/
	public static void removeByF_REFERENCE_UID_CREATED_BETWEEN(
		String referenceUid, Date createDate, Date modifiedDate) {
		getPersistence()
			.removeByF_REFERENCE_UID_CREATED_BETWEEN(referenceUid, createDate,
			modifiedDate);
	}

	/**
	* Returns the number of sync trackings where referenceUid = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param referenceUid the reference uid
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @return the number of matching sync trackings
	*/
	public static int countByF_REFERENCE_UID_CREATED_BETWEEN(
		String referenceUid, Date createDate, Date modifiedDate) {
		return getPersistence()
				   .countByF_REFERENCE_UID_CREATED_BETWEEN(referenceUid,
			createDate, modifiedDate);
	}

	/**
	* Returns all the sync trackings where serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param serviceCode the service code
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @return the matching sync trackings
	*/
	public static List<SyncTracking> findByF_SERVICECODE_CREATED_BETWEEN(
		String serviceCode, Date createDate, Date modifiedDate) {
		return getPersistence()
				   .findByF_SERVICECODE_CREATED_BETWEEN(serviceCode,
			createDate, modifiedDate);
	}

	/**
	* Returns a range of all the sync trackings where serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param serviceCode the service code
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @return the range of matching sync trackings
	*/
	public static List<SyncTracking> findByF_SERVICECODE_CREATED_BETWEEN(
		String serviceCode, Date createDate, Date modifiedDate, int start,
		int end) {
		return getPersistence()
				   .findByF_SERVICECODE_CREATED_BETWEEN(serviceCode,
			createDate, modifiedDate, start, end);
	}

	/**
	* Returns an ordered range of all the sync trackings where serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param serviceCode the service code
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sync trackings
	*/
	public static List<SyncTracking> findByF_SERVICECODE_CREATED_BETWEEN(
		String serviceCode, Date createDate, Date modifiedDate, int start,
		int end, OrderByComparator<SyncTracking> orderByComparator) {
		return getPersistence()
				   .findByF_SERVICECODE_CREATED_BETWEEN(serviceCode,
			createDate, modifiedDate, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the sync trackings where serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param serviceCode the service code
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sync trackings
	*/
	public static List<SyncTracking> findByF_SERVICECODE_CREATED_BETWEEN(
		String serviceCode, Date createDate, Date modifiedDate, int start,
		int end, OrderByComparator<SyncTracking> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_SERVICECODE_CREATED_BETWEEN(serviceCode,
			createDate, modifiedDate, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first sync tracking in the ordered set where serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param serviceCode the service code
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public static SyncTracking findByF_SERVICECODE_CREATED_BETWEEN_First(
		String serviceCode, Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence()
				   .findByF_SERVICECODE_CREATED_BETWEEN_First(serviceCode,
			createDate, modifiedDate, orderByComparator);
	}

	/**
	* Returns the first sync tracking in the ordered set where serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param serviceCode the service code
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public static SyncTracking fetchByF_SERVICECODE_CREATED_BETWEEN_First(
		String serviceCode, Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator) {
		return getPersistence()
				   .fetchByF_SERVICECODE_CREATED_BETWEEN_First(serviceCode,
			createDate, modifiedDate, orderByComparator);
	}

	/**
	* Returns the last sync tracking in the ordered set where serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param serviceCode the service code
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public static SyncTracking findByF_SERVICECODE_CREATED_BETWEEN_Last(
		String serviceCode, Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence()
				   .findByF_SERVICECODE_CREATED_BETWEEN_Last(serviceCode,
			createDate, modifiedDate, orderByComparator);
	}

	/**
	* Returns the last sync tracking in the ordered set where serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param serviceCode the service code
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public static SyncTracking fetchByF_SERVICECODE_CREATED_BETWEEN_Last(
		String serviceCode, Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator) {
		return getPersistence()
				   .fetchByF_SERVICECODE_CREATED_BETWEEN_Last(serviceCode,
			createDate, modifiedDate, orderByComparator);
	}

	/**
	* Returns the sync trackings before and after the current sync tracking in the ordered set where serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param trackingId the primary key of the current sync tracking
	* @param serviceCode the service code
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync tracking
	* @throws NoSuchSyncTrackingException if a sync tracking with the primary key could not be found
	*/
	public static SyncTracking[] findByF_SERVICECODE_CREATED_BETWEEN_PrevAndNext(
		long trackingId, String serviceCode, Date createDate,
		Date modifiedDate, OrderByComparator<SyncTracking> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence()
				   .findByF_SERVICECODE_CREATED_BETWEEN_PrevAndNext(trackingId,
			serviceCode, createDate, modifiedDate, orderByComparator);
	}

	/**
	* Removes all the sync trackings where serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63; from the database.
	*
	* @param serviceCode the service code
	* @param createDate the create date
	* @param modifiedDate the modified date
	*/
	public static void removeByF_SERVICECODE_CREATED_BETWEEN(
		String serviceCode, Date createDate, Date modifiedDate) {
		getPersistence()
			.removeByF_SERVICECODE_CREATED_BETWEEN(serviceCode, createDate,
			modifiedDate);
	}

	/**
	* Returns the number of sync trackings where serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param serviceCode the service code
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @return the number of matching sync trackings
	*/
	public static int countByF_SERVICECODE_CREATED_BETWEEN(String serviceCode,
		Date createDate, Date modifiedDate) {
		return getPersistence()
				   .countByF_SERVICECODE_CREATED_BETWEEN(serviceCode,
			createDate, modifiedDate);
	}

	/**
	* Returns all the sync trackings where serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param serviceCode the service code
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @return the matching sync trackings
	*/
	public static List<SyncTracking> findByF_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(
		String serviceCode, String dossierNo, Date createDate, Date modifiedDate) {
		return getPersistence()
				   .findByF_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(serviceCode,
			dossierNo, createDate, modifiedDate);
	}

	/**
	* Returns a range of all the sync trackings where serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param serviceCode the service code
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @return the range of matching sync trackings
	*/
	public static List<SyncTracking> findByF_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(
		String serviceCode, String dossierNo, Date createDate,
		Date modifiedDate, int start, int end) {
		return getPersistence()
				   .findByF_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(serviceCode,
			dossierNo, createDate, modifiedDate, start, end);
	}

	/**
	* Returns an ordered range of all the sync trackings where serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param serviceCode the service code
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sync trackings
	*/
	public static List<SyncTracking> findByF_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(
		String serviceCode, String dossierNo, Date createDate,
		Date modifiedDate, int start, int end,
		OrderByComparator<SyncTracking> orderByComparator) {
		return getPersistence()
				   .findByF_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(serviceCode,
			dossierNo, createDate, modifiedDate, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the sync trackings where serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param serviceCode the service code
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sync trackings
	*/
	public static List<SyncTracking> findByF_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(
		String serviceCode, String dossierNo, Date createDate,
		Date modifiedDate, int start, int end,
		OrderByComparator<SyncTracking> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(serviceCode,
			dossierNo, createDate, modifiedDate, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first sync tracking in the ordered set where serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param serviceCode the service code
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public static SyncTracking findByF_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_First(
		String serviceCode, String dossierNo, Date createDate,
		Date modifiedDate, OrderByComparator<SyncTracking> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence()
				   .findByF_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_First(serviceCode,
			dossierNo, createDate, modifiedDate, orderByComparator);
	}

	/**
	* Returns the first sync tracking in the ordered set where serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param serviceCode the service code
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public static SyncTracking fetchByF_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_First(
		String serviceCode, String dossierNo, Date createDate,
		Date modifiedDate, OrderByComparator<SyncTracking> orderByComparator) {
		return getPersistence()
				   .fetchByF_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_First(serviceCode,
			dossierNo, createDate, modifiedDate, orderByComparator);
	}

	/**
	* Returns the last sync tracking in the ordered set where serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param serviceCode the service code
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public static SyncTracking findByF_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_Last(
		String serviceCode, String dossierNo, Date createDate,
		Date modifiedDate, OrderByComparator<SyncTracking> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence()
				   .findByF_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_Last(serviceCode,
			dossierNo, createDate, modifiedDate, orderByComparator);
	}

	/**
	* Returns the last sync tracking in the ordered set where serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param serviceCode the service code
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public static SyncTracking fetchByF_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_Last(
		String serviceCode, String dossierNo, Date createDate,
		Date modifiedDate, OrderByComparator<SyncTracking> orderByComparator) {
		return getPersistence()
				   .fetchByF_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_Last(serviceCode,
			dossierNo, createDate, modifiedDate, orderByComparator);
	}

	/**
	* Returns the sync trackings before and after the current sync tracking in the ordered set where serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param trackingId the primary key of the current sync tracking
	* @param serviceCode the service code
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync tracking
	* @throws NoSuchSyncTrackingException if a sync tracking with the primary key could not be found
	*/
	public static SyncTracking[] findByF_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_PrevAndNext(
		long trackingId, String serviceCode, String dossierNo, Date createDate,
		Date modifiedDate, OrderByComparator<SyncTracking> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence()
				   .findByF_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_PrevAndNext(trackingId,
			serviceCode, dossierNo, createDate, modifiedDate, orderByComparator);
	}

	/**
	* Removes all the sync trackings where serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63; from the database.
	*
	* @param serviceCode the service code
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	*/
	public static void removeByF_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(
		String serviceCode, String dossierNo, Date createDate, Date modifiedDate) {
		getPersistence()
			.removeByF_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(serviceCode,
			dossierNo, createDate, modifiedDate);
	}

	/**
	* Returns the number of sync trackings where serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param serviceCode the service code
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @return the number of matching sync trackings
	*/
	public static int countByF_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(
		String serviceCode, String dossierNo, Date createDate, Date modifiedDate) {
		return getPersistence()
				   .countByF_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(serviceCode,
			dossierNo, createDate, modifiedDate);
	}

	/**
	* Returns all the sync trackings where api = &#63; and serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param api the api
	* @param serviceCode the service code
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @return the matching sync trackings
	*/
	public static List<SyncTracking> findByF_API_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(
		String api, String serviceCode, String dossierNo, Date createDate,
		Date modifiedDate) {
		return getPersistence()
				   .findByF_API_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(api,
			serviceCode, dossierNo, createDate, modifiedDate);
	}

	/**
	* Returns a range of all the sync trackings where api = &#63; and serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param api the api
	* @param serviceCode the service code
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @return the range of matching sync trackings
	*/
	public static List<SyncTracking> findByF_API_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(
		String api, String serviceCode, String dossierNo, Date createDate,
		Date modifiedDate, int start, int end) {
		return getPersistence()
				   .findByF_API_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(api,
			serviceCode, dossierNo, createDate, modifiedDate, start, end);
	}

	/**
	* Returns an ordered range of all the sync trackings where api = &#63; and serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param api the api
	* @param serviceCode the service code
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sync trackings
	*/
	public static List<SyncTracking> findByF_API_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(
		String api, String serviceCode, String dossierNo, Date createDate,
		Date modifiedDate, int start, int end,
		OrderByComparator<SyncTracking> orderByComparator) {
		return getPersistence()
				   .findByF_API_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(api,
			serviceCode, dossierNo, createDate, modifiedDate, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the sync trackings where api = &#63; and serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param api the api
	* @param serviceCode the service code
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sync trackings
	*/
	public static List<SyncTracking> findByF_API_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(
		String api, String serviceCode, String dossierNo, Date createDate,
		Date modifiedDate, int start, int end,
		OrderByComparator<SyncTracking> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_API_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(api,
			serviceCode, dossierNo, createDate, modifiedDate, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first sync tracking in the ordered set where api = &#63; and serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param api the api
	* @param serviceCode the service code
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public static SyncTracking findByF_API_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_First(
		String api, String serviceCode, String dossierNo, Date createDate,
		Date modifiedDate, OrderByComparator<SyncTracking> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence()
				   .findByF_API_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_First(api,
			serviceCode, dossierNo, createDate, modifiedDate, orderByComparator);
	}

	/**
	* Returns the first sync tracking in the ordered set where api = &#63; and serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param api the api
	* @param serviceCode the service code
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public static SyncTracking fetchByF_API_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_First(
		String api, String serviceCode, String dossierNo, Date createDate,
		Date modifiedDate, OrderByComparator<SyncTracking> orderByComparator) {
		return getPersistence()
				   .fetchByF_API_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_First(api,
			serviceCode, dossierNo, createDate, modifiedDate, orderByComparator);
	}

	/**
	* Returns the last sync tracking in the ordered set where api = &#63; and serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param api the api
	* @param serviceCode the service code
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public static SyncTracking findByF_API_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_Last(
		String api, String serviceCode, String dossierNo, Date createDate,
		Date modifiedDate, OrderByComparator<SyncTracking> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence()
				   .findByF_API_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_Last(api,
			serviceCode, dossierNo, createDate, modifiedDate, orderByComparator);
	}

	/**
	* Returns the last sync tracking in the ordered set where api = &#63; and serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param api the api
	* @param serviceCode the service code
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public static SyncTracking fetchByF_API_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_Last(
		String api, String serviceCode, String dossierNo, Date createDate,
		Date modifiedDate, OrderByComparator<SyncTracking> orderByComparator) {
		return getPersistence()
				   .fetchByF_API_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_Last(api,
			serviceCode, dossierNo, createDate, modifiedDate, orderByComparator);
	}

	/**
	* Returns the sync trackings before and after the current sync tracking in the ordered set where api = &#63; and serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param trackingId the primary key of the current sync tracking
	* @param api the api
	* @param serviceCode the service code
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync tracking
	* @throws NoSuchSyncTrackingException if a sync tracking with the primary key could not be found
	*/
	public static SyncTracking[] findByF_API_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_PrevAndNext(
		long trackingId, String api, String serviceCode, String dossierNo,
		Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence()
				   .findByF_API_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_PrevAndNext(trackingId,
			api, serviceCode, dossierNo, createDate, modifiedDate,
			orderByComparator);
	}

	/**
	* Removes all the sync trackings where api = &#63; and serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63; from the database.
	*
	* @param api the api
	* @param serviceCode the service code
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	*/
	public static void removeByF_API_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(
		String api, String serviceCode, String dossierNo, Date createDate,
		Date modifiedDate) {
		getPersistence()
			.removeByF_API_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(api,
			serviceCode, dossierNo, createDate, modifiedDate);
	}

	/**
	* Returns the number of sync trackings where api = &#63; and serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param api the api
	* @param serviceCode the service code
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @return the number of matching sync trackings
	*/
	public static int countByF_API_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(
		String api, String serviceCode, String dossierNo, Date createDate,
		Date modifiedDate) {
		return getPersistence()
				   .countByF_API_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(api,
			serviceCode, dossierNo, createDate, modifiedDate);
	}

	/**
	* Returns all the sync trackings where api = &#63; and serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param api the api
	* @param serviceCode the service code
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @return the matching sync trackings
	*/
	public static List<SyncTracking> findByF_API_SERVICECODE_CREATED_BETWEEN(
		String api, String serviceCode, Date createDate, Date modifiedDate) {
		return getPersistence()
				   .findByF_API_SERVICECODE_CREATED_BETWEEN(api, serviceCode,
			createDate, modifiedDate);
	}

	/**
	* Returns a range of all the sync trackings where api = &#63; and serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param api the api
	* @param serviceCode the service code
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @return the range of matching sync trackings
	*/
	public static List<SyncTracking> findByF_API_SERVICECODE_CREATED_BETWEEN(
		String api, String serviceCode, Date createDate, Date modifiedDate,
		int start, int end) {
		return getPersistence()
				   .findByF_API_SERVICECODE_CREATED_BETWEEN(api, serviceCode,
			createDate, modifiedDate, start, end);
	}

	/**
	* Returns an ordered range of all the sync trackings where api = &#63; and serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param api the api
	* @param serviceCode the service code
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sync trackings
	*/
	public static List<SyncTracking> findByF_API_SERVICECODE_CREATED_BETWEEN(
		String api, String serviceCode, Date createDate, Date modifiedDate,
		int start, int end, OrderByComparator<SyncTracking> orderByComparator) {
		return getPersistence()
				   .findByF_API_SERVICECODE_CREATED_BETWEEN(api, serviceCode,
			createDate, modifiedDate, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the sync trackings where api = &#63; and serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param api the api
	* @param serviceCode the service code
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sync trackings
	*/
	public static List<SyncTracking> findByF_API_SERVICECODE_CREATED_BETWEEN(
		String api, String serviceCode, Date createDate, Date modifiedDate,
		int start, int end, OrderByComparator<SyncTracking> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_API_SERVICECODE_CREATED_BETWEEN(api, serviceCode,
			createDate, modifiedDate, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first sync tracking in the ordered set where api = &#63; and serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param api the api
	* @param serviceCode the service code
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public static SyncTracking findByF_API_SERVICECODE_CREATED_BETWEEN_First(
		String api, String serviceCode, Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence()
				   .findByF_API_SERVICECODE_CREATED_BETWEEN_First(api,
			serviceCode, createDate, modifiedDate, orderByComparator);
	}

	/**
	* Returns the first sync tracking in the ordered set where api = &#63; and serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param api the api
	* @param serviceCode the service code
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public static SyncTracking fetchByF_API_SERVICECODE_CREATED_BETWEEN_First(
		String api, String serviceCode, Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator) {
		return getPersistence()
				   .fetchByF_API_SERVICECODE_CREATED_BETWEEN_First(api,
			serviceCode, createDate, modifiedDate, orderByComparator);
	}

	/**
	* Returns the last sync tracking in the ordered set where api = &#63; and serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param api the api
	* @param serviceCode the service code
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public static SyncTracking findByF_API_SERVICECODE_CREATED_BETWEEN_Last(
		String api, String serviceCode, Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence()
				   .findByF_API_SERVICECODE_CREATED_BETWEEN_Last(api,
			serviceCode, createDate, modifiedDate, orderByComparator);
	}

	/**
	* Returns the last sync tracking in the ordered set where api = &#63; and serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param api the api
	* @param serviceCode the service code
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public static SyncTracking fetchByF_API_SERVICECODE_CREATED_BETWEEN_Last(
		String api, String serviceCode, Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator) {
		return getPersistence()
				   .fetchByF_API_SERVICECODE_CREATED_BETWEEN_Last(api,
			serviceCode, createDate, modifiedDate, orderByComparator);
	}

	/**
	* Returns the sync trackings before and after the current sync tracking in the ordered set where api = &#63; and serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param trackingId the primary key of the current sync tracking
	* @param api the api
	* @param serviceCode the service code
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync tracking
	* @throws NoSuchSyncTrackingException if a sync tracking with the primary key could not be found
	*/
	public static SyncTracking[] findByF_API_SERVICECODE_CREATED_BETWEEN_PrevAndNext(
		long trackingId, String api, String serviceCode, Date createDate,
		Date modifiedDate, OrderByComparator<SyncTracking> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence()
				   .findByF_API_SERVICECODE_CREATED_BETWEEN_PrevAndNext(trackingId,
			api, serviceCode, createDate, modifiedDate, orderByComparator);
	}

	/**
	* Removes all the sync trackings where api = &#63; and serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63; from the database.
	*
	* @param api the api
	* @param serviceCode the service code
	* @param createDate the create date
	* @param modifiedDate the modified date
	*/
	public static void removeByF_API_SERVICECODE_CREATED_BETWEEN(String api,
		String serviceCode, Date createDate, Date modifiedDate) {
		getPersistence()
			.removeByF_API_SERVICECODE_CREATED_BETWEEN(api, serviceCode,
			createDate, modifiedDate);
	}

	/**
	* Returns the number of sync trackings where api = &#63; and serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param api the api
	* @param serviceCode the service code
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @return the number of matching sync trackings
	*/
	public static int countByF_API_SERVICECODE_CREATED_BETWEEN(String api,
		String serviceCode, Date createDate, Date modifiedDate) {
		return getPersistence()
				   .countByF_API_SERVICECODE_CREATED_BETWEEN(api, serviceCode,
			createDate, modifiedDate);
	}

	/**
	* Returns all the sync trackings where api = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param api the api
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @return the matching sync trackings
	*/
	public static List<SyncTracking> findByF_API_DOSSIERNO_CREATED_BETWEEN(
		String api, String dossierNo, Date createDate, Date modifiedDate) {
		return getPersistence()
				   .findByF_API_DOSSIERNO_CREATED_BETWEEN(api, dossierNo,
			createDate, modifiedDate);
	}

	/**
	* Returns a range of all the sync trackings where api = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param api the api
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @return the range of matching sync trackings
	*/
	public static List<SyncTracking> findByF_API_DOSSIERNO_CREATED_BETWEEN(
		String api, String dossierNo, Date createDate, Date modifiedDate,
		int start, int end) {
		return getPersistence()
				   .findByF_API_DOSSIERNO_CREATED_BETWEEN(api, dossierNo,
			createDate, modifiedDate, start, end);
	}

	/**
	* Returns an ordered range of all the sync trackings where api = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param api the api
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sync trackings
	*/
	public static List<SyncTracking> findByF_API_DOSSIERNO_CREATED_BETWEEN(
		String api, String dossierNo, Date createDate, Date modifiedDate,
		int start, int end, OrderByComparator<SyncTracking> orderByComparator) {
		return getPersistence()
				   .findByF_API_DOSSIERNO_CREATED_BETWEEN(api, dossierNo,
			createDate, modifiedDate, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the sync trackings where api = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param api the api
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sync trackings
	*/
	public static List<SyncTracking> findByF_API_DOSSIERNO_CREATED_BETWEEN(
		String api, String dossierNo, Date createDate, Date modifiedDate,
		int start, int end, OrderByComparator<SyncTracking> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_API_DOSSIERNO_CREATED_BETWEEN(api, dossierNo,
			createDate, modifiedDate, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first sync tracking in the ordered set where api = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param api the api
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public static SyncTracking findByF_API_DOSSIERNO_CREATED_BETWEEN_First(
		String api, String dossierNo, Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence()
				   .findByF_API_DOSSIERNO_CREATED_BETWEEN_First(api, dossierNo,
			createDate, modifiedDate, orderByComparator);
	}

	/**
	* Returns the first sync tracking in the ordered set where api = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param api the api
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public static SyncTracking fetchByF_API_DOSSIERNO_CREATED_BETWEEN_First(
		String api, String dossierNo, Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator) {
		return getPersistence()
				   .fetchByF_API_DOSSIERNO_CREATED_BETWEEN_First(api,
			dossierNo, createDate, modifiedDate, orderByComparator);
	}

	/**
	* Returns the last sync tracking in the ordered set where api = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param api the api
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public static SyncTracking findByF_API_DOSSIERNO_CREATED_BETWEEN_Last(
		String api, String dossierNo, Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence()
				   .findByF_API_DOSSIERNO_CREATED_BETWEEN_Last(api, dossierNo,
			createDate, modifiedDate, orderByComparator);
	}

	/**
	* Returns the last sync tracking in the ordered set where api = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param api the api
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public static SyncTracking fetchByF_API_DOSSIERNO_CREATED_BETWEEN_Last(
		String api, String dossierNo, Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator) {
		return getPersistence()
				   .fetchByF_API_DOSSIERNO_CREATED_BETWEEN_Last(api, dossierNo,
			createDate, modifiedDate, orderByComparator);
	}

	/**
	* Returns the sync trackings before and after the current sync tracking in the ordered set where api = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param trackingId the primary key of the current sync tracking
	* @param api the api
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync tracking
	* @throws NoSuchSyncTrackingException if a sync tracking with the primary key could not be found
	*/
	public static SyncTracking[] findByF_API_DOSSIERNO_CREATED_BETWEEN_PrevAndNext(
		long trackingId, String api, String dossierNo, Date createDate,
		Date modifiedDate, OrderByComparator<SyncTracking> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence()
				   .findByF_API_DOSSIERNO_CREATED_BETWEEN_PrevAndNext(trackingId,
			api, dossierNo, createDate, modifiedDate, orderByComparator);
	}

	/**
	* Removes all the sync trackings where api = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63; from the database.
	*
	* @param api the api
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	*/
	public static void removeByF_API_DOSSIERNO_CREATED_BETWEEN(String api,
		String dossierNo, Date createDate, Date modifiedDate) {
		getPersistence()
			.removeByF_API_DOSSIERNO_CREATED_BETWEEN(api, dossierNo,
			createDate, modifiedDate);
	}

	/**
	* Returns the number of sync trackings where api = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param api the api
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @return the number of matching sync trackings
	*/
	public static int countByF_API_DOSSIERNO_CREATED_BETWEEN(String api,
		String dossierNo, Date createDate, Date modifiedDate) {
		return getPersistence()
				   .countByF_API_DOSSIERNO_CREATED_BETWEEN(api, dossierNo,
			createDate, modifiedDate);
	}

	/**
	* Returns all the sync trackings where api = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param api the api
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @return the matching sync trackings
	*/
	public static List<SyncTracking> findByF_API_CREATED_BETWEEN(String api,
		Date createDate, Date modifiedDate) {
		return getPersistence()
				   .findByF_API_CREATED_BETWEEN(api, createDate, modifiedDate);
	}

	/**
	* Returns a range of all the sync trackings where api = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param api the api
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @return the range of matching sync trackings
	*/
	public static List<SyncTracking> findByF_API_CREATED_BETWEEN(String api,
		Date createDate, Date modifiedDate, int start, int end) {
		return getPersistence()
				   .findByF_API_CREATED_BETWEEN(api, createDate, modifiedDate,
			start, end);
	}

	/**
	* Returns an ordered range of all the sync trackings where api = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param api the api
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sync trackings
	*/
	public static List<SyncTracking> findByF_API_CREATED_BETWEEN(String api,
		Date createDate, Date modifiedDate, int start, int end,
		OrderByComparator<SyncTracking> orderByComparator) {
		return getPersistence()
				   .findByF_API_CREATED_BETWEEN(api, createDate, modifiedDate,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the sync trackings where api = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param api the api
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sync trackings
	*/
	public static List<SyncTracking> findByF_API_CREATED_BETWEEN(String api,
		Date createDate, Date modifiedDate, int start, int end,
		OrderByComparator<SyncTracking> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_API_CREATED_BETWEEN(api, createDate, modifiedDate,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first sync tracking in the ordered set where api = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param api the api
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public static SyncTracking findByF_API_CREATED_BETWEEN_First(String api,
		Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence()
				   .findByF_API_CREATED_BETWEEN_First(api, createDate,
			modifiedDate, orderByComparator);
	}

	/**
	* Returns the first sync tracking in the ordered set where api = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param api the api
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public static SyncTracking fetchByF_API_CREATED_BETWEEN_First(String api,
		Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator) {
		return getPersistence()
				   .fetchByF_API_CREATED_BETWEEN_First(api, createDate,
			modifiedDate, orderByComparator);
	}

	/**
	* Returns the last sync tracking in the ordered set where api = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param api the api
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public static SyncTracking findByF_API_CREATED_BETWEEN_Last(String api,
		Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence()
				   .findByF_API_CREATED_BETWEEN_Last(api, createDate,
			modifiedDate, orderByComparator);
	}

	/**
	* Returns the last sync tracking in the ordered set where api = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param api the api
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public static SyncTracking fetchByF_API_CREATED_BETWEEN_Last(String api,
		Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator) {
		return getPersistence()
				   .fetchByF_API_CREATED_BETWEEN_Last(api, createDate,
			modifiedDate, orderByComparator);
	}

	/**
	* Returns the sync trackings before and after the current sync tracking in the ordered set where api = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param trackingId the primary key of the current sync tracking
	* @param api the api
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync tracking
	* @throws NoSuchSyncTrackingException if a sync tracking with the primary key could not be found
	*/
	public static SyncTracking[] findByF_API_CREATED_BETWEEN_PrevAndNext(
		long trackingId, String api, Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence()
				   .findByF_API_CREATED_BETWEEN_PrevAndNext(trackingId, api,
			createDate, modifiedDate, orderByComparator);
	}

	/**
	* Removes all the sync trackings where api = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63; from the database.
	*
	* @param api the api
	* @param createDate the create date
	* @param modifiedDate the modified date
	*/
	public static void removeByF_API_CREATED_BETWEEN(String api,
		Date createDate, Date modifiedDate) {
		getPersistence()
			.removeByF_API_CREATED_BETWEEN(api, createDate, modifiedDate);
	}

	/**
	* Returns the number of sync trackings where api = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param api the api
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @return the number of matching sync trackings
	*/
	public static int countByF_API_CREATED_BETWEEN(String api, Date createDate,
		Date modifiedDate) {
		return getPersistence()
				   .countByF_API_CREATED_BETWEEN(api, createDate, modifiedDate);
	}

	/**
	* Caches the sync tracking in the entity cache if it is enabled.
	*
	* @param syncTracking the sync tracking
	*/
	public static void cacheResult(SyncTracking syncTracking) {
		getPersistence().cacheResult(syncTracking);
	}

	/**
	* Caches the sync trackings in the entity cache if it is enabled.
	*
	* @param syncTrackings the sync trackings
	*/
	public static void cacheResult(List<SyncTracking> syncTrackings) {
		getPersistence().cacheResult(syncTrackings);
	}

	/**
	* Creates a new sync tracking with the primary key. Does not add the sync tracking to the database.
	*
	* @param trackingId the primary key for the new sync tracking
	* @return the new sync tracking
	*/
	public static SyncTracking create(long trackingId) {
		return getPersistence().create(trackingId);
	}

	/**
	* Removes the sync tracking with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trackingId the primary key of the sync tracking
	* @return the sync tracking that was removed
	* @throws NoSuchSyncTrackingException if a sync tracking with the primary key could not be found
	*/
	public static SyncTracking remove(long trackingId)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence().remove(trackingId);
	}

	public static SyncTracking updateImpl(SyncTracking syncTracking) {
		return getPersistence().updateImpl(syncTracking);
	}

	/**
	* Returns the sync tracking with the primary key or throws a {@link NoSuchSyncTrackingException} if it could not be found.
	*
	* @param trackingId the primary key of the sync tracking
	* @return the sync tracking
	* @throws NoSuchSyncTrackingException if a sync tracking with the primary key could not be found
	*/
	public static SyncTracking findByPrimaryKey(long trackingId)
		throws org.opencps.adminconfig.exception.NoSuchSyncTrackingException {
		return getPersistence().findByPrimaryKey(trackingId);
	}

	/**
	* Returns the sync tracking with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param trackingId the primary key of the sync tracking
	* @return the sync tracking, or <code>null</code> if a sync tracking with the primary key could not be found
	*/
	public static SyncTracking fetchByPrimaryKey(long trackingId) {
		return getPersistence().fetchByPrimaryKey(trackingId);
	}

	public static java.util.Map<java.io.Serializable, SyncTracking> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the sync trackings.
	*
	* @return the sync trackings
	*/
	public static List<SyncTracking> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the sync trackings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @return the range of sync trackings
	*/
	public static List<SyncTracking> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the sync trackings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of sync trackings
	*/
	public static List<SyncTracking> findAll(int start, int end,
		OrderByComparator<SyncTracking> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the sync trackings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of sync trackings
	*/
	public static List<SyncTracking> findAll(int start, int end,
		OrderByComparator<SyncTracking> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the sync trackings from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of sync trackings.
	*
	* @return the number of sync trackings
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static SyncTrackingPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<SyncTrackingPersistence, SyncTrackingPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(SyncTrackingPersistence.class);

		ServiceTracker<SyncTrackingPersistence, SyncTrackingPersistence> serviceTracker =
			new ServiceTracker<SyncTrackingPersistence, SyncTrackingPersistence>(bundle.getBundleContext(),
				SyncTrackingPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}