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

import org.opencps.dossiermgt.model.DossierActionSync;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the dossier action sync service. This utility wraps {@link org.opencps.dossiermgt.service.persistence.impl.DossierActionSyncPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see DossierActionSyncPersistence
 * @see org.opencps.dossiermgt.service.persistence.impl.DossierActionSyncPersistenceImpl
 * @generated
 */
@ProviderType
public class DossierActionSyncUtil {
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
	public static void clearCache(DossierActionSync dossierActionSync) {
		getPersistence().clearCache(dossierActionSync);
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
	public static List<DossierActionSync> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<DossierActionSync> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<DossierActionSync> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<DossierActionSync> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static DossierActionSync update(DossierActionSync dossierActionSync) {
		return getPersistence().update(dossierActionSync);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static DossierActionSync update(
		DossierActionSync dossierActionSync, ServiceContext serviceContext) {
		return getPersistence().update(dossierActionSync, serviceContext);
	}

	/**
	* Returns all the dossier action syncs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching dossier action syncs
	*/
	public static List<DossierActionSync> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the dossier action syncs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier action syncs
	* @param end the upper bound of the range of dossier action syncs (not inclusive)
	* @return the range of matching dossier action syncs
	*/
	public static List<DossierActionSync> findByUuid(String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the dossier action syncs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier action syncs
	* @param end the upper bound of the range of dossier action syncs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier action syncs
	*/
	public static List<DossierActionSync> findByUuid(String uuid, int start,
		int end, OrderByComparator<DossierActionSync> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier action syncs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier action syncs
	* @param end the upper bound of the range of dossier action syncs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier action syncs
	*/
	public static List<DossierActionSync> findByUuid(String uuid, int start,
		int end, OrderByComparator<DossierActionSync> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first dossier action sync in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier action sync
	* @throws NoSuchDossierActionSyncException if a matching dossier action sync could not be found
	*/
	public static DossierActionSync findByUuid_First(String uuid,
		OrderByComparator<DossierActionSync> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionSyncException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first dossier action sync in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier action sync, or <code>null</code> if a matching dossier action sync could not be found
	*/
	public static DossierActionSync fetchByUuid_First(String uuid,
		OrderByComparator<DossierActionSync> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last dossier action sync in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier action sync
	* @throws NoSuchDossierActionSyncException if a matching dossier action sync could not be found
	*/
	public static DossierActionSync findByUuid_Last(String uuid,
		OrderByComparator<DossierActionSync> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionSyncException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last dossier action sync in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier action sync, or <code>null</code> if a matching dossier action sync could not be found
	*/
	public static DossierActionSync fetchByUuid_Last(String uuid,
		OrderByComparator<DossierActionSync> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the dossier action syncs before and after the current dossier action sync in the ordered set where uuid = &#63;.
	*
	* @param dossierActionSyncId the primary key of the current dossier action sync
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier action sync
	* @throws NoSuchDossierActionSyncException if a dossier action sync with the primary key could not be found
	*/
	public static DossierActionSync[] findByUuid_PrevAndNext(
		long dossierActionSyncId, String uuid,
		OrderByComparator<DossierActionSync> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionSyncException {
		return getPersistence()
				   .findByUuid_PrevAndNext(dossierActionSyncId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the dossier action syncs where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of dossier action syncs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching dossier action syncs
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the dossier action sync where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDossierActionSyncException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dossier action sync
	* @throws NoSuchDossierActionSyncException if a matching dossier action sync could not be found
	*/
	public static DossierActionSync findByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionSyncException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the dossier action sync where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dossier action sync, or <code>null</code> if a matching dossier action sync could not be found
	*/
	public static DossierActionSync fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the dossier action sync where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier action sync, or <code>null</code> if a matching dossier action sync could not be found
	*/
	public static DossierActionSync fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the dossier action sync where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the dossier action sync that was removed
	*/
	public static DossierActionSync removeByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionSyncException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of dossier action syncs where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching dossier action syncs
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the dossier action syncs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching dossier action syncs
	*/
	public static List<DossierActionSync> findByUuid_C(String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the dossier action syncs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossier action syncs
	* @param end the upper bound of the range of dossier action syncs (not inclusive)
	* @return the range of matching dossier action syncs
	*/
	public static List<DossierActionSync> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the dossier action syncs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossier action syncs
	* @param end the upper bound of the range of dossier action syncs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier action syncs
	*/
	public static List<DossierActionSync> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<DossierActionSync> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier action syncs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossier action syncs
	* @param end the upper bound of the range of dossier action syncs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier action syncs
	*/
	public static List<DossierActionSync> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<DossierActionSync> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier action sync in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier action sync
	* @throws NoSuchDossierActionSyncException if a matching dossier action sync could not be found
	*/
	public static DossierActionSync findByUuid_C_First(String uuid,
		long companyId, OrderByComparator<DossierActionSync> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionSyncException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first dossier action sync in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier action sync, or <code>null</code> if a matching dossier action sync could not be found
	*/
	public static DossierActionSync fetchByUuid_C_First(String uuid,
		long companyId, OrderByComparator<DossierActionSync> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last dossier action sync in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier action sync
	* @throws NoSuchDossierActionSyncException if a matching dossier action sync could not be found
	*/
	public static DossierActionSync findByUuid_C_Last(String uuid,
		long companyId, OrderByComparator<DossierActionSync> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionSyncException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last dossier action sync in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier action sync, or <code>null</code> if a matching dossier action sync could not be found
	*/
	public static DossierActionSync fetchByUuid_C_Last(String uuid,
		long companyId, OrderByComparator<DossierActionSync> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the dossier action syncs before and after the current dossier action sync in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param dossierActionSyncId the primary key of the current dossier action sync
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier action sync
	* @throws NoSuchDossierActionSyncException if a dossier action sync with the primary key could not be found
	*/
	public static DossierActionSync[] findByUuid_C_PrevAndNext(
		long dossierActionSyncId, String uuid, long companyId,
		OrderByComparator<DossierActionSync> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionSyncException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(dossierActionSyncId, uuid,
			companyId, orderByComparator);
	}

	/**
	* Removes all the dossier action syncs where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of dossier action syncs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching dossier action syncs
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the dossier action syncs where dossierActionId = &#63;.
	*
	* @param dossierActionId the dossier action ID
	* @return the matching dossier action syncs
	*/
	public static List<DossierActionSync> findByDID(long dossierActionId) {
		return getPersistence().findByDID(dossierActionId);
	}

	/**
	* Returns a range of all the dossier action syncs where dossierActionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierActionId the dossier action ID
	* @param start the lower bound of the range of dossier action syncs
	* @param end the upper bound of the range of dossier action syncs (not inclusive)
	* @return the range of matching dossier action syncs
	*/
	public static List<DossierActionSync> findByDID(long dossierActionId,
		int start, int end) {
		return getPersistence().findByDID(dossierActionId, start, end);
	}

	/**
	* Returns an ordered range of all the dossier action syncs where dossierActionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierActionId the dossier action ID
	* @param start the lower bound of the range of dossier action syncs
	* @param end the upper bound of the range of dossier action syncs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier action syncs
	*/
	public static List<DossierActionSync> findByDID(long dossierActionId,
		int start, int end,
		OrderByComparator<DossierActionSync> orderByComparator) {
		return getPersistence()
				   .findByDID(dossierActionId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier action syncs where dossierActionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierActionId the dossier action ID
	* @param start the lower bound of the range of dossier action syncs
	* @param end the upper bound of the range of dossier action syncs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier action syncs
	*/
	public static List<DossierActionSync> findByDID(long dossierActionId,
		int start, int end,
		OrderByComparator<DossierActionSync> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByDID(dossierActionId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first dossier action sync in the ordered set where dossierActionId = &#63;.
	*
	* @param dossierActionId the dossier action ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier action sync
	* @throws NoSuchDossierActionSyncException if a matching dossier action sync could not be found
	*/
	public static DossierActionSync findByDID_First(long dossierActionId,
		OrderByComparator<DossierActionSync> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionSyncException {
		return getPersistence()
				   .findByDID_First(dossierActionId, orderByComparator);
	}

	/**
	* Returns the first dossier action sync in the ordered set where dossierActionId = &#63;.
	*
	* @param dossierActionId the dossier action ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier action sync, or <code>null</code> if a matching dossier action sync could not be found
	*/
	public static DossierActionSync fetchByDID_First(long dossierActionId,
		OrderByComparator<DossierActionSync> orderByComparator) {
		return getPersistence()
				   .fetchByDID_First(dossierActionId, orderByComparator);
	}

	/**
	* Returns the last dossier action sync in the ordered set where dossierActionId = &#63;.
	*
	* @param dossierActionId the dossier action ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier action sync
	* @throws NoSuchDossierActionSyncException if a matching dossier action sync could not be found
	*/
	public static DossierActionSync findByDID_Last(long dossierActionId,
		OrderByComparator<DossierActionSync> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionSyncException {
		return getPersistence()
				   .findByDID_Last(dossierActionId, orderByComparator);
	}

	/**
	* Returns the last dossier action sync in the ordered set where dossierActionId = &#63;.
	*
	* @param dossierActionId the dossier action ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier action sync, or <code>null</code> if a matching dossier action sync could not be found
	*/
	public static DossierActionSync fetchByDID_Last(long dossierActionId,
		OrderByComparator<DossierActionSync> orderByComparator) {
		return getPersistence()
				   .fetchByDID_Last(dossierActionId, orderByComparator);
	}

	/**
	* Returns the dossier action syncs before and after the current dossier action sync in the ordered set where dossierActionId = &#63;.
	*
	* @param dossierActionSyncId the primary key of the current dossier action sync
	* @param dossierActionId the dossier action ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier action sync
	* @throws NoSuchDossierActionSyncException if a dossier action sync with the primary key could not be found
	*/
	public static DossierActionSync[] findByDID_PrevAndNext(
		long dossierActionSyncId, long dossierActionId,
		OrderByComparator<DossierActionSync> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionSyncException {
		return getPersistence()
				   .findByDID_PrevAndNext(dossierActionSyncId, dossierActionId,
			orderByComparator);
	}

	/**
	* Removes all the dossier action syncs where dossierActionId = &#63; from the database.
	*
	* @param dossierActionId the dossier action ID
	*/
	public static void removeByDID(long dossierActionId) {
		getPersistence().removeByDID(dossierActionId);
	}

	/**
	* Returns the number of dossier action syncs where dossierActionId = &#63;.
	*
	* @param dossierActionId the dossier action ID
	* @return the number of matching dossier action syncs
	*/
	public static int countByDID(long dossierActionId) {
		return getPersistence().countByDID(dossierActionId);
	}

	/**
	* Caches the dossier action sync in the entity cache if it is enabled.
	*
	* @param dossierActionSync the dossier action sync
	*/
	public static void cacheResult(DossierActionSync dossierActionSync) {
		getPersistence().cacheResult(dossierActionSync);
	}

	/**
	* Caches the dossier action syncs in the entity cache if it is enabled.
	*
	* @param dossierActionSyncs the dossier action syncs
	*/
	public static void cacheResult(List<DossierActionSync> dossierActionSyncs) {
		getPersistence().cacheResult(dossierActionSyncs);
	}

	/**
	* Creates a new dossier action sync with the primary key. Does not add the dossier action sync to the database.
	*
	* @param dossierActionSyncId the primary key for the new dossier action sync
	* @return the new dossier action sync
	*/
	public static DossierActionSync create(long dossierActionSyncId) {
		return getPersistence().create(dossierActionSyncId);
	}

	/**
	* Removes the dossier action sync with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierActionSyncId the primary key of the dossier action sync
	* @return the dossier action sync that was removed
	* @throws NoSuchDossierActionSyncException if a dossier action sync with the primary key could not be found
	*/
	public static DossierActionSync remove(long dossierActionSyncId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionSyncException {
		return getPersistence().remove(dossierActionSyncId);
	}

	public static DossierActionSync updateImpl(
		DossierActionSync dossierActionSync) {
		return getPersistence().updateImpl(dossierActionSync);
	}

	/**
	* Returns the dossier action sync with the primary key or throws a {@link NoSuchDossierActionSyncException} if it could not be found.
	*
	* @param dossierActionSyncId the primary key of the dossier action sync
	* @return the dossier action sync
	* @throws NoSuchDossierActionSyncException if a dossier action sync with the primary key could not be found
	*/
	public static DossierActionSync findByPrimaryKey(long dossierActionSyncId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionSyncException {
		return getPersistence().findByPrimaryKey(dossierActionSyncId);
	}

	/**
	* Returns the dossier action sync with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param dossierActionSyncId the primary key of the dossier action sync
	* @return the dossier action sync, or <code>null</code> if a dossier action sync with the primary key could not be found
	*/
	public static DossierActionSync fetchByPrimaryKey(long dossierActionSyncId) {
		return getPersistence().fetchByPrimaryKey(dossierActionSyncId);
	}

	public static java.util.Map<java.io.Serializable, DossierActionSync> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the dossier action syncs.
	*
	* @return the dossier action syncs
	*/
	public static List<DossierActionSync> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the dossier action syncs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier action syncs
	* @param end the upper bound of the range of dossier action syncs (not inclusive)
	* @return the range of dossier action syncs
	*/
	public static List<DossierActionSync> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the dossier action syncs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier action syncs
	* @param end the upper bound of the range of dossier action syncs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of dossier action syncs
	*/
	public static List<DossierActionSync> findAll(int start, int end,
		OrderByComparator<DossierActionSync> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier action syncs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier action syncs
	* @param end the upper bound of the range of dossier action syncs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of dossier action syncs
	*/
	public static List<DossierActionSync> findAll(int start, int end,
		OrderByComparator<DossierActionSync> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the dossier action syncs from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of dossier action syncs.
	*
	* @return the number of dossier action syncs
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static DossierActionSyncPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DossierActionSyncPersistence, DossierActionSyncPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(DossierActionSyncPersistence.class);

		ServiceTracker<DossierActionSyncPersistence, DossierActionSyncPersistence> serviceTracker =
			new ServiceTracker<DossierActionSyncPersistence, DossierActionSyncPersistence>(bundle.getBundleContext(),
				DossierActionSyncPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}