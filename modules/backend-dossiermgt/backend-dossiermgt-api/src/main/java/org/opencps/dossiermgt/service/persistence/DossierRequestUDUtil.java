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

import org.opencps.dossiermgt.model.DossierRequestUD;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the dossier request ud service. This utility wraps {@link org.opencps.dossiermgt.service.persistence.impl.DossierRequestUDPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see DossierRequestUDPersistence
 * @see org.opencps.dossiermgt.service.persistence.impl.DossierRequestUDPersistenceImpl
 * @generated
 */
@ProviderType
public class DossierRequestUDUtil {
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
	public static void clearCache(DossierRequestUD dossierRequestUD) {
		getPersistence().clearCache(dossierRequestUD);
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
	public static List<DossierRequestUD> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<DossierRequestUD> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<DossierRequestUD> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<DossierRequestUD> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static DossierRequestUD update(DossierRequestUD dossierRequestUD) {
		return getPersistence().update(dossierRequestUD);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static DossierRequestUD update(DossierRequestUD dossierRequestUD,
		ServiceContext serviceContext) {
		return getPersistence().update(dossierRequestUD, serviceContext);
	}

	/**
	* Returns all the dossier request uds where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching dossier request uds
	*/
	public static List<DossierRequestUD> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the dossier request uds where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierRequestUDModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier request uds
	* @param end the upper bound of the range of dossier request uds (not inclusive)
	* @return the range of matching dossier request uds
	*/
	public static List<DossierRequestUD> findByUuid(String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the dossier request uds where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierRequestUDModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier request uds
	* @param end the upper bound of the range of dossier request uds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier request uds
	*/
	public static List<DossierRequestUD> findByUuid(String uuid, int start,
		int end, OrderByComparator<DossierRequestUD> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier request uds where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierRequestUDModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier request uds
	* @param end the upper bound of the range of dossier request uds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier request uds
	*/
	public static List<DossierRequestUD> findByUuid(String uuid, int start,
		int end, OrderByComparator<DossierRequestUD> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first dossier request ud in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier request ud
	* @throws NoSuchDossierRequestUDException if a matching dossier request ud could not be found
	*/
	public static DossierRequestUD findByUuid_First(String uuid,
		OrderByComparator<DossierRequestUD> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierRequestUDException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first dossier request ud in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier request ud, or <code>null</code> if a matching dossier request ud could not be found
	*/
	public static DossierRequestUD fetchByUuid_First(String uuid,
		OrderByComparator<DossierRequestUD> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last dossier request ud in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier request ud
	* @throws NoSuchDossierRequestUDException if a matching dossier request ud could not be found
	*/
	public static DossierRequestUD findByUuid_Last(String uuid,
		OrderByComparator<DossierRequestUD> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierRequestUDException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last dossier request ud in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier request ud, or <code>null</code> if a matching dossier request ud could not be found
	*/
	public static DossierRequestUD fetchByUuid_Last(String uuid,
		OrderByComparator<DossierRequestUD> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the dossier request uds before and after the current dossier request ud in the ordered set where uuid = &#63;.
	*
	* @param dossierRequestId the primary key of the current dossier request ud
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier request ud
	* @throws NoSuchDossierRequestUDException if a dossier request ud with the primary key could not be found
	*/
	public static DossierRequestUD[] findByUuid_PrevAndNext(
		long dossierRequestId, String uuid,
		OrderByComparator<DossierRequestUD> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierRequestUDException {
		return getPersistence()
				   .findByUuid_PrevAndNext(dossierRequestId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the dossier request uds where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of dossier request uds where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching dossier request uds
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the dossier request ud where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDossierRequestUDException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dossier request ud
	* @throws NoSuchDossierRequestUDException if a matching dossier request ud could not be found
	*/
	public static DossierRequestUD findByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierRequestUDException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the dossier request ud where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dossier request ud, or <code>null</code> if a matching dossier request ud could not be found
	*/
	public static DossierRequestUD fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the dossier request ud where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier request ud, or <code>null</code> if a matching dossier request ud could not be found
	*/
	public static DossierRequestUD fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the dossier request ud where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the dossier request ud that was removed
	*/
	public static DossierRequestUD removeByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierRequestUDException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of dossier request uds where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching dossier request uds
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the dossier request uds where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching dossier request uds
	*/
	public static List<DossierRequestUD> findByUuid_C(String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the dossier request uds where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierRequestUDModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossier request uds
	* @param end the upper bound of the range of dossier request uds (not inclusive)
	* @return the range of matching dossier request uds
	*/
	public static List<DossierRequestUD> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the dossier request uds where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierRequestUDModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossier request uds
	* @param end the upper bound of the range of dossier request uds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier request uds
	*/
	public static List<DossierRequestUD> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<DossierRequestUD> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier request uds where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierRequestUDModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossier request uds
	* @param end the upper bound of the range of dossier request uds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier request uds
	*/
	public static List<DossierRequestUD> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<DossierRequestUD> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier request ud in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier request ud
	* @throws NoSuchDossierRequestUDException if a matching dossier request ud could not be found
	*/
	public static DossierRequestUD findByUuid_C_First(String uuid,
		long companyId, OrderByComparator<DossierRequestUD> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierRequestUDException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first dossier request ud in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier request ud, or <code>null</code> if a matching dossier request ud could not be found
	*/
	public static DossierRequestUD fetchByUuid_C_First(String uuid,
		long companyId, OrderByComparator<DossierRequestUD> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last dossier request ud in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier request ud
	* @throws NoSuchDossierRequestUDException if a matching dossier request ud could not be found
	*/
	public static DossierRequestUD findByUuid_C_Last(String uuid,
		long companyId, OrderByComparator<DossierRequestUD> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierRequestUDException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last dossier request ud in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier request ud, or <code>null</code> if a matching dossier request ud could not be found
	*/
	public static DossierRequestUD fetchByUuid_C_Last(String uuid,
		long companyId, OrderByComparator<DossierRequestUD> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the dossier request uds before and after the current dossier request ud in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param dossierRequestId the primary key of the current dossier request ud
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier request ud
	* @throws NoSuchDossierRequestUDException if a dossier request ud with the primary key could not be found
	*/
	public static DossierRequestUD[] findByUuid_C_PrevAndNext(
		long dossierRequestId, String uuid, long companyId,
		OrderByComparator<DossierRequestUD> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierRequestUDException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(dossierRequestId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the dossier request uds where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of dossier request uds where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching dossier request uds
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the dossier request uds where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @return the matching dossier request uds
	*/
	public static List<DossierRequestUD> findByD_(long dossierId) {
		return getPersistence().findByD_(dossierId);
	}

	/**
	* Returns a range of all the dossier request uds where dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierRequestUDModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of dossier request uds
	* @param end the upper bound of the range of dossier request uds (not inclusive)
	* @return the range of matching dossier request uds
	*/
	public static List<DossierRequestUD> findByD_(long dossierId, int start,
		int end) {
		return getPersistence().findByD_(dossierId, start, end);
	}

	/**
	* Returns an ordered range of all the dossier request uds where dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierRequestUDModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of dossier request uds
	* @param end the upper bound of the range of dossier request uds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier request uds
	*/
	public static List<DossierRequestUD> findByD_(long dossierId, int start,
		int end, OrderByComparator<DossierRequestUD> orderByComparator) {
		return getPersistence()
				   .findByD_(dossierId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier request uds where dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierRequestUDModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of dossier request uds
	* @param end the upper bound of the range of dossier request uds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier request uds
	*/
	public static List<DossierRequestUD> findByD_(long dossierId, int start,
		int end, OrderByComparator<DossierRequestUD> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByD_(dossierId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first dossier request ud in the ordered set where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier request ud
	* @throws NoSuchDossierRequestUDException if a matching dossier request ud could not be found
	*/
	public static DossierRequestUD findByD__First(long dossierId,
		OrderByComparator<DossierRequestUD> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierRequestUDException {
		return getPersistence().findByD__First(dossierId, orderByComparator);
	}

	/**
	* Returns the first dossier request ud in the ordered set where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier request ud, or <code>null</code> if a matching dossier request ud could not be found
	*/
	public static DossierRequestUD fetchByD__First(long dossierId,
		OrderByComparator<DossierRequestUD> orderByComparator) {
		return getPersistence().fetchByD__First(dossierId, orderByComparator);
	}

	/**
	* Returns the last dossier request ud in the ordered set where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier request ud
	* @throws NoSuchDossierRequestUDException if a matching dossier request ud could not be found
	*/
	public static DossierRequestUD findByD__Last(long dossierId,
		OrderByComparator<DossierRequestUD> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierRequestUDException {
		return getPersistence().findByD__Last(dossierId, orderByComparator);
	}

	/**
	* Returns the last dossier request ud in the ordered set where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier request ud, or <code>null</code> if a matching dossier request ud could not be found
	*/
	public static DossierRequestUD fetchByD__Last(long dossierId,
		OrderByComparator<DossierRequestUD> orderByComparator) {
		return getPersistence().fetchByD__Last(dossierId, orderByComparator);
	}

	/**
	* Returns the dossier request uds before and after the current dossier request ud in the ordered set where dossierId = &#63;.
	*
	* @param dossierRequestId the primary key of the current dossier request ud
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier request ud
	* @throws NoSuchDossierRequestUDException if a dossier request ud with the primary key could not be found
	*/
	public static DossierRequestUD[] findByD__PrevAndNext(
		long dossierRequestId, long dossierId,
		OrderByComparator<DossierRequestUD> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierRequestUDException {
		return getPersistence()
				   .findByD__PrevAndNext(dossierRequestId, dossierId,
			orderByComparator);
	}

	/**
	* Removes all the dossier request uds where dossierId = &#63; from the database.
	*
	* @param dossierId the dossier ID
	*/
	public static void removeByD_(long dossierId) {
		getPersistence().removeByD_(dossierId);
	}

	/**
	* Returns the number of dossier request uds where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @return the number of matching dossier request uds
	*/
	public static int countByD_(long dossierId) {
		return getPersistence().countByD_(dossierId);
	}

	/**
	* Returns all the dossier request uds where dossierId = &#63; and requestType = &#63;.
	*
	* @param dossierId the dossier ID
	* @param requestType the request type
	* @return the matching dossier request uds
	*/
	public static List<DossierRequestUD> findByD_RT(long dossierId,
		String requestType) {
		return getPersistence().findByD_RT(dossierId, requestType);
	}

	/**
	* Returns a range of all the dossier request uds where dossierId = &#63; and requestType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierRequestUDModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param requestType the request type
	* @param start the lower bound of the range of dossier request uds
	* @param end the upper bound of the range of dossier request uds (not inclusive)
	* @return the range of matching dossier request uds
	*/
	public static List<DossierRequestUD> findByD_RT(long dossierId,
		String requestType, int start, int end) {
		return getPersistence().findByD_RT(dossierId, requestType, start, end);
	}

	/**
	* Returns an ordered range of all the dossier request uds where dossierId = &#63; and requestType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierRequestUDModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param requestType the request type
	* @param start the lower bound of the range of dossier request uds
	* @param end the upper bound of the range of dossier request uds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier request uds
	*/
	public static List<DossierRequestUD> findByD_RT(long dossierId,
		String requestType, int start, int end,
		OrderByComparator<DossierRequestUD> orderByComparator) {
		return getPersistence()
				   .findByD_RT(dossierId, requestType, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier request uds where dossierId = &#63; and requestType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierRequestUDModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param requestType the request type
	* @param start the lower bound of the range of dossier request uds
	* @param end the upper bound of the range of dossier request uds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier request uds
	*/
	public static List<DossierRequestUD> findByD_RT(long dossierId,
		String requestType, int start, int end,
		OrderByComparator<DossierRequestUD> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByD_RT(dossierId, requestType, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier request ud in the ordered set where dossierId = &#63; and requestType = &#63;.
	*
	* @param dossierId the dossier ID
	* @param requestType the request type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier request ud
	* @throws NoSuchDossierRequestUDException if a matching dossier request ud could not be found
	*/
	public static DossierRequestUD findByD_RT_First(long dossierId,
		String requestType,
		OrderByComparator<DossierRequestUD> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierRequestUDException {
		return getPersistence()
				   .findByD_RT_First(dossierId, requestType, orderByComparator);
	}

	/**
	* Returns the first dossier request ud in the ordered set where dossierId = &#63; and requestType = &#63;.
	*
	* @param dossierId the dossier ID
	* @param requestType the request type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier request ud, or <code>null</code> if a matching dossier request ud could not be found
	*/
	public static DossierRequestUD fetchByD_RT_First(long dossierId,
		String requestType,
		OrderByComparator<DossierRequestUD> orderByComparator) {
		return getPersistence()
				   .fetchByD_RT_First(dossierId, requestType, orderByComparator);
	}

	/**
	* Returns the last dossier request ud in the ordered set where dossierId = &#63; and requestType = &#63;.
	*
	* @param dossierId the dossier ID
	* @param requestType the request type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier request ud
	* @throws NoSuchDossierRequestUDException if a matching dossier request ud could not be found
	*/
	public static DossierRequestUD findByD_RT_Last(long dossierId,
		String requestType,
		OrderByComparator<DossierRequestUD> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierRequestUDException {
		return getPersistence()
				   .findByD_RT_Last(dossierId, requestType, orderByComparator);
	}

	/**
	* Returns the last dossier request ud in the ordered set where dossierId = &#63; and requestType = &#63;.
	*
	* @param dossierId the dossier ID
	* @param requestType the request type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier request ud, or <code>null</code> if a matching dossier request ud could not be found
	*/
	public static DossierRequestUD fetchByD_RT_Last(long dossierId,
		String requestType,
		OrderByComparator<DossierRequestUD> orderByComparator) {
		return getPersistence()
				   .fetchByD_RT_Last(dossierId, requestType, orderByComparator);
	}

	/**
	* Returns the dossier request uds before and after the current dossier request ud in the ordered set where dossierId = &#63; and requestType = &#63;.
	*
	* @param dossierRequestId the primary key of the current dossier request ud
	* @param dossierId the dossier ID
	* @param requestType the request type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier request ud
	* @throws NoSuchDossierRequestUDException if a dossier request ud with the primary key could not be found
	*/
	public static DossierRequestUD[] findByD_RT_PrevAndNext(
		long dossierRequestId, long dossierId, String requestType,
		OrderByComparator<DossierRequestUD> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierRequestUDException {
		return getPersistence()
				   .findByD_RT_PrevAndNext(dossierRequestId, dossierId,
			requestType, orderByComparator);
	}

	/**
	* Removes all the dossier request uds where dossierId = &#63; and requestType = &#63; from the database.
	*
	* @param dossierId the dossier ID
	* @param requestType the request type
	*/
	public static void removeByD_RT(long dossierId, String requestType) {
		getPersistence().removeByD_RT(dossierId, requestType);
	}

	/**
	* Returns the number of dossier request uds where dossierId = &#63; and requestType = &#63;.
	*
	* @param dossierId the dossier ID
	* @param requestType the request type
	* @return the number of matching dossier request uds
	*/
	public static int countByD_RT(long dossierId, String requestType) {
		return getPersistence().countByD_RT(dossierId, requestType);
	}

	/**
	* Returns all the dossier request uds where isNew = &#63;.
	*
	* @param isNew the is new
	* @return the matching dossier request uds
	*/
	public static List<DossierRequestUD> findByIS_NEW(int isNew) {
		return getPersistence().findByIS_NEW(isNew);
	}

	/**
	* Returns a range of all the dossier request uds where isNew = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierRequestUDModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param isNew the is new
	* @param start the lower bound of the range of dossier request uds
	* @param end the upper bound of the range of dossier request uds (not inclusive)
	* @return the range of matching dossier request uds
	*/
	public static List<DossierRequestUD> findByIS_NEW(int isNew, int start,
		int end) {
		return getPersistence().findByIS_NEW(isNew, start, end);
	}

	/**
	* Returns an ordered range of all the dossier request uds where isNew = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierRequestUDModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param isNew the is new
	* @param start the lower bound of the range of dossier request uds
	* @param end the upper bound of the range of dossier request uds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier request uds
	*/
	public static List<DossierRequestUD> findByIS_NEW(int isNew, int start,
		int end, OrderByComparator<DossierRequestUD> orderByComparator) {
		return getPersistence()
				   .findByIS_NEW(isNew, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier request uds where isNew = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierRequestUDModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param isNew the is new
	* @param start the lower bound of the range of dossier request uds
	* @param end the upper bound of the range of dossier request uds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier request uds
	*/
	public static List<DossierRequestUD> findByIS_NEW(int isNew, int start,
		int end, OrderByComparator<DossierRequestUD> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByIS_NEW(isNew, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first dossier request ud in the ordered set where isNew = &#63;.
	*
	* @param isNew the is new
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier request ud
	* @throws NoSuchDossierRequestUDException if a matching dossier request ud could not be found
	*/
	public static DossierRequestUD findByIS_NEW_First(int isNew,
		OrderByComparator<DossierRequestUD> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierRequestUDException {
		return getPersistence().findByIS_NEW_First(isNew, orderByComparator);
	}

	/**
	* Returns the first dossier request ud in the ordered set where isNew = &#63;.
	*
	* @param isNew the is new
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier request ud, or <code>null</code> if a matching dossier request ud could not be found
	*/
	public static DossierRequestUD fetchByIS_NEW_First(int isNew,
		OrderByComparator<DossierRequestUD> orderByComparator) {
		return getPersistence().fetchByIS_NEW_First(isNew, orderByComparator);
	}

	/**
	* Returns the last dossier request ud in the ordered set where isNew = &#63;.
	*
	* @param isNew the is new
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier request ud
	* @throws NoSuchDossierRequestUDException if a matching dossier request ud could not be found
	*/
	public static DossierRequestUD findByIS_NEW_Last(int isNew,
		OrderByComparator<DossierRequestUD> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierRequestUDException {
		return getPersistence().findByIS_NEW_Last(isNew, orderByComparator);
	}

	/**
	* Returns the last dossier request ud in the ordered set where isNew = &#63;.
	*
	* @param isNew the is new
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier request ud, or <code>null</code> if a matching dossier request ud could not be found
	*/
	public static DossierRequestUD fetchByIS_NEW_Last(int isNew,
		OrderByComparator<DossierRequestUD> orderByComparator) {
		return getPersistence().fetchByIS_NEW_Last(isNew, orderByComparator);
	}

	/**
	* Returns the dossier request uds before and after the current dossier request ud in the ordered set where isNew = &#63;.
	*
	* @param dossierRequestId the primary key of the current dossier request ud
	* @param isNew the is new
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier request ud
	* @throws NoSuchDossierRequestUDException if a dossier request ud with the primary key could not be found
	*/
	public static DossierRequestUD[] findByIS_NEW_PrevAndNext(
		long dossierRequestId, int isNew,
		OrderByComparator<DossierRequestUD> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierRequestUDException {
		return getPersistence()
				   .findByIS_NEW_PrevAndNext(dossierRequestId, isNew,
			orderByComparator);
	}

	/**
	* Removes all the dossier request uds where isNew = &#63; from the database.
	*
	* @param isNew the is new
	*/
	public static void removeByIS_NEW(int isNew) {
		getPersistence().removeByIS_NEW(isNew);
	}

	/**
	* Returns the number of dossier request uds where isNew = &#63;.
	*
	* @param isNew the is new
	* @return the number of matching dossier request uds
	*/
	public static int countByIS_NEW(int isNew) {
		return getPersistence().countByIS_NEW(isNew);
	}

	/**
	* Returns all the dossier request uds where dossierId = &#63; and isNew = &#63;.
	*
	* @param dossierId the dossier ID
	* @param isNew the is new
	* @return the matching dossier request uds
	*/
	public static List<DossierRequestUD> findByDID_IN(long dossierId, int isNew) {
		return getPersistence().findByDID_IN(dossierId, isNew);
	}

	/**
	* Returns a range of all the dossier request uds where dossierId = &#63; and isNew = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierRequestUDModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param isNew the is new
	* @param start the lower bound of the range of dossier request uds
	* @param end the upper bound of the range of dossier request uds (not inclusive)
	* @return the range of matching dossier request uds
	*/
	public static List<DossierRequestUD> findByDID_IN(long dossierId,
		int isNew, int start, int end) {
		return getPersistence().findByDID_IN(dossierId, isNew, start, end);
	}

	/**
	* Returns an ordered range of all the dossier request uds where dossierId = &#63; and isNew = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierRequestUDModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param isNew the is new
	* @param start the lower bound of the range of dossier request uds
	* @param end the upper bound of the range of dossier request uds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier request uds
	*/
	public static List<DossierRequestUD> findByDID_IN(long dossierId,
		int isNew, int start, int end,
		OrderByComparator<DossierRequestUD> orderByComparator) {
		return getPersistence()
				   .findByDID_IN(dossierId, isNew, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier request uds where dossierId = &#63; and isNew = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierRequestUDModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param isNew the is new
	* @param start the lower bound of the range of dossier request uds
	* @param end the upper bound of the range of dossier request uds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier request uds
	*/
	public static List<DossierRequestUD> findByDID_IN(long dossierId,
		int isNew, int start, int end,
		OrderByComparator<DossierRequestUD> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByDID_IN(dossierId, isNew, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier request ud in the ordered set where dossierId = &#63; and isNew = &#63;.
	*
	* @param dossierId the dossier ID
	* @param isNew the is new
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier request ud
	* @throws NoSuchDossierRequestUDException if a matching dossier request ud could not be found
	*/
	public static DossierRequestUD findByDID_IN_First(long dossierId,
		int isNew, OrderByComparator<DossierRequestUD> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierRequestUDException {
		return getPersistence()
				   .findByDID_IN_First(dossierId, isNew, orderByComparator);
	}

	/**
	* Returns the first dossier request ud in the ordered set where dossierId = &#63; and isNew = &#63;.
	*
	* @param dossierId the dossier ID
	* @param isNew the is new
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier request ud, or <code>null</code> if a matching dossier request ud could not be found
	*/
	public static DossierRequestUD fetchByDID_IN_First(long dossierId,
		int isNew, OrderByComparator<DossierRequestUD> orderByComparator) {
		return getPersistence()
				   .fetchByDID_IN_First(dossierId, isNew, orderByComparator);
	}

	/**
	* Returns the last dossier request ud in the ordered set where dossierId = &#63; and isNew = &#63;.
	*
	* @param dossierId the dossier ID
	* @param isNew the is new
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier request ud
	* @throws NoSuchDossierRequestUDException if a matching dossier request ud could not be found
	*/
	public static DossierRequestUD findByDID_IN_Last(long dossierId, int isNew,
		OrderByComparator<DossierRequestUD> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierRequestUDException {
		return getPersistence()
				   .findByDID_IN_Last(dossierId, isNew, orderByComparator);
	}

	/**
	* Returns the last dossier request ud in the ordered set where dossierId = &#63; and isNew = &#63;.
	*
	* @param dossierId the dossier ID
	* @param isNew the is new
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier request ud, or <code>null</code> if a matching dossier request ud could not be found
	*/
	public static DossierRequestUD fetchByDID_IN_Last(long dossierId,
		int isNew, OrderByComparator<DossierRequestUD> orderByComparator) {
		return getPersistence()
				   .fetchByDID_IN_Last(dossierId, isNew, orderByComparator);
	}

	/**
	* Returns the dossier request uds before and after the current dossier request ud in the ordered set where dossierId = &#63; and isNew = &#63;.
	*
	* @param dossierRequestId the primary key of the current dossier request ud
	* @param dossierId the dossier ID
	* @param isNew the is new
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier request ud
	* @throws NoSuchDossierRequestUDException if a dossier request ud with the primary key could not be found
	*/
	public static DossierRequestUD[] findByDID_IN_PrevAndNext(
		long dossierRequestId, long dossierId, int isNew,
		OrderByComparator<DossierRequestUD> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierRequestUDException {
		return getPersistence()
				   .findByDID_IN_PrevAndNext(dossierRequestId, dossierId,
			isNew, orderByComparator);
	}

	/**
	* Removes all the dossier request uds where dossierId = &#63; and isNew = &#63; from the database.
	*
	* @param dossierId the dossier ID
	* @param isNew the is new
	*/
	public static void removeByDID_IN(long dossierId, int isNew) {
		getPersistence().removeByDID_IN(dossierId, isNew);
	}

	/**
	* Returns the number of dossier request uds where dossierId = &#63; and isNew = &#63;.
	*
	* @param dossierId the dossier ID
	* @param isNew the is new
	* @return the number of matching dossier request uds
	*/
	public static int countByDID_IN(long dossierId, int isNew) {
		return getPersistence().countByDID_IN(dossierId, isNew);
	}

	/**
	* Caches the dossier request ud in the entity cache if it is enabled.
	*
	* @param dossierRequestUD the dossier request ud
	*/
	public static void cacheResult(DossierRequestUD dossierRequestUD) {
		getPersistence().cacheResult(dossierRequestUD);
	}

	/**
	* Caches the dossier request uds in the entity cache if it is enabled.
	*
	* @param dossierRequestUDs the dossier request uds
	*/
	public static void cacheResult(List<DossierRequestUD> dossierRequestUDs) {
		getPersistence().cacheResult(dossierRequestUDs);
	}

	/**
	* Creates a new dossier request ud with the primary key. Does not add the dossier request ud to the database.
	*
	* @param dossierRequestId the primary key for the new dossier request ud
	* @return the new dossier request ud
	*/
	public static DossierRequestUD create(long dossierRequestId) {
		return getPersistence().create(dossierRequestId);
	}

	/**
	* Removes the dossier request ud with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierRequestId the primary key of the dossier request ud
	* @return the dossier request ud that was removed
	* @throws NoSuchDossierRequestUDException if a dossier request ud with the primary key could not be found
	*/
	public static DossierRequestUD remove(long dossierRequestId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierRequestUDException {
		return getPersistence().remove(dossierRequestId);
	}

	public static DossierRequestUD updateImpl(DossierRequestUD dossierRequestUD) {
		return getPersistence().updateImpl(dossierRequestUD);
	}

	/**
	* Returns the dossier request ud with the primary key or throws a {@link NoSuchDossierRequestUDException} if it could not be found.
	*
	* @param dossierRequestId the primary key of the dossier request ud
	* @return the dossier request ud
	* @throws NoSuchDossierRequestUDException if a dossier request ud with the primary key could not be found
	*/
	public static DossierRequestUD findByPrimaryKey(long dossierRequestId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierRequestUDException {
		return getPersistence().findByPrimaryKey(dossierRequestId);
	}

	/**
	* Returns the dossier request ud with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param dossierRequestId the primary key of the dossier request ud
	* @return the dossier request ud, or <code>null</code> if a dossier request ud with the primary key could not be found
	*/
	public static DossierRequestUD fetchByPrimaryKey(long dossierRequestId) {
		return getPersistence().fetchByPrimaryKey(dossierRequestId);
	}

	public static java.util.Map<java.io.Serializable, DossierRequestUD> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the dossier request uds.
	*
	* @return the dossier request uds
	*/
	public static List<DossierRequestUD> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the dossier request uds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierRequestUDModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier request uds
	* @param end the upper bound of the range of dossier request uds (not inclusive)
	* @return the range of dossier request uds
	*/
	public static List<DossierRequestUD> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the dossier request uds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierRequestUDModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier request uds
	* @param end the upper bound of the range of dossier request uds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of dossier request uds
	*/
	public static List<DossierRequestUD> findAll(int start, int end,
		OrderByComparator<DossierRequestUD> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier request uds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierRequestUDModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier request uds
	* @param end the upper bound of the range of dossier request uds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of dossier request uds
	*/
	public static List<DossierRequestUD> findAll(int start, int end,
		OrderByComparator<DossierRequestUD> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the dossier request uds from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of dossier request uds.
	*
	* @return the number of dossier request uds
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static DossierRequestUDPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DossierRequestUDPersistence, DossierRequestUDPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(DossierRequestUDPersistence.class);

		ServiceTracker<DossierRequestUDPersistence, DossierRequestUDPersistence> serviceTracker =
			new ServiceTracker<DossierRequestUDPersistence, DossierRequestUDPersistence>(bundle.getBundleContext(),
				DossierRequestUDPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}