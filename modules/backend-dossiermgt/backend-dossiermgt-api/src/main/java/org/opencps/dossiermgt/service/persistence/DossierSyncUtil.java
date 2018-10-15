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

import org.opencps.dossiermgt.model.DossierSync;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the dossier sync service. This utility wraps {@link org.opencps.dossiermgt.service.persistence.impl.DossierSyncPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see DossierSyncPersistence
 * @see org.opencps.dossiermgt.service.persistence.impl.DossierSyncPersistenceImpl
 * @generated
 */
@ProviderType
public class DossierSyncUtil {
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
	public static void clearCache(DossierSync dossierSync) {
		getPersistence().clearCache(dossierSync);
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
	public static List<DossierSync> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<DossierSync> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<DossierSync> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<DossierSync> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static DossierSync update(DossierSync dossierSync) {
		return getPersistence().update(dossierSync);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static DossierSync update(DossierSync dossierSync,
		ServiceContext serviceContext) {
		return getPersistence().update(dossierSync, serviceContext);
	}

	/**
	* Returns all the dossier syncs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching dossier syncs
	*/
	public static List<DossierSync> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the dossier syncs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier syncs
	* @param end the upper bound of the range of dossier syncs (not inclusive)
	* @return the range of matching dossier syncs
	*/
	public static List<DossierSync> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the dossier syncs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier syncs
	* @param end the upper bound of the range of dossier syncs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier syncs
	*/
	public static List<DossierSync> findByUuid(String uuid, int start, int end,
		OrderByComparator<DossierSync> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier syncs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier syncs
	* @param end the upper bound of the range of dossier syncs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier syncs
	*/
	public static List<DossierSync> findByUuid(String uuid, int start, int end,
		OrderByComparator<DossierSync> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first dossier sync in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier sync
	* @throws NoSuchDossierSyncException if a matching dossier sync could not be found
	*/
	public static DossierSync findByUuid_First(String uuid,
		OrderByComparator<DossierSync> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierSyncException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first dossier sync in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier sync, or <code>null</code> if a matching dossier sync could not be found
	*/
	public static DossierSync fetchByUuid_First(String uuid,
		OrderByComparator<DossierSync> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last dossier sync in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier sync
	* @throws NoSuchDossierSyncException if a matching dossier sync could not be found
	*/
	public static DossierSync findByUuid_Last(String uuid,
		OrderByComparator<DossierSync> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierSyncException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last dossier sync in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier sync, or <code>null</code> if a matching dossier sync could not be found
	*/
	public static DossierSync fetchByUuid_Last(String uuid,
		OrderByComparator<DossierSync> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the dossier syncs before and after the current dossier sync in the ordered set where uuid = &#63;.
	*
	* @param DossierSyncId the primary key of the current dossier sync
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier sync
	* @throws NoSuchDossierSyncException if a dossier sync with the primary key could not be found
	*/
	public static DossierSync[] findByUuid_PrevAndNext(long DossierSyncId,
		String uuid, OrderByComparator<DossierSync> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierSyncException {
		return getPersistence()
				   .findByUuid_PrevAndNext(DossierSyncId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the dossier syncs where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of dossier syncs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching dossier syncs
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the dossier sync where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDossierSyncException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dossier sync
	* @throws NoSuchDossierSyncException if a matching dossier sync could not be found
	*/
	public static DossierSync findByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierSyncException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the dossier sync where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dossier sync, or <code>null</code> if a matching dossier sync could not be found
	*/
	public static DossierSync fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the dossier sync where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier sync, or <code>null</code> if a matching dossier sync could not be found
	*/
	public static DossierSync fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the dossier sync where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the dossier sync that was removed
	*/
	public static DossierSync removeByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierSyncException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of dossier syncs where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching dossier syncs
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the dossier syncs where state = &#63;.
	*
	* @param state the state
	* @return the matching dossier syncs
	*/
	public static List<DossierSync> findByST(int state) {
		return getPersistence().findByST(state);
	}

	/**
	* Returns a range of all the dossier syncs where state = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param state the state
	* @param start the lower bound of the range of dossier syncs
	* @param end the upper bound of the range of dossier syncs (not inclusive)
	* @return the range of matching dossier syncs
	*/
	public static List<DossierSync> findByST(int state, int start, int end) {
		return getPersistence().findByST(state, start, end);
	}

	/**
	* Returns an ordered range of all the dossier syncs where state = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param state the state
	* @param start the lower bound of the range of dossier syncs
	* @param end the upper bound of the range of dossier syncs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier syncs
	*/
	public static List<DossierSync> findByST(int state, int start, int end,
		OrderByComparator<DossierSync> orderByComparator) {
		return getPersistence().findByST(state, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier syncs where state = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param state the state
	* @param start the lower bound of the range of dossier syncs
	* @param end the upper bound of the range of dossier syncs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier syncs
	*/
	public static List<DossierSync> findByST(int state, int start, int end,
		OrderByComparator<DossierSync> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByST(state, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first dossier sync in the ordered set where state = &#63;.
	*
	* @param state the state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier sync
	* @throws NoSuchDossierSyncException if a matching dossier sync could not be found
	*/
	public static DossierSync findByST_First(int state,
		OrderByComparator<DossierSync> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierSyncException {
		return getPersistence().findByST_First(state, orderByComparator);
	}

	/**
	* Returns the first dossier sync in the ordered set where state = &#63;.
	*
	* @param state the state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier sync, or <code>null</code> if a matching dossier sync could not be found
	*/
	public static DossierSync fetchByST_First(int state,
		OrderByComparator<DossierSync> orderByComparator) {
		return getPersistence().fetchByST_First(state, orderByComparator);
	}

	/**
	* Returns the last dossier sync in the ordered set where state = &#63;.
	*
	* @param state the state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier sync
	* @throws NoSuchDossierSyncException if a matching dossier sync could not be found
	*/
	public static DossierSync findByST_Last(int state,
		OrderByComparator<DossierSync> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierSyncException {
		return getPersistence().findByST_Last(state, orderByComparator);
	}

	/**
	* Returns the last dossier sync in the ordered set where state = &#63;.
	*
	* @param state the state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier sync, or <code>null</code> if a matching dossier sync could not be found
	*/
	public static DossierSync fetchByST_Last(int state,
		OrderByComparator<DossierSync> orderByComparator) {
		return getPersistence().fetchByST_Last(state, orderByComparator);
	}

	/**
	* Returns the dossier syncs before and after the current dossier sync in the ordered set where state = &#63;.
	*
	* @param DossierSyncId the primary key of the current dossier sync
	* @param state the state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier sync
	* @throws NoSuchDossierSyncException if a dossier sync with the primary key could not be found
	*/
	public static DossierSync[] findByST_PrevAndNext(long DossierSyncId,
		int state, OrderByComparator<DossierSync> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierSyncException {
		return getPersistence()
				   .findByST_PrevAndNext(DossierSyncId, state, orderByComparator);
	}

	/**
	* Removes all the dossier syncs where state = &#63; from the database.
	*
	* @param state the state
	*/
	public static void removeByST(int state) {
		getPersistence().removeByST(state);
	}

	/**
	* Returns the number of dossier syncs where state = &#63;.
	*
	* @param state the state
	* @return the number of matching dossier syncs
	*/
	public static int countByST(int state) {
		return getPersistence().countByST(state);
	}

	/**
	* Returns all the dossier syncs where groupId = &#63; and dossierRefUid = &#63; and infoType = &#63;.
	*
	* @param groupId the group ID
	* @param dossierRefUid the dossier ref uid
	* @param infoType the info type
	* @return the matching dossier syncs
	*/
	public static List<DossierSync> findByDRID_IT(long groupId,
		String dossierRefUid, int infoType) {
		return getPersistence().findByDRID_IT(groupId, dossierRefUid, infoType);
	}

	/**
	* Returns a range of all the dossier syncs where groupId = &#63; and dossierRefUid = &#63; and infoType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierRefUid the dossier ref uid
	* @param infoType the info type
	* @param start the lower bound of the range of dossier syncs
	* @param end the upper bound of the range of dossier syncs (not inclusive)
	* @return the range of matching dossier syncs
	*/
	public static List<DossierSync> findByDRID_IT(long groupId,
		String dossierRefUid, int infoType, int start, int end) {
		return getPersistence()
				   .findByDRID_IT(groupId, dossierRefUid, infoType, start, end);
	}

	/**
	* Returns an ordered range of all the dossier syncs where groupId = &#63; and dossierRefUid = &#63; and infoType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierRefUid the dossier ref uid
	* @param infoType the info type
	* @param start the lower bound of the range of dossier syncs
	* @param end the upper bound of the range of dossier syncs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier syncs
	*/
	public static List<DossierSync> findByDRID_IT(long groupId,
		String dossierRefUid, int infoType, int start, int end,
		OrderByComparator<DossierSync> orderByComparator) {
		return getPersistence()
				   .findByDRID_IT(groupId, dossierRefUid, infoType, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier syncs where groupId = &#63; and dossierRefUid = &#63; and infoType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierRefUid the dossier ref uid
	* @param infoType the info type
	* @param start the lower bound of the range of dossier syncs
	* @param end the upper bound of the range of dossier syncs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier syncs
	*/
	public static List<DossierSync> findByDRID_IT(long groupId,
		String dossierRefUid, int infoType, int start, int end,
		OrderByComparator<DossierSync> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByDRID_IT(groupId, dossierRefUid, infoType, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier sync in the ordered set where groupId = &#63; and dossierRefUid = &#63; and infoType = &#63;.
	*
	* @param groupId the group ID
	* @param dossierRefUid the dossier ref uid
	* @param infoType the info type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier sync
	* @throws NoSuchDossierSyncException if a matching dossier sync could not be found
	*/
	public static DossierSync findByDRID_IT_First(long groupId,
		String dossierRefUid, int infoType,
		OrderByComparator<DossierSync> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierSyncException {
		return getPersistence()
				   .findByDRID_IT_First(groupId, dossierRefUid, infoType,
			orderByComparator);
	}

	/**
	* Returns the first dossier sync in the ordered set where groupId = &#63; and dossierRefUid = &#63; and infoType = &#63;.
	*
	* @param groupId the group ID
	* @param dossierRefUid the dossier ref uid
	* @param infoType the info type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier sync, or <code>null</code> if a matching dossier sync could not be found
	*/
	public static DossierSync fetchByDRID_IT_First(long groupId,
		String dossierRefUid, int infoType,
		OrderByComparator<DossierSync> orderByComparator) {
		return getPersistence()
				   .fetchByDRID_IT_First(groupId, dossierRefUid, infoType,
			orderByComparator);
	}

	/**
	* Returns the last dossier sync in the ordered set where groupId = &#63; and dossierRefUid = &#63; and infoType = &#63;.
	*
	* @param groupId the group ID
	* @param dossierRefUid the dossier ref uid
	* @param infoType the info type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier sync
	* @throws NoSuchDossierSyncException if a matching dossier sync could not be found
	*/
	public static DossierSync findByDRID_IT_Last(long groupId,
		String dossierRefUid, int infoType,
		OrderByComparator<DossierSync> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierSyncException {
		return getPersistence()
				   .findByDRID_IT_Last(groupId, dossierRefUid, infoType,
			orderByComparator);
	}

	/**
	* Returns the last dossier sync in the ordered set where groupId = &#63; and dossierRefUid = &#63; and infoType = &#63;.
	*
	* @param groupId the group ID
	* @param dossierRefUid the dossier ref uid
	* @param infoType the info type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier sync, or <code>null</code> if a matching dossier sync could not be found
	*/
	public static DossierSync fetchByDRID_IT_Last(long groupId,
		String dossierRefUid, int infoType,
		OrderByComparator<DossierSync> orderByComparator) {
		return getPersistence()
				   .fetchByDRID_IT_Last(groupId, dossierRefUid, infoType,
			orderByComparator);
	}

	/**
	* Returns the dossier syncs before and after the current dossier sync in the ordered set where groupId = &#63; and dossierRefUid = &#63; and infoType = &#63;.
	*
	* @param DossierSyncId the primary key of the current dossier sync
	* @param groupId the group ID
	* @param dossierRefUid the dossier ref uid
	* @param infoType the info type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier sync
	* @throws NoSuchDossierSyncException if a dossier sync with the primary key could not be found
	*/
	public static DossierSync[] findByDRID_IT_PrevAndNext(long DossierSyncId,
		long groupId, String dossierRefUid, int infoType,
		OrderByComparator<DossierSync> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierSyncException {
		return getPersistence()
				   .findByDRID_IT_PrevAndNext(DossierSyncId, groupId,
			dossierRefUid, infoType, orderByComparator);
	}

	/**
	* Returns all the dossier syncs where groupId = &#63; and dossierRefUid = &#63; and infoType = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierRefUid the dossier ref uid
	* @param infoTypes the info types
	* @return the matching dossier syncs
	*/
	public static List<DossierSync> findByDRID_IT(long groupId,
		String dossierRefUid, int[] infoTypes) {
		return getPersistence().findByDRID_IT(groupId, dossierRefUid, infoTypes);
	}

	/**
	* Returns a range of all the dossier syncs where groupId = &#63; and dossierRefUid = &#63; and infoType = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierRefUid the dossier ref uid
	* @param infoTypes the info types
	* @param start the lower bound of the range of dossier syncs
	* @param end the upper bound of the range of dossier syncs (not inclusive)
	* @return the range of matching dossier syncs
	*/
	public static List<DossierSync> findByDRID_IT(long groupId,
		String dossierRefUid, int[] infoTypes, int start, int end) {
		return getPersistence()
				   .findByDRID_IT(groupId, dossierRefUid, infoTypes, start, end);
	}

	/**
	* Returns an ordered range of all the dossier syncs where groupId = &#63; and dossierRefUid = &#63; and infoType = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierRefUid the dossier ref uid
	* @param infoTypes the info types
	* @param start the lower bound of the range of dossier syncs
	* @param end the upper bound of the range of dossier syncs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier syncs
	*/
	public static List<DossierSync> findByDRID_IT(long groupId,
		String dossierRefUid, int[] infoTypes, int start, int end,
		OrderByComparator<DossierSync> orderByComparator) {
		return getPersistence()
				   .findByDRID_IT(groupId, dossierRefUid, infoTypes, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier syncs where groupId = &#63; and dossierRefUid = &#63; and infoType = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierRefUid the dossier ref uid
	* @param infoType the info type
	* @param start the lower bound of the range of dossier syncs
	* @param end the upper bound of the range of dossier syncs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier syncs
	*/
	public static List<DossierSync> findByDRID_IT(long groupId,
		String dossierRefUid, int[] infoTypes, int start, int end,
		OrderByComparator<DossierSync> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByDRID_IT(groupId, dossierRefUid, infoTypes, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the dossier syncs where groupId = &#63; and dossierRefUid = &#63; and infoType = &#63; from the database.
	*
	* @param groupId the group ID
	* @param dossierRefUid the dossier ref uid
	* @param infoType the info type
	*/
	public static void removeByDRID_IT(long groupId, String dossierRefUid,
		int infoType) {
		getPersistence().removeByDRID_IT(groupId, dossierRefUid, infoType);
	}

	/**
	* Returns the number of dossier syncs where groupId = &#63; and dossierRefUid = &#63; and infoType = &#63;.
	*
	* @param groupId the group ID
	* @param dossierRefUid the dossier ref uid
	* @param infoType the info type
	* @return the number of matching dossier syncs
	*/
	public static int countByDRID_IT(long groupId, String dossierRefUid,
		int infoType) {
		return getPersistence().countByDRID_IT(groupId, dossierRefUid, infoType);
	}

	/**
	* Returns the number of dossier syncs where groupId = &#63; and dossierRefUid = &#63; and infoType = any &#63;.
	*
	* @param groupId the group ID
	* @param dossierRefUid the dossier ref uid
	* @param infoTypes the info types
	* @return the number of matching dossier syncs
	*/
	public static int countByDRID_IT(long groupId, String dossierRefUid,
		int[] infoTypes) {
		return getPersistence().countByDRID_IT(groupId, dossierRefUid, infoTypes);
	}

	/**
	* Returns all the dossier syncs where groupId = &#63; and actionCode = &#63; and syncType = &#63; and infoType = &#63;.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param syncType the sync type
	* @param infoType the info type
	* @return the matching dossier syncs
	*/
	public static List<DossierSync> findByG_AC_ST_IT(long groupId,
		String actionCode, int syncType, int infoType) {
		return getPersistence()
				   .findByG_AC_ST_IT(groupId, actionCode, syncType, infoType);
	}

	/**
	* Returns a range of all the dossier syncs where groupId = &#63; and actionCode = &#63; and syncType = &#63; and infoType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param syncType the sync type
	* @param infoType the info type
	* @param start the lower bound of the range of dossier syncs
	* @param end the upper bound of the range of dossier syncs (not inclusive)
	* @return the range of matching dossier syncs
	*/
	public static List<DossierSync> findByG_AC_ST_IT(long groupId,
		String actionCode, int syncType, int infoType, int start, int end) {
		return getPersistence()
				   .findByG_AC_ST_IT(groupId, actionCode, syncType, infoType,
			start, end);
	}

	/**
	* Returns an ordered range of all the dossier syncs where groupId = &#63; and actionCode = &#63; and syncType = &#63; and infoType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param syncType the sync type
	* @param infoType the info type
	* @param start the lower bound of the range of dossier syncs
	* @param end the upper bound of the range of dossier syncs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier syncs
	*/
	public static List<DossierSync> findByG_AC_ST_IT(long groupId,
		String actionCode, int syncType, int infoType, int start, int end,
		OrderByComparator<DossierSync> orderByComparator) {
		return getPersistence()
				   .findByG_AC_ST_IT(groupId, actionCode, syncType, infoType,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier syncs where groupId = &#63; and actionCode = &#63; and syncType = &#63; and infoType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param syncType the sync type
	* @param infoType the info type
	* @param start the lower bound of the range of dossier syncs
	* @param end the upper bound of the range of dossier syncs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier syncs
	*/
	public static List<DossierSync> findByG_AC_ST_IT(long groupId,
		String actionCode, int syncType, int infoType, int start, int end,
		OrderByComparator<DossierSync> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_AC_ST_IT(groupId, actionCode, syncType, infoType,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier sync in the ordered set where groupId = &#63; and actionCode = &#63; and syncType = &#63; and infoType = &#63;.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param syncType the sync type
	* @param infoType the info type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier sync
	* @throws NoSuchDossierSyncException if a matching dossier sync could not be found
	*/
	public static DossierSync findByG_AC_ST_IT_First(long groupId,
		String actionCode, int syncType, int infoType,
		OrderByComparator<DossierSync> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierSyncException {
		return getPersistence()
				   .findByG_AC_ST_IT_First(groupId, actionCode, syncType,
			infoType, orderByComparator);
	}

	/**
	* Returns the first dossier sync in the ordered set where groupId = &#63; and actionCode = &#63; and syncType = &#63; and infoType = &#63;.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param syncType the sync type
	* @param infoType the info type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier sync, or <code>null</code> if a matching dossier sync could not be found
	*/
	public static DossierSync fetchByG_AC_ST_IT_First(long groupId,
		String actionCode, int syncType, int infoType,
		OrderByComparator<DossierSync> orderByComparator) {
		return getPersistence()
				   .fetchByG_AC_ST_IT_First(groupId, actionCode, syncType,
			infoType, orderByComparator);
	}

	/**
	* Returns the last dossier sync in the ordered set where groupId = &#63; and actionCode = &#63; and syncType = &#63; and infoType = &#63;.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param syncType the sync type
	* @param infoType the info type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier sync
	* @throws NoSuchDossierSyncException if a matching dossier sync could not be found
	*/
	public static DossierSync findByG_AC_ST_IT_Last(long groupId,
		String actionCode, int syncType, int infoType,
		OrderByComparator<DossierSync> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierSyncException {
		return getPersistence()
				   .findByG_AC_ST_IT_Last(groupId, actionCode, syncType,
			infoType, orderByComparator);
	}

	/**
	* Returns the last dossier sync in the ordered set where groupId = &#63; and actionCode = &#63; and syncType = &#63; and infoType = &#63;.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param syncType the sync type
	* @param infoType the info type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier sync, or <code>null</code> if a matching dossier sync could not be found
	*/
	public static DossierSync fetchByG_AC_ST_IT_Last(long groupId,
		String actionCode, int syncType, int infoType,
		OrderByComparator<DossierSync> orderByComparator) {
		return getPersistence()
				   .fetchByG_AC_ST_IT_Last(groupId, actionCode, syncType,
			infoType, orderByComparator);
	}

	/**
	* Returns the dossier syncs before and after the current dossier sync in the ordered set where groupId = &#63; and actionCode = &#63; and syncType = &#63; and infoType = &#63;.
	*
	* @param DossierSyncId the primary key of the current dossier sync
	* @param groupId the group ID
	* @param actionCode the action code
	* @param syncType the sync type
	* @param infoType the info type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier sync
	* @throws NoSuchDossierSyncException if a dossier sync with the primary key could not be found
	*/
	public static DossierSync[] findByG_AC_ST_IT_PrevAndNext(
		long DossierSyncId, long groupId, String actionCode, int syncType,
		int infoType, OrderByComparator<DossierSync> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierSyncException {
		return getPersistence()
				   .findByG_AC_ST_IT_PrevAndNext(DossierSyncId, groupId,
			actionCode, syncType, infoType, orderByComparator);
	}

	/**
	* Returns all the dossier syncs where groupId = &#63; and actionCode = &#63; and syncType = &#63; and infoType = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param syncType the sync type
	* @param infoTypes the info types
	* @return the matching dossier syncs
	*/
	public static List<DossierSync> findByG_AC_ST_IT(long groupId,
		String actionCode, int syncType, int[] infoTypes) {
		return getPersistence()
				   .findByG_AC_ST_IT(groupId, actionCode, syncType, infoTypes);
	}

	/**
	* Returns a range of all the dossier syncs where groupId = &#63; and actionCode = &#63; and syncType = &#63; and infoType = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param syncType the sync type
	* @param infoTypes the info types
	* @param start the lower bound of the range of dossier syncs
	* @param end the upper bound of the range of dossier syncs (not inclusive)
	* @return the range of matching dossier syncs
	*/
	public static List<DossierSync> findByG_AC_ST_IT(long groupId,
		String actionCode, int syncType, int[] infoTypes, int start, int end) {
		return getPersistence()
				   .findByG_AC_ST_IT(groupId, actionCode, syncType, infoTypes,
			start, end);
	}

	/**
	* Returns an ordered range of all the dossier syncs where groupId = &#63; and actionCode = &#63; and syncType = &#63; and infoType = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param syncType the sync type
	* @param infoTypes the info types
	* @param start the lower bound of the range of dossier syncs
	* @param end the upper bound of the range of dossier syncs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier syncs
	*/
	public static List<DossierSync> findByG_AC_ST_IT(long groupId,
		String actionCode, int syncType, int[] infoTypes, int start, int end,
		OrderByComparator<DossierSync> orderByComparator) {
		return getPersistence()
				   .findByG_AC_ST_IT(groupId, actionCode, syncType, infoTypes,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier syncs where groupId = &#63; and actionCode = &#63; and syncType = &#63; and infoType = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param syncType the sync type
	* @param infoType the info type
	* @param start the lower bound of the range of dossier syncs
	* @param end the upper bound of the range of dossier syncs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier syncs
	*/
	public static List<DossierSync> findByG_AC_ST_IT(long groupId,
		String actionCode, int syncType, int[] infoTypes, int start, int end,
		OrderByComparator<DossierSync> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_AC_ST_IT(groupId, actionCode, syncType, infoTypes,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the dossier syncs where groupId = &#63; and actionCode = &#63; and syncType = &#63; and infoType = &#63; from the database.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param syncType the sync type
	* @param infoType the info type
	*/
	public static void removeByG_AC_ST_IT(long groupId, String actionCode,
		int syncType, int infoType) {
		getPersistence()
			.removeByG_AC_ST_IT(groupId, actionCode, syncType, infoType);
	}

	/**
	* Returns the number of dossier syncs where groupId = &#63; and actionCode = &#63; and syncType = &#63; and infoType = &#63;.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param syncType the sync type
	* @param infoType the info type
	* @return the number of matching dossier syncs
	*/
	public static int countByG_AC_ST_IT(long groupId, String actionCode,
		int syncType, int infoType) {
		return getPersistence()
				   .countByG_AC_ST_IT(groupId, actionCode, syncType, infoType);
	}

	/**
	* Returns the number of dossier syncs where groupId = &#63; and actionCode = &#63; and syncType = &#63; and infoType = any &#63;.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param syncType the sync type
	* @param infoTypes the info types
	* @return the number of matching dossier syncs
	*/
	public static int countByG_AC_ST_IT(long groupId, String actionCode,
		int syncType, int[] infoTypes) {
		return getPersistence()
				   .countByG_AC_ST_IT(groupId, actionCode, syncType, infoTypes);
	}

	/**
	* Caches the dossier sync in the entity cache if it is enabled.
	*
	* @param dossierSync the dossier sync
	*/
	public static void cacheResult(DossierSync dossierSync) {
		getPersistence().cacheResult(dossierSync);
	}

	/**
	* Caches the dossier syncs in the entity cache if it is enabled.
	*
	* @param dossierSyncs the dossier syncs
	*/
	public static void cacheResult(List<DossierSync> dossierSyncs) {
		getPersistence().cacheResult(dossierSyncs);
	}

	/**
	* Creates a new dossier sync with the primary key. Does not add the dossier sync to the database.
	*
	* @param DossierSyncId the primary key for the new dossier sync
	* @return the new dossier sync
	*/
	public static DossierSync create(long DossierSyncId) {
		return getPersistence().create(DossierSyncId);
	}

	/**
	* Removes the dossier sync with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param DossierSyncId the primary key of the dossier sync
	* @return the dossier sync that was removed
	* @throws NoSuchDossierSyncException if a dossier sync with the primary key could not be found
	*/
	public static DossierSync remove(long DossierSyncId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierSyncException {
		return getPersistence().remove(DossierSyncId);
	}

	public static DossierSync updateImpl(DossierSync dossierSync) {
		return getPersistence().updateImpl(dossierSync);
	}

	/**
	* Returns the dossier sync with the primary key or throws a {@link NoSuchDossierSyncException} if it could not be found.
	*
	* @param DossierSyncId the primary key of the dossier sync
	* @return the dossier sync
	* @throws NoSuchDossierSyncException if a dossier sync with the primary key could not be found
	*/
	public static DossierSync findByPrimaryKey(long DossierSyncId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierSyncException {
		return getPersistence().findByPrimaryKey(DossierSyncId);
	}

	/**
	* Returns the dossier sync with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param DossierSyncId the primary key of the dossier sync
	* @return the dossier sync, or <code>null</code> if a dossier sync with the primary key could not be found
	*/
	public static DossierSync fetchByPrimaryKey(long DossierSyncId) {
		return getPersistence().fetchByPrimaryKey(DossierSyncId);
	}

	public static java.util.Map<java.io.Serializable, DossierSync> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the dossier syncs.
	*
	* @return the dossier syncs
	*/
	public static List<DossierSync> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the dossier syncs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier syncs
	* @param end the upper bound of the range of dossier syncs (not inclusive)
	* @return the range of dossier syncs
	*/
	public static List<DossierSync> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the dossier syncs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier syncs
	* @param end the upper bound of the range of dossier syncs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of dossier syncs
	*/
	public static List<DossierSync> findAll(int start, int end,
		OrderByComparator<DossierSync> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier syncs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier syncs
	* @param end the upper bound of the range of dossier syncs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of dossier syncs
	*/
	public static List<DossierSync> findAll(int start, int end,
		OrderByComparator<DossierSync> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the dossier syncs from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of dossier syncs.
	*
	* @return the number of dossier syncs
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static DossierSyncPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DossierSyncPersistence, DossierSyncPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(DossierSyncPersistence.class);

		ServiceTracker<DossierSyncPersistence, DossierSyncPersistence> serviceTracker =
			new ServiceTracker<DossierSyncPersistence, DossierSyncPersistence>(bundle.getBundleContext(),
				DossierSyncPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}