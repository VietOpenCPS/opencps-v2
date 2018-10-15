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

import org.opencps.dossiermgt.model.DossierAction;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the dossier action service. This utility wraps {@link org.opencps.dossiermgt.service.persistence.impl.DossierActionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see DossierActionPersistence
 * @see org.opencps.dossiermgt.service.persistence.impl.DossierActionPersistenceImpl
 * @generated
 */
@ProviderType
public class DossierActionUtil {
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
	public static void clearCache(DossierAction dossierAction) {
		getPersistence().clearCache(dossierAction);
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
	public static List<DossierAction> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<DossierAction> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<DossierAction> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<DossierAction> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static DossierAction update(DossierAction dossierAction) {
		return getPersistence().update(dossierAction);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static DossierAction update(DossierAction dossierAction,
		ServiceContext serviceContext) {
		return getPersistence().update(dossierAction, serviceContext);
	}

	/**
	* Returns all the dossier actions where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching dossier actions
	*/
	public static List<DossierAction> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the dossier actions where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier actions
	* @param end the upper bound of the range of dossier actions (not inclusive)
	* @return the range of matching dossier actions
	*/
	public static List<DossierAction> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the dossier actions where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier actions
	* @param end the upper bound of the range of dossier actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier actions
	*/
	public static List<DossierAction> findByUuid(String uuid, int start,
		int end, OrderByComparator<DossierAction> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier actions where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier actions
	* @param end the upper bound of the range of dossier actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier actions
	*/
	public static List<DossierAction> findByUuid(String uuid, int start,
		int end, OrderByComparator<DossierAction> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first dossier action in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier action
	* @throws NoSuchDossierActionException if a matching dossier action could not be found
	*/
	public static DossierAction findByUuid_First(String uuid,
		OrderByComparator<DossierAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first dossier action in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier action, or <code>null</code> if a matching dossier action could not be found
	*/
	public static DossierAction fetchByUuid_First(String uuid,
		OrderByComparator<DossierAction> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last dossier action in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier action
	* @throws NoSuchDossierActionException if a matching dossier action could not be found
	*/
	public static DossierAction findByUuid_Last(String uuid,
		OrderByComparator<DossierAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last dossier action in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier action, or <code>null</code> if a matching dossier action could not be found
	*/
	public static DossierAction fetchByUuid_Last(String uuid,
		OrderByComparator<DossierAction> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the dossier actions before and after the current dossier action in the ordered set where uuid = &#63;.
	*
	* @param dossierActionId the primary key of the current dossier action
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier action
	* @throws NoSuchDossierActionException if a dossier action with the primary key could not be found
	*/
	public static DossierAction[] findByUuid_PrevAndNext(long dossierActionId,
		String uuid, OrderByComparator<DossierAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionException {
		return getPersistence()
				   .findByUuid_PrevAndNext(dossierActionId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the dossier actions where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of dossier actions where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching dossier actions
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the dossier action where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDossierActionException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dossier action
	* @throws NoSuchDossierActionException if a matching dossier action could not be found
	*/
	public static DossierAction findByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the dossier action where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dossier action, or <code>null</code> if a matching dossier action could not be found
	*/
	public static DossierAction fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the dossier action where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier action, or <code>null</code> if a matching dossier action could not be found
	*/
	public static DossierAction fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the dossier action where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the dossier action that was removed
	*/
	public static DossierAction removeByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of dossier actions where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching dossier actions
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the dossier actions where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching dossier actions
	*/
	public static List<DossierAction> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the dossier actions where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossier actions
	* @param end the upper bound of the range of dossier actions (not inclusive)
	* @return the range of matching dossier actions
	*/
	public static List<DossierAction> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the dossier actions where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossier actions
	* @param end the upper bound of the range of dossier actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier actions
	*/
	public static List<DossierAction> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<DossierAction> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier actions where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossier actions
	* @param end the upper bound of the range of dossier actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier actions
	*/
	public static List<DossierAction> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<DossierAction> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier action in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier action
	* @throws NoSuchDossierActionException if a matching dossier action could not be found
	*/
	public static DossierAction findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<DossierAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first dossier action in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier action, or <code>null</code> if a matching dossier action could not be found
	*/
	public static DossierAction fetchByUuid_C_First(String uuid,
		long companyId, OrderByComparator<DossierAction> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last dossier action in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier action
	* @throws NoSuchDossierActionException if a matching dossier action could not be found
	*/
	public static DossierAction findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DossierAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last dossier action in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier action, or <code>null</code> if a matching dossier action could not be found
	*/
	public static DossierAction fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DossierAction> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the dossier actions before and after the current dossier action in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param dossierActionId the primary key of the current dossier action
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier action
	* @throws NoSuchDossierActionException if a dossier action with the primary key could not be found
	*/
	public static DossierAction[] findByUuid_C_PrevAndNext(
		long dossierActionId, String uuid, long companyId,
		OrderByComparator<DossierAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(dossierActionId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the dossier actions where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of dossier actions where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching dossier actions
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns the dossier action where dossierId = &#63; and pending = &#63; or throws a {@link NoSuchDossierActionException} if it could not be found.
	*
	* @param dossierId the dossier ID
	* @param pending the pending
	* @return the matching dossier action
	* @throws NoSuchDossierActionException if a matching dossier action could not be found
	*/
	public static DossierAction findByDID_DPG(long dossierId, boolean pending)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionException {
		return getPersistence().findByDID_DPG(dossierId, pending);
	}

	/**
	* Returns the dossier action where dossierId = &#63; and pending = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param dossierId the dossier ID
	* @param pending the pending
	* @return the matching dossier action, or <code>null</code> if a matching dossier action could not be found
	*/
	public static DossierAction fetchByDID_DPG(long dossierId, boolean pending) {
		return getPersistence().fetchByDID_DPG(dossierId, pending);
	}

	/**
	* Returns the dossier action where dossierId = &#63; and pending = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param dossierId the dossier ID
	* @param pending the pending
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier action, or <code>null</code> if a matching dossier action could not be found
	*/
	public static DossierAction fetchByDID_DPG(long dossierId, boolean pending,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByDID_DPG(dossierId, pending, retrieveFromCache);
	}

	/**
	* Removes the dossier action where dossierId = &#63; and pending = &#63; from the database.
	*
	* @param dossierId the dossier ID
	* @param pending the pending
	* @return the dossier action that was removed
	*/
	public static DossierAction removeByDID_DPG(long dossierId, boolean pending)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionException {
		return getPersistence().removeByDID_DPG(dossierId, pending);
	}

	/**
	* Returns the number of dossier actions where dossierId = &#63; and pending = &#63;.
	*
	* @param dossierId the dossier ID
	* @param pending the pending
	* @return the number of matching dossier actions
	*/
	public static int countByDID_DPG(long dossierId, boolean pending) {
		return getPersistence().countByDID_DPG(dossierId, pending);
	}

	/**
	* Returns the dossier action where dossierId = &#63; and nextActionId = &#63; or throws a {@link NoSuchDossierActionException} if it could not be found.
	*
	* @param dossierId the dossier ID
	* @param nextActionId the next action ID
	* @return the matching dossier action
	* @throws NoSuchDossierActionException if a matching dossier action could not be found
	*/
	public static DossierAction findByDID_NACTID(long dossierId,
		long nextActionId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionException {
		return getPersistence().findByDID_NACTID(dossierId, nextActionId);
	}

	/**
	* Returns the dossier action where dossierId = &#63; and nextActionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param dossierId the dossier ID
	* @param nextActionId the next action ID
	* @return the matching dossier action, or <code>null</code> if a matching dossier action could not be found
	*/
	public static DossierAction fetchByDID_NACTID(long dossierId,
		long nextActionId) {
		return getPersistence().fetchByDID_NACTID(dossierId, nextActionId);
	}

	/**
	* Returns the dossier action where dossierId = &#63; and nextActionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param dossierId the dossier ID
	* @param nextActionId the next action ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier action, or <code>null</code> if a matching dossier action could not be found
	*/
	public static DossierAction fetchByDID_NACTID(long dossierId,
		long nextActionId, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByDID_NACTID(dossierId, nextActionId, retrieveFromCache);
	}

	/**
	* Removes the dossier action where dossierId = &#63; and nextActionId = &#63; from the database.
	*
	* @param dossierId the dossier ID
	* @param nextActionId the next action ID
	* @return the dossier action that was removed
	*/
	public static DossierAction removeByDID_NACTID(long dossierId,
		long nextActionId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionException {
		return getPersistence().removeByDID_NACTID(dossierId, nextActionId);
	}

	/**
	* Returns the number of dossier actions where dossierId = &#63; and nextActionId = &#63;.
	*
	* @param dossierId the dossier ID
	* @param nextActionId the next action ID
	* @return the number of matching dossier actions
	*/
	public static int countByDID_NACTID(long dossierId, long nextActionId) {
		return getPersistence().countByDID_NACTID(dossierId, nextActionId);
	}

	/**
	* Returns the dossier action where dossierId = &#63; and actionCode = &#63; or throws a {@link NoSuchDossierActionException} if it could not be found.
	*
	* @param dossierId the dossier ID
	* @param actionCode the action code
	* @return the matching dossier action
	* @throws NoSuchDossierActionException if a matching dossier action could not be found
	*/
	public static DossierAction findByDID_ACTC(long dossierId, String actionCode)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionException {
		return getPersistence().findByDID_ACTC(dossierId, actionCode);
	}

	/**
	* Returns the dossier action where dossierId = &#63; and actionCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param dossierId the dossier ID
	* @param actionCode the action code
	* @return the matching dossier action, or <code>null</code> if a matching dossier action could not be found
	*/
	public static DossierAction fetchByDID_ACTC(long dossierId,
		String actionCode) {
		return getPersistence().fetchByDID_ACTC(dossierId, actionCode);
	}

	/**
	* Returns the dossier action where dossierId = &#63; and actionCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param dossierId the dossier ID
	* @param actionCode the action code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier action, or <code>null</code> if a matching dossier action could not be found
	*/
	public static DossierAction fetchByDID_ACTC(long dossierId,
		String actionCode, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByDID_ACTC(dossierId, actionCode, retrieveFromCache);
	}

	/**
	* Removes the dossier action where dossierId = &#63; and actionCode = &#63; from the database.
	*
	* @param dossierId the dossier ID
	* @param actionCode the action code
	* @return the dossier action that was removed
	*/
	public static DossierAction removeByDID_ACTC(long dossierId,
		String actionCode)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionException {
		return getPersistence().removeByDID_ACTC(dossierId, actionCode);
	}

	/**
	* Returns the number of dossier actions where dossierId = &#63; and actionCode = &#63;.
	*
	* @param dossierId the dossier ID
	* @param actionCode the action code
	* @return the number of matching dossier actions
	*/
	public static int countByDID_ACTC(long dossierId, String actionCode) {
		return getPersistence().countByDID_ACTC(dossierId, actionCode);
	}

	/**
	* Returns the dossier action where dossierId = &#63; and userId = &#63; or throws a {@link NoSuchDossierActionException} if it could not be found.
	*
	* @param dossierId the dossier ID
	* @param userId the user ID
	* @return the matching dossier action
	* @throws NoSuchDossierActionException if a matching dossier action could not be found
	*/
	public static DossierAction findByDID_UID(long dossierId, long userId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionException {
		return getPersistence().findByDID_UID(dossierId, userId);
	}

	/**
	* Returns the dossier action where dossierId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param dossierId the dossier ID
	* @param userId the user ID
	* @return the matching dossier action, or <code>null</code> if a matching dossier action could not be found
	*/
	public static DossierAction fetchByDID_UID(long dossierId, long userId) {
		return getPersistence().fetchByDID_UID(dossierId, userId);
	}

	/**
	* Returns the dossier action where dossierId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param dossierId the dossier ID
	* @param userId the user ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier action, or <code>null</code> if a matching dossier action could not be found
	*/
	public static DossierAction fetchByDID_UID(long dossierId, long userId,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByDID_UID(dossierId, userId, retrieveFromCache);
	}

	/**
	* Removes the dossier action where dossierId = &#63; and userId = &#63; from the database.
	*
	* @param dossierId the dossier ID
	* @param userId the user ID
	* @return the dossier action that was removed
	*/
	public static DossierAction removeByDID_UID(long dossierId, long userId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionException {
		return getPersistence().removeByDID_UID(dossierId, userId);
	}

	/**
	* Returns the number of dossier actions where dossierId = &#63; and userId = &#63;.
	*
	* @param dossierId the dossier ID
	* @param userId the user ID
	* @return the number of matching dossier actions
	*/
	public static int countByDID_UID(long dossierId, long userId) {
		return getPersistence().countByDID_UID(dossierId, userId);
	}

	/**
	* Returns all the dossier actions where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @return the matching dossier actions
	*/
	public static List<DossierAction> findByDID(long dossierId) {
		return getPersistence().findByDID(dossierId);
	}

	/**
	* Returns a range of all the dossier actions where dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of dossier actions
	* @param end the upper bound of the range of dossier actions (not inclusive)
	* @return the range of matching dossier actions
	*/
	public static List<DossierAction> findByDID(long dossierId, int start,
		int end) {
		return getPersistence().findByDID(dossierId, start, end);
	}

	/**
	* Returns an ordered range of all the dossier actions where dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of dossier actions
	* @param end the upper bound of the range of dossier actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier actions
	*/
	public static List<DossierAction> findByDID(long dossierId, int start,
		int end, OrderByComparator<DossierAction> orderByComparator) {
		return getPersistence()
				   .findByDID(dossierId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier actions where dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of dossier actions
	* @param end the upper bound of the range of dossier actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier actions
	*/
	public static List<DossierAction> findByDID(long dossierId, int start,
		int end, OrderByComparator<DossierAction> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByDID(dossierId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first dossier action in the ordered set where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier action
	* @throws NoSuchDossierActionException if a matching dossier action could not be found
	*/
	public static DossierAction findByDID_First(long dossierId,
		OrderByComparator<DossierAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionException {
		return getPersistence().findByDID_First(dossierId, orderByComparator);
	}

	/**
	* Returns the first dossier action in the ordered set where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier action, or <code>null</code> if a matching dossier action could not be found
	*/
	public static DossierAction fetchByDID_First(long dossierId,
		OrderByComparator<DossierAction> orderByComparator) {
		return getPersistence().fetchByDID_First(dossierId, orderByComparator);
	}

	/**
	* Returns the last dossier action in the ordered set where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier action
	* @throws NoSuchDossierActionException if a matching dossier action could not be found
	*/
	public static DossierAction findByDID_Last(long dossierId,
		OrderByComparator<DossierAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionException {
		return getPersistence().findByDID_Last(dossierId, orderByComparator);
	}

	/**
	* Returns the last dossier action in the ordered set where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier action, or <code>null</code> if a matching dossier action could not be found
	*/
	public static DossierAction fetchByDID_Last(long dossierId,
		OrderByComparator<DossierAction> orderByComparator) {
		return getPersistence().fetchByDID_Last(dossierId, orderByComparator);
	}

	/**
	* Returns the dossier actions before and after the current dossier action in the ordered set where dossierId = &#63;.
	*
	* @param dossierActionId the primary key of the current dossier action
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier action
	* @throws NoSuchDossierActionException if a dossier action with the primary key could not be found
	*/
	public static DossierAction[] findByDID_PrevAndNext(long dossierActionId,
		long dossierId, OrderByComparator<DossierAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionException {
		return getPersistence()
				   .findByDID_PrevAndNext(dossierActionId, dossierId,
			orderByComparator);
	}

	/**
	* Removes all the dossier actions where dossierId = &#63; from the database.
	*
	* @param dossierId the dossier ID
	*/
	public static void removeByDID(long dossierId) {
		getPersistence().removeByDID(dossierId);
	}

	/**
	* Returns the number of dossier actions where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @return the number of matching dossier actions
	*/
	public static int countByDID(long dossierId) {
		return getPersistence().countByDID(dossierId);
	}

	/**
	* Returns all the dossier actions where groupId = &#63; and pending = &#63;.
	*
	* @param groupId the group ID
	* @param pending the pending
	* @return the matching dossier actions
	*/
	public static List<DossierAction> findByG_PENDING(long groupId,
		boolean pending) {
		return getPersistence().findByG_PENDING(groupId, pending);
	}

	/**
	* Returns a range of all the dossier actions where groupId = &#63; and pending = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param pending the pending
	* @param start the lower bound of the range of dossier actions
	* @param end the upper bound of the range of dossier actions (not inclusive)
	* @return the range of matching dossier actions
	*/
	public static List<DossierAction> findByG_PENDING(long groupId,
		boolean pending, int start, int end) {
		return getPersistence().findByG_PENDING(groupId, pending, start, end);
	}

	/**
	* Returns an ordered range of all the dossier actions where groupId = &#63; and pending = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param pending the pending
	* @param start the lower bound of the range of dossier actions
	* @param end the upper bound of the range of dossier actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier actions
	*/
	public static List<DossierAction> findByG_PENDING(long groupId,
		boolean pending, int start, int end,
		OrderByComparator<DossierAction> orderByComparator) {
		return getPersistence()
				   .findByG_PENDING(groupId, pending, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier actions where groupId = &#63; and pending = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param pending the pending
	* @param start the lower bound of the range of dossier actions
	* @param end the upper bound of the range of dossier actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier actions
	*/
	public static List<DossierAction> findByG_PENDING(long groupId,
		boolean pending, int start, int end,
		OrderByComparator<DossierAction> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_PENDING(groupId, pending, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier action in the ordered set where groupId = &#63; and pending = &#63;.
	*
	* @param groupId the group ID
	* @param pending the pending
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier action
	* @throws NoSuchDossierActionException if a matching dossier action could not be found
	*/
	public static DossierAction findByG_PENDING_First(long groupId,
		boolean pending, OrderByComparator<DossierAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionException {
		return getPersistence()
				   .findByG_PENDING_First(groupId, pending, orderByComparator);
	}

	/**
	* Returns the first dossier action in the ordered set where groupId = &#63; and pending = &#63;.
	*
	* @param groupId the group ID
	* @param pending the pending
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier action, or <code>null</code> if a matching dossier action could not be found
	*/
	public static DossierAction fetchByG_PENDING_First(long groupId,
		boolean pending, OrderByComparator<DossierAction> orderByComparator) {
		return getPersistence()
				   .fetchByG_PENDING_First(groupId, pending, orderByComparator);
	}

	/**
	* Returns the last dossier action in the ordered set where groupId = &#63; and pending = &#63;.
	*
	* @param groupId the group ID
	* @param pending the pending
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier action
	* @throws NoSuchDossierActionException if a matching dossier action could not be found
	*/
	public static DossierAction findByG_PENDING_Last(long groupId,
		boolean pending, OrderByComparator<DossierAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionException {
		return getPersistence()
				   .findByG_PENDING_Last(groupId, pending, orderByComparator);
	}

	/**
	* Returns the last dossier action in the ordered set where groupId = &#63; and pending = &#63;.
	*
	* @param groupId the group ID
	* @param pending the pending
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier action, or <code>null</code> if a matching dossier action could not be found
	*/
	public static DossierAction fetchByG_PENDING_Last(long groupId,
		boolean pending, OrderByComparator<DossierAction> orderByComparator) {
		return getPersistence()
				   .fetchByG_PENDING_Last(groupId, pending, orderByComparator);
	}

	/**
	* Returns the dossier actions before and after the current dossier action in the ordered set where groupId = &#63; and pending = &#63;.
	*
	* @param dossierActionId the primary key of the current dossier action
	* @param groupId the group ID
	* @param pending the pending
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier action
	* @throws NoSuchDossierActionException if a dossier action with the primary key could not be found
	*/
	public static DossierAction[] findByG_PENDING_PrevAndNext(
		long dossierActionId, long groupId, boolean pending,
		OrderByComparator<DossierAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionException {
		return getPersistence()
				   .findByG_PENDING_PrevAndNext(dossierActionId, groupId,
			pending, orderByComparator);
	}

	/**
	* Removes all the dossier actions where groupId = &#63; and pending = &#63; from the database.
	*
	* @param groupId the group ID
	* @param pending the pending
	*/
	public static void removeByG_PENDING(long groupId, boolean pending) {
		getPersistence().removeByG_PENDING(groupId, pending);
	}

	/**
	* Returns the number of dossier actions where groupId = &#63; and pending = &#63;.
	*
	* @param groupId the group ID
	* @param pending the pending
	* @return the number of matching dossier actions
	*/
	public static int countByG_PENDING(long groupId, boolean pending) {
		return getPersistence().countByG_PENDING(groupId, pending);
	}

	/**
	* Returns all the dossier actions where dossierId = &#63; and fromSequenceNo = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fromSequenceNo the from sequence no
	* @return the matching dossier actions
	*/
	public static List<DossierAction> findByDID_FSN(long dossierId,
		String fromSequenceNo) {
		return getPersistence().findByDID_FSN(dossierId, fromSequenceNo);
	}

	/**
	* Returns a range of all the dossier actions where dossierId = &#63; and fromSequenceNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param fromSequenceNo the from sequence no
	* @param start the lower bound of the range of dossier actions
	* @param end the upper bound of the range of dossier actions (not inclusive)
	* @return the range of matching dossier actions
	*/
	public static List<DossierAction> findByDID_FSN(long dossierId,
		String fromSequenceNo, int start, int end) {
		return getPersistence()
				   .findByDID_FSN(dossierId, fromSequenceNo, start, end);
	}

	/**
	* Returns an ordered range of all the dossier actions where dossierId = &#63; and fromSequenceNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param fromSequenceNo the from sequence no
	* @param start the lower bound of the range of dossier actions
	* @param end the upper bound of the range of dossier actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier actions
	*/
	public static List<DossierAction> findByDID_FSN(long dossierId,
		String fromSequenceNo, int start, int end,
		OrderByComparator<DossierAction> orderByComparator) {
		return getPersistence()
				   .findByDID_FSN(dossierId, fromSequenceNo, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier actions where dossierId = &#63; and fromSequenceNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param fromSequenceNo the from sequence no
	* @param start the lower bound of the range of dossier actions
	* @param end the upper bound of the range of dossier actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier actions
	*/
	public static List<DossierAction> findByDID_FSN(long dossierId,
		String fromSequenceNo, int start, int end,
		OrderByComparator<DossierAction> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByDID_FSN(dossierId, fromSequenceNo, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier action in the ordered set where dossierId = &#63; and fromSequenceNo = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fromSequenceNo the from sequence no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier action
	* @throws NoSuchDossierActionException if a matching dossier action could not be found
	*/
	public static DossierAction findByDID_FSN_First(long dossierId,
		String fromSequenceNo,
		OrderByComparator<DossierAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionException {
		return getPersistence()
				   .findByDID_FSN_First(dossierId, fromSequenceNo,
			orderByComparator);
	}

	/**
	* Returns the first dossier action in the ordered set where dossierId = &#63; and fromSequenceNo = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fromSequenceNo the from sequence no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier action, or <code>null</code> if a matching dossier action could not be found
	*/
	public static DossierAction fetchByDID_FSN_First(long dossierId,
		String fromSequenceNo,
		OrderByComparator<DossierAction> orderByComparator) {
		return getPersistence()
				   .fetchByDID_FSN_First(dossierId, fromSequenceNo,
			orderByComparator);
	}

	/**
	* Returns the last dossier action in the ordered set where dossierId = &#63; and fromSequenceNo = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fromSequenceNo the from sequence no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier action
	* @throws NoSuchDossierActionException if a matching dossier action could not be found
	*/
	public static DossierAction findByDID_FSN_Last(long dossierId,
		String fromSequenceNo,
		OrderByComparator<DossierAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionException {
		return getPersistence()
				   .findByDID_FSN_Last(dossierId, fromSequenceNo,
			orderByComparator);
	}

	/**
	* Returns the last dossier action in the ordered set where dossierId = &#63; and fromSequenceNo = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fromSequenceNo the from sequence no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier action, or <code>null</code> if a matching dossier action could not be found
	*/
	public static DossierAction fetchByDID_FSN_Last(long dossierId,
		String fromSequenceNo,
		OrderByComparator<DossierAction> orderByComparator) {
		return getPersistence()
				   .fetchByDID_FSN_Last(dossierId, fromSequenceNo,
			orderByComparator);
	}

	/**
	* Returns the dossier actions before and after the current dossier action in the ordered set where dossierId = &#63; and fromSequenceNo = &#63;.
	*
	* @param dossierActionId the primary key of the current dossier action
	* @param dossierId the dossier ID
	* @param fromSequenceNo the from sequence no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier action
	* @throws NoSuchDossierActionException if a dossier action with the primary key could not be found
	*/
	public static DossierAction[] findByDID_FSN_PrevAndNext(
		long dossierActionId, long dossierId, String fromSequenceNo,
		OrderByComparator<DossierAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionException {
		return getPersistence()
				   .findByDID_FSN_PrevAndNext(dossierActionId, dossierId,
			fromSequenceNo, orderByComparator);
	}

	/**
	* Removes all the dossier actions where dossierId = &#63; and fromSequenceNo = &#63; from the database.
	*
	* @param dossierId the dossier ID
	* @param fromSequenceNo the from sequence no
	*/
	public static void removeByDID_FSN(long dossierId, String fromSequenceNo) {
		getPersistence().removeByDID_FSN(dossierId, fromSequenceNo);
	}

	/**
	* Returns the number of dossier actions where dossierId = &#63; and fromSequenceNo = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fromSequenceNo the from sequence no
	* @return the number of matching dossier actions
	*/
	public static int countByDID_FSN(long dossierId, String fromSequenceNo) {
		return getPersistence().countByDID_FSN(dossierId, fromSequenceNo);
	}

	/**
	* Returns all the dossier actions where groupId = &#63; and dossierId = &#63; and sequenceNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param sequenceNo the sequence no
	* @return the matching dossier actions
	*/
	public static List<DossierAction> findByG_DID_SN(long groupId,
		long dossierId, String sequenceNo) {
		return getPersistence().findByG_DID_SN(groupId, dossierId, sequenceNo);
	}

	/**
	* Returns a range of all the dossier actions where groupId = &#63; and dossierId = &#63; and sequenceNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param sequenceNo the sequence no
	* @param start the lower bound of the range of dossier actions
	* @param end the upper bound of the range of dossier actions (not inclusive)
	* @return the range of matching dossier actions
	*/
	public static List<DossierAction> findByG_DID_SN(long groupId,
		long dossierId, String sequenceNo, int start, int end) {
		return getPersistence()
				   .findByG_DID_SN(groupId, dossierId, sequenceNo, start, end);
	}

	/**
	* Returns an ordered range of all the dossier actions where groupId = &#63; and dossierId = &#63; and sequenceNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param sequenceNo the sequence no
	* @param start the lower bound of the range of dossier actions
	* @param end the upper bound of the range of dossier actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier actions
	*/
	public static List<DossierAction> findByG_DID_SN(long groupId,
		long dossierId, String sequenceNo, int start, int end,
		OrderByComparator<DossierAction> orderByComparator) {
		return getPersistence()
				   .findByG_DID_SN(groupId, dossierId, sequenceNo, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier actions where groupId = &#63; and dossierId = &#63; and sequenceNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param sequenceNo the sequence no
	* @param start the lower bound of the range of dossier actions
	* @param end the upper bound of the range of dossier actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier actions
	*/
	public static List<DossierAction> findByG_DID_SN(long groupId,
		long dossierId, String sequenceNo, int start, int end,
		OrderByComparator<DossierAction> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_DID_SN(groupId, dossierId, sequenceNo, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier action in the ordered set where groupId = &#63; and dossierId = &#63; and sequenceNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param sequenceNo the sequence no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier action
	* @throws NoSuchDossierActionException if a matching dossier action could not be found
	*/
	public static DossierAction findByG_DID_SN_First(long groupId,
		long dossierId, String sequenceNo,
		OrderByComparator<DossierAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionException {
		return getPersistence()
				   .findByG_DID_SN_First(groupId, dossierId, sequenceNo,
			orderByComparator);
	}

	/**
	* Returns the first dossier action in the ordered set where groupId = &#63; and dossierId = &#63; and sequenceNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param sequenceNo the sequence no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier action, or <code>null</code> if a matching dossier action could not be found
	*/
	public static DossierAction fetchByG_DID_SN_First(long groupId,
		long dossierId, String sequenceNo,
		OrderByComparator<DossierAction> orderByComparator) {
		return getPersistence()
				   .fetchByG_DID_SN_First(groupId, dossierId, sequenceNo,
			orderByComparator);
	}

	/**
	* Returns the last dossier action in the ordered set where groupId = &#63; and dossierId = &#63; and sequenceNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param sequenceNo the sequence no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier action
	* @throws NoSuchDossierActionException if a matching dossier action could not be found
	*/
	public static DossierAction findByG_DID_SN_Last(long groupId,
		long dossierId, String sequenceNo,
		OrderByComparator<DossierAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionException {
		return getPersistence()
				   .findByG_DID_SN_Last(groupId, dossierId, sequenceNo,
			orderByComparator);
	}

	/**
	* Returns the last dossier action in the ordered set where groupId = &#63; and dossierId = &#63; and sequenceNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param sequenceNo the sequence no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier action, or <code>null</code> if a matching dossier action could not be found
	*/
	public static DossierAction fetchByG_DID_SN_Last(long groupId,
		long dossierId, String sequenceNo,
		OrderByComparator<DossierAction> orderByComparator) {
		return getPersistence()
				   .fetchByG_DID_SN_Last(groupId, dossierId, sequenceNo,
			orderByComparator);
	}

	/**
	* Returns the dossier actions before and after the current dossier action in the ordered set where groupId = &#63; and dossierId = &#63; and sequenceNo = &#63;.
	*
	* @param dossierActionId the primary key of the current dossier action
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param sequenceNo the sequence no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier action
	* @throws NoSuchDossierActionException if a dossier action with the primary key could not be found
	*/
	public static DossierAction[] findByG_DID_SN_PrevAndNext(
		long dossierActionId, long groupId, long dossierId, String sequenceNo,
		OrderByComparator<DossierAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionException {
		return getPersistence()
				   .findByG_DID_SN_PrevAndNext(dossierActionId, groupId,
			dossierId, sequenceNo, orderByComparator);
	}

	/**
	* Removes all the dossier actions where groupId = &#63; and dossierId = &#63; and sequenceNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param sequenceNo the sequence no
	*/
	public static void removeByG_DID_SN(long groupId, long dossierId,
		String sequenceNo) {
		getPersistence().removeByG_DID_SN(groupId, dossierId, sequenceNo);
	}

	/**
	* Returns the number of dossier actions where groupId = &#63; and dossierId = &#63; and sequenceNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param sequenceNo the sequence no
	* @return the number of matching dossier actions
	*/
	public static int countByG_DID_SN(long groupId, long dossierId,
		String sequenceNo) {
		return getPersistence().countByG_DID_SN(groupId, dossierId, sequenceNo);
	}

	/**
	* Returns all the dossier actions where groupId = &#63; and dossierId = &#63; and fromSequenceNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param fromSequenceNo the from sequence no
	* @return the matching dossier actions
	*/
	public static List<DossierAction> findByG_DID_FSN(long groupId,
		long dossierId, String fromSequenceNo) {
		return getPersistence()
				   .findByG_DID_FSN(groupId, dossierId, fromSequenceNo);
	}

	/**
	* Returns a range of all the dossier actions where groupId = &#63; and dossierId = &#63; and fromSequenceNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param fromSequenceNo the from sequence no
	* @param start the lower bound of the range of dossier actions
	* @param end the upper bound of the range of dossier actions (not inclusive)
	* @return the range of matching dossier actions
	*/
	public static List<DossierAction> findByG_DID_FSN(long groupId,
		long dossierId, String fromSequenceNo, int start, int end) {
		return getPersistence()
				   .findByG_DID_FSN(groupId, dossierId, fromSequenceNo, start,
			end);
	}

	/**
	* Returns an ordered range of all the dossier actions where groupId = &#63; and dossierId = &#63; and fromSequenceNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param fromSequenceNo the from sequence no
	* @param start the lower bound of the range of dossier actions
	* @param end the upper bound of the range of dossier actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier actions
	*/
	public static List<DossierAction> findByG_DID_FSN(long groupId,
		long dossierId, String fromSequenceNo, int start, int end,
		OrderByComparator<DossierAction> orderByComparator) {
		return getPersistence()
				   .findByG_DID_FSN(groupId, dossierId, fromSequenceNo, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier actions where groupId = &#63; and dossierId = &#63; and fromSequenceNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param fromSequenceNo the from sequence no
	* @param start the lower bound of the range of dossier actions
	* @param end the upper bound of the range of dossier actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier actions
	*/
	public static List<DossierAction> findByG_DID_FSN(long groupId,
		long dossierId, String fromSequenceNo, int start, int end,
		OrderByComparator<DossierAction> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_DID_FSN(groupId, dossierId, fromSequenceNo, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier action in the ordered set where groupId = &#63; and dossierId = &#63; and fromSequenceNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param fromSequenceNo the from sequence no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier action
	* @throws NoSuchDossierActionException if a matching dossier action could not be found
	*/
	public static DossierAction findByG_DID_FSN_First(long groupId,
		long dossierId, String fromSequenceNo,
		OrderByComparator<DossierAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionException {
		return getPersistence()
				   .findByG_DID_FSN_First(groupId, dossierId, fromSequenceNo,
			orderByComparator);
	}

	/**
	* Returns the first dossier action in the ordered set where groupId = &#63; and dossierId = &#63; and fromSequenceNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param fromSequenceNo the from sequence no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier action, or <code>null</code> if a matching dossier action could not be found
	*/
	public static DossierAction fetchByG_DID_FSN_First(long groupId,
		long dossierId, String fromSequenceNo,
		OrderByComparator<DossierAction> orderByComparator) {
		return getPersistence()
				   .fetchByG_DID_FSN_First(groupId, dossierId, fromSequenceNo,
			orderByComparator);
	}

	/**
	* Returns the last dossier action in the ordered set where groupId = &#63; and dossierId = &#63; and fromSequenceNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param fromSequenceNo the from sequence no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier action
	* @throws NoSuchDossierActionException if a matching dossier action could not be found
	*/
	public static DossierAction findByG_DID_FSN_Last(long groupId,
		long dossierId, String fromSequenceNo,
		OrderByComparator<DossierAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionException {
		return getPersistence()
				   .findByG_DID_FSN_Last(groupId, dossierId, fromSequenceNo,
			orderByComparator);
	}

	/**
	* Returns the last dossier action in the ordered set where groupId = &#63; and dossierId = &#63; and fromSequenceNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param fromSequenceNo the from sequence no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier action, or <code>null</code> if a matching dossier action could not be found
	*/
	public static DossierAction fetchByG_DID_FSN_Last(long groupId,
		long dossierId, String fromSequenceNo,
		OrderByComparator<DossierAction> orderByComparator) {
		return getPersistence()
				   .fetchByG_DID_FSN_Last(groupId, dossierId, fromSequenceNo,
			orderByComparator);
	}

	/**
	* Returns the dossier actions before and after the current dossier action in the ordered set where groupId = &#63; and dossierId = &#63; and fromSequenceNo = &#63;.
	*
	* @param dossierActionId the primary key of the current dossier action
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param fromSequenceNo the from sequence no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier action
	* @throws NoSuchDossierActionException if a dossier action with the primary key could not be found
	*/
	public static DossierAction[] findByG_DID_FSN_PrevAndNext(
		long dossierActionId, long groupId, long dossierId,
		String fromSequenceNo,
		OrderByComparator<DossierAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionException {
		return getPersistence()
				   .findByG_DID_FSN_PrevAndNext(dossierActionId, groupId,
			dossierId, fromSequenceNo, orderByComparator);
	}

	/**
	* Removes all the dossier actions where groupId = &#63; and dossierId = &#63; and fromSequenceNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param fromSequenceNo the from sequence no
	*/
	public static void removeByG_DID_FSN(long groupId, long dossierId,
		String fromSequenceNo) {
		getPersistence().removeByG_DID_FSN(groupId, dossierId, fromSequenceNo);
	}

	/**
	* Returns the number of dossier actions where groupId = &#63; and dossierId = &#63; and fromSequenceNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param fromSequenceNo the from sequence no
	* @return the number of matching dossier actions
	*/
	public static int countByG_DID_FSN(long groupId, long dossierId,
		String fromSequenceNo) {
		return getPersistence()
				   .countByG_DID_FSN(groupId, dossierId, fromSequenceNo);
	}

	/**
	* Returns all the dossier actions where dossierId = &#63; and fromStepCode = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fromStepCode the from step code
	* @return the matching dossier actions
	*/
	public static List<DossierAction> findByDID_STEP(long dossierId,
		String fromStepCode) {
		return getPersistence().findByDID_STEP(dossierId, fromStepCode);
	}

	/**
	* Returns a range of all the dossier actions where dossierId = &#63; and fromStepCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param fromStepCode the from step code
	* @param start the lower bound of the range of dossier actions
	* @param end the upper bound of the range of dossier actions (not inclusive)
	* @return the range of matching dossier actions
	*/
	public static List<DossierAction> findByDID_STEP(long dossierId,
		String fromStepCode, int start, int end) {
		return getPersistence()
				   .findByDID_STEP(dossierId, fromStepCode, start, end);
	}

	/**
	* Returns an ordered range of all the dossier actions where dossierId = &#63; and fromStepCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param fromStepCode the from step code
	* @param start the lower bound of the range of dossier actions
	* @param end the upper bound of the range of dossier actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier actions
	*/
	public static List<DossierAction> findByDID_STEP(long dossierId,
		String fromStepCode, int start, int end,
		OrderByComparator<DossierAction> orderByComparator) {
		return getPersistence()
				   .findByDID_STEP(dossierId, fromStepCode, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier actions where dossierId = &#63; and fromStepCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param fromStepCode the from step code
	* @param start the lower bound of the range of dossier actions
	* @param end the upper bound of the range of dossier actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier actions
	*/
	public static List<DossierAction> findByDID_STEP(long dossierId,
		String fromStepCode, int start, int end,
		OrderByComparator<DossierAction> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByDID_STEP(dossierId, fromStepCode, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier action in the ordered set where dossierId = &#63; and fromStepCode = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fromStepCode the from step code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier action
	* @throws NoSuchDossierActionException if a matching dossier action could not be found
	*/
	public static DossierAction findByDID_STEP_First(long dossierId,
		String fromStepCode, OrderByComparator<DossierAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionException {
		return getPersistence()
				   .findByDID_STEP_First(dossierId, fromStepCode,
			orderByComparator);
	}

	/**
	* Returns the first dossier action in the ordered set where dossierId = &#63; and fromStepCode = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fromStepCode the from step code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier action, or <code>null</code> if a matching dossier action could not be found
	*/
	public static DossierAction fetchByDID_STEP_First(long dossierId,
		String fromStepCode, OrderByComparator<DossierAction> orderByComparator) {
		return getPersistence()
				   .fetchByDID_STEP_First(dossierId, fromStepCode,
			orderByComparator);
	}

	/**
	* Returns the last dossier action in the ordered set where dossierId = &#63; and fromStepCode = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fromStepCode the from step code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier action
	* @throws NoSuchDossierActionException if a matching dossier action could not be found
	*/
	public static DossierAction findByDID_STEP_Last(long dossierId,
		String fromStepCode, OrderByComparator<DossierAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionException {
		return getPersistence()
				   .findByDID_STEP_Last(dossierId, fromStepCode,
			orderByComparator);
	}

	/**
	* Returns the last dossier action in the ordered set where dossierId = &#63; and fromStepCode = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fromStepCode the from step code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier action, or <code>null</code> if a matching dossier action could not be found
	*/
	public static DossierAction fetchByDID_STEP_Last(long dossierId,
		String fromStepCode, OrderByComparator<DossierAction> orderByComparator) {
		return getPersistence()
				   .fetchByDID_STEP_Last(dossierId, fromStepCode,
			orderByComparator);
	}

	/**
	* Returns the dossier actions before and after the current dossier action in the ordered set where dossierId = &#63; and fromStepCode = &#63;.
	*
	* @param dossierActionId the primary key of the current dossier action
	* @param dossierId the dossier ID
	* @param fromStepCode the from step code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier action
	* @throws NoSuchDossierActionException if a dossier action with the primary key could not be found
	*/
	public static DossierAction[] findByDID_STEP_PrevAndNext(
		long dossierActionId, long dossierId, String fromStepCode,
		OrderByComparator<DossierAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionException {
		return getPersistence()
				   .findByDID_STEP_PrevAndNext(dossierActionId, dossierId,
			fromStepCode, orderByComparator);
	}

	/**
	* Removes all the dossier actions where dossierId = &#63; and fromStepCode = &#63; from the database.
	*
	* @param dossierId the dossier ID
	* @param fromStepCode the from step code
	*/
	public static void removeByDID_STEP(long dossierId, String fromStepCode) {
		getPersistence().removeByDID_STEP(dossierId, fromStepCode);
	}

	/**
	* Returns the number of dossier actions where dossierId = &#63; and fromStepCode = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fromStepCode the from step code
	* @return the number of matching dossier actions
	*/
	public static int countByDID_STEP(long dossierId, String fromStepCode) {
		return getPersistence().countByDID_STEP(dossierId, fromStepCode);
	}

	/**
	* Returns all the dossier actions where dossierId = &#63; and stepCode = &#63;.
	*
	* @param dossierId the dossier ID
	* @param stepCode the step code
	* @return the matching dossier actions
	*/
	public static List<DossierAction> findByDID_SC(long dossierId,
		String stepCode) {
		return getPersistence().findByDID_SC(dossierId, stepCode);
	}

	/**
	* Returns a range of all the dossier actions where dossierId = &#63; and stepCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param stepCode the step code
	* @param start the lower bound of the range of dossier actions
	* @param end the upper bound of the range of dossier actions (not inclusive)
	* @return the range of matching dossier actions
	*/
	public static List<DossierAction> findByDID_SC(long dossierId,
		String stepCode, int start, int end) {
		return getPersistence().findByDID_SC(dossierId, stepCode, start, end);
	}

	/**
	* Returns an ordered range of all the dossier actions where dossierId = &#63; and stepCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param stepCode the step code
	* @param start the lower bound of the range of dossier actions
	* @param end the upper bound of the range of dossier actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier actions
	*/
	public static List<DossierAction> findByDID_SC(long dossierId,
		String stepCode, int start, int end,
		OrderByComparator<DossierAction> orderByComparator) {
		return getPersistence()
				   .findByDID_SC(dossierId, stepCode, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier actions where dossierId = &#63; and stepCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param stepCode the step code
	* @param start the lower bound of the range of dossier actions
	* @param end the upper bound of the range of dossier actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier actions
	*/
	public static List<DossierAction> findByDID_SC(long dossierId,
		String stepCode, int start, int end,
		OrderByComparator<DossierAction> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByDID_SC(dossierId, stepCode, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier action in the ordered set where dossierId = &#63; and stepCode = &#63;.
	*
	* @param dossierId the dossier ID
	* @param stepCode the step code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier action
	* @throws NoSuchDossierActionException if a matching dossier action could not be found
	*/
	public static DossierAction findByDID_SC_First(long dossierId,
		String stepCode, OrderByComparator<DossierAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionException {
		return getPersistence()
				   .findByDID_SC_First(dossierId, stepCode, orderByComparator);
	}

	/**
	* Returns the first dossier action in the ordered set where dossierId = &#63; and stepCode = &#63;.
	*
	* @param dossierId the dossier ID
	* @param stepCode the step code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier action, or <code>null</code> if a matching dossier action could not be found
	*/
	public static DossierAction fetchByDID_SC_First(long dossierId,
		String stepCode, OrderByComparator<DossierAction> orderByComparator) {
		return getPersistence()
				   .fetchByDID_SC_First(dossierId, stepCode, orderByComparator);
	}

	/**
	* Returns the last dossier action in the ordered set where dossierId = &#63; and stepCode = &#63;.
	*
	* @param dossierId the dossier ID
	* @param stepCode the step code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier action
	* @throws NoSuchDossierActionException if a matching dossier action could not be found
	*/
	public static DossierAction findByDID_SC_Last(long dossierId,
		String stepCode, OrderByComparator<DossierAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionException {
		return getPersistence()
				   .findByDID_SC_Last(dossierId, stepCode, orderByComparator);
	}

	/**
	* Returns the last dossier action in the ordered set where dossierId = &#63; and stepCode = &#63;.
	*
	* @param dossierId the dossier ID
	* @param stepCode the step code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier action, or <code>null</code> if a matching dossier action could not be found
	*/
	public static DossierAction fetchByDID_SC_Last(long dossierId,
		String stepCode, OrderByComparator<DossierAction> orderByComparator) {
		return getPersistence()
				   .fetchByDID_SC_Last(dossierId, stepCode, orderByComparator);
	}

	/**
	* Returns the dossier actions before and after the current dossier action in the ordered set where dossierId = &#63; and stepCode = &#63;.
	*
	* @param dossierActionId the primary key of the current dossier action
	* @param dossierId the dossier ID
	* @param stepCode the step code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier action
	* @throws NoSuchDossierActionException if a dossier action with the primary key could not be found
	*/
	public static DossierAction[] findByDID_SC_PrevAndNext(
		long dossierActionId, long dossierId, String stepCode,
		OrderByComparator<DossierAction> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionException {
		return getPersistence()
				   .findByDID_SC_PrevAndNext(dossierActionId, dossierId,
			stepCode, orderByComparator);
	}

	/**
	* Removes all the dossier actions where dossierId = &#63; and stepCode = &#63; from the database.
	*
	* @param dossierId the dossier ID
	* @param stepCode the step code
	*/
	public static void removeByDID_SC(long dossierId, String stepCode) {
		getPersistence().removeByDID_SC(dossierId, stepCode);
	}

	/**
	* Returns the number of dossier actions where dossierId = &#63; and stepCode = &#63;.
	*
	* @param dossierId the dossier ID
	* @param stepCode the step code
	* @return the number of matching dossier actions
	*/
	public static int countByDID_SC(long dossierId, String stepCode) {
		return getPersistence().countByDID_SC(dossierId, stepCode);
	}

	/**
	* Caches the dossier action in the entity cache if it is enabled.
	*
	* @param dossierAction the dossier action
	*/
	public static void cacheResult(DossierAction dossierAction) {
		getPersistence().cacheResult(dossierAction);
	}

	/**
	* Caches the dossier actions in the entity cache if it is enabled.
	*
	* @param dossierActions the dossier actions
	*/
	public static void cacheResult(List<DossierAction> dossierActions) {
		getPersistence().cacheResult(dossierActions);
	}

	/**
	* Creates a new dossier action with the primary key. Does not add the dossier action to the database.
	*
	* @param dossierActionId the primary key for the new dossier action
	* @return the new dossier action
	*/
	public static DossierAction create(long dossierActionId) {
		return getPersistence().create(dossierActionId);
	}

	/**
	* Removes the dossier action with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierActionId the primary key of the dossier action
	* @return the dossier action that was removed
	* @throws NoSuchDossierActionException if a dossier action with the primary key could not be found
	*/
	public static DossierAction remove(long dossierActionId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionException {
		return getPersistence().remove(dossierActionId);
	}

	public static DossierAction updateImpl(DossierAction dossierAction) {
		return getPersistence().updateImpl(dossierAction);
	}

	/**
	* Returns the dossier action with the primary key or throws a {@link NoSuchDossierActionException} if it could not be found.
	*
	* @param dossierActionId the primary key of the dossier action
	* @return the dossier action
	* @throws NoSuchDossierActionException if a dossier action with the primary key could not be found
	*/
	public static DossierAction findByPrimaryKey(long dossierActionId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierActionException {
		return getPersistence().findByPrimaryKey(dossierActionId);
	}

	/**
	* Returns the dossier action with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param dossierActionId the primary key of the dossier action
	* @return the dossier action, or <code>null</code> if a dossier action with the primary key could not be found
	*/
	public static DossierAction fetchByPrimaryKey(long dossierActionId) {
		return getPersistence().fetchByPrimaryKey(dossierActionId);
	}

	public static java.util.Map<java.io.Serializable, DossierAction> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the dossier actions.
	*
	* @return the dossier actions
	*/
	public static List<DossierAction> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the dossier actions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier actions
	* @param end the upper bound of the range of dossier actions (not inclusive)
	* @return the range of dossier actions
	*/
	public static List<DossierAction> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the dossier actions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier actions
	* @param end the upper bound of the range of dossier actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of dossier actions
	*/
	public static List<DossierAction> findAll(int start, int end,
		OrderByComparator<DossierAction> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier actions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier actions
	* @param end the upper bound of the range of dossier actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of dossier actions
	*/
	public static List<DossierAction> findAll(int start, int end,
		OrderByComparator<DossierAction> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the dossier actions from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of dossier actions.
	*
	* @return the number of dossier actions
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static DossierActionPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DossierActionPersistence, DossierActionPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(DossierActionPersistence.class);

		ServiceTracker<DossierActionPersistence, DossierActionPersistence> serviceTracker =
			new ServiceTracker<DossierActionPersistence, DossierActionPersistence>(bundle.getBundleContext(),
				DossierActionPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}