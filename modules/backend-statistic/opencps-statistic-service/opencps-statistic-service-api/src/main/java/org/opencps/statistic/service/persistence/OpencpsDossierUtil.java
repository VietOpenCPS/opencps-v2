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

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.statistic.model.OpencpsDossier;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the opencps dossier service. This utility wraps {@link org.opencps.statistic.service.persistence.impl.OpencpsDossierPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see OpencpsDossierPersistence
 * @see org.opencps.statistic.service.persistence.impl.OpencpsDossierPersistenceImpl
 * @generated
 */
@ProviderType
public class OpencpsDossierUtil {
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
	public static void clearCache(OpencpsDossier opencpsDossier) {
		getPersistence().clearCache(opencpsDossier);
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
	public static List<OpencpsDossier> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<OpencpsDossier> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<OpencpsDossier> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<OpencpsDossier> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static OpencpsDossier update(OpencpsDossier opencpsDossier) {
		return getPersistence().update(opencpsDossier);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static OpencpsDossier update(OpencpsDossier opencpsDossier,
		ServiceContext serviceContext) {
		return getPersistence().update(opencpsDossier, serviceContext);
	}

	/**
	* Returns all the opencps dossiers where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching opencps dossiers
	*/
	public static List<OpencpsDossier> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the opencps dossiers where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of opencps dossiers
	* @param end the upper bound of the range of opencps dossiers (not inclusive)
	* @return the range of matching opencps dossiers
	*/
	public static List<OpencpsDossier> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the opencps dossiers where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of opencps dossiers
	* @param end the upper bound of the range of opencps dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossiers
	*/
	public static List<OpencpsDossier> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<OpencpsDossier> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps dossiers where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of opencps dossiers
	* @param end the upper bound of the range of opencps dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossiers
	*/
	public static List<OpencpsDossier> findByUuid(java.lang.String uuid,
		int start, int end,
		OrderByComparator<OpencpsDossier> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first opencps dossier in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier
	* @throws NoSuchOpencpsDossierException if a matching opencps dossier could not be found
	*/
	public static OpencpsDossier findByUuid_First(java.lang.String uuid,
		OrderByComparator<OpencpsDossier> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first opencps dossier in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier, or <code>null</code> if a matching opencps dossier could not be found
	*/
	public static OpencpsDossier fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<OpencpsDossier> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last opencps dossier in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier
	* @throws NoSuchOpencpsDossierException if a matching opencps dossier could not be found
	*/
	public static OpencpsDossier findByUuid_Last(java.lang.String uuid,
		OrderByComparator<OpencpsDossier> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last opencps dossier in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier, or <code>null</code> if a matching opencps dossier could not be found
	*/
	public static OpencpsDossier fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<OpencpsDossier> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the opencps dossiers before and after the current opencps dossier in the ordered set where uuid = &#63;.
	*
	* @param dossierId the primary key of the current opencps dossier
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps dossier
	* @throws NoSuchOpencpsDossierException if a opencps dossier with the primary key could not be found
	*/
	public static OpencpsDossier[] findByUuid_PrevAndNext(long dossierId,
		java.lang.String uuid,
		OrderByComparator<OpencpsDossier> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierException {
		return getPersistence()
				   .findByUuid_PrevAndNext(dossierId, uuid, orderByComparator);
	}

	/**
	* Removes all the opencps dossiers where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of opencps dossiers where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching opencps dossiers
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the opencps dossier where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchOpencpsDossierException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching opencps dossier
	* @throws NoSuchOpencpsDossierException if a matching opencps dossier could not be found
	*/
	public static OpencpsDossier findByUUID_G(java.lang.String uuid,
		long groupId)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the opencps dossier where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching opencps dossier, or <code>null</code> if a matching opencps dossier could not be found
	*/
	public static OpencpsDossier fetchByUUID_G(java.lang.String uuid,
		long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the opencps dossier where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching opencps dossier, or <code>null</code> if a matching opencps dossier could not be found
	*/
	public static OpencpsDossier fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the opencps dossier where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the opencps dossier that was removed
	*/
	public static OpencpsDossier removeByUUID_G(java.lang.String uuid,
		long groupId)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of opencps dossiers where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching opencps dossiers
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the opencps dossiers where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching opencps dossiers
	*/
	public static List<OpencpsDossier> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the opencps dossiers where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of opencps dossiers
	* @param end the upper bound of the range of opencps dossiers (not inclusive)
	* @return the range of matching opencps dossiers
	*/
	public static List<OpencpsDossier> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the opencps dossiers where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of opencps dossiers
	* @param end the upper bound of the range of opencps dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossiers
	*/
	public static List<OpencpsDossier> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<OpencpsDossier> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps dossiers where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of opencps dossiers
	* @param end the upper bound of the range of opencps dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossiers
	*/
	public static List<OpencpsDossier> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<OpencpsDossier> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first opencps dossier in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier
	* @throws NoSuchOpencpsDossierException if a matching opencps dossier could not be found
	*/
	public static OpencpsDossier findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<OpencpsDossier> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first opencps dossier in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier, or <code>null</code> if a matching opencps dossier could not be found
	*/
	public static OpencpsDossier fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<OpencpsDossier> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last opencps dossier in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier
	* @throws NoSuchOpencpsDossierException if a matching opencps dossier could not be found
	*/
	public static OpencpsDossier findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<OpencpsDossier> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last opencps dossier in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier, or <code>null</code> if a matching opencps dossier could not be found
	*/
	public static OpencpsDossier fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<OpencpsDossier> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the opencps dossiers before and after the current opencps dossier in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param dossierId the primary key of the current opencps dossier
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps dossier
	* @throws NoSuchOpencpsDossierException if a opencps dossier with the primary key could not be found
	*/
	public static OpencpsDossier[] findByUuid_C_PrevAndNext(long dossierId,
		java.lang.String uuid, long companyId,
		OrderByComparator<OpencpsDossier> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(dossierId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the opencps dossiers where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of opencps dossiers where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching opencps dossiers
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the opencps dossiers where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching opencps dossiers
	*/
	public static List<OpencpsDossier> findByG_(long groupId) {
		return getPersistence().findByG_(groupId);
	}

	/**
	* Returns a range of all the opencps dossiers where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of opencps dossiers
	* @param end the upper bound of the range of opencps dossiers (not inclusive)
	* @return the range of matching opencps dossiers
	*/
	public static List<OpencpsDossier> findByG_(long groupId, int start, int end) {
		return getPersistence().findByG_(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the opencps dossiers where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of opencps dossiers
	* @param end the upper bound of the range of opencps dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossiers
	*/
	public static List<OpencpsDossier> findByG_(long groupId, int start,
		int end, OrderByComparator<OpencpsDossier> orderByComparator) {
		return getPersistence().findByG_(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps dossiers where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of opencps dossiers
	* @param end the upper bound of the range of opencps dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossiers
	*/
	public static List<OpencpsDossier> findByG_(long groupId, int start,
		int end, OrderByComparator<OpencpsDossier> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first opencps dossier in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier
	* @throws NoSuchOpencpsDossierException if a matching opencps dossier could not be found
	*/
	public static OpencpsDossier findByG__First(long groupId,
		OrderByComparator<OpencpsDossier> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierException {
		return getPersistence().findByG__First(groupId, orderByComparator);
	}

	/**
	* Returns the first opencps dossier in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier, or <code>null</code> if a matching opencps dossier could not be found
	*/
	public static OpencpsDossier fetchByG__First(long groupId,
		OrderByComparator<OpencpsDossier> orderByComparator) {
		return getPersistence().fetchByG__First(groupId, orderByComparator);
	}

	/**
	* Returns the last opencps dossier in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier
	* @throws NoSuchOpencpsDossierException if a matching opencps dossier could not be found
	*/
	public static OpencpsDossier findByG__Last(long groupId,
		OrderByComparator<OpencpsDossier> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierException {
		return getPersistence().findByG__Last(groupId, orderByComparator);
	}

	/**
	* Returns the last opencps dossier in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier, or <code>null</code> if a matching opencps dossier could not be found
	*/
	public static OpencpsDossier fetchByG__Last(long groupId,
		OrderByComparator<OpencpsDossier> orderByComparator) {
		return getPersistence().fetchByG__Last(groupId, orderByComparator);
	}

	/**
	* Returns the opencps dossiers before and after the current opencps dossier in the ordered set where groupId = &#63;.
	*
	* @param dossierId the primary key of the current opencps dossier
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps dossier
	* @throws NoSuchOpencpsDossierException if a opencps dossier with the primary key could not be found
	*/
	public static OpencpsDossier[] findByG__PrevAndNext(long dossierId,
		long groupId, OrderByComparator<OpencpsDossier> orderByComparator)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierException {
		return getPersistence()
				   .findByG__PrevAndNext(dossierId, groupId, orderByComparator);
	}

	/**
	* Removes all the opencps dossiers where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByG_(long groupId) {
		getPersistence().removeByG_(groupId);
	}

	/**
	* Returns the number of opencps dossiers where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching opencps dossiers
	*/
	public static int countByG_(long groupId) {
		return getPersistence().countByG_(groupId);
	}

	/**
	* Caches the opencps dossier in the entity cache if it is enabled.
	*
	* @param opencpsDossier the opencps dossier
	*/
	public static void cacheResult(OpencpsDossier opencpsDossier) {
		getPersistence().cacheResult(opencpsDossier);
	}

	/**
	* Caches the opencps dossiers in the entity cache if it is enabled.
	*
	* @param opencpsDossiers the opencps dossiers
	*/
	public static void cacheResult(List<OpencpsDossier> opencpsDossiers) {
		getPersistence().cacheResult(opencpsDossiers);
	}

	/**
	* Creates a new opencps dossier with the primary key. Does not add the opencps dossier to the database.
	*
	* @param dossierId the primary key for the new opencps dossier
	* @return the new opencps dossier
	*/
	public static OpencpsDossier create(long dossierId) {
		return getPersistence().create(dossierId);
	}

	/**
	* Removes the opencps dossier with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierId the primary key of the opencps dossier
	* @return the opencps dossier that was removed
	* @throws NoSuchOpencpsDossierException if a opencps dossier with the primary key could not be found
	*/
	public static OpencpsDossier remove(long dossierId)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierException {
		return getPersistence().remove(dossierId);
	}

	public static OpencpsDossier updateImpl(OpencpsDossier opencpsDossier) {
		return getPersistence().updateImpl(opencpsDossier);
	}

	/**
	* Returns the opencps dossier with the primary key or throws a {@link NoSuchOpencpsDossierException} if it could not be found.
	*
	* @param dossierId the primary key of the opencps dossier
	* @return the opencps dossier
	* @throws NoSuchOpencpsDossierException if a opencps dossier with the primary key could not be found
	*/
	public static OpencpsDossier findByPrimaryKey(long dossierId)
		throws org.opencps.statistic.exception.NoSuchOpencpsDossierException {
		return getPersistence().findByPrimaryKey(dossierId);
	}

	/**
	* Returns the opencps dossier with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param dossierId the primary key of the opencps dossier
	* @return the opencps dossier, or <code>null</code> if a opencps dossier with the primary key could not be found
	*/
	public static OpencpsDossier fetchByPrimaryKey(long dossierId) {
		return getPersistence().fetchByPrimaryKey(dossierId);
	}

	public static java.util.Map<java.io.Serializable, OpencpsDossier> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the opencps dossiers.
	*
	* @return the opencps dossiers
	*/
	public static List<OpencpsDossier> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the opencps dossiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of opencps dossiers
	* @param end the upper bound of the range of opencps dossiers (not inclusive)
	* @return the range of opencps dossiers
	*/
	public static List<OpencpsDossier> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the opencps dossiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of opencps dossiers
	* @param end the upper bound of the range of opencps dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of opencps dossiers
	*/
	public static List<OpencpsDossier> findAll(int start, int end,
		OrderByComparator<OpencpsDossier> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the opencps dossiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of opencps dossiers
	* @param end the upper bound of the range of opencps dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of opencps dossiers
	*/
	public static List<OpencpsDossier> findAll(int start, int end,
		OrderByComparator<OpencpsDossier> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the opencps dossiers from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of opencps dossiers.
	*
	* @return the number of opencps dossiers
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static OpencpsDossierPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<OpencpsDossierPersistence, OpencpsDossierPersistence> _serviceTracker =
		ServiceTrackerFactory.open(OpencpsDossierPersistence.class);
}