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

import org.opencps.dossiermgt.model.DossierPart;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the dossier part service. This utility wraps {@link org.opencps.dossiermgt.service.persistence.impl.DossierPartPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see DossierPartPersistence
 * @see org.opencps.dossiermgt.service.persistence.impl.DossierPartPersistenceImpl
 * @generated
 */
@ProviderType
public class DossierPartUtil {
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
	public static void clearCache(DossierPart dossierPart) {
		getPersistence().clearCache(dossierPart);
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
	public static List<DossierPart> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<DossierPart> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<DossierPart> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<DossierPart> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static DossierPart update(DossierPart dossierPart) {
		return getPersistence().update(dossierPart);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static DossierPart update(DossierPart dossierPart,
		ServiceContext serviceContext) {
		return getPersistence().update(dossierPart, serviceContext);
	}

	/**
	* Returns all the dossier parts where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching dossier parts
	*/
	public static List<DossierPart> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the dossier parts where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierPartModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier parts
	* @param end the upper bound of the range of dossier parts (not inclusive)
	* @return the range of matching dossier parts
	*/
	public static List<DossierPart> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the dossier parts where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierPartModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier parts
	* @param end the upper bound of the range of dossier parts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier parts
	*/
	public static List<DossierPart> findByUuid(String uuid, int start, int end,
		OrderByComparator<DossierPart> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier parts where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierPartModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier parts
	* @param end the upper bound of the range of dossier parts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier parts
	*/
	public static List<DossierPart> findByUuid(String uuid, int start, int end,
		OrderByComparator<DossierPart> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first dossier part in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier part
	* @throws NoSuchDossierPartException if a matching dossier part could not be found
	*/
	public static DossierPart findByUuid_First(String uuid,
		OrderByComparator<DossierPart> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierPartException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first dossier part in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier part, or <code>null</code> if a matching dossier part could not be found
	*/
	public static DossierPart fetchByUuid_First(String uuid,
		OrderByComparator<DossierPart> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last dossier part in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier part
	* @throws NoSuchDossierPartException if a matching dossier part could not be found
	*/
	public static DossierPart findByUuid_Last(String uuid,
		OrderByComparator<DossierPart> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierPartException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last dossier part in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier part, or <code>null</code> if a matching dossier part could not be found
	*/
	public static DossierPart fetchByUuid_Last(String uuid,
		OrderByComparator<DossierPart> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the dossier parts before and after the current dossier part in the ordered set where uuid = &#63;.
	*
	* @param dossierPartId the primary key of the current dossier part
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier part
	* @throws NoSuchDossierPartException if a dossier part with the primary key could not be found
	*/
	public static DossierPart[] findByUuid_PrevAndNext(long dossierPartId,
		String uuid, OrderByComparator<DossierPart> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierPartException {
		return getPersistence()
				   .findByUuid_PrevAndNext(dossierPartId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the dossier parts where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of dossier parts where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching dossier parts
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the dossier part where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDossierPartException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dossier part
	* @throws NoSuchDossierPartException if a matching dossier part could not be found
	*/
	public static DossierPart findByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierPartException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the dossier part where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dossier part, or <code>null</code> if a matching dossier part could not be found
	*/
	public static DossierPart fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the dossier part where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier part, or <code>null</code> if a matching dossier part could not be found
	*/
	public static DossierPart fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the dossier part where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the dossier part that was removed
	*/
	public static DossierPart removeByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierPartException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of dossier parts where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching dossier parts
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the dossier parts where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching dossier parts
	*/
	public static List<DossierPart> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the dossier parts where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierPartModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossier parts
	* @param end the upper bound of the range of dossier parts (not inclusive)
	* @return the range of matching dossier parts
	*/
	public static List<DossierPart> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the dossier parts where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierPartModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossier parts
	* @param end the upper bound of the range of dossier parts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier parts
	*/
	public static List<DossierPart> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<DossierPart> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier parts where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierPartModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossier parts
	* @param end the upper bound of the range of dossier parts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier parts
	*/
	public static List<DossierPart> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<DossierPart> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier part in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier part
	* @throws NoSuchDossierPartException if a matching dossier part could not be found
	*/
	public static DossierPart findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<DossierPart> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierPartException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first dossier part in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier part, or <code>null</code> if a matching dossier part could not be found
	*/
	public static DossierPart fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<DossierPart> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last dossier part in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier part
	* @throws NoSuchDossierPartException if a matching dossier part could not be found
	*/
	public static DossierPart findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DossierPart> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierPartException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last dossier part in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier part, or <code>null</code> if a matching dossier part could not be found
	*/
	public static DossierPart fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DossierPart> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the dossier parts before and after the current dossier part in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param dossierPartId the primary key of the current dossier part
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier part
	* @throws NoSuchDossierPartException if a dossier part with the primary key could not be found
	*/
	public static DossierPart[] findByUuid_C_PrevAndNext(long dossierPartId,
		String uuid, long companyId,
		OrderByComparator<DossierPart> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierPartException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(dossierPartId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the dossier parts where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of dossier parts where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching dossier parts
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the dossier parts where groupId = &#63; and templateNo = &#63;.
	*
	* @param groupId the group ID
	* @param templateNo the template no
	* @return the matching dossier parts
	*/
	public static List<DossierPart> findByTP_NO(long groupId, String templateNo) {
		return getPersistence().findByTP_NO(groupId, templateNo);
	}

	/**
	* Returns a range of all the dossier parts where groupId = &#63; and templateNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierPartModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param templateNo the template no
	* @param start the lower bound of the range of dossier parts
	* @param end the upper bound of the range of dossier parts (not inclusive)
	* @return the range of matching dossier parts
	*/
	public static List<DossierPart> findByTP_NO(long groupId,
		String templateNo, int start, int end) {
		return getPersistence().findByTP_NO(groupId, templateNo, start, end);
	}

	/**
	* Returns an ordered range of all the dossier parts where groupId = &#63; and templateNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierPartModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param templateNo the template no
	* @param start the lower bound of the range of dossier parts
	* @param end the upper bound of the range of dossier parts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier parts
	*/
	public static List<DossierPart> findByTP_NO(long groupId,
		String templateNo, int start, int end,
		OrderByComparator<DossierPart> orderByComparator) {
		return getPersistence()
				   .findByTP_NO(groupId, templateNo, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier parts where groupId = &#63; and templateNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierPartModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param templateNo the template no
	* @param start the lower bound of the range of dossier parts
	* @param end the upper bound of the range of dossier parts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier parts
	*/
	public static List<DossierPart> findByTP_NO(long groupId,
		String templateNo, int start, int end,
		OrderByComparator<DossierPart> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByTP_NO(groupId, templateNo, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier part in the ordered set where groupId = &#63; and templateNo = &#63;.
	*
	* @param groupId the group ID
	* @param templateNo the template no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier part
	* @throws NoSuchDossierPartException if a matching dossier part could not be found
	*/
	public static DossierPart findByTP_NO_First(long groupId,
		String templateNo, OrderByComparator<DossierPart> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierPartException {
		return getPersistence()
				   .findByTP_NO_First(groupId, templateNo, orderByComparator);
	}

	/**
	* Returns the first dossier part in the ordered set where groupId = &#63; and templateNo = &#63;.
	*
	* @param groupId the group ID
	* @param templateNo the template no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier part, or <code>null</code> if a matching dossier part could not be found
	*/
	public static DossierPart fetchByTP_NO_First(long groupId,
		String templateNo, OrderByComparator<DossierPart> orderByComparator) {
		return getPersistence()
				   .fetchByTP_NO_First(groupId, templateNo, orderByComparator);
	}

	/**
	* Returns the last dossier part in the ordered set where groupId = &#63; and templateNo = &#63;.
	*
	* @param groupId the group ID
	* @param templateNo the template no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier part
	* @throws NoSuchDossierPartException if a matching dossier part could not be found
	*/
	public static DossierPart findByTP_NO_Last(long groupId, String templateNo,
		OrderByComparator<DossierPart> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierPartException {
		return getPersistence()
				   .findByTP_NO_Last(groupId, templateNo, orderByComparator);
	}

	/**
	* Returns the last dossier part in the ordered set where groupId = &#63; and templateNo = &#63;.
	*
	* @param groupId the group ID
	* @param templateNo the template no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier part, or <code>null</code> if a matching dossier part could not be found
	*/
	public static DossierPart fetchByTP_NO_Last(long groupId,
		String templateNo, OrderByComparator<DossierPart> orderByComparator) {
		return getPersistence()
				   .fetchByTP_NO_Last(groupId, templateNo, orderByComparator);
	}

	/**
	* Returns the dossier parts before and after the current dossier part in the ordered set where groupId = &#63; and templateNo = &#63;.
	*
	* @param dossierPartId the primary key of the current dossier part
	* @param groupId the group ID
	* @param templateNo the template no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier part
	* @throws NoSuchDossierPartException if a dossier part with the primary key could not be found
	*/
	public static DossierPart[] findByTP_NO_PrevAndNext(long dossierPartId,
		long groupId, String templateNo,
		OrderByComparator<DossierPart> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierPartException {
		return getPersistence()
				   .findByTP_NO_PrevAndNext(dossierPartId, groupId, templateNo,
			orderByComparator);
	}

	/**
	* Removes all the dossier parts where groupId = &#63; and templateNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param templateNo the template no
	*/
	public static void removeByTP_NO(long groupId, String templateNo) {
		getPersistence().removeByTP_NO(groupId, templateNo);
	}

	/**
	* Returns the number of dossier parts where groupId = &#63; and templateNo = &#63;.
	*
	* @param groupId the group ID
	* @param templateNo the template no
	* @return the number of matching dossier parts
	*/
	public static int countByTP_NO(long groupId, String templateNo) {
		return getPersistence().countByTP_NO(groupId, templateNo);
	}

	/**
	* Returns the dossier part where groupId = &#63; and templateNo = &#63; and partNo = &#63; or throws a {@link NoSuchDossierPartException} if it could not be found.
	*
	* @param groupId the group ID
	* @param templateNo the template no
	* @param partNo the part no
	* @return the matching dossier part
	* @throws NoSuchDossierPartException if a matching dossier part could not be found
	*/
	public static DossierPart findByTP_NO_PART(long groupId, String templateNo,
		String partNo)
		throws org.opencps.dossiermgt.exception.NoSuchDossierPartException {
		return getPersistence().findByTP_NO_PART(groupId, templateNo, partNo);
	}

	/**
	* Returns the dossier part where groupId = &#63; and templateNo = &#63; and partNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param templateNo the template no
	* @param partNo the part no
	* @return the matching dossier part, or <code>null</code> if a matching dossier part could not be found
	*/
	public static DossierPart fetchByTP_NO_PART(long groupId,
		String templateNo, String partNo) {
		return getPersistence().fetchByTP_NO_PART(groupId, templateNo, partNo);
	}

	/**
	* Returns the dossier part where groupId = &#63; and templateNo = &#63; and partNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param templateNo the template no
	* @param partNo the part no
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier part, or <code>null</code> if a matching dossier part could not be found
	*/
	public static DossierPart fetchByTP_NO_PART(long groupId,
		String templateNo, String partNo, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByTP_NO_PART(groupId, templateNo, partNo,
			retrieveFromCache);
	}

	/**
	* Removes the dossier part where groupId = &#63; and templateNo = &#63; and partNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param templateNo the template no
	* @param partNo the part no
	* @return the dossier part that was removed
	*/
	public static DossierPart removeByTP_NO_PART(long groupId,
		String templateNo, String partNo)
		throws org.opencps.dossiermgt.exception.NoSuchDossierPartException {
		return getPersistence().removeByTP_NO_PART(groupId, templateNo, partNo);
	}

	/**
	* Returns the number of dossier parts where groupId = &#63; and templateNo = &#63; and partNo = &#63;.
	*
	* @param groupId the group ID
	* @param templateNo the template no
	* @param partNo the part no
	* @return the number of matching dossier parts
	*/
	public static int countByTP_NO_PART(long groupId, String templateNo,
		String partNo) {
		return getPersistence().countByTP_NO_PART(groupId, templateNo, partNo);
	}

	/**
	* Returns the dossier part where groupId = &#63; and fileTemplateNo = &#63; or throws a {@link NoSuchDossierPartException} if it could not be found.
	*
	* @param groupId the group ID
	* @param fileTemplateNo the file template no
	* @return the matching dossier part
	* @throws NoSuchDossierPartException if a matching dossier part could not be found
	*/
	public static DossierPart findByGID_FTN(long groupId, String fileTemplateNo)
		throws org.opencps.dossiermgt.exception.NoSuchDossierPartException {
		return getPersistence().findByGID_FTN(groupId, fileTemplateNo);
	}

	/**
	* Returns the dossier part where groupId = &#63; and fileTemplateNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param fileTemplateNo the file template no
	* @return the matching dossier part, or <code>null</code> if a matching dossier part could not be found
	*/
	public static DossierPart fetchByGID_FTN(long groupId, String fileTemplateNo) {
		return getPersistence().fetchByGID_FTN(groupId, fileTemplateNo);
	}

	/**
	* Returns the dossier part where groupId = &#63; and fileTemplateNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param fileTemplateNo the file template no
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier part, or <code>null</code> if a matching dossier part could not be found
	*/
	public static DossierPart fetchByGID_FTN(long groupId,
		String fileTemplateNo, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByGID_FTN(groupId, fileTemplateNo, retrieveFromCache);
	}

	/**
	* Removes the dossier part where groupId = &#63; and fileTemplateNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param fileTemplateNo the file template no
	* @return the dossier part that was removed
	*/
	public static DossierPart removeByGID_FTN(long groupId,
		String fileTemplateNo)
		throws org.opencps.dossiermgt.exception.NoSuchDossierPartException {
		return getPersistence().removeByGID_FTN(groupId, fileTemplateNo);
	}

	/**
	* Returns the number of dossier parts where groupId = &#63; and fileTemplateNo = &#63;.
	*
	* @param groupId the group ID
	* @param fileTemplateNo the file template no
	* @return the number of matching dossier parts
	*/
	public static int countByGID_FTN(long groupId, String fileTemplateNo) {
		return getPersistence().countByGID_FTN(groupId, fileTemplateNo);
	}

	/**
	* Returns the dossier part where templateNo = &#63; and partNo = &#63; and partType = &#63; and eSign = &#63; or throws a {@link NoSuchDossierPartException} if it could not be found.
	*
	* @param templateNo the template no
	* @param partNo the part no
	* @param partType the part type
	* @param eSign the e sign
	* @return the matching dossier part
	* @throws NoSuchDossierPartException if a matching dossier part could not be found
	*/
	public static DossierPart findByTP_NO_PART_ESIGN(String templateNo,
		String partNo, int partType, boolean eSign)
		throws org.opencps.dossiermgt.exception.NoSuchDossierPartException {
		return getPersistence()
				   .findByTP_NO_PART_ESIGN(templateNo, partNo, partType, eSign);
	}

	/**
	* Returns the dossier part where templateNo = &#63; and partNo = &#63; and partType = &#63; and eSign = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param templateNo the template no
	* @param partNo the part no
	* @param partType the part type
	* @param eSign the e sign
	* @return the matching dossier part, or <code>null</code> if a matching dossier part could not be found
	*/
	public static DossierPart fetchByTP_NO_PART_ESIGN(String templateNo,
		String partNo, int partType, boolean eSign) {
		return getPersistence()
				   .fetchByTP_NO_PART_ESIGN(templateNo, partNo, partType, eSign);
	}

	/**
	* Returns the dossier part where templateNo = &#63; and partNo = &#63; and partType = &#63; and eSign = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param templateNo the template no
	* @param partNo the part no
	* @param partType the part type
	* @param eSign the e sign
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier part, or <code>null</code> if a matching dossier part could not be found
	*/
	public static DossierPart fetchByTP_NO_PART_ESIGN(String templateNo,
		String partNo, int partType, boolean eSign, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByTP_NO_PART_ESIGN(templateNo, partNo, partType,
			eSign, retrieveFromCache);
	}

	/**
	* Removes the dossier part where templateNo = &#63; and partNo = &#63; and partType = &#63; and eSign = &#63; from the database.
	*
	* @param templateNo the template no
	* @param partNo the part no
	* @param partType the part type
	* @param eSign the e sign
	* @return the dossier part that was removed
	*/
	public static DossierPart removeByTP_NO_PART_ESIGN(String templateNo,
		String partNo, int partType, boolean eSign)
		throws org.opencps.dossiermgt.exception.NoSuchDossierPartException {
		return getPersistence()
				   .removeByTP_NO_PART_ESIGN(templateNo, partNo, partType, eSign);
	}

	/**
	* Returns the number of dossier parts where templateNo = &#63; and partNo = &#63; and partType = &#63; and eSign = &#63;.
	*
	* @param templateNo the template no
	* @param partNo the part no
	* @param partType the part type
	* @param eSign the e sign
	* @return the number of matching dossier parts
	*/
	public static int countByTP_NO_PART_ESIGN(String templateNo, String partNo,
		int partType, boolean eSign) {
		return getPersistence()
				   .countByTP_NO_PART_ESIGN(templateNo, partNo, partType, eSign);
	}

	/**
	* Caches the dossier part in the entity cache if it is enabled.
	*
	* @param dossierPart the dossier part
	*/
	public static void cacheResult(DossierPart dossierPart) {
		getPersistence().cacheResult(dossierPart);
	}

	/**
	* Caches the dossier parts in the entity cache if it is enabled.
	*
	* @param dossierParts the dossier parts
	*/
	public static void cacheResult(List<DossierPart> dossierParts) {
		getPersistence().cacheResult(dossierParts);
	}

	/**
	* Creates a new dossier part with the primary key. Does not add the dossier part to the database.
	*
	* @param dossierPartId the primary key for the new dossier part
	* @return the new dossier part
	*/
	public static DossierPart create(long dossierPartId) {
		return getPersistence().create(dossierPartId);
	}

	/**
	* Removes the dossier part with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierPartId the primary key of the dossier part
	* @return the dossier part that was removed
	* @throws NoSuchDossierPartException if a dossier part with the primary key could not be found
	*/
	public static DossierPart remove(long dossierPartId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierPartException {
		return getPersistence().remove(dossierPartId);
	}

	public static DossierPart updateImpl(DossierPart dossierPart) {
		return getPersistence().updateImpl(dossierPart);
	}

	/**
	* Returns the dossier part with the primary key or throws a {@link NoSuchDossierPartException} if it could not be found.
	*
	* @param dossierPartId the primary key of the dossier part
	* @return the dossier part
	* @throws NoSuchDossierPartException if a dossier part with the primary key could not be found
	*/
	public static DossierPart findByPrimaryKey(long dossierPartId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierPartException {
		return getPersistence().findByPrimaryKey(dossierPartId);
	}

	/**
	* Returns the dossier part with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param dossierPartId the primary key of the dossier part
	* @return the dossier part, or <code>null</code> if a dossier part with the primary key could not be found
	*/
	public static DossierPart fetchByPrimaryKey(long dossierPartId) {
		return getPersistence().fetchByPrimaryKey(dossierPartId);
	}

	public static java.util.Map<java.io.Serializable, DossierPart> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the dossier parts.
	*
	* @return the dossier parts
	*/
	public static List<DossierPart> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the dossier parts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierPartModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier parts
	* @param end the upper bound of the range of dossier parts (not inclusive)
	* @return the range of dossier parts
	*/
	public static List<DossierPart> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the dossier parts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierPartModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier parts
	* @param end the upper bound of the range of dossier parts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of dossier parts
	*/
	public static List<DossierPart> findAll(int start, int end,
		OrderByComparator<DossierPart> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier parts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierPartModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier parts
	* @param end the upper bound of the range of dossier parts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of dossier parts
	*/
	public static List<DossierPart> findAll(int start, int end,
		OrderByComparator<DossierPart> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the dossier parts from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of dossier parts.
	*
	* @return the number of dossier parts
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static DossierPartPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DossierPartPersistence, DossierPartPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(DossierPartPersistence.class);

		ServiceTracker<DossierPartPersistence, DossierPartPersistence> serviceTracker =
			new ServiceTracker<DossierPartPersistence, DossierPartPersistence>(bundle.getBundleContext(),
				DossierPartPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}