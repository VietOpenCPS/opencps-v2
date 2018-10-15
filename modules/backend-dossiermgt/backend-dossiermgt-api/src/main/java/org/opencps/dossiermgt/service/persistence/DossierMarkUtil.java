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

import org.opencps.dossiermgt.model.DossierMark;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the dossier mark service. This utility wraps {@link org.opencps.dossiermgt.service.persistence.impl.DossierMarkPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see DossierMarkPersistence
 * @see org.opencps.dossiermgt.service.persistence.impl.DossierMarkPersistenceImpl
 * @generated
 */
@ProviderType
public class DossierMarkUtil {
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
	public static void clearCache(DossierMark dossierMark) {
		getPersistence().clearCache(dossierMark);
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
	public static List<DossierMark> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<DossierMark> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<DossierMark> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<DossierMark> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static DossierMark update(DossierMark dossierMark) {
		return getPersistence().update(dossierMark);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static DossierMark update(DossierMark dossierMark,
		ServiceContext serviceContext) {
		return getPersistence().update(dossierMark, serviceContext);
	}

	/**
	* Returns all the dossier marks where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching dossier marks
	*/
	public static List<DossierMark> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the dossier marks where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierMarkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier marks
	* @param end the upper bound of the range of dossier marks (not inclusive)
	* @return the range of matching dossier marks
	*/
	public static List<DossierMark> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the dossier marks where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierMarkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier marks
	* @param end the upper bound of the range of dossier marks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier marks
	*/
	public static List<DossierMark> findByUuid(String uuid, int start, int end,
		OrderByComparator<DossierMark> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier marks where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierMarkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier marks
	* @param end the upper bound of the range of dossier marks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier marks
	*/
	public static List<DossierMark> findByUuid(String uuid, int start, int end,
		OrderByComparator<DossierMark> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first dossier mark in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier mark
	* @throws NoSuchDossierMarkException if a matching dossier mark could not be found
	*/
	public static DossierMark findByUuid_First(String uuid,
		OrderByComparator<DossierMark> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierMarkException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first dossier mark in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier mark, or <code>null</code> if a matching dossier mark could not be found
	*/
	public static DossierMark fetchByUuid_First(String uuid,
		OrderByComparator<DossierMark> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last dossier mark in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier mark
	* @throws NoSuchDossierMarkException if a matching dossier mark could not be found
	*/
	public static DossierMark findByUuid_Last(String uuid,
		OrderByComparator<DossierMark> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierMarkException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last dossier mark in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier mark, or <code>null</code> if a matching dossier mark could not be found
	*/
	public static DossierMark fetchByUuid_Last(String uuid,
		OrderByComparator<DossierMark> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the dossier marks before and after the current dossier mark in the ordered set where uuid = &#63;.
	*
	* @param dossierMarkId the primary key of the current dossier mark
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier mark
	* @throws NoSuchDossierMarkException if a dossier mark with the primary key could not be found
	*/
	public static DossierMark[] findByUuid_PrevAndNext(long dossierMarkId,
		String uuid, OrderByComparator<DossierMark> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierMarkException {
		return getPersistence()
				   .findByUuid_PrevAndNext(dossierMarkId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the dossier marks where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of dossier marks where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching dossier marks
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the dossier mark where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDossierMarkException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dossier mark
	* @throws NoSuchDossierMarkException if a matching dossier mark could not be found
	*/
	public static DossierMark findByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierMarkException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the dossier mark where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dossier mark, or <code>null</code> if a matching dossier mark could not be found
	*/
	public static DossierMark fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the dossier mark where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier mark, or <code>null</code> if a matching dossier mark could not be found
	*/
	public static DossierMark fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the dossier mark where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the dossier mark that was removed
	*/
	public static DossierMark removeByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierMarkException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of dossier marks where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching dossier marks
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the dossier marks where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching dossier marks
	*/
	public static List<DossierMark> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the dossier marks where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierMarkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossier marks
	* @param end the upper bound of the range of dossier marks (not inclusive)
	* @return the range of matching dossier marks
	*/
	public static List<DossierMark> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the dossier marks where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierMarkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossier marks
	* @param end the upper bound of the range of dossier marks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier marks
	*/
	public static List<DossierMark> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<DossierMark> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier marks where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierMarkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossier marks
	* @param end the upper bound of the range of dossier marks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier marks
	*/
	public static List<DossierMark> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<DossierMark> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier mark in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier mark
	* @throws NoSuchDossierMarkException if a matching dossier mark could not be found
	*/
	public static DossierMark findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<DossierMark> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierMarkException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first dossier mark in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier mark, or <code>null</code> if a matching dossier mark could not be found
	*/
	public static DossierMark fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<DossierMark> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last dossier mark in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier mark
	* @throws NoSuchDossierMarkException if a matching dossier mark could not be found
	*/
	public static DossierMark findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DossierMark> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierMarkException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last dossier mark in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier mark, or <code>null</code> if a matching dossier mark could not be found
	*/
	public static DossierMark fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DossierMark> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the dossier marks before and after the current dossier mark in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param dossierMarkId the primary key of the current dossier mark
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier mark
	* @throws NoSuchDossierMarkException if a dossier mark with the primary key could not be found
	*/
	public static DossierMark[] findByUuid_C_PrevAndNext(long dossierMarkId,
		String uuid, long companyId,
		OrderByComparator<DossierMark> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierMarkException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(dossierMarkId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the dossier marks where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of dossier marks where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching dossier marks
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns the dossier mark where groupId = &#63; and dossierId = &#63; and dossierPartNo = &#63; or throws a {@link NoSuchDossierMarkException} if it could not be found.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param dossierPartNo the dossier part no
	* @return the matching dossier mark
	* @throws NoSuchDossierMarkException if a matching dossier mark could not be found
	*/
	public static DossierMark findByG_DID_PN(long groupId, long dossierId,
		String dossierPartNo)
		throws org.opencps.dossiermgt.exception.NoSuchDossierMarkException {
		return getPersistence().findByG_DID_PN(groupId, dossierId, dossierPartNo);
	}

	/**
	* Returns the dossier mark where groupId = &#63; and dossierId = &#63; and dossierPartNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param dossierPartNo the dossier part no
	* @return the matching dossier mark, or <code>null</code> if a matching dossier mark could not be found
	*/
	public static DossierMark fetchByG_DID_PN(long groupId, long dossierId,
		String dossierPartNo) {
		return getPersistence()
				   .fetchByG_DID_PN(groupId, dossierId, dossierPartNo);
	}

	/**
	* Returns the dossier mark where groupId = &#63; and dossierId = &#63; and dossierPartNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param dossierPartNo the dossier part no
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier mark, or <code>null</code> if a matching dossier mark could not be found
	*/
	public static DossierMark fetchByG_DID_PN(long groupId, long dossierId,
		String dossierPartNo, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByG_DID_PN(groupId, dossierId, dossierPartNo,
			retrieveFromCache);
	}

	/**
	* Removes the dossier mark where groupId = &#63; and dossierId = &#63; and dossierPartNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param dossierPartNo the dossier part no
	* @return the dossier mark that was removed
	*/
	public static DossierMark removeByG_DID_PN(long groupId, long dossierId,
		String dossierPartNo)
		throws org.opencps.dossiermgt.exception.NoSuchDossierMarkException {
		return getPersistence()
				   .removeByG_DID_PN(groupId, dossierId, dossierPartNo);
	}

	/**
	* Returns the number of dossier marks where groupId = &#63; and dossierId = &#63; and dossierPartNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param dossierPartNo the dossier part no
	* @return the number of matching dossier marks
	*/
	public static int countByG_DID_PN(long groupId, long dossierId,
		String dossierPartNo) {
		return getPersistence()
				   .countByG_DID_PN(groupId, dossierId, dossierPartNo);
	}

	/**
	* Returns all the dossier marks where groupId = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @return the matching dossier marks
	*/
	public static List<DossierMark> findByG_DID(long groupId, long dossierId) {
		return getPersistence().findByG_DID(groupId, dossierId);
	}

	/**
	* Returns a range of all the dossier marks where groupId = &#63; and dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierMarkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of dossier marks
	* @param end the upper bound of the range of dossier marks (not inclusive)
	* @return the range of matching dossier marks
	*/
	public static List<DossierMark> findByG_DID(long groupId, long dossierId,
		int start, int end) {
		return getPersistence().findByG_DID(groupId, dossierId, start, end);
	}

	/**
	* Returns an ordered range of all the dossier marks where groupId = &#63; and dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierMarkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of dossier marks
	* @param end the upper bound of the range of dossier marks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier marks
	*/
	public static List<DossierMark> findByG_DID(long groupId, long dossierId,
		int start, int end, OrderByComparator<DossierMark> orderByComparator) {
		return getPersistence()
				   .findByG_DID(groupId, dossierId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier marks where groupId = &#63; and dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierMarkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of dossier marks
	* @param end the upper bound of the range of dossier marks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier marks
	*/
	public static List<DossierMark> findByG_DID(long groupId, long dossierId,
		int start, int end, OrderByComparator<DossierMark> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_DID(groupId, dossierId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier mark in the ordered set where groupId = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier mark
	* @throws NoSuchDossierMarkException if a matching dossier mark could not be found
	*/
	public static DossierMark findByG_DID_First(long groupId, long dossierId,
		OrderByComparator<DossierMark> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierMarkException {
		return getPersistence()
				   .findByG_DID_First(groupId, dossierId, orderByComparator);
	}

	/**
	* Returns the first dossier mark in the ordered set where groupId = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier mark, or <code>null</code> if a matching dossier mark could not be found
	*/
	public static DossierMark fetchByG_DID_First(long groupId, long dossierId,
		OrderByComparator<DossierMark> orderByComparator) {
		return getPersistence()
				   .fetchByG_DID_First(groupId, dossierId, orderByComparator);
	}

	/**
	* Returns the last dossier mark in the ordered set where groupId = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier mark
	* @throws NoSuchDossierMarkException if a matching dossier mark could not be found
	*/
	public static DossierMark findByG_DID_Last(long groupId, long dossierId,
		OrderByComparator<DossierMark> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierMarkException {
		return getPersistence()
				   .findByG_DID_Last(groupId, dossierId, orderByComparator);
	}

	/**
	* Returns the last dossier mark in the ordered set where groupId = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier mark, or <code>null</code> if a matching dossier mark could not be found
	*/
	public static DossierMark fetchByG_DID_Last(long groupId, long dossierId,
		OrderByComparator<DossierMark> orderByComparator) {
		return getPersistence()
				   .fetchByG_DID_Last(groupId, dossierId, orderByComparator);
	}

	/**
	* Returns the dossier marks before and after the current dossier mark in the ordered set where groupId = &#63; and dossierId = &#63;.
	*
	* @param dossierMarkId the primary key of the current dossier mark
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier mark
	* @throws NoSuchDossierMarkException if a dossier mark with the primary key could not be found
	*/
	public static DossierMark[] findByG_DID_PrevAndNext(long dossierMarkId,
		long groupId, long dossierId,
		OrderByComparator<DossierMark> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierMarkException {
		return getPersistence()
				   .findByG_DID_PrevAndNext(dossierMarkId, groupId, dossierId,
			orderByComparator);
	}

	/**
	* Removes all the dossier marks where groupId = &#63; and dossierId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	*/
	public static void removeByG_DID(long groupId, long dossierId) {
		getPersistence().removeByG_DID(groupId, dossierId);
	}

	/**
	* Returns the number of dossier marks where groupId = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @return the number of matching dossier marks
	*/
	public static int countByG_DID(long groupId, long dossierId) {
		return getPersistence().countByG_DID(groupId, dossierId);
	}

	/**
	* Returns all the dossier marks where groupId = &#63; and dossierId = &#63; and fileMark &ne; &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param fileMark the file mark
	* @return the matching dossier marks
	*/
	public static List<DossierMark> findByG_DID_MARK(long groupId,
		long dossierId, int fileMark) {
		return getPersistence().findByG_DID_MARK(groupId, dossierId, fileMark);
	}

	/**
	* Returns a range of all the dossier marks where groupId = &#63; and dossierId = &#63; and fileMark &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierMarkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param fileMark the file mark
	* @param start the lower bound of the range of dossier marks
	* @param end the upper bound of the range of dossier marks (not inclusive)
	* @return the range of matching dossier marks
	*/
	public static List<DossierMark> findByG_DID_MARK(long groupId,
		long dossierId, int fileMark, int start, int end) {
		return getPersistence()
				   .findByG_DID_MARK(groupId, dossierId, fileMark, start, end);
	}

	/**
	* Returns an ordered range of all the dossier marks where groupId = &#63; and dossierId = &#63; and fileMark &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierMarkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param fileMark the file mark
	* @param start the lower bound of the range of dossier marks
	* @param end the upper bound of the range of dossier marks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier marks
	*/
	public static List<DossierMark> findByG_DID_MARK(long groupId,
		long dossierId, int fileMark, int start, int end,
		OrderByComparator<DossierMark> orderByComparator) {
		return getPersistence()
				   .findByG_DID_MARK(groupId, dossierId, fileMark, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier marks where groupId = &#63; and dossierId = &#63; and fileMark &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierMarkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param fileMark the file mark
	* @param start the lower bound of the range of dossier marks
	* @param end the upper bound of the range of dossier marks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier marks
	*/
	public static List<DossierMark> findByG_DID_MARK(long groupId,
		long dossierId, int fileMark, int start, int end,
		OrderByComparator<DossierMark> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_DID_MARK(groupId, dossierId, fileMark, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier mark in the ordered set where groupId = &#63; and dossierId = &#63; and fileMark &ne; &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param fileMark the file mark
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier mark
	* @throws NoSuchDossierMarkException if a matching dossier mark could not be found
	*/
	public static DossierMark findByG_DID_MARK_First(long groupId,
		long dossierId, int fileMark,
		OrderByComparator<DossierMark> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierMarkException {
		return getPersistence()
				   .findByG_DID_MARK_First(groupId, dossierId, fileMark,
			orderByComparator);
	}

	/**
	* Returns the first dossier mark in the ordered set where groupId = &#63; and dossierId = &#63; and fileMark &ne; &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param fileMark the file mark
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier mark, or <code>null</code> if a matching dossier mark could not be found
	*/
	public static DossierMark fetchByG_DID_MARK_First(long groupId,
		long dossierId, int fileMark,
		OrderByComparator<DossierMark> orderByComparator) {
		return getPersistence()
				   .fetchByG_DID_MARK_First(groupId, dossierId, fileMark,
			orderByComparator);
	}

	/**
	* Returns the last dossier mark in the ordered set where groupId = &#63; and dossierId = &#63; and fileMark &ne; &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param fileMark the file mark
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier mark
	* @throws NoSuchDossierMarkException if a matching dossier mark could not be found
	*/
	public static DossierMark findByG_DID_MARK_Last(long groupId,
		long dossierId, int fileMark,
		OrderByComparator<DossierMark> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierMarkException {
		return getPersistence()
				   .findByG_DID_MARK_Last(groupId, dossierId, fileMark,
			orderByComparator);
	}

	/**
	* Returns the last dossier mark in the ordered set where groupId = &#63; and dossierId = &#63; and fileMark &ne; &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param fileMark the file mark
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier mark, or <code>null</code> if a matching dossier mark could not be found
	*/
	public static DossierMark fetchByG_DID_MARK_Last(long groupId,
		long dossierId, int fileMark,
		OrderByComparator<DossierMark> orderByComparator) {
		return getPersistence()
				   .fetchByG_DID_MARK_Last(groupId, dossierId, fileMark,
			orderByComparator);
	}

	/**
	* Returns the dossier marks before and after the current dossier mark in the ordered set where groupId = &#63; and dossierId = &#63; and fileMark &ne; &#63;.
	*
	* @param dossierMarkId the primary key of the current dossier mark
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param fileMark the file mark
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier mark
	* @throws NoSuchDossierMarkException if a dossier mark with the primary key could not be found
	*/
	public static DossierMark[] findByG_DID_MARK_PrevAndNext(
		long dossierMarkId, long groupId, long dossierId, int fileMark,
		OrderByComparator<DossierMark> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierMarkException {
		return getPersistence()
				   .findByG_DID_MARK_PrevAndNext(dossierMarkId, groupId,
			dossierId, fileMark, orderByComparator);
	}

	/**
	* Removes all the dossier marks where groupId = &#63; and dossierId = &#63; and fileMark &ne; &#63; from the database.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param fileMark the file mark
	*/
	public static void removeByG_DID_MARK(long groupId, long dossierId,
		int fileMark) {
		getPersistence().removeByG_DID_MARK(groupId, dossierId, fileMark);
	}

	/**
	* Returns the number of dossier marks where groupId = &#63; and dossierId = &#63; and fileMark &ne; &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param fileMark the file mark
	* @return the number of matching dossier marks
	*/
	public static int countByG_DID_MARK(long groupId, long dossierId,
		int fileMark) {
		return getPersistence().countByG_DID_MARK(groupId, dossierId, fileMark);
	}

	/**
	* Caches the dossier mark in the entity cache if it is enabled.
	*
	* @param dossierMark the dossier mark
	*/
	public static void cacheResult(DossierMark dossierMark) {
		getPersistence().cacheResult(dossierMark);
	}

	/**
	* Caches the dossier marks in the entity cache if it is enabled.
	*
	* @param dossierMarks the dossier marks
	*/
	public static void cacheResult(List<DossierMark> dossierMarks) {
		getPersistence().cacheResult(dossierMarks);
	}

	/**
	* Creates a new dossier mark with the primary key. Does not add the dossier mark to the database.
	*
	* @param dossierMarkId the primary key for the new dossier mark
	* @return the new dossier mark
	*/
	public static DossierMark create(long dossierMarkId) {
		return getPersistence().create(dossierMarkId);
	}

	/**
	* Removes the dossier mark with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierMarkId the primary key of the dossier mark
	* @return the dossier mark that was removed
	* @throws NoSuchDossierMarkException if a dossier mark with the primary key could not be found
	*/
	public static DossierMark remove(long dossierMarkId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierMarkException {
		return getPersistence().remove(dossierMarkId);
	}

	public static DossierMark updateImpl(DossierMark dossierMark) {
		return getPersistence().updateImpl(dossierMark);
	}

	/**
	* Returns the dossier mark with the primary key or throws a {@link NoSuchDossierMarkException} if it could not be found.
	*
	* @param dossierMarkId the primary key of the dossier mark
	* @return the dossier mark
	* @throws NoSuchDossierMarkException if a dossier mark with the primary key could not be found
	*/
	public static DossierMark findByPrimaryKey(long dossierMarkId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierMarkException {
		return getPersistence().findByPrimaryKey(dossierMarkId);
	}

	/**
	* Returns the dossier mark with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param dossierMarkId the primary key of the dossier mark
	* @return the dossier mark, or <code>null</code> if a dossier mark with the primary key could not be found
	*/
	public static DossierMark fetchByPrimaryKey(long dossierMarkId) {
		return getPersistence().fetchByPrimaryKey(dossierMarkId);
	}

	public static java.util.Map<java.io.Serializable, DossierMark> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the dossier marks.
	*
	* @return the dossier marks
	*/
	public static List<DossierMark> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the dossier marks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierMarkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier marks
	* @param end the upper bound of the range of dossier marks (not inclusive)
	* @return the range of dossier marks
	*/
	public static List<DossierMark> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the dossier marks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierMarkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier marks
	* @param end the upper bound of the range of dossier marks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of dossier marks
	*/
	public static List<DossierMark> findAll(int start, int end,
		OrderByComparator<DossierMark> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier marks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierMarkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier marks
	* @param end the upper bound of the range of dossier marks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of dossier marks
	*/
	public static List<DossierMark> findAll(int start, int end,
		OrderByComparator<DossierMark> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the dossier marks from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of dossier marks.
	*
	* @return the number of dossier marks
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static DossierMarkPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DossierMarkPersistence, DossierMarkPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(DossierMarkPersistence.class);

		ServiceTracker<DossierMarkPersistence, DossierMarkPersistence> serviceTracker =
			new ServiceTracker<DossierMarkPersistence, DossierMarkPersistence>(bundle.getBundleContext(),
				DossierMarkPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}